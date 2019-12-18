package main.java.messages.secure.proofwork.components.solvedblock.signature;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;

import main.java.common.utils.CommonUtils;
import main.java.messages.secure.proofwork.components.solvedblock.info.SecureProofOfWorkMessageComponentsSolvedBlockInfo;

public class SecureProofOfWorkMessageComponentsSolvedBlockSignature {
	
	
	private SecureProofOfWorkMessageComponentsSolvedBlockInfo 
			secureProofOfWorkMessageComponentsSolvedBlockInfo;
	
	
	private byte[] secureProofOfWorkMessageComponentsSolvedBlockInfoSerialized;
	
	private boolean isSecureProofOfWorkMessageComponentsSolvedBlockInfoSerialized;
	
	private int sizeOfSecureProofOfWorkMessageComponentsSolvedBlockInfoSerialized;
	
	
	private byte[] secureProofOfWorkMessageComponentsSolvedBlockInfoDigitalSigned;
	
	private boolean isSecureProofOfWorkMessageComponentsSolvedBlockInfoDigitalSigned;
	
	
	private boolean isSecureProofOfWorkMessageComponentsSolvedBlockInfoDigitalSignedVerified;
	
	private boolean isSecureProofOfWorkMessageComponentsSolvedBlockInfoDigitalSignedValid;
	
	
	
	private String userPeerID;
	
	
	public SecureProofOfWorkMessageComponentsSolvedBlockSignature
		  (SecureProofOfWorkMessageComponentsSolvedBlockInfo secureProofOfWorkMessageComponentsSolvedBlockInfo,
		   String userPeerID) {
		
		this.secureProofOfWorkMessageComponentsSolvedBlockInfo = secureProofOfWorkMessageComponentsSolvedBlockInfo;
		
		
		this.secureProofOfWorkMessageComponentsSolvedBlockInfo.doBlockSerializedAndSolvedHashed();;
		
		
		this.secureProofOfWorkMessageComponentsSolvedBlockInfoSerialized = 
			 secureProofOfWorkMessageComponentsSolvedBlockInfo.getBlockSerializedAndSolvedHashed();
		
		this.isSecureProofOfWorkMessageComponentsSolvedBlockInfoSerialized = true;
		
		this.sizeOfSecureProofOfWorkMessageComponentsSolvedBlockInfoSerialized = 
				this.secureProofOfWorkMessageComponentsSolvedBlockInfoSerialized.length;
		
		
		this.secureProofOfWorkMessageComponentsSolvedBlockInfoDigitalSigned = null;
		this.isSecureProofOfWorkMessageComponentsSolvedBlockInfoDigitalSigned = false;
		
		
		this.isSecureProofOfWorkMessageComponentsSolvedBlockInfoDigitalSignedVerified = false;
		this.isSecureProofOfWorkMessageComponentsSolvedBlockInfoDigitalSignedValid = false;
		
		this.userPeerID = userPeerID;
		
	}
	
	
	public SecureProofOfWorkMessageComponentsSolvedBlockSignature
		  (byte[] secureProofOfWorkMessageComponentsSolvedBlockInfoDigitalSigned,
		   byte[] secureProofOfWorkMessageComponentsSolvedBlockInfoSerialized,
		   String userPeerID) {
	
		this.secureProofOfWorkMessageComponentsSolvedBlockInfoDigitalSigned = 
				secureProofOfWorkMessageComponentsSolvedBlockInfoDigitalSigned;
		this.isSecureProofOfWorkMessageComponentsSolvedBlockInfoDigitalSigned = true;
		
		
		this.secureProofOfWorkMessageComponentsSolvedBlockInfoSerialized = 
				secureProofOfWorkMessageComponentsSolvedBlockInfoSerialized;
		this.isSecureProofOfWorkMessageComponentsSolvedBlockInfoSerialized = true;
		this.sizeOfSecureProofOfWorkMessageComponentsSolvedBlockInfoSerialized = 
				secureProofOfWorkMessageComponentsSolvedBlockInfoSerialized.length;
		
		
		this.secureProofOfWorkMessageComponentsSolvedBlockInfo = null;
		
		
		this.isSecureProofOfWorkMessageComponentsSolvedBlockInfoDigitalSignedVerified = false;
		this.isSecureProofOfWorkMessageComponentsSolvedBlockInfoDigitalSignedValid = false;
		
		this.userPeerID = userPeerID;
		
	}
	
	
	
	
	public SecureProofOfWorkMessageComponentsSolvedBlockInfo getSecureProofOfWorkMessageComponentsSolvedBlockInfo() {
		return this.secureProofOfWorkMessageComponentsSolvedBlockInfo;
	}
	
	
	public byte[] getSecureProofOfWorkMessageComponentsSolvedBlockInfoSerialized() {
		return this.secureProofOfWorkMessageComponentsSolvedBlockInfoSerialized;
	}
	
	public boolean getIsSecureProofOfWorkMessageComponentsSolvedBlockInfoSerialized() {
		return this.isSecureProofOfWorkMessageComponentsSolvedBlockInfoSerialized;
	}
	
	public void setIsSecureProofOfWorkMessageComponentsSolvedBlockInfoSerialized
	       (boolean isSecureProofOfWorkMessageComponentsSolvedBlockInfoSerialized) {
		
		this.isSecureProofOfWorkMessageComponentsSolvedBlockInfoSerialized = 
				isSecureProofOfWorkMessageComponentsSolvedBlockInfoSerialized;
		
	}
	
