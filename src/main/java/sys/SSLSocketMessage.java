package main.java.sys;

import java.io.Serializable;
import java.util.HashMap;

public class SSLSocketMessage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5475198985218772861L;
	
	private SSLSocketAuctionOperation operation;
	
	private HashMap<String, String> paramsMap;
	
	private String body;
	
	public SSLSocketMessage(SSLSocketAuctionOperation operation,
							HashMap<String, String> paramsMap,
							String body) {
		this.operation = operation;
		this.paramsMap = paramsMap;
		this.body = body;
	}

	public SSLSocketAuctionOperation getOperation() {
		return operation;
	}

	public void setOperation(SSLSocketAuctionOperation operation) {
		this.operation = operation;
	}

	public HashMap<String, String> getParamsMap() {
		return paramsMap;
	}

	public void setParamsMap(HashMap<String, String> paramsMap) {
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
	
	
	
}
