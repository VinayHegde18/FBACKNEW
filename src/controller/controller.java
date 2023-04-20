package controller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class controller {
	@FXML
	private TextField text1;
	@FXML
	private TextField text2;
	@FXML
	private Button submit;
	@FXML
	private Label wlcometxt;
	@FXML
    private Hyperlink signuplink;

    @FXML
    private Button exit;

	static String uname ;
	String password;

	public void submit(ActionEvent event) throws SQLException, NoSuchAlgorithmException, InvalidKeySpecException {
		uname = text1.getText();
		password = text2.getText();

//		SecureRandom random = new SecureRandom();
//		byte[] salt = new byte[16];
////		random.nextBytes(salt);
//		KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
//		SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
//		byte[] hash = factory.generateSecret(spec).getEncoded();
//
		Connection con;
		con = DriverManager.getConnection("jdbc:mysql://localhost/java", "root", "");
		Statement stmt = con.createStatement();

		ResultSet rs = stmt
				.executeQuery("select * from users where del is null and username ='" + uname + "' and password='" + password + "'");
//      while(rs.next()) {
		if (rs.next() && uname.equalsIgnoreCase(rs.getString("username")) && password.equals(rs.getString("password"))) {
//			uname = rs.getString("username");
			int level = rs.getInt("level");
			if(level == 1) {
				Node node = (Node) event.getSource();
				Stage stage = (Stage) node.getScene().getWindow();
				stage.close();
				try {

					FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/admindashboard.fxml"));

					Pane root = loader.load();

//					controller2 controller2 = loader.getController();
//					controller2.setLabelText(uname);
					Scene scene = new Scene(root);
					scene.getStylesheets().add(getClass().getResource("/css/admin.css").toExternalForm());
					Stage loginStage = new Stage();
					loginStage.setResizable(false);
					loginStage.setScene(scene);

					loginStage.setScene(scene);

					loginStage.show();
				} catch (IOException e) {
					System.err.println(String.format("Error: %s", e.getMessage()));
				}
			}
			else {
				
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
				loginStage.setResizable(false);
				loginStage.setScene(scene);

				loginStage.show();
			} catch (IOException e) {
				System.err.println(String.format("Error: %s", e.getMessage()));
			}
			}
		} else {
			Alert alert = new Alert(AlertType.ERROR);
		      alert.setContentText("INCORRECT USERNAME OR PASSWORD");
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
			    	  scene.getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());
			    	  newstage.setScene(scene);
			    	  newstage.show();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		      }
		}
	}
//	}
	@FXML
    void onClickSignupcntrl(ActionEvent event) throws SQLException {
//           SignUpController SignUpController = new SignUpController();
//           SignUpController.onClickSignup(event);
		Node node = (Node) event.getSource();

		Stage stage = (Stage) node.getScene().getWindow();
		stage.close();
		try {

			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/signup.fxml"));

			Pane root = loader.load();
			Scene scene = new Scene(root);
			Stage loginStage = new Stage();
			loginStage.setScene(scene);

			loginStage.setScene(scene);

			loginStage.show();
		} catch (IOException e) {
			System.err.println(String.format("Error: %s", e.getMessage()));
		}
    }


	    @FXML
	    void exit(ActionEvent event) {
	    	Alert alert = new Alert(AlertType.CONFIRMATION);
		      alert.setContentText("Do You want to Quit?");
		      Optional<ButtonType> option = alert.showAndWait();

		      if(option.get().equals(ButtonType.OK)) {
		    	  System.exit(0);
		      }
	    }
}
