package main.java.sys.rest.client.services;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SignatureException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import javax.crypto.NoSuchPaddingException;

import com.google.gson.Gson;

import main.java.common.protocols.MessageType;
import main.java.common.protocols.VersionNumber;
import main.java.common.utils.CommonUtils;
import main.java.messages.secure.common.header.SecureCommonHeader;
import main.java.messages.secure.common.key.exchange.SecureCommonKeyExchange;
import main.java.messages.secure.proofwork.SecureProofOfWorkMessage;
import main.java.messages.secure.proofwork.components.SecureProofOfWorkMessageComponents;
import main.java.messages.secure.proofwork.components.solvedblock.SecureProofOfWorkMessageComponentsSolvedBlock;
import main.java.messages.secure.proofwork.components.solvedblock.info.SecureProofOfWorkMessageComponentsSolvedBlockInfo;
import main.java.messages.secure.proofwork.components.solvedblock.signature.SecureProofOfWorkMessageComponentsSolvedBlockSignature;
import main.java.messages.secure.proofwork.dos.mitigation.SecureProofOfWorkMessageDoSMitigation;
import main.java.messages.secure.proofwork.metaheader.SecureProofOfWorkMessageMetaHeader;
import main.java.resources.bid.Bid;
import main.java.resources.block.Block;
import main.java.sys.rest.client.Client;
import main.java.sys.rest.server.auction.messageTypes.MessagePacketClientToServer;
import main.java.sys.rest.server.auction.messageTypes.MessagePacketClientToServerTypes;

public class TryToMineBlockOfOpenBidsService implements Runnable {

	private Client client;
	
	private byte strategyForTryToMineBlockOfBids;

	private byte numBytesToSolveChallengeType;
	
	private Map<Integer, Bid> openBidsMap;
	
	private Map<Integer, Block> minedBlockMap;

	private Gson gson;
	
	
	public TryToMineBlockOfOpenBidsService(Client client,
										   byte strategyForTryToMineBlockOfBids,
										   byte numBytesToSolveChallengeType,
										   Map<Integer, Bid> openBidsList,
										   Map<Integer, Block> minedBlockMap) {
		
		this.client = client;
		this.gson = new Gson();
		
		this.strategyForTryToMineBlockOfBids = strategyForTryToMineBlockOfBids;
		this.numBytesToSolveChallengeType = numBytesToSolveChallengeType;

		this.openBidsMap = openBidsList;
		this.minedBlockMap = minedBlockMap;
		
	}

