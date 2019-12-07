package main.java.messages.secure.bid.dos.mitigation;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;

import javax.crypto.Mac;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.encoders.Base64;

import main.java.common.utils.CommonUtils;
import main.java.messages.secure.bid.components.SecureBidMessageComponents;

public class SecureBidMessageDoSMitigation {
	
	private SecureBidMessageComponents secureBidMessageComponents;
	
	private byte[] secureBidMessageComponentsSerialized;
	
	private byte[] secureBidMessageComponentsHashedForDoSMitigation;
	
	private byte[] secretHMACKeyForDoSMitigationInBytes;
	
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
									     byte[] secureBidMessageComponentsHashedForDoSMitigation,
									     byte[] secretHMACKeyForDoSMitigationInBytes) {
		
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
	
	public byte[] getSecretHMACKeyForDoSMitigationInBytes() {
		return this.secretHMACKeyForDoSMitigationInBytes;
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
			
			// Starts the MAC Hash process over the Secure Message serialized (applying the HMAC or CMAC operation),
			// before the sending of the final concatenation of it with Secure Message serialized
			try {
				
				// The Initialization Vector and its Parameter's Specifications
				Key secretHMACKeyForDoSMitigationMACKey = CommonUtils.createKeyForAES(256, new SecureRandom());
				this.secretHMACKeyForDoSMitigationInBytes = Base64.decode(secretHMACKeyForDoSMitigationMACKey.getEncoded());
				Mac mac = Mac.getInstance("HMacSHA256");
				SecretKeySpec keySpec = new SecretKeySpec(this.secretHMACKeyForDoSMitigationInBytes, "HMacSHA256");
				// The configuration, initialization and update of the MAC Hash process
				mac.init(keySpec);
				mac.update(this.secureBidMessageComponentsSerialized);
				
				// Performs the final operation of MAC Hash process over the Secure Message serialized
				// (applying the HMAC or CMAC operation)
				this.secureBidMessageComponentsHashedForDoSMitigation = mac.doFinal();
			}
			catch (NoSuchAlgorithmException noSuchAlgorithmException) {
				System.err.println("Error occurred during the Hashing Function over the Secure Message's Attributes:");
				System.err.println("- Cryptographic Algorithm not found!!!");
				noSuchAlgorithmException.printStackTrace();
			}
			catch (InvalidKeyException invalidKeyException) {
				System.err.println("Error occurred during the Hashing Function over the Secure Message's Attributes:");
				System.err.println("- Invalid Secret Key!!!");
				invalidKeyException.printStackTrace();
			}
			
			
			this.setIsSecureBidMessageComponentsHashedForDoSMitigation(true);
			
		}
		
	}
	
	public boolean checkIfHashOfSecureBidMessageDoSMitigationIsValid() throws NoSuchAlgorithmException {
		
		if(this.isSecureBidMessageComponentsHashedForDoSMitigation) {
			
			byte[] secureBidMessageComponentsSerializedToCompare = this.secureBidMessageComponentsSerialized;
			
			// Starts the MAC Hash process over the Secure Message serialized received (applying the HMAC or CMAC operation),
			// comparing it with Secure Message serialized hashed received (the MAC Hash process related to the Fast Secure Message Check)
			try {
			
				// The Initialization Vector and its Parameter's Specifications
				Mac mac = Mac.getInstance("HMacSHA256");
				SecretKeySpec keySpec = new SecretKeySpec(this.secretHMACKeyForDoSMitigationInBytes, "HMacSHA256");
				// The configuration, initialization and update of the MAC Hash process
				mac.init(keySpec);
				mac.update(secureBidMessageComponentsSerializedToCompare);
				
				// Performs the final operation of MAC Hash process over the Secure Message serialized
				// (applying the HMAC or CMAC operation)
				secureBidMessageComponentsSerializedToCompare = mac.doFinal();
			}
			catch (NoSuchAlgorithmException noSuchAlgorithmException) {
				System.err.println("Error occurred during the Hashing Function over the Secure Message's Attributes:");
				System.err.println("- Cryptographic Algorithm not found!!!");
				noSuchAlgorithmException.printStackTrace();
			}
			catch (InvalidKeyException invalidKeyException) {
				System.err.println("Error occurred during the Hashing Function over the Secure Message's Attributes:");
				System.err.println("- Invalid Secret Key!!!");
				invalidKeyException.printStackTrace();
			}
			
			this.isSecureBidMessageComponentsHashedForDoSMitigationValid = 
									  Arrays.areEqual(this.getSecureBidMessageComponentsHashedForDoSMitigation(), 
											  		  secureBidMessageComponentsSerializedToCompare) ? 
															  true : false;
			
			if(!this.isSecureBidMessageComponentsHashedForDoSMitigationValid) {
				System.err.println("The Fast Secure Message Check it's not valid:");
				System.err.println("- The Secure Message will be ignored!!!");
			}

			
			this.setIsSecureBidMessageComponentsHashedForDoSMitigationVerified(true);
			this.setIsSecureBidMessageComponentsHashedForDoSMitigation(false);
			
			
			return this.isSecureBidMessageComponentsHashedForDoSMitigationValid;
			
		}
		
		return false;
	}
}
