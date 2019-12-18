package main.java.messages.secure.proofwork.metaheader;

import main.java.common.utils.CommonUtils;

public class SecureProofOfWorkMessageMetaHeader {
	
	private int sizeOfUserPeerIDSerialized;
	
	private int sizeOfSecureProofOfWorkMessageKeyExchangeSerializedCiphered;
	private int sizeOfSecureProofOfWorkMessageKeyExchangeSerializedCipheredSigned;
	
	private int sizeOfSecureProofOfWorkMessageComponentsSolvedBlockSerialized;
	private int sizeOfSecureProofOfWorkMessageDoSMitigationSerialized;
	
	private int sizeOfSecureBidMessageSolvedBlockConfidentialSerialized;
	private int sizeOfSecureBidMessageSolvedBlockSignatureSerialized;
	
	private int sizeOfBlockAndBlockSolvedHashedSerialized;
	private int sizeOfBlockSerialized;
	private int sizeOfBlockSolvedHashedSerialized;
	
	
	private byte[] secureProofOfWorkMessageMetaHeaderSerialized;
	
	private boolean isSecureProofOfWorkMessageMetaHeaderSerialized;
	
	
	public SecureProofOfWorkMessageMetaHeader(int sizeOfUserPeerIDSerialized,
											  
											  int sizeOfSecureProofOfWorkMessageKeyExchangeSerializedCiphered,
											  int sizeOfSecureProofOfWorkMessageKeyExchangeSerializedCipheredSigned,
											  
											  int sizeOfSecureProofOfWorkMessageComponentsSolvedBlockSerialized,
											  int sizeOfSecureProofOfWorkMessageDoSMitigationSerialized,
											  
											  int sizeOfSecureBidMessageSolvedBlockConfidentialSerialized,
											  int sizeOfSecureBidMessageSolvedBlockSignatureSerialized,
											  
											  int sizeOfBlockAndBlockSolvedHashedSerialized,
											  int sizeOfBlockSerialized,
											  int sizeOfBlockSolvedHashedSerialized) {
		
		
		this.sizeOfUserPeerIDSerialized = sizeOfUserPeerIDSerialized;
		
		this.sizeOfSecureProofOfWorkMessageKeyExchangeSerializedCiphered = 
					sizeOfSecureProofOfWorkMessageKeyExchangeSerializedCiphered;
		this.sizeOfSecureProofOfWorkMessageKeyExchangeSerializedCipheredSigned = 
					sizeOfSecureProofOfWorkMessageKeyExchangeSerializedCipheredSigned;
		
		this.sizeOfSecureProofOfWorkMessageComponentsSolvedBlockSerialized = 
					sizeOfSecureProofOfWorkMessageComponentsSolvedBlockSerialized;
		this.sizeOfSecureProofOfWorkMessageDoSMitigationSerialized = 
					sizeOfSecureProofOfWorkMessageDoSMitigationSerialized;
		
		this.sizeOfSecureBidMessageSolvedBlockConfidentialSerialized = 
					sizeOfSecureBidMessageSolvedBlockConfidentialSerialized;
		this.sizeOfSecureBidMessageSolvedBlockSignatureSerialized = 
					sizeOfSecureBidMessageSolvedBlockSignatureSerialized;
		
		this.sizeOfBlockAndBlockSolvedHashedSerialized = sizeOfBlockAndBlockSolvedHashedSerialized;
		this.sizeOfBlockSerialized = sizeOfBlockSerialized;
		this.sizeOfBlockSolvedHashedSerialized = sizeOfBlockSolvedHashedSerialized;
		
		
		this.secureProofOfWorkMessageMetaHeaderSerialized = null;
		
		this.isSecureProofOfWorkMessageMetaHeaderSerialized = false;
		
	}
	
	
	public int getSizeOfUserPeerIDSerialized() {
		return this.sizeOfUserPeerIDSerialized;
	}

	public int getSizeOfSecureProofOfWorkMessageKeyExchangeSerializedCiphered() {
		return this.sizeOfSecureProofOfWorkMessageKeyExchangeSerializedCiphered;
	}
	
	public int getSizeOfSecureProofOfWorkMessageKeyExchangeSerializedCipheredSigned() {
		return this.sizeOfSecureProofOfWorkMessageKeyExchangeSerializedCipheredSigned;
	}

