package main.java.sys.rest.server.auction;

import java.io.BufferedReader;
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
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.sql.SQLException;

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
import main.java.resources.user.User;
import main.java.resources.user.UserAuctionInfo;
import main.java.sys.SSLSocketMessage;

public class AuctionServer extends Thread implements AuctionServerAPI{

	private static final String AUCTION_SERVER_REPOSITORY_ADDRESS = "http://localhost:8080/products-auctions";

	private int currentAuctionID;
	private int currentSerialNumber;

	private Gson gson;

	HttpClient httpClient;

	private SSLServerSocket serverSocket;
	private SSLSocket responseSocket;

	public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
		int port = 8081;

		URI baseUri = UriBuilder.fromUri("http://0.0.0.0/").port(port).build();
		ResourceConfig config = new ResourceConfig();
		try {
			config.register( new AuctionServer() );
		} catch (IOException e) {
			e.getMessage();
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.getMessage();
			e.printStackTrace();
		} catch (UnrecoverableKeyException e) {
			e.getMessage();
			e.printStackTrace();
		} catch (KeyStoreException e) {
			e.getMessage();
			e.printStackTrace();
		} catch (CertificateException e) {
			e.getMessage();
			e.printStackTrace();
		} catch (KeyManagementException e) {
			e.getMessage();
			e.printStackTrace();
		}

		JdkHttpServerFactory.createHttpServer(baseUri, config);

		System.out.println("Auction Server ready @ " + baseUri);
	}

	public AuctionServer() throws IOException, NoSuchAlgorithmException, UnrecoverableKeyException, KeyStoreException, CertificateException, KeyManagementException {

		//		System.setProperty("javax.net.debug", "SSL,handshake");

		System.setProperty("javax.net.ssl.keyStore", "res/keystores/auctionServerKeystore.jks");
		System.setProperty("javax.net.ssl.keyStorePassword", "auctionServer1920");

		System.setProperty("javax.net.ssl.trustStore", "res/truststores/auctionServerTruststore.jks");
		System.setProperty("javax.net.ssl.trustStorePassword", "auctionServer1920");

		currentAuctionID = 0;
		currentSerialNumber = 0;

		gson = new Gson();
		httpClient = HttpClients.createDefault();

		SSLContext sslContext = SSLContext.getDefault();
		SSLServerSocketFactory serverSocketFactory = sslContext.getServerSocketFactory();
		serverSocket = (SSLServerSocket)serverSocketFactory.createServerSocket(8443);
//		serverSocket.setWantClientAuth(true);

		start();
	}

	public void run() {
		String arg1 = null, arg2 = null;
		HttpResponse response = null;

		try {
			responseSocket = (SSLSocket) serverSocket.accept();
			responseSocket.startHandshake();
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
						response = listAllBidsMadeByBidderUserClientInProductAuctionByID(arg1, arg2);
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
			try {
				responseSocket.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.getMessage();
			e.printStackTrace();
		}
	}

	private String sslReadRequest(InputStream socketInStream) throws IOException {
		StringBuilder builder = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(socketInStream));
		builder.append(br.readLine());

		return builder.toString();
	}

	private void sslWriteResponse(OutputStream socketOutStream, HttpResponse response) throws ParseException, IOException {
		String string;
		if(response.getEntity() != null)
			string = EntityUtils.toString(response.getEntity(), Charset.forName("UTF-8"));
		else
			string = response.getStatusLine().toString();
		PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(socketOutStream));
		printWriter.print(string + System.lineSeparator());
		printWriter.flush();
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
				user.getUserFirstName() + " " + user.getUserLastName());

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

		String url = AUCTION_SERVER_REPOSITORY_ADDRESS + "/close-auction/" + openedAuctionID;

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
	public HttpResponse addBidToOpenedProductAuction(String openedAuctionID, String bidForAuctionJSONString)
			throws SQLException {
		System.out.println("[" + this.getClass().getCanonicalName() + "]: " +
				"Received request to add bid to " + openedAuctionID + "!");

		String url = AUCTION_SERVER_REPOSITORY_ADDRESS + "/add-bid-to-opened-auction/" + openedAuctionID;
		HttpPost postRequest = new HttpPost(url);
		HttpResponse response = null;
		try {
			postRequest.setEntity(new StringEntity(bidForAuctionJSONString));
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
				"Received request to get Auction with id" + auctionID + "!");
		String url = AUCTION_SERVER_REPOSITORY_ADDRESS + "/all/" + auctionID;

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
				"Received request to get opened Auction with id" + openedAuctionID + "!");
		String url = AUCTION_SERVER_REPOSITORY_ADDRESS + "/opened/" + openedAuctionID;

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
				"Received request to get opened Auction with id" + closedAuctionID + "!");
		String url = AUCTION_SERVER_REPOSITORY_ADDRESS + "/closed/" + closedAuctionID;

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HttpResponse listAllBidsOfOpenedProductAuctionByID(String openedAuctionID) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HttpResponse listAllBidsOfClosedProductAuctionByID(String closedAuctionID) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HttpResponse listAllBidsMadeByBidderUserClientInProductAuctionByID(String auctionID, String bidderUserClientID)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HttpResponse listAllBidsMadeByBidderUserClientInOpenedProductAuctionByID(String openedAuctionID,
			String bidderUserClientID) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HttpResponse listAllBidsMadeByBidderUserClientInClosedProductAuctionByID(String closedAuctionID,
			String bidderUserClientID) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
}
