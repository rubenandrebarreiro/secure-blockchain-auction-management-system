package main.java.resources.auction;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import main.java.resources.bid.Bid;

@DatabaseTable(tableName = "auctions")
public class Auction {
		
	@DatabaseField(id = true) private String auctionID;
	@DatabaseField private int auctionSerialNumber;
	@DatabaseField private String auctionDescription;
	@DatabaseField private byte auctionBidType;
	@DatabaseField private double currentBidValue;
	@DatabaseField private double minAmountBidValue;
	@DatabaseField private double maxAmountBidValue;
	@DatabaseField private int numMaxAuctionBidsAllowed;
	@DatabaseField private long auctionTimestampStart;
	@DatabaseField private long auctionTimestampLimit;
	@DatabaseField private boolean auctionIsOpen;
	@DatabaseField private int productID;
	@DatabaseField private String productName;
	@DatabaseField private String productOwnerUserClientID;
	
	// Both set as HashMap because the interface is not serializable!
	@DatabaseField(dataType = DataType.SERIALIZABLE) 
	private HashMap<Long, Bid> auctionBidsMade;
	
	@DatabaseField(dataType = DataType.SERIALIZABLE) 
	private HashMap<String, Integer> numAuctionBidsForEachUserClient;
	
	public Auction() {
		// TODO Auto-generated constructor stub
	}
	
	public Auction(String auctionID, int auctionSerialNumber, String auctionDescription,
			byte auctionBidType, double initialBidValue, 
			double minAmountBidValue, double maxAmountBidValue,
			HashMap<String, Integer> numAuctionBidsForEachUserClient,
			int numMaxAuctionBidsAllowed, long auctionTimestampLimit,
			int productID, String productName, String productOwnerUserClientID) {

		this.auctionID = auctionID;
		this.auctionSerialNumber = auctionSerialNumber;
		this.auctionDescription = auctionDescription;

		this.auctionBidType = auctionBidType;

		switch(this.auctionBidType) {
		case 1:
			this.currentBidValue = 0.0;
			this.minAmountBidValue = 0.0;
			this.maxAmountBidValue = 0.0;
			this.numAuctionBidsForEachUserClient = null;
			this.numMaxAuctionBidsAllowed = Integer.MAX_VALUE;
			this.auctionTimestampLimit = -1L;

			break;

		case 2:
			this.currentBidValue = initialBidValue;
			this.minAmountBidValue = 0.0;
			this.maxAmountBidValue = 0.0;
			this.numAuctionBidsForEachUserClient = null;
			this.numMaxAuctionBidsAllowed = Integer.MAX_VALUE;
			this.auctionTimestampLimit = -1L;

			break;

		case 3:
			this.currentBidValue = 0.0;
			this.minAmountBidValue = minAmountBidValue;
			this.maxAmountBidValue = 0.0;
			this.numAuctionBidsForEachUserClient = null;
			this.numMaxAuctionBidsAllowed = Integer.MAX_VALUE;
			this.auctionTimestampLimit = -1L;

			break;

		case 4:
			this.currentBidValue = 0.0;
			this.minAmountBidValue = 0.0;
			this.maxAmountBidValue = 0.0;
			this.numAuctionBidsForEachUserClient = null;
			this.numMaxAuctionBidsAllowed = Integer.MAX_VALUE;
			this.auctionTimestampLimit = -1L;

			break;

		case 5:
			this.currentBidValue = 0.0;
			this.minAmountBidValue = minAmountBidValue;
			this.maxAmountBidValue = maxAmountBidValue;
			this.numAuctionBidsForEachUserClient = null;
			this.numMaxAuctionBidsAllowed = Integer.MAX_VALUE;
			this.auctionTimestampLimit = -1L;

			break;

		case 6:
			this.currentBidValue = 0.0;
			this.minAmountBidValue = 0.0;
			this.maxAmountBidValue = 0.0;
			this.numAuctionBidsForEachUserClient = numAuctionBidsForEachUserClient;
			this.numMaxAuctionBidsAllowed = Integer.MAX_VALUE;
			this.auctionTimestampLimit = -1L;

			break;

		case 7:
			this.currentBidValue = 0.0;
			this.minAmountBidValue = 0.0;
			this.maxAmountBidValue = 0.0;
			this.numAuctionBidsForEachUserClient = numAuctionBidsForEachUserClient;
			this.numMaxAuctionBidsAllowed = Integer.MAX_VALUE;
			this.auctionTimestampLimit = -1L;

			break;

		case 8:
			this.currentBidValue = 0.0;
			this.minAmountBidValue = 0.0;
			this.maxAmountBidValue = 0.0;
			this.numAuctionBidsForEachUserClient = numAuctionBidsForEachUserClient;
			this.numMaxAuctionBidsAllowed = numMaxAuctionBidsAllowed;
			this.auctionTimestampLimit = -1L;

			break;

		case 9:
			this.currentBidValue = 0.0;
			this.minAmountBidValue = 0.0;
			this.maxAmountBidValue = 0.0;
			this.numAuctionBidsForEachUserClient = numAuctionBidsForEachUserClient;
			this.numMaxAuctionBidsAllowed = Integer.MAX_VALUE;
			this.auctionTimestampLimit = auctionTimestampLimit;

			break;

		default:
			break;
		}

		this.auctionTimestampStart = System.currentTimeMillis();

		this.auctionBidsMade = new HashMap<Long, Bid>();

		this.auctionIsOpen = true;

		this.productID = productID;
		this.productName = productName;
		this.productOwnerUserClientID = productOwnerUserClientID;

	}

	
	public String getAuctionID() {
		return this.auctionID;
	}

