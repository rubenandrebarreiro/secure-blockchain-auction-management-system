package main.java.sys.rest.client.services;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.security.cert.Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import main.java.common.protocols.MessageType;
import main.java.common.protocols.VersionNumber;
import main.java.common.utils.CommonUtils;
import main.java.messages.secure.common.header.SecureCommonHeader;
import main.java.messages.secure.common.key.exchange.SecureCommonKeyExchange;
import main.java.messages.secure.proofwork.components.solvedblock.SecureProofOfWorkMessageComponentsSolvedBlock;
import main.java.messages.secure.proofwork.components.solvedblock.confidential.SecureProofOfWorkMessageComponentsSolvedBlockConfidential;
import main.java.messages.secure.proofwork.components.solvedblock.signature.SecureProofOfWorkMessageComponentsSolvedBlockSignature;
import main.java.resources.bid.Bid;
import main.java.resources.block.Block;
import main.java.sys.rest.client.Client;
import main.java.sys.rest.server.auction.messageTypes.MessagePacketServerToClient;
import main.java.sys.rest.server.auction.messageTypes.MessagePacketClientToServer;
import main.java.sys.rest.server.auction.messageTypes.MessagePacketClientToServerTypes;

public class TryToMineBlockOfOpenBidsService implements Runnable {

	private Client client;
	
	private byte strategyForTryToMineBlockOfBids;

	private byte numBytesToSolveChallengeType;
	
	private Map<Integer, Bid> openBidsMap;
	
	private Map<Integer, Block> minedBlockMap;
	
	private Certificate auctionServerCertificate;
	
	
	public TryToMineBlockOfOpenBidsService(Client client,
										   byte strategyForTryToMineBlockOfBids,
										   byte numBytesToSolveChallengeType,
										   Map<Integer, Bid> openBidsList,
										   Map<Integer, Block> minedBlockMap,
										   Certificate auctionServerCertificate) {

		this.strategyForTryToMineBlockOfBids = strategyForTryToMineBlockOfBids;
		this.numBytesToSolveChallengeType = numBytesToSolveChallengeType;

		this.openBidsMap = openBidsList;
		this.minedBlockMap = minedBlockMap;

		this.auctionServerCertificate = auctionServerCertificate;
		
	}

	@Override
	public void run() {
		for(;;) {
			try {
				Thread.sleep(CommonUtils.TRY_TO_CLOSE_BLOCK_OF_BIDS_SERVICE_VERIFICATION_RATE_TIME);
			}
			catch (InterruptedException interruptedException) {
				interruptedException.printStackTrace();
			}

			List<Bid> openBidsToMineList = this.openBidsMap.values().stream()
					.filter(bid -> !bid.getIsBidMined())
					.collect(Collectors.toList());


			int numPossibleOpenBidsToMine = openBidsToMineList.size();

			int numChosenOpenBidsToMine = 0;

			List<Bid> chosenOpenBidsToMineList = new ArrayList<Bid>();

			Random random = new Random();

			int numOpenBidsToMine = 0;

			while(numOpenBidsToMine == 0) {
				numOpenBidsToMine = random.nextInt(CommonUtils.MAX_NUM_BIDS_TO_TRY_TO_MINE);
			}


			if(numOpenBidsToMine <= numPossibleOpenBidsToMine) {

				while(numChosenOpenBidsToMine < numOpenBidsToMine) {

					int chosenBidToMineIndex = random.nextInt(numPossibleOpenBidsToMine);

					Bid chosenBidToMine = openBidsToMineList.get(chosenBidToMineIndex);

					if(!chosenOpenBidsToMineList.contains(chosenBidToMine)) {

						chosenOpenBidsToMineList.add(chosenBidToMine);

					}

					numChosenOpenBidsToMine++;

				}

			}

			int previousBlockID = ( this.minedBlockMap.size() - 1 );
			int currentBlockID = ( previousBlockID + 1 );
			
			Block previousBlock = this.minedBlockMap.get(previousBlockID);
			byte[] previousBlockHashed = previousBlock.getBlockSerializedHashed();
			
			
			Block blockOfOpenBidsForChallenge = new Block( currentBlockID, previousBlockHashed, ( (Bid[]) chosenOpenBidsToMineList.toArray() ), this.strategyForTryToMineBlockOfBids, this.numBytesToSolveChallengeType );
			
			blockOfOpenBidsForChallenge.doBidsOfCurrentBlockToTryToMineSerialization();
			
			
			try {
				
				blockOfOpenBidsForChallenge.startProcessToTryToSolveBlockHashChallenge();
				blockOfOpenBidsForChallenge.tryToSolveBlockHashChallenge();
				
			}
			catch (NoSuchAlgorithmException noSuchAlgorithmException) {
				
				noSuchAlgorithmException.printStackTrace();
				
			}
			
			String clientUserID = this.client.getClientUser().getUserPeerID();
			
			byte[] blockOfOpenBidsForChallengeSerializedHashed = 
					blockOfOpenBidsForChallenge.getBlockSerializedHashed();
			
			SecureProofOfWorkMessageComponentsSolvedBlockConfidential
					secureProofOfWorkMessageComponentsSolvedBlockConfidential = 
							new SecureProofOfWorkMessageComponentsSolvedBlockConfidential
									(blockOfOpenBidsForChallenge, blockOfOpenBidsForChallengeSerializedHashed);
			
			secureProofOfWorkMessageComponentsSolvedBlockConfidential
					.buildSecureProofOfWorkMessageComponentsSolvedBlockConfidentialToSend();
			
			SecureProofOfWorkMessageComponentsSolvedBlockSignature secureProofOfWorkMessageComponentsSolvedBlockSignature = 
					new SecureProofOfWorkMessageComponentsSolvedBlockSignature
							(secureProofOfWorkMessageComponentsSolvedBlockConfidential,
							 clientUserID);
			
			try {
				secureProofOfWorkMessageComponentsSolvedBlockSignature
					.signSecureProofOfWorkMessageComponentsSolvedBlock();
			} catch (InvalidKeyException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SignatureException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (NoSuchAlgorithmException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
			try {
				SecureProofOfWorkMessageComponentsSolvedBlock secureProofOfWorkMessageSolvedBlock =
						new SecureProofOfWorkMessageComponentsSolvedBlock
								(secureProofOfWorkMessageComponentsSolvedBlockConfidential,
								 secureProofOfWorkMessageComponentsSolvedBlockSignature);
			} catch (InvalidKeyException | SignatureException | NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			SecureCommonHeader secureCommonHeader = 
					new SecureCommonHeader(
							VersionNumber.VERSION_01.getVersionNumber(), 
							MessageType.MESSAGE_TYPE_3.getMessageType(),
							System.currentTimeMillis());
			
			SecureCommonKeyExchange secureCommonKeyExchange = 
					new SecureCommonKeyExchange(blockOfOpenBidsForChallengeSerializedHashed,
											    blockOfOpenBidsForChallengeSerializedHashed,
											    clientUserID);
			
			//SecureProofOfWorkMessage secureProofOfWorkMessage = new SecureProofOfWorkMessage();
			
			
			// TODO - Send to the Server
			/*this.client.sendMessage
				(new MessagePacketClientToServer(MessagePacketClientToServerTypes.PROOF_WORK_SENT,
					                             null, ));
			
			*/
			
			// TODO - Broadcast of ProofOfWorkMessage (made by the Server)
			
			
			
		}
	}
}
