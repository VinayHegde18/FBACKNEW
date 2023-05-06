package controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/*
 * 
 * A PROJECT BY VINAY HEGDE
 * 
 */

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/view/home.fxml"));

	        Scene scene = new Scene(root);
	        scene.getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());
	        primaryStage.setScene(scene);
	        primaryStage.setResizable(false);
	        primaryStage.show();
	    
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) { 
		Application.launch(args);
	}
}
