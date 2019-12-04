package main.java.messages.secure.key.exchange;

import java.security.NoSuchAlgorithmException;

import main.java.common.utils.CommonUtils;
import main.java.messages.secure.key.exchange.agreement.SecureKeyExchangeMessageAgreement;
import main.java.messages.secure.key.exchange.integrity.SecureKeyExchangeMessageIntegrity;

public class SecureKeyExchangeMessage {

	private SecureKeyExchangeMessageAgreement secureKeyExchangeMessageAgreement;
	
	private SecureKeyExchangeMessageIntegrity secureKeyExchangeMessageIntegrity;
	
	private byte[] secureKeyExchangeMessageSerialized;
	
	private boolean isSecureKeyExchangeMessageSerialized;
	
	
	public SecureKeyExchangeMessage(SecureKeyExchangeMessageAgreement secureKeyExchangeMessageAgreement,
									SecureKeyExchangeMessageIntegrity secureKeyExchangeMessageIntegrity) {
		
		this.secureKeyExchangeMessageAgreement = secureKeyExchangeMessageAgreement;
		
		this.secureKeyExchangeMessageIntegrity = secureKeyExchangeMessageIntegrity;
		
		this.secureKeyExchangeMessageSerialized = null;
		
		this.isSecureKeyExchangeMessageSerialized = false;
		
	}
	
	public SecureKeyExchangeMessage(byte[] secureKeyExchangeMessageSerialized) {

		this.secureKeyExchangeMessageSerialized = secureKeyExchangeMessageSerialized;
		
		this.isSecureKeyExchangeMessageSerialized = true;
		
		this.secureKeyExchangeMessageAgreement = null;
		
		this.secureKeyExchangeMessageIntegrity = null;
		
		
	}
	
	
	
	public SecureKeyExchangeMessageAgreement getSecureKeyExchangeMessageAgreement() {
		return this.secureKeyExchangeMessageAgreement;
	}
	
	public SecureKeyExchangeMessageIntegrity getSecureKeyExchangeMessageIntegrity() {
		return this.secureKeyExchangeMessageIntegrity;
	}
	
	public byte[] getSecureKeyExchangeMessageSerialized() {
		return this.secureKeyExchangeMessageSerialized;
	}
	
	public boolean getIsSecureKeyExchangeMessageSerialized() {
		return this.isSecureKeyExchangeMessageSerialized;
	}

	public void setIsSecureKeyExchangeMessageSerialized(boolean isSecureKeyExchangeMessageSerialized) {
		this.isSecureKeyExchangeMessageSerialized = isSecureKeyExchangeMessageSerialized;
	}
	
	
	public void doSecureKeyExchangeMessageSerialization() throws NoSuchAlgorithmException {
		
		if(!this.isSecureKeyExchangeMessageSerialized) {
			
			this.secureKeyExchangeMessageAgreement.doSerializationOfSecureKeyExchangeMessageAgreement();
			this.secureKeyExchangeMessageIntegrity.doHashOfSecureKeyExchangeAgreementSerialized();
			
			byte[] secureKeyExchangeMessageAgreementSerialized = 
					this.secureKeyExchangeMessageAgreement.getSecureKeyExchangeAgreementSerialized();
			
			byte[] secureKeyExchangeMessageHashedForIntegrity =
					this.secureKeyExchangeMessageIntegrity.getSecureKeyExchangeAgreementSerializedHashed();
			
			
			int sizeOfSecureKeyExchangeMessageSerialized = ( secureKeyExchangeMessageAgreementSerialized.length +
															 secureKeyExchangeMessageHashedForIntegrity.length );
			
			this.secureKeyExchangeMessageSerialized = new byte[ sizeOfSecureKeyExchangeMessageSerialized ];
			
			
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
			System.arraycopy(secureKeyExchangeMessageAgreementSerialized, 0,
							 this.secureKeyExchangeMessageSerialized, serializationOffset,
							 secureKeyExchangeMessageAgreementSerialized.length);
			serializationOffset += secureKeyExchangeMessageAgreementSerialized.length;

			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(secureKeyExchangeMessageHashedForIntegrity, 0, 
							 this.isSecureKeyExchangeMessageSerialized, serializationOffset, 
							 secureKeyExchangeMessageHashedForIntegrity.length);
			
			
			this.setIsSecureKeyExchangeMessageSerialized(true);
			
		}
		
	}
	
	public void undoSecureKeyExchangeMessageSerialization() {
		
		if(this.isSecureKeyExchangeMessageSerialized) {
			
			int sizeOfSecureKeyExchangeMessageAgreementSerialized = ( ( 2 * CommonUtils.LONG_IN_BYTES_LENGTH ) +
																	  ( 2 * CommonUtils.LENGTH_256_BITS_IN_BYTES ) );
			
			byte[] secureKeyExchangeMessageAgreementSerialized = new byte[ sizeOfSecureKeyExchangeMessageAgreementSerialized ];
			
			byte[] secureKeyExchangeMessageHashedForIntegrity = new byte[CommonUtils.LENGTH_256_BITS_IN_BYTES];

			
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
			System.arraycopy(this.secureKeyExchangeMessageSerialized, serializationOffset,
							 secureKeyExchangeMessageAgreementSerialized, 0,
							 secureKeyExchangeMessageAgreementSerialized.length);
			serializationOffset += secureKeyExchangeMessageAgreementSerialized.length;

			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(this.isSecureKeyExchangeMessageSerialized, serializationOffset, 
							 secureKeyExchangeMessageHashedForIntegrity, 0, 
							 secureKeyExchangeMessageHashedForIntegrity.length);

			
			this.secureKeyExchangeMessageAgreement = 
					new SecureKeyExchangeMessageAgreement(secureKeyExchangeMessageAgreementSerialized);
			
			this.secureKeyExchangeMessageIntegrity =
					new SecureKeyExchangeMessageIntegrity(secureKeyExchangeMessageAgreementSerialized,
												          secureKeyExchangeMessageHashedForIntegrity);
			
			this.setIsSecureKeyExchangeMessageSerialized(false);

		}
		
	}
}
