package main.java.messages.secure.bid.metaheader;

import main.java.common.utils.CommonUtils;

public class SecureBidMessageMetaHeader {
	
	private int sizeOfUserPeerIDSerialized;
	
	private int sizeOfInitialisationVector;
	
	private int sizeOfSecureBidMessageKeyExchangeSerializedCiphered;
	private int sizeOfSecureBidMessageKeyExchangeSerializedCipheredSigned;
	
	private int sizeOfSecureBidMessageDataSerialized;
	private int sizeOfSecureBidMessageDoSMitigationSerialized;
	  
	private int sizeOfSecureBidMessageDataSignatureSerialized;
	private int sizeOfSecureBidMessageDataConfidentialSerializedCipheredAndHashed;
	  
	private int sizeOfBidSerialized;
	private int sizeOfBidSerializedDigitalSigned;
	  
	private int sizeOfBidderUserClientIDSerialized;
	  
	private int sizeOfSecureBidMessageDataConfidentialSerializedCiphered;
	private int sizeOfSecureBidMessageDataConfidentialSerializedCipheredHashed;
	private int sizeOfSecureBidMessageDataConfidentialSerialized;
	  
	private int sizeOfUserEmailSerialized;
	private int sizeOfUserHomeAddressSerialized;
	private int sizeOfUserBankAccountNIBSerialized;
	
	
	private byte[] secureBidMessageMetaHeaderSerialized;
	
	private boolean isSecureBidMessageMetaHeaderSerialized;

	
	
	public SecureBidMessageMetaHeader(int sizeOfUserPeerIDSerialized,
				
									  int sizeOfInitialisationVector,
			
									  int sizeOfSecureBidMessageKeyExchangeSerializedCiphered,
			  						  int sizeOfSecureBidMessageKeyExchangeSerializedCipheredSigned,
			  						  
			  						  int sizeOfSecureBidMessageDataSerialized,
									  int sizeOfSecureBidMessageDoSMitigationSerialized,
									  
									  int sizeOfSecureBidMessageDataSignatureSerialized,
									  int sizeOfSecureBidMessageDataConfidentialSerializedCipheredAndHashed,
									  
									  int sizeOfBidSerialized,
									  int sizeOfBidSerializedDigitalSigned,
									  
									  int sizeOfBidderUserClientIDSerialized,
									  
									  int sizeOfSecureBidMessageDataConfidentialSerializedCiphered,
									  int sizeOfSecureBidMessageDataConfidentialSerializedCipheredHashed,
									  int sizeOfSecureBidMessageDataConfidentialSerialized, 
									  
									  int sizeOfUserEmailSerialized,
									  int sizeOfUserHomeAddressSerialized,
									  int sizeOfUserBankAccountNIBSerialized) {
		
		this.sizeOfUserPeerIDSerialized = sizeOfUserPeerIDSerialized;
		
		this.sizeOfInitialisationVector = sizeOfInitialisationVector;
		
		this.sizeOfSecureBidMessageKeyExchangeSerializedCiphered = 
				sizeOfSecureBidMessageKeyExchangeSerializedCiphered;
		this.sizeOfSecureBidMessageKeyExchangeSerializedCipheredSigned =
				sizeOfSecureBidMessageKeyExchangeSerializedCipheredSigned;
		
		this.sizeOfSecureBidMessageDataSerialized = sizeOfSecureBidMessageDataSerialized;
		this.sizeOfSecureBidMessageDoSMitigationSerialized = sizeOfSecureBidMessageDoSMitigationSerialized;
		  
		this.sizeOfSecureBidMessageDataSignatureSerialized = sizeOfSecureBidMessageDataSignatureSerialized;
		this.sizeOfSecureBidMessageDataConfidentialSerializedCipheredAndHashed = 
				sizeOfSecureBidMessageDataConfidentialSerializedCipheredAndHashed;
		  
		this.sizeOfBidSerialized = sizeOfBidSerialized;
		this.sizeOfBidSerializedDigitalSigned = sizeOfBidSerializedDigitalSigned;
		  
		this.sizeOfBidderUserClientIDSerialized = sizeOfBidderUserClientIDSerialized;
		  
		this.sizeOfSecureBidMessageDataConfidentialSerializedCiphered =
		     sizeOfSecureBidMessageDataConfidentialSerializedCiphered;
		this.sizeOfSecureBidMessageDataConfidentialSerializedCipheredHashed = 
			 sizeOfSecureBidMessageDataConfidentialSerializedCipheredHashed;
		this.sizeOfSecureBidMessageDataConfidentialSerialized = sizeOfSecureBidMessageDataConfidentialSerialized;
		
		this.sizeOfUserEmailSerialized = 
						sizeOfUserEmailSerialized;
		this.sizeOfUserHomeAddressSerialized = 
						sizeOfUserHomeAddressSerialized;
		this.sizeOfUserBankAccountNIBSerialized =
						sizeOfUserBankAccountNIBSerialized;
		
		this.secureBidMessageMetaHeaderSerialized = null;
		this.isSecureBidMessageMetaHeaderSerialized = false;
		
	}
	
