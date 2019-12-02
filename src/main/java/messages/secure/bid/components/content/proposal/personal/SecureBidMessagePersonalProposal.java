package main.java.messages.secure.bid.components.content.proposal.personal;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.ShortBufferException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import main.java.common.utils.CommonUtils;

public class SecureBidMessagePersonalProposal {

	private String userEmail;
	
	private String userHomeAddress;

	private String userBankAccountNIB;
	
	
	private byte[] secureBidMessagePersonalProposalSerialized;
	
	private boolean isSecureBidMessagePersonalProposalSerialized;
	
	private byte[] secureBidMessagePersonalProposalSerializedCiphered;
	
	private boolean isSecureBidMessagePersonalProposalSerializedCiphered;
	
	private byte[] secureBidMessagePersonalProposalSerializedCipheredHashed;
	
	private boolean isSecureBidMessagePersonalProposalSerializedCipheredHashed;
	
	
	public SecureBidMessagePersonalProposal(String userEmail,
										    String userHomeAddress,
										    String userBankAccountNIB) {
		
		this.userEmail = userEmail;
		this.userHomeAddress = userHomeAddress;
		this.userBankAccountNIB = userBankAccountNIB;
	
		this.secureBidMessagePersonalProposalSerialized = null;
		this.isSecureBidMessagePersonalProposalSerialized = false;
		
		this.secureBidMessagePersonalProposalSerializedCiphered = null;
		this.isSecureBidMessagePersonalProposalSerializedCiphered = false;
		
		this.secureBidMessagePersonalProposalSerializedCipheredHashed = null;
		this.isSecureBidMessagePersonalProposalSerializedCipheredHashed = false;
		
	}
	
	
	public SecureBidMessagePersonalProposal() {
		
		// TODO
		
	}
	
	
	public String getUserEmail() {
		return this.userEmail;
	}
	
	public String getUserHomeAddress() {
		return this.userHomeAddress;
	}
	
	public String getUserBankAccountNIB() {
		return this.userBankAccountNIB;
	}
	
	public byte[] getSecureBidMessagePersonalProposalSerialized() {
		return this.secureBidMessagePersonalProposalSerialized;
	}
	
	public boolean getIsSecureBidMessagePersonalProposalSerialized() {
		return this.isSecureBidMessagePersonalProposalSerialized;
	}
	
	public void setIsSecureBidMessagePersonalProposalSerialized
	      (boolean isSecureBidMessagePersonalProposalSerialized) {
		
		this.isSecureBidMessagePersonalProposalSerialized = isSecureBidMessagePersonalProposalSerialized;
	
	}
	
	public byte[] getSecureBidMessagePersonalProposalSerializedCiphered() {
		return this.secureBidMessagePersonalProposalSerializedCiphered;
	}
	
	public boolean getIsSecureBidMessagePersonalProposalSerializedCiphered() {
		return this.isSecureBidMessagePersonalProposalSerializedCiphered;
	}
	
	public void setIsSecureBidMessagePersonalProposalSerializedCiphered
		  (boolean isSecureBidMessagePersonalProposalSerializedCiphered) {
		
		this.isSecureBidMessagePersonalProposalSerializedCiphered = 
				   isSecureBidMessagePersonalProposalSerializedCiphered;
			
	}
	
	public byte[] getSecureBidMessagePersonalProposalSerializedCipheredHashed() {
		return this.secureBidMessagePersonalProposalSerializedCipheredHashed;
	}
	
	public boolean getIsSecureBidMessagePersonalProposalSerializedCipheredHashed() {
		return this.isSecureBidMessagePersonalProposalSerializedCipheredHashed;
	}
	
	public void setIsSecureBidMessagePersonalProposalSerializedCipheredHashed
		  (boolean isSecureBidMessagePersonalProposalSerializedCipheredHashed) {
		
		this.isSecureBidMessagePersonalProposalSerializedCipheredHashed = 
				   isSecureBidMessagePersonalProposalSerializedCipheredHashed;
			
	}
	

