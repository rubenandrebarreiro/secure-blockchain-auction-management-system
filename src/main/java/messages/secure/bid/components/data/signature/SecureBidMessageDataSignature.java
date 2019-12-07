package main.java.messages.secure.bid.components.data.signature;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.cert.Certificate;

import javax.crypto.NoSuchPaddingException;

import main.java.resources.bid.Bid;

public class SecureBidMessageDataSignature {

	private Bid bid;

	
	private byte[] bidSerialized;

	private boolean isBidSerialized;

	private int sizeOfBidSerialized;
	
	private int sizeOfBidderUserClientIDSerialized;

	
	
	private byte[] bidSerializedDigitalSigned;
	
	private boolean isBidSerializedDigitalSigned;
	
	private int sizeOfBidSerializedDigitalSigned;
	
	private boolean isBidSerializedDigitalSignedVerified;
	
	private boolean isBidSerializedDigitalSignedValid;
	
	
	private byte[] bidDigitalSigned;

	private boolean isBidDigitalSigned;


	public SecureBidMessageDataSignature(Bid bid) {

		this.bid = bid;

		this.bidSerialized = null;
		this.isBidSerialized = false;
		this.sizeOfBidSerialized = 0;
		this.sizeOfBidderUserClientIDSerialized = 0;

		this.bidSerializedDigitalSigned = null;
		this.isBidSerializedDigitalSigned = false;
		this.sizeOfBidSerializedDigitalSigned = 0;
		this.isBidSerializedDigitalSignedVerified = false;
		this.isBidSerializedDigitalSignedValid = false;

		this.bidDigitalSigned = null;
		this.isBidDigitalSigned = false;

	}

	public SecureBidMessageDataSignature(byte[] bidDigitalSigned,
										 int sizeOfBidSerialized,
										 int sizeOfBidderUserClientIDSerialized,
										 int sizeOfBidSerializedDigitalSigned) {

		this.bidDigitalSigned = bidDigitalSigned;
		this.isBidDigitalSigned = true;

		this.bidSerialized = null;
		this.isBidSerialized = false;
		this.sizeOfBidSerialized = sizeOfBidSerialized;
		this.sizeOfBidderUserClientIDSerialized = sizeOfBidderUserClientIDSerialized;
		
		this.bidSerializedDigitalSigned = null;
		this.isBidSerializedDigitalSigned = false;
		this.sizeOfBidSerializedDigitalSigned = sizeOfBidSerializedDigitalSigned;
		this.isBidSerializedDigitalSignedVerified = false;
		this.isBidSerializedDigitalSignedValid = false;

		this.bid = null;

	}

	
	public Bid getBid() {
		return this.bid;
	}
	
