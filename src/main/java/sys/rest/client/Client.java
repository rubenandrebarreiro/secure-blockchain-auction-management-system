package main.java.sys.rest.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.ws.rs.core.UriBuilder;

import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.Dsl;
import org.asynchttpclient.ListenableFuture;
import org.asynchttpclient.Response;
import org.bouncycastle.util.encoders.Hex;
import org.glassfish.jersey.jdkhttp.JdkHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import com.google.gson.Gson;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.misc.IOUtils;
import com.j256.ormlite.support.ConnectionSource;

import main.java.api.rest.client.ClientAPI;
import main.java.resources.user.User;
import main.java.resources.user.UserAuctionInfo;
import main.java.sys.SSLSocketAuctionOperation;
import main.java.sys.SSLSocketMessage;

public class Client implements ClientAPI {

	private static final String OPEN_AUCTION = "create auction";
	private static final String CLOSE_AUCTION = "close auction";
	private static final String ADD_BID = "create bid";
	private static final String LIST_ALL_AUCTIONS = "list all";
	private static final String LIST_OPENED_AUCTIONS = "list open";
	private static final String LIST_CLOSED_AUCTIONS = "list closed";
	private static final String LIST_ALL_AUCTIONS_BY_OWNER = "list all userID";
	private static final String LIST_OPENED_AUCTIONS_BY_OWNER = "list open userID";
	private static final String LIST_CLOSED_AUCTIONS_BY_OWNER = "list closed userID";
	private static final String LIST_ALL_AUCTIONS_BY_ID = "list all auctionID";
	private static final String LIST_OPENED_AUCTIONS_BY_ID = "list open auctionID";
	private static final String LIST_CLOSED_AUCTIONS_BY_ID = "list closed auctionID";
	//TODO Implement these!
	private static final String LIST_BIDS_OF_ALL_AUCTIONS_BY_AUCTION_ID = "list all bids auctionID";
	private static final String LIST_BIDS_OF_OPENED_AUCTIONS_BY_AUCTION_ID = "list opened bids auctionID";
	private static final String LIST_BIDS_OF_CLOSED_AUCTIONS_BY_AUCTION_ID = "list closed bids auctionID";
	private static final String LIST_BIDS_OF_ALL_AUCTIONS_BY_AUCTION_ID_AND_CLIENT_ID = "list all bids auctionID userID";
	private static final String LIST_BIDS_OF_OPENED_AUCTIONS_BY_AUCTION_ID_AND_CLIENT_ID = "list opened bids auctionID userID";
	private static final String LIST_BIDS_OF_CLOSED_AUCTIONS_BY_AUCTION_ID_AND_CLIENT_ID = "list closed bids auctionID userID";

	private static final String HELP = "help";
	private static final String EXIT = "exit";

	private static final String HASH_ALGORITHM = "SHA-256";

	private static final String AUCTION_SERVER_ADDRESS = "https://localhost:8081/auction-server";
	private static final String USER_DATABASE_JDBC_PATH = "jdbc:sqlite:res/database/client/users.db";

	private static final String ENCONDING = "UTF-8";

	private User currentUser;

	private Gson gson;
	private AsyncHttpClient httpClient;
	private Dao<User, String> userDao;

	private BufferedReader br;

