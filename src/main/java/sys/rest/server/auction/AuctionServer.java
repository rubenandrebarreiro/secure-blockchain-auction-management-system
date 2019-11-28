package main.java.sys.rest.server.auction;

import java.io.IOException;
import java.net.URI;
import java.sql.SQLException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.apache.http.client.ClientProtocolException;
import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.Dsl;
import org.asynchttpclient.ListenableFuture;
import org.glassfish.jersey.jdkhttp.JdkHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import com.google.gson.Gson;

import main.java.api.rest.server.auction.AuctionServerAPI;
import main.java.resources.auction.Auction;
import main.java.resources.user.User;
import main.java.resources.user.UserAuctionInfo;

public class AuctionServer implements AuctionServerAPI{

	private static final String CLIENT_SERVER_ADDRESS = "http://localhost:8082";
	private static final String AUCTION_SERVER_REPOSITORY_ADDRESS = "http://localhost:8080/products-auctions";

	private int currentAuctionID;
	private int currentSerialNumber;

	private Gson gson;
	private AsyncHttpClient httpClient;

	public static void main(String[] args) {
		int port = 8081;
		URI baseUri = UriBuilder.fromUri("http://0.0.0.0/").port(port).build();
		ResourceConfig config = new ResourceConfig();
		config.register( new AuctionServer() );

		JdkHttpServerFactory.createHttpServer(baseUri, config);

		System.out.println("Auction Server ready @ " + baseUri);
	}

	public AuctionServer() {

		currentAuctionID = 0;
		currentSerialNumber = 0;

		gson = new Gson();
		httpClient = Dsl.asyncHttpClient();
	}

	@Override
	public Response createAuction(String clientAuctionInformation) throws ClientProtocolException, IOException {
		System.out.println("[" + this.getClass().getCanonicalName() + "]: " +
				"Received request to create a new Auction!");
		
		String url = AUCTION_SERVER_REPOSITORY_ADDRESS + "/open-normal-auction";
		
		UserAuctionInfo userAuctionInfo = gson.fromJson(clientAuctionInformation, UserAuctionInfo.class);
		User user = userAuctionInfo.getUser();
		String auctionDescription = userAuctionInfo.getDescription();

		// TODO Change some values
		Auction newAuction = new Auction(
				String.valueOf(currentAuctionID++), 
				currentSerialNumber++,
				auctionDescription, 
				userAuctionInfo.getBidType(),
				userAuctionInfo.getCurrentBidValue(), 
				userAuctionInfo.getMinAmountBidValue(), 
				userAuctionInfo.getMaxAmountBidValue(), 
				userAuctionInfo.getNumAuctionBidsForEachUserClient(), 
				userAuctionInfo.getNumMaxAuctionBidsAllowed(),
				userAuctionInfo.getAuctionTimestampLimit(),
				1,
				"ProductNameTest", 
				user.getUserFirstName() + " " + user.getUserLastName());

		System.out.println("[" + this.getClass().getCanonicalName() + "]: " +
				"Created auction:\n" + 
				newAuction);


		String serializedNewAuction = gson.toJson(newAuction);

		ListenableFuture<org.asynchttpclient.Response> future;

		switch (newAuction.getAuctionBidType()) {
		case 1:
			url = AUCTION_SERVER_REPOSITORY_ADDRESS + "/open-normal-auction";
			break;
		case 2:
			url = AUCTION_SERVER_REPOSITORY_ADDRESS + "/open-auction-min-initial-bid-value";
			break;
		case 3:
			url = AUCTION_SERVER_REPOSITORY_ADDRESS + "/open-auction-min-amount-bid-value";
			break;
		case 4:
			url = AUCTION_SERVER_REPOSITORY_ADDRESS + "/open-auction-max-amount-bid-value";
			break;
		case 5:
			url = AUCTION_SERVER_REPOSITORY_ADDRESS + "/open-auction-min-and-max-amount-bid-value";
			break;
		case 6:
			url = AUCTION_SERVER_REPOSITORY_ADDRESS + "/open-auction-limited-set-user-client-bidders";
			break;
		case 7:
			url = AUCTION_SERVER_REPOSITORY_ADDRESS + "/open-auction-limited-number-bids-for-each-user-client-bidder";
			break;
		case 8:
			url = AUCTION_SERVER_REPOSITORY_ADDRESS + "/open-auction-limited-number-bids";
			break;
		case 9:
			// TODO this url does not exist yet on the repository!
			url = AUCTION_SERVER_REPOSITORY_ADDRESS + "/open-normal-auction";
			break;
		default:
			break;
		}
		
		future = httpClient.preparePost(url)
				.setBody(serializedNewAuction)
				.execute();

		org.asynchttpclient.Response r = null;
		try {
			r = future.get();
		} catch (Exception e) {
			System.err.println("[" + this.getClass().getCanonicalName() + "]: " + 
					"Error getting response!");
		}

		System.err.println("[" + this.getClass().getCanonicalName() + "]" + 
				"Response: " + r.getStatusText());
		
		return Response.status(r.getStatusCode()).build();
	}

