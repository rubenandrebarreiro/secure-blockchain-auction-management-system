package main.java.messages.secure.proofwork.metaheader;

import main.java.common.utils.CommonUtils;

public class SecureProofOfWorkMessageMetaHeader {
	
	private int sizeOfUserPeerIDSerialized;
	
	private int sizeOfSecureProofOfWorkMessageKeyExchangeSerializedCiphered;
	private int sizeOfSecureProofOfWorkMessageKeyExchangeSerializedCipheredSigned;
	
	private int sizeOfSecureProofOfWorkMessageComponentsSerializedCiphered;

	private int sizeOfSecureProofOfWorkMessageDoSMitigationSerialized;
	
	private int sizeOfSecureProofOfWorkMessageComponentsSolvedBlockSerialized;
	
	private int sizeOfSecureProofOfWorkMessageSolvedBlockInfoSerialized;
	private int sizeOfSecureProofOfWorkMessageSolvedBlockSignatureSerialized;
	
	private int sizeOfBlockAndBlockSolvedHashedSerialized;
	private int sizeOfBlockSerialized;
	private int sizeOfBlockSolvedHashedSerialized;
	
	private int sizeOfBidsOfCurrentBlockToTryToMineSerialized;
	
	private byte[] secureProofOfWorkMessageMetaHeaderSerialized;
	
