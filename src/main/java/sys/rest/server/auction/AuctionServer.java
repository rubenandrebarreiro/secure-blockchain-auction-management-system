package main.java.sys.rest.server.auction;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Principal;
import java.security.SignatureException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.sql.SQLException;
import java.util.Map;
import java.util.Random;

import javax.crypto.NoSuchPaddingException;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;

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

import com.google.gson.Gson;

import main.java.api.rest.server.auction.AuctionServerAPI;
import main.java.messages.secure.bid.SecureBidMessage;
import main.java.messages.secure.bid.components.SecureBidMessageComponents;
import main.java.messages.secure.bid.components.data.SecureBidMessageData;
import main.java.messages.secure.bid.components.data.confidential.SecureBidMessageDataConfidential;
import main.java.messages.secure.bid.components.data.signature.SecureBidMessageDataSignature;
import main.java.messages.secure.bid.dos.mitigation.SecureBidMessageDoSMitigation;
import main.java.messages.secure.common.key.exchange.SecureCommonKeyExchange;
import main.java.resources.auction.Auction;
import main.java.resources.user.User;
import main.java.resources.user.UserAuctionInfo;
import main.java.sys.SSLSocketMessage;
import main.java.sys.rest.server.auction.messageTypes.MessageEnvelope;
import main.java.sys.rest.server.auction.messageTypes.MessageEnvelopeTypes;

public class AuctionServer extends Thread implements AuctionServerAPI{

	private static final String AUCTION_SERVER_REPOSITORY_ADDRESS = "http://localhost:8080/products-auctions";

	private static final String URL_SPACE = "%20";

	private int currentSerialNumber;

	private Gson gson;

	HttpClient httpClient;

	private SSLSocket responseSocket;
	private Random random;
	private Map<String, String> connectedClientsMap;
	private boolean mutualAuth;

	public AuctionServer(SSLServerSocket serverSocket, SSLSocket responseSocket, Map<String, String> connectedClientsMap, String mutualAuth) throws IOException, NoSuchAlgorithmException, UnrecoverableKeyException, KeyStoreException, CertificateException, KeyManagementException {
		this.responseSocket = responseSocket;
		this.connectedClientsMap = connectedClientsMap;
		this.gson = new Gson();
		this.httpClient = HttpClients.createDefault();
		this.random = new Random();
		if(mutualAuth.contentEquals(AuctionServerEntryPoint.TLS_CONF_SERVER_ONLY))
			this.mutualAuth = false;
		else this.mutualAuth = true;		
	}

