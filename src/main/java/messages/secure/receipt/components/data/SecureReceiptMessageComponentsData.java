package main.java.messages.secure.receipt.components.data;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;

import main.java.common.utils.CommonUtils;
import main.java.messages.secure.receipt.components.data.info.SecureReceiptMessageComponentsDataInfo;
import main.java.messages.secure.receipt.components.data.signature.SecureReceiptMessageComponentsDataSignature;

public class SecureReceiptMessageComponentsData {

	private SecureReceiptMessageComponentsDataInfo secureReceiptMessageComponentsDataInfo;
	
	private byte[] secureReceiptMessageComponentsDataInfoSerialized;
	
	private int sizeOfSecureReceiptMessageComponentsDataInfoSerialized;
	
	private int sizeOfBidderUserClientIDSerialized;
	
	
	private SecureReceiptMessageComponentsDataSignature secureReceiptMessageComponentsDataSignature;
	
	private byte[] secureReceiptMessageComponentsDataSignatureSerialized;
	
	private int sizeOfSecureReceiptMessageComponentsDataSignatureSerialized;
	
	
	
	private String secureReceiptMessageComponentsDataResponse;
	
	private byte[] secureReceiptMessageComponentsDataResponseSerialized;
	
	private int sizeOfSecureReceiptMessageComponentsDataResponseSerialized;
	
	
	private byte[] secureReceiptMessageComponentsDataSerialized;
	
	private boolean isSecureReceiptMessageComponentsDataSerialized;
	
	
	private String userPeerID;
	
	
	public SecureReceiptMessageComponentsData
		  (SecureReceiptMessageComponentsDataInfo secureReceiptMessageComponentsDataInfo,
		   SecureReceiptMessageComponentsDataSignature secureReceiptMessageComponentsDataSignature,
		   String secureReceiptMessageComponentsDataResponse) {
		
		this.secureReceiptMessageComponentsDataInfo = secureReceiptMessageComponentsDataInfo;
		this.secureReceiptMessageComponentsDataInfoSerialized = null;
		this.sizeOfSecureReceiptMessageComponentsDataInfoSerialized = 0;
		
		this.sizeOfBidderUserClientIDSerialized = 0;
		
		this.secureReceiptMessageComponentsDataSignature = secureReceiptMessageComponentsDataSignature;
		this.secureReceiptMessageComponentsDataSignatureSerialized = null;
		this.sizeOfSecureReceiptMessageComponentsDataSignatureSerialized = 0;
		
		this.secureReceiptMessageComponentsDataResponse = secureReceiptMessageComponentsDataResponse;
		this.secureReceiptMessageComponentsDataResponseSerialized = null;
		this.sizeOfSecureReceiptMessageComponentsDataResponseSerialized = 0;
		
		this.secureReceiptMessageComponentsDataSerialized = null;
		this.isSecureReceiptMessageComponentsDataSerialized = false;
		
		this.userPeerID = null;
		
	}
	
	public SecureReceiptMessageComponentsData
		  (byte[] secureReceiptMessageComponentsDataSerialized,
		   int sizeOfSecureReceiptMessageComponentsDataInfoSerialized,
		   int sizeOfSecureReceiptMessageComponentsDataSignatureSerialized,
		   int sizeOfSecureReceiptMessageComponentsDataResponseSerialized,
		   int sizeOfBidderUserClientIDSerialized,
		   String userPeerID) {
	
		this.secureReceiptMessageComponentsDataSerialized = 
					secureReceiptMessageComponentsDataSerialized;
		this.isSecureReceiptMessageComponentsDataSerialized = true;
		
		this.secureReceiptMessageComponentsDataInfo = null;
		this.secureReceiptMessageComponentsDataInfoSerialized = null;
		this.sizeOfSecureReceiptMessageComponentsDataInfoSerialized = 
					sizeOfSecureReceiptMessageComponentsDataInfoSerialized;
		
		this.sizeOfBidderUserClientIDSerialized = sizeOfBidderUserClientIDSerialized;
		
		this.secureReceiptMessageComponentsDataSignature = null;
		this.secureReceiptMessageComponentsDataSignatureSerialized = null;
		this.sizeOfSecureReceiptMessageComponentsDataSignatureSerialized = 
					sizeOfSecureReceiptMessageComponentsDataSignatureSerialized;
		
		this.secureReceiptMessageComponentsDataResponse = null;
		this.secureReceiptMessageComponentsDataResponseSerialized = null;
		this.sizeOfSecureReceiptMessageComponentsDataResponseSerialized = 
					sizeOfSecureReceiptMessageComponentsDataResponseSerialized;
		
		this.userPeerID = userPeerID;
		
	}
	
	
	
	public SecureReceiptMessageComponentsDataInfo getSecureReceiptMessageComponentsDataInfo() {
		return this.secureReceiptMessageComponentsDataInfo;
	}
	
