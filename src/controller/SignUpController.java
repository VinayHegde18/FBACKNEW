package controller;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class SignUpController {

	    @FXML
	    private TextField username;

	    @FXML
	    private PasswordField password;

	    @FXML
	    private PasswordField cpassword;

	    @FXML
	    private Button signup;

	    @FXML
	    private TextField email;

	    @FXML
	    private TextField fullname;
	    
	    @FXML
	    private Button home;

	    @FXML
	    void onClickSignup(ActionEvent event) throws SQLException, NoSuchAlgorithmException, InvalidKeySpecException {
	    	String uname = username.getText();
			String pword = password.getText();
			String cpword = cpassword.getText();
			String emailStr = email.getText();
			String name = fullname.getText();

//			SecureRandom random = new SecureRandom();
//			byte[] salt = new byte[16];
//			random.nextBytes(salt);
//			KeySpec spec = new PBEKeySpec(pword.toCharArray(), salt, 65536, 128);
//			SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
//			byte[] hash = factory.generateSecret(spec).getEncoded();

			Connection con;
			con = DriverManager.getConnection("jdbc:mysql://localhost/java", "root", "");
			Statement stmt = con.createStatement();
			
             ResultSet rs = stmt
					.executeQuery("select * from users where del is null and username ='" + uname + "'");
             
             if(rs.next()) {
            	    Alert newAlert = new Alert(Alert.AlertType.INFORMATION);
					newAlert.setContentText("This username is already taken");
					newAlert.show();
             }
             else {

			int result = stmt.executeUpdate("insert into users(name,email,username,password,cpassword) values ('" + name + "','" + emailStr + "','" + uname + "','" + pword + "','" + cpword + "')");

			if(result==1) {
				Node node = (Node) event.getSource();

				Stage stage = (Stage) node.getScene().getWindow();
				stage.close();
				try {

					FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/userDashboard.fxml"));

					Pane root = loader.load();

					controller2 controller2 = loader.getController();
					controller2.setLabelText(uname);
					Scene scene = new Scene(root);
					Stage loginStage = new Stage();
					loginStage.setScene(scene);

					loginStage.setScene(scene);

					loginStage.show();
				} catch (IOException e) {
					System.err.println(String.format("Error: %s", e.getMessage()));
				}
			}
	    }
	    }
	    @FXML
	    void homePage(ActionEvent event) {
	        Alert alert = new Alert(AlertType.CONFIRMATION);
		      alert.setContentText("Are You sure?");
		      Optional<ButtonType> option = alert.showAndWait();

		      if(option.get().equals(ButtonType.OK)) {
				  Node reqnode = (Node) event.getSource();
				   Stage stage = (Stage) reqnode.getScene().getWindow();
				   stage.close();

		    	  FXMLLoader newloader3 = new FXMLLoader(getClass().getResource("/view/home.fxml"));
		    	  Pane root3;
				try {
					root3 = newloader3.load();
			    	  Stage newstage = new Stage();
			    	  Scene scene = new Scene(root3);
			    	  newstage.setScene(scene);
			    	  newstage.show();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		      }
	    }

	}
