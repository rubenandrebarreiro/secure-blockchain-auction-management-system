package main.java.sys.rest.server.auction;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.nio.charset.Charset;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.sql.SQLException;
import java.util.Random;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;
import javax.ws.rs.core.UriBuilder;

import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.glassfish.jersey.jdkhttp.JdkHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import com.google.gson.Gson;

import main.java.api.rest.server.auction.AuctionServerAPI;
import main.java.resources.auction.Auction;
import main.java.resources.bid.Bid;
import main.java.resources.user.User;
import main.java.resources.user.UserAuctionInfo;
import main.java.resources.user.UserBidInfo;
import main.java.sys.SSLSocketMessage;
import main.java.sys.rest.server.auction.configuration.utils.AuctionServerKeyStoreConfigurationReader;
import main.java.sys.rest.server.auction.configuration.utils.AuctionServerTLSConfigurationReader;

public class AuctionServer extends Thread implements AuctionServerAPI{

	private static final String AUCTION_SERVER_REPOSITORY_ADDRESS = "http://localhost:8080/products-auctions";

	private static final String URL_SPACE = "%20";

	private static final String AUCTION_SERVER_TLS_CONFIGURATION_PATH = "res/configurations/auction-server-tls-configuration.conf";
	private static final String AUCTION_SERVER_STORES_CONFIGURATION_PATH = "res/configurations/auction-server-keystore-configuration.conf";
	
	private static final String TLS_CONF_CLIENT_ONLY = "CLIENT-ONLY";
	private static final String TLS_CONF_SERVER_ONLY = "SERVER-ONLY";
	private static final String TLS_CONF_MUTUAL = "MUTUAL";
	
	private int currentAuctionID;
	private int currentSerialNumber;

	private Gson gson;

	HttpClient httpClient;

	private SSLServerSocket serverSocket;
	private SSLSocket responseSocket;
	private KeyManagerFactory kmf;
	private KeyStore ks;

	public AuctionServer(SSLServerSocket serverSocket, SSLSocket responseSocket) throws IOException, NoSuchAlgorithmException, UnrecoverableKeyException, KeyStoreException, CertificateException, KeyManagementException {
		this.serverSocket = serverSocket;
		this.responseSocket = responseSocket;
		this.gson = new Gson();
		this.httpClient = HttpClients.createDefault();
	}

