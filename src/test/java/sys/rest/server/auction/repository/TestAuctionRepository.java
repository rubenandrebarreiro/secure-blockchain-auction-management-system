package test.java.sys.rest.server.auction.repository;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.equalTo;

import java.io.File;
import java.io.FilenameFilter;
import java.nio.file.Files;

import javax.ws.rs.core.Response.Status;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import com.google.gson.Gson;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import main.java.resources.auction.Auction;
import main.java.sys.rest.server.auction.repository.AuctionRepositoryServer;
import test.java.sys.rest.server.auction.repository.data.AuctionRepositoryTestData;

public class TestAuctionRepository {
	
	private Gson gson;
	
	@BeforeAll
	public static void initAuctionRepositoryServer() throws InterruptedException {
    	File file = new File("res/database");
    	File[] files = file.listFiles();
    	for (File tempFile : files) {
			tempFile.renameTo(new File(tempFile.getAbsolutePath() + ".bak"));
		}
    	
    	AuctionRepositoryServer.main(null);
	}
	
    @BeforeEach
    public void init() {
        gson = new Gson();
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8080;
    }
 
    @AfterAll
    public static void cleanup() {
    	File file = new File("res/database");
    	File[] files = file.listFiles( new FilenameFilter() {
			
			@Override
			public boolean accept(File dir, String name) {
				// TODO Auto-generated method stub
				return name.endsWith(".db");
			}
		});
    	for (File tempFile : files) {
			tempFile.delete();
		}
    	
    	file = new File("res/database");
    	files = file.listFiles( new FilenameFilter() {
			
			@Override
			public boolean accept(File dir, String name) {
				// TODO Auto-generated method stub
				System.err.println("Found: " + name);
				return name.endsWith(".bak");
			}
		});
    	for (File tempFile : files) {
			tempFile.renameTo(
					new File(tempFile.getAbsolutePath().substring(0, tempFile.getAbsolutePath().length() - 4)));
		}
    }
    
    @Test
    public void testConnection() throws InterruptedException {
        get("/products-auctions/all")
        .then()
        .statusCode(Status.NO_CONTENT.getStatusCode());
    }
    
    @Test
    public void testSimpleAuctionAdd() throws InterruptedException {
    	given()
    	.contentType(ContentType.JSON)
    	.body(AuctionRepositoryTestData.auction0002)
    	.when()
    	.post("/products-auctions/open-normal-auction")
        .then()  	
        .statusCode(Status.ACCEPTED.getStatusCode());   	
    }
    
    @Test
    public void testGetAuctionsFromPreviousAdd() throws InterruptedException {
        get("/products-auctions/all")
        .then()
        .statusCode(Status.OK.getStatusCode())
        .contentType(ContentType.JSON);
    }
    
    @Test
    public void testGetAuctionAddedFromPreviousAdd() throws InterruptedException {
    	Auction testAuction = buildAuction(AuctionRepositoryTestData.auction0002);
        get("/products-auctions/all/auction0002")
        .then()
        .statusCode(Status.OK.getStatusCode())
        .contentType(ContentType.JSON)
        .body("auctionID", equalTo(testAuction.getAuctionID()))
        .body("auctionSerialNumber", equalTo(testAuction.getAuctionSerial()))
        .body("auctionDescription", equalTo(testAuction.getAuctionDescription()))
//        .body("auctionBidType", equalTo(testAuction.getAuctionBidType()))
//        .body("currentBidValue", equalTo(testAuction.getCurrentBidValue()))
//        .body("minAmountBidValue", equalTo(testAuction.getMinAmountBidValue()))
//        .body("maxAmountBidValue", equalTo(testAuction.getMaxAmountBidValue()))
        .body("numMaxAuctionBidsAllowed", equalTo(testAuction.getNumMaxAuctionBidsAllowed()))
        .body("auctionTimestampStart", equalTo(testAuction.getAuctionTimestampStart()))
        .body("auctionTimestampLimit", equalTo(testAuction.getAuctionTimestampLimit()))
        .body("auctionIsOpen", equalTo(testAuction.verifyIfAuctionIsOpen()))
        .body("productID", equalTo(testAuction.getProductID()))
        .body("productName", equalTo(testAuction.getProductName()))
        .body("productOwnerUserClientID", equalTo(testAuction.getProductOwnerUserClientID()));
    }
    
    private Auction buildAuction(String json) {
    	return gson.fromJson(json, Auction.class);
    }
}
