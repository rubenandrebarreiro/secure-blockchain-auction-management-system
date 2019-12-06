package main.java.messages.secure.bid.components;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import javax.crypto.NoSuchPaddingException;

import main.java.common.utils.CommonUtils;
import main.java.messages.secure.bid.components.data.SecureBidMessageData;
import main.java.messages.secure.bid.components.key.exchange.SecureBidMessageKeyExchange;
import main.java.messages.secure.common.header.SecureCommonHeader;

public class SecureBidMessageComponents {

	private SecureCommonHeader secureCommonHeader;
	
	private SecureBidMessageData secureBidMessageData;
	
	private SecureBidMessageKeyExchange secureBidMessageKeyExchange;
	
	
	private int sizeOfSecureBidMessageDataSerialized;
	
	
	private byte[] secureBidMessageComponentsSerialized;
	
	private boolean isSecureBidMessageComponentsSerialized;
	
	
	private int sizeOfSecureBidMessageDataSignatureSerialized;
	
	private int sizeOfBidSerialized;
	
	private int sizeOfBidSerializedHashedCiphered;
	
	private int sizeOfBidderUserClientIDSerialized;
	
	
	private int sizeOfSecureBidMessageDataPersonalSerializedAndHashedCiphered;
	
	private int sizeOfSecureBidMessageDataPersonalSerialized;
	
	private int sizeOfSecureBidMessageDataPersonalSerializedHashed;
	
	private int sizeOfUserEmailSerialized;
	
	private int sizeOfUserHomeAddressSerialized;
	
	private int sizeOfUserBankAccountNIBSerialized;
	
	
	public SecureBidMessageComponents(SecureCommonHeader secureCommonHeader,
								      SecureBidMessageData secureBidMessageData,
								      SecureBidMessageKeyExchange secureBidMessageKeyExchange) {
		
		this.secureCommonHeader = secureCommonHeader;
		
		this.secureBidMessageData = secureBidMessageData;
		this.sizeOfSecureBidMessageDataSerialized = 0;
		
		this.secureBidMessageKeyExchange = secureBidMessageKeyExchange;
		
		this.secureBidMessageComponentsSerialized = null;
		this.isSecureBidMessageComponentsSerialized = false;
		
	}
	
	public SecureBidMessageComponents(byte[] secureBidMessageComponentsSerialized,
									  int sizeOfSecureBidMessageDataSerialized,
									  int sizeOfSecureBidMessageDataSignatureSerialized,
									  int sizeOfBidSerialized,
									  int sizeOfBidSerializedHashedCiphered,
									  int sizeOfBidderUserClientIDSerialized,
									  int sizeOfSecureBidMessageDataPersonalSerializedAndHashedCiphered,
									  int sizeOfSecureBidMessageDataPersonalSerialized,
									  int sizeOfSecureBidMessageDataPersonalSerializedHashed,
									  int sizeOfUserEmailSerialized, int sizeOfUserHomeAddressSerialized, 
									  int sizeOfUserBankAccountNIBSerialized) {

		this.secureBidMessageComponentsSerialized = secureBidMessageComponentsSerialized;
		this.isSecureBidMessageComponentsSerialized = true;
		
		this.secureCommonHeader = null;
		
		this.secureBidMessageData = null;

		this.secureBidMessageKeyExchange = null;
		
		
		this.sizeOfSecureBidMessageDataSerialized = sizeOfSecureBidMessageDataSerialized;
		
		this.sizeOfSecureBidMessageDataSignatureSerialized = sizeOfSecureBidMessageDataSignatureSerialized;
		this.sizeOfBidSerialized = sizeOfBidSerialized;
		this.sizeOfBidSerializedHashedCiphered = sizeOfBidSerializedHashedCiphered;
		this.sizeOfBidderUserClientIDSerialized = sizeOfBidderUserClientIDSerialized;
		
		this.sizeOfSecureBidMessageDataPersonalSerializedAndHashedCiphered = 
				sizeOfSecureBidMessageDataPersonalSerializedAndHashedCiphered;
		this.sizeOfSecureBidMessageDataPersonalSerialized = sizeOfSecureBidMessageDataPersonalSerialized;
		this.sizeOfSecureBidMessageDataPersonalSerializedHashed = sizeOfSecureBidMessageDataPersonalSerializedHashed;
		this.sizeOfUserEmailSerialized = sizeOfUserEmailSerialized;
		this.sizeOfUserHomeAddressSerialized = sizeOfUserEmailSerialized; 
		this.sizeOfUserBankAccountNIBSerialized = sizeOfUserBankAccountNIBSerialized;
		
	}
	
	
	public SecureCommonHeader getSecureCommonHeader() {
		return this.secureCommonHeader;
	}
	
