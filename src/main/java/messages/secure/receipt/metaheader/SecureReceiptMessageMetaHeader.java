package main.java.messages.secure.receipt.metaheader;

import main.java.common.utils.CommonUtils;

public class SecureReceiptMessageMetaHeader {

	private int sizeOfUserPeerIDSerialized;
		
	private int sizeOfSecureReceiptMessageKeyExchangeSerializedCiphered;
	private int sizeOfSecureReceiptMessageKeyExchangeSerializedCipheredSigned;
	
	private int sizeOfSecureReceiptMessageComponentsCiphered;
	private int sizeOfSecureReceiptMessageDoSMitigationSerialized;
	 
	private int sizeOfSecureReceiptMessageComponentsDataSerialized;
	
	private int sizeOfSecureReceiptMessageComponentsDataInfoSerialized;
	private int sizeOfSecureReceiptMessageComponentsDataSignatureSerialized;
	private int sizeOfSecureReceiptMessageComponentsDataResponseSerialized;
	
	
	private int sizeOfBidderUserClientIDSerialized;	
	
	
	private byte[] secureReceiptMessageMetaHeaderSerialized;
	
	private boolean isSecureReceiptMessageMetaHeaderSerialized;

	
	public SecureReceiptMessageMetaHeader(int sizeOfUserPeerIDSerialized,
										  int sizeOfSecureReceiptMessageKeyExchangeSerializedCiphered,
										  int sizeOfSecureReceiptMessageKeyExchangeSerializedCipheredSigned,
										  int sizeOfSecureReceiptMessageComponentsCiphered,
										  int sizeOfSecureReceiptMessageDoSMitigationSerialized,
										  int sizeOfSecureReceiptMessageComponentsDataSerialized,
										  int sizeOfSecureReceiptMessageComponentsDataInfoSerialized,
										  int sizeOfSecureReceiptMessageComponentsDataSignatureSerialized,
										  int sizeOfSecureReceiptMessageComponentsDataResponseSerialized,
										  int sizeOfBidderUserClientIDSerialized) {
		
		this.sizeOfUserPeerIDSerialized = sizeOfUserPeerIDSerialized;
		
		this.sizeOfSecureReceiptMessageKeyExchangeSerializedCiphered = 
				sizeOfSecureReceiptMessageKeyExchangeSerializedCiphered;
		this.sizeOfSecureReceiptMessageKeyExchangeSerializedCipheredSigned = 
				sizeOfSecureReceiptMessageKeyExchangeSerializedCipheredSigned;
		
		this.sizeOfSecureReceiptMessageComponentsCiphered =
				sizeOfSecureReceiptMessageComponentsCiphered;
		this.sizeOfSecureReceiptMessageDoSMitigationSerialized = 
				sizeOfSecureReceiptMessageDoSMitigationSerialized;
		 
		this.sizeOfSecureReceiptMessageComponentsDataSerialized =
				sizeOfSecureReceiptMessageComponentsDataSerialized;
		
		this.sizeOfSecureReceiptMessageComponentsDataInfoSerialized = 
				sizeOfSecureReceiptMessageComponentsDataInfoSerialized;
		this.sizeOfSecureReceiptMessageComponentsDataSignatureSerialized =
				sizeOfSecureReceiptMessageComponentsDataSignatureSerialized;
		this.sizeOfSecureReceiptMessageComponentsDataResponseSerialized =
				sizeOfSecureReceiptMessageComponentsDataResponseSerialized;
		
		this.sizeOfBidderUserClientIDSerialized = 
				sizeOfBidderUserClientIDSerialized;	
		
		
		this.secureReceiptMessageMetaHeaderSerialized = null;
		
		this.isSecureReceiptMessageMetaHeaderSerialized = false;
		
	}
	
	
	public SecureReceiptMessageMetaHeader(byte[] secureReceiptMessageMetaHeaderSerialized) {

		this.secureReceiptMessageMetaHeaderSerialized = secureReceiptMessageMetaHeaderSerialized;
		this.isSecureReceiptMessageMetaHeaderSerialized = true;
		
		this.sizeOfBidderUserClientIDSerialized = 0;
		
		this.sizeOfSecureReceiptMessageComponentsDataSerialized = 0;
		
		this.sizeOfSecureReceiptMessageComponentsDataInfoSerialized = 0;
		this.sizeOfSecureReceiptMessageComponentsDataSignatureSerialized = 0;
		this.sizeOfSecureReceiptMessageComponentsDataResponseSerialized = 0;
		

		this.sizeOfSecureReceiptMessageComponentsCiphered = 0;
		this.sizeOfSecureReceiptMessageDoSMitigationSerialized = 0;
		
		this.sizeOfSecureReceiptMessageKeyExchangeSerializedCiphered = 0;
		this.sizeOfSecureReceiptMessageKeyExchangeSerializedCipheredSigned = 0;

		this.sizeOfUserPeerIDSerialized = 0;
	
	}
	
	
	
