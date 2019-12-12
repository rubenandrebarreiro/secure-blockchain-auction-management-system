package main.java.sys.rest.server.auction.services;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import main.java.common.utils.CommonUtils;
import main.java.resources.bid.Bid;
import main.java.resources.block.Block;
import main.java.resources.user.User;

public class TryToCloseBlockOfOpenBidsServiceByAuctionServerIfClientsAreBusy implements Runnable {

	private byte strategyForTryToMineBlockOfBids;

	private byte numBytesToSolveChallengeType;
	
	
	private Map<Integer, User> usersList;
	
	private Map<Integer, Bid> openBidsList;
	
	private Map<Integer, Block> minedBlockMap;
	
	
	
	public TryToCloseBlockOfOpenBidsServiceByAuctionServerIfClientsAreBusy(byte strategyForTryToCloseBlockOfBids,
																           byte numBytesToSolveChallengeType,
																           Map<Integer, User> usersList,
																           Map<Integer, Bid> openBidsList,
																		   Map<Integer, Block> minedBlockMap) {
		
	    this.strategyForTryToMineBlockOfBids = strategyForTryToCloseBlockOfBids;
		this.numBytesToSolveChallengeType = numBytesToSolveChallengeType;
		
		this.usersList = usersList;
		this.openBidsList = openBidsList;
		this.minedBlockMap = minedBlockMap;
		
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
			
			
			boolean tryToCloseBids = true;
			
			for(User user : usersList.values()) {
				
				if(!user.getIsSolvingChallenge()) {
					
					tryToCloseBids = false;
					
					break;
					
				}
				
			}
			
			
			if(tryToCloseBids) {
				
				List<Bid> openBidsToMineList = this.openBidsList.values().stream()
												   .filter(bid -> !bid.getIsBidMined())
												   .collect(Collectors.toList());
				
				
				int numPossibleOpenBidsToMine = openBidsToMineList.size();
				
				int numChosenOpenBidsToMine = 0;
				
				List<Bid> chosenOpenBidsToMineList = new ArrayList<Bid>();
				
				Random random = new Random();
				
				int numOpenBidsToMine = 0;
				
				while( (numOpenBidsToMine == 0) || 
					   (numOpenBidsToMine < CommonUtils.MIN_NUM_BIDS_TO_TRY_TO_MINE) ) {
					
					numOpenBidsToMine = random.nextInt(CommonUtils.MAX_NUM_BIDS_TO_TRY_TO_MINE);
				
				}
				
				
				if(numOpenBidsToMine < numPossibleOpenBidsToMine) {
					
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
				
				
				Block blockOfOpenBidsForChallenge = 
						new Block( currentBlockID, previousBlockHashed, 
								   ( (Bid[]) chosenOpenBidsToMineList.toArray() ), 
								   this.strategyForTryToMineBlockOfBids, this.numBytesToSolveChallengeType );
				
				blockOfOpenBidsForChallenge.doBidsOfCurrentBlockToTryToMineSerialization();
				
				
				try {
					
					blockOfOpenBidsForChallenge.startProcessToTryToSolveBlockHashChallenge();
					blockOfOpenBidsForChallenge.tryToSolveBlockHashChallenge();
					
				}
				catch (NoSuchAlgorithmException noSuchAlgorithmException) {
					
					noSuchAlgorithmException.printStackTrace();
					
				}
				
				
				// TODO - Broadcast of ProofOfWorkMessage
				
				
				
			}
			
		}
	}
	
}
