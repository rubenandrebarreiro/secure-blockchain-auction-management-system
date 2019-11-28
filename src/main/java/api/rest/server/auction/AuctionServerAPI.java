package main.java.api.rest.server.auction;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

import org.apache.http.client.ClientProtocolException;

@Path("/auction-server")
public interface AuctionServerAPI {

	@POST
	@Path("/open-auction")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createAuction(String clientAuctionInformation) throws UnsupportedEncodingException, ClientProtocolException, IOException;

	@PUT
	@Path("/close-auction/{auction-id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response closeAuction(@PathParam("auction-id") String openedAuctionID)
		   throws SQLException;
	
	@POST
    @Path("/add-bid-to-opened-auction/{auction-id}")
    @Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
    public Response addBidToOpenedProductAuction
    	 (@PathParam("auction-id") String openedAuctionID, String bidForAuctionJSONString)
    	   throws SQLException;
	
	@GET
	@Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
 	public Response listAllProductsAuctions() throws SQLException;
	
	@GET
	@Path("/opened")
    @Produces(MediaType.APPLICATION_JSON)
 	public Response listOpenedProductsAuctions() throws SQLException;
	
	@GET
	@Path("/closed")
    @Produces(MediaType.APPLICATION_JSON)
 	public Response listClosedProductsAuctions() throws SQLException;;
	
	@GET
	@Path("/all/by-product-owner-user/{product-owner-user-client-id}")
    @Produces(MediaType.APPLICATION_JSON)
 	public Response listAllProductsAuctionsByProductOwnerUserClient(@PathParam("product-owner-user-client-id") String productOwnerUserClientID) throws SQLException;
	
	@GET
	@Path("/opened/by-product-owner-user/{product-owner-user-client-id}")
    @Produces(MediaType.APPLICATION_JSON)
 	public Response listOpenedProductsAuctionsByProductOwnerUserClient(@PathParam("product-owner-user-client-id") String productOwnerUserClientID) throws SQLException;
	
	@GET
	@Path("/closed/by-product-owner-user/{product-owner-user-client-id}")
    @Produces(MediaType.APPLICATION_JSON)
 	public Response listClosedProductsAuctionsByProductOwnerUserClient(@PathParam("product-owner-user-client-id") String productOwnerUserClientID) throws SQLException;
	
	@GET
	@Path("/all/{auction-id}")
    @Produces(MediaType.APPLICATION_JSON)
 	public Response findProductAuctionByID(@PathParam("auction-id") String auctionID) throws SQLException;
	
	@GET
	@Path("/opened/{auction-id}")
    @Produces(MediaType.APPLICATION_JSON)
 	public Response findOpenedProductAuctionByID(@PathParam("auction-id") String openedAuctionID) throws SQLException;
	
	@GET
	@Path("/closed/{auction-id}")
    @Produces(MediaType.APPLICATION_JSON)
 	public Response findClosedProductAuctionByID(@PathParam("auction-id") String closedAuctionID) throws SQLException;
	
	@GET
	@Path("/all/{auction-id}/bids")
    @Produces(MediaType.APPLICATION_JSON)
 	public Response listAllBidsOfProductAuctionByID(@PathParam("auction-id") String auctionID) throws SQLException;
	
	@GET
	@Path("/opened/{auction-id}/bids")
    @Produces(MediaType.APPLICATION_JSON)
 	public Response listAllBidsOfOpenedProductAuctionByID(@PathParam("auction-id") String openedAuctionID) throws SQLException;
	
	@GET
	@Path("/closed/{auction-id}/bids")
    @Produces(MediaType.APPLICATION_JSON)
 	public Response listAllBidsOfClosedProductAuctionByID(@PathParam("auction-id") String closedAuctionID) throws SQLException;
	
	@GET
	@Path("/all/{auction-id}/bids/{bidder-user-client-id}")
    @Produces(MediaType.APPLICATION_JSON)
 	public Response listAllBidsMadeByBidderUserClientInProductAuctionByID(@PathParam("auction-id") String auctionID,
 																		  @PathParam("bidder-user-client-id") String bidderUserClientID) throws SQLException;
	@GET
	@Path("/opened/{auction-id}/bids/{bidder-user-client-id}")
    @Produces(MediaType.APPLICATION_JSON)
 	public Response listAllBidsMadeByBidderUserClientInOpenedProductAuctionByID(@PathParam("auction-id") String openedAuctionID,
 																				@PathParam("bidder-user-client-id") String bidderUserClientID) throws SQLException;
	
	@GET
	@Path("/closed/{auction-id}/bids/{bidder-user-client-id}")
    @Produces(MediaType.APPLICATION_JSON)
 	public Response listAllBidsMadeByBidderUserClientInClosedProductAuctionByID(@PathParam("auction-id") String closedAuctionID,
 																				@PathParam("bidder-user-client-id") String bidderUserClientID) throws SQLException;
	
}
