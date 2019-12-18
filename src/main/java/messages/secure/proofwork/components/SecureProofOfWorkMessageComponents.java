package main.java.messages.secure.proofwork.components;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.ShortBufferException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import main.java.common.utils.CommonUtils;
import main.java.messages.secure.common.header.SecureCommonHeader;
import main.java.messages.secure.proofwork.components.solvedblock.SecureProofOfWorkMessageComponentsSolvedBlock;


public class SecureProofOfWorkMessageComponents {
	
	
	private SecureCommonHeader secureCommonHeader;
	
	private byte[] secureCommonHeaderSerialized;
	
	private boolean isSecureCommonHeaderSerialized;
	
	
	private SecureProofOfWorkMessageComponentsSolvedBlock
										secureProofOfWorkMessageComponentsSolvedBlock;
	
	private int sizeOfSecureProofOfWorkMessageComponentsSolvedBlockInfoSerialized;
	
	private int sizeOfSecureProofOfWorkMessageComponentsSolvedBlockSignatureSerialized;
	
	private byte[] secureProofOfWorkMessageComponentsSolvedBlockSerialized;
	
	private int sizeOfSecureProofOfWorkMessageComponentsSolvedBlockSerialized;
	
	private boolean isSecureProofOfWorkMessageComponentsSolvedBlockSerialized;
	
	private int sizeOfBlockSerialized;
	
	private int sizeOfBlockSolvedHashedSerialized;
	
	private int sizeOfBidsOfCurrentBlockToTryToMineSerialized;
	
	private byte[] secureProofOfWorkMessageComponentsSerialized;
	
	private int sizeOfSecureProofOfWorkMessageComponentsSerialized;
	
	private boolean isSecureProofOfWorkMessageComponentsSerialized;
	
	
	private byte[] initialisationVector;
	
	private byte[] secretSymmetricKeyForProofOfWorkMessageComponentsInBytes;
	
	
	private byte[] secureProofOfWorkMessageComponentsSerializedCiphered;
	
	private int sizeOfSecureProofOfWorkMessageComponentsSerializedCiphered;
	