	public int getSizeOfSecureProofOfWorkMessageComponentsSolvedBlockInfoSerialized() {
		return this.sizeOfSecureProofOfWorkMessageComponentsSolvedBlockInfoSerialized;
	}
	
	
	public byte[] getSecureProofOfWorkMessageComponentsSolvedBlockInfoDigitalSigned() {
		return this.secureProofOfWorkMessageComponentsSolvedBlockInfoDigitalSigned;
	}
	
	public boolean getIsSecureProofOfWorkMessageComponentsSolvedBlockInfoDigitalSigned() {
		return this.isSecureProofOfWorkMessageComponentsSolvedBlockInfoDigitalSigned;
	}
	
	public void setIsSecureProofOfWorkMessageComponentsSolvedBlockInfoDigitalSigned
		  (boolean isSecureProofOfWorkMessageComponentsSolvedBlockInfoDigitalSigned) {
		
		this.isSecureProofOfWorkMessageComponentsSolvedBlockInfoDigitalSigned = 
				isSecureProofOfWorkMessageComponentsSolvedBlockInfoDigitalSigned;
	
	}
	
	public boolean getIsSecureProofOfWorkMessageComponentsSolvedBlockInfoDigitalSignedVerified() {
		return this.isSecureProofOfWorkMessageComponentsSolvedBlockInfoDigitalSignedVerified;
	}
	
	public void setIsSecureProofOfWorkMessageComponentsSolvedBlockInfoDigitalSignedVerified
		  (boolean isSecureProofOfWorkMessageComponentsSolvedBlockInfoDigitalSignedVerified) {
		
		this.isSecureProofOfWorkMessageComponentsSolvedBlockInfoDigitalSignedVerified = 
				isSecureProofOfWorkMessageComponentsSolvedBlockInfoDigitalSignedVerified;
	
	}
	
	public boolean getIsSecureProofOfWorkMessageComponentsSolvedBlockInfoDigitalSignedValid() {
		return this.isSecureProofOfWorkMessageComponentsSolvedBlockInfoDigitalSignedValid;
	}
	
	public void setIsSecureProofOfWorkMessageComponentsSolvedBlockInfoDigitalSignedValid
		  (boolean isSecureProofOfWorkMessageComponentsSolvedBlockInfoDigitalSignedValid) {
		
		this.isSecureProofOfWorkMessageComponentsSolvedBlockInfoDigitalSignedValid = 
				isSecureProofOfWorkMessageComponentsSolvedBlockInfoDigitalSignedValid;
	
	}
	
	
	
	public void signSecureProofOfWorkMessageComponentsSolvedBlock()
		   throws SignatureException, InvalidKeyException, NoSuchAlgorithmException {

		if(!this.isSecureProofOfWorkMessageComponentsSolvedBlockInfoDigitalSigned) {

			Signature secureProofOfWorkMessageComponentsSolvedBlockDataInfoSerializedSignature = 
					  Signature.getInstance("SHA256withRSA");
			
			PrivateKey userClientPrivateKey = CommonUtils.readKeysFromKeystore(this.userPeerID).getPrivate(); //TODO Private Key to Sign contained in the KeyStore of the User
			
			secureProofOfWorkMessageComponentsSolvedBlockDataInfoSerializedSignature.initSign(userClientPrivateKey);
			
			secureProofOfWorkMessageComponentsSolvedBlockDataInfoSerializedSignature
				.update(this.secureProofOfWorkMessageComponentsSolvedBlockInfoSerialized);
			
			this.secureProofOfWorkMessageComponentsSolvedBlockInfoDigitalSigned = 
					secureProofOfWorkMessageComponentsSolvedBlockDataInfoSerializedSignature.sign();
			
			
			this.setIsSecureProofOfWorkMessageComponentsSolvedBlockInfoDigitalSigned(true);			
		}
		
	}
	
	
	public boolean checkIfProofOfWorkMessageComponentsSolvedBlockDigitalSignedIsValid()
		   throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
			
		if(this.isSecureProofOfWorkMessageComponentsSolvedBlockInfoDigitalSigned) {

			Signature secureProofOfWorkMessageComponentsSolvedBlockDataInfoSerializedSignature = 
					Signature.getInstance("SHA256withRSA");

			PublicKey userClientPublicKey = CommonUtils.readCertificate(userPeerID).getPublicKey(); //TODO Public Key or Certificate of the User contained in the Server 

			secureProofOfWorkMessageComponentsSolvedBlockDataInfoSerializedSignature.initVerify(userClientPublicKey);

			secureProofOfWorkMessageComponentsSolvedBlockDataInfoSerializedSignature
				.update(this.secureProofOfWorkMessageComponentsSolvedBlockInfoSerialized);

			this.isSecureProofOfWorkMessageComponentsSolvedBlockInfoDigitalSignedValid = 
					secureProofOfWorkMessageComponentsSolvedBlockDataInfoSerializedSignature
					.verify(this.secureProofOfWorkMessageComponentsSolvedBlockInfoDigitalSigned);


			if(this.isSecureProofOfWorkMessageComponentsSolvedBlockInfoDigitalSignedValid) {

				System.out.println("Valid Signature!!!");

			}
			else {

				System.out.println("Invalid Signature!!!");

			}

			this.setIsSecureProofOfWorkMessageComponentsSolvedBlockInfoDigitalSignedValid(true);
			this.setIsSecureProofOfWorkMessageComponentsSolvedBlockInfoDigitalSigned(false);


			return this.isSecureProofOfWorkMessageComponentsSolvedBlockInfoDigitalSignedValid;

		}

		return false;

	}
}
