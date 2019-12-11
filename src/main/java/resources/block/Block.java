package main.java.resources.block;

import java.security.SecureRandom;
import java.util.Arrays;

import main.java.resources.bid.Bid;

public class Block {

	private int blockID;
	
	
	private byte[] previousBlockHashed;
	
	
	private Bid[] bidsOfCurrentBlockToTryToMine;
	
	private byte[] bidsOfCurrentBlockToTryToMineSerialized;
	
	private int sizeOfBidsOfCurrentBlockToTryToMine;
	
	private boolean areBidsOfCurrentBlockToTryToMineSerialized;
	
	
	private byte strategyForCryptoPuzzle;
	
	private SecureRandom secureRandom;
	
	private int nonce;
	
	
	private byte[] blockSerialized;
	
	private int sizeOfBlockSerialized;
	
	private boolean isBlockSerialized;
	
	
	private byte[] blockSerializedHashed;
	
	private boolean isBlockSerializedHashed;
	
	
	private boolean isBlockSerializedMined;
	
	
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
	
	public int getSizeOfBidsOfCurrentBlockToTryToMine() {
		return this.sizeOfBidsOfCurrentBlockToTryToMine;
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
	
	public boolean getIsBlockSerializedMined() {
		return this.isBlockSerializedMined;
	}
	
	public void setIsBlockSerializedMined(boolean isBlockSerializedMined) {
		this.isBlockSerializedMined = isBlockSerializedMined;
	}
	
	
	
	
	public void doBidsOfCurrentBlockToTryToMineSerialization() {
		
		boolean isPossibleToDoBidsOfCurrentBlockToTryToMineSerialization = 
				( !this.areBidsOfCurrentBlockToTryToMineSerialized && !this.isBlockSerialized && 
				  !this.isBlockSerializedHashed && !this.isBlockSerializedMined );
		
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
			
			this.setAreBidsOfCurrentBlockToTryToMineSerialized(true);
			
		}
		
	}
	
	
}