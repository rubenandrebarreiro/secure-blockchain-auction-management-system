package main.java.messages.secure.receipt.components;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.security.SignatureException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.ShortBufferException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import main.java.common.utils.CommonUtils;
import main.java.messages.secure.common.header.SecureCommonHeader;
import main.java.messages.secure.receipt.components.data.SecureReceiptMessageComponentsData;

public class SecureReceiptMessageComponents {

	private SecureCommonHeader secureCommonHeader;
	
	private byte[] secureCommonHeaderSerialized;
	
	
	private SecureReceiptMessageComponentsData secureReceiptMessageComponentsData;
	
	private byte[] secureReceiptMessageComponentsDataSerialized;
	
	private int sizeOfSecureReceiptMessageComponentsDataSerialized;
	
	
	private int sizeOfSecureReceiptMessageComponentsDataInfoSerialized;
	
	private int sizeOfSecureReceiptMessageComponentsDataSignatureSerialized;
	
	private int sizeOfSecureReceiptMessageComponentsDataResponseSerialized;
	
	private int sizeOfBidderUserClientIDSerialized;
	
	
	private byte[] secureReceiptMessageComponentsSerialized;
	
	private int sizeOfSecureReceiptMessageComponentsSerialized;
	
	private boolean isSecureReceiptMessageComponentsSerialized;
	
	
	private byte[] secretSymmetricKeyForComponentsInBytes;
	
	private byte[] initialisationVectorInBytes;
	
	
	private byte[] secureReceiptMessageComponentsSerializedCiphered;
	
	private int sizeOfSecureReceiptMessageComponentsSerializedCiphered;
	
	
	private boolean isSecureReceiptMessageComponentsSerializedCiphered;
	
	
	private String userPeerID;
	
	
	
	public SecureReceiptMessageComponents(SecureCommonHeader secureCommonHeader,
										  SecureReceiptMessageComponentsData secureReceiptMessageComponentsData) {
		
		this.secureCommonHeader = secureCommonHeader;
		this.secureCommonHeaderSerialized = null;
		
		this.secureReceiptMessageComponentsData = secureReceiptMessageComponentsData;
		this.secureReceiptMessageComponentsDataSerialized = null;
		this.sizeOfSecureReceiptMessageComponentsDataSerialized = 0;
		
		this.sizeOfSecureReceiptMessageComponentsDataInfoSerialized = 0;
		this.sizeOfSecureReceiptMessageComponentsDataSignatureSerialized = 0;
		this.sizeOfSecureReceiptMessageComponentsDataResponseSerialized = 0;
		this.sizeOfBidderUserClientIDSerialized = 0;
		
		this.secureReceiptMessageComponentsSerialized = null;
		this.isSecureReceiptMessageComponentsSerialized = false;

		
		this.secretSymmetricKeyForComponentsInBytes = null;
		this.initialisationVectorInBytes = null;

		
		this.secureReceiptMessageComponentsSerializedCiphered = null;
		this.isSecureReceiptMessageComponentsSerializedCiphered = false;
		this.sizeOfSecureReceiptMessageComponentsSerializedCiphered = 0;
		
		this.userPeerID = null;
		
	}
	
	public SecureReceiptMessageComponents(byte[] secureReceiptMessageComponentsSerializedCiphered,
										  byte[] secretSymmetricKeyForComponentsInBytes,
										  byte[] initialisationVectorInBytes,
										  int sizeOfSecureReceiptMessageComponentsDataSerialized,
										  int sizeOfSecureReceiptMessageComponentsDataInfoSerialized,
										  int sizeOfSecureReceiptMessageComponentsDataSignatureSerialized,
										  int sizeOfSecureReceiptMessageComponentsDataResponseSerialized,
										  int sizeOfBidderUserClientIDSerialized,
										  String userPeerID) {
		
		this.secureReceiptMessageComponentsSerializedCiphered = secureReceiptMessageComponentsSerializedCiphered;
		this.sizeOfSecureReceiptMessageComponentsSerializedCiphered = 
						this.secureReceiptMessageComponentsSerializedCiphered.length;
		this.isSecureReceiptMessageComponentsSerializedCiphered = true;
		
		this.secretSymmetricKeyForComponentsInBytes = secretSymmetricKeyForComponentsInBytes;
		this.initialisationVectorInBytes = initialisationVectorInBytes;
		
		this.secureReceiptMessageComponentsSerialized = null;
		this.isSecureReceiptMessageComponentsSerialized = true;
		
		this.secureCommonHeader = null;
		this.secureCommonHeaderSerialized = null;
		
		this.secureReceiptMessageComponentsData = null;
		this.secureReceiptMessageComponentsDataSerialized = null;
		this.sizeOfSecureReceiptMessageComponentsDataSerialized = 
						sizeOfSecureReceiptMessageComponentsDataSerialized;
		
		this.sizeOfSecureReceiptMessageComponentsDataInfoSerialized = 
						sizeOfSecureReceiptMessageComponentsDataInfoSerialized;
		this.sizeOfSecureReceiptMessageComponentsDataSignatureSerialized = 
						sizeOfSecureReceiptMessageComponentsDataSignatureSerialized;
		this.sizeOfSecureReceiptMessageComponentsDataResponseSerialized = 
						sizeOfSecureReceiptMessageComponentsDataResponseSerialized;
		this.sizeOfBidderUserClientIDSerialized = sizeOfBidderUserClientIDSerialized;
		
		this.userPeerID = userPeerID;
		
	}
	
	
	public SecureCommonHeader getSecureCommonHeader() {
		return this.secureCommonHeader;
	}
	
