package main.java.sys.rest.server.auction.repository;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.ServerSocket;
import java.net.URI;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.Response.Status;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpsConfigurator;
import com.sun.net.httpserver.HttpsParameters;
import com.sun.net.httpserver.HttpsServer;

import org.glassfish.jersey.jdkhttp.JdkHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import com.google.gson.Gson;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import main.java.api.rest.server.auction.repository.AuctionRepositoryAPI;
import main.java.resources.auction.Auction;
import main.java.resources.auction.utils.AuctionBidTypes;
import main.java.resources.bid.Bid;
import main.java.resources.user.User;
import main.java.sys.rest.server.auction.configuration.utils.AuctionServerKeyStoreConfigurationReader;
import main.java.sys.rest.server.auction.configuration.utils.AuctionServerTLSConfigurationReader;

public class AuctionRepositoryServer implements AuctionRepositoryAPI {
	
	private Gson gsonObject;

	private Dao<Auction, String> allProductsAuctionsRepositoryDao;
	
	private Dao<Auction, String> openedProductsAuctionsRepositoryDao;

	private Dao<Auction, String> closedProductsAuctionsRepositoryDao;

	private Dao<Bid, Long> allBidsRepositoryDao;
	
	private Dao<Bid, Long> openedBidsRepositoryDao;
	
	private Dao<Bid, Long> closedBidsRepositoryDao; //TODO - ver como fechar a bid
	
	private Dao<User, String> allUsersRepositoryDao;
	
	
	
	public AuctionRepositoryServer()
		   throws FileNotFoundException {
		
		this.gsonObject = new Gson();
		
		System.out.println("Created a JDBC Connection!!!");
		
		this.createAuctionRepositoriesDao();
		
	}

	public static void main(String[] args) throws NoSuchAlgorithmException, FileNotFoundException {
		
		if(args.length != 1) {

			System.err.println
			(String.format
					("Usage: java AuctionRepositoryServer <url>")
			);
			System.exit(1);
		}
		
		int port = Integer.parseInt(args[0]);
		
		URI baseUri = UriBuilder.fromUri("http://0.0.0.0/").port(port).build();
		
		ResourceConfig config = new ResourceConfig();
		
		config.register( new AuctionRepositoryServer() );

		JdkHttpServerFactory.createHttpServer(baseUri, config);

		System.out.println("AuctionRepository ready @ " + baseUri);
	}
		
	private void createAuctionRepositoriesDao() {
		
		new File("res/database").mkdirs();

		String databaseAllUsersRepositoryURL = "jdbc:sqlite:res/database/all-users.db";

		String databaseAllProductsAuctionsRepositoryURL = "jdbc:sqlite:res/database/all-auctions.db";

		String databaseOpenedProductsAuctionsRepositoryURL = "jdbc:sqlite:res/database/opened-auctions.db";

		String databaseClosedProductsAuctionsRepositoryURL = "jdbc:sqlite:res/database/closed-auctions.db";

		String databaseAllBidsRepositoryURL = "jdbc:sqlite:res/database/all-bids.db";
		
		String databaseOpenedBidsRepositoryURL = "jdbc:sqlite:res/database/opened-bids.db";

		String databaseClosedBidsRepositoryURL = "jdbc:sqlite:res/database/closed-bids.db";


		try {

			ConnectionSource connectionAllUsersSource = 
					new JdbcConnectionSource(databaseAllUsersRepositoryURL);

			ConnectionSource connectionAllProductsAuctionsSource = 
					new JdbcConnectionSource(databaseAllProductsAuctionsRepositoryURL);

			ConnectionSource connectionOpenedProductsAuctionsSource = 
					new JdbcConnectionSource(databaseOpenedProductsAuctionsRepositoryURL);

			ConnectionSource connectionClosedProductsAuctionsSource = 
					new JdbcConnectionSource(databaseClosedProductsAuctionsRepositoryURL);

			ConnectionSource connectionAllBidsSource = 
					new JdbcConnectionSource(databaseAllBidsRepositoryURL);

			ConnectionSource connectionOpenedBidsSource = 
					new JdbcConnectionSource(databaseOpenedBidsRepositoryURL);

			ConnectionSource connectionClosedBidsSource = 
					new JdbcConnectionSource(databaseClosedBidsRepositoryURL);


			this.allUsersRepositoryDao = 
					DaoManager.createDao(connectionAllUsersSource, User.class);

			this.allProductsAuctionsRepositoryDao = 
					DaoManager.createDao(connectionAllProductsAuctionsSource, Auction.class);

			this.openedProductsAuctionsRepositoryDao = 
					DaoManager.createDao(connectionOpenedProductsAuctionsSource, Auction.class);

			this.closedProductsAuctionsRepositoryDao = 
					DaoManager.createDao(connectionClosedProductsAuctionsSource, Auction.class);

			this.allBidsRepositoryDao = 
					DaoManager.createDao(connectionAllBidsSource, Bid.class);
			
			this.openedBidsRepositoryDao = 
					DaoManager.createDao(connectionOpenedBidsSource, Bid.class);

			this.closedBidsRepositoryDao = 
					DaoManager.createDao(connectionClosedBidsSource, Bid.class);
			
			
			TableUtils.createTableIfNotExists(connectionAllUsersSource, User.class);

			TableUtils.createTableIfNotExists(connectionAllProductsAuctionsSource, Auction.class);

			TableUtils.createTableIfNotExists(connectionOpenedProductsAuctionsSource, Auction.class);

			TableUtils.createTableIfNotExists(connectionClosedProductsAuctionsSource, Auction.class);

			TableUtils.createTableIfNotExists(connectionAllBidsSource, Bid.class);

			TableUtils.createTableIfNotExists(connectionOpenedBidsSource, Bid.class);
			
			TableUtils.createTableIfNotExists(connectionClosedBidsSource, Bid.class);
			

		}
		catch(SQLException sqlException) {
			sqlException.printStackTrace();
		}
	}


