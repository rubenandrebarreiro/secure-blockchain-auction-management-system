package main.java.sys.rest.server.auction;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.HashMap;

import javax.ws.rs.core.UriBuilder;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
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
	private final CloseableHttpClient httpClient;

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
		httpClient = HttpClients.createDefault();
	}

	@Override
	public void createAuction(String clientAuctionInformation) throws ClientProtocolException, IOException {
		System.out.println("[" + this.getClass().getCanonicalName() + "]" +
							"Received request to create a new Auction!");
		String result;

		UserAuctionInfo userAuctionInfo = gson.fromJson(clientAuctionInformation, UserAuctionInfo.class);
		User user = userAuctionInfo.getUser();
		String auctionDescription = userAuctionInfo.getDescription();
		
		HttpPost post = new HttpPost(AUCTION_SERVER_REPOSITORY_ADDRESS + "/open-normal-auction");
		// TODO Change some values
		Auction newAuction = new Auction(
				String.valueOf(currentAuctionID++), currentSerialNumber++,
				auctionDescription, (byte)1,
				(double)0, (double)0, (double)100, new HashMap<String, Integer>(), 100,
				System.currentTimeMillis() + 100000,
				1,
				"ProductNameTest", 
				user.getUserFirstName() + " " + user.getUserLastName());
		
		String serializedNewAuction = gson.toJson(newAuction);
		post.setEntity(new StringEntity(serializedNewAuction));

		CloseableHttpResponse response = null;
		response = httpClient.execute(post);
		
		result = EntityUtils.toString(response.getEntity());

		System.out.println(result);
	}
	
	
	
}
