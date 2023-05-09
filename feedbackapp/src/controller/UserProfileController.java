package controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import dbcon.DbCon;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class UserProfileController {

	@FXML
	private static TextField fullNameProfile;

	@FXML
	private static TextField userPasswordProfile;

	@FXML
	private static TextField userNameProfile;

	@FXML
	private static TextField emailIdProfile;

	@FXML
	private static TextField userStateProfile;
	
	public static String loggedinUname = controller.curUname;

	public static Statement stmt;
	
	public static void GetUserDetails() {
		
	DbCon dbCon = new DbCon();

	try {
		stmt = dbCon.con.createStatement();

		ResultSet profileRs2 = stmt
				.executeQuery("select * from users where del is null and username='" + loggedinUname + "'");

		while (profileRs2.next()) {

			fullNameProfile.setText(profileRs2.getString("name"));

			userPasswordProfile.setText(profileRs2.getString("password"));

			userNameProfile.setText(profileRs2.getString("username"));

			emailIdProfile.setText(profileRs2.getString("email"));

			userStateProfile.setText(profileRs2.getString("userstate"));
		}

	} catch (SQLException e) {

		e.printStackTrace();
	}
	}
	
}
