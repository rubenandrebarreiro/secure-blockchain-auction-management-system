package main.java.messages.secure.challenge.block;

import java.security.NoSuchAlgorithmException;
import java.util.Random;

import main.java.resources.block.Block;

public class ChallengeMessageBlock {
	
	private Block block;

	private byte[] blockChallenge;
	
	private int numBytesBlockChallenge;
	
	private byte[] blockChallengeMessageSerialized;
	
	
	public ChallengeMessageBlock(Block block) {
		
		this.block = block;
		this.blockChallenge = null;
		this.numBytesBlockChallenge = 0;
		
		this.blockChallengeMessageSerialized = null;
		
	}
	
	public Block getBlock() {
		return this.block;
	}
	
	public void setBlock(Block block) {
		this.block = block;
	}
	
	public byte[] getBlockChallenge() {
		return this.blockChallenge;
	}
	
	public void setBlockChallenge(byte[] blockChallenge) {
		this.blockChallenge = blockChallenge;
	}
	
	public int getNumBytesBlockChallenge() {
		return this.numBytesBlockChallenge;
	}
	
	public void setNumBytesBlockChallenge(int numBytesBlockChallenge) {
		this.numBytesBlockChallenge = numBytesBlockChallenge;
	}
	
	public byte[] getBlockChallengeMessageSerialized() {
		return this.blockChallengeMessageSerialized;
	}
	
	public void setBlockChallengeMessageSerialized(byte[] blockChallengeMessageSerialized) {
		this.blockChallengeMessageSerialized = blockChallengeMessageSerialized;
	}
	
	public void createChallengeToSolve() throws NoSuchAlgorithmException {
		
		Random random = new Random();
		
		while(this.numBytesBlockChallenge == 0) {
			this.numBytesBlockChallenge = random.nextInt(5);
		}
		
		this.block.doBlockSerialization();
		
		this.block.doHashOfBlockOfBidsToMine();
		
		
		this.blockChallenge = this.block.getBlockSerializedHashed();
		
		for(int currentByteBlockChallenge = this.numBytesBlockChallenge; currentByteBlockChallenge > 0; currentByteBlockChallenge--) {
			this.blockChallenge[blockChallenge.length - currentByteBlockChallenge] = (byte) -1;
		}
		
	}
	
	public void createBlockChallengeToSolve() {

		byte[] blockSerialized = this.block.getBlockSerialized();
		byte[] blockChallenge = this.getBlockChallenge();
		
		int sizeOfBlockChallengeMessage = ( blockSerialized.length + blockChallenge.length );
		
		this.blockChallengeMessageSerialized = new byte[ sizeOfBlockChallengeMessage ];
		
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
		System.arraycopy(blockSerialized, 0, this.blockChallengeMessageSerialized, serializationOffset, blockSerialized.length);
		serializationOffset += blockSerialized.length;
		
		// Fills the byte array of the Block's Serialization with
		// the correspondent bytes from the current Bid serialized,
		// From the position corresponding to the length of the previous Bid's Serialization to
		// the position corresponding to the length of the current Bid's Serialization
		System.arraycopy(blockChallenge, 0, this.blockChallengeMessageSerialized, serializationOffset, blockChallenge.length);
		
	}
}
