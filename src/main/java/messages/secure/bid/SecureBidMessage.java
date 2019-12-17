package main.java.messages.secure.bid;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SignatureException;

import javax.crypto.NoSuchPaddingException;

import main.java.common.utils.CommonUtils;
import main.java.messages.secure.bid.components.SecureBidMessageComponents;
import main.java.messages.secure.bid.dos.mitigation.SecureBidMessageDoSMitigation;
import main.java.messages.secure.bid.metaheader.SecureBidMessageMetaHeader;
import main.java.messages.secure.common.key.exchange.SecureCommonKeyExchange;

public class SecureBidMessage {
	
	private SecureBidMessageMetaHeader secureBidMessageMetaHeader;
	
	private String userPeerID;
	
	private byte[] initialisationVectorBytes;
	
	private SecureCommonKeyExchange secureBidMessageKeyExchange;
	
	private SecureBidMessageComponents secureBidMessageComponents;
	
	private SecureBidMessageDoSMitigation secureBidMessageDoSMitigation;
	
	private byte[] secureBidMessageSerialized;
	
	private boolean isSecureBidMessageSerialized;
	
	
	public SecureBidMessage(SecureBidMessageMetaHeader secureBidMessageMetaHeader,
							String userPeerID,
							byte[] initialisationVectorBytes,
							SecureCommonKeyExchange secureBidMessageKeyExchange,
							SecureBidMessageComponents secureBidMessageComponents,
							SecureBidMessageDoSMitigation secureBidMessageDoSMitigation) {
		
		this.secureBidMessageMetaHeader = secureBidMessageMetaHeader;
		this.userPeerID = userPeerID;
		this.initialisationVectorBytes = initialisationVectorBytes;
		this.secureBidMessageKeyExchange = secureBidMessageKeyExchange;
		this.secureBidMessageComponents = secureBidMessageComponents;
		this.secureBidMessageDoSMitigation = secureBidMessageDoSMitigation;
		
		this.secureBidMessageSerialized = null;
		this.isSecureBidMessageSerialized = false;
		
	}
	
	public SecureBidMessage(byte[] secureBidMessageSerialized) {
		
		this.secureBidMessageSerialized = secureBidMessageSerialized;
		this.isSecureBidMessageSerialized = true;
		
		this.secureBidMessageMetaHeader = null;
		this.userPeerID = null;
		this.initialisationVectorBytes = null;
		this.secureBidMessageKeyExchange = null;
		this.secureBidMessageComponents = null;
		this.secureBidMessageDoSMitigation = null;
		
	}
	
	
	public SecureBidMessageMetaHeader getSecureBidMessageMetaHeader() {
		return this.secureBidMessageMetaHeader;
	}
	
	public String getUserPeerID() {
		return this.userPeerID;
	}
	
	public byte[] getInitialisationVectorBytes() {
		return this.initialisationVectorBytes;
	}
	
