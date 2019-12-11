package main.java.messages.secure.common.key.exchange;

import java.io.FileInputStream;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.SignatureException;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.util.Collection;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.ShortBufferException;
import main.java.common.utils.CommonUtils;

public class SecureCommonKeyExchange {
		
	private byte[] secretSymmetricKeyInBytes;
	
	private byte[] secretHMACKeyForDoSMitigationInBytes;
	
	
	private byte[] secureCommonKeyExchangeSerialized;
	
	private boolean isSecureCommonKeyExchangeSerialized;
	
	
	private byte[] secureCommonKeyExchangeSerializedCiphered;
	
	private int sizeOfSecureCommonKeyExchangeSerializedCiphered;
	
	private boolean isSecureCommonKeyExchangeSerializedCiphered;
	
	
	private byte[] secureCommonKeyExchangeSerializedCipheredSigned;
	
	private boolean isSecureCommonKeyExchangeSerializedCipheredSigned;
	
	private int sizeOfSecureCommonKeyExchangeSerializedCipheredSigned;
	
	private boolean isSecureCommonKeyExchangeSerializedCipheredSignedVerified;
	
	private boolean isSecureCommonKeyExchangeSerializedCipheredSignedValid;
	
	
	private byte[] secureCommonKeyExchangeSerializedCipheredAndSigned;
	
	private boolean isSecureCommonKeyExchangeSerializedCipheredAndSigned;
	
	private String userPeerID;
	
	public SecureCommonKeyExchange(byte[] secretSymmetricKeyInBytes,
									   byte[] secretHMACKeyForDoSMitigationInBytes,
									   String userPeerID) {
			
		this.secretSymmetricKeyInBytes = secretSymmetricKeyInBytes;
		this.secretHMACKeyForDoSMitigationInBytes = secretHMACKeyForDoSMitigationInBytes;
		
		this.secureCommonKeyExchangeSerialized = null;
		this.isSecureCommonKeyExchangeSerialized = false;
		
		this.secureCommonKeyExchangeSerializedCiphered = null;
		this.sizeOfSecureCommonKeyExchangeSerializedCiphered = 0;
		this.isSecureCommonKeyExchangeSerializedCiphered = false;
		
		this.secureCommonKeyExchangeSerializedCipheredSigned = null;
		this.sizeOfSecureCommonKeyExchangeSerializedCipheredSigned = 0;
		this.isSecureCommonKeyExchangeSerializedCipheredSigned = false;
		
		this.secureCommonKeyExchangeSerializedCipheredAndSigned = null;
		this.isSecureCommonKeyExchangeSerializedCipheredAndSigned = false;
		
		this.userPeerID = userPeerID;
		
	}
	
	
	public SecureCommonKeyExchange(byte[] secureCommonKeyExchangeSerializedCipheredAndSigned,
								   int sizeOfSecureCommonKeyExchangeSerializedCiphered,
								   int sizeOfSecureCommonKeyExchangeSerializedCipheredSigned,
								   String userPeerID) {
		
		this.secureCommonKeyExchangeSerializedCipheredAndSigned = 
				secureCommonKeyExchangeSerializedCipheredAndSigned;
		this.isSecureCommonKeyExchangeSerializedCipheredAndSigned = true;
		
		this.secureCommonKeyExchangeSerializedCiphered = null;
		this.sizeOfSecureCommonKeyExchangeSerializedCiphered = 
				sizeOfSecureCommonKeyExchangeSerializedCiphered;
		this.isSecureCommonKeyExchangeSerializedCiphered = true;
		
		this.secureCommonKeyExchangeSerializedCipheredSigned = null;
		this.sizeOfSecureCommonKeyExchangeSerializedCipheredSigned = 
				sizeOfSecureCommonKeyExchangeSerializedCipheredSigned;
		this.isSecureCommonKeyExchangeSerializedCipheredSigned = true;
		
		this.secureCommonKeyExchangeSerialized = null;
		this.isSecureCommonKeyExchangeSerialized = true;
		
		this.secretSymmetricKeyInBytes = null;
		this.secretHMACKeyForDoSMitigationInBytes = null;
		
		this.userPeerID = userPeerID;
	}
	
		
	public byte[] getSecretSymmetricKeyInBytes() {
		return this.secretSymmetricKeyInBytes;
	}
	
	public byte[] getSecretHMACKeyForDoSMitigationInBytes() {
		return this.secretHMACKeyForDoSMitigationInBytes;
	}
	
