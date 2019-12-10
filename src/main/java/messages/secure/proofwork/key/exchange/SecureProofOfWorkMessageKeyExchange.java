package main.java.messages.secure.proofwork.key.exchange;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.ShortBufferException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import main.java.common.utils.CommonUtils;

public class SecureProofOfWorkMessageKeyExchange {
		
	private byte[] secretSymmetricKeyForProofOfWorkInBytes;
	
	private byte[] secretHMACKeyForDoSMitigationInBytes;
	
	
	private byte[] secureProofOfWorkMessageKeyExchangeSerialized;
	
	private boolean isSecureProofOfWorkMessageKeyExchangeSerialized;
	
	
	private byte[] secureProofOfWorkMessageKeyExchangeSerializedCiphered;
	
	private int sizeOfSecureProofOfWorkMessageKeyExchangeSerializedCiphered;
	
	private boolean isSecureProofOfWorkMessageKeyExchangeSerializedCiphered;
	
	
	private byte[] secureProofOfWorkMessageKeyExchangeSerializedCipheredSigned;
	
	private boolean isSecureProofOfWorkMessageKeyExchangeSerializedCipheredSigned;
	
	private int sizeOfSecureProofOfWorkMessageKeyExchangeSerializedCipheredSigned;
	
	private boolean isSecureProofOfWorkMessageKeyExchangeSerializedCipheredSignedVerified;
	
	private boolean isSecureProofOfWorkMessageKeyExchangeSerializedCipheredSignedValid;
	
	
	private byte[] secureProofOfWorkMessageKeyExchangeSerializedCipheredAndSigned;
	
	private boolean isSecureProofOfWorkMessageKeyExchangeSerializedCipheredAndSigned;
	
	
	
	public SecureProofOfWorkMessageKeyExchange(byte[] secretSymmetricKeyForProofOfWorkInBytes,
									   		   byte[] secretHMACKeyForDoSMitigationInBytes) {
			
		this.secretSymmetricKeyForProofOfWorkInBytes = secretSymmetricKeyForProofOfWorkInBytes;
		this.secretHMACKeyForDoSMitigationInBytes = secretHMACKeyForDoSMitigationInBytes;
		
		this.secureProofOfWorkMessageKeyExchangeSerialized = null;
		this.isSecureProofOfWorkMessageKeyExchangeSerialized = false;
		
		this.secureProofOfWorkMessageKeyExchangeSerializedCiphered = null;
		this.sizeOfSecureProofOfWorkMessageKeyExchangeSerializedCiphered = 0;
		this.isSecureProofOfWorkMessageKeyExchangeSerializedCiphered = false;
		
		this.secureProofOfWorkMessageKeyExchangeSerializedCipheredSigned = null;
		this.sizeOfSecureProofOfWorkMessageKeyExchangeSerializedCipheredSigned = 0;
		this.isSecureProofOfWorkMessageKeyExchangeSerializedCipheredSigned = false;
		
		this.secureProofOfWorkMessageKeyExchangeSerializedCipheredAndSigned = null;
		this.isSecureProofOfWorkMessageKeyExchangeSerializedCipheredAndSigned = false;
		
	}
	
	
	public SecureProofOfWorkMessageKeyExchange(byte[] secureProofOfWorkMessageKeyExchangeSerializedCipheredAndSigned,
											   int sizeOfSecureProofOfWorkMessageKeyExchangeSerializedCiphered,
											   int sizeOfSecureProofOfWorkMessageKeyExchangeSerializedCipheredSigned) {
		
		this.secureProofOfWorkMessageKeyExchangeSerializedCipheredAndSigned = 
				secureProofOfWorkMessageKeyExchangeSerializedCipheredAndSigned;
		this.isSecureProofOfWorkMessageKeyExchangeSerializedCipheredAndSigned = true;
		
		this.secureProofOfWorkMessageKeyExchangeSerializedCiphered = null;
		this.sizeOfSecureProofOfWorkMessageKeyExchangeSerializedCiphered = 
				sizeOfSecureProofOfWorkMessageKeyExchangeSerializedCiphered;
		this.isSecureProofOfWorkMessageKeyExchangeSerializedCiphered = true;
		
		this.secureProofOfWorkMessageKeyExchangeSerializedCipheredSigned = null;
		this.sizeOfSecureProofOfWorkMessageKeyExchangeSerializedCipheredSigned = 
				sizeOfSecureProofOfWorkMessageKeyExchangeSerializedCipheredSigned;
		this.isSecureProofOfWorkMessageKeyExchangeSerializedCipheredSigned = true;
		
		this.secureProofOfWorkMessageKeyExchangeSerialized = null;
		this.isSecureProofOfWorkMessageKeyExchangeSerialized = true;
		
		this.secretSymmetricKeyForProofOfWorkInBytes = null;
		this.secretHMACKeyForDoSMitigationInBytes = null;
		
	}
	
		
	public byte[] getSecretSymmetricKeyForProofOfWorkInBytes() {
		return this.secretSymmetricKeyForProofOfWorkInBytes;
	}
	