	public void doSecureBidMessagePersonalProposalSerialization() {
		
		boolean isPossibleToDoSecureBidMessagePersonalProposalSerialization = 
				( !this.getIsSecureBidMessagePersonalProposalSerialized() &&
				  !this.getIsSecureBidMessagePersonalProposalSerializedCiphered() &&
				  !this.getIsSecureBidMessagePersonalProposalSerializedCipheredHashed() );
		
		if(isPossibleToDoSecureBidMessagePersonalProposalSerialization) {
			
			byte[] userEmailSerialized = CommonUtils.fromStringToByteArray(this.userEmail);
			
			byte[] userHomeAddressSerialized = CommonUtils.fromStringToByteArray(this.userHomeAddress);
			
			byte[] userBankAccountNIBSerialized = CommonUtils.fromStringToByteArray(this.userBankAccountNIB);
			
			
			int sizeOfSecureBidMessagePersonalProposalSerialized = 
										( userEmailSerialized.length + 
										  userHomeAddressSerialized.length + 
										  userBankAccountNIBSerialized.length );
			
			this.secureBidMessagePersonalProposalSerialized = 
							new byte[ sizeOfSecureBidMessagePersonalProposalSerialized ];
			
			
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
			System.arraycopy(userEmailSerialized, 0, this.secureBidMessagePersonalProposalSerialized, serializationOffset, userEmailSerialized.length);
			serializationOffset += userEmailSerialized.length;
			
			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(userHomeAddressSerialized, 0, this.secureBidMessagePersonalProposalSerialized, serializationOffset, userHomeAddressSerialized.length);
			serializationOffset += userHomeAddressSerialized.length;
			
			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(userBankAccountNIBSerialized, 0, this.secureBidMessagePersonalProposalSerialized, serializationOffset, userBankAccountNIBSerialized.length);
			
			
			this.setIsSecureBidMessagePersonalProposalSerialized(true);
			
		}
		
	}
	
	public void undoSecureBidMessagePersonalProposalSerialization() {
		
		boolean isPossibleToUndoSecureBidMessagePersonalProposalSerialization = 
				(  this.getIsSecureBidMessagePersonalProposalSerialized() &&
				  !this.getIsSecureBidMessagePersonalProposalSerializedCiphered() &&
				  !this.getIsSecureBidMessagePersonalProposalSerializedCipheredHashed() );
		
		if(isPossibleToUndoSecureBidMessagePersonalProposalSerialization) {
		
			
			
			
			this.setIsSecureBidMessagePersonalProposalSerialized(false);
			
		}
		
	}
	