	private boolean verifyExistenceOfAlreadyOpenedAuction(String newAuctionID) throws SQLException {

		if(this.allProductsAuctionsRepositoryDao.idExists(newAuctionID)) {

			System.err.println(String.format
					("Already exists a Product Auction "
							+ "with the ID [%s]!!!",
							newAuctionID));

			return true;

		}

		if(this.openedProductsAuctionsRepositoryDao.idExists(newAuctionID)) {

			System.err.println(String.format
					("Already exists an Opened Product Auction "
							+ "with the ID [%s]!!!",
							newAuctionID));

			return true;

		}

		if(this.closedProductsAuctionsRepositoryDao.idExists(newAuctionID)) {

			System.err.println(String.format
					("Already exists a Closed Product Auction "
							+ "with the ID [%s]!!!",
							newAuctionID));

			return true;
		}

		return false;

	}

	private boolean verifyExistenceOfAlreadyClosedAuction(String newAuctionID) throws SQLException {

		if(!this.allProductsAuctionsRepositoryDao.idExists(newAuctionID)) {

			System.err.println(String.format
					("Don't exist a Product Auction "
							+ "with the ID [%s]!!!",
							newAuctionID));

			return true;

		}

		if(!this.openedProductsAuctionsRepositoryDao.idExists(newAuctionID)) {

			System.err.println(String.format
					("Don't exist an Opened Product Auction "
							+ "with the ID [%s]!!!",
							newAuctionID));

			return true;

		}

		if(this.closedProductsAuctionsRepositoryDao.idExists(newAuctionID)) {

			System.err.println(String.format
					("Already exists a Closed Product Auction "
							+ "with the ID [%s]!!!",
							newAuctionID));

			return true;
		}

		return false;
	}

	private boolean verifyMinAmountBidValueForAuction(double auctionMinAmountBidValue) {

		if(auctionMinAmountBidValue <= 0.0) {
			System.err.println(String.format
					("The minimum value for the Amount of a Bid allowed to "
							+ "a Product's Auction must be strictly positive, "
							+ "the value of [%d] it's not valid!!!", 
							auctionMinAmountBidValue));

			return true;
		}

		return false;
	}

	private boolean verifyMaxAmountBidValueForAuction(double auctionMaxAmountBidValue) {

		if(auctionMaxAmountBidValue <= 0.0) {
			System.err.println(String.format
					("The maximum value for the Amount of a Bid allowed to "
							+ "a Product's Auction must be strictly positive, "
							+ "the value of [%d] it's not valid!!!", 
							auctionMaxAmountBidValue));

			return true;
		}

		return false;
	}

	private boolean verifyNotExistenceOfClientUserForLimitedSet(String limitedUserClientBidderID) throws SQLException {

		if(!this.allUsersRepositoryDao.idExists(limitedUserClientBidderID)) {

			System.err.println(String.format
					("Don't exists any User/Client "
							+ "registered in the system "
							+ "with the ID [%s] to be "
							+ "added to a limited set of User/Client Bidders "
							+ "for this Product's Auction!!!", limitedUserClientBidderID));


			return true;
		}

		return true;
	}

	private void addOpenedAuction(Auction newAuction) throws SQLException {

		this.allProductsAuctionsRepositoryDao.create(newAuction);
		System.out.println("New Auction added to all Product Auctions!!!");
	
		this.openedProductsAuctionsRepositoryDao.create(newAuction);
		System.out.println("New Auction added to all Opened Product Auctions!!!");
		
	}

	private boolean verifyExistenceOfAnyProductsAuctions(List<Auction> allProductsAuctions) {

		if(allProductsAuctions.isEmpty()) {

			System.err.println("Don't exist any Products' Auctions in the system, at this moment!!!");

			return false;

		}

		return true;
	}

	private boolean verifyExistenceOfAnyOpenedProductsAuctions(List<Auction> openedProductsAuctions) {

		if(openedProductsAuctions.isEmpty()) {

			System.err.println("Don't exist any Opened Products' Auctions occurring in the system, at this moment!!!");

			return false;

		}

		return true;

	}

	private boolean verifyExistenceOfAnyClosedProductsAuctions(List<Auction> closedProductsAuctions) {

		if(closedProductsAuctions.isEmpty()) {

			System.err.println("Don't exist any Closed Products' Auctions in the system, at this moment!!!");

			return false;

		}

		return true;
	}

	private boolean verifyExistenceOfProductAuction(String auctionID, Auction productAuction) {

		if(productAuction == null) {

			System.err.println(String.format("Don't exist any Product's Auction with the ID [%s]!!!", auctionID));

			return false;

		}

		return true;
	}

	private boolean verifyExistenceOfOpenedProductAuction(String openedAuctionID, Auction openedProductAuction) {

		if(openedProductAuction == null) {

			System.err.println(String.format("Don't exist any Opened Product's Auction with the ID [%s]!!!", openedAuctionID));

			return false;

		}

		return true;

	}

	private boolean verifyExistenceOfClosedProductAuction(String closedAuctionID, Auction closedProductAuction) {

		if(closedProductAuction == null) {

			System.err.println(String.format("Don't exist any Closed Product's Auction with the ID [%s]!!!", closedAuctionID));

			return false;

		}

		return true;
	}

