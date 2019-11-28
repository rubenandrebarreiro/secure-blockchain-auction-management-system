package main.java.messages.secure.bid.components.content.proposal.signature;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.Random;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import main.java.common.utils.CommonUtils;
import main.java.resources.bid.Bid;

public class SecureBidMessageSignatureProposal {

	private Bid bid;

	private byte[] bidSerialized;

	private boolean isBidSerialized;

	private int sizeOfBidSerialized;

	private byte[] bidSerializedHashed;

	private boolean isBidSerializedHashed;
	

	private byte[] bidSerializedHashedChallenge;

	private boolean isBidSerializedHashedChallengeMade;
	
	
	private byte[] bidSerializedHashedChallengeCiphered;

	private boolean isBidSerializedHashedChallengeCiphered;

	private byte[] bidDigitalSigned;

	private boolean isBidDigitalSigned;


	public SecureBidMessageSignatureProposal(Bid bid) {

		this.bid = bid;

		this.bidSerialized = null;
		this.isBidSerialized = false;
		this.sizeOfBidSerialized = 0;

		this.bidSerializedHashed = null;
		this.isBidSerializedHashed = false;

		this.bidSerializedHashedChallenge = null;
		this.isBidSerializedHashedChallengeMade = false;
		
		this.bidSerializedHashedChallengeCiphered = null;
		this.isBidSerializedHashedChallengeCiphered = false;

		this.bidDigitalSigned = null;
		this.isBidDigitalSigned = false;

	}

	public SecureBidMessageSignatureProposal(byte[] bidDigitalSigned, int sizeOfBidSerialized) {

		this.bidDigitalSigned = bidDigitalSigned;
		this.isBidDigitalSigned = true;

		this.bidSerializedHashedChallengeCiphered = null;
		this.isBidSerializedHashedChallengeCiphered = true;

		this.bidSerializedHashedChallenge = null;
		this.isBidSerializedHashedChallengeMade = false;
		
		this.bidSerializedHashed = null;
		this.isBidSerializedHashed = true;

		this.bidSerialized = null;
		this.isBidSerialized = true;
		this.sizeOfBidSerialized = sizeOfBidSerialized;

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

	public byte[] getBidSerializedHashedChallenge() {
		return this.bidSerializedHashedChallenge;
	}

	public void setBidSerializedHashedChallenge(byte[] bidSerializedHashedChallenge) {
		this.bidSerializedHashedChallenge = bidSerializedHashedChallenge;
	}

	public boolean getIsBidSerializedHashedChallengeMade() {
		return this.isBidSerializedHashedChallengeMade;
	}

	public void setIsBidSerializedHashedChallengeMade(boolean isBidSerializedHashedChallengeMade) {
		this.isBidSerializedHashedChallengeMade = isBidSerializedHashedChallengeMade;
	}

	public byte[] getBidSerializedHashedChallengeCiphered() {
		return this.bidSerializedHashedChallengeCiphered;
	}

	public void setBidSerializedHashedChallengeCiphered(byte[] bidSerializedHashedChallengeCiphered) {
		this.bidSerializedHashedChallengeCiphered = bidSerializedHashedChallengeCiphered;
	}

	public boolean getIsBidSerializedHashedChallengeCiphered() {
		return this.isBidSerializedHashedChallengeCiphered;
	}

	public void setIsBidSerializedHashedChallengeCiphered(boolean isBidSerializedHashedChallengeCiphered) {
		this.isBidSerializedHashedChallengeCiphered = isBidSerializedHashedChallengeCiphered;
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
					!this.getIsBidSerializedHashedChallengeMade() && !this.getIsBidSerializedHashedChallengeCiphered() && 
						!this.getIsBidDigitalSigned() );	

		if(isPossibleToBuildSecureBidMessageSignatureToSend) {

			this.doSerializationOfBid();

			this.doHashOfSerializedBid();
			
			this.doChallengeForSerializedHashedBid();
			
			this.encryptSerializedHashedChallengeBid();

			this.doDigitalSignatureOfSerializedHashedChallengeEncryptedBid();
			
		}

	}
	
	public void buildSecureBidMessageSignatureReceived() {
		
		boolean isPossibleToBuildSecureBidMessageSignatureReceived = 
				( this.getIsBidSerialized() && this.getIsBidSerializedHashed() && 
					this.getIsBidSerializedHashedChallengeMade() && this.getIsBidSerializedHashedChallengeCiphered() && 
						this.getIsBidDigitalSigned() );
		
		
		
	}

