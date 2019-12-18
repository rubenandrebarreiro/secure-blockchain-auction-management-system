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
import java.util.Map.Entry;
import java.util.Random;
import java.util.concurrent.BlockingQueue;

import javax.crypto.NoSuchPaddingException;
import javax.net.ssl.SSLPeerUnverifiedException;
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

import main.java.common.protocols.MessageType;
import main.java.common.protocols.VersionNumber;
import main.java.common.utils.CommonUtils;
import main.java.messages.secure.bid.SecureBidMessage;
import main.java.messages.secure.bid.components.SecureBidMessageComponents;
import main.java.messages.secure.bid.components.data.SecureBidMessageData;
import main.java.messages.secure.bid.components.data.confidential.SecureBidMessageDataConfidential;
import main.java.messages.secure.bid.components.data.signature.SecureBidMessageDataSignature;
import main.java.messages.secure.bid.dos.mitigation.SecureBidMessageDoSMitigation;
import main.java.messages.secure.common.header.SecureCommonHeader;
import main.java.messages.secure.common.key.exchange.SecureCommonKeyExchange;
import main.java.messages.secure.proofwork.SecureProofOfWorkMessage;
import main.java.messages.secure.receipt.SecureReceiptMessage;
import main.java.messages.secure.receipt.components.SecureReceiptMessageComponents;
import main.java.messages.secure.receipt.components.data.SecureReceiptMessageComponentsData;
import main.java.messages.secure.receipt.components.data.info.SecureReceiptMessageComponentsDataInfo;
import main.java.messages.secure.receipt.components.data.signature.SecureReceiptMessageComponentsDataSignature;
import main.java.messages.secure.receipt.dos.mitigation.SecureReceiptMessageDoSMitigation;
import main.java.messages.secure.receipt.metaheader.SecureReceiptMessageMetaHeader;
import main.java.resources.auction.Auction;
import main.java.resources.bid.Bid;
import main.java.resources.user.User;
import main.java.resources.user.UserAuctionInfo;
import main.java.sys.rest.server.auction.messageTypes.MessagePacketServerToClient;
import main.java.sys.rest.server.auction.messageTypes.MessagePacketServerToClientTypes;
import main.java.sys.rest.server.auction.messageTypes.MessagePacketClientToServer;

public class AuctionServer extends Thread{

	private static final String AUCTION_SERVER_REPOSITORY_ADDRESS = "http://localhost:8080/products-auctions";

	private static final String URL_SPACE = "%20";

	private int currentSerialNumber;

	private Gson gson;

	private HttpClient httpClient;

	private boolean exitFlag;
	
	private SSLSocket responseSocket;
	private Random random;
	private Map<String, BlockingQueue<Object>> connectedClientsMap;
	private boolean mutualAuth;
	private String userName;
	
	private Thread updateClientBidsService;