	@Override
	public Response closeAuction(String openedAuctionID) throws SQLException {	
		System.out.println("[" + this.getClass().getCanonicalName() + "]: " +
				"Received request to close an existing Auction!");
		
		String url = AUCTION_SERVER_REPOSITORY_ADDRESS + "/close-auction/" + openedAuctionID;
		
		ListenableFuture<org.asynchttpclient.Response> future;

		future = httpClient.preparePut(url)
				.execute();

		org.asynchttpclient.Response r = null;
		try {
			r = future.get();
		} catch (Exception e) {
			System.err.println("[" + this.getClass().getCanonicalName() + "]: " + 
					"Error getting response!");
		}
		
		System.err.println("[" + this.getClass().getCanonicalName() + "]: " + 
				"Response: " + r.getStatusText());
		
		return Response.status(r.getStatusCode()).build();
	}

	@Override
	public Response addBidToOpenedProductAuction(String openedAuctionID, String bidForAuctionJSONString)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response listAllProductsAuctions() throws SQLException {
		System.out.println("[" + this.getClass().getCanonicalName() + "]: " +
				"Received request to get all Auctions!");
		String url = AUCTION_SERVER_REPOSITORY_ADDRESS + "/all";
		ListenableFuture<org.asynchttpclient.Response> future;

		future = httpClient.prepareGet(url)
				.execute();

		org.asynchttpclient.Response r = null;
		try {
			r = future.get();
		} catch (Exception e) {
			System.err.println("[" + this.getClass().getCanonicalName() + "]" + 
					"Error getting response!");
		}

		System.err.println("[" + this.getClass().getCanonicalName() + "]: " + 
				"Response: " + r.getStatusText());
		
		return Response.status(r.getStatusCode()).build();
	}

	@Override
	public Response listOpenedProductsAuctions() throws SQLException {
		System.out.println("[" + this.getClass().getCanonicalName() + "]: " +
				"Received request to get all opened Auctions!");
		String url = AUCTION_SERVER_REPOSITORY_ADDRESS + "/opened";
		ListenableFuture<org.asynchttpclient.Response> future;

		future = httpClient.prepareGet(url)
				.execute();

		org.asynchttpclient.Response r = null;
		try {
			r = future.get();
		} catch (Exception e) {
			System.err.println("[" + this.getClass().getCanonicalName() + "]" + 
					"Error getting response!");
		}

		System.err.println("[" + this.getClass().getCanonicalName() + "]: " + 
				"Response: " + r.getStatusText());
		
		return Response.status(r.getStatusCode()).build();
	}

