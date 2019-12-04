package main.java.messages.secure.bid.components.data;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import javax.crypto.NoSuchPaddingException;

import main.java.messages.secure.bid.components.data.personal.SecureBidMessageDataPersonal;
import main.java.messages.secure.bid.components.data.signature.SecureBidMessageDataSignature;

public class SecureBidMessageData {

	private SecureBidMessageDataSignature secureBidMessageDataSignature;
	
	private int sizeOfSecureBidMessageDataSignatureSerialized;
	
	private int sizeOfBidSerialized;
	
	private int sizeOfBidSerializedHashedCiphered;
	
	private int sizeOfBidderUserClientIDSerialized;
	
	
	
	private SecureBidMessageDataPersonal secureBidMessageDataPersonal;
	
	private int sizeOfSecureBidMessageDataPersonalSerializedAndHashedCiphered;
	
	private int sizeOfSecureBidMessageDataPersonalSerialized;
	
	private int sizeOfSecureBidMessageDataPersonalSerializedHashed;
	
	private int sizeOfUserEmailSerialized;
	
	private int sizeOfUserHomeAddressSerialized; 
	
	private int sizeOfUserBankAccountNIBSerialized;
	
	
	private byte[] secureBidMessageDataSerialized;
	
	private boolean isSecureBidMessageDataSerialized;
	
	
	public SecureBidMessageData(SecureBidMessageDataSignature secureBidMessageDataSignature,
								SecureBidMessageDataPersonal secureBidMessageDataPersonal) {
		
		this.secureBidMessageDataSignature = secureBidMessageDataSignature;
		this.sizeOfSecureBidMessageDataSignatureSerialized = 0;
		
		this.secureBidMessageDataPersonal = secureBidMessageDataPersonal;
		this.sizeOfSecureBidMessageDataPersonalSerializedAndHashedCiphered = 0;
		
		this.secureBidMessageDataSerialized = null;
		this.isSecureBidMessageDataSerialized = false;
		
	}
	
	
	public SecureBidMessageData(byte[] secureBidMessageDataSerialized,
								int sizeOfSecureBidMessageDataSignatureSerialized,
								int sizeOfBidSerialized,
								int sizeOfBidSerializedHashedCiphered,
							    int sizeOfBidderUserClientIDSerialized,
								int sizeOfSecureBidMessageDataPersonalSerializedAndHashedCiphered,
								int sizeOfSecureBidMessageDataPersonalSerialized,
								int sizeOfSecureBidMessageDataPersonalSerializedHashed,
								int sizeOfUserEmailSerialized, int sizeOfUserHomeAddressSerialized, 
								int sizeOfUserBankAccountNIBSerialized) {

		this.secureBidMessageDataSerialized = secureBidMessageDataSerialized;
		this.isSecureBidMessageDataSerialized = true;
		
		this.secureBidMessageDataSignature = null;
		this.sizeOfSecureBidMessageDataSignatureSerialized = sizeOfSecureBidMessageDataSignatureSerialized;
		this.sizeOfBidSerialized = sizeOfBidSerialized;
		this.sizeOfBidSerializedHashedCiphered = sizeOfBidSerializedHashedCiphered;
		this.sizeOfBidderUserClientIDSerialized = sizeOfBidderUserClientIDSerialized;
		
		this.secureBidMessageDataPersonal = null;
		this.sizeOfSecureBidMessageDataPersonalSerializedAndHashedCiphered = 
				sizeOfSecureBidMessageDataPersonalSerializedAndHashedCiphered;
		this.sizeOfSecureBidMessageDataPersonalSerialized = sizeOfSecureBidMessageDataPersonalSerialized;
		this.sizeOfSecureBidMessageDataPersonalSerializedHashed = sizeOfSecureBidMessageDataPersonalSerializedHashed;
		this.sizeOfUserEmailSerialized = sizeOfUserEmailSerialized;
		this.sizeOfUserHomeAddressSerialized = sizeOfUserEmailSerialized; 
		this.sizeOfUserBankAccountNIBSerialized = sizeOfUserBankAccountNIBSerialized;
		
	}
	
	
	
	public SecureBidMessageDataSignature getSecureBidMessageDataSignature() {
		return this.secureBidMessageDataSignature;
	}	

	public int getSizeOfSecureBidMessageDataSignatureSerialized() {
		return this.sizeOfSecureBidMessageDataSignatureSerialized;
	}
	
	public int getSizeOfBidSerialized() {
		return this.sizeOfBidSerialized;
	}
	
	public int getSizeOfBidSerializedHashedCiphered() {
		return this.sizeOfBidSerializedHashedCiphered;
	}
	
	public int getSizeOfBidderUserClientIDSerialized() {
		return this.sizeOfBidderUserClientIDSerialized;
	}
	
	
	
	
	
