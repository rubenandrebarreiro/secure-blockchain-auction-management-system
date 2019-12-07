package main.java.messages.secure.bid.key.exchange;

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

public class SecureBidMessageKeyExchange {
		
	private byte[] secretSymmetricKeyForDataPersonalInBytes;
	
	private byte[] secretHMACKeyForDoSMitigationInBytes;
	
	
	private byte[] secureBidMessageKeyExchangeSerialized;
	
	private boolean isSecureBidMessageKeyExchangeSerialized;
	
	
	private byte[] secureBidMessageKeyExchangeSerializedCiphered;
	
	private int sizeOfSecureBidMessageKeyExchangeSerializedCiphered;
	
	private boolean isSecureBidMessageKeyExchangeSerializedCiphered;
	
	
	private byte[] secureBidMessageKeyExchangeSerializedCipheredSigned;
	
	private boolean isSecureBidMessageKeyExchangeSerializedCipheredSigned;
	
	private int sizeOfSecureBidMessageKeyExchangeSerializedCipheredSigned;
	
	private boolean isSecureBidMessageKeyExchangeSerializedCipheredSignedVerified;
	
	private boolean isSecureBidMessageKeyExchangeSerializedCipheredSignedValid;
	
	
	private byte[] secureBidMessageKeyExchangeSerializedCipheredAndSigned;
	
	private boolean isSecureBidMessageKeyExchangeSerializedCipheredAndSigned;
	
	
	
	public SecureBidMessageKeyExchange(byte[] secretSymmetricKeyForDataPersonalInBytes,
									   byte[] secretHMACKeyForDoSMitigationInBytes) {
			
		this.secretSymmetricKeyForDataPersonalInBytes = secretSymmetricKeyForDataPersonalInBytes;
		this.secretHMACKeyForDoSMitigationInBytes = secretHMACKeyForDoSMitigationInBytes;
		
		this.secureBidMessageKeyExchangeSerialized = null;
		this.isSecureBidMessageKeyExchangeSerialized = false;
		
		this.secureBidMessageKeyExchangeSerializedCiphered = null;
		this.sizeOfSecureBidMessageKeyExchangeSerializedCiphered = 0;
		this.isSecureBidMessageKeyExchangeSerializedCiphered = false;
		
		this.secureBidMessageKeyExchangeSerializedCipheredSigned = null;
		this.sizeOfSecureBidMessageKeyExchangeSerializedCipheredSigned = 0;
		this.isSecureBidMessageKeyExchangeSerializedCipheredSigned = false;
		
		this.secureBidMessageKeyExchangeSerializedCipheredAndSigned = null;
		this.isSecureBidMessageKeyExchangeSerializedCipheredAndSigned = false;
		
	}
	
	
	public SecureBidMessageKeyExchange(byte[] secureBidMessageKeyExchangeSerializedCipheredAndSigned,
									   int sizeOfSecureBidMessageKeyExchangeSerializedCiphered,
									   int sizeOfSecureBidMessageKeyExchangeSerializedCipheredSigned) {
		
		this.secureBidMessageKeyExchangeSerializedCipheredAndSigned = 
				secureBidMessageKeyExchangeSerializedCipheredAndSigned;
		this.isSecureBidMessageKeyExchangeSerializedCipheredAndSigned = true;
		
		this.secureBidMessageKeyExchangeSerializedCiphered = null;
		this.sizeOfSecureBidMessageKeyExchangeSerializedCiphered = 
				sizeOfSecureBidMessageKeyExchangeSerializedCiphered;
		this.isSecureBidMessageKeyExchangeSerializedCiphered = true;
		
		this.secureBidMessageKeyExchangeSerializedCipheredSigned = null;
		this.sizeOfSecureBidMessageKeyExchangeSerializedCipheredSigned = 
				sizeOfSecureBidMessageKeyExchangeSerializedCipheredSigned;
		this.isSecureBidMessageKeyExchangeSerializedCipheredSigned = true;
		
		this.secureBidMessageKeyExchangeSerialized = null;
		this.isSecureBidMessageKeyExchangeSerialized = true;
		
		this.secretSymmetricKeyForDataPersonalInBytes = null;
		this.secretHMACKeyForDoSMitigationInBytes = null;
		
	}
	
		
	public byte[] getSecretSymmetricKeyForDataPersonalInBytes() {
		return this.secretSymmetricKeyForDataPersonalInBytes;
	}
	
