package main.java.messages.secure.common.header;

import main.java.common.utils.CommonUtils;

public abstract class SecureCommonHeader {
	
	private byte versionID;
	
	private byte messageType;
	
	private long timestamp;
	
	private byte[] secureCommonHeaderSerialized;
	
	public SecureCommonHeader(byte versionID, byte messageType, long timestamp) {
		
		this.versionID = versionID;
		this.messageType = messageType;
		this.timestamp = timestamp;
		
		byte[] timestampSerialized = CommonUtils.fromLongToByteArray(timestamp);
		this.secureCommonHeaderSerialized = new byte[] { versionID, messageType,
	 													 timestampSerialized[0], timestampSerialized[1],
														 timestampSerialized[2], timestampSerialized[3],
														 timestampSerialized[4], timestampSerialized[5],
														 timestampSerialized[6], timestampSerialized[7] };
		
	}
	
	public SecureCommonHeader(byte[] secureCommonHeaderSerialized) {
		
		this.secureCommonHeaderSerialized = secureCommonHeaderSerialized;

		this.versionID = secureCommonHeaderSerialized[0];
		this.messageType = secureCommonHeaderSerialized[1];
		this.timestamp = CommonUtils.fromByteArrayToLong
						(new byte[] { secureCommonHeaderSerialized[2], secureCommonHeaderSerialized[3],
						 			  secureCommonHeaderSerialized[4], secureCommonHeaderSerialized[5],
									  secureCommonHeaderSerialized[6], secureCommonHeaderSerialized[7],
									  secureCommonHeaderSerialized[8], secureCommonHeaderSerialized[9] });
		
	}
	
	public byte getVersionID() {
		return this.versionID;
	}
	
	public void setVersionID(byte versionID) {
		this.versionID = versionID;
	}
	
	public byte getMessageType() {
		return this.messageType;
	}
	
	public void setMessageType(byte messageType) {
		this.messageType = messageType;
	}
	
	public long getTimestamp() {
		return this.timestamp;
	}
	
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	
	public byte[] getSecureCommonHeaderSerialized() {
		return this.secureCommonHeaderSerialized;
	}
	
	public void setSecureCommonHeaderSerialized(byte[] secureCommonHeaderSerialized) {
		this.secureCommonHeaderSerialized = secureCommonHeaderSerialized;
	}
	
}