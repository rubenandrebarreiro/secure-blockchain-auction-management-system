package resources.bid;

import common.utils.CommonUtils;

public class Bid {

	private long bidID;

	private String bidderUserClientID;

	private double bidValue;

	private long bidTimestamp;

	private byte[] serializedBytes;


	public Bid(long bidID, String bidderUserClientID, double bidValue) {

		this.bidID = bidID;
		this.bidderUserClientID = bidderUserClientID;
		this.bidValue = bidValue;
		this.bidTimestamp = System.currentTimeMillis();

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
	
	public byte[] getSerializedBytes() {
		return this.serializedBytes;
	}

	public void setSerializedBytes(byte[] serializedBytes) {
		this.serializedBytes = serializedBytes;
	}
	
	public void doSerialization() {
		
		int sizeOfSerializedBytes = ( CommonUtils.LONG_IN_BYTES_LENGTH + this.bidderUserClientID.length()
		                            + CommonUtils.DOUBLE_IN_BYTES_LENGTH + CommonUtils.LONG_IN_BYTES_LENGTH );
		
		this.serializedBytes = new byte[sizeOfSerializedBytes];
		
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
		System.arraycopy(bidIDSerialized, 0, this.serializedBytes, serializationOffset, bidIDSerialized.length);
		serializationOffset += bidIDSerialized.length;
		
		// Fills the byte array of the Bid serialized with its User/Client Bidder's ID,
		// From the position corresponding to the length of Bid's ID to
		// the corresponding of the length of Bid's User/Client Bidder's ID
		System.arraycopy(bidderUserClientIDSerialized, 0, this.serializedBytes, serializationOffset, bidderUserClientIDSerialized.length);
		serializationOffset += bidderUserClientIDSerialized.length;

		// Fills the byte array of the Bid serialized with its Value Amount,
		// From the position corresponding to the length of Bid's User/Client Bidder's ID to
		// the corresponding of the length of Bid's Value Amount
		System.arraycopy(bidValueSerialized, 0, this.serializedBytes, serializationOffset, bidValueSerialized.length);
		serializationOffset += bidValueSerialized.length;

		// Fills the byte array of the Bid serialized with its Timestampo,
		// From the position corresponding to the length of Bid's Value Amount to
		// the corresponding of the length of Bid's Timestamp
		System.arraycopy(bidTimestampSerialized, 0, this.serializedBytes, serializationOffset, bidTimestampSerialized.length);
		
	}

	public void undoSerialization() {
		
	}
}