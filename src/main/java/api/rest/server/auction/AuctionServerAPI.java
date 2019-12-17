package main.java.api.rest.server.auction;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SignatureException;
import java.sql.SQLException;

import javax.crypto.NoSuchPaddingException;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;

@Path("/auction-server")
public interface AuctionServerAPI {

	@POST
	@Path("/open-auction")
	@Consumes(MediaType.APPLICATION_JSON)
	public HttpResponse createAuction(String clientAuctionInformation) throws UnsupportedEncodingException, ClientProtocolException, IOException;

	@PUT
	@Path("/close-auction/{auction-id}")
	@Produces(MediaType.APPLICATION_JSON)
	public HttpResponse closeAuction(@PathParam("auction-id") String openedAuctionID)
		   throws SQLException;
	
	@POST
    @Path("/add-bid-to-opened-auction/{auction-id}")
    @Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
    public String addBidToOpenedProductAuction
    	 (@PathParam("auction-id") String openedAuctionID, String userBidInfo, byte[] ivBytes)
    	   throws SQLException, NoSuchAlgorithmException, InvalidKeyException, SignatureException, NoSuchProviderException, NoSuchPaddingException, InvalidAlgorithmParameterException, ClientProtocolException, IOException;
	
	@GET
	@Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
 	public HttpResponse listAllProductsAuctions() throws SQLException;
	
	@GET
	@Path("/opened")
    @Produces(MediaType.APPLICATION_JSON)
 	public HttpResponse listOpenedProductsAuctions() throws SQLException;
	
	@GET
	@Path("/closed")
    @Produces(MediaType.APPLICATION_JSON)
 	public HttpResponse listClosedProductsAuctions() throws SQLException;;
	
	@GET
	@Path("/all/by-product-owner-user/{product-owner-user-client-id}")
    @Produces(MediaType.APPLICATION_JSON)
 	public HttpResponse listAllProductsAuctionsByProductOwnerUserClient(@PathParam("product-owner-user-client-id") String productOwnerUserClientID) throws SQLException;
	
	@GET
	@Path("/opened/by-product-owner-user/{product-owner-user-client-id}")
    @Produces(MediaType.APPLICATION_JSON)
 	public HttpResponse listOpenedProductsAuctionsByProductOwnerUserClient(@PathParam("product-owner-user-client-id") String productOwnerUserClientID) throws SQLException;
	
	@GET
	@Path("/closed/by-product-owner-user/{product-owner-user-client-id}")
    @Produces(MediaType.APPLICATION_JSON)
 	public HttpResponse listClosedProductsAuctionsByProductOwnerUserClient(@PathParam("product-owner-user-client-id") String productOwnerUserClientID) throws SQLException;
	
	@GET
	@Path("/all/{auction-id}")
    @Produces(MediaType.APPLICATION_JSON)
 	public HttpResponse findProductAuctionByID(@PathParam("auction-id") String auctionID) throws SQLException;
	
	@GET
	@Path("/opened/{auction-id}")
    @Produces(MediaType.APPLICATION_JSON)
 	public HttpResponse findOpenedProductAuctionByID(@PathParam("auction-id") String openedAuctionID) throws SQLException;
	
	@GET
	@Path("/closed/{auction-id}")
    @Produces(MediaType.APPLICATION_JSON)
 	public HttpResponse findClosedProductAuctionByID(@PathParam("auction-id") String closedAuctionID) throws SQLException;
	
	@GET
	@Path("/all/{auction-id}/bids")
    @Produces(MediaType.APPLICATION_JSON)
 	public HttpResponse listAllBidsOfProductAuctionByID(@PathParam("auction-id") String auctionID) throws SQLException;
	
	@GET
	@Path("/opened/{auction-id}/bids")
    @Produces(MediaType.APPLICATION_JSON)
 	public HttpResponse listAllBidsOfOpenedProductAuctionByID(@PathParam("auction-id") String openedAuctionID) throws SQLException;
	
