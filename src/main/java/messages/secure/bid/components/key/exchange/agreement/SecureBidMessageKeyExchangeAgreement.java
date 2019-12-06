package main.java.messages.secure.bid.components.key.exchange.agreement;

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

public class SecureBidMessageKeyExchangeAgreement {
		
	private byte[] secretSymmetricKeyForDataPersonalInBytes;
	
	private byte[] secretHMACKeyForDoSMitigationInBytes;
	
	
	private byte[] secureBidMessageKeyExchangeAgreementSerialized;
	
	private boolean isSecureBidMessageKeyExchangeAgreementSerialized;
	
	
	private byte[] secureBidMessageKeyExchangeAgreementSerializedCiphered;
	
	private int sizeOfSecureBidMessageKeyExchangeAgreementSerializedCiphered;
	
	private boolean isSecureBidMessageKeyExchangeAgreementSerializedCiphered;
	
	
	private byte[] secureBidMessageKeyExchangeAgreementSerializedCipheredSigned;
	
	private int sizeOfSecureBidMessageKeyExchangeAgreementSerializedCipheredSigned;
	
	private boolean isSecureBidMessageKeyExchangeAgreementSerializedCipheredSigned;
	
	
	private byte[] secureBidMessageKeyExchangeAgreementSerializedCipheredAndSigned;
	
	private boolean isSecureBidMessageKeyExchangeAgreementSerializedCipheredAndSigned;
	
	
	
	public SecureBidMessageKeyExchangeAgreement(byte[] secretSymmetricKeyForDataPersonalInBytes,
											    byte[] secretHMACKeyForDoSMitigationInBytes) {
			
		this.secretSymmetricKeyForDataPersonalInBytes = secretSymmetricKeyForDataPersonalInBytes;
		this.secretHMACKeyForDoSMitigationInBytes = secretHMACKeyForDoSMitigationInBytes;
		
		this.secureBidMessageKeyExchangeAgreementSerialized = null;
		this.isSecureBidMessageKeyExchangeAgreementSerialized = false;
		
		this.secureBidMessageKeyExchangeAgreementSerializedCiphered = null;
		this.sizeOfSecureBidMessageKeyExchangeAgreementSerializedCiphered = 0;
		this.isSecureBidMessageKeyExchangeAgreementSerializedCiphered = false;
		
		this.secureBidMessageKeyExchangeAgreementSerializedCipheredSigned = null;
		this.sizeOfSecureBidMessageKeyExchangeAgreementSerializedCipheredSigned = 0;
		this.isSecureBidMessageKeyExchangeAgreementSerializedCipheredSigned = false;
		
		this.secureBidMessageKeyExchangeAgreementSerializedCipheredAndSigned = null;
		this.isSecureBidMessageKeyExchangeAgreementSerializedCipheredAndSigned = false;
		
	}
	
	
	public SecureBidMessageKeyExchangeAgreement(byte[] secureBidMessageKeyExchangeAgreementSerializedCipheredAndSigned,
												int sizeOfSecureBidMessageKeyExchangeAgreementSerializedCiphered,
												int sizeOfSecureBidMessageKeyExchangeAgreementSerializedCipheredSigned) {
		
		this.secureBidMessageKeyExchangeAgreementSerializedCipheredAndSigned = 
				secureBidMessageKeyExchangeAgreementSerializedCipheredAndSigned;
		this.isSecureBidMessageKeyExchangeAgreementSerializedCipheredAndSigned = true;
		
		this.secureBidMessageKeyExchangeAgreementSerializedCiphered = null;
		this.sizeOfSecureBidMessageKeyExchangeAgreementSerializedCiphered = 
				sizeOfSecureBidMessageKeyExchangeAgreementSerializedCiphered;
		this.isSecureBidMessageKeyExchangeAgreementSerializedCiphered = true;
		
		this.secureBidMessageKeyExchangeAgreementSerializedCipheredSigned = null;
		this.sizeOfSecureBidMessageKeyExchangeAgreementSerializedCipheredSigned = 
				sizeOfSecureBidMessageKeyExchangeAgreementSerializedCipheredSigned;
		this.isSecureBidMessageKeyExchangeAgreementSerializedCipheredSigned = true;
		
		this.secureBidMessageKeyExchangeAgreementSerialized = null;
		this.isSecureBidMessageKeyExchangeAgreementSerialized = true;
		
		this.secretSymmetricKeyForDataPersonalInBytes = null;
		this.secretHMACKeyForDoSMitigationInBytes = null;
		
	}
	
		
	public byte[] getSecretSymmetricKeyForDataPersonalInBytes() {
		return this.secretSymmetricKeyForDataPersonalInBytes;
	}
	