	public int getSizeOfSecureProofOfWorkMessageComponentsSolvedBlockSerialized() {
		return this.sizeOfSecureProofOfWorkMessageComponentsSolvedBlockSerialized;
	}
	
	public int getSizeOfSecureProofOfWorkMessageDoSMitigationSerialized() {
		return this.sizeOfSecureProofOfWorkMessageDoSMitigationSerialized;
	}
	  
	public int getSizeOfSecureBidMessageSolvedBlockConfidentialSerialized() {
		return this.sizeOfSecureBidMessageSolvedBlockConfidentialSerialized;
	}
	
	public int getSizeOfSecureBidMessageSolvedBlockSignatureSerialized() {
		return this.sizeOfSecureBidMessageSolvedBlockSignatureSerialized;
	}
	
	public int getSizeOfBlockAndBlockSolvedHashedSerialized() {
		return this.sizeOfBlockAndBlockSolvedHashedSerialized;
	}
	
	public int getSizeOfBlockSerialized() {
		return this.sizeOfBlockSerialized;
	}
	
	public int getSizeOfBlockSolvedHashedSerialized() {
		return this.sizeOfBlockSolvedHashedSerialized;
	}
	
	public byte[] getSecureProofOfWorkMessageMetaHeaderSerialized() {
		return this.secureProofOfWorkMessageMetaHeaderSerialized;
	}
	
	public boolean getIsSecureProofOfWorkMessageMetaHeaderSerialized() {
		return this.isSecureProofOfWorkMessageMetaHeaderSerialized;
	}
	
	public void setIsSecureProofOfWorkMessageMetaHeaderSerialized
		  (boolean isSecureProofOfWorkMessageMetaHeaderSerialized) {
		
		this.isSecureProofOfWorkMessageMetaHeaderSerialized = isSecureProofOfWorkMessageMetaHeaderSerialized;
		
	}


