package main.java.sys.rest.client.services;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import main.java.common.utils.CommonUtils;
import main.java.resources.bid.Bid;
import main.java.resources.block.BlockOld;
import main.java.resources.cryptopuzzle.CryptoPuzzleSolverForProofOfWork;

public class TryToCloseBlockOfOpenBidsService implements Runnable {

	private byte strategyForTryToCloseBlockOfBids;

	private byte numBytesToSolveChallengeType;
	
	private List<Bid> openBidsList;

	
	public TryToCloseBlockOfOpenBidsService(byte strategyForTryToCloseBlockOfBids,
											byte numBytesToSolveChallengeType,
											List<Bid> openBidsList) {

		this.strategyForTryToCloseBlockOfBids = strategyForTryToCloseBlockOfBids;
		this.numBytesToSolveChallengeType = numBytesToSolveChallengeType;

		this.openBidsList = openBidsList;

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

			List<Bid> openBidsToMineList = this.openBidsList.stream()
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


			BlockOld blockOfOpenBidsForChallenge = new BlockOld( ( (Bid[]) chosenOpenBidsToMineList.toArray() ) );

			try {

				CryptoPuzzleSolverForProofOfWork cryptoPuzzleSolverForProofOfWork = 
						new CryptoPuzzleSolverForProofOfWork( this.strategyForTryToCloseBlockOfBids, 
														      this.numBytesToSolveChallengeType,
															  blockOfOpenBidsForChallenge );

				cryptoPuzzleSolverForProofOfWork.solveBlockChallenge();

			}
			catch (NoSuchAlgorithmException noSuchAlgorithmException) {
				noSuchAlgorithmException.printStackTrace();
			}
			
			// TODO - Broadcast of ProofOfWorkMessage
			
			
			
		}
	}
}