	public SecureCommonKeyExchange getSecureBidMessageKeyExchange() {
		return this.secureBidMessageKeyExchange;
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
		   throws InvalidKeyException, NoSuchAlgorithmException, SignatureException, NoSuchProviderException,
		          NoSuchPaddingException, InvalidAlgorithmParameterException {
		
		if(!this.isSecureBidMessageSerialized) {
			
			this.secureBidMessageMetaHeader.doSecureBidMessageMetaHeaderSerialization();
			byte[] secureBidMessageMetaHeaderSerialized = 
					this.secureBidMessageMetaHeader.getSecureBidMessageMetaHeaderSerialized();
			
			byte[] userPeerIDSerialized = CommonUtils.fromStringToByteArray(userPeerID);
			
			this.secureBidMessageKeyExchange.buildSecureCommonKeyExchangeToSend();
			byte[] secureBidMessageKeyExchangeSerializedCipheredAndSigned = 
					this.secureBidMessageKeyExchange.getSecureCommonKeyExchangeSerializedCipheredAndSigned();
			
			this.secureBidMessageComponents.doSecureBidMessageComponentsSerialization();
			byte[] secureBidMessageComponentsSerialized = 
					this.secureBidMessageComponents.getSecureBidMessageComponentsSerialized();
			
			this.secureBidMessageDoSMitigation.doHashOfSecureBidMessageDoSMitigation();
			byte[] secureBidMessageDoSMitigationSerialized = 
					this.secureBidMessageDoSMitigation.getSecureBidMessageComponentsHashedForDoSMitigation();
			
			int sizeOfSecureBidMessageSerialized = (secureBidMessageMetaHeaderSerialized.length +
													userPeerIDSerialized.length +
													this.initialisationVectorBytes.length +
												    secureBidMessageKeyExchangeSerializedCipheredAndSigned.length +
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
			System.arraycopy(userPeerIDSerialized, 0, this.secureBidMessageSerialized,
							 serializationOffset, userPeerIDSerialized.length);
			serializationOffset += userPeerIDSerialized.length;

			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(this.initialisationVectorBytes, 0, this.secureBidMessageSerialized,
							 serializationOffset, this.initialisationVectorBytes.length);
			serializationOffset += this.initialisationVectorBytes.length;
			
			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(secureBidMessageKeyExchangeSerializedCipheredAndSigned, 0, this.secureBidMessageSerialized,
							 serializationOffset, secureBidMessageKeyExchangeSerializedCipheredAndSigned.length);
			serializationOffset += secureBidMessageKeyExchangeSerializedCipheredAndSigned.length;
			
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
															   ( 16 * CommonUtils.META_HEADER_INSIDE_SEPARATORS_LENGTH) +
															   ( 17 * CommonUtils.INTEGER_IN_BYTES_LENGTH ) );
			
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
			this.secureBidMessageMetaHeader.undoSecureBidMessageMetaHeaderSerialization();
			
			int sizeOfUserPeerIDSerialized = this.secureBidMessageMetaHeader.getSizeOfUserPeerIDSerialized();
			
			byte[] userPeerIDSerialized = new byte[ sizeOfUserPeerIDSerialized ];
			
			
			int sizeOfInitialisationVectorBytes = this.secureBidMessageMetaHeader.getSizeOfInitialisationVector();
			
			byte[] initialisationVectorInBytes = new byte[ sizeOfInitialisationVectorBytes ];
			
			
			int sizeOfSecureBidMessageKeyExchangeSerialized = 
					( this.secureBidMessageMetaHeader.getSizeOfSecureBidMessageKeyExchangeSerializedCiphered() + 
					  this.secureBidMessageMetaHeader.getSizeOfSecureBidMessageKeyExchangeSerializedCipheredSigned() );
			
			byte[] secureBidMessageKeyExchangeSerialized = new byte[ sizeOfSecureBidMessageKeyExchangeSerialized ];
			
			
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
					userPeerIDSerialized, 0, userPeerIDSerialized.length);
			serializationOffset += userPeerIDSerialized.length;
			
			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(this.secureBidMessageSerialized, serializationOffset,
					initialisationVectorInBytes, 0, initialisationVectorInBytes.length);
			serializationOffset += initialisationVectorInBytes.length;
			
			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(this.secureBidMessageSerialized, serializationOffset,
					secureBidMessageKeyExchangeSerialized,
							 0, secureBidMessageKeyExchangeSerialized.length);
			serializationOffset += secureBidMessageKeyExchangeSerialized.length;
			
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
			
			
			int sizeOfSecureBidMessageKeyExchangeSerializedCiphered = 
					this.secureBidMessageMetaHeader.getSizeOfSecureBidMessageKeyExchangeSerializedCiphered();
			int sizeOfSecureBidMessageKeyExchangeSerializedCipheredSigned = 
					this.secureBidMessageMetaHeader.getSizeOfSecureBidMessageKeyExchangeSerializedCipheredSigned();
			
			
			int sizeOfSecureBidMessageDataSerialized = 
					this.secureBidMessageMetaHeader.getSizeOfSecureBidMessageDataSerialized();
			  
			int sizeOfSecureBidMessageDataSignatureSerialized = 
					this.secureBidMessageMetaHeader.getSizeOfSecureBidMessageDataSignatureSerialized();
			int sizeOfSecureBidMessageDataConfidentialSerializedCipheredAndHashed = 
					this.secureBidMessageMetaHeader.getSizeOfSecureBidMessageDataConfidentialSerializedCipheredAndHashed();
			  
			int sizeOfBidSerialized = 
					this.secureBidMessageMetaHeader.getSizeOfBidSerialized();
			int sizeOfBidSerializedDigitalSigned = 
					this.secureBidMessageMetaHeader.getSizeOfBidSerializedDigitalSigned();
			  
			int sizeOfBidderUserClientIDSerialized = 
					this.secureBidMessageMetaHeader.getSizeOfBidderUserClientIDSerialized();
			  
			int sizeOfSecureBidMessageDataConfidentialSerializedCiphered = 
					this.secureBidMessageMetaHeader.getSizeOfSecureBidMessageDataConfidentialSerializedCiphered();
			int sizeOfSecureBidMessageDataConfidentialSerializedCipheredHashed = 
					this.secureBidMessageMetaHeader.getSizeOfSecureBidMessageDataConfidentialSerializedCipheredHashed();
			int sizeOfSecureBidMessageDataConfidentialSerialized = 
					this.secureBidMessageMetaHeader.getSizeOfSecureBidMessageDataConfidentialSerialized();
			  
			int sizeOfUserEmailSerialized = 
					this.secureBidMessageMetaHeader.getSizeOfUserEmailSerialized();
			int sizeOfUserHomeAddressSerialized = 
					this.secureBidMessageMetaHeader.getSizeOfUserHomeAddressSerialized();
			int sizeOfUserBankAccountNIBSerialized = 
					this.secureBidMessageMetaHeader.getSizeOfUserBankAccountNIBSerialized();
			
			this.userPeerID = CommonUtils.fromByteArrayToString(userPeerIDSerialized);
			
			this.secureBidMessageKeyExchange = 
					new SecureCommonKeyExchange(secureBidMessageKeyExchangeSerialized,
												sizeOfSecureBidMessageKeyExchangeSerializedCiphered,
												sizeOfSecureBidMessageKeyExchangeSerializedCipheredSigned,
												userPeerID);
		
			try {
				this.secureBidMessageKeyExchange.buildSecureCommonKeyExchangeReceived();
			} catch (InvalidKeyException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SignatureException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			this.secureBidMessageComponents = 
					new SecureBidMessageComponents(secureBidMessageComponentsSerialized,
												   this.secureBidMessageKeyExchange.getSecretSymmetricKeyInBytes(),
												   sizeOfSecureBidMessageDataSerialized,
												   sizeOfSecureBidMessageDataSignatureSerialized,
												   sizeOfSecureBidMessageDataConfidentialSerializedCipheredAndHashed,
												   sizeOfBidSerialized,
												   sizeOfBidSerializedDigitalSigned,
												   sizeOfBidderUserClientIDSerialized,
												   sizeOfSecureBidMessageDataConfidentialSerializedCiphered,
												   sizeOfSecureBidMessageDataConfidentialSerializedCipheredHashed,
												   sizeOfSecureBidMessageDataConfidentialSerialized,
												   sizeOfUserEmailSerialized,
												   sizeOfUserHomeAddressSerialized, 
												   sizeOfUserBankAccountNIBSerialized,
												   this.userPeerID,
												   this.initialisationVectorBytes);
						
			this.secureBidMessageDoSMitigation = 
					new SecureBidMessageDoSMitigation(secureBidMessageComponentsSerialized,
													  secureBidMessageDoSMitigationSerialized,
													  this.secureBidMessageKeyExchange.getSecretHMACKeyForDoSMitigationInBytes());
			
			
			
			this.setIsSecureBidMessageSerialized(false);
			
			
		}
		
	}
}