	private boolean verifyExistenceOfBidsInProductAuction(String auctionID, Map<Long, Bid> bidsFromAuction) {

		if(bidsFromAuction.isEmpty()) {

			System.err.println(String.format("Don't exist any Bid made for the Product's Auction with the ID: [%s]!!!", auctionID));

			return false;

		}

		return true;
	}

	private boolean verifyExistenceOfBidsInOpenedProductAuction(String openedAuctionID, Map<Long, Bid> bidsFromOpenedAuction) {

		if(bidsFromOpenedAuction.isEmpty()) {

			System.err.println(String.format("Don't exist any Bid made for the Opened Product's Auction with the ID: [%s]!!!", openedAuctionID));

			return false;

		}

		return true;

	}

	private boolean verifyExistenceOfBidsInClosedProductAuction(String closedAuctionID, Map<Long, Bid> bidsFromClosedAuction) {

		if(bidsFromClosedAuction.isEmpty()) {

			System.err.println(String.format("Don't exist any Bid made for the Closed Product's Auction with the ID: [%s]!!!", closedAuctionID));

			return false;

		}

		return true;
	}

	@Override
	public Response openNormalAuction(String normalAuctionJSONString) throws SQLException {

		// TODO Could be a potentially less expensive operation 
		// by checking if ID exists before creating auction from json 
		System.out.println("Preparing to create a Normal Auction from JSON Object...");

		Auction newAuction = this.gsonObject.fromJson(normalAuctionJSONString, Auction.class);

		String newAuctionID = newAuction.getAuctionID();


		byte auctionBidType = newAuction.getAuctionBidType();

		if(auctionBidType != AuctionBidTypes.NORMAL_BIDS.getBidsType()) {
			return Response.status(Status.BAD_REQUEST).build();
		}

		if(this.verifyExistenceOfAlreadyOpenedAuction(newAuctionID)) {
			return Response.status(Status.CONFLICT).build();
		}


		this.addOpenedAuction(newAuction);


		return Response.status(Status.ACCEPTED).build();

	}

	@Override
	public Response openAuctionWithMinimumInitialBidValue(String auctionWithMinInitialBidValueJSONString) throws SQLException {

		System.out.println("Preparing to create an Auction with Minimum Initial Bid Value from JSON Object...");

		Auction newAuction = this.gsonObject.fromJson(auctionWithMinInitialBidValueJSONString, Auction.class);

		String newAuctionID = newAuction.getAuctionID();


		byte auctionBidType = newAuction.getAuctionBidType();

		if(auctionBidType != AuctionBidTypes.MIN_INITIAL_VALUE_BID.getBidsType()) {
			return Response.status(Status.BAD_REQUEST).build();
		}

		double auctionMinInitialBidValue = newAuction.getCurrentBidValue();


		if(auctionMinInitialBidValue <= 0.0) {

			System.err.println(String.format
					("The minimum value for the Initial Bid allowed to "
							+ "a Product's Auction must be strictly positive, "
							+ "the value of [%d] it's not valid!!!", 
							auctionMinInitialBidValue));


			return Response.status(Status.BAD_REQUEST).build();

		}


		if(this.verifyExistenceOfAlreadyOpenedAuction(newAuctionID)) {
			return Response.status(Status.CONFLICT).build();
		}


		this.addOpenedAuction(newAuction);


		return Response.status(Status.ACCEPTED).build();
		
	}

	@Override
	public Response openAuctionWithMinimumAmountBidValue(String auctionWithMinAmountBidValueJSONString) throws SQLException {

		System.out.println("Preparing to create an Auction with Minimum Amount Bid Value from JSON Object...");

		Auction newAuction = this.gsonObject.fromJson(auctionWithMinAmountBidValueJSONString, Auction.class);

		String newAuctionID = newAuction.getAuctionID();


		byte auctionBidType = newAuction.getAuctionBidType();

		if(auctionBidType != AuctionBidTypes.MIN_AMOUNT_VALUE_BID.getBidsType()) {
			return Response.status(Status.BAD_REQUEST).build();
		}


		double auctionMinAmountBidValue = newAuction.getMinAmountBidValue();

		if(this.verifyMinAmountBidValueForAuction(auctionMinAmountBidValue)) {
			return Response.status(Status.BAD_REQUEST).build();
		}


		if(this.verifyExistenceOfAlreadyOpenedAuction(newAuctionID)) {
			return Response.status(Status.CONFLICT).build();
		}
	

		this.addOpenedAuction(newAuction);


		return Response.status(Status.ACCEPTED).build();

	}

	@Override
	public Response openAuctionWithMaximumAmountBidValue(String auctionWithMaxAmountBidValueJSONString) throws SQLException {

		System.out.println("Preparing to create an Auction with Maximum Amount Bid Value from JSON Object...");

		Auction newAuction = this.gsonObject.fromJson(auctionWithMaxAmountBidValueJSONString, Auction.class);

		String newAuctionID = newAuction.getAuctionID();


		byte auctionBidType = newAuction.getAuctionBidType();

		if(auctionBidType != AuctionBidTypes.MAX_AMOUNT_VALUE_BID.getBidsType()) {
			return Response.status(Status.BAD_REQUEST).build();
		}


		double auctionMaxAmountBidValue = newAuction.getMaxAmountBidValue();

		if(this.verifyMaxAmountBidValueForAuction(auctionMaxAmountBidValue)) {
			return Response.status(Status.BAD_REQUEST).build();
		}


		if(this.verifyExistenceOfAlreadyOpenedAuction(newAuctionID)) {
			return Response.status(Status.CONFLICT).build();
		}


		this.addOpenedAuction(newAuction);


		return Response.status(Status.ACCEPTED).build();
		
	}