	public void run() {
		String arg1 = null, arg2 = null;
		HttpResponse response = null;

		try {
			while(true) {
				String jsonMessage = sslReadRequest(responseSocket.getInputStream());
				//					SSLSession session = responseSocket.getSession();
				//					Principal clientID = session.getPeerPrincipal();
				//					System.out.println("Client has been identified as: " + clientID);
				SSLSocketMessage message = gson.fromJson(jsonMessage, SSLSocketMessage.class);
				if(message != null) {

					switch (message.getOperation()) {
					case OPEN_AUCTION:
						response = createAuction(message.getBody());
						break;
					case CLOSE_AUCTION:
						response = closeAuction(message.getParamsMap().get("auction-id"));
						break;
					case ADD_BID:
						arg1 = message.getParamsMap().get("auction-id");
						arg2 = message.getBody();
						response = addBidToOpenedProductAuction(arg1, arg2);
						break;
					case LIST_ALL_AUCTIONS:
						response = listAllProductsAuctions();
						break;
					case LIST_OPENED_AUCTIONS:
						response = listOpenedProductsAuctions();
						break;
					case LIST_CLOSED_AUCTIONS:
						response = listClosedProductsAuctions();
						break;
					case LIST_ALL_AUCTIONS_BY_OWNER:
						response = listAllProductsAuctionsByProductOwnerUserClient(message.getParamsMap().get("product-owner-user-client-id"));
						break;
					case LIST_OPENED_AUCTIONS_BY_OWNER:
						response = listOpenedProductsAuctionsByProductOwnerUserClient(message.getParamsMap().get("product-owner-user-client-id"));
						break;
					case LIST_CLOSED_AUCTIONS_BY_OWNER:
						response = listClosedProductsAuctionsByProductOwnerUserClient(message.getParamsMap().get("product-owner-user-client-id"));
						break;
					case LIST_ALL_AUCTIONS_BY_ID:
						response = findProductAuctionByID(message.getParamsMap().get("auction-id"));
						break;
					case LIST_OPENED_AUCTIONS_BY_ID:
						response = findOpenedProductAuctionByID(message.getParamsMap().get("auction-id"));
						break;
					case LIST_CLOSED_AUCTIONS_BY_ID:
						response = findClosedProductAuctionByID(message.getParamsMap().get("auction-id"));
						break;
					case LIST_BIDS_OF_ALL_AUCTIONS_BY_AUCTION_ID:
						response = listAllBidsOfProductAuctionByID(message.getParamsMap().get("auction-id"));
						break;
					case LIST_BIDS_OF_OPENED_AUCTIONS_BY_AUCTION_ID:
						response = listAllBidsOfOpenedProductAuctionByID(message.getParamsMap().get("auction-id"));
						break;
					case LIST_BIDS_OF_CLOSED_AUCTIONS_BY_AUCTION_ID:
						response = listAllBidsOfClosedProductAuctionByID(message.getParamsMap().get("auction-id"));
						break;
					case LIST_BIDS_OF_ALL_AUCTIONS_BY_AUCTION_ID_AND_CLIENT_ID:
						arg1 = message.getParamsMap().get("auction-id");
						arg2 = message.getParamsMap().get("bidder-user-client-id");
						response = listAllBidsMadeByBidderUserClientInAllProductAuctionByID(arg1, arg2);
						break;
					case LIST_BIDS_OF_OPENED_AUCTIONS_BY_AUCTION_ID_AND_CLIENT_ID:
						arg1 = message.getParamsMap().get("auction-id");
						arg2 = message.getParamsMap().get("bidder-user-client-id");
						response = listAllBidsMadeByBidderUserClientInOpenedProductAuctionByID(arg1, arg2);
						break;
					case LIST_BIDS_OF_CLOSED_AUCTIONS_BY_AUCTION_ID_AND_CLIENT_ID:
						arg1 = message.getParamsMap().get("auction-id");
						arg2 = message.getParamsMap().get("bidder-user-client-id");
						response = listAllBidsMadeByBidderUserClientInClosedProductAuctionByID(arg1, arg2);
						break;
					default:
						//TODO Error somewhere?
						break;
					}
					sslWriteResponse(responseSocket.getOutputStream(), response);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.getMessage();
			e.printStackTrace();
		}
	}

	@Override
	public HttpResponse createAuction(String clientAuctionInformation) {
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
				user.getUserPeerID());

		System.out.println("[" + this.getClass().getCanonicalName() + "]: " +
				"Created auction:\n" + 
				newAuction);

		String serializedNewAuction = gson.toJson(newAuction);

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
		
		url = removeSpaceFromURL(url);

		HttpPost postRequest = new HttpPost(url);
		HttpResponse response = null;
		try {
			postRequest.setEntity(new StringEntity(serializedNewAuction));
			postRequest.setHeader("Accept", "application/json");
			postRequest.setHeader("Content-type", "application/json");
			response = httpClient.execute(postRequest);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.err.println("[" + this.getClass().getCanonicalName() + "]" + 
				"Response: " + response.getStatusLine());
		System.err.println("[" + this.getClass().getCanonicalName() + "]" + 
				"Response: " + response.getEntity());

		return response;
	}

	@Override
	public HttpResponse closeAuction(String openedAuctionID) throws SQLException {	
		System.out.println("[" + this.getClass().getCanonicalName() + "]: " +
				"Received request to close an existing Auction!");

		String url = (AUCTION_SERVER_REPOSITORY_ADDRESS + "/close-auction/" + openedAuctionID).replace(" ", URL_SPACE);
		url = removeSpaceFromURL(url);
		HttpPut putRequest = new HttpPut(url);
		HttpResponse response = null;
		try {
			response = httpClient.execute(putRequest);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.err.println("[" + this.getClass().getCanonicalName() + "]" + 
				"Response: " + response.getStatusLine());
		System.err.println("[" + this.getClass().getCanonicalName() + "]" + 
				"Response: " + response.getEntity());

		return response;
	}

	@Override
	public HttpResponse addBidToOpenedProductAuction(String openedAuctionID, String userBidInfo)
			throws SQLException {
		System.out.println("[" + this.getClass().getCanonicalName() + "]: " +
				"Received request to add bid to " + openedAuctionID + "!");

		String url = AUCTION_SERVER_REPOSITORY_ADDRESS + "/add-bid-to-opened-auction/" + openedAuctionID;
		url = removeSpaceFromURL(url);
		HttpPost postRequest = new HttpPost(url);
		HttpResponse response = null;
		UserBidInfo bidInfo = gson.fromJson(userBidInfo, UserBidInfo.class);
		//TODO Change generation of Bid ID!
		Random random = new Random();
		Bid bid = new Bid(random.nextLong(), bidInfo.getUser().getUserPeerID(), bidInfo.getBidValue());
		String bidJson = gson.toJson(bid);
		try {
			postRequest.setEntity(new StringEntity(bidJson));
			postRequest.setHeader("Accept", "application/json");
			postRequest.setHeader("Content-type", "application/json");
			response = httpClient.execute(postRequest);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.err.println("[" + this.getClass().getCanonicalName() + "]" + 
				"Response: " + response.getStatusLine());
		System.err.println("[" + this.getClass().getCanonicalName() + "]" + 
				"Response: " + response.getEntity());

		return response;
	}

	@Override
	public HttpResponse listAllProductsAuctions() throws SQLException {
		System.out.println("[" + this.getClass().getCanonicalName() + "]: " +
				"Received request to get all Auctions!");
		
		String url = AUCTION_SERVER_REPOSITORY_ADDRESS + "/all";
		url = removeSpaceFromURL(url);
		HttpGet getRequest = new HttpGet(url);
		HttpResponse response = null;
		try {
			response = httpClient.execute(getRequest);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.err.println("[" + this.getClass().getCanonicalName() + "]" + 
				"Response: " + response.getStatusLine());
		System.err.println("[" + this.getClass().getCanonicalName() + "]" + 
				"Response: " + response.getEntity());

		return response;
	}

	@Override
	public HttpResponse listOpenedProductsAuctions() throws SQLException {
		System.out.println("[" + this.getClass().getCanonicalName() + "]: " +
				"Received request to get all opened Auctions!");
	
		String url = AUCTION_SERVER_REPOSITORY_ADDRESS + "/opened";
		url = removeSpaceFromURL(url);
		HttpGet getRequest = new HttpGet(url);
		HttpResponse response = null;
		try {
			response = httpClient.execute(getRequest);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.err.println("[" + this.getClass().getCanonicalName() + "]" + 
				"Response: " + response.getStatusLine());
		System.err.println("[" + this.getClass().getCanonicalName() + "]" + 
				"Response: " + response.getEntity());

		return response;
	}

	@Override
	public HttpResponse listClosedProductsAuctions() throws SQLException {
		System.out.println("[" + this.getClass().getCanonicalName() + "]: " +
				"Received request to get all closed Auctions!");
		
		String url = AUCTION_SERVER_REPOSITORY_ADDRESS + "/closed";
		url = removeSpaceFromURL(url);
		HttpGet getRequest = new HttpGet(url);
		HttpResponse response = null;
		try {
			response = httpClient.execute(getRequest);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.err.println("[" + this.getClass().getCanonicalName() + "]" + 
				"Response: " + response.getStatusLine());
		System.err.println("[" + this.getClass().getCanonicalName() + "]" + 
				"Response: " + response.getEntity());

		return response;
	}

	@Override
	public HttpResponse listAllProductsAuctionsByProductOwnerUserClient(String productOwnerUserClientID)
			throws SQLException {
		System.out.println("[" + this.getClass().getCanonicalName() + "]: " +
				"Received request to get all Auctions by owner " + productOwnerUserClientID + "!");
		
		String url = AUCTION_SERVER_REPOSITORY_ADDRESS + "/all/by-product-owner-user/" + productOwnerUserClientID;
		url = removeSpaceFromURL(url);
		HttpGet getRequest = new HttpGet(url);
		HttpResponse response = null;
		try {
			response = httpClient.execute(getRequest);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.err.println("[" + this.getClass().getCanonicalName() + "]" + 
				"Response: " + response.getStatusLine());
		System.err.println("[" + this.getClass().getCanonicalName() + "]" + 
				"Response: " + response.getEntity());

		return response;
	}

	@Override
	public HttpResponse listOpenedProductsAuctionsByProductOwnerUserClient(String productOwnerUserClientID)
			throws SQLException {
		System.out.println("[" + this.getClass().getCanonicalName() + "]: " +
				"Received request to get all opened Auctions by owner " + productOwnerUserClientID + "!");
		
		String url = AUCTION_SERVER_REPOSITORY_ADDRESS + "/opened/by-product-owner-user/" + productOwnerUserClientID;
		url = removeSpaceFromURL(url);
		HttpGet getRequest = new HttpGet(url);
		HttpResponse response = null;
		try {
			response = httpClient.execute(getRequest);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.err.println("[" + this.getClass().getCanonicalName() + "]" + 
				"Response: " + response.getStatusLine());
		System.err.println("[" + this.getClass().getCanonicalName() + "]" + 
				"Response: " + response.getEntity());

		return response;
	}

	@Override
	public HttpResponse listClosedProductsAuctionsByProductOwnerUserClient(String productOwnerUserClientID)
			throws SQLException {
		System.out.println("[" + this.getClass().getCanonicalName() + "]: " +
				"Received request to get all closed Auctions by owner " + productOwnerUserClientID + "!");
		
		String url = AUCTION_SERVER_REPOSITORY_ADDRESS + "/closed/by-product-owner-user/" + productOwnerUserClientID;
		url = removeSpaceFromURL(url);
		HttpGet getRequest = new HttpGet(url);
		HttpResponse response = null;
		try {
			response = httpClient.execute(getRequest);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.err.println("[" + this.getClass().getCanonicalName() + "]" + 
				"Response: " + response.getStatusLine());
		System.err.println("[" + this.getClass().getCanonicalName() + "]" + 
				"Response: " + response.getEntity());

		return response;
	}

	@Override
	public HttpResponse findProductAuctionByID(String auctionID) throws SQLException {
		System.out.println("[" + this.getClass().getCanonicalName() + "]: " +
				"Received request to get Auction with id " + auctionID + "!");
		
		String url = AUCTION_SERVER_REPOSITORY_ADDRESS + "/all/" + auctionID;
		url = removeSpaceFromURL(url);
		HttpGet getRequest = new HttpGet(url);
		HttpResponse response = null;
		try {
			response = httpClient.execute(getRequest);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.err.println("[" + this.getClass().getCanonicalName() + "]" + 
				"Response: " + response.getStatusLine());
		System.err.println("[" + this.getClass().getCanonicalName() + "]" + 
				"Response: " + response.getEntity());
		return response;
	}

	@Override
	public HttpResponse findOpenedProductAuctionByID(String openedAuctionID) throws SQLException {
		System.out.println("[" + this.getClass().getCanonicalName() + "]: " +
				"Received request to get opened Auction with id " + openedAuctionID + "!");
		
		String url = AUCTION_SERVER_REPOSITORY_ADDRESS + "/opened/" + openedAuctionID;
		url = removeSpaceFromURL(url);
		HttpGet getRequest = new HttpGet(url);
		HttpResponse response = null;
		try {
			response = httpClient.execute(getRequest);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.err.println("[" + this.getClass().getCanonicalName() + "]" + 
				"Response: " + response.getStatusLine());
		System.err.println("[" + this.getClass().getCanonicalName() + "]" + 
				"Response: " + response.getEntity());

		return response;
	}

	@Override
	public HttpResponse findClosedProductAuctionByID(String closedAuctionID) throws SQLException {
		System.out.println("[" + this.getClass().getCanonicalName() + "]: " +
				"Received request to get opened Auction with id " + closedAuctionID + "!");
		
		String url = AUCTION_SERVER_REPOSITORY_ADDRESS + "/closed/" + closedAuctionID;
		url = removeSpaceFromURL(url);
		HttpGet getRequest = new HttpGet(url);
		HttpResponse response = null;
		try {
			response = httpClient.execute(getRequest);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.err.println("[" + this.getClass().getCanonicalName() + "]" + 
				"Response: " + response.getStatusLine());
		System.err.println("[" + this.getClass().getCanonicalName() + "]" + 
				"Response: " + response.getEntity());
		return response;
	}

	@Override
	public HttpResponse listAllBidsOfProductAuctionByID(String auctionID) throws SQLException {
		System.out.println("[" + this.getClass().getCanonicalName() + "]: " +
				"Received request to get all bids from all Auction with id " + auctionID + "!");
		
		String url = AUCTION_SERVER_REPOSITORY_ADDRESS + "/all/" + auctionID + "/bids";
		url = removeSpaceFromURL(url);
		HttpGet getRequest = new HttpGet(url);
		HttpResponse response = null;
		try {
			response = httpClient.execute(getRequest);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.err.println("[" + this.getClass().getCanonicalName() + "]" + 
				"Response: " + response.getStatusLine());
		System.err.println("[" + this.getClass().getCanonicalName() + "]" + 
				"Response: " + response.getEntity());
		return response;
	}

	@Override
	public HttpResponse listAllBidsOfOpenedProductAuctionByID(String openedAuctionID) throws SQLException {
		System.out.println("[" + this.getClass().getCanonicalName() + "]: " +
				"Received request to get all bids from opened Auction with id " + openedAuctionID + "!");
		
		String url = AUCTION_SERVER_REPOSITORY_ADDRESS + "/opened/" + openedAuctionID + "/bids";
		url = removeSpaceFromURL(url);
		HttpGet getRequest = new HttpGet(url);
		HttpResponse response = null;
		try {
			response = httpClient.execute(getRequest);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.err.println("[" + this.getClass().getCanonicalName() + "]" + 
				"Response: " + response.getStatusLine());
		System.err.println("[" + this.getClass().getCanonicalName() + "]" + 
				"Response: " + response.getEntity());
		return response;
	}

	@Override
	public HttpResponse listAllBidsOfClosedProductAuctionByID(String closedAuctionID) throws SQLException {
		System.out.println("[" + this.getClass().getCanonicalName() + "]: " +
				"Received request to get all bids from closed Auction with id" + closedAuctionID + "!");
		
		String url = AUCTION_SERVER_REPOSITORY_ADDRESS + "/closed/" + closedAuctionID + "/bids";
		url = removeSpaceFromURL(url);
		HttpGet getRequest = new HttpGet(url);
		HttpResponse response = null;
		try {
			response = httpClient.execute(getRequest);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.err.println("[" + this.getClass().getCanonicalName() + "]" + 
				"Response: " + response.getStatusLine());
		System.err.println("[" + this.getClass().getCanonicalName() + "]" + 
				"Response: " + response.getEntity());
		return response;
	}

	@Override
	public HttpResponse listAllBidsMadeByBidderUserClientInAllProductAuctionByID(String auctionID, String bidderUserClientID)
			throws SQLException {
		System.out.println("[" + this.getClass().getCanonicalName() + "]: " +
				"Received request to get all bids from Auction with id" + auctionID + " and from " + bidderUserClientID + "!");
		
		String url = AUCTION_SERVER_REPOSITORY_ADDRESS + "/all/" + auctionID + "/bids/" + bidderUserClientID;
		url = removeSpaceFromURL(url);
		HttpGet getRequest = new HttpGet(url);
		HttpResponse response = null;
		try {
			response = httpClient.execute(getRequest);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.err.println("[" + this.getClass().getCanonicalName() + "]" + 
				"Response: " + response.getStatusLine());
		System.err.println("[" + this.getClass().getCanonicalName() + "]" + 
				"Response: " + response.getEntity());
		return response;
	}

	@Override
	public HttpResponse listAllBidsMadeByBidderUserClientInOpenedProductAuctionByID(String openedAuctionID,
			String bidderUserClientID) throws SQLException {
		System.out.println("[" + this.getClass().getCanonicalName() + "]: " +
				"Received request to get all bids from Opened Auction with id" + openedAuctionID + " and from " + bidderUserClientID + "!");
		
		String url = AUCTION_SERVER_REPOSITORY_ADDRESS + "/opened/" + openedAuctionID + "/bids/" + bidderUserClientID;
		url = removeSpaceFromURL(url);
		HttpGet getRequest = new HttpGet(url);
		HttpResponse response = null;
		try {
			response = httpClient.execute(getRequest);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.err.println("[" + this.getClass().getCanonicalName() + "]" + 
				"Response: " + response.getStatusLine());
		System.err.println("[" + this.getClass().getCanonicalName() + "]" + 
				"Response: " + response.getEntity());
		return response;
	}

	@Override
	public HttpResponse listAllBidsMadeByBidderUserClientInClosedProductAuctionByID(String closedAuctionID,
			String bidderUserClientID) throws SQLException {
		System.out.println("[" + this.getClass().getCanonicalName() + "]: " +
				"Received request to get all bids from Closed Auction with id" + closedAuctionID + " and from " + bidderUserClientID + "!");
		
		String url = AUCTION_SERVER_REPOSITORY_ADDRESS + "/closed/" + closedAuctionID + "/bids/" + bidderUserClientID;
		url = removeSpaceFromURL(url);
		HttpGet getRequest = new HttpGet(url);
		HttpResponse response = null;
		try {
			response = httpClient.execute(getRequest);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.err.println("[" + this.getClass().getCanonicalName() + "]" + 
				"Response: " + response.getStatusLine());
		System.err.println("[" + this.getClass().getCanonicalName() + "]" + 
				"Response: " + response.getEntity());
		return response;
	}
	
	@Override
	public HttpResponse listAllBidsMadeByBidderUserClientID(String bidderUserClientID) throws SQLException {
		System.out.println("[" + this.getClass().getCanonicalName() + "]: " +
				"Received request to get all bids from " + bidderUserClientID + "!");
		
		String url = AUCTION_SERVER_REPOSITORY_ADDRESS + "/bids/all/" + bidderUserClientID;
		url = removeSpaceFromURL(url);
		HttpGet getRequest = new HttpGet(url);
		HttpResponse response = null;
		try {
			response = httpClient.execute(getRequest);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.err.println("[" + this.getClass().getCanonicalName() + "]" + 
				"Response: " + response.getStatusLine());
		System.err.println("[" + this.getClass().getCanonicalName() + "]" + 
				"Response: " + response.getEntity());
		return response;
	}
	
	@Override
	public HttpResponse listOpenedBidsMadeByBidderUserClientID(String bidderUserClientID) throws SQLException {
		System.out.println("[" + this.getClass().getCanonicalName() + "]: " +
				"Received request to get opened bids from " + bidderUserClientID + "!");
		
		String url = AUCTION_SERVER_REPOSITORY_ADDRESS + "/bids/opened/" + bidderUserClientID;
		url = removeSpaceFromURL(url);
		HttpGet getRequest = new HttpGet(url);
		HttpResponse response = null;
		try {
			response = httpClient.execute(getRequest);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.err.println("[" + this.getClass().getCanonicalName() + "]" + 
				"Response: " + response.getStatusLine());
		System.err.println("[" + this.getClass().getCanonicalName() + "]" + 
				"Response: " + response.getEntity());
		return response;
	}
	
	@Override
	public HttpResponse listClosedBidsMadeByBidderUserClientID(String bidderUserClientID) throws SQLException {
		System.out.println("[" + this.getClass().getCanonicalName() + "]: " +
				"Received request to get closed bids from " + bidderUserClientID + "!");
		
		String url = AUCTION_SERVER_REPOSITORY_ADDRESS + "/bids/closed/" + bidderUserClientID;
		url = removeSpaceFromURL(url);
		HttpGet getRequest = new HttpGet(url);
		HttpResponse response = null;
		try {
			response = httpClient.execute(getRequest);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.err.println("[" + this.getClass().getCanonicalName() + "]" + 
				"Response: " + response.getStatusLine());
		System.err.println("[" + this.getClass().getCanonicalName() + "]" + 
				"Response: " + response.getEntity());
		return response;
	}
	
	@Override
	public HttpResponse checkOutcomeAllAuctions(String bidderUserClientID) throws SQLException {
		System.out.println("[" + this.getClass().getCanonicalName() + "]: " +
				"Received request to check the outcome from all auctions where " + bidderUserClientID + " participated!");
		
		String url = AUCTION_SERVER_REPOSITORY_ADDRESS + "/outcome/all/" + bidderUserClientID;
		url = removeSpaceFromURL(url);
		HttpGet getRequest = new HttpGet(url);
		HttpResponse response = null;
		try {
			response = httpClient.execute(getRequest);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.err.println("[" + this.getClass().getCanonicalName() + "]" + 
				"Response: " + response.getStatusLine());
		System.err.println("[" + this.getClass().getCanonicalName() + "]" + 
				"Response: " + response.getEntity());
		return response;
	}

	@Override
	public HttpResponse checkOutcomeOpenedAuctions(String bidderUserClientID) throws SQLException {
		System.out.println("[" + this.getClass().getCanonicalName() + "]: " +
				"Received request to check the outcome from all auctions where " + bidderUserClientID + " participated!");
		
		String url = AUCTION_SERVER_REPOSITORY_ADDRESS + "/outcome/opened/" + bidderUserClientID;
		url = removeSpaceFromURL(url);
		HttpGet getRequest = new HttpGet(url);
		HttpResponse response = null;
		try {
			response = httpClient.execute(getRequest);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.err.println("[" + this.getClass().getCanonicalName() + "]" + 
				"Response: " + response.getStatusLine());
		System.err.println("[" + this.getClass().getCanonicalName() + "]" + 
				"Response: " + response.getEntity());
		return response;
	}
	
	@Override
	public HttpResponse checkOutcomeClosedAuctions(String bidderUserClientID) throws SQLException {
		System.out.println("[" + this.getClass().getCanonicalName() + "]: " +
				"Received request to check the outcome from all auctions where " + bidderUserClientID + " participated!");
		
		String url = AUCTION_SERVER_REPOSITORY_ADDRESS + "/outcome/closed/" + bidderUserClientID;
		url = removeSpaceFromURL(url);
		HttpGet getRequest = new HttpGet(url);
		HttpResponse response = null;
		try {
			response = httpClient.execute(getRequest);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.err.println("[" + this.getClass().getCanonicalName() + "]" + 
				"Response: " + response.getStatusLine());
		System.err.println("[" + this.getClass().getCanonicalName() + "]" + 
				"Response: " + response.getEntity());
		return response;
	}
	private String sslReadRequest(InputStream socketInStream) throws IOException {
		StringBuilder builder = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(socketInStream));
		builder.append(br.readLine());

		return builder.toString();
	}

	private void sslWriteResponse(OutputStream socketOutStream, HttpResponse response) throws ParseException, IOException {
		String string = response.getStatusLine().toString();
		if(response.getEntity() != null && response.getEntity().getContentLength() != 0)
			string = EntityUtils.toString(response.getEntity(), Charset.forName("UTF-8"));
		PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(socketOutStream));
		printWriter.print(string + System.lineSeparator());
		printWriter.flush();
	}
	
	private String removeSpaceFromURL(String url) {
		return url.replace(" ", URL_SPACE);
	}
}
