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
import main.java.messages.secure.proofwork.components.solvedblock.SecureProofOfWorkMessageComponentsSolvedBlock;

public class SecureProofOfWorkMessageDoSMitigation {

	private SecureProofOfWorkMessageComponentsSolvedBlock secureProofOfWorkMessageComponentsSolvedBlock;
	
	private byte[] secureProofOfWorkMessageComponentsSolvedBlockSerialized;
	
	private byte[] secureProofOfWorkMessageComponentsSolvedBlockHashedForDoSMitigation;
	
	private byte[] secretHMACKeyForDoSMitigationInBytes;
	
	private boolean isSecureProofOfWorkMessageComponentsSolvedBlockHashedForDoSMitigation;
	
	private boolean isSecureProofOfWorkMessageComponentsSolvedBlockHashedForDoSMitigationVerified;
	
	private boolean isSecureProofOfWorkMessageComponentsSolvedBlockHashedForDoSMitigationValid;
	
	private int sizeOfSecureProofOfWorkMessageComponentsSolvedBlockHashedForDoSMitigation;
	
	
	public SecureProofOfWorkMessageDoSMitigation
	      (SecureProofOfWorkMessageComponentsSolvedBlock secureProofOfWorkMessageComponentsSolvedBlock) {
		
		this.secureProofOfWorkMessageComponentsSolvedBlock = secureProofOfWorkMessageComponentsSolvedBlock;
		
		this.secureProofOfWorkMessageComponentsSolvedBlockSerialized = null;
		
		this.secureProofOfWorkMessageComponentsSolvedBlockHashedForDoSMitigation = null;
		
		this.isSecureProofOfWorkMessageComponentsSolvedBlockHashedForDoSMitigation = false;
		
		this.sizeOfSecureProofOfWorkMessageComponentsSolvedBlockHashedForDoSMitigation = 0;
		
	}
	
	
	public SecureProofOfWorkMessageDoSMitigation(byte[] secureProofOfWorkMessageComponentsSolvedBlockSerialized,
												 byte[] secureProofOfWorkMessageComponentsSolvedBlockHashedForDoSMitigation,
									             byte[] secretHMACKeyForDoSMitigationInBytes) {
		
		this.secureProofOfWorkMessageComponentsSolvedBlock = null;
		
		this.secureProofOfWorkMessageComponentsSolvedBlockSerialized = secureProofOfWorkMessageComponentsSolvedBlockSerialized;
		
		this.secureProofOfWorkMessageComponentsSolvedBlockHashedForDoSMitigation = 
				secureProofOfWorkMessageComponentsSolvedBlockHashedForDoSMitigation;
		
		this.secretHMACKeyForDoSMitigationInBytes = 
				secretHMACKeyForDoSMitigationInBytes;
		
		this.isSecureProofOfWorkMessageComponentsSolvedBlockHashedForDoSMitigation = true;
		
		this.sizeOfSecureProofOfWorkMessageComponentsSolvedBlockHashedForDoSMitigation = 
				this.secureProofOfWorkMessageComponentsSolvedBlockHashedForDoSMitigation.length;
		
	}




	public SecureProofOfWorkMessageComponentsSolvedBlock getSecureProofOfWorkMessageComponentsSolvedBlock() {
		return this.secureProofOfWorkMessageComponentsSolvedBlock;
	}
	
	public byte[] getSecureProofOfWorkMessageComponentsSolvedBlockSerialized() {
		return this.secureProofOfWorkMessageComponentsSolvedBlockSerialized;
	}
	
	public byte[] getSecureProofOfWorkMessageComponentsSolvedBlockHashedForDoSMitigation() {
		return this.secureProofOfWorkMessageComponentsSolvedBlockHashedForDoSMitigation;
	}
	
	public byte[] getSecretHMACKeyForDoSMitigationInBytes() {
		return this.secretHMACKeyForDoSMitigationInBytes;
	}
	
	public boolean getIsSecureProofOfWorkMessageComponentsSolvedBlockHashedForDoSMitigation() {
		return this.isSecureProofOfWorkMessageComponentsSolvedBlockHashedForDoSMitigation;
	}


	public void setIsSecureProofOfWorkMessageComponentsSolvedBlockHashedForDoSMitigation
		  (boolean isSecureProofOfWorkMessageComponentsSolvedBlockHashedForDoSMitigation) {
		
		this.isSecureProofOfWorkMessageComponentsSolvedBlockHashedForDoSMitigation =
						isSecureProofOfWorkMessageComponentsSolvedBlockHashedForDoSMitigation;
	
	}

	public boolean getIsSecureProofOfWorkMessageComponentsSolvedBlockHashedForDoSMitigationVerified() {
		return this.isSecureProofOfWorkMessageComponentsSolvedBlockHashedForDoSMitigationVerified;
	}