	private boolean isSecureProofOfWorkMessageMetaHeaderSerialized;
	
	
	public SecureProofOfWorkMessageMetaHeader(int sizeOfUserPeerIDSerialized,
											  
											  int sizeOfSecureProofOfWorkMessageKeyExchangeSerializedCiphered,
											  int sizeOfSecureProofOfWorkMessageKeyExchangeSerializedCipheredSigned,
											  
											  int sizeOfSecureProofOfWorkMessageComponentsSerializedCiphered,
											  int sizeOfSecureProofOfWorkMessageDoSMitigationSerialized,

											  int sizeOfSecureProofOfWorkMessageComponentsSolvedBlockSerialized,

											  int sizeOfSecureProofOfWorkMessageSolvedBlockInfoSerialized,
											  int sizeOfSecureProofOfWorkMessageSolvedBlockSignatureSerialized,
											  
											  int sizeOfBlockAndBlockSolvedHashedSerialized,
											  int sizeOfBlockSerialized,
											  int sizeOfBlockSolvedHashedSerialized,
											  
											  int sizeOfBidsOfCurrentBlockToTryToMineSerialized) {
		
		
		this.sizeOfUserPeerIDSerialized = sizeOfUserPeerIDSerialized;
		
		this.sizeOfSecureProofOfWorkMessageKeyExchangeSerializedCiphered = 
					sizeOfSecureProofOfWorkMessageKeyExchangeSerializedCiphered;
		this.sizeOfSecureProofOfWorkMessageKeyExchangeSerializedCipheredSigned = 
					sizeOfSecureProofOfWorkMessageKeyExchangeSerializedCipheredSigned;
		
		this.sizeOfSecureProofOfWorkMessageComponentsSerializedCiphered = 
				sizeOfSecureProofOfWorkMessageComponentsSerializedCiphered;
		this.sizeOfSecureProofOfWorkMessageDoSMitigationSerialized = 
					sizeOfSecureProofOfWorkMessageDoSMitigationSerialized;
		
		this.sizeOfSecureProofOfWorkMessageComponentsSolvedBlockSerialized =
				sizeOfSecureProofOfWorkMessageComponentsSolvedBlockSerialized;
		
		this.sizeOfSecureProofOfWorkMessageSolvedBlockInfoSerialized = 
					sizeOfSecureProofOfWorkMessageSolvedBlockInfoSerialized;
		this.sizeOfSecureProofOfWorkMessageSolvedBlockSignatureSerialized = 
					sizeOfSecureProofOfWorkMessageSolvedBlockSignatureSerialized;
		
		this.sizeOfBlockAndBlockSolvedHashedSerialized = sizeOfBlockAndBlockSolvedHashedSerialized;
		this.sizeOfBlockSerialized = sizeOfBlockSerialized;
		this.sizeOfBlockSolvedHashedSerialized = sizeOfBlockSolvedHashedSerialized;
		
		this.sizeOfBidsOfCurrentBlockToTryToMineSerialized = 
				sizeOfBidsOfCurrentBlockToTryToMineSerialized;
		
		this.secureProofOfWorkMessageMetaHeaderSerialized = null;
		
		this.isSecureProofOfWorkMessageMetaHeaderSerialized = false;
		
	}
	
	
	public SecureProofOfWorkMessageMetaHeader(byte[] secureProofOfWorkMessageMetaHeaderSerialized) {

		this.secureProofOfWorkMessageMetaHeaderSerialized = secureProofOfWorkMessageMetaHeaderSerialized;
		this.isSecureProofOfWorkMessageMetaHeaderSerialized = true;
		
		this.sizeOfBlockAndBlockSolvedHashedSerialized = 0;
		this.sizeOfBlockSerialized = 0;
		this.sizeOfBlockSolvedHashedSerialized = 0;
		
		this.sizeOfBidsOfCurrentBlockToTryToMineSerialized = 0;
		
		this.sizeOfSecureProofOfWorkMessageSolvedBlockInfoSerialized = 0;
		this.sizeOfSecureProofOfWorkMessageSolvedBlockSignatureSerialized = 0;
		
		this.sizeOfSecureProofOfWorkMessageComponentsSolvedBlockSerialized = 0;
		this.sizeOfSecureProofOfWorkMessageDoSMitigationSerialized = 0;
			
		this.sizeOfSecureProofOfWorkMessageKeyExchangeSerializedCiphered = 0;
		this.sizeOfSecureProofOfWorkMessageKeyExchangeSerializedCipheredSigned = 0;
			
		this.sizeOfUserPeerIDSerialized = 0;

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

	public int getSizeOfSecureProofOfWorkMessageComponentsSerializedCiphered() {
		return sizeOfSecureProofOfWorkMessageComponentsSerializedCiphered;
	}
	
	public int getSizeOfSecureProofOfWorkMessageDoSMitigationSerialized() {
		return this.sizeOfSecureProofOfWorkMessageDoSMitigationSerialized;
	}

	public int getSizeOfSecureProofOfWorkMessageComponentsSolvedBlockSerialized() {
		return this.sizeOfSecureProofOfWorkMessageComponentsSolvedBlockSerialized;
	}
	
	public int getSizeOfSecureProofOfWorkMessageSolvedBlockInfoSerialized() {
		return this.sizeOfSecureProofOfWorkMessageSolvedBlockInfoSerialized;
	}
	
	public int getSizeOfSecureProofOfWorkMessageSolvedBlockSignatureSerialized() {
		return this.sizeOfSecureProofOfWorkMessageSolvedBlockSignatureSerialized;
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
	
	public int getSizeOfBidsOfCurrentBlockToTryToMineSerialized() {
		return this.sizeOfBidsOfCurrentBlockToTryToMineSerialized;
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
										   ( 11 * CommonUtils.META_HEADER_INSIDE_SEPARATORS_LENGTH) +
										   ( 12 * CommonUtils.INTEGER_IN_BYTES_LENGTH ) );
								
			this.secureProofOfWorkMessageMetaHeaderSerialized = 
					new byte[ sizeOfSecureProofOfWorkMessageMetaHeaderSerialized ];

			byte[] outsideSeparator = new byte[] { ( (byte) 0x00 ), ( (byte) 0x00 ) };
			byte[] insideSeparator = new byte[] { ( (byte) 0x00 ) };
			
			byte[] sizeOfUserPeerIDSerializedInBytes = 
					CommonUtils.fromIntToByteArray(this.sizeOfUserPeerIDSerialized);

			byte[] sizeOfSecureProofOfWorkMessageKeyExchangeSerializedCipheredInBytes = 
					CommonUtils.fromIntToByteArray(this.sizeOfSecureProofOfWorkMessageKeyExchangeSerializedCiphered);
			byte[] sizeOfSecureProofOfWorkMessageKeyExchangeSerializedCipheredSignedInBytes = 
					CommonUtils.fromIntToByteArray(this.sizeOfSecureProofOfWorkMessageKeyExchangeSerializedCipheredSigned);
			
			byte[] sizeOfSecureProofOfWorkMessageComponentsSerializedCipheredInBytes =
					CommonUtils.fromIntToByteArray(this.sizeOfSecureProofOfWorkMessageComponentsSerializedCiphered);
			byte[] sizeOfSecureProofOfWorkMessageDoSMitigationSerializedInBytes =
					CommonUtils.fromIntToByteArray(this.sizeOfSecureProofOfWorkMessageDoSMitigationSerialized);

			byte[] sizeOfSecureProofOfWorkMessageComponentsSolvedBlockSerializedInBytes =
					CommonUtils.fromIntToByteArray(this.sizeOfSecureProofOfWorkMessageComponentsSolvedBlockSerialized);

			
			byte[] sizeOfSecureProofOfWorkMessageSolvedBlockInfoSerializedInBytes =
					CommonUtils.fromIntToByteArray(this.sizeOfSecureProofOfWorkMessageSolvedBlockInfoSerialized);
			byte[] sizeOfSecureProofOfWorkMessageSolvedBlockSignatureSerializedInBytes = 
					CommonUtils.fromIntToByteArray(this.sizeOfSecureProofOfWorkMessageSolvedBlockSignatureSerialized);
			
			byte[] sizeOfBlockAndBlockSolvedHashedSerializedInBytes = 
					CommonUtils.fromIntToByteArray(sizeOfBlockAndBlockSolvedHashedSerialized);
			byte[] sizeOfBlockSerializedInBytes = 
					CommonUtils.fromIntToByteArray(sizeOfBlockSerialized);
			byte[] sizeOfBlockSolvedHashedSerializedInBytes = 
					CommonUtils.fromIntToByteArray(sizeOfBlockSolvedHashedSerialized);
			
			byte[] sizeOfBidsOfCurrentBlockToTryToMineSerializedInBytes = 
					CommonUtils.fromIntToByteArray(sizeOfBidsOfCurrentBlockToTryToMineSerialized);
			
			
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
			// the correspondent bytes from the current ProofOfWork serialized,
			// From the position corresponding to the length of the previous ProofOfWork's Serialization to
			// the position corresponding to the length of the current ProofOfWork's Serialization
			System.arraycopy(outsideSeparator, 0, this.secureProofOfWorkMessageMetaHeaderSerialized, serializationOffset, outsideSeparator.length);
			serializationOffset += outsideSeparator.length;
			
			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current ProofOfWork serialized,
			// From the position corresponding to the length of the previous ProofOfWork's Serialization to
			// the position corresponding to the length of the current ProofOfWork's Serialization
			System.arraycopy(sizeOfUserPeerIDSerializedInBytes, 0, this.secureProofOfWorkMessageMetaHeaderSerialized,
					serializationOffset, sizeOfUserPeerIDSerializedInBytes.length);
			serializationOffset += sizeOfUserPeerIDSerializedInBytes.length;

			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current ProofOfWork serialized,
			// From the position corresponding to the length of the previous ProofOfWork's Serialization to
			// the position corresponding to the length of the current ProofOfWork's Serialization
			System.arraycopy(insideSeparator, 0, this.secureProofOfWorkMessageMetaHeaderSerialized, serializationOffset, insideSeparator.length);
			serializationOffset += insideSeparator.length;

			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current ProofOfWork serialized,
			// From the position corresponding to the length of the previous ProofOfWork's Serialization to
			// the position corresponding to the length of the current ProofOfWork's Serialization
			System.arraycopy(sizeOfSecureProofOfWorkMessageKeyExchangeSerializedCipheredInBytes, 0, this.secureProofOfWorkMessageMetaHeaderSerialized,
					serializationOffset, sizeOfSecureProofOfWorkMessageKeyExchangeSerializedCipheredInBytes.length);
			serializationOffset += sizeOfSecureProofOfWorkMessageKeyExchangeSerializedCipheredInBytes.length;

			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current ProofOfWork serialized,
			// From the position corresponding to the length of the previous ProofOfWork's Serialization to
			// the position corresponding to the length of the current ProofOfWork's Serialization
			System.arraycopy(insideSeparator, 0, this.secureProofOfWorkMessageMetaHeaderSerialized, serializationOffset, insideSeparator.length);
			serializationOffset += insideSeparator.length;
			
			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current ProofOfWork serialized,
			// From the position corresponding to the length of the previous ProofOfWork's Serialization to
			// the position corresponding to the length of the current ProofOfWork's Serialization
			System.arraycopy(sizeOfSecureProofOfWorkMessageKeyExchangeSerializedCipheredSignedInBytes, 0, this.secureProofOfWorkMessageMetaHeaderSerialized,
					serializationOffset, sizeOfSecureProofOfWorkMessageKeyExchangeSerializedCipheredSignedInBytes.length);
			serializationOffset += sizeOfSecureProofOfWorkMessageKeyExchangeSerializedCipheredSignedInBytes.length;
			
			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current ProofOfWork serialized,
			// From the position corresponding to the length of the previous ProofOfWork's Serialization to
			// the position corresponding to the length of the current ProofOfWork's Serialization
			System.arraycopy(insideSeparator, 0, this.secureProofOfWorkMessageMetaHeaderSerialized, serializationOffset, insideSeparator.length);
			serializationOffset += insideSeparator.length;
			
			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current ProofOfWork serialized,
			// From the position corresponding to the length of the previous ProofOfWork's Serialization to
			// the position corresponding to the length of the current ProofOfWork's Serialization
			System.arraycopy(sizeOfSecureProofOfWorkMessageComponentsSerializedCipheredInBytes, 0, this.secureProofOfWorkMessageMetaHeaderSerialized,
					serializationOffset, sizeOfSecureProofOfWorkMessageComponentsSerializedCipheredInBytes.length);
			serializationOffset += sizeOfSecureProofOfWorkMessageComponentsSerializedCipheredInBytes.length;

			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current ProofOfWork serialized,
			// From the position corresponding to the length of the previous ProofOfWork's Serialization to
			// the position corresponding to the length of the current ProofOfWork's Serialization
			System.arraycopy(insideSeparator, 0, this.secureProofOfWorkMessageMetaHeaderSerialized, serializationOffset, insideSeparator.length);
			serializationOffset += insideSeparator.length;

			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current ProofOfWork serialized,
			// From the position corresponding to the length of the previous ProofOfWork's Serialization to
			// the position corresponding to the length of the current ProofOfWork's Serialization
			System.arraycopy(sizeOfSecureProofOfWorkMessageDoSMitigationSerializedInBytes, 0, this.secureProofOfWorkMessageMetaHeaderSerialized,
					serializationOffset, sizeOfSecureProofOfWorkMessageDoSMitigationSerializedInBytes.length);
			serializationOffset += sizeOfSecureProofOfWorkMessageDoSMitigationSerializedInBytes.length;
			
			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current ProofOfWork serialized,
			// From the position corresponding to the length of the previous ProofOfWork's Serialization to
			// the position corresponding to the length of the current ProofOfWork's Serialization
			System.arraycopy(insideSeparator, 0, this.secureProofOfWorkMessageMetaHeaderSerialized, serializationOffset, insideSeparator.length);
			serializationOffset += insideSeparator.length;
			
			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current ProofOfWork serialized,
			// From the position corresponding to the length of the previous ProofOfWork's Serialization to
			// the position corresponding to the length of the current ProofOfWork's Serialization
			System.arraycopy(sizeOfSecureProofOfWorkMessageComponentsSolvedBlockSerializedInBytes, 0, this.secureProofOfWorkMessageMetaHeaderSerialized,
					serializationOffset, sizeOfSecureProofOfWorkMessageComponentsSolvedBlockSerializedInBytes.length);
			serializationOffset += sizeOfSecureProofOfWorkMessageComponentsSolvedBlockSerializedInBytes.length;
			
			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current ProofOfWork serialized,
			// From the position corresponding to the length of the previous ProofOfWork's Serialization to
			// the position corresponding to the length of the current ProofOfWork's Serialization
			System.arraycopy(insideSeparator, 0, this.secureProofOfWorkMessageMetaHeaderSerialized, serializationOffset, insideSeparator.length);
			serializationOffset += insideSeparator.length;
			
			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current ProofOfWork serialized,
			// From the position corresponding to the length of the previous ProofOfWork's Serialization to
			// the position corresponding to the length of the current ProofOfWork's Serialization
			System.arraycopy(sizeOfSecureProofOfWorkMessageSolvedBlockInfoSerializedInBytes, 0, this.secureProofOfWorkMessageMetaHeaderSerialized,
					serializationOffset, sizeOfSecureProofOfWorkMessageSolvedBlockInfoSerializedInBytes.length);
			serializationOffset += sizeOfSecureProofOfWorkMessageSolvedBlockInfoSerializedInBytes.length;

			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current ProofOfWork serialized,
			// From the position corresponding to the length of the previous ProofOfWork's Serialization to
			// the position corresponding to the length of the current ProofOfWork's Serialization
			System.arraycopy(insideSeparator, 0, this.secureProofOfWorkMessageMetaHeaderSerialized, serializationOffset, insideSeparator.length);
			serializationOffset += insideSeparator.length;
			
			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current ProofOfWork serialized,
			// From the position corresponding to the length of the previous ProofOfWork's Serialization to
			// the position corresponding to the length of the current ProofOfWork's Serialization
			System.arraycopy(sizeOfSecureProofOfWorkMessageSolvedBlockSignatureSerializedInBytes, 0, this.secureProofOfWorkMessageMetaHeaderSerialized,
					serializationOffset, sizeOfSecureProofOfWorkMessageSolvedBlockSignatureSerializedInBytes.length);
			serializationOffset += sizeOfSecureProofOfWorkMessageSolvedBlockSignatureSerializedInBytes.length;

			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current ProofOfWork serialized,
			// From the position corresponding to the length of the previous ProofOfWork's Serialization to
			// the position corresponding to the length of the current ProofOfWork's Serialization
			System.arraycopy(insideSeparator, 0, this.secureProofOfWorkMessageMetaHeaderSerialized, serializationOffset, insideSeparator.length);
			serializationOffset += insideSeparator.length;
			
			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current ProofOfWork serialized,
			// From the position corresponding to the length of the previous ProofOfWork's Serialization to
			// the position corresponding to the length of the current ProofOfWork's Serialization
			System.arraycopy(sizeOfBlockAndBlockSolvedHashedSerializedInBytes, 0, this.secureProofOfWorkMessageMetaHeaderSerialized,
					serializationOffset, sizeOfBlockAndBlockSolvedHashedSerializedInBytes.length);
			serializationOffset += sizeOfBlockAndBlockSolvedHashedSerializedInBytes.length;

			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current ProofOfWork serialized,
			// From the position corresponding to the length of the previous ProofOfWork's Serialization to
			// the position corresponding to the length of the current ProofOfWork's Serialization
			System.arraycopy(insideSeparator, 0, this.secureProofOfWorkMessageMetaHeaderSerialized, serializationOffset, insideSeparator.length);
			serializationOffset += insideSeparator.length;
			
			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current ProofOfWork serialized,
			// From the position corresponding to the length of the previous ProofOfWork's Serialization to
			// the position corresponding to the length of the current ProofOfWork's Serialization
			System.arraycopy(sizeOfBlockSerializedInBytes, 0, this.secureProofOfWorkMessageMetaHeaderSerialized,
					serializationOffset, sizeOfBlockSerializedInBytes.length);
			serializationOffset += sizeOfBlockSerializedInBytes.length;

			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current ProofOfWork serialized,
			// From the position corresponding to the length of the previous ProofOfWork's Serialization to
			// the position corresponding to the length of the current ProofOfWork's Serialization
			System.arraycopy(insideSeparator, 0, this.secureProofOfWorkMessageMetaHeaderSerialized, serializationOffset, insideSeparator.length);
			serializationOffset += insideSeparator.length;
			
			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current ProofOfWork serialized,
			// From the position corresponding to the length of the previous ProofOfWork's Serialization to
			// the position corresponding to the length of the current ProofOfWork's Serialization
			System.arraycopy(sizeOfBlockSolvedHashedSerializedInBytes, 0, this.secureProofOfWorkMessageMetaHeaderSerialized,
					serializationOffset, sizeOfBlockSolvedHashedSerializedInBytes.length);
			serializationOffset += sizeOfBlockSolvedHashedSerializedInBytes.length;

			
			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current ProofOfWork serialized,
			// From the position corresponding to the length of the previous ProofOfWork's Serialization to
			// the position corresponding to the length of the current ProofOfWork's Serialization
			System.arraycopy(insideSeparator, 0, this.secureProofOfWorkMessageMetaHeaderSerialized, serializationOffset, insideSeparator.length);
			serializationOffset += insideSeparator.length;
			
			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current ProofOfWork serialized,
			// From the position corresponding to the length of the previous ProofOfWork's Serialization to
			// the position corresponding to the length of the current ProofOfWork's Serialization
			System.arraycopy(sizeOfBidsOfCurrentBlockToTryToMineSerializedInBytes, 0, this.secureProofOfWorkMessageMetaHeaderSerialized,
					serializationOffset, sizeOfBidsOfCurrentBlockToTryToMineSerializedInBytes.length);
			serializationOffset += sizeOfBidsOfCurrentBlockToTryToMineSerializedInBytes.length;


			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current ProofOfWork serialized,
			// From the position corresponding to the length of the previous ProofOfWork's Serialization to
			// the position corresponding to the length of the current ProofOfWork's Serialization
			System.arraycopy(outsideSeparator, 0, this.secureProofOfWorkMessageMetaHeaderSerialized, serializationOffset, outsideSeparator.length);
			

			this.setIsSecureProofOfWorkMessageMetaHeaderSerialized(true);
			
		}
		
	}
	
