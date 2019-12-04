package main.java.messages.secure.bid.data.signature;

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
import main.java.resources.bid.Bid;

public class SecureBidMessageDataSignature {

	private Bid bid;

	private byte[] bidSerialized;

	private boolean isBidSerialized;

	private int sizeOfBidSerialized;
	
	private int sizeOfBidderUserClientID;

	private byte[] bidSerializedHashed;

	private boolean isBidSerializedHashed;
	
	private boolean isBidSerializedHashedVerified;
	
	private boolean isBidSerializedHashedValid;
	
	private byte[] bidSerializedHashedCiphered;

	private boolean isBidSerializedHashedCiphered;

	private byte[] bidDigitalSigned;

	private boolean isBidDigitalSigned;


	public SecureBidMessageDataSignature(Bid bid) {

		this.bid = bid;

		this.bidSerialized = null;
		this.isBidSerialized = false;
		this.sizeOfBidSerialized = 0;

		this.bidSerializedHashed = null;
		this.isBidSerializedHashed = false;
		this.isBidSerializedHashedVerified = false;
		this.isBidSerializedHashedValid = false;
		
		this.bidSerializedHashedCiphered = null;
		this.isBidSerializedHashedCiphered = false;

		this.bidDigitalSigned = null;
		this.isBidDigitalSigned = false;

	}

	public SecureBidMessageDataSignature(byte[] bidDigitalSigned,
										 int sizeOfBidSerialized,
										 int sizeOfBidderUserClientID) {

		this.bidDigitalSigned = bidDigitalSigned;
		this.isBidDigitalSigned = true;

		this.bidSerializedHashedCiphered = null;
		this.isBidSerializedHashedCiphered = true;

		this.bidSerializedHashed = null;
		this.isBidSerializedHashed = true;
		this.isBidSerializedHashedVerified = false;
		this.isBidSerializedHashedValid = false;
		
		this.bidSerialized = null;
		this.isBidSerialized = true;
		
		this.sizeOfBidSerialized = sizeOfBidSerialized;
		this.sizeOfBidderUserClientID = sizeOfBidderUserClientID;

		this.bid = null;

	}

	public Bid getBid() {
		return this.bid;
	}

	public void setBid(Bid bid) {
		this.bid = bid;
	}

	public byte[] getBidSerialized() {
		return this.bidSerialized;
	}

	public void setBidSerialized(byte[] bidSerialized) {
		this.bidSerialized = bidSerialized;
	}

	public boolean getIsBidSerialized() {
		return this.isBidSerialized;
	}

	public void setIsBidSerialized(boolean isBidSerialized) {
		this.isBidSerialized = isBidSerialized;
	}

	public int getSizeOfBidSerialized() {
		return this.sizeOfBidSerialized;
	}

	public void setSizeOfBidSerialized(int sizeOfBidSerialized) {
		this.sizeOfBidSerialized = sizeOfBidSerialized;
	}

	public byte[] getBidSerializedHashed() {
		return this.bidSerializedHashed;
	}

	public void setBidSerializedHashed(byte[] bidSerializedHashed) {
		this.bidSerializedHashed = bidSerializedHashed;
	}

	public boolean getIsBidSerializedHashed() {
		return this.isBidSerializedHashed;
	}

	public void setIsBidSerializedHashed(boolean isBidSerializedHashed) {
		this.isBidSerializedHashed = isBidSerializedHashed;
	}

	public boolean getIsBidSerializedHashedVerified() {
		return this.isBidSerializedHashedVerified;
	}

	public void setIsBidSerializedHashedVerified(boolean isBidSerializedHashedVerified) {
		this.isBidSerializedHashedVerified = isBidSerializedHashedVerified;
	}

	public boolean getIsBidSerializedHashedValid() {
		return this.isBidSerializedHashedValid;
	}

	public void setIsBidSerializedHashedValid(boolean isBidSerializedHashedValid) {
		this.isBidSerializedHashedValid = isBidSerializedHashedValid;
	}
		
	public byte[] getBidSerializedHashedCiphered() {
		return this.bidSerializedHashedCiphered;
	}

	public void setBidSerializedHashedCiphered(byte[] bidSerializedHashedCiphered) {
		this.bidSerializedHashedCiphered = bidSerializedHashedCiphered;
	}

	public boolean getIsBidSerializedHashedCiphered() {
		return this.isBidSerializedHashedCiphered;
	}