	public int getSizeOfUserPeerIDSerialized() {
		return this.sizeOfUserPeerIDSerialized;
	}
	
	public int getSizeOfSecureReceiptMessageKeyExchangeSerializedCiphered() {
		return this.sizeOfSecureReceiptMessageKeyExchangeSerializedCiphered;
	}
	
	public int getSizeOfSecureReceiptMessageKeyExchangeSerializedCipheredSigned() {
		return this.sizeOfSecureReceiptMessageKeyExchangeSerializedCipheredSigned;
	}
	
	public int getSizeOfSecureReceiptMessageComponentsCiphered() {
		return this.sizeOfSecureReceiptMessageComponentsCiphered;
	}
	
	public int getSizeOfSecureReceiptMessageDoSMitigationSerialized() {
		return this.sizeOfSecureReceiptMessageDoSMitigationSerialized;
	}
	
	public int getSizeOfSecureReceiptMessageComponentsDataSerialized() {
		return this.sizeOfSecureReceiptMessageComponentsDataSerialized;
	}
	
	public int getSizeOfSecureReceiptMessageComponentsDataInfoSerialized() {
		return this.sizeOfSecureReceiptMessageComponentsDataInfoSerialized;
	}
	
	public int getSizeOfSecureReceiptMessageComponentsDataSignatureSerialized() {
		return this.sizeOfSecureReceiptMessageComponentsDataSignatureSerialized;
	}
	
	public int getSizeOfSecureReceiptMessageComponentsDataResponseSerialized() {
		return this.sizeOfSecureReceiptMessageComponentsDataResponseSerialized;
	}
	
	
	public int getSizeOfBidderUserClientIDSerialized() {
		return this.sizeOfBidderUserClientIDSerialized;
	}
	
	
	public byte[] getSecureReceiptMessageMetaHeaderSerialized() {
		return this.secureReceiptMessageMetaHeaderSerialized;
	}
	
	public boolean getIsSecureReceiptMessageMetaHeaderSerialized() {
		return this.isSecureReceiptMessageMetaHeaderSerialized;
	}
	
	public void setIsSecureReceiptMessageMetaHeaderSerialized
		  (boolean isSecureReceiptMessageMetaHeaderSerialized) {
		
		this.isSecureReceiptMessageMetaHeaderSerialized = 
				isSecureReceiptMessageMetaHeaderSerialized;
	
	}
	
	
	
