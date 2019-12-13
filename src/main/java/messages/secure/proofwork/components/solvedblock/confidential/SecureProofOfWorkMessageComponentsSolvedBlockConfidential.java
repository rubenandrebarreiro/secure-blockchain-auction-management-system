package main.java.messages.secure.proofwork.components.solvedblock.confidential;

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
import main.java.resources.block.Block;

public class SecureProofOfWorkMessageComponentsSolvedBlockConfidential {

	private Block block;
	
	
	private byte[] blockSerialized;
	
	private int sizeOfBlockSerialized;
	
	private boolean isBlockSerialized;
	
	
	private byte[] blockSolvedHashed;
	
	private int sizeOfBlockSolvedHashed;
	
	private boolean isBlockSolvedHashed;
	
	
	private byte[] blockSerializedAndSolvedHashed;
	
	private int sizeOfBlockSerializedAndSolvedHashed;
	
	private boolean isBlockSerializedAndSolvedHashed;
	
	
	private byte[] secretSymmetricKeyForSolvedBlockConfidentialInBytes;
	
	
	private byte[] blockSerializedAndSolvedHashedCiphered;
	
	private int sizeOfBlockSerializedAndSolvedHashedCiphered;
	
	private boolean isBlockSerializedAndSolvedHashedCiphered;
	
	
	
	public SecureProofOfWorkMessageComponentsSolvedBlockConfidential(Block block, byte[] blockSolvedHashed) {
		
		this.block = block;
		
		
		this.block.doBlockSerialization();
		
		this.blockSerialized = this.block.getBlockSerialized();
		this.sizeOfBlockSerialized = this.blockSerialized.length;
		this.isBlockSerialized = true;
		
		
		this.blockSolvedHashed = blockSolvedHashed;
		this.sizeOfBlockSolvedHashed = this.blockSolvedHashed.length;
		this.isBlockSolvedHashed = true;
		
		
		this.blockSerializedAndSolvedHashed = null;
		this.sizeOfBlockSerializedAndSolvedHashed = 0;
		this.isBlockSerializedAndSolvedHashed = false;
		
		
		this.blockSerializedAndSolvedHashedCiphered = null;
		this.sizeOfBlockSerializedAndSolvedHashedCiphered = 0;
		this.isBlockSerializedAndSolvedHashedCiphered = false;
		
	}
	
	public SecureProofOfWorkMessageComponentsSolvedBlockConfidential(byte[] blockSerializedAndSolvedHashedCiphered,
														   int sizeOfBlockSerializedAndSolvedHashed,
														   int sizeOfBlockSolvedHashed,
														   int sizeOfBlockSerialized) {
		
		this.blockSerializedAndSolvedHashedCiphered = blockSerializedAndSolvedHashedCiphered;
		this.sizeOfBlockSerializedAndSolvedHashedCiphered = blockSerializedAndSolvedHashedCiphered.length;
		this.isBlockSerializedAndSolvedHashedCiphered = true;
		
		
		this.blockSerializedAndSolvedHashed = null;
		this.sizeOfBlockSerializedAndSolvedHashed = sizeOfBlockSerializedAndSolvedHashed;
		this.isBlockSerializedAndSolvedHashed = true;
		
		
		this.blockSolvedHashed = null;
		this.sizeOfBlockSolvedHashed = sizeOfBlockSolvedHashed;
		this.isBlockSolvedHashed = true;
		
		this.blockSerialized = null;
		this.sizeOfBlockSerialized = sizeOfBlockSerialized;
		this.isBlockSerialized = true;
		
		this.block = null;
		
	}
	
	
	
	
	public Block getBlock() {
		return this.block;
	}
	
	
	public byte[] getBlockSerialized() {
		return this.blockSerialized;
	}
	
	public int getSizeOfBlockSerialized() {
		return this.sizeOfBlockSerialized;
	}
	
	public boolean getIsBlockSerialized() {
		return this.isBlockSerialized;
	}
	
	public void setIsBlockSerialized(boolean isBlockSerialized) {
		this.isBlockSerialized = isBlockSerialized;
	}
	
	public byte[] getBlockSolvedHashed() {
		return this.blockSolvedHashed;
	}
	
	public int getSizeOfBlockSolvedHashed() {
		return this.sizeOfBlockSolvedHashed;
	}
	
	public boolean getIsBlockSolvedHashed() {
		return this.isBlockSolvedHashed;
	}
	
	public void setIsBlockSolvedHashed(boolean isBlockSolvedHashed) {
		this.isBlockSolvedHashed = isBlockSolvedHashed;
	}
	
	
	public byte[] getBlockSerializedAndSolvedHashed() {
		return this.blockSerializedAndSolvedHashed;
	}
	
