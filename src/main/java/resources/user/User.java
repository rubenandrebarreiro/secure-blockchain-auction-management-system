package main.java.resources.user;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import main.java.blockchain.structure.MerkleTree;

@DatabaseTable(tableName = "users")
public class User {
	
	@DatabaseField(id = true)
	private String userPeerID;
	@DatabaseField
	private String userPassword;
	@DatabaseField
	private String userFirstName;
	@DatabaseField
	private String userLastName;
	@DatabaseField
	private String userEmail;
	@DatabaseField
	private String userHomeAddress;
	@DatabaseField
	private String userBankAccountNIB;
	
	private boolean isSolvingChallenge;
	
	// TODO - confirmar
	private MerkleTree blockchainHashesMerkleTree;
	
	
	public User() {
		// Empty/Obsolete constructor
	}
	
	public User(String userPeerID, String userPassword,
			    String userFirstName, String userLastName,
			    String userEmail, String userHomeAddress,
			    String userBankAccountNIB) {
		
		this.userPeerID = userPeerID;
		this.userPassword = userPassword;
		
		this.userFirstName = userFirstName;
		this.userLastName = userLastName;
		
		this.userEmail = userEmail;
		
		this.userHomeAddress = userHomeAddress;
		this.userBankAccountNIB = userBankAccountNIB;
		
		this.isSolvingChallenge = false;
		
		this.blockchainHashesMerkleTree = new MerkleTree(); // TODO - ver como usar merkle tree
	}
	
	
	public String getUserPeerID() {
		return this.userPeerID;
	}
	
	public void setUserPeerID(String userPeerID) {
		this.userPeerID = userPeerID;
	}
	
	public String getUserPassword() {
		return this.userPassword;
	}
	
	public void setUserPassword(String userPasswordHash) {
		this.userPassword = userPasswordHash;
	}
	
	public String getUserFirstName() {
		return this.userFirstName;
	}
	
	public void setUserFirstName(String userFirstName) {
		this.userFirstName = userFirstName;
	}
	
	public String getUserLastName() {
		return this.userLastName;
	}
	
	public void setUserLastName(String userLastName) {
		this.userLastName = userLastName;
	}
	
	public String getUserEmail() {
		return this.userEmail;
	}
	
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	
	public String getUserHomeAddress() {
		return this.userHomeAddress;
	}
	
	public void setUserHomeAddress(String userHomeAddress) {
		this.userHomeAddress = userHomeAddress;
	}
	
	public String getUserBankAccountNIB() {
		return this.userBankAccountNIB;
	}
	
	public void setUserBankNIB(String userBankAccountNIB) {
		this.userBankAccountNIB = userBankAccountNIB;
	}
	
	public boolean getIsSolvingChallenge() {
		return this.isSolvingChallenge;
	}
	
	public void setIsSolvingChallenge(boolean isSolvingChallenge) {
		this.isSolvingChallenge = isSolvingChallenge;
	}
	
}