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
	
	private int sizeOfBidSerializedDigitalSigned;
	
	private int sizeOfBidderUserClientIDSerialized;
	
	
	
	private SecureBidMessageDataPersonal secureBidMessageDataPersonal;
	
	private int sizeOfSecureBidMessageDataPersonalSerializedCipheredAndHashed;
	
	private int sizeOfSecureBidMessageDataPersonalSerializedCiphered;
	
	private int sizeOfSecureBidMessageDataPersonalSerializedCipheredHashed;
	
	private int sizeOfSecureBidMessageDataPersonalSerialized;
	
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
		this.sizeOfSecureBidMessageDataPersonalSerializedCipheredAndHashed = 0;
		this.sizeOfSecureBidMessageDataPersonalSerializedCiphered = 0;
		this.sizeOfSecureBidMessageDataPersonalSerializedCipheredHashed = 0;
		
		this.secureBidMessageDataSerialized = null;
		this.isSecureBidMessageDataSerialized = false;
		
	}
	
	
	public SecureBidMessageData(byte[] secureBidMessageDataSerialized,
								int sizeOfSecureBidMessageDataSignatureSerialized,
								int sizeOfBidSerialized,
								int sizeOfBidSerializedDigitalSigned,
							    int sizeOfBidderUserClientIDSerialized,
								int sizeOfSecureBidMessageDataPersonalSerializedCipheredAndHashed,
								int sizeOfSecureBidMessageDataPersonalSerializedCiphered,
								int sizeOfSecureBidMessageDataPersonalSerializedCipheredHashed,
								int sizeOfSecureBidMessageDataPersonalSerialized,
								int sizeOfUserEmailSerialized, int sizeOfUserHomeAddressSerialized, 
								int sizeOfUserBankAccountNIBSerialized) {

		this.secureBidMessageDataSerialized = secureBidMessageDataSerialized;
		this.isSecureBidMessageDataSerialized = true;
		
		this.secureBidMessageDataSignature = null;
		this.sizeOfSecureBidMessageDataSignatureSerialized = sizeOfSecureBidMessageDataSignatureSerialized;
		this.sizeOfBidSerialized = sizeOfBidSerialized;
		this.sizeOfBidSerializedDigitalSigned = sizeOfBidSerializedDigitalSigned;
		this.sizeOfBidderUserClientIDSerialized = sizeOfBidderUserClientIDSerialized;
		
		this.secureBidMessageDataPersonal = null;
		this.sizeOfSecureBidMessageDataPersonalSerializedCipheredAndHashed = 
				sizeOfSecureBidMessageDataPersonalSerializedCipheredAndHashed;
		this.sizeOfSecureBidMessageDataPersonalSerializedCiphered = sizeOfSecureBidMessageDataPersonalSerializedCiphered;
		this.sizeOfSecureBidMessageDataPersonalSerializedCipheredHashed = sizeOfSecureBidMessageDataPersonalSerializedCipheredHashed;
		this.sizeOfSecureBidMessageDataPersonalSerialized = sizeOfSecureBidMessageDataPersonalSerialized;
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
	
	public int getSizeOfBidSerializedDigitalSigned() {
		return this.sizeOfBidSerializedDigitalSigned;
	}
	
	public int getSizeOfBidderUserClientIDSerialized() {
		return this.sizeOfBidderUserClientIDSerialized;
	}
	
	
	
	
	
	public SecureBidMessageDataPersonal getSecureBidMessageDataPersonal() {
		return this.secureBidMessageDataPersonal;
	}

	public int getSizeOfSecureBidMessageDataPersonalSerializedCipheredAndHashed() {
		return this.sizeOfSecureBidMessageDataPersonalSerializedCipheredAndHashed;
	}
	
	public int getSizeOfSecureBidMessageDataPersonalSerializedCiphered() {
		return this.sizeOfSecureBidMessageDataPersonalSerializedCiphered;
	}
	
	public int getSizeOfSecureBidMessageDataPersonalSerializedCipheredHashed() {
		return this.sizeOfSecureBidMessageDataPersonalSerializedCipheredHashed;
	}

	public int getSizeOfSecureBidMessageDataPersonalSerialized() {
		return this.sizeOfSecureBidMessageDataPersonalSerialized;
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
			
			byte[] secureBidMessageDataPersonalSerializedCipheredAndHashed = 
				   this.secureBidMessageDataPersonal.getSecureBidMessageDataPersonalSerializedCipheredAndHashed();
			
			
			int sizeOfSecureBidMessageDataSerialized = ( secureBidMessageDataSignatureSerialized.length +
														 secureBidMessageDataPersonalSerializedCipheredAndHashed.length );
			
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
			System.arraycopy(secureBidMessageDataPersonalSerializedCipheredAndHashed, 0,
							 this.secureBidMessageDataSerialized, serializationOffset,
							 secureBidMessageDataPersonalSerializedCipheredAndHashed.length);
			
			this.setIsSecureBidMessageDataSerialized(true);
			
		}
		
	}
	
	public void undoSecureBidMessageDataSerialization()
		   throws InvalidKeyException, NoSuchAlgorithmException, NoSuchProviderException,
		          NoSuchPaddingException, InvalidAlgorithmParameterException {
		
		if(this.isSecureBidMessageDataSerialized) {
			
			byte[] secureBidMessageDataSignatureSerialized = 
					new byte[this.sizeOfSecureBidMessageDataSignatureSerialized];
			
			byte[] secureBidMessageDataPersonalSerializedCipheredAndHashed = 
					new byte[this.sizeOfSecureBidMessageDataPersonalSerializedCipheredAndHashed];
			
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
							 secureBidMessageDataPersonalSerializedCipheredAndHashed, 0,
							 secureBidMessageDataPersonalSerializedCipheredAndHashed.length);
			
			this.secureBidMessageDataSignature = 
					new SecureBidMessageDataSignature(secureBidMessageDataSignatureSerialized,
													  this.sizeOfBidSerialized,
												      this.sizeOfBidSerializedDigitalSigned,
												      this.sizeOfSecureBidMessageDataSignatureSerialized);
			
			this.secureBidMessageDataSignature.buildSecureBidMessageDataSignatureReceived();
			
			
			this.secureBidMessageDataPersonal = 
					new SecureBidMessageDataPersonal(secureBidMessageDataPersonalSerializedCipheredAndHashed,
									 				 this.sizeOfSecureBidMessageDataPersonalSerializedCiphered,
													 this.sizeOfSecureBidMessageDataPersonalSerializedCipheredHashed,
													 this.sizeOfSecureBidMessageDataPersonalSerialized,
													 this.sizeOfUserEmailSerialized,
													 this.sizeOfUserHomeAddressSerialized,
													 this.sizeOfUserBankAccountNIBSerialized);
			
			this.secureBidMessageDataPersonal.buildSecureBidMessageDataPersonalReceived();
			
			
			this.setIsSecureBidMessageDataSerialized(false);
			
		}
		
	}
	
}