package main.java.messages.secure.bid.components.key.exchange.integrity;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.bouncycastle.util.Arrays;

public class SecureBidMessageKeyExchangeIntegrity {

	private byte[] secureBidMessageKeyExchangeAgreementSerialized;
	
	private byte[] secureBidMessageKeyExchangeAgreementSerializedHashed;
	
	private boolean isSecureBidMessageKeyExchangeAgreementSerializedHashed;
	
	private boolean isSecureBidMessageKeyExchangeAgreementSerializedHashedVerified;
	
	private boolean isSecureBidMessageKeyExchangeAgreementSerializedHashedValid;
	
	
	public SecureBidMessageKeyExchangeIntegrity(byte[] secureBidMessageKeyExchangeAgreementSerialized) {
		
		this.secureBidMessageKeyExchangeAgreementSerialized = 
						secureBidMessageKeyExchangeAgreementSerialized;
		
		this.secureBidMessageKeyExchangeAgreementSerializedHashed = null;
		
		this.isSecureBidMessageKeyExchangeAgreementSerializedHashed = false;
		
		this.isSecureBidMessageKeyExchangeAgreementSerializedHashedVerified = false;
		
		this.isSecureBidMessageKeyExchangeAgreementSerializedHashedValid = false;
		
	}
	
	
	public SecureBidMessageKeyExchangeIntegrity(byte[] secureBidMessageKeyExchangeAgreementSerialized,
											    byte[] secureBidMessageKeyExchangeAgreementSerializedHashed) {
		
		this.secureBidMessageKeyExchangeAgreementSerialized = 
				secureBidMessageKeyExchangeAgreementSerialized;
		
		this.secureBidMessageKeyExchangeAgreementSerializedHashed = secureBidMessageKeyExchangeAgreementSerializedHashed;
		
		this.isSecureBidMessageKeyExchangeAgreementSerializedHashed = true;
		
		this.isSecureBidMessageKeyExchangeAgreementSerializedHashedVerified = false;
		
		this.isSecureBidMessageKeyExchangeAgreementSerializedHashedValid = false;
		
	}
	
	
	
	public byte[] getSecureBidMessageKeyExchangeAgreementSerialized() {
		return this.secureBidMessageKeyExchangeAgreementSerialized;
	}
	
	public byte[] getSecureBidMessageKeyExchangeAgreementSerializedHashed() {
		return this.secureBidMessageKeyExchangeAgreementSerializedHashed;
	}
	
	public boolean getIsSecureBidMessageKeyExchangeAgreementSerializedHashed() {
		return this.isSecureBidMessageKeyExchangeAgreementSerializedHashed;
	}
	
	public void setIsSecureBidMessageKeyExchangeAgreementSerializedHashed
		  (boolean isSecureBidMessageKeyExchangeAgreementSerializedHashed) {
		
		this.isSecureBidMessageKeyExchangeAgreementSerializedHashed = 
				isSecureBidMessageKeyExchangeAgreementSerializedHashed;
	
	}
	
	public boolean getIsSecureBidMessageKeyExchangeAgreementSerializedHashedVerified() {
		return this.isSecureBidMessageKeyExchangeAgreementSerializedHashedVerified;
	}
	
	public void setIsSecureBidMessageKeyExchangeAgreementSerializedHashedVerified
		  (boolean isSecureBidMessageKeyExchangeAgreementSerializedHashedVerified) {
		
		this.isSecureBidMessageKeyExchangeAgreementSerializedHashedVerified = 
				isSecureBidMessageKeyExchangeAgreementSerializedHashedVerified;
		
	}
	
	public boolean getIsSecureBidMessageKeyExchangeAgreementSerializedHashedValid() {
		return this.isSecureBidMessageKeyExchangeAgreementSerializedHashedValid;
	}
	
	public void setIsSecureBidMessageKeyExchangeAgreementSerializedHashedValid
		  (boolean isSecureBidMessageKeyExchangeAgreementSerializedHashedValid) {
		
		this.isSecureBidMessageKeyExchangeAgreementSerializedHashedValid = 
				isSecureBidMessageKeyExchangeAgreementSerializedHashedValid;
		
	}
	
	
	public void doHashOfSecureBidMessageKeyExchangeAgreementSerialized() throws NoSuchAlgorithmException {
		
		if(!this.isSecureBidMessageKeyExchangeAgreementSerializedHashed) {
			
			MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
			this.secureBidMessageKeyExchangeAgreementSerializedHashed = 
					messageDigest.digest(this.secureBidMessageKeyExchangeAgreementSerialized);
			
			this.setIsSecureBidMessageKeyExchangeAgreementSerializedHashed(true);
			
		}
		
	}
	
	public boolean checkIfHashOfSecureKeyExchangeAgreementSerializedIsValid() throws NoSuchAlgorithmException {
		
		if(this.isSecureBidMessageKeyExchangeAgreementSerializedHashed) {
			
			MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
			byte[] secureBidMessageKeyExchangeAgreementSerializedHashedToCompare = 
					messageDigest.digest(this.secureBidMessageKeyExchangeAgreementSerialized);
			
			this.isSecureBidMessageKeyExchangeAgreementSerializedHashedValid = 
								Arrays.areEqual(this.secureBidMessageKeyExchangeAgreementSerializedHashed, 
												secureBidMessageKeyExchangeAgreementSerializedHashedToCompare) ? 
													  true : false;
			
			
			this.setIsSecureBidMessageKeyExchangeAgreementSerializedHashedVerified(true);
			this.setIsSecureBidMessageKeyExchangeAgreementSerializedHashed(false);
			
			
			return this.isSecureBidMessageKeyExchangeAgreementSerializedHashedValid;
			
		}
		
		return false;
		
	}
	
}
