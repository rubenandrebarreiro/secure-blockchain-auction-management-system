package main.java.messages.secure.bid;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import javax.crypto.NoSuchPaddingException;

import main.java.common.utils.CommonUtils;
import main.java.messages.secure.bid.components.SecureBidMessageComponents;
import main.java.messages.secure.bid.dos.mitigation.SecureBidMessageDoSMitigation;
import main.java.messages.secure.bid.metaheader.SecureBidMessageMetaHeader;

public class SecureBidMessage {
	
	private SecureBidMessageMetaHeader secureBidMessageMetaHeader;
	
	private SecureBidMessageComponents secureBidMessageComponents;
	
	private SecureBidMessageDoSMitigation secureBidMessageDoSMitigation;
	
	private byte[] secureBidMessageSerialized;
	
	private boolean isSecureBidMessageSerialized;
	
	
	public SecureBidMessage(SecureBidMessageMetaHeader secureBidMessageMetaHeader,
							SecureBidMessageComponents secureBidMessageComponents,
							SecureBidMessageDoSMitigation secureBidMessageDoSMitigation) {
		
		this.secureBidMessageMetaHeader = secureBidMessageMetaHeader;
		this.secureBidMessageComponents = secureBidMessageComponents;
		this.secureBidMessageDoSMitigation = secureBidMessageDoSMitigation;
		
		this.secureBidMessageSerialized = null;
		this.isSecureBidMessageSerialized = false;
		
	}
	
	public SecureBidMessage(byte[] secureBidMessageSerialized) {
		
		this.secureBidMessageSerialized = secureBidMessageSerialized;
		this.isSecureBidMessageSerialized = true;
		
		this.secureBidMessageMetaHeader = null;
		this.secureBidMessageComponents = null;
		this.secureBidMessageDoSMitigation = null;
		
	}
	
	
	public SecureBidMessageMetaHeader getSecureBidMessageMetaHeader() {
		return this.secureBidMessageMetaHeader;
	}
	
	public SecureBidMessageComponents getSecureBidMessageComponents() {
		return this.secureBidMessageComponents;
	}
	
	public SecureBidMessageDoSMitigation getSecureBidMessageDoSMitigation() {
		return this.secureBidMessageDoSMitigation;
	}
	
	public byte[] getSecureBidMessageSerialized() {
		return this.secureBidMessageSerialized;
	}
	
	public boolean getIsSecureBidMessageSerialized() {
		return this.isSecureBidMessageSerialized;
	}
	
