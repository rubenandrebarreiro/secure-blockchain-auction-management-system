package main.java.resources.block;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;

import main.java.common.utils.CommonUtils;
import main.java.resources.bid.Bid;

public class Block {

	private int blockID;
	
	
	private byte[] previousBlockHashed;
	
	
	private Bid[] bidsOfCurrentBlockToTryToMine;
	
	private byte[] bidsOfCurrentBlockToTryToMineSerialized;
	
	private int sizeOfBidsOfCurrentBlockToTryToMineSerialized;
	
	private boolean areBidsOfCurrentBlockToTryToMineSerialized;
	
	
	private byte strategyForCryptoPuzzle;
	
	private SecureRandom secureRandom;
	
	private int nonce;
	
	
	private byte[] blockSerialized;
	
	private int sizeOfBlockSerialized;
	
	private boolean isBlockSerialized;
	
	
	private byte[] blockSerializedHashed;
	
	private boolean isBlockSerializedHashed;
	
	
	private boolean isBlockMined;
	
	
	public Block() {
		
	}
	
	
	
	
	public int getBlockID() {
		return this.blockID;
	}
	
	
	public byte[] getPreviousBlockHashed() {
		return this.previousBlockHashed;
	}
	
	
	public Bid[] getBidsOfCurrentBlockToTryToMine() {
		return this.bidsOfCurrentBlockToTryToMine;
	}
	
	public byte[] getBidsOfCurrentBlockToTryToMineSerialized() {
		return this.bidsOfCurrentBlockToTryToMineSerialized;
	}
	
	public int getSizeOfBidsOfCurrentBlockToTryToMineSerialized() {
		return this.sizeOfBidsOfCurrentBlockToTryToMineSerialized;
	}
	
	public boolean getAreBidsOfCurrentBlockToTryToMineSerialized() {
		return this.areBidsOfCurrentBlockToTryToMineSerialized;
	}

	public void setAreBidsOfCurrentBlockToTryToMineSerialized(boolean areBidsOfCurrentBlockToTryToMineSerialized) {
		this.areBidsOfCurrentBlockToTryToMineSerialized = areBidsOfCurrentBlockToTryToMineSerialized;
	}
	
	public byte getStrategyForCryptoPuzzle() {
		return this.strategyForCryptoPuzzle;
	}
	
	public int getNonce() {
		return this.nonce;
	}
	
	
	public byte[] getBlockSerialized() {
		return this.blockSerialized;
	}
	
	public int getSizeOfBlockSerialized() {
		return this.sizeOfBlockSerialized;
	}
	
	public boolean getIsBlockSerialized() {
		return this.isBlockSerialized;
	}
	
	public void setIsBlockSerialized(boolean isBlockSerialized) {
		this.isBlockSerialized = isBlockSerialized;
	}
	
	public byte[] getBlockSerializedHashed() {
		return this.blockSerializedHashed;
	}
	
	public boolean getIsBlockSerializedHashed() {
		return this.isBlockSerializedHashed;
	}
	
	public void setIsBlockSerializedHashed(boolean isBlockSerializedHashed) {
		this.isBlockSerializedHashed = isBlockSerializedHashed;
	}
	
	public boolean getIsBlockMined() {
		return this.isBlockMined;
	}
	
	public void setIsBlockMined(boolean isBlockMined) {
		this.isBlockMined = isBlockMined;
	}
	
