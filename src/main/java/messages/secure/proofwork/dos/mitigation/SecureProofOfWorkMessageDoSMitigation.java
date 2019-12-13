package main.java.messages.secure.proofwork.dos.mitigation;

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
import main.java.messages.secure.proofwork.components.SecureProofOfWorkMessageComponents;



public class SecureProofOfWorkMessageDoSMitigation {

	private SecureProofOfWorkMessageComponents secureProofOfWorkMessageComponents;
	
	private byte[] secureProofOfWorkMessageComponentsSerialized;
	
	private byte[] secureProofOfWorkMessageComponentsHashedForDoSMitigation;
	
	private byte[] secretHMACKeyForDoSMitigationInBytes;
	
	private boolean isSecureProofOfWorkMessageComponentsHashedForDoSMitigation;
	
	private boolean isSecureProofOfWorkMessageComponentsHashedForDoSMitigationVerified;
	
	private boolean isSecureProofOfWorkMessageComponentsHashedForDoSMitigationValid;
	
	private int sizeOfSecureProofOfWorkMessageComponentsHashedForDoSMitigation;
	
	
	public SecureProofOfWorkMessageDoSMitigation
	      (SecureProofOfWorkMessageComponents secureProofOfWorkMessageComponents) {
		
		this.secureProofOfWorkMessageComponents = secureProofOfWorkMessageComponents;
		
		this.secureProofOfWorkMessageComponentsSerialized = null;
		
		this.secureProofOfWorkMessageComponentsHashedForDoSMitigation = null;
		
		this.isSecureProofOfWorkMessageComponentsHashedForDoSMitigation = false;
		
		this.sizeOfSecureProofOfWorkMessageComponentsHashedForDoSMitigation = 0;
		
	}
	
	
	public SecureProofOfWorkMessageDoSMitigation(byte[] secureProofOfWorkMessageComponentsSerialized,
												 byte[] secureProofOfWorkMessageComponentsHashedForDoSMitigation,
									             byte[] secretHMACKeyForDoSMitigationInBytes) {
		
		this.secureProofOfWorkMessageComponents = null;
		
		this.secureProofOfWorkMessageComponentsSerialized = secureProofOfWorkMessageComponentsSerialized;
		
		this.secureProofOfWorkMessageComponentsHashedForDoSMitigation = 
				secureProofOfWorkMessageComponentsHashedForDoSMitigation;
		
		this.secretHMACKeyForDoSMitigationInBytes = 
				secretHMACKeyForDoSMitigationInBytes;
		
		this.isSecureProofOfWorkMessageComponentsHashedForDoSMitigation = true;
		
		this.sizeOfSecureProofOfWorkMessageComponentsHashedForDoSMitigation = 
				this.secureProofOfWorkMessageComponentsHashedForDoSMitigation.length;
		
	}




	public SecureProofOfWorkMessageComponents getSecureProofOfWorkMessageComponents() {
		return this.secureProofOfWorkMessageComponents;
	}
	
	public byte[] getSecureProofOfWorkMessageComponentsSerialized() {
		return this.secureProofOfWorkMessageComponentsSerialized;
	}
	
	public byte[] getSecureProofOfWorkMessageComponentsHashedForDoSMitigation() {
		return this.secureProofOfWorkMessageComponentsHashedForDoSMitigation;
	}
	
	public byte[] getSecretHMACKeyForDoSMitigationInBytes() {
		return this.secretHMACKeyForDoSMitigationInBytes;
	}
	
	public boolean getIsSecureProofOfWorkMessageComponentsHashedForDoSMitigation() {
		return this.isSecureProofOfWorkMessageComponentsHashedForDoSMitigation;
	}


	public void setIsSecureProofOfWorkMessageComponentsHashedForDoSMitigation
		  (boolean isSecureProofOfWorkMessageComponentsHashedForDoSMitigation) {
		
		this.isSecureProofOfWorkMessageComponentsHashedForDoSMitigation =
						isSecureProofOfWorkMessageComponentsHashedForDoSMitigation;
	
	}

	public boolean getIsSecureProofOfWorkMessageComponentsHashedForDoSMitigationVerified() {
		return this.isSecureProofOfWorkMessageComponentsHashedForDoSMitigationVerified;
	}