	public byte[] getSecretHMACKeyForDoSMitigationInBytes() {
		return this.secretHMACKeyForDoSMitigationInBytes;
	}
	
	public byte[] getSecureProofOfWorkMessageKeyExchangeSerialized() {
		return this.secureProofOfWorkMessageKeyExchangeSerialized;
	}
	
	public boolean getIsSecureProofOfWorkMessageKeyExchangeSerialized() {
		return this.isSecureProofOfWorkMessageKeyExchangeSerialized;
	}
	
	public void setIsSecureProofOfWorkMessageKeyExchangeSerialized
		  (boolean isSecureProofOfWorkMessageKeyExchangeSerialized) {
		
		this.isSecureProofOfWorkMessageKeyExchangeSerialized = 
				isSecureProofOfWorkMessageKeyExchangeSerialized;
	
	}
	
	public byte[] getSecureProofOfWorkMessageKeyExchangeSerializedCiphered() {
		return this.secureProofOfWorkMessageKeyExchangeSerializedCiphered;
	}
	
	public int getSizeOfSecureProofOfWorkMessageKeyExchangeSerializedCiphered() {
		return this.sizeOfSecureProofOfWorkMessageKeyExchangeSerializedCiphered;
	}
	
	public boolean getIsSecureProofOfWorkMessageKeyExchangeSerializedCiphered() {
		return this.isSecureProofOfWorkMessageKeyExchangeSerializedCiphered;
	}
	
	public void setIsSecureProofOfWorkMessageKeyExchangeSerializedCiphered
		  (boolean isSecureProofOfWorkMessageKeyExchangeSerializedCiphered) {
		
		this.isSecureProofOfWorkMessageKeyExchangeSerializedCiphered = 
				isSecureProofOfWorkMessageKeyExchangeSerializedCiphered;
	
	}
	
	public byte[] getSecureProofOfWorkMessageKeyExchangeSerializedCipheredSigned() {
		return this.secureProofOfWorkMessageKeyExchangeSerializedCipheredSigned;
	}
	
	public boolean getIsSecureProofOfWorkMessageKeyExchangeSerializedCipheredSigned() {
		return this.isSecureProofOfWorkMessageKeyExchangeSerializedCipheredSigned;
	}
	
	public void setIsSecureProofOfWorkMessageKeyExchangeSerializedCipheredSigned
		  (boolean isSecureProofOfWorkMessageKeyExchangeSerializedCipheredSigned) {
		
		this.isSecureProofOfWorkMessageKeyExchangeSerializedCipheredSigned = 
				isSecureProofOfWorkMessageKeyExchangeSerializedCipheredSigned;
	
	}
	
	public int getSizeOfSecureProofOfWorkMessageKeyExchangeSerializedCipheredSigned() {
		return this.sizeOfSecureProofOfWorkMessageKeyExchangeSerializedCipheredSigned;
	}
	
	public boolean getIsSecureProofOfWorkMessageKeyExchangeSerializedCipheredSignedVerified() {
		return this.isSecureProofOfWorkMessageKeyExchangeSerializedCipheredSignedVerified;
	}
	
	public void setIsSecureProofOfWorkMessageKeyExchangeSerializedCipheredSignedVerified
	      (boolean isSecureProofOfWorkMessageKeyExchangeSerializedCipheredSignedVerified) {
		
		this.isSecureProofOfWorkMessageKeyExchangeSerializedCipheredSignedVerified = 
				isSecureProofOfWorkMessageKeyExchangeSerializedCipheredSignedVerified;
		
	}
	
	public boolean getIsSecureProofOfWorkMessageKeyExchangeSerializedCipheredSignedValid() {
		return this.isSecureProofOfWorkMessageKeyExchangeSerializedCipheredSignedValid;
	}
	
	public void setIsSecureProofOfWorkMessageKeyExchangeSerializedCipheredSignedValid
    	  (boolean isSecureProofOfWorkMessageKeyExchangeSerializedCipheredSignedValid) {
	
		this.isSecureProofOfWorkMessageKeyExchangeSerializedCipheredSignedValid = 
				isSecureProofOfWorkMessageKeyExchangeSerializedCipheredSignedValid;
		
	}
	
		
	public byte[] getSecureProofOfWorkMessageKeyExchangeSerializedCipheredAndSigned() {
		return this.secureProofOfWorkMessageKeyExchangeSerializedCipheredAndSigned;
	}
	
	public boolean getIsSecureProofOfWorkMessageKeyExchangeSerializedCipheredAndSigned() {
		return this.isSecureProofOfWorkMessageKeyExchangeSerializedCipheredAndSigned;
	}
	
