package main.java.messages.secure.receipt.components.data.info;

import main.java.common.utils.CommonUtils;

public class SecureReceiptMessageComponentsDataInfo {

	private long bidID;
	
	private String bidderUserClientID;
	
	private int sizeOfBidderUserClientIDSerialized;
	
	private long bidTimestamp;
	
	
	private byte[] secureReceiptMessageComponentsDataInfoSerialized;
	
	private boolean isSecureReceiptMessageComponentsDataInfoSerialized;
	
	
	public SecureReceiptMessageComponentsDataInfo(long bidID, String bidderUserClientID, long bidTimestamp) {
		
		this.bidID = bidID;
		
		this.bidderUserClientID = bidderUserClientID;
		this.sizeOfBidderUserClientIDSerialized = this.bidderUserClientID.length();
		
		this.bidTimestamp = bidTimestamp;
		
		this.secureReceiptMessageComponentsDataInfoSerialized = null;
		this.isSecureReceiptMessageComponentsDataInfoSerialized = false;
		
	}
	
	
	public SecureReceiptMessageComponentsDataInfo(byte[] secureReceiptMessageComponentsDataInfoSerialized,
												  int sizeOfBidderUserClientIDSerialized) {

		this.secureReceiptMessageComponentsDataInfoSerialized = secureReceiptMessageComponentsDataInfoSerialized;
		this.isSecureReceiptMessageComponentsDataInfoSerialized = true;

		this.bidID = -1L;
		
		this.bidderUserClientID = null;
		this.sizeOfBidderUserClientIDSerialized = sizeOfBidderUserClientIDSerialized;
		
		this.bidTimestamp = -1L;
		
	} 
	
	
	public long getBidID() {
		return this.bidID;
	}
	
	public String getBidderUserClientID() {
		return this.bidderUserClientID;
	}
	
	public int getSizeOfBidderUserClientIDSerialized() {
		return this.sizeOfBidderUserClientIDSerialized;
	}
	
	public long getBidTimestamp() {
		return this.bidTimestamp;
	}
	
	
	public byte[] getSecureReceiptMessageComponentsDataInfoSerialized() {
		return this.secureReceiptMessageComponentsDataInfoSerialized;
	}
	
	public boolean getIsSecureReceiptMessageComponentsDataInfoSerialized() {
		return this.isSecureReceiptMessageComponentsDataInfoSerialized;
	}

	public void setIsSecureReceiptMessageComponentsDataInfoSerialized
		  (boolean isSecureReceiptMessageComponentsDataInfoSerialized) {
		
		this.isSecureReceiptMessageComponentsDataInfoSerialized = 
						isSecureReceiptMessageComponentsDataInfoSerialized;
	
	}
	
	
	public void doSecureReceiptMessageComponentsDataInfoSerialization() {
		
		if(!this.isSecureReceiptMessageComponentsDataInfoSerialized) {
			
			byte[] bidIDSerialized = CommonUtils.fromLongToByteArray(bidID);
			byte[] bidderUserClientIDSerialized = CommonUtils.fromStringToByteArray(bidderUserClientID);
			byte[] bidTimestampSerialized = CommonUtils.fromLongToByteArray(bidTimestamp);
			
			
			int sizeOfSecureReceiptMessageComponentsDataInfoSerialized = 
					( ( 2 * CommonUtils.LONG_IN_BYTES_LENGTH ) +
							bidderUserClientIDSerialized.length );
			
			this.secureReceiptMessageComponentsDataInfoSerialized = 
					new byte[ sizeOfSecureReceiptMessageComponentsDataInfoSerialized ];
			
			
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
			System.arraycopy(bidIDSerialized, 0, this.secureReceiptMessageComponentsDataInfoSerialized,
							 serializationOffset, bidIDSerialized.length);
			serializationOffset += bidIDSerialized.length;
			
			// Fills the byte array of the Bid serialized with its User/Client Bidder's ID,
			// From the position corresponding to the length of Bid's ID to
			// the corresponding of the length of Bid's User/Client Bidder's ID
			System.arraycopy(bidderUserClientIDSerialized, 0, this.secureReceiptMessageComponentsDataInfoSerialized,
							 serializationOffset, bidderUserClientIDSerialized.length);
			serializationOffset += bidderUserClientIDSerialized.length;
			
			// Fills the byte array of the Bid serialized with its Timestamp,
			// From the position corresponding to the length of Bid's Value Amount to
			// the corresponding of the length of Bid's Timestamp
			System.arraycopy(bidTimestampSerialized, 0, this.secureReceiptMessageComponentsDataInfoSerialized,
							 serializationOffset, bidTimestampSerialized.length);
			
			
			this.setIsSecureReceiptMessageComponentsDataInfoSerialized(true);
			
		}
		
	}
	
	public void undoSecureReceiptMessageComponentsDataInfoSerialization() {
		
		byte[] bidIDSerialized = new byte[CommonUtils.LONG_IN_BYTES_LENGTH];
		byte[] bidderUserClientIDSerialized = new byte[this.sizeOfBidderUserClientIDSerialized];
		byte[] bidTimestampSerialized = new byte[CommonUtils.LONG_IN_BYTES_LENGTH];
		
		
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
		System.arraycopy(this.secureReceiptMessageComponentsDataInfoSerialized, serializationOffset, bidIDSerialized,
						 0, bidIDSerialized.length);
		serializationOffset += bidIDSerialized.length;
		
		// Fills the byte array of the Bid's User/Client Bidder's ID serialized from the Bid serialized,
		// From the position corresponding to the length of Bid's ID serialized to
		// the corresponding of the length of Bid's User/Client Bidder's ID serialized
		System.arraycopy(this.secureReceiptMessageComponentsDataInfoSerialized, serializationOffset, bidderUserClientIDSerialized,
						 0, bidderUserClientIDSerialized.length);
		serializationOffset += bidderUserClientIDSerialized.length;

		// Fills the byte array of the Bid's Timestamp from the Bid serialized,
		// From the position corresponding to the length of Bid's Value Amount serialized to
		// the corresponding of the length of Bid's Timestamp serialized
		System.arraycopy(this.secureReceiptMessageComponentsDataInfoSerialized, serializationOffset, bidTimestampSerialized,
						 0, bidTimestampSerialized.length);
		
		
		this.bidID = CommonUtils.fromByteArrayToLong(bidIDSerialized);
		this.bidderUserClientID = CommonUtils.fromByteArrayToString(bidderUserClientIDSerialized);
		this.bidTimestamp = CommonUtils.fromByteArrayToLong(bidTimestampSerialized);
		
		
		this.setIsSecureReceiptMessageComponentsDataInfoSerialized(false);
		
	}
	
}