	@Override
	public Response openAuctionWithMinimumAndMaximumAmountBidValue
		  (String auctionWithMinAndMaxAmountBidValueJSONString) throws SQLException {

		System.out.println("Preparing to create an Auction with Minimum and Maximum Amount Bid Value from JSON Object...");

		Auction newAuction = this.gsonObject.fromJson(auctionWithMinAndMaxAmountBidValueJSONString, Auction.class);

		String newAuctionID = newAuction.getAuctionID();


		byte auctionBidType = newAuction.getAuctionBidType();

		if(auctionBidType != AuctionBidTypes.MIN_MAX_AMOUNT_VALUE_BID.getBidsType()) {
			return Response.status(Status.BAD_REQUEST).build();
		}


		double auctionMinAmountBidValue = newAuction.getMinAmountBidValue();

		double auctionMaxAmountBidValue = newAuction.getMaxAmountBidValue();

		if(this.verifyMinAmountBidValueForAuction(auctionMinAmountBidValue)) {
			return Response.status(Status.BAD_REQUEST).build();
		}

		if(this.verifyMaxAmountBidValueForAuction(auctionMaxAmountBidValue)) {
			return Response.status(Status.BAD_REQUEST).build();
		}

		if(auctionMaxAmountBidValue <= auctionMinAmountBidValue) {
			System.err.println(String.format
					("The maximum value for the Amount of a Bid allowed to "
							+ "a Product's Auction must be strictly greater than "
							+ "the minimum value for the Amount of a Bid allowed to the same!!!"));

			System.err.println(String.format
					("The value of [%d] for the minimum value for the Amount of a Bid "
							+ "and the value of [%d] for the maximum value for the Amount of a Bid "
							+ "aren't valid!!!", 
							auctionMinAmountBidValue, auctionMaxAmountBidValue));

			return Response.status(Status.BAD_REQUEST).build();
		}


		if(this.verifyExistenceOfAlreadyOpenedAuction(newAuctionID)) {
			return Response.status(Status.CONFLICT).build();
		}


		this.addOpenedAuction(newAuction);


		return Response.status(Status.ACCEPTED).build();
	
	}

	@Override
	public Response openAuctionWithLimitedSetUserClientBidders
	      (String auctionWithLimitedSetClientBiddersJSONString) throws SQLException {

		System.out.println("Preparing to create an Auction with Limited Set of User/Client Bidders from JSON Object...");

		Auction newAuction = this.gsonObject.fromJson(auctionWithLimitedSetClientBiddersJSONString, Auction.class);

		String newAuctionID = newAuction.getAuctionID();


		byte auctionBidType = newAuction.getAuctionBidType();

		if(auctionBidType != AuctionBidTypes.LIMITED_SET_CLIENT_BIDDERS.getBidsType()) {
			return Response.status(Status.BAD_REQUEST).build();
		}


		for(String limitedUserClientBidderID : newAuction.getNumAuctionBidsForEachUserClient().keySet()) {

			if(this.verifyNotExistenceOfClientUserForLimitedSet(limitedUserClientBidderID)) {

				return Response.status(Status.BAD_REQUEST).build();

			}
		}

		
		if(this.verifyExistenceOfAlreadyOpenedAuction(newAuctionID)) {
			return Response.status(Status.CONFLICT).build();
		}


		this.addOpenedAuction(newAuction);


		return Response.status(Status.ACCEPTED).build();


	}

	@Override
	public Response openAuctionWithLimitedNumberBidsForEachUserClientBidder
		  (String auctionWithLimitedNumberBidsForEachUserClientBidderJSONString) throws SQLException {

		System.out.println("Preparing to create an Auction with Limited Number of Bids for each User/Client Bidder from JSON Object...");

		Auction newAuction = 
				this.gsonObject
				.fromJson(auctionWithLimitedNumberBidsForEachUserClientBidderJSONString, Auction.class);

		String newAuctionID = newAuction.getAuctionID();


		byte auctionBidType = newAuction.getAuctionBidType();

		if(auctionBidType != AuctionBidTypes.LIMITED_NUMBER_BIDS_FOR_EACH_CLIENT.getBidsType()) {
			return Response.status(Status.BAD_REQUEST).build();
		}


		for(Entry<String, Integer> limitedNumberOfBidsForUserClient : 
			newAuction.getNumAuctionBidsForEachUserClient().entrySet()) {

			String limitedUserClientBidderID = limitedNumberOfBidsForUserClient.getKey();

			if(this.verifyNotExistenceOfClientUserForLimitedSet(limitedUserClientBidderID)) {

				return Response.status(Status.BAD_REQUEST).build();

			}


			int limitedNumberOfBidsForUserClientBidder = limitedNumberOfBidsForUserClient.getValue();

			if(limitedNumberOfBidsForUserClientBidder <= 0) {
				System.err.println(String.format
						("The allowed Number of Bids for "
								+ "the User/Client Bidder with the ID [%s] "
								+ "for this Product's Auction must be strictly positive!!!",
								limitedNumberOfBidsForUserClientBidder));

				return Response.status(Status.BAD_REQUEST).build();

			}
		}


		if(this.verifyExistenceOfAlreadyOpenedAuction(newAuctionID)) {
			return Response.status(Status.CONFLICT).build();
		}


		this.addOpenedAuction(newAuction);


		return Response.status(Status.ACCEPTED).build();

	}

