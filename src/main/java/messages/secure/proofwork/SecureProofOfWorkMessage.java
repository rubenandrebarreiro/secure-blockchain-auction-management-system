package main.java.messages.secure.proofwork;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SignatureException;

import javax.crypto.NoSuchPaddingException;

import main.java.common.utils.CommonUtils;
import main.java.messages.secure.common.key.exchange.SecureCommonKeyExchange;
import main.java.messages.secure.proofwork.components.SecureProofOfWorkMessageComponents;
import main.java.messages.secure.proofwork.dos.mitigation.SecureProofOfWorkMessageDoSMitigation;
import main.java.messages.secure.proofwork.metaheader.SecureProofOfWorkMessageMetaHeader;

public class SecureProofOfWorkMessage {
	
	private byte[] initialisationVector;
	
	private SecureProofOfWorkMessageMetaHeader secureProofOfWorkMessageMetaHeader;
	
	private String userPeerID;
	
	private SecureCommonKeyExchange secureProofOfWorkMessageKeyExchange;
	
	private SecureProofOfWorkMessageComponents secureProofOfWorkMessageComponents;
	
	private SecureProofOfWorkMessageDoSMitigation secureProofOfWorkMessageDoSMitigation;
	
	private byte[] secureProofOfWorkMessageSerialized;
	
	private boolean isSecureProofOfWorkMessageSerialized;
	
	
	
	public SecureProofOfWorkMessage(byte[] initialisationVector,
									SecureProofOfWorkMessageMetaHeader secureProofOfWorkMessageMetaHeader,
									String userPeerID,
									SecureCommonKeyExchange secureProofOfWorkMessageKeyExchange,
									SecureProofOfWorkMessageComponents secureProofOfWorkMessageComponents,
									SecureProofOfWorkMessageDoSMitigation secureProofOfWorkMessageDoSMitigation) {

		this.initialisationVector = initialisationVector;
		this.secureProofOfWorkMessageMetaHeader = secureProofOfWorkMessageMetaHeader;
		this.userPeerID = userPeerID;
		this.secureProofOfWorkMessageKeyExchange = secureProofOfWorkMessageKeyExchange;
		this.secureProofOfWorkMessageComponents = secureProofOfWorkMessageComponents;
		this.secureProofOfWorkMessageDoSMitigation = secureProofOfWorkMessageDoSMitigation;
		
		this.secureProofOfWorkMessageSerialized = null;
		this.isSecureProofOfWorkMessageSerialized = false;
		
	}

	
	public SecureProofOfWorkMessage(byte[] secureProofOfWorkMessageSerialized) {
		
		this.secureProofOfWorkMessageSerialized = secureProofOfWorkMessageSerialized;
		this.isSecureProofOfWorkMessageSerialized = true;
		
		this.initialisationVector = null;
		this.secureProofOfWorkMessageMetaHeader = null;
		this.userPeerID = null;
		this.secureProofOfWorkMessageKeyExchange = null;
		this.secureProofOfWorkMessageComponents = null;
		this.secureProofOfWorkMessageDoSMitigation = null;
		
	}
	
	public byte[] getInitialisationVector() {
		return this.initialisationVector;
	}
	
	public SecureProofOfWorkMessageMetaHeader getSecureProofOfWorkMessageMetaHeader() {
		return this.secureProofOfWorkMessageMetaHeader;
	}
	
	public String getUserPeerID() {
		return this.userPeerID;
	}
	
	public SecureCommonKeyExchange getSecureProofOfWorkMessageKeyExchange() {
		return this.secureProofOfWorkMessageKeyExchange;
	}
	
	public SecureProofOfWorkMessageComponents getSecureProofOfWorkMessageComponents() {
		return this.secureProofOfWorkMessageComponents;
	}
	
	public SecureProofOfWorkMessageDoSMitigation getSecureProofOfWorkMessageDoSMitigation() {
		return this.secureProofOfWorkMessageDoSMitigation;
	}
	
	public byte[] getSecureProofOfWorkMessageSerialized() {
		return this.secureProofOfWorkMessageSerialized;
	}
	
	public boolean getIsSecureProofOfWorkMessageSerialized() {
		return this.isSecureProofOfWorkMessageSerialized;
	}
	
