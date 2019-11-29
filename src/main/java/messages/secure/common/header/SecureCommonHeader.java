package main.java.messages.secure.common.header;

public abstract class SecureCommonHeader {
	
	private byte versionID;
	
	private byte messageType;
	
	private byte[] secureCommonHeaderSerialized;
	
	public SecureCommonHeader(byte versionID, byte messageType) {
		
		this.versionID = versionID;
		this.messageType = messageType;
		this.secureCommonHeaderSerialized = new byte[] { versionID, messageType };
		
	}
	
	public SecureCommonHeader(byte[] secureCommonHeaderSerialized) {
		
		this.secureCommonHeaderSerialized = secureCommonHeaderSerialized;
		this.versionID = secureCommonHeaderSerialized[0];
		this.messageType = secureCommonHeaderSerialized[1];
		
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
	
	public byte[] getSecureCommonHeaderSerialized() {
		return this.secureCommonHeaderSerialized;
	}
	
	public void setSecureCommonHeaderSerialized(byte[] secureCommonHeaderSerialized) {
		this.secureCommonHeaderSerialized = secureCommonHeaderSerialized;
	}
	
}