	public byte[] getSecretHMACKeyForDoSMitigationInBytes() {
		return this.secretHMACKeyForDoSMitigationInBytes;
	}
	
	public byte[] getSecureBidMessageKeyExchangeSerialized() {
		return this.secureBidMessageKeyExchangeSerialized;
	}
	
	public boolean getIsSecureBidMessageKeyExchangeSerialized() {
		return this.isSecureBidMessageKeyExchangeSerialized;
	}
	
	public void setIsSecureBidMessageKeyExchangeSerialized
		  (boolean isSecureBidMessageKeyExchangeSerialized) {
		
		this.isSecureBidMessageKeyExchangeSerialized = 
				isSecureBidMessageKeyExchangeSerialized;
	
	}
	
	public byte[] getSecureBidMessageKeyExchangeSerializedCiphered() {
		return this.secureBidMessageKeyExchangeSerializedCiphered;
	}
	
	public int getSizeOfSecureBidMessageKeyExchangeSerializedCiphered() {
		return this.sizeOfSecureBidMessageKeyExchangeSerializedCiphered;
	}
	
	public boolean getIsSecureBidMessageKeyExchangeSerializedCiphered() {
		return this.isSecureBidMessageKeyExchangeSerializedCiphered;
	}
	
	public void setIsSecureBidMessageKeyExchangeSerializedCiphered
		  (boolean isSecureBidMessageKeyExchangeSerializedCiphered) {
		
		this.isSecureBidMessageKeyExchangeSerializedCiphered = 
				isSecureBidMessageKeyExchangeSerializedCiphered;
	
	}
	
	public byte[] getSecureBidMessageKeyExchangeSerializedCipheredSigned() {
		return this.secureBidMessageKeyExchangeSerializedCipheredSigned;
	}
	
	public boolean getIsSecureBidMessageKeyExchangeSerializedCipheredSigned() {
		return this.isSecureBidMessageKeyExchangeSerializedCipheredSigned;
	}
	
	public void setIsSecureBidMessageKeyExchangeSerializedCipheredSigned
		  (boolean isSecureBidMessageKeyExchangeSerializedCipheredSigned) {
		
		this.isSecureBidMessageKeyExchangeSerializedCipheredSigned = 
				isSecureBidMessageKeyExchangeSerializedCipheredSigned;
	
	}
	
	public int getSizeOfSecureBidMessageKeyExchangeSerializedCipheredSigned() {
		return this.sizeOfSecureBidMessageKeyExchangeSerializedCipheredSigned;
	}
	
	public boolean getIsSecureBidMessageKeyExchangeSerializedCipheredSignedVerified() {
		return this.isSecureBidMessageKeyExchangeSerializedCipheredSignedVerified;
	}
	
	public void setIsSecureBidMessageKeyExchangeSerializedCipheredSignedVerified
	      (boolean isSecureBidMessageKeyExchangeSerializedCipheredSignedVerified) {
		
		this.isSecureBidMessageKeyExchangeSerializedCipheredSignedVerified = 
				isSecureBidMessageKeyExchangeSerializedCipheredSignedVerified;
		
	}
	
	public boolean getIsSecureBidMessageKeyExchangeSerializedCipheredSignedValid() {
		return this.isSecureBidMessageKeyExchangeSerializedCipheredSignedValid;
	}
	
	public void setIsSecureBidMessageKeyExchangeSerializedCipheredSignedValid
    	  (boolean isSecureBidMessageKeyExchangeSerializedCipheredSignedValid) {
	
		this.isSecureBidMessageKeyExchangeSerializedCipheredSignedValid = 
				isSecureBidMessageKeyExchangeSerializedCipheredSignedValid;
		
	}
	
		
	public byte[] getSecureBidMessageKeyExchangeSerializedCipheredAndSigned() {
		return this.secureBidMessageKeyExchangeSerializedCipheredAndSigned;
	}
	
