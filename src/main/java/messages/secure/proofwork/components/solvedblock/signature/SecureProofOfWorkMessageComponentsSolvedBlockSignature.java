package main.java.messages.secure.proofwork.components.solvedblock.signature;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;

import main.java.common.utils.CommonUtils;
import main.java.messages.secure.proofwork.components.solvedblock.confidential.SecureProofOfWorkMessageComponentsSolvedBlockConfidential;

public class SecureProofOfWorkMessageComponentsSolvedBlockSignature {
	
	
	private SecureProofOfWorkMessageComponentsSolvedBlockConfidential 
			secureProofOfWorkMessageComponentsSolvedBlockConfidential;
	
	
	private byte[] secureProofOfWorkMessageComponentsSolvedBlockConfidentialSerialized;
	
	private boolean isSecureProofOfWorkMessageComponentsSolvedBlockConfidentialSerialized;
	
	private int sizeOfSecureProofOfWorkMessageComponentsSolvedBlockConfidentialSerialized;
	
	
	private byte[] secureProofOfWorkMessageComponentsSolvedBlockConfidentialDigitalSigned;
	
	private boolean isSecureProofOfWorkMessageComponentsSolvedBlockConfidentialDigitalSigned;
	
	
	private boolean isSecureProofOfWorkMessageComponentsSolvedBlockConfidentialDigitalSignedVerified;
	
	private boolean isSecureProofOfWorkMessageComponentsSolvedBlockConfidentialDigitalSignedValid;
	
	
	
	private String userPeerID;
	
	
	public SecureProofOfWorkMessageComponentsSolvedBlockSignature
		  (SecureProofOfWorkMessageComponentsSolvedBlockConfidential secureProofOfWorkMessageComponentsSolvedBlockConfidential,
		   String userPeerID) {
		
		this.secureProofOfWorkMessageComponentsSolvedBlockConfidential = secureProofOfWorkMessageComponentsSolvedBlockConfidential;
		
		
		this.secureProofOfWorkMessageComponentsSolvedBlockConfidential.buildSecureProofOfWorkMessageComponentsSolvedBlockConfidentialToSend();
		
		
		this.secureProofOfWorkMessageComponentsSolvedBlockConfidentialSerialized = 
			 secureProofOfWorkMessageComponentsSolvedBlockConfidential.getBlockSerializedAndSolvedHashedCiphered();
		
		this.isSecureProofOfWorkMessageComponentsSolvedBlockConfidentialSerialized = true;
		
		this.sizeOfSecureProofOfWorkMessageComponentsSolvedBlockConfidentialSerialized = 
				this.secureProofOfWorkMessageComponentsSolvedBlockConfidentialSerialized.length;
		
		
		this.secureProofOfWorkMessageComponentsSolvedBlockConfidentialDigitalSigned = null;
		this.isSecureProofOfWorkMessageComponentsSolvedBlockConfidentialDigitalSigned = false;
		
		
		this.isSecureProofOfWorkMessageComponentsSolvedBlockConfidentialDigitalSignedVerified = false;
		this.isSecureProofOfWorkMessageComponentsSolvedBlockConfidentialDigitalSignedValid = false;
		
	}
	
	
	public SecureProofOfWorkMessageComponentsSolvedBlockSignature
		  (byte[] secureProofOfWorkMessageComponentsSolvedBlockConfidentialDigitalSigned,
		   byte[] secureProofOfWorkMessageComponentsSolvedBlockConfidentialSerialized,
		   String userPeerID) {
	
		this.secureProofOfWorkMessageComponentsSolvedBlockConfidentialDigitalSigned = 
				secureProofOfWorkMessageComponentsSolvedBlockConfidentialDigitalSigned;
		this.isSecureProofOfWorkMessageComponentsSolvedBlockConfidentialDigitalSigned = true;
		
		
		this.secureProofOfWorkMessageComponentsSolvedBlockConfidentialSerialized = 
				secureProofOfWorkMessageComponentsSolvedBlockConfidentialSerialized;
		this.isSecureProofOfWorkMessageComponentsSolvedBlockConfidentialSerialized = true;
		this.sizeOfSecureProofOfWorkMessageComponentsSolvedBlockConfidentialSerialized = 
				secureProofOfWorkMessageComponentsSolvedBlockConfidentialSerialized.length;
		
		
		this.secureProofOfWorkMessageComponentsSolvedBlockConfidential = null;
		
		
		this.isSecureProofOfWorkMessageComponentsSolvedBlockConfidentialDigitalSignedVerified = false;
		this.isSecureProofOfWorkMessageComponentsSolvedBlockConfidentialDigitalSignedValid = false;
		
	}
	
	
	
	
	public SecureProofOfWorkMessageComponentsSolvedBlockConfidential getSecureProofOfWorkMessageComponentsSolvedBlockConfidential() {
		return this.secureProofOfWorkMessageComponentsSolvedBlockConfidential;
	}
	
	
	public byte[] getSecureProofOfWorkMessageComponentsSolvedBlockConfidentialSerialized() {
		return this.secureProofOfWorkMessageComponentsSolvedBlockConfidentialSerialized;
	}
	
