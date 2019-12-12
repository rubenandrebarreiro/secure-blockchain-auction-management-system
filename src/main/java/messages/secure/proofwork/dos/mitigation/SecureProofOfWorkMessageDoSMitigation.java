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
import main.java.messages.secure.proofwork.solvedblock.SecureProofOfWorkMessageSolvedBlock;

public class SecureProofOfWorkMessageDoSMitigation {

	private SecureProofOfWorkMessageSolvedBlock secureProofOfWorkMessageSolvedBlock;
	
	private byte[] secureProofOfWorkMessageSolvedBlockSerialized;
	
	private byte[] secureProofOfWorkMessageSolvedBlockHashedForDoSMitigation;
	
	private byte[] secretHMACKeyForDoSMitigationInBytes;
	
	private boolean isSecureProofOfWorkMessageSolvedBlockHashedForDoSMitigation;
	
	private boolean isSecureProofOfWorkMessageSolvedBlockHashedForDoSMitigationVerified;
	
	private boolean isSecureProofOfWorkMessageSolvedBlockHashedForDoSMitigationValid;
	
	private int sizeOfSecureProofOfWorkMessageSolvedBlockHashedForDoSMitigation;
	
	
	public SecureProofOfWorkMessageDoSMitigation
	      (SecureProofOfWorkMessageSolvedBlock secureProofOfWorkMessageSolvedBlock) {
		
		this.secureProofOfWorkMessageSolvedBlock = secureProofOfWorkMessageSolvedBlock;
		
		this.secureProofOfWorkMessageSolvedBlockSerialized = null;
		
		this.secureProofOfWorkMessageSolvedBlockHashedForDoSMitigation = null;
		
		this.isSecureProofOfWorkMessageSolvedBlockHashedForDoSMitigation = false;
		
		this.sizeOfSecureProofOfWorkMessageSolvedBlockHashedForDoSMitigation = 0;
		
	}
	
	
	public SecureProofOfWorkMessageDoSMitigation(byte[] secureProofOfWorkMessageSolvedBlockSerialized,
												 byte[] secureProofOfWorkMessageSolvedBlockHashedForDoSMitigation,
									             byte[] secretHMACKeyForDoSMitigationInBytes) {
		
		this.secureProofOfWorkMessageSolvedBlock = null;
		
		this.secureProofOfWorkMessageSolvedBlockSerialized = secureProofOfWorkMessageSolvedBlockSerialized;
		
		this.secureProofOfWorkMessageSolvedBlockHashedForDoSMitigation = 
				secureProofOfWorkMessageSolvedBlockHashedForDoSMitigation;
		
		this.secretHMACKeyForDoSMitigationInBytes = 
				secretHMACKeyForDoSMitigationInBytes;
		
		this.isSecureProofOfWorkMessageSolvedBlockHashedForDoSMitigation = true;
		
		this.sizeOfSecureProofOfWorkMessageSolvedBlockHashedForDoSMitigation = 
				this.secureProofOfWorkMessageSolvedBlockHashedForDoSMitigation.length;
		
	}




	public SecureProofOfWorkMessageSolvedBlock getSecureProofOfWorkMessageSolvedBlock() {
		return this.secureProofOfWorkMessageSolvedBlock;
	}
	
	public byte[] getSecureProofOfWorkMessageSolvedBlockSerialized() {
		return this.secureProofOfWorkMessageSolvedBlockSerialized;
	}
	
	public byte[] getSecureProofOfWorkMessageSolvedBlockHashedForDoSMitigation() {
		return this.secureProofOfWorkMessageSolvedBlockHashedForDoSMitigation;
	}
	
	public byte[] getSecretHMACKeyForDoSMitigationInBytes() {
		return this.secretHMACKeyForDoSMitigationInBytes;
	}
	
	public boolean getIsSecureProofOfWorkMessageSolvedBlockHashedForDoSMitigation() {
		return this.isSecureProofOfWorkMessageSolvedBlockHashedForDoSMitigation;
	}


	public void setIsSecureProofOfWorkMessageSolvedBlockHashedForDoSMitigation
		  (boolean isSecureProofOfWorkMessageSolvedBlockHashedForDoSMitigation) {
		
		this.isSecureProofOfWorkMessageSolvedBlockHashedForDoSMitigation =
						isSecureProofOfWorkMessageSolvedBlockHashedForDoSMitigation;
	
	}

	public boolean getIsSecureProofOfWorkMessageSolvedBlockHashedForDoSMitigationVerified() {
		return this.isSecureProofOfWorkMessageSolvedBlockHashedForDoSMitigationVerified;
	}


	public void setIsSecureProofOfWorkMessageSolvedBlockHashedForDoSMitigationVerified
		  (boolean isSecureProofOfWorkMessageSolvedBlockHashedForDoSMitigationVerified) {
		
		this.isSecureProofOfWorkMessageSolvedBlockHashedForDoSMitigationVerified =
				isSecureProofOfWorkMessageSolvedBlockHashedForDoSMitigationVerified;
	
	}
	
