package main.java.sys.rest.server.auction;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;

import main.java.sys.rest.server.auction.configuration.utils.AuctionServerKeyStoreConfigurationReader;
import main.java.sys.rest.server.auction.configuration.utils.AuctionServerTLSConfigurationReader;

public class AuctionServerEntryPoint extends Thread{

	private static final String AUCTION_SERVER_TLS_CONFIGURATION_PATH = "res/configurations/auction-server-tls-configuration.conf";
	private static final String AUCTION_SERVER_STORES_CONFIGURATION_PATH = "res/configurations/auction-server-keystore-configuration.conf";
	
	private static final String TLS_CONF_CLIENT_ONLY = "CLIENT-ONLY";
	private static final String TLS_CONF_SERVER_ONLY = "SERVER-ONLY";
	private static final String TLS_CONF_MUTUAL = "MUTUAL";
	
	SSLServerSocketFactory serverSocketFactory;
	SSLServerSocket serverSocket;
	SSLSocket responseSocket;
	
	public static void main(String[] args) throws UnrecoverableKeyException, KeyManagementException, NoSuchAlgorithmException, KeyStoreException, CertificateException, IOException {
		new AuctionServerEntryPoint();
	}
	
	public AuctionServerEntryPoint() throws NoSuchAlgorithmException, IOException, UnrecoverableKeyException, KeyManagementException, KeyStoreException, CertificateException {
		AuctionServerTLSConfigurationReader tlsConfigurationReader = null;
		AuctionServerKeyStoreConfigurationReader storesConfigurationReader = null;
		try {
			tlsConfigurationReader = new AuctionServerTLSConfigurationReader(AUCTION_SERVER_TLS_CONFIGURATION_PATH);
			storesConfigurationReader = new AuctionServerKeyStoreConfigurationReader(AUCTION_SERVER_STORES_CONFIGURATION_PATH);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.setProperty("javax.net.ssl.keyStore", storesConfigurationReader.getKeyStoreFileLocationPath());
		System.setProperty("javax.net.ssl.keyStorePassword", storesConfigurationReader.getKeyStorePassword());

		System.setProperty("javax.net.ssl.trustStore", storesConfigurationReader.getTrustStoreFileLocationPath());
		System.setProperty("javax.net.ssl.trustStorePassword", storesConfigurationReader.getTrustStorePassword());

		System.out.println(storesConfigurationReader.getKeyStoreFileLocationPath());
		System.out.println(storesConfigurationReader.getKeyStorePassword());
		System.out.println(storesConfigurationReader.getTrustStoreFileLocationPath());
		System.out.println(storesConfigurationReader.getTrustStorePassword());
		
//		kmf = KeyManagerFactory.getInstance("SunX509");
//		ks = KeyStore.getInstance("JKS");		
//		sslContext.init(null, null, null);
		SSLContext sslContext = SSLContext.getDefault();
		SSLServerSocketFactory serverSocketFactory = sslContext.getServerSocketFactory();
		serverSocket = (SSLServerSocket)serverSocketFactory.createServerSocket(8443);
		serverSocket.setEnabledCipherSuites(tlsConfigurationReader.getAvailableTLSCiphersuites());
		serverSocket.setEnabledProtocols(tlsConfigurationReader.getAvailableTLSVersions());
		String[] mutualAuth = tlsConfigurationReader.getAvailableTLSAuthenticationModes();
		// TODO Change this maybe!
		if(mutualAuth[0].equals(TLS_CONF_MUTUAL))
			serverSocket.setNeedClientAuth(true);
		
		while(true) {
			responseSocket = (SSLSocket) serverSocket.accept();
			responseSocket.startHandshake();

			Thread t = new AuctionServer(serverSocket, responseSocket);
			t.start();
		}
	}
	
}
