package model;


public class ManageUsersModel {
	int slno;
	String fullName;
	String userName;
	String emailId;

	public ManageUsersModel(int slno, String fullName, String userName, String emailId) {
		this.slno = slno;
		this.fullName = fullName;
		this.userName = userName;
		this.emailId = emailId;
		
	}

	public int getSlno() {
		return slno;
	}

	public void setSlno(int slno) {
		this.slno = slno;
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