	public boolean getIsSecureProofOfWorkMessageSolvedBlockHashedForDoSMitigationValid() {
		return this.isSecureProofOfWorkMessageSolvedBlockHashedForDoSMitigationValid;
	}


	public void setIsSecureProofOfWorkMessageSolvedBlockHashedForDoSMitigationValid
		  (boolean isSecureProofOfWorkMessageSolvedBlockHashedForDoSMitigationValid) {
		
		this.isSecureProofOfWorkMessageSolvedBlockHashedForDoSMitigationValid =
				isSecureProofOfWorkMessageSolvedBlockHashedForDoSMitigationValid;
	
	}
	
	
	public int getSizeOfSecureProofOfWorkMessageSolvedBlockHashedForDoSMitigation() {
		return this.sizeOfSecureProofOfWorkMessageSolvedBlockHashedForDoSMitigation;
	}
	
	
	public void doHashOfSecureProofOfWorkMessageDoSMitigation()
		   throws NoSuchAlgorithmException, InvalidKeyException, NoSuchProviderException, 
		          NoSuchPaddingException, InvalidAlgorithmParameterException, SignatureException {
		
		if(!this.isSecureProofOfWorkMessageSolvedBlockHashedForDoSMitigation) {
			
			this.secureProofOfWorkMessageSolvedBlock.doSecureProofOfWorkMessageSolvedBlockSerialization();
			this.secureProofOfWorkMessageSolvedBlockSerialized = 
					this.secureProofOfWorkMessageSolvedBlock.getSecureProofOfWorkMessageSolvedBlockSerialized();
			
			// Starts the MAC Hash process over the Secure Message serialized (applying the HMAC or CMAC operation),
			// before the sending of the final concatenation of it with Secure Message serialized
			try {
				
				// The Initialization Vector and its Parameter's Specifications
				Key secretHMACKeyForDoSMitigationMACKey = CommonUtils.createKeyForAES(256, new SecureRandom());
				this.secretHMACKeyForDoSMitigationInBytes = secretHMACKeyForDoSMitigationMACKey.getEncoded();
				Mac mac = Mac.getInstance("HMacSHA256");
				SecretKeySpec keySpec = new SecretKeySpec(this.secretHMACKeyForDoSMitigationInBytes, "HMacSHA256");
				// The configuration, initialization and update of the MAC Hash process
				mac.init(keySpec);
				mac.update(this.secureProofOfWorkMessageSolvedBlockSerialized);
				
				// Performs the final operation of MAC Hash process over the Secure Message serialized
				// (applying the HMAC or CMAC operation)
				this.secureProofOfWorkMessageSolvedBlockHashedForDoSMitigation = mac.doFinal();
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
			
			
			this.setIsSecureProofOfWorkMessageSolvedBlockHashedForDoSMitigation(true);
			
		}
		
	}
	
	public boolean checkIfHashOfSecureProofOfWorkMessageDoSMitigationIsValid() throws NoSuchAlgorithmException {
		
		if(this.isSecureProofOfWorkMessageSolvedBlockHashedForDoSMitigation) {
			
			byte[] secureProofOfWorkMessageSolvedBlockSerializedHashedToCompare = this.secureProofOfWorkMessageSolvedBlockSerialized;
			
			// Starts the MAC Hash process over the Secure Message serialized received (applying the HMAC or CMAC operation),
			// comparing it with Secure Message serialized hashed received (the MAC Hash process related to the Fast Secure Message Check)
			try {
			
				// The Initialization Vector and its Parameter's Specifications
				Mac mac = Mac.getInstance("HMacSHA256");
				SecretKeySpec keySpec = new SecretKeySpec(this.secretHMACKeyForDoSMitigationInBytes, "HMacSHA256");
				// The configuration, initialization and update of the MAC Hash process
				mac.init(keySpec);
				mac.update(secureProofOfWorkMessageSolvedBlockSerializedHashedToCompare);
				
				// Performs the final operation of MAC Hash process over the Secure Message serialized
				// (applying the HMAC or CMAC operation)
				secureProofOfWorkMessageSolvedBlockSerializedHashedToCompare = mac.doFinal();
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
			
			this.isSecureProofOfWorkMessageSolvedBlockHashedForDoSMitigationValid = 
									  Arrays.areEqual(this.getSecureProofOfWorkMessageSolvedBlockHashedForDoSMitigation(), 
											  		  secureProofOfWorkMessageSolvedBlockSerializedHashedToCompare) ? 
															  true : false;
			
			if(!this.isSecureProofOfWorkMessageSolvedBlockHashedForDoSMitigationValid) {
				System.err.println("The Fast Secure Message Check it's not valid:");
				System.err.println("- The Secure Message will be ignored!!!");
			}

			
			this.setIsSecureProofOfWorkMessageSolvedBlockHashedForDoSMitigationVerified(true);
			this.setIsSecureProofOfWorkMessageSolvedBlockHashedForDoSMitigation(false);
			
			
			return this.isSecureProofOfWorkMessageSolvedBlockHashedForDoSMitigationValid;
			
		}
		
		return false;
	}
	
	
}