	public void run() {
		String arg1 = null, arg2 = null;
		HttpResponse response = null;
		MessageEnvelopeTypes messageType = null;

		try {
			while(true) {
				System.out.println("Connected clients are: " );
				connectedClientsMap.forEach((x,y) -> System.out.println(x + " " + y));
				String jsonMessage = sslReadRequest(responseSocket.getInputStream());
				if(mutualAuth) {
					SSLSession session = responseSocket.getSession();
					Principal clientID = session.getPeerPrincipal();
					System.out.println("Client has been identified as: " + clientID);
				}
				SSLSocketMessage message = gson.fromJson(jsonMessage, SSLSocketMessage.class);
				if(message != null) {

					switch (message.getOperation()) {
					case OPEN_AUCTION:
						response = createAuction(message.getBody());
						messageType = MessageEnvelopeTypes.SIMPLE_RESPONSE;
						break;
					case CLOSE_AUCTION:
						response = closeAuction(message.getParamsMap().get("auction-id"));
						messageType = MessageEnvelopeTypes.SIMPLE_RESPONSE;
						break;
					case ADD_BID:
						arg1 = message.getParamsMap().get("auction-id");
						arg2 = message.getBody();
						response = addBidToOpenedProductAuction(arg1, arg2);
						messageType = MessageEnvelopeTypes.SIMPLE_RESPONSE;
						break;
					case LIST_ALL_AUCTIONS:
						response = listAllProductsAuctions();
						messageType = MessageEnvelopeTypes.COMPLEX_RESPONSE;
						break;
					case LIST_OPENED_AUCTIONS:
						response = listOpenedProductsAuctions();
						messageType = MessageEnvelopeTypes.COMPLEX_RESPONSE;
						break;
					case LIST_CLOSED_AUCTIONS:
						response = listClosedProductsAuctions();
						messageType = MessageEnvelopeTypes.COMPLEX_RESPONSE;
						break;
					case LIST_ALL_AUCTIONS_BY_OWNER:
						response = listAllProductsAuctionsByProductOwnerUserClient(message.getParamsMap().get("product-owner-user-client-id"));
						messageType = MessageEnvelopeTypes.COMPLEX_RESPONSE;
						break;
					case LIST_OPENED_AUCTIONS_BY_OWNER:
						response = listOpenedProductsAuctionsByProductOwnerUserClient(message.getParamsMap().get("product-owner-user-client-id"));
						messageType = MessageEnvelopeTypes.COMPLEX_RESPONSE;
						break;
					case LIST_CLOSED_AUCTIONS_BY_OWNER:
						response = listClosedProductsAuctionsByProductOwnerUserClient(message.getParamsMap().get("product-owner-user-client-id"));
						messageType = MessageEnvelopeTypes.COMPLEX_RESPONSE;
						break;
					case LIST_ALL_AUCTIONS_BY_ID:
						response = findProductAuctionByID(message.getParamsMap().get("auction-id"));
						messageType = MessageEnvelopeTypes.COMPLEX_RESPONSE;
						break;
					case LIST_OPENED_AUCTIONS_BY_ID:
						response = findOpenedProductAuctionByID(message.getParamsMap().get("auction-id"));
						messageType = MessageEnvelopeTypes.COMPLEX_RESPONSE;
						break;
					case LIST_CLOSED_AUCTIONS_BY_ID:
						response = findClosedProductAuctionByID(message.getParamsMap().get("auction-id"));
						messageType = MessageEnvelopeTypes.COMPLEX_RESPONSE;
						break;
					case LIST_BIDS_OF_ALL_AUCTIONS_BY_AUCTION_ID:
						response = listAllBidsOfProductAuctionByID(message.getParamsMap().get("auction-id"));
						messageType = MessageEnvelopeTypes.COMPLEX_RESPONSE;
						break;
					case LIST_BIDS_OF_OPENED_AUCTIONS_BY_AUCTION_ID:
						response = listAllBidsOfOpenedProductAuctionByID(message.getParamsMap().get("auction-id"));
						messageType = MessageEnvelopeTypes.COMPLEX_RESPONSE;
						break;
					case LIST_BIDS_OF_CLOSED_AUCTIONS_BY_AUCTION_ID:
						response = listAllBidsOfClosedProductAuctionByID(message.getParamsMap().get("auction-id"));
						messageType = MessageEnvelopeTypes.COMPLEX_RESPONSE;
						break;
					case LIST_BIDS_OF_ALL_AUCTIONS_BY_AUCTION_ID_AND_CLIENT_ID:
						arg1 = message.getParamsMap().get("auction-id");
						arg2 = message.getParamsMap().get("bidder-user-client-id");
						response = listAllBidsMadeByBidderUserClientInAllProductAuctionByID(arg1, arg2);
						messageType = MessageEnvelopeTypes.COMPLEX_RESPONSE;
						break;
					case LIST_BIDS_OF_OPENED_AUCTIONS_BY_AUCTION_ID_AND_CLIENT_ID:
						arg1 = message.getParamsMap().get("auction-id");
						arg2 = message.getParamsMap().get("bidder-user-client-id");
						response = listAllBidsMadeByBidderUserClientInOpenedProductAuctionByID(arg1, arg2);
						messageType = MessageEnvelopeTypes.COMPLEX_RESPONSE;
						break;
					case LIST_BIDS_OF_CLOSED_AUCTIONS_BY_AUCTION_ID_AND_CLIENT_ID:
						arg1 = message.getParamsMap().get("auction-id");
						arg2 = message.getParamsMap().get("bidder-user-client-id");
						response = listAllBidsMadeByBidderUserClientInClosedProductAuctionByID(arg1, arg2);
						messageType = MessageEnvelopeTypes.COMPLEX_RESPONSE;
						break;
						
					case LIST_ALL_BIDS_BY_CLIENT_ID:
						arg1 = message.getParamsMap().get("bidder-user-client-id");
						response = listAllBidsMadeByBidderUserClientID(arg1);
						messageType = MessageEnvelopeTypes.COMPLEX_RESPONSE;
						break;
					case LIST_OPENED_BIDS_BY_CLIENT_ID:
						arg1 = message.getParamsMap().get("bidder-user-client-id");
						response = listOpenedBidsMadeByBidderUserClientID(arg1);
						messageType = MessageEnvelopeTypes.COMPLEX_RESPONSE;
						break;
					case LIST_CLOSED_BIDS_BY_CLIENT_ID:
						arg1 = message.getParamsMap().get("bidder-user-client-id");
						response = listClosedBidsMadeByBidderUserClientID(arg1);
						messageType = MessageEnvelopeTypes.COMPLEX_RESPONSE;
						break;
					
					
					case CHECK_OUTCOME_ALL_AUCTION:
						arg1 = message.getParamsMap().get("bidder-user-client-id");
						response = checkOutcomeAllAuctionsByAuctionID(arg1, arg2);
						messageType = MessageEnvelopeTypes.COMPLEX_RESPONSE;
						break;
					case CHECK_OUTCOME_OPENED_AUCTION:
						arg1 = message.getParamsMap().get("bidder-user-client-id");
						response = checkOutcomeOpenedAuctionsByAuctionID(arg1, arg2);
						messageType = MessageEnvelopeTypes.COMPLEX_RESPONSE;
						break;
					case CHECK_OUTCOME_CLOSED_AUCTION:
						arg1 = message.getParamsMap().get("bidder-user-client-id");
						response = checkOutcomeClosedAuctionsByAuctionID(arg1, arg2);
						messageType = MessageEnvelopeTypes.COMPLEX_RESPONSE;
						break;
					
					
					case CHECK_OUTCOME_ALL_AUCTION_ID:
						arg1 = message.getParamsMap().get("bidder-user-client-id");
						arg2 = message.getParamsMap().get("auction-id");
						response = checkOutcomeAllAuctionsByAuctionID(arg1, arg2);
						messageType = MessageEnvelopeTypes.COMPLEX_RESPONSE;
						break;
					case CHECK_OUTCOME_OPENED_AUCTION_ID:
						arg1 = message.getParamsMap().get("bidder-user-client-id");
						arg2 = message.getParamsMap().get("auction-id");
						response = checkOutcomeOpenedAuctionsByAuctionID(arg1, arg2);
						messageType = MessageEnvelopeTypes.COMPLEX_RESPONSE;
						break;
					case CHECK_OUTCOME_CLOSED_AUCTION_ID:
						arg1 = message.getParamsMap().get("bidder-user-client-id");
						arg2 = message.getParamsMap().get("auction-id");
						response = checkOutcomeClosedAuctionsByAuctionID(arg1, arg2);
						messageType = MessageEnvelopeTypes.COMPLEX_RESPONSE;
						break;						
						
					default:
						//TODO Error somewhere?
						break;
					}
					sslWriteResponse(responseSocket.getOutputStream(), response, messageType);
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
				String.valueOf(random.nextInt()), 
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

		printErrorStringWithClassName(response.getStatusLine());
		printErrorStringWithClassName(response.getEntity());
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

		printErrorStringWithClassName(response.getStatusLine());
		printErrorStringWithClassName(response.getEntity());
		return response;
	}

	@Override
	public HttpResponse addBidToOpenedProductAuction(String openedAuctionID, String userBidInfo)
			throws SQLException, NoSuchAlgorithmException, InvalidKeyException, SignatureException, NoSuchProviderException, NoSuchPaddingException, InvalidAlgorithmParameterException, ClientProtocolException, IOException {
		System.out.println("[" + this.getClass().getCanonicalName() + "]: " +
				"Received request to add bid to " + openedAuctionID + "!");

		String url = AUCTION_SERVER_REPOSITORY_ADDRESS + "/add-bid-to-opened-auction/" + openedAuctionID;
		url = removeSpaceFromURL(url);
		HttpPost postRequest = new HttpPost(url);
		HttpResponse response = null;
		SecureBidMessage bidInfo = gson.fromJson(userBidInfo, SecureBidMessage.class);
		
		SecureBidMessage serializedBidInfo = new SecureBidMessage(bidInfo.getSecureBidMessageSerialized());
		serializedBidInfo.undoSecureBidMessageSerialized();

		SecureBidMessageData secureBidMessageData = null;
		SecureCommonKeyExchange secureBidMessageKeyExchange = serializedBidInfo.getSecureBidMessageKeyExchange();
		SecureBidMessageDoSMitigation secureBidMessageDosMitigation = serializedBidInfo.getSecureBidMessageDoSMitigation();
		SecureBidMessageComponents secureBidMessageComponents = serializedBidInfo.getSecureBidMessageComponents();
		
		secureBidMessageKeyExchange.buildSecureCommonKeyExchangeReceived();
		
		if(secureBidMessageKeyExchange.getIsSecureCommonKeyExchangeSerializedCipheredSignedValid()) {
						
			if(secureBidMessageDosMitigation.checkIfHashOfSecureBidMessageDoSMitigationIsValid()) {
				
				secureBidMessageComponents.undoSecureBidMessageComponentsSerialization();

				secureBidMessageData = secureBidMessageComponents.getSecureBidMessageData();
				

				SecureBidMessageDataSignature secureBidMessageDataSignature = secureBidMessageData.getSecureBidMessageDataSignature();
				secureBidMessageDataSignature.buildSecureBidMessageDataSignatureReceived();
				
				SecureBidMessageDataConfidential secureBidMessageDataConfidential = secureBidMessageData.getSecureBidMessageDataConfidential();
				secureBidMessageDataConfidential.buildSecureBidMessageDataConfidentialReceived();
				
				
				String bidJson = gson.toJson(secureBidMessageData.getSecureBidMessageDataSignature().getBid());
				System.err.println("Bid is: " + secureBidMessageData.getSecureBidMessageDataSignature().getBid());
				postRequest.setEntity(new StringEntity(bidJson));
				postRequest.setHeader("Accept", "application/json");
				postRequest.setHeader("Content-type", "application/json");

			}
			
		}
		response = httpClient.execute(postRequest);	
		printErrorStringWithClassName(response.getStatusLine());
		printErrorStringWithClassName(response.getEntity());
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

		printErrorStringWithClassName(response.getStatusLine());
		printErrorStringWithClassName(response.getEntity());
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

		printErrorStringWithClassName(response.getStatusLine());
		printErrorStringWithClassName(response.getEntity());
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

		printErrorStringWithClassName(response.getStatusLine());
		printErrorStringWithClassName(response.getEntity());
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

		printErrorStringWithClassName(response.getStatusLine());
		printErrorStringWithClassName(response.getEntity());
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

		printErrorStringWithClassName(response.getStatusLine());
		printErrorStringWithClassName(response.getEntity());
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

		printErrorStringWithClassName(response.getStatusLine());
		printErrorStringWithClassName(response.getEntity());
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

		printErrorStringWithClassName(response.getStatusLine());
		printErrorStringWithClassName(response.getEntity());
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

		printErrorStringWithClassName(response.getStatusLine());
		printErrorStringWithClassName(response.getEntity());
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

		printErrorStringWithClassName(response.getStatusLine());
		printErrorStringWithClassName(response.getEntity());
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

		printErrorStringWithClassName(response.getStatusLine());
		printErrorStringWithClassName(response.getEntity());
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

		printErrorStringWithClassName(response.getStatusLine());
		printErrorStringWithClassName(response.getEntity());
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

		printErrorStringWithClassName(response.getStatusLine());
		printErrorStringWithClassName(response.getEntity());
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

		printErrorStringWithClassName(response.getStatusLine());
		printErrorStringWithClassName(response.getEntity());
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

		printErrorStringWithClassName(response.getStatusLine());
		printErrorStringWithClassName(response.getEntity());
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

		printErrorStringWithClassName(response.getStatusLine());
		printErrorStringWithClassName(response.getEntity());
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

		printErrorStringWithClassName(response.getStatusLine());
		printErrorStringWithClassName(response.getEntity());
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

		printErrorStringWithClassName(response.getStatusLine());
		printErrorStringWithClassName(response.getEntity());
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

		printErrorStringWithClassName(response.getStatusLine());
		printErrorStringWithClassName(response.getEntity());
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

		printErrorStringWithClassName(response.getStatusLine());
		printErrorStringWithClassName(response.getEntity());
		return response;
	}

	@Override
	public HttpResponse checkOutcomeOpenedAuctions(String bidderUserClientID) throws SQLException {
		System.out.println("[" + this.getClass().getCanonicalName() + "]: " +
				"Received request to check the outcome from opened auctions where " + bidderUserClientID + " participated!");
		
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

		printErrorStringWithClassName(response.getStatusLine());
		printErrorStringWithClassName(response.getEntity());
		return response;
	}
	
	@Override
	public HttpResponse checkOutcomeClosedAuctions(String bidderUserClientID) throws SQLException {
		System.out.println("[" + this.getClass().getCanonicalName() + "]: " +
				"Received request to check the outcome from closed auctions where " + bidderUserClientID + " participated!");
		
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

		printErrorStringWithClassName(response.getStatusLine());
		printErrorStringWithClassName(response.getEntity());
		return response;
	}
	
	@Override
	public HttpResponse checkOutcomeAllAuctionsByAuctionID(String bidderUserClientID, String auctionID) throws SQLException {
		System.out.println("[" + this.getClass().getCanonicalName() + "]: " +
				"Received request to check the outcome from closed auctions with ID " + auctionID + "where " + bidderUserClientID + " participated!");
		
		String url = AUCTION_SERVER_REPOSITORY_ADDRESS + "/outcome/all/" + auctionID + "/" + bidderUserClientID;
		url = removeSpaceFromURL(url);
		HttpGet getRequest = new HttpGet(url);
		HttpResponse response = null;
		try {
			response = httpClient.execute(getRequest);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		printErrorStringWithClassName(response.getStatusLine());
		printErrorStringWithClassName(response.getEntity());
		return response;
	}

	@Override
	public HttpResponse checkOutcomeOpenedAuctionsByAuctionID(String bidderUserClientID, String auctionID) throws SQLException {
		System.out.println("[" + this.getClass().getCanonicalName() + "]: " +
				"Received request to check the outcome from opened auctions with ID " + auctionID + "where " + bidderUserClientID + " participated!");
		
		String url = AUCTION_SERVER_REPOSITORY_ADDRESS + "/outcome/opened/" + auctionID + "/" + bidderUserClientID;
		url = removeSpaceFromURL(url);
		HttpGet getRequest = new HttpGet(url);
		HttpResponse response = null;
		try {
			response = httpClient.execute(getRequest);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		printErrorStringWithClassName(response.getStatusLine());
		printErrorStringWithClassName(response.getEntity());
		return response;
	}
	
	@Override
	public HttpResponse checkOutcomeClosedAuctionsByAuctionID(String bidderUserClientID, String auctionID) throws SQLException {
		System.out.println("[" + this.getClass().getCanonicalName() + "]: " +
				"Received request to check the outcome from closed auctions with ID " + auctionID + "where " + bidderUserClientID + " participated!");
		
		String url = AUCTION_SERVER_REPOSITORY_ADDRESS + "/outcome/closed/" + auctionID + "/" + bidderUserClientID;
		url = removeSpaceFromURL(url);
		HttpGet getRequest = new HttpGet(url);
		HttpResponse response = null;
		try {
			response = httpClient.execute(getRequest);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		printErrorStringWithClassName(response.getStatusLine());
		printErrorStringWithClassName(response.getEntity());
		return response;
	}
	
	
	private String sslReadRequest(InputStream socketInStream) throws IOException {
		StringBuilder builder = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(socketInStream));
		builder.append(br.readLine());

		return builder.toString();
	}

	private void sslWriteResponse(OutputStream socketOutStream, HttpResponse response, MessageEnvelopeTypes messageType) throws ParseException, IOException {
		String message = response.getStatusLine().toString();
		printErrorStringWithClassName("SENDING SOMETHING!\n" + message);
		if(response.getEntity() != null && response.getEntity().getContentLength() != 0)
			message = EntityUtils.toString(response.getEntity(), Charset.forName("UTF-8"));
		MessageEnvelope envelope = new MessageEnvelope(messageType, message);
		String envelopeJson = gson.toJson(envelope);
		PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(socketOutStream));
		printWriter.print(envelopeJson + System.lineSeparator());
		printWriter.flush();
	}
	
	private String removeSpaceFromURL(String url) {
		return url.replace(" ", URL_SPACE);
	}
	
	private void printErrorStringWithClassName(Object message) {
		System.err.println("[" + this.getClass().getCanonicalName() + "] " + 
				"Response: " + message);
	}
}