	public void setIsSecureProofOfWorkMessageKeyExchangeSerializedCipheredAndSigned
		  (boolean isSecureProofOfWorkMessageKeyExchangeSerializedCipheredAndSigned) {
		
		this.isSecureProofOfWorkMessageKeyExchangeSerializedCipheredAndSigned = 
				isSecureProofOfWorkMessageKeyExchangeSerializedCipheredAndSigned;
	
	}
	
	public void buildSecureProofOfWorkMessageKeyExchangeToSend() 
		   throws InvalidKeyException, NoSuchAlgorithmException, SignatureException {

		boolean isPossibleToBuildSecureProofOfWorkMessageKeyExchangeToSend = 
				( !this.getIsSecureProofOfWorkMessageKeyExchangeSerialized() && 
				  !this.getIsSecureProofOfWorkMessageKeyExchangeSerializedCiphered() && 
				  !this.getIsSecureProofOfWorkMessageKeyExchangeSerializedCipheredSigned() &&
				  !this.getIsSecureProofOfWorkMessageKeyExchangeSerializedCipheredAndSigned() );	

		if(isPossibleToBuildSecureProofOfWorkMessageKeyExchangeToSend) {

			this.doSerializationOfSecureProofOfWorkMessageKeyExchange();

			this.encryptSecureProofOfWorkMessageKeyExchangeSerialized();
						
			this.signSecureProofOfWorkMessageKeyExchangeSerializedCiphered();

			this.doSecureProofOfWorkMessageKeyExchangeSerializedCipheredAndSigned();
			
		}

	}
		
	public void buildSecureProofOfWorkMessageDataPersonalReceived()
		   throws InvalidKeyException, NoSuchAlgorithmException, SignatureException {
		
		boolean isPossibleToBuildSecureProofOfWorkMessageKeyExchangeReceived = 
				( this.getIsSecureProofOfWorkMessageKeyExchangeSerialized() && 
				  this.getIsSecureProofOfWorkMessageKeyExchangeSerializedCiphered() && 
				  this.getIsSecureProofOfWorkMessageKeyExchangeSerializedCipheredSigned() &&
				  this.getIsSecureProofOfWorkMessageKeyExchangeSerializedCipheredAndSigned() );	

		if(isPossibleToBuildSecureProofOfWorkMessageKeyExchangeReceived) {
			
			this.undoSecureProofOfWorkMessageKeyExchangeSerializedCipheredAndSigned();
						
			if(this.checkIfSecureProofOfWorkMessageKeyExchangeSerializedCipheredSignedIsValid()) {
				
				this.decryptSecureProofOfWorkMessageKeyExchangeSerialized();
				
				this.undoSecureProofOfWorkMessageKeyExchangeSerializedCipheredAndSigned();

			}
			
		}
	}
	
	
	public void doSerializationOfSecureProofOfWorkMessageKeyExchange() {
		
		boolean isPossibleToDoSerializationOfSecureProofOfWorkMessageKeyExchange = 
				( !this.isSecureProofOfWorkMessageKeyExchangeSerialized && 
				  !this.isSecureProofOfWorkMessageKeyExchangeSerializedCiphered &&
				  !this.isSecureProofOfWorkMessageKeyExchangeSerializedCipheredSigned &&
				  !this.isSecureProofOfWorkMessageKeyExchangeSerializedCipheredAndSigned);
		
		if(isPossibleToDoSerializationOfSecureProofOfWorkMessageKeyExchange) {
			
			int sizeOfSecureKeyExchangeSerialized = 
					( this.secretSymmetricKeyForProofOfWorkInBytes.length +
					  this.secretHMACKeyForDoSMitigationInBytes.length);
			
			this.secureProofOfWorkMessageKeyExchangeSerialized = 
					new byte[sizeOfSecureKeyExchangeSerialized];
					
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
			// the correspondent bytes from the current ProofOfWork serialized,
			// From the position corresponding to the length of the previous ProofOfWork's Serialization to
			// the position corresponding to the length of the current ProofOfWork's Serialization
			System.arraycopy(this.secretSymmetricKeyForProofOfWorkInBytes, 0, this.secureProofOfWorkMessageKeyExchangeSerialized,
							 serializationOffset, this.secretSymmetricKeyForProofOfWorkInBytes.length);
			serializationOffset += this.secretSymmetricKeyForProofOfWorkInBytes.length;
			
			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current ProofOfWork serialized,
			// From the position corresponding to the length of the previous ProofOfWork's Serialization to
			// the position corresponding to the length of the current ProofOfWork's Serialization
			System.arraycopy(this.secretHMACKeyForDoSMitigationInBytes, 0, this.secureProofOfWorkMessageKeyExchangeSerialized,
					         serializationOffset, this.secretHMACKeyForDoSMitigationInBytes.length);
			
			
			
			this.setIsSecureProofOfWorkMessageKeyExchangeSerialized(true);
			
		}
		
	}
	