	public byte[] getSecureCommonHeaderSerialized() {
		return this.secureCommonHeaderSerialized;
	}
	
	public SecureReceiptMessageComponentsData getSecureReceiptMessageComponentsData() {
		return this.secureReceiptMessageComponentsData;
	}
	
	public byte[] getSecureReceiptMessageComponentsDataSerialized() {
		return this.secureReceiptMessageComponentsDataSerialized;
	}
	
	public int getSizeOfSecureReceiptMessageComponentsDataSerialized() {
		return this.sizeOfSecureReceiptMessageComponentsDataSerialized;
	}
	
	
	
    public int getSizeOfSecureReceiptMessageComponentsDataInfoSerialized() {
    	return this.sizeOfSecureReceiptMessageComponentsDataInfoSerialized;
    }
	
	public int getSizeOfSecureReceiptMessageComponentsDataSignatureSerialized() {
		return this.sizeOfSecureReceiptMessageComponentsDataSignatureSerialized;
	}
	
	public int getSizeOfSecureReceiptMessageComponentsDataResponseSerialized() {
		return this.sizeOfSecureReceiptMessageComponentsDataResponseSerialized;
	}
	
	public int getSizeOfBidderUserClientIDSerialized() {
		return this.sizeOfBidderUserClientIDSerialized;
	}
	
	
	
	public byte[] getSecureReceiptMessageComponentsSerialized() {
		return this.secureReceiptMessageComponentsSerialized;
	}
	
	public int getSizeOfSecureReceiptMessageComponentsSerialized() {
		return this.sizeOfSecureReceiptMessageComponentsSerialized;
	}
	
	public boolean getIsSecureReceiptMessageComponentsSerialized() {
		return this.isSecureReceiptMessageComponentsSerialized;
	}
	
	public void setIsSecureReceiptMessageComponentsSerialized
		  (boolean isSecureReceiptMessageComponentsSerialized) {
		
		this.isSecureReceiptMessageComponentsSerialized =
				isSecureReceiptMessageComponentsSerialized;
		
	}
	
	
	public byte[] getSecretSymmetricKeyForComponentsInBytes() {
		return this.secretSymmetricKeyForComponentsInBytes;
	}
	
	public byte[] getInitialisationVectorInBytes() {
		return this.initialisationVectorInBytes;
	}
	
	
	public byte[] getSecureReceiptMessageComponentsSerializedCiphered() {
		return this.secureReceiptMessageComponentsSerializedCiphered;
	}
	
	public boolean getIsSecureReceiptMessageComponentsSerializedCiphered() {
		return this.isSecureReceiptMessageComponentsSerializedCiphered;
	}
	
	public void setIsSecureReceiptMessageComponentsSerializedCiphered
		  (boolean isSecureReceiptMessageComponentsSerializedCiphered) {
		
		this.isSecureReceiptMessageComponentsSerializedCiphered =
				isSecureReceiptMessageComponentsSerializedCiphered;
		
	}
	
	public int getSizeOfSecureReceiptMessageComponentsSerializedCiphered() {
		return this.sizeOfSecureReceiptMessageComponentsSerializedCiphered;
	}
	
	
	public String getUserPeerID() {
		return this.userPeerID;
	}
	
	
	
