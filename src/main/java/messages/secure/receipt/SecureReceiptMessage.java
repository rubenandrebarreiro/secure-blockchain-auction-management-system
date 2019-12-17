package main.java.messages.secure.receipt;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SignatureException;

import javax.crypto.NoSuchPaddingException;

import main.java.common.utils.CommonUtils;
import main.java.messages.secure.common.key.exchange.SecureCommonKeyExchange;
import main.java.messages.secure.receipt.components.SecureReceiptMessageComponents;
import main.java.messages.secure.receipt.dos.mitigation.SecureReceiptMessageDoSMitigation;
import main.java.messages.secure.receipt.metaheader.SecureReceiptMessageMetaHeader;

public class SecureReceiptMessage {
	
	private SecureReceiptMessageMetaHeader secureReceiptMessageMetaHeader;
	
	private String userPeerID;
	
	private byte[] initialisationVectorInBytes;
	
	private SecureCommonKeyExchange secureReceiptMessageKeyExchange;
	
	private SecureReceiptMessageComponents secureReceiptMessageComponents;
	
	private SecureReceiptMessageDoSMitigation secureReceiptMessageDoSMitigation;
	
	
	
	private byte[] secureReceiptMessageSerialized;
	
	private boolean isSecureReceiptMessageSerialized;
	
	
	public SecureReceiptMessage(SecureReceiptMessageMetaHeader secureReceiptMessageMetaHeader,
								String userPeerID,
								byte[] initialisationVectorInBytes,
								SecureCommonKeyExchange secureReceiptMessageKeyExchange,
								SecureReceiptMessageComponents secureReceiptMessageComponents,
								SecureReceiptMessageDoSMitigation secureReceiptMessageDoSMitigation) {
		
		this.secureReceiptMessageMetaHeader = secureReceiptMessageMetaHeader;
		this.userPeerID = userPeerID;
		this.initialisationVectorInBytes = initialisationVectorInBytes;
		this.secureReceiptMessageKeyExchange = secureReceiptMessageKeyExchange;
		this.secureReceiptMessageComponents = secureReceiptMessageComponents;
		this.secureReceiptMessageDoSMitigation = secureReceiptMessageDoSMitigation;
		
	}
	
	
	public SecureReceiptMessage(byte[] secureReceiptMessageSerialized) {
		
		this.secureReceiptMessageSerialized = secureReceiptMessageSerialized;
		this.isSecureReceiptMessageSerialized = true;
		
		this.secureReceiptMessageMetaHeader = null;
		this.userPeerID = null;
		this.initialisationVectorInBytes = null;
		this.secureReceiptMessageKeyExchange = null;
		this.secureReceiptMessageComponents = null;
		this.secureReceiptMessageDoSMitigation = null;
		
	}
	
	public SecureReceiptMessageMetaHeader getSecureReceiptMessageMetaHeader() {
		return this.secureReceiptMessageMetaHeader;
	}
	
	public String getUserPeerID() {
		return this.userPeerID;
	}
	
	public byte[] getInitialisationVectorInBytes() {
		return this.initialisationVectorInBytes;
	}
	
	public SecureCommonKeyExchange getSecureReceiptMessageKeyExchange() {
		return this.secureReceiptMessageKeyExchange;
	}
	
	public SecureReceiptMessageComponents getSecureReceiptMessageComponents() {
		return this.secureReceiptMessageComponents;
	}
	
	public SecureReceiptMessageDoSMitigation getSecureReceiptMessageDoSMitigation() {
		return this.secureReceiptMessageDoSMitigation;
	}
	
	public byte[] getSecureReceiptMessageSerialized() {
		return this.secureReceiptMessageSerialized;
	}
	
	public boolean getIsSecureReceiptMessageSerialized() {
		return this.isSecureReceiptMessageSerialized;
	}
	