	public boolean getIsSecureBidMessageKeyExchangeSerializedCipheredAndSigned() {
		return this.isSecureBidMessageKeyExchangeSerializedCipheredAndSigned;
	}
	
	public void setIsSecureBidMessageKeyExchangeSerializedCipheredAndSigned
		  (boolean isSecureBidMessageKeyExchangeSerializedCipheredAndSigned) {
		
		this.isSecureBidMessageKeyExchangeSerializedCipheredAndSigned = 
				isSecureBidMessageKeyExchangeSerializedCipheredAndSigned;
	
	}
	
	public void buildSecureBidMessageKeyExchangeToSend() 
		   throws InvalidKeyException, NoSuchAlgorithmException, SignatureException {

		boolean isPossibleToBuildSecureBidMessageKeyExchangeToSend = 
				( !this.getIsSecureBidMessageKeyExchangeSerialized() && 
				  !this.getIsSecureBidMessageKeyExchangeSerializedCiphered() && 
				  !this.getIsSecureBidMessageKeyExchangeSerializedCipheredSigned() &&
				  !this.getIsSecureBidMessageKeyExchangeSerializedCipheredAndSigned() );	

		if(isPossibleToBuildSecureBidMessageKeyExchangeToSend) {

			this.doSerializationOfSecureBidMessageKeyExchange();

			this.encryptSecureBidMessageKeyExchangeSerialized();
						
			this.signSecureBidMessageKeyExchangeSerializedCiphered();

			this.doSecureBidMessageKeyExchangeSerializedCipheredAndSigned();
			
		}

	}
		
	public void buildSecureBidMessageDataPersonalReceived()
		   throws InvalidKeyException, NoSuchAlgorithmException, SignatureException {
		
		boolean isPossibleToBuildSecureBidMessageKeyExchangeReceived = 
				( this.getIsSecureBidMessageKeyExchangeSerialized() && 
				  this.getIsSecureBidMessageKeyExchangeSerializedCiphered() && 
				  this.getIsSecureBidMessageKeyExchangeSerializedCipheredSigned() &&
				  this.getIsSecureBidMessageKeyExchangeSerializedCipheredAndSigned() );	

		if(isPossibleToBuildSecureBidMessageKeyExchangeReceived) {
			
			this.undoSecureBidMessageKeyExchangeSerializedCipheredAndSigned();
						
			if(this.checkIfSecureBidMessageKeyExchangeSerializedCipheredSignedIsValid()) {
				
				this.decryptSecureBidMessageKeyExchangeSerialized();
				
				this.undoSecureBidMessageKeyExchangeSerializedCipheredAndSigned();

			}
			
		}
	}
	
	
	public void doSerializationOfSecureBidMessageKeyExchange() {
		
		boolean isPossibleToDoSerializationOfSecureBidMessageKeyExchange = 
				( !this.isSecureBidMessageKeyExchangeSerialized && 
				  !this.isSecureBidMessageKeyExchangeSerializedCiphered &&
				  !this.isSecureBidMessageKeyExchangeSerializedCipheredSigned &&
				  !this.isSecureBidMessageKeyExchangeSerializedCipheredAndSigned);
		
		if(isPossibleToDoSerializationOfSecureBidMessageKeyExchange) {
			
			int sizeOfSecureKeyExchangeSerialized = 
					( this.secretSymmetricKeyForDataPersonalInBytes.length +
					  this.secretHMACKeyForDoSMitigationInBytes.length);
			
			this.secureBidMessageKeyExchangeSerialized = 
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
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(this.secretSymmetricKeyForDataPersonalInBytes, 0, this.secureBidMessageKeyExchangeSerialized,
							 serializationOffset, this.secretSymmetricKeyForDataPersonalInBytes.length);
			serializationOffset += this.secretSymmetricKeyForDataPersonalInBytes.length;
			
			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(this.secretHMACKeyForDoSMitigationInBytes, 0, this.secureBidMessageKeyExchangeSerialized,
					         serializationOffset, this.secretHMACKeyForDoSMitigationInBytes.length);
			
			
			
			this.setIsSecureBidMessageKeyExchangeSerialized(true);
			
		}
		
	}
	
