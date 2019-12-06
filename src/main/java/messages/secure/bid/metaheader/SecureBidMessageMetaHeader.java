package main.java.messages.secure.bid.metaheader;

import main.java.common.utils.CommonUtils;

public class SecureBidMessageMetaHeader {
	
	private int sizeOfSecureBidMessageDataSerialized;
	private int sizeOfSecureBidMessageDoSMitigationSerialized;
	  
	private int sizeOfSecureBidMessageDataSignatureSerialized;
	private int sizeOfSecureBidMessageDataPersonalSerializedCipheredAndHashed;
	  
	private int sizeOfBidSerialized;
	private int sizeOfBidSerializedDigitalSigned;
	  
	private int sizeOfBidderUserClientIDSerialized;
	  
	private int sizeOfSecureBidMessageDataPersonalSerializedCiphered;
	private int sizeOfSecureBidMessageDataPersonalSerializedCipheredHashed;
	private int sizeOfSecureBidMessageDataPersonalSerialized;
	  
	private int sizeOfUserEmailSerialized;
	private int sizeOfUserHomeAddressSerialized;
	private int sizeOfUserBankAccountNIBSerialized;
	  
	private int sizeOfSecureBidMessageKeyExchangeAgreementSerializedCiphered;
	private int sizeOfSecureBidMessageKeyExchangeAgreementSerializedCipheredSigned;
	
	
	private byte[] secureBidMessageMetaHeaderSerialized;
	
	private boolean isSecureBidMessageMetaHeaderSerialized;

	
	
	public SecureBidMessageMetaHeader(int sizeOfSecureBidMessageDataSerialized,
									  int sizeOfSecureBidMessageDoSMitigationSerialized,
									  
									  int sizeOfSecureBidMessageDataSignatureSerialized,
									  int sizeOfSecureBidMessageDataPersonalSerializedCipheredAndHashed,
									  
									  int sizeOfBidSerialized,
									  int sizeOfBidSerializedDigitalSigned,
									  
									  int sizeOfBidderUserClientIDSerialized,
									  
									  int sizeOfSecureBidMessageDataPersonalSerializedCiphered,
									  int sizeOfSecureBidMessageDataPersonalSerializedCipheredHashed,
									  int sizeOfSecureBidMessageDataPersonalSerialized, 
									  
									  int sizeOfUserEmailSerialized,
									  int sizeOfUserHomeAddressSerialized,
									  int sizeOfUserBankAccountNIBSerialized,
									  
									  int sizeOfSecureBidMessageKeyExchangeAgreementSerializedCiphered,
									  int sizeOfSecureBidMessageKeyExchangeAgreementSerializedCipheredSigned) {
		
		this.sizeOfSecureBidMessageDataSerialized = sizeOfSecureBidMessageDataSerialized;
		this.sizeOfSecureBidMessageDoSMitigationSerialized = sizeOfSecureBidMessageDoSMitigationSerialized;
		  
		this.sizeOfSecureBidMessageDataSignatureSerialized = sizeOfSecureBidMessageDataSignatureSerialized;
		this.sizeOfSecureBidMessageDataPersonalSerializedCipheredAndHashed = 
				sizeOfSecureBidMessageDataPersonalSerializedCipheredAndHashed;
		  
		this.sizeOfBidSerialized = sizeOfBidSerialized;
		this.sizeOfBidSerializedDigitalSigned = sizeOfBidSerializedDigitalSigned;
		  
		this.sizeOfBidderUserClientIDSerialized = sizeOfBidderUserClientIDSerialized;
		  
		this.sizeOfSecureBidMessageDataPersonalSerializedCiphered =
		     sizeOfSecureBidMessageDataPersonalSerializedCiphered;
		this.sizeOfSecureBidMessageDataPersonalSerializedCipheredHashed = 
			 sizeOfSecureBidMessageDataPersonalSerializedCipheredHashed;
		this.sizeOfSecureBidMessageDataPersonalSerialized = sizeOfSecureBidMessageDataPersonalSerialized;
		
		this.sizeOfUserEmailSerialized = 
						sizeOfUserEmailSerialized;
		this.sizeOfUserHomeAddressSerialized = 
						sizeOfUserHomeAddressSerialized;
		this.sizeOfUserBankAccountNIBSerialized =
						sizeOfUserBankAccountNIBSerialized;
		
		this.sizeOfSecureBidMessageKeyExchangeAgreementSerializedCiphered = 
				sizeOfSecureBidMessageKeyExchangeAgreementSerializedCiphered;
		this.sizeOfSecureBidMessageKeyExchangeAgreementSerializedCipheredSigned =
				sizeOfSecureBidMessageKeyExchangeAgreementSerializedCipheredSigned;
		
		this.secureBidMessageMetaHeaderSerialized = null;
		this.isSecureBidMessageMetaHeaderSerialized = false;
		
	}
	
