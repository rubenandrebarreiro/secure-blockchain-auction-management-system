package main.java.messages.secure.bid.components.key.exchange;

import java.security.NoSuchAlgorithmException;

import main.java.common.utils.CommonUtils;
import main.java.messages.secure.bid.components.key.exchange.agreement.SecureBidMessageKeyExchangeAgreement;
import main.java.messages.secure.bid.components.key.exchange.integrity.SecureBidMessageKeyExchangeIntegrity;

public class SecureBidMessageKeyExchange {

	private SecureBidMessageKeyExchangeAgreement secureBidMessageKeyExchangeAgreement;
	
	private int sizeOfSecureBidMessageKeyExchangeAgreementSerializedCiphered;
	
	private int sizeOfSecureBidMessageKeyExchangeAgreementSerializedCipheredSigned;
	
	
	private SecureBidMessageKeyExchangeIntegrity secureBidMessageKeyExchangeIntegrity;
	
	
	private byte[] secureBidMessageKeyExchangeSerialized;
	
	private boolean isSecureBidMessageKeyExchangeSerialized;
	
	
	public SecureBidMessageKeyExchange(SecureBidMessageKeyExchangeAgreement secureBidMessageKeyExchangeAgreement,
									   SecureBidMessageKeyExchangeIntegrity secureBidMessageKeyExchangeIntegrity) {
		
		this.secureBidMessageKeyExchangeAgreement = secureBidMessageKeyExchangeAgreement;
		
		this.secureBidMessageKeyExchangeIntegrity = secureBidMessageKeyExchangeIntegrity;
		
		this.secureBidMessageKeyExchangeSerialized = null;
		
		this.isSecureBidMessageKeyExchangeSerialized = false;
		
	}
	
	public SecureBidMessageKeyExchange(byte[] secureBidMessageKeyExchangeSerialized,
									   int sizeOfSecureBidMessageKeyExchangeAgreementSerializedCiphered,
									   int sizeOfSecureBidMessageKeyExchangeAgreementSerializedCipheredSigned) {

		this.secureBidMessageKeyExchangeSerialized = secureBidMessageKeyExchangeSerialized;
		
		
		this.isSecureBidMessageKeyExchangeSerialized = true;
		
		
		this.secureBidMessageKeyExchangeAgreement = null;
		
		this.sizeOfSecureBidMessageKeyExchangeAgreementSerializedCiphered = 
			 sizeOfSecureBidMessageKeyExchangeAgreementSerializedCiphered;
		
		this.sizeOfSecureBidMessageKeyExchangeAgreementSerializedCipheredSigned =
			 sizeOfSecureBidMessageKeyExchangeAgreementSerializedCipheredSigned;
		
		
		this.secureBidMessageKeyExchangeIntegrity = null;
		
	}
	
	
	
	public SecureBidMessageKeyExchangeAgreement getSecureKeyExchangeMessageAgreement() {
		return this.secureBidMessageKeyExchangeAgreement;
	}
	
	public int getSizeOfSecureBidMessageKeyExchangeAgreementSerializedCiphered() {
		return this.sizeOfSecureBidMessageKeyExchangeAgreementSerializedCiphered;
	}
	
	public int getSizeOfSecureBidMessageKeyExchangeAgreementSerializedCipheredSigned() {
		return this.sizeOfSecureBidMessageKeyExchangeAgreementSerializedCipheredSigned;
	}
	
	
	public SecureBidMessageKeyExchangeIntegrity getSecureKeyExchangeMessageIntegrity() {
		return this.secureBidMessageKeyExchangeIntegrity;
	}
	
	public byte[] getSecureBidMessageKeyExchangeSerialized() {
		return this.secureBidMessageKeyExchangeSerialized;
	}
	
	public boolean getIsSecureBidMessageKeyExchangeSerialized() {
		return this.isSecureBidMessageKeyExchangeSerialized;
	}

	public void setIsSecureBidMessageKeyExchangeSerialized
	      (boolean isSecureBidMessageKeyExchangeSerialized) {
		
		this.isSecureBidMessageKeyExchangeSerialized = isSecureBidMessageKeyExchangeSerialized;
	
	}
	
