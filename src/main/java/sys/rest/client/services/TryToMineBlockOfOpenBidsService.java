package main.java.sys.rest.client.services;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import main.java.common.utils.CommonUtils;
import main.java.resources.bid.Bid;
import main.java.resources.block.Block;

public class TryToMineBlockOfOpenBidsService implements Runnable {

	private byte strategyForTryToMineBlockOfBids;

	private byte numBytesToSolveChallengeType;
	
	private Map<Integer, Bid> openBidsMap;
	
	private Map<Integer, Block> minedBlockMap;
	
	
	public TryToMineBlockOfOpenBidsService(byte strategyForTryToMineBlockOfBids,
										   byte numBytesToSolveChallengeType,
										   Map<Integer, Bid> openBidsList,
										   Map<Integer, Block> minedBlockMap) {

		this.strategyForTryToMineBlockOfBids = strategyForTryToMineBlockOfBids;
		this.numBytesToSolveChallengeType = numBytesToSolveChallengeType;

		this.openBidsMap = openBidsList;
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
			
			
			// TODO - Broadcast of ProofOfWorkMessage
			
			
			
		}
	}
}
