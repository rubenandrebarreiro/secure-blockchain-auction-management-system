package main.java.sys.rest.server.auction.configuration.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class AuctionServerKeyStoreConfigurationReader {

	private static final String KEY_STORE_FILE_LOCATION_PATH_START = "<KEY_STORE_FILE_LOCATION_PATH>";

	private static final String KEY_STORE_FILE_LOCATION_PATH_END = "</KEY_STORE_FILE_LOCATION_PATH>";

	private static final String KEY_STORE_PASSWORD_START = "<KEY_STORE_PASSWORD>";

	private static final String KEY_STORE_PASSWORD_END = "</KEY_STORE_PASSWORD>";

	private static final String KEY_STORE_INSTANCE_START = "<KEY_STORE_INSTANCE>";

	private static final String KEY_STORE_INSTANCE_END = "</KEY_STORE_INSTANCE>";

	private static final String KEY_MANAGER_FACTORY_START = "<KEY_MANAGER_FACTORY>";

	private static final String KEY_MANAGER_FACTORY_END = "</KEY_MANAGER_FACTORY>";
	
	private static final String TRUST_STORE_FILE_LOCATION_PATH_START = "<TRUST_STORE_FILE_LOCATION_PATH>";
                                
	private static final String TRUST_STORE_FILE_LOCATION_PATH_END = "</TRUST_STORE_FILE_LOCATION_PATH>";
                                
	private static final String TRUST_STORE_PASSWORD_START = "<TRUST_STORE_PASSWORD>";
                                
	private static final String TRUST_STORE_PASSWORD_END = "</TRUST_STORE_PASSWORD>";
	
	
		
	private String keyStoreFileLocationPath;

	private String keyStorePassword;
	
	private String keyStoreInstance;

	private String keyManagerFactoryInstance;
	
	private String trustStoreFileLocationPath;
	
	private String trustStorePassword;
	
	
	public AuctionServerKeyStoreConfigurationReader(String keyStoreConfigurationsFilePath) throws FileNotFoundException {

		System.out.println("---------------------" + this.getClass().getCanonicalName() + " start---------------------");		
		
		this.readKeyStoreConfigurations(keyStoreConfigurationsFilePath);

		System.out.println();
		System.out.println();

		System.out.println("Configured/Setted the following Key Store Configurations for the Auction Server...");

		System.out.println();


		System.out.println("Available KeyStore in the following Files' Location/Path:");
		System.out.println(this.keyStoreFileLocationPath);

		System.out.println();
		
		System.out.println("The instance for the Key Store will be:");
		System.out.println(this.keyStoreInstance);

		System.out.println();
		
		System.out.println("The instance for the Key Manager Factory will be:");
		System.out.println(this.keyManagerFactoryInstance);

		System.out.println();

	}


	private void readKeyStoreConfigurations(String keyStoreConfigurationsFilePath) throws FileNotFoundException {
		
		File auctionServerKeyStoreConfigurationFile = new File(keyStoreConfigurationsFilePath);

		String auctionServerKeyStoreConfigurationFileAbsolutePath = auctionServerKeyStoreConfigurationFile.getAbsolutePath();

		System.out.println
		(String.format("File for Auction's Server KeyStore Configurations/Setup read from the path:\n"
				+ "- %s", auctionServerKeyStoreConfigurationFileAbsolutePath));

		System.out.println();
		System.out.println();

		Scanner auctionServerKeyStoreConfigurationFileReader = new Scanner(auctionServerKeyStoreConfigurationFile);

		while( auctionServerKeyStoreConfigurationFileReader.hasNextLine() ) {

			String nextLine = auctionServerKeyStoreConfigurationFileReader.nextLine();

			if(nextLine.equalsIgnoreCase(KEY_STORE_FILE_LOCATION_PATH_START)) {

				System.out.println
				(String.format("Reading the Available Configuration for "
						+ "the KeyStore File Location/Path for the Auction Server..."));

				System.out.println();
				
				nextLine = auctionServerKeyStoreConfigurationFileReader.nextLine();

				while(auctionServerKeyStoreConfigurationFileReader.hasNextLine() && 
						!nextLine.equalsIgnoreCase(KEY_STORE_FILE_LOCATION_PATH_END)) {

					nextLine = nextLine.trim();

					System.out.println(String.format("- Reading the KeyStore File Location/Path: %s!!!", nextLine));


					this.keyStoreFileLocationPath = nextLine;

					nextLine = auctionServerKeyStoreConfigurationFileReader.nextLine();

				}
			
				System.out.println();
				System.out.println();

			}
			
			
			if(nextLine.equalsIgnoreCase(KEY_STORE_PASSWORD_START)) {

				System.out.println
			   (String.format("Reading the Available Configurations for "
							+ "the Access Password to the KeyStore for the Auction Server..."));
				
				System.out.println();
				
				nextLine = auctionServerKeyStoreConfigurationFileReader.nextLine();

				while(auctionServerKeyStoreConfigurationFileReader.hasNextLine() && 
						!nextLine.equalsIgnoreCase(KEY_STORE_PASSWORD_END)) {

					nextLine = nextLine.trim();

					this.keyStorePassword = nextLine;

					nextLine = auctionServerKeyStoreConfigurationFileReader.nextLine();

				}
			
				System.out.println();
				System.out.println();

			}
			
			
			if(nextLine.equalsIgnoreCase(KEY_STORE_INSTANCE_START)) {

				System.out.println
			   (String.format("Reading the Available Configurations for "
						    + "the KeyStore Instance for the Auction Server..."));

				System.out.println();
				
				nextLine = auctionServerKeyStoreConfigurationFileReader.nextLine();

				while(auctionServerKeyStoreConfigurationFileReader.hasNextLine() && 
						!nextLine.equalsIgnoreCase(KEY_STORE_INSTANCE_END)) {

					nextLine = nextLine.trim();

					System.out.println(String.format("- Reading a new KeyStore Instance: %s!!!", nextLine));
					
					this.keyStoreInstance = nextLine;
					
					nextLine = auctionServerKeyStoreConfigurationFileReader.nextLine();

				}
			}
			
			
			if(nextLine.equalsIgnoreCase(KEY_MANAGER_FACTORY_START)) {

				System.out.println
			   (String.format("Reading the Available Configurations for "
						    + "the KeyManager Factory Instance for the Auction Server..."));

				System.out.println();
				
				nextLine = auctionServerKeyStoreConfigurationFileReader.nextLine();

				while(auctionServerKeyStoreConfigurationFileReader.hasNextLine() && 
						!nextLine.equalsIgnoreCase(KEY_MANAGER_FACTORY_END)) {

					nextLine = nextLine.trim();

					System.out.println(String.format("- Reading a new KeyManager Factory Instance: %s!!!", nextLine));
					
					this.keyStoreInstance = nextLine;
					
					nextLine = auctionServerKeyStoreConfigurationFileReader.nextLine();

				}
			}
			
			if(nextLine.equalsIgnoreCase(TRUST_STORE_FILE_LOCATION_PATH_START)) {
				
				System.out.println
				(String.format("Reading the Available Configuration for "
						+ "the TrustStore File Location/Path..."));

				System.out.println();
				
				nextLine = auctionServerKeyStoreConfigurationFileReader.nextLine();

				while(auctionServerKeyStoreConfigurationFileReader.hasNextLine() && 
						!nextLine.equalsIgnoreCase(TRUST_STORE_FILE_LOCATION_PATH_END)) {

					nextLine = nextLine.trim();

					System.out.println(String.format("- Reading the TrustStore File Location/Path: %s!!!", nextLine));

					this.trustStoreFileLocationPath = nextLine;

					nextLine = auctionServerKeyStoreConfigurationFileReader.nextLine();

				}
			
				System.out.println();
				System.out.println();

			}
			
			if(nextLine.equalsIgnoreCase(TRUST_STORE_PASSWORD_START)) {

				System.out.println
			   (String.format("Reading the Available Configurations for "
							+ "the Access Password to the TrustStore..."));
				
				System.out.println();
				
				nextLine = auctionServerKeyStoreConfigurationFileReader.nextLine();

				while(auctionServerKeyStoreConfigurationFileReader.hasNextLine() && 
						!nextLine.equalsIgnoreCase(TRUST_STORE_PASSWORD_END)) {

					nextLine = nextLine.trim();

					this.trustStorePassword = nextLine;

					nextLine = auctionServerKeyStoreConfigurationFileReader.nextLine();

				}
			
				System.out.println();
				System.out.println();

			}

		}

		auctionServerKeyStoreConfigurationFileReader.close();

		System.out.println("---------------------" + this.getClass().getCanonicalName() + " finish---------------------");
		
		System.out.println();
		
	}


	public String getKeyStoreFileLocationPath() {
		return keyStoreFileLocationPath;
	}


	public void setKeyStoreFileLocationPath(String keyStoreFileLocationPath) {
		this.keyStoreFileLocationPath = keyStoreFileLocationPath;
	}


	public String getKeyStorePassword() {
		return keyStorePassword;
	}


	public void setKeyStorePassword(String keyStorePassword) {
		this.keyStorePassword = keyStorePassword;
	}


	public String getKeyStoreInstance() {
		return keyStoreInstance;
	}


	public void setKeyStoreInstance(String keyStoreInstance) {
		this.keyStoreInstance = keyStoreInstance;
	}


	public String getKeyManagerFactoryInstance() {
		return keyManagerFactoryInstance;
	}


	public void setKeyManagerFactoryInstance(String keyManagerFactoryInstance) {
		this.keyManagerFactoryInstance = keyManagerFactoryInstance;
	}
	
	public String getTrustStoreFileLocationPath() {
		return trustStoreFileLocationPath;
	}


	public void setTrustStoreFileLocationPath(String trustStoreFileLocationPath) {
		this.trustStoreFileLocationPath = trustStoreFileLocationPath;
	}


	public String getTrustStorePassword() {
		return trustStorePassword;
	}


	public void setTrustStorePassword(String trustStorePassword) {
		this.trustStorePassword = trustStorePassword;
	}

}