	public byte[] getSecureReceiptMessageComponentsDataInfoSerialized() {
		return this.secureReceiptMessageComponentsDataInfoSerialized;
	}
	
	public int getSizeOfSecureReceiptMessageComponentsDataInfoSerialized() {
		return this.sizeOfSecureReceiptMessageComponentsDataInfoSerialized;
	}	
	
	public int getSizeOfBidderUserClientIDSerialized() {
		return this.sizeOfBidderUserClientIDSerialized;
	}
	
	
	public SecureReceiptMessageComponentsDataSignature getSecureReceiptMessageComponentsDataSignature() {
		return this.secureReceiptMessageComponentsDataSignature;
	}
	
	public byte[] getSecureReceiptMessageComponentsDataSignatureSerialized() {
		return this.secureReceiptMessageComponentsDataSignatureSerialized;
	}
	
	public int getSizeOfSecureReceiptMessageComponentsDataSignatureSerialized() {
		return this.sizeOfSecureReceiptMessageComponentsDataSignatureSerialized;
	}
	
	
	public String getSecureReceiptMessageComponentsDataResponse() {
		return this.secureReceiptMessageComponentsDataResponse;
	}
	
	public byte[] getSecureReceiptMessageComponentsDataResponseSerialized() {
		return this.secureReceiptMessageComponentsDataResponseSerialized;
	}
	
	public int getSizeOfSecureReceiptMessageComponentsDataResponseSerialized() {
		return this.sizeOfSecureReceiptMessageComponentsDataResponseSerialized;
	}
	
	
	public byte[] getSecureReceiptMessageComponentsDataSerialized() {
		return this.secureReceiptMessageComponentsDataSerialized;
	}
	
	public boolean getIsSecureReceiptMessageComponentsDataSerialized() {
		return this.isSecureReceiptMessageComponentsDataSerialized;
	}
	
	public void setIsSecureReceiptMessageComponentsDataSerialized
	      (boolean isSecureReceiptMessageComponentsDataSerialized) {
		
		this.isSecureReceiptMessageComponentsDataSerialized = 
				isSecureReceiptMessageComponentsDataSerialized;
		
	}
	
	public String getUserPeerID() {
		return this.userPeerID;
	}
	
	
	
