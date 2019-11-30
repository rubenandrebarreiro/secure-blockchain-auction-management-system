package main.java.messages.secure.proofwork.solvedblock;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import main.java.common.utils.CommonUtils;
import main.java.resources.block.Block;

public class ProofOfWorkMessageSolvedBlockSignature {
	
	private Block block;

	private byte[] blockSolved;
	
	private byte[] blockSolvedCiphered;
	
	private boolean isBlockSolvedCiphered;
	
	private byte[] proofOfWorkDigitalSigned;

	private boolean isProofOfWorkDigitalSigned;
	
	
	public ProofOfWorkMessageSolvedBlockSignature(Block block, byte[] blockSolved) {
		
		this.block = block;
		this.block.doBlockSerialization();
		
		this.blockSolved = blockSolved;
		
		this.blockSolvedCiphered = null;
		this.isBlockSolvedCiphered = false;
		
		this.proofOfWorkDigitalSigned = null;
		this.isProofOfWorkDigitalSigned = false;
		
	}
	
	public Block getBlock() {
		return this.block;
	}
	
	public void setBlock(Block block) {
		this.block = block;
	}
	
	public byte[] getBlockSolved() {
		return this.blockSolved;
	}
	
	public void setBlockSolved(byte[] blockSolved) {
		this.blockSolved = blockSolved;
	}
	
	public byte[] getBlockSolvedCiphered() {
		return this.blockSolvedCiphered;
	}
	
	public void setBlockSolvedCiphered(byte[] blockSolvedCiphered) {
		this.blockSolvedCiphered = blockSolvedCiphered;
	}
	
	public boolean getIsBlockSolvedCiphered() {
		return this.isBlockSolvedCiphered;
	}
	
	public void setIsBlockSolvedCiphered(boolean isBlockSolvedCiphered) {
		this.isBlockSolvedCiphered = isBlockSolvedCiphered;
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
					( !this.getIsBlockSolvedCiphered() && !this.getIsProofOfWorkDigitalSigned() );	

			if(isPossibleToBuildProofOfWorkMessageSolvedBlockSignatureToSend) {

				this.encryptBlockSolved();
				
				this.doDigitalSignatureOfChallengeSolved();
				
			}

	}
	
	public void buildProofOfWorkMessageSolvedBlockSignatureReceived() {
		
	}
	
	
	
	private void encryptBlockSolved()
			throws NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException,
			       InvalidKeyException, InvalidAlgorithmParameterException {
		
		boolean isPossibleToEncryptBlockSolved = 
				( !this.getIsBlockSolvedCiphered() && !this.getIsProofOfWorkDigitalSigned() );
	
		if(isPossibleToEncryptBlockSolved) {
			
			// TODO - to complete
			
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
					System.out.println("[SecureBidMessageSignatureProposal.ENCRYPT] Cipher's Block Mode needs IV (Initialisation Vector)!!!");
					initialisationVectorBytes = 
							CommonUtils.generateIV(proofOfWorkMessageSolvedBlockSymmetricEncryptionCipher);
					
					// Showing the randomly defined IV (Initialisation Vector)
					System.out.println("[SecureBidMessageSignatureProposal.ENCRYPT] - IV (Initialisation Vector) is:\n- " 
									   + CommonUtils.fromByteArrayToHexadecimalFormat(initialisationVectorBytes));
					
					IvParameterSpec initializationVectorParameterSpecifications = new IvParameterSpec(initialisationVectorBytes);
					proofOfWorkMessageSolvedBlockSymmetricEncryptionCipher
						.init(Cipher.ENCRYPT_MODE, secretKeySpecifications, initializationVectorParameterSpecifications);
					
				}
				else {
					
					// Algorithms that need IV (Initialisation Vector)
					// The parameter specifications for the IV (Initialisation Vector)
					System.out.println("[SecureBidMessageSignatureProposal.ENCRYPT] Cipher's Block Mode doesn't needs IV (Initialisation Vector)!!!");
					
					proofOfWorkMessageSolvedBlockSymmetricEncryptionCipher
						.init(Cipher.ENCRYPT_MODE, secretKeySpecifications);
					
				}
				
				this.blockSolvedCiphered = 
						proofOfWorkMessageSolvedBlockSymmetricEncryptionCipher.doFinal(this.blockSolved);
				
				
				this.setIsBlockSolvedCiphered(true);		
				
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
	
	private void decryptBlockSolved() {
		
	}
	
	private void doDigitalSignatureOfChallengeSolved() {
		
		boolean isPossibleToSignEncryptedBlockSolved = 
				( this.getIsBlockSolvedCiphered() && !this.getIsProofOfWorkDigitalSigned() );
		
		if(isPossibleToSignEncryptedBlockSolved) {
			
			byte[] blockSerialized = this.block.getBlockSerialized();
			byte[] blockSolvedCiphered = this.getBlockSolvedCiphered();
			
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
	
	}
}