	@Override
	public Response openAuctionWithLimitedNumberOfBids(String auctionWithLimitedNumberBidsJSONString)
		   throws SQLException {

		System.out.println("Preparing to create an Auction with Limited Number of Bids from JSON Object...");

		Auction newAuction = this.gsonObject.fromJson(auctionWithLimitedNumberBidsJSONString, Auction.class);

		String newAuctionID = newAuction.getAuctionID();


		byte auctionBidType = newAuction.getAuctionBidType();

		if(auctionBidType != AuctionBidTypes.LIMITED_NUMBER_BIDS.getBidsType()) {
		
			return Response.status(Status.BAD_REQUEST).build();
		
		}


		int limitNumberOfBids = newAuction.getNumMaxAuctionBidsAllowed();

		// The limit Number of Bids allowed for this Product's Auction must be positive
		if(limitNumberOfBids <= 0) {

			System.err.println(String.format
					("The limit Number of Bids allowed for "
							+ "a Product's Auction must be strictly positive, "
							+ "the value of [%d] it's not valid!!!", limitNumberOfBids));

			return Response.status(Status.BAD_REQUEST).build();
		
		}


		if(this.verifyExistenceOfAlreadyOpenedAuction(newAuctionID)) {
			return Response.status(Status.CONFLICT).build();
		}


		this.addOpenedAuction(newAuction);


		return Response.status(Status.ACCEPTED).build();
		
	}

	
	@Override
	public Response closeAuction(String openedAuctionID) throws SQLException {

		System.out.println("Preparing to close an Auction...");		

		if(this.verifyExistenceOfAlreadyClosedAuction(openedAuctionID)) {
			return Response.status(Status.CONFLICT).build();
		}


		Auction auctionFromAllAuctions = this.allProductsAuctionsRepositoryDao.queryForId(openedAuctionID);
		
		Auction auctionFromOpenedAuctions = this.openedProductsAuctionsRepositoryDao.queryForId(openedAuctionID);

		if( !(auctionFromAllAuctions.getAuctionID().equals(auctionFromOpenedAuctions.getAuctionID())) ) {

			System.err.println("Not a valid Opened Auction to be closed!!!");

			return Response.status(Status.BAD_REQUEST).build();

		}

		
		Auction auctionToBeClosed = auctionFromAllAuctions;
		
		if( !( auctionToBeClosed.verifyIfAuctionIsOpen() ) ) {

			System.err.println("The Auction should be opened, in order to be closed!!!");

			return Response.status(Status.BAD_REQUEST).build();

		}
		

		this.openedProductsAuctionsRepositoryDao.delete(auctionToBeClosed);
		System.out.println("Auction which will be closed deleted from all Opened Product Auctions!!!");


		auctionToBeClosed.closeAuction();

		this.allProductsAuctionsRepositoryDao.update(auctionToBeClosed);
		System.out.println("Auction setted/updated as closed to all Product Auctions!!!");

		this.closedProductsAuctionsRepositoryDao.create(auctionToBeClosed);
		System.out.println("Closed Auction added to all Closed Product Auctions!!!");


		return Response.status(Status.ACCEPTED).build();
		
	}

	@Override
	public Response addBidToOpenedProductAuction(String openedAuctionID, String bidForOpenedProductAuctionJSONString) 
		   throws SQLException {

		System.out.println("Preparing to add a Bid to a current Opened Product's Auction occurring, at this moment, from JSON Object...");

		Bid newBid = this.gsonObject.fromJson(bidForOpenedProductAuctionJSONString, Bid.class);
		
		
		Auction openedAuction;
		
		
		List<Auction> allProductsAuctions = this.allProductsAuctionsRepositoryDao.queryForAll();

		if( !this.verifyExistenceOfAnyProductsAuctions(allProductsAuctions) ) {

			return Response.status(Status.NO_CONTENT).build();

		}

		List<Auction> openedProductsAuctions = this.allProductsAuctionsRepositoryDao.queryForAll();

		if( !this.verifyExistenceOfAnyOpenedProductsAuctions(openedProductsAuctions) ) {

			return Response.status(Status.NO_CONTENT).build();

		}


		Auction auction = this.allProductsAuctionsRepositoryDao.queryForId(openedAuctionID);

		if( !this.verifyExistenceOfProductAuction(openedAuctionID, auction) ) {

			return Response.status(Status.NOT_FOUND).build(); 

		}


		openedAuction = this.openedProductsAuctionsRepositoryDao.queryForId(openedAuctionID);

		if( !this.verifyExistenceOfOpenedProductAuction(openedAuctionID, openedAuction) ) {

			return Response.status(Status.NOT_FOUND).build(); 

		}

		
		if( !auction.equals(openedAuction) ) {

			System.err.println("Not a valid Opened Auction to receive Bids!!!");
			
			return Response.status(Status.BAD_REQUEST).build(); 
			
		}
		
		
		if( !auction.verifyIfAuctionIsOpen() ) {
			
			System.err.println("The Auction must be opened, in order to receive Bids!!!");
			
			return Response.status(Status.BAD_REQUEST).build(); 
			
		}
		
		
		openedAuction.addAuctionBid(newBid);
		
		
		long newBidID = newBid.getBidID();
		
		if( this.allBidsRepositoryDao.idExists(newBidID) ) {
			
			System.err.println(String.format("Already exists a Bid with the ID: [%s]!!!", newBidID));
			
			return Response.status(Status.CONFLICT).build();
		
		}
		else {
			
			this.allBidsRepositoryDao.create(newBid);
			
			System.out.println("Bid added to All Bids in Auction!!!");
			
		}
		
		if( this.openedBidsRepositoryDao.idExists(newBidID) ) {
			
			System.err.println(String.format("Already exists an Opened Bid with the ID: [%s]!!!", newBidID));
			
			return Response.status(Status.CONFLICT).build();
		
		}
		else {
			
			this.allBidsRepositoryDao.create(newBid);
			
			System.out.println("Bid added to Opened Bids in an Auction!!!");
			
		}
		
		
		this.openedBidsRepositoryDao.create(newBid);
		
		
		this.allProductsAuctionsRepositoryDao.update(openedAuction);

		this.openedProductsAuctionsRepositoryDao.update(openedAuction);


		return Response.status(Status.ACCEPTED).build();
		
	}