	@Override
	public Response listClosedProductsAuctions() throws SQLException {
		System.out.println("[" + this.getClass().getCanonicalName() + "]: " +
				"Received request to get all closed Auctions!");
		String url = AUCTION_SERVER_REPOSITORY_ADDRESS + "/closed";
		ListenableFuture<org.asynchttpclient.Response> future;

		future = httpClient.prepareGet(url)
				.execute();

		org.asynchttpclient.Response r = null;
		try {
			r = future.get();
		} catch (Exception e) {
			System.err.println("[" + this.getClass().getCanonicalName() + "]" + 
					"Error getting response!");
		}

		System.err.println("[" + this.getClass().getCanonicalName() + "]: " + 
				"Response: " + r.getStatusText());
		
		return Response.status(r.getStatusCode()).build();
	}

	@Override
	public Response listAllProductsAuctionsByProductOwnerUserClient(String productOwnerUserClientID)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response listOpenedProductsAuctionsByProductOwnerUserClient(String productOwnerUserClientID)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response listClosedProductsAuctionsByProductOwnerUserClient(String productOwnerUserClientID)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response findProductAuctionByID(String auctionID) throws SQLException {
		System.out.println("[" + this.getClass().getCanonicalName() + "]: " +
				"Received request to get Auction with id" + auctionID + "!");
		String url = AUCTION_SERVER_REPOSITORY_ADDRESS + "/all/" + auctionID;
		ListenableFuture<org.asynchttpclient.Response> future;

		future = httpClient.prepareGet(url)
				.execute();

		org.asynchttpclient.Response r = null;
		try {
			r = future.get();
		} catch (Exception e) {
			System.err.println("[" + this.getClass().getCanonicalName() + "]" + 
					"Error getting response!");
		}

		System.err.println("[" + this.getClass().getCanonicalName() + "]: " + 
				"Response: " + r.getStatusText());
		
		return Response.status(r.getStatusCode()).build();
	}

	@Override
	public Response findOpenedProductAuctionByID(String openedAuctionID) throws SQLException {
		System.out.println("[" + this.getClass().getCanonicalName() + "]: " +
				"Received request to get opened Auction with id" + openedAuctionID + "!");
		String url = AUCTION_SERVER_REPOSITORY_ADDRESS + "/opened/" + openedAuctionID;
		ListenableFuture<org.asynchttpclient.Response> future;

		future = httpClient.prepareGet(url)
				.execute();

		org.asynchttpclient.Response r = null;
		try {
			r = future.get();
		} catch (Exception e) {
			System.err.println("[" + this.getClass().getCanonicalName() + "]" + 
					"Error getting response!");
		}

		System.err.println("[" + this.getClass().getCanonicalName() + "]: " + 
				"Response: " + r.getStatusText());
		
		return Response.status(r.getStatusCode()).build();
	}

	@Override
	public Response findClosedProductAuctionByID(String closedAuctionID) throws SQLException {
		System.out.println("[" + this.getClass().getCanonicalName() + "]: " +
				"Received request to get opened Auction with id" + closedAuctionID + "!");
		String url = AUCTION_SERVER_REPOSITORY_ADDRESS + "/closed/" + closedAuctionID;
		ListenableFuture<org.asynchttpclient.Response> future;

		future = httpClient.prepareGet(url)
				.execute();

		org.asynchttpclient.Response r = null;
		try {
			r = future.get();
		} catch (Exception e) {
			System.err.println("[" + this.getClass().getCanonicalName() + "]" + 
					"Error getting response!");
		}

		System.err.println("[" + this.getClass().getCanonicalName() + "]: " + 
				"Response: " + r.getStatusText());
		
		return Response.status(r.getStatusCode()).build();
	}

	@Override
	public Response listAllBidsOfProductAuctionByID(String auctionID) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response listAllBidsOfOpenedProductAuctionByID(String openedAuctionID) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response listAllBidsOfClosedProductAuctionByID(String closedAuctionID) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response listAllBidsMadeByBidderUserClientInProductAuctionByID(String auctionID, String bidderUserClientID)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response listAllBidsMadeByBidderUserClientInOpenedProductAuctionByID(String openedAuctionID,
			String bidderUserClientID) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response listAllBidsMadeByBidderUserClientInClosedProductAuctionByID(String closedAuctionID,
			String bidderUserClientID) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
}