	public void setIsSecureProofOfWorkMessageComponentsHashedForDoSMitigationVerified
		  (boolean isSecureProofOfWorkMessageComponentsHashedForDoSMitigationVerified) {
		
		this.isSecureProofOfWorkMessageComponentsHashedForDoSMitigationVerified =
				isSecureProofOfWorkMessageComponentsHashedForDoSMitigationVerified;
	
	}
	
	public boolean getIsSecureProofOfWorkMessageComponentsHashedForDoSMitigationValid() {
		return this.isSecureProofOfWorkMessageComponentsHashedForDoSMitigationValid;
	}


	public void setIsSecureProofOfWorkMessageComponentsHashedForDoSMitigationValid
		  (boolean isSecureProofOfWorkMessageComponentsHashedForDoSMitigationValid) {
		
		this.isSecureProofOfWorkMessageComponentsHashedForDoSMitigationValid =
				isSecureProofOfWorkMessageComponentsHashedForDoSMitigationValid;
	
	}
	
	
	public int getSizeOfSecureProofOfWorkMessageComponentsHashedForDoSMitigation() {
		return this.sizeOfSecureProofOfWorkMessageComponentsHashedForDoSMitigation;
	}
	
	
	public void doHashOfSecureProofOfWorkMessageDoSMitigation()
		   throws NoSuchAlgorithmException, InvalidKeyException, NoSuchProviderException, 
		          NoSuchPaddingException, InvalidAlgorithmParameterException, SignatureException {
		
		if(!this.isSecureProofOfWorkMessageComponentsHashedForDoSMitigation) {
			
			this.secureProofOfWorkMessageComponents
				.doSecureProofOfWorkMessageComponentsSerialization();
			this.secureProofOfWorkMessageComponentsSerialized = 
					this.secureProofOfWorkMessageComponents
						.getSecureProofOfWorkMessageComponentsSerialized();
			
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
				mac.update(this.secureProofOfWorkMessageComponentsSerialized);
				
				// Performs the final operation of MAC Hash process over the Secure MessageComponents serialized
				// (applying the HMAC or CMAC operation)
				this.secureProofOfWorkMessageComponentsHashedForDoSMitigation = mac.doFinal();
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
			
			
			this.setIsSecureProofOfWorkMessageComponentsHashedForDoSMitigation(true);
			
		}
		
	}
	
	public boolean checkIfHashOfSecureProofOfWorkMessageDoSMitigationIsValid() throws NoSuchAlgorithmException {
		
		if(this.isSecureProofOfWorkMessageComponentsHashedForDoSMitigation) {
			
			byte[] secureProofOfWorkMessageComponentsSerializedHashedToCompare = 
				   this.secureProofOfWorkMessageComponentsSerialized;
			
			// Starts the MAC Hash process over the Secure MessageComponents serialized received (applying the HMAC or CMAC operation),
			// comparing it with Secure MessageComponents serialized hashed received (the MAC Hash process related to the Fast Secure MessageComponents Check)
			try {
			
				// The Initialization Vector and its Parameter's Specifications
				Mac mac = Mac.getInstance("HMacSHA256");
				SecretKeySpec keySpec = new SecretKeySpec(this.secretHMACKeyForDoSMitigationInBytes, "HMacSHA256");
				// The configuration, initialization and update of the MAC Hash process
				mac.init(keySpec);
				mac.update(secureProofOfWorkMessageComponentsSerializedHashedToCompare);
				
				// Performs the final operation of MAC Hash process over the Secure MessageComponents serialized
				// (applying the HMAC or CMAC operation)
				secureProofOfWorkMessageComponentsSerializedHashedToCompare = mac.doFinal();
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
			
			this.isSecureProofOfWorkMessageComponentsHashedForDoSMitigationValid = 
				  Arrays.areEqual(this.getSecureProofOfWorkMessageComponentsHashedForDoSMitigation(), 
						  		  secureProofOfWorkMessageComponentsSerializedHashedToCompare) ? 
										  true : false;
			
			if(!this.isSecureProofOfWorkMessageComponentsHashedForDoSMitigationValid) {
				System.err.println("The Fast Secure MessageComponents Check it's not valid:");
				System.err.println("- The Secure MessageComponents will be ignored!!!");
			}

			
			this.setIsSecureProofOfWorkMessageComponentsHashedForDoSMitigationVerified(true);
			this.setIsSecureProofOfWorkMessageComponentsHashedForDoSMitigation(false);
			
			
			return this.isSecureProofOfWorkMessageComponentsHashedForDoSMitigationValid;
			
		}
		
		return false;
	}
	
	
}