	public int getSizeOfBlockSerializedAndSolvedHashed() {
		return this.sizeOfBlockSerializedAndSolvedHashed;
	}
	
	public boolean getIsBlockSerializedAndSolvedHashed() {
		return this.isBlockSerializedAndSolvedHashed;
	}
	
	public void setIsBlockSerializedAndSolvedHashed(boolean isBlockSerializedAndSolvedHashed) {
		this.isBlockSerializedAndSolvedHashed = isBlockSerializedAndSolvedHashed;
	}
	
	public byte[] getBlockSerializedAndSolvedHashedCiphered() {
		return this.blockSerializedAndSolvedHashedCiphered;
	}
	
	public int getSizeOfBlockSerializedAndSolvedHashedCiphered() {
		return this.sizeOfBlockSerializedAndSolvedHashedCiphered;
	}
	
	public boolean getIsBlockSerializedAndSolvedHashedCiphered() {
		return this.isBlockSerializedAndSolvedHashedCiphered;
	}
	
	public void setIsBlockSerializedAndSolvedHashedCiphered(boolean isBlockSerializedAndSolvedHashedCiphered) {
		this.isBlockSerializedAndSolvedHashedCiphered = isBlockSerializedAndSolvedHashedCiphered;
	}
	
	public byte[] getSecretSymmetricKeyForSolvedBlockConfidentialInBytes() {
		return this.secretSymmetricKeyForSolvedBlockConfidentialInBytes;
	}

	
	public void buildSecureProofOfWorkMessageComponentsSolvedBlockConfidentialToSend() {
		
		boolean isPossibleToBuildSecureProofOfWorkMessageComponentsSolvedBlockConfidentialToSend = 
			  (  this.isBlockSerialized && this.isBlockSolvedHashed && 
			    !this.isBlockSerializedAndSolvedHashed && 
			    !this.isBlockSerializedAndSolvedHashedCiphered);
		
		
		if(isPossibleToBuildSecureProofOfWorkMessageComponentsSolvedBlockConfidentialToSend) {
			
			this.doBlockSerializedAndSolvedHashed();
			
			this.encryptBlockSerializedAndSolvedHashed();
			
		}
		
	}
	
	public void buildSecureProofOfWorkMessageComponentsSolvedBlockConfidentialReceived() {
		
		boolean isPossibleToBuildSecureProofOfWorkMessageComponentsSolvedBlockConfidentialReceived = 
			  (  this.isBlockSerialized && this.isBlockSolvedHashed && 
			     this.isBlockSerializedAndSolvedHashed && 
			     this.isBlockSerializedAndSolvedHashedCiphered);
	
		
		if(isPossibleToBuildSecureProofOfWorkMessageComponentsSolvedBlockConfidentialReceived) {
			
			this.decryptBlockSerializedAndSolvedHashed();

			this.undoBlockSerializedAndSolvedHashed();
			
		}
		
	}
	
	public void doBlockSerializedAndSolvedHashed() {
		
		boolean isPossibleToDoBlockSerializedAndSolvedHashed = 
			  (  this.isBlockSerialized && this.isBlockSolvedHashed && 
			    !this.isBlockSerializedAndSolvedHashed && 
			    !this.isBlockSerializedAndSolvedHashedCiphered);
		
		
		if(isPossibleToDoBlockSerializedAndSolvedHashed) {
			
			this.sizeOfBlockSerializedAndSolvedHashed = ( this.sizeOfBlockSerialized + this.sizeOfBlockSolvedHashed );
			
			this.blockSerializedAndSolvedHashed = 
					new byte[ this.sizeOfBlockSerializedAndSolvedHashed ];
			
			
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
			System.arraycopy(this.blockSerialized, 0,
							 this.blockSerializedAndSolvedHashed,
							 serializationOffset, this.blockSerialized.length);
			serializationOffset += this.blockSerialized.length;
			
			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current ProofOfWork serialized,
			// From the position corresponding to the length of the previous ProofOfWork's Serialization to
			// the position corresponding to the length of the current ProofOfWork's Serialization
			System.arraycopy(this.blockSolvedHashed, 0,
					         this.blockSerializedAndSolvedHashed,
					         serializationOffset, this.blockSerialized.length);
			
			
			this.setIsBlockSerializedAndSolvedHashed(true);
			
		}
		
		
	}
	