	public byte[] getSecretHMACKeyForDoSMitigationInBytes() {
		return this.secretHMACKeyForDoSMitigationInBytes;
	}
	
	public byte[] getSecureBidMessageKeyExchangeAgreementSerialized() {
		return this.secureBidMessageKeyExchangeAgreementSerialized;
	}
	
	public boolean getIsSecureBidMessageKeyExchangeAgreementSerialized() {
		return this.isSecureBidMessageKeyExchangeAgreementSerialized;
	}
	
	public void setIsSecureBidMessageKeyExchangeAgreementSerialized
		  (boolean isSecureBidMessageKeyExchangeAgreementSerialized) {
		
		this.isSecureBidMessageKeyExchangeAgreementSerialized = 
				isSecureBidMessageKeyExchangeAgreementSerialized;
	
	}
	
	public byte[] getSecureBidMessageKeyExchangeAgreementSerializedCiphered() {
		return this.secureBidMessageKeyExchangeAgreementSerializedCiphered;
	}
	
	public int getSizeOfSecureBidMessageKeyExchangeAgreementSerializedCiphered() {
		return this.sizeOfSecureBidMessageKeyExchangeAgreementSerializedCiphered;
	}
	
	public boolean getIsSecureBidMessageKeyExchangeAgreementSerializedCiphered() {
		return this.isSecureBidMessageKeyExchangeAgreementSerializedCiphered;
	}
	
	public void setIsSecureBidMessageKeyExchangeAgreementSerializedCiphered
		  (boolean isSecureBidMessageKeyExchangeAgreementSerializedCiphered) {
		
		this.isSecureBidMessageKeyExchangeAgreementSerializedCiphered = 
				isSecureBidMessageKeyExchangeAgreementSerializedCiphered;
	
	}
	
	public byte[] getSecureBidMessageKeyExchangeAgreementSerializedCipheredSigned() {
		return this.secureBidMessageKeyExchangeAgreementSerializedCipheredSigned;
	}
	
	public int getSizeOfSecureBidMessageKeyExchangeAgreementSerializedCipheredSigned() {
		return this.sizeOfSecureBidMessageKeyExchangeAgreementSerializedCipheredSigned;
	}
	
	public boolean getIsSecureBidMessageKeyExchangeAgreementSerializedCipheredSigned() {
		return this.isSecureBidMessageKeyExchangeAgreementSerializedCipheredSigned;
	}
	
	public void setIsSecureBidMessageKeyExchangeAgreementSerializedCipheredSigned
		  (boolean isSecureBidMessageKeyExchangeAgreementSerializedCipheredSigned) {
		
		this.isSecureBidMessageKeyExchangeAgreementSerializedCipheredSigned = 
				isSecureBidMessageKeyExchangeAgreementSerializedCipheredSigned;
	
	}
	
	public byte[] getSecureBidMessageKeyExchangeAgreementSerializedCipheredAndSigned() {
		return this.secureBidMessageKeyExchangeAgreementSerializedCipheredAndSigned;
	}
	
	public boolean getIsSecureBidMessageKeyExchangeAgreementSerializedCipheredAndSigned() {
		return this.isSecureBidMessageKeyExchangeAgreementSerializedCipheredAndSigned;
	}
	
