package test.java.sys.rest.server.auction.repository;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.equalTo;

import javax.ws.rs.core.Response.Status;

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
	public static void initAuctionRepositoryServer() {
    	AuctionRepositoryServer.main(null);
	}
	
    @BeforeEach
    public void init() {
        gson = new Gson();
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8080;
    }
 
    @Test
    public void testConnection() {
        get("/products-auctions/all")
        .then()
        .statusCode(Status.NO_CONTENT.getStatusCode());
    }
    
    @Test
    public void testSimpleAuctionAdd() {
    	given()
    	.contentType("application/json")
    	.body(AuctionRepositoryTestData.auction0002)
    	.when()
    	.post("/products-auctions/open-normal-auction")
        .then()  	
        .statusCode(Status.ACCEPTED.getStatusCode());   	
    }
    
    private Auction buildAuction(String json) {
    	return gson.fromJson(json, Auction.class);
    }
}
