package main.java.messages.secure.challenge.block;

import java.util.Arrays;

import main.java.resources.bid.Bid;

public class Block {

	private Bid[] bidsToMinerate;
	
	private byte[] blockSerialized;
	
	public Block(Bid[] bidsToMinerate) {
		
		this.bidsToMinerate = bidsToMinerate;
		this.blockSerialized = null;
		
	}

	public Bid[] getBidsToMinerate() {
		return bidsToMinerate;
	}

	public void setBidsToMinerate(Bid[] bidsToMinerate) {
		this.bidsToMinerate = bidsToMinerate;
	}

	public byte[] getBlockSerialized() {
		return blockSerialized;
	}

	public void setBlockSerialized(byte[] blockSerialized) {
		this.blockSerialized = blockSerialized;
	}
	
	public void doBlockSerialization() {
		
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
		
		
		for(Bid bid : bidsToMinerate) {

			bid.doSerialization();
			
			if(this.blockSerialized == null) {
					
				this.blockSerialized = bid.getBidSerializedBytes();
				
			}
			else {
			
				byte[] serializedBidBytes = bid.getBidSerializedBytes();
				
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
	}
	
}