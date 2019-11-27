package main.java.sys.rest.server.auction;

import java.io.IOException;
import java.net.URI;
import java.util.HashMap;

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
		
		UserAuctionInfo userAuctionInfo = gson.fromJson(clientAuctionInformation, UserAuctionInfo.class);
		User user = userAuctionInfo.getUser();
		String auctionDescription = userAuctionInfo.getDescription();

		// TODO Change some values
		Auction newAuction = new Auction(
				String.valueOf(currentAuctionID++), currentSerialNumber++,
				auctionDescription, (byte)1,
				(double)0, (double)0, (double)100, new HashMap<String, Integer>(), 100,
				System.currentTimeMillis() + 100000,
				1,
				"ProductNameTest", 
				user.getUserFirstName() + " " + user.getUserLastName());

		System.out.println("[" + this.getClass().getCanonicalName() + "]: " +
				"Created auction:\n" + 
				newAuction);


		String serializedNewAuction = gson.toJson(newAuction);

		ListenableFuture<org.asynchttpclient.Response> future;

		future = httpClient.preparePost(AUCTION_SERVER_REPOSITORY_ADDRESS + "/open-normal-auction")
				.setBody(serializedNewAuction)
				.execute();

		org.asynchttpclient.Response r = null;
		try {
			r = future.get();
		} catch (Exception e) {
			System.err.println("[" + this.getClass().getCanonicalName() + "]" + 
					"Error getting response!");
		}

		System.err.println("[" + this.getClass().getCanonicalName() + "]" + 
				"Response: " + r.getStatusText());
		
		return Response.status(r.getStatusCode()).build();
	}



}