	public boolean getIsSecureProofOfWorkMessageComponentsSolvedBlockConfidentialSerialized() {
		return this.isSecureProofOfWorkMessageComponentsSolvedBlockConfidentialSerialized;
	}
	
	public void setIsSecureProofOfWorkMessageComponentsSolvedBlockConfidentialSerialized
	       (boolean isSecureProofOfWorkMessageComponentsSolvedBlockConfidentialSerialized) {
		
		this.isSecureProofOfWorkMessageComponentsSolvedBlockConfidentialSerialized = 
				isSecureProofOfWorkMessageComponentsSolvedBlockConfidentialSerialized;
		
	}
	
	public int getSizeOfSecureProofOfWorkMessageComponentsSolvedBlockConfidentialSerialized() {
		return this.sizeOfSecureProofOfWorkMessageComponentsSolvedBlockConfidentialSerialized;
	}
	
	
	public byte[] getSecureProofOfWorkMessageComponentsSolvedBlockConfidentialDigitalSigned() {
		return this.secureProofOfWorkMessageComponentsSolvedBlockConfidentialDigitalSigned;
	}
	
	public boolean getIsSecureProofOfWorkMessageComponentsSolvedBlockConfidentialDigitalSigned() {
		return this.isSecureProofOfWorkMessageComponentsSolvedBlockConfidentialDigitalSigned;
	}
	
	public void setIsSecureProofOfWorkMessageComponentsSolvedBlockConfidentialDigitalSigned
		  (boolean isSecureProofOfWorkMessageComponentsSolvedBlockConfidentialDigitalSigned) {
		
		this.isSecureProofOfWorkMessageComponentsSolvedBlockConfidentialDigitalSigned = 
				isSecureProofOfWorkMessageComponentsSolvedBlockConfidentialDigitalSigned;
	
	}
	
	public boolean getIsSecureProofOfWorkMessageComponentsSolvedBlockConfidentialDigitalSignedVerified() {
		return this.isSecureProofOfWorkMessageComponentsSolvedBlockConfidentialDigitalSignedVerified;
	}
	
	public void setIsSecureProofOfWorkMessageComponentsSolvedBlockConfidentialDigitalSignedVerified
		  (boolean isSecureProofOfWorkMessageComponentsSolvedBlockConfidentialDigitalSignedVerified) {
		
		this.isSecureProofOfWorkMessageComponentsSolvedBlockConfidentialDigitalSignedVerified = 
				isSecureProofOfWorkMessageComponentsSolvedBlockConfidentialDigitalSignedVerified;
	
	}
	
	public boolean getIsSecureProofOfWorkMessageComponentsSolvedBlockConfidentialDigitalSignedValid() {
		return this.isSecureProofOfWorkMessageComponentsSolvedBlockConfidentialDigitalSignedValid;
	}
	
