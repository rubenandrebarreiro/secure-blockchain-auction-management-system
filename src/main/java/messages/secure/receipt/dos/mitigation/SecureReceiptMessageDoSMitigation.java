package main.java.messages.secure.receipt.dos.mitigation;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.security.SignatureException;

import javax.crypto.Mac;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import org.bouncycastle.util.Arrays;

import main.java.common.utils.CommonUtils;
import main.java.messages.secure.receipt.components.SecureReceiptMessageComponents;

public class SecureReceiptMessageDoSMitigation {

	
	private SecureReceiptMessageComponents secureReceiptMessageComponents;
	
	
	private byte[] secureReceiptMessageComponentsSerialized;
	
	private byte[] secureReceiptMessageComponentsHashedForDoSMitigation;
	
	
	private byte[] secretHMACKeyForDoSMitigationInBytes;
	
	
	private boolean isSecureReceiptMessageComponentsHashedForDoSMitigation;
	
	private boolean isSecureReceiptMessageComponentsHashedForDoSMitigationVerified;
	
	private boolean isSecureReceiptMessageComponentsHashedForDoSMitigationValid;
	
	private int sizeOfSecureReceiptMessageComponentsHashedForDoSMitigation;
	
	
	
	public SecureReceiptMessageDoSMitigation
		  (SecureReceiptMessageComponents secureReceiptMessageComponents) {
	
		this.secureReceiptMessageComponents = secureReceiptMessageComponents;
		
		this.secureReceiptMessageComponentsSerialized = null;
		
		this.secureReceiptMessageComponentsHashedForDoSMitigation = null;
		
		this.isSecureReceiptMessageComponentsHashedForDoSMitigation = false;
		
		this.sizeOfSecureReceiptMessageComponentsHashedForDoSMitigation = 0;
		
	}


	
	public SecureReceiptMessageDoSMitigation(byte[] secureReceiptMessageComponentsSerialized,
											 byte[] secureReceiptMessageComponentsHashedForDoSMitigation,
								             byte[] secretHMACKeyForDoSMitigationInBytes) {
	
		this.secureReceiptMessageComponents = null;
		
		this.secureReceiptMessageComponentsSerialized = secureReceiptMessageComponentsSerialized;
		
		this.secureReceiptMessageComponentsHashedForDoSMitigation = 
				secureReceiptMessageComponentsHashedForDoSMitigation;
		
		this.secretHMACKeyForDoSMitigationInBytes = 
				secretHMACKeyForDoSMitigationInBytes;
		
		this.isSecureReceiptMessageComponentsHashedForDoSMitigation = true;
		
		this.sizeOfSecureReceiptMessageComponentsHashedForDoSMitigation = 
				this.secureReceiptMessageComponentsHashedForDoSMitigation.length;
		
	}

	
	
	public SecureReceiptMessageComponents getSecureReceiptMessageComponents() {
		return this.secureReceiptMessageComponents;
	}
	
	public byte[] getSecureReceiptMessageComponentsSerialized() {
		return this.secureReceiptMessageComponentsSerialized;
	}
	
	public byte[] getSecureReceiptMessageComponentsHashedForDoSMitigation() {
		return this.secureReceiptMessageComponentsHashedForDoSMitigation;
	}
	
	
	
	public byte[] getSecretHMACKeyForDoSMitigationInBytes() {
		return this.secretHMACKeyForDoSMitigationInBytes;
	}
	
	
	
	public boolean getIsSecureReceiptMessageComponentsHashedForDoSMitigation() {
		return this.isSecureReceiptMessageComponentsHashedForDoSMitigation;
	}


	public void setIsSecureReceiptMessageComponentsHashedForDoSMitigation
		  (boolean isSecureReceiptMessageComponentsHashedForDoSMitigation) {
		
		this.isSecureReceiptMessageComponentsHashedForDoSMitigation =
						isSecureReceiptMessageComponentsHashedForDoSMitigation;
	
	}

	public boolean getIsSecureReceiptMessageComponentsHashedForDoSMitigationVerified() {
		return this.isSecureReceiptMessageComponentsHashedForDoSMitigationVerified;
	}


	public void setIsSecureReceiptMessageComponentsHashedForDoSMitigationVerified
		  (boolean isSecureReceiptMessageComponentsHashedForDoSMitigationVerified) {
		
		this.isSecureReceiptMessageComponentsHashedForDoSMitigationVerified =
				isSecureReceiptMessageComponentsHashedForDoSMitigationVerified;
	
	}
	
	public boolean getIsSecureReceiptMessageComponentsHashedForDoSMitigationValid() {
		return this.isSecureReceiptMessageComponentsHashedForDoSMitigationValid;
	}


	public void setIsSecureReceiptMessageComponentsHashedForDoSMitigationValid
		  (boolean isSecureReceiptMessageComponentsHashedForDoSMitigationValid) {
		
		this.isSecureReceiptMessageComponentsHashedForDoSMitigationValid =
				isSecureReceiptMessageComponentsHashedForDoSMitigationValid;
	
	}
	
	
	public int getSizeOfSecureReceiptMessageComponentsHashedForDoSMitigation() {
		return this.sizeOfSecureReceiptMessageComponentsHashedForDoSMitigation;
	}
	
	
	
