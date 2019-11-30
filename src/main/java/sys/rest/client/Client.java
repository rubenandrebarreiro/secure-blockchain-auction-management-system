package main.java.sys.rest.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.security.MessageDigest;
import java.sql.SQLException;
import java.util.HashMap;

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
import com.j256.ormlite.support.ConnectionSource;

import main.java.api.rest.client.ClientAPI;
import main.java.resources.user.User;
import main.java.resources.user.UserAuctionInfo;

public class Client implements ClientAPI {

	private static final String AUCTION_CREATE = "create auction";
	private static final String AUCTION_CLOSE = "close auction";
	private static final String BID_CREATE = "create bid";
	private static final String LIST_ALL = "list all";
	private static final String LIST_OPEN = "list open";
	private static final String LIST_CLOSED = "list closed";
	private static final String LIST_BY_USERID = "list all userID";
	private static final String LIST_OPEN_BY_USERID = "list open userID";
	private static final String LIST_CLOSED_BY_USERID = "list closed userID";
	private static final String LIST_BY_AUCTIONID = "list all auctionID";
	private static final String LIST_OPEN_BY_AUCTIONID = "list open auctionID";
	private static final String LIST_CLOSED_BY_AUCTIONID = "list closed auctionID";
	private static final String HELP = "help";
	private static final String EXIT = "exit";

	private static final String HASH_ALGORITHM = "SHA-256";

	private static final String AUCTION_SERVER_ADDRESS = "http://localhost:8081/auction-server";
	private static final String USER_DATABASE_JDBC_PATH = "jdbc:sqlite:res/database/client/users.db";

	private User currentUser;

	private Gson gson;
	private AsyncHttpClient httpClient;
	private Dao<User, String> userDao;

	private BufferedReader br;
	
	private SSLSocket socket;

	public static void main(String[] args) {
		int port = 8082;
		URI baseUri = UriBuilder.fromUri("http://0.0.0.0/").port(port).build();
		ResourceConfig config = new ResourceConfig();
		config.register( new Client() );

		JdkHttpServerFactory.createHttpServer(baseUri, config);

		System.out.println("Client ready @ " + baseUri);

	}