	public void doSecureBidMessageKeyExchangeSerialization() throws NoSuchAlgorithmException {
		
		if(!this.isSecureBidMessageKeyExchangeSerialized) {
			
			this.secureBidMessageKeyExchangeAgreement
			    .doSerializationOfSecureBidMessageKeyExchangeAgreement();
			
			this.secureBidMessageKeyExchangeIntegrity
			    .doHashOfSecureBidMessageKeyExchangeAgreementSerialized();
			
			
			byte[] secureBidMessageKeyExchangeAgreementSerialized = 
					this.secureBidMessageKeyExchangeAgreement
					    .getSecureBidMessageKeyExchangeAgreementSerialized();
			
			byte[] secureBidMessageKeyExchangeAgreementSerializedHashedForIntegrity =
					this.secureBidMessageKeyExchangeIntegrity
					    .getSecureBidMessageKeyExchangeAgreementSerializedHashed();
			
			
			int sizeOfSecureKeyExchangeMessageSerialized = 
					( secureBidMessageKeyExchangeAgreementSerialized.length +
					  secureBidMessageKeyExchangeAgreementSerializedHashedForIntegrity.length );
			
			this.secureBidMessageKeyExchangeSerialized = 
					new byte[ sizeOfSecureKeyExchangeMessageSerialized ];
			
			
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
			System.arraycopy(secureBidMessageKeyExchangeAgreementSerialized, 0,
							 this.secureBidMessageKeyExchangeSerialized, serializationOffset,
							 secureBidMessageKeyExchangeAgreementSerialized.length);
			serializationOffset += secureBidMessageKeyExchangeAgreementSerialized.length;

			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(secureBidMessageKeyExchangeAgreementSerializedHashedForIntegrity, 0, 
							 this.secureBidMessageKeyExchangeSerialized, serializationOffset, 
							 secureBidMessageKeyExchangeAgreementSerializedHashedForIntegrity.length);
			
			
			this.setIsSecureBidMessageKeyExchangeSerialized(true);
			
		}
		
	}
	
	
	public void undoSecureBidMessageKeyExchangeSerialization() {
		
		if(this.isSecureBidMessageKeyExchangeSerialized) {
			
			int sizeOfSecureBidMessageKeyExchangeAgreementSerialized = 
						( 2 * CommonUtils.LENGTH_256_BITS_IN_BYTES );
			
			byte[] secureBidMessageKeyExchangeAgreementSerialized = 
				    	new byte[ sizeOfSecureBidMessageKeyExchangeAgreementSerialized ];
			
			byte[] secureBidMessageKeyExchangeAgreementSerializedHashedForIntegrity = 
						new byte[ CommonUtils.LENGTH_256_BITS_IN_BYTES ];

			
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
			System.arraycopy(this.secureBidMessageKeyExchangeSerialized, serializationOffset,
							 secureBidMessageKeyExchangeAgreementSerialized, 0,
							 secureBidMessageKeyExchangeAgreementSerialized.length);
			serializationOffset += secureBidMessageKeyExchangeAgreementSerialized.length;

			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(this.secureBidMessageKeyExchangeSerialized, serializationOffset, 
							 secureBidMessageKeyExchangeAgreementSerializedHashedForIntegrity, 0, 
							 secureBidMessageKeyExchangeAgreementSerializedHashedForIntegrity.length);

			
			this.secureBidMessageKeyExchangeAgreement = 
					new SecureBidMessageKeyExchangeAgreement(secureBidMessageKeyExchangeAgreementSerialized,
															 sizeOfSecureBidMessageKeyExchangeAgreementSerializedCiphered,
															 sizeOfSecureBidMessageKeyExchangeAgreementSerializedCipheredSigned);
			
			this.secureBidMessageKeyExchangeIntegrity =
					new SecureBidMessageKeyExchangeIntegrity(secureBidMessageKeyExchangeAgreementSerialized,
															 secureBidMessageKeyExchangeAgreementSerializedHashedForIntegrity);
			
			this.setIsSecureBidMessageKeyExchangeSerialized(false);

		}
		
	}
	
}
