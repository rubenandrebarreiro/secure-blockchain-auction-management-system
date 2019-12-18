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
			byte[] secureProofOfWorkMessageComponentsSerializedCiphered = 
					this.secureProofOfWorkMessageComponents.getSecureProofOfWorkMessageComponentsSerializedCiphered();
					
			this.secureProofOfWorkMessageDoSMitigation.doHashOfSecureProofOfWorkMessageDoSMitigation();
			byte[] secureProofOfWorkMessageDoSMitigationSerialized = 
					this.secureProofOfWorkMessageDoSMitigation.getSecureProofOfWorkMessageComponentsHashedForDoSMitigation();
			
			int sizeOfSecureProofOfWorkMessageSerialized = (initialisationVector.length +
													secureProofOfWorkMessageMetaHeaderSerialized.length +
													userPeerIDSerialized.length +
												    secureProofOfWorkMessageKeyExchangeSerializedCipheredAndSigned.length +
													secureProofOfWorkMessageComponentsSerializedCiphered.length +
													secureProofOfWorkMessageDoSMitigationSerialized.length);

			this.secureProofOfWorkMessageSerialized = new byte[sizeOfSecureProofOfWorkMessageSerialized];
			
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
			// the correspondent bytes from the current ProofOfWork serialized,
			// From the position corresponding to the length of the previous ProofOfWork's Serialization to
			// the position corresponding to the length of the current ProofOfWork's Serialization
			System.arraycopy(this.initialisationVector, 0, this.secureProofOfWorkMessageSerialized,
							 serializationOffset, this.initialisationVector.length);
			serializationOffset += this.initialisationVector.length;
	
			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current ProofOfWork serialized,
			// From the position corresponding to the length of the previous ProofOfWork's Serialization to
			// the position corresponding to the length of the current ProofOfWork's Serialization
			System.arraycopy(secureProofOfWorkMessageMetaHeaderSerialized, 0, this.secureProofOfWorkMessageSerialized,
							 serializationOffset, secureProofOfWorkMessageMetaHeaderSerialized.length);
			serializationOffset += secureProofOfWorkMessageMetaHeaderSerialized.length;
		
			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current ProofOfWork serialized,
			// From the position corresponding to the length of the previous ProofOfWork's Serialization to
			// the position corresponding to the length of the current ProofOfWork's Serialization
			System.arraycopy(userPeerIDSerialized, 0, this.secureProofOfWorkMessageSerialized,
							 serializationOffset, userPeerIDSerialized.length);
			serializationOffset += userPeerIDSerialized.length;

			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current ProofOfWork serialized,
			// From the position corresponding to the length of the previous ProofOfWork's Serialization to
			// the position corresponding to the length of the current ProofOfWork's Serialization
			System.arraycopy(secureProofOfWorkMessageKeyExchangeSerializedCipheredAndSigned, 0, this.secureProofOfWorkMessageSerialized,
							 serializationOffset, secureProofOfWorkMessageKeyExchangeSerializedCipheredAndSigned.length);
			serializationOffset += secureProofOfWorkMessageKeyExchangeSerializedCipheredAndSigned.length;
			
			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current ProofOfWork serialized,
			// From the position corresponding to the length of the previous ProofOfWork's Serialization to
			// the position corresponding to the length of the current ProofOfWork's Serialization
			System.arraycopy(secureProofOfWorkMessageComponentsSerializedCiphered, 0, this.secureProofOfWorkMessageSerialized,
							 serializationOffset, secureProofOfWorkMessageComponentsSerializedCiphered.length);
			serializationOffset += secureProofOfWorkMessageComponentsSerializedCiphered.length;
		
			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current ProofOfWork serialized,
			// From the position corresponding to the length of the previous ProofOfWork's Serialization to
			// the position corresponding to the length of the current ProofOfWork's Serialization
			System.arraycopy(secureProofOfWorkMessageDoSMitigationSerialized, 0, this.secureProofOfWorkMessageSerialized,
							 serializationOffset, secureProofOfWorkMessageDoSMitigationSerialized.length);
			
			
			this.setIsSecureProofOfWorkMessageSerialized(true);
			
		}
		
	}
	
	public void undoSecureProofOfWorkMessageSerialized() {
				
		if(this.isSecureProofOfWorkMessageSerialized) {
			
			int sizeOfSecureProofOfWorkMessageMetaHeaderSerialized = 
										 ( ( 2 * CommonUtils.META_HEADER_OUTSIDE_SEPARATORS_LENGTH) +
										   ( 10 * CommonUtils.META_HEADER_INSIDE_SEPARATORS_LENGTH) +
										   ( 11 * CommonUtils.INTEGER_IN_BYTES_LENGTH ) );
			
			byte[] secureProofOfWorkMessageMetaHeaderSerialized = 
					new byte[ sizeOfSecureProofOfWorkMessageMetaHeaderSerialized ];
			
						
			// Operations to Fill a Byte Array, with the following parameters:
			// 1) src - The source of the array to be copied
			// 2) srcPos - The position from the array to be copied, representing the first element to be copied
			// 3) dest - The destination of the array to be copied
			// 4) destPos - The position of the array where will be placed the new copy,
			//              representing the first element where new data will be placed
			// 5) length - The length of the data to be copied from the source array to the destination array
		
			// The offset related to fulfilment of the serialization process
			int serializationOffset = 0;
			
			byte[] initialisationVectorInBytes = new byte[ 16 ];
			
			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current ProofOfWork serialized,
			// From the position corresponding to the length of the previous ProofOfWork's Serialization to
			// the position corresponding to the length of the current ProofOfWork's Serialization
			System.arraycopy(this.secureProofOfWorkMessageSerialized, serializationOffset,
							 initialisationVectorInBytes,
							 0, initialisationVectorInBytes.length);
			serializationOffset += initialisationVectorInBytes.length;
	
			
			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current ProofOfWork serialized,
			// From the position corresponding to the length of the previous ProofOfWork's Serialization to
			// the position corresponding to the length of the current ProofOfWork's Serialization
			System.arraycopy(this.secureProofOfWorkMessageSerialized, serializationOffset, secureProofOfWorkMessageMetaHeaderSerialized,
					0, secureProofOfWorkMessageMetaHeaderSerialized.length);
			serializationOffset += secureProofOfWorkMessageMetaHeaderSerialized.length;
		
			
			this.secureProofOfWorkMessageMetaHeader = 
					new SecureProofOfWorkMessageMetaHeader(secureProofOfWorkMessageMetaHeaderSerialized);
			this.secureProofOfWorkMessageMetaHeader.undoSecureProofOfWorkMessageMetaHeaderSerialization();
			
			
			int sizeOfUserPeerIDSerialized = this.secureProofOfWorkMessageMetaHeader.getSizeOfUserPeerIDSerialized();
			byte[] userPeerIDSerialized = new byte[ sizeOfUserPeerIDSerialized ];
			
			int sizeOfSecureProofOfWorkMessageKeyExchangeSerialized = 
					( this.secureProofOfWorkMessageMetaHeader
						  .getSizeOfSecureProofOfWorkMessageKeyExchangeSerializedCiphered() + 
					  this.secureProofOfWorkMessageMetaHeader
					  	  .getSizeOfSecureProofOfWorkMessageKeyExchangeSerializedCipheredSigned() );
			
			byte[] secureProofOfWorkMessageKeyExchangeSerialized = new byte[ sizeOfSecureProofOfWorkMessageKeyExchangeSerialized ];
			
			
			int sizeOfSecureProofOfWorkMessageComponentsSerializedCiphered = 
					this.secureProofOfWorkMessageMetaHeader
					  	  .getSizeOfSecureProofOfWorkMessageComponentsSerializedCiphered();
			
			byte[] secureProofOfWorkMessageComponentsSerializedCiphered = 
					new byte[ sizeOfSecureProofOfWorkMessageComponentsSerializedCiphered ];
			
			int sizeOfSecureProofOfWorkMessageDoSMitigationSerialized = 
					this.secureProofOfWorkMessageMetaHeader.getSizeOfSecureProofOfWorkMessageDoSMitigationSerialized();
			byte[] secureProofOfWorkMessageDoSMitigationSerialized = 
					new byte[ sizeOfSecureProofOfWorkMessageDoSMitigationSerialized ];
			
			
			
			
			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current ProofOfWork serialized,
			// From the position corresponding to the length of the previous ProofOfWork's Serialization to
			// the position corresponding to the length of the current ProofOfWork's Serialization
			System.arraycopy(this.secureProofOfWorkMessageSerialized, serializationOffset, userPeerIDSerialized,
							 0, userPeerIDSerialized.length);
			serializationOffset += userPeerIDSerialized.length;

			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current ProofOfWork serialized,
			// From the position corresponding to the length of the previous ProofOfWork's Serialization to
			// the position corresponding to the length of the current ProofOfWork's Serialization
			System.arraycopy(this.secureProofOfWorkMessageSerialized, serializationOffset, secureProofOfWorkMessageKeyExchangeSerialized,
							 0, secureProofOfWorkMessageKeyExchangeSerialized.length);
			serializationOffset += secureProofOfWorkMessageKeyExchangeSerialized.length;
			
			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current ProofOfWork serialized,
			// From the position corresponding to the length of the previous ProofOfWork's Serialization to
			// the position corresponding to the length of the current ProofOfWork's Serialization
			System.arraycopy(this.secureProofOfWorkMessageSerialized, serializationOffset, secureProofOfWorkMessageComponentsSerializedCiphered,
							 0, secureProofOfWorkMessageComponentsSerializedCiphered.length);
			serializationOffset += secureProofOfWorkMessageComponentsSerializedCiphered.length;
		
			// Fills the byte array of the Block's Serialization with
			// the correspondent bytes from the current ProofOfWork serialized,
			// From the position corresponding to the length of the previous ProofOfWork's Serialization to
			// the position corresponding to the length of the current ProofOfWork's Serialization
			System.arraycopy(this.secureProofOfWorkMessageSerialized, serializationOffset, secureProofOfWorkMessageDoSMitigationSerialized,
							 0, secureProofOfWorkMessageDoSMitigationSerialized.length);
			
			
			int sizeOfSecureProofOfWorkMessageKeyExchangeSerializedCiphered = 
					this.secureProofOfWorkMessageMetaHeader.getSizeOfSecureProofOfWorkMessageKeyExchangeSerializedCiphered();
			int sizeOfSecureProofOfWorkMessageKeyExchangeSerializedCipheredSigned = 
					this.secureProofOfWorkMessageMetaHeader.getSizeOfSecureProofOfWorkMessageKeyExchangeSerializedCipheredSigned();
			
			
			int sizeOfSecureProofOfWorkMessageComponentsSolvedBlockSerialized = 
					this.secureProofOfWorkMessageMetaHeader.getSizeOfSecureProofOfWorkMessageComponentsSolvedBlockSerialized();
			  
			int sizeOfSecureProofOfWorkMessageComponentsSolvedBlockInfoSerialized = 
					this.secureProofOfWorkMessageMetaHeader.getSizeOfSecureProofOfWorkMessageSolvedBlockInfoSerialized();
			
			int sizeOfSecureProofOfWorkMessageComponentsSolvedBlockSignatureSerialized = 
					this.secureProofOfWorkMessageMetaHeader.getSizeOfSecureProofOfWorkMessageSolvedBlockSignatureSerialized();
			
			int sizeOfBlockSerialized = this.secureProofOfWorkMessageMetaHeader.getSizeOfBlockSerialized();
			
			int sizeOfBlockSolvedHashedSerialized = 
					this.secureProofOfWorkMessageMetaHeader.getSizeOfBlockSolvedHashedSerialized();
			
			
			
			this.userPeerID = CommonUtils.fromByteArrayToString(userPeerIDSerialized);
			
			this.secureProofOfWorkMessageKeyExchange = 
					new SecureCommonKeyExchange(secureProofOfWorkMessageKeyExchangeSerialized,
												sizeOfSecureProofOfWorkMessageKeyExchangeSerializedCiphered,
												sizeOfSecureProofOfWorkMessageKeyExchangeSerializedCipheredSigned,
												userPeerID);
		
			try {
				this.secureProofOfWorkMessageKeyExchange.buildSecureCommonKeyExchangeReceived();
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
		
			
			this.secureProofOfWorkMessageComponents = 
					new SecureProofOfWorkMessageComponents(secureProofOfWorkMessageComponentsSerializedCiphered,
														   initialisationVectorInBytes,
														   this.secureProofOfWorkMessageKeyExchange.getSecretSymmetricKeyInBytes(),
														   sizeOfSecureProofOfWorkMessageComponentsSolvedBlockSerialized,
														   sizeOfSecureProofOfWorkMessageComponentsSolvedBlockInfoSerialized,
														   sizeOfSecureProofOfWorkMessageComponentsSolvedBlockSignatureSerialized,
														   sizeOfBlockSerialized,
														   sizeOfBlockSolvedHashedSerialized,
														   userPeerID);
			
			this.secureProofOfWorkMessageDoSMitigation =
					new SecureProofOfWorkMessageDoSMitigation
							(secureProofOfWorkMessageComponentsSerializedCiphered,
							 secureProofOfWorkMessageDoSMitigationSerialized,
							 this.secureProofOfWorkMessageKeyExchange.getSecretHMACKeyForDoSMitigationInBytes());
			
			
			
			this.setIsSecureProofOfWorkMessageSerialized(false);
			
		}
	}
}