	public void setIsBidSerializedHashedCiphered(boolean isBidSerializedHashedCiphered) {
		this.isBidSerializedHashedCiphered = isBidSerializedHashedCiphered;
	}

	public byte[] getBidDigitalSigned() {
		return this.bidDigitalSigned;
	}

	public void setBidDigitalSigned(byte[] bidDigitalSigned) {
		this.bidDigitalSigned = bidDigitalSigned;
	}

	public boolean getIsBidDigitalSigned() {
		return this.isBidDigitalSigned;
	}

	public void setIsBidDigitalSigned(boolean isBidDigitalSigned) {
		this.isBidDigitalSigned = isBidDigitalSigned;
	}

	public void buildSecureBidMessageSignatureToSend()
		   throws NoSuchAlgorithmException, InvalidKeyException, NoSuchProviderException,
		          NoSuchPaddingException, InvalidAlgorithmParameterException {

		boolean isPossibleToBuildSecureBidMessageSignatureToSend = 
				( !this.getIsBidSerialized() && !this.getIsBidSerializedHashed() && 
					!this.getIsBidSerializedHashedCiphered() && !this.getIsBidDigitalSigned() );	

		if(isPossibleToBuildSecureBidMessageSignatureToSend) {

			this.doSerializationOfBid();

			this.doHashOfSerializedBid();
						
			this.encryptSerializedHashedBid();

			this.doDigitalSignatureOfSerializedHashedEncryptedBid();
			
		}

	}
	
	public void buildSecureBidMessageSignatureReceived() throws NoSuchAlgorithmException {
		
		boolean isPossibleToBuildSecureBidMessageSignatureReceived = 
				( this.getIsBidSerialized() && this.getIsBidSerializedHashed() && 
						this.getIsBidSerializedHashedCiphered() && this.getIsBidDigitalSigned() );
		
		if(isPossibleToBuildSecureBidMessageSignatureReceived) {
			
			this.undoDigitalSignatureOfSerializedHashedEncryptedBid();
			
			this.decryptSerializedHashedBid();
			
			if(this.checkIfHashOfSerializedBidIsValid()) {
				this.undoSerializationOfBid();
			}
		}
	}

	public void doSerializationOfBid() {
		
		boolean isPossibleToDoSerializationOfBid = 
				( !this.getIsBidSerialized() && !this.getIsBidSerializedHashed() && 
						!this.getIsBidSerializedHashedCiphered() && !this.getIsBidDigitalSigned() );	
			
		if(isPossibleToDoSerializationOfBid) {
			
			this.bid.doSerialization();
			
			this.bidSerialized = this.bid.getBidSerializedBytes();
			
			this.setIsBidSerialized(true);
			
		}
		
	}

	public void undoSerializationOfBid() {
		
		boolean isPossibleToUndoSerializationOfBid = 
				( this.getIsBidSerialized() && !this.getIsBidSerializedHashed() && 
						!this.getIsBidSerializedHashedCiphered() && !this.getIsBidDigitalSigned() );	
		
		if(isPossibleToUndoSerializationOfBid) {
			
			boolean isBidSerializedHashedVerifiedAndValid = 
				( this.getIsBidSerializedHashedVerified() && this.getIsBidSerializedHashedValid() );
			
			if(isBidSerializedHashedVerifiedAndValid) {
				
				this.bid = new Bid(this.bidSerialized, this.sizeOfBidderUserClientID);
				
				this.bid.undoSerialization();
				
				this.setIsBidSerialized(false);
				
			}
			
		}
		
	}

	public void doHashOfSerializedBid() throws NoSuchAlgorithmException {

		boolean isPossibleToDoHashOfBidSerialized = 
				( this.getIsBidSerialized() && !this.getIsBidSerializedHashed() && 
						!this.getIsBidSerializedHashedCiphered() && !this.getIsBidDigitalSigned() );	
		
		if(isPossibleToDoHashOfBidSerialized) {

			MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
			this.bidSerializedHashed = messageDigest.digest(this.bidSerialized);

			this.setIsBidSerializedHashed(true);			
		}
		
	}

	public boolean checkIfHashOfSerializedBidIsValid() throws NoSuchAlgorithmException {
		
		boolean isPossibleToCheckHashOfBidSerialized = 
				( this.getIsBidSerialized() && this.getIsBidSerializedHashed() &&
						!this.getIsBidSerializedHashedCiphered() && !this.getIsBidDigitalSigned() );
		
		if(isPossibleToCheckHashOfBidSerialized) {
			
			MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
			byte[] bidSerializedHashedToCompare = messageDigest.digest(this.bidSerialized);
			
			this.isBidSerializedHashedValid = Arrays.areEqual(this.bidSerializedHashed, 
															  bidSerializedHashedToCompare) ? 
																	  true : false;
			
			
			this.setIsBidSerializedHashedVerified(true);
			this.setIsBidSerializedHashed(false);
			
			
			return this.isBidSerializedHashedValid;
			
		}
		
		return false;
	}
	