	public void setIsSecureProofOfWorkMessageComponentsSolvedBlockConfidentialDigitalSignedValid
		  (boolean isSecureProofOfWorkMessageComponentsSolvedBlockConfidentialDigitalSignedValid) {
		
		this.isSecureProofOfWorkMessageComponentsSolvedBlockConfidentialDigitalSignedValid = 
				isSecureProofOfWorkMessageComponentsSolvedBlockConfidentialDigitalSignedValid;
	
	}
	
	
	
	public void signSecureProofOfWorkMessageComponentsSolvedBlock()
		   throws SignatureException, InvalidKeyException, NoSuchAlgorithmException {

		boolean isPossibleToSignBidSerialized = true;
		
		if(isPossibleToSignBidSerialized) {

			Signature secureProofOfWorkMessageComponentsSolvedBlockDataConfidentialSerializedSignature = 
					  Signature.getInstance("SHA256withRSA");
			
			PrivateKey userClientPrivateKey = CommonUtils.readKeysFromKeystore(this.userPeerID).getPrivate(); //TODO Private Key to Sign contained in the KeyStore of the User
			
			secureProofOfWorkMessageComponentsSolvedBlockDataConfidentialSerializedSignature.initSign(userClientPrivateKey);
			
			secureProofOfWorkMessageComponentsSolvedBlockDataConfidentialSerializedSignature
				.update(this.secureProofOfWorkMessageComponentsSolvedBlockConfidentialSerialized);
			
			this.secureProofOfWorkMessageComponentsSolvedBlockConfidentialDigitalSigned = 
					secureProofOfWorkMessageComponentsSolvedBlockDataConfidentialSerializedSignature.sign();
			
			
			this.setIsSecureProofOfWorkMessageComponentsSolvedBlockConfidentialDigitalSigned(true);			
		}
		
	}
	
	
	public boolean checkIfProofOfWorkMessageComponentsSolvedBlockDigitalSignedIsValid()
		   throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
				
		boolean isPossibleToVerifyProofOfWorkMessageComponentsSolvedBlockDigitalSigned = true; //TODO


		if(isPossibleToVerifyProofOfWorkMessageComponentsSolvedBlockDigitalSigned) {

			Signature secureProofOfWorkMessageComponentsSolvedBlockDataConfidentialSerializedSignature = 
					Signature.getInstance("SHA256withRSA");

			PublicKey userClientPublicKey = CommonUtils.readCertificate(userPeerID).getPublicKey(); //TODO Public Key or Certificate of the User contained in the Server 

			secureProofOfWorkMessageComponentsSolvedBlockDataConfidentialSerializedSignature.initVerify(userClientPublicKey);

			secureProofOfWorkMessageComponentsSolvedBlockDataConfidentialSerializedSignature
				.update(this.secureProofOfWorkMessageComponentsSolvedBlockConfidentialSerialized);

			this.isSecureProofOfWorkMessageComponentsSolvedBlockConfidentialDigitalSignedValid = 
					secureProofOfWorkMessageComponentsSolvedBlockDataConfidentialSerializedSignature
					.verify(this.secureProofOfWorkMessageComponentsSolvedBlockConfidentialDigitalSigned);


			if(this.isSecureProofOfWorkMessageComponentsSolvedBlockConfidentialDigitalSignedValid) {

				System.out.println("Valid Signature!!!");

			}
			else {

				System.out.println("Invalid Signature!!!");

			}

			this.setIsSecureProofOfWorkMessageComponentsSolvedBlockConfidentialDigitalSignedValid(true);
			this.setIsSecureProofOfWorkMessageComponentsSolvedBlockConfidentialDigitalSigned(false);


			return this.isSecureProofOfWorkMessageComponentsSolvedBlockConfidentialDigitalSignedValid;

		}

		return false;

	}
}