	public void setIsSecureReceiptMessageSerialized(boolean isSecureReceiptMessageSerialized) {
		this.isSecureReceiptMessageSerialized = isSecureReceiptMessageSerialized;
	}
	
	
	
	
	public void doSecureReceiptMessageSerialized()
		   throws InvalidKeyException, NoSuchAlgorithmException, SignatureException,
		   		  NoSuchProviderException, NoSuchPaddingException, InvalidAlgorithmParameterException {
			
		if(!this.isSecureReceiptMessageSerialized) {
			
			this.secureReceiptMessageMetaHeader.doSecureReceiptMessageMetaHeaderSerialization();
			byte[] secureReceiptMessageMetaHeaderSerialized = 
					this.secureReceiptMessageMetaHeader.getSecureReceiptMessageMetaHeaderSerialized();
			
			byte[] userPeerIDSerialized = CommonUtils.fromStringToByteArray(userPeerID);
			
			this.secureReceiptMessageKeyExchange.buildSecureCommonKeyExchangeToSend();
			byte[] secureReceiptMessageKeyExchangeSerializedCipheredAndSigned = 
					this.secureReceiptMessageKeyExchange.getSecureCommonKeyExchangeSerializedCipheredAndSigned();

				
			this.secureReceiptMessageComponents.doSecureReceiptMessageComponentsSerialization();
			byte[] secureReceiptMessageComponentsSerializedCiphered = 
					this.secureReceiptMessageComponents.getSecureReceiptMessageComponentsSerializedCiphered();
					
			this.secureReceiptMessageDoSMitigation.doHashOfSecureReceiptMessageDoSMitigation();
			byte[] secureReceiptMessageDoSMitigationSerialized = 
					this.secureReceiptMessageDoSMitigation.getSecureReceiptMessageComponentsHashedForDoSMitigation();
			
			int sizeOfSecureBidMessageSerialized = (secureReceiptMessageMetaHeaderSerialized.length +
													userPeerIDSerialized.length +
													this.initialisationVectorInBytes.length +
												    secureReceiptMessageKeyExchangeSerializedCipheredAndSigned.length +
													secureReceiptMessageComponentsSerializedCiphered.length +
													secureReceiptMessageDoSMitigationSerialized.length);

			this.secureReceiptMessageSerialized = new byte[sizeOfSecureBidMessageSerialized];
			
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
			System.arraycopy(secureReceiptMessageMetaHeaderSerialized, 0, this.secureReceiptMessageSerialized,
							 serializationOffset, secureReceiptMessageMetaHeaderSerialized.length);
			serializationOffset += secureReceiptMessageMetaHeaderSerialized.length;
		
			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(userPeerIDSerialized, 0, this.secureReceiptMessageSerialized,
							 serializationOffset, userPeerIDSerialized.length);
			serializationOffset += userPeerIDSerialized.length;

			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(this.initialisationVectorInBytes, 0, this.secureReceiptMessageSerialized,
							 serializationOffset, this.initialisationVectorInBytes.length);
			serializationOffset += this.initialisationVectorInBytes.length;
			
			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(secureReceiptMessageKeyExchangeSerializedCipheredAndSigned, 0, this.secureReceiptMessageSerialized,
							 serializationOffset, secureReceiptMessageKeyExchangeSerializedCipheredAndSigned.length);
			serializationOffset += secureReceiptMessageKeyExchangeSerializedCipheredAndSigned.length;
			
			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(secureReceiptMessageComponentsSerializedCiphered, 0, this.secureReceiptMessageSerialized,
							 serializationOffset, secureReceiptMessageComponentsSerializedCiphered.length);
			serializationOffset += secureReceiptMessageComponentsSerializedCiphered.length;
		
			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(secureReceiptMessageDoSMitigationSerialized, 0, this.secureReceiptMessageSerialized,
							 serializationOffset, secureReceiptMessageDoSMitigationSerialized.length);
			
			
			this.setIsSecureReceiptMessageSerialized(true);
			
		}
		
	}
	