	public void setIsSecureProofOfWorkMessageSerialized(boolean isSecureProofOfWorkMessageSerialized) {
		this.isSecureProofOfWorkMessageSerialized = isSecureProofOfWorkMessageSerialized;
	}
	
	
	public void doSecureProofOfWorkMessageSerialized()
		   throws InvalidKeyException, NoSuchAlgorithmException, SignatureException,
		   		  NoSuchProviderException, NoSuchPaddingException, InvalidAlgorithmParameterException {
			
		if(!this.isSecureProofOfWorkMessageSerialized) {
			
			this.secureProofOfWorkMessageMetaHeader.doSecureProofOfWorkMessageMetaHeaderSerialization();
			byte[] secureProofOfWorkMessageMetaHeaderSerialized = 
					this.secureProofOfWorkMessageMetaHeader.getSecureProofOfWorkMessageMetaHeaderSerialized();
			
			byte[] userPeerIDSerialized = CommonUtils.fromStringToByteArray(userPeerID);
			
			this.secureProofOfWorkMessageKeyExchange.buildSecureCommonKeyExchangeToSend();
			byte[] secureProofOfWorkMessageKeyExchangeSerializedCipheredAndSigned = 
					this.secureProofOfWorkMessageKeyExchange.getSecureCommonKeyExchangeSerializedCipheredAndSigned();

				
			this.secureProofOfWorkMessageComponents.doSecureProofOfWorkMessageComponentsSerialization();
			byte[] secureProofOfWorkMessageComponentsSerialized = 
					this.secureProofOfWorkMessageComponents.getSecureProofOfWorkMessageComponentsSerialized();
					
			this.secureProofOfWorkMessageDoSMitigation.doHashOfSecureProofOfWorkMessageDoSMitigation();
			byte[] secureProofOfWorkMessageDoSMitigationSerialized = 
					this.secureProofOfWorkMessageDoSMitigation.getSecureProofOfWorkMessageComponentsHashedForDoSMitigation();
			
			int sizeOfSecureBidMessageSerialized = (secureProofOfWorkMessageMetaHeaderSerialized.length +
													userPeerIDSerialized.length +
												    secureProofOfWorkMessageKeyExchangeSerializedCipheredAndSigned.length +
													secureProofOfWorkMessageComponentsSerialized.length +
													secureProofOfWorkMessageDoSMitigationSerialized.length);

			this.secureProofOfWorkMessageSerialized = new byte[sizeOfSecureBidMessageSerialized];
			
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
			System.arraycopy(this.initialisationVector, 0, this.secureProofOfWorkMessageSerialized,
							 serializationOffset, this.initialisationVector.length);
			serializationOffset += this.initialisationVector.length;
	
			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(secureProofOfWorkMessageMetaHeaderSerialized, 0, this.secureProofOfWorkMessageSerialized,
							 serializationOffset, secureProofOfWorkMessageMetaHeaderSerialized.length);
			serializationOffset += secureProofOfWorkMessageMetaHeaderSerialized.length;
		
			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(userPeerIDSerialized, 0, this.secureProofOfWorkMessageSerialized,
							 serializationOffset, userPeerIDSerialized.length);
			serializationOffset += userPeerIDSerialized.length;

			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(secureProofOfWorkMessageKeyExchangeSerializedCipheredAndSigned, 0, this.secureProofOfWorkMessageSerialized,
							 serializationOffset, secureProofOfWorkMessageKeyExchangeSerializedCipheredAndSigned.length);
			serializationOffset += secureProofOfWorkMessageKeyExchangeSerializedCipheredAndSigned.length;
			
			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(secureProofOfWorkMessageComponentsSerialized, 0, this.secureProofOfWorkMessageSerialized,
							 serializationOffset, secureProofOfWorkMessageComponentsSerialized.length);
			serializationOffset += secureProofOfWorkMessageComponentsSerialized.length;
		
			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current Bid serialized,
			// From the position corresponding to the length of the previous Bid's Serialization to
			// the position corresponding to the length of the current Bid's Serialization
			System.arraycopy(secureProofOfWorkMessageDoSMitigationSerialized, 0, this.secureProofOfWorkMessageSerialized,
							 serializationOffset, secureProofOfWorkMessageDoSMitigationSerialized.length);
			
			
			this.setIsSecureProofOfWorkMessageSerialized(true);
			
		}
		
	}
}