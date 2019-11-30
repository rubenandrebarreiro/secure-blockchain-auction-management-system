package main.java.messages.secure.challenge;

import java.security.NoSuchAlgorithmException;

import main.java.messages.secure.challenge.block.ChallengeMessageBlock;
import main.java.messages.secure.challenge.metaheader.ChallengeMessageMetaHeader;
import main.java.messages.secure.common.header.SecureCommonHeader;

public class ChallengeMessage {
	
	private ChallengeMessageMetaHeader challengeMessageMetaHeader;
	
	private int sizeOfChallengeMessageMetaHeader;
	
	
	private SecureCommonHeader secureCommonHeader;
	
	private int sizeOfSecureCommonHeader;
	
	
	private ChallengeMessageBlock challengeMessageBlock;
	
	private int sizeOfChallengeMessageBlock;
	
	
	private byte[] challengeMessageSerialized;
			
	
	
	public ChallengeMessage(ChallengeMessageMetaHeader challengeMessageMetaHeader,
			                SecureCommonHeader secureCommonHeader,
			                ChallengeMessageBlock challengeMessageBlock) throws NoSuchAlgorithmException {
		
		this.challengeMessageMetaHeader = challengeMessageMetaHeader;
		this.challengeMessageMetaHeader.doChallengeMessageMetaHeaderSerialized();
		this.sizeOfChallengeMessageMetaHeader = this.challengeMessageMetaHeader
													.getChallengeMessageMetaHeaderSerialized().length;
		
		this.secureCommonHeader = secureCommonHeader;
		this.sizeOfSecureCommonHeader = this.secureCommonHeader.getSecureCommonHeaderSerialized().length;
		
		this.challengeMessageBlock = challengeMessageBlock;
		this.challengeMessageBlock.createBlockChallengeToSolve();
		this.challengeMessageBlock.createChallengeToSolve();
		this.sizeOfChallengeMessageBlock = this.getChallengeMessageBlock()
											   .getBlockChallengeMessageSerialized().length;
		
		this.challengeMessageSerialized = null;
		
	}
	
	public ChallengeMessage(byte[] challengeMessageSerialized,
							int sizeOfChallengeMessageMetaHeader,
							int sizeOfSecureCommonHeader,
							int sizeOfChallengeMessageBlock) {
		
		this.challengeMessageSerialized = challengeMessageSerialized;

		this.challengeMessageMetaHeader = null;
		this.sizeOfChallengeMessageMetaHeader = sizeOfChallengeMessageMetaHeader;
		
		this.secureCommonHeader = null;
		this.sizeOfSecureCommonHeader = sizeOfSecureCommonHeader;
		
		this.challengeMessageBlock = null;
		this.sizeOfChallengeMessageBlock = sizeOfChallengeMessageBlock;
		
	}
	
	
	
	public ChallengeMessageMetaHeader getChallengeMessageMetaHeader() {
		return this.challengeMessageMetaHeader;
	}
	
	public void setChallengeMessageMetaHeader(ChallengeMessageMetaHeader challengeMessageMetaHeader) {
		this.challengeMessageMetaHeader = challengeMessageMetaHeader;
	}
	
	public int getSizeOfChallengeMessageMetaHeader() {
		return sizeOfChallengeMessageMetaHeader;
	}

	public void setSizeOfChallengeMessageMetaHeader(int sizeOfChallengeMessageMetaHeader) {
		this.sizeOfChallengeMessageMetaHeader = sizeOfChallengeMessageMetaHeader;
	}
	
	public SecureCommonHeader getSecureCommonHeader() {
		return this.secureCommonHeader;
	}
	
	public void setSecureCommonHeader(SecureCommonHeader secureCommonHeader) {
		this.secureCommonHeader = secureCommonHeader;
	}
	
	public int getSizeOfSecureCommonHeader() {
		return sizeOfSecureCommonHeader;
	}

	public void setSizeOfSecureCommonHeader(int sizeOfSecureCommonHeader) {
		this.sizeOfSecureCommonHeader = sizeOfSecureCommonHeader;
	}
	
	public ChallengeMessageBlock getChallengeMessageBlock() {
		return this.challengeMessageBlock;
	}
	
