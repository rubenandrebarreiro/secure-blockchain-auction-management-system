package main.java.messages.secure.receipt.components.data.signature;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;

import main.java.common.utils.CommonUtils;

public class SecureReceiptMessageComponentsDataSignature {
	
	private byte[] secureReceiptMessageComponentsDataInfoSerialized;
	
	
	private byte[] secureReceiptMessageComponentsDataInfoSerializedDigitalSigned;
	
	private boolean isSecureReceiptMessageComponentsDataInfoSerializedDigitalSigned;
	
	private int sizeOfSecureReceiptMessageComponentsDataInfoSerializedDigitalSigned;
	
	
	private boolean isSecureReceiptMessageComponentsDataInfoDigitalSignedVerified;
	
	private boolean isSecureReceiptMessageComponentsDataInfoDigitalSignedValid;
	
	
	private String userPeerID;
	
	
	
	public SecureReceiptMessageComponentsDataSignature
		  (byte[] secureReceiptMessageComponentsDataInfoSerialized,
		   String userPeerID) {
		
		this.secureReceiptMessageComponentsDataInfoSerialized = 
					secureReceiptMessageComponentsDataInfoSerialized;
		
		this.secureReceiptMessageComponentsDataInfoSerializedDigitalSigned = null;
		this.isSecureReceiptMessageComponentsDataInfoSerializedDigitalSigned = false;
		this.sizeOfSecureReceiptMessageComponentsDataInfoSerializedDigitalSigned = 0;
		
		this.isSecureReceiptMessageComponentsDataInfoDigitalSignedVerified = false;
		this.isSecureReceiptMessageComponentsDataInfoDigitalSignedValid = false;

		this.userPeerID = userPeerID;
		
	}
	
	public SecureReceiptMessageComponentsDataSignature
		  (byte[] secureReceiptMessageComponentsDataInfoSerialized,
		   byte[] secureReceiptMessageComponentsDataInfoSerializedDigitalSigned,
		   String userPeerID) {
		
		this.secureReceiptMessageComponentsDataInfoSerialized = 
				secureReceiptMessageComponentsDataInfoSerialized;
	
		this.secureReceiptMessageComponentsDataInfoSerializedDigitalSigned = 
					secureReceiptMessageComponentsDataInfoSerializedDigitalSigned;
		this.isSecureReceiptMessageComponentsDataInfoSerializedDigitalSigned = true;
		this.sizeOfSecureReceiptMessageComponentsDataInfoSerializedDigitalSigned = 0;
		
		this.isSecureReceiptMessageComponentsDataInfoDigitalSignedVerified = false;
		this.isSecureReceiptMessageComponentsDataInfoDigitalSignedValid = false;
		
		this.userPeerID = userPeerID;
		
	}
	
	
	public byte[] getSecureReceiptMessageComponentsDataInfoSerialized() {
		return this.secureReceiptMessageComponentsDataInfoSerialized;
	}
	
	public byte[] getSecureReceiptMessageComponentsDataInfoSerializedDigitalSigned() {
		return this.secureReceiptMessageComponentsDataInfoSerializedDigitalSigned;
	}
	
	public boolean getIsSecureReceiptMessageComponentsDataInfoSerializedDigitalSigned() {
		return this.isSecureReceiptMessageComponentsDataInfoSerializedDigitalSigned;
	}
	
	public void setIsSecureReceiptMessageComponentsDataInfoSerializedDigitalSigned
		  (boolean isSecureReceiptMessageComponentsDataInfoSerializedDigitalSigned) {
		
		this.isSecureReceiptMessageComponentsDataInfoSerializedDigitalSigned = 
				isSecureReceiptMessageComponentsDataInfoSerializedDigitalSigned;
		
	}
	
	public int getSizeOfSecureReceiptMessageComponentsDataInfoSerializedDigitalSigned() {
		return this.sizeOfSecureReceiptMessageComponentsDataInfoSerializedDigitalSigned;
	}
	