	public void setAuctionID(String auctionID) {
		this.auctionID = auctionID;
	}

	public int getAuctionSerial() {
		return this.auctionSerialNumber;
	}

	public void setAuctionSerial(int auctionSerial) {
		this.auctionSerialNumber = auctionSerial;
	}

	public String getAuctionDescription() {
		return this.auctionDescription;
	}

	public void setAuctionDescription(String auctionDescription) {
		this.auctionDescription = auctionDescription;
	}
	
	
	
	public byte getAuctionBidType() {
		return this.auctionBidType;
	}

	public void setAuctionBidType(byte auctionBidType) {
		this.auctionBidType = auctionBidType;
	}
	
	public double getCurrentBidValue() {
		return this.currentBidValue;
	}

	public void setCurrentBidValue(double currentBidValue) {
		this.currentBidValue = currentBidValue;
	}
	
	public double getMinAmountBidValue() {
		return this.minAmountBidValue;
	}

	public void setMinAmountBidValue(double minAmountBidValue) {
		this.minAmountBidValue = minAmountBidValue;
	}
	
	public double getMaxAmountBidValue() {
		return this.maxAmountBidValue;
	}

	public void setMaxAmountBidValue(double maxAmountBidValue) {
		this.maxAmountBidValue = maxAmountBidValue;
	}
	
	public Map<String, Integer> getNumAuctionBidsForEachUserClient() {
		return this.numAuctionBidsForEachUserClient;
	}
	
	public void setNumAuctionBidsForEachUserClient(HashMap<String, Integer> numAuctionBidsForEachUserClient) {
		this.numAuctionBidsForEachUserClient = numAuctionBidsForEachUserClient;
	}
	
	public int getNumMaxAuctionBidsAllowed() {
		return this.numMaxAuctionBidsAllowed;
	}
	
	public void setNumMaxAuctionBidsAllowed(int numMaxAuctionBidsAllowed) {
		this.numMaxAuctionBidsAllowed = numMaxAuctionBidsAllowed;
	}
	
	public long getAuctionTimestampStart() {
		return this.auctionTimestampStart;
	}
	
	public void setAuctionTimestampStart(long auctionTimestampStart) {
		this.auctionTimestampStart = auctionTimestampStart;
	}
	
	public long getAuctionTimestampLimit() {
		return this.auctionTimestampLimit; 
	}
	
	public void setAuctionTimestampLimit(long auctionTimestampLimit) {
		this.auctionTimestampLimit = auctionTimestampLimit;
	}
	
	public boolean verifyIfAuctionTimestampIsValid() {
		return (System.currentTimeMillis() - this.auctionTimestampStart) > this.auctionTimestampLimit;
	}
	
	public Map<Long, Bid> getAuctionBidsMade() {
		return this.auctionBidsMade; 
	}
	
	public void setAuctionBids(HashMap<Long, Bid> auctionBidsMade) {
		this.auctionBidsMade = auctionBidsMade;
	}

	public void addAuctionBid(Bid auctionBid) {
		
		if(this.validateAuctionBid(auctionBid)) {
			this.auctionBidsMade.put(auctionBid.getBidID(), auctionBid); 
		}
		else {
			
			System.err.println("Invalid parameters for the Bid, "
							 + "this Bid will not be accepted!!!");
			
		}
	}
		