	public AuctionServer(SSLServerSocket serverSocket, SSLSocket responseSocket, Map<String, BlockingQueue<Object>> connectedClientsMap, String mutualAuth, String userName) throws IOException, NoSuchAlgorithmException, UnrecoverableKeyException, KeyStoreException, CertificateException, KeyManagementException {
		exitFlag = false;
		this.responseSocket = responseSocket;
		this.connectedClientsMap = connectedClientsMap;
		this.gson = new Gson();
		this.httpClient = HttpClients.createDefault();
		this.random = new Random();
		if(mutualAuth.contentEquals(AuctionServerEntryPoint.TLS_CONF_SERVER_ONLY))
			this.mutualAuth = false;
		else this.mutualAuth = true;		
		this.userName = userName;
		
		updateClientBidsService = new Thread(){
			public void run() {
				printStringWithClassName("Update client bids service is online for user " + userName + ".");
				while(!exitFlag) {
//					printErrorStringWithClassName("Current map status:");
//					connectedClientsMap.forEach((x,y) -> {
//						printErrorStringWithClassName(x + " " + y);
//					});
					try {
						Thread.sleep(CommonUtils.TRY_TO_CLOSE_BLOCK_OF_BIDS_SERVICE_VERIFICATION_RATE_TIME);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					BlockingQueue<Object> workQueue = connectedClientsMap.get(userName);
					if(workQueue != null) {
						try {
							while(!workQueue.isEmpty()) {
								String string = gson.toJson(workQueue.remove());
//								printStringWithClassName("Update client bids updating for user " + userName + " with bid " + string);
								sslWriteResponse(responseSocket.getOutputStream(), string, null, MessagePacketServerToClientTypes.UPDATE_CLIENT_BIDS);
							}
						} catch (ParseException | IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				printStringWithClassName("Update client bids service is going down for user " + userName + ".");
			}
		};
		updateClientBidsService.start();
	}

	public void run() {
		String arg1 = null, arg2 = null;
		HttpResponse response = null;
		String responseString = null;
		MessagePacketServerToClientTypes messageType = null;
		
		if(mutualAuth) {
			SSLSession session = responseSocket.getSession();
			Principal clientID;
			try {
				clientID = session.getPeerPrincipal();
				System.out.println("Client has been identified as: " + clientID);
			} catch (SSLPeerUnverifiedException e) {
				printStringWithClassName("Error getting peer principal!\n" + e.getMessage());
			}
		}
		
		try {
			while(!exitFlag) {
				responseString = null;
				response = null;
				String jsonMessage = sslReadRequest(responseSocket.getInputStream());
				MessagePacketClientToServer message = gson.fromJson(jsonMessage, MessagePacketClientToServer.class);
				if(message != null) {

					switch (message.getOperation()) {
					case OPEN_AUCTION:
						response = createAuction(message.getBody());
						messageType = MessagePacketServerToClientTypes.SIMPLE_RESPONSE;
						break;
					case CLOSE_AUCTION:
						response = closeAuction(message.getParamsMap().get("auction-id"));
						messageType = MessagePacketServerToClientTypes.SIMPLE_RESPONSE;
						break;
					case ADD_BID:
						arg1 = message.getParamsMap().get("auction-id");
						arg2 = message.getBody();
						responseString = addBidToOpenedProductAuction(arg1, arg2);
						messageType = MessagePacketServerToClientTypes.RECEIPT;
						break;
					case LIST_ALL_AUCTIONS:
						response = listAllProductsAuctions();
						messageType = MessagePacketServerToClientTypes.COMPLEX_RESPONSE;
						break;
					case LIST_OPENED_AUCTIONS:
						response = listOpenedProductsAuctions();
						messageType = MessagePacketServerToClientTypes.COMPLEX_RESPONSE;
						break;
					case LIST_CLOSED_AUCTIONS:
						response = listClosedProductsAuctions();
						messageType = MessagePacketServerToClientTypes.COMPLEX_RESPONSE;
						break;
					case LIST_ALL_AUCTIONS_BY_OWNER:
						response = listAllProductsAuctionsByProductOwnerUserClient(message.getParamsMap().get("product-owner-user-client-id"));
						messageType = MessagePacketServerToClientTypes.COMPLEX_RESPONSE;
						break;
					case LIST_OPENED_AUCTIONS_BY_OWNER:
						response = listOpenedProductsAuctionsByProductOwnerUserClient(message.getParamsMap().get("product-owner-user-client-id"));
						messageType = MessagePacketServerToClientTypes.COMPLEX_RESPONSE;
						break;
					case LIST_CLOSED_AUCTIONS_BY_OWNER:
						response = listClosedProductsAuctionsByProductOwnerUserClient(message.getParamsMap().get("product-owner-user-client-id"));
						messageType = MessagePacketServerToClientTypes.COMPLEX_RESPONSE;
						break;
					case LIST_ALL_AUCTIONS_BY_ID:
						response = findProductAuctionByID(message.getParamsMap().get("auction-id"));
						messageType = MessagePacketServerToClientTypes.COMPLEX_RESPONSE;
						break;
					case LIST_OPENED_AUCTIONS_BY_ID:
						response = findOpenedProductAuctionByID(message.getParamsMap().get("auction-id"));
						messageType = MessagePacketServerToClientTypes.COMPLEX_RESPONSE;
						break;
					case LIST_CLOSED_AUCTIONS_BY_ID:
						response = findClosedProductAuctionByID(message.getParamsMap().get("auction-id"));
						messageType = MessagePacketServerToClientTypes.COMPLEX_RESPONSE;
						break;
					case LIST_BIDS_OF_ALL_AUCTIONS_BY_AUCTION_ID:
						response = listAllBidsOfProductAuctionByID(message.getParamsMap().get("auction-id"));
						messageType = MessagePacketServerToClientTypes.COMPLEX_RESPONSE;
						break;
					case LIST_BIDS_OF_OPENED_AUCTIONS_BY_AUCTION_ID:
						response = listAllBidsOfOpenedProductAuctionByID(message.getParamsMap().get("auction-id"));
						messageType = MessagePacketServerToClientTypes.COMPLEX_RESPONSE;
						break;
					case LIST_BIDS_OF_CLOSED_AUCTIONS_BY_AUCTION_ID:
						response = listAllBidsOfClosedProductAuctionByID(message.getParamsMap().get("auction-id"));
						messageType = MessagePacketServerToClientTypes.COMPLEX_RESPONSE;
						break;
					case LIST_BIDS_OF_ALL_AUCTIONS_BY_AUCTION_ID_AND_CLIENT_ID:
						arg1 = message.getParamsMap().get("auction-id");
						arg2 = message.getParamsMap().get("bidder-user-client-id");
						response = listAllBidsMadeByBidderUserClientInAllProductAuctionByID(arg1, arg2);
						messageType = MessagePacketServerToClientTypes.COMPLEX_RESPONSE;
						break;
					case LIST_BIDS_OF_OPENED_AUCTIONS_BY_AUCTION_ID_AND_CLIENT_ID:
						arg1 = message.getParamsMap().get("auction-id");
						arg2 = message.getParamsMap().get("bidder-user-client-id");
						response = listAllBidsMadeByBidderUserClientInOpenedProductAuctionByID(arg1, arg2);
						messageType = MessagePacketServerToClientTypes.COMPLEX_RESPONSE;
						break;
					case LIST_BIDS_OF_CLOSED_AUCTIONS_BY_AUCTION_ID_AND_CLIENT_ID:
						arg1 = message.getParamsMap().get("auction-id");
						arg2 = message.getParamsMap().get("bidder-user-client-id");
						response = listAllBidsMadeByBidderUserClientInClosedProductAuctionByID(arg1, arg2);
						messageType = MessagePacketServerToClientTypes.COMPLEX_RESPONSE;
						break;
						
					case LIST_ALL_BIDS_BY_CLIENT_ID:
						arg1 = message.getParamsMap().get("bidder-user-client-id");
						response = listAllBidsMadeByBidderUserClientID(arg1);
						messageType = MessagePacketServerToClientTypes.COMPLEX_RESPONSE;
						break;
					case LIST_OPENED_BIDS_BY_CLIENT_ID:
						arg1 = message.getParamsMap().get("bidder-user-client-id");
						response = listOpenedBidsMadeByBidderUserClientID(arg1);
						messageType = MessagePacketServerToClientTypes.COMPLEX_RESPONSE;
						break;
					case LIST_CLOSED_BIDS_BY_CLIENT_ID:
						arg1 = message.getParamsMap().get("bidder-user-client-id");
						response = listClosedBidsMadeByBidderUserClientID(arg1);
						messageType = MessagePacketServerToClientTypes.COMPLEX_RESPONSE;
						break;
					
					
					case CHECK_OUTCOME_ALL_AUCTION:
						arg1 = message.getParamsMap().get("bidder-user-client-id");
						response = checkOutcomeAllAuctionsByAuctionID(arg1, arg2);
						messageType = MessagePacketServerToClientTypes.COMPLEX_RESPONSE;
						break;
					case CHECK_OUTCOME_OPENED_AUCTION:
						arg1 = message.getParamsMap().get("bidder-user-client-id");
						response = checkOutcomeOpenedAuctionsByAuctionID(arg1, arg2);
						messageType = MessagePacketServerToClientTypes.COMPLEX_RESPONSE;
						break;
					case CHECK_OUTCOME_CLOSED_AUCTION:
						arg1 = message.getParamsMap().get("bidder-user-client-id");
						response = checkOutcomeClosedAuctionsByAuctionID(arg1, arg2);
						messageType = MessagePacketServerToClientTypes.COMPLEX_RESPONSE;
						break;
					
					
					case CHECK_OUTCOME_ALL_AUCTION_ID:
						arg1 = message.getParamsMap().get("bidder-user-client-id");
						arg2 = message.getParamsMap().get("auction-id");
						response = checkOutcomeAllAuctionsByAuctionID(arg1, arg2);
						messageType = MessagePacketServerToClientTypes.COMPLEX_RESPONSE;
						break;
					case CHECK_OUTCOME_OPENED_AUCTION_ID:
						arg1 = message.getParamsMap().get("bidder-user-client-id");
						arg2 = message.getParamsMap().get("auction-id");
						response = checkOutcomeOpenedAuctionsByAuctionID(arg1, arg2);
						messageType = MessagePacketServerToClientTypes.COMPLEX_RESPONSE;
						break;
					case CHECK_OUTCOME_CLOSED_AUCTION_ID:
						arg1 = message.getParamsMap().get("bidder-user-client-id");
						arg2 = message.getParamsMap().get("auction-id");
						response = checkOutcomeClosedAuctionsByAuctionID(arg1, arg2);
						messageType = MessagePacketServerToClientTypes.COMPLEX_RESPONSE;
						break;						
					
					case PROOF_WORK_SENT:
						messageType = MessagePacketServerToClientTypes.PROOF_OF_WORK;
						responseString = "";
						handleReceivedProofOfWork(message.getBody());
						break;
					default:
						break;
					}
					sslWriteResponse(responseSocket.getOutputStream(), responseString, response, messageType);
				}
				else {
					// Not supposed to get null messages Except is the client disconnects.
					connectedClientsMap.remove(userName);
					exitFlag = true;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.getMessage();
			e.printStackTrace();
		}
	}

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

		printStringWithClassName(response.getStatusLine());
		printStringWithClassName(response.getEntity());
		return response;
	}

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

		printStringWithClassName(response.getStatusLine());
		printStringWithClassName(response.getEntity());
		return response;
	}

	public String addBidToOpenedProductAuction(String openedAuctionID, String userBidInfo)
			throws SQLException, NoSuchAlgorithmException, InvalidKeyException, SignatureException, NoSuchProviderException, NoSuchPaddingException, InvalidAlgorithmParameterException, ClientProtocolException, IOException {
		System.out.println("[" + this.getClass().getCanonicalName() + "]: " +
				"Received request to add bid to " + openedAuctionID + "!");

		String url = AUCTION_SERVER_REPOSITORY_ADDRESS + "/add-bid-to-opened-auction/" + openedAuctionID;
		url = removeSpaceFromURL(url);
		HttpPost postRequest = new HttpPost(url);
		HttpResponse response = null;
		String methodResult = null;
		SecureBidMessage bidInfo = gson.fromJson(userBidInfo, SecureBidMessage.class);
		
		SecureBidMessage serializedBidInfo = new SecureBidMessage(bidInfo.getSecureBidMessageSerialized());
		serializedBidInfo.undoSecureBidMessageSerialized();
		byte[] initialisationVectorInBytes = new byte[16];
		//Extract IV
		System.arraycopy(bidInfo.getSecureBidMessageSerialized(), 0,
				initialisationVectorInBytes,
				0, initialisationVectorInBytes.length);

		SecureBidMessageData secureBidMessageData = null;
		SecureCommonKeyExchange secureBidMessageKeyExchange = serializedBidInfo.getSecureBidMessageKeyExchange();
		SecureBidMessageDoSMitigation secureBidMessageDosMitigation = serializedBidInfo.getSecureBidMessageDoSMitigation();
		SecureBidMessageComponents secureBidMessageComponents = serializedBidInfo.getSecureBidMessageComponents();
		
		secureBidMessageKeyExchange.buildSecureCommonKeyExchangeReceived();
		
		if(secureBidMessageKeyExchange.getIsSecureCommonKeyExchangeSerializedCipheredSignedValid()) {
						
			if(secureBidMessageDosMitigation.checkIfHashOfSecureBidMessageDoSMitigationIsValid()) {
				
				secureBidMessageComponents.setIV(initialisationVectorInBytes);
				secureBidMessageComponents.undoSecureBidMessageComponentsSerialization();

				secureBidMessageData = secureBidMessageComponents.getSecureBidMessageData();
				

				SecureBidMessageDataSignature secureBidMessageDataSignature = secureBidMessageData.getSecureBidMessageDataSignature();
				secureBidMessageDataSignature.buildSecureBidMessageDataSignatureReceived();
				
				SecureBidMessageDataConfidential secureBidMessageDataConfidential = secureBidMessageData.getSecureBidMessageDataConfidential();
				secureBidMessageDataConfidential.buildSecureBidMessageDataConfidentialReceived();
				
				
				String bidJson = gson.toJson(secureBidMessageData.getSecureBidMessageDataSignature().getBid());
				postRequest.setEntity(new StringEntity(bidJson));
				postRequest.setHeader("Accept", "application/json");
				postRequest.setHeader("Content-type", "application/json");
				
				
				response = httpClient.execute(postRequest);
				
				printStringWithClassName(response.getStatusLine());
				printStringWithClassName(response.getEntity());
				
				
				
				Bid bid = secureBidMessageData.getSecureBidMessageDataSignature().getBid();
				
				SecureReceiptMessageComponentsDataInfo secureReceiptMessageComponentsDataInfo = 
						new SecureReceiptMessageComponentsDataInfo(bid.getBidID(), bid.getBidderUserClientID(),
																   bid.getBidTimestamp());

				secureReceiptMessageComponentsDataInfo.doSecureReceiptMessageComponentsDataInfoSerialization();
				
				byte[] secureReceiptMessageComponentsDataInfoSerialized = 
						secureReceiptMessageComponentsDataInfo.getSecureReceiptMessageComponentsDataInfoSerialized();
				
				
				
				SecureReceiptMessageComponentsDataSignature secureReceiptMessageComponentsDataSignature = 
						new SecureReceiptMessageComponentsDataSignature(secureReceiptMessageComponentsDataInfoSerialized,
																		bid.getBidderUserClientID());
				secureReceiptMessageComponentsDataSignature.signSecureReceiptMessageComponentsDataInfoSignature();
				
				
				
				String responseStringRepresentation = response.getStatusLine().toString();
								
				SecureReceiptMessageComponentsData secureReceiptMessageComponentsData = 
						new SecureReceiptMessageComponentsData(secureReceiptMessageComponentsDataInfo,
															   secureReceiptMessageComponentsDataSignature,
															   responseStringRepresentation);
				
				secureReceiptMessageComponentsData.doSecureReceiptMessageComponentsDataSerialization();
								
				SecureCommonHeader secureCommonHeader = new SecureCommonHeader(VersionNumber.VERSION_01.getVersionNumber(),
																			   MessageType.MESSAGE_TYPE_2.getMessageType(),
																			   System.currentTimeMillis());
				
				SecureReceiptMessageComponents secureReceiptMessageComponents = 
						new SecureReceiptMessageComponents(secureCommonHeader, secureReceiptMessageComponentsData);
				
				secureReceiptMessageComponents.doSecureReceiptMessageComponentsSerialization();
				secureReceiptMessageComponents.encryptSecureReceiptMessageComponents();
				
				byte[] secureReceiptMessageComponentsCiphered = secureReceiptMessageComponents
																.getSecureReceiptMessageComponentsSerializedCiphered();
				
				SecureReceiptMessageDoSMitigation secureReceiptMessageDoSMitigation = 
						new SecureReceiptMessageDoSMitigation(secureReceiptMessageComponents);
				
				secureReceiptMessageDoSMitigation.doHashOfSecureReceiptMessageDoSMitigation();
				
				
				byte[] secretSymmetricKeyInBytes = secureReceiptMessageComponents
												   .getSecretSymmetricKeyForComponentsInBytes();
				byte[] secretHMACKeyForDoSMitigationInBytes = secureReceiptMessageDoSMitigation
															  .getSecretHMACKeyForDoSMitigationInBytes();
				
				SecureCommonKeyExchange secureCommonKeyExchange = 
						new SecureCommonKeyExchange(secretSymmetricKeyInBytes,
													secretHMACKeyForDoSMitigationInBytes,
													bid.getBidderUserClientID());
				
				secureCommonKeyExchange.buildSecureCommonKeyExchangeToSend();
				
				byte[] secureReceiptMessageKeyExchangeSerializedCiphered = 
						secureCommonKeyExchange.getSecureCommonKeyExchangeSerializedCiphered();
				
				byte[] secureReceiptMessageKeyExchangeSerializedCipheredSigned = 
						secureCommonKeyExchange.getSecureCommonKeyExchangeSerializedCipheredSigned();
				
				byte[] secureReceiptMessageComponentsDataSerialized = 
						secureReceiptMessageComponentsData.getSecureReceiptMessageComponentsDataSerialized();

				byte[] secureReceiptMessageDoSMitigationSerialized = 
								secureReceiptMessageDoSMitigation.getSecureReceiptMessageComponentsHashedForDoSMitigation();
				
				byte[] secureReceiptMessageComponentsDataSignatureSerialized = 
								secureReceiptMessageComponentsDataSignature
								   		.getSecureReceiptMessageComponentsDataInfoSerializedDigitalSigned();
				
				byte[] secureReceiptMessageComponentsInitialisationVector = 
						secureReceiptMessageComponents.getInitialisationVectorInBytes();
				
				SecureReceiptMessageMetaHeader secureReceiptMessageMetaHeader = 
						new SecureReceiptMessageMetaHeader(bid.getSizeOfBidderUserClientID(),
														   secureReceiptMessageKeyExchangeSerializedCiphered.length,
														   secureReceiptMessageKeyExchangeSerializedCipheredSigned.length,
														   secureReceiptMessageComponentsCiphered.length,
														   secureReceiptMessageDoSMitigationSerialized.length,
														   secureReceiptMessageComponentsDataSerialized.length,
														   secureReceiptMessageComponentsDataInfoSerialized.length,
														   secureReceiptMessageComponentsDataSignatureSerialized.length,
														   responseStringRepresentation.length(),
														   bid.getSizeOfBidderUserClientID());
				
				secureReceiptMessageMetaHeader.doSecureReceiptMessageMetaHeaderSerialization();
				
				
				SecureReceiptMessage secureReceiptMessage = 
						new SecureReceiptMessage(secureReceiptMessageMetaHeader,
												 bid.getBidderUserClientID(),
												 secureReceiptMessageComponentsInitialisationVector,
												 secureCommonKeyExchange,
												 secureReceiptMessageComponents,
												 secureReceiptMessageDoSMitigation);
				
				secureReceiptMessage.doSecureReceiptMessageSerialized();
				
				
				byte[] secureReceiptMessageSerialized = secureReceiptMessage.getSecureReceiptMessageSerialized();

				// If create bid was successful, add bid to send to other clients queue
				if(response.getStatusLine().getStatusCode() == 202) {
					for (Entry<String, BlockingQueue<Object>> entry : connectedClientsMap.entrySet()) {
						if(!entry.getKey().equals(userName))
							entry.getValue().add(secureBidMessageData.getSecureBidMessageDataSignature().getBid());
					}
//					printStringWithClassName("Bids to send to: ");
//					connectedClientsMap.forEach( (x,y) -> {
//						System.out.println(x + " " + y);
//					});
				}

				methodResult = java.util.Base64.getEncoder().encodeToString(secureReceiptMessageSerialized);
			}
			
		}
		
		return methodResult;
	}

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

		printStringWithClassName(response.getStatusLine());
		printStringWithClassName(response.getEntity());
		return response;
	}

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

		printStringWithClassName(response.getStatusLine());
		printStringWithClassName(response.getEntity());
		return response;
	}

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

		printStringWithClassName(response.getStatusLine());
		printStringWithClassName(response.getEntity());
		return response;
	}

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

		printStringWithClassName(response.getStatusLine());
		printStringWithClassName(response.getEntity());
		return response;
	}

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

