package main.java.sys.rest.server.auction.services;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import main.java.common.utils.CommonUtils;
import main.java.resources.bid.Bid;
import main.java.resources.block.Block;
import main.java.resources.cryptopuzzle.CryptoPuzzleSolverForProofOfWork;
import main.java.resources.user.User;

public class AuctionServerTryToCloseBlockOfBidsIfUsersAreBusyService implements Runnable {

	private byte strategyForTryToCloseBlockOfBids;
	
	private List<User> usersList;
	
	private List<Bid> openBidsList;
	
	
	public AuctionServerTryToCloseBlockOfBidsIfUsersAreBusyService(byte strategyForTryToCloseBlockOfBids,
															 	   List<User> usersList, List<Bid> openBidsList) {
		
		this.strategyForTryToCloseBlockOfBids = strategyForTryToCloseBlockOfBids;
		this.usersList = usersList;
		this.openBidsList = openBidsList;
		
	}
	
	
	@Override
	public void run() {
		for(;;) {
			try {
				Thread.sleep(CommonUtils.TRY_TO_CLOSE_BIDS_IF_USERS_ARE_BUSY_SERVICE_VERIFICATION_RATE_TIME);
			}
			catch (InterruptedException interruptedException) {
				interruptedException.printStackTrace();
			}
			
			
			boolean tryToCloseBids = true;
			
			for(User user : usersList) {
				
				if(!user.getIsSolvingChallenge()) {
					
					tryToCloseBids = false;
					
					break;
					
				}
				
			}
			
			
			if(tryToCloseBids) {
				
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
				
				
				Block blockOfOpenBidsForChallenge = new Block( ( (Bid[]) chosenOpenBidsToMineList.toArray() ) );
				
				try {
					
					CryptoPuzzleSolverForProofOfWork cryptoPuzzleSolverForProofOfWork = 
							new CryptoPuzzleSolverForProofOfWork( this.strategyForTryToCloseBlockOfBids, 
																  CommonUtils.NUM_BYTES_TO_SOLVE_CHALLENGE,
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
	
}
