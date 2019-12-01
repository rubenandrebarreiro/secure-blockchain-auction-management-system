package main.java.resources.cryptopuzzle;

import java.security.NoSuchAlgorithmException;

import java.util.Random;

import org.bouncycastle.util.Arrays;

import main.java.common.utils.CommonUtils;
import main.java.resources.block.Block;

public class CryptoPuzzleSolverForProofOfWork {
	
	
	private byte strategyUsedForCryptoPuzzleSolverForProofOfWork;
	
	private Block blockOfOpenBidsForChallengeToCheck;
	
	private byte[] blockOfOpenBidsForChallengeSerializedHashedToMine;
	
	private int numBidsInTheBlockOfOpenBidsForChallenge;
	
	
	public CryptoPuzzleSolverForProofOfWork(byte strategyUsedForCryptoPuzzleSolverForProofOfWork,
											Block blockOfOpenBidsForChallengeToCheck) throws NoSuchAlgorithmException {
		
		this.strategyUsedForCryptoPuzzleSolverForProofOfWork = strategyUsedForCryptoPuzzleSolverForProofOfWork;
		this.blockOfOpenBidsForChallengeToCheck = blockOfOpenBidsForChallengeToCheck;
		
		this.numBidsInTheBlockOfOpenBidsForChallenge = this.blockOfOpenBidsForChallengeToCheck.getBidsToMine().length;
		
		this.initializeBlockOfOpenBidsChallenge();
		
	}
	
	public byte getStrategyUsedForCryptoPuzzleSolverForProofOfWork() {
		return this.strategyUsedForCryptoPuzzleSolverForProofOfWork;
	}
	
	public Block getBlockOfOpenBidsForChallengeToCheck() {
		return this.blockOfOpenBidsForChallengeToCheck;
	}
	
	public byte[] getBlockOfOpenBidsForChallengeSerializedHashedToMine() {
		return this.blockOfOpenBidsForChallengeSerializedHashedToMine;
	}
	
	public int getNumBidsInTheBlockOfOpenBidsForChallenge() {
		return this.numBidsInTheBlockOfOpenBidsForChallenge;
	}
	
	private void initializeBlockOfOpenBidsChallenge() throws NoSuchAlgorithmException {
		
		this.blockOfOpenBidsForChallengeToCheck.doBlockSerialization();
		this.blockOfOpenBidsForChallengeToCheck.doHashOfBlockOfBidsToMine();
		
		this.blockOfOpenBidsForChallengeSerializedHashedToMine = this.blockOfOpenBidsForChallengeToCheck
															         .getBlockSerializedHashed();
		
		int sizeOfBlockOfOpenBidsForChallengeSerializedHashedToMine = 
							this.blockOfOpenBidsForChallengeSerializedHashedToMine.length;
		
		this.blockOfOpenBidsForChallengeSerializedHashedToMine
			 [sizeOfBlockOfOpenBidsForChallengeSerializedHashedToMine - 2] = (byte) 0;
		this.blockOfOpenBidsForChallengeSerializedHashedToMine
		     [sizeOfBlockOfOpenBidsForChallengeSerializedHashedToMine - 1] = (byte) 0;
        
	}
	