		printStringWithClassName(response.getStatusLine());
		printStringWithClassName(response.getEntity());
		return response;
	}

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

		printStringWithClassName(response.getStatusLine());
		printStringWithClassName(response.getEntity());
		return response;
	}

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

		printStringWithClassName(response.getStatusLine());
		printStringWithClassName(response.getEntity());
		return response;
	}

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

		printStringWithClassName(response.getStatusLine());
		printStringWithClassName(response.getEntity());
		return response;
	}

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

		printStringWithClassName(response.getStatusLine());
		printStringWithClassName(response.getEntity());
		return response;
	}

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

		printStringWithClassName(response.getStatusLine());
		printStringWithClassName(response.getEntity());
		return response;
	}

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

		printStringWithClassName(response.getStatusLine());
		printStringWithClassName(response.getEntity());
		return response;
	}

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

		printStringWithClassName(response.getStatusLine());
		printStringWithClassName(response.getEntity());
		return response;
	}

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

		printStringWithClassName(response.getStatusLine());
		printStringWithClassName(response.getEntity());
		return response;
	}

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

		printStringWithClassName(response.getStatusLine());
		printStringWithClassName(response.getEntity());
		return response;
	}

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

		printStringWithClassName(response.getStatusLine());
		printStringWithClassName(response.getEntity());
		return response;
	}
	
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

		printStringWithClassName(response.getStatusLine());
		printStringWithClassName(response.getEntity());
		return response;
	}
	
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

		printStringWithClassName(response.getStatusLine());
		printStringWithClassName(response.getEntity());
		return response;
	}
	
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

		printStringWithClassName(response.getStatusLine());
		printStringWithClassName(response.getEntity());
		return response;
	}
	
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

		printStringWithClassName(response.getStatusLine());
		printStringWithClassName(response.getEntity());
		return response;
	}

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

		printStringWithClassName(response.getStatusLine());
		printStringWithClassName(response.getEntity());
		return response;
	}
	
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

		printStringWithClassName(response.getStatusLine());
		printStringWithClassName(response.getEntity());
		return response;
	}
	
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

		printStringWithClassName(response.getStatusLine());
		printStringWithClassName(response.getEntity());
		return response;
	}

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

		printStringWithClassName(response.getStatusLine());
		printStringWithClassName(response.getEntity());
		return response;
	}
	
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
		
		printStringWithClassName(response.getStatusLine());
		printStringWithClassName(response.getEntity());
		return response;
	}
	
	// TODO Change to proof of work message!
	private void handleReceivedProofOfWork(String proofOfWorkSerializedJson) {
		printStringWithClassName("COMPLETE ME! (handleReceivedProofOfWork)");
		SecureProofOfWorkMessage proofOfWork = gson.fromJson(proofOfWorkSerializedJson, SecureProofOfWorkMessage.class);
		// Validate proof. If valid, broadcast to clients.
		connectedClientsMap.forEach((x,y) -> {
			if(!x.equals(userName))
				y.add(proofOfWork);
		});
	}
	
	private String sslReadRequest(InputStream socketInStream) throws IOException {
		StringBuilder builder = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(socketInStream));
		builder.append(br.readLine());

		return builder.toString();
	}

	private void sslWriteResponse(OutputStream socketOutStream, String notResponseMessage, HttpResponse response, MessagePacketServerToClientTypes messageType) throws ParseException, IOException {
		PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(socketOutStream));
		String httpResponseMessage = null;
		MessagePacketServerToClient messagePacket = null;
		if(notResponseMessage != null && (messageType == MessagePacketServerToClientTypes.RECEIPT || messageType == MessagePacketServerToClientTypes.UPDATE_CLIENT_BIDS)) {
			httpResponseMessage = notResponseMessage;
			messagePacket = new MessagePacketServerToClient(messageType, httpResponseMessage);
		}
		else {
			httpResponseMessage = response.getStatusLine().toString();
			if(response.getEntity() != null && response.getEntity().getContentLength() != 0)
				httpResponseMessage = EntityUtils.toString(response.getEntity(), Charset.forName("UTF-8"));
			messagePacket = new MessagePacketServerToClient(messageType, httpResponseMessage);
		}
		String messagePacketJson = gson.toJson(messagePacket);
//		printStringWithClassName("Sending -> " + messagePacketJson);
		printWriter.print(messagePacketJson + System.lineSeparator());
		printWriter.flush();
	}
	
	private String removeSpaceFromURL(String url) {
		return url.replace(" ", URL_SPACE);
	}
	
	private void printStringWithClassName(Object message) {
		System.out.println("[" + this.getClass().getCanonicalName() + "]: " + message);
	}
}