	public void doSecureReceiptMessageComponentsSerialization()
		   throws InvalidKeyException, SignatureException, NoSuchAlgorithmException {
		
		boolean isPossibleToDoSecureReceiptMessageComponentsSerialization = 
				( !this.isSecureReceiptMessageComponentsSerialized && 
				  !this.isSecureReceiptMessageComponentsSerializedCiphered );
		
		if( isPossibleToDoSecureReceiptMessageComponentsSerialization ) {
			
			this.secureCommonHeader.doSecureCommonHeaderSerialization();
			this.secureCommonHeaderSerialized = 
					this.secureCommonHeader.getSecureCommonHeaderSerialized();
			
			this.secureReceiptMessageComponentsData.doSecureReceiptMessageComponentsDataSerialization();
			this.secureReceiptMessageComponentsDataSerialized = 
					this.secureReceiptMessageComponentsData.getSecureReceiptMessageComponentsDataSerialized();
			this.sizeOfSecureReceiptMessageComponentsDataSerialized = 
					this.secureReceiptMessageComponentsDataSerialized.length;
			
			
			int sizeOfSecureReceiptMessageComponentsSerialized =
					( CommonUtils.COMMON_HEADER_LENGTH + 
					  this.sizeOfSecureReceiptMessageComponentsDataSerialized );
			
			this.secureReceiptMessageComponentsSerialized = 
					new byte[ sizeOfSecureReceiptMessageComponentsSerialized ];
			
			
			// Operations to Fill a Byte Array, with the following parameters:
			// 1) src - The source of the array to be copied
			// 2) srcPos - The position from the array to be copied, representing the first element to be copied
			// 3) dest - The destination of the array to be copied
			// 4) destPos - The position of the array where will be placed the new copy,
			//              representing the first element where new data will be placed
			// 5) length - The length of the data to be copied from the source array to the destination array
			
			// The offset related to fulfilment of the serialization process
			int serializationOffset = 0;
			
			// Fills the byte array of the Bid serialized with its ID,
			// From the initial position to the corresponding to the length of Bid's ID
			System.arraycopy(this.secureCommonHeaderSerialized,
							 0, this.secureReceiptMessageComponentsSerialized,
							 serializationOffset, this.secureCommonHeaderSerialized.length);
			serializationOffset += this.secureCommonHeaderSerialized.length;
			
			// Fills the byte array of the Bid serialized with its User/Client Bidder's ID,
			// From the position corresponding to the length of Bid's ID to
			// the corresponding of the length of Bid's User/Client Bidder's ID
			System.arraycopy(this.secureReceiptMessageComponentsDataSerialized,
							 0, this.secureReceiptMessageComponentsSerialized,
							 serializationOffset, this.secureReceiptMessageComponentsDataSerialized.length);
			
			
			this.setIsSecureReceiptMessageComponentsSerialized(true);
			
		}
		
	}
	
	public void undoSecureReceiptMessageComponentsSerialization() {
		
		boolean isPossibleToUndoSecureReceiptMessageComponentsSerialization = 
				(  this.isSecureReceiptMessageComponentsSerialized && 
				  !this.isSecureReceiptMessageComponentsSerializedCiphered );
		
		if( isPossibleToUndoSecureReceiptMessageComponentsSerialization ) {
			
			this.secureCommonHeaderSerialized = new byte[ CommonUtils.COMMON_HEADER_LENGTH ];
			this.secureReceiptMessageComponentsDataSerialized = 
					new byte[ this.sizeOfSecureReceiptMessageComponentsDataSerialized ];
			
			// Operations to Fill a Byte Array, with the following parameters:
			// 1) src - The source of the array to be copied
			// 2) srcPos - The position from the array to be copied, representing the first element to be copied
			// 3) dest - The destination of the array to be copied
			// 4) destPos - The position of the array where will be placed the new copy,
			//              representing the first element where new data will be placed
			// 5) length - The length of the data to be copied from the source array to the destination array
			
			// The offset related to fulfilment of the serialization process
			int serializationOffset = 0;
			
			// Fills the byte array of the Bid serialized with its ID,
			// From the initial position to the corresponding to the length of Bid's ID
			System.arraycopy(this.secureReceiptMessageComponentsSerialized,
							 serializationOffset, this.secureCommonHeaderSerialized,
							 0, this.secureCommonHeaderSerialized.length);
			serializationOffset += this.secureCommonHeaderSerialized.length;
			
			// Fills the byte array of the Bid serialized with its User/Client Bidder's ID,
			// From the position corresponding to the length of Bid's ID to
			// the corresponding of the length of Bid's User/Client Bidder's ID
			System.arraycopy(this.secureReceiptMessageComponentsSerialized,
							 serializationOffset, this.secureReceiptMessageComponentsDataSerialized,
							 0, this.secureReceiptMessageComponentsDataSerialized.length);
			
			
			this.secureCommonHeader = new SecureCommonHeader(this.secureCommonHeaderSerialized);
			
			this.secureCommonHeader.undoSecureCommonHeaderSerialization();
			
			
			
			this.secureReceiptMessageComponentsData = 
					new SecureReceiptMessageComponentsData(this.secureReceiptMessageComponentsDataSerialized,
														   this.sizeOfSecureReceiptMessageComponentsDataInfoSerialized,
														   this.sizeOfSecureReceiptMessageComponentsDataSignatureSerialized,
														   this.sizeOfSecureReceiptMessageComponentsDataResponseSerialized,
														   this.sizeOfBidderUserClientIDSerialized,
														   this.userPeerID);
			
			secureReceiptMessageComponentsData.undoSecureReceiptMessageComponentsDataSerialization();
			
			this.setIsSecureReceiptMessageComponentsSerialized(false);
			
		}
		
	}
	
	
	
	
	
	
	
	
	