	public byte[] getBidSerialized() {
		return this.bidSerialized;
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
	
	public int getSizeOfBidderUserClientIDSerialized() {
		return this.sizeOfBidderUserClientIDSerialized;
	}
	
	public byte[] getBidSerializedDigitalSigned() {
		return this.bidSerializedDigitalSigned;
	}
	
	public boolean getIsBidSerializedDigitalSigned() {
		return this.isBidSerializedDigitalSigned;
	}
	
	public void setIsBidSerializedDigitalSigned(boolean isBidSerializedDigitalSigned) {
		this.isBidSerializedDigitalSigned = isBidSerializedDigitalSigned;
	}
	
	public int getSizeOfBidSerializedDigitalSigned() {
		return this.sizeOfBidSerializedDigitalSigned;
	}
	
	public boolean getIsBidSerializedDigitalSignedVerified() {
		return this.isBidSerializedDigitalSignedVerified;
	}
	
	public void setIsBidSerializedDigitalSignedVerified
	      (boolean isBidSerializedDigitalSignedVerified) {
		
		this.isBidSerializedDigitalSignedVerified = isBidSerializedDigitalSignedVerified;
	
	}

	public boolean getIsBidSerializedDigitalSignedValid() {
		return this.isBidSerializedDigitalSignedValid;
	}
	
	public void setIsBidSerializedDigitalSignedValid
	      (boolean isBidSerializedDigitalSignedValid) {
		
		this.isBidSerializedDigitalSignedValid = isBidSerializedDigitalSignedValid;
	
	}	
	
	public byte[] getBidDigitalSigned() {
		return this.bidDigitalSigned;
	}

	public boolean getIsBidDigitalSigned() {
		return this.isBidDigitalSigned;
	}

	public void setIsBidDigitalSigned(boolean isBidDigitalSigned) {
		this.isBidDigitalSigned = isBidDigitalSigned;
	}
	
	
	public void buildSecureBidMessageDataSignatureToSend()
		   throws NoSuchAlgorithmException, InvalidKeyException, NoSuchProviderException,
		          NoSuchPaddingException, InvalidAlgorithmParameterException {

		boolean isPossibleToBuildSecureBidMessageSignatureToSend = 
				( !this.getIsBidSerialized() && !this.getIsBidSerializedDigitalSigned() && 
				  !this.getIsBidDigitalSigned() );	

		if(isPossibleToBuildSecureBidMessageSignatureToSend) {

			this.doSerializationOfBid();

						
		}

	}
	
	public void buildSecureBidMessageDataSignatureReceived() throws NoSuchAlgorithmException {
		
		boolean isPossibleToBuildSecureBidMessageSignatureReceived = 
				( this.getIsBidSerialized() && this.getIsBidSerializedDigitalSigned() && 
				  this.getIsBidDigitalSigned() );
		
		if(isPossibleToBuildSecureBidMessageSignatureReceived) {
			
			
			
		}
	}

	public void doSerializationOfBid() {
		
		boolean isPossibleToDoSerializationOfBid = 
				( !this.getIsBidSerialized() && !this.getIsBidSerializedDigitalSigned() && 
				  !this.getIsBidDigitalSigned() );
		
		if(isPossibleToDoSerializationOfBid) {
			
			this.bid.doSerialization();
			
			this.bidSerialized = this.bid.getBidSerializedBytes();
			
			this.setIsBidSerialized(true);
			
		}
		
	}

	public void undoSerializationOfBid() {
		
		boolean isPossibleToUndoSerializationOfBid = 
				(  this.getIsBidSerialized() && !this.getIsBidSerializedDigitalSigned() && 
				  !this.getIsBidDigitalSigned() );	
		
		if(isPossibleToUndoSerializationOfBid) {
			
			boolean isBidSerializedDigitalSignedVerifiedAndValid = 
				( this.getIsBidSerializedDigitalSignedVerified() &&
				  this.getIsBidSerializedDigitalSignedValid() );
			
			if(isBidSerializedDigitalSignedVerifiedAndValid) {
				
				this.bid = new Bid(this.bidSerialized, this.sizeOfBidderUserClientIDSerialized);
				
				this.bid.undoSerialization();
				
				this.setIsBidSerialized(false);
				
			}
			
		}
		
	}

	public void signSecureBidMessageBidSerialized()
		   throws SignatureException, InvalidKeyException, NoSuchAlgorithmException {

		boolean isPossibleToSignBidSerialized = 
				(  this.getIsBidSerialized() && !this.getIsBidSerializedDigitalSigned() && 
				  !this.getIsBidDigitalSigned() );	
		
		if(isPossibleToSignBidSerialized) {

			Signature secureBidMessageDataSignatureBidSerialized = 
					  Signature.getInstance("SHA256withDSA");
			
			PrivateKey userClientPrivateKey = null; //TODO
			
			secureBidMessageDataSignatureBidSerialized.initSign(userClientPrivateKey);
			
			secureBidMessageDataSignatureBidSerialized.update(this.bidSerialized);
			
			this.bidSerializedDigitalSigned = secureBidMessageDataSignatureBidSerialized.sign();
			
			
			this.setIsBidSerializedDigitalSigned(true);			
		}
		
	}

	public boolean checkIfSecureBidMessageDataSignatureBidSerializedSignedIsValid()
			   throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
				
		boolean isPossibleToVerifySecureBidMessageDataSignatureBidSerializedDigitalSigned = 
				(  this.getIsBidSerialized() && this.getIsBidSerializedDigitalSigned() && 
						  !this.getIsBidDigitalSigned() );


		if(isPossibleToVerifySecureBidMessageDataSignatureBidSerializedDigitalSigned) {

			Signature secureBidMessageDataSignatureBidSerializedSignature = 
					Signature.getInstance("SHA256withDSA");

			Certificate certificate = null;
			secureBidMessageDataSignatureBidSerializedSignature.initVerify(certificate);
			
			PublicKey userClientPublicKey = null; //TODO

			secureBidMessageDataSignatureBidSerializedSignature.initVerify(userClientPublicKey);

			secureBidMessageDataSignatureBidSerializedSignature.update(this.bidSerialized);

			this.isBidSerializedDigitalSignedValid = 
					secureBidMessageDataSignatureBidSerializedSignature
					.verify(this.bidSerializedDigitalSigned);


			if(this.isBidSerializedDigitalSignedValid) {

				System.out.println("Valid Signature!!!");

			}
			else {

				System.out.println("Invalid Signature!!!");

			}

			this.setIsBidSerializedDigitalSignedVerified(true);
			this.setIsBidSerializedDigitalSigned(false);


			return this.isBidSerializedDigitalSignedValid;

		}

		return false;

	}
	