	public void setIsSecureBidMessageKeyExchangeAgreementSerializedCipheredAndSigned
		  (boolean isSecureBidMessageKeyExchangeAgreementSerializedCipheredAndSigned) {
		
		this.isSecureBidMessageKeyExchangeAgreementSerializedCipheredAndSigned = 
				isSecureBidMessageKeyExchangeAgreementSerializedCipheredAndSigned;
	
	}
	
	
	
	
	
	
	public void doSerializationOfSecureBidMessageKeyExchangeAgreement() {
		
		boolean isPossibleToDoSerializationOfSecureBidMessageKeyExchangeAgreement = 
				( !this.isSecureBidMessageKeyExchangeAgreementSerialized && 
				  !this.isSecureBidMessageKeyExchangeAgreementSerializedCiphered &&
				  !this.isSecureBidMessageKeyExchangeAgreementSerializedCipheredSigned &&
				  !this.isSecureBidMessageKeyExchangeAgreementSerializedCipheredAndSigned);
		
		if(isPossibleToDoSerializationOfSecureBidMessageKeyExchangeAgreement) {
			
			int sizeOfSecureKeyExchangeAgreementSerialized = 
					( this.secretSymmetricKeyForDataPersonalInBytes.length +
					  this.secretHMACKeyForDoSMitigationInBytes.length);
			
			this.secureBidMessageKeyExchangeAgreementSerialized = 
					new byte[sizeOfSecureKeyExchangeAgreementSerialized];
					
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
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(this.secretSymmetricKeyForDataPersonalInBytes, 0, this.secureBidMessageKeyExchangeAgreementSerialized,
							 serializationOffset, this.secretSymmetricKeyForDataPersonalInBytes.length);
			serializationOffset += this.secretSymmetricKeyForDataPersonalInBytes.length;
			
			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(this.secretHMACKeyForDoSMitigationInBytes, 0, this.secureBidMessageKeyExchangeAgreementSerialized,
					         serializationOffset, this.secretHMACKeyForDoSMitigationInBytes.length);
			
			
			
			this.setIsSecureBidMessageKeyExchangeAgreementSerialized(true);
			
		}
		
	}
	
	public void undoSerializationOfSecureBidMessageKeyExchangeAgreement() {
		
		boolean isPossibleToUndoSerializationOfSecureBidMessageKeyExchangeAgreement = 
				(  this.isSecureBidMessageKeyExchangeAgreementSerialized && 
				  !this.isSecureBidMessageKeyExchangeAgreementSerializedCiphered &&
				  !this.isSecureBidMessageKeyExchangeAgreementSerializedCipheredSigned &&
				  !this.isSecureBidMessageKeyExchangeAgreementSerializedCipheredAndSigned);
		
		if(isPossibleToUndoSerializationOfSecureBidMessageKeyExchangeAgreement) {
			
			this.secretSymmetricKeyForDataPersonalInBytes = new byte[CommonUtils.LENGTH_256_BITS_IN_BYTES];
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
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(this.secureBidMessageKeyExchangeAgreementSerialized, serializationOffset, this.secretSymmetricKeyForDataPersonalInBytes,
							 0, this.secretSymmetricKeyForDataPersonalInBytes.length);
			serializationOffset += this.secretSymmetricKeyForDataPersonalInBytes.length;
			
			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(this.secureBidMessageKeyExchangeAgreementSerialized, 0, this.secretHMACKeyForDoSMitigationInBytes,
					         0, this.secretHMACKeyForDoSMitigationInBytes.length);
			
			this.setIsSecureBidMessageKeyExchangeAgreementSerialized(false);
			
		}
		
	}
	