	public void setChallengeMessageBlock(ChallengeMessageBlock challengeMessageBlock) {
		this.challengeMessageBlock = challengeMessageBlock;
	}
	
	public int getSizeOfChallengeMessageBlock() {
		return sizeOfChallengeMessageBlock;
	}

	public void setSizeOfChallengeMessageBlock(int sizeOfChallengeMessageBlock) {
		this.sizeOfChallengeMessageBlock = sizeOfChallengeMessageBlock;
	}
	
	public byte[] getChallengeMessageSerialized() {
		return this.challengeMessageSerialized;
	}
	
	public void setChallengeMessageSerialized(byte[] challengeMessageSerialized) {
		this.challengeMessageSerialized = challengeMessageSerialized;
	}
	
	public void doChallengeMessageSerialization() {
		
		byte[] challengeMessageMetaHeaderSerialized = this.challengeMessageMetaHeader
														  .getChallengeMessageMetaHeaderSerialized();
		
		byte[] secureCommonHeaderSerialized = this.secureCommonHeader.getSecureCommonHeaderSerialized();
		
		byte[] challengeMessageBlockSerialized = this.challengeMessageBlock.getBlockChallengeMessageSerialized();
		
		
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
		System.arraycopy(challengeMessageMetaHeaderSerialized, 0, this.challengeMessageSerialized, serializationOffset, challengeMessageMetaHeaderSerialized.length);
		serializationOffset += challengeMessageMetaHeaderSerialized.length;
		
		// Fills the byte array of the Block's Serialization with
		// the correspondent bytes from the current Bid serialized,
		// From the position corresponding to the length of the previous Bid's Serialization to
		// the position corresponding to the length of the current Bid's Serialization
		System.arraycopy(secureCommonHeaderSerialized, 0, this.challengeMessageSerialized, serializationOffset, secureCommonHeaderSerialized.length);
		serializationOffset += secureCommonHeaderSerialized.length;
		
		// Fills the byte array of the Block's Serialization with
		// the correspondent bytes from the current Bid serialized,
		// From the position corresponding to the length of the previous Bid's Serialization to
		// the position corresponding to the length of the current Bid's Serialization
		System.arraycopy(challengeMessageBlockSerialized, 0, this.challengeMessageSerialized, serializationOffset, challengeMessageBlockSerialized.length);
		
	}
	
	public void undoChallengeMessageSerialization() {
		
		byte[] challengeMessageMetaHeaderSerialized = new byte[this.sizeOfChallengeMessageMetaHeader];

		byte[] secureCommonHeaderSerialized = new byte[this.sizeOfSecureCommonHeader];
		
		byte[] challengeMessageBlockSerialized = new byte[this.sizeOfChallengeMessageBlock];
		
		
		int sizeOfChallengeMessageSerialized = ( this.sizeOfChallengeMessageMetaHeader + 
												 this.sizeOfSecureCommonHeader + 
												 this.sizeOfChallengeMessageBlock );
		
		this.challengeMessageSerialized = new byte[ sizeOfChallengeMessageSerialized ];
		
		
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
		System.arraycopy(this.challengeMessageSerialized, serializationOffset, challengeMessageMetaHeaderSerialized, 0, challengeMessageMetaHeaderSerialized.length);
		serializationOffset += challengeMessageMetaHeaderSerialized.length;
		
		// Fills the byte array of the Block's Serialization with
		// the correspondent bytes from the current Bid serialized,
		// From the position corresponding to the length of the previous Bid's Serialization to
		// the position corresponding to the length of the current Bid's Serialization
		System.arraycopy(this.challengeMessageSerialized, serializationOffset, secureCommonHeaderSerialized, 0, secureCommonHeaderSerialized.length);
		serializationOffset += secureCommonHeaderSerialized.length;
		
		// Fills the byte array of the Block's Serialization with
		// the correspondent bytes from the current Bid serialized,
		// From the position corresponding to the length of the previous Bid's Serialization to
		// the position corresponding to the length of the current Bid's Serialization
		System.arraycopy(this.challengeMessageSerialized, serializationOffset, challengeMessageBlockSerialized, 0, challengeMessageBlockSerialized.length);
		
	}
}