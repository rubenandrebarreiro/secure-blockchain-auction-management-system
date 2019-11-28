package main.java.resources.user;

import java.util.Map;

public class UserAuctionInfo {

	private User user;
	private String description;
	private byte bidType;
	private double currentBidValue;
	private double minAmountBidValue;
	private double maxAmountBidValue;
	private Map<String, Integer> numAuctionBidsForEachUserClient;
	private int numMaxAuctionBidsAllowed;
	private long auctionTimestampLimit;

	public UserAuctionInfo(User user, String description, byte bidType, double currentBidValue,
			double minAmountBidValue, double maxAmountBidValue, Map<String, Integer> numAuctionBidsForEachUserClient,
			int numMaxAuctionBidsAllowed, long auctionTimestampLimit) {
		this.user = user;
		this.description = description;
		this.bidType = bidType;
		this.currentBidValue = currentBidValue;
		this.minAmountBidValue = minAmountBidValue;
		this.maxAmountBidValue = maxAmountBidValue;
		this.numAuctionBidsForEachUserClient = numAuctionBidsForEachUserClient;
		this.numMaxAuctionBidsAllowed = numMaxAuctionBidsAllowed;
		this.auctionTimestampLimit = auctionTimestampLimit;
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

	public byte getBidType() {
		return bidType;
	}

	public void setBitType(byte bidType) {
		this.bidType = bidType;
	}

	public double getCurrentBidValue() {
		return currentBidValue;
	}

	public void setCurrentBidValue(double currentBidValue) {
		this.currentBidValue = currentBidValue;
	}

	public double getMinAmountBidValue() {
		return minAmountBidValue;
	}

	public void setMinAmountBidValue(double minAmountBidValue) {
		this.minAmountBidValue = minAmountBidValue;
	}

	public double getMaxAmountBidValue() {
		return maxAmountBidValue;
	}

	public void setMaxAmountBidValue(double maxAmountBidValue) {
		this.maxAmountBidValue = maxAmountBidValue;
	}

	public Map<String, Integer> getNumAuctionBidsForEachUserClient() {
		return numAuctionBidsForEachUserClient;
	}

	public void setNumAuctionBidsForEachUserClient(Map<String, Integer> numAuctionBidsForEachUserClient) {
		this.numAuctionBidsForEachUserClient = numAuctionBidsForEachUserClient;
	}

	public int getNumMaxAuctionBidsAllowed() {
		return numMaxAuctionBidsAllowed;
	}

	public void setNumMaxAuctionBidsAllowed(int numMaxAuctionBidsAllowed) {
		this.numMaxAuctionBidsAllowed = numMaxAuctionBidsAllowed;
	}

	public long getAuctionTimestampLimit() {
		return auctionTimestampLimit;
	}

	public void setAuctionTimestampLimit(long auctionTimestampLimit) {
		this.auctionTimestampLimit = auctionTimestampLimit;
	}

	public void setBidType(byte bidType) {
		this.bidType = bidType;
	}

}
