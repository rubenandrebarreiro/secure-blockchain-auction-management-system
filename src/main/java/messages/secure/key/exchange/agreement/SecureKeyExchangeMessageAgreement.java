package main.java.messages.secure.key.exchange.agreement;

import java.security.SecureRandom;

import main.java.common.utils.CommonUtils;

public class SecureKeyExchangeMessageAgreement {
	
	private long randomNonce;
	
	private long timestamp;
	
	private byte[] secretSymmetricKeyForDataPersonalInBytes;
	
	private byte[] secretHMACKeyForDoSMitigationInBytes;
	
	private byte[] secureKeyExchangeAgreementSerialized;
	
	private boolean isSecureKeyExchangeAgreementSerialized;
	
	
	
	public SecureKeyExchangeMessageAgreement(byte[] secretSymmetricKeyForDataPersonalInBytes,
											 byte[] secretHMACKeyForDoSMitigationInBytes) {
		
		SecureRandom secureRandom = new SecureRandom();
		
		this.randomNonce = secureRandom.nextLong();
		this.timestamp = System.currentTimeMillis();
		
		this.secretSymmetricKeyForDataPersonalInBytes = secretSymmetricKeyForDataPersonalInBytes;
		this.secretHMACKeyForDoSMitigationInBytes = secretHMACKeyForDoSMitigationInBytes;
		
		this.secureKeyExchangeAgreementSerialized = null;
		this.isSecureKeyExchangeAgreementSerialized = false;
	
	}
	
	
	public SecureKeyExchangeMessageAgreement(byte[] secureKeyExchangeAgreementSerialized) {
		
		this.secureKeyExchangeAgreementSerialized = secureKeyExchangeAgreementSerialized;
		this.isSecureKeyExchangeAgreementSerialized = true;
		
		this.randomNonce = 0L;
		this.timestamp = 0L;
		
		this.secretSymmetricKeyForDataPersonalInBytes = null;
		this.secretHMACKeyForDoSMitigationInBytes = null;
		
	}
	
	
	public long getRandomNonce() {
		return this.randomNonce;
	}
	
	public long getTimestamp() {
		return this.timestamp;
	}
	
	public byte[] getSecretSymmetricKeyForDataPersonalInBytes() {
		return this.secretSymmetricKeyForDataPersonalInBytes;
	}
	
	public byte[] getSecretHMACKeyForDoSMitigationInBytes() {
		return this.secretHMACKeyForDoSMitigationInBytes;
	}
	
	public byte[] secureKeyExchangeAgreementSerialized() {
		return this.secureKeyExchangeAgreementSerialized;
	}
	
	public boolean getIsSecureKeyExchangeAgreementSerialized() {
		return this.isSecureKeyExchangeAgreementSerialized;
	}
	
	public void setIsSecureKeyExchangeAgreementSerialized
		  (boolean isSecureKeyExchangeAgreementSerialized) {
		
		this.isSecureKeyExchangeAgreementSerialized = 
				isSecureKeyExchangeAgreementSerialized;
	
	}
	
	public void doSerializationOfSecureKeyExchangeMessageAgreement() {
		
		if(!this.isSecureKeyExchangeAgreementSerialized) {
			
			byte[] randomNonceSerialized = CommonUtils.fromLongToByteArray(this.randomNonce);
			byte[] timestampSerialized = CommonUtils.fromLongToByteArray(this.timestamp);
			
			
			int sizeOfSecureKeyExchangeAgreementSerialized = ( randomNonceSerialized.length + 
															   timestampSerialized.length + 
															   this.secretSymmetricKeyForDataPersonalInBytes.length +
															   this.secretHMACKeyForDoSMitigationInBytes.length);
			
			this.secureKeyExchangeAgreementSerialized = new byte[sizeOfSecureKeyExchangeAgreementSerialized];
					
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
			System.arraycopy(randomNonceSerialized, 0, this.secureKeyExchangeAgreementSerialized, serializationOffset, randomNonceSerialized.length);
			serializationOffset += randomNonceSerialized.length;

			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(timestampSerialized, 0, this.secureKeyExchangeAgreementSerialized, serializationOffset, timestampSerialized.length);
			serializationOffset += timestampSerialized.length;
			
			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(this.secretSymmetricKeyForDataPersonalInBytes, 0, this.secureKeyExchangeAgreementSerialized,
							 serializationOffset, this.secretSymmetricKeyForDataPersonalInBytes.length);
			serializationOffset += this.secretSymmetricKeyForDataPersonalInBytes.length;
			
			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(this.secretHMACKeyForDoSMitigationInBytes, 0, this.secureKeyExchangeAgreementSerialized,
					         serializationOffset, this.secretHMACKeyForDoSMitigationInBytes.length);
			
			
			
			this.setIsSecureKeyExchangeAgreementSerialized(true);
			
		}
		
	}
	
	public void undoSerializationOfSecureKeyExchangeMessageAgreement() {
		
		if(this.isSecureKeyExchangeAgreementSerialized) {
			
			byte[] randomNonceSerialized = new byte[CommonUtils.LONG_IN_BYTES_LENGTH];
			byte[] timestampSerialized = new byte[CommonUtils.LONG_IN_BYTES_LENGTH];
			
			this.secretSymmetricKeyForDataPersonalInBytes = new byte[CommonUtils.SECRET_KEY_256_BITS_IN_BYTES];
			this.secretHMACKeyForDoSMitigationInBytes = new byte[CommonUtils.SECRET_KEY_256_BITS_IN_BYTES];
			
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
			System.arraycopy(this.secureKeyExchangeAgreementSerialized, serializationOffset, randomNonceSerialized, 0, randomNonceSerialized.length);
			serializationOffset += randomNonceSerialized.length;

			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(this.secureKeyExchangeAgreementSerialized, serializationOffset, timestampSerialized, 0, timestampSerialized.length);
			serializationOffset += timestampSerialized.length;
			
			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(this.secureKeyExchangeAgreementSerialized, serializationOffset, this.secretSymmetricKeyForDataPersonalInBytes,
							 0, this.secretSymmetricKeyForDataPersonalInBytes.length);
			serializationOffset += this.secretSymmetricKeyForDataPersonalInBytes.length;
			
			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(this.secureKeyExchangeAgreementSerialized, 0, this.secretHMACKeyForDoSMitigationInBytes,
					         0, this.secretHMACKeyForDoSMitigationInBytes.length);
			
			this.setIsSecureKeyExchangeAgreementSerialized(false);
			
		}
		
	}
}