	@Override
	public Response listAllProductsAuctions() throws SQLException {

		List<Auction> allProductsAuctions = this.allProductsAuctionsRepositoryDao.queryForAll();
		
		if(!this.verifyExistenceOfAnyProductsAuctions(allProductsAuctions)) {

			return Response.status(Status.NO_CONTENT).build();

		}

		return Response.ok(this.gsonObject.toJson(allProductsAuctions)).build();

	}

	@Override
	public Response listOpenedProductsAuctions() throws SQLException {

		List<Auction> openedProductsAuctions = this.openedProductsAuctionsRepositoryDao.queryForAll();

		if(!this.verifyExistenceOfAnyOpenedProductsAuctions(openedProductsAuctions)) {

			return Response.status(Status.NO_CONTENT).build();

		}

		return Response.ok(this.gsonObject.toJson(openedProductsAuctions)).build();

	}

	@Override
	public Response listClosedProductsAuctions() throws SQLException {

		List<Auction> closedProductsAuctions = this.closedProductsAuctionsRepositoryDao.queryForAll();

		if(!this.verifyExistenceOfAnyClosedProductsAuctions(closedProductsAuctions)) {

			return Response.status(Status.NO_CONTENT).build();

		}

		return Response.ok(this.gsonObject.toJson(closedProductsAuctions)).build();

	}

	@Override
	public Response listAllProductsAuctionsByProductOwnerUserClient(String productOwnerUserClientID) throws SQLException {

		List<Auction> allProductsAuctions = this.allProductsAuctionsRepositoryDao.queryForAll();

		if(!this.verifyExistenceOfAnyProductsAuctions(allProductsAuctions)) {

			return Response.status(Status.NO_CONTENT).build();

		}


		List<Auction> allProductsAuctionsByUser = allProductsAuctions.stream()
				.filter(auction -> auction.getProductOwnerUserClientID().equalsIgnoreCase(productOwnerUserClientID))
				.collect(Collectors.toList());

		if(allProductsAuctionsByUser.isEmpty()) {

			System.err.println(String.format("The User/Client with the ID: [%s] don't have any Product's Auction, at this moment!!!",
					productOwnerUserClientID));	

			return Response.status(Status.NO_CONTENT).build();

		}

		return Response.ok(this.gsonObject.toJson(allProductsAuctionsByUser)).build();

	}

	@Override
	public Response listOpenedProductsAuctionsByProductOwnerUserClient(String productOwnerUserClientID) throws SQLException {

		List<Auction> openedProductsAuctions = this.openedProductsAuctionsRepositoryDao.queryForAll();

		if(!this.verifyExistenceOfAnyOpenedProductsAuctions(openedProductsAuctions)) {

			return Response.status(Status.NO_CONTENT).build();

		}


		List<Auction> openedProductsAuctionsByUser = openedProductsAuctions.stream()
				.filter(auction -> auction.getProductOwnerUserClientID().equalsIgnoreCase(productOwnerUserClientID))
				.collect(Collectors.toList());

		if(openedProductsAuctionsByUser.isEmpty()) {

			System.err.println(String.format("The User/Client with the ID: [%s] don't have any Opened Product's Auction occurring, at this moment!!!",
					productOwnerUserClientID));	

			return Response.status(Status.NO_CONTENT).build();

		}

		return Response.ok(this.gsonObject.toJson(openedProductsAuctionsByUser)).build();

	}

	@Override
	public Response listClosedProductsAuctionsByProductOwnerUserClient(String productOwnerUserClientID) throws SQLException {

		List<Auction> closedProductsAuctions = this.closedProductsAuctionsRepositoryDao.queryForAll();

		if(!this.verifyExistenceOfAnyClosedProductsAuctions(closedProductsAuctions)) {

			return Response.status(Status.NO_CONTENT).build();

		}


		List<Auction> closedProductsAuctionsByUser = closedProductsAuctions.stream()
				.filter(auction -> auction.getProductOwnerUserClientID().equalsIgnoreCase(productOwnerUserClientID))
				.collect(Collectors.toList());

		if(closedProductsAuctionsByUser.isEmpty()) {

			System.err.println(String.format("The User/Client with the ID: [%s] don't have any Closed Product's Auction, at this moment!!!",
					productOwnerUserClientID));	

			return Response.status(Status.NO_CONTENT).build();

		}

		return Response.ok(this.gsonObject.toJson(closedProductsAuctionsByUser)).build();

	}

	@Override
	public Response findProductAuctionByID(String auctionID) throws SQLException {

		List<Auction> allProductsAuctions = this.allProductsAuctionsRepositoryDao.queryForAll();

		if( !this.verifyExistenceOfAnyProductsAuctions(allProductsAuctions) ) {

			return Response.status(Status.NO_CONTENT).build();

		}


		Auction auction = this.allProductsAuctionsRepositoryDao.queryForId(auctionID);

		if( !this.verifyExistenceOfProductAuction(auctionID, auction) ) {

			return Response.status(Status.NOT_FOUND).build(); 

		}


		return Response.ok(this.gsonObject.toJson(auction)).build();

	}

