package main.java.messages.secure.challenge.metaheader;

import main.java.common.utils.CommonUtils;

public class ChallengeMessageMetaHeader {
	
	private int sizeOfBlockSerialized;
	
	private int sizeOfBlockChallenge;
	
	private byte[] challengeMessageMetaHeaderSerialized;
	
	private boolean isChallengeMessageMetaHeaderSerialized;
	
	
	public ChallengeMessageMetaHeader(int sizeOfBlockSerialized, int sizeOfBlockChallenge) {
		
		this.sizeOfBlockSerialized = sizeOfBlockSerialized;
		this.sizeOfBlockChallenge = sizeOfBlockChallenge;
		
		this.challengeMessageMetaHeaderSerialized = null;
	
		this.isChallengeMessageMetaHeaderSerialized = false;
		
	}
	
	public ChallengeMessageMetaHeader(byte[] challengeMessageMetaHeaderSerialized) {
		
		this.challengeMessageMetaHeaderSerialized = challengeMessageMetaHeaderSerialized;
		
		this.sizeOfBlockSerialized = 0;
		this.sizeOfBlockChallenge = 0;
		
		this.isChallengeMessageMetaHeaderSerialized = true;
		
	}
	
	
	public int getSizeOfBlockSerialized() {
		return this.sizeOfBlockSerialized;
	}
	
	public void setSizeOfBlockSerialized(int sizeOfBlockSerialized) {
		this.sizeOfBlockSerialized = sizeOfBlockSerialized;
	}
	
	public int getSizeOfBlockChallenge() {
		return this.sizeOfBlockChallenge;
	}
	
	public void setSizeOfBlockChallenge(int sizeOfBlockChallenge) {
		this.sizeOfBlockChallenge = sizeOfBlockChallenge;
	}
	
	public byte[] getChallengeMessageMetaHeaderSerialized() {
		return this.challengeMessageMetaHeaderSerialized;
	}
	
	public void setChallengeMessageMetaHeaderSerialized(byte[] challengeMessageMetaHeaderSerialized) {
		this.challengeMessageMetaHeaderSerialized = challengeMessageMetaHeaderSerialized;
	}
	
	public boolean getIsChallengeMessageMetaHeaderSerialized() {
		return this.isChallengeMessageMetaHeaderSerialized;
	}
	
	public void setIsChallengeMessageMetaHeaderSerialized(boolean isChallengeMessageMetaHeaderSerialized) {
		this.isChallengeMessageMetaHeaderSerialized = isChallengeMessageMetaHeaderSerialized;
	}
	
	
	public void doChallengeMessageMetaHeaderSerialized() {
			
		if(!this.isChallengeMessageMetaHeaderSerialized) {
			
			int sizeOfChallengeMessageMetaHeaderSerialized = ( ( 2 * CommonUtils.META_HEADER_OUTSIDE_SEPARATORS_LENGTH) + 
															   ( CommonUtils.SHORT_IN_BYTES_LENGTH ) +
															   ( 2 * CommonUtils.INTEGER_IN_BYTES_LENGTH ) );

			this.challengeMessageMetaHeaderSerialized = new byte[ sizeOfChallengeMessageMetaHeaderSerialized ];
			
			byte[] outsideSeparator = new byte[] { ( (byte) 0x00 ), ( (byte) 0x00 ) };
			byte[] insideSeparator = new byte[] { ( (byte) 0x00 ) };
			
			byte[] sizeOfBlockSerializedInBytes = CommonUtils.fromIntToByteArray(this.sizeOfBlockSerialized);
			byte[] sizeOfBlockChallengeInBytes = CommonUtils.fromIntToByteArray(this.sizeOfBlockChallenge);
			
			
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
			System.arraycopy(outsideSeparator, 0, this.challengeMessageMetaHeaderSerialized, serializationOffset, outsideSeparator.length);
			serializationOffset += outsideSeparator.length;
			
			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(sizeOfBlockSerializedInBytes, 0, this.challengeMessageMetaHeaderSerialized, serializationOffset, sizeOfBlockSerializedInBytes.length);
			serializationOffset += sizeOfBlockSerializedInBytes.length;
			
			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(insideSeparator, 0, this.challengeMessageMetaHeaderSerialized, serializationOffset, insideSeparator.length);
			serializationOffset += insideSeparator.length;
			
			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(sizeOfBlockChallengeInBytes, 0, this.challengeMessageMetaHeaderSerialized, serializationOffset, sizeOfBlockChallengeInBytes.length);
			serializationOffset += sizeOfBlockChallengeInBytes.length;
			
			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(outsideSeparator, 0, this.challengeMessageMetaHeaderSerialized, serializationOffset, outsideSeparator.length);
						
		
			this.setIsChallengeMessageMetaHeaderSerialized(true);
			
		}	
	}
	
	
	public void undoChallengeMessageMetaHeaderSerialized() {
		
		if(this.isChallengeMessageMetaHeaderSerialized) {
			
			byte[] sizeOfBlockSerializedInBytes = new byte[CommonUtils.INTEGER_IN_BYTES_LENGTH];
			byte[] sizeOfBlockChallengeInBytes = new byte[CommonUtils.INTEGER_IN_BYTES_LENGTH];
			
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
			serializationOffset += CommonUtils.META_HEADER_OUTSIDE_SEPARATORS_LENGTH;
			System.arraycopy(this.challengeMessageMetaHeaderSerialized, serializationOffset, sizeOfBlockSerializedInBytes, 0, sizeOfBlockSerializedInBytes.length);
			serializationOffset += CommonUtils.INTEGER_IN_BYTES_LENGTH;
			
			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			serializationOffset += CommonUtils.META_HEADER_INSIDE_SEPARATORS_LENGTH;
			System.arraycopy(this.challengeMessageMetaHeaderSerialized, serializationOffset, sizeOfBlockChallengeInBytes, 0, sizeOfBlockChallengeInBytes.length);
			
			
			this.sizeOfBlockSerialized = CommonUtils.fromByteArrayToInt(sizeOfBlockSerializedInBytes);
			this.sizeOfBlockChallenge = CommonUtils.fromByteArrayToInt(sizeOfBlockChallengeInBytes);
			
			
			this.setIsChallengeMessageMetaHeaderSerialized(false);
			
		}
		
	}
	
}