	public void startProcessToTryToSolveBlockHashChallenge() throws NoSuchAlgorithmException {
		
		this.setIsBlockSerialized(false);
		this.setIsBlockSerializedHashed(false);
		
		switch(this.getStrategyForCryptoPuzzle()) {
			
			case 1:
				this.nonce++;
				break;
			
			case 2:
				this.nonce--;
				break;
			
			case 3:
				this.nonce = this.secureRandom.nextInt();
				break;
			
			default:
				break;
		
		}
		
		this.doBlockSerialization();
		this.doBlockSerializedHash();
		
	}
	
	
	public void doBidsOfCurrentBlockToTryToMineSerialization() {
		
		boolean isPossibleToDoBidsOfCurrentBlockToTryToMineSerialization = 
				( !this.areBidsOfCurrentBlockToTryToMineSerialized && !this.isBlockSerialized && 
				  !this.isBlockSerializedHashed && !this.isBlockMined );
		
		if(isPossibleToDoBidsOfCurrentBlockToTryToMineSerialization) {
		
			int newSizeOfBidsOfCurrentBlockToTryToMineSerialized = 0;
			
			// Operations to Fill a Byte Array, with the following parameters:
			// 1) src - The source of the array to be copied
			// 2) srcPos - The position from the array to be copied, representing the first element to be copied
			// 3) dest - The destination of the array to be copied
			// 4) destPos - The position of the array where will be placed the new copy,
			//              representing the first element where new data will be placed
			// 5) length - The length of the data to be copied from the source array to the destination array
			
			// The offset related to fulfilment of the serialization process
			int serializationOffset = 0;
			
			
			for(Bid bidToMine : this.bidsOfCurrentBlockToTryToMine) {

				bidToMine.doSerialization();
				
				if(this.blockSerialized == null) {
					
					this.bidsOfCurrentBlockToTryToMineSerialized = bidToMine.getBidSerializedBytes();
					
					newSizeOfBidsOfCurrentBlockToTryToMineSerialized = this.bidsOfCurrentBlockToTryToMineSerialized.length;
					
				}
				else {
					
					byte[] serializedBidBytes = bidToMine.getBidSerializedBytes();
					
					int sizeOfSerializedBid = serializedBidBytes.length;
					
					newSizeOfBidsOfCurrentBlockToTryToMineSerialized = ( this.bidsOfCurrentBlockToTryToMineSerialized.length + sizeOfSerializedBid );
					
					this.blockSerialized = Arrays.copyOf(this.blockSerialized, newSizeOfBidsOfCurrentBlockToTryToMineSerialized);
					
					// Fills the byte array of the Block's Serialization with
					// the correspondent bytes from the current Bid serialized,
					// From the position corresponding to the length of the previous Bid's Serialization to
					// the position corresponding to the length of the current Bid's Serialization
					System.arraycopy(serializedBidBytes, 0, this.bidsOfCurrentBlockToTryToMineSerialized,
							         serializationOffset, sizeOfSerializedBid);
					
				}
				
				serializationOffset = this.bidsOfCurrentBlockToTryToMineSerialized.length;
				
			}	
			
			
			this.sizeOfBidsOfCurrentBlockToTryToMineSerialized = newSizeOfBidsOfCurrentBlockToTryToMineSerialized;
			
			this.setAreBidsOfCurrentBlockToTryToMineSerialized(true);
			
		}
		
	}
	
	
	public void doBlockSerialization() {
		
		boolean isPossibleToDoBidsOfCurrentBlockToTryToMineSerialization = 
				(  this.areBidsOfCurrentBlockToTryToMineSerialized && !this.isBlockSerialized && 
				  !this.isBlockSerializedHashed && !this.isBlockMined );
		
		if(isPossibleToDoBidsOfCurrentBlockToTryToMineSerialization) {
			
			byte[] blockIDSerialized = CommonUtils.fromIntToByteArray(blockID);
			
			byte[] nonceSerialized = CommonUtils.fromIntToByteArray(nonce);
			
			
			this.sizeOfBlockSerialized = ( ( 2 * CommonUtils.INTEGER_IN_BYTES_LENGTH ) + 
										   CommonUtils.LENGTH_256_BITS_IN_BYTES +
										   this.sizeOfBidsOfCurrentBlockToTryToMineSerialized );
									
			// Operations to Fill a Byte Array, with the following parameters:
			// 1) src - The source of the array to be copied
			// 2) srcPos - The position from the array to be copied, representing the first element to be copied
			// 3) dest - The destination of the array to be copied
			// 4) destPos - The position of the array where will be placed the new copy,
			//              representing the first element where new data will be placed
			// 5) length - The length of the data to be copied from the source array to the destination array
			
			// The offset related to fulfilment of the serialization process
			int serializationOffset = 0;

			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(blockIDSerialized, serializationOffset,
					         this.blockSerialized, 0,
					         blockIDSerialized.length);
			serializationOffset += blockIDSerialized.length;

			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(this.previousBlockHashed, serializationOffset,
							 this.blockSerialized, 0,
							 this.previousBlockHashed.length);
			serializationOffset += this.previousBlockHashed.length;
			
			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(this.bidsOfCurrentBlockToTryToMineSerialized, serializationOffset,
							 this.blockSerialized, 0,
							 this.bidsOfCurrentBlockToTryToMineSerialized.length);
			serializationOffset += this.bidsOfCurrentBlockToTryToMineSerialized.length;
			
			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(nonceSerialized, serializationOffset,
					         this.blockSerialized, 0,
					         nonceSerialized.length);
			
		}
	}
	
	public void doBlockSerializedHash() throws NoSuchAlgorithmException {
		
		boolean isPossibleToDoBlockSerializedHash = 
				(  this.areBidsOfCurrentBlockToTryToMineSerialized && this.isBlockSerialized && 
				  !this.isBlockSerializedHashed && !this.isBlockMined );
		
		if(isPossibleToDoBlockSerializedHash) {

			MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
			this.blockSerializedHashed = messageDigest.digest(this.blockSerialized);
			
			this.setIsBlockSerializedHashed(true);	
			
		}
	}
	
	public void tryToSolveBlockHashChallenge() throws NoSuchAlgorithmException {
		
		boolean isPossibleToTryToSolveBlockHashChallenge = 
				(  this.areBidsOfCurrentBlockToTryToMineSerialized && this.isBlockSerialized && 
				   this.isBlockSerializedHashed && !this.isBlockMined );
		
		
		if(isPossibleToTryToSolveBlockHashChallenge) {			
			
			// TODO change dynamic/random difficulty of challenge between 2 and 5
			int difficulty = 4;
			
			
			byte[] challengeTarget = new byte[difficulty]; 
			
			while(!Arrays.copyOfRange(this.blockSerializedHashed, 0, difficulty).equals(challengeTarget)) {
			
				this.startProcessToTryToSolveBlockHashChallenge();
			
			}
			
			this.setIsBlockMined(true);	
			
		}
		
	}
}