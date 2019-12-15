package main.java.messages.secure.receipt.components;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;

import main.java.common.utils.CommonUtils;
import main.java.messages.secure.common.header.SecureCommonHeader;
import main.java.messages.secure.receipt.components.data.SecureReceiptMessageComponentsData;

public class SecureReceiptMessageComponents {

	private SecureCommonHeader secureCommonHeader;
	
	private byte[] secureCommonHeaderSerialized;
	
	
	private SecureReceiptMessageComponentsData secureReceiptMessageComponentsData;
	
	private byte[] secureReceiptMessageComponentsDataSerialized;
	
	private int sizeOfSecureReceiptMessageComponentsDataSerialized;
	
	
	private int sizeOfSecureReceiptMessageComponentsDataInfoSerialized;
	
	private int sizeOfSecureReceiptMessageComponentsDataSignatureSerialized;
	
	private int sizeOfSecureReceiptMessageComponentsDataResponseSerialized;
	
	private int sizeOfBidderUserClientIDSerialized;
	
	
	private byte[] secureReceiptMessageComponentsSerialized;
	
	private boolean isSecureReceiptMessageComponentsSerialized;
	
	
	private String userPeerID;
	
	
	
	public SecureReceiptMessageComponents(SecureCommonHeader secureCommonHeader,
										  SecureReceiptMessageComponentsData secureReceiptMessageComponentsData) {
		
		this.secureCommonHeader = secureCommonHeader;
		this.secureCommonHeaderSerialized = null;
		
		this.secureReceiptMessageComponentsData = secureReceiptMessageComponentsData;
		this.secureReceiptMessageComponentsDataSerialized = null;
		this.sizeOfSecureReceiptMessageComponentsDataSerialized = 0;
		
		this.sizeOfSecureReceiptMessageComponentsDataInfoSerialized = 0;
		this.sizeOfSecureReceiptMessageComponentsDataSignatureSerialized = 0;
		this.sizeOfSecureReceiptMessageComponentsDataResponseSerialized = 0;
		this.sizeOfBidderUserClientIDSerialized = 0;
		
		this.secureReceiptMessageComponentsSerialized = null;
		this.isSecureReceiptMessageComponentsSerialized = false;
		
		this.userPeerID = null;
		
	}
	
	public SecureReceiptMessageComponents(byte[] secureReceiptMessageComponentsSerialized,
										  int sizeOfSecureReceiptMessageComponentsDataSerialized,
										  int sizeOfSecureReceiptMessageComponentsDataInfoSerialized,
										  int sizeOfSecureReceiptMessageComponentsDataSignatureSerialized,
										  int sizeOfSecureReceiptMessageComponentsDataResponseSerialized,
										  int sizeOfBidderUserClientIDSerialized,
										  String userPeerID) {
		
		this.secureReceiptMessageComponentsSerialized = secureReceiptMessageComponentsSerialized;
		this.isSecureReceiptMessageComponentsSerialized = true;
		
		this.secureCommonHeader = null;
		this.secureCommonHeaderSerialized = null;
		
		this.secureReceiptMessageComponentsData = null;
		this.secureReceiptMessageComponentsDataSerialized = null;
		this.sizeOfSecureReceiptMessageComponentsDataSerialized = 
				sizeOfSecureReceiptMessageComponentsDataSerialized;
		
		this.sizeOfSecureReceiptMessageComponentsDataInfoSerialized = 
				sizeOfSecureReceiptMessageComponentsDataInfoSerialized;
		this.sizeOfSecureReceiptMessageComponentsDataSignatureSerialized = 
				sizeOfSecureReceiptMessageComponentsDataSignatureSerialized;
		this.sizeOfSecureReceiptMessageComponentsDataResponseSerialized = 
				sizeOfSecureReceiptMessageComponentsDataResponseSerialized;
		this.sizeOfBidderUserClientIDSerialized = sizeOfBidderUserClientIDSerialized;
		
		this.userPeerID = userPeerID;
		
	}
	
	
	public SecureCommonHeader getSecureCommonHeader() {
		return this.secureCommonHeader;
	}
	
	public byte[] getSecureCommonHeaderSerialized() {
		return this.secureCommonHeaderSerialized;
	}
	
	public SecureReceiptMessageComponentsData getSecureReceiptMessageComponentsData() {
		return this.secureReceiptMessageComponentsData;
	}
	
	public byte[] getSecureReceiptMessageComponentsDataSerialized() {
		return this.secureReceiptMessageComponentsDataSerialized;
	}
	
	public int getSizeOfSecureReceiptMessageComponentsDataSerialized() {
		return this.sizeOfSecureReceiptMessageComponentsDataSerialized;
	}
	
	
	
    public int getSizeOfSecureReceiptMessageComponentsDataInfoSerialized() {
    	return this.sizeOfSecureReceiptMessageComponentsDataInfoSerialized;
    }
	
	public int getSizeOfSecureReceiptMessageComponentsDataSignatureSerialized() {
		return this.sizeOfSecureReceiptMessageComponentsDataSignatureSerialized;
	}
	
