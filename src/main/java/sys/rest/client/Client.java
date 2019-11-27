package main.java.sys.rest.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.HashMap;

import javax.ws.rs.core.UriBuilder;

import org.apache.http.ParseException;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
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

public class Client implements ClientAPI {

	private static final String CREATE_AUCTION = "create";
	private static final String HELP = "help";
	private static final String EXIT = "exit";
	
	private static final String HASH_ALGORITHM = "SHA-256";

	private User currentUser;

	private Gson gson;
	private final CloseableHttpClient httpClient;
	private Dao<User, String> userDao;


	public static void main(String[] args) {
		int port = 8082;
		URI baseUri = UriBuilder.fromUri("http://0.0.0.0/").port(port).build();
		ResourceConfig config = new ResourceConfig();
		config.register( new Client() );

		JdkHttpServerFactory.createHttpServer(baseUri, config);

		System.out.println("Client ready @ " + baseUri);
	}

	@SuppressWarnings("null")
	public Client() {

		String userRepository = "jdbc:sqlite:res/database/client/users.db";
		gson = new Gson();

		ConnectionSource connectionSource;
		try {
			connectionSource = new JdbcConnectionSource(userRepository);
			userDao = DaoManager.createDao(connectionSource, User.class);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		httpClient = HttpClients.createDefault();

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = null;

		try {
			while((line = br.readLine()) != null)
			{
				
				if(currentUser == null) {
					currentUser = login(br.readLine(), br.readLine());
				}
				
				switch (line) {
				case CREATE_AUCTION:
					createAuction();
					break;

				case HELP:
					System.out.println("HELP SCREEN");
					System.out.println(CREATE_AUCTION);
					System.out.println(HELP);
					System.out.println(EXIT);
					break;

				case EXIT:
					System.exit(0);
					break;

				default:
					System.out.println("HELP SCREEN");
					System.out.println(CREATE_AUCTION);
					System.out.println(HELP);
					System.out.println(EXIT);
					break;
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void createAuction() {
		HttpPost post = new HttpPost("http://localhost:8080/open-normal-auction");
		String currentUserAsJson = gson.toJson(currentUser);
		String result = null;

		try {
			post.setEntity(new StringEntity(currentUserAsJson));

			CloseableHttpResponse response = null;
			response = httpClient.execute(post);

			result = EntityUtils.toString(response.getEntity());
		} catch (ParseException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(result);
	}

	private User login(String id, String password) {
		User result = null;

		try {
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
}
