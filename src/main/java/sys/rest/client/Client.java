package main.java.sys.rest.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.security.MessageDigest;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

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

	private static final String CREATE_AUCTION = "create";
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

	public static void main(String[] args) {
		int port = 8082;
		URI baseUri = UriBuilder.fromUri("http://0.0.0.0/").port(port).build();
		ResourceConfig config = new ResourceConfig();
		config.register( new Client() );

		JdkHttpServerFactory.createHttpServer(baseUri, config);

		System.out.println("Client ready @ " + baseUri);
	}

	public Client() {

		String userRepository = USER_DATABASE_JDBC_PATH;
		gson = new Gson();

		ConnectionSource connectionSource;
		try {
			connectionSource = new JdbcConnectionSource(userRepository);
			userDao = DaoManager.createDao(connectionSource, User.class);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
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
				case CREATE_AUCTION:
					createAuction();
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
			Map<String, Integer> limitedUsersMap = new HashMap<>();
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
			Map<String, Integer> limitedUsersBidNumberMap = new HashMap<>();
			System.out.println("Enter userIDs that can bid, seperated by newlines: ");
			String user;
			while ( !(user = br.readLine()).equals("") ) {
				System.out.println("Enter " + user + " bid limit: ");
				limitedUsersBidNumberMap.put(user, Integer.parseInt(br.readLine()));
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("Got user: " + result.getUserPeerID());

		return result;
	}

	private void helpScreen() {
		System.out.println("HELP SCREEN");
		System.out.println(CREATE_AUCTION);
		System.out.println(HELP);
		System.out.println(EXIT);
	}

}
