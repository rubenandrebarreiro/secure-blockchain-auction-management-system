package main.java.messages.secure.proofwork.metaheader;

import main.java.common.utils.CommonUtils;

public class ProofOfWorkMessageMetaHeader {
	
	private int sizeOfBlockSerialized;
	
	private int sizeOfSolvedBlockChallenge;
	
	private byte[] proofOfWorkMessageMetaHeaderSerialized;
	
	private boolean isProofOfWorkMessageMetaHeaderSerialized;
	
	
	public ProofOfWorkMessageMetaHeader(int sizeOfBlockSerialized, int sizeOfSolvedBlockChallenge) {
		
		this.sizeOfBlockSerialized = sizeOfBlockSerialized;
		this.sizeOfSolvedBlockChallenge = sizeOfSolvedBlockChallenge;
		
		this.proofOfWorkMessageMetaHeaderSerialized = null;
	
		this.isProofOfWorkMessageMetaHeaderSerialized = false;
		
	}
	
	public ProofOfWorkMessageMetaHeader(byte[] proofOfWorkMessageMetaHeaderSerialized) {
		
		this.proofOfWorkMessageMetaHeaderSerialized = proofOfWorkMessageMetaHeaderSerialized;
		
		this.sizeOfBlockSerialized = 0;
		this.sizeOfSolvedBlockChallenge = 0;
		
		this.isProofOfWorkMessageMetaHeaderSerialized = true;
		
	}
	
	
	public int getSizeOfBlockSerialized() {
		return this.sizeOfBlockSerialized;
	}
	
	public void setSizeOfBlockSerialized(int sizeOfBlockSerialized) {
		this.sizeOfBlockSerialized = sizeOfBlockSerialized;
	}
	
	public int getSizeOfSolvedBlockChallenge() {
		return this.sizeOfSolvedBlockChallenge;
	}
	
	public void setSizeOfSolvedBlockChallenge(int sizeOfSolvedBlockChallenge) {
		this.sizeOfSolvedBlockChallenge = sizeOfSolvedBlockChallenge;
	}
	
	public byte[] getProofOfWorkMessageMetaHeaderSerialized() {
		return this.proofOfWorkMessageMetaHeaderSerialized;
	}
	
	public void setProofOfWorkMessageMetaHeaderSerialized(byte[] proofOfWorkMessageMetaHeaderSerialized) {
		this.proofOfWorkMessageMetaHeaderSerialized = proofOfWorkMessageMetaHeaderSerialized;
	}
	
	public boolean getIsProofOfWorkMessageMetaHeaderSerialized() {
		return this.isProofOfWorkMessageMetaHeaderSerialized;
	}
	
	public void setIsProofOfWorkMessageMetaHeaderSerialized(boolean isProofOfWorkMessageMetaHeaderSerialized) {
		this.isProofOfWorkMessageMetaHeaderSerialized = isProofOfWorkMessageMetaHeaderSerialized;
	}
	
	
	public void doProofOfWorkMessageMetaHeaderSerialized() {
			
		if(!this.isProofOfWorkMessageMetaHeaderSerialized) {
			
			int sizeOfChallengeMessageMetaHeaderSerialized = ( ( 2 * CommonUtils.META_HEADER_OUTSIDE_SEPARATORS_LENGTH) + 
															   ( CommonUtils.META_HEADER_INSIDE_SEPARATORS_LENGTH) +
															   ( 2 * CommonUtils.INTEGER_IN_BYTES_LENGTH ) );

			this.proofOfWorkMessageMetaHeaderSerialized = new byte[ sizeOfChallengeMessageMetaHeaderSerialized ];
			
			byte[] outsideSeparator = new byte[] { ( (byte) 0x00 ), ( (byte) 0x00 ) };
			byte[] insideSeparator = new byte[] { ( (byte) 0x00 ) };
			
			byte[] sizeOfBlockSerializedInBytes = CommonUtils.fromIntToByteArray(this.sizeOfBlockSerialized);
			byte[] sizeOfBlockChallengeInBytes = CommonUtils.fromIntToByteArray(this.sizeOfSolvedBlockChallenge);
			
			
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
			System.arraycopy(outsideSeparator, 0, this.proofOfWorkMessageMetaHeaderSerialized, serializationOffset, outsideSeparator.length);
			serializationOffset += outsideSeparator.length;
			
			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(sizeOfBlockSerializedInBytes, 0, this.proofOfWorkMessageMetaHeaderSerialized, serializationOffset, sizeOfBlockSerializedInBytes.length);
			serializationOffset += sizeOfBlockSerializedInBytes.length;
			
			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(insideSeparator, 0, this.proofOfWorkMessageMetaHeaderSerialized, serializationOffset, insideSeparator.length);
			serializationOffset += insideSeparator.length;
			
			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(sizeOfBlockChallengeInBytes, 0, this.proofOfWorkMessageMetaHeaderSerialized, serializationOffset, sizeOfBlockChallengeInBytes.length);
			serializationOffset += sizeOfBlockChallengeInBytes.length;
			
			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(outsideSeparator, 0, this.proofOfWorkMessageMetaHeaderSerialized, serializationOffset, outsideSeparator.length);
						
		
			this.setIsProofOfWorkMessageMetaHeaderSerialized(true);
			
		}	
	}
	
	
	public void undoProofOfWorkMessageMetaHeaderSerialized() {
		
		if(this.isProofOfWorkMessageMetaHeaderSerialized) {
			
			byte[] sizeOfBlockSerializedInBytes = new byte[CommonUtils.INTEGER_IN_BYTES_LENGTH];
			byte[] sizeOfSolvedBlockChallengeInBytes = new byte[CommonUtils.INTEGER_IN_BYTES_LENGTH];
			
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
			System.arraycopy(this.proofOfWorkMessageMetaHeaderSerialized, serializationOffset, sizeOfBlockSerializedInBytes, 0, sizeOfBlockSerializedInBytes.length);
			serializationOffset += CommonUtils.INTEGER_IN_BYTES_LENGTH;
			
			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			serializationOffset += CommonUtils.META_HEADER_INSIDE_SEPARATORS_LENGTH;
			System.arraycopy(this.proofOfWorkMessageMetaHeaderSerialized, serializationOffset, sizeOfSolvedBlockChallengeInBytes, 0, sizeOfSolvedBlockChallengeInBytes.length);
			
			
			this.sizeOfBlockSerialized = CommonUtils.fromByteArrayToInt(sizeOfBlockSerializedInBytes);
			this.sizeOfSolvedBlockChallenge = CommonUtils.fromByteArrayToInt(sizeOfSolvedBlockChallengeInBytes);
			
			
			this.setIsProofOfWorkMessageMetaHeaderSerialized(false);
			
		}
		
	}
	
}
