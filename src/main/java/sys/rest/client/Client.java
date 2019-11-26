package main.java.sys.rest.client;

import java.net.URI;

import javax.jcr.LoginException;
import javax.jcr.RepositoryException;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.jdkhttp.JdkHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import main.java.api.rest.client.ClientAPI;
import main.java.sys.rest.server.auction.repository.AuctionRepositoryServer;

public class Client implements ClientAPI {

	public static void main(String[] args) throws LoginException, RepositoryException {
		// TODO Auto-generated method stub
		int port = 8082;
		URI baseUri = UriBuilder.fromUri("http://0.0.0.0/").port(port).build();
		ResourceConfig config = new ResourceConfig();
		config.register( new AuctionRepositoryServer((byte)1));
		
		JdkHttpServerFactory.createHttpServer(baseUri, config);

		System.out.println("Client ready @ " + baseUri);
	}

}