	private SSLSocket socket;
	private SSLSocketFactory socketFactory;
	public static void main(String[] args) {

		if(args.length != 6) {

			System.err.println
			(String.format
					("Usage: java AuctionRepositoryServer <url> <port> "
							+ "<key-store-file-path> <key-store-password>"
							+ "<trust-store-file-path> <trust-store-password>"
							)
					);

			System.exit(1);
		}

		String url = args[0];
		int serverPort = Integer.parseInt(args[1]);

		String keyStoreFilePath = args[2];
		String keyStorePassword = args[3];

		String trustStoreFilePath = args[4];
		String trustStorePassword = args[5];

		new Client(url, serverPort,
				keyStoreFilePath, keyStorePassword,
				trustStoreFilePath, trustStorePassword);				
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
			String keyStorePath, String keyStorePassword,
			String trustStorePath, String trustStorePassword) {

		//SSL Connection

		try {
			System.setProperty("javax.net.ssl.keyStore", keyStorePath);
			System.setProperty("javax.net.ssl.keyStorePassword", keyStorePassword);
			System.setProperty("javax.net.ssl.trustStore", trustStorePath);
			System.setProperty("javax.net.ssl.trustStorePassword", trustStorePassword);

			//			System.setProperty("javax.net.debug", "SSL,handshake");

			SSLContext sslContext = SSLContext.getDefault();

			socketFactory = sslContext.getSocketFactory();
			socket = (SSLSocket)socketFactory.createSocket(url, serverPort);
			//		    socket.setSoTimeout(1000);
			socket.startHandshake();
		} catch (Exception e) {
			System.err.println("Error setting up TLS connection/socket!");
			e.getMessage();
			e.printStackTrace();
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

		httpClient = Dsl.asyncHttpClient();

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
					listOpen();
					break;
				case LIST_CLOSED_AUCTIONS:
					listClosed();
					break;
				case LIST_ALL_AUCTIONS_BY_OWNER:
					listAllByUserID();
					break;
				case LIST_OPENED_AUCTIONS_BY_OWNER:
					listOpenByUserID();
					break;
				case LIST_CLOSED_AUCTIONS_BY_OWNER:
					listClosedByUserID();
					break;
				case LIST_ALL_AUCTIONS_BY_ID:
					listAllByAuctionID();
					break;
				case LIST_CLOSED_AUCTIONS_BY_ID:
					listClosedByAuctionID();
					break;
				case LIST_OPENED_AUCTIONS_BY_ID:
					listOpenByAuctionID();
					break;
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
			System.out.print("Enter user identifiction: ");
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
						+ "1: NORMAL_BIDS,\n" + 
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
		sendMessage(message);
	}

	private void closeAuction() throws IOException {
		System.out.println("Enter auctionID to close: ");
		String auctionIDToClose = br.readLine();
		HashMap<String,String> paramsMap = new HashMap<String, String>();
		paramsMap.put("auction-id", auctionIDToClose);
		SSLSocketMessage message = new SSLSocketMessage(SSLSocketAuctionOperation.CLOSE_AUCTION, paramsMap, "");
		sendMessage(message);
	}

	private void createBid() throws IOException{
		// TODO
		String result;
		System.out.println("Enter auctionID to bid: ");
		String auctionID = br.readLine();
		System.out.println("Enter bid amount: ");
		String bidAmount = br.readLine();

		String url = AUCTION_SERVER_ADDRESS + "/add-bid-to-opened-auction/)" + auctionID;
	}

	private void listAll() throws IOException {
		SSLSocketMessage message = new SSLSocketMessage(SSLSocketAuctionOperation.LIST_ALL_AUCTIONS,
				new HashMap<String, String>(), "");
		sendMessage(message);
	}

	private void listOpen() throws IOException {
		SSLSocketMessage message = new SSLSocketMessage(SSLSocketAuctionOperation.LIST_OPENED_AUCTIONS,
				new HashMap<String, String>(), "");
		sendMessage(message);
	}

	private void listClosed() throws IOException {
		SSLSocketMessage message = new SSLSocketMessage(SSLSocketAuctionOperation.LIST_CLOSED_AUCTIONS_BY_OWNER,
				new HashMap<String, String>(), "");
		sendMessage(message);
	}

	private void listAllByUserID() throws IOException{
		System.out.println("Enter userID: ");
		String userID = br.readLine();
		HashMap<String,String> paramsMap = new HashMap<String, String>();
		paramsMap.put("product-owner-user-client-id", userID);
		SSLSocketMessage message = new SSLSocketMessage(SSLSocketAuctionOperation.LIST_ALL_AUCTIONS_BY_OWNER,
				new HashMap<String, String>(), "");
		sendMessage(message);
	}

	private void listOpenByUserID() throws IOException{
		System.out.println("Enter userID: ");
		String userID = br.readLine();
		HashMap<String,String> paramsMap = new HashMap<String, String>();
		paramsMap.put("product-owner-user-client-id", userID);
		SSLSocketMessage message = new SSLSocketMessage(SSLSocketAuctionOperation.LIST_OPENED_AUCTIONS_BY_OWNER,
				new HashMap<String, String>(), "");
		sendMessage(message);
	}

	private void listClosedByUserID() throws IOException{
		System.out.println("Enter userID: ");
		String userID = br.readLine();
		HashMap<String,String> paramsMap = new HashMap<String, String>();
		paramsMap.put("product-owner-user-client-id", userID);
		SSLSocketMessage message = new SSLSocketMessage(SSLSocketAuctionOperation.LIST_CLOSED_AUCTIONS_BY_OWNER,
				new HashMap<String, String>(), "");
		sendMessage(message);
	}

	private void listAllByAuctionID() throws IOException {
		System.out.println("Enter auctionID: ");
		String auctionID = br.readLine();
		HashMap<String,String> paramsMap = new HashMap<String, String>();
		paramsMap.put("product-owner-user-client-id", auctionID);
		SSLSocketMessage message = new SSLSocketMessage(SSLSocketAuctionOperation.LIST_ALL_AUCTIONS_BY_ID,
				new HashMap<String, String>(), "");
		sendMessage(message);
	}

	private void listOpenByAuctionID() throws IOException {
		System.out.println("Enter auctionID: ");
		String auctionID = br.readLine();
		HashMap<String,String> paramsMap = new HashMap<String, String>();
		paramsMap.put("product-owner-user-client-id", auctionID);
		SSLSocketMessage message = new SSLSocketMessage(SSLSocketAuctionOperation.LIST_OPENED_AUCTIONS_BY_ID,
				new HashMap<String, String>(), "");
		sendMessage(message);
	}

	private void listClosedByAuctionID() throws IOException {
		System.out.println("Enter auctionID: ");
		String auctionID = br.readLine();
		HashMap<String,String> paramsMap = new HashMap<String, String>();
		paramsMap.put("product-owner-user-client-id", auctionID);
		SSLSocketMessage message = new SSLSocketMessage(SSLSocketAuctionOperation.LIST_CLOSED_AUCTIONS_BY_ID,
				new HashMap<String, String>(), "");
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
		//TODO Implements these!
		System.out.println(LIST_BIDS_OF_ALL_AUCTIONS_BY_AUCTION_ID);
		System.out.println(LIST_BIDS_OF_OPENED_AUCTIONS_BY_AUCTION_ID);
		System.out.println(LIST_BIDS_OF_CLOSED_AUCTIONS_BY_AUCTION_ID);
		System.out.println(LIST_BIDS_OF_ALL_AUCTIONS_BY_AUCTION_ID_AND_CLIENT_ID);
		System.out.println(LIST_BIDS_OF_OPENED_AUCTIONS_BY_AUCTION_ID_AND_CLIENT_ID);
		System.out.println(LIST_BIDS_OF_CLOSED_AUCTIONS_BY_AUCTION_ID_AND_CLIENT_ID);

		System.out.println(HELP);
		System.out.println(EXIT);
	}

	private void sslReadResponse(InputStream socketInStream) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(socketInStream));
		System.out.println(br.readLine());					
	}

	private HttpsURLConnection createGetRequest(String urlString, Map<String, String> params) {
		URL url = null;
		HttpsURLConnection result = null;

		try {
			url = new URL(addParamsToURL(urlString, params));
			result = (HttpsURLConnection)url.openConnection();
			result.setRequestMethod("GET");
		} catch (MalformedURLException e) {
			System.err.println("Invalid URL");
		} catch (IOException e) {
			System.err.println("Error opening connection");
		}

		return result;
	}

	private byte[] createPostRequest(String urlString, Map<String, String> params) {
		URL url = null;
		HttpsURLConnection result = null;

		try {
			url = new URL(addParamsToURL(urlString, params));
			result = (HttpsURLConnection)url.openConnection();
			result.setRequestMethod("POST");
		} catch (MalformedURLException e) {
			System.err.println("Invalid URL");
		} catch (IOException e) {
			System.err.println("Error opening connection");
		}


		return null;	
	}

	private byte[] createPutRequest(String urlString, Map<String, String> params) {
		URL url = null;
		HttpsURLConnection result = null;

		try {
			url = new URL(urlString);
			result = (HttpsURLConnection)url.openConnection();
			result.setRequestMethod("PUT");
		} catch (MalformedURLException e) {
			System.err.println("Invalid URL");
		} catch (IOException e) {
			System.err.println("Error opening connection");
		}


		return null;	
	}

	private byte[] createDeleteRequest(String urlString, Map<String, String> params) {
		URL url = null;
		HttpsURLConnection result = null;

		try {
			url = new URL(urlString);
			result = (HttpsURLConnection)url.openConnection();
			result.setRequestMethod("DELETE");
		} catch (MalformedURLException e) {
			System.err.println("Invalid URL");
		} catch (IOException e) {
			System.err.println("Error opening connection");
		}

		return null;	
	}

	private String addParamsToURL(String urlString, Map<String, String> params) {
		StringBuilder builder = new StringBuilder();
		boolean first = true;
		try {
			builder.append(urlString);
			if(params != null) {
				Set<Entry<String, String>> entryMap = params.entrySet();
				if(entryMap.size() != 0) {
					for (Entry<String, String> entry : entryMap) {
						if(first) first = false;
						else builder.append("&");
						builder.append(URLEncoder.encode(entry.getKey(), ENCONDING));
						builder.append("=");
						builder.append(URLEncoder.encode(entry.getValue(), ENCONDING));
					}
				}
			}
		} catch (UnsupportedEncodingException e) {
			System.err.println("Error while adding parameters to URL");
		}

		return builder.toString();
	}

	private void sendMessage(SSLSocketMessage message) throws IOException {
		OutputStream out = socket.getOutputStream();
		PrintWriter printWriter = new PrintWriter(out);

		printWriter.print(gson.toJson(message) + System.lineSeparator());
		printWriter.flush();

		InputStream in = socket.getInputStream();
		sslReadResponse(in);
	}

}