	public void undoSerializationOfSecureProofOfWorkMessageKeyExchange() {
		
		boolean isPossibleToUndoSerializationOfSecureProofOfWorkMessageKeyExchange = 
				(  this.isSecureProofOfWorkMessageKeyExchangeSerialized && 
				  !this.isSecureProofOfWorkMessageKeyExchangeSerializedCiphered &&
				  !this.isSecureProofOfWorkMessageKeyExchangeSerializedCipheredSigned &&
				  !this.isSecureProofOfWorkMessageKeyExchangeSerializedCipheredAndSigned);
		
		if(isPossibleToUndoSerializationOfSecureProofOfWorkMessageKeyExchange) {
			
			this.secretSymmetricKeyForProofOfWorkInBytes = new byte[CommonUtils.LENGTH_256_BITS_IN_BYTES];
			this.secretHMACKeyForDoSMitigationInBytes = new byte[CommonUtils.LENGTH_256_BITS_IN_BYTES];
			
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
			// the correspondent bytes from the current ProofOfWork serialized,
			// From the position corresponding to the length of the previous ProofOfWork's Serialization to
			// the position corresponding to the length of the current ProofOfWork's Serialization
			System.arraycopy(this.secureProofOfWorkMessageKeyExchangeSerialized, serializationOffset, this.secretSymmetricKeyForProofOfWorkInBytes,
							 0, this.secretSymmetricKeyForProofOfWorkInBytes.length);
			serializationOffset += this.secretSymmetricKeyForProofOfWorkInBytes.length;
			
			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current ProofOfWork serialized,
			// From the position corresponding to the length of the previous ProofOfWork's Serialization to
			// the position corresponding to the length of the current ProofOfWork's Serialization
			System.arraycopy(this.secureProofOfWorkMessageKeyExchangeSerialized, 0, this.secretHMACKeyForDoSMitigationInBytes,
					         0, this.secretHMACKeyForDoSMitigationInBytes.length);
			
			this.setIsSecureProofOfWorkMessageKeyExchangeSerialized(false);
			
		}
		
	}
	