	public SecureBidMessageMetaHeader(byte[] secureBidMessageMetaHeaderSerialized) {

		this.secureBidMessageMetaHeaderSerialized = secureBidMessageMetaHeaderSerialized;
		this.isSecureBidMessageMetaHeaderSerialized = true;
		
		this.sizeOfUserEmailSerialized = 0;
		this.sizeOfUserHomeAddressSerialized = 0;
		this.sizeOfUserBankAccountNIBSerialized = 0;
		
		this.sizeOfSecureBidMessageDataConfidentialSerializedCiphered = 0;
		this.sizeOfSecureBidMessageDataConfidentialSerializedCipheredHashed = 0;
		this.sizeOfSecureBidMessageDataConfidentialSerialized = 0;
	
		this.sizeOfBidderUserClientIDSerialized = 0;

		this.sizeOfBidSerialized = 0;
		this.sizeOfBidSerializedDigitalSigned = 0;
		
		this.sizeOfSecureBidMessageDataSignatureSerialized = 0;
		this.sizeOfSecureBidMessageDataConfidentialSerializedCipheredAndHashed = 0;
		
		this.sizeOfSecureBidMessageDataSerialized = 0;
		this.sizeOfSecureBidMessageDoSMitigationSerialized = 0;
		
		this.sizeOfSecureBidMessageKeyExchangeSerializedCiphered = 0;
		this.sizeOfSecureBidMessageKeyExchangeSerializedCipheredSigned = 0;
		
		this.sizeOfInitialisationVector = 0;
		
		this.sizeOfUserPeerIDSerialized = 0;
		
	}

	
	public int getSizeOfUserPeerIDSerialized() {
		return this.sizeOfUserPeerIDSerialized;
	}
	
	public int getSizeOfInitialisationVector() {
		return this.sizeOfInitialisationVector;
	}
	
	public int getSizeOfSecureBidMessageKeyExchangeSerializedCiphered() {
		return this.sizeOfSecureBidMessageKeyExchangeSerializedCiphered;
	}
	
	public void setSizeOfSecureBidMessageKeyExchangeSerializedCiphered
	      (int sizeOfSecureBidMessageKeyExchangeSerializedCiphered) {
	
		this.sizeOfSecureBidMessageKeyExchangeSerializedCiphered = 
				sizeOfSecureBidMessageKeyExchangeSerializedCiphered;
	
	}
	
	public int getSizeOfSecureBidMessageKeyExchangeSerializedCipheredSigned() {
		return this.sizeOfSecureBidMessageKeyExchangeSerializedCipheredSigned;
	}
	
	public void setSizeOfSecureBidMessageKeyExchangeSerializedCipheredSigned
	      (int sizeOfSecureBidMessageKeyExchangeSerializedCipheredSigned) {
	
		this.sizeOfSecureBidMessageKeyExchangeSerializedCipheredSigned = 
				sizeOfSecureBidMessageKeyExchangeSerializedCipheredSigned;
	
	}
	
	public int getSizeOfSecureBidMessageDataSerialized() {
		return this.sizeOfSecureBidMessageDataSerialized;
	}
	
	public void setSizeOfSecureBidMessageDataSerialized(int sizeOfSecureBidMessageDataSerialized) {
		this.sizeOfSecureBidMessageDataSerialized = sizeOfSecureBidMessageDataSerialized;
	}
	
	
	public int getSizeOfSecureBidMessageDoSMitigationSerialized() {
		return this.sizeOfSecureBidMessageDoSMitigationSerialized;
	}
	
	public void setSizeOfSecureBidMessageDoSMitigationSerialized(int sizeOfSecureBidMessageDoSMitigationSerialized) {
		this.sizeOfSecureBidMessageDoSMitigationSerialized = sizeOfSecureBidMessageDoSMitigationSerialized;
	}

	
	public int getSizeOfSecureBidMessageDataSignatureSerialized() {
		return this.sizeOfSecureBidMessageDataSignatureSerialized;
	}
	
	public void setSizeOfSecureBidMessageDataSignatureSerialized(int sizeOfSecureBidMessageDataSignatureSerialized) {
		this.sizeOfSecureBidMessageDataSignatureSerialized = sizeOfSecureBidMessageDataSignatureSerialized;
	}
	
	public int getSizeOfSecureBidMessageDataConfidentialSerializedCipheredAndHashed() {
		return this.sizeOfSecureBidMessageDataConfidentialSerializedCipheredAndHashed;
	}
	
	public void setSizeOfSecureBidMessageDataConfidentialSerializedCipheredAndHashed
		  (int sizeOfSecureBidMessageDataConfidentialSerializedCipheredAndHashed) {
		
		this.sizeOfSecureBidMessageDataConfidentialSerializedCipheredAndHashed = 
				sizeOfSecureBidMessageDataConfidentialSerializedCipheredAndHashed;
	
	}
	  
