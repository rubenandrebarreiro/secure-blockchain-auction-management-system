package main.java.resources.bid;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import main.java.common.utils.CommonUtils;

@DatabaseTable(tableName = "bids")
public class Bid {
	
	@DatabaseField( id = true )
	private long bidID;
	@DatabaseField
	private String bidderUserClientID;
	@DatabaseField
	private double bidValue;
	@DatabaseField
	private long bidTimestamp;
	@DatabaseField
	private boolean isBidMined;
	
	
	
	private byte[] bidSerializedBytes;

	
	public Bid() {
		
		// Obsolete/Empty Consructor
		
	}
	
	public Bid(long bidID, String bidderUserClientID, double bidValue) {

		this.bidID = bidID;
		this.bidderUserClientID = bidderUserClientID;
		this.bidValue = bidValue;
		this.bidTimestamp = System.currentTimeMillis();

		this.isBidMined = false;
		
		this.bidSerializedBytes = null;
		
	}
	
	public Bid(byte[] bidSerializedBytes) {
		
		this.bidID = -1L;
		this.bidderUserClientID = null;
		this.bidValue = -1.0;
		this.bidTimestamp = -1L;
		
		this.isBidMined = false;
		
		this.bidSerializedBytes = bidSerializedBytes;
		
	}
	
	
	public long getBidID() {
		return this.bidID;
	}

	public void setBidID(long bidID) {
		this.bidID = bidID;
	}

	public String getBidderUserClientID() {
		return this.bidderUserClientID;
	}

	public void setBidderUserClientID(String bidderUserClientID) {
		this.bidderUserClientID = bidderUserClientID;
	}

	public double getBidValue() {
		return this.bidValue;
	}

	public void setBidValue(double bidValue) {
		this.bidValue = bidValue;
	}

	public long getBidTimestamp() {
		return this.bidTimestamp;
	}

	public void setBidTimestamp(long bidTimestamp) {
		this.bidTimestamp = bidTimestamp;
	}

	public boolean getIsBidMined() {
		return this.isBidMined;
	}

	public void setIsBidMined(boolean isBidMined) {
		this.isBidMined = isBidMined;
	}
	
	public byte[] getBidSerializedBytes() {
		return this.bidSerializedBytes;
	}

	public void setBidSerializedBytes(byte[] bidSerializedBytes) {
		this.bidSerializedBytes = bidSerializedBytes;
	}
	
	public void doSerialization() {
		
		int sizeOfBidSerializedBytes = ( CommonUtils.LONG_IN_BYTES_LENGTH + this.bidderUserClientID.length()
		                            + CommonUtils.DOUBLE_IN_BYTES_LENGTH + CommonUtils.LONG_IN_BYTES_LENGTH );
		
		this.bidSerializedBytes = new byte[sizeOfBidSerializedBytes];
		
		byte[] bidIDSerialized = CommonUtils.fromLongToByteArray(this.bidID);
		byte[] bidderUserClientIDSerialized = CommonUtils.fromStringToByteArray(this.bidderUserClientID);
		byte[] bidValueSerialized = CommonUtils.fromDoubleToByteArray(this.bidValue);
		byte[] bidTimestampSerialized = CommonUtils.fromLongToByteArray(this.bidTimestamp);
		
		
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
		System.arraycopy(bidIDSerialized, 0, this.bidSerializedBytes, serializationOffset, bidIDSerialized.length);
		serializationOffset += bidIDSerialized.length;
		
		// Fills the byte array of the Bid serialized with its User/Client Bidder's ID,
		// From the position corresponding to the length of Bid's ID to
		// the corresponding of the length of Bid's User/Client Bidder's ID
		System.arraycopy(bidderUserClientIDSerialized, 0, this.bidSerializedBytes, serializationOffset, bidderUserClientIDSerialized.length);
		serializationOffset += bidderUserClientIDSerialized.length;

		// Fills the byte array of the Bid serialized with its Value Amount,
		// From the position corresponding to the length of Bid's User/Client Bidder's ID to
		// the corresponding of the length of Bid's Value Amount
		System.arraycopy(bidValueSerialized, 0, this.bidSerializedBytes, serializationOffset, bidValueSerialized.length);
		serializationOffset += bidValueSerialized.length;

		// Fills the byte array of the Bid serialized with its Timestamp,
		// From the position corresponding to the length of Bid's Value Amount to
		// the corresponding of the length of Bid's Timestamp
		System.arraycopy(bidTimestampSerialized, 0, this.bidSerializedBytes, serializationOffset, bidTimestampSerialized.length);
		
	}

	public void undoSerialization(int sizeOfBidderUserClientID) {
		
		byte[] bidIDSerialized = new byte[CommonUtils.LONG_IN_BYTES_LENGTH];
		byte[] bidderUserClientIDSerialized = new byte[sizeOfBidderUserClientID];
		byte[] bidValueSerialized = new byte[CommonUtils.DOUBLE_IN_BYTES_LENGTH];
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
		System.arraycopy(this.bidSerializedBytes, serializationOffset, bidIDSerialized, 0, bidIDSerialized.length);
		serializationOffset += bidIDSerialized.length;
		
		// Fills the byte array of the Bid's User/Client Bidder's ID serialized from the Bid serialized,
		// From the position corresponding to the length of Bid's ID serialized to
		// the corresponding of the length of Bid's User/Client Bidder's ID serialized
		System.arraycopy(this.bidSerializedBytes, serializationOffset, bidderUserClientIDSerialized, 0, bidderUserClientIDSerialized.length);
		serializationOffset += bidderUserClientIDSerialized.length;

		// Fills the byte array of the Bid's Value Amount from the Bid serialized,
		// From the position corresponding to the length of Bid's User/Client Bidder's ID serialized to
		// the corresponding of the length of Bid's Value Amount serialized
		System.arraycopy(this.bidSerializedBytes, serializationOffset, bidValueSerialized, 0, bidValueSerialized.length);
		serializationOffset += bidValueSerialized.length;

		// Fills the byte array of the Bid's Timestamp from the Bid serialized,
		// From the position corresponding to the length of Bid's Value Amount serialized to
		// the corresponding of the length of Bid's Timestamp serialized
		System.arraycopy(this.bidSerializedBytes, serializationOffset, bidTimestampSerialized, 0, bidTimestampSerialized.length);
		
		
		this.bidID = CommonUtils.fromByteArrayToLong(bidIDSerialized);
		this.bidderUserClientID = CommonUtils.fromByteArrayToString(bidderUserClientIDSerialized);
		this.bidValue = CommonUtils.fromByteArrayToDouble(bidValueSerialized);
		this.bidTimestamp = CommonUtils.fromByteArrayToLong(bidTimestampSerialized);
		
	}
	
}