	private boolean isSecureProofOfWorkMessageComponentsSerializedCiphered;
	
	
	private String userPeerID;
	
	
	public SecureProofOfWorkMessageComponents(SecureCommonHeader secureCommonHeader,
											  SecureProofOfWorkMessageComponentsSolvedBlock 
											  			secureProofOfWorkMessageComponentsSolvedBlock) {
		
		this.secureCommonHeader = secureCommonHeader;
		
		this.secureCommonHeader
			.doSecureCommonHeaderSerialization();
		this.secureCommonHeaderSerialized = this.secureCommonHeader.getSecureCommonHeaderSerialized();
		
		this.isSecureCommonHeaderSerialized = true;
		
		
		this.secureProofOfWorkMessageComponentsSolvedBlock = secureProofOfWorkMessageComponentsSolvedBlock;
		
		this.sizeOfSecureProofOfWorkMessageComponentsSolvedBlockInfoSerialized = 0;
		this.sizeOfSecureProofOfWorkMessageComponentsSolvedBlockSignatureSerialized = 0;
		
		this.secureProofOfWorkMessageComponentsSolvedBlock
			.doSecureProofOfWorkMessageComponentsSolvedBlockSerialization();
		this.secureProofOfWorkMessageComponentsSolvedBlockSerialized = 
					this.secureProofOfWorkMessageComponentsSolvedBlock
						.getSecureProofOfWorkMessageComponentsSolvedBlockSerialized();
		
		this.sizeOfSecureProofOfWorkMessageComponentsSolvedBlockSerialized = 
				this.secureProofOfWorkMessageComponentsSolvedBlockSerialized.length;
		
		this.isSecureProofOfWorkMessageComponentsSolvedBlockSerialized = true;
		
		this.secureProofOfWorkMessageComponentsSerialized = null;
		this.isSecureProofOfWorkMessageComponentsSerialized = false;
			
		
		this.initialisationVector = null;
		
		this.secretSymmetricKeyForProofOfWorkMessageComponentsInBytes = null;
		
		
		this.secureProofOfWorkMessageComponentsSerializedCiphered = null;
		this.sizeOfSecureProofOfWorkMessageComponentsSerializedCiphered = 0;
		this.isSecureProofOfWorkMessageComponentsSerializedCiphered = false;
		
	}
	
	
	public SecureProofOfWorkMessageComponents(byte[] secureProofOfWorkMessageComponentsSerializedCiphered,
											  byte[] initialisationVector,
								  			  byte[] secretSymmetricKeyForProofOfWorkMessageComponentsInBytes,
								  			  int sizeOfSecureProofOfWorkMessageComponentsSolvedBlockSerialized,
								  			  int sizeOfSecureProofOfWorkMessageComponentsSolvedBlockInfoSerialized,
								  			  int sizeOfSecureProofOfWorkMessageComponentsSolvedBlockSignatureSerialized,
								  			  int sizeOfBlockSerialized,
								  			  int sizeOfBlockSolvedHashedSerialized,
								  			  int sizeOfBidsOfCurrentBlockToTryToMineSerialized,
								  			  String userPeerID) {
		
		this.secureProofOfWorkMessageComponentsSerializedCiphered = 
					secureProofOfWorkMessageComponentsSerializedCiphered;
		this.sizeOfSecureProofOfWorkMessageComponentsSerializedCiphered = 
					this.secureProofOfWorkMessageComponentsSerializedCiphered.length;
		this.isSecureProofOfWorkMessageComponentsSerializedCiphered = true;
	
		
		this.initialisationVector = initialisationVector;
		this.secretSymmetricKeyForProofOfWorkMessageComponentsInBytes = 
					secretSymmetricKeyForProofOfWorkMessageComponentsInBytes;
		
		
		this.secureProofOfWorkMessageComponentsSerialized = null;
		this.sizeOfSecureProofOfWorkMessageComponentsSerialized = 0;
		this.isSecureProofOfWorkMessageComponentsSerialized = true;
		
		
		this.secureCommonHeaderSerialized = null;
		this.isSecureCommonHeaderSerialized = true;
		
		
		this.secureProofOfWorkMessageComponentsSolvedBlockSerialized = null;
		this.sizeOfSecureProofOfWorkMessageComponentsSolvedBlockSerialized = 
				sizeOfSecureProofOfWorkMessageComponentsSolvedBlockSerialized;		
		this.isSecureProofOfWorkMessageComponentsSolvedBlockSerialized = true;
				
		this.sizeOfSecureProofOfWorkMessageComponentsSolvedBlockInfoSerialized = 
				sizeOfSecureProofOfWorkMessageComponentsSolvedBlockInfoSerialized;		
		
		this.sizeOfSecureProofOfWorkMessageComponentsSolvedBlockSignatureSerialized = 
				sizeOfSecureProofOfWorkMessageComponentsSolvedBlockSignatureSerialized;		
		
		this.secureCommonHeader = null;
		this.secureProofOfWorkMessageComponentsSolvedBlock = null;
		
		this.sizeOfBlockSerialized = sizeOfBlockSerialized;
		this.sizeOfBlockSolvedHashedSerialized = sizeOfBlockSolvedHashedSerialized;
		
		this.sizeOfBidsOfCurrentBlockToTryToMineSerialized = sizeOfBidsOfCurrentBlockToTryToMineSerialized;
		
		this.userPeerID = userPeerID;
		
	}

	
	
	
	public SecureCommonHeader getSecureCommonHeader() {
		return this.secureCommonHeader;
	}
	
	public byte[] getSecureCommonHeaderSerialized() {
		return this.secureCommonHeaderSerialized;
	}
	
	public boolean getIsSecureCommonHeaderSerialized() {
		return this.isSecureCommonHeaderSerialized;
	}
	
	public void setIsSecureCommonHeaderSerialized(boolean isSecureCommonHeaderSerialized) {
		this.isSecureCommonHeaderSerialized = isSecureCommonHeaderSerialized;
	}
	
	
	
	public SecureProofOfWorkMessageComponentsSolvedBlock
								getSecureProofOfWorkMessageComponentsSolvedBlock() {
		
		return this.secureProofOfWorkMessageComponentsSolvedBlock;
		
	}