	public int getSizeOfBidSerialized() {
		return this.sizeOfBidSerialized;
	}
	
	public void setSizeOfBidSerialized(int sizeOfBidSerialized) {
		this.sizeOfBidSerialized = sizeOfBidSerialized;
	}
	
	public int getSizeOfBidSerializedDigitalSigned() {
		return this.sizeOfBidSerializedDigitalSigned;
	}
	
	public void setSizeOfBidSerializedDigitalSigned(int sizeOfBidSerializedDigitalSigned) {
		this.sizeOfBidSerializedDigitalSigned = sizeOfBidSerializedDigitalSigned;
	}
	  
	public int getSizeOfBidderUserClientIDSerialized() {
		return this.sizeOfBidderUserClientIDSerialized;
	}
	
	public void getSizeOfBidderUserClientIDSerialized(int sizeOfBidderUserClientIDSerialized) {
		this.sizeOfBidderUserClientIDSerialized = sizeOfBidderUserClientIDSerialized;
	}
	
	public int getSizeOfSecureBidMessageDataConfidentialSerializedCiphered() {
		return this.sizeOfSecureBidMessageDataConfidentialSerializedCiphered;
	}
	
	public void setSizeOfSecureBidMessageDataConfidentialSerializedCiphered
	      (int sizeOfSecureBidMessageDataConfidentialSerializedCiphered) {
		
		this.sizeOfSecureBidMessageDataConfidentialSerializedCiphered = 
					sizeOfSecureBidMessageDataConfidentialSerializedCiphered;
	
	}
	
	public int getSizeOfSecureBidMessageDataConfidentialSerializedCipheredHashed() {
		return this.sizeOfSecureBidMessageDataConfidentialSerializedCipheredHashed;
	}
	
	public void setSizeOfSecureBidMessageDataConfidentialSerializedCipheredHashed
	      (int sizeOfSecureBidMessageDataConfidentialSerializedCipheredHashed) {
		
		this.sizeOfSecureBidMessageDataConfidentialSerializedCipheredHashed = 
				sizeOfSecureBidMessageDataConfidentialSerializedCipheredHashed;
	
	}
	
	public int getSizeOfSecureBidMessageDataConfidentialSerialized() {
		return this.sizeOfSecureBidMessageDataConfidentialSerialized;
	}
	
	public void setSizeOfSecureBidMessageDataConfidentialSerialized
	      (int sizeOfSecureBidMessageDataConfidentialSerialized) {
		
		this.sizeOfSecureBidMessageDataConfidentialSerialized = 
				sizeOfSecureBidMessageDataConfidentialSerialized;
	
	}
		  
	public int getSizeOfUserEmailSerialized() {
		return this.sizeOfUserEmailSerialized;
	}
	
	public void setSizeOfUserEmailSerialized(int sizeOfUserEmailSerialized) {
		this.sizeOfUserEmailSerialized = sizeOfUserEmailSerialized;
	}
	
	public int getSizeOfUserHomeAddressSerialized() {
		return this.sizeOfUserHomeAddressSerialized;
	}
	
	public void setSizeOfUserHomeAddressSerialized(int sizeOfUserHomeAddressSerialized) {
		this.sizeOfUserHomeAddressSerialized = sizeOfUserHomeAddressSerialized;
	}
	
	public int getSizeOfUserBankAccountNIBSerialized() {
		return this.sizeOfUserBankAccountNIBSerialized;
	}
	
	public void setSizeOfUserBankAccountNIBSerialized(int sizeOfUserBankAccountNIBSerialized) {
		this.sizeOfUserBankAccountNIBSerialized = sizeOfUserBankAccountNIBSerialized;
	}
	
	
	public byte[] getSecureBidMessageMetaHeaderSerialized() {
		return this.secureBidMessageMetaHeaderSerialized;
	}
	
	public boolean getIsSecureBidMessageMetaHeaderSerialized() {
		return this.isSecureBidMessageMetaHeaderSerialized;
	}
	
