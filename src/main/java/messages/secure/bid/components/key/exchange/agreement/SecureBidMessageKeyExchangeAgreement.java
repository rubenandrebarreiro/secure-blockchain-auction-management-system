package main.java.messages.secure.bid.components.key.exchange.agreement;

import main.java.common.utils.CommonUtils;

public class SecureBidMessageKeyExchangeAgreement {
		
	private byte[] secretSymmetricKeyForDataPersonalInBytes;
	
	private byte[] secretHMACKeyForDoSMitigationInBytes;
	
	private byte[] secureBidMessageKeyExchangeAgreementSerialized;
	
	private boolean isSecureBidMessageKeyExchangeAgreementSerialized;
	
	
	
	public SecureBidMessageKeyExchangeAgreement(byte[] secretSymmetricKeyForDataPersonalInBytes,
											    byte[] secretHMACKeyForDoSMitigationInBytes) {
			
		this.secretSymmetricKeyForDataPersonalInBytes = secretSymmetricKeyForDataPersonalInBytes;
		this.secretHMACKeyForDoSMitigationInBytes = secretHMACKeyForDoSMitigationInBytes;
		
		this.secureBidMessageKeyExchangeAgreementSerialized = null;
		this.isSecureBidMessageKeyExchangeAgreementSerialized = false;
	
	}
	
	
	public SecureBidMessageKeyExchangeAgreement(byte[] secureBidMessageKeyExchangeAgreementSerialized) {
		
		this.secureBidMessageKeyExchangeAgreementSerialized = secureBidMessageKeyExchangeAgreementSerialized;
		this.isSecureBidMessageKeyExchangeAgreementSerialized = true;
		
		this.secretSymmetricKeyForDataPersonalInBytes = null;
		this.secretHMACKeyForDoSMitigationInBytes = null;
		
	}
	
		
	public byte[] getSecretSymmetricKeyForDataPersonalInBytes() {
		return this.secretSymmetricKeyForDataPersonalInBytes;
	}
	
	public byte[] getSecretHMACKeyForDoSMitigationInBytes() {
		return this.secretHMACKeyForDoSMitigationInBytes;
	}
	
	public byte[] getSecureBidMessageKeyExchangeAgreementSerialized() {
		return this.secureBidMessageKeyExchangeAgreementSerialized;
	}
	
	public boolean getIsSecureBidMessageKeyExchangeAgreementSerialized() {
		return this.isSecureBidMessageKeyExchangeAgreementSerialized;
	}
	
	public void setIsSecureBidMessageKeyExchangeAgreementSerialized
		  (boolean isSecureBidMessageKeyExchangeAgreementSerialized) {
		
		this.isSecureBidMessageKeyExchangeAgreementSerialized = 
				isSecureBidMessageKeyExchangeAgreementSerialized;
	
	}
	
	public void doSerializationOfSecureBidMessageKeyExchangeAgreement() {
		
		if(!this.isSecureBidMessageKeyExchangeAgreementSerialized) {
			
			int sizeOfSecureKeyExchangeAgreementSerialized = ( this.secretSymmetricKeyForDataPersonalInBytes.length +
															   this.secretHMACKeyForDoSMitigationInBytes.length);
			
			this.secureBidMessageKeyExchangeAgreementSerialized = new byte[sizeOfSecureKeyExchangeAgreementSerialized];
					
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
			System.arraycopy(this.secretSymmetricKeyForDataPersonalInBytes, 0, this.secureBidMessageKeyExchangeAgreementSerialized,
							 serializationOffset, this.secretSymmetricKeyForDataPersonalInBytes.length);
			serializationOffset += this.secretSymmetricKeyForDataPersonalInBytes.length;
			
			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(this.secretHMACKeyForDoSMitigationInBytes, 0, this.secureBidMessageKeyExchangeAgreementSerialized,
					         serializationOffset, this.secretHMACKeyForDoSMitigationInBytes.length);
			
			
			
			this.setIsSecureBidMessageKeyExchangeAgreementSerialized(true);
			
		}
		
	}
	
	public void undoSerializationOfSecureBidMessageKeyExchangeAgreement() {
		
		if(this.isSecureBidMessageKeyExchangeAgreementSerialized) {
			
			this.secretSymmetricKeyForDataPersonalInBytes = new byte[CommonUtils.LENGTH_256_BITS_IN_BYTES];
			this.secretHMACKeyForDoSMitigationInBytes = new byte[CommonUtils.LENGTH_256_BITS_IN_BYTES];
			
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
			System.arraycopy(this.secureBidMessageKeyExchangeAgreementSerialized, serializationOffset, this.secretSymmetricKeyForDataPersonalInBytes,
							 0, this.secretSymmetricKeyForDataPersonalInBytes.length);
			serializationOffset += this.secretSymmetricKeyForDataPersonalInBytes.length;
			
			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(this.secureBidMessageKeyExchangeAgreementSerialized, 0, this.secretHMACKeyForDoSMitigationInBytes,
					         0, this.secretHMACKeyForDoSMitigationInBytes.length);
			
			this.setIsSecureBidMessageKeyExchangeAgreementSerialized(false);
			
		}
		
	}
}
