package messages.secure.components.header;

public class MessageHeader {
	
	private byte versionID;
	
	private String auctionID;
	
	private byte messageType;
	
	
	public MessageHeader(byte versionID, String auctionID, byte messageType) {
		this.versionID = versionID;
		this.auctionID = auctionID;
		this.messageType = messageType;
	}
	
	public byte getVersionID() {
		return this.versionID;
	}
	
	public void setVersionID(byte versionID) {
		this.versionID = versionID;
	}
	
	public String getAuctionID() {
		return this.auctionID;
	}
	
	public void setAuctionID(String auctionID) {
		this.auctionID = auctionID;
	}
	
	public byte getMessageType() {
		return this.messageType;
	}
	
	public void setMessageType(byte messageType) {
		this.messageType = messageType;
	}
	
}