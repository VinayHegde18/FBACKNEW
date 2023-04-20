package controller;

import java.io.IOException;
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
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class controller2 {
	@FXML
	Label wlcometxt;

	public void setLabelText(String text) {
		wlcometxt.setText("Welcome " + text);
	}

	@FXML
	Button postButton;
   @FXML
	public void onClickPost(ActionEvent event) {
//			  Node node = (Node) event.getSource();
////
//			   Stage stage = (Stage) node.getScene().getWindow();
//			   stage.close();
		try {

			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/postrequirement.fxml"));

			Pane root = loader.load();

			PostRequirementsController PostRequirementsController = loader.getController();
			Scene scene = new Scene(root);
			Stage stage = new Stage();
			stage.setScene(scene);

			stage.setScene(scene);

			stage.show();
		} catch (IOException e) {
			System.err.println(String.format("Error: %s", e.getMessage()));
		}
	}

	@FXML
	Button allReqButton;
	@FXML
	public void onClickAllRequirements(ActionEvent event) {
//				  Node reqnode = (Node) event.getSource();
//				   Stage stage = (Stage) reqnode.getScene().getWindow();
//				   stage.close();
		try {

			FXMLLoader newloader = new FXMLLoader(getClass().getResource("/view/allrequirement.fxml"));

			Pane root2 = newloader.load();

			AllRequirementsController AllRequirementsController = new AllRequirementsController();
			Scene scene = new Scene(root2);
			Stage newstage = new Stage();
			newstage.setScene(scene);

			newstage.show();
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println(String.format("Error: %s", e.getMessage()));
		}
	}

	@FXML
	Button yourReqButton;
   @FXML
	public void onClickYourRequirements(ActionEvent event) {
		try {

			FXMLLoader newloader2 = new FXMLLoader(getClass().getResource("/view/yourrequirements.fxml"));

			Pane root3 = newloader2.load();

			YourrequirementsController YourrequirementsController = new YourrequirementsController();
			Scene scene = new Scene(root3);
			Stage newstage1 = new Stage();
			newstage1.setScene(scene);

			newstage1.show();
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println(String.format("Error: %s", e.getMessage()));
		}
	}

	@FXML
	Button logoutButton;
    @FXML
	public void onClickLogout(ActionEvent event) {
    	LogoutController LogoutController= new LogoutController(event);
	}
}
