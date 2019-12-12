package main.java.messages.secure.proofwork.solvedblock.signature;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;

import main.java.common.utils.CommonUtils;
import main.java.messages.secure.proofwork.solvedblock.confidential.SecureProofOfWorkMessageSolvedBlockConfidential;

public class SecureProofOfWorkMessageSolvedBlockSignature {
	
	
	private SecureProofOfWorkMessageSolvedBlockConfidential secureProofOfWorkMessageSolvedBlockConfidential;
	
	
	private byte[] secureProofOfWorkMessageSolvedBlockConfidentialSerialized;
	
	private boolean isSecureProofOfWorkMessageSolvedBlockConfidentialSerialized;
	
	private int sizeOfSecureProofOfWorkMessageSolvedBlockConfidentialSerialized;
	
	
	private byte[] secureProofOfWorkMessageSolvedBlockConfidentialDigitalSigned;
	
	private boolean isSecureProofOfWorkMessageSolvedBlockConfidentialDigitalSigned;
	
	
	private boolean isSecureProofOfWorkMessageSolvedBlockConfidentialDigitalSignedVerified;
	
	private boolean isSecureProofOfWorkMessageSolvedBlockConfidentialDigitalSignedValid;
	
	
	
	private String userPeerID;
	
	
	public SecureProofOfWorkMessageSolvedBlockSignature
		  (SecureProofOfWorkMessageSolvedBlockConfidential secureProofOfWorkMessageSolvedBlockConfidential,
		   String userPeerID) {
		
		this.secureProofOfWorkMessageSolvedBlockConfidential = secureProofOfWorkMessageSolvedBlockConfidential;
		
		
		this.secureProofOfWorkMessageSolvedBlockConfidential.buildSecureProofOfWorkMessageSolvedBlockConfidentialToSend();
		
		
		this.secureProofOfWorkMessageSolvedBlockConfidentialSerialized = 
			 secureProofOfWorkMessageSolvedBlockConfidential.getBlockSerializedAndSolvedHashedCiphered();
		
		this.isSecureProofOfWorkMessageSolvedBlockConfidentialSerialized = true;
		
		this.sizeOfSecureProofOfWorkMessageSolvedBlockConfidentialSerialized = 
				this.secureProofOfWorkMessageSolvedBlockConfidentialSerialized.length;
		
		
		this.secureProofOfWorkMessageSolvedBlockConfidentialDigitalSigned = null;
		this.isSecureProofOfWorkMessageSolvedBlockConfidentialDigitalSigned = false;
		
		
		this.isSecureProofOfWorkMessageSolvedBlockConfidentialDigitalSignedVerified = false;
		this.isSecureProofOfWorkMessageSolvedBlockConfidentialDigitalSignedValid = false;
		
	}
	
	
	public SecureProofOfWorkMessageSolvedBlockSignature
		  (byte[] secureProofOfWorkMessageSolvedBlockConfidentialDigitalSigned,
		   byte[] secureProofOfWorkMessageSolvedBlockConfidentialSerialized,
		   String userPeerID) {
	
		this.secureProofOfWorkMessageSolvedBlockConfidentialDigitalSigned = 
				secureProofOfWorkMessageSolvedBlockConfidentialDigitalSigned;
		this.isSecureProofOfWorkMessageSolvedBlockConfidentialDigitalSigned = true;
		
		
		this.secureProofOfWorkMessageSolvedBlockConfidentialSerialized = 
				secureProofOfWorkMessageSolvedBlockConfidentialSerialized;
		this.isSecureProofOfWorkMessageSolvedBlockConfidentialSerialized = true;
		this.sizeOfSecureProofOfWorkMessageSolvedBlockConfidentialSerialized = 
				secureProofOfWorkMessageSolvedBlockConfidentialSerialized.length;
		
		
		this.secureProofOfWorkMessageSolvedBlockConfidential = null;
		
		
		this.isSecureProofOfWorkMessageSolvedBlockConfidentialDigitalSignedVerified = false;
		this.isSecureProofOfWorkMessageSolvedBlockConfidentialDigitalSignedValid = false;
		
	}
	
	
	
	
	public SecureProofOfWorkMessageSolvedBlockConfidential getSecureProofOfWorkMessageSolvedBlockConfidential() {
		return this.secureProofOfWorkMessageSolvedBlockConfidential;
	}
	
	
	public byte[] getSecureProofOfWorkMessageSolvedBlockConfidentialSerialized() {
		return this.secureProofOfWorkMessageSolvedBlockConfidentialSerialized;
	}
	
	public boolean getIsSecureProofOfWorkMessageSolvedBlockConfidentialSerialized() {
		return this.isSecureProofOfWorkMessageSolvedBlockConfidentialSerialized;
	}
	
	public void setIsSecureProofOfWorkMessageSolvedBlockConfidentialSerialized
	       (boolean isSecureProofOfWorkMessageSolvedBlockConfidentialSerialized) {
		
		this.isSecureProofOfWorkMessageSolvedBlockConfidentialSerialized = 
				isSecureProofOfWorkMessageSolvedBlockConfidentialSerialized;
		
	}
	