	public void doSerializationOfBid() {
		
		boolean isPossibleToDoSerializationOfBid = 
				( !this.getIsBidSerialized() && !this.getIsBidSerializedHashed() && 
						!this.getIsBidSerializedHashedChallengeMade() && !this.getIsBidSerializedHashedChallengeCiphered() && 
							!this.getIsBidDigitalSigned() );	
			
		if(isPossibleToDoSerializationOfBid) {
			
			this.bid.doSerialization();
			
			this.bidSerialized = this.bid.getSerializedBytes();
			
			this.setIsBidSerialized(true);
			
		}
		
	}

	public void undoSerializationOfBid() {

	}

	public void doHashOfSerializedBid() throws NoSuchAlgorithmException {

		boolean isPossibleToDoHashOfBidSerialized = 
				( this.getIsBidSerialized() && !this.getIsBidSerializedHashed() && 
						!this.getIsBidSerializedHashedChallengeMade() && !this.getIsBidSerializedHashedChallengeCiphered() && 
							!this.getIsBidDigitalSigned() );	
		
		if(isPossibleToDoHashOfBidSerialized) {

			MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
			this.bidSerializedHashed = messageDigest.digest(this.bidSerialized);

			this.setIsBidSerializedHashed(true);			
		}
		
	}

	public boolean checkHashOfSerializedBid(Bid bidToCompare) {
		// TODO - to complete
		return true;
	}
	
	public void doChallengeForSerializedHashedBid() throws NoSuchAlgorithmException {

		boolean isPossibleToDoChallengeForBidSerializedHashed = 
				( this.getIsBidSerialized() && this.getIsBidSerializedHashed() && 
					!this.getIsBidSerializedHashedChallengeMade() &&
						!this.getIsBidSerializedHashedChallengeCiphered() && !this.getIsBidDigitalSigned() );

		if(isPossibleToDoChallengeForBidSerializedHashed) {
			
			Random random = new Random();
			
			this.bidSerializedHashedChallenge = this.bidSerializedHashed;
			
			// At least one byte unknown for the challenge
			int sizeOfChallenge = random.nextInt(this.bidSerializedHashedChallenge.length - 1);
			
			for(int i = sizeOfChallenge; i < sizeOfChallenge; i++) {
				this.bidSerializedHashedChallenge[i] = -1;
			}
			
			this.setIsBidSerializedHashedChallengeMade(true);			
		}
	}
	
	public void solveCryptoPuzzleChallenge() {
		// TODO - solve it
	}
	
	private void encryptSerializedHashedChallengeBid()
			throws NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException,
			       InvalidKeyException, InvalidAlgorithmParameterException {
		
		boolean isPossibleToEncryptBidSerializedHashedChallenge = 
				( this.getIsBidSerialized() && this.getIsBidSerializedHashed() && 
						this.getIsBidSerializedHashedChallengeMade() &&
							!this.getIsBidSerializedHashedChallengeCiphered() && !this.getIsBidDigitalSigned() );
	
		if(isPossibleToEncryptBidSerializedHashedChallenge) {
			
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
				
				this.bidSerializedHashedChallengeCiphered = 
						secureBidMessageSignatureProposalSerializedHashedSymmetricEncryptionCipher.doFinal(this.bidSerializedHashedChallenge);
				
				this.setIsBidSerializedHashedChallengeCiphered(true);		
				
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
	
	private void decryptSerializedHashedChallengeBid() {
		
	}
	
	private void doDigitalSignatureOfSerializedHashedChallengeEncryptedBid() {
		
		boolean isPossibleToSignBidSerializedHashedChallengeEncrypted = 
				( this.getIsBidSerialized() && this.getIsBidSerializedHashed() && 
						this.getIsBidSerializedHashedChallengeMade() &&
							this.getIsBidSerializedHashedChallengeCiphered() && !this.getIsBidDigitalSigned() );
		
		if(isPossibleToSignBidSerializedHashedChallengeEncrypted) {
			
			byte[] bidSerialized = this.getBidSerialized();
			byte[] bidSerializedHashedChallengeCiphered = this.getBidSerializedHashedChallengeCiphered();
			
			int sizeOfBidDigitalSigned = (bidSerialized.length + bidSerializedHashedChallengeCiphered.length);
			
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
			
			// Fills the byte array of the Bid Digital Signed with the Hash Challenge of the Bid's Serialization Ciphered,
			// From the position corresponding to the length of Bid's Serialization to
			// the corresponding of the length of the Hash Challenge of the Bid's Serialization Ciphered
			System.arraycopy(bidSerializedHashedChallengeCiphered, 0, this.bidDigitalSigned, serializationOffset, bidSerializedHashedChallengeCiphered.length);
		
			this.setIsBidDigitalSigned(true);
			
		}
	}
	
	private void undoSignatureSerializedHashedChallengeEncryptedBid() {
		
	}
}