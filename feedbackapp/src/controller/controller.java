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

import dbcon.DbCon;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
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

	static String uname;
	static String curUname;
	String password;

	private Connection con;

	public void submit(ActionEvent event) throws SQLException, NoSuchAlgorithmException, InvalidKeySpecException {

		uname = text1.getText();
		password = text2.getText();

//		SecureRandom random = new SecureRandom();
//		byte[] salt = new byte[16];
////		random.nextBytes(salt);
//		KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
//		SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
//		byte[] hash = factory.generateSecret(spec).getEncoded();
//		DbCon dbCon = new DbCon();
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost/java","root","");
		Statement stmt = con.createStatement();

		ResultSet rs = stmt.executeQuery(
				"select * from users where del is null and username ='" + uname + "' and password='" + password + "'");
//      while(rs.next()) {
		if (rs.next() && uname.equalsIgnoreCase(rs.getString("username"))
				&& password.equals(rs.getString("password"))) {
			curUname = rs.getString("username");
			int level = rs.getInt("level");
			if (level == 1) {

				try {
					Node node = (Node) event.getSource();
					Stage stage = (Stage) node.getScene().getWindow();
					stage.close();
					FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/admindashboard.fxml"));
					Pane root = loader.load();
					Scene scene = new Scene(root);
					scene.getStylesheets().add(getClass().getResource("/css/admin.css").toExternalForm());
					Stage loginStage = new Stage();
					loginStage.setResizable(false);
					loginStage.setScene(scene);

					loginStage.setScene(scene);

					loginStage.show();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {

				Node node = (Node) event.getSource();
				Stage stage = (Stage) node.getScene().getWindow();
				stage.close();

				try {

					FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/userDashboard.fxml"));

					Pane root = loader.load();

					UserDashboardController UserDashboardController = loader.getController();
					UserDashboardController.setLabelText(uname);

//					VBox newroot = new VBox();
//					newroot.setId("menuVbox");
//					int btnId=1;
//					try {
//						Statement resstmt = con.createStatement();
//						ResultSet menulist = resstmt.executeQuery(
//								"select * from userauthlevel where menuitem in (select menuname from menumaster) and authusername='" + uname + "'");
//						while (menulist.next()) {
//							String buttonString = menulist.getString("menuItem");
//							Button button = new Button(buttonString);
//							String conv = String.valueOf(btnId);
//							String btnid = "menuBtn"+conv;
//							button.setId(btnid);
//							button.setMinWidth(200);
//							button.setMaxWidth(200);
//							button.setMaxHeight(40);
//							button.setMinHeight(40);
//							button.setOnAction(new EventHandler<ActionEvent>() {
//								@Override
//								public void handle(ActionEvent event) {
//									onActionClass onActionClass = new onActionClass();
//									onActionClass.onAction(event, btnid);
//								}
//								
//							});
//							newroot.getChildren().addAll(button);
//							btnId++;
//						}
//					} catch (Exception e) {
//						e.printStackTrace();
//					}
//					root.getChildren().add(newroot);
					Scene scene = new Scene(root);
					scene.getStylesheets().add(getClass().getResource("/css/user.css").toExternalForm());
					Stage loginStage = new Stage();
					loginStage.setScene(scene);
					loginStage.setResizable(false);
					loginStage.setScene(scene);

					loginStage.show();
				} catch (IOException e) {
					e.printStackTrace();
					System.err.println(String.format("Error: %s", e.getMessage()));
				}
			}
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setContentText("INCORRECT USERNAME OR PASSWORD");
			Optional<ButtonType> option = alert.showAndWait();

			if (option.get().equals(ButtonType.OK)) {
				text1.setText("");
				text2.setText("");
				text1.setFocusTraversable(true);
			}
		}
	}

	@FXML
	void onkeypressEvent(KeyEvent event) {
		KeyPressEvent keyPressEvent = new KeyPressEvent();
		keyPressEvent.escKeyEvenet(event);
	}

	@FXML
	void onClickSignupcntrl(ActionEvent event) throws SQLException {
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

		if (option.get().equals(ButtonType.OK)) {
			System.exit(0);
		}
	}
}
