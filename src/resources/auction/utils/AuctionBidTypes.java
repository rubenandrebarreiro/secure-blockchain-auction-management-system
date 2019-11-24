package resources.auction.utils;

public enum AuctionBidTypes {

	
	// Enumerations of Types of Auction's Bids
	
	/**
	 * The available Types of Bids allowed to an Auction
	 */
	NORMAL_BIDS( (byte) 1 ),
	MIN_INITIAL_VALUE_BID( (byte) 2 ),
	MIN_AMOUNT_VALUE_BID( (byte) 3 ),
	MAX_AMOUNT_VALUE_BID( (byte) 4 ),
	MIN_MAX_AMOUNT_VALUE_BID( (byte) 5 ),
	LIMITED_SET_CLIENT_BIDDERS( (byte) 6 ),
	LIMITED_NUMBER_BIDS_FOR_EACH_CLIENT( (byte) 7 ),
	LIMITED_NUMBER_BIDS( (byte) 8 ),
	LIMITED_TIME_BIDS( (byte) 9 );
	
	
	// Global Instance Variables
	
	/**
	 * The Type of the Bids allowed to an Auction
	 */
	public byte auctionBidsType;
	
	
	// Constructors:
	
	/**
	 * Constructor #1:
	 * - The constructor for the Type of the Bid
	 *   allowed to an Auction;
	 * 
	 * @param auctionBidsType the Type of Bids
	 *        allowed to an Auction
	 */
	private AuctionBidTypes(byte auctionBidsType) {
		this.auctionBidsType = auctionBidsType;
	}
	
	public byte getBidsType() {
		return this.auctionBidsType;
	}
}