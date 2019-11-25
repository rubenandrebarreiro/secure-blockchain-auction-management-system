package test.java.sys.rest.server.auction.repository;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.equalTo;

import java.io.File;
import java.io.FilenameFilter;
import javax.ws.rs.core.Response.Status;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.google.gson.Gson;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import main.java.resources.auction.Auction;
import main.java.sys.rest.server.auction.repository.AuctionRepositoryServer;
import test.java.sys.rest.server.auction.repository.data.AuctionRepositoryTestData;

@TestMethodOrder(OrderAnnotation.class)
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
				return name.endsWith(".bak");
			}
		});
    	for (File tempFile : files) {
			tempFile.renameTo(
					new File(tempFile.getAbsolutePath().substring(0, tempFile.getAbsolutePath().length() - 4)));
		}
    }
    
    @Test
    @Order(1)
    public void testConnection() throws InterruptedException {
        get("/products-auctions/all")
        .then()
        .statusCode(Status.NO_CONTENT.getStatusCode());
    }
    
    @Test
    @Order(2)
    public void testSimpleAuctionAdd() throws InterruptedException {
    	given()
    	.contentType(ContentType.JSON)
    	.body(AuctionRepositoryTestData.auction0001)
    	.when()
    	.post("/products-auctions/open-normal-auction")
        .then()  	
        .statusCode(Status.ACCEPTED.getStatusCode());   	
    }
    
    @Test
    @Order(3)
    public void testGetAuctionsFromPreviousAdd() throws InterruptedException {
        get("/products-auctions/all")
        .then()
        .statusCode(Status.OK.getStatusCode())
        .contentType(ContentType.JSON);
    }
    
    @Test
    @Order(4)
    public void testGetAuctionAddedFromPreviousAdd() throws InterruptedException {
    	Auction testAuction = buildAuction(AuctionRepositoryTestData.auction0001);
        get("/products-auctions/all/auction0001")
        .then()
        .statusCode(Status.OK.getStatusCode())
        .contentType(ContentType.JSON)
        .body("auctionID", equalTo(testAuction.getAuctionID()))
        .body("auctionSerialNumber", equalTo(testAuction.getAuctionSerial()))
        .body("auctionDescription", equalTo(testAuction.getAuctionDescription()))
        .body("auctionBidType", equalTo( (int) testAuction.getAuctionBidType()))
        .body("currentBidValue", equalTo( (float) testAuction.getCurrentBidValue()))
        .body("minAmountBidValue", equalTo( (float) testAuction.getMinAmountBidValue()))
        .body("maxAmountBidValue", equalTo( (float) testAuction.getMaxAmountBidValue()))
        .body("numMaxAuctionBidsAllowed", equalTo(testAuction.getNumMaxAuctionBidsAllowed()))
        .body("auctionTimestampStart", equalTo(testAuction.getAuctionTimestampStart()))
        .body("auctionTimestampLimit", equalTo(testAuction.getAuctionTimestampLimit()))
        .body("auctionIsOpen", equalTo(testAuction.verifyIfAuctionIsOpen()))
        .body("productID", equalTo(testAuction.getProductID()))
        .body("productName", equalTo(testAuction.getProductName()))
        .body("productOwnerUserClientID", equalTo(testAuction.getProductOwnerUserClientID()));
    }
    
    @Test
    @Order(5)
    public void testGetOpenAuctions() {
    	Auction testAuction = buildAuction(AuctionRepositoryTestData.auction0001);
        get("/products-auctions/opened")
        .then()
        .statusCode(Status.OK.getStatusCode())
        .contentType(ContentType.JSON)
        .body("auctionID[0]", equalTo(testAuction.getAuctionID()))
        .body("auctionSerialNumber[0]", equalTo(testAuction.getAuctionSerial()))
        .body("auctionDescription[0]", equalTo(testAuction.getAuctionDescription()))
        .body("auctionBidType[0]", equalTo( (int) testAuction.getAuctionBidType()))
        .body("currentBidValue[0]", equalTo( (float) testAuction.getCurrentBidValue()))
        .body("minAmountBidValue[0]", equalTo( (float) testAuction.getMinAmountBidValue()))
        .body("maxAmountBidValue[0]", equalTo( (float) testAuction.getMaxAmountBidValue()))
        .body("numMaxAuctionBidsAllowed[0]", equalTo(testAuction.getNumMaxAuctionBidsAllowed()))
        .body("auctionTimestampStart[0]", equalTo(testAuction.getAuctionTimestampStart()))
        .body("auctionTimestampLimit[0]", equalTo(testAuction.getAuctionTimestampLimit()))
        .body("auctionIsOpen[0]", equalTo(testAuction.verifyIfAuctionIsOpen()))
        .body("productID[0]", equalTo(testAuction.getProductID()))
        .body("productName[0]", equalTo(testAuction.getProductName()))
        .body("productOwnerUserClientID[0]", equalTo(testAuction.getProductOwnerUserClientID()));
    }
    
    @Test
    @Order(6)
    public void testGetClosedAuctions() {
        get("/products-auctions/closed")
        .then()
        .statusCode(Status.NO_CONTENT.getStatusCode());
    }
    
    @Test
    @Order(7)
    public void testClosingAuction() {
    	put("/products-auctions/close-auction/auction0001")
    	.then()
        .statusCode(Status.ACCEPTED.getStatusCode());
    }
    
    @Test
    @Order(8)
    public void testGetOpenAuctionsFromPreviousClose() {
        get("/products-auctions/opened")
        .then()
        .statusCode(Status.NO_CONTENT.getStatusCode());
    }
    
    @Test
    @Order(9)
    public void testGetClosedAuctionsFromPreviousClose() {
    	Auction testAuction = buildAuction(AuctionRepositoryTestData.auction0001);
        get("/products-auctions/closed")
        .then()
        .statusCode(Status.OK.getStatusCode())
        .contentType(ContentType.JSON)
        .body("auctionID[0]", equalTo(testAuction.getAuctionID()))
        .body("auctionSerialNumber[0]", equalTo(testAuction.getAuctionSerial()))
        .body("auctionDescription[0]", equalTo(testAuction.getAuctionDescription()))
        .body("auctionBidType[0]", equalTo( (int) testAuction.getAuctionBidType()))
        .body("currentBidValue[0]", equalTo( (float) testAuction.getCurrentBidValue()))
        .body("minAmountBidValue[0]", equalTo( (float) testAuction.getMinAmountBidValue()))
        .body("maxAmountBidValue[0]", equalTo( (float) testAuction.getMaxAmountBidValue()))
        .body("numMaxAuctionBidsAllowed[0]", equalTo(testAuction.getNumMaxAuctionBidsAllowed()))
        .body("auctionTimestampStart[0]", equalTo(testAuction.getAuctionTimestampStart()))
        .body("auctionTimestampLimit[0]", equalTo(testAuction.getAuctionTimestampLimit()))
        .body("auctionIsOpen[0]", equalTo(!testAuction.verifyIfAuctionIsOpen()))
        .body("productID[0]", equalTo(testAuction.getProductID()))
        .body("productName[0]", equalTo(testAuction.getProductName()))
        .body("productOwnerUserClientID[0]", equalTo(testAuction.getProductOwnerUserClientID()));
    }
    
    @Test
    @Order(10)
    public void testAddAnotherAuction() {
    	given()
    	.contentType(ContentType.JSON)
    	.body(AuctionRepositoryTestData.auction0002)
    	.when()
    	.post("/products-auctions/open-normal-auction")
        .then()  	
        .statusCode(Status.ACCEPTED.getStatusCode());

        get("/products-auctions/all")
        .then()
        .body("auctionID.size()", equalTo(2));
    }
    
    @Test
    @Order(11)
    public void testGetClosedAndOpenAuctionsSeperatlyAfterPreviousAdd() {
    	Auction testAuction1 = buildAuction(AuctionRepositoryTestData.auction0001);
        get("/products-auctions/closed")
        .then()
        .statusCode(Status.OK.getStatusCode())
        .contentType(ContentType.JSON)
        .body("auctionID.size()", equalTo(1))
        .body("auctionID[0]", equalTo(testAuction1.getAuctionID()))
        .body("auctionSerialNumber[0]", equalTo(testAuction1.getAuctionSerial()))
        .body("auctionDescription[0]", equalTo(testAuction1.getAuctionDescription()))
        .body("auctionBidType[0]", equalTo( (int) testAuction1.getAuctionBidType()))
        .body("currentBidValue[0]", equalTo( (float) testAuction1.getCurrentBidValue()))
        .body("minAmountBidValue[0]", equalTo( (float) testAuction1.getMinAmountBidValue()))
        .body("maxAmountBidValue[0]", equalTo( (float) testAuction1.getMaxAmountBidValue()))
        .body("numMaxAuctionBidsAllowed[0]", equalTo(testAuction1.getNumMaxAuctionBidsAllowed()))
        .body("auctionTimestampStart[0]", equalTo(testAuction1.getAuctionTimestampStart()))
        .body("auctionTimestampLimit[0]", equalTo(testAuction1.getAuctionTimestampLimit()))
        .body("auctionIsOpen[0]", equalTo(!testAuction1.verifyIfAuctionIsOpen()))
        .body("productID[0]", equalTo(testAuction1.getProductID()))
        .body("productName[0]", equalTo(testAuction1.getProductName()))
        .body("productOwnerUserClientID[0]", equalTo(testAuction1.getProductOwnerUserClientID()));
        
    	Auction testAuction2 = buildAuction(AuctionRepositoryTestData.auction0002);
        get("/products-auctions/opened")
        .then()
        .statusCode(Status.OK.getStatusCode())
        .contentType(ContentType.JSON)
        .body("auctionID.size()", equalTo(1))
        .body("auctionID[0]", equalTo(testAuction2.getAuctionID()))
        .body("auctionSerialNumber[0]", equalTo(testAuction2.getAuctionSerial()))
        .body("auctionDescription[0]", equalTo(testAuction2.getAuctionDescription()))
        .body("auctionBidType[0]", equalTo( (int) testAuction2.getAuctionBidType()))
        .body("currentBidValue[0]", equalTo( (float) testAuction2.getCurrentBidValue()))
        .body("minAmountBidValue[0]", equalTo( (float) testAuction2.getMinAmountBidValue()))
        .body("maxAmountBidValue[0]", equalTo( (float) testAuction2.getMaxAmountBidValue()))
        .body("numMaxAuctionBidsAllowed[0]", equalTo(testAuction2.getNumMaxAuctionBidsAllowed()))
        .body("auctionTimestampStart[0]", equalTo(testAuction2.getAuctionTimestampStart()))
        .body("auctionTimestampLimit[0]", equalTo(testAuction2.getAuctionTimestampLimit()))
        .body("auctionIsOpen[0]", equalTo(testAuction2.verifyIfAuctionIsOpen()))
        .body("productID[0]", equalTo(testAuction2.getProductID()))
        .body("productName[0]", equalTo(testAuction2.getProductName()))
        .body("productOwnerUserClientID[0]", equalTo(testAuction2.getProductOwnerUserClientID()));
    }
    
    @Test
    @Order(12)
    public void addAllRemainingAuctionsToDatabase() {
    	given()
    	.contentType(ContentType.JSON)
    	.body(AuctionRepositoryTestData.auction0003)
    	.when()
    	.post("/products-auctions/open-normal-auction")
        .then()  	
        .statusCode(Status.ACCEPTED.getStatusCode());
    	given()
    	.contentType(ContentType.JSON)
    	.body(AuctionRepositoryTestData.auction0005)
    	.when()
    	.post("/products-auctions/open-normal-auction")
        .then()  	
        .statusCode(Status.ACCEPTED.getStatusCode());
    	given()
    	.contentType(ContentType.JSON)
    	.body(AuctionRepositoryTestData.auction0006)
    	.when()
    	.post("/products-auctions/open-normal-auction")
        .then()  	
        .statusCode(Status.ACCEPTED.getStatusCode());
    	
    	//Not a normal Bid
    	given()
    	.contentType(ContentType.JSON)
    	.body(AuctionRepositoryTestData.auction0004)
    	.when()
    	.post("/products-auctions/open-auction-min-initial-bid-value")
        .then()  	
        .statusCode(Status.ACCEPTED.getStatusCode());
    	
        get("/products-auctions/all")
        .then()
        .statusCode(Status.OK.getStatusCode())
        .contentType(ContentType.JSON)
        .body("auctionID.size()", equalTo(6));
    }
    
    private Auction buildAuction(String json) {
    	return gson.fromJson(json, Auction.class);
    }
}