	public byte[] getSecureCommonKeyExchangeSerialized() {
		return this.secureCommonKeyExchangeSerialized;
	}
	
	public boolean getIsSecureCommonKeyExchangeSerialized() {
		return this.isSecureCommonKeyExchangeSerialized;
	}
	
	public void setIsSecureCommonKeyExchangeSerialized
		  (boolean isSecureCommonKeyExchangeSerialized) {
		
		this.isSecureCommonKeyExchangeSerialized = 
				isSecureCommonKeyExchangeSerialized;
	
	}
	
	public byte[] getSecureCommonKeyExchangeSerializedCiphered() {
		return this.secureCommonKeyExchangeSerializedCiphered;
	}
	
	public int getSizeOfSecureCommonKeyExchangeSerializedCiphered() {
		return this.sizeOfSecureCommonKeyExchangeSerializedCiphered;
	}
	
	public boolean getIsSecureCommonKeyExchangeSerializedCiphered() {
		return this.isSecureCommonKeyExchangeSerializedCiphered;
	}
	
	public void setIsSecureCommonKeyExchangeSerializedCiphered
		  (boolean isSecureCommonKeyExchangeSerializedCiphered) {
		
		this.isSecureCommonKeyExchangeSerializedCiphered = 
				isSecureCommonKeyExchangeSerializedCiphered;
	
	}
	
	public byte[] getSecureCommonKeyExchangeSerializedCipheredSigned() {
		return this.secureCommonKeyExchangeSerializedCipheredSigned;
	}
	
	public boolean getIsSecureCommonKeyExchangeSerializedCipheredSigned() {
		return this.isSecureCommonKeyExchangeSerializedCipheredSigned;
	}
	
	public void setIsSecureCommonKeyExchangeSerializedCipheredSigned
		  (boolean isSecureCommonKeyExchangeSerializedCipheredSigned) {
		
		this.isSecureCommonKeyExchangeSerializedCipheredSigned = 
				isSecureCommonKeyExchangeSerializedCipheredSigned;
	
	}
	
	public int getSizeOfSecureCommonKeyExchangeSerializedCipheredSigned() {
		return this.sizeOfSecureCommonKeyExchangeSerializedCipheredSigned;
	}
	
	public boolean getIsSecureCommonKeyExchangeSerializedCipheredSignedVerified() {
		return this.isSecureCommonKeyExchangeSerializedCipheredSignedVerified;
	}
	
	public void setIsSecureCommonKeyExchangeSerializedCipheredSignedVerified
	      (boolean isSecureCommonKeyExchangeSerializedCipheredSignedVerified) {
		
		this.isSecureCommonKeyExchangeSerializedCipheredSignedVerified = 
				isSecureCommonKeyExchangeSerializedCipheredSignedVerified;
		
	}
	
	public boolean getIsSecureCommonKeyExchangeSerializedCipheredSignedValid() {
		return this.isSecureCommonKeyExchangeSerializedCipheredSignedValid;
	}
	
	public void setIsSecureCommonKeyExchangeSerializedCipheredSignedValid
    	  (boolean isSecureCommonKeyExchangeSerializedCipheredSignedValid) {
	
		this.isSecureCommonKeyExchangeSerializedCipheredSignedValid = 
				isSecureCommonKeyExchangeSerializedCipheredSignedValid;
		
	}
	
		
	public byte[] getSecureCommonKeyExchangeSerializedCipheredAndSigned() {
		return this.secureCommonKeyExchangeSerializedCipheredAndSigned;
	}
	
	public boolean getIsSecureCommonKeyExchangeSerializedCipheredAndSigned() {
		return this.isSecureCommonKeyExchangeSerializedCipheredAndSigned;
	}
	
	public void setIsSecureCommonKeyExchangeSerializedCipheredAndSigned
		  (boolean isSecureCommonKeyExchangeSerializedCipheredAndSigned) {
		
		this.isSecureCommonKeyExchangeSerializedCipheredAndSigned = 
				isSecureCommonKeyExchangeSerializedCipheredAndSigned;
	
	}
	
