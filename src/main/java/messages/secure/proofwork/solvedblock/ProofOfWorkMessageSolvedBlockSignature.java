package main.java.messages.secure.proofwork.solvedblock;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.ShortBufferException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.bouncycastle.util.Arrays;

import main.java.common.utils.CommonUtils;
import main.java.resources.block.Block;

public class ProofOfWorkMessageSolvedBlockSignature {
	
	private Block block;
	
	private byte[] blockSerialized;
	
	private int sizeOfBlockSerialized;

	private byte[] blockHashSolved;
	
	private byte[] blockHashSolvedCiphered;
	
	private int sizeOfBlockHashSolvedCiphered;
	
	private boolean isBlockHashSolvedCiphered;
	
	private byte[] proofOfWorkDigitalSigned;

	private boolean isProofOfWorkDigitalSigned;
	
	private boolean isProofOfWorkValid;
	
	
	public ProofOfWorkMessageSolvedBlockSignature(Block block, byte[] blockHashSolved) {
		
		this.block = block;
		this.block.doBlockSerialization();
		this.blockSerialized = this.block.getBlockSerialized();
		
		this.sizeOfBlockSerialized = this.blockSerialized.length;
		
		this.blockHashSolved = blockHashSolved;
		
		this.blockHashSolvedCiphered = null;
		this.isBlockHashSolvedCiphered = false;
		
		this.sizeOfBlockHashSolvedCiphered = 0;
		
		this.proofOfWorkDigitalSigned = null;
		this.isProofOfWorkDigitalSigned = false;
		
		this.isProofOfWorkValid = false;
		
	}
	
	public ProofOfWorkMessageSolvedBlockSignature(byte[] proofOfWorkDigitalSigned,
												  int sizeOfBlockSerialized,
												  int sizeOfBlockHashSolvedCiphered) {
		
		this.proofOfWorkDigitalSigned = proofOfWorkDigitalSigned;
		this.isProofOfWorkDigitalSigned = true;
		
		this.sizeOfBlockHashSolvedCiphered = sizeOfBlockHashSolvedCiphered;
		
		this.blockHashSolvedCiphered = null;
		this.isBlockHashSolvedCiphered = true;

		this.blockHashSolved = null;
		
		this.sizeOfBlockSerialized = sizeOfBlockSerialized;
		
		this.block = null;
		this.blockSerialized = null;
		
		this.isProofOfWorkValid = false;
		
	}
	
	
	public Block getBlock() {
		return this.block;
	}
	
	public void setBlock(Block block) {
		this.block = block;
	}
	
	public byte[] getBlockSerialized() {
		return this.blockSerialized;
	}
	
	public void setBlockSerialized(byte[] blockSerialized) {
		this.blockSerialized = blockSerialized;
	}
	
	public int getSizeOfBlockSerialized() {
		return this.sizeOfBlockSerialized;
	}
	
	public void setSizeOfBlockSerialized(int sizeOfBlockSerialized) {
		this.sizeOfBlockSerialized = sizeOfBlockSerialized;
	}
	
	public byte[] getBlockHashSolved() {
		return this.blockHashSolved;
	}
	
	public void setBlockHashSolved(byte[] blockHashSolved) {
		this.blockHashSolved = blockHashSolved;
	}
	
	public byte[] getBlockHashSolvedCiphered() {
		return this.blockHashSolvedCiphered;
	}
	
	public void setBlockHashSolvedCiphered(byte[] blockHashSolvedCiphered) {
		this.blockHashSolvedCiphered = blockHashSolvedCiphered;
	}
	
	public boolean getIsBlockHashSolvedCiphered() {
		return this.isBlockHashSolvedCiphered;
	}
	
	public void setIsBlockHashSolvedCiphered(boolean isBlockHashSolvedCiphered) {
		this.isBlockHashSolvedCiphered = isBlockHashSolvedCiphered;
	}
	
	public int getSizeOfBlockHashSolvedCiphered() {
		return this.sizeOfBlockHashSolvedCiphered;
	}
	
	public void setSizeOfBlockHashSolvedCiphered(int sizeOfBlockHashSolvedCiphered) {
		this.sizeOfBlockHashSolvedCiphered = sizeOfBlockHashSolvedCiphered;
	}
	
	public byte[] getProofOfWorkDigitalSigned() {
		return this.proofOfWorkDigitalSigned;
	}
	
