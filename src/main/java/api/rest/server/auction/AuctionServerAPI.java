package main.java.api.rest.server.auction;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

import org.apache.http.client.ClientProtocolException;

@Path("/auction-server")
public interface AuctionServerAPI {

	@POST
	@Path("/open-auction")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createAuction(String clientAuctionInformation) throws UnsupportedEncodingException, ClientProtocolException, IOException;

}