	public void solveBlockChallenge() {
		
		byte[] blockOfOpenBidsForChallengeSerializedAndHashedToCheck = this.blockOfOpenBidsForChallengeToCheck
															    		   .getBlockSerializedHashed();
	
		byte[] blockOfOpenBidsForChallengeSerializedHashedToMine = 
							this.blockOfOpenBidsForChallengeSerializedHashedToMine;
		
		System.out.println();
		System.out.println();
		System.out.println();
		
		System.out.println(String.format("The correct answer to the Block's Challenge:\n- %s",
						   CommonUtils.fromByteArrayToHexadecimalFormat
						  (blockOfOpenBidsForChallengeSerializedAndHashedToCheck)));
		
		System.out.println();
		
		System.out.println(String.format("The initial Block's Challenge to be solved:\n- %s",
						   CommonUtils.fromByteArrayToHexadecimalFormat
						  (blockOfOpenBidsForChallengeSerializedHashedToMine)));
		
		System.out.println();
		System.out.println();
		System.out.println();
		
		
		switch(this.strategyUsedForCryptoPuzzleSolverForProofOfWork) {
			
			case 0:
				
				this.solveChallengeSequential();
				
				break;
			
			
			case 1:
				
				this.solveChallengeInverseSequential();
				
				break;
				
				
			case 2:
		
				this.solveChallengeRandomly();
				
				break;
				
		}
		
		System.out.println();
		System.out.println(String.format("It was found the correct answer for the Challenge!!!"));
		
		
	}
	
	private void solveChallengeSequential() {
		
		byte[] blockOfOpenBidsForChallengeSerializedAndHashedToCheck = this.blockOfOpenBidsForChallengeToCheck
	    		   .getBlockSerializedHashed();

		byte[] blockOfOpenBidsForChallengeSerializedHashedToMine = 
				   this.blockOfOpenBidsForChallengeSerializedHashedToMine;

		
		boolean hashCollisionFound = false;
		
		while(!hashCollisionFound) {

			for(int attempt1st = 0; attempt1st < CommonUtils.NUM_ASCII_TABLE_POSSIBILITIES_IN_CHALLENGE; attempt1st++) {
			
				for(int attempt2nd = 0; attempt2nd < CommonUtils.NUM_ASCII_TABLE_POSSIBILITIES_IN_CHALLENGE; attempt2nd++) {
				
					System.out.println(String.format("The correct answer to the Block's Challenge:\n- %s",
									   CommonUtils.fromByteArrayToHexadecimalFormat
									  (blockOfOpenBidsForChallengeSerializedAndHashedToCheck)));
					
					System.out.println();
					
					int sizeOfBlockOfOpenBidsForChallengeSerializedHashedToMine = 
									blockOfOpenBidsForChallengeSerializedHashedToMine.length;
					
					blockOfOpenBidsForChallengeSerializedHashedToMine
						[sizeOfBlockOfOpenBidsForChallengeSerializedHashedToMine - 2] = 
						 CommonUtils.fromCharToByte( (char) attempt1st );
					
					blockOfOpenBidsForChallengeSerializedHashedToMine
						[sizeOfBlockOfOpenBidsForChallengeSerializedHashedToMine - 1] =
						 CommonUtils.fromCharToByte( (char) attempt2nd );
						
			
					System.out.println(String.format("The current answer to the Block's Challenge:\n- %s",
							   		   CommonUtils.fromByteArrayToHexadecimalFormat
							   		  (blockOfOpenBidsForChallengeSerializedHashedToMine)));
					
					
					hashCollisionFound = Arrays.areEqual
										(blockOfOpenBidsForChallengeSerializedAndHashedToCheck,
	 									 blockOfOpenBidsForChallengeSerializedHashedToMine);
						
					if(hashCollisionFound) {
						break;
					}
						
				}
						
				if(hashCollisionFound) {
					break;
				}
				
			}
		}
	}
	
