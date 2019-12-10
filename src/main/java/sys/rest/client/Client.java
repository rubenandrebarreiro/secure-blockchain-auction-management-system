package main.java.sys.rest.client;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SignatureException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Random;

import javax.crypto.NoSuchPaddingException;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import org.bouncycastle.util.encoders.Hex;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;

import main.java.api.rest.client.ClientAPI;
import main.java.common.protocols.MessageType;
import main.java.common.protocols.VersionNumber;
import main.java.messages.secure.bid.SecureBidMessage;
import main.java.messages.secure.bid.components.SecureBidMessageComponents;
import main.java.messages.secure.bid.components.data.SecureBidMessageData;
import main.java.messages.secure.bid.components.data.confidential.SecureBidMessageDataConfidential;
import main.java.messages.secure.bid.components.data.signature.SecureBidMessageDataSignature;
import main.java.messages.secure.bid.dos.mitigation.SecureBidMessageDoSMitigation;
import main.java.messages.secure.bid.metaheader.SecureBidMessageMetaHeader;
import main.java.messages.secure.common.header.SecureCommonHeader;
import main.java.messages.secure.common.key.exchange.SecureCommonKeyExchange;
import main.java.resources.bid.Bid;
import main.java.resources.user.User;
import main.java.resources.user.UserAuctionInfo;
import main.java.resources.user.UserBidInfo;
import main.java.sys.SSLSocketAuctionOperation;
import main.java.sys.SSLSocketMessage;
import main.java.sys.rest.server.auction.configuration.utils.AuctionServerTLSConfigurationReader;

public class Client implements ClientAPI {

	private static final String OPEN_AUCTION = "create auction";
	private static final String CLOSE_AUCTION = "close auction";
	private static final String ADD_BID = "create bid";
	private static final String LIST_ALL_AUCTIONS = "list all";
	private static final String LIST_OPENED_AUCTIONS = "list opened";
	private static final String LIST_CLOSED_AUCTIONS = "list closed";
	private static final String LIST_ALL_AUCTIONS_BY_OWNER = "list all userID";
	private static final String LIST_OPENED_AUCTIONS_BY_OWNER = "list opened userID";
	private static final String LIST_CLOSED_AUCTIONS_BY_OWNER = "list closed userID";
	private static final String LIST_ALL_AUCTIONS_BY_ID = "list all auctionID";
	private static final String LIST_OPENED_AUCTIONS_BY_ID = "list opened auctionID";
	private static final String LIST_CLOSED_AUCTIONS_BY_ID = "list closed auctionID";
	private static final String LIST_BIDS_OF_ALL_AUCTIONS_BY_AUCTION_ID = "list bids all auction auctionID";
	private static final String LIST_BIDS_OF_OPENED_AUCTIONS_BY_AUCTION_ID = "list bids opened auction auctionID";
	private static final String LIST_BIDS_OF_CLOSED_AUCTIONS_BY_AUCTION_ID = "list bids closed auction auctionID";
	private static final String LIST_BIDS_OF_ALL_AUCTIONS_BY_AUCTION_ID_AND_CLIENT_ID = "list bids all auction by user auctionID userID";
	private static final String LIST_BIDS_OF_OPENED_AUCTIONS_BY_AUCTION_ID_AND_CLIENT_ID = "list bids opened auction by user auctionID userID";
	private static final String LIST_BIDS_OF_CLOSED_AUCTIONS_BY_AUCTION_ID_AND_CLIENT_ID = "list bids closed auction by user auctionID userID";
	
	private static final String LIST_ALL_BIDS_BY_CLIENT_ID = "list all bids userID";
	private static final String LIST_OPENED_BIDS_BY_CLIENT_ID = "list opened bids userID";
	private static final String LIST_CLOSED_BIDS_BY_CLIENT_ID = "list closed bids userID";
	
	private static final String CHECK_OUTCOME_ALL_AUCTION = "check outcome all auction";
	private static final String CHECK_OUTCOME_OPENED_AUCTION = "check outcome opened auction";
	private static final String CHECK_OUTCOME_CLOSED_AUCTION = "check outcome closed auction";
	
	private static final String CHECK_OUTCOME_ALL_AUCTION_ID = "check outcome all auction auctionID";
	private static final String CHECK_OUTCOME_OPENED_AUCTION_ID = "check outcome opened auction auctionID";
	private static final String CHECK_OUTCOME_CLOSED_AUCTION_ID = "check outcome closed auction auctionID";
	
	private static final String HELP = "help";
	private static final String EXIT = "exit";

	private static final String HASH_ALGORITHM = "SHA-256";

	private static final String USER_DATABASE_JDBC_PATH = "jdbc:sqlite:res/database/client/users.db";
	private static final String USER_TLS_CONFIGURATION_PATH = "res/configurations/client-tls-configuration.conf";

	private User currentUser;

	private Gson gson;
	private Dao<User, String> userDao;

	private BufferedReader br;

	private SSLSocket socket;
	private SSLSocketFactory socketFactory;
	
	public static void main(String[] args) {

		if(args.length != 6) {
			System.err.println("Usage: java Client <serverURL> <serverSocketPort> "
					+ "<keystore> <keystorePassword> "
					+ "<truststore> <truststorePassword>");
			System.exit(1);
		}

		String url = args[0];
		int serverPort = Integer.parseInt(args[1]);
		String keystorePath = args[2];
		String keystorePassword = args[3];
		String truststorePath = args[4];
		String truststorePassword = args[5];
		
		new Client(url, serverPort,
				keystorePath, keystorePassword,
				truststorePath, truststorePassword);				
	}