	public boolean getIsSecureReceiptMessageComponentsDataInfoDigitalSignedVerified() {
		return this.isSecureReceiptMessageComponentsDataInfoDigitalSignedVerified;
	}
	
	public void setIsSecureReceiptMessageComponentsDataInfoDigitalSignedVerified
		  (boolean isSecureReceiptMessageComponentsDataInfoDigitalSignedVerified) {
		
		this.isSecureReceiptMessageComponentsDataInfoDigitalSignedVerified = 
				isSecureReceiptMessageComponentsDataInfoDigitalSignedVerified;
		
	}
	
	public boolean getIsSecureReceiptMessageComponentsDataInfoDigitalSignedValid() {
		return this.isSecureReceiptMessageComponentsDataInfoDigitalSignedValid;
	}
	
	public void setIsSecureReceiptMessageComponentsDataInfoDigitalSignedValid
		  (boolean isSecureReceiptMessageComponentsDataInfoDigitalSignedValid) {
		
		this.isSecureReceiptMessageComponentsDataInfoDigitalSignedValid = 
				isSecureReceiptMessageComponentsDataInfoDigitalSignedValid;
		
	}
	
	
	
	public String getUserPeerID() {
		return this.userPeerID;
	}
	
	
	
	public void signSecureReceiptMessageComponentsDataInfoSignature()
		   throws SignatureException, InvalidKeyException, NoSuchAlgorithmException {

		if(!this.isSecureReceiptMessageComponentsDataInfoSerializedDigitalSigned) {

			Signature secureReceiptMessageComponentsDataInfoSignature = 
					  Signature.getInstance("SHA256withRSA");
			
			PrivateKey userClientPrivateKey = CommonUtils.readKeysFromKeystore(this.userPeerID).getPrivate(); //TODO Private Key to Sign contained in the KeyStore of the User
			
			secureReceiptMessageComponentsDataInfoSignature.initSign(userClientPrivateKey);
			
			secureReceiptMessageComponentsDataInfoSignature
				.update(this.secureReceiptMessageComponentsDataInfoSerialized);
			
			this.secureReceiptMessageComponentsDataInfoSerializedDigitalSigned = 
					secureReceiptMessageComponentsDataInfoSignature.sign();
			
			
			this.setIsSecureReceiptMessageComponentsDataInfoSerializedDigitalSigned(true);			
			
		}
		
	}
	
	
	public boolean checkIfSecureReceiptMessageComponentsDataInfoDigitalSignedIsValid()
		   throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
			
		if(this.isSecureReceiptMessageComponentsDataInfoSerializedDigitalSigned) {

			Signature secureReceiptMessageComponentsDataInfoSignature = 
					  Signature.getInstance("SHA256withRSA");

			PublicKey userClientPublicKey = CommonUtils.readCertificate(userPeerID).getPublicKey(); //TODO Public Key or Certificate of the User contained in the Server 

			secureReceiptMessageComponentsDataInfoSignature.initVerify(userClientPublicKey);

			secureReceiptMessageComponentsDataInfoSignature
				.update(this.secureReceiptMessageComponentsDataInfoSerialized);

			this.isSecureReceiptMessageComponentsDataInfoDigitalSignedValid = 
					secureReceiptMessageComponentsDataInfoSignature
					.verify(this.secureReceiptMessageComponentsDataInfoSerializedDigitalSigned);


			if(this.isSecureReceiptMessageComponentsDataInfoDigitalSignedValid) {

//				System.out.println("Valid Signature!!!");

			}
			else {

//				System.out.println("Invalid Signature!!!");

			}

			this.setIsSecureReceiptMessageComponentsDataInfoDigitalSignedValid(true);
			this.setIsSecureReceiptMessageComponentsDataInfoSerializedDigitalSigned(false);


			return this.isSecureReceiptMessageComponentsDataInfoDigitalSignedValid;

		}

		return false;

	}
	
}