	public void undoSerializationOfSecureBidMessageKeyExchange() {
		
		boolean isPossibleToUndoSerializationOfSecureBidMessageKeyExchange = 
				(  this.isSecureBidMessageKeyExchangeSerialized && 
				  !this.isSecureBidMessageKeyExchangeSerializedCiphered &&
				  !this.isSecureBidMessageKeyExchangeSerializedCipheredSigned &&
				  !this.isSecureBidMessageKeyExchangeSerializedCipheredAndSigned);
		
		if(isPossibleToUndoSerializationOfSecureBidMessageKeyExchange) {
			
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
			System.arraycopy(this.secureBidMessageKeyExchangeSerialized, serializationOffset, this.secretSymmetricKeyForDataPersonalInBytes,
							 0, this.secretSymmetricKeyForDataPersonalInBytes.length);
			serializationOffset += this.secretSymmetricKeyForDataPersonalInBytes.length;
			
			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(this.secureBidMessageKeyExchangeSerialized, 0, this.secretHMACKeyForDoSMitigationInBytes,
					         0, this.secretHMACKeyForDoSMitigationInBytes.length);
			
			this.setIsSecureBidMessageKeyExchangeSerialized(false);
			
		}
		
	}
	
	public void encryptSecureBidMessageKeyExchangeSerialized() {
		
		boolean isPossibleToEncryptSecureBidMessageKeyExchangeSerialized = 
				(  this.isSecureBidMessageKeyExchangeSerialized && 
				  !this.isSecureBidMessageKeyExchangeSerializedCiphered &&
				  !this.isSecureBidMessageKeyExchangeSerializedCipheredSigned &&
				  !this.isSecureBidMessageKeyExchangeSerializedCipheredAndSigned);
		
		
		if(isPossibleToEncryptSecureBidMessageKeyExchangeSerialized) {
			
			byte[] secretKeyBytes = null;  //TODO Public Key of the Server
			
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
					System.out.println("[SecureBidMessageKeyExchange.ENCRYPT] Cipher's Block Mode needs IV (Initialisation Vector)!!!");
					initialisationVectorBytes = 
							CommonUtils.generateIV(secureBidMessageKeyExchangeAgreeementSymmetricEncryptionCipher);
					
					// Showing the randomly defined IV (Initialisation Vector)
					System.out.println("[SecureBidMessageKeyExchange.ENCRYPT] - IV (Initialisation Vector) is:\n- " 
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
				
				this.secureBidMessageKeyExchangeSerializedCiphered = 
						secureBidMessageKeyExchangeAgreeementSymmetricEncryptionCipher
						.doFinal(this.secureBidMessageKeyExchangeSerialized);
				
				
				this.setIsSecureBidMessageKeyExchangeSerializedCiphered(true);		
				
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
	
	public void decryptSecureBidMessageKeyExchangeSerialized() {
		
		boolean isPossibleToDecryptSecureBidMessageKeyExchangeSerializedCiphered = 
				(  this.isSecureBidMessageKeyExchangeSerialized && 
				   this.isSecureBidMessageKeyExchangeSerializedCiphered &&
				  !this.isSecureBidMessageKeyExchangeSerializedCipheredSigned &&
				  !this.isSecureBidMessageKeyExchangeSerializedCipheredAndSigned);
		
		if(isPossibleToDecryptSecureBidMessageKeyExchangeSerializedCiphered) {
			
			byte[] secretKeyBytes = null; //TODO Private Key of the Server
			
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
								           provider);
				
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
				
				this.sizeOfSecureBidMessageKeyExchangeSerializedCiphered = 
						this.secureBidMessageKeyExchangeSerializedCiphered.length;
				
			  	// The Plain Text of the bytes of the data input received through the communication channel
			  	this.secureBidMessageKeyExchangeSerialized = 
			  			new byte[ secureBidMessageKeyExchangeAgreeementSymmetricEncryptionDecipher
			  	                  .getOutputSize(this.sizeOfSecureBidMessageKeyExchangeSerializedCiphered) ];
			    
			  	int sizeOfSecureBidMessageKeyExchangeSerialized = 
			  											 secureBidMessageKeyExchangeAgreeementSymmetricEncryptionDecipher
				  									     .update(this.secureBidMessageKeyExchangeSerializedCiphered, 
				  										   	     0, this.sizeOfSecureBidMessageKeyExchangeSerializedCiphered,
				  											     this.secureBidMessageKeyExchangeSerialized, 0);
			  	
			  	secureBidMessageKeyExchangeAgreeementSymmetricEncryptionDecipher
			  									   .doFinal(this.secureBidMessageKeyExchangeSerialized,
			  											    sizeOfSecureBidMessageKeyExchangeSerialized);
			    
			  	
				this.setIsSecureBidMessageKeyExchangeSerializedCiphered(false);		
				
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
	
	
	public void signSecureBidMessageKeyExchangeSerializedCiphered()
		   throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
		
		boolean isPossibleToSignSecureBidMessageKeyExchangeSerializedCiphered = 
				(  this.isSecureBidMessageKeyExchangeSerialized && 
				   this.isSecureBidMessageKeyExchangeSerializedCiphered &&
				  !this.isSecureBidMessageKeyExchangeSerializedCipheredSigned &&
				  !this.isSecureBidMessageKeyExchangeSerializedCipheredAndSigned);
		
		
		if(isPossibleToSignSecureBidMessageKeyExchangeSerializedCiphered) {
			
			Signature secureBidMessageKeyExchangeSerializedCipheredSignature = 
					  Signature.getInstance("SHA256withDSA");
			
			PrivateKey userClientPrivateKey = null; //TODO Private Key to Sign contained in the KeyStore of the User
			
			secureBidMessageKeyExchangeSerializedCipheredSignature.initSign(userClientPrivateKey);
			
			secureBidMessageKeyExchangeSerializedCipheredSignature
			.update(this.secureBidMessageKeyExchangeSerializedCiphered);
			
			this.secureBidMessageKeyExchangeSerializedCipheredSigned = 
				 secureBidMessageKeyExchangeSerializedCipheredSignature.sign();
			
			
			
			this.setIsSecureBidMessageKeyExchangeSerializedCipheredSigned(true);
			
		}
		
	}
	
	
	public boolean checkIfSecureBidMessageKeyExchangeSerializedCipheredSignedIsValid()
		   throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
			
		boolean isPossibleToVerifySecureBidMessageKeyExchangeSerializedCipheredSigned = 
				(  this.isSecureBidMessageKeyExchangeSerialized && 
				   this.isSecureBidMessageKeyExchangeSerializedCiphered &&
				   this.isSecureBidMessageKeyExchangeSerializedCipheredSigned &&
				  !this.isSecureBidMessageKeyExchangeSerializedCipheredAndSigned);
		
		
		if(isPossibleToVerifySecureBidMessageKeyExchangeSerializedCipheredSigned) {
			
			Signature secureBidMessageKeyExchangeSerializedCipheredSignature = 
					  Signature.getInstance("SHA256withDSA");
			
			PublicKey userClientPublicKey = null; //TODO Public Key or Certificate of the User contained in the Server 
			
			secureBidMessageKeyExchangeSerializedCipheredSignature.initVerify(userClientPublicKey);
			
			secureBidMessageKeyExchangeSerializedCipheredSignature
			.update(this.secureBidMessageKeyExchangeSerializedCiphered);
			
			this.isSecureBidMessageKeyExchangeSerializedCipheredSignedValid = 
					secureBidMessageKeyExchangeSerializedCipheredSignature
					.verify(this.secureBidMessageKeyExchangeSerializedCipheredSigned);
		
			
			if(this.isSecureBidMessageKeyExchangeSerializedCipheredSignedValid) {
				
				System.out.println("Valid Signature!!!");
				
			}
	        else {
	        	
	        	System.out.println("Invalid Signature!!!");
	        	
	        }
			
			this.setIsSecureBidMessageKeyExchangeSerializedCipheredSignedVerified(true);
			this.setIsSecureBidMessageKeyExchangeSerializedCipheredSigned(false);
			
			
			return this.isSecureBidMessageKeyExchangeSerializedCipheredSignedValid;
					
		}
		
		return false;
		
	}

	public void doSecureBidMessageKeyExchangeSerializedCipheredAndSigned() {
		
		boolean isPossibleToDoSecureBidMessageKeyExchangeSerializedCipheredAndSigned = 
				(  this.isSecureBidMessageKeyExchangeSerialized && 
				   this.isSecureBidMessageKeyExchangeSerializedCiphered &&
				   this.isSecureBidMessageKeyExchangeSerializedCipheredSigned &&
				  !this.isSecureBidMessageKeyExchangeSerializedCipheredAndSigned);
		
		
		if(isPossibleToDoSecureBidMessageKeyExchangeSerializedCipheredAndSigned) {
			
			int sizeOfSecureBidMessageKeyExchangeSerializedCipheredAndSigned =
					( this.secureBidMessageKeyExchangeSerializedCiphered.length +
					  this.secureBidMessageKeyExchangeSerializedCipheredSigned.length );
			
			this.secureBidMessageKeyExchangeSerializedCipheredAndSigned = 
					new byte[ sizeOfSecureBidMessageKeyExchangeSerializedCipheredAndSigned ];

			
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
			System.arraycopy(this.secureBidMessageKeyExchangeSerializedCiphered, 0,
					         this.secureBidMessageKeyExchangeSerializedCipheredAndSigned,
							 serializationOffset, this.secureBidMessageKeyExchangeSerializedCiphered.length);
			serializationOffset += this.secureBidMessageKeyExchangeSerializedCiphered.length;
			
			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(this.secureBidMessageKeyExchangeSerializedCipheredSigned, 0,
					         this.secureBidMessageKeyExchangeSerializedCipheredAndSigned,
					         serializationOffset, this.secureBidMessageKeyExchangeSerializedCiphered.length);
			
			
			this.setIsSecureBidMessageKeyExchangeSerializedCipheredAndSigned(true);
			
		}
		
	}
	
	public void undoSecureBidMessageKeyExchangeSerializedCipheredAndSigned() {
		
		boolean isPossibleToUndoSecureBidMessageKeyExchangeSerializedCipheredAndSigned = 
				( this.isSecureBidMessageKeyExchangeSerialized && 
				  this.isSecureBidMessageKeyExchangeSerializedCiphered &&
				  this.isSecureBidMessageKeyExchangeSerializedCipheredSigned &&
				  this.isSecureBidMessageKeyExchangeSerializedCipheredAndSigned);
		
		
		if(isPossibleToUndoSecureBidMessageKeyExchangeSerializedCipheredAndSigned) {
			
			this.secureBidMessageKeyExchangeSerializedCiphered = 
					new byte[ this.sizeOfSecureBidMessageKeyExchangeSerializedCiphered ];

			this.secureBidMessageKeyExchangeSerializedCipheredSigned = 
					new byte[ this.sizeOfSecureBidMessageKeyExchangeSerializedCipheredSigned ];
			
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
			System.arraycopy(this.secureBidMessageKeyExchangeSerializedCipheredAndSigned, serializationOffset,
							 this.secureBidMessageKeyExchangeSerializedCiphered,
							 0, this.secureBidMessageKeyExchangeSerializedCiphered.length);
			serializationOffset += this.secureBidMessageKeyExchangeSerializedCiphered.length;
			
			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(this.secureBidMessageKeyExchangeSerializedCipheredAndSigned, serializationOffset,
					         this.secureBidMessageKeyExchangeSerializedCipheredSigned,
					         0, this.secureBidMessageKeyExchangeSerializedCipheredSigned.length);
			
			
			this.setIsSecureBidMessageKeyExchangeSerializedCipheredSigned(false);
			
		}
	}
}