	public SecureBidMessageMetaHeader(byte[] secureBidMessageMetaHeaderSerialized) {

		this.secureBidMessageMetaHeaderSerialized = secureBidMessageMetaHeaderSerialized;
		this.isSecureBidMessageMetaHeaderSerialized = true;
		
		this.sizeOfSecureBidMessageKeyExchangeAgreementSerializedCiphered = 0;
		this.sizeOfSecureBidMessageKeyExchangeAgreementSerializedCipheredSigned = 0;
		
		this.sizeOfUserEmailSerialized = 0;
		this.sizeOfUserHomeAddressSerialized = 0;
		this.sizeOfUserBankAccountNIBSerialized = 0;
		
		this.sizeOfSecureBidMessageDataPersonalSerializedCiphered = 0;
		this.sizeOfSecureBidMessageDataPersonalSerializedCipheredHashed = 0;
		this.sizeOfSecureBidMessageDataPersonalSerialized = 0;
	
		this.sizeOfBidderUserClientIDSerialized = 0;

		this.sizeOfBidSerialized = 0;
		this.sizeOfBidSerializedDigitalSigned = 0;
		
		this.sizeOfSecureBidMessageDataSignatureSerialized = 0;
		this.sizeOfSecureBidMessageDataPersonalSerializedCipheredAndHashed = 0;
		
		this.sizeOfSecureBidMessageDataSerialized = 0;
		this.sizeOfSecureBidMessageDoSMitigationSerialized = 0;
		
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
	
	public int getSizeOfSecureBidMessageDataPersonalSerializedCipheredAndHashed() {
		return this.sizeOfSecureBidMessageDataPersonalSerializedCipheredAndHashed;
	}
	
	public void setSizeOfSecureBidMessageDataPersonalSerializedCipheredAndHashed
		  (int sizeOfSecureBidMessageDataPersonalSerializedCipheredAndHashed) {
		
		this.sizeOfSecureBidMessageDataPersonalSerializedCipheredAndHashed = 
				sizeOfSecureBidMessageDataPersonalSerializedCipheredAndHashed;
	
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
	
	public int getSizeOfSecureBidMessageDataPersonalSerializedCiphered() {
		return this.sizeOfSecureBidMessageDataPersonalSerializedCiphered;
	}
	
	public void setSizeOfSecureBidMessageDataPersonalSerializedCiphered
	      (int sizeOfSecureBidMessageDataPersonalSerializedCiphered) {
		
		this.sizeOfSecureBidMessageDataPersonalSerializedCiphered = 
					sizeOfSecureBidMessageDataPersonalSerializedCiphered;
	
	}
	
	public int getSizeOfSecureBidMessageDataPersonalSerializedCipheredHashed() {
		return this.sizeOfSecureBidMessageDataPersonalSerializedCipheredHashed;
	}
	
	public void setSizeOfSecureBidMessageDataPersonalSerializedCipheredHashed
	      (int sizeOfSecureBidMessageDataPersonalSerializedCipheredHashed) {
		
		this.sizeOfSecureBidMessageDataPersonalSerializedCipheredHashed = 
				sizeOfSecureBidMessageDataPersonalSerializedCipheredHashed;
	
	}
	
	public int getSizeOfSecureBidMessageDataPersonalSerialized() {
		return this.sizeOfSecureBidMessageDataPersonalSerialized;
	}
	
	public void setSizeOfSecureBidMessageDataPersonalSerialized
	      (int sizeOfSecureBidMessageDataPersonalSerialized) {
		
		this.sizeOfSecureBidMessageDataPersonalSerialized = 
				sizeOfSecureBidMessageDataPersonalSerialized;
	
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
	  
	public int getSizeOfSecureBidMessageKeyExchangeAgreementSerializedCiphered() {
		return this.sizeOfSecureBidMessageKeyExchangeAgreementSerializedCiphered;
	}
	
	public void setSizeOfSecureBidMessageKeyExchangeAgreementSerializedCiphered
	      (int sizeOfSecureBidMessageKeyExchangeAgreementSerializedCiphered) {
	
		this.sizeOfSecureBidMessageKeyExchangeAgreementSerializedCiphered = 
				sizeOfSecureBidMessageKeyExchangeAgreementSerializedCiphered;
	
	}
	
	public int getSizeOfSecureBidMessageKeyExchangeAgreementSerializedCipheredSigned() {
		return this.sizeOfSecureBidMessageKeyExchangeAgreementSerializedCipheredSigned;
	}
	
	public void setSizeOfSecureBidMessageKeyExchangeAgreementSerializedCipheredSigned
	      (int sizeOfSecureBidMessageKeyExchangeAgreementSerializedCipheredSigned) {
	
		this.sizeOfSecureBidMessageKeyExchangeAgreementSerializedCipheredSigned = 
				sizeOfSecureBidMessageKeyExchangeAgreementSerializedCipheredSigned;
	
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
															   ( 14 * CommonUtils.META_HEADER_INSIDE_SEPARATORS_LENGTH) +
															   ( 15 * CommonUtils.INTEGER_IN_BYTES_LENGTH ) );

			this.secureBidMessageMetaHeaderSerialized = new byte[ sizeOfSecureBidMessageMetaHeaderSerialized ];

			byte[] outsideSeparator = new byte[] { ( (byte) 0x00 ), ( (byte) 0x00 ) };
			byte[] insideSeparator = new byte[] { ( (byte) 0x00 ) };

			
			byte[] sizeOfSecureBidMessageDataSerializedInBytes = 
					CommonUtils.fromIntToByteArray(this.sizeOfSecureBidMessageDataSerialized);
			byte[] sizeOfSecureBidMessageDoSMitigationSerializedInBytes = 
					CommonUtils.fromIntToByteArray(this.sizeOfSecureBidMessageDoSMitigationSerialized);

			
			byte[] sizeOfSecureBidMessageDataSignatureSerializedInBytes = 
					CommonUtils.fromIntToByteArray(this.sizeOfSecureBidMessageDataSignatureSerialized);
			byte[] sizeOfSecureBidMessageDataPersonalSerializedCipheredAndHashedInBytes = 
					CommonUtils.fromIntToByteArray(this.sizeOfSecureBidMessageDataPersonalSerializedCipheredAndHashed);

			
			byte[] sizeOfBidSerializedInBytes = 
					CommonUtils.fromIntToByteArray(this.sizeOfBidSerialized);
			byte[] sizeOfBidSerializedDigitalSignedInBytes = 
					CommonUtils.fromIntToByteArray(this.sizeOfBidSerializedDigitalSigned);

			
			byte[] sizeOfBidderUserClientIDSerializedInBytes = 
				    CommonUtils.fromIntToByteArray(this.sizeOfBidderUserClientIDSerialized);
			
			
			byte[] sizeOfSecureBidMessageDataPersonalSerializedCipheredInBytes = CommonUtils.fromIntToByteArray(this.sizeOfSecureBidMessageDataPersonalSerializedCiphered);
			byte[] sizeOfSecureBidMessageDataPersonalSerializedCipheredHashedInBytes = CommonUtils.fromIntToByteArray(this.sizeOfSecureBidMessageDataPersonalSerializedCipheredHashed);
			byte[] sizeOfSecureBidMessageDataPersonalSerializedInBytes = CommonUtils.fromIntToByteArray(this.sizeOfSecureBidMessageDataPersonalSerialized);

			
			byte[] sizeOfUserEmailSerializedInBytes = 
					CommonUtils.fromIntToByteArray(this.sizeOfUserEmailSerialized);
			byte[] sizeOfUserHomeAddressSerializedInBytes = 
					CommonUtils.fromIntToByteArray(this.sizeOfUserHomeAddressSerialized);
			byte[] sizeOfUserBankAccountNIBSerializedInBytes = 
					CommonUtils.fromIntToByteArray(this.sizeOfUserBankAccountNIBSerialized);

			
			byte[] sizeOfSecureBidMessageKeyExchangeAgreementSerializedCipheredInBytes = 
					CommonUtils.fromIntToByteArray(this.sizeOfSecureBidMessageKeyExchangeAgreementSerializedCiphered);
			byte[] sizeOfSecureBidMessageKeyExchangeAgreementSerializedCipheredSignedInBytes = 
					CommonUtils.fromIntToByteArray(this.sizeOfSecureBidMessageKeyExchangeAgreementSerializedCipheredSigned);

			
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
			System.arraycopy(sizeOfSecureBidMessageDataPersonalSerializedCipheredAndHashedInBytes, 0, this.secureBidMessageMetaHeaderSerialized,
					serializationOffset, sizeOfSecureBidMessageDataPersonalSerializedCipheredAndHashedInBytes.length);
			serializationOffset += sizeOfSecureBidMessageDataPersonalSerializedCipheredAndHashedInBytes.length;

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
			System.arraycopy(sizeOfSecureBidMessageDataPersonalSerializedCipheredInBytes, 0, this.secureBidMessageMetaHeaderSerialized,
					serializationOffset, sizeOfSecureBidMessageDataPersonalSerializedCipheredInBytes.length);
			serializationOffset += sizeOfSecureBidMessageDataPersonalSerializedCipheredInBytes.length;

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
			System.arraycopy(sizeOfSecureBidMessageDataPersonalSerializedCipheredHashedInBytes, 0, this.secureBidMessageMetaHeaderSerialized,
					serializationOffset, sizeOfSecureBidMessageDataPersonalSerializedCipheredHashedInBytes.length);
			serializationOffset += sizeOfSecureBidMessageDataPersonalSerializedCipheredHashedInBytes.length;

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
			System.arraycopy(sizeOfSecureBidMessageDataPersonalSerializedInBytes, 0, this.secureBidMessageMetaHeaderSerialized,
					serializationOffset, sizeOfSecureBidMessageDataPersonalSerializedInBytes.length);
			serializationOffset += sizeOfSecureBidMessageDataPersonalSerializedInBytes.length;

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
			System.arraycopy(insideSeparator, 0, this.secureBidMessageMetaHeaderSerialized, serializationOffset, insideSeparator.length);
			serializationOffset += insideSeparator.length;
			
			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(sizeOfSecureBidMessageKeyExchangeAgreementSerializedCipheredInBytes, 0, this.secureBidMessageMetaHeaderSerialized,
					serializationOffset, sizeOfSecureBidMessageKeyExchangeAgreementSerializedCipheredInBytes.length);
			serializationOffset += sizeOfSecureBidMessageKeyExchangeAgreementSerializedCipheredInBytes.length;

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
			System.arraycopy(sizeOfSecureBidMessageKeyExchangeAgreementSerializedCipheredSignedInBytes, 0, this.secureBidMessageMetaHeaderSerialized,
					serializationOffset, sizeOfSecureBidMessageKeyExchangeAgreementSerializedCipheredSignedInBytes.length);
			serializationOffset += sizeOfSecureBidMessageKeyExchangeAgreementSerializedCipheredSignedInBytes.length;

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
		
			byte[] sizeOfSecureBidMessageDataSerializedInBytes = new byte[CommonUtils.INTEGER_IN_BYTES_LENGTH];
			byte[] sizeOfSecureBidMessageDoSMitigationSerializedInBytes = new byte[CommonUtils.INTEGER_IN_BYTES_LENGTH];

			
			byte[] sizeOfSecureBidMessageDataSignatureSerializedInBytes = new byte[CommonUtils.INTEGER_IN_BYTES_LENGTH];
			byte[] sizeOfSecureBidMessageDataPersonalSerializedCipheredAndHashedInBytes = new byte[CommonUtils.INTEGER_IN_BYTES_LENGTH];

			
			byte[] sizeOfBidSerializedInBytes = new byte[CommonUtils.INTEGER_IN_BYTES_LENGTH];
			byte[] sizeOfBidSerializedDigitalSignedInBytes = new byte[CommonUtils.INTEGER_IN_BYTES_LENGTH];

			
			byte[] sizeOfBidderUserClientIDSerializedInBytes = new byte[CommonUtils.INTEGER_IN_BYTES_LENGTH];
			
			
			byte[] sizeOfSecureBidMessageDataPersonalSerializedCipheredInBytes = new byte[CommonUtils.INTEGER_IN_BYTES_LENGTH];
			byte[] sizeOfSecureBidMessageDataPersonalSerializedCipheredHashedInBytes = new byte[CommonUtils.INTEGER_IN_BYTES_LENGTH];
			byte[] sizeOfSecureBidMessageDataPersonalSerializedInBytes = new byte[CommonUtils.INTEGER_IN_BYTES_LENGTH];
			
			
			byte[] sizeOfUserEmailSerializedInBytes = new byte[CommonUtils.INTEGER_IN_BYTES_LENGTH];
			byte[] sizeOfUserHomeAddressSerializedInBytes = new byte[CommonUtils.INTEGER_IN_BYTES_LENGTH];
			byte[] sizeOfUserBankAccountNIBSerializedInBytes = new byte[CommonUtils.INTEGER_IN_BYTES_LENGTH];

			
			byte[] sizeOfSecureBidMessageKeyExchangeAgreementSerializedCipheredInBytes = new byte[CommonUtils.INTEGER_IN_BYTES_LENGTH];
			byte[] sizeOfSecureBidMessageKeyExchangeAgreementSerializedCipheredSignedInBytes = new byte[CommonUtils.INTEGER_IN_BYTES_LENGTH];

			
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
					sizeOfSecureBidMessageDataPersonalSerializedCipheredAndHashedInBytes,
					0, sizeOfSecureBidMessageDataPersonalSerializedCipheredAndHashedInBytes.length);
			serializationOffset += sizeOfSecureBidMessageDataPersonalSerializedCipheredAndHashedInBytes.length;

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
					sizeOfSecureBidMessageDataPersonalSerializedCipheredInBytes,
					0, sizeOfSecureBidMessageDataPersonalSerializedCipheredInBytes.length);
			serializationOffset += sizeOfSecureBidMessageDataPersonalSerializedCipheredInBytes.length;

			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			serializationOffset += insideSeparator.length;
			System.arraycopy(this.secureBidMessageMetaHeaderSerialized, serializationOffset,
					sizeOfSecureBidMessageDataPersonalSerializedCipheredHashedInBytes,
					0, sizeOfSecureBidMessageDataPersonalSerializedCipheredHashedInBytes.length);
			serializationOffset += sizeOfSecureBidMessageDataPersonalSerializedCipheredHashedInBytes.length;

			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			serializationOffset += insideSeparator.length;
			System.arraycopy(this.secureBidMessageMetaHeaderSerialized, serializationOffset,
					sizeOfSecureBidMessageDataPersonalSerializedInBytes,
					0, sizeOfSecureBidMessageDataPersonalSerializedInBytes.length);
			serializationOffset += sizeOfSecureBidMessageDataPersonalSerializedInBytes.length;

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

			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			serializationOffset += insideSeparator.length;
			System.arraycopy(this.secureBidMessageMetaHeaderSerialized, serializationOffset,
					sizeOfSecureBidMessageKeyExchangeAgreementSerializedCipheredInBytes,
					0, sizeOfSecureBidMessageKeyExchangeAgreementSerializedCipheredInBytes.length);
			serializationOffset += sizeOfSecureBidMessageKeyExchangeAgreementSerializedCipheredInBytes.length;

			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			serializationOffset += insideSeparator.length;
			System.arraycopy(this.secureBidMessageMetaHeaderSerialized, serializationOffset,
					sizeOfSecureBidMessageKeyExchangeAgreementSerializedCipheredSignedInBytes,
					0, sizeOfSecureBidMessageKeyExchangeAgreementSerializedCipheredSignedInBytes.length);
			serializationOffset += sizeOfSecureBidMessageKeyExchangeAgreementSerializedCipheredSignedInBytes.length;
			
			
			this.sizeOfSecureBidMessageDataSerialized = 
					CommonUtils.fromByteArrayToInt(sizeOfSecureBidMessageDataSerializedInBytes);
			this.sizeOfSecureBidMessageDoSMitigationSerialized = 
					CommonUtils.fromByteArrayToInt(sizeOfSecureBidMessageDoSMitigationSerializedInBytes);
			  
			this.sizeOfSecureBidMessageDataSignatureSerialized = 
					CommonUtils.fromByteArrayToInt(sizeOfSecureBidMessageDataSignatureSerializedInBytes);
			this.sizeOfSecureBidMessageDataPersonalSerializedCipheredAndHashed = 
					CommonUtils.fromByteArrayToInt(sizeOfSecureBidMessageDataPersonalSerializedCipheredAndHashedInBytes);
			  
			this.sizeOfBidSerialized = CommonUtils.fromByteArrayToInt(sizeOfBidSerializedInBytes);
			this.sizeOfBidSerializedDigitalSigned = CommonUtils.fromByteArrayToInt(sizeOfBidSerializedDigitalSignedInBytes);
			  
			this.sizeOfBidderUserClientIDSerialized = CommonUtils.fromByteArrayToInt(sizeOfBidderUserClientIDSerializedInBytes);
			  
			this.sizeOfSecureBidMessageDataPersonalSerializedCiphered = 
					CommonUtils.fromByteArrayToInt(sizeOfSecureBidMessageDataPersonalSerializedCipheredInBytes);
			this.sizeOfSecureBidMessageDataPersonalSerializedCipheredHashed = 
					CommonUtils.fromByteArrayToInt(sizeOfSecureBidMessageDataPersonalSerializedCipheredHashedInBytes);
			this.sizeOfSecureBidMessageDataPersonalSerialized = 
					CommonUtils.fromByteArrayToInt(sizeOfSecureBidMessageDataPersonalSerializedInBytes);
			
			this.sizeOfUserEmailSerialized = 
					CommonUtils.fromByteArrayToInt(sizeOfUserEmailSerializedInBytes);
			this.sizeOfUserHomeAddressSerialized = 
					CommonUtils.fromByteArrayToInt(sizeOfUserHomeAddressSerializedInBytes);
			this.sizeOfUserBankAccountNIBSerialized = 
					CommonUtils.fromByteArrayToInt(sizeOfUserBankAccountNIBSerializedInBytes);
			
			this.sizeOfSecureBidMessageKeyExchangeAgreementSerializedCiphered = 
					CommonUtils.fromByteArrayToInt(sizeOfSecureBidMessageKeyExchangeAgreementSerializedCipheredInBytes);
			this.sizeOfSecureBidMessageKeyExchangeAgreementSerializedCipheredSigned = 
					CommonUtils.fromByteArrayToInt(sizeOfSecureBidMessageKeyExchangeAgreementSerializedCipheredSignedInBytes);
			
			
			this.setIsSecureBidMessageMetaHeaderSerialized(false);
			
		}
	}
	
}