	public int getSizeOfSecureProofOfWorkMessageComponentsSolvedBlockInfoSerialized() {
		return this.sizeOfSecureProofOfWorkMessageComponentsSolvedBlockInfoSerialized;
	}
	
	public int getSizeOfSecureProofOfWorkMessageComponentsSolvedBlockSignatureSerialized() {
		return this.sizeOfSecureProofOfWorkMessageComponentsSolvedBlockSignatureSerialized;
	}

	
	public byte[] getSecureProofOfWorkMessageComponentsSolvedBlockSerialized() {
		return this.secureProofOfWorkMessageComponentsSolvedBlockSerialized;
	}
	
	public int getSizeOfSecureProofOfWorkMessageComponentsSolvedBlockSerialized() {
		return this.sizeOfSecureProofOfWorkMessageComponentsSolvedBlockSerialized;
	}
		
	public boolean getIsSecureProofOfWorkMessageComponentsSolvedBlockSerialized() {
		return this.isSecureProofOfWorkMessageComponentsSolvedBlockSerialized;
	}
	
	public void setIsSecureProofOfWorkMessageComponentsSolvedBlockSerialized
		  (boolean isSecureProofOfWorkMessageComponentsSolvedBlockSerialized) {
		
		this.isSecureProofOfWorkMessageComponentsSolvedBlockSerialized = 
				isSecureProofOfWorkMessageComponentsSolvedBlockSerialized;
	
	}
	
	
	
	public byte[] getSecureProofOfWorkMessageComponentsSerialized() {
		return this.secureProofOfWorkMessageComponentsSerialized;
	}
	
	public int getSizeOfSecureProofOfWorkMessageComponentsSerialized() {
		return this.sizeOfSecureProofOfWorkMessageComponentsSerialized;
	}
	
	public boolean getIsSecureProofOfWorkMessageComponentsSerialized() {
		return this.isSecureProofOfWorkMessageComponentsSerialized;
	}
	
	public void setIsSecureProofOfWorkMessageComponentsSerialized
		  (boolean isSecureProofOfWorkMessageComponentsSerialized) {
		
		this.isSecureProofOfWorkMessageComponentsSerialized = 
				isSecureProofOfWorkMessageComponentsSerialized;
	}
	
	
	
	public byte[] getInitialisationVector() {
		return this.initialisationVector;
	}
	
	public byte[] getSecretSymmetricKeyForProofOfWorkMessageComponentsInBytes() {
		return this.secretSymmetricKeyForProofOfWorkMessageComponentsInBytes;
	}
	
	
	
	public byte[] getSecureProofOfWorkMessageComponentsSerializedCiphered() {
		return this.secureProofOfWorkMessageComponentsSerializedCiphered;
	}
	
	public int getSizeOfSecureProofOfWorkMessageComponentsSerializedCiphered() {
		return this.sizeOfSecureProofOfWorkMessageComponentsSerializedCiphered;
	}
	
	public boolean getIsSecureProofOfWorkMessageComponentsSerializedCiphered() {
		return this.isSecureProofOfWorkMessageComponentsSerializedCiphered;
	}
	