	public SecureBidMessageDataPersonal getSecureBidMessageDataPersonal() {
		return this.secureBidMessageDataPersonal;
	}

	public int getSizeOfSecureBidMessageDataPersonalSerializedAndHashedCiphered() {
		return this.sizeOfSecureBidMessageDataPersonalSerializedAndHashedCiphered;
	}

	public byte[] getSecureBidMessageDataSerialized() {
		return this.secureBidMessageDataSerialized;
	}

	public boolean getIsSecureBidMessageDataSerialized() {
		return this.isSecureBidMessageDataSerialized;
	}
	
	public void setIsSecureBidMessageDataSerialized(boolean isSecureBidMessageDataSerialized) {
		this.isSecureBidMessageDataSerialized = isSecureBidMessageDataSerialized;
	}
	
	
	
	
	public void doSecureBidMessageDataSerialization()
			throws InvalidKeyException, NoSuchAlgorithmException, NoSuchProviderException,
			NoSuchPaddingException, InvalidAlgorithmParameterException {
	
		if(!this.isSecureBidMessageDataSerialized) {
			
			this.secureBidMessageDataSignature.buildSecureBidMessageDataSignatureToSend();
			
			byte[] secureBidMessageDataSignatureSerialized = 
				   this.secureBidMessageDataSignature.getBidDigitalSigned();
			
			this.secureBidMessageDataPersonal.buildSecureBidMessageDataPersonalToSend();
			
			byte[] secureBidMessageDataPersonalSerializedAndHashedCiphered = 
				   this.secureBidMessageDataPersonal.getSecureBidMessageDataPersonalSerializedAndHashedCiphered();
			
			
			int sizeOfSecureBidMessageDataSerialized = ( secureBidMessageDataSignatureSerialized.length +
														 secureBidMessageDataPersonalSerializedAndHashedCiphered.length );
			
			this.secureBidMessageDataSerialized = new byte[ sizeOfSecureBidMessageDataSerialized ];
			
			
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
			System.arraycopy(secureBidMessageDataSignatureSerialized, 0,
					         this.secureBidMessageDataSerialized, serializationOffset,
					         secureBidMessageDataSignatureSerialized.length);
			serializationOffset += secureBidMessageDataSignatureSerialized.length;

			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(secureBidMessageDataPersonalSerializedAndHashedCiphered, 0,
							 this.secureBidMessageDataSerialized, serializationOffset,
							 secureBidMessageDataPersonalSerializedAndHashedCiphered.length);
			
			this.setIsSecureBidMessageDataSerialized(true);
			
		}
		
	}
	
	public void undoSecureBidMessageDataSerialization()
		   throws InvalidKeyException, NoSuchAlgorithmException, NoSuchProviderException,
		          NoSuchPaddingException, InvalidAlgorithmParameterException {
		
		if(this.isSecureBidMessageDataSerialized) {
			
			byte[] secureBidMessageDataSignatureSerialized = 
					new byte[this.sizeOfSecureBidMessageDataSignatureSerialized];
			
			byte[] secureBidMessageDataPersonalSerializedAndHashedCiphered = 
					new byte[this.sizeOfSecureBidMessageDataPersonalSerializedAndHashedCiphered];
			
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
			System.arraycopy(this.secureBidMessageDataSerialized, serializationOffset,
					         secureBidMessageDataSignatureSerialized, 0,
					         secureBidMessageDataSignatureSerialized.length);
			serializationOffset += secureBidMessageDataSignatureSerialized.length;

			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(this.secureBidMessageDataSerialized, serializationOffset,
							 secureBidMessageDataPersonalSerializedAndHashedCiphered, 0,
							 secureBidMessageDataPersonalSerializedAndHashedCiphered.length);
			
			this.secureBidMessageDataSignature = 
					new SecureBidMessageDataSignature(secureBidMessageDataSignatureSerialized,
													  this.sizeOfBidSerialized,
												      this.sizeOfBidSerializedHashedCiphered,
												      this.sizeOfSecureBidMessageDataPersonalSerializedAndHashedCiphered);
			
			this.secureBidMessageDataSignature.buildSecureBidMessageDataSignatureReceived();
			
			
			this.secureBidMessageDataPersonal = 
					new SecureBidMessageDataPersonal(secureBidMessageDataPersonalSerializedAndHashedCiphered,
									 				 this.sizeOfSecureBidMessageDataPersonalSerialized,
													 this.sizeOfSecureBidMessageDataPersonalSerializedHashed,
													 this.sizeOfUserEmailSerialized,
													 this.sizeOfUserHomeAddressSerialized,
													 this.sizeOfUserBankAccountNIBSerialized);
			
			this.secureBidMessageDataPersonal.buildSecureBidMessageDataPersonalReceived();
			
			
			this.setIsSecureBidMessageDataSerialized(false);
			
		}
		
	}
	
}