package main.java.messages.secure.common.header;

import main.java.common.utils.CommonUtils;

public class SecureCommonHeader {
	
	private byte versionID;
	
	private byte messageType;
	
	private long timestamp;
	
	private byte[] secureCommonHeaderSerialized;
	
	private boolean isSecureCommonHeaderSerialized;
	
	
	
	public SecureCommonHeader(byte versionID, byte messageType, long timestamp) {
		
		this.versionID = versionID;
		this.messageType = messageType;
		this.timestamp = timestamp;
		
		this.secureCommonHeaderSerialized = null;
		
		this.isSecureCommonHeaderSerialized = false;
		
	}
	
	public SecureCommonHeader(byte[] secureCommonHeaderSerialized) {
		
		this.secureCommonHeaderSerialized = secureCommonHeaderSerialized;

		this.isSecureCommonHeaderSerialized = true;
		
		this.versionID = ( (byte) 0x00 );
		this.messageType = ( (byte) 0x00 );
		this.timestamp = ( (byte) 0x00 );
		
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
	
	public boolean getIsSecureCommonHeaderSerialized() {
		return this.isSecureCommonHeaderSerialized;
	}
	
	public void setIsSecureCommonHeaderSerialized(boolean isSecureCommonHeaderSerialized) {
		this.isSecureCommonHeaderSerialized = isSecureCommonHeaderSerialized;
	}
	
	
	public void doSerializationOfSecureCommonHeader() {
		
		if(!this.isSecureCommonHeaderSerialized) {
			
			byte[] timestampSerialized = CommonUtils.fromLongToByteArray(timestamp);
			this.secureCommonHeaderSerialized = new byte[] { this.versionID, this.messageType,
		 													 timestampSerialized[0], timestampSerialized[1],
															 timestampSerialized[2], timestampSerialized[3],
															 timestampSerialized[4], timestampSerialized[5],
															 timestampSerialized[6], timestampSerialized[7] };
			
			this.setIsSecureCommonHeaderSerialized(true);
			
		}
		
	}
	
	public void undoSerializationOfSecureCommonHeader() {
		
		if(this.isSecureCommonHeaderSerialized) {
			
			this.versionID = this.secureCommonHeaderSerialized[0];
			this.messageType = this.secureCommonHeaderSerialized[1];
			this.timestamp = CommonUtils.fromByteArrayToLong
							(new byte[] { this.secureCommonHeaderSerialized[2], this.secureCommonHeaderSerialized[3],
										  this.secureCommonHeaderSerialized[4], this.secureCommonHeaderSerialized[5],
										  this.secureCommonHeaderSerialized[6], this.secureCommonHeaderSerialized[7],
										  this.secureCommonHeaderSerialized[8], this.secureCommonHeaderSerialized[9] });
			
			this.setIsSecureCommonHeaderSerialized(false);
			
		}
		
	}
	
}