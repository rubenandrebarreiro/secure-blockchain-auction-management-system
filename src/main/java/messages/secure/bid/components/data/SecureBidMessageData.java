package main.java.messages.secure.bid.components.data;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SignatureException;

import javax.crypto.NoSuchPaddingException;

import main.java.messages.secure.bid.components.data.confidential.SecureBidMessageDataConfidential;
import main.java.messages.secure.bid.components.data.signature.SecureBidMessageDataSignature;

public class SecureBidMessageData {

	private SecureBidMessageDataSignature secureBidMessageDataSignature;
	
	private byte[] secretSymmetricKeyForDataConfidentialInBytes;
	
	private int sizeOfSecureBidMessageDataSignatureSerialized;
	
	private int sizeOfBidSerialized;
	
	private int sizeOfBidSerializedDigitalSigned;
	
	private int sizeOfBidderUserClientIDSerialized;
	
	
	
	private SecureBidMessageDataConfidential secureBidMessageDataConfidential;
	
	private int sizeOfSecureBidMessageDataConfidentialSerializedCipheredAndHashed;
	
	private int sizeOfSecureBidMessageDataConfidentialSerializedCiphered;
	
	private int sizeOfSecureBidMessageDataConfidentialSerializedCipheredHashed;
	
	private int sizeOfSecureBidMessageDataConfidentialSerialized;
	
	private int sizeOfUserEmailSerialized;
	
	private int sizeOfUserHomeAddressSerialized; 
	
	private int sizeOfUserBankAccountNIBSerialized;
	
	
	private byte[] secureBidMessageDataSerialized;
	
	private boolean isSecureBidMessageDataSerialized;
	
	private String userPeerID;
	
	private byte[] initialisationVectorBytes;
	
	
	public SecureBidMessageData(SecureBidMessageDataSignature secureBidMessageDataSignature,
								SecureBidMessageDataConfidential secureBidMessageDataConfidential,
								String userPeerID) {
		
		this.secureBidMessageDataSignature = secureBidMessageDataSignature;
		this.sizeOfSecureBidMessageDataSignatureSerialized = 0;
		
		this.secureBidMessageDataConfidential = secureBidMessageDataConfidential;
		this.sizeOfSecureBidMessageDataConfidentialSerializedCipheredAndHashed = 0;
		this.sizeOfSecureBidMessageDataConfidentialSerializedCiphered = 0;
		this.sizeOfSecureBidMessageDataConfidentialSerializedCipheredHashed = 0;
		
		this.secureBidMessageDataSerialized = null;
		this.isSecureBidMessageDataSerialized = false;
		
		this.userPeerID = userPeerID;
		
	}
	
	
	public SecureBidMessageData(byte[] secureBidMessageDataSerialized,
							    byte[] secretSymmetricKeyForDataConfidentialInBytes,
								int sizeOfSecureBidMessageDataSignatureSerialized,
								int sizeOfBidSerialized,
								int sizeOfBidSerializedDigitalSigned,
							    int sizeOfBidderUserClientIDSerialized,
								int sizeOfSecureBidMessageDataConfidentialSerializedCipheredAndHashed,
								int sizeOfSecureBidMessageDataConfidentialSerializedCiphered,
								int sizeOfSecureBidMessageDataConfidentialSerializedCipheredHashed,
								int sizeOfSecureBidMessageDataConfidentialSerialized,
								int sizeOfUserEmailSerialized,
								int sizeOfUserHomeAddressSerialized, 
								int sizeOfUserBankAccountNIBSerialized,
								String userPeerID,
								byte[] initialisationVectorBytes) {

		this.secureBidMessageDataSerialized = secureBidMessageDataSerialized;
		this.secretSymmetricKeyForDataConfidentialInBytes = secretSymmetricKeyForDataConfidentialInBytes;
		this.isSecureBidMessageDataSerialized = true;
		
		this.secureBidMessageDataSignature = null;
		this.sizeOfSecureBidMessageDataSignatureSerialized = sizeOfSecureBidMessageDataSignatureSerialized;
		this.sizeOfBidSerialized = sizeOfBidSerialized;
		this.sizeOfBidSerializedDigitalSigned = sizeOfBidSerializedDigitalSigned;
		this.sizeOfBidderUserClientIDSerialized = sizeOfBidderUserClientIDSerialized;
		
		this.secureBidMessageDataConfidential = null;
		this.sizeOfSecureBidMessageDataConfidentialSerializedCipheredAndHashed = 
				sizeOfSecureBidMessageDataConfidentialSerializedCipheredAndHashed;
		this.sizeOfSecureBidMessageDataConfidentialSerializedCiphered = sizeOfSecureBidMessageDataConfidentialSerializedCiphered;
		this.sizeOfSecureBidMessageDataConfidentialSerializedCipheredHashed = sizeOfSecureBidMessageDataConfidentialSerializedCipheredHashed;
		this.sizeOfSecureBidMessageDataConfidentialSerialized = sizeOfSecureBidMessageDataConfidentialSerialized;
		this.sizeOfUserEmailSerialized = sizeOfUserEmailSerialized;
		this.sizeOfUserHomeAddressSerialized = sizeOfUserHomeAddressSerialized; 
		this.sizeOfUserBankAccountNIBSerialized = sizeOfUserBankAccountNIBSerialized;
		
		this.userPeerID = userPeerID;
		this.initialisationVectorBytes = initialisationVectorBytes;
		
	}
	
	
	
