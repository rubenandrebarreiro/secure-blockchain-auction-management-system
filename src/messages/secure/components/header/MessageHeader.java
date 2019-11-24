package messages.secure.components.header;

public class MessageHeader {
	
	private byte versionID;
	
	private String sessionID;
	
	private byte messageType;
	
	
	public MessageHeader(byte versionID, String sessionID, byte messageType) {
		this.versionID = versionID;
		this.sessionID = sessionID;
		this.messageType = messageType;
	}
	
	public byte getVersionID() {
		return this.versionID;
	}
	
	public void setVersionID(byte versionID) {
		this.versionID = versionID;
	}
	
	public String sessionID() {
		return this.sessionID;
	}
	
	public void setSessionID(String sessionID) {
		this.sessionID = sessionID;
	}
	
	public byte getMessageType() {
		return this.messageType;
	}
	
	public void setMessageType(byte messageType) {
		this.messageType = messageType;
	}
	
}