	public int getSizeOfSecureReceiptMessageComponentsDataResponseSerialized() {
		return this.sizeOfSecureReceiptMessageComponentsDataResponseSerialized;
	}
	
	public int getSizeOfBidderUserClientIDSerialized() {
		return this.sizeOfBidderUserClientIDSerialized;
	}
	
	
	
	public byte[] getSecureReceiptMessageComponentsSerialized() {
		return this.secureReceiptMessageComponentsSerialized;
	}
	
	public boolean getIsSecureReceiptMessageComponentsSerialized() {
		return this.isSecureReceiptMessageComponentsSerialized;
	}
	
	public void setIsSecureReceiptMessageComponentsSerialized
		  (boolean isSecureReceiptMessageComponentsSerialized) {
		
		this.isSecureReceiptMessageComponentsSerialized =
				isSecureReceiptMessageComponentsSerialized;
		
	}
	
	public String getUserPeerID() {
		return this.userPeerID;
	}
	
	
	
	public void doSecureReceiptMessageComponentsSerialization()
		   throws InvalidKeyException, SignatureException, NoSuchAlgorithmException {
		
		if(!this.isSecureReceiptMessageComponentsSerialized) {
			
			this.secureCommonHeader.doSecureCommonHeaderSerialization();
			this.secureCommonHeaderSerialized = 
					this.secureCommonHeader.getSecureCommonHeaderSerialized();
			
			this.secureReceiptMessageComponentsData.doSecureReceiptMessageComponentsDataSerialization();
			this.secureReceiptMessageComponentsDataSerialized = 
					this.secureReceiptMessageComponentsData.getSecureReceiptMessageComponentsDataSerialized();
			this.sizeOfSecureReceiptMessageComponentsDataSerialized = 
					this.secureReceiptMessageComponentsDataSerialized.length;
			
			
			int sizeOfSecureReceiptMessageComponentsSerialized =
					( CommonUtils.COMMON_HEADER_LENGTH + 
					  this.sizeOfSecureReceiptMessageComponentsDataSerialized );
			
			this.secureReceiptMessageComponentsSerialized = 
					new byte[ sizeOfSecureReceiptMessageComponentsSerialized ];
			
			
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
			System.arraycopy(this.secureCommonHeaderSerialized,
							 0, this.secureReceiptMessageComponentsSerialized,
							 serializationOffset, this.secureCommonHeaderSerialized.length);
			serializationOffset += this.secureCommonHeaderSerialized.length;
			
			// Fills the byte array of the Bid serialized with its User/Client Bidder's ID,
			// From the position corresponding to the length of Bid's ID to
			// the corresponding of the length of Bid's User/Client Bidder's ID
			System.arraycopy(this.secureReceiptMessageComponentsDataSerialized,
							 0, this.secureReceiptMessageComponentsSerialized,
							 serializationOffset, this.secureReceiptMessageComponentsDataSerialized.length);
			
			
			this.setIsSecureReceiptMessageComponentsSerialized(true);
			
		}
		
	}
	
	public void undoSecureReceiptMessageComponentsSerialization() {
		
		if(this.isSecureReceiptMessageComponentsSerialized) {
			
			this.secureCommonHeaderSerialized = new byte[ CommonUtils.COMMON_HEADER_LENGTH ];
			this.secureReceiptMessageComponentsDataSerialized = 
					new byte[ this.sizeOfSecureReceiptMessageComponentsDataSerialized ];
			
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
			System.arraycopy(this.secureReceiptMessageComponentsSerialized,
							 serializationOffset, this.secureCommonHeaderSerialized,
							 0, this.secureCommonHeaderSerialized.length);
			serializationOffset += this.secureCommonHeaderSerialized.length;
			
			// Fills the byte array of the Bid serialized with its User/Client Bidder's ID,
			// From the position corresponding to the length of Bid's ID to
			// the corresponding of the length of Bid's User/Client Bidder's ID
			System.arraycopy(this.secureReceiptMessageComponentsSerialized,
							 serializationOffset, this.secureReceiptMessageComponentsDataSerialized,
							 0, this.secureReceiptMessageComponentsDataSerialized.length);
			
			
			this.secureCommonHeader = new SecureCommonHeader(this.secureCommonHeaderSerialized);
			this.secureCommonHeader.undoSecureCommonHeaderSerialization();
			
			this.secureReceiptMessageComponentsData = 
					new SecureReceiptMessageComponentsData(this.secureReceiptMessageComponentsDataSerialized,
														   this.sizeOfSecureReceiptMessageComponentsDataInfoSerialized,
														   this.sizeOfSecureReceiptMessageComponentsDataSignatureSerialized,
														   this.sizeOfSecureReceiptMessageComponentsDataResponseSerialized,
														   this.sizeOfBidderUserClientIDSerialized,
														   this.userPeerID);
			
			
			this.setIsSecureReceiptMessageComponentsSerialized(false);
			
		}
		
	}
	
}