	@Override
	public Response findOpenedProductAuctionByID(String openedAuctionID) throws SQLException {

		List<Auction> openedProductsAuctions = this.openedProductsAuctionsRepositoryDao.queryForAll();

		if( !this.verifyExistenceOfAnyOpenedProductsAuctions(openedProductsAuctions) ) {

			return Response.status(Status.NO_CONTENT).build();

		}


		Auction openedAuction = this.openedProductsAuctionsRepositoryDao.queryForId(openedAuctionID);

		if( !this.verifyExistenceOfOpenedProductAuction(openedAuctionID, openedAuction) ) {

			return Response.status(Status.NOT_FOUND).build(); 

		}


		return Response.ok(this.gsonObject.toJson(openedAuction)).build();

	}

	@Override
	public Response findClosedProductAuctionByID(String closedAuctionID) throws SQLException {

		List<Auction> closedProductsAuctions = this.closedProductsAuctionsRepositoryDao.queryForAll();

		if( !this.verifyExistenceOfAnyClosedProductsAuctions(closedProductsAuctions) ) {

			return Response.status(Status.NO_CONTENT).build();

		}


		Auction closedAuction = this.closedProductsAuctionsRepositoryDao.queryForId(closedAuctionID);

		if( !this.verifyExistenceOfClosedProductAuction(closedAuctionID, closedAuction) ) {

			return Response.status(Status.NOT_FOUND).build(); 

		}


		return Response.ok(this.gsonObject.toJson(closedAuction)).build();

	}

	@Override
	public Response listAllBidsOfProductAuctionByID(String auctionID) throws SQLException {

		List<Auction> allProductsAuctions = this.allProductsAuctionsRepositoryDao.queryForAll();

		if( !this.verifyExistenceOfAnyProductsAuctions(allProductsAuctions) ) {

			return Response.status(Status.NO_CONTENT).build();

		}


		Auction auction = this.closedProductsAuctionsRepositoryDao.queryForId(auctionID);

		if( !this.verifyExistenceOfProductAuction(auctionID, auction) ) {

			return Response.status(Status.NOT_FOUND).build(); 

		}


		Map<Long, Bid> bidsFromAuction = auction.getAuctionBidsMade();

		if( !this.verifyExistenceOfBidsInProductAuction(auctionID, bidsFromAuction) ) {

			return Response.status(Status.NO_CONTENT).build();

		}

		return Response.ok(this.gsonObject.toJson(bidsFromAuction)).build();

	}

	@Override
	public Response listAllBidsOfOpenedProductAuctionByID(String openedAuctionID) throws SQLException {

		List<Auction> openedProductsAuctions = this.openedProductsAuctionsRepositoryDao.queryForAll();

		if( !this.verifyExistenceOfAnyOpenedProductsAuctions(openedProductsAuctions) ) {

			return Response.status(Status.NO_CONTENT).build();

		}


		Auction openedAuction = this.closedProductsAuctionsRepositoryDao.queryForId(openedAuctionID);

		if( !this.verifyExistenceOfClosedProductAuction(openedAuctionID, openedAuction) ) {

			return Response.status(Status.NOT_FOUND).build(); 

		}


		Map<Long, Bid> bidsFromOpenedAuction = openedAuction.getAuctionBidsMade();

		if( !this.verifyExistenceOfBidsInOpenedProductAuction(openedAuctionID, bidsFromOpenedAuction) ) {

			return Response.status(Status.NO_CONTENT).build();

		}

		return Response.ok(this.gsonObject.toJson(bidsFromOpenedAuction)).build();

	}

	@Override
	public Response listAllBidsOfClosedProductAuctionByID(String closedAuctionID) throws SQLException {

		List<Auction> closedProductsAuctions = this.closedProductsAuctionsRepositoryDao.queryForAll();

		if( !this.verifyExistenceOfAnyClosedProductsAuctions(closedProductsAuctions) ) {

			return Response.status(Status.NO_CONTENT).build();

		}


		Auction closedAuction = this.closedProductsAuctionsRepositoryDao.queryForId(closedAuctionID);

		if( !this.verifyExistenceOfClosedProductAuction(closedAuctionID, closedAuction) ) {

			return Response.status(Status.NOT_FOUND).build(); 

		}


		Map<Long, Bid> bidsFromClosedAuction = closedAuction.getAuctionBidsMade();

		if( !this.verifyExistenceOfBidsInClosedProductAuction(closedAuctionID, bidsFromClosedAuction) ) {

			return Response.status(Status.NO_CONTENT).build();

		}

		return Response.ok(this.gsonObject.toJson(bidsFromClosedAuction)).build();

	}