	@GET
	@Path("/closed/{auction-id}/bids")
    @Produces(MediaType.APPLICATION_JSON)
 	public HttpResponse listAllBidsOfClosedProductAuctionByID(@PathParam("auction-id") String closedAuctionID) throws SQLException;
	
	@GET
	@Path("/all/{auction-id}/bids/{bidder-user-client-id}")
    @Produces(MediaType.APPLICATION_JSON)
 	public HttpResponse listAllBidsMadeByBidderUserClientInAllProductAuctionByID(@PathParam("auction-id") String auctionID,
 																		  @PathParam("bidder-user-client-id") String bidderUserClientID) throws SQLException;
	@GET
	@Path("/opened/{auction-id}/bids/{bidder-user-client-id}")
    @Produces(MediaType.APPLICATION_JSON)
 	public HttpResponse listAllBidsMadeByBidderUserClientInOpenedProductAuctionByID(@PathParam("auction-id") String openedAuctionID,
 																				@PathParam("bidder-user-client-id") String bidderUserClientID) throws SQLException;
	@GET
	@Path("/closed/{auction-id}/bids/{bidder-user-client-id}")
    @Produces(MediaType.APPLICATION_JSON)
 	public HttpResponse listAllBidsMadeByBidderUserClientInClosedProductAuctionByID(@PathParam("auction-id") String closedAuctionID,
 																				@PathParam("bidder-user-client-id") String bidderUserClientID) throws SQLException;
	@GET
	@Path("/bids/all/{bidder-user-client-id}")
    @Produces(MediaType.APPLICATION_JSON)
	HttpResponse listAllBidsMadeByBidderUserClientID(String bidderUserClientID) throws SQLException;
	
	@GET
	@Path("/bids/opened/{bidder-user-client-id}")
    @Produces(MediaType.APPLICATION_JSON)
	HttpResponse listOpenedBidsMadeByBidderUserClientID(String bidderUserClientID) throws SQLException;
	
	@GET
	@Path("/bids/closed/{bidder-user-client-id}")
    @Produces(MediaType.APPLICATION_JSON)
	HttpResponse listClosedBidsMadeByBidderUserClientID(String bidderUserClientID) throws SQLException;

	@GET
	@Path("/outcome/all/{bidder-user-client-id}")
    @Produces(MediaType.APPLICATION_JSON)
	HttpResponse checkOutcomeAllAuctions(String bidderUserClientID) throws SQLException;
	
	@GET
	@Path("/outcome/opened/{bidder-user-client-id}")
    @Produces(MediaType.APPLICATION_JSON)
	HttpResponse checkOutcomeOpenedAuctions(String bidderUserClientID) throws SQLException;
	
	@GET
	@Path("/outcome/closed/{bidder-user-client-id}")
    @Produces(MediaType.APPLICATION_JSON)
	HttpResponse checkOutcomeClosedAuctions(String bidderUserClientID) throws SQLException;
	
	@GET
	@Path("/outcome/all/{auction-id}/{bidder-user-client-id}")
    @Produces(MediaType.APPLICATION_JSON)
	HttpResponse checkOutcomeAllAuctionsByAuctionID(String bidderUserClientID, String auctionID) throws SQLException;
	
	@GET
	@Path("/outcome/opened/{auction-id}/{bidder-user-client-id}")
    @Produces(MediaType.APPLICATION_JSON)
	HttpResponse checkOutcomeOpenedAuctionsByAuctionID(String bidderUserClientID, String auctionID) throws SQLException;
	
	@GET
	@Path("/outcome/closed/{auction-id}/{bidder-user-client-id}")
    @Produces(MediaType.APPLICATION_JSON)
	HttpResponse checkOutcomeClosedAuctionsByAuctionID(String bidderUserClientID, String auctionID) throws SQLException;
	
}
