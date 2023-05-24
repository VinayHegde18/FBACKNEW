package controller;

import java.io.IOException;
import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.KeyEvent;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class LogoutController {
   public void LogoutController(ActionEvent event) {
	      Alert alert = new Alert(AlertType.CONFIRMATION);
	      alert.setContentText("Are You sure You want to Logout?");
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
   public void EscKeyPressed(KeyEvent event) {
	      Alert alert = new Alert(AlertType.CONFIRMATION);
	      alert.setContentText("Are You sure You want to Logout?");
	      Optional<ButtonType> option = alert.showAndWait();

	      if(option.get().equals(ButtonType.OK)) {
			  Node reqnode = (Node) event.getSource();
			   Stage stage = (Stage) reqnode.getScene().getWindow();
			   stage.close();

	    	  FXMLLoader newloader3;
			try {
				newloader3 = new FXMLLoader(getClass().getResource("/view/home.fxml"));
		    	  Pane root3;
						root3 = newloader3.load();
				    	  Stage newstage = new Stage();
				    	  Scene scene = new Scene(root3);
				    	  newstage.setScene(scene);
				    	  newstage.show();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	      }
   }
   }