	public void setProofOfWorkDigitalSigned(byte[] proofOfWorkDigitalSigned) {
		this.proofOfWorkDigitalSigned = proofOfWorkDigitalSigned;
	}
	
	public boolean getIsProofOfWorkDigitalSigned() {
		return this.isProofOfWorkDigitalSigned;
	}
	
	public void setIsProofOfWorkDigitalSigned(boolean isProofOfWorkDigitalSigned) {
		this.isProofOfWorkDigitalSigned = isProofOfWorkDigitalSigned;
	}
	
	public void buildProofOfWorkMessageSolvedBlockSignatureToSend()
			   throws NoSuchAlgorithmException, InvalidKeyException, NoSuchProviderException,
			          NoSuchPaddingException, InvalidAlgorithmParameterException {

		boolean isPossibleToBuildProofOfWorkMessageSolvedBlockSignatureToSend = 
				( !this.getIsBlockHashSolvedCiphered() && !this.getIsProofOfWorkDigitalSigned() );	

		if(isPossibleToBuildProofOfWorkMessageSolvedBlockSignatureToSend) {
			
			this.encryptBlockHashSolved();
			
			this.doDigitalSignatureOfChallengeSolved();
			
		}

	}
	
	public void buildProofOfWorkMessageSolvedBlockSignatureReceived() {
		
		boolean isPossibleToBuildProofOfWorkMessageSolvedBlockSignatureReceived = 
				( this.getIsBlockHashSolvedCiphered() && this.getIsProofOfWorkDigitalSigned() );
		
		if(isPossibleToBuildProofOfWorkMessageSolvedBlockSignatureReceived) {
			
			this.undoDigitalSignatureOfChallengeSolved();
			
			this.decryptBlockHashSolved();
			
		}
		
	}
	
	public boolean verifyProofOfWork() throws NoSuchAlgorithmException {
		
		boolean isPossibleToVerifyProofOfWork = 
				( !this.getIsBlockHashSolvedCiphered() && !this.getIsProofOfWorkDigitalSigned() );
		
		if(isPossibleToVerifyProofOfWork) {
			
			MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
			byte[] blockSerializedHashedToCompare = messageDigest.digest(this.blockSerialized);
			
			this.isProofOfWorkValid = Arrays.areEqual(this.blockHashSolved, 
													  blockSerializedHashedToCompare) ? 
																	  true : false;
			return this.isProofOfWorkValid;
			
		}
		
		
		return false;
		
	}
	
