package main.java.sys;

public enum SSLSocketAuctionOperation {

	OPEN_AUCTION, 
	CLOSE_AUCTION,
	ADD_BID,
	LIST_ALL_AUCTIONS,
	LIST_OPENED_AUCTIONS,
	LIST_CLOSED_AUCTIONS,
	LIST_ALL_AUCTIONS_BY_OWNER,
	LIST_OPENED_AUCTIONS_BY_OWNER,
	LIST_CLOSED_AUCTIONS_BY_OWNER,
	LIST_ALL_AUCTIONS_BY_ID,
	LIST_OPENED_AUCTIONS_BY_ID,
	LIST_CLOSED_AUCTIONS_BY_ID,
	LIST_BIDS_OF_ALL_AUCTIONS_BY_AUCTION_ID,
	LIST_BIDS_OF_OPENED_AUCTIONS_BY_AUCTION_ID,
	LIST_BIDS_OF_CLOSED_AUCTIONS_BY_AUCTION_ID,
	LIST_BIDS_OF_ALL_AUCTIONS_BY_AUCTION_ID_AND_CLIENT_ID,
	LIST_BIDS_OF_OPENED_AUCTIONS_BY_AUCTION_ID_AND_CLIENT_ID,
	LIST_BIDS_OF_CLOSED_AUCTIONS_BY_AUCTION_ID_AND_CLIENT_ID
}
