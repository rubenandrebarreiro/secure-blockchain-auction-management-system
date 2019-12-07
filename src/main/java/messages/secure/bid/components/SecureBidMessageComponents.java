package main.java.messages.secure.bid.components;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SignatureException;

import javax.crypto.NoSuchPaddingException;

import main.java.common.utils.CommonUtils;
import main.java.messages.secure.bid.components.data.SecureBidMessageData;
import main.java.messages.secure.common.header.SecureCommonHeader;

public class SecureBidMessageComponents {

	private SecureCommonHeader secureCommonHeader;
	
	private SecureBidMessageData secureBidMessageData;
		
	
	private int sizeOfSecureBidMessageDataSerialized;
	
	
	private byte[] secureBidMessageComponentsSerialized;
	
	private byte[] secretSymmetricKeyForDataPersonalInBytes;
	
	private boolean isSecureBidMessageComponentsSerialized;
	
	
	private int sizeOfSecureBidMessageDataSignatureSerialized;
	private int sizeOfSecureBidMessageDataPersonalSerializedCipheredAndHashed;
	  
	private int sizeOfBidSerialized;
	private int sizeOfBidSerializedDigitalSigned;
	  
	private int sizeOfBidderUserClientIDSerialized;
	  
	private int sizeOfSecureBidMessageDataPersonalSerializedCiphered;
	private int sizeOfSecureBidMessageDataPersonalSerializedCipheredHashed;
	private int sizeOfSecureBidMessageDataPersonalSerialized;
	  
	private int sizeOfUserEmailSerialized;
	private int sizeOfUserHomeAddressSerialized;
	private int sizeOfUserBankAccountNIBSerialized;
	
	private String userPeerID;
	
	public SecureBidMessageComponents(SecureCommonHeader secureCommonHeader,
								      SecureBidMessageData secureBidMessageData,
								      byte[] secretSymmetricKeyForDataPersonalInBytes,
								      String userPeerID) {
		
		this.secureCommonHeader = secureCommonHeader;
		
		this.secureBidMessageData = secureBidMessageData;
		this.sizeOfSecureBidMessageDataSerialized = 0;
		
		this.secureBidMessageComponentsSerialized = null;
		this.isSecureBidMessageComponentsSerialized = false;
		
		this.secretSymmetricKeyForDataPersonalInBytes = secretSymmetricKeyForDataPersonalInBytes;
		
		this.userPeerID = userPeerID;
		
	}
	
	public SecureBidMessageComponents(byte[] secureBidMessageComponentsSerialized,
									  byte[] secretSymmetricKeyForDataPersonalInBytes,
									  int sizeOfSecureBidMessageDataSerialized,
									  int sizeOfSecureBidMessageDataSignatureSerialized,
									  int sizeOfSecureBidMessageDataPersonalSerializedCipheredAndHashed,
									  int sizeOfBidSerialized,
									  int sizeOfBidSerializedDigitalSigned,
									  int sizeOfBidderUserClientIDSerialized,
									  int sizeOfSecureBidMessageDataPersonalSerializedCiphered,
									  int sizeOfSecureBidMessageDataPersonalSerializedCipheredHashed,
									  int sizeOfSecureBidMessageDataPersonalSerialized,
									  int sizeOfUserEmailSerialized,
									  int sizeOfUserHomeAddressSerialized, 
									  int sizeOfUserBankAccountNIBSerialized,
									  String userPeerID) {

		this.secureBidMessageComponentsSerialized = secureBidMessageComponentsSerialized;
		this.secretSymmetricKeyForDataPersonalInBytes = secretSymmetricKeyForDataPersonalInBytes;
		this.isSecureBidMessageComponentsSerialized = true;
		
		this.secureCommonHeader = null;
		
		this.secureBidMessageData = null;
		
		
		this.sizeOfSecureBidMessageDataSerialized = sizeOfSecureBidMessageDataSerialized;
		  
		this.sizeOfSecureBidMessageDataSignatureSerialized = sizeOfSecureBidMessageDataSignatureSerialized;
		this.sizeOfSecureBidMessageDataPersonalSerializedCipheredAndHashed = 
				sizeOfSecureBidMessageDataPersonalSerializedCipheredAndHashed;
		  
		this.sizeOfBidSerialized = sizeOfBidSerialized;
		this.sizeOfBidSerializedDigitalSigned = sizeOfBidSerializedDigitalSigned;
		  
		this.sizeOfBidderUserClientIDSerialized = sizeOfBidderUserClientIDSerialized;
		  
		this.sizeOfSecureBidMessageDataPersonalSerializedCiphered =
		     sizeOfSecureBidMessageDataPersonalSerializedCiphered;
		this.sizeOfSecureBidMessageDataPersonalSerializedCipheredHashed = 
			 sizeOfSecureBidMessageDataPersonalSerializedCipheredHashed;
		this.sizeOfSecureBidMessageDataPersonalSerialized = sizeOfSecureBidMessageDataPersonalSerialized;
		
		this.sizeOfUserEmailSerialized = 
						sizeOfUserEmailSerialized;
		this.sizeOfUserHomeAddressSerialized = 
						sizeOfUserHomeAddressSerialized;
		this.sizeOfUserBankAccountNIBSerialized =
						sizeOfUserBankAccountNIBSerialized;
		
		this.userPeerID = userPeerID;
		
	}
	
	
	public SecureCommonHeader getSecureCommonHeader() {
		return this.secureCommonHeader;
	}
	
