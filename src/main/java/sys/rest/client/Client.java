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
import java.security.Security;
import java.security.SignatureException;
import java.sql.SQLException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.crypto.NoSuchPaddingException;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
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
import main.java.common.protocols.NumBytesChallengeType;
import main.java.common.protocols.StrategyCryptoPuzzle;
import main.java.common.protocols.VersionNumber;
import main.java.common.utils.CommonUtils;
import main.java.messages.secure.bid.SecureBidMessage;
import main.java.messages.secure.bid.components.SecureBidMessageComponents;
import main.java.messages.secure.bid.components.data.SecureBidMessageData;
import main.java.messages.secure.bid.components.data.confidential.SecureBidMessageDataConfidential;
import main.java.messages.secure.bid.components.data.signature.SecureBidMessageDataSignature;
import main.java.messages.secure.bid.dos.mitigation.SecureBidMessageDoSMitigation;
import main.java.messages.secure.bid.metaheader.SecureBidMessageMetaHeader;
import main.java.messages.secure.common.header.SecureCommonHeader;
import main.java.messages.secure.common.key.exchange.SecureCommonKeyExchange;
import main.java.messages.secure.receipt.SecureReceiptMessage;
import main.java.messages.secure.receipt.components.SecureReceiptMessageComponents;
import main.java.messages.secure.receipt.components.data.SecureReceiptMessageComponentsData;
import main.java.messages.secure.receipt.components.data.info.SecureReceiptMessageComponentsDataInfo;
import main.java.messages.secure.receipt.components.data.signature.SecureReceiptMessageComponentsDataSignature;
import main.java.messages.secure.receipt.dos.mitigation.SecureReceiptMessageDoSMitigation;
import main.java.resources.bid.Bid;
import main.java.resources.block.Block;
import main.java.resources.user.User;
import main.java.resources.user.UserAuctionInfo;
import main.java.sys.rest.client.services.TryToMineBlockOfOpenBidsService;
import main.java.sys.rest.server.auction.configuration.utils.AuctionServerTLSConfigurationReader;
import main.java.sys.rest.server.auction.messageTypes.MessagePacketServerToClient;
import main.java.sys.rest.server.auction.messageTypes.MessagePacketServerToClientTypes;
import main.java.sys.rest.server.auction.messageTypes.MessagePacketClientToServerTypes;
import main.java.sys.rest.server.auction.messageTypes.MessagePacketClientToServer;

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
	
	private Thread inputStreamThread;
	private InputStream inputStream;
	private OutputStream outputStream;
	
	
	private TryToMineBlockOfOpenBidsService tryToMineBlockOfOpenBidsService;
	
	private Thread tryToMineBlockOfOpenBidsServiceThread;
	
	
	public static void main(String[] args) {

		if(args.length != 6) {
			System.err.println("Usage: java Client <serverURL> <serverSocketPort> "
					+ "<keystore> <keystorePassword> "
					+ "<truststore> <truststorePassword>");
			System.exit(1);
		}

		// if provider is not present, add it
		if (Security.getProvider(BouncyCastleProvider.PROVIDER_NAME) == null) {
		    // insert at specific position
		    Security.addProvider(new BouncyCastleProvider());
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
			printErrorStringWithClassName("Configuration file not found!\n" + e.getMessage());
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
			
			
			this.tryToMineBlockOfOpenBidsService = 
					new TryToMineBlockOfOpenBidsService(this,
														StrategyCryptoPuzzle.STRATEGY_CRYPTO_PUZZLE_1.getStrategyUsedForCryptoPuzzle(),
														NumBytesChallengeType.NUM_BYTES_CHALLENGE_TYPE_3.getNumBytesChallengeType(),
														/**openBidsList**/ null, /**minedBlockMap**/ null);
			
			this.tryToMineBlockOfOpenBidsServiceThread = new Thread(this.tryToMineBlockOfOpenBidsService);
			this.tryToMineBlockOfOpenBidsServiceThread.start();
			
			
			inputStreamThread = new Thread() {
				public void run() {
					try {
						boolean exitFlag = false;
						String response;
						MessagePacketServerToClient envelope;
						MessagePacketServerToClientTypes messageType;
						String message;
						inputStream = socket.getInputStream();
						BufferedReader socketReader = new BufferedReader(new InputStreamReader(inputStream));
						while(!exitFlag) {
							// TODO Read and handle response
							response = socketReader.readLine();
							try {
								envelope = gson.fromJson(response, MessagePacketServerToClient.class);
								message = envelope.getMessage();
								messageType = envelope.getType();
								switch (messageType) {
								case SIMPLE_RESPONSE:
									System.out.println(message);
									break;
								case COMPLEX_RESPONSE:
									System.out.println(getPrettyJsonString(message));
									break;
								case PROOF_OF_WORK:
								// TODO complete
									printErrorStringWithClassName("PROOF OF WORK!!!");
									break;
								case RECEIPT:
									System.out.println(decodeReceipt(message));
									break;
								default:
									break;
								}
							} catch (Exception e) {
								if(response == null) {
									exitFlag = true;
									currentUser = null;
									System.out.println("Server disconnected!");
								}
								else {
									printErrorStringWithClassName("Received something unexpected? -> " + e.getMessage());
									printErrorStringWithClassName(response);
								}
							}
						}
					} catch (IOException e) {
						printErrorStringWithClassName("Error setting up inputStream!\n" + e.getMessage());
					}
				}
			};
			inputStreamThread.start();
			
			outputStream = socket.getOutputStream();
			
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

	public User getClientUser() {
		return this.currentUser;
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

		MessagePacketClientToServer message = new MessagePacketClientToServer(MessagePacketClientToServerTypes.OPEN_AUCTION,
				new HashMap<String, String>(), postData);
		sendMessage(message);
	}

	private void closeAuction() throws IOException {
		System.out.println("Enter auctionID to close: ");
		String auctionIDToClose = br.readLine();
		HashMap<String,String> paramsMap = new HashMap<String, String>();
		paramsMap.put("auction-id", auctionIDToClose);
		MessagePacketClientToServer message = new MessagePacketClientToServer(MessagePacketClientToServerTypes.CLOSE_AUCTION, paramsMap, "");
		sendMessage(message);
	}

	private void createBid() throws IOException{
		System.out.println("Enter auctionID to bid: ");
		String auctionID = br.readLine();
		System.out.println("Enter bid amount: ");
		String bidAmount = br.readLine();
		
		Random random = new Random();
		
		Bid bid = new Bid(random.nextLong(), currentUser.getUserPeerID(), Double.parseDouble(bidAmount));
		
		SecureBidMessageDataSignature secureBidMessageDataSignature = 
				new SecureBidMessageDataSignature(bid,
												  currentUser.getUserPeerID());
		
		SecureBidMessageDataConfidential secureBidMessageDataConfidential =
				new SecureBidMessageDataConfidential(currentUser.getUserEmail(),
													 currentUser.getUserHomeAddress(),
													 currentUser.getUserBankAccountNIB());
												
		SecureBidMessageData secureBidMessageData = 
				new SecureBidMessageData(secureBidMessageDataSignature,
								         secureBidMessageDataConfidential,
										 currentUser.getUserPeerID());
								
		SecureCommonHeader secureCommonHeader = 
				new SecureCommonHeader(VersionNumber.VERSION_01.getVersionNumber(),
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
			

			secureBidMessageKeyExchange.buildSecureCommonKeyExchangeToSend();
			secureBidMessageKeyExchangeSerializedCiphered = secureBidMessageKeyExchange.getSecureCommonKeyExchangeSerializedCiphered();
			secureBidMessageKeyExchangeSerializedCipheredSigned = secureBidMessageKeyExchange.getSecureCommonKeyExchangeSerializedCipheredSigned();

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
		
		String bidInfoSerialiazed = gson.toJson(bidMessage);
		HashMap<String,String> paramsMap = new HashMap<String, String>();
		paramsMap.put("auction-id", auctionID);
		MessagePacketClientToServer message = new MessagePacketClientToServer(MessagePacketClientToServerTypes.ADD_BID, paramsMap, bidInfoSerialiazed);
		sendMessage(message);
	}

	private void listAll() throws IOException {
		MessagePacketClientToServer message = new MessagePacketClientToServer(MessagePacketClientToServerTypes.LIST_ALL_AUCTIONS,
				new HashMap<String, String>(), "");
		sendMessage(message);
	}

	private void listOpened() throws IOException {
		MessagePacketClientToServer message = new MessagePacketClientToServer(MessagePacketClientToServerTypes.LIST_OPENED_AUCTIONS,
				new HashMap<String, String>(), "");
		sendMessage(message);
	}

	private void listClosed() throws IOException {
		MessagePacketClientToServer message = new MessagePacketClientToServer(MessagePacketClientToServerTypes.LIST_CLOSED_AUCTIONS,
				new HashMap<String, String>(), "");
		sendMessage(message);
	}

	private void listAllByUserID() throws IOException{
		System.out.println("Enter userID: ");
		String userID = br.readLine();
		HashMap<String,String> paramsMap = new HashMap<String, String>();
		paramsMap.put("product-owner-user-client-id", userID);
		MessagePacketClientToServer message = new MessagePacketClientToServer(MessagePacketClientToServerTypes.LIST_ALL_AUCTIONS_BY_OWNER,
				paramsMap, "");
		sendMessage(message);
	}

	private void listOpenedByUserID() throws IOException{
		System.out.println("Enter userID: ");
		String userID = br.readLine();
		HashMap<String,String> paramsMap = new HashMap<String, String>();
		paramsMap.put("product-owner-user-client-id", userID);
		MessagePacketClientToServer message = new MessagePacketClientToServer(MessagePacketClientToServerTypes.LIST_OPENED_AUCTIONS_BY_OWNER,
				paramsMap, "");
		sendMessage(message);
	}

	private void listClosedByUserID() throws IOException{
		System.out.println("Enter userID: ");
		String userID = br.readLine();
		HashMap<String,String> paramsMap = new HashMap<String, String>();
		paramsMap.put("product-owner-user-client-id", userID);
		MessagePacketClientToServer message = new MessagePacketClientToServer(MessagePacketClientToServerTypes.LIST_CLOSED_AUCTIONS_BY_OWNER,
				paramsMap, "");
		sendMessage(message);
	}

	private void listAllByAuctionID() throws IOException {
		System.out.println("Enter auctionID: ");
		String auctionID = br.readLine();
		HashMap<String,String> paramsMap = new HashMap<String, String>();
		paramsMap.put("auction-id", auctionID);
		MessagePacketClientToServer message = new MessagePacketClientToServer(MessagePacketClientToServerTypes.LIST_ALL_AUCTIONS_BY_ID,
				paramsMap, "");
		sendMessage(message);
	}

	private void listOpenByAuctionID() throws IOException {
		System.out.println("Enter auctionID: ");
		String auctionID = br.readLine();
		HashMap<String,String> paramsMap = new HashMap<String, String>();
		paramsMap.put("auction-id", auctionID);
		MessagePacketClientToServer message = new MessagePacketClientToServer(MessagePacketClientToServerTypes.LIST_OPENED_AUCTIONS_BY_ID,
				paramsMap, "");
		sendMessage(message);
	}

	private void listClosedByAuctionID() throws IOException {
		System.out.println("Enter auctionID: ");
		String auctionID = br.readLine();
		HashMap<String,String> paramsMap = new HashMap<String, String>();
		paramsMap.put("auction-id", auctionID);
		MessagePacketClientToServer message = new MessagePacketClientToServer(MessagePacketClientToServerTypes.LIST_CLOSED_AUCTIONS_BY_ID,
				paramsMap, "");
		sendMessage(message);
	}

	private void listBidsOfAllAuctionsByAuctionID() throws IOException {
		System.out.println("Enter auctionID: ");
		String auctionID = br.readLine();
		HashMap<String,String> paramsMap = new HashMap<String, String>();
		paramsMap.put("auction-id", auctionID);
		MessagePacketClientToServer message = new MessagePacketClientToServer(MessagePacketClientToServerTypes.LIST_BIDS_OF_ALL_AUCTIONS_BY_AUCTION_ID,
				paramsMap, "");
		sendMessage(message);
	}
	
	private void listBidsOfOpenedAuctionByAuctionID() throws IOException {
		System.out.println("Enter auctionID: ");
		String auctionID = br.readLine();
		HashMap<String,String> paramsMap = new HashMap<String, String>();
		paramsMap.put("auction-id", auctionID);
		MessagePacketClientToServer message = new MessagePacketClientToServer(MessagePacketClientToServerTypes.LIST_BIDS_OF_OPENED_AUCTIONS_BY_AUCTION_ID,
				paramsMap, "");
		sendMessage(message);
	}
	
	private void listBidsOfClosedAuctionsByAuctionID() throws IOException {
		System.out.println("Enter auctionID: ");
		String auctionID = br.readLine();
		HashMap<String,String> paramsMap = new HashMap<String, String>();
		paramsMap.put("auction-id", auctionID);
		MessagePacketClientToServer message = new MessagePacketClientToServer(MessagePacketClientToServerTypes.LIST_BIDS_OF_CLOSED_AUCTIONS_BY_AUCTION_ID,
				paramsMap, "");
		sendMessage(message);
	}
	
	private void listBidsOfAllAuctionsByAuctionIDAndClientID() throws IOException {
		System.out.println("Enter auctionID: ");
		String auctionID = br.readLine();
		System.out.println("Enter bidderID: ");
		String bidderID = br.readLine();
		HashMap<String,String> paramsMap = new HashMap<String, String>();
		paramsMap.put("auction-id", auctionID);
		paramsMap.put("bidder-user-client-id", bidderID);
		MessagePacketClientToServer message = new MessagePacketClientToServer(MessagePacketClientToServerTypes.LIST_BIDS_OF_ALL_AUCTIONS_BY_AUCTION_ID_AND_CLIENT_ID,
				paramsMap, "");
		sendMessage(message);
	}
	
	private void listBidsOfOpenedAuctionsByAuctionIDAndClientID() throws IOException {
		System.out.println("Enter auctionID: ");
		String auctionID = br.readLine();
		System.out.println("Enter bidderID: ");
		String bidderID = br.readLine();
		HashMap<String,String> paramsMap = new HashMap<String, String>();
		paramsMap.put("auction-id", auctionID);
		paramsMap.put("bidder-user-client-id", bidderID);
		MessagePacketClientToServer message = new MessagePacketClientToServer(MessagePacketClientToServerTypes.LIST_BIDS_OF_OPENED_AUCTIONS_BY_AUCTION_ID_AND_CLIENT_ID,
				paramsMap, "");
		sendMessage(message);
	}
	
	private void listBidsOfClosedAuctionsByAuctionIDAndClientID() throws IOException {
		System.out.println("Enter auctionID: ");
		String auctionID = br.readLine();
		System.out.println("Enter bidderID: ");
		String bidderID = br.readLine();
		HashMap<String,String> paramsMap = new HashMap<String, String>();
		paramsMap.put("auction-id", auctionID);
		paramsMap.put("bidder-user-client-id", bidderID);
		MessagePacketClientToServer message = new MessagePacketClientToServer(MessagePacketClientToServerTypes.LIST_BIDS_OF_CLOSED_AUCTIONS_BY_AUCTION_ID_AND_CLIENT_ID,
				paramsMap, "");
		sendMessage(message);
	}
	
	private void listAllBidsByClientID() throws IOException {
		System.out.println("Enter bidderID: ");
		String bidderID = br.readLine();
		HashMap<String,String> paramsMap = new HashMap<String, String>();
		paramsMap.put("bidder-user-client-id", bidderID);
		MessagePacketClientToServer message = new MessagePacketClientToServer(MessagePacketClientToServerTypes.LIST_ALL_BIDS_BY_CLIENT_ID,
				paramsMap, "");
		sendMessage(message);
	}
	
	private void listOpenedBidsByClientID() throws IOException {
		System.out.println("Enter bidderID: ");
		String bidderID = br.readLine();
		HashMap<String,String> paramsMap = new HashMap<String, String>();
		paramsMap.put("bidder-user-client-id", bidderID);
		MessagePacketClientToServer message = new MessagePacketClientToServer(MessagePacketClientToServerTypes.LIST_OPENED_BIDS_BY_CLIENT_ID,
				paramsMap, "");
		sendMessage(message);
	}
	
	private void listClosedBidsByClientID() throws IOException {
		System.out.println("Enter bidderID: ");
		String bidderID = br.readLine();
		HashMap<String,String> paramsMap = new HashMap<String, String>();
		paramsMap.put("bidder-user-client-id", bidderID);
		MessagePacketClientToServer message = new MessagePacketClientToServer(MessagePacketClientToServerTypes.LIST_CLOSED_BIDS_BY_CLIENT_ID,
				paramsMap, "");
		sendMessage(message);
	}
	
	private void checkOutcomeAllAuctions() throws IOException {
		HashMap<String,String> paramsMap = new HashMap<String, String>();
		paramsMap.put("bidder-user-client-id", this.currentUser.getUserPeerID());
		MessagePacketClientToServer message = new MessagePacketClientToServer(MessagePacketClientToServerTypes.CHECK_OUTCOME_ALL_AUCTION,
				paramsMap, "");
		sendMessage(message);
	}
	
	private void checkOutcomeOpenedAuctions() throws IOException {
		HashMap<String,String> paramsMap = new HashMap<String, String>();
		paramsMap.put("bidder-user-client-id", this.currentUser.getUserPeerID());
		MessagePacketClientToServer message = new MessagePacketClientToServer(MessagePacketClientToServerTypes.CHECK_OUTCOME_OPENED_AUCTION,
				paramsMap, "");
		sendMessage(message);
	}
	
	private void checkOutcomeClosedAuctions() throws IOException {
		HashMap<String,String> paramsMap = new HashMap<String, String>();
		paramsMap.put("bidder-user-client-id", this.currentUser.getUserPeerID());
		MessagePacketClientToServer message = new MessagePacketClientToServer(MessagePacketClientToServerTypes.CHECK_OUTCOME_CLOSED_AUCTION,
				paramsMap, "");
		sendMessage(message);
	}
	
	private void checkOutcomeAllAuctionsByAuctionID() throws IOException {
		System.out.println("Enter auctionID: ");
		String auctionID = br.readLine();
		HashMap<String,String> paramsMap = new HashMap<String, String>();
		paramsMap.put("auction-id", auctionID);
		paramsMap.put("bidder-user-client-id", this.currentUser.getUserPeerID());
		MessagePacketClientToServer message = new MessagePacketClientToServer(MessagePacketClientToServerTypes.CHECK_OUTCOME_ALL_AUCTION_ID,
				paramsMap, "");
		sendMessage(message);
	}
	
	private void checkOutcomeOpenedAuctionsByAuctionID() throws IOException {
		System.out.println("Enter auctionID: ");
		String auctionID = br.readLine();
		HashMap<String,String> paramsMap = new HashMap<String, String>();
		paramsMap.put("auction-id", auctionID);
		paramsMap.put("bidder-user-client-id", this.currentUser.getUserPeerID());
		MessagePacketClientToServer message = new MessagePacketClientToServer(MessagePacketClientToServerTypes.CHECK_OUTCOME_OPENED_AUCTION_ID,
				paramsMap, "");
		sendMessage(message);
	}
	
	private void checkOutcomeClosedAuctionsByAuctionID() throws IOException {
		System.out.println("Enter auctionID: ");
		String auctionID = br.readLine();
		HashMap<String,String> paramsMap = new HashMap<String, String>();
		paramsMap.put("auction-id", auctionID);
		paramsMap.put("bidder-user-client-id", this.currentUser.getUserPeerID());
		MessagePacketClientToServer message = new MessagePacketClientToServer(MessagePacketClientToServerTypes.CHECK_OUTCOME_CLOSED_AUCTION_ID,
				paramsMap, "");
		sendMessage(message);
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

	// TODO Remove when confirmed thread works
//	private String sslReadResponse(InputStream socketInStream) throws IOException {
//		//Intercept auction bid message
//		//If special message, run special method TODO
//		BufferedReader br = new BufferedReader(new InputStreamReader(socketInStream));
//		String response = br.readLine();
//		return response;
//	}

	// TODO Remove when confirmed thread works
//	private String sendMessageAndGetResponse(SSLSocketMessage message) throws IOException {
//		OutputStream out = socket.getOutputStream();
//		PrintWriter printWriter = new PrintWriter(out);
//
//		printWriter.print(gson.toJson(message) + System.lineSeparator());
//		printWriter.flush();
//
//		InputStream in = socket.getInputStream();
//		return sslReadResponse(in);
//	}
	
	public void sendMessage(MessagePacketClientToServer message) {
		PrintWriter printWriter = new PrintWriter(outputStream);

		printWriter.print(gson.toJson(message) + System.lineSeparator());
		printWriter.flush();
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
	
	private void printErrorStringWithClassName(Object message) {
		System.err.println("[" + this.getClass().getCanonicalName() + "] " + 
				"Response: " + message);
	}
	
	private String decodeReceipt(String toDecode){
		String result = "COMPLETE ME (THE RECEIPT DECODE) PLS!!!";
		byte[] secureReceiptMessageSerialized = Base64.getDecoder().decode(toDecode);
		
		try {
			SecureReceiptMessage secureReceiptMessage = 
					new SecureReceiptMessage(secureReceiptMessageSerialized);
			
			secureReceiptMessage.undoSecureReceiptMessageSerialized();
			
			SecureReceiptMessageDoSMitigation secureReceiptMessageDoSMitigation =
					secureReceiptMessage.getSecureReceiptMessageDoSMitigation();
			
			if(!secureReceiptMessageDoSMitigation.checkIfHashOfSecureReceiptMessageDoSMitigationIsValid()) {
				System.out.println("OH NOES!");
			}
			else {
				SecureReceiptMessageComponents secureReceiptMessageComponents = 
						secureReceiptMessage.getSecureReceiptMessageComponents();
				
				secureReceiptMessageComponents.decryptSecureReceiptMessageComponents();
				secureReceiptMessageComponents.undoSecureReceiptMessageComponentsSerialization();
				
				SecureCommonHeader secureCommonHeader = secureReceiptMessageComponents.getSecureCommonHeader();
				
				// TODO - verificar header
				
				
				
				SecureReceiptMessageComponentsData secureReceiptMessageComponentsData = 
						secureReceiptMessageComponents.getSecureReceiptMessageComponentsData();
				
				SecureReceiptMessageComponentsDataInfo secureReceiptMessageComponentsDataInfo = 
						secureReceiptMessageComponentsData.getSecureReceiptMessageComponentsDataInfo();
				
				secureReceiptMessageComponentsDataInfo.undoSecureReceiptMessageComponentsDataInfoSerialization();
				
				SecureReceiptMessageComponentsDataSignature secureReceiptMessageComponentsDataSignature = 
						secureReceiptMessageComponentsData.getSecureReceiptMessageComponentsDataSignature();
				
				String secureReceiptMessageComponentsDataResponse = 
						secureReceiptMessageComponentsData.getSecureReceiptMessageComponentsDataResponse();
				
				
				if(!secureReceiptMessageComponentsDataSignature.checkIfSecureReceiptMessageComponentsDataInfoDigitalSignedIsValid()) {
					//TODO error
					result = "ERROR";
				}
				else {
					result =  secureReceiptMessageComponentsDataResponse;
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