	public void undoSecureProofOfWorkMessageMetaHeaderSerialization() {
		
		if(this.getIsSecureProofOfWorkMessageMetaHeaderSerialized()) {
		
			byte[] sizeOfUserPeerIDSerializedInBytes = new byte[CommonUtils.INTEGER_IN_BYTES_LENGTH];
			
			byte[] sizeOfSecureProofOfWorkMessageKeyExchangeSerializedCipheredInBytes = 
												new byte[CommonUtils.INTEGER_IN_BYTES_LENGTH];
			byte[] sizeOfSecureProofOfWorkMessageKeyExchangeSerializedCipheredSignedInBytes = 
												new byte[CommonUtils.INTEGER_IN_BYTES_LENGTH];
			
			byte[] sizeOfSecureProofOfWorkMessageComponentsSolvedBlockSerializedCipheredInBytes = 
						new byte[CommonUtils.INTEGER_IN_BYTES_LENGTH];
			byte[] sizeOfSecureProofOfWorkMessageDoSMitigationSerializedInBytes = 
						new byte[CommonUtils.INTEGER_IN_BYTES_LENGTH];
			

			byte[] sizeOfSecureProofOfWorkMessageComponentsSolvedBlockSerializedInBytes = 
						new byte[CommonUtils.INTEGER_IN_BYTES_LENGTH];
			
			byte[] sizeOfSecureProofOfWorkMessageSolvedBlockInfoSerializedInBytes = 
						new byte[CommonUtils.INTEGER_IN_BYTES_LENGTH];
			byte[] sizeOfSecureProofOfWorkMessageSolvedBlockSignatureSerializedInBytes = 
						new byte[CommonUtils.INTEGER_IN_BYTES_LENGTH];
			
			byte[] sizeOfBlockAndBlockSolvedHashedSerializedInBytes = new byte[CommonUtils.INTEGER_IN_BYTES_LENGTH];
			byte[] sizeOfBlockSerializedInBytes = new byte[CommonUtils.INTEGER_IN_BYTES_LENGTH];
			byte[] sizeOfBlockSolvedHashedSerializedInBytes = new byte[CommonUtils.INTEGER_IN_BYTES_LENGTH];
			
			byte[] sizeOfBidsOfCurrentBlockToTryToMineSerializedInBytes = new byte[CommonUtils.INTEGER_IN_BYTES_LENGTH];
			
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
			// the correspondent bytes from the current ProofOfWork serialized,
			// From the position corresponding to the length of the previous ProofOfWork's Serialization to
			// the position corresponding to the length of the current ProofOfWork's Serialization
			serializationOffset += outsideSeparator.length;
			System.arraycopy(this.secureProofOfWorkMessageMetaHeaderSerialized, serializationOffset,
					sizeOfUserPeerIDSerializedInBytes, 0, sizeOfUserPeerIDSerializedInBytes.length);
			serializationOffset += sizeOfUserPeerIDSerializedInBytes.length;
			
			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current ProofOfWork serialized,
			// From the position corresponding to the length of the previous ProofOfWork's Serialization to
			// the position corresponding to the length of the current ProofOfWork's Serialization
			serializationOffset += insideSeparator.length;
			System.arraycopy(this.secureProofOfWorkMessageMetaHeaderSerialized, serializationOffset,
					sizeOfSecureProofOfWorkMessageKeyExchangeSerializedCipheredInBytes,
					0, sizeOfSecureProofOfWorkMessageKeyExchangeSerializedCipheredInBytes.length);
			serializationOffset += sizeOfSecureProofOfWorkMessageKeyExchangeSerializedCipheredInBytes.length;

			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current ProofOfWork serialized,
			// From the position corresponding to the length of the previous ProofOfWork's Serialization to
			// the position corresponding to the length of the current ProofOfWork's Serialization
			serializationOffset += insideSeparator.length;
			System.arraycopy(this.secureProofOfWorkMessageMetaHeaderSerialized, serializationOffset,
					sizeOfSecureProofOfWorkMessageKeyExchangeSerializedCipheredSignedInBytes,
					0, sizeOfSecureProofOfWorkMessageKeyExchangeSerializedCipheredSignedInBytes.length);
			serializationOffset += sizeOfSecureProofOfWorkMessageKeyExchangeSerializedCipheredSignedInBytes.length;
			
			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current ProofOfWork serialized,
			// From the position corresponding to the length of the previous ProofOfWork's Serialization to
			// the position corresponding to the length of the current ProofOfWork's Serialization
			serializationOffset += insideSeparator.length;
			System.arraycopy(this.secureProofOfWorkMessageMetaHeaderSerialized, serializationOffset,
					sizeOfSecureProofOfWorkMessageComponentsSolvedBlockSerializedCipheredInBytes,
					0, sizeOfSecureProofOfWorkMessageComponentsSolvedBlockSerializedCipheredInBytes.length);
			serializationOffset += sizeOfSecureProofOfWorkMessageComponentsSolvedBlockSerializedCipheredInBytes.length;
					
			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current ProofOfWork serialized,
			// From the position corresponding to the length of the previous ProofOfWork's Serialization to
			// the position corresponding to the length of the current ProofOfWork's Serialization
			serializationOffset += insideSeparator.length;
			System.arraycopy(this.secureProofOfWorkMessageMetaHeaderSerialized, serializationOffset,
					sizeOfSecureProofOfWorkMessageDoSMitigationSerializedInBytes,
					0, sizeOfSecureProofOfWorkMessageDoSMitigationSerializedInBytes.length);
			serializationOffset += sizeOfSecureProofOfWorkMessageDoSMitigationSerializedInBytes.length;
			
			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current ProofOfWork serialized,
			// From the position corresponding to the length of the previous ProofOfWork's Serialization to
			// the position corresponding to the length of the current ProofOfWork's Serialization
			serializationOffset += insideSeparator.length;
			System.arraycopy(this.secureProofOfWorkMessageMetaHeaderSerialized, serializationOffset,
					sizeOfSecureProofOfWorkMessageComponentsSolvedBlockSerializedInBytes,
					0, sizeOfSecureProofOfWorkMessageComponentsSolvedBlockSerializedInBytes.length);
			serializationOffset += sizeOfSecureProofOfWorkMessageComponentsSolvedBlockSerializedInBytes.length;
			
			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current ProofOfWork serialized,
			// From the position corresponding to the length of the previous ProofOfWork's Serialization to
			// the position corresponding to the length of the current ProofOfWork's Serialization
			serializationOffset += insideSeparator.length;
			System.arraycopy(this.secureProofOfWorkMessageMetaHeaderSerialized, serializationOffset,
					sizeOfSecureProofOfWorkMessageSolvedBlockInfoSerializedInBytes,
					0, sizeOfSecureProofOfWorkMessageSolvedBlockInfoSerializedInBytes.length);
			serializationOffset += sizeOfSecureProofOfWorkMessageSolvedBlockInfoSerializedInBytes.length;
			
			
			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current ProofOfWork serialized,
			// From the position corresponding to the length of the previous ProofOfWork's Serialization to
			// the position corresponding to the length of the current ProofOfWork's Serialization
			serializationOffset += insideSeparator.length;
			System.arraycopy(this.secureProofOfWorkMessageMetaHeaderSerialized, serializationOffset,
					sizeOfSecureProofOfWorkMessageSolvedBlockSignatureSerializedInBytes,
					0, sizeOfSecureProofOfWorkMessageSolvedBlockSignatureSerializedInBytes.length);
			serializationOffset += sizeOfSecureProofOfWorkMessageSolvedBlockSignatureSerializedInBytes.length;
			
			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current ProofOfWork serialized,
			// From the position corresponding to the length of the previous ProofOfWork's Serialization to
			// the position corresponding to the length of the current ProofOfWork's Serialization
			serializationOffset += insideSeparator.length;
			System.arraycopy(this.secureProofOfWorkMessageMetaHeaderSerialized, serializationOffset,
					sizeOfBlockAndBlockSolvedHashedSerializedInBytes,
					0, sizeOfBlockAndBlockSolvedHashedSerializedInBytes.length);
			serializationOffset += sizeOfBlockAndBlockSolvedHashedSerializedInBytes.length;
			
			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current ProofOfWork serialized,
			// From the position corresponding to the length of the previous ProofOfWork's Serialization to
			// the position corresponding to the length of the current ProofOfWork's Serialization
			serializationOffset += insideSeparator.length;
			System.arraycopy(this.secureProofOfWorkMessageMetaHeaderSerialized, serializationOffset,
					sizeOfBlockSerializedInBytes,
					0, sizeOfBlockSerializedInBytes.length);
			serializationOffset += sizeOfBlockSerializedInBytes.length;
			
			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current ProofOfWork serialized,
			// From the position corresponding to the length of the previous ProofOfWork's Serialization to
			// the position corresponding to the length of the current ProofOfWork's Serialization
			serializationOffset += insideSeparator.length;
			System.arraycopy(this.secureProofOfWorkMessageMetaHeaderSerialized, serializationOffset,
					sizeOfBlockSolvedHashedSerializedInBytes,
					0, sizeOfBlockSolvedHashedSerializedInBytes.length);
			serializationOffset += sizeOfBlockSolvedHashedSerializedInBytes.length;
			
			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current ProofOfWork serialized,
			// From the position corresponding to the length of the previous ProofOfWork's Serialization to
			// the position corresponding to the length of the current ProofOfWork's Serialization
			serializationOffset += insideSeparator.length;
			System.arraycopy(this.secureProofOfWorkMessageMetaHeaderSerialized, serializationOffset,
					sizeOfBidsOfCurrentBlockToTryToMineSerializedInBytes,
					0, sizeOfBidsOfCurrentBlockToTryToMineSerializedInBytes.length);
			serializationOffset += sizeOfBidsOfCurrentBlockToTryToMineSerializedInBytes.length;
			
			
			this.sizeOfUserPeerIDSerialized = 
					CommonUtils.fromByteArrayToInt(sizeOfUserPeerIDSerializedInBytes);
			
			this.sizeOfSecureProofOfWorkMessageKeyExchangeSerializedCiphered = 
					CommonUtils.fromByteArrayToInt(sizeOfSecureProofOfWorkMessageKeyExchangeSerializedCipheredInBytes);
			this.sizeOfSecureProofOfWorkMessageKeyExchangeSerializedCipheredSigned = 
					CommonUtils.fromByteArrayToInt(sizeOfSecureProofOfWorkMessageKeyExchangeSerializedCipheredSignedInBytes);
			
			
			this.sizeOfSecureProofOfWorkMessageComponentsSerializedCiphered = 
					CommonUtils.fromByteArrayToInt(sizeOfSecureProofOfWorkMessageComponentsSolvedBlockSerializedCipheredInBytes);
			this.sizeOfSecureProofOfWorkMessageDoSMitigationSerialized = 
					CommonUtils.fromByteArrayToInt(sizeOfSecureProofOfWorkMessageDoSMitigationSerializedInBytes);
			
			this.sizeOfSecureProofOfWorkMessageComponentsSolvedBlockSerialized = 
					CommonUtils.fromByteArrayToInt(sizeOfSecureProofOfWorkMessageComponentsSolvedBlockSerializedInBytes);
			
			this.sizeOfSecureProofOfWorkMessageSolvedBlockInfoSerialized = 
					CommonUtils.fromByteArrayToInt(sizeOfSecureProofOfWorkMessageSolvedBlockInfoSerializedInBytes);
			this.sizeOfSecureProofOfWorkMessageSolvedBlockSignatureSerialized = 
					CommonUtils.fromByteArrayToInt(sizeOfSecureProofOfWorkMessageSolvedBlockSignatureSerializedInBytes);

			
			this.sizeOfBlockAndBlockSolvedHashedSerialized = 
					CommonUtils.fromByteArrayToInt(sizeOfBlockAndBlockSolvedHashedSerializedInBytes);

			this.sizeOfBlockSerialized = CommonUtils.fromByteArrayToInt(sizeOfBlockSerializedInBytes);

			this.sizeOfBlockSolvedHashedSerialized = CommonUtils.fromByteArrayToInt(sizeOfBlockSolvedHashedSerializedInBytes);
			
			this.sizeOfBidsOfCurrentBlockToTryToMineSerialized = CommonUtils.fromByteArrayToInt(sizeOfBidsOfCurrentBlockToTryToMineSerializedInBytes);
			
			
			this.setIsSecureProofOfWorkMessageMetaHeaderSerialized(false);
			
		}
	}
}