	public SecureBidMessageData getSecureBidMessageData() {
		return this.secureBidMessageData;
	}
	
	
	public int getSizeOfSecureBidMessageDataSerialized() {
		return this.sizeOfSecureBidMessageDataSerialized;
	}

	public byte[] getSecureBidMessageComponentsSerialized() {
		return this.secureBidMessageComponentsSerialized;
	}
	
	public boolean getIsSecureBidMessageComponentsSerialized() {
		return this.isSecureBidMessageComponentsSerialized;
	}

	public void setIsSecureBidMessageComponentsSerialized(boolean isSecureBidMessageComponentsSerialized) {
		this.isSecureBidMessageComponentsSerialized = isSecureBidMessageComponentsSerialized;
	}

	
	public void doSecureBidMessageComponentsSerialization()
		   throws InvalidKeyException, NoSuchAlgorithmException, NoSuchProviderException,
		          NoSuchPaddingException, InvalidAlgorithmParameterException, SignatureException {
		
		if(!this.isSecureBidMessageComponentsSerialized) {
			
			this.secureCommonHeader.doSecureCommonHeaderSerialization();
			
			byte[] secureCommonHeaderSerialized = 
				   this.secureCommonHeader.getSecureCommonHeaderSerialized();
			
			
			this.secureBidMessageData.doSecureBidMessageDataSerialization();
			
			byte[] secureBidMessageDataSerialized =
				   this.secureBidMessageData.getSecureBidMessageDataSerialized();
			
			
			
			int sizeOfSecureBidMessageComponentsSerialized = ( secureCommonHeaderSerialized.length + 
															   secureBidMessageDataSerialized.length );
			
			this.secureBidMessageComponentsSerialized = new byte[ sizeOfSecureBidMessageComponentsSerialized ];
			
			
			
			
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
			System.arraycopy(secureCommonHeaderSerialized, 0,
					         this.secureBidMessageComponentsSerialized, serializationOffset,
					         secureCommonHeaderSerialized.length);
			serializationOffset += secureCommonHeaderSerialized.length;

			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(secureBidMessageDataSerialized, 0,
							 this.secureBidMessageComponentsSerialized, serializationOffset,
							 secureBidMessageDataSerialized.length);
			
			
			this.setIsSecureBidMessageComponentsSerialized(true);
			
		}
		
	}
	
	public void undoSecureBidMessageComponentsSerialization()
		   throws InvalidKeyException, NoSuchAlgorithmException, NoSuchProviderException,
		          NoSuchPaddingException, InvalidAlgorithmParameterException, SignatureException {
		
		if(this.isSecureBidMessageComponentsSerialized) {
			
			byte[] secureCommonHeaderSerialized = new byte[CommonUtils.COMMON_HEADER_LENGTH];
			
			byte[] secureBidMessageDataSerialized = new byte[this.sizeOfSecureBidMessageDataSerialized];			
			
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
			System.arraycopy(this.secureBidMessageComponentsSerialized, serializationOffset,
							 secureCommonHeaderSerialized, 0,
					         secureCommonHeaderSerialized.length);
			serializationOffset += secureCommonHeaderSerialized.length;

			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(this.secureBidMessageComponentsSerialized, serializationOffset,
							 secureBidMessageDataSerialized, 0,
							 secureBidMessageDataSerialized.length);
			serializationOffset += secureBidMessageDataSerialized.length;
			
			this.secureCommonHeader = new SecureCommonHeader(secureCommonHeaderSerialized);
			this.secureCommonHeader.undoSecureCommonHeaderSerialization();
			
			
			this.secureBidMessageData = new SecureBidMessageData(secureBidMessageDataSerialized,
																 this.secretSymmetricKeyForDataPersonalInBytes,
																 this.sizeOfSecureBidMessageDataSignatureSerialized,
																 this.sizeOfBidSerialized,
																 this.sizeOfBidSerializedDigitalSigned,
																 this.sizeOfBidderUserClientIDSerialized,
																 this.sizeOfSecureBidMessageDataPersonalSerializedCipheredAndHashed,
																 this.sizeOfSecureBidMessageDataPersonalSerializedCiphered,
																 this.sizeOfSecureBidMessageDataPersonalSerializedCipheredHashed,
																 this.sizeOfSecureBidMessageDataPersonalSerialized,
																 this.sizeOfUserEmailSerialized,
																 this.sizeOfUserHomeAddressSerialized,
																 this.sizeOfUserBankAccountNIBSerialized,
																 this.userPeerID);
			
			this.secureBidMessageData.undoSecureBidMessageDataSerialization();
			
			
			this.setIsSecureBidMessageComponentsSerialized(false);
			
		}
		
	}
}