	public void encryptSecureBidMessagePersonalProposalSerialized() {
		
		boolean isPossibleToEncryptSecureBidMessagePersonalProposalSerialized = 
				( this.getIsSecureBidMessagePersonalProposalSerialized() &&
				 !this.getIsSecureBidMessagePersonalProposalSerializedCiphered() &&
				 !this.getIsSecureBidMessagePersonalProposalSerializedCipheredHashed() );
		
		if(isPossibleToEncryptSecureBidMessagePersonalProposalSerialized) {
			
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
				Cipher secureBidMessagePersonalProposalSerializedSymmetricEncryptionCipher = 
						Cipher.getInstance(String.format("%s/%s/%s",
										   symmetricEncryptionAlgorithm, symmetricEncryptionMode, symmetricEncryptionPadding), 
								           provider );
				
				byte[] initialisationVectorBytes = null;
				
				if(CommonUtils.blockModeRequiresIV(symmetricEncryptionMode)) {

					// Algorithms that don't need IV (Initialisation Vector): ECB
					// The parameter specifications for the IV (Initialisation Vector)	
					System.out.println("[SecureBidMessagePersonalProposal.ENCRYPT] Cipher's Block Mode needs IV (Initialisation Vector)!!!");
					initialisationVectorBytes = 
							CommonUtils.generateIV(secureBidMessagePersonalProposalSerializedSymmetricEncryptionCipher);
					
					// Showing the randomly defined IV (Initialisation Vector)
					System.out.println("[SecureBidMessagePersonalProposal.ENCRYPT] - IV (Initialisation Vector) is:\n- " 
									   + CommonUtils.fromByteArrayToHexadecimalFormat(initialisationVectorBytes));
					
					IvParameterSpec initializationVectorParameterSpecifications = new IvParameterSpec(initialisationVectorBytes);
					secureBidMessagePersonalProposalSerializedSymmetricEncryptionCipher
						.init(Cipher.ENCRYPT_MODE, secretKeySpecifications, initializationVectorParameterSpecifications);
					
				}
				else {
					
					// Algorithms that need IV (Initialisation Vector)
					// The parameter specifications for the IV (Initialisation Vector)
					System.out.println("[SecureBidMessagePersonalProposal.ENCRYPT] Cipher's Block Mode doesn't needs IV (Initialisation Vector)!!!");
					
					secureBidMessagePersonalProposalSerializedSymmetricEncryptionCipher
						.init(Cipher.ENCRYPT_MODE, secretKeySpecifications);
					
				}
				
				this.secureBidMessagePersonalProposalSerializedCiphered = 
						secureBidMessagePersonalProposalSerializedSymmetricEncryptionCipher
						.doFinal(this.secureBidMessagePersonalProposalSerialized);
				
				
				this.setIsSecureBidMessagePersonalProposalSerializedCiphered(true);		
				
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
	
	public void decryptSecureBidMessagePersonalProposalSerialized() {
		
		boolean isPossibleToDecryptSecureBidMessagePersonalProposalSerializedCiphered = 
				( this.getIsSecureBidMessagePersonalProposalSerialized() &&
				  this.getIsSecureBidMessagePersonalProposalSerializedCiphered() &&
				 !this.getIsSecureBidMessagePersonalProposalSerializedCipheredHashed() );
	
		if(isPossibleToDecryptSecureBidMessagePersonalProposalSerializedCiphered) {
			byte[] secretKeyBytes = null;
			
			try {
				
				String symmetricEncryptionAlgorithm = "AES";
				String symmetricEncryptionMode = "CBC";
		 	    String symmetricEncryptionPadding = "NoPadding";
				
				
				// Set the Secret Key and its specifications,
		 		// using the AES (Advanced Encryption Standard - Rijndael) Symmetric Encryption
				SecretKeySpec secretKeySpecifications = new SecretKeySpec(secretKeyBytes, symmetricEncryptionAlgorithm);
				
				
				String provider = "BC";
				Cipher secureBidMessagePersonalProposalSerializedSymmetricEncryptionDecipher = 
						Cipher.getInstance(String.format("%s/%s/%s",
										   symmetricEncryptionAlgorithm, symmetricEncryptionMode, symmetricEncryptionPadding), 
								           provider );
				
				byte[] initialisationVectorBytes = null;
			
				if(CommonUtils.blockModeRequiresIV(symmetricEncryptionMode)) {
					
					// Algorithms that don't need IV (Initialisation Vector): ECB
					// The parameter specifications for the IV (Initialisation Vector)	
					System.out.println("[SecureBidMessagePersonalProposal.DECRYPT] Cipher's Block Mode needs IV (Initialisation Vector)!!!");
					
					// Showing the randomly defined IV (Initialisation Vector)
					System.out.println("[SecureBidMessagePersonalProposal.DECRYPT] - IV (Initialisation Vector) is:\n- " 
									   + CommonUtils.fromByteArrayToHexadecimalFormat(initialisationVectorBytes));
					
					IvParameterSpec initializationVectorParameterSpecifications = new IvParameterSpec(initialisationVectorBytes);
					secureBidMessagePersonalProposalSerializedSymmetricEncryptionDecipher
						.init(Cipher.DECRYPT_MODE, secretKeySpecifications, initializationVectorParameterSpecifications);
				}
				else {
					
					// Algorithms that need IV (Initialisation Vector)
					// The parameter specifications for the IV (Initialisation Vector)
					System.out.println("[SecureBidMessagePersonalProposal.DECRYPT] Cipher's Block Mode doesn't needs IV (Initialisation Vector)!!!");
					
					secureBidMessagePersonalProposalSerializedSymmetricEncryptionDecipher
						.init(Cipher.DECRYPT_MODE, secretKeySpecifications);
					
				}
				
				int sizeOfSecureBidMessagePersonalProposalSerializedCiphered = 
						this.secureBidMessagePersonalProposalSerializedCiphered.length;
				
			  	// The Plain Text of the bytes of the data input received through the communication channel
			  	this.secureBidMessagePersonalProposalSerialized = 
			  				new byte[ secureBidMessagePersonalProposalSerializedSymmetricEncryptionDecipher
			  	                      .getOutputSize(sizeOfSecureBidMessagePersonalProposalSerializedCiphered) ];
			    
			  	int sizeOfSecureBidMessagePersonalProposalSerialized = secureBidMessagePersonalProposalSerializedSymmetricEncryptionDecipher
								  									   .update(this.secureBidMessagePersonalProposalSerializedCiphered, 
								  										       0, sizeOfSecureBidMessagePersonalProposalSerializedCiphered,
								  											   this.secureBidMessagePersonalProposalSerialized, 0);
			  	
			  	secureBidMessagePersonalProposalSerializedSymmetricEncryptionDecipher
						   .doFinal(this.secureBidMessagePersonalProposalSerialized, sizeOfSecureBidMessagePersonalProposalSerialized);
			    
			  	
				this.setIsSecureBidMessagePersonalProposalSerializedCiphered(false);		
				
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
}
