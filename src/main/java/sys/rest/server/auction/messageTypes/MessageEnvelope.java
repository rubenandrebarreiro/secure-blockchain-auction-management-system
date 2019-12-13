package main.java.sys.rest.server.auction.messageTypes;

public class MessageEnvelope {

	private MessageEnvelopeTypes type;
	
	private String message;
	
	/**
	 * Envelope that contains a message and an identifier
	 * @param type of message inside
	 * @param message contents in JSON
	 */
	public MessageEnvelope(MessageEnvelopeTypes type, String message) {
		this.type = type;
		this.message = message;
	}

	public MessageEnvelopeTypes getType() {
		return type;
	}

	public void setType(MessageEnvelopeTypes type) {
		this.type = type;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}	
}
