package main.java.sys.rest.server.auction.messageTypes;

import java.io.Serializable;
import java.util.HashMap;

public class MessagePacketClientToServer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5475198985218772861L;
	
	private MessagePacketClientToServerTypes operation;
	
	private HashMap<String, ?> paramsMap;
	
	private String body;
	
	public MessagePacketClientToServer(MessagePacketClientToServerTypes operation,
							HashMap<String, ?> paramsMap,
							String body) {
		this.operation = operation;
		this.paramsMap = paramsMap;
		this.body = body;
	}

	public MessagePacketClientToServerTypes getOperation() {
		return operation;
	}

	public void setOperation(MessagePacketClientToServerTypes operation) {
		this.operation = operation;
	}

	public HashMap<String, ?> getParamsMap() {
		return paramsMap;
	}

	public void setParamsMap(HashMap<String, ?> paramsMap) {
		this.paramsMap = paramsMap;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "SSLSocketMessage [operation=" + operation + ", paramsMap=" + paramsMap + ", body=" + body + "]";
	}
	
	
	
}