	public void undoBlockSerializedAndSolvedHashed() {
		
		boolean isPossibleToUndoBlockSerializedAndSolvedHashed = 
			  (  this.isBlockSerialized && this.isBlockSolvedHashed && 
			     this.isBlockSerializedAndSolvedHashed && 
			    !this.isBlockSerializedAndSolvedHashedCiphered);
			
		
		if(isPossibleToUndoBlockSerializedAndSolvedHashed) {
			
			this.blockSerialized = new byte[ this.sizeOfBlockSerialized ];
			
			this.blockSolvedHashed = new byte[ this.sizeOfBlockSolvedHashed ];
			
			
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
			System.arraycopy(this.blockSerializedAndSolvedHashed, serializationOffset,
							 this.blockSerialized, 0, this.blockSerialized.length);
			serializationOffset += this.blockSerialized.length;
			
			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current ProofOfWork serialized,
			// From the position corresponding to the length of the previous ProofOfWork's Serialization to
			// the position corresponding to the length of the current ProofOfWork's Serialization
			System.arraycopy(this.blockSerializedAndSolvedHashed, serializationOffset,
					         this.blockSolvedHashed, 0, this.blockSerialized.length);
			
			
			this.setIsBlockSerializedAndSolvedHashed(false);
			
		}
		
	}
	
	
	public void encryptBlockSerializedAndSolvedHashed() {
		
		boolean isPossibleToEncryptSerializedAndSolvedHashed = 
			  (  this.isBlockSerialized && this.isBlockSolvedHashed && 
			     this.isBlockSerializedAndSolvedHashed && 
			    !this.isBlockSerializedAndSolvedHashedCiphered);
			
		
		if(isPossibleToEncryptSerializedAndSolvedHashed) {
			
			try {
				byte[] key = CommonUtils.createKeyForAES(256, new SecureRandom()).getEncoded();
				this.secretSymmetricKeyForSolvedBlockConfidentialInBytes = key;
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
				SecretKeySpec secretKeySpecifications = new SecretKeySpec(this.secretSymmetricKeyForSolvedBlockConfidentialInBytes, symmetricEncryptionAlgorithm);


				String provider = "BC";
				Cipher SecureProofOfWorkMessageComponentsSolvedBlockConfidentialSerializedSymmetricEncryptionCipher = 
						Cipher.getInstance(String.format("%s/%s/%s",
								symmetricEncryptionAlgorithm, symmetricEncryptionMode, symmetricEncryptionPadding), 
								provider );

				byte[] initialisationVectorBytes = null;

				if(CommonUtils.blockModeRequiresIV(symmetricEncryptionMode)) {

					// Algorithms that don't need IV (Initialisation Vector): ECB
					// The parameter specifications for the IV (Initialisation Vector)	
					System.out.println("[SecureProofOfWorkMessageComponentsSolvedBlockConfidential.ENCRYPT] Cipher's Block Mode needs IV (Initialisation Vector)!!!");
					initialisationVectorBytes = 
							CommonUtils.generateIV(SecureProofOfWorkMessageComponentsSolvedBlockConfidentialSerializedSymmetricEncryptionCipher);

					// Showing the randomly defined IV (Initialisation Vector)
					System.out.println("[SecureProofOfWorkMessageComponentsSolvedBlockConfidential.ENCRYPT] - IV (Initialisation Vector) is:\n- " 
							+ CommonUtils.fromByteArrayToHexadecimalFormat(initialisationVectorBytes));

					IvParameterSpec initializationVectorParameterSpecifications = new IvParameterSpec(initialisationVectorBytes);
					
					SecureProofOfWorkMessageComponentsSolvedBlockConfidentialSerializedSymmetricEncryptionCipher
							.init(Cipher.ENCRYPT_MODE, secretKeySpecifications, initializationVectorParameterSpecifications);

				}
				else {

					// Algorithms that need IV (Initialisation Vector)
					// The parameter specifications for the IV (Initialisation Vector)
					System.out.println("[SecureProofOfWorkMessageComponentsSolvedBlockConfidential.ENCRYPT] Cipher's Block Mode doesn't needs IV (Initialisation Vector)!!!");

					SecureProofOfWorkMessageComponentsSolvedBlockConfidentialSerializedSymmetricEncryptionCipher
					.init(Cipher.ENCRYPT_MODE, secretKeySpecifications);

				}

				this.blockSerializedAndSolvedHashedCiphered = 
						SecureProofOfWorkMessageComponentsSolvedBlockConfidentialSerializedSymmetricEncryptionCipher
						.doFinal(this.blockSerializedAndSolvedHashed);


				this.setIsBlockSerializedAndSolvedHashedCiphered(true);		

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
	
	public void decryptBlockSerializedAndSolvedHashed() {
		
		boolean isPossibleToDecryptSerializedAndSolvedHashed = 
			  (  this.isBlockSerialized && this.isBlockSolvedHashed && 
			     this.isBlockSerializedAndSolvedHashed && 
			     this.isBlockSerializedAndSolvedHashedCiphered);
				
		
		if(isPossibleToDecryptSerializedAndSolvedHashed) {
			
			byte[] secretKeyBytes = this.secretSymmetricKeyForSolvedBlockConfidentialInBytes; // TODO Symmetric Key contained in the Envelope (secretHMACKeyForDoSMitigationInBytes)
			
			try {
				
				String symmetricEncryptionAlgorithm = "AES";
				String symmetricEncryptionMode = "CBC";
		 	    String symmetricEncryptionPadding = "NoPadding";
				
				
				// Set the Secret Key and its specifications,
		 		// using the AES (Advanced Encryption Standard - Rijndael) Symmetric Encryption
				SecretKeySpec secretKeySpecifications = new SecretKeySpec(secretKeyBytes, symmetricEncryptionAlgorithm);
				
				
				String provider = "BC";
				Cipher SecureProofOfWorkMessageComponentsSolvedBlockConfidentialSerializedAndHashedSymmetricEncryptionDecipher = 
						Cipher.getInstance(String.format("%s/%s/%s",
										   symmetricEncryptionAlgorithm, symmetricEncryptionMode, symmetricEncryptionPadding), 
								           provider );
				
				byte[] initialisationVectorBytes = null;
			
				if(CommonUtils.blockModeRequiresIV(symmetricEncryptionMode)) {
					
					// Algorithms that don't need IV (Initialisation Vector): ECB
					// The parameter specifications for the IV (Initialisation Vector)	
					System.out.println("[SecureProofOfWorkMessageComponentsSolvedBlockConfidential.DECRYPT] Cipher's Block Mode needs IV (Initialisation Vector)!!!");
					
					// Showing the randomly defined IV (Initialisation Vector)
					System.out.println("[SecureProofOfWorkMessageComponentsSolvedBlockConfidential.DECRYPT] - IV (Initialisation Vector) is:\n- " 
									   + CommonUtils.fromByteArrayToHexadecimalFormat(initialisationVectorBytes));
					
					IvParameterSpec initializationVectorParameterSpecifications = new IvParameterSpec(initialisationVectorBytes);
					SecureProofOfWorkMessageComponentsSolvedBlockConfidentialSerializedAndHashedSymmetricEncryptionDecipher
						.init(Cipher.DECRYPT_MODE, secretKeySpecifications, initializationVectorParameterSpecifications);
				}
				else {
					
					// Algorithms that need IV (Initialisation Vector)
					// The parameter specifications for the IV (Initialisation Vector)
					System.out.println("[SecureProofOfWorkMessageComponentsSolvedBlockConfidential.DECRYPT] Cipher's Block Mode doesn't needs IV (Initialisation Vector)!!!");
					
					SecureProofOfWorkMessageComponentsSolvedBlockConfidentialSerializedAndHashedSymmetricEncryptionDecipher
						.init(Cipher.DECRYPT_MODE, secretKeySpecifications);
					
				}
				
				this.sizeOfBlockSerializedAndSolvedHashedCiphered = 
						this.blockSerializedAndSolvedHashedCiphered.length;
				
			  	// The Plain Text of the bytes of the SolvedBlock input received through the communication channel
			  	this.blockSerializedAndSolvedHashed = 
			  			new byte[ SecureProofOfWorkMessageComponentsSolvedBlockConfidentialSerializedAndHashedSymmetricEncryptionDecipher
			  	                  .getOutputSize(this.sizeOfBlockSerializedAndSolvedHashedCiphered) ];
			    
			  	this.sizeOfBlockSerializedAndSolvedHashed = SecureProofOfWorkMessageComponentsSolvedBlockConfidentialSerializedAndHashedSymmetricEncryptionDecipher
										  								    .update(this.blockSerializedAndSolvedHashedCiphered, 
										  									   	    0, this.sizeOfBlockSerializedAndSolvedHashedCiphered,
										  										    this.blockSerializedAndSolvedHashed, 0);
			  	
			  	SecureProofOfWorkMessageComponentsSolvedBlockConfidentialSerializedAndHashedSymmetricEncryptionDecipher
			  									   .doFinal(this.blockSerializedAndSolvedHashed,
			  											    this.sizeOfBlockSerializedAndSolvedHashed);
			    
			  	
				this.setIsBlockSerializedAndSolvedHashedCiphered(false);		
				
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