	public void doSecureProofOfWorkMessageMetaHeaderSerialization() {

		if(!this.getIsSecureProofOfWorkMessageMetaHeaderSerialized()) {
			
			int sizeOfSecureProofOfWorkMessageMetaHeaderSerialized = 
										 ( ( 2 * CommonUtils.META_HEADER_OUTSIDE_SEPARATORS_LENGTH) +
										   ( 9 * CommonUtils.META_HEADER_INSIDE_SEPARATORS_LENGTH) +
										   ( 10 * CommonUtils.INTEGER_IN_BYTES_LENGTH ) );
								
			this.secureProofOfWorkMessageMetaHeaderSerialized = 
					new byte[ sizeOfSecureProofOfWorkMessageMetaHeaderSerialized ];

			byte[] outsideSeparator = new byte[] { ( (byte) 0x00 ), ( (byte) 0x00 ) };
			byte[] insideSeparator = new byte[] { ( (byte) 0x00 ) };
			
			byte[] sizeOfUserPeerIDSerializedInBytes = 
					CommonUtils.fromIntToByteArray(this.sizeOfUserPeerIDSerialized);

			byte[] sizeOfSecureBidMessageKeyExchangeSerializedCipheredInBytes = 
					CommonUtils.fromIntToByteArray(this.sizeOfSecureProofOfWorkMessageKeyExchangeSerializedCiphered);
			byte[] sizeOfSecureBidMessageKeyExchangeSerializedCipheredSignedInBytes = 
					CommonUtils.fromIntToByteArray(this.sizeOfSecureProofOfWorkMessageKeyExchangeSerializedCipheredSigned);
			
			byte[] sizeOfSecureProofOfWorkMessageComponentsSolvedBlockSerializedInBytes =
					CommonUtils.fromIntToByteArray(this.sizeOfSecureProofOfWorkMessageComponentsSolvedBlockSerialized);
			byte[] sizeOfSecureProofOfWorkMessageDoSMitigationSerializedInBytes =
					CommonUtils.fromIntToByteArray(this.sizeOfSecureProofOfWorkMessageDoSMitigationSerialized);
			
			byte[] sizeOfSecureBidMessageSolvedBlockConfidentialSerializedInBytes =
					CommonUtils.fromIntToByteArray(this.sizeOfSecureBidMessageSolvedBlockConfidentialSerialized);
			byte[] sizeOfSecureBidMessageSolvedBlockSignatureSerializedInBytes = 
					CommonUtils.fromIntToByteArray(this.sizeOfSecureBidMessageSolvedBlockSignatureSerialized);
			
			byte[] sizeOfBlockAndBlockSolvedHashedSerializedInBytes = 
					CommonUtils.fromIntToByteArray(sizeOfBlockAndBlockSolvedHashedSerialized);
			byte[] sizeOfBlockSerializedInBytes = 
					CommonUtils.fromIntToByteArray(sizeOfBlockSerialized);
			byte[] sizeOfBlockSolvedHashedSerializedInBytes = 
					CommonUtils.fromIntToByteArray(sizeOfBlockSolvedHashedSerialized);
			
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
			System.arraycopy(outsideSeparator, 0, this.secureProofOfWorkMessageMetaHeaderSerialized, serializationOffset, outsideSeparator.length);
			serializationOffset += outsideSeparator.length;
			
			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(sizeOfUserPeerIDSerializedInBytes, 0, this.secureProofOfWorkMessageMetaHeaderSerialized,
					serializationOffset, sizeOfUserPeerIDSerializedInBytes.length);
			serializationOffset += sizeOfUserPeerIDSerializedInBytes.length;

			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(insideSeparator, 0, this.secureProofOfWorkMessageMetaHeaderSerialized, serializationOffset, insideSeparator.length);
			serializationOffset += insideSeparator.length;

			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(sizeOfSecureBidMessageKeyExchangeSerializedCipheredInBytes, 0, this.secureProofOfWorkMessageMetaHeaderSerialized,
					serializationOffset, sizeOfSecureBidMessageKeyExchangeSerializedCipheredInBytes.length);
			serializationOffset += sizeOfSecureBidMessageKeyExchangeSerializedCipheredInBytes.length;

			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(insideSeparator, 0, this.secureProofOfWorkMessageMetaHeaderSerialized, serializationOffset, insideSeparator.length);
			serializationOffset += insideSeparator.length;
			
			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(sizeOfSecureBidMessageKeyExchangeSerializedCipheredSignedInBytes, 0, this.secureProofOfWorkMessageMetaHeaderSerialized,
					serializationOffset, sizeOfSecureBidMessageKeyExchangeSerializedCipheredSignedInBytes.length);
			serializationOffset += sizeOfSecureBidMessageKeyExchangeSerializedCipheredSignedInBytes.length;
			
			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(insideSeparator, 0, this.secureProofOfWorkMessageMetaHeaderSerialized, serializationOffset, insideSeparator.length);
			serializationOffset += insideSeparator.length;
			
			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(sizeOfSecureProofOfWorkMessageComponentsSolvedBlockSerializedInBytes, 0, this.secureProofOfWorkMessageMetaHeaderSerialized,
					serializationOffset, sizeOfSecureProofOfWorkMessageComponentsSolvedBlockSerializedInBytes.length);
			serializationOffset += sizeOfSecureProofOfWorkMessageComponentsSolvedBlockSerializedInBytes.length;

			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(insideSeparator, 0, this.secureProofOfWorkMessageMetaHeaderSerialized, serializationOffset, insideSeparator.length);
			serializationOffset += insideSeparator.length;

			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(sizeOfSecureProofOfWorkMessageDoSMitigationSerializedInBytes, 0, this.secureProofOfWorkMessageMetaHeaderSerialized,
					serializationOffset, sizeOfSecureProofOfWorkMessageDoSMitigationSerializedInBytes.length);
			serializationOffset += sizeOfSecureProofOfWorkMessageDoSMitigationSerializedInBytes.length;

			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(insideSeparator, 0, this.secureProofOfWorkMessageMetaHeaderSerialized, serializationOffset, insideSeparator.length);
			serializationOffset += insideSeparator.length;
			
			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(sizeOfSecureBidMessageSolvedBlockConfidentialSerializedInBytes, 0, this.secureProofOfWorkMessageMetaHeaderSerialized,
					serializationOffset, sizeOfSecureBidMessageSolvedBlockConfidentialSerializedInBytes.length);
			serializationOffset += sizeOfSecureBidMessageSolvedBlockConfidentialSerializedInBytes.length;

			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(insideSeparator, 0, this.secureProofOfWorkMessageMetaHeaderSerialized, serializationOffset, insideSeparator.length);
			serializationOffset += insideSeparator.length;
			
			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(sizeOfSecureBidMessageSolvedBlockSignatureSerializedInBytes, 0, this.secureProofOfWorkMessageMetaHeaderSerialized,
					serializationOffset, sizeOfSecureBidMessageSolvedBlockSignatureSerializedInBytes.length);
			serializationOffset += sizeOfSecureBidMessageSolvedBlockSignatureSerializedInBytes.length;

			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(insideSeparator, 0, this.secureProofOfWorkMessageMetaHeaderSerialized, serializationOffset, insideSeparator.length);
			serializationOffset += insideSeparator.length;
			
			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(sizeOfBlockAndBlockSolvedHashedSerializedInBytes, 0, this.secureProofOfWorkMessageMetaHeaderSerialized,
					serializationOffset, sizeOfBlockAndBlockSolvedHashedSerializedInBytes.length);
			serializationOffset += sizeOfBlockAndBlockSolvedHashedSerializedInBytes.length;

			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(insideSeparator, 0, this.secureProofOfWorkMessageMetaHeaderSerialized, serializationOffset, insideSeparator.length);
			serializationOffset += insideSeparator.length;
			
			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(sizeOfBlockSerializedInBytes, 0, this.secureProofOfWorkMessageMetaHeaderSerialized,
					serializationOffset, sizeOfBlockSerializedInBytes.length);
			serializationOffset += sizeOfBlockSerializedInBytes.length;

			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(insideSeparator, 0, this.secureProofOfWorkMessageMetaHeaderSerialized, serializationOffset, insideSeparator.length);
			serializationOffset += insideSeparator.length;
			
			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(sizeOfBlockSolvedHashedSerializedInBytes, 0, this.secureProofOfWorkMessageMetaHeaderSerialized,
					serializationOffset, sizeOfBlockSolvedHashedSerializedInBytes.length);
			serializationOffset += sizeOfBlockSolvedHashedSerializedInBytes.length;

			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(outsideSeparator, 0, this.secureProofOfWorkMessageMetaHeaderSerialized, serializationOffset, outsideSeparator.length);


			this.setIsSecureProofOfWorkMessageMetaHeaderSerialized(true);
			
		}
		
	}
	