	public void encryptSecureProofOfWorkMessageKeyExchangeSerialized() {
		
		boolean isPossibleToEncryptSecureProofOfWorkMessageKeyExchangeSerialized = 
				(  this.isSecureProofOfWorkMessageKeyExchangeSerialized && 
				  !this.isSecureProofOfWorkMessageKeyExchangeSerializedCiphered &&
				  !this.isSecureProofOfWorkMessageKeyExchangeSerializedCipheredSigned &&
				  !this.isSecureProofOfWorkMessageKeyExchangeSerializedCipheredAndSigned);
		
		
		if(isPossibleToEncryptSecureProofOfWorkMessageKeyExchangeSerialized) {
			
			byte[] secretKeyBytes = null;  //TODO Public Key of the Server
			
			try {
				
				String symmetricEncryptionAlgorithm = "AES";
				String symmetricEncryptionMode = "CBC";
		 	    String symmetricEncryptionPadding = "NoPadding";
				
				
				// Set the Secret Key and its specifications,
		 		// using the AES (Advanced Encryption Standard - Rijndael) Symmetric Encryption
				SecretKeySpec secretKeySpecifications = new SecretKeySpec(secretKeyBytes, symmetricEncryptionAlgorithm);
				
				
				String provider = "BC";
				Cipher secureProofOfWorkMessageKeyExchangeAgreeementSymmetricEncryptionCipher = 
						Cipher.getInstance(String.format("%s/%s/%s",
										   symmetricEncryptionAlgorithm, symmetricEncryptionMode, symmetricEncryptionPadding), 
								           provider );
				
				byte[] initialisationVectorBytes = null;
				
				if(CommonUtils.blockModeRequiresIV(symmetricEncryptionMode)) {

					// Algorithms that don't need IV (Initialisation Vector): ECB
					// The parameter specifications for the IV (Initialisation Vector)	
					System.out.println("[SecureProofOfWorkMessageKeyExchange.ENCRYPT] Cipher's Block Mode needs IV (Initialisation Vector)!!!");
					initialisationVectorBytes = 
							CommonUtils.generateIV(secureProofOfWorkMessageKeyExchangeAgreeementSymmetricEncryptionCipher);
					
					// Showing the randomly defined IV (Initialisation Vector)
					System.out.println("[SecureProofOfWorkMessageKeyExchange.ENCRYPT] - IV (Initialisation Vector) is:\n- " 
									   + CommonUtils.fromByteArrayToHexadecimalFormat(initialisationVectorBytes));
					
					IvParameterSpec initializationVectorParameterSpecifications = new IvParameterSpec(initialisationVectorBytes);
					secureProofOfWorkMessageKeyExchangeAgreeementSymmetricEncryptionCipher
						.init(Cipher.ENCRYPT_MODE, secretKeySpecifications, initializationVectorParameterSpecifications);
					
				}
				else {
					
					// Algorithms that need IV (Initialisation Vector)
					// The parameter specifications for the IV (Initialisation Vector)
					System.out.println("[SecureProofOfWorkMessageSignatureProposal.ENCRYPT] Cipher's Block Mode doesn't needs IV (Initialisation Vector)!!!");
					
					secureProofOfWorkMessageKeyExchangeAgreeementSymmetricEncryptionCipher
						.init(Cipher.ENCRYPT_MODE, secretKeySpecifications);
					
				}
				
				this.secureProofOfWorkMessageKeyExchangeSerializedCiphered = 
						secureProofOfWorkMessageKeyExchangeAgreeementSymmetricEncryptionCipher
						.doFinal(this.secureProofOfWorkMessageKeyExchangeSerialized);
				
				
				this.setIsSecureProofOfWorkMessageKeyExchangeSerializedCiphered(true);		
				
			}
			catch (NoSuchAlgorithmException noSuchAlgorithmException) {
				System.err.println("Error occurred during the Symmetric Encryption over the Secure Message's Payload:");
				System.err.println("- Cryptographic Algorithm not found!!!");
				noSuchAlgorithmException.printStackTrace();
			}
			catch (InvalidAlgorithmParameterException invalidAlgorithmParameterException) {
				System.err.println("Error occurred during the Symmetric Encryption over the Secure Message's Payload:");
				System.err.println("- Invalid Cryptographic Algorithm's Parameters!!!");
				invalidAlgorithmParameterException.printStackTrace();
			}
			catch (NoSuchProviderException noSuchProviderException) {
				System.err.println("Error occurred during the Symmetric Encryption over the Secure Message's Payload:");
				System.err.println("- Cryptograhic Provider not found!!!");
				noSuchProviderException.printStackTrace();
			}
			catch (NoSuchPaddingException noSuchPaddingException) {
				System.err.println("Error occurred during the Symmetric Encryption over the Secure Message's Payload:");
				System.err.println("- Padding Method not found!!!");
				noSuchPaddingException.printStackTrace();
			}
			catch (BadPaddingException badPaddingException) {
				System.err.println("Error occurred during the Symmetric Encryption over the Secure Message's Payload:");
				System.err.println("- Bad/Wrong Padding Method in use!!!");
				badPaddingException.printStackTrace();
			}
			catch (InvalidKeyException invalidKeyException) {
				System.err.println("Error occurred during the Symmetric Encryption over the Secure Message's Payload:");
				System.err.println("- Invalid Cryptographic Algorithm's Secret Key!!!");
				invalidKeyException.printStackTrace();
			}
			catch (IllegalBlockSizeException illegalBlockSizeException) {
				System.err.println("Error occurred during the Symmetric Encryption over the Secure Message's Payload:");
				System.err.println("- Illegal Cryptographic Algorithm's Block Size!!!");
				illegalBlockSizeException.printStackTrace();
			}	
			
		}
	}
	
