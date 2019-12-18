package main.java.messages.secure.proofwork.components.solvedblock.info;

import main.java.resources.block.Block;

public class SecureProofOfWorkMessageComponentsSolvedBlockInfo {

	private Block block;
	
	
	private byte[] blockSerialized;
	
	private int sizeOfBlockSerialized;
	
	private boolean isBlockSerialized;
	
	
	private byte[] blockSolvedHashed;
	
	private int sizeOfBlockSolvedHashed;
	
	private boolean isBlockSolvedHashed;
	
	
	private byte[] blockSerializedAndSolvedHashed;
	
	private int sizeOfBlockSerializedAndSolvedHashed;
	
	private boolean isBlockSerializedAndSolvedHashed;	
	
	
	public SecureProofOfWorkMessageComponentsSolvedBlockInfo(Block block, byte[] blockSolvedHashed) {
		
		this.block = block;
		
		
		this.block.doBlockSerialization();
		
		this.blockSerialized = this.block.getBlockSerialized();
		this.sizeOfBlockSerialized = this.blockSerialized.length;
		this.isBlockSerialized = true;
		
		
		this.blockSolvedHashed = blockSolvedHashed;
		this.sizeOfBlockSolvedHashed = this.blockSolvedHashed.length;
		this.isBlockSolvedHashed = true;
		
		
		this.blockSerializedAndSolvedHashed = null;
		this.sizeOfBlockSerializedAndSolvedHashed = 0;
		this.isBlockSerializedAndSolvedHashed = false;
		
		
	}
	
	public SecureProofOfWorkMessageComponentsSolvedBlockInfo(byte[] blockSerializedAndSolvedHashed,
																	 int sizeOfBlockSolvedHashed,
																	 int sizeOfBlockSerialized) {
		
		this.blockSerializedAndSolvedHashed = blockSerializedAndSolvedHashed;
		this.sizeOfBlockSerializedAndSolvedHashed = blockSerializedAndSolvedHashed.length;
		this.isBlockSerializedAndSolvedHashed = true;
		
		this.blockSolvedHashed = null;
		this.sizeOfBlockSolvedHashed = sizeOfBlockSolvedHashed;
		this.isBlockSolvedHashed = true;
		
		this.blockSerialized = null;
		this.sizeOfBlockSerialized = sizeOfBlockSerialized;
		this.isBlockSerialized = true;
		
		this.block = null;
		
	}
	
	
	
	
	public Block getBlock() {
		return this.block;
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
	
	public byte[] getBlockSolvedHashed() {
		return this.blockSolvedHashed;
	}
	
	public int getSizeOfBlockSolvedHashed() {
		return this.sizeOfBlockSolvedHashed;
	}
	
	public boolean getIsBlockSolvedHashed() {
		return this.isBlockSolvedHashed;
	}
	
	public void setIsBlockSolvedHashed(boolean isBlockSolvedHashed) {
		this.isBlockSolvedHashed = isBlockSolvedHashed;
	}
	
	
	public byte[] getBlockSerializedAndSolvedHashed() {
		return this.blockSerializedAndSolvedHashed;
	}
	
	public int getSizeOfBlockSerializedAndSolvedHashed() {
		return this.sizeOfBlockSerializedAndSolvedHashed;
	}
	
	public boolean getIsBlockSerializedAndSolvedHashed() {
		return this.isBlockSerializedAndSolvedHashed;
	}
	
	public void setIsBlockSerializedAndSolvedHashed(boolean isBlockSerializedAndSolvedHashed) {
		this.isBlockSerializedAndSolvedHashed = isBlockSerializedAndSolvedHashed;
	}
	
	
	public void doBlockSerializedAndSolvedHashed() {
		
		boolean isPossibleToDoBlockSerializedAndSolvedHashed = 
			  (  this.isBlockSerialized && this.isBlockSolvedHashed && 
			    !this.isBlockSerializedAndSolvedHashed);
		
		
		if(isPossibleToDoBlockSerializedAndSolvedHashed) {
			
			this.sizeOfBlockSerializedAndSolvedHashed = ( this.sizeOfBlockSerialized + this.sizeOfBlockSolvedHashed );
			
			this.blockSerializedAndSolvedHashed = 
					new byte[ this.sizeOfBlockSerializedAndSolvedHashed ];
			
			
			// Operations to Fill a Byte Array, with the following parameters:
			// 1) src - The source of the array to be copied
			// 2) srcPos - The position from the array to be copied, representing the first element to be copied
			// 3) dest - The destination of the array to be copied
			// 4) destPos - The position of the array where will be placed the new copy,
			//              representing the first element where new SolvedBlock will be placed
			// 5) length - The length of the SolvedBlock to be copied from the source array to the destination array
	
			// The offset related to fulfilment of the serialization process
			int serializationOffset = 0;
			
			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current ProofOfWork serialized,
			// From the position corresponding to the length of the previous ProofOfWork's Serialization to
			// the position corresponding to the length of the current ProofOfWork's Serialization
			System.arraycopy(this.blockSerialized, 0,
							 this.blockSerializedAndSolvedHashed,
							 serializationOffset, this.blockSerialized.length);
			serializationOffset += this.blockSerialized.length;
			
			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current ProofOfWork serialized,
			// From the position corresponding to the length of the previous ProofOfWork's Serialization to
			// the position corresponding to the length of the current ProofOfWork's Serialization
			System.arraycopy(this.blockSolvedHashed, 0,
					         this.blockSerializedAndSolvedHashed,
					         serializationOffset, this.blockSolvedHashed.length);
			
			
			this.setIsBlockSerializedAndSolvedHashed(true);
			
		}
		
		
	}
	
	public void undoBlockSerializedAndSolvedHashed() {
		
		boolean isPossibleToUndoBlockSerializedAndSolvedHashed = 
			  (  this.isBlockSerialized && this.isBlockSolvedHashed && 
			     this.isBlockSerializedAndSolvedHashed );
			
		
		if(isPossibleToUndoBlockSerializedAndSolvedHashed) {
			
			this.blockSerialized = new byte[ this.sizeOfBlockSerialized ];
			
			this.blockSolvedHashed = new byte[ this.sizeOfBlockSolvedHashed ];
			
			
			// Operations to Fill a Byte Array, with the following parameters:
			// 1) src - The source of the array to be copied
			// 2) srcPos - The position from the array to be copied, representing the first element to be copied
			// 3) dest - The destination of the array to be copied
			// 4) destPos - The position of the array where will be placed the new copy,
			//              representing the first element where new SolvedBlock will be placed
			// 5) length - The length of the SolvedBlock to be copied from the source array to the destination array
	
			// The offset related to fulfilment of the serialization process
			int serializationOffset = 0;
			
			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current ProofOfWork serialized,
			// From the position corresponding to the length of the previous ProofOfWork's Serialization to
			// the position corresponding to the length of the current ProofOfWork's Serialization
			System.arraycopy(this.blockSerializedAndSolvedHashed, serializationOffset,
							 this.blockSerialized, 0, this.blockSerialized.length);
			serializationOffset += this.blockSerialized.length;
			
			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current ProofOfWork serialized,
			// From the position corresponding to the length of the previous ProofOfWork's Serialization to
			// the position corresponding to the length of the current ProofOfWork's Serialization
			System.arraycopy(this.blockSerializedAndSolvedHashed, serializationOffset,
					         this.blockSolvedHashed, 0, this.blockSerialized.length);
			
			
			this.setIsBlockSerializedAndSolvedHashed(false);
			
		}
		
	}
}
