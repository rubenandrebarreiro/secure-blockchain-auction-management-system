package messages.secure.bid.components.content.proposal.signature;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import common.utils.CommonUtils;
import resources.bid.Bid;

public class SecureBidMessageSignatureProposal {

	private Bid bid;

	private byte[] bidSerialized;

	private boolean isBidSerialized;

	private int sizeOfBidSerialized;

	private byte[] bidSerializedHashed;

	private boolean isBidSerializedHashed;

	private byte[] bidSerializedHashedCiphered;

	private boolean isBidSerializedHashedCiphered;

	private byte[] bidDigitalSigned;

	private boolean isBidDigitalSigned;


	public SecureBidMessageSignatureProposal(Bid bid) {

		this.bid = bid;

		this.bidSerialized = null;
		this.isBidSerialized = false;
		this.sizeOfBidSerialized = 0;

		this.bidSerializedHashed = null;
		this.isBidSerializedHashed = false;

		this.bidSerializedHashedCiphered = null;
		this.isBidSerializedHashedCiphered = false;

		this.bidDigitalSigned = null;
		this.isBidDigitalSigned = false;

	}

	public SecureBidMessageSignatureProposal(byte[] bidDigitalSigned, int sizeOfBidSerialized) {

		this.bidDigitalSigned = bidDigitalSigned;
		this.isBidDigitalSigned = true;

		this.bidSerializedHashedCiphered = null;
		this.isBidSerializedHashedCiphered = true;

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

	public void buildSecureBidMessageSignatureToSend() throws NoSuchAlgorithmException {

		boolean isPossibleToBuildSecureBidMessageSignatureToSend = 
				( !this.getIsBidSerialized() && !this.getIsBidSerializedHashed() && 
						!this.getIsBidSerializedHashedCiphered() && !this.getIsBidDigitalSigned() );	

		if(isPossibleToBuildSecureBidMessageSignatureToSend) {

			this.doSerializationOfBid();

			

		}

	}

	public void doSerializationOfBid() {

		boolean isPossibleToDoSerializationOfBid = 
				( !this.getIsBidSerialized() && !this.getIsBidSerializedHashed() && 
						!this.getIsBidSerializedHashedCiphered() && !this.getIsBidDigitalSigned() );

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
						!this.getIsBidSerializedHashedCiphered() && !this.getIsBidDigitalSigned() );

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

	private void encryptSerializedHashedBid() {
		
		boolean isPossibleToEncryptBidSerializedHashed = 
				( this.getIsBidSerialized() && this.getIsBidSerializedHashed() && 
						!this.getIsBidSerializedHashedCiphered() && !this.getIsBidDigitalSigned() );

		if(isPossibleToEncryptBidSerializedHashed) {
			
			// TODO - to complete
			
			
			
			// Set the Secret Key and its specifications,
	 		// using the AES (Advanced Encryption Standard - Rijndael) Symmetric Encryption
	 	 //   SecretKeySpec secretKeySpecifications = new SecretKeySpec(secretKeyBytes, symmetricEncryptionAlgorithm);
	 	    
			
			//Cipher secureBidMessageSignatureProposalSymmetricEncryptionCipher = 
				//		Cipher.getInstance(String.format("%s/%s/%s",
					//					   symmetricEncryptionAlgorithm, symmetricEncryptionMode, symmetricEncryptionPadding), 
						//		           provider);

			//if(requiresIV(symmetricEncryptionMode)) {
				
				// Modes that do not need IVs: ECB
				// The parameter specifications for the Initialization Vector				
				//System.out.println("[SecureBidMessageSignatureProposal.ENCRYPT] Block mode needs IV");
				//this.IVBytes = generateIV(secureMessagePayloadSerializationSymmetricEncryptionCipher);
				
				//System.out.println("[SecureBidMessageSignatureProposal.ENCRYPT] IV is: " + CommonUtils.fromByteArrayToHexadecimalFormat(this.IVBytes));
				
				//IvParameterSpec initializationVectorParameterSpecifications = new IvParameterSpec(this.IVBytes);
				//secureMessagePayloadSerializationSymmetricEncryptionCipher
					//.init(Cipher.ENCRYPT_MODE, secretKeySpecifications, initializationVectorParameterSpecifications);
			//}
			//else {
				//System.out.println("[SecureBidMessageSignatureProposal.ENCRYPT] Block mode doesn't needs IV");
			//	secureMessagePayloadSerializationSymmetricEncryptionCipher
				//	.init(Cipher.ENCRYPT_MODE, secretKeySpecifications);
		//	}
							
		//	this.bidSerializedHashedCiphered = 
			//		secureBidMessageSignatureProposalSymmetricEncryptionCipher.doFinal(this.bidSerializedHashed);
			
			
			this.setIsBidSerializedHashedCiphered(true);			
		}
		
	}
	
	private void decryptSerializedHashedBid() {
		
	}
}