	public int getSizeOfSecureProofOfWorkMessageSolvedBlockConfidentialSerialized() {
		return this.sizeOfSecureProofOfWorkMessageSolvedBlockConfidentialSerialized;
	}
	
	
	public byte[] getSecureProofOfWorkMessageSolvedBlockConfidentialDigitalSigned() {
		return this.secureProofOfWorkMessageSolvedBlockConfidentialDigitalSigned;
	}
	
	public boolean getIsSecureProofOfWorkMessageSolvedBlockConfidentialDigitalSigned() {
		return this.isSecureProofOfWorkMessageSolvedBlockConfidentialDigitalSigned;
	}
	
	public void setIsSecureProofOfWorkMessageSolvedBlockConfidentialDigitalSigned
		  (boolean isSecureProofOfWorkMessageSolvedBlockConfidentialDigitalSigned) {
		
		this.isSecureProofOfWorkMessageSolvedBlockConfidentialDigitalSigned = 
				isSecureProofOfWorkMessageSolvedBlockConfidentialDigitalSigned;
	
	}
	
	public boolean getIsSecureProofOfWorkMessageSolvedBlockConfidentialDigitalSignedVerified() {
		return this.isSecureProofOfWorkMessageSolvedBlockConfidentialDigitalSignedVerified;
	}
	
	public void setIsSecureProofOfWorkMessageSolvedBlockConfidentialDigitalSignedVerified
		  (boolean isSecureProofOfWorkMessageSolvedBlockConfidentialDigitalSignedVerified) {
		
		this.isSecureProofOfWorkMessageSolvedBlockConfidentialDigitalSignedVerified = 
				isSecureProofOfWorkMessageSolvedBlockConfidentialDigitalSignedVerified;
	
	}
	
	public boolean getIsSecureProofOfWorkMessageSolvedBlockConfidentialDigitalSignedValid() {
		return this.isSecureProofOfWorkMessageSolvedBlockConfidentialDigitalSignedValid;
	}
	
	public void setIsSecureProofOfWorkMessageSolvedBlockConfidentialDigitalSignedValid
		  (boolean isSecureProofOfWorkMessageSolvedBlockConfidentialDigitalSignedValid) {
		
		this.isSecureProofOfWorkMessageSolvedBlockConfidentialDigitalSignedValid = 
				isSecureProofOfWorkMessageSolvedBlockConfidentialDigitalSignedValid;
	
	}
	
	
	
	public void signSecureProofOfWorkMessageSolvedBlock()
		   throws SignatureException, InvalidKeyException, NoSuchAlgorithmException {

		boolean isPossibleToSignBidSerialized = true;
		
		if(isPossibleToSignBidSerialized) {

			Signature secureProofOfWorkMessageSolvedBlockDataConfidentialSerializedSignature = 
					  Signature.getInstance("SHA256withRSA");
			
			PrivateKey userClientPrivateKey = CommonUtils.readKeysFromKeystore(this.userPeerID).getPrivate(); //TODO Private Key to Sign contained in the KeyStore of the User
			
			secureProofOfWorkMessageSolvedBlockDataConfidentialSerializedSignature.initSign(userClientPrivateKey);
			
			secureProofOfWorkMessageSolvedBlockDataConfidentialSerializedSignature.update(this.secureProofOfWorkMessageSolvedBlockConfidentialSerialized);
			
			this.secureProofOfWorkMessageSolvedBlockConfidentialDigitalSigned = 
					secureProofOfWorkMessageSolvedBlockDataConfidentialSerializedSignature.sign();
			
			
			this.setIsSecureProofOfWorkMessageSolvedBlockConfidentialDigitalSigned(true);			
		}
		
	}
	
	
	public boolean checkIfSecureBidMessageDataSignatureBidSerializedSignedIsValid()
		   throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
				
		boolean isPossibleToVerifySecureBidMessageDataSignatureBidSerializedDigitalSigned = true;


		if(isPossibleToVerifySecureBidMessageDataSignatureBidSerializedDigitalSigned) {

			Signature secureProofOfWorkMessageSolvedBlockDataConfidentialSerializedSignature = 
					Signature.getInstance("SHA256withRSA");

			PublicKey userClientPublicKey = CommonUtils.readCertificate(userPeerID).getPublicKey(); //TODO Public Key or Certificate of the User contained in the Server 

			secureProofOfWorkMessageSolvedBlockDataConfidentialSerializedSignature.initVerify(userClientPublicKey);

			secureProofOfWorkMessageSolvedBlockDataConfidentialSerializedSignature.update(this.secureProofOfWorkMessageSolvedBlockConfidentialSerialized);

			this.isSecureProofOfWorkMessageSolvedBlockConfidentialDigitalSignedValid = 
					secureProofOfWorkMessageSolvedBlockDataConfidentialSerializedSignature
					.verify(this.secureProofOfWorkMessageSolvedBlockConfidentialDigitalSigned);


			if(this.isSecureProofOfWorkMessageSolvedBlockConfidentialDigitalSignedValid) {

				System.out.println("Valid Signature!!!");

			}
			else {

				System.out.println("Invalid Signature!!!");

			}

			this.setIsSecureProofOfWorkMessageSolvedBlockConfidentialDigitalSignedValid(true);
			this.setIsSecureProofOfWorkMessageSolvedBlockConfidentialDigitalSigned(false);


			return this.isSecureProofOfWorkMessageSolvedBlockConfidentialDigitalSignedValid;

		}

		return false;

	}
}
