package sys.rest.client;

import java.net.URI;

import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.jdkhttp.JdkHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import api.rest.client.ClientAPI;
import sys.rest.server.auction.repository.AuctionRepositoryServer;

public class Client implements ClientAPI {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int port = 8082;
		URI baseUri = UriBuilder.fromUri("http://0.0.0.0/").port(port).build();
		ResourceConfig config = new ResourceConfig();
		config.register( new AuctionRepositoryServer());
		
		JdkHttpServerFactory.createHttpServer(baseUri, config);

		System.out.println("Client ready @ " + baseUri);
	}

}
