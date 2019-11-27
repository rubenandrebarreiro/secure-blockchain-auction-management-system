package main.java.resources.user;

public class UserAuctionInfo {

	private User user;
	private String description;
	
	public UserAuctionInfo(User currentUser, String description) {
		this.user = currentUser;
		this.description = description;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User client) {
		this.user = client;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
}