	public void doSecureReceiptMessageMetaHeaderSerialization() {
		
		if(!this.getIsSecureReceiptMessageMetaHeaderSerialized()) {
			
			int sizeOfSecureReceiptMessageMetaHeaderSerialized = ( ( 2 * CommonUtils.META_HEADER_OUTSIDE_SEPARATORS_LENGTH) +
																   ( 9 * CommonUtils.META_HEADER_INSIDE_SEPARATORS_LENGTH) +
																   ( 10 * CommonUtils.INTEGER_IN_BYTES_LENGTH ) );

			this.secureReceiptMessageMetaHeaderSerialized = new byte[ sizeOfSecureReceiptMessageMetaHeaderSerialized ];

			byte[] outsideSeparator = new byte[] { ( (byte) 0x00 ), ( (byte) 0x00 ) };
			byte[] insideSeparator = new byte[] { ( (byte) 0x00 ) };
			
			byte[] sizeOfUserPeerIDSerializedInBytes = 
					CommonUtils.fromIntToByteArray(this.sizeOfUserPeerIDSerialized);
					
			byte[] sizeOfSecureReceiptMessageKeyExchangeSerializedCipheredInBytes = 
					CommonUtils.fromIntToByteArray(this.sizeOfSecureReceiptMessageKeyExchangeSerializedCiphered);
			byte[] sizeOfSecureReceiptMessageKeyExchangeSerializedCipheredSignedInBytes = 
					CommonUtils.fromIntToByteArray(this.sizeOfSecureReceiptMessageKeyExchangeSerializedCipheredSigned);
			
			byte[] sizeOfSecureReceiptMessageComponentsCipheredInBytes = 
					CommonUtils.fromIntToByteArray(this.sizeOfSecureReceiptMessageComponentsCiphered);
			byte[] sizeOfSecureReceiptMessageDoSMitigationSerializedInBytes = 
					CommonUtils.fromIntToByteArray(this.sizeOfSecureReceiptMessageDoSMitigationSerialized);

			
			byte[] sizeOfSecureReceiptMessageComponentsDataSerializedInBytes = 
					CommonUtils.fromIntToByteArray(this.sizeOfSecureReceiptMessageComponentsDataSerialized);
			
			byte[] sizeOfSecureReceiptMessageComponentsDataInfoSerializedInBytes = 
					CommonUtils.fromIntToByteArray(this.sizeOfSecureReceiptMessageComponentsDataInfoSerialized);
			byte[] sizeOfSecureReceiptMessageComponentsDataSignatureSerializedInBytes = 
					CommonUtils.fromIntToByteArray(this.sizeOfSecureReceiptMessageComponentsDataSignatureSerialized);
			byte[] sizeOfSecureReceiptMessageComponentsDataResponseSerializedInBytes = 
					CommonUtils.fromIntToByteArray(this.sizeOfSecureReceiptMessageComponentsDataResponseSerialized);

			
			byte[] sizeOfBidderUserClientIDSerializedInBytes = 
				    CommonUtils.fromIntToByteArray(this.sizeOfBidderUserClientIDSerialized);
			
			
			
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
			System.arraycopy(outsideSeparator, 0, this.secureReceiptMessageMetaHeaderSerialized, serializationOffset, outsideSeparator.length);
			serializationOffset += outsideSeparator.length;
			
			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(sizeOfUserPeerIDSerializedInBytes, 0, this.secureReceiptMessageMetaHeaderSerialized,
					serializationOffset, sizeOfUserPeerIDSerializedInBytes.length);
			serializationOffset += sizeOfUserPeerIDSerializedInBytes.length;

			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(insideSeparator, 0, this.secureReceiptMessageMetaHeaderSerialized, serializationOffset, insideSeparator.length);
			serializationOffset += insideSeparator.length;
			
			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(sizeOfSecureReceiptMessageKeyExchangeSerializedCipheredInBytes, 0, this.secureReceiptMessageMetaHeaderSerialized,
					serializationOffset, sizeOfSecureReceiptMessageKeyExchangeSerializedCipheredInBytes.length);
			serializationOffset += sizeOfSecureReceiptMessageKeyExchangeSerializedCipheredInBytes.length;

			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(insideSeparator, 0, this.secureReceiptMessageMetaHeaderSerialized, serializationOffset, insideSeparator.length);
			serializationOffset += insideSeparator.length;
			
			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(sizeOfSecureReceiptMessageKeyExchangeSerializedCipheredSignedInBytes, 0, this.secureReceiptMessageMetaHeaderSerialized,
					serializationOffset, sizeOfSecureReceiptMessageKeyExchangeSerializedCipheredSignedInBytes.length);
			serializationOffset += sizeOfSecureReceiptMessageKeyExchangeSerializedCipheredSignedInBytes.length;
			
			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(insideSeparator, 0, this.secureReceiptMessageMetaHeaderSerialized, serializationOffset, insideSeparator.length);
			serializationOffset += insideSeparator.length;
			
			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(sizeOfSecureReceiptMessageComponentsCipheredInBytes, 0, this.secureReceiptMessageMetaHeaderSerialized,
					serializationOffset, sizeOfSecureReceiptMessageComponentsCipheredInBytes.length);
			serializationOffset += sizeOfSecureReceiptMessageComponentsCipheredInBytes.length;
			
			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(insideSeparator, 0, this.secureReceiptMessageMetaHeaderSerialized, serializationOffset, insideSeparator.length);
			serializationOffset += insideSeparator.length;

			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(sizeOfSecureReceiptMessageDoSMitigationSerializedInBytes, 0, this.secureReceiptMessageMetaHeaderSerialized,
					serializationOffset, sizeOfSecureReceiptMessageDoSMitigationSerializedInBytes.length);
			serializationOffset += sizeOfSecureReceiptMessageDoSMitigationSerializedInBytes.length;

			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(insideSeparator, 0, this.secureReceiptMessageMetaHeaderSerialized, serializationOffset, insideSeparator.length);
			serializationOffset += insideSeparator.length;
			
			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(sizeOfSecureReceiptMessageComponentsDataSerializedInBytes, 0, this.secureReceiptMessageMetaHeaderSerialized,
					serializationOffset, sizeOfSecureReceiptMessageComponentsDataSerializedInBytes.length);
			serializationOffset += sizeOfSecureReceiptMessageComponentsDataSerializedInBytes.length;
			
			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(insideSeparator, 0, this.secureReceiptMessageMetaHeaderSerialized, serializationOffset, insideSeparator.length);
			serializationOffset += insideSeparator.length;
			
			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(sizeOfSecureReceiptMessageComponentsDataInfoSerializedInBytes, 0, this.secureReceiptMessageMetaHeaderSerialized,
					serializationOffset, sizeOfSecureReceiptMessageComponentsDataInfoSerializedInBytes.length);
			serializationOffset += sizeOfSecureReceiptMessageComponentsDataInfoSerializedInBytes.length;

			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(insideSeparator, 0, this.secureReceiptMessageMetaHeaderSerialized, serializationOffset, insideSeparator.length);
			serializationOffset += insideSeparator.length;
			
			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(sizeOfSecureReceiptMessageComponentsDataSignatureSerializedInBytes, 0, this.secureReceiptMessageMetaHeaderSerialized,
					serializationOffset, sizeOfSecureReceiptMessageComponentsDataSignatureSerializedInBytes.length);
			serializationOffset += sizeOfSecureReceiptMessageComponentsDataSignatureSerializedInBytes.length;

			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(insideSeparator, 0, this.secureReceiptMessageMetaHeaderSerialized, serializationOffset, insideSeparator.length);
			serializationOffset += insideSeparator.length;
			
			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(sizeOfSecureReceiptMessageComponentsDataResponseSerializedInBytes, 0, this.secureReceiptMessageMetaHeaderSerialized,
					serializationOffset, sizeOfSecureReceiptMessageComponentsDataResponseSerializedInBytes.length);
			serializationOffset += sizeOfSecureReceiptMessageComponentsDataResponseSerializedInBytes.length;

			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(insideSeparator, 0, this.secureReceiptMessageMetaHeaderSerialized, serializationOffset, insideSeparator.length);
			serializationOffset += insideSeparator.length;
			
			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(sizeOfBidderUserClientIDSerializedInBytes, 0, this.secureReceiptMessageMetaHeaderSerialized,
					serializationOffset, sizeOfBidderUserClientIDSerializedInBytes.length);
			serializationOffset += sizeOfBidderUserClientIDSerializedInBytes.length;

			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(outsideSeparator, 0, this.secureReceiptMessageMetaHeaderSerialized, serializationOffset, outsideSeparator.length);


			this.setIsSecureReceiptMessageMetaHeaderSerialized(true);
			
		}
		
	}
	
	
	public void undoSecureReceiptMessageMetaHeaderSerialization() {
		
		if(this.getIsSecureReceiptMessageMetaHeaderSerialized()) {
		

			byte[] sizeOfUserPeerIDSerializedInBytes = new byte[CommonUtils.INTEGER_IN_BYTES_LENGTH];
			
			byte[] sizeOfSecureReceiptMessageKeyExchangeSerializedCipheredInBytes = new byte[CommonUtils.INTEGER_IN_BYTES_LENGTH];
			byte[] sizeOfSecureReceiptMessageKeyExchangeSerializedCipheredSignedInBytes = new byte[CommonUtils.INTEGER_IN_BYTES_LENGTH];
			
			byte[] sizeOfSecureReceiptMessageComponentsCipheredInBytes = new byte[CommonUtils.INTEGER_IN_BYTES_LENGTH];
			byte[] sizeOfSecureReceiptMessageDoSMitigationSerializedInBytes = new byte[CommonUtils.INTEGER_IN_BYTES_LENGTH];

			byte[] sizeOfSecureReceiptMessageComponentsDataSerializedInBytes = new byte[CommonUtils.INTEGER_IN_BYTES_LENGTH];
			
			byte[] sizeOfSecureReceiptMessageComponentsDataInfoSerializedInBytes = new byte[CommonUtils.INTEGER_IN_BYTES_LENGTH];
			byte[] sizeOfSecureReceiptMessageComponentsDataSignatureSerializedInBytes = new byte[CommonUtils.INTEGER_IN_BYTES_LENGTH];
			byte[] sizeOfSecureReceiptMessageComponentsDataResponseSerializedInBytes = new byte[CommonUtils.INTEGER_IN_BYTES_LENGTH];

			
			byte[] sizeOfBidderUserClientIDSerializedInBytes = new byte[CommonUtils.INTEGER_IN_BYTES_LENGTH];
			
						
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
			System.arraycopy(this.secureReceiptMessageMetaHeaderSerialized, serializationOffset,
					sizeOfUserPeerIDSerializedInBytes, 0, sizeOfUserPeerIDSerializedInBytes.length);
			serializationOffset += sizeOfUserPeerIDSerializedInBytes.length;
			
			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			serializationOffset += insideSeparator.length;
			System.arraycopy(this.secureReceiptMessageMetaHeaderSerialized, serializationOffset,
					sizeOfSecureReceiptMessageKeyExchangeSerializedCipheredInBytes,
					0, sizeOfSecureReceiptMessageKeyExchangeSerializedCipheredInBytes.length);
			serializationOffset += sizeOfSecureReceiptMessageKeyExchangeSerializedCipheredInBytes.length;

			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			serializationOffset += insideSeparator.length;
			System.arraycopy(this.secureReceiptMessageMetaHeaderSerialized, serializationOffset,
					sizeOfSecureReceiptMessageKeyExchangeSerializedCipheredSignedInBytes,
					0, sizeOfSecureReceiptMessageKeyExchangeSerializedCipheredSignedInBytes.length);
			serializationOffset += sizeOfSecureReceiptMessageKeyExchangeSerializedCipheredSignedInBytes.length;
			
			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			serializationOffset += insideSeparator.length;
			System.arraycopy(this.secureReceiptMessageMetaHeaderSerialized, serializationOffset,
					sizeOfSecureReceiptMessageComponentsCipheredInBytes,
					0, sizeOfSecureReceiptMessageComponentsCipheredInBytes.length);
			serializationOffset += sizeOfSecureReceiptMessageComponentsCipheredInBytes.length;

			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			serializationOffset += insideSeparator.length;
			System.arraycopy(this.secureReceiptMessageMetaHeaderSerialized, serializationOffset,
					sizeOfSecureReceiptMessageDoSMitigationSerializedInBytes,
					0, sizeOfSecureReceiptMessageDoSMitigationSerializedInBytes.length);
			serializationOffset += sizeOfSecureReceiptMessageDoSMitigationSerializedInBytes.length;


			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			serializationOffset += insideSeparator.length;
			System.arraycopy(this.secureReceiptMessageMetaHeaderSerialized, serializationOffset,
					sizeOfSecureReceiptMessageComponentsDataSerializedInBytes,
					0, sizeOfSecureReceiptMessageComponentsDataSerializedInBytes.length);
			serializationOffset += sizeOfSecureReceiptMessageComponentsDataSerializedInBytes.length;
			
			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			serializationOffset += insideSeparator.length;
			System.arraycopy(this.secureReceiptMessageMetaHeaderSerialized, serializationOffset,
					sizeOfSecureReceiptMessageComponentsDataInfoSerializedInBytes,
					0, sizeOfSecureReceiptMessageComponentsDataInfoSerializedInBytes.length);
			serializationOffset += sizeOfSecureReceiptMessageComponentsDataInfoSerializedInBytes.length;

			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			serializationOffset += insideSeparator.length;
			System.arraycopy(this.secureReceiptMessageMetaHeaderSerialized, serializationOffset,
					sizeOfSecureReceiptMessageComponentsDataSignatureSerializedInBytes,
					0, sizeOfSecureReceiptMessageComponentsDataSignatureSerializedInBytes.length);
			serializationOffset += sizeOfSecureReceiptMessageComponentsDataSignatureSerializedInBytes.length;

			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			serializationOffset += insideSeparator.length;
			System.arraycopy(this.secureReceiptMessageMetaHeaderSerialized, serializationOffset,
					sizeOfSecureReceiptMessageComponentsDataResponseSerializedInBytes,
					0, sizeOfSecureReceiptMessageComponentsDataResponseSerializedInBytes.length);
			serializationOffset += sizeOfSecureReceiptMessageComponentsDataResponseSerializedInBytes.length;

			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			serializationOffset += insideSeparator.length;
			System.arraycopy(this.secureReceiptMessageMetaHeaderSerialized, serializationOffset,
					sizeOfBidderUserClientIDSerializedInBytes,
					0, sizeOfBidderUserClientIDSerializedInBytes.length);
			serializationOffset += sizeOfBidderUserClientIDSerializedInBytes.length;


			this.sizeOfUserPeerIDSerialized = 
					CommonUtils.fromByteArrayToInt(sizeOfUserPeerIDSerializedInBytes);
			
			this.sizeOfSecureReceiptMessageKeyExchangeSerializedCiphered = 
					CommonUtils.fromByteArrayToInt(sizeOfSecureReceiptMessageKeyExchangeSerializedCipheredInBytes);
			this.sizeOfSecureReceiptMessageKeyExchangeSerializedCipheredSigned = 
					CommonUtils.fromByteArrayToInt(sizeOfSecureReceiptMessageKeyExchangeSerializedCipheredSignedInBytes);
			
			this.sizeOfSecureReceiptMessageComponentsCiphered = 
					CommonUtils.fromByteArrayToInt(sizeOfSecureReceiptMessageComponentsCipheredInBytes);
			this.sizeOfSecureReceiptMessageDoSMitigationSerialized = 
					CommonUtils.fromByteArrayToInt(sizeOfSecureReceiptMessageDoSMitigationSerializedInBytes);

			this.sizeOfSecureReceiptMessageComponentsDataSerialized = 
					CommonUtils.fromByteArrayToInt(sizeOfSecureReceiptMessageComponentsDataSerializedInBytes);

			this.sizeOfSecureReceiptMessageComponentsDataInfoSerialized = 
					CommonUtils.fromByteArrayToInt(sizeOfSecureReceiptMessageComponentsDataInfoSerializedInBytes);
			this.sizeOfSecureReceiptMessageComponentsDataSignatureSerialized = 
					CommonUtils.fromByteArrayToInt(sizeOfSecureReceiptMessageComponentsDataSignatureSerializedInBytes);
			this.sizeOfSecureReceiptMessageComponentsDataResponseSerialized = 
					CommonUtils.fromByteArrayToInt(sizeOfSecureReceiptMessageComponentsDataResponseSerializedInBytes);
			
			this.sizeOfBidderUserClientIDSerialized = CommonUtils.fromByteArrayToInt(sizeOfBidderUserClientIDSerializedInBytes);
			
			
			this.setIsSecureReceiptMessageMetaHeaderSerialized(false);
			
		}
	}
	
}