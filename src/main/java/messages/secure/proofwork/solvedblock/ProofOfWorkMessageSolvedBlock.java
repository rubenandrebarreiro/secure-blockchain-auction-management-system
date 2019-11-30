package main.java.messages.secure.proofwork.solvedblock;

import main.java.resources.block.Block;

public class ProofOfWorkMessageSolvedBlock {
	
	private Block block;

	private byte[] blockSolved;
	
	private byte[] proofOfWorkMessageSolvedBlockSerialized;
	
	
	public ProofOfWorkMessageSolvedBlock(Block block, byte[] blockSolved) {
		
		this.block = block;
		this.blockSolved = blockSolved;
		
		this.proofOfWorkMessageSolvedBlockSerialized = null;
		
	}
	
	public Block getBlock() {
		return this.block;
	}
	
	public void setBlock(Block block) {
		this.block = block;
	}
	
	public byte[] getBlockSolved() {
		return this.blockSolved;
	}
	
	public void setBlockSolved(byte[] blockSolved) {
		this.blockSolved = blockSolved;
	}
	
	public byte[] getProofOfWorkMessageSolvedBlockSerialized() {
		return this.proofOfWorkMessageSolvedBlockSerialized;
	}
	
	public void setProofOfWorkMessageSolvedBlockSerialized(byte[] proofOfWorkMessageSolvedBlockSerialized) {
		this.proofOfWorkMessageSolvedBlockSerialized = proofOfWorkMessageSolvedBlockSerialized;
	}
	
	
	
	public void createProofOfWorkMessageSolvedBlock() {

		byte[] blockSerialized = this.block.getBlockSerialized();
		byte[] blockSolved = this.getBlockSolved();
		
		int sizeOfProofOfWorkMessageSolvedBlockSerialized = ( blockSerialized.length + blockSolved.length );
		
		this.proofOfWorkMessageSolvedBlockSerialized = new byte[ sizeOfProofOfWorkMessageSolvedBlockSerialized ];
		
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
		System.arraycopy(blockSerialized, 0, this.proofOfWorkMessageSolvedBlockSerialized, serializationOffset, blockSerialized.length);
		serializationOffset += blockSerialized.length;
		
		// Fills the byte array of the Block's Serialization with
		// the correspondent bytes from the current Bid serialized,
		// From the position corresponding to the length of the previous Bid's Serialization to
		// the position corresponding to the length of the current Bid's Serialization
		System.arraycopy(blockSolved, 0, this.proofOfWorkMessageSolvedBlockSerialized, serializationOffset, blockSolved.length);
		
	}
	
	
}