	@Override
	public void run() {
		System.out.println("Mining service online.");
		for(;;) {
			try {
				Thread.sleep(CommonUtils.TRY_TO_CLOSE_BLOCK_OF_BIDS_SERVICE_VERIFICATION_RATE_TIME);
			}
			catch (InterruptedException interruptedException) {
				interruptedException.printStackTrace();
			}
			if(openBidsMap != null && !openBidsMap.isEmpty()) {
				System.out.println("Trying to mine!");

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
				
				
				int difficultyToSolveChallenge = 0;
				
				switch(this.numBytesToSolveChallengeType) {
					
					case 0x01:
						difficultyToSolveChallenge = 1;
						break;
						
					case 0x02:
						difficultyToSolveChallenge = 2;
						break;
						
					case 0x03:
						difficultyToSolveChallenge = 3;
						break;
						
					case 0x04:
						difficultyToSolveChallenge = 4;
						break;
						
					default:
						difficultyToSolveChallenge = 0;
						break;
						
				}
				
				
				Block blockOfOpenBidsForChallenge = new Block( currentBlockID, previousBlockHashed,
															   ( (Bid[]) chosenOpenBidsToMineList.toArray() ),
															   this.strategyForTryToMineBlockOfBids,
															   difficultyToSolveChallenge );
				
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
				
				try {
					
					SecureProofOfWorkMessageComponentsSolvedBlockInfo
							secureProofOfWorkMessageComponentsSolvedBlockInfo = 
									new SecureProofOfWorkMessageComponentsSolvedBlockInfo
											(blockOfOpenBidsForChallenge, blockOfOpenBidsForChallengeSerializedHashed);
		
					secureProofOfWorkMessageComponentsSolvedBlockInfo
							.doBlockSerializedAndSolvedHashed();
			
					SecureProofOfWorkMessageComponentsSolvedBlockSignature secureProofOfWorkMessageComponentsSolvedBlockSignature = 
							new SecureProofOfWorkMessageComponentsSolvedBlockSignature
									(secureProofOfWorkMessageComponentsSolvedBlockInfo,
									 clientUserID);
					
					secureProofOfWorkMessageComponentsSolvedBlockSignature
							.signSecureProofOfWorkMessageComponentsSolvedBlock();
					
					
					SecureProofOfWorkMessageComponentsSolvedBlock secureProofOfWorkMessageComponentsSolvedBlock =
							new SecureProofOfWorkMessageComponentsSolvedBlock
									(secureProofOfWorkMessageComponentsSolvedBlockInfo,
									 secureProofOfWorkMessageComponentsSolvedBlockSignature);
					
					secureProofOfWorkMessageComponentsSolvedBlock
							.doSecureProofOfWorkMessageComponentsSolvedBlockSerialization();
					
					
					SecureCommonHeader secureCommonHeader = 
							new SecureCommonHeader(
									VersionNumber.VERSION_01.getVersionNumber(), 
									MessageType.MESSAGE_TYPE_3.getMessageType(),
									System.currentTimeMillis());
					
					secureCommonHeader.doSecureCommonHeaderSerialization();
					
					
					SecureProofOfWorkMessageComponents secureProofOfWorkMessageComponents =
							new SecureProofOfWorkMessageComponents
									(secureCommonHeader,
									 secureProofOfWorkMessageComponentsSolvedBlock);
					
					secureProofOfWorkMessageComponents.doSecureProofOfWorkMessageComponentsSerialization();
					
					
					SecureProofOfWorkMessageDoSMitigation secureProofOfWorkMessageDoSMitigation = 
							new SecureProofOfWorkMessageDoSMitigation(secureProofOfWorkMessageComponents);
					
					byte[] initialisationVectorInBytes = secureProofOfWorkMessageComponents.getInitialisationVector();
					
					byte[] secretSymmetricKeyInBytes = secureProofOfWorkMessageComponents
													   .getSecretSymmetricKeyForProofOfWorkMessageComponentsInBytes();
					
					byte[] secretHMACKeyForDoSMitigationInBytes = secureProofOfWorkMessageDoSMitigation
																  .getSecretHMACKeyForDoSMitigationInBytes();
					
					SecureCommonKeyExchange secureProofOfWorkMessageKeyExchange = 
							new SecureCommonKeyExchange(secretSymmetricKeyInBytes,
														secretHMACKeyForDoSMitigationInBytes,
													    clientUserID);
					
					secureProofOfWorkMessageKeyExchange.buildSecureCommonKeyExchangeToSend();
					
					byte[] secureBidMessageKeyExchangeSerializedCiphered = 
							secureProofOfWorkMessageKeyExchange.getSecureCommonKeyExchangeSerializedCiphered();
					byte[] secureBidMessageKeyExchangeSerializedCipheredSigned = 
							secureProofOfWorkMessageKeyExchange.getSecureCommonKeyExchangeSerializedCipheredSigned();
					
					byte[] secureProofOfWorkMessageComponentsSolvedBlockSerialized = 
							secureProofOfWorkMessageComponentsSolvedBlock.getSecureProofOfWorkMessageComponentsSolvedBlockSerialized();
					byte[] secureProofOfWorkMessageDoSMitigationSerialized = 
							secureProofOfWorkMessageDoSMitigation.getSecureProofOfWorkMessageComponentsHashedForDoSMitigation();
					
					byte[] secureBidMessageComponentsSolvedBlockInfoSerialized =
							secureProofOfWorkMessageComponentsSolvedBlockInfo.getBlockSerializedAndSolvedHashed();
					byte[] secureBidMessageComponentsSolvedBlockSignatureSerialized = 
								secureProofOfWorkMessageComponentsSolvedBlockSignature
										.getSecureProofOfWorkMessageComponentsSolvedBlockInfoDigitalSigned();
					
					
					byte[] blockAndBlockSolvedHashedSerialized = 
							secureProofOfWorkMessageComponentsSolvedBlockInfo.getBlockSerializedAndSolvedHashed();
					byte[] blockSerialized = secureProofOfWorkMessageComponentsSolvedBlockInfo.getBlockSerialized();
					byte[] blockSolvedHashed = secureProofOfWorkMessageComponentsSolvedBlockInfo.getBlockSolvedHashed();
							
					SecureProofOfWorkMessageMetaHeader secureProofOfWorkMessageMetaHeader = 
								new SecureProofOfWorkMessageMetaHeader	
											(clientUserID.getBytes("UTF-8").length,
											 secureBidMessageKeyExchangeSerializedCiphered.length,
											 secureBidMessageKeyExchangeSerializedCipheredSigned.length,
											 secureProofOfWorkMessageComponentsSolvedBlockSerialized.length,
											 secureProofOfWorkMessageDoSMitigationSerialized.length,
											 secureBidMessageComponentsSolvedBlockInfoSerialized.length,
											 secureBidMessageComponentsSolvedBlockSignatureSerialized.length,
										 	 blockAndBlockSolvedHashedSerialized.length,
											 blockSerialized.length,
											 blockSolvedHashed.length);
					
					secureProofOfWorkMessageMetaHeader.doSecureProofOfWorkMessageMetaHeaderSerialization();
					
					
					SecureProofOfWorkMessage secureProofOfWorkMessage = 
							new SecureProofOfWorkMessage(initialisationVectorInBytes,
														 secureProofOfWorkMessageMetaHeader, 
														 clientUserID,
														 secureProofOfWorkMessageKeyExchange,
														 secureProofOfWorkMessageComponents,
														 secureProofOfWorkMessageDoSMitigation);
					
					secureProofOfWorkMessage.doSecureProofOfWorkMessageSerialized();
					
					String proofOfWorkInfoSerialiazed = gson.toJson(secureProofOfWorkMessage);
					//HashMap<String,String> paramsMap = new HashMap<String, String>();
					//paramsMap.put("auction-id", auctionID); //TODO - confirmar
					MessagePacketClientToServer message = 
							new MessagePacketClientToServer(MessagePacketClientToServerTypes.PROOF_WORK_SENT, 
															null, proofOfWorkInfoSerialiazed);
					this.client.sendMessage(message);
					
				}
				catch (InvalidKeyException e) {
					e.printStackTrace();
				}
				catch (SignatureException e) {
					e.printStackTrace();
				}
				catch (NoSuchAlgorithmException e) {
					e.printStackTrace();
				}
				catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				catch (NoSuchProviderException e) {
					e.printStackTrace();
				}
				catch (NoSuchPaddingException e) {
					e.printStackTrace();
				}
				catch (InvalidAlgorithmParameterException e) {
					e.printStackTrace();
				}

				// TODO - Broadcast of ProofOfWorkMessage (made by the Server)
				
			}

		}
	}
}