	public void encryptSecureReceiptMessageComponents() {
		
		boolean isPossibleToEncryptSecureReceiptMessageComponents = 
			  (  this.isSecureReceiptMessageComponentsSerialized && 
			    !this.isSecureReceiptMessageComponentsSerializedCiphered);
			
		if(isPossibleToEncryptSecureReceiptMessageComponents) {
			
			try {
				byte[] key = CommonUtils.createKeyForAES(256, new SecureRandom()).getEncoded();
				this.secretSymmetricKeyForComponentsInBytes = key;
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchProviderException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} // TODO Symmetric Key generated on the fly with KeyGen (secretHMACKeyForDoSMitigationInBytes)

			try {

				String symmetricEncryptionAlgorithm = "AES";
				String symmetricEncryptionMode = "CBC";
				String symmetricEncryptionPadding = "PKCS7Padding";


				// Set the Secret Key and its specifications,
				// using the AES (Advanced Encryption Standard - Rijndael) Symmetric Encryption
				SecretKeySpec secretKeySpecifications = new SecretKeySpec(this.secretSymmetricKeyForComponentsInBytes, symmetricEncryptionAlgorithm);


				String provider = "BC";
				Cipher secureReceiptMessageComponentsSerializedSymmetricEncryptionCipher = 
						Cipher.getInstance(String.format("%s/%s/%s",
								symmetricEncryptionAlgorithm, symmetricEncryptionMode, symmetricEncryptionPadding), 
								provider );

				if(CommonUtils.blockModeRequiresIV(symmetricEncryptionMode)) {

					// Algorithms that don't need IV (Initialisation Vector): ECB
					// The parameter specifications for the IV (Initialisation Vector)	
//					System.out.println("[SecureReceiptMessageComponents.ENCRYPT] Cipher's Block Mode needs IV (Initialisation Vector)!!!");
					this.initialisationVectorInBytes = 
							CommonUtils.generateIV(secureReceiptMessageComponentsSerializedSymmetricEncryptionCipher);

					// Showing the randomly defined IV (Initialisation Vector)
//					System.out.println("[SecureReceiptMessageComponents.ENCRYPT] - IV (Initialisation Vector) is:\n- " 
//							+ CommonUtils.fromByteArrayToHexadecimalFormat(initialisationVectorInBytes));

					IvParameterSpec initializationVectorParameterSpecifications = new IvParameterSpec(initialisationVectorInBytes);
					
					secureReceiptMessageComponentsSerializedSymmetricEncryptionCipher
							.init(Cipher.ENCRYPT_MODE, secretKeySpecifications, initializationVectorParameterSpecifications);

				}
				else {

					// Algorithms that need IV (Initialisation Vector)
					// The parameter specifications for the IV (Initialisation Vector)
//					System.out.println("[SecureReceiptMessageComponents.ENCRYPT] Cipher's Block Mode doesn't needs IV (Initialisation Vector)!!!");

					secureReceiptMessageComponentsSerializedSymmetricEncryptionCipher
							.init(Cipher.ENCRYPT_MODE, secretKeySpecifications);

				}

				this.secureReceiptMessageComponentsSerializedCiphered = 
						secureReceiptMessageComponentsSerializedSymmetricEncryptionCipher
						.doFinal(this.secureReceiptMessageComponentsSerialized);

				this.sizeOfSecureReceiptMessageComponentsSerializedCiphered =
						secureReceiptMessageComponentsSerializedCiphered.length;

				this.setIsSecureReceiptMessageComponentsSerializedCiphered(true);		

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
	
	public void decryptSecureReceiptMessageComponents() {
		
		boolean isPossibleToDecryptSecureReceiptMessageComponents = 
				  (  this.isSecureReceiptMessageComponentsSerialized && 
				     this.isSecureReceiptMessageComponentsSerializedCiphered);
				
		
		if(isPossibleToDecryptSecureReceiptMessageComponents) {
			
			byte[] secretKeyBytes = this.secretSymmetricKeyForComponentsInBytes; // TODO Symmetric Key contained in the Envelope (secretHMACKeyForDoSMitigationInBytes)
			
			try {
				
				String symmetricEncryptionAlgorithm = "AES";
				String symmetricEncryptionMode = "CBC";
		 	    String symmetricEncryptionPadding = "NoPadding";
				
				
				// Set the Secret Key and its specifications,
		 		// using the AES (Advanced Encryption Standard - Rijndael) Symmetric Encryption
				SecretKeySpec secretKeySpecifications = new SecretKeySpec(secretKeyBytes, symmetricEncryptionAlgorithm);
				
				
				String provider = "BC";
				Cipher secureReceiptMessageComponentsSerializedSymmetricEncryptionDecipher = 
						Cipher.getInstance(String.format("%s/%s/%s",
										   symmetricEncryptionAlgorithm, symmetricEncryptionMode, symmetricEncryptionPadding), 
								           provider );
							
				if(CommonUtils.blockModeRequiresIV(symmetricEncryptionMode)) {
					
					// Algorithms that don't need IV (Initialisation Vector): ECB
					// The parameter specifications for the IV (Initialisation Vector)	
//					System.out.println("[SecureReceiptMessageComponents.DECRYPT] Cipher's Block Mode needs IV (Initialisation Vector)!!!");
					
					// Showing the randomly defined IV (Initialisation Vector)
//					System.out.println("[SecureReceiptMessageComponents.DECRYPT] - IV (Initialisation Vector) is:\n- " 
//									   + CommonUtils.fromByteArrayToHexadecimalFormat(this.initialisationVectorInBytes));
					
					IvParameterSpec initializationVectorParameterSpecifications = new IvParameterSpec(this.initialisationVectorInBytes);
					secureReceiptMessageComponentsSerializedSymmetricEncryptionDecipher
						.init(Cipher.DECRYPT_MODE, secretKeySpecifications, initializationVectorParameterSpecifications);
				}
				else {
					
					// Algorithms that need IV (Initialisation Vector)
					// The parameter specifications for the IV (Initialisation Vector)
//					System.out.println("[SecureReceiptMessageComponents.DECRYPT] Cipher's Block Mode doesn't needs IV (Initialisation Vector)!!!");
					
					secureReceiptMessageComponentsSerializedSymmetricEncryptionDecipher
						.init(Cipher.DECRYPT_MODE, secretKeySpecifications);
					
				}
				
				this.sizeOfSecureReceiptMessageComponentsSerializedCiphered = 
						this.secureReceiptMessageComponentsSerializedCiphered.length;
				
			  	// The Plain Text of the bytes of the SolvedBlock input received through the communication channel
			  	this.secureReceiptMessageComponentsSerialized = 
			  			new byte[ secureReceiptMessageComponentsSerializedSymmetricEncryptionDecipher
			  	                  .getOutputSize(this.sizeOfSecureReceiptMessageComponentsSerializedCiphered) ];
			    
			  	this.sizeOfSecureReceiptMessageComponentsSerialized = secureReceiptMessageComponentsSerializedSymmetricEncryptionDecipher
										  								    .update(this.secureReceiptMessageComponentsSerializedCiphered, 
										  									   	    0, this.sizeOfSecureReceiptMessageComponentsSerializedCiphered,
										  										    this.secureReceiptMessageComponentsSerialized, 0);
			  	
			  	secureReceiptMessageComponentsSerializedSymmetricEncryptionDecipher
  									   .doFinal(this.secureReceiptMessageComponentsSerialized,
  											    this.sizeOfSecureReceiptMessageComponentsSerialized);
    
			  	
				this.setIsSecureReceiptMessageComponentsSerializedCiphered(false);		
				
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
	
	
}
