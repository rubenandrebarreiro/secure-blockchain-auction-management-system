package main.java.messages.secure.bid.data;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import javax.crypto.NoSuchPaddingException;

import main.java.messages.secure.bid.data.personal.SecureBidMessageDataPersonal;
import main.java.messages.secure.bid.data.signature.SecureBidMessageDataSignature;

public class SecureBidMessageData {

	private SecureBidMessageDataSignature secureBidMessageDataSignature;
	
	private int sizeOfSecureBidMessageDataSignatureSerialized;
	

	private SecureBidMessageDataPersonal secureBidMessageDataPersonal;
	
	private int sizeOfSecureBidMessageDataPersonalSerializedAndHashedCiphered;
	
	
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
							    int sizeOfBidderUserClientID,
								int sizeOfSecureBidMessageDataPersonalSerializedAndHashedCiphered,
								int sizeOfSecureBidMessageDataPersonalSerialized,
								int sizeOfSecureBidMessageDataPersonalSerializedHashed,
								int sizeOfUserEmailSerialized, int sizeOfUserHomeAddress, 
								int sizeOfUserBankAccountNIB) {

		this.secureBidMessageDataSerialized = secureBidMessageDataSerialized;
		this.isSecureBidMessageDataSerialized = true;
		
		this.secureBidMessageDataSignature = null;
		this.sizeOfSecureBidMessageDataSignatureSerialized = sizeOfSecureBidMessageDataSignatureSerialized;
		
		this.secureBidMessageDataPersonal = null;
		this.sizeOfSecureBidMessageDataPersonalSerializedAndHashedCiphered = 
				sizeOfSecureBidMessageDataPersonalSerializedAndHashedCiphered;
		
	}
	
	
	
	public SecureBidMessageDataSignature getSecureBidMessageDataSignature() {
		return this.secureBidMessageDataSignature;
	}	

	public int getSizeOfSecureBidMessageDataSignatureSerialized() {
		return this.sizeOfSecureBidMessageDataSignatureSerialized;
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
			
			this.secureBidMessageDataSignature.buildSecureBidMessageSignatureToSend();
			
			byte[] secureBidMessageDataSignatureSerialized = 
				   this.secureBidMessageDataSignature.getBidDigitalSigned();
			
			this.secureBidMessageDataPersonal = null; //TODO
			
			byte[] secureBidMessageDataPersonalSerialized = 
				   this.secureBidMessageDataPersonal.getSecureBidMessageDataPersonalSerializedAndHashedCiphered();
			
			
			
			
			this.setIsSecureBidMessageDataSerialized(true);
			
		}
		
	}
	
	public void undoSecureBidMessageDataSerialization() {
		
		if(!this.isSecureBidMessageDataSerialized) {
			
			
			
			this.setIsSecureBidMessageDataSerialized(false);
			
		}
		
	}
	
}