	public void undoSecureReceiptMessageSerialized() {
		
		if(this.isSecureReceiptMessageSerialized ) {
			
			
			int sizeOfSecureReceiptMessageMetaHeaderSerialized = ( ( 2 * CommonUtils.META_HEADER_OUTSIDE_SEPARATORS_LENGTH) +
															   ( 9 * CommonUtils.META_HEADER_INSIDE_SEPARATORS_LENGTH) +
															   ( 10 * CommonUtils.INTEGER_IN_BYTES_LENGTH ) );
			
			byte[] secureReceiptMessageMetaHeaderSerialized = new byte[ sizeOfSecureReceiptMessageMetaHeaderSerialized ];
			
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
			System.arraycopy(this.secureReceiptMessageSerialized, serializationOffset,
							 secureReceiptMessageMetaHeaderSerialized,
							 0, secureReceiptMessageMetaHeaderSerialized.length);
			serializationOffset += secureReceiptMessageMetaHeaderSerialized.length;
			
			this.secureReceiptMessageMetaHeader = new SecureReceiptMessageMetaHeader(secureReceiptMessageMetaHeaderSerialized);
			this.secureReceiptMessageMetaHeader.undoSecureReceiptMessageMetaHeaderSerialization();
			
			int sizeOfUserPeerIDSerialized = this.secureReceiptMessageMetaHeader.getSizeOfUserPeerIDSerialized();
			
			byte[] userPeerIDSerialized = new byte[ sizeOfUserPeerIDSerialized ];
			
			
			int sizeOfInitialisationVectorInBytes = this.secureReceiptMessageMetaHeader.getSizeOfInitialisationVector();
			
			byte[] initialisationVectorInBytes = new byte[ sizeOfInitialisationVectorInBytes ];
			
			
			
			int sizeOfSecureReceiptMessageKeyExchangeSerialized = 
					( this.secureReceiptMessageMetaHeader.getSizeOfSecureReceiptMessageKeyExchangeSerializedCiphered() + 
					  this.secureReceiptMessageMetaHeader.getSizeOfSecureReceiptMessageKeyExchangeSerializedCipheredSigned() );
			
			byte[] secureReceiptMessageKeyExchangeSerialized = new byte[ sizeOfSecureReceiptMessageKeyExchangeSerialized ];
			
			
			int sizeOfSecureReceiptMessageComponentsSerializedCiphered = 
					( this.secureReceiptMessageMetaHeader.getSizeOfSecureReceiptMessageComponentsSerializedCiphered() );
			
			byte[] secureReceiptMessageComponentsSerializedCiphered = new byte[ sizeOfSecureReceiptMessageComponentsSerializedCiphered ];
			
			
			int sizeOfSecureReceiptMessageDoSMitigationSerialized = 
					this.secureReceiptMessageMetaHeader.getSizeOfSecureReceiptMessageDoSMitigationSerialized();
			
			byte[] secureReceiptMessageDoSMitigationSerialized = new byte[ sizeOfSecureReceiptMessageDoSMitigationSerialized ];
			
			
			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(this.secureReceiptMessageSerialized, serializationOffset,
					userPeerIDSerialized, 0, userPeerIDSerialized.length);
			serializationOffset += userPeerIDSerialized.length;
			
			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(this.secureReceiptMessageSerialized, serializationOffset,
					initialisationVectorInBytes, 0, initialisationVectorInBytes.length);
			serializationOffset += initialisationVectorInBytes.length;
			
			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(this.secureReceiptMessageSerialized, serializationOffset,
					secureReceiptMessageKeyExchangeSerialized,
							 0, secureReceiptMessageKeyExchangeSerialized.length);
			serializationOffset += secureReceiptMessageKeyExchangeSerialized.length;
			
			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(this.secureReceiptMessageSerialized, serializationOffset,
							 secureReceiptMessageComponentsSerializedCiphered,
							 0, secureReceiptMessageComponentsSerializedCiphered.length);
			serializationOffset += secureReceiptMessageComponentsSerializedCiphered.length;
			
			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(this.secureReceiptMessageSerialized, serializationOffset,
							 secureReceiptMessageDoSMitigationSerialized,
							 0, secureReceiptMessageDoSMitigationSerialized.length);
			
			
			int sizeOfSecureReceiptMessageKeyExchangeSerializedCiphered = 
					this.secureReceiptMessageMetaHeader.getSizeOfSecureReceiptMessageKeyExchangeSerializedCiphered();
			int sizeOfSecureReceiptMessageKeyExchangeSerializedCipheredSigned = 
					this.secureReceiptMessageMetaHeader.getSizeOfSecureReceiptMessageKeyExchangeSerializedCipheredSigned();
			
			
			int sizeOfSecureReceiptMessageComponentsDataInfoSerialized = 
					this.secureReceiptMessageMetaHeader.getSizeOfSecureReceiptMessageComponentsDataInfoSerialized();
			int sizeOfSecureReceiptMessageComponentsDataSignatureSerialized = 
					this.secureReceiptMessageMetaHeader.getSizeOfSecureReceiptMessageComponentsDataSignatureSerialized();
			int sizeOfSecureReceiptMessageComponentsDataResponseSerialized = 
					this.secureReceiptMessageMetaHeader.getSizeOfSecureReceiptMessageComponentsDataResponseSerialized();
			
			
			int sizeOfBidderUserClientIDSerialized =
					this.secureReceiptMessageMetaHeader.getSizeOfBidderUserClientIDSerialized();
			
			
			this.userPeerID = CommonUtils.fromByteArrayToString(userPeerIDSerialized);
			
			this.secureReceiptMessageKeyExchange = 
					new SecureCommonKeyExchange(secureReceiptMessageKeyExchangeSerialized,
												sizeOfSecureReceiptMessageKeyExchangeSerializedCiphered,
												sizeOfSecureReceiptMessageKeyExchangeSerializedCipheredSigned,
												this.userPeerID);
		
			try {
				this.secureReceiptMessageKeyExchange.buildSecureCommonKeyExchangeReceived();
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
			
			this.secureReceiptMessageComponents = 
					new SecureReceiptMessageComponents(secureReceiptMessageComponentsSerializedCiphered,
													   this.secureReceiptMessageKeyExchange.getSecretSymmetricKeyInBytes(),
													   this.initialisationVectorInBytes,
													   sizeOfSecureReceiptMessageComponentsSerializedCiphered,
													   sizeOfSecureReceiptMessageComponentsDataInfoSerialized,
													   sizeOfSecureReceiptMessageComponentsDataSignatureSerialized,
													   sizeOfSecureReceiptMessageComponentsDataResponseSerialized,
													   sizeOfBidderUserClientIDSerialized,
													   this.userPeerID);
			
			
			this.secureReceiptMessageDoSMitigation = 
					new SecureReceiptMessageDoSMitigation(secureReceiptMessageComponentsSerializedCiphered,
														  secureReceiptMessageDoSMitigationSerialized,
														  this.secureReceiptMessageKeyExchange.getSecretHMACKeyForDoSMitigationInBytes());
			
			
			
			this.setIsSecureReceiptMessageSerialized(false);
			
			
		}
		
	}
	
}
