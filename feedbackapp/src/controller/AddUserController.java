package controller;

import java.awt.Event;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.AddCountryModel;
import model.AllRequirementsModel;

public class AddUserController {

	@FXML
	private AnchorPane createuserContainer;

	@FXML
	private AnchorPane rightContainer;

	@FXML
	private static TextField fullName;

	@FXML
	private static TextField userName;

	@FXML
	private static TextField emailId;

	@FXML
	private static TextField dobdt;

	@FXML
	private static MenuButton userLevel;

	@FXML
	private static MenuButton userCountry;

	@FXML
	private static TextField password;

	@FXML
	private static TextField confirmPassword;

	@FXML
	private Button createButton;

	@FXML
	private Button clearButton;

	@FXML
	private AnchorPane leftContainer;

	@FXML
	private Button closeButton;

	public static void onClickCreateUser(String uname,String pword,String cpword,String emailStr,String name,int uLevel, String uState) {

		Connection con;
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost/java", "root", "");
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from users where del is null and username ='" + uname + "'");

			if (rs.next()) {
				Alert newAlert = new Alert(Alert.AlertType.INFORMATION);
				newAlert.setContentText("This username is already taken");
				newAlert.show();
			}

			else {
				int result = stmt.executeUpdate("insert into users(name,email,username,password,cpassword,level,userstate) values ('"
						+ name + "','" + emailStr + "','" + uname + "','" + pword + "','" + cpword + "','" + uLevel + "','" + uState + "')");

				if (result == 1) {
					Alert newAlert = new Alert(Alert.AlertType.INFORMATION);
					newAlert.setContentText("User created Successfully");
					newAlert.show();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
//	public void clearUserFields() {
//		userName.setText("");
//
//		password.setText("");
//
//		confirmPassword.setText("");
//
//		emailId.setText("");
//
//		fullName.setText("");
//
//		dobdt.setText("");
//
//	}

}