	public void setIsSecureProofOfWorkMessageComponentsSerializedCiphered
		  (boolean isSecureProofOfWorkMessageComponentsSerializedCiphered) {
		
		this.isSecureProofOfWorkMessageComponentsSerializedCiphered = 
				isSecureProofOfWorkMessageComponentsSerializedCiphered;
	
	}
	
	
	
	
	public void doSecureProofOfWorkMessageComponentsSerialization() {
		
		boolean isPossibleToDoSecureProofOfWorkMessageComponentsSerialized = 
				( this.isSecureCommonHeaderSerialized && 
				  this.isSecureProofOfWorkMessageComponentsSolvedBlockSerialized &&
				 !this.isSecureProofOfWorkMessageComponentsSerialized &&
				 !this.isSecureProofOfWorkMessageComponentsSerializedCiphered);
	
		
		if(isPossibleToDoSecureProofOfWorkMessageComponentsSerialized) {
		
			int sizeOfSecureProofOfWorkMessageComponentsSerialized =
					( CommonUtils.COMMON_HEADER_LENGTH + 
					  this.sizeOfSecureProofOfWorkMessageComponentsSolvedBlockSerialized );
			
			this.secureProofOfWorkMessageComponentsSerialized =
					new byte[ sizeOfSecureProofOfWorkMessageComponentsSerialized ];
			
			// Operations to Fill a Byte Array, with the following parameters:
			// 1) src - The source of the array to be copied
			// 2) srcPos - The position from the array to be copied, representing the first element to be copied
			// 3) dest - The destination of the array to be copied
			// 4) destPos - The position of the array where will be placed the new copy,
			//              representing the first element where new SolvedBlock will be placed
			// 5) length - The length of the SolvedBlock to be copied from the source array to the destination array
	
			// The offset related to fulfilment of the serialization process
			int serializationOffset = 0;
			
			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current ProofOfWork serialized,
			// From the position corresponding to the length of the previous ProofOfWork's Serialization to
			// the position corresponding to the length of the current ProofOfWork's Serialization
			System.arraycopy(this.secureCommonHeaderSerialized, 0,
							 this.secureProofOfWorkMessageComponentsSerialized,
							 serializationOffset, this.secureCommonHeaderSerialized.length);
			serializationOffset += this.secureCommonHeaderSerialized.length;
			
			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current ProofOfWork serialized,
			// From the position corresponding to the length of the previous ProofOfWork's Serialization to
			// the position corresponding to the length of the current ProofOfWork's Serialization
			System.arraycopy(this.secureProofOfWorkMessageComponentsSolvedBlockSerialized, 0,
					         this.secureProofOfWorkMessageComponentsSerialized,
					         serializationOffset, this.secureProofOfWorkMessageComponentsSolvedBlockSerialized.length);
			
			
			this.setIsSecureProofOfWorkMessageComponentsSerialized(true);
		
		}
				
	}
	
	
	public void undoSecureProofOfWorkMessageComponentsSerialization() {
		
		boolean isPossibleToUndoSecureProofOfWorkMessageComponentsSerialized = 
				( this.isSecureCommonHeaderSerialized && 
				  this.isSecureProofOfWorkMessageComponentsSolvedBlockSerialized &&
				  this.isSecureProofOfWorkMessageComponentsSerialized &&
				 !this.isSecureProofOfWorkMessageComponentsSerializedCiphered);
	
		
		if(isPossibleToUndoSecureProofOfWorkMessageComponentsSerialized) {
		
			this.secureCommonHeaderSerialized = new byte[ CommonUtils.COMMON_HEADER_LENGTH ];
			this.secureProofOfWorkMessageComponentsSolvedBlockSerialized = 
					new byte[ this.sizeOfSecureProofOfWorkMessageComponentsSolvedBlockSerialized ];
			
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
			System.arraycopy(this.secureProofOfWorkMessageComponentsSerialized,
							 serializationOffset, this.secureCommonHeaderSerialized,
							 0, this.secureCommonHeaderSerialized.length);
			serializationOffset += this.secureCommonHeaderSerialized.length;
			
			// Fills the byte array of the Bid serialized with its User/Client Bidder's ID,
			// From the position corresponding to the length of Bid's ID to
			// the corresponding of the length of Bid's User/Client Bidder's ID
			System.arraycopy(this.secureProofOfWorkMessageComponentsSerialized,
							 serializationOffset, this.secureProofOfWorkMessageComponentsSolvedBlockSerialized,
							 0, this.secureProofOfWorkMessageComponentsSolvedBlockSerialized.length);
			
			
			this.secureCommonHeader = new SecureCommonHeader(this.secureCommonHeaderSerialized);
			
			this.secureCommonHeader.undoSecureCommonHeaderSerialization();
			
			
			this.secureProofOfWorkMessageComponentsSolvedBlock = 
					new SecureProofOfWorkMessageComponentsSolvedBlock
							(this.secureProofOfWorkMessageComponentsSolvedBlockSerialized,
							 this.sizeOfSecureProofOfWorkMessageComponentsSolvedBlockInfoSerialized,
							 this.sizeOfSecureProofOfWorkMessageComponentsSolvedBlockSignatureSerialized,
							 this.sizeOfBlockSerialized,
							 this.sizeOfBlockSolvedHashedSerialized,
							 this.sizeOfBidsOfCurrentBlockToTryToMineSerialized,
							 this.userPeerID);
			
			secureProofOfWorkMessageComponentsSolvedBlock.undoSecureProofOfWorkMessageSolvedBlockSerialization();
			
			this.setIsSecureProofOfWorkMessageComponentsSerialized(false);
		
		}
				
	}
	