	public void doSecureBidMessageDataSignatureBidSerializedAndSigned() {
		
		boolean isPossibleToDoSecureBidMessageDataSignatureBidSerializedAndSigned = 
				(  this.getIsBidSerialized() && this.getIsBidSerializedDigitalSigned() && 
				  !this.getIsBidDigitalSigned() );
		
		
		if(isPossibleToDoSecureBidMessageDataSignatureBidSerializedAndSigned) {
			
			int sizeOfSecureBidMessageDataSignatureBidSerializedAndSigned =
					( this.bidSerialized.length +
					  this.bidSerializedDigitalSigned.length );
			
			this.bidDigitalSigned = new byte[ sizeOfSecureBidMessageDataSignatureBidSerializedAndSigned ];

			
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
			System.arraycopy(this.bidSerialized, 0, this.bidDigitalSigned,
							 serializationOffset, this.bidSerialized.length);
			serializationOffset += this.bidSerialized.length;
			
			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(this.bidSerializedDigitalSigned, 0, this.bidDigitalSigned,
					         serializationOffset, this.bidSerializedDigitalSigned.length);
			
			
			this.setIsBidDigitalSigned(true);
			
		}
		
	}
	
	public void undoSecureBidMessageKeyExchangeAgreementSerializedCipheredAndSigned() {
		
		boolean isPossibleToUndoSecureBidMessageDataSignatureBidSerializedAndSigned = 
				(  this.getIsBidSerialized() && this.getIsBidSerializedDigitalSigned() && 
				   this.getIsBidDigitalSigned() );
		
		
		if(isPossibleToUndoSecureBidMessageDataSignatureBidSerializedAndSigned) {
				
			this.bidSerialized = new byte[ this.sizeOfBidSerialized ];

			this.bidSerializedDigitalSigned = new byte[ this.sizeOfBidSerializedDigitalSigned ];
			
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
			System.arraycopy(this.bidDigitalSigned, serializationOffset,
							 this.bidSerialized, 0, this.bidSerialized.length);
			serializationOffset += this.bidSerialized.length;
			
			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(this.bidDigitalSigned, serializationOffset,
					         this.bidSerializedDigitalSigned, 0, this.bidSerializedDigitalSigned.length);
			
			
			this.setIsBidDigitalSigned(false);
			
		}
	}
	
}