	@Override
	public Response listAllBidsMadeByBidderUserClientInProductAuctionByID(String auctionID, String bidderUserClientID) throws SQLException {

		List<Auction> allProductsAuctions = this.allProductsAuctionsRepositoryDao.queryForAll();

		if( !this.verifyExistenceOfAnyProductsAuctions(allProductsAuctions) ) {

			return Response.status(Status.NO_CONTENT).build();

		}


		Auction auction = this.openedProductsAuctionsRepositoryDao.queryForId(auctionID);

		if( !this.verifyExistenceOfProductAuction(auctionID, auction) ) {

			return Response.status(Status.NOT_FOUND).build(); 

		}


		Map<Long, Bid> bidsFromAuction = auction.getAuctionBidsMade();

		if( !this.verifyExistenceOfBidsInProductAuction(auctionID, bidsFromAuction) ) {

			return Response.status(Status.NO_CONTENT).build();

		}


		List<Bid> bidsFromAuctionMadeByBidderUserClientList = 
				bidsFromAuction.entrySet().stream()
				.filter(bidEntry -> bidEntry.getValue().getBidderUserClientID().equalsIgnoreCase(bidderUserClientID))
				.map(bidEntry -> bidEntry.getValue())
				.collect(Collectors.toList());

		if(bidsFromAuctionMadeByBidderUserClientList.isEmpty()) {

			System.err.println(String.format("Don't exist any Bid made by the User/Client with the ID: [%s] in "
					+ "the Product's Auction with the ID: [%s]!!!", bidderUserClientID, auctionID));

			return Response.status(Status.NOT_FOUND).build();

		}


		Map<Long, Bid> bidsFromAuctionMadeByBidderUserClient = new HashMap<Long, Bid>();

		for(Bid bid : bidsFromAuctionMadeByBidderUserClientList) {
			bidsFromAuctionMadeByBidderUserClient.put(bid.getBidID(), bid);
		}


		return Response.ok(this.gsonObject.toJson(bidsFromAuctionMadeByBidderUserClient)).build();

	}

	@Override
	public Response listAllBidsMadeByBidderUserClientInOpenedProductAuctionByID(String openedAuctionID, String bidderUserClientID) throws SQLException {

		List<Auction> openedProductsAuctions = this.openedProductsAuctionsRepositoryDao.queryForAll();

		if( !this.verifyExistenceOfAnyOpenedProductsAuctions(openedProductsAuctions) ) {

			return Response.status(Status.NO_CONTENT).build();

		}


		Auction openedAuction = this.openedProductsAuctionsRepositoryDao.queryForId(openedAuctionID);

		if( !this.verifyExistenceOfOpenedProductAuction(openedAuctionID, openedAuction) ) {

			return Response.status(Status.NOT_FOUND).build(); 

		}


		Map<Long, Bid> bidsFromOpenedAuction = openedAuction.getAuctionBidsMade();

		if( !this.verifyExistenceOfBidsInOpenedProductAuction(openedAuctionID, bidsFromOpenedAuction) ) {

			return Response.status(Status.NO_CONTENT).build();

		}


		List<Bid> bidsFromOpenedAuctionMadeByBidderUserClientList = 
				bidsFromOpenedAuction.entrySet().stream()
				.filter(bidEntry -> bidEntry.getValue().getBidderUserClientID().equalsIgnoreCase(bidderUserClientID))
				.map(bidEntry -> bidEntry.getValue())
				.collect(Collectors.toList());

		if(bidsFromOpenedAuctionMadeByBidderUserClientList.isEmpty()) {

			System.err.println(String.format("Don't exist any Bid made by the User/Client with the ID: [%s] in "
					+ "the Opened Product's Auction with the ID: [%s]!!!", bidderUserClientID, openedAuctionID));

			return Response.status(Status.NOT_FOUND).build();

		}


		Map<Long ,Bid> bidsFromOpenedAuctionMadeByBidderUserClient = new HashMap<Long, Bid>();

		for(Bid bid : bidsFromOpenedAuctionMadeByBidderUserClientList) {
			bidsFromOpenedAuctionMadeByBidderUserClient.put(bid.getBidID(), bid);
		}


		return Response.ok(this.gsonObject.toJson(bidsFromOpenedAuctionMadeByBidderUserClient)).build();

	}

	@Override
	public Response listAllBidsMadeByBidderUserClientInClosedProductAuctionByID(String closedAuctionID, String bidderUserClientID) throws SQLException {

		List<Auction> closedProductsAuctions = this.closedProductsAuctionsRepositoryDao.queryForAll();

		if( !this.verifyExistenceOfAnyClosedProductsAuctions(closedProductsAuctions) ) {

			return Response.status(Status.NO_CONTENT).build();

		}


		Auction closedAuction = this.closedProductsAuctionsRepositoryDao.queryForId(closedAuctionID);

		if( !this.verifyExistenceOfClosedProductAuction(closedAuctionID, closedAuction) ) {

			return Response.status(Status.NOT_FOUND).build(); 

		}


		Map<Long, Bid> bidsFromClosedAuction = closedAuction.getAuctionBidsMade();

		if( !this.verifyExistenceOfBidsInClosedProductAuction(closedAuctionID, bidsFromClosedAuction) ) {

			return Response.status(Status.NO_CONTENT).build();

		}


		List<Bid> bidsFromClosedAuctionMadeByBidderUserClientList = 
				bidsFromClosedAuction.entrySet().stream()
				.filter(bidEntry -> bidEntry.getValue().getBidderUserClientID().equalsIgnoreCase(bidderUserClientID))
				.map(bidEntry -> bidEntry.getValue())
				.collect(Collectors.toList());

		if(bidsFromClosedAuctionMadeByBidderUserClientList.isEmpty()) {

			System.err.println(String.format("Don't exist any Bid made by the User/Client with the ID: [%s] in "
					+ "the Closed Product's Auction with the ID: [%s]!!!", bidderUserClientID, closedAuctionID));

			return Response.status(Status.NOT_FOUND).build();

		}


		Map<Long ,Bid> bidsFromClosedAuctionMadeByBidderUserClient = new HashMap<Long, Bid>();

		for(Bid bid : bidsFromClosedAuctionMadeByBidderUserClientList) {
			bidsFromClosedAuctionMadeByBidderUserClient.put(bid.getBidID(), bid);
		}


		return Response.ok(this.gsonObject.toJson(bidsFromClosedAuctionMadeByBidderUserClient)).build();

	}
}