	public void decryptSecureProofOfWorkMessageKeyExchangeSerialized() {
		
		boolean isPossibleToDecryptSecureProofOfWorkMessageKeyExchangeSerializedCiphered = 
				(  this.isSecureProofOfWorkMessageKeyExchangeSerialized && 
				   this.isSecureProofOfWorkMessageKeyExchangeSerializedCiphered &&
				  !this.isSecureProofOfWorkMessageKeyExchangeSerializedCipheredSigned &&
				  !this.isSecureProofOfWorkMessageKeyExchangeSerializedCipheredAndSigned);
		
		if(isPossibleToDecryptSecureProofOfWorkMessageKeyExchangeSerializedCiphered) {
			
			byte[] secretKeyBytes = null; //TODO Private Key of the Server
			
			try {
				
				String symmetricEncryptionAlgorithm = "AES";
				String symmetricEncryptionMode = "CBC";
		 	    String symmetricEncryptionPadding = "NoPadding";
				
				
				// Set the Secret Key and its specifications,
		 		// using the AES (Advanced Encryption Standard - Rijndael) Symmetric Encryption
				SecretKeySpec secretKeySpecifications = new SecretKeySpec(secretKeyBytes, symmetricEncryptionAlgorithm);
				
				
				String provider = "BC";
				Cipher secureProofOfWorkMessageKeyExchangeAgreeementSymmetricEncryptionDecipher = 
						Cipher.getInstance(String.format("%s/%s/%s",
										   symmetricEncryptionAlgorithm, symmetricEncryptionMode, symmetricEncryptionPadding), 
								           provider);
				
				byte[] initialisationVectorBytes = null;
			
				if(CommonUtils.blockModeRequiresIV(symmetricEncryptionMode)) {
					
					// Algorithms that don't need IV (Initialisation Vector): ECB
					// The parameter specifications for the IV (Initialisation Vector)	
					System.out.println("[SecureProofOfWorkMessageSignatureProposal.DECRYPT] Cipher's Block Mode needs IV (Initialisation Vector)!!!");
					
					// Showing the randomly defined IV (Initialisation Vector)
					System.out.println("[SecureProofOfWorkMessageSignatureProposal.DECRYPT] - IV (Initialisation Vector) is:\n- " 
									   + CommonUtils.fromByteArrayToHexadecimalFormat(initialisationVectorBytes));
					
					IvParameterSpec initializationVectorParameterSpecifications = new IvParameterSpec(initialisationVectorBytes);
					secureProofOfWorkMessageKeyExchangeAgreeementSymmetricEncryptionDecipher
						.init(Cipher.DECRYPT_MODE, secretKeySpecifications, initializationVectorParameterSpecifications);
				}
				else {
					
					// Algorithms that need IV (Initialisation Vector)
					// The parameter specifications for the IV (Initialisation Vector)
					System.out.println("[SecureProofOfWorkMessageSignatureProposal.DECRYPT] Cipher's Block Mode doesn't needs IV (Initialisation Vector)!!!");
					
					secureProofOfWorkMessageKeyExchangeAgreeementSymmetricEncryptionDecipher
						.init(Cipher.DECRYPT_MODE, secretKeySpecifications);
					
				}
				
				this.sizeOfSecureProofOfWorkMessageKeyExchangeSerializedCiphered = 
						this.secureProofOfWorkMessageKeyExchangeSerializedCiphered.length;
				
			  	// The Plain Text of the bytes of the data input received through the communication channel
			  	this.secureProofOfWorkMessageKeyExchangeSerialized = 
			  			new byte[ secureProofOfWorkMessageKeyExchangeAgreeementSymmetricEncryptionDecipher
			  	                  .getOutputSize(this.sizeOfSecureProofOfWorkMessageKeyExchangeSerializedCiphered) ];
			    
			  	int sizeOfSecureProofOfWorkMessageKeyExchangeSerialized = 
			  											 secureProofOfWorkMessageKeyExchangeAgreeementSymmetricEncryptionDecipher
				  									     .update(this.secureProofOfWorkMessageKeyExchangeSerializedCiphered, 
				  										   	     0, this.sizeOfSecureProofOfWorkMessageKeyExchangeSerializedCiphered,
				  											     this.secureProofOfWorkMessageKeyExchangeSerialized, 0);
			  	
			  	secureProofOfWorkMessageKeyExchangeAgreeementSymmetricEncryptionDecipher
			  									   .doFinal(this.secureProofOfWorkMessageKeyExchangeSerialized,
			  											    sizeOfSecureProofOfWorkMessageKeyExchangeSerialized);
			    
			  	
				this.setIsSecureProofOfWorkMessageKeyExchangeSerializedCiphered(false);		
				
			}
			catch (NoSuchAlgorithmException noSuchAlgorithmException) {
				System.err.println("Error occurred during the Symmetric Encryption over the Secure ProofOfWork Message's Signature Proposal:");
				System.err.println("- Cryptographic Algorithm not found!!!");
				noSuchAlgorithmException.printStackTrace();
			}
			catch (InvalidAlgorithmParameterException invalidAlgorithmParameterException) {
				System.err.println("Error occurred during the Symmetric Encryption over the Secure ProofOfWork Message's Signature Proposal:");
				System.err.println("- Invalid Cryptographic Algorithm's Parameters!!!");
				invalidAlgorithmParameterException.printStackTrace();
			}
			catch (NoSuchProviderException noSuchProviderException) {
				System.err.println("Error occurred during the Symmetric Encryption over the Secure ProofOfWork Message's Signature Proposal:");
				System.err.println("- Cryptograhic Provider not found!!!");
				noSuchProviderException.printStackTrace();
			}
			catch (NoSuchPaddingException noSuchPaddingException) {
				System.err.println("Error occurred during the Symmetric Encryption over the Secure ProofOfWork Message's Signature Proposal:");
				System.err.println("- Padding Method not found!!!");
				noSuchPaddingException.printStackTrace();
			}
			catch (BadPaddingException badPaddingException) {
				System.err.println("Error occurred during the Symmetric Encryption over the Secure ProofOfWork Message's Signature Proposal:");
				System.err.println("- Bad/Wrong Padding Method in use!!!");
				badPaddingException.printStackTrace();
			}
			catch (InvalidKeyException invalidKeyException) {
				System.err.println("Error occurred during the Symmetric Encryption over the Secure ProofOfWork Message's Signature Proposal:");
				System.err.println("- Invalid Cryptographic Algorithm's Secret Key!!!");
				invalidKeyException.printStackTrace();
			}
			catch (IllegalBlockSizeException illegalBlockSizeException) {
				System.err.println("Error occurred during the Symmetric Encryption over the Secure ProofOfWork Message's Signature Proposal:");
				System.err.println("- Illegal Cryptographic Algorithm's Block Size!!!");
				illegalBlockSizeException.printStackTrace();
			}
			catch (ShortBufferException shortBufferException) {
				System.err.println("Error occurred during the Symmetric Encryption over the Secure ProofOfWork Message's Signature Proposal:");
				System.err.println("- The Buffer in use, during the Deciphering process it's not correct!!!");
				shortBufferException.printStackTrace();
			}
		}
	}
	
	
	public void signSecureProofOfWorkMessageKeyExchangeSerializedCiphered()
		   throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
		
