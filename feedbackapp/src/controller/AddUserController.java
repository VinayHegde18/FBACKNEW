package controller;

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

//	public AddUserController() {
//
//		try {
//			Connection con = DriverManager.getConnection("jdbc:mysql://localhost/java", "root", "");
//			Statement stmt = con.createStatement();
//
//			ResultSet rs = stmt.executeQuery("select username from users where del is null");
//			while (rs.next()) {
//
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}


	public static void onClickCreateUser(String uname,String pword,String cpword,String emailStr,String name) {
//		String uname = userName.getText();
//		String pword = password.getText();
//		String cpword = confirmPassword.getText();
//		String emailStr = emailId.getText();
//		String name = fullName.getText();
		

//		SecureRandom random = new SecureRandom();
//		byte[] salt = new byte[16];
//		random.nextBytes(salt);
//		KeySpec spec = new PBEKeySpec(pword.toCharArray(), salt, 65536, 128);
//		SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
//		byte[] hash = factory.generateSecret(spec).getEncoded();

		Connection con;
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost/java", "root", "");
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from users where username ='" + uname + "'");

			if (rs.next()) {
				Alert newAlert = new Alert(Alert.AlertType.INFORMATION);
				newAlert.setContentText("This username is already taken");
				newAlert.show();
			}

			else {
				int result = stmt.executeUpdate("insert into users(name,email,username,password,cpassword) values ('"
						+ name + "','" + emailStr + "','" + uname + "','" + pword + "','" + cpword + "')");

				if (result == 1) {
                     System.out.println("inserted successfully");
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

//	public static void onClickClear() {
//		
//		userName.setText("");
//		password.setText("");
//		confirmPassword.setText("");
//		emailId.setText("");
//		fullName.setText("");
//		dobdt.setText("");
//		userLevel.setText("Select Userlevel");
//		userCountry.setText("Select Country");
//	}

//	@FXML
//	void close(ActionEvent event) {
//		Alert alert = new Alert(AlertType.CONFIRMATION);
//		alert.setContentText("Are You sure?");
//		Optional<ButtonType> option = alert.showAndWait();
//
//		if (option.get().equals(ButtonType.OK)) {
//			try {
//				Node node = (Node) event.getSource();
//				Stage stage = (Stage) node.getScene().getWindow();
//				stage.close();
//				FXMLLoader newloader3 = new FXMLLoader(getClass().getResource("/view/admindashboard.fxml"));
//				Pane root3;
//				root3 = newloader3.load();
//				Stage adminstage = new Stage();
//				Scene scene = new Scene(root3);
//				adminstage.setScene(scene);
//				adminstage.show();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//	}

}
