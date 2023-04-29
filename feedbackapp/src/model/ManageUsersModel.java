package model;


public class ManageUsersModel {
	int userId;
	String fullName;
	String userName;
	String emailId;

	public ManageUsersModel(int userId, String fullName, String userName, String emailId) {
		this.userId = userId;
		this.fullName = fullName;
		this.userName = userName;
		this.emailId = emailId;
		
	}

	public int getuserId() {
		return userId;
	}

	public void setuserId(int userId) {
		this.userId = userId;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
}