	public void encryptSecureProofOfWorkMessageComponents() {
		
		boolean isPossibleToEncryptSecureProofOfWorkMessageComponents = 
				( this.isSecureCommonHeaderSerialized && 
				  this.isSecureProofOfWorkMessageComponentsSolvedBlockSerialized &&
				  this.isSecureProofOfWorkMessageComponentsSerialized &&
				 !this.isSecureProofOfWorkMessageComponentsSerializedCiphered);
		
		
		if(isPossibleToEncryptSecureProofOfWorkMessageComponents) {
			
			try {
				byte[] key = CommonUtils.createKeyForAES(256, new SecureRandom()).getEncoded();
				this.secretSymmetricKeyForProofOfWorkMessageComponentsInBytes = key;
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
				SecretKeySpec secretKeySpecifications = new SecretKeySpec(this.secretSymmetricKeyForProofOfWorkMessageComponentsInBytes, symmetricEncryptionAlgorithm);


				String provider = "BC";
				Cipher secureProofOfWorkMessageComponentsSymmetricEncryptionCipher = 
						Cipher.getInstance(String.format("%s/%s/%s",
								symmetricEncryptionAlgorithm, symmetricEncryptionMode, symmetricEncryptionPadding), 
								provider );

				if(CommonUtils.blockModeRequiresIV(symmetricEncryptionMode)) {

					// Algorithms that don't need IV (Initialisation Vector): ECB
					// The parameter specifications for the IV (Initialisation Vector)	
					System.out.println("[SecureProofOfWorkMessageComponentsSolvedBlockConfidential.ENCRYPT] Cipher's Block Mode needs IV (Initialisation Vector)!!!");
					this.initialisationVector = 
							CommonUtils.generateIV(secureProofOfWorkMessageComponentsSymmetricEncryptionCipher);

					// Showing the randomly defined IV (Initialisation Vector)
					System.out.println("[SecureProofOfWorkMessageComponentsSolvedBlockConfidential.ENCRYPT] - IV (Initialisation Vector) is:\n- " 
							+ CommonUtils.fromByteArrayToHexadecimalFormat(initialisationVector));

					IvParameterSpec initializationVectorParameterSpecifications = new IvParameterSpec(initialisationVector);
					
					secureProofOfWorkMessageComponentsSymmetricEncryptionCipher
							.init(Cipher.ENCRYPT_MODE, secretKeySpecifications, initializationVectorParameterSpecifications);

				}
				else {

					// Algorithms that need IV (Initialisation Vector)
					// The parameter specifications for the IV (Initialisation Vector)
					System.out.println("[SecureProofOfWorkMessageComponentsSolvedBlockConfidential.ENCRYPT] Cipher's Block Mode doesn't needs IV (Initialisation Vector)!!!");

					secureProofOfWorkMessageComponentsSymmetricEncryptionCipher
					.init(Cipher.ENCRYPT_MODE, secretKeySpecifications);

				}

				this.secureProofOfWorkMessageComponentsSerializedCiphered = 
						secureProofOfWorkMessageComponentsSymmetricEncryptionCipher
						.doFinal(this.secureProofOfWorkMessageComponentsSerialized);


				this.setIsSecureProofOfWorkMessageComponentsSerializedCiphered(true);		

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
	
	public void decryptSecureProofOfWorkMessageComponents() {
		
		boolean isPossibleToDecryptSecureProofOfWorkMessageComponents = 
				( this.isSecureCommonHeaderSerialized && 
				  this.isSecureProofOfWorkMessageComponentsSolvedBlockSerialized &&
				  this.isSecureProofOfWorkMessageComponentsSerialized &&
				  this.isSecureProofOfWorkMessageComponentsSerializedCiphered);
				
		
		if(isPossibleToDecryptSecureProofOfWorkMessageComponents) {
			
			byte[] secretKeyBytes = this.secretSymmetricKeyForProofOfWorkMessageComponentsInBytes; // TODO Symmetric Key contained in the Envelope (secretHMACKeyForDoSMitigationInBytes)
			
			try {
				
				String symmetricEncryptionAlgorithm = "AES";
				String symmetricEncryptionMode = "CBC";
		 	    String symmetricEncryptionPadding = "PKCS7Padding";
				
				
				// Set the Secret Key and its specifications,
		 		// using the AES (Advanced Encryption Standard - Rijndael) Symmetric Encryption
				SecretKeySpec secretKeySpecifications = new SecretKeySpec(secretKeyBytes, symmetricEncryptionAlgorithm);
				
				
				String provider = "BC";
				Cipher secureProofOfWorkMessageComponentsSymmetricEncryptionDecipher = 
						Cipher.getInstance(String.format("%s/%s/%s",
										   symmetricEncryptionAlgorithm, symmetricEncryptionMode, symmetricEncryptionPadding), 
								           provider );
				
				if(CommonUtils.blockModeRequiresIV(symmetricEncryptionMode)) {
					
					// Algorithms that don't need IV (Initialisation Vector): ECB
					// The parameter specifications for the IV (Initialisation Vector)	
					System.out.println("[SecureProofOfWorkMessageComponentsSolvedBlockConfidential.DECRYPT] Cipher's Block Mode needs IV (Initialisation Vector)!!!");
					
					// Showing the randomly defined IV (Initialisation Vector)
					System.out.println("[SecureProofOfWorkMessageComponentsSolvedBlockConfidential.DECRYPT] - IV (Initialisation Vector) is:\n- " 
									   + CommonUtils.fromByteArrayToHexadecimalFormat(this.initialisationVector));
					
					IvParameterSpec initializationVectorParameterSpecifications = new IvParameterSpec(this.initialisationVector);
					secureProofOfWorkMessageComponentsSymmetricEncryptionDecipher
						.init(Cipher.DECRYPT_MODE, secretKeySpecifications, initializationVectorParameterSpecifications);
				}
				else {
					
					// Algorithms that need IV (Initialisation Vector)
					// The parameter specifications for the IV (Initialisation Vector)
					System.out.println("[SecureProofOfWorkMessageComponentsSolvedBlockConfidential.DECRYPT] Cipher's Block Mode doesn't needs IV (Initialisation Vector)!!!");
					
					secureProofOfWorkMessageComponentsSymmetricEncryptionDecipher
						.init(Cipher.DECRYPT_MODE, secretKeySpecifications);
					
				}
				
				this.sizeOfSecureProofOfWorkMessageComponentsSerializedCiphered = 
						this.secureProofOfWorkMessageComponentsSerializedCiphered.length;
				
			  	// The Plain Text of the bytes of the SolvedBlock input received through the communication channel
			  	this.secureProofOfWorkMessageComponentsSerialized = 
			  			new byte[ secureProofOfWorkMessageComponentsSymmetricEncryptionDecipher
			  	                  .getOutputSize(this.sizeOfSecureProofOfWorkMessageComponentsSerializedCiphered) ];
			    
			  	this.sizeOfSecureProofOfWorkMessageComponentsSerialized = secureProofOfWorkMessageComponentsSymmetricEncryptionDecipher
										  								    .update(this.secureProofOfWorkMessageComponentsSerializedCiphered, 
										  									   	    0, this.sizeOfSecureProofOfWorkMessageComponentsSerializedCiphered,
										  										    this.secureProofOfWorkMessageComponentsSerialized, 0);
			  	
			  	secureProofOfWorkMessageComponentsSymmetricEncryptionDecipher
			  									   .doFinal(this.secureProofOfWorkMessageComponentsSerialized,
			  											    this.sizeOfSecureProofOfWorkMessageComponentsSerialized);
			    
			  	
				this.setIsSecureProofOfWorkMessageComponentsSerializedCiphered(false);		
				
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
