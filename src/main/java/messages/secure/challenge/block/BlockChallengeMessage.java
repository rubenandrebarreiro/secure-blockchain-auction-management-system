package main.java.messages.secure.challenge.block;

import java.security.NoSuchAlgorithmException;
import java.util.Random;

import main.java.resources.block.Block;

public class BlockChallengeMessage {
	
	private Block block;

	private byte[] blockChallenge;
	
	private int numBytesBlockChallenge;
	
	
	public BlockChallengeMessage(Block block) {
		
		this.block = block;
		this.blockChallenge = null;
		this.numBytesBlockChallenge = 0;
		
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
		
		// TODO
		
	}
}
