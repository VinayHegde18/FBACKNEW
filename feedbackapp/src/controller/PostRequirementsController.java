package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class PostRequirementsController {

	   public static void postRequirements(String reqText) throws SQLException {
		   String username = controller.curUname;
		   LocalDate date = LocalDate.now();
		   Connection con;
		   con = DriverManager.getConnection("jdbc:mysql://localhost/java","root","");
		   java.sql.Statement stmt = con.createStatement();

		   int rs = stmt.executeUpdate("insert into allreq(req,username,date) values('"+reqText+"','"+username+"','"+date+"')");

		   if(rs > 0) {
			   Alert alert = new Alert(AlertType.INFORMATION);
			   alert.setTitle("");
			   alert.setHeaderText("");
			   alert.setContentText("Saved");
			   alert.showAndWait();

		   }
		   else
		   {
			   Alert alert = new Alert(AlertType.ERROR);
			   alert.setTitle("");
			   alert.setHeaderText("");
			   alert.setContentText("Error while Saving");
			   alert.showAndWait();
//			   .ifPresent(res -> {
//			       if (res == ButtonType.OK) {
//			           System.out.println("Pressed OK.");
//			       }
//			   });
		   }
	   }
}