	public SecureBidMessageData getSecureBidMessageData() {
		return this.secureBidMessageData;
	}
	
	public SecureBidMessageKeyExchange getSecureBidMessageKeyExchange() {
		return this.secureBidMessageKeyExchange;
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
		          NoSuchPaddingException, InvalidAlgorithmParameterException {
		
		if(!this.isSecureBidMessageComponentsSerialized) {
			
			this.secureCommonHeader.doSecureCommonHeaderSerialization();
			
			byte[] secureCommonHeaderSerialized = 
				   this.secureCommonHeader.getSecureCommonHeaderSerialized();
			
			
			this.secureBidMessageData.doSecureBidMessageDataSerialization();
			
			byte[] secureBidMessageDataSerialized =
				   this.secureBidMessageData.getSecureBidMessageDataSerialized();
			
			
			this.secureBidMessageKeyExchange.doSecureBidMessageKeyExchangeSerialization();
			
			byte[] secureBidMessageKeyExchangeSerialized =
				   this.secureBidMessageKeyExchange.getSecureBidMessageKeyExchangeSerialized();
			
			
			int sizeOfSecureBidMessageComponentsSerialized = ( secureCommonHeaderSerialized.length + 
															   secureBidMessageDataSerialized.length +
															   secureBidMessageKeyExchangeSerialized.length );
			
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
			
			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(secureBidMessageKeyExchangeSerialized, 0,
							 this.secureBidMessageComponentsSerialized, serializationOffset,
							 secureBidMessageKeyExchangeSerialized.length);
			
			
			this.setIsSecureBidMessageComponentsSerialized(true);
			
		}
		
	}
	
	public void undoSecureBidMessageComponentsSerialization()
		   throws InvalidKeyException, NoSuchAlgorithmException, NoSuchProviderException,
		          NoSuchPaddingException, InvalidAlgorithmParameterException {
		
		if(this.isSecureBidMessageComponentsSerialized) {
			
			byte[] secureCommonHeaderSerialized = new byte[CommonUtils.COMMON_HEADER_LENGTH];
			
			byte[] secureBidMessageDataSerialized = new byte[this.sizeOfSecureBidMessageDataSerialized];
			
			byte[] secureBidMessageKeyExchangeSerialized = new byte[ 3 * CommonUtils.LENGTH_256_BITS_IN_BYTES ];
			
			
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
			System.arraycopy(this.secureBidMessageComponentsSerialized, 0,
							 secureCommonHeaderSerialized, serializationOffset,
					         secureCommonHeaderSerialized.length);
			serializationOffset += secureCommonHeaderSerialized.length;

			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(this.secureBidMessageComponentsSerialized, 0,
							 secureBidMessageDataSerialized, serializationOffset,
							 secureBidMessageDataSerialized.length);
			serializationOffset += secureBidMessageDataSerialized.length;

			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(this.secureBidMessageComponentsSerialized, 0,
							 secureBidMessageKeyExchangeSerialized, serializationOffset,
							 secureBidMessageKeyExchangeSerialized.length);
			serializationOffset += secureBidMessageKeyExchangeSerialized.length;
			
			
			
			this.secureCommonHeader = new SecureCommonHeader(secureCommonHeaderSerialized);
			this.secureCommonHeader.undoSecureCommonHeaderSerialization();
			
			
			this.secureBidMessageData = new SecureBidMessageData(secureBidMessageDataSerialized,
																 this.sizeOfSecureBidMessageDataSignatureSerialized,
																 this.sizeOfBidSerialized,
																 this.sizeOfBidSerializedHashedCiphered,
																 this.sizeOfBidderUserClientIDSerialized,
																 this.sizeOfSecureBidMessageDataPersonalSerializedAndHashedCiphered,
																 this.sizeOfSecureBidMessageDataPersonalSerialized,
																 this.sizeOfSecureBidMessageDataPersonalSerializedHashed,
																 this.sizeOfUserEmailSerialized,
																 this.sizeOfUserHomeAddressSerialized,
																 this.sizeOfUserBankAccountNIBSerialized);
			
			this.secureBidMessageData.undoSecureBidMessageDataSerialization();
			
			
			this.secureBidMessageKeyExchange = new SecureBidMessageKeyExchange(secureBidMessageKeyExchangeSerialized);
			this.secureBidMessageKeyExchange.undoSecureBidMessageKeyExchangeSerialization();
			
			this.setIsSecureBidMessageComponentsSerialized(false);
			
		}
		
	}
}
