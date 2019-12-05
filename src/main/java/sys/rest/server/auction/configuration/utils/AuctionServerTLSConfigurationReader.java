package main.java.sys.rest.server.auction.configuration.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AuctionServerTLSConfigurationReader {

	private static final String SSL_CONTEXT_INSTANCE_START = "<SSL_CONTEXT_INSTANCE>";

	private static final String SSL_CONTEXT_INSTANCE_END = "</SSL_CONTEXT_INSTANCE>";
	
	private static final String TLS_VERSION_SEPARATOR_START = "<TLS_ENABLED_VERSION>";

	private static final String TLS_VERSION_SEPARATOR_END = "</TLS_ENABLED_VERSION>";

	private static final String ENABLED_CIPHERSUITES_START = "<TLS_ENABLED_CIPHERSUITES>";

	private static final String ENABLED_CIPHERSUITES_END = "</TLS_ENABLED_CIPHERSUITES>";

	private static final String AUTH_MODE_START = "<TLS_ENABLED_AUTH_MODE>";

	private static final String AUTH_MODE_END = "</TLS_ENABLED_AUTH_MODE>";


	private String sslContextInstance;
	
	private String[] availableTLSVersions;

	private String[] availableTLSCiphersuites;

	private String[] availableTLSAuthenticationModes;

	
	
	public AuctionServerTLSConfigurationReader(String tlsConfigurationsFilePath) 
		   throws FileNotFoundException {

		this.readTLSConfigurations(tlsConfigurationsFilePath);

		System.out.println();
		System.out.println();

		System.out.println("Configured/Setted the following TLS Configurations for the Auction Server...");

		System.out.println();


		System.out.println("Available TLS Versions:");

		for(int i = 0; i < this.availableTLSVersions.length; i++) {
			System.out.println(String.format("- %s", this.availableTLSVersions[i]));
		}


		System.out.println();

		System.out.println("Available TLS CipherSuites:");

		for(int i = 0; i < this.availableTLSCiphersuites.length; i++) {
			System.out.println(String.format("- %s", this.availableTLSCiphersuites[i]));
		}


		System.out.println();

		System.out.println("Available TLS Authentication Modes:");

		for(int i = 0; i < this.availableTLSAuthenticationModes.length; i++) {
			System.out.println(String.format("- %s", this.availableTLSAuthenticationModes[i]));
		}
	}


	private void readTLSConfigurations(String tlsConfigurationsPath) throws FileNotFoundException {

		List<String> availableTLSVersionsList = new ArrayList<String>();

		List<String> availableTLSCiphersuitesList = new ArrayList<String>();

		List<String> availableTLSAuthenticationModesList = new ArrayList<String>();


		File auctionServerTLSConfigurationFile = new File(tlsConfigurationsPath);

		String auctionServerTLSConfigurationFileAbsolutePath = auctionServerTLSConfigurationFile.getAbsolutePath();

		System.out.println
		(String.format("File for Auction's Server TLS Configurations/Setup read from the path:\n"
				+ "- %s", auctionServerTLSConfigurationFileAbsolutePath));

		System.out.println();
		System.out.println();

		Scanner auctionServerTLSConfigurationFileReader = new Scanner(auctionServerTLSConfigurationFile);
		
		while( auctionServerTLSConfigurationFileReader.hasNextLine() ) {

			String nextLine = auctionServerTLSConfigurationFileReader.nextLine();

			if(nextLine.equalsIgnoreCase(SSL_CONTEXT_INSTANCE_START)) {

				System.out.println
				(String.format("Reading the Available Configurations for "
						+ "the SSL Context Instance for the Auction Server..."));

				System.out.println();
				
				nextLine = auctionServerTLSConfigurationFileReader.nextLine();

				while(auctionServerTLSConfigurationFileReader.hasNextLine() && 
						!nextLine.equalsIgnoreCase(SSL_CONTEXT_INSTANCE_END)) {

					nextLine = nextLine.trim();

					System.out.println(String.format("- Reading a new SSL Context Instance: %s!!!", nextLine));
					
					this.sslContextInstance = nextLine;

					nextLine = auctionServerTLSConfigurationFileReader.nextLine();

				}
			
				System.out.println();
				System.out.println();

			}
			
			if(nextLine.equalsIgnoreCase(TLS_VERSION_SEPARATOR_START)) {

				System.out.println
				(String.format("Reading the Available Configurations for "
						+ "the TLS Versions for the Auction Server..."));

				System.out.println();
				
				nextLine = auctionServerTLSConfigurationFileReader.nextLine();

				while(auctionServerTLSConfigurationFileReader.hasNextLine() && 
						!nextLine.equalsIgnoreCase(TLS_VERSION_SEPARATOR_END)) {

					nextLine = nextLine.trim();

					System.out.println(String.format("- Reading a new TLS Version: %s!!!", nextLine));


					availableTLSVersionsList.add(nextLine);

					nextLine = auctionServerTLSConfigurationFileReader.nextLine();

				}
			
				System.out.println();
				System.out.println();

			}
			
			
			if(nextLine.equalsIgnoreCase(ENABLED_CIPHERSUITES_START)) {

				System.out.println
				(String.format("Reading the Available Configurations for "
						+ "the TLS Enabled CipherSuites for the Auction Server..."));
				
				System.out.println();
				
				nextLine = auctionServerTLSConfigurationFileReader.nextLine();

				while(auctionServerTLSConfigurationFileReader.hasNextLine() && 
						!nextLine.equalsIgnoreCase(ENABLED_CIPHERSUITES_END)) {

					nextLine = nextLine.trim();

					System.out.println(String.format("- Reading a new TLS Enabled CipherSuite: %s!!!", nextLine));


					availableTLSCiphersuitesList.add(nextLine);

					nextLine = auctionServerTLSConfigurationFileReader.nextLine();

				}
			
				System.out.println();
				System.out.println();

			}
			
			
			if(nextLine.equalsIgnoreCase(AUTH_MODE_START)) {

				System.out.println
				(String.format("Reading the Available Configurations for "
						+ "the TLS Authentication Modes for the Auction Server..."));

				System.out.println();
				
				nextLine = auctionServerTLSConfigurationFileReader.nextLine();

				while(auctionServerTLSConfigurationFileReader.hasNextLine() && 
						!nextLine.equalsIgnoreCase(AUTH_MODE_END)) {

					nextLine = nextLine.trim();

					System.out.println(String.format("- Reading a new TLS Authentication Mode: %s!!!", nextLine));


					availableTLSAuthenticationModesList.add(nextLine);

					nextLine = auctionServerTLSConfigurationFileReader.nextLine();

				}
			}

		}


		this.availableTLSVersions = availableTLSVersionsList.stream().toArray(String[]::new);

		this.availableTLSCiphersuites = availableTLSCiphersuitesList.stream().toArray(String[]::new);

		this.availableTLSAuthenticationModes = availableTLSAuthenticationModesList.stream().toArray(String[]::new);


		auctionServerTLSConfigurationFileReader.close();

	}

	
	public String getSSLContextInstance() {
		return sslContextInstance;
	}

	public void setSSLContextInstance(String sslContextInstance) {
		this.sslContextInstance = sslContextInstance;
	}
	
	public String[] getAvailableTLSVersions() {
		return availableTLSVersions;
	}

	public void setAvailableTLSVersions(String[] availableTLSVersions) {
		this.availableTLSVersions = availableTLSVersions;
	}

	public String[] getAvailableTLSCiphersuites() {
		return availableTLSCiphersuites;
	}

	public void setAvailableTLSCiphersuites(String[] availableTLSCiphersuites) {
		this.availableTLSCiphersuites = availableTLSCiphersuites;
	}

	public String[] getAvailableTLSAuthenticationModes() {
		return availableTLSAuthenticationModes;
	}

	public void setAvailableTLSAuthenticationModes(String[] availableTLSAuthenticationModes) {
		this.availableTLSAuthenticationModes = availableTLSAuthenticationModes;
	}
	
}