	private void solveChallengeInverseSequential() {
		
		byte[] blockOfOpenBidsForChallengeSerializedAndHashedToCheck = this.blockOfOpenBidsForChallengeToCheck
	    		   .getBlockSerializedHashed();

		byte[] blockOfOpenBidsForChallengeSerializedHashedToMine = 
				   this.blockOfOpenBidsForChallengeSerializedHashedToMine;

		
		boolean hashCollisionFound = false;
		
		while(!hashCollisionFound) {

			for(int attempt1st = 0; attempt1st < CommonUtils.NUM_ASCII_TABLE_POSSIBILITIES_IN_CHALLENGE; attempt1st++) {
			
				for(int attempt2nd = 0; attempt2nd < CommonUtils.NUM_ASCII_TABLE_POSSIBILITIES_IN_CHALLENGE; attempt2nd++) {
				
					System.out.println(String.format("The correct answer to the Block's Challenge:\n- %s",
									   CommonUtils.fromByteArrayToHexadecimalFormat
									  (blockOfOpenBidsForChallengeSerializedAndHashedToCheck)));
					
					System.out.println();
					
					int sizeOfBlockOfOpenBidsForChallengeSerializedHashedToMine = 
									blockOfOpenBidsForChallengeSerializedHashedToMine.length;
					
					blockOfOpenBidsForChallengeSerializedHashedToMine
						[sizeOfBlockOfOpenBidsForChallengeSerializedHashedToMine - 2] = 
						 CommonUtils.fromCharToByte( (char) attempt2nd );
					
					blockOfOpenBidsForChallengeSerializedHashedToMine
						[sizeOfBlockOfOpenBidsForChallengeSerializedHashedToMine - 1] =
						 CommonUtils.fromCharToByte( (char) attempt1st );
						
			
					System.out.println(String.format("The current answer to the Block's Challenge:\n- %s",
							   		   CommonUtils.fromByteArrayToHexadecimalFormat
							   		  (blockOfOpenBidsForChallengeSerializedHashedToMine)));
					
					
					hashCollisionFound = Arrays.areEqual
										(blockOfOpenBidsForChallengeSerializedAndHashedToCheck,
	 									 blockOfOpenBidsForChallengeSerializedHashedToMine);
						
					if(hashCollisionFound) {
						break;
					}
						
				}
						
				if(hashCollisionFound) {
					break;
				}
				
			}
		}
		
	}
	
	private void solveChallengeRandomly() {
		
		byte[] blockOfOpenBidsForChallengeSerializedAndHashedToCheck = this.blockOfOpenBidsForChallengeToCheck
	    		   .getBlockSerializedHashed();

		byte[] blockOfOpenBidsForChallengeSerializedHashedToMine = 
				   this.blockOfOpenBidsForChallengeSerializedHashedToMine;

		
		boolean hashCollisionFound = false;
		
		Random random = new Random();
		
		while(!hashCollisionFound) {
			
			int attempt1st = random.nextInt(CommonUtils.NUM_ASCII_TABLE_POSSIBILITIES_IN_CHALLENGE);
			
			int attempt2nd = random.nextInt(CommonUtils.NUM_ASCII_TABLE_POSSIBILITIES_IN_CHALLENGE);
			
			
			System.out.println(String.format("The correct answer to the Block's Challenge:\n- %s",
							   CommonUtils.fromByteArrayToHexadecimalFormat
							  (blockOfOpenBidsForChallengeSerializedAndHashedToCheck)));
			
			System.out.println();
			
			int sizeOfBlockOfOpenBidsForChallengeSerializedHashedToMine = 
							blockOfOpenBidsForChallengeSerializedHashedToMine.length;
			
			blockOfOpenBidsForChallengeSerializedHashedToMine
				[sizeOfBlockOfOpenBidsForChallengeSerializedHashedToMine - 2] = 
				 CommonUtils.fromCharToByte( (char) attempt1st );
			
			blockOfOpenBidsForChallengeSerializedHashedToMine
				[sizeOfBlockOfOpenBidsForChallengeSerializedHashedToMine - 1] =
				 CommonUtils.fromCharToByte( (char) attempt2nd );
				
	
			System.out.println(String.format("The current answer to the Block's Challenge:\n- %s",
					   		   CommonUtils.fromByteArrayToHexadecimalFormat
					   		  (blockOfOpenBidsForChallengeSerializedHashedToMine)));
			
			
			hashCollisionFound = Arrays.areEqual
								(blockOfOpenBidsForChallengeSerializedAndHashedToCheck,
								 blockOfOpenBidsForChallengeSerializedHashedToMine);
				
		}
		
	}
}
