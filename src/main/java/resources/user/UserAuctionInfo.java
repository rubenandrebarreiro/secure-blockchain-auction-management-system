package main.java.resources.user;

import main.java.sys.rest.client.Client;

public class UserAuctionInfo {

	private Client client;
	private String description;
	
	public UserAuctionInfo(Client client, String description) {
		this.client = client;
		this.description = description;
	}
	
	public Client getClient() {
		return client;
	}
	
	public void setClient(Client client) {
		this.client = client;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
}
