package main.java.sys.rest.server.auction.messageTypes;

public class MessagePacketServerToClient {

	private MessagePacketServerToClientTypes type;
	
	private String message;
	
	/**
	 * Envelope that contains a message and an identifier
	 * @param type of message inside
	 * @param message contents in JSON
	 */
	public MessagePacketServerToClient(MessagePacketServerToClientTypes type, String message) {
		this.type = type;
		this.message = message;
	}

	public MessagePacketServerToClientTypes getType() {
		return type;
	}

	public void setType(MessagePacketServerToClientTypes type) {
		this.type = type;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}	
}