	public void doHashOfSecureReceiptMessageDoSMitigation()
		   throws NoSuchAlgorithmException, InvalidKeyException, NoSuchProviderException, 
			          NoSuchPaddingException, InvalidAlgorithmParameterException, SignatureException {
			
		if(!this.isSecureReceiptMessageComponentsHashedForDoSMitigation) {
				
			this.secureReceiptMessageComponents
					.doSecureReceiptMessageComponentsSerialization();
			this.secureReceiptMessageComponentsSerialized = 
						this.secureReceiptMessageComponents
							.getSecureReceiptMessageComponentsSerialized();
				
			// Starts the MAC Hash process over the Secure MessageComponents serialized (applying the HMAC or CMAC operation),
			// before the sending of the final concatenation of it with Secure MessageComponents serialized
			try {
				
				// The Initialization Vector and its Parameter's Specifications
				Key secretHMACKeyForDoSMitigationMACKey = CommonUtils.createKeyForAES(256, new SecureRandom());
				this.secretHMACKeyForDoSMitigationInBytes = secretHMACKeyForDoSMitigationMACKey.getEncoded();
				Mac mac = Mac.getInstance("HMacSHA256");
				SecretKeySpec keySpec = new SecretKeySpec(this.secretHMACKeyForDoSMitigationInBytes, "HMacSHA256");
				// The configuration, initialization and update of the MAC Hash process
				mac.init(keySpec);
				mac.update(this.secureReceiptMessageComponentsSerialized);
				
				// Performs the final operation of MAC Hash process over the Secure MessageComponents serialized
				// (applying the HMAC or CMAC operation)
				this.secureReceiptMessageComponentsHashedForDoSMitigation = mac.doFinal();
			}
			catch (NoSuchAlgorithmException noSuchAlgorithmException) {
				System.err.println("Error occurred during the Hashing Function over the Secure MessageComponents's Attributes:");
				System.err.println("- Cryptographic Algorithm not found!!!");
				noSuchAlgorithmException.printStackTrace();
			}
			catch (InvalidKeyException invalidKeyException) {
				System.err.println("Error occurred during the Hashing Function over the Secure MessageComponents's Attributes:");
				System.err.println("- Invalid Secret Key!!!");
				invalidKeyException.printStackTrace();
			}
			
			
			this.setIsSecureReceiptMessageComponentsHashedForDoSMitigation(true);
			
		}
		
	}
	
	
	public boolean checkIfHashOfSecureReceiptMessageDoSMitigationIsValid() throws NoSuchAlgorithmException {
		
		if(this.isSecureReceiptMessageComponentsHashedForDoSMitigation) {
			
			byte[] secureReceiptMessageComponentsSerializedHashedToCompare = 
				   this.secureReceiptMessageComponentsSerialized;
			
			// Starts the MAC Hash process over the Secure MessageComponents serialized received (applying the HMAC or CMAC operation),
			// comparing it with Secure MessageComponents serialized hashed received (the MAC Hash process related to the Fast Secure MessageComponents Check)
			try {
			
				// The Initialization Vector and its Parameter's Specifications
				Mac mac = Mac.getInstance("HMacSHA256");
				SecretKeySpec keySpec = new SecretKeySpec(this.secretHMACKeyForDoSMitigationInBytes, "HMacSHA256");
				// The configuration, initialization and update of the MAC Hash process
				mac.init(keySpec);
				mac.update(secureReceiptMessageComponentsSerializedHashedToCompare);
				
				// Performs the final operation of MAC Hash process over the Secure MessageComponents serialized
				// (applying the HMAC or CMAC operation)
				secureReceiptMessageComponentsSerializedHashedToCompare = mac.doFinal();
			}
			catch (NoSuchAlgorithmException noSuchAlgorithmException) {
				System.err.println("Error occurred during the Hashing Function over the Secure MessageComponents's Attributes:");
				System.err.println("- Cryptographic Algorithm not found!!!");
				noSuchAlgorithmException.printStackTrace();
			}
			catch (InvalidKeyException invalidKeyException) {
				System.err.println("Error occurred during the Hashing Function over the Secure MessageComponents's Attributes:");
				System.err.println("- Invalid Secret Key!!!");
				invalidKeyException.printStackTrace();
			}
			
			this.isSecureReceiptMessageComponentsHashedForDoSMitigationValid = 
				  Arrays.areEqual(this.getSecureReceiptMessageComponentsHashedForDoSMitigation(), 
						  		  secureReceiptMessageComponentsSerializedHashedToCompare) ? 
										  true : false;
			
			if(!this.isSecureReceiptMessageComponentsHashedForDoSMitigationValid) {
				System.err.println("The Fast Secure MessageComponents Check it's not valid:");
				System.err.println("- The Secure MessageComponents will be ignored!!!");
			}

			
			this.setIsSecureReceiptMessageComponentsHashedForDoSMitigationVerified(true);
			this.setIsSecureReceiptMessageComponentsHashedForDoSMitigation(false);
			
			
			return this.isSecureReceiptMessageComponentsHashedForDoSMitigationValid;
			
		}
		
		return false;
	}
	
}