	public void setIsSecureBidMessageMetaHeaderSerialized(boolean isSecureBidMessageMetaHeaderSerialized) {
		this.isSecureBidMessageMetaHeaderSerialized = isSecureBidMessageMetaHeaderSerialized;
	}
	
	
	public void doSecureBidMessageMetaHeaderSerialization() {
		
		if(!this.getIsSecureBidMessageMetaHeaderSerialized()) {
			
			int sizeOfSecureBidMessageMetaHeaderSerialized = ( ( 2 * CommonUtils.META_HEADER_OUTSIDE_SEPARATORS_LENGTH) +
															   ( 16 * CommonUtils.META_HEADER_INSIDE_SEPARATORS_LENGTH) +
															   ( 17 * CommonUtils.INTEGER_IN_BYTES_LENGTH ) );

			this.secureBidMessageMetaHeaderSerialized = new byte[ sizeOfSecureBidMessageMetaHeaderSerialized ];

			byte[] outsideSeparator = new byte[] { ( (byte) 0x00 ), ( (byte) 0x00 ) };
			byte[] insideSeparator = new byte[] { ( (byte) 0x00 ) };
			
			byte[] sizeOfUserPeerIDSerializedInBytes = 
					CommonUtils.fromIntToByteArray(this.sizeOfUserPeerIDSerialized);
			
			byte[] sizeOfInitialisationVectorInBytes = 
					CommonUtils.fromIntToByteArray(this.sizeOfInitialisationVector);
			
			byte[] sizeOfSecureBidMessageKeyExchangeSerializedCipheredInBytes = 
					CommonUtils.fromIntToByteArray(this.sizeOfSecureBidMessageKeyExchangeSerializedCiphered);
			byte[] sizeOfSecureBidMessageKeyExchangeSerializedCipheredSignedInBytes = 
					CommonUtils.fromIntToByteArray(this.sizeOfSecureBidMessageKeyExchangeSerializedCipheredSigned);
			
			byte[] sizeOfSecureBidMessageDataSerializedInBytes = 
					CommonUtils.fromIntToByteArray(this.sizeOfSecureBidMessageDataSerialized);
			byte[] sizeOfSecureBidMessageDoSMitigationSerializedInBytes = 
					CommonUtils.fromIntToByteArray(this.sizeOfSecureBidMessageDoSMitigationSerialized);

			
			byte[] sizeOfSecureBidMessageDataSignatureSerializedInBytes = 
					CommonUtils.fromIntToByteArray(this.sizeOfSecureBidMessageDataSignatureSerialized);
			byte[] sizeOfSecureBidMessageDataConfidentialSerializedCipheredAndHashedInBytes = 
					CommonUtils.fromIntToByteArray(this.sizeOfSecureBidMessageDataConfidentialSerializedCipheredAndHashed);

			
			byte[] sizeOfBidSerializedInBytes = 
					CommonUtils.fromIntToByteArray(this.sizeOfBidSerialized);
			byte[] sizeOfBidSerializedDigitalSignedInBytes = 
					CommonUtils.fromIntToByteArray(this.sizeOfBidSerializedDigitalSigned);

			
			byte[] sizeOfBidderUserClientIDSerializedInBytes = 
				    CommonUtils.fromIntToByteArray(this.sizeOfBidderUserClientIDSerialized);
			
			
			byte[] sizeOfSecureBidMessageDataConfidentialSerializedCipheredInBytes = CommonUtils.fromIntToByteArray(this.sizeOfSecureBidMessageDataConfidentialSerializedCiphered);
			byte[] sizeOfSecureBidMessageDataConfidentialSerializedCipheredHashedInBytes = CommonUtils.fromIntToByteArray(this.sizeOfSecureBidMessageDataConfidentialSerializedCipheredHashed);
			byte[] sizeOfSecureBidMessageDataConfidentialSerializedInBytes = CommonUtils.fromIntToByteArray(this.sizeOfSecureBidMessageDataConfidentialSerialized);

			
			byte[] sizeOfUserEmailSerializedInBytes = 
					CommonUtils.fromIntToByteArray(this.sizeOfUserEmailSerialized);
			byte[] sizeOfUserHomeAddressSerializedInBytes = 
					CommonUtils.fromIntToByteArray(this.sizeOfUserHomeAddressSerialized);
			byte[] sizeOfUserBankAccountNIBSerializedInBytes = 
					CommonUtils.fromIntToByteArray(this.sizeOfUserBankAccountNIBSerialized);

			
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
			System.arraycopy(outsideSeparator, 0, this.secureBidMessageMetaHeaderSerialized, serializationOffset, outsideSeparator.length);
			serializationOffset += outsideSeparator.length;
			
			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(sizeOfUserPeerIDSerializedInBytes, 0, this.secureBidMessageMetaHeaderSerialized,
					serializationOffset, sizeOfUserPeerIDSerializedInBytes.length);
			serializationOffset += sizeOfUserPeerIDSerializedInBytes.length;

			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(insideSeparator, 0, this.secureBidMessageMetaHeaderSerialized, serializationOffset, insideSeparator.length);
			serializationOffset += insideSeparator.length;
			
			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(sizeOfInitialisationVectorInBytes, 0, this.secureBidMessageMetaHeaderSerialized,
					serializationOffset, sizeOfInitialisationVectorInBytes.length);
			serializationOffset += sizeOfUserPeerIDSerializedInBytes.length;

			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(insideSeparator, 0, this.secureBidMessageMetaHeaderSerialized, serializationOffset, insideSeparator.length);
			serializationOffset += insideSeparator.length;
			
			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(sizeOfSecureBidMessageKeyExchangeSerializedCipheredInBytes, 0, this.secureBidMessageMetaHeaderSerialized,
					serializationOffset, sizeOfSecureBidMessageKeyExchangeSerializedCipheredInBytes.length);
			serializationOffset += sizeOfSecureBidMessageKeyExchangeSerializedCipheredInBytes.length;

			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(insideSeparator, 0, this.secureBidMessageMetaHeaderSerialized, serializationOffset, insideSeparator.length);
			serializationOffset += insideSeparator.length;
			
			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(sizeOfSecureBidMessageKeyExchangeSerializedCipheredSignedInBytes, 0, this.secureBidMessageMetaHeaderSerialized,
					serializationOffset, sizeOfSecureBidMessageKeyExchangeSerializedCipheredSignedInBytes.length);
			serializationOffset += sizeOfSecureBidMessageKeyExchangeSerializedCipheredSignedInBytes.length;
			
			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(insideSeparator, 0, this.secureBidMessageMetaHeaderSerialized, serializationOffset, insideSeparator.length);
			serializationOffset += insideSeparator.length;
			
			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(sizeOfSecureBidMessageDataSerializedInBytes, 0, this.secureBidMessageMetaHeaderSerialized,
					serializationOffset, sizeOfSecureBidMessageDataSerializedInBytes.length);
			serializationOffset += sizeOfSecureBidMessageDataSerializedInBytes.length;

			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(insideSeparator, 0, this.secureBidMessageMetaHeaderSerialized, serializationOffset, insideSeparator.length);
			serializationOffset += insideSeparator.length;

			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(sizeOfSecureBidMessageDoSMitigationSerializedInBytes, 0, this.secureBidMessageMetaHeaderSerialized,
					serializationOffset, sizeOfSecureBidMessageDoSMitigationSerializedInBytes.length);
			serializationOffset += sizeOfSecureBidMessageDoSMitigationSerializedInBytes.length;

			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(insideSeparator, 0, this.secureBidMessageMetaHeaderSerialized, serializationOffset, insideSeparator.length);
			serializationOffset += insideSeparator.length;
			
			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(sizeOfSecureBidMessageDataSignatureSerializedInBytes, 0, this.secureBidMessageMetaHeaderSerialized,
					serializationOffset, sizeOfSecureBidMessageDataSignatureSerializedInBytes.length);
			serializationOffset += sizeOfSecureBidMessageDataSignatureSerializedInBytes.length;

			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(insideSeparator, 0, this.secureBidMessageMetaHeaderSerialized, serializationOffset, insideSeparator.length);
			serializationOffset += insideSeparator.length;
			
			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(sizeOfSecureBidMessageDataConfidentialSerializedCipheredAndHashedInBytes, 0, this.secureBidMessageMetaHeaderSerialized,
					serializationOffset, sizeOfSecureBidMessageDataConfidentialSerializedCipheredAndHashedInBytes.length);
			serializationOffset += sizeOfSecureBidMessageDataConfidentialSerializedCipheredAndHashedInBytes.length;

			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(insideSeparator, 0, this.secureBidMessageMetaHeaderSerialized, serializationOffset, insideSeparator.length);
			serializationOffset += insideSeparator.length;
			
			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(sizeOfBidSerializedInBytes, 0, this.secureBidMessageMetaHeaderSerialized,
					serializationOffset, sizeOfBidSerializedInBytes.length);
			serializationOffset += sizeOfBidSerializedInBytes.length;

			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(insideSeparator, 0, this.secureBidMessageMetaHeaderSerialized, serializationOffset, insideSeparator.length);
			serializationOffset += insideSeparator.length;
			
			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(sizeOfBidSerializedDigitalSignedInBytes, 0, this.secureBidMessageMetaHeaderSerialized,
					serializationOffset, sizeOfBidSerializedDigitalSignedInBytes.length);
			serializationOffset += sizeOfBidSerializedDigitalSignedInBytes.length;

			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(insideSeparator, 0, this.secureBidMessageMetaHeaderSerialized, serializationOffset, insideSeparator.length);
			serializationOffset += insideSeparator.length;
			
			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(sizeOfBidderUserClientIDSerializedInBytes, 0, this.secureBidMessageMetaHeaderSerialized,
					serializationOffset, sizeOfBidderUserClientIDSerializedInBytes.length);
			serializationOffset += sizeOfBidderUserClientIDSerializedInBytes.length;

			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(insideSeparator, 0, this.secureBidMessageMetaHeaderSerialized, serializationOffset, insideSeparator.length);
			serializationOffset += insideSeparator.length;
			
			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(sizeOfSecureBidMessageDataConfidentialSerializedCipheredInBytes, 0, this.secureBidMessageMetaHeaderSerialized,
					serializationOffset, sizeOfSecureBidMessageDataConfidentialSerializedCipheredInBytes.length);
			serializationOffset += sizeOfSecureBidMessageDataConfidentialSerializedCipheredInBytes.length;

			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(insideSeparator, 0, this.secureBidMessageMetaHeaderSerialized, serializationOffset, insideSeparator.length);
			serializationOffset += insideSeparator.length;
			
			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(sizeOfSecureBidMessageDataConfidentialSerializedCipheredHashedInBytes, 0, this.secureBidMessageMetaHeaderSerialized,
					serializationOffset, sizeOfSecureBidMessageDataConfidentialSerializedCipheredHashedInBytes.length);
			serializationOffset += sizeOfSecureBidMessageDataConfidentialSerializedCipheredHashedInBytes.length;

			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(insideSeparator, 0, this.secureBidMessageMetaHeaderSerialized, serializationOffset, insideSeparator.length);
			serializationOffset += insideSeparator.length;
			
			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(sizeOfSecureBidMessageDataConfidentialSerializedInBytes, 0, this.secureBidMessageMetaHeaderSerialized,
					serializationOffset, sizeOfSecureBidMessageDataConfidentialSerializedInBytes.length);
			serializationOffset += sizeOfSecureBidMessageDataConfidentialSerializedInBytes.length;

			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(insideSeparator, 0, this.secureBidMessageMetaHeaderSerialized, serializationOffset, insideSeparator.length);
			serializationOffset += insideSeparator.length;
			
			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(sizeOfUserEmailSerializedInBytes, 0, this.secureBidMessageMetaHeaderSerialized,
					serializationOffset, sizeOfUserEmailSerializedInBytes.length);
			serializationOffset += sizeOfUserEmailSerializedInBytes.length;

			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(insideSeparator, 0, this.secureBidMessageMetaHeaderSerialized, serializationOffset, insideSeparator.length);
			serializationOffset += insideSeparator.length;
			
			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(sizeOfUserHomeAddressSerializedInBytes, 0, this.secureBidMessageMetaHeaderSerialized,
					serializationOffset, sizeOfUserHomeAddressSerializedInBytes.length);
			serializationOffset += sizeOfUserHomeAddressSerializedInBytes.length;

			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(insideSeparator, 0, this.secureBidMessageMetaHeaderSerialized, serializationOffset, insideSeparator.length);
			serializationOffset += insideSeparator.length;
			
			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(sizeOfUserBankAccountNIBSerializedInBytes, 0, this.secureBidMessageMetaHeaderSerialized,
					serializationOffset, sizeOfUserBankAccountNIBSerializedInBytes.length);
			serializationOffset += sizeOfUserBankAccountNIBSerializedInBytes.length;

			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(outsideSeparator, 0, this.secureBidMessageMetaHeaderSerialized, serializationOffset, outsideSeparator.length);


			this.setIsSecureBidMessageMetaHeaderSerialized(true);
			
		}
		
	}
	
	
	public void undoSecureBidMessageMetaHeaderSerialization() {
		
		if(this.getIsSecureBidMessageMetaHeaderSerialized()) {
		
			byte[] sizeOfUserPeerIDSerializedInBytes = new byte[CommonUtils.INTEGER_IN_BYTES_LENGTH];
			
			byte[] sizeOfInitialisationVectorInBytes = new byte[CommonUtils.INTEGER_IN_BYTES_LENGTH];
			
			byte[] sizeOfSecureBidMessageKeyExchangeSerializedCipheredInBytes = new byte[CommonUtils.INTEGER_IN_BYTES_LENGTH];
			byte[] sizeOfSecureBidMessageKeyExchangeSerializedCipheredSignedInBytes = new byte[CommonUtils.INTEGER_IN_BYTES_LENGTH];
			
			byte[] sizeOfSecureBidMessageDataSerializedInBytes = new byte[CommonUtils.INTEGER_IN_BYTES_LENGTH];
			byte[] sizeOfSecureBidMessageDoSMitigationSerializedInBytes = new byte[CommonUtils.INTEGER_IN_BYTES_LENGTH];

			
			byte[] sizeOfSecureBidMessageDataSignatureSerializedInBytes = new byte[CommonUtils.INTEGER_IN_BYTES_LENGTH];
			byte[] sizeOfSecureBidMessageDataConfidentialSerializedCipheredAndHashedInBytes = new byte[CommonUtils.INTEGER_IN_BYTES_LENGTH];

			
			byte[] sizeOfBidSerializedInBytes = new byte[CommonUtils.INTEGER_IN_BYTES_LENGTH];
			byte[] sizeOfBidSerializedDigitalSignedInBytes = new byte[CommonUtils.INTEGER_IN_BYTES_LENGTH];

			
			byte[] sizeOfBidderUserClientIDSerializedInBytes = new byte[CommonUtils.INTEGER_IN_BYTES_LENGTH];
			
			
			byte[] sizeOfSecureBidMessageDataConfidentialSerializedCipheredInBytes = new byte[CommonUtils.INTEGER_IN_BYTES_LENGTH];
			byte[] sizeOfSecureBidMessageDataConfidentialSerializedCipheredHashedInBytes = new byte[CommonUtils.INTEGER_IN_BYTES_LENGTH];
			byte[] sizeOfSecureBidMessageDataConfidentialSerializedInBytes = new byte[CommonUtils.INTEGER_IN_BYTES_LENGTH];
			
			
			byte[] sizeOfUserEmailSerializedInBytes = new byte[CommonUtils.INTEGER_IN_BYTES_LENGTH];
			byte[] sizeOfUserHomeAddressSerializedInBytes = new byte[CommonUtils.INTEGER_IN_BYTES_LENGTH];
			byte[] sizeOfUserBankAccountNIBSerializedInBytes = new byte[CommonUtils.INTEGER_IN_BYTES_LENGTH];

						
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
			System.arraycopy(this.secureBidMessageMetaHeaderSerialized, serializationOffset,
					sizeOfUserPeerIDSerializedInBytes, 0, sizeOfUserPeerIDSerializedInBytes.length);
			serializationOffset += sizeOfUserPeerIDSerializedInBytes.length;

			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			serializationOffset += insideSeparator.length;
			System.arraycopy(this.secureBidMessageMetaHeaderSerialized, serializationOffset,
					sizeOfInitialisationVectorInBytes, 0, sizeOfInitialisationVectorInBytes.length);
			serializationOffset += sizeOfInitialisationVectorInBytes.length;
			
			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			serializationOffset += insideSeparator.length;
			System.arraycopy(this.secureBidMessageMetaHeaderSerialized, serializationOffset,
					sizeOfSecureBidMessageKeyExchangeSerializedCipheredInBytes,
					0, sizeOfSecureBidMessageKeyExchangeSerializedCipheredInBytes.length);
			serializationOffset += sizeOfSecureBidMessageKeyExchangeSerializedCipheredInBytes.length;

			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			serializationOffset += insideSeparator.length;
			System.arraycopy(this.secureBidMessageMetaHeaderSerialized, serializationOffset,
					sizeOfSecureBidMessageKeyExchangeSerializedCipheredSignedInBytes,
					0, sizeOfSecureBidMessageKeyExchangeSerializedCipheredSignedInBytes.length);
			serializationOffset += sizeOfSecureBidMessageKeyExchangeSerializedCipheredSignedInBytes.length;
			
			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			serializationOffset += insideSeparator.length;
			System.arraycopy(this.secureBidMessageMetaHeaderSerialized, serializationOffset,
					sizeOfSecureBidMessageDataSerializedInBytes,
					0, sizeOfSecureBidMessageDataSerializedInBytes.length);
			serializationOffset += sizeOfSecureBidMessageDataSerializedInBytes.length;

			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			serializationOffset += insideSeparator.length;
			System.arraycopy(this.secureBidMessageMetaHeaderSerialized, serializationOffset,
					sizeOfSecureBidMessageDoSMitigationSerializedInBytes,
					0, sizeOfSecureBidMessageDoSMitigationSerializedInBytes.length);
			serializationOffset += sizeOfSecureBidMessageDoSMitigationSerializedInBytes.length;

			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			serializationOffset += insideSeparator.length;
			System.arraycopy(this.secureBidMessageMetaHeaderSerialized, serializationOffset,
					sizeOfSecureBidMessageDataSignatureSerializedInBytes,
					0, sizeOfSecureBidMessageDataSignatureSerializedInBytes.length);
			serializationOffset += sizeOfSecureBidMessageDataSignatureSerializedInBytes.length;

			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			serializationOffset += insideSeparator.length;
			System.arraycopy(this.secureBidMessageMetaHeaderSerialized, serializationOffset,
					sizeOfSecureBidMessageDataConfidentialSerializedCipheredAndHashedInBytes,
					0, sizeOfSecureBidMessageDataConfidentialSerializedCipheredAndHashedInBytes.length);
			serializationOffset += sizeOfSecureBidMessageDataConfidentialSerializedCipheredAndHashedInBytes.length;

			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			serializationOffset += insideSeparator.length;
			System.arraycopy(this.secureBidMessageMetaHeaderSerialized, serializationOffset,
					sizeOfBidSerializedInBytes,
					0, sizeOfBidSerializedInBytes.length);
			serializationOffset += sizeOfBidSerializedInBytes.length;

			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			serializationOffset += insideSeparator.length;
			System.arraycopy(this.secureBidMessageMetaHeaderSerialized, serializationOffset,
					sizeOfBidSerializedDigitalSignedInBytes,
					0, sizeOfBidSerializedDigitalSignedInBytes.length);
			serializationOffset += sizeOfBidSerializedDigitalSignedInBytes.length;

			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			serializationOffset += insideSeparator.length;
			System.arraycopy(this.secureBidMessageMetaHeaderSerialized, serializationOffset,
					sizeOfBidderUserClientIDSerializedInBytes,
					0, sizeOfBidderUserClientIDSerializedInBytes.length);
			serializationOffset += sizeOfBidderUserClientIDSerializedInBytes.length;

			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			serializationOffset += insideSeparator.length;
			System.arraycopy(this.secureBidMessageMetaHeaderSerialized, serializationOffset,
					sizeOfSecureBidMessageDataConfidentialSerializedCipheredInBytes,
					0, sizeOfSecureBidMessageDataConfidentialSerializedCipheredInBytes.length);
			serializationOffset += sizeOfSecureBidMessageDataConfidentialSerializedCipheredInBytes.length;

			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			serializationOffset += insideSeparator.length;
			System.arraycopy(this.secureBidMessageMetaHeaderSerialized, serializationOffset,
					sizeOfSecureBidMessageDataConfidentialSerializedCipheredHashedInBytes,
					0, sizeOfSecureBidMessageDataConfidentialSerializedCipheredHashedInBytes.length);
			serializationOffset += sizeOfSecureBidMessageDataConfidentialSerializedCipheredHashedInBytes.length;

			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			serializationOffset += insideSeparator.length;
			System.arraycopy(this.secureBidMessageMetaHeaderSerialized, serializationOffset,
					sizeOfSecureBidMessageDataConfidentialSerializedInBytes,
					0, sizeOfSecureBidMessageDataConfidentialSerializedInBytes.length);
			serializationOffset += sizeOfSecureBidMessageDataConfidentialSerializedInBytes.length;

			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			serializationOffset += insideSeparator.length;
			System.arraycopy(this.secureBidMessageMetaHeaderSerialized, serializationOffset,
					sizeOfUserEmailSerializedInBytes,
					0, sizeOfUserEmailSerializedInBytes.length);
			serializationOffset += sizeOfUserEmailSerializedInBytes.length;

			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			serializationOffset += insideSeparator.length;
			System.arraycopy(this.secureBidMessageMetaHeaderSerialized, serializationOffset,
					sizeOfUserHomeAddressSerializedInBytes,
					0, sizeOfUserHomeAddressSerializedInBytes.length);
			serializationOffset += sizeOfUserHomeAddressSerializedInBytes.length;

			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			serializationOffset += insideSeparator.length;
			System.arraycopy(this.secureBidMessageMetaHeaderSerialized, serializationOffset,
					sizeOfUserBankAccountNIBSerializedInBytes,
					0, sizeOfUserBankAccountNIBSerializedInBytes.length);
			serializationOffset += sizeOfUserBankAccountNIBSerializedInBytes.length;			
			
			
			this.sizeOfUserPeerIDSerialized = 
					CommonUtils.fromByteArrayToInt(sizeOfUserPeerIDSerializedInBytes);
			
			this.sizeOfSecureBidMessageKeyExchangeSerializedCiphered = 
					CommonUtils.fromByteArrayToInt(sizeOfSecureBidMessageKeyExchangeSerializedCipheredInBytes);
			this.sizeOfSecureBidMessageKeyExchangeSerializedCipheredSigned = 
					CommonUtils.fromByteArrayToInt(sizeOfSecureBidMessageKeyExchangeSerializedCipheredSignedInBytes);
			
			this.sizeOfSecureBidMessageDataSerialized = 
					CommonUtils.fromByteArrayToInt(sizeOfSecureBidMessageDataSerializedInBytes);
			this.sizeOfSecureBidMessageDoSMitigationSerialized = 
					CommonUtils.fromByteArrayToInt(sizeOfSecureBidMessageDoSMitigationSerializedInBytes);
			  
			this.sizeOfSecureBidMessageDataSignatureSerialized = 
					CommonUtils.fromByteArrayToInt(sizeOfSecureBidMessageDataSignatureSerializedInBytes);
			this.sizeOfSecureBidMessageDataConfidentialSerializedCipheredAndHashed = 
					CommonUtils.fromByteArrayToInt(sizeOfSecureBidMessageDataConfidentialSerializedCipheredAndHashedInBytes);
			  
			this.sizeOfBidSerialized = CommonUtils.fromByteArrayToInt(sizeOfBidSerializedInBytes);
			this.sizeOfBidSerializedDigitalSigned = CommonUtils.fromByteArrayToInt(sizeOfBidSerializedDigitalSignedInBytes);
			  
			this.sizeOfBidderUserClientIDSerialized = CommonUtils.fromByteArrayToInt(sizeOfBidderUserClientIDSerializedInBytes);
			  
			this.sizeOfSecureBidMessageDataConfidentialSerializedCiphered = 
					CommonUtils.fromByteArrayToInt(sizeOfSecureBidMessageDataConfidentialSerializedCipheredInBytes);
			this.sizeOfSecureBidMessageDataConfidentialSerializedCipheredHashed = 
					CommonUtils.fromByteArrayToInt(sizeOfSecureBidMessageDataConfidentialSerializedCipheredHashedInBytes);
			this.sizeOfSecureBidMessageDataConfidentialSerialized = 
					CommonUtils.fromByteArrayToInt(sizeOfSecureBidMessageDataConfidentialSerializedInBytes);
			
			this.sizeOfUserEmailSerialized = 
					CommonUtils.fromByteArrayToInt(sizeOfUserEmailSerializedInBytes);
			this.sizeOfUserHomeAddressSerialized = 
					CommonUtils.fromByteArrayToInt(sizeOfUserHomeAddressSerializedInBytes);
			this.sizeOfUserBankAccountNIBSerialized = 
					CommonUtils.fromByteArrayToInt(sizeOfUserBankAccountNIBSerializedInBytes);
			
			
			this.setIsSecureBidMessageMetaHeaderSerialized(false);
			
		}
	}
	
}
