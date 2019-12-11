package main.java.resources.block;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;

import main.java.resources.bid.Bid;

public class Block {

	private Bid[] bidsToMine;
	
	private int randomNonce;
	
	private byte[] blockSerialized;
	
	private boolean isBlockSerialized;
	
	private byte[] blockSerializedHashed;
	
	private boolean isBlockSerializedHashed;
	
	private boolean isBlockMinedAndClosed;
	
	
	public Block(Bid[] bidsToMine) {
		
		this.bidsToMine = bidsToMine;
		
		SecureRandom secureRandom = new SecureRandom();
		this.randomNonce = secureRandom.nextInt();
		
		this.blockSerialized = null;
		this.isBlockSerialized = false;
		
		this.blockSerializedHashed = null;
		this.isBlockSerializedHashed = false;
		
	}
	
	
	public Bid[] getBidsToMine() {
		return bidsToMine;
	}
	
	public int getRandomNonce() {
		return this.randomNonce;
	}

	public byte[] getBlockSerialized() {
		return this.blockSerialized;
	}

	public void setBlockSerialized(byte[] blockSerialized) {
		this.blockSerialized = blockSerialized;
	}
	
	public boolean getIsBlockSerialized() {
		return this.isBlockSerialized;
	}
	
	public void setIsBlockSerialized(boolean isBlockSerialized) {
		this.isBlockSerialized = isBlockSerialized;
	}
	
	public boolean getIsBlockSerializedHashed() {
		return this.isBlockSerializedHashed;
	}
	
	public void setIsBlockSerializedHashed(boolean isBlockSerializedHashed) {
		this.isBlockSerializedHashed = isBlockSerializedHashed;
	}
	
	public byte[] getBlockSerializedHashed() {
		return this.blockSerializedHashed;
	}
	
	public void setBlockSerializedHashed(byte[] blockSerializedHashed) {
		this.blockSerializedHashed = blockSerializedHashed;
	}
	
	public boolean isBlockMinedAndClosed() {
		return isBlockMinedAndClosed;
	}
	
	public void setIsBlockMinedAndClosed(boolean isBlockMinedAndClosed) {
		this.isBlockMinedAndClosed = isBlockMinedAndClosed;
	}
	
	public void doBlockSerialization() {
		
		boolean isPossibleToDoBlockSerialization = 
				( !this.isBlockSerialized && !this.isBlockSerializedHashed );
		
		if(isPossibleToDoBlockSerialization) {
		
			int newSizeOfBlockSerialized = 0;
			
			// Operations to Fill a Byte Array, with the following parameters:
			// 1) src - The source of the array to be copied
			// 2) srcPos - The position from the array to be copied, representing the first element to be copied
			// 3) dest - The destination of the array to be copied
			// 4) destPos - The position of the array where will be placed the new copy,
			//              representing the first element where new data will be placed
			// 5) length - The length of the data to be copied from the source array to the destination array
			
			// The offset related to fulfilment of the serialization process
			int serializationOffset = 0;
			
			
			for(Bid bidToMine : bidsToMine) {

				bidToMine.doSerialization();
				
				if(this.blockSerialized == null) {
					
					this.blockSerialized = bidToMine.getBidSerializedBytes();
					
				}
				else {
					
					byte[] serializedBidBytes = bidToMine.getBidSerializedBytes();
					
					int sizeOfSerializedBid = serializedBidBytes.length;
					
					newSizeOfBlockSerialized = ( this.blockSerialized.length + sizeOfSerializedBid );
					
					this.blockSerialized = Arrays.copyOf(this.blockSerialized, newSizeOfBlockSerialized);
					
					// Fills the byte array of the Block's Serialization with
					// the correspondent bytes from the current Bid serialized,
					// From the position corresponding to the length of the previous Bid's Serialization to
					// the position corresponding to the length of the current Bid's Serialization
					System.arraycopy(serializedBidBytes, 0, this.blockSerialized, serializationOffset, sizeOfSerializedBid);
					
				}
				
				serializationOffset = this.blockSerialized.length;
				
			}	
			
			this.setIsBlockSerialized(true);
			
		}
		
	}
	
	public void doHashOfBlockOfBidsToMine() throws NoSuchAlgorithmException {
		
		boolean isPossibleToDoBlockSerializedHash = 
				( this.isBlockSerialized && !this.isBlockSerializedHashed );
		
		if(isPossibleToDoBlockSerializedHash) {
			
			MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
			this.blockSerializedHashed = messageDigest.digest(this.blockSerialized);

			this.setIsBlockSerializedHashed(true);	
			
		}
		
	}
	
}