	public Client() {

		//SSL Connection

		try {
			System.setProperty("javax.net.ssl.trustStore", "res/keystore/truststores/eduardoTruststore.jks");
			System.setProperty("javax.net.ssl.trustStorePassword", "eduardo1920");
			System.setProperty("javax.net.ssl.keyStore", "res/keystore/keystores/eduardoKeystore.jks");
			System.setProperty("javax.net.ssl.keyStorePassword", "eduardo1920");
			
			System.setProperty("javax.net.debug", "SSL,handshake");
			
			SSLContext sslContext = SSLContext.getDefault();
			
		    SSLSocketFactory factory = sslContext.getSocketFactory();
		    socket = (SSLSocket)factory.createSocket("192.168.1.6", 8443);
//		    socket.setEnabledProtocols(new String[] { "TLSv1.2" });
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
				case AUCTION_CREATE:
					createAuction();
					break;
				case AUCTION_CLOSE:
					closeAuction();
					break;
				case BID_CREATE:
					createBid();
					break;
				case LIST_ALL:
					listAll();
					break;
				case LIST_OPEN:
					listOpen();
					break;
				case LIST_CLOSED:
					listClosed();
					break;
				case LIST_BY_USERID:
					listAllByUserID();
					break;
				case LIST_OPEN_BY_USERID:
					listOpenByUserID();
					break;
				case LIST_CLOSED_BY_USERID:
					listClosedByUserID();
					break;
				case LIST_BY_AUCTIONID:
					listAllByAuctionID();
					break;
				case LIST_CLOSED_BY_AUCTIONID:
					listClosedByAuctionID();
					break;
				case LIST_OPEN_BY_AUCTIONID:
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
		String result = null;
		String url = AUCTION_SERVER_ADDRESS + "/open-auction";

		System.out.println("Enter product description: ");
		String productDescription = br.readLine();
		System.out.println(
				"	1: NORMAL_BIDS,\n" + 
						"	2: MIN_INITIAL_VALUE_BID\n" + 
						"	3: MIN_AMOUNT_VALUE_BID\n" + 
						"	4: MAX_AMOUNT_VALUE_BID\n" + 
						"	5: MIN_MAX_AMOUNT_VALUE_BID\n" + 
						"	6: LIMITED_SET_CLIENT_BIDDERS\n" + 
						"	7: LIMITED_NUMBER_BIDS_FOR_EACH_CLIENT\n" + 
						"	8: LIMITED_NUMBER_BIDS\n" + 
				"	9: LIMITED_TIME_BIDS");
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
			url = AUCTION_SERVER_ADDRESS + "/open-auction";
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

		ListenableFuture<Response> future;

		future = httpClient.preparePost(url)
				.setBody(postData)
				.execute();

		Response r = null;
		try {
			r = future.get();
		} catch (Exception e) {
			System.err.println("[" + this.getClass().getCanonicalName() + "]" + 
					"Error getting response!");
		}

		result = r.getStatusText();

		System.out.println(result);
	}

	private void closeAuction() throws IOException {
		String result;
		System.out.println("Enter auctionID to close: ");
		String auctionIDToClose = br.readLine();
		String url = AUCTION_SERVER_ADDRESS + "/close-auction/" + auctionIDToClose;
		System.out.println(url);
		ListenableFuture<Response> future;

		future = httpClient.preparePut(url)
				.execute();

		Response r = null;
		try {
			r = future.get();
		} catch (Exception e) {
			System.err.println("[" + this.getClass().getCanonicalName() + "]" + 
					"Error getting response!");
		}

		result = r.getStatusText();

		System.out.println(result);
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
	
	private void listAll() {
		String url = AUCTION_SERVER_ADDRESS + "/all";
		String result;
		ListenableFuture<Response> future;

		future = httpClient.prepareGet(url)
				.execute();

		Response r = null;
		try {
			r = future.get();
		} catch (Exception e) {
			System.err.println("[" + this.getClass().getCanonicalName() + "]" + 
					"Error getting response!");
		}

		result = r.getStatusText();

		System.out.println(result);
	}
	
	private void listOpen() {
		String url = AUCTION_SERVER_ADDRESS + "/opened";
		String result;
		ListenableFuture<Response> future;

		future = httpClient.prepareGet(url)
				.execute();

		Response r = null;
		try {
			r = future.get();
		} catch (Exception e) {
			System.err.println("[" + this.getClass().getCanonicalName() + "]" + 
					"Error getting response!");
		}

		result = r.getStatusText();

		System.out.println(result);
	}
	
	private void listClosed() {
		String url = AUCTION_SERVER_ADDRESS + "/closed";
		String result;
		ListenableFuture<Response> future;

		future = httpClient.prepareGet(url)
				.execute();

		Response r = null;
		try {
			r = future.get();
		} catch (Exception e) {
			System.err.println("[" + this.getClass().getCanonicalName() + "]" + 
					"Error getting response!");
		}

		result = r.getStatusText();

		System.out.println(result);

	}
	
	private void listAllByUserID() throws IOException{
		String result;
		System.out.println("Enter userID: ");
		String userID = br.readLine();
		String url = AUCTION_SERVER_ADDRESS + "/all/by-product-owner-user/" + userID;

		ListenableFuture<Response> future;

		future = httpClient.prepareGet(url)
				.execute();

		Response r = null;
		try {
			r = future.get();
		} catch (Exception e) {
			System.err.println("[" + this.getClass().getCanonicalName() + "]" + 
					"Error getting response!");
		}

		result = r.getStatusText();

		System.out.println(result);	}
	
	private void listOpenByUserID() throws IOException{
		String result;
		System.out.println("Enter userID: ");
		String userID = br.readLine();
		String url = AUCTION_SERVER_ADDRESS + "/opened/by-product-owner-user/" + userID;

		ListenableFuture<Response> future;

		future = httpClient.prepareGet(url)
				.execute();

		Response r = null;
		try {
			r = future.get();
		} catch (Exception e) {
			System.err.println("[" + this.getClass().getCanonicalName() + "]" + 
					"Error getting response!");
		}

		result = r.getStatusText();

		System.out.println(result);	}
	
	private void listClosedByUserID() throws IOException{
		String result;
		System.out.println("Enter userID: ");
		String userID = br.readLine();
		String url = AUCTION_SERVER_ADDRESS + "/closed/by-product-owner-user/" + userID;

		ListenableFuture<Response> future;

		future = httpClient.prepareGet(url)
				.execute();

		Response r = null;
		try {
			r = future.get();
		} catch (Exception e) {
			System.err.println("[" + this.getClass().getCanonicalName() + "]" + 
					"Error getting response!");
		}

		result = r.getStatusText();

		System.out.println(result);	}

	private void listAllByAuctionID() throws IOException {
		String result;
		System.out.println("Enter auctionID: ");
		String auctionID = br.readLine();
		String url = AUCTION_SERVER_ADDRESS + "/all/" + auctionID;

		ListenableFuture<Response> future;

		future = httpClient.prepareGet(url)
				.execute();

		Response r = null;
		try {
			r = future.get();
		} catch (Exception e) {
			System.err.println("[" + this.getClass().getCanonicalName() + "]" + 
					"Error getting response!");
		}

		result = r.getStatusText();

		System.out.println(result);
	}
	
	private void listOpenByAuctionID() throws IOException {
		String result;
		System.out.println("Enter auctionID: ");
		String auctionID = br.readLine();
		String url = AUCTION_SERVER_ADDRESS + "/opened/" + auctionID;

		ListenableFuture<Response> future;

		future = httpClient.prepareGet(url)
				.execute();

		Response r = null;
		try {
			r = future.get();
		} catch (Exception e) {
			System.err.println("[" + this.getClass().getCanonicalName() + "]" + 
					"Error getting response!");
		}

		result = r.getStatusText();

		System.out.println(result);
	}
	
	private void listClosedByAuctionID() throws IOException {
		String result;
		System.out.println("Enter auctionID: ");
		String auctionID = br.readLine();
		String url = AUCTION_SERVER_ADDRESS + "/closed/" + auctionID;

		ListenableFuture<Response> future;

		future = httpClient.prepareGet(url)
				.execute();

		Response r = null;
		try {
			r = future.get();
		} catch (Exception e) {
			System.err.println("[" + this.getClass().getCanonicalName() + "]" + 
					"Error getting response!");
		}

		result = r.getStatusText();

		System.out.println(result);
	}
	
	
	private void helpScreen() {
		System.out.println("HELP SCREEN");
		System.out.println(AUCTION_CREATE);
		System.out.println(AUCTION_CLOSE);
		System.out.println(BID_CREATE);
		System.out.println(LIST_ALL);
		System.out.println(LIST_OPEN);
		System.out.println(LIST_CLOSED);
		System.out.println(LIST_BY_USERID);
		System.out.println(LIST_OPEN_BY_USERID);
		System.out.println(LIST_CLOSED_BY_USERID);
		System.out.println(LIST_BY_AUCTIONID);
		System.out.println(LIST_OPEN_BY_AUCTIONID);
		System.out.println(LIST_CLOSED_BY_AUCTIONID);
		System.out.println(HELP);
		System.out.println(EXIT);
	}

}