	private void encryptBlockHashSolved()
			throws NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException,
			       InvalidKeyException, InvalidAlgorithmParameterException {
		
		boolean isPossibleToEncryptBlockSolved = 
				( !this.getIsBlockHashSolvedCiphered() && !this.getIsProofOfWorkDigitalSigned() );
	
		if(isPossibleToEncryptBlockSolved) {
			
			byte[] secretKeyBytes = null;
			
			try {
				
				String symmetricEncryptionAlgorithm = "AES";
				String symmetricEncryptionMode = "CBC";
		 	    String symmetricEncryptionPadding = "NoPadding";
				
				
				// Set the Secret Key and its specifications,
		 		// using the AES (Advanced Encryption Standard - Rijndael) Symmetric Encryption
				SecretKeySpec secretKeySpecifications = new SecretKeySpec(secretKeyBytes, symmetricEncryptionAlgorithm);
				
				
				String provider = "BC";
				Cipher proofOfWorkMessageSolvedBlockSymmetricEncryptionCipher = 
						Cipher.getInstance(String.format("%s/%s/%s",
										   symmetricEncryptionAlgorithm, symmetricEncryptionMode, symmetricEncryptionPadding), 
								           provider );
				
				byte[] initialisationVectorBytes = null;
				
				if(CommonUtils.blockModeRequiresIV(symmetricEncryptionMode)) {

					// Algorithms that don't need IV (Initialisation Vector): ECB
					// The parameter specifications for the IV (Initialisation Vector)	
					System.out.println("[ProofOfWorkMessageSolvedBlockSignature.ENCRYPT] Cipher's Block Mode needs IV (Initialisation Vector)!!!");
					initialisationVectorBytes = 
							CommonUtils.generateIV(proofOfWorkMessageSolvedBlockSymmetricEncryptionCipher);
					
					// Showing the randomly defined IV (Initialisation Vector)
					System.out.println("[ProofOfWorkMessageSolvedBlockSignature.ENCRYPT] - IV (Initialisation Vector) is:\n- " 
									   + CommonUtils.fromByteArrayToHexadecimalFormat(initialisationVectorBytes));
					
					IvParameterSpec initializationVectorParameterSpecifications = new IvParameterSpec(initialisationVectorBytes);
					proofOfWorkMessageSolvedBlockSymmetricEncryptionCipher
						.init(Cipher.ENCRYPT_MODE, secretKeySpecifications, initializationVectorParameterSpecifications);
					
				}
				else {
					
					// Algorithms that need IV (Initialisation Vector)
					// The parameter specifications for the IV (Initialisation Vector)
					System.out.println("[ProofOfWorkMessageSolvedBlockSignature.ENCRYPT] Cipher's Block Mode doesn't needs IV (Initialisation Vector)!!!");
					
					proofOfWorkMessageSolvedBlockSymmetricEncryptionCipher
						.init(Cipher.ENCRYPT_MODE, secretKeySpecifications);
					
				}
				
				this.blockHashSolvedCiphered = 
						proofOfWorkMessageSolvedBlockSymmetricEncryptionCipher.doFinal(this.blockHashSolved);
				
				
				this.setIsBlockHashSolvedCiphered(true);		
				
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
	
	private void decryptBlockHashSolved() {
		
		boolean isPossibleToDecryptBlockSolved = 
				( this.getIsBlockHashSolvedCiphered() && !this.getIsProofOfWorkDigitalSigned() );
		
		if(isPossibleToDecryptBlockSolved) {
			
			byte[] secretKeyBytes = null;
			
			try {
				
				String symmetricEncryptionAlgorithm = "AES";
				String symmetricEncryptionMode = "CBC";
		 	    String symmetricEncryptionPadding = "NoPadding";
				
				
				// Set the Secret Key and its specifications,
		 		// using the AES (Advanced Encryption Standard - Rijndael) Symmetric Encryption
				SecretKeySpec secretKeySpecifications = new SecretKeySpec(secretKeyBytes, symmetricEncryptionAlgorithm);
				
				
				String provider = "BC";
				Cipher proofOfWorkMessageSolvedBlockSymmetricEncryptionDecipher = 
						Cipher.getInstance(String.format("%s/%s/%s",
										   symmetricEncryptionAlgorithm, symmetricEncryptionMode, symmetricEncryptionPadding), 
								           provider );
				
				byte[] initialisationVectorBytes = null;
			
				if(CommonUtils.blockModeRequiresIV(symmetricEncryptionMode)) {
					
					// Algorithms that don't need IV (Initialisation Vector): ECB
					// The parameter specifications for the IV (Initialisation Vector)	
					System.out.println("[ProofOfWorkMessageSolvedBlockSignature.DECRYPT] Cipher's Block Mode needs IV (Initialisation Vector)!!!");
					
					// Showing the randomly defined IV (Initialisation Vector)
					System.out.println("[ProofOfWorkMessageSolvedBlockSignature.DECRYPT] - IV (Initialisation Vector) is:\n- " 
									   + CommonUtils.fromByteArrayToHexadecimalFormat(initialisationVectorBytes));
					
					IvParameterSpec initializationVectorParameterSpecifications = new IvParameterSpec(initialisationVectorBytes);
					proofOfWorkMessageSolvedBlockSymmetricEncryptionDecipher
						.init(Cipher.DECRYPT_MODE, secretKeySpecifications, initializationVectorParameterSpecifications);
				}
				else {
					
					// Algorithms that need IV (Initialisation Vector)
					// The parameter specifications for the IV (Initialisation Vector)
					System.out.println("[ProofOfWorkMessageSolvedBlockSignature.DECRYPT] Cipher's Block Mode doesn't needs IV (Initialisation Vector)!!!");
					
					proofOfWorkMessageSolvedBlockSymmetricEncryptionDecipher
						.init(Cipher.DECRYPT_MODE, secretKeySpecifications);
					
				}
				
				int sizeOfBlockHashSolvedCiphered = 
						this.getSizeOfBlockHashSolvedCiphered();
				
			  	// The Plain Text of the bytes of the data input received through the communication channel
			  	this.blockHashSolved = new byte[ proofOfWorkMessageSolvedBlockSymmetricEncryptionDecipher
			  	                                                .getOutputSize(sizeOfBlockHashSolvedCiphered) ];
			    
			  	int sizeOfBlockHashSolved = proofOfWorkMessageSolvedBlockSymmetricEncryptionDecipher
				  									     .update(this.blockHashSolvedCiphered, 
				  										   	     0, sizeOfBlockHashSolvedCiphered,
				  											     this.blockHashSolved, 0);
			  	
			  	proofOfWorkMessageSolvedBlockSymmetricEncryptionDecipher
			  									   .doFinal(this.blockHashSolved, sizeOfBlockHashSolved);
			    
			  	
				this.setIsBlockHashSolvedCiphered(false);		
				
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
	
	private void doDigitalSignatureOfChallengeSolved() {
		
		boolean isPossibleToSignEncryptedBlockSolved = 
				( this.getIsBlockHashSolvedCiphered() && !this.getIsProofOfWorkDigitalSigned() );
		
		if(isPossibleToSignEncryptedBlockSolved) {
			
			byte[] blockSerialized = this.block.getBlockSerialized();
			byte[] blockSolvedCiphered = this.getBlockHashSolvedCiphered();
			
			int sizeOfProofOfWorkDigitalSigned = (blockSerialized.length + blockSolvedCiphered.length);
			
			this.proofOfWorkDigitalSigned = new byte[sizeOfProofOfWorkDigitalSigned];
			
			// Operations to Fill a Byte Array, with the following parameters:
			// 1) src - The source of the array to be copied
			// 2) srcPos - The position from the array to be copied, representing the first element to be copied
			// 3) dest - The destination of the array to be copied
			// 4) destPos - The position of the array where will be placed the new copy,
			//              representing the first element where new data will be placed
			// 5) length - The length of the data to be copied from the source array to the destination array
			
			// The offset related to fulfilment of the serialization process
			int serializationOffset = 0;
			
			// Fills the byte array of the Bid Digital Signed with the Bid's Serialization,
			// From the initial position to the corresponding to the length of Bid's Serialization
			System.arraycopy(blockSerialized, 0, this.proofOfWorkDigitalSigned, serializationOffset, blockSerialized.length);
			serializationOffset += blockSerialized.length;
			
			// Fills the byte array of the Bid Digital Signed with the Hash of the Bid's Serialization Ciphered,
			// From the position corresponding to the length of Bid's Serialization to
			// the corresponding of the length of the Hash of the Bid's Serialization Ciphered
			System.arraycopy(blockSolvedCiphered, 0, this.proofOfWorkDigitalSigned, serializationOffset, blockSolvedCiphered.length);
			
			
			this.setIsProofOfWorkDigitalSigned(true);
			
		}
		
	}	
	
	private void undoDigitalSignatureOfChallengeSolved() {
		
		byte[] proofOfWorkDigitalSigned = this.getProofOfWorkDigitalSigned();
		
		this.blockSerialized = new byte[ this.getSizeOfBlockSerialized() ];
		this.blockHashSolvedCiphered = new byte[ this.getSizeOfBlockHashSolvedCiphered() ];
		
		
		// Operations to Fill a Byte Array, with the following parameters:
		// 1) src - The source of the array to be copied
		// 2) srcPos - The position from the array to be copied, representing the first element to be copied
		// 3) dest - The destination of the array to be copied
		// 4) destPos - The position of the array where will be placed the new copy,
		//              representing the first element where new data will be placed
		// 5) length - The length of the data to be copied from the source array to the destination array
		
		// The offset related to fulfilment of the serialization process
		int serializationOffset = 0;
		
		// Fills the byte array of the Bid's Serialization with
		// the correspondent bytes from the Bid Digital Signed,
		// From the initial position to the corresponding to the length of Bid's Serialization
		System.arraycopy(proofOfWorkDigitalSigned, serializationOffset, this.blockSerialized, 0, this.blockSerialized.length);
		serializationOffset += this.blockSerialized.length;
		
		// Fills the byte array of the Bid's Serialization Ciphered with
		// the correspondent bytes from the Hash of the Bid Digital Signed,
		// From the position corresponding to the length of Bid's Serialization to
		// the corresponding of the length of the Hash of the Bid's Serialization Ciphered
		System.arraycopy(proofOfWorkDigitalSigned, serializationOffset, this.blockHashSolvedCiphered, 0, this.blockHashSolvedCiphered.length);
		
		
		this.setIsProofOfWorkDigitalSigned(false);
		
	}
}
