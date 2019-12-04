package main.java.messages.secure.key.exchange.integrity;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.bouncycastle.util.Arrays;

public class SecureKeyExchangeMessageIntegrity {

	private byte[] secureKeyExchangeAgreementSerialized;
	
	private byte[] secureKeyExchangeAgreementSerializedHashed;
	
	private boolean isSecureKeyExchangeAgreementSerializedHashed;
	
	private boolean isSecureKeyExchangeAgreementSerializedHashedVerified;
	
	private boolean isSecureKeyExchangeAgreementSerializedHashedValid;
	
	
	public SecureKeyExchangeMessageIntegrity(byte[] secureKeyExchangeAgreementSerialized) {
		
		this.secureKeyExchangeAgreementSerialized = secureKeyExchangeAgreementSerialized;
		
		this.secureKeyExchangeAgreementSerializedHashed = null;
		
		this.isSecureKeyExchangeAgreementSerializedHashed = false;
		
		this.isSecureKeyExchangeAgreementSerializedHashedVerified = false;
		
		this.isSecureKeyExchangeAgreementSerializedHashedValid = false;
		
	}
	
	
	public SecureKeyExchangeMessageIntegrity(byte[] secureKeyExchangeAgreementSerialized,
											 byte[] secureKeyExchangeAgreementSerializedHashed) {
		
		this.secureKeyExchangeAgreementSerialized = secureKeyExchangeAgreementSerialized;
		
		this.secureKeyExchangeAgreementSerializedHashed = secureKeyExchangeAgreementSerializedHashed;
		
		this.isSecureKeyExchangeAgreementSerializedHashed = true;
		
		this.isSecureKeyExchangeAgreementSerializedHashedVerified = false;
		
		this.isSecureKeyExchangeAgreementSerializedHashedValid = false;
		
	}
	
	
	
	public byte[] getSecureKeyExchangeAgreementSerialized() {
		return this.secureKeyExchangeAgreementSerialized;
	}
	
	public byte[] getSecureKeyExchangeAgreementSerializedHashed() {
		return this.secureKeyExchangeAgreementSerializedHashed;
	}
	
	public boolean getIsSecureKeyExchangeAgreementSerializedHashed() {
		return this.isSecureKeyExchangeAgreementSerializedHashed;
	}
	
	public void setIsSecureKeyExchangeAgreementSerializedHashed
		  (boolean isSecureKeyExchangeAgreementSerializedHashed) {
		
		this.isSecureKeyExchangeAgreementSerializedHashed = 
				isSecureKeyExchangeAgreementSerializedHashed;
	
	}
	
	public boolean getIsSecureKeyExchangeAgreementSerializedHashedVerified() {
		return this.isSecureKeyExchangeAgreementSerializedHashedVerified;
	}
	
	public void setIsSecureKeyExchangeAgreementSerializedHashedVerified
		  (boolean isSecureKeyExchangeAgreementSerializedHashedVerified) {
		
		this.isSecureKeyExchangeAgreementSerializedHashedVerified = 
				isSecureKeyExchangeAgreementSerializedHashedVerified;
		
	}
	
	public boolean getIsSecureKeyExchangeAgreementSerializedHashedValid() {
		return this.isSecureKeyExchangeAgreementSerializedHashedValid;
	}
	
	public void setIsSecureKeyExchangeAgreementSerializedHashedValid
		  (boolean isSecureKeyExchangeAgreementSerializedHashedValid) {
		
		this.isSecureKeyExchangeAgreementSerializedHashedValid = 
				isSecureKeyExchangeAgreementSerializedHashedValid;
		
	}
	
	
	public void doHashOfSecureKeyExchangeAgreementSerialized() throws NoSuchAlgorithmException {
		
		if(!this.isSecureKeyExchangeAgreementSerializedHashed) {
			
			MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
			this.secureKeyExchangeAgreementSerializedHashed = 
					messageDigest.digest(this.secureKeyExchangeAgreementSerialized);
			
			this.setIsSecureKeyExchangeAgreementSerializedHashed(true);
			
		}
		
	}
	
	public boolean checkIfHashOfSecureKeyExchangeAgreementSerializedIsValid() throws NoSuchAlgorithmException {
		
		if(this.isSecureKeyExchangeAgreementSerializedHashed) {
			
			MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
			byte[] secureKeyExchangeAgreementSerializedHashedToCompare = 
					messageDigest.digest(this.secureKeyExchangeAgreementSerialized);
			
			this.isSecureKeyExchangeAgreementSerializedHashedValid = 
								Arrays.areEqual(this.secureKeyExchangeAgreementSerializedHashed, 
												secureKeyExchangeAgreementSerializedHashedToCompare) ? 
													  true : false;
			
			
			this.setIsSecureKeyExchangeAgreementSerializedHashedVerified(true);
			this.setIsSecureKeyExchangeAgreementSerializedHashed(false);
			
			
			return this.isSecureKeyExchangeAgreementSerializedHashedValid;
			
		}
		
		return false;
		
	}
	
}
