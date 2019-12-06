package main.java.messages.secure.bid.dos.mitigation;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import javax.crypto.NoSuchPaddingException;

import org.bouncycastle.util.Arrays;

import main.java.messages.secure.bid.components.SecureBidMessageComponents;

public class SecureBidMessageDoSMitigation {
	
	private SecureBidMessageComponents secureBidMessageComponents;
	
	private byte[] secureBidMessageComponentsSerialized;
	
	private byte[] secureBidMessageComponentsHashedForDoSMitigation;
	
	private boolean isSecureBidMessageComponentsHashedForDoSMitigation;
	
	private boolean isSecureBidMessageComponentsHashedForDoSMitigationVerified;
	
	private boolean isSecureBidMessageComponentsHashedForDoSMitigationValid;
	
	private int sizeOfSecureBidMessageComponentsHashedForDoSMitigation;
	
	
	public SecureBidMessageDoSMitigation(SecureBidMessageComponents secureBidMessageComponents) {
		
		this.secureBidMessageComponents = secureBidMessageComponents;
		
		this.secureBidMessageComponentsSerialized = null;
		
		this.secureBidMessageComponentsHashedForDoSMitigation = null;
		
		this.isSecureBidMessageComponentsHashedForDoSMitigation = false;
		
		this.sizeOfSecureBidMessageComponentsHashedForDoSMitigation = 0;
		
	}
	
	
	public SecureBidMessageDoSMitigation(byte[] secureBidMessageComponentsSerialized,
									     byte[] secureBidMessageComponentsHashedForDoSMitigation) {
		
		this.secureBidMessageComponents = null;
		
		this.secureBidMessageComponentsSerialized = secureBidMessageComponentsSerialized;
		
		this.secureBidMessageComponentsHashedForDoSMitigation = 
				secureBidMessageComponentsHashedForDoSMitigation;
		
		this.isSecureBidMessageComponentsHashedForDoSMitigation = true;
		
		this.sizeOfSecureBidMessageComponentsHashedForDoSMitigation = 
				this.secureBidMessageComponentsHashedForDoSMitigation.length;
		
	}




	public SecureBidMessageComponents getSecureBidMessageComponents() {
		return this.secureBidMessageComponents;
	}
	
	public byte[] getSecureBidMessageComponentsSerialized() {
		return this.secureBidMessageComponentsSerialized;
	}
	
	public byte[] getSecureBidMessageComponentsHashedForDoSMitigation() {
		return this.secureBidMessageComponentsHashedForDoSMitigation;
	}
	
	public boolean getIsSecureBidMessageComponentsHashedForDoSMitigation() {
		return this.isSecureBidMessageComponentsHashedForDoSMitigation;
	}


	public void setIsSecureBidMessageComponentsHashedForDoSMitigation
		  (boolean isSecureBidMessageComponentsHashedForDoSMitigation) {
		
		this.isSecureBidMessageComponentsHashedForDoSMitigation =
						isSecureBidMessageComponentsHashedForDoSMitigation;
	
	}

	public boolean getIsSecureBidMessageComponentsHashedForDoSMitigationVerified() {
		return this.isSecureBidMessageComponentsHashedForDoSMitigationVerified;
	}


	public void setIsSecureBidMessageComponentsHashedForDoSMitigationVerified
		  (boolean isSecureBidMessageComponentsHashedForDoSMitigationVerified) {
		
		this.isSecureBidMessageComponentsHashedForDoSMitigationVerified =
						isSecureBidMessageComponentsHashedForDoSMitigationVerified;
	
	}
	
	public boolean getIsSecureBidMessageComponentsHashedForDoSMitigationValid() {
		return this.isSecureBidMessageComponentsHashedForDoSMitigationValid;
	}


	public void setIsSecureBidMessageComponentsHashedForDoSMitigationValid
		  (boolean isSecureBidMessageComponentsHashedForDoSMitigationValid) {
		
		this.isSecureBidMessageComponentsHashedForDoSMitigationValid =
						isSecureBidMessageComponentsHashedForDoSMitigationValid;
	
	}
	
	
	public int getSizeOfSecureBidMessageComponentsHashedForDoSMitigation() {
		return this.sizeOfSecureBidMessageComponentsHashedForDoSMitigation;
	}
	
	
	public void doHashOfSecureBidMessageDoSMitigation()
		   throws NoSuchAlgorithmException, InvalidKeyException, NoSuchProviderException, 
		          NoSuchPaddingException, InvalidAlgorithmParameterException {
		
		if(!this.isSecureBidMessageComponentsHashedForDoSMitigation) {
			
			this.secureBidMessageComponents.doSecureBidMessageComponentsSerialization();
			this.secureBidMessageComponentsSerialized = 
					this.secureBidMessageComponents.getSecureBidMessageComponentsSerialized();
			
			MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
			this.secureBidMessageComponentsHashedForDoSMitigation = 
					messageDigest.digest(secureBidMessageComponentsSerialized);
			
			this.sizeOfSecureBidMessageComponentsHashedForDoSMitigation = 
					this.secureBidMessageComponentsHashedForDoSMitigation.length;
			
			this.setIsSecureBidMessageComponentsHashedForDoSMitigation(true);
			
		}
		
	}
	
	public boolean checkIfHashOfSecureBidMessageDoSMitigationIsValid() throws NoSuchAlgorithmException {
		
		if(this.isSecureBidMessageComponentsHashedForDoSMitigation) {
			
			MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
			byte[] bidSerializedHashedToCompare = messageDigest.digest(this.secureBidMessageComponentsSerialized);
			
			this.isSecureBidMessageComponentsHashedForDoSMitigationValid = 
								Arrays.areEqual(this.secureBidMessageComponentsHashedForDoSMitigation, 
								 			    bidSerializedHashedToCompare) ? 
												true : false;
			
			
			this.setIsSecureBidMessageComponentsHashedForDoSMitigationVerified(true);
			this.setIsSecureBidMessageComponentsHashedForDoSMitigation(false);
			
			
			return this.isSecureBidMessageComponentsHashedForDoSMitigationValid;
			
		}
		
		return false;
	}
}