	public void encryptSecureBidMessageKeyExchangeAgreementSerialized() {
		
		boolean isPossibleToEncryptSecureBidMessageKeyExchangeAgreementSerialized = 
				(  this.isSecureBidMessageKeyExchangeAgreementSerialized && 
				  !this.isSecureBidMessageKeyExchangeAgreementSerializedCiphered &&
				  !this.isSecureBidMessageKeyExchangeAgreementSerializedCipheredSigned &&
				  !this.isSecureBidMessageKeyExchangeAgreementSerializedCipheredAndSigned);
		
		
		if(isPossibleToEncryptSecureBidMessageKeyExchangeAgreementSerialized) {
			
			byte[] secretKeyBytes = null;
			
			try {
				
				String symmetricEncryptionAlgorithm = "AES";
				String symmetricEncryptionMode = "CBC";
		 	    String symmetricEncryptionPadding = "NoPadding";
				
				
				// Set the Secret Key and its specifications,
		 		// using the AES (Advanced Encryption Standard - Rijndael) Symmetric Encryption
				SecretKeySpec secretKeySpecifications = new SecretKeySpec(secretKeyBytes, symmetricEncryptionAlgorithm);
				
				
				String provider = "BC";
				Cipher secureBidMessageKeyExchangeAgreeementSymmetricEncryptionCipher = 
						Cipher.getInstance(String.format("%s/%s/%s",
										   symmetricEncryptionAlgorithm, symmetricEncryptionMode, symmetricEncryptionPadding), 
								           provider );
				
				byte[] initialisationVectorBytes = null;
				
				if(CommonUtils.blockModeRequiresIV(symmetricEncryptionMode)) {

					// Algorithms that don't need IV (Initialisation Vector): ECB
					// The parameter specifications for the IV (Initialisation Vector)	
					System.out.println("[SecureBidMessageKeyExchangeAgreement.ENCRYPT] Cipher's Block Mode needs IV (Initialisation Vector)!!!");
					initialisationVectorBytes = 
							CommonUtils.generateIV(secureBidMessageKeyExchangeAgreeementSymmetricEncryptionCipher);
					
					// Showing the randomly defined IV (Initialisation Vector)
					System.out.println("[SecureBidMessageKeyExchangeAgreement.ENCRYPT] - IV (Initialisation Vector) is:\n- " 
									   + CommonUtils.fromByteArrayToHexadecimalFormat(initialisationVectorBytes));
					
					IvParameterSpec initializationVectorParameterSpecifications = new IvParameterSpec(initialisationVectorBytes);
					secureBidMessageKeyExchangeAgreeementSymmetricEncryptionCipher
						.init(Cipher.ENCRYPT_MODE, secretKeySpecifications, initializationVectorParameterSpecifications);
					
				}
				else {
					
					// Algorithms that need IV (Initialisation Vector)
					// The parameter specifications for the IV (Initialisation Vector)
					System.out.println("[SecureBidMessageSignatureProposal.ENCRYPT] Cipher's Block Mode doesn't needs IV (Initialisation Vector)!!!");
					
					secureBidMessageKeyExchangeAgreeementSymmetricEncryptionCipher
						.init(Cipher.ENCRYPT_MODE, secretKeySpecifications);
					
				}
				
				this.secureBidMessageKeyExchangeAgreementSerializedCiphered = 
						secureBidMessageKeyExchangeAgreeementSymmetricEncryptionCipher
						.doFinal(this.secureBidMessageKeyExchangeAgreementSerialized);
				
				
				this.setIsSecureBidMessageKeyExchangeAgreementSerializedCiphered(true);		
				
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
	
	public void decryptSecureBidMessageKeyExchangeAgreementSerialized() {
		
		boolean isPossibleToDecryptSecureBidMessageKeyExchangeAgreementSerializedCiphered = 
				(  this.isSecureBidMessageKeyExchangeAgreementSerialized && 
				   this.isSecureBidMessageKeyExchangeAgreementSerializedCiphered &&
				  !this.isSecureBidMessageKeyExchangeAgreementSerializedCipheredSigned &&
				  !this.isSecureBidMessageKeyExchangeAgreementSerializedCipheredAndSigned);
		
		if(isPossibleToDecryptSecureBidMessageKeyExchangeAgreementSerializedCiphered) {
			
			byte[] secretKeyBytes = null;
			
			try {
				
				String symmetricEncryptionAlgorithm = "AES";
				String symmetricEncryptionMode = "CBC";
		 	    String symmetricEncryptionPadding = "NoPadding";
				
				
				// Set the Secret Key and its specifications,
		 		// using the AES (Advanced Encryption Standard - Rijndael) Symmetric Encryption
				SecretKeySpec secretKeySpecifications = new SecretKeySpec(secretKeyBytes, symmetricEncryptionAlgorithm);
				
				
				String provider = "BC";
				Cipher secureBidMessageKeyExchangeAgreeementSymmetricEncryptionDecipher = 
						Cipher.getInstance(String.format("%s/%s/%s",
										   symmetricEncryptionAlgorithm, symmetricEncryptionMode, symmetricEncryptionPadding), 
								           provider );
				
				byte[] initialisationVectorBytes = null;
			
				if(CommonUtils.blockModeRequiresIV(symmetricEncryptionMode)) {
					
					// Algorithms that don't need IV (Initialisation Vector): ECB
					// The parameter specifications for the IV (Initialisation Vector)	
					System.out.println("[SecureBidMessageSignatureProposal.DECRYPT] Cipher's Block Mode needs IV (Initialisation Vector)!!!");
					
					// Showing the randomly defined IV (Initialisation Vector)
					System.out.println("[SecureBidMessageSignatureProposal.DECRYPT] - IV (Initialisation Vector) is:\n- " 
									   + CommonUtils.fromByteArrayToHexadecimalFormat(initialisationVectorBytes));
					
					IvParameterSpec initializationVectorParameterSpecifications = new IvParameterSpec(initialisationVectorBytes);
					secureBidMessageKeyExchangeAgreeementSymmetricEncryptionDecipher
						.init(Cipher.DECRYPT_MODE, secretKeySpecifications, initializationVectorParameterSpecifications);
				}
				else {
					
					// Algorithms that need IV (Initialisation Vector)
					// The parameter specifications for the IV (Initialisation Vector)
					System.out.println("[SecureBidMessageSignatureProposal.DECRYPT] Cipher's Block Mode doesn't needs IV (Initialisation Vector)!!!");
					
					secureBidMessageKeyExchangeAgreeementSymmetricEncryptionDecipher
						.init(Cipher.DECRYPT_MODE, secretKeySpecifications);
					
				}
				
				this.sizeOfSecureBidMessageKeyExchangeAgreementSerializedCiphered = 
						this.secureBidMessageKeyExchangeAgreementSerializedCiphered.length;
				
			  	// The Plain Text of the bytes of the data input received through the communication channel
			  	this.secureBidMessageKeyExchangeAgreementSerialized = 
			  			new byte[ secureBidMessageKeyExchangeAgreeementSymmetricEncryptionDecipher
			  	                  .getOutputSize(this.sizeOfSecureBidMessageKeyExchangeAgreementSerializedCiphered) ];
			    
			  	int sizeOfSecureBidMessageKeyExchangeAgreementSerialized = 
			  											 secureBidMessageKeyExchangeAgreeementSymmetricEncryptionDecipher
				  									     .update(this.secureBidMessageKeyExchangeAgreementSerializedCiphered, 
				  										   	     0, this.sizeOfSecureBidMessageKeyExchangeAgreementSerializedCiphered,
				  											     this.secureBidMessageKeyExchangeAgreementSerialized, 0);
			  	
			  	secureBidMessageKeyExchangeAgreeementSymmetricEncryptionDecipher
			  									   .doFinal(this.secureBidMessageKeyExchangeAgreementSerialized,
			  											    sizeOfSecureBidMessageKeyExchangeAgreementSerialized);
			    
			  	
				this.setIsSecureBidMessageKeyExchangeAgreementSerializedCiphered(false);		
				
			}
			catch (NoSuchAlgorithmException noSuchAlgorithmException) {
				System.err.println("Error occurred during the Symmetric Encryption over the Secure Bid Message's Signature Proposal:");
				System.err.println("- Cryptographic Algorithm not found!!!");
				noSuchAlgorithmException.printStackTrace();
			}
			catch (InvalidAlgorithmParameterException invalidAlgorithmParameterException) {
				System.err.println("Error occurred during the Symmetric Encryption over the Secure Bid Message's Signature Proposal:");
				System.err.println("- Invalid Cryptographic Algorithm's Parameters!!!");
				invalidAlgorithmParameterException.printStackTrace();
			}
			catch (NoSuchProviderException noSuchProviderException) {
				System.err.println("Error occurred during the Symmetric Encryption over the Secure Bid Message's Signature Proposal:");
				System.err.println("- Cryptograhic Provider not found!!!");
				noSuchProviderException.printStackTrace();
			}
			catch (NoSuchPaddingException noSuchPaddingException) {
				System.err.println("Error occurred during the Symmetric Encryption over the Secure Bid Message's Signature Proposal:");
				System.err.println("- Padding Method not found!!!");
				noSuchPaddingException.printStackTrace();
			}
			catch (BadPaddingException badPaddingException) {
				System.err.println("Error occurred during the Symmetric Encryption over the Secure Bid Message's Signature Proposal:");
				System.err.println("- Bad/Wrong Padding Method in use!!!");
				badPaddingException.printStackTrace();
			}
			catch (InvalidKeyException invalidKeyException) {
				System.err.println("Error occurred during the Symmetric Encryption over the Secure Bid Message's Signature Proposal:");
				System.err.println("- Invalid Cryptographic Algorithm's Secret Key!!!");
				invalidKeyException.printStackTrace();
			}
			catch (IllegalBlockSizeException illegalBlockSizeException) {
				System.err.println("Error occurred during the Symmetric Encryption over the Secure Bid Message's Signature Proposal:");
				System.err.println("- Illegal Cryptographic Algorithm's Block Size!!!");
				illegalBlockSizeException.printStackTrace();
			}
			catch (ShortBufferException shortBufferException) {
				System.err.println("Error occurred during the Symmetric Encryption over the Secure Bid Message's Signature Proposal:");
				System.err.println("- The Buffer in use, during the Deciphering process it's not correct!!!");
				shortBufferException.printStackTrace();
			}
		}
	}
	
	
	public void signSecureBidMessageKeyExchangeAgreementSerializedCiphered()
		   throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
		
		boolean isPossibleToSignSecureBidMessageKeyExchangeAgreementSerializedCiphered = 
				(  this.isSecureBidMessageKeyExchangeAgreementSerialized && 
				   this.isSecureBidMessageKeyExchangeAgreementSerializedCiphered &&
				  !this.isSecureBidMessageKeyExchangeAgreementSerializedCipheredSigned &&
				  !this.isSecureBidMessageKeyExchangeAgreementSerializedCipheredAndSigned);
		
		
		if(isPossibleToSignSecureBidMessageKeyExchangeAgreementSerializedCiphered) {
			
			Signature secureBidMessageKeyExchangeAgreementSerializedCipheredSignature = 
					  Signature.getInstance("SHA256withDSA");
			
			PrivateKey userClientPrivateKey = null;
			
			secureBidMessageKeyExchangeAgreementSerializedCipheredSignature.initSign(userClientPrivateKey);
			
			secureBidMessageKeyExchangeAgreementSerializedCipheredSignature
			.update(this.secureBidMessageKeyExchangeAgreementSerializedCiphered);
			
			this.secureBidMessageKeyExchangeAgreementSerializedCipheredSigned = 
				 secureBidMessageKeyExchangeAgreementSerializedCipheredSignature.sign();
			
			
			
			this.setIsSecureBidMessageKeyExchangeAgreementSerializedCipheredSigned(true);
			
		}
		
	}
	
	
	public void verifySecureBidMessageKeyExchangeAgreementSerializedCipheredSigned()
		   throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
			
		boolean isPossibleToSignSecureBidMessageKeyExchangeAgreementSerializedCiphered = 
				(  this.isSecureBidMessageKeyExchangeAgreementSerialized && 
				   this.isSecureBidMessageKeyExchangeAgreementSerializedCiphered &&
				   this.isSecureBidMessageKeyExchangeAgreementSerializedCipheredSigned &&
				  !this.isSecureBidMessageKeyExchangeAgreementSerializedCipheredAndSigned);
		
		
		if(isPossibleToSignSecureBidMessageKeyExchangeAgreementSerializedCiphered) {
			
			Signature secureBidMessageKeyExchangeAgreementSerializedCipheredSignature = 
					  Signature.getInstance("SHA256withDSA");
			
			PublicKey userClientPublicKey = null;
			
			secureBidMessageKeyExchangeAgreementSerializedCipheredSignature.initVerify(userClientPublicKey);
			
			secureBidMessageKeyExchangeAgreementSerializedCipheredSignature
			.update(this.secureBidMessageKeyExchangeAgreementSerializedCiphered);
			
			if(secureBidMessageKeyExchangeAgreementSerializedCipheredSignature
			   .verify(this.secureBidMessageKeyExchangeAgreementSerializedCipheredSigned)) {
	           
				System.out.println("Valid Signature!!!");
				
				this.setIsSecureBidMessageKeyExchangeAgreementSerializedCipheredSigned(false);
				
			}
	        else {
	        	
	        	System.out.println("Invalid Signature!!!");
	        	
	        }
			
		}
		
	}

	public void doSecureBidMessageKeyExchangeAgreementSerializedCipheredAndSigned() {
		
		boolean isPossibleToDoSecureBidMessageKeyExchangeAgreementSerializedCipheredAndSigned = 
				(  this.isSecureBidMessageKeyExchangeAgreementSerialized && 
				   this.isSecureBidMessageKeyExchangeAgreementSerializedCiphered &&
				   this.isSecureBidMessageKeyExchangeAgreementSerializedCipheredSigned &&
				  !this.isSecureBidMessageKeyExchangeAgreementSerializedCipheredAndSigned);
		
		
		if(isPossibleToDoSecureBidMessageKeyExchangeAgreementSerializedCipheredAndSigned) {
			
			int sizeOfSecureBidMessageKeyExchangeAgreementSerializedCipheredAndSigned =
					( this.secureBidMessageKeyExchangeAgreementSerializedCiphered.length +
					  this.secureBidMessageKeyExchangeAgreementSerializedCipheredSigned.length );
			
			this.secureBidMessageKeyExchangeAgreementSerializedCipheredAndSigned = 
					new byte[ sizeOfSecureBidMessageKeyExchangeAgreementSerializedCipheredAndSigned ];

			
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
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(this.secureBidMessageKeyExchangeAgreementSerializedCiphered, 0,
					         this.secureBidMessageKeyExchangeAgreementSerializedCipheredAndSigned,
							 serializationOffset, this.secureBidMessageKeyExchangeAgreementSerializedCiphered.length);
			serializationOffset += this.secureBidMessageKeyExchangeAgreementSerializedCiphered.length;
			
			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(this.secureBidMessageKeyExchangeAgreementSerializedCipheredSigned, 0,
					         this.secureBidMessageKeyExchangeAgreementSerializedCipheredAndSigned,
					         serializationOffset, this.secureBidMessageKeyExchangeAgreementSerializedCiphered.length);
			
			
			this.setIsSecureBidMessageKeyExchangeAgreementSerializedCipheredAndSigned(true);
			
		}
		
	}
	
	public void undoSecureBidMessageKeyExchangeAgreementSerializedCipheredAndSigned() {
		
		boolean isPossibleToUndoSecureBidMessageKeyExchangeAgreementSerializedCipheredAndSigned = 
				( this.isSecureBidMessageKeyExchangeAgreementSerialized && 
				  this.isSecureBidMessageKeyExchangeAgreementSerializedCiphered &&
				  this.isSecureBidMessageKeyExchangeAgreementSerializedCipheredSigned &&
				  this.isSecureBidMessageKeyExchangeAgreementSerializedCipheredAndSigned);
		
		
		if(isPossibleToUndoSecureBidMessageKeyExchangeAgreementSerializedCipheredAndSigned) {
			
			this.secureBidMessageKeyExchangeAgreementSerializedCiphered = 
					new byte[ this.sizeOfSecureBidMessageKeyExchangeAgreementSerializedCiphered ];

			this.secureBidMessageKeyExchangeAgreementSerializedCipheredSigned = 
					new byte[ this.sizeOfSecureBidMessageKeyExchangeAgreementSerializedCipheredSigned ];
			
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
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(this.secureBidMessageKeyExchangeAgreementSerializedCipheredAndSigned, serializationOffset,
							 this.secureBidMessageKeyExchangeAgreementSerializedCiphered,
							 0, this.secureBidMessageKeyExchangeAgreementSerializedCiphered.length);
			serializationOffset += this.secureBidMessageKeyExchangeAgreementSerializedCiphered.length;
			
			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(this.secureBidMessageKeyExchangeAgreementSerializedCipheredAndSigned, serializationOffset,
					         this.secureBidMessageKeyExchangeAgreementSerializedCipheredSigned,
					         0, this.secureBidMessageKeyExchangeAgreementSerializedCipheredSigned.length);
			
			
			this.setIsSecureBidMessageKeyExchangeAgreementSerializedCipheredSigned(false);
			
		}
	}
}
