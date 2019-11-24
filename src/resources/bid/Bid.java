package resources.bid;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "bids")
public class Bid {
	
	@DatabaseField( id = true )
	private long bidID;
	@DatabaseField
	private String bidderUserClientID;
	@DatabaseField
	private double bidValue;
	@DatabaseField
	private long bidTimestamp;
	
	public Bid() {
		// TODO Auto-generated constructor stub
	}
	
	public Bid(long bidID, String bidderUserClientID, double bidValue) {
		this.bidID = bidID;
		this.bidderUserClientID = bidderUserClientID;
		this.bidValue = bidValue;
		this.bidTimestamp = System.currentTimeMillis();
	}

	public long getBidID() {
		return this.bidID;
	}

	public void setBidID(long bidID) {
		this.bidID = bidID;
	}

	public String getBidderUserClientID() {
		return this.bidderUserClientID;
	}

	public void setBidderUserClientID(String bidderUserClientID) {
		this.bidderUserClientID = bidderUserClientID;
	}

	public double getBidValue() {
		return this.bidValue;
	}

	public void setBidValue(double bidValue) {
		this.bidValue = bidValue;
	}
	
	public long getBidTimestamp() {
		return this.bidTimestamp;
	}

	public void setBidTimestamp(long bidTimestamp) {
		this.bidTimestamp = bidTimestamp;
	}
}