	public void doSecureReceiptMessageComponentsDataSerialization()
		   throws InvalidKeyException, SignatureException, NoSuchAlgorithmException {
		
		if(!this.isSecureReceiptMessageComponentsDataSerialized) {
			
			this.secureReceiptMessageComponentsDataInfo
				.doSecureReceiptMessageComponentsDataInfoSerialization();
			
			this.secureReceiptMessageComponentsDataInfoSerialized =
						this.secureReceiptMessageComponentsDataInfo
							.getSecureReceiptMessageComponentsDataInfoSerialized();
			
			this.sizeOfSecureReceiptMessageComponentsDataInfoSerialized = 
					this.secureReceiptMessageComponentsDataInfoSerialized.length;
			
			
			this.secureReceiptMessageComponentsDataSignature
				.signSecureReceiptMessageComponentsDataInfoSignature();
			
			this.secureReceiptMessageComponentsDataSignatureSerialized = 
					this.secureReceiptMessageComponentsDataSignature
						.getSecureReceiptMessageComponentsDataInfoSerializedDigitalSigned();
			
			this.sizeOfSecureReceiptMessageComponentsDataSignatureSerialized = 
					this.secureReceiptMessageComponentsDataSignatureSerialized.length;
			
			
			this.secureReceiptMessageComponentsDataResponseSerialized = 
					CommonUtils.fromStringToByteArray(this.secureReceiptMessageComponentsDataResponse);
			
			this.sizeOfSecureReceiptMessageComponentsDataResponseSerialized = 
					this.secureReceiptMessageComponentsDataResponseSerialized.length;
			
			
			int sizeOfSecureReceiptMessageComponentsDataSerialized = ( this.sizeOfSecureReceiptMessageComponentsDataInfoSerialized +
																	   this.sizeOfSecureReceiptMessageComponentsDataSignatureSerialized +
																	   this.sizeOfSecureReceiptMessageComponentsDataResponseSerialized );
			
			this.secureReceiptMessageComponentsDataSerialized = 
							new byte[ sizeOfSecureReceiptMessageComponentsDataSerialized ];
			
			
			// Operations to Fill a Byte Array, with the following parameters:
			// 1) src - The source of the array to be copied
			// 2) srcPos - The position from the array to be copied, representing the first element to be copied
			// 3) dest - The destination of the array to be copied
			// 4) destPos - The position of the array where will be placed the new copy,
			//              representing the first element where new data will be placed
			// 5) length - The length of the data to be copied from the source array to the destination array
			
			// The offset related to fulfilment of the serialization process
			int serializationOffset = 0;
			
			// Fills the byte array of the Bid serialized with its ID,
			// From the initial position to the corresponding to the length of Bid's ID
			System.arraycopy(this.secureReceiptMessageComponentsDataInfoSerialized,
							 0, this.secureReceiptMessageComponentsDataSerialized,
							 serializationOffset, secureReceiptMessageComponentsDataInfoSerialized.length);
			serializationOffset += this.secureReceiptMessageComponentsDataInfoSerialized.length;
			
			// Fills the byte array of the Bid serialized with its User/Client Bidder's ID,
			// From the position corresponding to the length of Bid's ID to
			// the corresponding of the length of Bid's User/Client Bidder's ID
			System.arraycopy(this.secureReceiptMessageComponentsDataSignatureSerialized,
							 0, this.secureReceiptMessageComponentsDataSerialized,
							 serializationOffset, secureReceiptMessageComponentsDataSignatureSerialized.length);
			serializationOffset += secureReceiptMessageComponentsDataSignatureSerialized.length;
			
			// Fills the byte array of the Bid serialized with its Timestamp,
			// From the position corresponding to the length of Bid's Value Amount to
			// the corresponding of the length of Bid's Timestamp
			System.arraycopy(this.secureReceiptMessageComponentsDataResponseSerialized,
							 0, this.secureReceiptMessageComponentsDataSerialized,
							 serializationOffset, secureReceiptMessageComponentsDataResponseSerialized.length);
			
			
			this.setIsSecureReceiptMessageComponentsDataSerialized(true);
			
		}
		
	}
	
	
	public void undoSecureReceiptMessageComponentsDataSerialization() {
		

		if(this.isSecureReceiptMessageComponentsDataSerialized) {
			
			this.secureReceiptMessageComponentsDataInfoSerialized = 
					new byte[this.sizeOfSecureReceiptMessageComponentsDataInfoSerialized];
			this.secureReceiptMessageComponentsDataSignatureSerialized = 
					new byte[this.sizeOfSecureReceiptMessageComponentsDataSignatureSerialized];
			this.secureReceiptMessageComponentsDataResponseSerialized = 
					new byte[this.sizeOfSecureReceiptMessageComponentsDataResponseSerialized];
			
			
			// Operations to Fill a Byte Array, with the following parameters:
			// 1) src - The source of the array to be copied
			// 2) srcPos - The position from the array to be copied, representing the first element to be copied
			// 3) dest - The destination of the array to be copied
			// 4) destPos - The position of the array where will be placed the new copy,
			//              representing the first element where new data will be placed
			// 5) length - The length of the data to be copied from the source array to the destination array
			
			// The offset related to fulfilment of the serialization process
			int serializationOffset = 0;
			
			// Fills the byte array of the Bid's ID serialized from the Bid serialized,
			// From the initial position to the corresponding to the length of Bid's serialized ID
			System.arraycopy(this.secureReceiptMessageComponentsDataSerialized,
							 serializationOffset, this.secureReceiptMessageComponentsDataInfoSerialized,
							 0, this.secureReceiptMessageComponentsDataInfoSerialized.length);
			serializationOffset += this.secureReceiptMessageComponentsDataInfoSerialized.length;
			
			// Fills the byte array of the Bid's User/Client Bidder's ID serialized from the Bid serialized,
			// From the position corresponding to the length of Bid's ID serialized to
			// the corresponding of the length of Bid's User/Client Bidder's ID serialized
			System.arraycopy(this.secureReceiptMessageComponentsDataSerialized,
							 serializationOffset, this.secureReceiptMessageComponentsDataSignatureSerialized,
							 0, this.secureReceiptMessageComponentsDataSignatureSerialized.length);
			serializationOffset += this.secureReceiptMessageComponentsDataSignatureSerialized.length;

			// Fills the byte array of the Bid's Timestamp from the Bid serialized,
			// From the position corresponding to the length of Bid's Value Amount serialized to
			// the corresponding of the length of Bid's Timestamp serialized
			System.arraycopy(this.secureReceiptMessageComponentsDataSerialized,
							 serializationOffset, this.secureReceiptMessageComponentsDataResponseSerialized,
							 0, this.secureReceiptMessageComponentsDataResponseSerialized.length);
			
			
			this.secureReceiptMessageComponentsDataInfo = 
					new SecureReceiptMessageComponentsDataInfo
							(this.secureReceiptMessageComponentsDataInfoSerialized,
							 this.sizeOfBidderUserClientIDSerialized);
			
			this.secureReceiptMessageComponentsDataSignature = 
					new SecureReceiptMessageComponentsDataSignature
							(this.secureReceiptMessageComponentsDataInfoSerialized,
							 this.secureReceiptMessageComponentsDataSignatureSerialized,
							 this.userPeerID);
			
			this.secureReceiptMessageComponentsDataResponse = 
				 CommonUtils.fromByteArrayToString(this.secureReceiptMessageComponentsDataResponseSerialized);
			
			
			this.setIsSecureReceiptMessageComponentsDataSerialized(false);
			
		}
		
	}
	
}
