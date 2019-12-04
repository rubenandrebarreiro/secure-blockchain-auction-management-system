package main.java.messages.secure.bid.metaheader;

import main.java.common.utils.CommonUtils;

public class SecureBidMessageMetaHeader {
	
	private int sizeOfSecureBidMessageDataSerialized;
	
	private int sizeOfSecureBidMessageDoSMitigationSerialized;
	
	
	private int sizeOfSecureBidMessageDataSignatureSerialized;

	private int sizeOfSecureBidMessageDataPersonalSerializedAndHashedCiphered;
	
	
	private int sizeOfSecureBidMessageDataPersonalSerialized;
	
	private int sizeOfSecureBidMessageDataPersonalSerializedHashed;
	
		
	private int sizeOfBidDigitalSignedSerialized;
	
	private int sizeOfBidSerialized;
	
	private int sizeOfBidSerializedHashedCiphered;
	
	private int sizeOfBidderUserClientID;
	
	
	private int sizeOfUserEmailFromSecureBidMessageDataPersonalSerialized;

	private int sizeOfUserHomeAddressFromSecureBidMessageDataPersonalSerialized;
	
	private int sizeOfUserBankAccountNIBFromSecureBidMessageDataPersonalSerialized;
	
	
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
									  int sizeOfBidderUserClientID,
									  int sizeOfUserEmailFromSecureBidMessageDataPersonalSerialized,
									  int sizeOfUserHomeAddressFromSecureBidMessageDataPersonalSerialized,
									  int sizeOfUserBankAccountNIBFromSecureBidMessageDataPersonalSerialized) {
		
		this.sizeOfSecureBidMessageDataSerialized = sizeOfSecureBidMessageDataSerialized;
		this.sizeOfSecureBidMessageDoSMitigationSerialized = sizeOfSecureBidMessageDoSMitigationSerialized;
		
		this.sizeOfSecureBidMessageDataSignatureSerialized = sizeOfSecureBidMessageDataSignatureSerialized;
		this.sizeOfSecureBidMessageDataPersonalSerializedAndHashedCiphered = 
											sizeOfSecureBidMessageDataPersonalSerializedAndHashedCiphered;
		
		this.sizeOfSecureBidMessageDataPersonalSerialized = sizeOfSecureBidMessageDataPersonalSerialized;
		this.sizeOfSecureBidMessageDataPersonalSerializedHashed = 
											sizeOfSecureBidMessageDataPersonalSerializedHashed;
		
		this.sizeOfBidDigitalSignedSerialized = sizeOfBidDigitalSignedSerialized;
		this.sizeOfBidSerialized = sizeOfBidSerialized;
		this.sizeOfBidSerializedHashedCiphered = sizeOfBidSerializedHashedCiphered;
		this.sizeOfBidderUserClientID = sizeOfBidderUserClientID;
		
		this.sizeOfUserEmailFromSecureBidMessageDataPersonalSerialized = 
						sizeOfUserEmailFromSecureBidMessageDataPersonalSerialized;
		this.sizeOfUserHomeAddressFromSecureBidMessageDataPersonalSerialized = 
						sizeOfUserHomeAddressFromSecureBidMessageDataPersonalSerialized;
		this.sizeOfUserBankAccountNIBFromSecureBidMessageDataPersonalSerialized =
						sizeOfUserBankAccountNIBFromSecureBidMessageDataPersonalSerialized;
		
		
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
		
		this.sizeOfSecureBidMessageDataPersonalSerialized = 0;
		this.sizeOfSecureBidMessageDataPersonalSerializedHashed = 0;
		
		this.sizeOfBidDigitalSignedSerialized = 0;
		this.sizeOfBidSerialized = 0;
		this.sizeOfBidSerializedHashedCiphered = 0;
		this.sizeOfBidderUserClientID = 0;
		
		this.sizeOfUserEmailFromSecureBidMessageDataPersonalSerialized = 0;
		this.sizeOfUserHomeAddressFromSecureBidMessageDataPersonalSerialized = 0;
		this.sizeOfUserBankAccountNIBFromSecureBidMessageDataPersonalSerialized = 0;
		
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

	public int getSizeOfBidderUserClientID() {
		return this.sizeOfBidderUserClientID;
	}

	public void setSizeOfBidderUserClientID(int sizeOfBidderUserClientID) {
		this.sizeOfBidderUserClientID = sizeOfBidderUserClientID;
	}

	public int getSizeOfUserEmailFromSecureBidMessageDataPersonalSerialized() {
		return this.sizeOfUserEmailFromSecureBidMessageDataPersonalSerialized;
	}

	public void setSizeOfUserEmailFromSecureBidMessageDataPersonalSerialized
		  (int sizeOfUserEmailFromSecureBidMessageDataPersonalSerialized) {
		
		this.sizeOfUserEmailFromSecureBidMessageDataPersonalSerialized = 
				sizeOfUserEmailFromSecureBidMessageDataPersonalSerialized;
	
	}

	public int getSizeOfUserHomeAddressFromSecureBidMessageDataPersonalSerialized() {
		return this.sizeOfUserHomeAddressFromSecureBidMessageDataPersonalSerialized;
	}

	public void setSizeOfUserHomeAddressFromSecureBidMessageDataPersonalSerialized
		  (int sizeOfUserHomeAddressFromSecureBidMessageDataPersonalSerialized) {
		
		this.sizeOfUserHomeAddressFromSecureBidMessageDataPersonalSerialized = 
				sizeOfUserHomeAddressFromSecureBidMessageDataPersonalSerialized;
		
	}

	public int getSizeOfUserBankAccountNIBFromSecureBidMessageDataPersonalSerialized() {
		return this.sizeOfUserBankAccountNIBFromSecureBidMessageDataPersonalSerialized;
	}

	public void setSizeOfUserBankAccountNIBFromSecureBidMessageDataPersonalSerialized
		  (int sizeOfUserBankAccountNIBFromSecureBidMessageDataPersonalSerialized) {
		
		this.sizeOfUserBankAccountNIBFromSecureBidMessageDataPersonalSerialized = 
				sizeOfUserBankAccountNIBFromSecureBidMessageDataPersonalSerialized;
		
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
	
	
	
	public void doSecureBidMessageMetaHeaderSerialized() {
		
		if(!this.getIsSecureBidMessageMetaHeaderSerialized()) {
			
			int sizeOfSecureBidMessageMetaHeaderSerialized = ( ( 2 * CommonUtils.META_HEADER_OUTSIDE_SEPARATORS_LENGTH) +
															   ( 12 * CommonUtils.META_HEADER_INSIDE_SEPARATORS_LENGTH) +
															   ( 13 * CommonUtils.INTEGER_IN_BYTES_LENGTH ) );

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
			byte[] sizeOfBidderUserClientIDInBytes = CommonUtils.fromIntToByteArray(this.sizeOfBidderUserClientID);

			byte[] sizeOfUserEmailFromSecureBidMessageDataPersonalSerializedInBytes = 
					CommonUtils.fromIntToByteArray(this.sizeOfUserEmailFromSecureBidMessageDataPersonalSerialized);
			byte[] sizeOfUserHomeAddressFromSecureBidMessageDataPersonalSerializedInBytes = 
					CommonUtils.fromIntToByteArray(this.sizeOfUserHomeAddressFromSecureBidMessageDataPersonalSerialized);
			byte[] sizeOfUserBankAccountNIBFromSecureBidMessageDataPersonalSerializedInBytes = 
					CommonUtils.fromIntToByteArray(this.sizeOfUserBankAccountNIBFromSecureBidMessageDataPersonalSerialized);


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
			System.arraycopy(sizeOfBidderUserClientIDInBytes, 0, this.secureBidMessageMetaHeaderSerialized,
					serializationOffset, sizeOfBidderUserClientIDInBytes.length);
			serializationOffset += sizeOfBidderUserClientIDInBytes.length;

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
			System.arraycopy(sizeOfUserEmailFromSecureBidMessageDataPersonalSerializedInBytes, 0, this.secureBidMessageMetaHeaderSerialized,
					serializationOffset, sizeOfUserEmailFromSecureBidMessageDataPersonalSerializedInBytes.length);
			serializationOffset += sizeOfUserEmailFromSecureBidMessageDataPersonalSerializedInBytes.length;

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
			System.arraycopy(sizeOfUserHomeAddressFromSecureBidMessageDataPersonalSerializedInBytes, 0, this.secureBidMessageMetaHeaderSerialized,
					serializationOffset, sizeOfUserHomeAddressFromSecureBidMessageDataPersonalSerializedInBytes.length);
			serializationOffset += sizeOfUserHomeAddressFromSecureBidMessageDataPersonalSerializedInBytes.length;

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
			System.arraycopy(sizeOfUserBankAccountNIBFromSecureBidMessageDataPersonalSerializedInBytes, 0, this.secureBidMessageMetaHeaderSerialized,
					serializationOffset, sizeOfUserBankAccountNIBFromSecureBidMessageDataPersonalSerializedInBytes.length);
			serializationOffset += sizeOfUserBankAccountNIBFromSecureBidMessageDataPersonalSerializedInBytes.length;

			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(outsideSeparator, 0, this.secureBidMessageMetaHeaderSerialized, serializationOffset, outsideSeparator.length);


			this.setIsSecureBidMessageMetaHeaderSerialized(true);
			
		}
		
	}
	
	
	public void undoSecureBidMessageMetaHeaderSerialized() {
		
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
			byte[] sizeOfBidderUserClientIDInBytes = new byte[CommonUtils.INTEGER_IN_BYTES_LENGTH];

			byte[] sizeOfUserEmailFromSecureBidMessageDataPersonalSerializedInBytes = new byte[CommonUtils.INTEGER_IN_BYTES_LENGTH];
			byte[] sizeOfUserHomeAddressFromSecureBidMessageDataPersonalSerializedInBytes = new byte[CommonUtils.INTEGER_IN_BYTES_LENGTH];
			byte[] sizeOfUserBankAccountNIBFromSecureBidMessageDataPersonalSerializedInBytes = new byte[CommonUtils.INTEGER_IN_BYTES_LENGTH];
			
			
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
			System.arraycopy(this.secureBidMessageMetaHeaderSerialized, serializationOffset, sizeOfBidderUserClientIDInBytes,
					0, sizeOfBidderUserClientIDInBytes.length);
			serializationOffset += sizeOfBidderUserClientIDInBytes.length;

			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			serializationOffset += insideSeparator.length;
			System.arraycopy(this.secureBidMessageMetaHeaderSerialized, serializationOffset,
					sizeOfUserEmailFromSecureBidMessageDataPersonalSerializedInBytes,
					0, sizeOfUserEmailFromSecureBidMessageDataPersonalSerializedInBytes.length);
			serializationOffset += sizeOfUserEmailFromSecureBidMessageDataPersonalSerializedInBytes.length;

			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			serializationOffset += insideSeparator.length;
			System.arraycopy(this.secureBidMessageMetaHeaderSerialized, serializationOffset,
					sizeOfUserHomeAddressFromSecureBidMessageDataPersonalSerializedInBytes,
					0, sizeOfUserHomeAddressFromSecureBidMessageDataPersonalSerializedInBytes.length);
			serializationOffset += sizeOfUserHomeAddressFromSecureBidMessageDataPersonalSerializedInBytes.length;

			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(this.secureBidMessageMetaHeaderSerialized, serializationOffset, 
					sizeOfUserBankAccountNIBFromSecureBidMessageDataPersonalSerializedInBytes,
					0, sizeOfUserBankAccountNIBFromSecureBidMessageDataPersonalSerializedInBytes.length);
			serializationOffset += sizeOfUserBankAccountNIBFromSecureBidMessageDataPersonalSerializedInBytes.length;
			
			
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
			this.sizeOfBidderUserClientID = CommonUtils.fromByteArrayToInt(sizeOfBidderUserClientIDInBytes);
			
			this.sizeOfUserEmailFromSecureBidMessageDataPersonalSerialized = 
					CommonUtils.fromByteArrayToInt(sizeOfUserEmailFromSecureBidMessageDataPersonalSerializedInBytes);
			this.sizeOfUserHomeAddressFromSecureBidMessageDataPersonalSerialized = 
					CommonUtils.fromByteArrayToInt(sizeOfUserHomeAddressFromSecureBidMessageDataPersonalSerializedInBytes);
			this.sizeOfUserBankAccountNIBFromSecureBidMessageDataPersonalSerialized = 
					CommonUtils.fromByteArrayToInt(sizeOfUserBankAccountNIBFromSecureBidMessageDataPersonalSerializedInBytes);
			
			
			this.setIsSecureBidMessageMetaHeaderSerialized(false);
			
		}
	}
	
}
