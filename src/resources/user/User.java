package resources.user;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

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
	
	public User() {
		// TODO Auto-generated constructor stub
	}
	
	public User(String userPeerID, String userPassword,
			    String userFirstName, String userLastName,
			    String userEmail) {
		
		this.userPeerID = userPeerID;
		this.userPassword = userPassword;
		
		this.userFirstName = userFirstName;
		this.userLastName = userLastName;
		
		this.userEmail = userEmail;
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
}