	// Taken from https://www.mkyong.com/webservices/jax-ws/java-security-cert-certificateexception-no-name-matching-localhost-found/
	static {
		//for localhost testing only
		javax.net.ssl.HttpsURLConnection.setDefaultHostnameVerifier(
				new javax.net.ssl.HostnameVerifier(){

					public boolean verify(String hostname,
							javax.net.ssl.SSLSession sslSession) {
						if (hostname.equals("localhost")) {
							return true;
						}
						return false;
					}
				});
	}

	public Client(String url, int serverPort,
			String keystorePath, String keystorePassword,
			String truststorePath, String truststorePassword) {

		AuctionServerTLSConfigurationReader tlsConfigurationReader = null;
		try {
			tlsConfigurationReader = new AuctionServerTLSConfigurationReader(USER_TLS_CONFIGURATION_PATH);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//SSL Connection
		try {
			System.setProperty("javax.net.ssl.keyStore", keystorePath);
			System.setProperty("javax.net.ssl.keyStorePassword",keystorePassword);
			System.setProperty("javax.net.ssl.trustStore", truststorePath);
			System.setProperty("javax.net.ssl.trustStorePassword", truststorePassword);

//			System.setProperty("javax.net.debug", "SSL,handshake");

			SSLContext sslContext = SSLContext.getDefault();

			socketFactory = sslContext.getSocketFactory();
			socket = (SSLSocket)socketFactory.createSocket(url, serverPort);
			socket.setEnabledCipherSuites(tlsConfigurationReader.getAvailableTLSCiphersuites());
			socket.setEnabledProtocols(tlsConfigurationReader.getAvailableTLSVersions());
			//		    socket.setSoTimeout(1000);
			socket.startHandshake();
		} catch (Exception e) {
			System.err.println("Error setting up TLS connection/socket!");
			e.getMessage();
			e.printStackTrace();
			System.exit(1);
		}

		String userRepository = USER_DATABASE_JDBC_PATH;
		gson = new Gson();

		ConnectionSource connectionSource;
		try {
			connectionSource = new JdbcConnectionSource(userRepository);
			userDao = DaoManager.createDao(connectionSource, User.class);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		br = new BufferedReader(new InputStreamReader(System.in));
		String line = null;

		currentUser = login();

		try {
			while((line = br.readLine()) != null)
			{

				if(currentUser == null) {
					currentUser = login();
				}

				switch (line) {
				case OPEN_AUCTION:
					createAuction();
					break;
				case CLOSE_AUCTION:
					closeAuction();
					break;
				case ADD_BID:
					createBid();
					break;
				case LIST_ALL_AUCTIONS:
					listAll();
					break;
				case LIST_OPENED_AUCTIONS:
					listOpened();
					break;
				case LIST_CLOSED_AUCTIONS:
					listClosed();
					break;
				case LIST_ALL_AUCTIONS_BY_OWNER:
					listAllByUserID();
					break;
				case LIST_OPENED_AUCTIONS_BY_OWNER:
					listOpenedByUserID();
					break;
				case LIST_CLOSED_AUCTIONS_BY_OWNER:
					listClosedByUserID();
					break;
				case LIST_ALL_AUCTIONS_BY_ID:
					listAllByAuctionID();
					break;
				case LIST_OPENED_AUCTIONS_BY_ID:
					listOpenByAuctionID();
					break;
				case LIST_CLOSED_AUCTIONS_BY_ID:
					listClosedByAuctionID();
					break;
				case LIST_BIDS_OF_ALL_AUCTIONS_BY_AUCTION_ID:
					listBidsOfAllAuctionsByAuctionID();
					break;
				case LIST_BIDS_OF_OPENED_AUCTIONS_BY_AUCTION_ID:
					listBidsOfOpenedAuctionByAuctionID();
					break;
				case LIST_BIDS_OF_CLOSED_AUCTIONS_BY_AUCTION_ID:
					listBidsOfClosedAuctionsByAuctionID();
					break;	
				case LIST_BIDS_OF_ALL_AUCTIONS_BY_AUCTION_ID_AND_CLIENT_ID:
					listBidsOfAllAuctionsByAuctionIDAndClientID();
					break;
				case LIST_BIDS_OF_OPENED_AUCTIONS_BY_AUCTION_ID_AND_CLIENT_ID:
					listBidsOfOpenedAuctionsByAuctionIDAndClientID();
					break;
				case LIST_BIDS_OF_CLOSED_AUCTIONS_BY_AUCTION_ID_AND_CLIENT_ID:
					listBidsOfClosedAuctionsByAuctionIDAndClientID();
					break;
			
					
				case LIST_ALL_BIDS_BY_CLIENT_ID:
					listAllBidsByClientID(); //TODO confirmar
					break;
				case LIST_OPENED_BIDS_BY_CLIENT_ID:
					listOpenedBidsByClientID(); //TODO confirmar
					break;
				case LIST_CLOSED_BIDS_BY_CLIENT_ID:
					listClosedBidsByClientID(); //TODO confirmat
					break;
				
					
				case CHECK_OUTCOME_ALL_AUCTION:
					checkOutcomeAllAuctions(); //TODO confirmar
					break;
				case CHECK_OUTCOME_OPENED_AUCTION:
					checkOutcomeOpenedAuctions(); //TODO confirmar
					break;
				case CHECK_OUTCOME_CLOSED_AUCTION:
					checkOutcomeClosedAuctions(); //TODO confirmar
					break;
				
					
				case CHECK_OUTCOME_ALL_AUCTION_ID:
					checkOutcomeAllAuctionsByAuctionID(); //TODO confirmar
					break;
				case CHECK_OUTCOME_OPENED_AUCTION_ID:
					checkOutcomeOpenedAuctionsByAuctionID(); //TODO confirmar
					break;
				case CHECK_OUTCOME_CLOSED_AUCTION_ID:
					checkOutcomeClosedAuctionsByAuctionID(); //TODO confirmar
					break;
				
				// VALIDATE RECEIPT
				
				case HELP:
					helpScreen();
					break;

				case EXIT:
					System.exit(0);
					break;

				default:
					helpScreen();
					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private User login() {
		User result = null;
		try {
			System.out.print("Enter user identification: ");
			String id = br.readLine();
			System.out.print("Enter user " + id + " password: ");
			String password = br.readLine();

			MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);
			digest.update(password.getBytes());
			byte[] digestData = digest.digest();
			String hashResult = new String(Hex.toHexString(digestData));

			HashMap<String, Object> fieldValues = new HashMap<>();
			fieldValues.put("userPeerID", id);
			fieldValues.put("userPassword", hashResult);
			result = userDao.queryForFieldValues(fieldValues).get(0);
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("Welcome " + result.getUserPeerID());
		System.out.println();
		helpScreen();

		return result;
	}

	private void createAuction() throws IOException {
		System.out.println("Enter product description: ");
		String productDescription = br.readLine();
		System.out.println(
				"	"
						+  "1: NORMAL_BIDS,\n" + 
						"	2: MIN_INITIAL_VALUE_BID\n" + 
						"	3: MIN_AMOUNT_VALUE_BID\n" + 
						"	4: MAX_AMOUNT_VALUE_BID\n" + 
						"	5: MIN_MAX_AMOUNT_VALUE_BID\n" + 
						"	6: LIMITED_SET_CLIENT_BIDDERS\n" + 
						"	7: LIMITED_NUMBER_BIDS_FOR_EACH_CLIENT\n" + 
						"	8: LIMITED_NUMBER_BIDS\n" + 
						"	9: LIMITED_TIME_BIDS"
						+ "");
		System.out.println();
		System.out.println("Enter bid type: ");
		byte bidType = new Byte(br.readLine());
		UserAuctionInfo userAuctionInfo;

		String line;

		switch (bidType) {
		case 1:
			userAuctionInfo = new UserAuctionInfo(currentUser,
					productDescription,
					bidType,
					0.0,
					0.0,
					0.0,
					null,
					Integer.MAX_VALUE,
					-1L);
			break;
		case 2:
			System.out.println("Enter minimum initial bid value: ");
			line = br.readLine();
			userAuctionInfo = new UserAuctionInfo(currentUser,
					productDescription,
					bidType,
					new Double(line),
					0.0,
					0.0,
					null,
					Integer.MAX_VALUE,
					-1L);
			break;
		case 3:
			System.out.println("Enter minimum amount bid value: ");
			line = br.readLine();
			userAuctionInfo = new UserAuctionInfo(currentUser,
					productDescription,
					bidType,
					0.0,
					new Double(line),
					0.0,
					null,
					Integer.MAX_VALUE,
					-1L);
			break;
		case 4:
			System.out.println("Enter maximum amount bid value: ");
			line = br.readLine();
			userAuctionInfo = new UserAuctionInfo(currentUser,
					productDescription,
					bidType,
					0.0,
					0.0,
					new Double(line),
					null,
					Integer.MAX_VALUE,
					-1L);
			break;
		case 5:
			System.out.println("Enter minimum amount bid value: ");
			String min = br.readLine();
			System.out.println("Enter maximum amount bid value: ");
			String max = br.readLine();
			userAuctionInfo = new UserAuctionInfo(currentUser,
					productDescription,
					bidType,
					0.0,
					new Double(min),
					new Double(max),
					null,
					Integer.MAX_VALUE,
					-1L);
			break;
		case 6:
			HashMap<String, Integer> limitedUsersMap = new HashMap<>();
			System.out.println("Enter userIDs that can bid, seperated by newlines: ");
			while ( !(line = br.readLine()).equals("") ) {
				limitedUsersMap.put(line, Integer.MAX_VALUE);
			}
			userAuctionInfo = new UserAuctionInfo(currentUser,
					productDescription,
					bidType,
					0.0,
					0.0,
					0.0,
					limitedUsersMap,
					Integer.MAX_VALUE,
					-1L);
			break;
		case 7:
			HashMap<String, Integer> limitedUsersBidNumberMap = new HashMap<>();
			System.out.println("Enter userIDs that can bid, seperated by newlines: ");
			String user;
			while ( !(user = br.readLine()).equals("") ) {
				System.out.println("Enter " + user + " bid limit: ");
				limitedUsersBidNumberMap.put(user, Integer.parseInt(br.readLine()));
				System.out.println("Another user: ");
			}
			userAuctionInfo = new UserAuctionInfo(currentUser,
					productDescription,
					bidType,
					0.0,
					0.0,
					0.0,
					limitedUsersBidNumberMap,
					Integer.MAX_VALUE,
					-1L);
			break;
		case 8:
			System.out.println("Enter max number of bids: ");
			line = br.readLine();
			userAuctionInfo = new UserAuctionInfo(currentUser,
					productDescription,
					bidType,
					0.0,
					0.0,
					0.0,
					null,
					Integer.parseInt(line),
					-1L);
			break;
		case 9:
			// TODO this url does not exist yet on the repository!
			System.out.println("Enter timelimit: ");
			line = br.readLine();
			userAuctionInfo = new UserAuctionInfo(currentUser,
					productDescription,
					bidType,
					0.0,
					0.0,
					0.0,
					null,
					Integer.MAX_VALUE,
					Long.parseLong(line));
			break;
		default:
			userAuctionInfo = new UserAuctionInfo(currentUser,
					productDescription,
					bidType,
					0.0,
					0.0,
					0.0,
					null,
					Integer.MAX_VALUE,
					-1L);
			break;
		}

		String postData = gson.toJson(userAuctionInfo);

		SSLSocketMessage message = new SSLSocketMessage(SSLSocketAuctionOperation.OPEN_AUCTION,
				new HashMap<String, String>(), postData);
		String result = sendMessageAndGetResponse(message);
		System.out.println(result);
	}

	private void closeAuction() throws IOException {
		System.out.println("Enter auctionID to close: ");
		String auctionIDToClose = br.readLine();
		HashMap<String,String> paramsMap = new HashMap<String, String>();
		paramsMap.put("auction-id", auctionIDToClose);
		SSLSocketMessage message = new SSLSocketMessage(SSLSocketAuctionOperation.CLOSE_AUCTION, paramsMap, "");
		String result = sendMessageAndGetResponse(message);
		System.out.println(result);
	}

	private void createBid() throws IOException{
		System.out.println("Enter auctionID to bid: ");
		String auctionID = br.readLine();
		System.out.println("Enter bid amount: ");
		String bidAmount = br.readLine();
		
		Random random = new Random();
		
		Bid bid = new Bid(random.nextLong(), currentUser.getUserPeerID(), Double.parseDouble(bidAmount));
		
		SecureBidMessageDataSignature secureBidMessageDataSignature = new SecureBidMessageDataSignature(
				bid,
				currentUser.getUserPeerID());
		
		SecureBidMessageDataConfidential secureBidMessageDataConfidential = new SecureBidMessageDataConfidential(
				currentUser.getUserEmail(),
				currentUser.getUserHomeAddress(),
				currentUser.getUserBankAccountNIB());
		
		SecureBidMessageData secureBidMessageData = new SecureBidMessageData(
				secureBidMessageDataSignature,
				secureBidMessageDataConfidential,
				currentUser.getUserPeerID());
		
		SecureCommonHeader secureCommonHeader = new SecureCommonHeader(
				VersionNumber.VERSION_01.getVersionNumber(),
				MessageType.MESSAGE_TYPE_1.getMessageType(),
				System.currentTimeMillis());
		
		SecureBidMessageComponents secureBidMessageComponents = null;
		
		SecureBidMessageDoSMitigation secureBidMessageDoSMitigation = null;

		SecureCommonKeyExchange secureBidMessageKeyExchange = null;
		
		byte[] secureBidMessageKeyExchangeSerializedCiphered = null;
		byte[] secureBidMessageKeyExchangeSerializedCipheredSigned = null;
		byte[] secureBidMessageDataSerialized = null;
		byte[] secureBidMessageDoSMitigationSerialized = null;
		byte[] secureBidMessageDataSignatureSerialized = null;
		byte[] secureBidMessageDataConfidentialSerializedCipheredAndHashed = null;
		byte[] bidSerialized = null;
		byte[] bidSerializedDigitalSigned = null;
		byte[] secureBidMessageDataConfidentialSerialized = null;
		byte[] secureBidMessageDataConfidentialSerializedCiphered = null;
		byte[] secureBidMessageDataConfidentialSerializedCipheredHashed = null;
		
		try {
			bid.doSerialization();
			bidSerialized = bid.getBidSerializedBytes();
			
			secureBidMessageDataSignature.buildSecureBidMessageDataSignatureToSend();
			bidSerializedDigitalSigned = secureBidMessageDataSignature.getBidSerializedDigitalSigned();
			secureBidMessageDataSignatureSerialized = secureBidMessageDataSignature.getBidDigitalSigned();
			
			secureBidMessageDataConfidential.buildSecureBidMessageDataConfidentialToSend();
			secureBidMessageDataConfidentialSerialized = secureBidMessageDataConfidential.getSecureBidMessageDataConfidentialSerialized();
			secureBidMessageDataConfidentialSerializedCipheredAndHashed = secureBidMessageDataConfidential.getSecureBidMessageDataConfidentialSerializedCipheredAndHashed();
			
			
			secureBidMessageData.doSecureBidMessageDataSerialization();
			secureBidMessageDataSerialized = secureBidMessageData.getSecureBidMessageDataSerialized();
			
			secureCommonHeader.doSecureCommonHeaderSerialization();
			

			// Needs to be here
			secureBidMessageComponents = new SecureBidMessageComponents(
					secureCommonHeader,
					secureBidMessageData,
					secureBidMessageDataConfidential.getSecretSymmetricKeyForDataConfidentialInBytes(),
					currentUser.getUserPeerID());
			
			secureBidMessageDoSMitigation = new SecureBidMessageDoSMitigation(
					secureBidMessageComponents);
			
			secureBidMessageComponents.doSecureBidMessageComponentsSerialization();
			
			secureBidMessageDoSMitigation.doHashOfSecureBidMessageDoSMitigation();
			secureBidMessageDoSMitigationSerialized = secureBidMessageDoSMitigation.getSecretHMACKeyForDoSMitigationInBytes();
						
			secureBidMessageKeyExchange = new SecureCommonKeyExchange(
					secureBidMessageDataConfidential.getSecretSymmetricKeyForDataConfidentialInBytes(),
					secureBidMessageDoSMitigation.getSecretHMACKeyForDoSMitigationInBytes(),
					currentUser.getUserPeerID());
			

			secureBidMessageKeyExchange.buildSecureBidMessageKeyExchangeToSend();
			secureBidMessageKeyExchangeSerializedCiphered = secureBidMessageKeyExchange.getSecureBidMessageKeyExchangeSerializedCiphered();
			secureBidMessageKeyExchangeSerializedCipheredSigned = secureBidMessageKeyExchange.getSecureBidMessageKeyExchangeSerializedCipheredSigned();

			secureBidMessageDataConfidentialSerializedCiphered = secureBidMessageDataConfidential.getSecureBidMessageDataConfidentialSerializedCiphered();
			secureBidMessageDataConfidentialSerializedCipheredHashed = secureBidMessageDataConfidential.getSecureBidMessageDataConfidentialSerializedCipheredHashed();	
			
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SignatureException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchProviderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidAlgorithmParameterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		SecureBidMessageMetaHeader secureBidMessageMetaHeader = new SecureBidMessageMetaHeader(
				currentUser.getUserPeerID().getBytes("UTF-8").length,
				secureBidMessageKeyExchangeSerializedCiphered.length,
				secureBidMessageKeyExchangeSerializedCipheredSigned.length,
				secureBidMessageDataSerialized.length,
				secureBidMessageDoSMitigationSerialized.length,
				secureBidMessageDataSignatureSerialized.length,
				secureBidMessageDataConfidentialSerializedCipheredAndHashed.length,
				bidSerialized.length,
				bidSerializedDigitalSigned.length,
				currentUser.getUserPeerID().getBytes("UTF-8").length,
				secureBidMessageDataConfidentialSerializedCiphered.length,
				secureBidMessageDataConfidentialSerializedCipheredHashed.length,
				secureBidMessageDataConfidentialSerialized.length,
				currentUser.getUserEmail().getBytes("UTF-8").length,
				currentUser.getUserHomeAddress().getBytes("UTF-8").length,
				currentUser.getUserBankAccountNIB().getBytes("UTF-8").length);
		
		secureBidMessageMetaHeader.doSecureBidMessageMetaHeaderSerialization();
		
		SecureBidMessage bidMessage = new SecureBidMessage(
				secureBidMessageMetaHeader,
				currentUser.getUserPeerID(),
				secureBidMessageKeyExchange,
				secureBidMessageComponents,
				secureBidMessageDoSMitigation);
		
		try {
			bidMessage.doSecureBidMessageSerialized();
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SignatureException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchProviderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidAlgorithmParameterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		UserBidInfo bidInfo = new UserBidInfo(currentUser, Double.parseDouble(bidAmount)); // TODO delete
		String bidInfoSerialiazed = gson.toJson(bidMessage);
		HashMap<String,String> paramsMap = new HashMap<String, String>();
		paramsMap.put("auction-id", auctionID);
		SSLSocketMessage message = new SSLSocketMessage(SSLSocketAuctionOperation.ADD_BID, paramsMap, bidInfoSerialiazed);
		String result = sendMessageAndGetResponse(message);
		System.out.println(result);
	}

	private void listAll() throws IOException {
		SSLSocketMessage message = new SSLSocketMessage(SSLSocketAuctionOperation.LIST_ALL_AUCTIONS,
				new HashMap<String, String>(), "");
		String result = sendMessageAndGetResponse(message);
		String prettyResult = getPrettyJsonString(result);
		System.out.println(prettyResult);
	}

	private void listOpened() throws IOException {
		SSLSocketMessage message = new SSLSocketMessage(SSLSocketAuctionOperation.LIST_OPENED_AUCTIONS,
				new HashMap<String, String>(), "");
		String result = sendMessageAndGetResponse(message);
		String prettyResult = getPrettyJsonString(result);
		System.out.println(prettyResult);
	}

	private void listClosed() throws IOException {
		SSLSocketMessage message = new SSLSocketMessage(SSLSocketAuctionOperation.LIST_CLOSED_AUCTIONS,
				new HashMap<String, String>(), "");
		String result = sendMessageAndGetResponse(message);
		String prettyResult = getPrettyJsonString(result);
		System.out.println(prettyResult);
	}

	private void listAllByUserID() throws IOException{
		System.out.println("Enter userID: ");
		String userID = br.readLine();
		HashMap<String,String> paramsMap = new HashMap<String, String>();
		paramsMap.put("product-owner-user-client-id", userID);
		SSLSocketMessage message = new SSLSocketMessage(SSLSocketAuctionOperation.LIST_ALL_AUCTIONS_BY_OWNER,
				paramsMap, "");
		String result = sendMessageAndGetResponse(message);
		String prettyResult = getPrettyJsonString(result);
		System.out.println(prettyResult);
	}

	private void listOpenedByUserID() throws IOException{
		System.out.println("Enter userID: ");
		String userID = br.readLine();
		HashMap<String,String> paramsMap = new HashMap<String, String>();
		paramsMap.put("product-owner-user-client-id", userID);
		SSLSocketMessage message = new SSLSocketMessage(SSLSocketAuctionOperation.LIST_OPENED_AUCTIONS_BY_OWNER,
				paramsMap, "");
		String result = sendMessageAndGetResponse(message);
		String prettyResult = getPrettyJsonString(result);
		System.out.println(prettyResult);
	}

	private void listClosedByUserID() throws IOException{
		System.out.println("Enter userID: ");
		String userID = br.readLine();
		HashMap<String,String> paramsMap = new HashMap<String, String>();
		paramsMap.put("product-owner-user-client-id", userID);
		SSLSocketMessage message = new SSLSocketMessage(SSLSocketAuctionOperation.LIST_CLOSED_AUCTIONS_BY_OWNER,
				paramsMap, "");
		String result = sendMessageAndGetResponse(message);
		String prettyResult = getPrettyJsonString(result);
		System.out.println(prettyResult);
	}

	private void listAllByAuctionID() throws IOException {
		System.out.println("Enter auctionID: ");
		String auctionID = br.readLine();
		HashMap<String,String> paramsMap = new HashMap<String, String>();
		paramsMap.put("auction-id", auctionID);
		SSLSocketMessage message = new SSLSocketMessage(SSLSocketAuctionOperation.LIST_ALL_AUCTIONS_BY_ID,
				paramsMap, "");
		String result = sendMessageAndGetResponse(message);
		String prettyResult = getPrettyJsonString(result);
		System.out.println(prettyResult);
	}

	private void listOpenByAuctionID() throws IOException {
		System.out.println("Enter auctionID: ");
		String auctionID = br.readLine();
		HashMap<String,String> paramsMap = new HashMap<String, String>();
		paramsMap.put("auction-id", auctionID);
		SSLSocketMessage message = new SSLSocketMessage(SSLSocketAuctionOperation.LIST_OPENED_AUCTIONS_BY_ID,
				paramsMap, "");
		String result = sendMessageAndGetResponse(message);
		String prettyResult = getPrettyJsonString(result);
		System.out.println(prettyResult);
	}

	private void listClosedByAuctionID() throws IOException {
		System.out.println("Enter auctionID: ");
		String auctionID = br.readLine();
		HashMap<String,String> paramsMap = new HashMap<String, String>();
		paramsMap.put("auction-id", auctionID);
		SSLSocketMessage message = new SSLSocketMessage(SSLSocketAuctionOperation.LIST_CLOSED_AUCTIONS_BY_ID,
				paramsMap, "");
		String result = sendMessageAndGetResponse(message);
		String prettyResult = getPrettyJsonString(result);
		System.out.println(prettyResult);
	}

	private void listBidsOfAllAuctionsByAuctionID() throws IOException {
		System.out.println("Enter auctionID: ");
		String auctionID = br.readLine();
		HashMap<String,String> paramsMap = new HashMap<String, String>();
		paramsMap.put("auction-id", auctionID);
		SSLSocketMessage message = new SSLSocketMessage(SSLSocketAuctionOperation.LIST_BIDS_OF_ALL_AUCTIONS_BY_AUCTION_ID,
				paramsMap, "");
		String result = sendMessageAndGetResponse(message);
		String prettyResult = getPrettyJsonString(result);
		System.out.println(prettyResult);
	}
	
	private void listBidsOfOpenedAuctionByAuctionID() throws IOException {
		System.out.println("Enter auctionID: ");
		String auctionID = br.readLine();
		HashMap<String,String> paramsMap = new HashMap<String, String>();
		paramsMap.put("auction-id", auctionID);
		SSLSocketMessage message = new SSLSocketMessage(SSLSocketAuctionOperation.LIST_BIDS_OF_OPENED_AUCTIONS_BY_AUCTION_ID,
				paramsMap, "");
		String result = sendMessageAndGetResponse(message);
		String prettyResult = getPrettyJsonString(result);
		System.out.println(prettyResult);
	}
	
	private void listBidsOfClosedAuctionsByAuctionID() throws IOException {
		System.out.println("Enter auctionID: ");
		String auctionID = br.readLine();
		HashMap<String,String> paramsMap = new HashMap<String, String>();
		paramsMap.put("auction-id", auctionID);
		SSLSocketMessage message = new SSLSocketMessage(SSLSocketAuctionOperation.LIST_BIDS_OF_CLOSED_AUCTIONS_BY_AUCTION_ID,
				paramsMap, "");
		String result = sendMessageAndGetResponse(message);
		String prettyResult = getPrettyJsonString(result);
		System.out.println(prettyResult);
	}
	
	private void listBidsOfAllAuctionsByAuctionIDAndClientID() throws IOException {
		System.out.println("Enter auctionID: ");
		String auctionID = br.readLine();
		System.out.println("Enter bidderID: ");
		String bidderID = br.readLine();
		HashMap<String,String> paramsMap = new HashMap<String, String>();
		paramsMap.put("auction-id", auctionID);
		paramsMap.put("bidder-user-client-id", bidderID);
		SSLSocketMessage message = new SSLSocketMessage(SSLSocketAuctionOperation.LIST_BIDS_OF_ALL_AUCTIONS_BY_AUCTION_ID_AND_CLIENT_ID,
				paramsMap, "");
		String result = sendMessageAndGetResponse(message);
		String prettyResult = getPrettyJsonString(result);
		System.out.println(prettyResult);
	}
	
	private void listBidsOfOpenedAuctionsByAuctionIDAndClientID() throws IOException {
		System.out.println("Enter auctionID: ");
		String auctionID = br.readLine();
		System.out.println("Enter bidderID: ");
		String bidderID = br.readLine();
		HashMap<String,String> paramsMap = new HashMap<String, String>();
		paramsMap.put("auction-id", auctionID);
		paramsMap.put("bidder-user-client-id", bidderID);
		SSLSocketMessage message = new SSLSocketMessage(SSLSocketAuctionOperation.LIST_BIDS_OF_OPENED_AUCTIONS_BY_AUCTION_ID_AND_CLIENT_ID,
				paramsMap, "");
		String result = sendMessageAndGetResponse(message);
		String prettyResult = getPrettyJsonString(result);
		System.out.println(prettyResult);
	}
	
	private void listBidsOfClosedAuctionsByAuctionIDAndClientID() throws IOException {
		System.out.println("Enter auctionID: ");
		String auctionID = br.readLine();
		System.out.println("Enter bidderID: ");
		String bidderID = br.readLine();
		HashMap<String,String> paramsMap = new HashMap<String, String>();
		paramsMap.put("auction-id", auctionID);
		paramsMap.put("bidder-user-client-id", bidderID);
		SSLSocketMessage message = new SSLSocketMessage(SSLSocketAuctionOperation.LIST_BIDS_OF_CLOSED_AUCTIONS_BY_AUCTION_ID_AND_CLIENT_ID,
				paramsMap, "");
		String result = sendMessageAndGetResponse(message);
		String prettyResult = getPrettyJsonString(result);
		System.out.println(prettyResult);
	}
	
	private void listAllBidsByClientID() throws IOException {
		System.out.println("Enter bidderID: ");
		String bidderID = br.readLine();
		HashMap<String,String> paramsMap = new HashMap<String, String>();
		paramsMap.put("bidder-user-client-id", bidderID);
		SSLSocketMessage message = new SSLSocketMessage(SSLSocketAuctionOperation.LIST_ALL_BIDS_BY_CLIENT_ID,
				paramsMap, "");
		String result = sendMessageAndGetResponse(message);
		String prettyResult = getPrettyJsonString(result);
		System.out.println(prettyResult);
	}
	
	private void listOpenedBidsByClientID() throws IOException {
		System.out.println("Enter bidderID: ");
		String bidderID = br.readLine();
		HashMap<String,String> paramsMap = new HashMap<String, String>();
		paramsMap.put("bidder-user-client-id", bidderID);
		SSLSocketMessage message = new SSLSocketMessage(SSLSocketAuctionOperation.LIST_OPENED_BIDS_BY_CLIENT_ID,
				paramsMap, "");
		String result = sendMessageAndGetResponse(message);
		String prettyResult = getPrettyJsonString(result);
		System.out.println(prettyResult);
	}
	
	private void listClosedBidsByClientID() throws IOException {
		System.out.println("Enter bidderID: ");
		String bidderID = br.readLine();
		HashMap<String,String> paramsMap = new HashMap<String, String>();
		paramsMap.put("bidder-user-client-id", bidderID);
		SSLSocketMessage message = new SSLSocketMessage(SSLSocketAuctionOperation.LIST_CLOSED_BIDS_BY_CLIENT_ID,
				paramsMap, "");
		String result = sendMessageAndGetResponse(message);
		String prettyResult = getPrettyJsonString(result);
		System.out.println(prettyResult);
	}
	
	private void checkOutcomeAllAuctions() throws IOException {
		HashMap<String,String> paramsMap = new HashMap<String, String>();
		paramsMap.put("bidder-user-client-id", this.currentUser.getUserPeerID());
		SSLSocketMessage message = new SSLSocketMessage(SSLSocketAuctionOperation.CHECK_OUTCOME_ALL_AUCTION,
				paramsMap, "");
		String result = sendMessageAndGetResponse(message);
		String prettyResult = getPrettyJsonString(result);
		System.out.println(prettyResult);
	}
	
	private void checkOutcomeOpenedAuctions() throws IOException {
		HashMap<String,String> paramsMap = new HashMap<String, String>();
		paramsMap.put("bidder-user-client-id", this.currentUser.getUserPeerID());
		SSLSocketMessage message = new SSLSocketMessage(SSLSocketAuctionOperation.CHECK_OUTCOME_OPENED_AUCTION,
				paramsMap, "");
		String result = sendMessageAndGetResponse(message);
		String prettyResult = getPrettyJsonString(result);
		System.out.println(prettyResult);
	}
	
	private void checkOutcomeClosedAuctions() throws IOException {
		HashMap<String,String> paramsMap = new HashMap<String, String>();
		paramsMap.put("bidder-user-client-id", this.currentUser.getUserPeerID());
		SSLSocketMessage message = new SSLSocketMessage(SSLSocketAuctionOperation.CHECK_OUTCOME_CLOSED_AUCTION,
				paramsMap, "");
		String result = sendMessageAndGetResponse(message);
		String prettyResult = getPrettyJsonString(result);
		System.out.println(prettyResult);
	}
	
	private void checkOutcomeAllAuctionsByAuctionID() throws IOException {
		System.out.println("Enter auctionID: ");
		String auctionID = br.readLine();
		HashMap<String,String> paramsMap = new HashMap<String, String>();
		paramsMap.put("auction-id", auctionID);
		paramsMap.put("bidder-user-client-id", this.currentUser.getUserPeerID());
		SSLSocketMessage message = new SSLSocketMessage(SSLSocketAuctionOperation.CHECK_OUTCOME_ALL_AUCTION_ID,
				paramsMap, "");
		String result = sendMessageAndGetResponse(message);
		String prettyResult = getPrettyJsonString(result);
		System.out.println(prettyResult);
	}
	
	private void checkOutcomeOpenedAuctionsByAuctionID() throws IOException {
		System.out.println("Enter auctionID: ");
		String auctionID = br.readLine();
		HashMap<String,String> paramsMap = new HashMap<String, String>();
		paramsMap.put("auction-id", auctionID);
		paramsMap.put("bidder-user-client-id", this.currentUser.getUserPeerID());
		SSLSocketMessage message = new SSLSocketMessage(SSLSocketAuctionOperation.CHECK_OUTCOME_OPENED_AUCTION_ID,
				paramsMap, "");
		String result = sendMessageAndGetResponse(message);
		String prettyResult = getPrettyJsonString(result);
		System.out.println(prettyResult);
	}
	
	private void checkOutcomeClosedAuctionsByAuctionID() throws IOException {
		System.out.println("Enter auctionID: ");
		String auctionID = br.readLine();
		HashMap<String,String> paramsMap = new HashMap<String, String>();
		paramsMap.put("auction-id", auctionID);
		paramsMap.put("bidder-user-client-id", this.currentUser.getUserPeerID());
		SSLSocketMessage message = new SSLSocketMessage(SSLSocketAuctionOperation.CHECK_OUTCOME_CLOSED_AUCTION_ID,
				paramsMap, "");
		String result = sendMessageAndGetResponse(message);
		String prettyResult = getPrettyJsonString(result);
		System.out.println(prettyResult);
	}
	
	private void helpScreen() {
		System.out.println("HELP SCREEN");
		System.out.println(OPEN_AUCTION);
		System.out.println(CLOSE_AUCTION);
		System.out.println(ADD_BID);
		System.out.println(LIST_ALL_AUCTIONS);
		System.out.println(LIST_OPENED_AUCTIONS);
		System.out.println(LIST_CLOSED_AUCTIONS);
		System.out.println(LIST_ALL_AUCTIONS_BY_OWNER);
		System.out.println(LIST_OPENED_AUCTIONS_BY_OWNER);
		System.out.println(LIST_CLOSED_AUCTIONS_BY_OWNER);
		System.out.println(LIST_ALL_AUCTIONS_BY_ID);
		System.out.println(LIST_OPENED_AUCTIONS_BY_ID);
		System.out.println(LIST_CLOSED_AUCTIONS_BY_ID);
		System.out.println(LIST_BIDS_OF_ALL_AUCTIONS_BY_AUCTION_ID);
		System.out.println(LIST_BIDS_OF_OPENED_AUCTIONS_BY_AUCTION_ID);
		System.out.println(LIST_BIDS_OF_CLOSED_AUCTIONS_BY_AUCTION_ID);
		System.out.println(LIST_BIDS_OF_ALL_AUCTIONS_BY_AUCTION_ID_AND_CLIENT_ID);
		System.out.println(LIST_BIDS_OF_OPENED_AUCTIONS_BY_AUCTION_ID_AND_CLIENT_ID);
		System.out.println(LIST_BIDS_OF_CLOSED_AUCTIONS_BY_AUCTION_ID_AND_CLIENT_ID);
		System.out.println(LIST_ALL_BIDS_BY_CLIENT_ID);
		System.out.println(LIST_OPENED_BIDS_BY_CLIENT_ID);
		System.out.println(LIST_CLOSED_BIDS_BY_CLIENT_ID);
		System.out.println(CHECK_OUTCOME_ALL_AUCTION);
		System.out.println(CHECK_OUTCOME_OPENED_AUCTION);
		System.out.println(CHECK_OUTCOME_CLOSED_AUCTION);
		System.out.println(CHECK_OUTCOME_ALL_AUCTION_ID);
		System.out.println(CHECK_OUTCOME_OPENED_AUCTION_ID);
		System.out.println(CHECK_OUTCOME_CLOSED_AUCTION_ID);
		System.out.println();
		System.out.println(HELP);
		System.out.println(EXIT);
	}

	private String sslReadResponse(InputStream socketInStream) throws IOException {
		//Intercept auction bid message
		//If special message, run special method TODO
		BufferedReader br = new BufferedReader(new InputStreamReader(socketInStream));
		String response = br.readLine();
		return response;
	}

	private String sendMessageAndGetResponse(SSLSocketMessage message) throws IOException {
		OutputStream out = socket.getOutputStream();
		PrintWriter printWriter = new PrintWriter(out);

		printWriter.print(gson.toJson(message) + System.lineSeparator());
		printWriter.flush();

		InputStream in = socket.getInputStream();
		return sslReadResponse(in);
	}
	
	private String getPrettyJsonString(String string) {
		String prettyResult = string;
		// Check if is valid JSON
		try {
			gson.fromJson(string, Object.class);
			if(string != null)
			{
				Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
				JsonElement jsonElement = new JsonParser().parse(string);
				prettyResult = prettyGson.toJson(jsonElement);

			}
		} catch (Exception e) {
			// Not JSON! Ignore and continue!
		}

		return prettyResult;
	}

}