	public boolean validateAuctionBid(Bid auctionBid) {
		
		double currentBidValue = this.getCurrentBidValue();
		double newBidValue = auctionBid.getBidValue();
			
		if(currentBidValue >= newBidValue) {
			return false;
		}
			
		switch(this.auctionBidType) {
			
			case 1:
				
				this.currentBidValue = newBidValue;
				return true;
				
			
			case 2:
				
				this.currentBidValue = newBidValue;
				return true;
			
			
			case 3:
				
				if( (newBidValue - currentBidValue) < this.getMinAmountBidValue() ) {
					return false;
				}
				else {
					
					this.currentBidValue = newBidValue;
					
					return true;
				}
			
			
			case 4:

				if( (newBidValue - currentBidValue) > this.getMaxAmountBidValue() ) {
					return false;
				}
				else {
					
					this.currentBidValue = newBidValue;
					
					return true;
				}
			
			
			case 5:
				
				double bidValueAmount = (newBidValue - currentBidValue);

				if( (bidValueAmount < this.getMinAmountBidValue()) ||
					(bidValueAmount	> this.getMaxAmountBidValue()) ) {
					
					return false;
				}
				else {
					
					this.currentBidValue = newBidValue;
					
					return true;
				}
				
				
			case 6:
				
				if( (!this.numAuctionBidsForEachUserClient
						  .containsKey(auctionBid.getBidderUserClientID())) ) {
					
					return false;
				}
				else {
					
					this.currentBidValue = newBidValue;
					
					return true;
				}
			
			
			case 7:
				
				String bidderUserID = auctionBid.getBidderUserClientID();
				
				if( (!this.numAuctionBidsForEachUserClient
						  .containsKey(bidderUserID)) ) {
					
					return false;
				}
				else {
					
					int numBidsAllowedForBidderUser = this.numAuctionBidsForEachUserClient.get(bidderUserID);
					
					int numCurrentBidsForBidderUser = 
							this.auctionBidsMade.entrySet().stream()
								.filter(entry -> entry.getValue().getBidderUserClientID() == bidderUserID)
								.collect(Collectors.toList()).size();
					
					if(numBidsAllowedForBidderUser <= numCurrentBidsForBidderUser) {
					
						return false;
					}
					else {
				
						this.currentBidValue = newBidValue;
						
						return true;
					}
				}
				
			
			case 8:
				
				int numMaxAuctionBidsAllowed = this.getNumMaxAuctionBidsAllowed();
				
				int numAuctionBidsMade = this.auctionBidsMade.size();
				
				if(numMaxAuctionBidsAllowed <= numAuctionBidsMade) {
					
					return false;
				}
				else {
					
					this.currentBidValue = newBidValue;
					
					return true;
				}
			
			
			case 9:
				
				this.currentBidValue = newBidValue;
				return true;
				
			
			default:
				break;
		}

		return false;
	}
	
	public boolean verifyIfAuctionIsOpen() {
		return this.auctionIsOpen;
	}

	public void closeAuction() {
		if(this.auctionIsOpen) {
			this.auctionIsOpen = false;
		}
	}
	
	public int getProductID() {
		return this.productID; 
	}

	public void setProductID(int productID) {
		this.productID = productID; 
	}

	public String getProductName() {
		return this.productName; 
	}

	public void setProductName(String productName) {
		this.productName = productName; 
	}

	public String getProductOwnerUserClientID() {
		return this.productOwnerUserClientID; 
	}

	public void setProductOwnerUserClientID(String productOwnerUserClientID) {
		this.productOwnerUserClientID = productOwnerUserClientID; 
	}	

	@Override
	public String toString() {
		return "Auction [auctionID=" + this.auctionID + ", auctionSerialNumber=" + this.auctionSerialNumber
				+ ", auctionDescription=" + this.auctionDescription + ", auctionBidType=" + this.auctionBidType
				+ ", currentBidValue=" + this.currentBidValue + ", minAmountBidValue=" + this.minAmountBidValue
				+ ", maxAmountBidValue=" + this.maxAmountBidValue + ", numAuctionBidsForEachClient="
				+ this.numAuctionBidsForEachUserClient + ", numMaxAuctionBidsAllowed=" + this.numMaxAuctionBidsAllowed
				+ ", auctionTimestampStart=" + this.auctionTimestampStart + ", auctionTimestampLimit="
				+ this.auctionTimestampLimit + ", auctionBidsMade=" + this.auctionBidsMade + ", auctionIsOpen=" + this.auctionIsOpen
				+ ", productID=" + this.productID + ", productName=" + this.productName + ", productOwnerUserID="
				+ this.productOwnerUserClientID + "]";
	}
	
}