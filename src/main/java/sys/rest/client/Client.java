package main.java.sys.rest.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.sql.SQLException;

import javax.ws.rs.core.UriBuilder;

import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
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

		System.out.print("Enter userID to login: ");
		try {
			line = br.readLine();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		currentUser = login(line);

		try {
			while( (line = br.readLine()) != null || line.equals(EXIT))
			{
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
		try {
			post.setEntity(new StringEntity(currentUserAsJson));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		CloseableHttpResponse response = null;
		try {
			response = httpClient.execute(post);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String result = null;
		try {
			result = EntityUtils.toString(response.getEntity());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	

		System.out.println(result);
	}

	private User login(String id) {
		User result = null;
		try {
			result = userDao.queryForId(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Got user: " + result.getUserPeerID());
		
		return result;
	}
}