	public SecureBidMessageDataSignature getSecureBidMessageDataSignature() {
		return this.secureBidMessageDataSignature;
	}	

	public int getSizeOfSecureBidMessageDataSignatureSerialized() {
		return this.sizeOfSecureBidMessageDataSignatureSerialized;
	}
	
	public int getSizeOfBidSerialized() {
		return this.sizeOfBidSerialized;
	}
	
	public int getSizeOfBidSerializedDigitalSigned() {
		return this.sizeOfBidSerializedDigitalSigned;
	}
	
	public int getSizeOfBidderUserClientIDSerialized() {
		return this.sizeOfBidderUserClientIDSerialized;
	}
	
	
	
	
	
	public SecureBidMessageDataConfidential getSecureBidMessageDataConfidential() {
		return this.secureBidMessageDataConfidential;
	}

	public int getSizeOfSecureBidMessageDataConfidentialSerializedCipheredAndHashed() {
		return this.sizeOfSecureBidMessageDataConfidentialSerializedCipheredAndHashed;
	}
	
	public int getSizeOfSecureBidMessageDataConfidentialSerializedCiphered() {
		return this.sizeOfSecureBidMessageDataConfidentialSerializedCiphered;
	}
	
	public int getSizeOfSecureBidMessageDataConfidentialSerializedCipheredHashed() {
		return this.sizeOfSecureBidMessageDataConfidentialSerializedCipheredHashed;
	}

	public int getSizeOfSecureBidMessageDataConfidentialSerialized() {
		return this.sizeOfSecureBidMessageDataConfidentialSerialized;
	}
	
	
	public byte[] getSecureBidMessageDataSerialized() {
		return this.secureBidMessageDataSerialized;
	}

	public boolean getIsSecureBidMessageDataSerialized() {
		return this.isSecureBidMessageDataSerialized;
	}
	
