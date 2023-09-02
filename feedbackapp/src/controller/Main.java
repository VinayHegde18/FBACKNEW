package controller;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import dbcon.DbCon;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/*
 * 
 * A PROJECT BY VINAY HEGDE
 * 
 */

public class Main extends Application {
	
	public static Statement stmt;
	@Override
	public void start(Stage primaryStage) throws IOException {
		try {
			AnchorPane root = FXMLLoader.load(getClass().getResource("/view/home.fxml"));
	        Scene scene = new Scene(root);
	        scene.getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());
	        primaryStage.setScene(scene);
	        primaryStage.setResizable(false);
	        primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
//			Pane root = FXMLLoader.load(getClass().getResource("/view/menucheck.fxml"));
//			DbCon dbCon = new DbCon();
//
//			try {
//				stmt = dbCon.con.createStatement();
//			} catch (SQLException e1) {
//				e1.printStackTrace();
//			}
//			
//			VBox newroot = new VBox();
//			try {
//
//				ResultSet menulist = stmt
//						.executeQuery("select * from userauthlevel where menuitem in (select menuname from menumaster)");
//				while (menulist.next()) {
//					 String buttonString= menulist.getString("menuItem");
//                       Button button = new Button(buttonString);
//                       button.setId(buttonString);
//                       button.setMinWidth(150);
//                       newroot.getChildren().addAll(button);
//           			
// 
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			root.getChildren().add(newroot);
//			Scene scene = new Scene(root);
//			Stage mewStage = new Stage();
//			mewStage.setScene(scene);
//			mewStage.show();
//	    

	}
	

	public static void main(String[] args) { 
		Application.launch(args);
	}
}