	private void encryptSerializedHashedBid()
			throws NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException,
			       InvalidKeyException, InvalidAlgorithmParameterException {
		
		boolean isPossibleToEncryptBidSerializedHashed = 
				( this.getIsBidSerialized() && this.getIsBidSerializedHashed() && 
						!this.getIsBidSerializedHashedCiphered() && !this.getIsBidDigitalSigned() );
	
		if(isPossibleToEncryptBidSerializedHashed) {
			
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
				Cipher secureBidMessageSignatureProposalSerializedHashedSymmetricEncryptionCipher = 
						Cipher.getInstance(String.format("%s/%s/%s",
										   symmetricEncryptionAlgorithm, symmetricEncryptionMode, symmetricEncryptionPadding), 
								           provider );
				
				byte[] initialisationVectorBytes = null;
				
				if(CommonUtils.blockModeRequiresIV(symmetricEncryptionMode)) {

					// Algorithms that don't need IV (Initialisation Vector): ECB
					// The parameter specifications for the IV (Initialisation Vector)	
					System.out.println("[SecureBidMessageSignatureProposal.ENCRYPT] Cipher's Block Mode needs IV (Initialisation Vector)!!!");
					initialisationVectorBytes = 
							CommonUtils.generateIV(secureBidMessageSignatureProposalSerializedHashedSymmetricEncryptionCipher);
					
					// Showing the randomly defined IV (Initialisation Vector)
					System.out.println("[SecureBidMessageSignatureProposal.ENCRYPT] - IV (Initialisation Vector) is:\n- " 
									   + CommonUtils.fromByteArrayToHexadecimalFormat(initialisationVectorBytes));
					
					IvParameterSpec initializationVectorParameterSpecifications = new IvParameterSpec(initialisationVectorBytes);
					secureBidMessageSignatureProposalSerializedHashedSymmetricEncryptionCipher
						.init(Cipher.ENCRYPT_MODE, secretKeySpecifications, initializationVectorParameterSpecifications);
					
				}
				else {
					
					// Algorithms that need IV (Initialisation Vector)
					// The parameter specifications for the IV (Initialisation Vector)
					System.out.println("[SecureBidMessageSignatureProposal.ENCRYPT] Cipher's Block Mode doesn't needs IV (Initialisation Vector)!!!");
					
					secureBidMessageSignatureProposalSerializedHashedSymmetricEncryptionCipher
						.init(Cipher.ENCRYPT_MODE, secretKeySpecifications);
					
				}
				
				this.bidSerializedHashedCiphered = 
						secureBidMessageSignatureProposalSerializedHashedSymmetricEncryptionCipher.doFinal(this.bidSerializedHashed);
				
				
				this.setIsBidSerializedHashedCiphered(true);		
				
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
	
	private void decryptSerializedHashedBid() {
	
		boolean isPossibleToDecryptBidSerializedHashed = 
				( this.getIsBidSerialized() && this.getIsBidSerializedHashed() && 
						this.getIsBidSerializedHashedCiphered() && !this.getIsBidDigitalSigned() );
		
		if(isPossibleToDecryptBidSerializedHashed) {
			
			byte[] secretKeyBytes = null;
			
			try {
				
				String symmetricEncryptionAlgorithm = "AES";
				String symmetricEncryptionMode = "CBC";
		 	    String symmetricEncryptionPadding = "NoPadding";
				
				
				// Set the Secret Key and its specifications,
		 		// using the AES (Advanced Encryption Standard - Rijndael) Symmetric Encryption
				SecretKeySpec secretKeySpecifications = new SecretKeySpec(secretKeyBytes, symmetricEncryptionAlgorithm);
				
				
				String provider = "BC";
				Cipher secureBidMessageSignatureProposalSerializedHashedSymmetricEncryptionDecipher = 
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
					secureBidMessageSignatureProposalSerializedHashedSymmetricEncryptionDecipher
						.init(Cipher.DECRYPT_MODE, secretKeySpecifications, initializationVectorParameterSpecifications);
				}
				else {
					
					// Algorithms that need IV (Initialisation Vector)
					// The parameter specifications for the IV (Initialisation Vector)
					System.out.println("[SecureBidMessageSignatureProposal.DECRYPT] Cipher's Block Mode doesn't needs IV (Initialisation Vector)!!!");
					
					secureBidMessageSignatureProposalSerializedHashedSymmetricEncryptionDecipher
						.init(Cipher.DECRYPT_MODE, secretKeySpecifications);
					
				}
				
				int sizeOfBidSerializedHashedCiphered = 
						this.bidSerializedHashedCiphered.length;
				
			  	// The Plain Text of the bytes of the data input received through the communication channel
			  	this.bidSerializedHashed = new byte[ secureBidMessageSignatureProposalSerializedHashedSymmetricEncryptionDecipher
			  	                                                .getOutputSize(sizeOfBidSerializedHashedCiphered) ];
			    
			  	int sizeOfBidSerializedHashed = secureBidMessageSignatureProposalSerializedHashedSymmetricEncryptionDecipher
				  									     .update(this.bidSerializedHashedCiphered, 
				  										   	     0, sizeOfBidSerializedHashedCiphered,
				  											     this.bidSerializedHashed, 0);
			  	
			  	secureBidMessageSignatureProposalSerializedHashedSymmetricEncryptionDecipher
			  									   .doFinal(this.bidSerializedHashed, sizeOfBidSerializedHashed);
			    
			  	
				this.setIsBidSerializedHashedCiphered(false);		
				
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
	
	private void doDigitalSignatureOfSerializedHashedEncryptedBid() {
		
		boolean isPossibleToSignSerializedHashedEncryptedBid = 
				( this.getIsBidSerialized() && this.getIsBidSerializedHashed() && 
						this.getIsBidSerializedHashedCiphered() && !this.getIsBidDigitalSigned() );
		
		if(isPossibleToSignSerializedHashedEncryptedBid) {
			
			byte[] bidSerialized = this.getBidSerialized();
			byte[] bidSerializedHashedCiphered = this.getBidSerializedHashedCiphered();
			
			int sizeOfBidDigitalSigned = (bidSerialized.length + bidSerializedHashedCiphered.length);
			
			this.bidDigitalSigned = new byte[sizeOfBidDigitalSigned];
			
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
			System.arraycopy(bidSerialized, 0, this.bidDigitalSigned, serializationOffset, bidSerialized.length);
			serializationOffset += bidSerialized.length;
			
			// Fills the byte array of the Bid Digital Signed with the Hash of the Bid's Serialization Ciphered,
			// From the position corresponding to the length of Bid's Serialization to
			// the corresponding of the length of the Hash of the Bid's Serialization Ciphered
			System.arraycopy(bidSerializedHashedCiphered, 0, this.bidDigitalSigned, serializationOffset, bidSerializedHashedCiphered.length);
			
			
			this.setIsBidDigitalSigned(true);
			
		}
	}
	
	private void undoDigitalSignatureOfSerializedHashedEncryptedBid() {
		
		boolean isPossibleToUndoBidSerializedHashedEncryptedSigned = 
				( this.getIsBidSerialized() && this.getIsBidSerializedHashed() && 
						this.getIsBidSerializedHashedCiphered() && this.getIsBidDigitalSigned() );
		
		if(isPossibleToUndoBidSerializedHashedEncryptedSigned) {
			
			byte[] bidDigitalSigned = this.getBidDigitalSigned();
			int sizeOfBidDigitalSigned = bidDigitalSigned.length;
			
			this.bidSerialized = new byte[ this.getSizeOfBidSerialized() ];
			this.bidSerializedHashedCiphered = 
					new byte[ ( sizeOfBidDigitalSigned - this.getSizeOfBidSerialized() ) ];
			
			
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
			System.arraycopy(bidDigitalSigned, serializationOffset, this.bidSerialized, 0, this.bidSerialized.length);
			serializationOffset += this.bidSerialized.length;
			
			// Fills the byte array of the Bid's Serialization Ciphered with
			// the correspondent bytes from the Hash of the Bid Digital Signed,
			// From the position corresponding to the length of Bid's Serialization to
			// the corresponding of the length of the Hash of the Bid's Serialization Ciphered
			System.arraycopy(bidDigitalSigned, serializationOffset, this.bidSerializedHashedCiphered, 0, this.bidSerializedHashedCiphered.length);
			
			
			this.setIsBidDigitalSigned(false);
		
		}
	}
}