package main.java.messages.secure.bid.metaheader;

import main.java.common.utils.CommonUtils;

public class SecureBidMessageMetaHeader {
	
	private int sizeOfSecureBidMessageDataSerialized;
	
	private int sizeOfSecureBidMessageDoSMitigationSerialized;
	
	
	private int sizeOfSecureBidMessageDataSignatureSerialized;

	private int sizeOfSecureBidMessageDataPersonalSerializedAndHashedCiphered;
	
	
		
	private int sizeOfBidDigitalSignedSerialized;
	
	private int sizeOfBidSerialized;
	
	private int sizeOfBidSerializedHashedCiphered;
	
	private int sizeOfBidderUserClientIDSerialized;
	
	
	
	private int sizeOfSecureBidMessageDataPersonalSerialized;
	
	private int sizeOfSecureBidMessageDataPersonalSerializedHashed;
	
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
									  int sizeOfSecureBidMessageDataPersonalSerializedAndHashedCiphered,
									  int sizeOfSecureBidMessageDataPersonalSerialized,
									  int sizeOfSecureBidMessageDataPersonalSerializedHashed,
									  int sizeOfBidDigitalSignedSerialized,
									  int sizeOfBidSerialized,
									  int sizeOfBidSerializedHashedCiphered,
									  int sizeOfBidderUserClientIDSerialized,
									  int sizeOfUserEmailSerialized,
									  int sizeOfUserHomeAddressSerialized,
									  int sizeOfUserBankAccountNIBSerialized,
									  int sizeOfSecureBidMessageKeyExchangeAgreementSerializedCiphered,
									  int sizeOfSecureBidMessageKeyExchangeAgreementSerializedCipheredSigned) {
		
		this.sizeOfSecureBidMessageDataSerialized = sizeOfSecureBidMessageDataSerialized;
		this.sizeOfSecureBidMessageDoSMitigationSerialized = sizeOfSecureBidMessageDoSMitigationSerialized;
		
		this.sizeOfSecureBidMessageDataSignatureSerialized = sizeOfSecureBidMessageDataSignatureSerialized;
		this.sizeOfSecureBidMessageDataPersonalSerializedAndHashedCiphered = 
											sizeOfSecureBidMessageDataPersonalSerializedAndHashedCiphered;
		
		this.sizeOfBidDigitalSignedSerialized = sizeOfBidDigitalSignedSerialized;
		this.sizeOfBidSerialized = sizeOfBidSerialized;
		this.sizeOfBidSerializedHashedCiphered = sizeOfBidSerializedHashedCiphered;
		this.sizeOfBidderUserClientIDSerialized = sizeOfBidderUserClientIDSerialized;
		
		
		this.sizeOfSecureBidMessageDataPersonalSerialized = sizeOfSecureBidMessageDataPersonalSerialized;
		this.sizeOfSecureBidMessageDataPersonalSerializedHashed = 
											sizeOfSecureBidMessageDataPersonalSerializedHashed;
		
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
		
		this.sizeOfSecureBidMessageDataSerialized = 0;
		this.sizeOfSecureBidMessageDoSMitigationSerialized = 0;
		
		this.sizeOfSecureBidMessageDataSignatureSerialized = 0;
		this.sizeOfSecureBidMessageDataPersonalSerializedAndHashedCiphered = 0;
		
		this.sizeOfBidDigitalSignedSerialized = 0;
		this.sizeOfBidSerialized = 0;
		this.sizeOfBidSerializedHashedCiphered = 0;
		this.sizeOfBidderUserClientIDSerialized = 0;
		
		this.sizeOfSecureBidMessageDataPersonalSerialized = 0;
		this.sizeOfSecureBidMessageDataPersonalSerializedHashed = 0;
		
		this.sizeOfUserEmailSerialized = 0;
		this.sizeOfUserHomeAddressSerialized = 0;
		this.sizeOfUserBankAccountNIBSerialized = 0;
		
		this.sizeOfSecureBidMessageKeyExchangeAgreementSerializedCiphered = 0;
		this.sizeOfSecureBidMessageKeyExchangeAgreementSerializedCipheredSigned = 0;
		
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

	public void setSizeOfSecureBidMessageDoSMitigationSerialized
		  (int sizeOfSecureBidMessageDoSMitigationSerialized) {
		
		this.sizeOfSecureBidMessageDoSMitigationSerialized = 
				sizeOfSecureBidMessageDoSMitigationSerialized;
	
	}

	
	
	public int getSizeOfSecureBidMessageDataSignatureSerialized() {
		return this.sizeOfSecureBidMessageDataSignatureSerialized;
	}

	public void setSizeOfSecureBidMessageDataSignatureSerialized
		  (int sizeOfSecureBidMessageDataSignatureSerialized) {
		
		this.sizeOfSecureBidMessageDataSignatureSerialized = 
				sizeOfSecureBidMessageDataSignatureSerialized;
	
	}
	

	public int getSizeOfBidDigitalSignedSerialized() {
		return this.sizeOfBidDigitalSignedSerialized;
	}

	public void setSizeOfBidDigitalSignedSerialized(int sizeOfBidDigitalSignedSerialized) {
		this.sizeOfBidDigitalSignedSerialized = sizeOfBidDigitalSignedSerialized;
	}

	public int getSizeOfBidSerialized() {
		return sizeOfBidSerialized;
	}

	public void setSizeOfBidSerialized(int sizeOfBidSerialized) {
		this.sizeOfBidSerialized = sizeOfBidSerialized;
	}

	public int getSizeOfBidSerializedHashedCiphered() {
		return sizeOfBidSerializedHashedCiphered;
	}

	public void setSizeOfBidSerializedHashedCiphered(int sizeOfBidSerializedHashedCiphered) {
		this.sizeOfBidSerializedHashedCiphered = sizeOfBidSerializedHashedCiphered;
	}

	public int getSizeOfBidderUserClientIDSerialized() {
		return this.sizeOfBidderUserClientIDSerialized;
	}

	public void setSizeOfBidderUserClientIDSerialized(int sizeOfBidderUserClientIDSerialized) {
		this.sizeOfBidderUserClientIDSerialized = sizeOfBidderUserClientIDSerialized;
	}
	
	
	
	public int getSizeOfSecureBidMessageDataPersonalSerializedAndHashedCiphered() {
		return this.sizeOfSecureBidMessageDataPersonalSerializedAndHashedCiphered;
	}

	public void setSizeOfSecureBidMessageDataPersonalSerializedAndHashedCiphered
		  (int sizeOfSecureBidMessageDataPersonalSerializedAndHashedCiphered) {
		
		this.sizeOfSecureBidMessageDataPersonalSerializedAndHashedCiphered = 
				sizeOfSecureBidMessageDataPersonalSerializedAndHashedCiphered;
	
	}

	
	public int getSizeOfSecureBidMessageDataPersonalSerialized() {
		return sizeOfSecureBidMessageDataPersonalSerialized;
	}

	public void setSizeOfSecureBidMessageDataPersonalSerialized
		  (int sizeOfSecureBidMessageDataPersonalSerialized) {
		
		this.sizeOfSecureBidMessageDataPersonalSerialized = 
				sizeOfSecureBidMessageDataPersonalSerialized;
	
	}
	
	public int getSizeOfSecureBidMessageDataPersonalSerializedHashed() {
		return this.sizeOfSecureBidMessageDataPersonalSerializedHashed;
	}

	public void setSizeOfSecureBidMessageDataPersonalSerializedHashed
		  (int sizeOfSecureBidMessageDataPersonalSerializedHashed) {
		
		this.sizeOfSecureBidMessageDataPersonalSerializedHashed = 
				sizeOfSecureBidMessageDataPersonalSerializedHashed;
	
	}
	
	public int getSizeOfUserEmailSerialized() {
		return this.sizeOfUserEmailSerialized;
	}

	public void setSizeOfUserEmailSerialized
		  (int sizeOfUserEmailSerialized) {
		
		this.sizeOfUserEmailSerialized = 
				sizeOfUserEmailSerialized;
	
	}

	public int getSizeOfUserHomeAddressSerialized() {
		return this.sizeOfUserHomeAddressSerialized;
	}

	public void setSizeOfUserHomeAddressSerialized
		  (int sizeOfUserHomeAddressSerialized) {
		
		this.sizeOfUserHomeAddressSerialized = 
				sizeOfUserHomeAddressSerialized;
		
	}

	public int getSizeOfUserBankAccountNIBSerialized() {
		return this.sizeOfUserBankAccountNIBSerialized;
	}

	public void setSizeOfUserBankAccountNIBSerialized
		  (int sizeOfUserBankAccountNIBSerialized) {
		
		this.sizeOfUserBankAccountNIBSerialized = 
				sizeOfUserBankAccountNIBSerialized;
		
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

	public void setSecureBidMessageMetaHeaderSerialized(byte[] secureBidMessageMetaHeaderSerialized) {
		this.secureBidMessageMetaHeaderSerialized = secureBidMessageMetaHeaderSerialized;
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


			byte[] sizeOfSecureBidMessageDataSerializedInBytes = CommonUtils.fromIntToByteArray(this.sizeOfSecureBidMessageDataSerialized);
			byte[] sizeOfSecureBidMessageDoSMitigationSerializedInBytes = 
					CommonUtils.fromIntToByteArray(this.sizeOfSecureBidMessageDoSMitigationSerialized);

			byte[] sizeOfSecureBidMessageDataSignatureSerializedInBytes = 
					CommonUtils.fromIntToByteArray(this.sizeOfSecureBidMessageDataSignatureSerialized);
			byte[] sizeOfSecureBidMessageDataPersonalSerializedAndHashedCipheredInBytes = 
					CommonUtils.fromIntToByteArray(this.sizeOfSecureBidMessageDataPersonalSerializedAndHashedCiphered);

			byte[] sizeOfSecureBidMessageDataPersonalSerializedInBytes = 
					CommonUtils.fromIntToByteArray(this.sizeOfSecureBidMessageDataPersonalSerialized);
			byte[] sizeOfSecureBidMessageDataPersonalSerializedHashedInBytes = 
					CommonUtils.fromIntToByteArray(this.sizeOfSecureBidMessageDataPersonalSerializedHashed);

			byte[] sizeOfBidDigitalSignedSerializedInBytes = CommonUtils.fromIntToByteArray(this.sizeOfBidDigitalSignedSerialized);
			byte[] sizeOfBidSerializedInBytes = CommonUtils.fromIntToByteArray(this.sizeOfBidSerialized);
			byte[] sizeOfBidSerializedHashedCipheredInBytes = CommonUtils.fromIntToByteArray(this.sizeOfBidSerializedHashedCiphered);
			byte[] sizeOfBidderUserClientIDSerializedInBytes = CommonUtils.fromIntToByteArray(this.sizeOfBidderUserClientIDSerialized);

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
			System.arraycopy(sizeOfSecureBidMessageDataPersonalSerializedAndHashedCipheredInBytes, 0, this.secureBidMessageMetaHeaderSerialized,
					serializationOffset, sizeOfSecureBidMessageDataPersonalSerializedAndHashedCipheredInBytes.length);
			serializationOffset += sizeOfSecureBidMessageDataPersonalSerializedAndHashedCipheredInBytes.length;

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
			System.arraycopy(sizeOfSecureBidMessageDataPersonalSerializedHashedInBytes, 0, this.secureBidMessageMetaHeaderSerialized,
					serializationOffset, sizeOfSecureBidMessageDataPersonalSerializedHashedInBytes.length);
			serializationOffset += sizeOfSecureBidMessageDataPersonalSerializedHashedInBytes.length;

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
			System.arraycopy(sizeOfBidDigitalSignedSerializedInBytes, 0, this.secureBidMessageMetaHeaderSerialized,
					serializationOffset, sizeOfBidDigitalSignedSerializedInBytes.length);
			serializationOffset += sizeOfBidDigitalSignedSerializedInBytes.length;

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
			System.arraycopy(sizeOfBidSerializedHashedCipheredInBytes, 0, this.secureBidMessageMetaHeaderSerialized,
					serializationOffset, sizeOfBidSerializedHashedCipheredInBytes.length);
			serializationOffset += sizeOfBidSerializedHashedCipheredInBytes.length;

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
			byte[] sizeOfSecureBidMessageDataPersonalSerializedAndHashedCipheredInBytes = new byte[CommonUtils.INTEGER_IN_BYTES_LENGTH];

			byte[] sizeOfSecureBidMessageDataPersonalSerializedInBytes = new byte[CommonUtils.INTEGER_IN_BYTES_LENGTH];
			byte[] sizeOfSecureBidMessageDataPersonalSerializedHashedInBytes = new byte[CommonUtils.INTEGER_IN_BYTES_LENGTH];
			
			byte[] sizeOfBidDigitalSignedSerializedInBytes = new byte[CommonUtils.INTEGER_IN_BYTES_LENGTH];
			byte[] sizeOfBidSerializedInBytes = new byte[CommonUtils.INTEGER_IN_BYTES_LENGTH];
			byte[] sizeOfBidSerializedHashedCipheredInBytes = new byte[CommonUtils.INTEGER_IN_BYTES_LENGTH];
			byte[] sizeOfBidderUserClientIDSerializedInBytes = new byte[CommonUtils.INTEGER_IN_BYTES_LENGTH];

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
					sizeOfSecureBidMessageDataPersonalSerializedAndHashedCipheredInBytes,
					0, sizeOfSecureBidMessageDataPersonalSerializedAndHashedCipheredInBytes.length);
			serializationOffset += sizeOfSecureBidMessageDataPersonalSerializedAndHashedCipheredInBytes.length;

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
					sizeOfSecureBidMessageDataPersonalSerializedHashedInBytes,
					0, sizeOfSecureBidMessageDataPersonalSerializedHashedInBytes.length);
			serializationOffset += sizeOfSecureBidMessageDataPersonalSerializedHashedInBytes.length;

			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			serializationOffset += insideSeparator.length;
			System.arraycopy(this.secureBidMessageMetaHeaderSerialized, serializationOffset, sizeOfBidDigitalSignedSerializedInBytes,
					0, sizeOfBidDigitalSignedSerializedInBytes.length);
			serializationOffset += sizeOfBidDigitalSignedSerializedInBytes.length;

			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			serializationOffset += insideSeparator.length;
			System.arraycopy(this.secureBidMessageMetaHeaderSerialized, serializationOffset, sizeOfBidSerializedInBytes,
					0, sizeOfBidSerializedInBytes.length);
			serializationOffset += sizeOfBidSerializedInBytes.length;

			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			serializationOffset += insideSeparator.length;
			System.arraycopy(this.secureBidMessageMetaHeaderSerialized, serializationOffset, sizeOfBidSerializedHashedCipheredInBytes,
					0, sizeOfBidSerializedHashedCipheredInBytes.length);
			serializationOffset += sizeOfBidSerializedHashedCipheredInBytes.length;

			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			serializationOffset += insideSeparator.length;
			System.arraycopy(this.secureBidMessageMetaHeaderSerialized, serializationOffset, sizeOfBidderUserClientIDSerializedInBytes,
					0, sizeOfBidderUserClientIDSerializedInBytes.length);
			serializationOffset += sizeOfBidderUserClientIDSerializedInBytes.length;

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
			System.arraycopy(this.secureBidMessageMetaHeaderSerialized, serializationOffset, 
					sizeOfUserBankAccountNIBSerializedInBytes,
					0, sizeOfUserBankAccountNIBSerializedInBytes.length);
			serializationOffset += sizeOfUserBankAccountNIBSerializedInBytes.length;

			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(this.secureBidMessageMetaHeaderSerialized, serializationOffset, 
					sizeOfSecureBidMessageKeyExchangeAgreementSerializedCipheredInBytes,
					0, sizeOfSecureBidMessageKeyExchangeAgreementSerializedCipheredInBytes.length);
			serializationOffset += sizeOfSecureBidMessageKeyExchangeAgreementSerializedCipheredInBytes.length;
			
			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(this.secureBidMessageMetaHeaderSerialized, serializationOffset, 
					sizeOfSecureBidMessageKeyExchangeAgreementSerializedCipheredSignedInBytes,
					0, sizeOfSecureBidMessageKeyExchangeAgreementSerializedCipheredSignedInBytes.length);
			serializationOffset += sizeOfSecureBidMessageKeyExchangeAgreementSerializedCipheredSignedInBytes.length;
		
			this.sizeOfSecureBidMessageDataSerialized = CommonUtils.fromByteArrayToInt(sizeOfSecureBidMessageDataSerializedInBytes);
			this.sizeOfSecureBidMessageDoSMitigationSerialized = CommonUtils.fromByteArrayToInt(sizeOfSecureBidMessageDoSMitigationSerializedInBytes);
			
			this.sizeOfSecureBidMessageDataSignatureSerialized = CommonUtils.fromByteArrayToInt(sizeOfSecureBidMessageDataSignatureSerializedInBytes);
			this.sizeOfSecureBidMessageDataPersonalSerializedAndHashedCiphered = 
					CommonUtils.fromByteArrayToInt(sizeOfSecureBidMessageDataPersonalSerializedAndHashedCipheredInBytes);
			
			this.sizeOfSecureBidMessageDataPersonalSerialized = 
					CommonUtils.fromByteArrayToInt(sizeOfSecureBidMessageDataPersonalSerializedInBytes);
			this.sizeOfSecureBidMessageDataPersonalSerializedHashed = 
					CommonUtils.fromByteArrayToInt(sizeOfSecureBidMessageDataPersonalSerializedHashedInBytes);
			
			this.sizeOfBidDigitalSignedSerialized = CommonUtils.fromByteArrayToInt(sizeOfBidDigitalSignedSerializedInBytes);
			this.sizeOfBidSerialized = CommonUtils.fromByteArrayToInt(sizeOfBidSerializedInBytes);
			this.sizeOfBidSerializedHashedCiphered = CommonUtils.fromByteArrayToInt(sizeOfBidSerializedHashedCipheredInBytes);
			this.sizeOfBidderUserClientIDSerialized = CommonUtils.fromByteArrayToInt(sizeOfBidderUserClientIDSerializedInBytes);
			
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
