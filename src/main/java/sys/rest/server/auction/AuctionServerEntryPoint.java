package main.java.sys.rest.server.auction;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.eclipse.jetty.util.BlockingArrayQueue;

import main.java.sys.rest.server.auction.configuration.utils.AuctionServerKeyStoreConfigurationReader;
import main.java.sys.rest.server.auction.configuration.utils.AuctionServerTLSConfigurationReader;

public class AuctionServerEntryPoint{

	private static final String AUCTION_SERVER_TLS_CONFIGURATION_PATH = "res/configurations/auction-server-tls-configuration.conf";
	private static final String AUCTION_SERVER_STORES_CONFIGURATION_PATH = "res/configurations/auction-server-keystore-configuration.conf";
	
	public static final String TLS_CONF_CLIENT_ONLY = "CLIENT-ONLY";
	public static final String TLS_CONF_SERVER_ONLY = "SERVER-ONLY";
	public static final String TLS_CONF_MUTUAL = "MUTUAL";
	
	private SSLServerSocketFactory serverSocketFactory;
	private SSLServerSocket serverSocket;
	private SSLSocket responseSocket;
	
	
	private Map<String, BlockingQueue<Object>> connectedClientsMap;
	
	
	public static void main(String[] args) throws UnrecoverableKeyException, KeyManagementException, NoSuchAlgorithmException, KeyStoreException, CertificateException, IOException {
		// if provider is not present, add it
		if (Security.getProvider(BouncyCastleProvider.PROVIDER_NAME) == null) {
		    // insert at specific position
		    Security.addProvider(new BouncyCastleProvider());
		}
		new AuctionServerEntryPoint();
	}
	
	public AuctionServerEntryPoint() throws NoSuchAlgorithmException, IOException, UnrecoverableKeyException, KeyManagementException, KeyStoreException, CertificateException {
		AuctionServerTLSConfigurationReader tlsConfigurationReader = null;
		AuctionServerKeyStoreConfigurationReader storesConfigurationReader = null;
		try {
			tlsConfigurationReader = new AuctionServerTLSConfigurationReader(AUCTION_SERVER_TLS_CONFIGURATION_PATH);
			storesConfigurationReader = new AuctionServerKeyStoreConfigurationReader(AUCTION_SERVER_STORES_CONFIGURATION_PATH);
		} catch (FileNotFoundException e) {
			printErrorStringWithClassName("Configuration file not found!\n" + e.getMessage());
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
		serverSocketFactory = sslContext.getServerSocketFactory();
		serverSocket = (SSLServerSocket)serverSocketFactory.createServerSocket(8443);
		serverSocket.setEnabledCipherSuites(tlsConfigurationReader.getAvailableTLSCiphersuites());
		serverSocket.setEnabledProtocols(tlsConfigurationReader.getAvailableTLSVersions());
		String[] mutualAuth = tlsConfigurationReader.getAvailableTLSAuthenticationModes();
		// TODO Change this maybe!
		if(mutualAuth[0].equals(TLS_CONF_CLIENT_ONLY)) {
			printErrorStringWithClassName("Not supported! -> " + TLS_CONF_CLIENT_ONLY);
			System.exit(1);
		}
		else if(mutualAuth[0].equals(TLS_CONF_MUTUAL))
			serverSocket.setNeedClientAuth(true);
		else serverSocket.setNeedClientAuth(false);
		
		connectedClientsMap = new ConcurrentHashMap<>();
		
		while(true) {
			try {
				responseSocket = (SSLSocket) serverSocket.accept();
				responseSocket.startHandshake();
				InputStream tempInputStream = responseSocket.getInputStream();
				BufferedReader br = new BufferedReader(new InputStreamReader(tempInputStream));
				String userName = br.readLine();
				userName = userName.substring(1, userName.length() - 1);
				connectedClientsMap.put(userName, new BlockingArrayQueue<Object>());
				System.out.println("User logged in as: " + userName);
				for (Entry<String, BlockingQueue<Object>> string : connectedClientsMap.entrySet()) {
					printErrorStringWithClassName(string);
				}
				Thread t = new AuctionServer(serverSocket, responseSocket, connectedClientsMap, mutualAuth[0], userName);
				t.start();
			} catch (Exception e) {
				printErrorStringWithClassName(e.getMessage());
			}
		}
	}
	
	private void printErrorStringWithClassName(Object message) {
		System.err.println("[" + this.getClass().getCanonicalName() + "] " + 
				"Message: " + message);
	}
	
}
