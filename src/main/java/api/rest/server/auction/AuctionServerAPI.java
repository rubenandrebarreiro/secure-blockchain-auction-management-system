package main.java.api.rest.server.auction;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

import org.apache.http.client.ClientProtocolException;

@Path("/auction-server")
public interface AuctionServerAPI {

	// Receives complete user information (to save on server), some auction information (what info?)
	// Auction name, unique identifier, one of timestamps defined by server
	@POST
	@Path("/open-normal-auction")
	@Consumes(MediaType.APPLICATION_JSON)
	public void createAuction(String clientAuctionInformation) throws UnsupportedEncodingException, ClientProtocolException, IOException;
	
	
}