	public void buildSecureCommonKeyExchangeToSend() 
		   throws InvalidKeyException, NoSuchAlgorithmException, SignatureException {

		boolean isPossibleToBuildSecureCommonKeyExchangeToSend = 
				( !this.getIsSecureCommonKeyExchangeSerialized() && 
				  !this.getIsSecureCommonKeyExchangeSerializedCiphered() && 
				  !this.getIsSecureCommonKeyExchangeSerializedCipheredSigned() &&
				  !this.getIsSecureCommonKeyExchangeSerializedCipheredAndSigned() );	

		if(isPossibleToBuildSecureCommonKeyExchangeToSend) {

			this.doSerializationOfSecureCommonKeyExchange();

			this.encryptSecureCommonKeyExchangeSerialized();
						
			this.signSecureCommonKeyExchangeSerializedCiphered();

			this.doSecureCommonKeyExchangeSerializedCipheredAndSigned();
			
		}

	}
		
	public void buildSecureCommonKeyExchangeReceived()
		   throws InvalidKeyException, NoSuchAlgorithmException, SignatureException {
		
		boolean isPossibleToBuildSecureCommonKeyExchangeReceived = 
				( this.getIsSecureCommonKeyExchangeSerialized() && 
				  this.getIsSecureCommonKeyExchangeSerializedCiphered() && 
				  this.getIsSecureCommonKeyExchangeSerializedCipheredSigned() &&
				  this.getIsSecureCommonKeyExchangeSerializedCipheredAndSigned() );	

		if(isPossibleToBuildSecureCommonKeyExchangeReceived) {
			
			this.undoSecureCommonKeyExchangeSerializedCipheredAndSigned();
						
			if(this.checkIfSecureCommonKeyExchangeSerializedCipheredSignedIsValid()) {
				
				this.decryptSecureCommonKeyExchangeSerialized();
				
				this.undoSerializationOfSecureCommonKeyExchange();
				
			}
			
		}
	}
	
	
	public void doSerializationOfSecureCommonKeyExchange() {
		
		boolean isPossibleToDoSerializationOfSecureCommonKeyExchange = 
				( !this.isSecureCommonKeyExchangeSerialized && 
				  !this.isSecureCommonKeyExchangeSerializedCiphered &&
				  !this.isSecureCommonKeyExchangeSerializedCipheredSigned &&
				  !this.isSecureCommonKeyExchangeSerializedCipheredAndSigned);
		
		if(isPossibleToDoSerializationOfSecureCommonKeyExchange) {
			
			int sizeOfSecureKeyExchangeSerialized = 
					( this.secretSymmetricKeyInBytes.length +
					  this.secretHMACKeyForDoSMitigationInBytes.length);
			
			this.secureCommonKeyExchangeSerialized = 
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
			System.arraycopy(this.secretSymmetricKeyInBytes, 0, this.secureCommonKeyExchangeSerialized,
							 serializationOffset, this.secretSymmetricKeyInBytes.length);
			serializationOffset += this.secretSymmetricKeyInBytes.length;
			
			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(this.secretHMACKeyForDoSMitigationInBytes, 0, this.secureCommonKeyExchangeSerialized,
					         serializationOffset, this.secretHMACKeyForDoSMitigationInBytes.length);
			
			
			
			this.setIsSecureCommonKeyExchangeSerialized(true);
			
		}
		
	}
	
	public void undoSerializationOfSecureCommonKeyExchange() {
		
		boolean isPossibleToUndoSerializationOfSecureCommonKeyExchange = 
				(  this.isSecureCommonKeyExchangeSerialized && 
				  !this.isSecureCommonKeyExchangeSerializedCiphered &&
				  !this.isSecureCommonKeyExchangeSerializedCipheredSigned &&
				  !this.isSecureCommonKeyExchangeSerializedCipheredAndSigned);
		
		if(isPossibleToUndoSerializationOfSecureCommonKeyExchange) {
			
			this.secretSymmetricKeyInBytes = new byte[CommonUtils.LENGTH_256_BITS_IN_BYTES];
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
			System.arraycopy(this.secureCommonKeyExchangeSerialized, serializationOffset, this.secretSymmetricKeyInBytes,
							 0, this.secretSymmetricKeyInBytes.length);
			serializationOffset += this.secretSymmetricKeyInBytes.length;
			
			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(this.secureCommonKeyExchangeSerialized, serializationOffset, this.secretHMACKeyForDoSMitigationInBytes,
					         0, this.secretHMACKeyForDoSMitigationInBytes.length);
			
			this.setIsSecureCommonKeyExchangeSerialized(false);
			
		}
		
	}
	