	public void setIsSecureBidMessageDataSerialized(boolean isSecureBidMessageDataSerialized) {
		this.isSecureBidMessageDataSerialized = isSecureBidMessageDataSerialized;
	}
	
	
	
	
	public void doSecureBidMessageDataSerialization()
			throws InvalidKeyException, NoSuchAlgorithmException, NoSuchProviderException,
			NoSuchPaddingException, InvalidAlgorithmParameterException, SignatureException {
	
		if(!this.isSecureBidMessageDataSerialized) {
			
			this.secureBidMessageDataSignature.buildSecureBidMessageDataSignatureToSend();
			
			byte[] secureBidMessageDataSignatureSerialized = 
				   this.secureBidMessageDataSignature.getBidDigitalSigned();
			
			this.secureBidMessageDataConfidential.buildSecureBidMessageDataConfidentialToSend();
			
			byte[] secureBidMessageDataConfidentialSerializedCipheredAndHashed = 
				   this.secureBidMessageDataConfidential.getSecureBidMessageDataConfidentialSerializedCipheredAndHashed();
			
			
			int sizeOfSecureBidMessageDataSerialized = ( secureBidMessageDataSignatureSerialized.length +
														 secureBidMessageDataConfidentialSerializedCipheredAndHashed.length );
			
			this.secureBidMessageDataSerialized = new byte[ sizeOfSecureBidMessageDataSerialized ];
			
			
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
			System.arraycopy(secureBidMessageDataSignatureSerialized, 0,
					         this.secureBidMessageDataSerialized, serializationOffset,
					         secureBidMessageDataSignatureSerialized.length);
			serializationOffset += secureBidMessageDataSignatureSerialized.length;

			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(secureBidMessageDataConfidentialSerializedCipheredAndHashed, 0,
							 this.secureBidMessageDataSerialized, serializationOffset,
							 secureBidMessageDataConfidentialSerializedCipheredAndHashed.length);
			
			this.setIsSecureBidMessageDataSerialized(true);
			
		}
		
	}
	
	public void undoSecureBidMessageDataSerialization()
		   throws InvalidKeyException, NoSuchAlgorithmException, NoSuchProviderException,
		          NoSuchPaddingException, InvalidAlgorithmParameterException, SignatureException {
		
		if(this.isSecureBidMessageDataSerialized) {
			
			byte[] secureBidMessageDataSignatureSerialized = 
					new byte[this.sizeOfSecureBidMessageDataSignatureSerialized];
			
			byte[] secureBidMessageDataConfidentialSerializedCipheredAndHashed = 
					new byte[this.sizeOfSecureBidMessageDataConfidentialSerializedCipheredAndHashed];
			
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
			System.arraycopy(this.secureBidMessageDataSerialized, serializationOffset,
					         secureBidMessageDataSignatureSerialized, 0,
					         secureBidMessageDataSignatureSerialized.length);
			serializationOffset += secureBidMessageDataSignatureSerialized.length;

			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(this.secureBidMessageDataSerialized, serializationOffset,
							 secureBidMessageDataConfidentialSerializedCipheredAndHashed, 0,
							 secureBidMessageDataConfidentialSerializedCipheredAndHashed.length);
			
			this.secureBidMessageDataSignature = 
					new SecureBidMessageDataSignature(secureBidMessageDataSignatureSerialized,
													  this.sizeOfBidSerialized,
												      this.sizeOfBidderUserClientIDSerialized,
												      this.sizeOfBidSerializedDigitalSigned,
												      this.userPeerID);
			
			this.secureBidMessageDataSignature.buildSecureBidMessageDataSignatureReceived();
			
			
			this.secureBidMessageDataConfidential = 
					new SecureBidMessageDataConfidential(secureBidMessageDataConfidentialSerializedCipheredAndHashed,
													 this.secretSymmetricKeyForDataConfidentialInBytes,
													 this.initialisationVectorBytes,
									 				 this.sizeOfSecureBidMessageDataConfidentialSerializedCiphered,
													 this.sizeOfSecureBidMessageDataConfidentialSerializedCipheredHashed,
													 this.sizeOfSecureBidMessageDataConfidentialSerialized,
													 this.sizeOfUserEmailSerialized,
													 this.sizeOfUserHomeAddressSerialized,
													 this.sizeOfUserBankAccountNIBSerialized);
			
			this.secureBidMessageDataConfidential.buildSecureBidMessageDataConfidentialReceived();
			
			
			this.setIsSecureBidMessageDataSerialized(false);
			
		}
		
	}
	
}