	public void undoSecureBidMessageMetaHeaderSerialization() {
		
		if(this.getIsSecureProofOfWorkMessageMetaHeaderSerialized()) {
		
			byte[] sizeOfUserPeerIDSerializedInBytes = new byte[CommonUtils.INTEGER_IN_BYTES_LENGTH];
			
			byte[] sizeOfSecureProofOfWorkMessageKeyExchangeSerializedCipheredInBytes = 
												new byte[CommonUtils.INTEGER_IN_BYTES_LENGTH];
			byte[] sizeOfSecureProofOfWorkMessageKeyExchangeSerializedCipheredSignedInBytes = 
												new byte[CommonUtils.INTEGER_IN_BYTES_LENGTH];
			
			byte[] sizeOfSecureProofOfWorkMessageComponentsSolvedBlockSerializedInBytes = 
						new byte[CommonUtils.INTEGER_IN_BYTES_LENGTH];
			byte[] sizeOfSecureProofOfWorkMessageDoSMitigationSerializedInBytes = 
						new byte[CommonUtils.INTEGER_IN_BYTES_LENGTH];
			
			byte[] sizeOfSecureBidMessageSolvedBlockConfidentialSerializedInBytes = 
						new byte[CommonUtils.INTEGER_IN_BYTES_LENGTH];
			byte[] sizeOfSecureBidMessageSolvedBlockSignatureSerializedInBytes = 
						new byte[CommonUtils.INTEGER_IN_BYTES_LENGTH];
			
			byte[] sizeOfBlockAndBlockSolvedHashedSerializedInBytes = new byte[CommonUtils.INTEGER_IN_BYTES_LENGTH];
			byte[] sizeOfBlockSerializedInBytes = new byte[CommonUtils.INTEGER_IN_BYTES_LENGTH];
			byte[] sizeOfBlockSolvedHashedSerializedInBytes = new byte[CommonUtils.INTEGER_IN_BYTES_LENGTH];
			
			
			byte[] outsideSeparator = new byte[] { ( (byte) 0x00 ), ( (byte) 0x00 ) };
			byte[] insideSeparator = new byte[] { ( (byte) 0x00 ) };
			
			
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
			serializationOffset += outsideSeparator.length;
			System.arraycopy(this.secureProofOfWorkMessageMetaHeaderSerialized, serializationOffset,
					sizeOfUserPeerIDSerializedInBytes, 0, sizeOfUserPeerIDSerializedInBytes.length);
			serializationOffset += sizeOfUserPeerIDSerializedInBytes.length;
			
			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			serializationOffset += insideSeparator.length;
			System.arraycopy(this.secureProofOfWorkMessageMetaHeaderSerialized, serializationOffset,
					sizeOfSecureProofOfWorkMessageKeyExchangeSerializedCipheredInBytes,
					0, sizeOfSecureProofOfWorkMessageKeyExchangeSerializedCipheredInBytes.length);
			serializationOffset += sizeOfSecureProofOfWorkMessageKeyExchangeSerializedCipheredInBytes.length;

			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			serializationOffset += insideSeparator.length;
			System.arraycopy(this.secureProofOfWorkMessageMetaHeaderSerialized, serializationOffset,
					sizeOfSecureProofOfWorkMessageKeyExchangeSerializedCipheredSignedInBytes,
					0, sizeOfSecureProofOfWorkMessageKeyExchangeSerializedCipheredSignedInBytes.length);
			serializationOffset += sizeOfSecureProofOfWorkMessageKeyExchangeSerializedCipheredSignedInBytes.length;
			
			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			serializationOffset += insideSeparator.length;
			System.arraycopy(this.secureProofOfWorkMessageMetaHeaderSerialized, serializationOffset,
					sizeOfSecureProofOfWorkMessageComponentsSolvedBlockSerializedInBytes,
					0, sizeOfSecureProofOfWorkMessageComponentsSolvedBlockSerializedInBytes.length);
			serializationOffset += sizeOfSecureProofOfWorkMessageComponentsSolvedBlockSerializedInBytes.length;
			
					
			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			serializationOffset += insideSeparator.length;
			System.arraycopy(this.secureProofOfWorkMessageMetaHeaderSerialized, serializationOffset,
					sizeOfSecureProofOfWorkMessageDoSMitigationSerializedInBytes,
					0, sizeOfSecureProofOfWorkMessageDoSMitigationSerializedInBytes.length);
			serializationOffset += sizeOfSecureProofOfWorkMessageDoSMitigationSerializedInBytes.length;
						
			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			serializationOffset += insideSeparator.length;
			System.arraycopy(this.secureProofOfWorkMessageMetaHeaderSerialized, serializationOffset,
					sizeOfSecureBidMessageSolvedBlockConfidentialSerializedInBytes,
					0, sizeOfSecureBidMessageSolvedBlockConfidentialSerializedInBytes.length);
			serializationOffset += sizeOfSecureBidMessageSolvedBlockConfidentialSerializedInBytes.length;
			
			
			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			serializationOffset += insideSeparator.length;
			System.arraycopy(this.secureProofOfWorkMessageMetaHeaderSerialized, serializationOffset,
					sizeOfSecureProofOfWorkMessageKeyExchangeSerializedCipheredSignedInBytes,
					0, sizeOfSecureBidMessageSolvedBlockSignatureSerializedInBytes.length);
			serializationOffset += sizeOfSecureProofOfWorkMessageKeyExchangeSerializedCipheredSignedInBytes.length;
						
			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			serializationOffset += insideSeparator.length;
			System.arraycopy(this.secureProofOfWorkMessageMetaHeaderSerialized, serializationOffset,
					sizeOfSecureBidMessageSolvedBlockConfidentialSerializedInBytes,
					0, sizeOfSecureBidMessageSolvedBlockConfidentialSerializedInBytes.length);
			serializationOffset += sizeOfSecureBidMessageSolvedBlockConfidentialSerializedInBytes.length;
			
			
			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			serializationOffset += insideSeparator.length;
			System.arraycopy(this.secureProofOfWorkMessageMetaHeaderSerialized, serializationOffset,
					sizeOfSecureBidMessageSolvedBlockSignatureSerializedInBytes,
					0, sizeOfSecureBidMessageSolvedBlockSignatureSerializedInBytes.length);
			serializationOffset += sizeOfSecureBidMessageSolvedBlockSignatureSerializedInBytes.length;
			
			
			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			serializationOffset += insideSeparator.length;
			System.arraycopy(this.secureProofOfWorkMessageMetaHeaderSerialized, serializationOffset,
					sizeOfSecureProofOfWorkMessageKeyExchangeSerializedCipheredSignedInBytes,
					0, sizeOfSecureBidMessageSolvedBlockSignatureSerializedInBytes.length);
			serializationOffset += sizeOfSecureProofOfWorkMessageKeyExchangeSerializedCipheredSignedInBytes.length;
						
			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			serializationOffset += insideSeparator.length;
			System.arraycopy(this.secureProofOfWorkMessageMetaHeaderSerialized, serializationOffset,
					sizeOfSecureBidMessageSolvedBlockConfidentialSerializedInBytes,
					0, sizeOfSecureBidMessageSolvedBlockConfidentialSerializedInBytes.length);
			serializationOffset += sizeOfSecureBidMessageSolvedBlockConfidentialSerializedInBytes.length;
			
			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			serializationOffset += insideSeparator.length;
			System.arraycopy(this.secureProofOfWorkMessageMetaHeaderSerialized, serializationOffset,
					sizeOfBlockAndBlockSolvedHashedSerializedInBytes,
					0, sizeOfBlockAndBlockSolvedHashedSerializedInBytes.length);
			serializationOffset += sizeOfBlockAndBlockSolvedHashedSerializedInBytes.length;
			
			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			serializationOffset += insideSeparator.length;
			System.arraycopy(this.secureProofOfWorkMessageMetaHeaderSerialized, serializationOffset,
					sizeOfBlockSerializedInBytes,
					0, sizeOfBlockSerializedInBytes.length);
			serializationOffset += sizeOfBlockSerializedInBytes.length;
			
			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			serializationOffset += insideSeparator.length;
			System.arraycopy(this.secureProofOfWorkMessageMetaHeaderSerialized, serializationOffset,
					sizeOfBlockSolvedHashedSerializedInBytes,
					0, sizeOfBlockSolvedHashedSerializedInBytes.length);
			serializationOffset += sizeOfBlockSolvedHashedSerializedInBytes.length;
			
			
			this.sizeOfUserPeerIDSerialized = 
					CommonUtils.fromByteArrayToInt(sizeOfUserPeerIDSerializedInBytes);
			
			this.sizeOfSecureProofOfWorkMessageKeyExchangeSerializedCiphered = 
					CommonUtils.fromByteArrayToInt(sizeOfSecureProofOfWorkMessageKeyExchangeSerializedCipheredInBytes);
			this.sizeOfSecureProofOfWorkMessageKeyExchangeSerializedCipheredSigned = 
					CommonUtils.fromByteArrayToInt(sizeOfSecureProofOfWorkMessageKeyExchangeSerializedCipheredSignedInBytes);
			
			
			this.sizeOfSecureProofOfWorkMessageComponentsSolvedBlockSerialized = 
					CommonUtils.fromByteArrayToInt(sizeOfSecureProofOfWorkMessageComponentsSolvedBlockSerializedInBytes);
			this.sizeOfSecureProofOfWorkMessageDoSMitigationSerialized = 
					CommonUtils.fromByteArrayToInt(sizeOfSecureProofOfWorkMessageDoSMitigationSerializedInBytes);
			
			this.sizeOfSecureBidMessageSolvedBlockConfidentialSerialized = 
					CommonUtils.fromByteArrayToInt(sizeOfSecureBidMessageSolvedBlockConfidentialSerializedInBytes);
			this.sizeOfSecureBidMessageSolvedBlockSignatureSerialized = 
					CommonUtils.fromByteArrayToInt(sizeOfSecureBidMessageSolvedBlockSignatureSerializedInBytes);

			
			this.sizeOfBlockAndBlockSolvedHashedSerialized = 
					CommonUtils.fromByteArrayToInt(sizeOfBlockAndBlockSolvedHashedSerializedInBytes);

			this.sizeOfBlockSerialized = CommonUtils.fromByteArrayToInt(sizeOfBlockSerializedInBytes);

			this.sizeOfBlockSolvedHashedSerialized = CommonUtils.fromByteArrayToInt(sizeOfBlockSolvedHashedSerializedInBytes);
			
			
			
			this.setIsSecureProofOfWorkMessageMetaHeaderSerialized(false);
			
		}
	}
}