	public void setIsSecureProofOfWorkMessageComponentsSolvedBlockHashedForDoSMitigationVerified
		  (boolean isSecureProofOfWorkMessageComponentsSolvedBlockHashedForDoSMitigationVerified) {
		
		this.isSecureProofOfWorkMessageComponentsSolvedBlockHashedForDoSMitigationVerified =
				isSecureProofOfWorkMessageComponentsSolvedBlockHashedForDoSMitigationVerified;
	
	}
	
	public boolean getIsSecureProofOfWorkMessageComponentsSolvedBlockHashedForDoSMitigationValid() {
		return this.isSecureProofOfWorkMessageComponentsSolvedBlockHashedForDoSMitigationValid;
	}


	public void setIsSecureProofOfWorkMessageComponentsSolvedBlockHashedForDoSMitigationValid
		  (boolean isSecureProofOfWorkMessageComponentsSolvedBlockHashedForDoSMitigationValid) {
		
		this.isSecureProofOfWorkMessageComponentsSolvedBlockHashedForDoSMitigationValid =
				isSecureProofOfWorkMessageComponentsSolvedBlockHashedForDoSMitigationValid;
	
	}
	
	
	public int getSizeOfSecureProofOfWorkMessageComponentsSolvedBlockHashedForDoSMitigation() {
		return this.sizeOfSecureProofOfWorkMessageComponentsSolvedBlockHashedForDoSMitigation;
	}
	
	
	public void doHashOfSecureProofOfWorkMessageDoSMitigation()
		   throws NoSuchAlgorithmException, InvalidKeyException, NoSuchProviderException, 
		          NoSuchPaddingException, InvalidAlgorithmParameterException, SignatureException {
		
		if(!this.isSecureProofOfWorkMessageComponentsSolvedBlockHashedForDoSMitigation) {
			
			this.secureProofOfWorkMessageComponentsSolvedBlock
				.doSecureProofOfWorkMessageComponentsSolvedBlockSerialization();
			this.secureProofOfWorkMessageComponentsSolvedBlockSerialized = 
					this.secureProofOfWorkMessageComponentsSolvedBlock
						.getSecureProofOfWorkMessageComponentsSolvedBlockSerialized();
			
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
				mac.update(this.secureProofOfWorkMessageComponentsSolvedBlockSerialized);
				
				// Performs the final operation of MAC Hash process over the Secure MessageComponents serialized
				// (applying the HMAC or CMAC operation)
				this.secureProofOfWorkMessageComponentsSolvedBlockHashedForDoSMitigation = mac.doFinal();
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
			
			
			this.setIsSecureProofOfWorkMessageComponentsSolvedBlockHashedForDoSMitigation(true);
			
		}
		
	}
	
	public boolean checkIfHashOfSecureProofOfWorkMessageDoSMitigationIsValid() throws NoSuchAlgorithmException {
		
		if(this.isSecureProofOfWorkMessageComponentsSolvedBlockHashedForDoSMitigation) {
			
			byte[] secureProofOfWorkMessageComponentsSolvedBlockSerializedHashedToCompare = 
				   this.secureProofOfWorkMessageComponentsSolvedBlockSerialized;
			
			// Starts the MAC Hash process over the Secure MessageComponents serialized received (applying the HMAC or CMAC operation),
			// comparing it with Secure MessageComponents serialized hashed received (the MAC Hash process related to the Fast Secure MessageComponents Check)
			try {
			
				// The Initialization Vector and its Parameter's Specifications
				Mac mac = Mac.getInstance("HMacSHA256");
				SecretKeySpec keySpec = new SecretKeySpec(this.secretHMACKeyForDoSMitigationInBytes, "HMacSHA256");
				// The configuration, initialization and update of the MAC Hash process
				mac.init(keySpec);
				mac.update(secureProofOfWorkMessageComponentsSolvedBlockSerializedHashedToCompare);
				
				// Performs the final operation of MAC Hash process over the Secure MessageComponents serialized
				// (applying the HMAC or CMAC operation)
				secureProofOfWorkMessageComponentsSolvedBlockSerializedHashedToCompare = mac.doFinal();
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
			
			this.isSecureProofOfWorkMessageComponentsSolvedBlockHashedForDoSMitigationValid = 
				  Arrays.areEqual(this.getSecureProofOfWorkMessageComponentsSolvedBlockHashedForDoSMitigation(), 
						  		  secureProofOfWorkMessageComponentsSolvedBlockSerializedHashedToCompare) ? 
										  true : false;
			
			if(!this.isSecureProofOfWorkMessageComponentsSolvedBlockHashedForDoSMitigationValid) {
				System.err.println("The Fast Secure MessageComponents Check it's not valid:");
				System.err.println("- The Secure MessageComponents will be ignored!!!");
			}

			
			this.setIsSecureProofOfWorkMessageComponentsSolvedBlockHashedForDoSMitigationVerified(true);
			this.setIsSecureProofOfWorkMessageComponentsSolvedBlockHashedForDoSMitigation(false);
			
			
			return this.isSecureProofOfWorkMessageComponentsSolvedBlockHashedForDoSMitigationValid;
			
		}
		
		return false;
	}
	
	
}