	public void encryptSecureCommonKeyExchangeSerialized() {
		
		boolean isPossibleToEncryptSecureCommonKeyExchangeSerialized = 
				(  this.isSecureCommonKeyExchangeSerialized && 
				  !this.isSecureCommonKeyExchangeSerializedCiphered &&
				  !this.isSecureCommonKeyExchangeSerializedCipheredSigned &&
				  !this.isSecureCommonKeyExchangeSerializedCipheredAndSigned);
		
		
		if(isPossibleToEncryptSecureCommonKeyExchangeSerialized) {
			
			Key secretKeyBytes = readKeysFromKeystore("auctionServer").getPublic();  //TODO Public Key of the Server
			
			try {
				
				String symmetricEncryptionAlgorithm = "RSA";
				String symmetricEncryptionMode = "None";
		 	    String symmetricEncryptionPadding = "NoPadding";
				
				String provider = "BC";
				Cipher secureCommonKeyExchangeAgreeementSymmetricEncryptionCipher = 
						Cipher.getInstance(String.format("%s/%s/%s",
										   symmetricEncryptionAlgorithm, symmetricEncryptionMode, symmetricEncryptionPadding), 
								           provider );
				
				secureCommonKeyExchangeAgreeementSymmetricEncryptionCipher
					.init(Cipher.ENCRYPT_MODE, secretKeyBytes, new SecureRandom());

				
				this.secureCommonKeyExchangeSerializedCiphered = 
						secureCommonKeyExchangeAgreeementSymmetricEncryptionCipher
						.doFinal(this.secureCommonKeyExchangeSerialized);
				
				System.err.println("Ciphered data size: " + this.secureCommonKeyExchangeSerializedCiphered.length);
				
				this.setIsSecureCommonKeyExchangeSerializedCiphered(true);		
				
			}
			catch (NoSuchAlgorithmException noSuchAlgorithmException) {
				System.err.println("Error occurred during the Symmetric Encryption over the Secure Message's Payload:");
				System.err.println("- Cryptographic Algorithm not found!!!");
				noSuchAlgorithmException.printStackTrace();
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
	
	public void decryptSecureCommonKeyExchangeSerialized() {
		
		boolean isPossibleToDecryptSecureCommonKeyExchangeSerializedCiphered = 
				(  this.isSecureCommonKeyExchangeSerialized && 
				   this.isSecureCommonKeyExchangeSerializedCiphered &&
				  !this.isSecureCommonKeyExchangeSerializedCipheredSigned &&
				  !this.isSecureCommonKeyExchangeSerializedCipheredAndSigned);
		
		if(isPossibleToDecryptSecureCommonKeyExchangeSerializedCiphered) {
			
			Key secretKeyBytes = readKeysFromKeystore("auctionServer").getPrivate(); //TODO Private Key of the Server
			
			try {
				
				String symmetricEncryptionAlgorithm = "RSA";
				String symmetricEncryptionMode = "None";
		 	    String symmetricEncryptionPadding = "NoPadding";
				
				String provider = "BC";
				Cipher secureCommonKeyExchangeAgreeementSymmetricEncryptionDecipher = 
						Cipher.getInstance(String.format("%s/%s/%s",
										   symmetricEncryptionAlgorithm, symmetricEncryptionMode, symmetricEncryptionPadding), 
								           provider);
				
			
				secureCommonKeyExchangeAgreeementSymmetricEncryptionDecipher
					.init(Cipher.DECRYPT_MODE, secretKeyBytes);
				
				this.sizeOfSecureCommonKeyExchangeSerializedCiphered = 
						this.secureCommonKeyExchangeSerializedCiphered.length;
				
			  	// The Plain Text of the bytes of the data input received through the communication channel
			  	this.secureCommonKeyExchangeSerialized = 
			  			new byte[ secureCommonKeyExchangeAgreeementSymmetricEncryptionDecipher
			  	                  .getOutputSize(this.sizeOfSecureCommonKeyExchangeSerializedCiphered) ];
			    
			  	int sizeOfSecureCommonKeyExchangeSerialized = 
			  											 secureCommonKeyExchangeAgreeementSymmetricEncryptionDecipher
				  									     .update(this.secureCommonKeyExchangeSerializedCiphered, 
				  										   	     0, this.sizeOfSecureCommonKeyExchangeSerializedCiphered,
				  											     this.secureCommonKeyExchangeSerialized, 0);
			  	
			  	secureCommonKeyExchangeAgreeementSymmetricEncryptionDecipher
			  									   .doFinal(this.secureCommonKeyExchangeSerialized,
			  											    sizeOfSecureCommonKeyExchangeSerialized);
			    
			  	
				this.setIsSecureCommonKeyExchangeSerializedCiphered(false);		
				
			}
			catch (NoSuchAlgorithmException noSuchAlgorithmException) {
				System.err.println("Error occurred during the Symmetric Encryption over the Secure Bid Message's Signature Proposal:");
				System.err.println("- Cryptographic Algorithm not found!!!");
				noSuchAlgorithmException.printStackTrace();
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
	
	
	public void signSecureCommonKeyExchangeSerializedCiphered()
		   throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
		
		boolean isPossibleToSignSecureCommonKeyExchangeSerializedCiphered = 
				(  this.isSecureCommonKeyExchangeSerialized && 
				   this.isSecureCommonKeyExchangeSerializedCiphered &&
				  !this.isSecureCommonKeyExchangeSerializedCipheredSigned &&
				  !this.isSecureCommonKeyExchangeSerializedCipheredAndSigned);
		
		
		if(isPossibleToSignSecureCommonKeyExchangeSerializedCiphered) {
			
			Signature secureCommonKeyExchangeSerializedCipheredSignature = 
					  Signature.getInstance("SHA256withRSA");
			
			PrivateKey userClientPrivateKey = readKeysFromKeystore(userPeerID).getPrivate(); //TODO Private Key to Sign contained in the KeyStore of the User
			
			secureCommonKeyExchangeSerializedCipheredSignature.initSign(userClientPrivateKey);
			
			secureCommonKeyExchangeSerializedCipheredSignature
			.update(this.secureCommonKeyExchangeSerializedCiphered);
			
			this.secureCommonKeyExchangeSerializedCipheredSigned = 
				 secureCommonKeyExchangeSerializedCipheredSignature.sign();
			
			
			
			this.setIsSecureCommonKeyExchangeSerializedCipheredSigned(true);
			
		}
		
	}
	
	
	public boolean checkIfSecureCommonKeyExchangeSerializedCipheredSignedIsValid()
		   throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
			
		boolean isPossibleToVerifySecureCommonKeyExchangeSerializedCipheredSigned = 
				(  this.isSecureCommonKeyExchangeSerialized && 
				   this.isSecureCommonKeyExchangeSerializedCiphered &&
				   this.isSecureCommonKeyExchangeSerializedCipheredSigned &&
				  !this.isSecureCommonKeyExchangeSerializedCipheredAndSigned);
		
		
		if(isPossibleToVerifySecureCommonKeyExchangeSerializedCipheredSigned) {
			
			Signature secureCommonKeyExchangeSerializedCipheredSignature = 
					  Signature.getInstance("SHA256withRSA");
			
			PublicKey userClientPublicKey = readCertificate(userPeerID).getPublicKey(); //TODO Public Key or Certificate of the User contained in the Server 
			
			secureCommonKeyExchangeSerializedCipheredSignature.initVerify(userClientPublicKey);
			
			secureCommonKeyExchangeSerializedCipheredSignature
			.update(this.secureCommonKeyExchangeSerializedCiphered);
			
			this.isSecureCommonKeyExchangeSerializedCipheredSignedValid = 
					secureCommonKeyExchangeSerializedCipheredSignature
					.verify(this.secureCommonKeyExchangeSerializedCipheredSigned);
		
			
			if(this.isSecureCommonKeyExchangeSerializedCipheredSignedValid) {
				
				System.out.println("Valid Signature!!!");
				
			}
	        else {
	        	
	        	System.out.println("Invalid Signature!!!");
	        	
	        }
			
			this.setIsSecureCommonKeyExchangeSerializedCipheredSignedVerified(true);
			this.setIsSecureCommonKeyExchangeSerializedCipheredSigned(false);
			
			
			return this.isSecureCommonKeyExchangeSerializedCipheredSignedValid;
					
		}
		
		return false;
		
	}

	public void doSecureCommonKeyExchangeSerializedCipheredAndSigned() {
		
		boolean isPossibleToDoSecureCommonKeyExchangeSerializedCipheredAndSigned = 
				(  this.isSecureCommonKeyExchangeSerialized && 
				   this.isSecureCommonKeyExchangeSerializedCiphered &&
				   this.isSecureCommonKeyExchangeSerializedCipheredSigned &&
				  !this.isSecureCommonKeyExchangeSerializedCipheredAndSigned);
		
		
		if(isPossibleToDoSecureCommonKeyExchangeSerializedCipheredAndSigned) {
			
			int sizeOfSecureCommonKeyExchangeSerializedCipheredAndSigned =
					( this.secureCommonKeyExchangeSerializedCiphered.length +
					  this.secureCommonKeyExchangeSerializedCipheredSigned.length );
			
			this.secureCommonKeyExchangeSerializedCipheredAndSigned = 
					new byte[ sizeOfSecureCommonKeyExchangeSerializedCipheredAndSigned ];

			
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
			System.arraycopy(this.secureCommonKeyExchangeSerializedCiphered, 0,
					         this.secureCommonKeyExchangeSerializedCipheredAndSigned,
							 serializationOffset, this.secureCommonKeyExchangeSerializedCiphered.length);
			serializationOffset += this.secureCommonKeyExchangeSerializedCiphered.length;
			
			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(this.secureCommonKeyExchangeSerializedCipheredSigned, 0,
					         this.secureCommonKeyExchangeSerializedCipheredAndSigned,
					         serializationOffset, this.secureCommonKeyExchangeSerializedCiphered.length);
			
			
			this.setIsSecureCommonKeyExchangeSerializedCipheredAndSigned(true);
			
		}
		
	}
	
	public void undoSecureCommonKeyExchangeSerializedCipheredAndSigned() {
		
		boolean isPossibleToUndoSecureCommonKeyExchangeSerializedCipheredAndSigned = 
				(  this.isSecureCommonKeyExchangeSerialized && 
				   this.isSecureCommonKeyExchangeSerializedCiphered &&
				   this.isSecureCommonKeyExchangeSerializedCipheredSigned &&
				   this.isSecureCommonKeyExchangeSerializedCipheredAndSigned);
		
		
		if(isPossibleToUndoSecureCommonKeyExchangeSerializedCipheredAndSigned) {
			
			this.secureCommonKeyExchangeSerializedCiphered = 
					new byte[ this.sizeOfSecureCommonKeyExchangeSerializedCiphered ];

			this.secureCommonKeyExchangeSerializedCipheredSigned = 
					new byte[ this.sizeOfSecureCommonKeyExchangeSerializedCipheredSigned ];
			
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
			System.arraycopy(this.secureCommonKeyExchangeSerializedCipheredAndSigned, serializationOffset,
							 this.secureCommonKeyExchangeSerializedCiphered,
							 0, this.secureCommonKeyExchangeSerializedCiphered.length);
			serializationOffset += this.secureCommonKeyExchangeSerializedCiphered.length;
			
			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(this.secureCommonKeyExchangeSerializedCipheredAndSigned, serializationOffset,
					         this.secureCommonKeyExchangeSerializedCipheredSigned,
					         0, this.secureCommonKeyExchangeSerializedCipheredSigned.length);
			
			
			this.setIsSecureCommonKeyExchangeSerializedCipheredAndSigned(false);
			
		}
	}
	
	private KeyPair readKeysFromKeystore(String alias) {
		KeyPair kp = null;
		try {
			FileInputStream inputStream = new FileInputStream("res/keystores/" + alias + "Keystore.jks");
			KeyStore keystore = KeyStore.getInstance(KeyStore.getDefaultType());
			char[] password = (alias + "1920").toCharArray();
			keystore.load(inputStream, password);
			Key key = keystore.getKey(alias, password);
			if(key instanceof PrivateKey) {
				Certificate cert = keystore.getCertificateChain(alias)[0];
				PublicKey publicKey = cert.getPublicKey();
				kp = new KeyPair(publicKey, (PrivateKey)key);
			}
			//            String publicKeyString = Base64.toBase64String(kp.getPublic().getEncoded());
			//            String privateKeyString = Base64.toBase64String(kp.getPrivate().getEncoded());
			//            System.out.println("Alias " + alias + " public string is: " + publicKeyString);
			//            System.out.println("Alias " + alias + " private string is: " + privateKeyString);
		} catch (Exception e) {
			e.getCause();
			e.getMessage();
			e.printStackTrace();
		}
		return kp;
	}

	private Certificate readCertificate(String alias) {
		Certificate cert = null;
		try {
			FileInputStream inputStream = new FileInputStream("res/certificates/" + alias + "Chain.pem");
			CertificateFactory certFactory = CertificateFactory.getInstance("X.509");
			Collection<? extends Certificate> certificates = certFactory.generateCertificates(inputStream);
			cert = (Certificate) certificates.toArray()[certificates.size() - 1];
			//			PublicKey pk = cert.getPublicKey();
			//			System.out.println(user + " public key is: " + Base64.toBase64String(pk.getEncoded()));
		} catch (Exception e) {
			e.getCause();
			e.getMessage();
			e.printStackTrace();
		}
		return cert;
	}
	
}
