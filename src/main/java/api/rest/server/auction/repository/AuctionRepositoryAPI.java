package main.java.api.rest.server.auction.repository;

import java.sql.SQLException;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

@Path("/products-auctions")
public interface AuctionRepositoryAPI {

	@POST
    @Path("/open-normal-auction")
    @Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
    public Response openNormalAuction(String normalAuctionJSONString)
    	   throws SQLException;
	
	@POST
    @Path("/open-auction-min-initial-bid-value")
    @Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
    public Response openAuctionWithMinimumInitialBidValue(String auctionWithMinInitialBidValueJSONString)
    	   throws SQLException;
	
	@POST
    @Path("/open-auction-min-amount-bid-value")
    @Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
    public Response openAuctionWithMinimumAmountBidValue(String auctionWithMinAmountBidValueJSONString)
    	   throws SQLException;
	
	@POST
    @Path("/open-auction-max-amount-bid-value")
    @Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
    public Response openAuctionWithMaximumAmountBidValue(String auctionWithMaxAmountBidValueJSONString)
    	   throws SQLException;
	
	@POST
    @Path("/open-auction-min-and-max-amount-bid-value")
    @Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
    public Response openAuctionWithMinimumAndMaximumAmountBidValue(String auctionWithMinAndMaxAmountBidValueJSONString)
    	   throws SQLException;
	
	@POST
    @Path("/open-auction-limited-set-user-client-bidders")
    @Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
    public Response openAuctionWithLimitedSetUserClientBidders(String auctionWithLimitedSetUserClientBiddersJSONString)
    	   throws SQLException;
	
	@POST
    @Path("/open-auction-limited-number-bids-for-each-user-client-bidder")
    @Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
    public Response openAuctionWithLimitedNumberBidsForEachUserClientBidder(String auctionWithLimitedNumberBidsForEachUserClientBidderJSONString)
    	   throws SQLException;
	
	@POST
    @Path("/open-auction-limited-number-bids")
    @Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
    public Response openAuctionWithLimitedNumberOfBids(String auctionWithLimitedNumberBidsJSONString)
    	   throws SQLException;
	
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
	
	@GET
	@Path("/bids/all/{bidder-user-client-id}")
    @Produces(MediaType.APPLICATION_JSON)
	public Response listAllBidsMadeByBidderUser(@PathParam("bidder-user-client-id") String bidderUserClientID) throws SQLException;
	
	@GET
	@Path("/bids/opened/{bidder-user-client-id}")
    @Produces(MediaType.APPLICATION_JSON)
	public Response listOpenedBidsMadeByBidderUser(@PathParam("bidder-user-client-id") String bidderUserClientID) throws SQLException;
	
	@GET
	@Path("/bids/closed/{bidder-user-client-id}")
    @Produces(MediaType.APPLICATION_JSON)
	public Response listClosedBidsMadeByBidderUser(@PathParam("bidder-user-client-id") String bidderUserClientID) throws SQLException;
	
	@GET
	@Path("/outcome/all/{bidder-user-client-id}")
    @Produces(MediaType.APPLICATION_JSON)
	public Response checkOutcomeAllAuctions(@PathParam("bidder-user-client-id") String bidderUserClientID) throws SQLException;
	
	@GET
	@Path("/outcome/opened/{bidder-user-client-id}")
    @Produces(MediaType.APPLICATION_JSON)
	public Response checkOutcomeOpenedAuctions(@PathParam("bidder-user-client-id") String bidderUserClientID) throws SQLException;
	
	@GET
	@Path("/outcome/closed/{bidder-user-client-id}")
    @Produces(MediaType.APPLICATION_JSON)
	public Response checkOutcomeClosedAuctions(@PathParam("bidder-user-client-id") String bidderUserClientID) throws SQLException;
	
	@GET
	@Path("/outcome/all/{auction-id}/{bidder-user-client-id}")
    @Produces(MediaType.APPLICATION_JSON)
	public Response checkOutcomeAllAuctionsByAuctionID(@PathParam("bidder-user-client-id") String bidderUserClientID, @PathParam("auction-id") String auctionID) throws SQLException;
	
	@GET
	@Path("/outcome/opened/{auction-id}/{bidder-user-client-id}")
    @Produces(MediaType.APPLICATION_JSON)
	public Response checkOutcomeOpenedAuctionsByAuctionID(@PathParam("bidder-user-client-id") String bidderUserClientID, @PathParam("auction-id") String auctionID) throws SQLException;
	
	@GET
	@Path("/outcome/closed/{auction-id}/{bidder-user-client-id}")
    @Produces(MediaType.APPLICATION_JSON)
	public Response checkOutcomeClosedAuctionsByAuctionID(@PathParam("bidder-user-client-id") String bidderUserClientID, @PathParam("auction-id") String auctionID) throws SQLException;
	
}
