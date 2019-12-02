package main.java.resources.user;

public class UserBidInfo {

	private User user;
	
	private double bidValue;
	
	public UserBidInfo(User user, double bidValue) {
		this.user = user;
		this.bidValue = bidValue;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public double getBidValue() {
		return bidValue;
	}

	public void setBidValue(double bidValue) {
		this.bidValue = bidValue;
	}

	@Override
	public String toString() {
		return "UserBidInfo [user=" + user + ", bidValue=" + bidValue + "]";
	}
	
	
	
}