	public void setIsSecureBidMessageSerialized(boolean isSecureBidMessageSerialized) {
		this.isSecureBidMessageSerialized = isSecureBidMessageSerialized;
	}
	
	
	public void doSecureBidMessageSerialized()
		   throws InvalidKeyException, NoSuchAlgorithmException, NoSuchProviderException,
		          NoSuchPaddingException, InvalidAlgorithmParameterException {
		
		if(!this.isSecureBidMessageSerialized) {
			
			this.secureBidMessageMetaHeader.doSecureBidMessageMetaHeaderSerialization();
			byte[] secureBidMessageMetaHeaderSerialized = 
					this.secureBidMessageMetaHeader.getSecureBidMessageMetaHeaderSerialized();
			
			this.secureBidMessageComponents.doSecureBidMessageComponentsSerialization();
			byte[] secureBidMessageComponentsSerialized = 
					this.secureBidMessageComponents.getSecureBidMessageComponentsSerialized();
			
			this.secureBidMessageDoSMitigation.doHashOfSecureBidMessageDoSMitigation();
			byte[] secureBidMessageDoSMitigationSerialized = 
					this.secureBidMessageDoSMitigation.getSecureBidMessageComponentsHashedForDoSMitigation();
			
			int sizeOfSecureBidMessageSerialized = (secureBidMessageMetaHeaderSerialized.length +
													secureBidMessageComponentsSerialized.length +
													secureBidMessageDoSMitigationSerialized.length);

			this.secureBidMessageSerialized = new byte[sizeOfSecureBidMessageSerialized];
			
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
			System.arraycopy(secureBidMessageMetaHeaderSerialized, 0, this.secureBidMessageSerialized,
							 serializationOffset, secureBidMessageMetaHeaderSerialized.length);
			serializationOffset += secureBidMessageMetaHeaderSerialized.length;
		
			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(secureBidMessageComponentsSerialized, 0, this.secureBidMessageSerialized,
							 serializationOffset, secureBidMessageComponentsSerialized.length);
			serializationOffset += secureBidMessageComponentsSerialized.length;
		
			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(secureBidMessageDoSMitigationSerialized, 0, this.secureBidMessageSerialized,
							 serializationOffset, secureBidMessageDoSMitigationSerialized.length);
			
			
			this.setIsSecureBidMessageSerialized(true);
			
		}
		
	}
	
	
	public void undoSecureBidMessageSerialized() {
		
		if(this.isSecureBidMessageSerialized ) {
			
			
			int sizeOfSecureBidMessageMetaHeaderSerialized = ( ( 2 * CommonUtils.META_HEADER_OUTSIDE_SEPARATORS_LENGTH) +
															   ( 12 * CommonUtils.META_HEADER_INSIDE_SEPARATORS_LENGTH) +
															   ( 13 * CommonUtils.INTEGER_IN_BYTES_LENGTH ) );
			
			byte[] secureBidMessageMetaHeaderSerialized = new byte[ sizeOfSecureBidMessageMetaHeaderSerialized ];
			
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
			System.arraycopy(this.secureBidMessageSerialized, serializationOffset,
							 secureBidMessageMetaHeaderSerialized,
							 0, secureBidMessageMetaHeaderSerialized.length);
			serializationOffset += secureBidMessageMetaHeaderSerialized.length;
			
			this.secureBidMessageMetaHeader = new SecureBidMessageMetaHeader(secureBidMessageMetaHeaderSerialized);
			
			
			int sizeOfSecureBidMessageComponentsSerialized = 
					( CommonUtils.COMMON_HEADER_LENGTH +
					  this.secureBidMessageMetaHeader.getSizeOfSecureBidMessageDataSerialized() );
			
			byte[] secureBidMessageComponentsSerialized = new byte[ sizeOfSecureBidMessageComponentsSerialized ];
			
			
			int sizeOfSecureBidMessageDoSMitigationSerialized = 
					this.secureBidMessageMetaHeader.getSizeOfSecureBidMessageDoSMitigationSerialized();
			
			byte[] secureBidMessageDoSMitigationSerialized = new byte[ sizeOfSecureBidMessageDoSMitigationSerialized ];
			
			
			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(this.secureBidMessageSerialized, serializationOffset,
							 secureBidMessageComponentsSerialized,
							 0, secureBidMessageComponentsSerialized.length);
			serializationOffset += secureBidMessageComponentsSerialized.length;
			
			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(this.secureBidMessageSerialized, serializationOffset,
							 secureBidMessageDoSMitigationSerialized,
							 0, secureBidMessageDoSMitigationSerialized.length);
			
			
			int sizeOfSecureBidMessageDataSerialized = 
					this.secureBidMessageMetaHeader.getSizeOfSecureBidMessageDataSerialized();
			int sizeOfSecureBidMessageDataSignatureSerialized = 
					this.secureBidMessageMetaHeader.getSizeOfSecureBidMessageDataSignatureSerialized();
			int sizeOfBidSerialized = 
					this.secureBidMessageMetaHeader.getSizeOfBidSerialized();
			int sizeOfBidSerializedHashedCiphered = 
					this.secureBidMessageMetaHeader.getSizeOfBidSerializedHashedCiphered();
			int sizeOfBidderUserClientIDSerialized = 
					this.secureBidMessageMetaHeader.getSizeOfBidderUserClientIDSerialized();
			int sizeOfSecureBidMessageDataPersonalSerializedAndHashedCiphered = 
					this.secureBidMessageMetaHeader.getSizeOfSecureBidMessageDataPersonalSerializedAndHashedCiphered();
			int sizeOfSecureBidMessageDataPersonalSerialized = 
					this.secureBidMessageMetaHeader.getSizeOfSecureBidMessageDataPersonalSerialized();
			int sizeOfSecureBidMessageDataPersonalSerializedHashed = 
					this.secureBidMessageMetaHeader.getSizeOfSecureBidMessageDataPersonalSerializedHashed();
			int sizeOfUserEmailSerialized = 
					this.secureBidMessageMetaHeader.getSizeOfUserEmailSerialized();
			int sizeOfUserHomeAddressSerialized = 
					this.secureBidMessageMetaHeader.getSizeOfUserHomeAddressSerialized();
			int sizeOfUserBankAccountNIBSerialized = 
					this.secureBidMessageMetaHeader.getSizeOfUserBankAccountNIBSerialized();

			
			this.secureBidMessageComponents = 
					new SecureBidMessageComponents(secureBidMessageComponentsSerialized,
												   sizeOfSecureBidMessageDataSerialized,
												   sizeOfSecureBidMessageDataSignatureSerialized,
												   sizeOfBidSerialized,
												   sizeOfBidSerializedHashedCiphered,
												   sizeOfBidderUserClientIDSerialized,
												   sizeOfSecureBidMessageDataPersonalSerializedAndHashedCiphered,
												   sizeOfSecureBidMessageDataPersonalSerialized,
												   sizeOfSecureBidMessageDataPersonalSerializedHashed,
												   sizeOfUserEmailSerialized,
												   sizeOfUserHomeAddressSerialized,
												   sizeOfUserBankAccountNIBSerialized);
			
			this.secureBidMessageDoSMitigation = 
					new SecureBidMessageDoSMitigation(secureBidMessageComponentsSerialized,
													  secureBidMessageDoSMitigationSerialized);
			
			
			
			this.setIsSecureBidMessageSerialized(false);
			
			
		}
		
	}
}
