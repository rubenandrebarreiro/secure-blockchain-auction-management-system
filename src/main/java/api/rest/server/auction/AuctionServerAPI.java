package main.java.api.rest.server.auction;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

@Path("/auction-server")
public interface AuctionServerAPI {

	// Receives complete user information (to save on server), some auction information (what info?)
	// Auction name, unique identifier, one of timestamps defined by server
	@POST
	@Path("/create-auction")
	@Consumes(MediaType.APPLICATION_JSON)
	public void createAuction(String clientAuctionInformation);
	
}