		boolean isPossibleToSignSecureProofOfWorkMessageKeyExchangeSerializedCiphered = 
				(  this.isSecureProofOfWorkMessageKeyExchangeSerialized && 
				   this.isSecureProofOfWorkMessageKeyExchangeSerializedCiphered &&
				  !this.isSecureProofOfWorkMessageKeyExchangeSerializedCipheredSigned &&
				  !this.isSecureProofOfWorkMessageKeyExchangeSerializedCipheredAndSigned);
		
		
		if(isPossibleToSignSecureProofOfWorkMessageKeyExchangeSerializedCiphered) {
			
			Signature secureProofOfWorkMessageKeyExchangeSerializedCipheredSignature = 
					  Signature.getInstance("SHA256withDSA");
			
			PrivateKey userClientPrivateKey = null; //TODO Private Key to Sign contained in the KeyStore of the User
			
			secureProofOfWorkMessageKeyExchangeSerializedCipheredSignature.initSign(userClientPrivateKey);
			
			secureProofOfWorkMessageKeyExchangeSerializedCipheredSignature
			.update(this.secureProofOfWorkMessageKeyExchangeSerializedCiphered);
			
			this.secureProofOfWorkMessageKeyExchangeSerializedCipheredSigned = 
				 secureProofOfWorkMessageKeyExchangeSerializedCipheredSignature.sign();
			
			
			
			this.setIsSecureProofOfWorkMessageKeyExchangeSerializedCipheredSigned(true);
			
		}
		
	}
	
	
	public boolean checkIfSecureProofOfWorkMessageKeyExchangeSerializedCipheredSignedIsValid()
		   throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
			
		boolean isPossibleToVerifySecureProofOfWorkMessageKeyExchangeSerializedCipheredSigned = 
				(  this.isSecureProofOfWorkMessageKeyExchangeSerialized && 
				   this.isSecureProofOfWorkMessageKeyExchangeSerializedCiphered &&
				   this.isSecureProofOfWorkMessageKeyExchangeSerializedCipheredSigned &&
				  !this.isSecureProofOfWorkMessageKeyExchangeSerializedCipheredAndSigned);
		
		
		if(isPossibleToVerifySecureProofOfWorkMessageKeyExchangeSerializedCipheredSigned) {
			
			Signature secureProofOfWorkMessageKeyExchangeSerializedCipheredSignature = 
					  Signature.getInstance("SHA256withDSA");
			
			PublicKey userClientPublicKey = null; //TODO Public Key or Certificate of the User contained in the Server 
			
			secureProofOfWorkMessageKeyExchangeSerializedCipheredSignature.initVerify(userClientPublicKey);
			
			secureProofOfWorkMessageKeyExchangeSerializedCipheredSignature
			.update(this.secureProofOfWorkMessageKeyExchangeSerializedCiphered);
			
			this.isSecureProofOfWorkMessageKeyExchangeSerializedCipheredSignedValid = 
					secureProofOfWorkMessageKeyExchangeSerializedCipheredSignature
					.verify(this.secureProofOfWorkMessageKeyExchangeSerializedCipheredSigned);
		
			
			if(this.isSecureProofOfWorkMessageKeyExchangeSerializedCipheredSignedValid) {
				
				System.out.println("Valid Signature!!!");
				
			}
	        else {
	        	
	        	System.out.println("Invalid Signature!!!");
	        	
	        }
			
			this.setIsSecureProofOfWorkMessageKeyExchangeSerializedCipheredSignedVerified(true);
			this.setIsSecureProofOfWorkMessageKeyExchangeSerializedCipheredSigned(false);
			
			
			return this.isSecureProofOfWorkMessageKeyExchangeSerializedCipheredSignedValid;
					
		}
		
		return false;
		
	}

	public void doSecureProofOfWorkMessageKeyExchangeSerializedCipheredAndSigned() {
		
		boolean isPossibleToDoSecureProofOfWorkMessageKeyExchangeSerializedCipheredAndSigned = 
				(  this.isSecureProofOfWorkMessageKeyExchangeSerialized && 
				   this.isSecureProofOfWorkMessageKeyExchangeSerializedCiphered &&
				   this.isSecureProofOfWorkMessageKeyExchangeSerializedCipheredSigned &&
				  !this.isSecureProofOfWorkMessageKeyExchangeSerializedCipheredAndSigned);
		
		
		if(isPossibleToDoSecureProofOfWorkMessageKeyExchangeSerializedCipheredAndSigned) {
			
			int sizeOfSecureProofOfWorkMessageKeyExchangeSerializedCipheredAndSigned =
					( this.secureProofOfWorkMessageKeyExchangeSerializedCiphered.length +
					  this.secureProofOfWorkMessageKeyExchangeSerializedCipheredSigned.length );
			
			this.secureProofOfWorkMessageKeyExchangeSerializedCipheredAndSigned = 
					new byte[ sizeOfSecureProofOfWorkMessageKeyExchangeSerializedCipheredAndSigned ];

			
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
			// the correspondent bytes from the current ProofOfWork serialized,
			// From the position corresponding to the length of the previous ProofOfWork's Serialization to
			// the position corresponding to the length of the current ProofOfWork's Serialization
			System.arraycopy(this.secureProofOfWorkMessageKeyExchangeSerializedCiphered, 0,
					         this.secureProofOfWorkMessageKeyExchangeSerializedCipheredAndSigned,
							 serializationOffset, this.secureProofOfWorkMessageKeyExchangeSerializedCiphered.length);
			serializationOffset += this.secureProofOfWorkMessageKeyExchangeSerializedCiphered.length;
			
			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current ProofOfWork serialized,
			// From the position corresponding to the length of the previous ProofOfWork's Serialization to
			// the position corresponding to the length of the current ProofOfWork's Serialization
			System.arraycopy(this.secureProofOfWorkMessageKeyExchangeSerializedCipheredSigned, 0,
					         this.secureProofOfWorkMessageKeyExchangeSerializedCipheredAndSigned,
					         serializationOffset, this.secureProofOfWorkMessageKeyExchangeSerializedCiphered.length);
			
			
			this.setIsSecureProofOfWorkMessageKeyExchangeSerializedCipheredAndSigned(true);
			
		}
		
	}
	
	public void undoSecureProofOfWorkMessageKeyExchangeSerializedCipheredAndSigned() {
		
		boolean isPossibleToUndoSecureProofOfWorkMessageKeyExchangeSerializedCipheredAndSigned = 
				( this.isSecureProofOfWorkMessageKeyExchangeSerialized && 
				  this.isSecureProofOfWorkMessageKeyExchangeSerializedCiphered &&
				  this.isSecureProofOfWorkMessageKeyExchangeSerializedCipheredSigned &&
				  this.isSecureProofOfWorkMessageKeyExchangeSerializedCipheredAndSigned);
		
		
		if(isPossibleToUndoSecureProofOfWorkMessageKeyExchangeSerializedCipheredAndSigned) {
			
			this.secureProofOfWorkMessageKeyExchangeSerializedCiphered = 
					new byte[ this.sizeOfSecureProofOfWorkMessageKeyExchangeSerializedCiphered ];

			this.secureProofOfWorkMessageKeyExchangeSerializedCipheredSigned = 
					new byte[ this.sizeOfSecureProofOfWorkMessageKeyExchangeSerializedCipheredSigned ];
			
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
			// the correspondent bytes from the current ProofOfWork serialized,
			// From the position corresponding to the length of the previous ProofOfWork's Serialization to
			// the position corresponding to the length of the current ProofOfWork's Serialization
			System.arraycopy(this.secureProofOfWorkMessageKeyExchangeSerializedCipheredAndSigned, serializationOffset,
							 this.secureProofOfWorkMessageKeyExchangeSerializedCiphered,
							 0, this.secureProofOfWorkMessageKeyExchangeSerializedCiphered.length);
			serializationOffset += this.secureProofOfWorkMessageKeyExchangeSerializedCiphered.length;
			
			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current ProofOfWork serialized,
			// From the position corresponding to the length of the previous ProofOfWork's Serialization to
			// the position corresponding to the length of the current ProofOfWork's Serialization
			System.arraycopy(this.secureProofOfWorkMessageKeyExchangeSerializedCipheredAndSigned, serializationOffset,
					         this.secureProofOfWorkMessageKeyExchangeSerializedCipheredSigned,
					         0, this.secureProofOfWorkMessageKeyExchangeSerializedCipheredSigned.length);
			
			
			this.setIsSecureProofOfWorkMessageKeyExchangeSerializedCipheredSigned(false);
			
		}
	}
}
