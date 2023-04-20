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
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.AllRequirementsModel;

public class AdminDashboardController implements Initializable {
	    
    @FXML
    private AnchorPane adminContainer;

    @FXML
    private Button logout;

    @FXML
    private AnchorPane adminLeftContainer;
    
    @FXML
    private Button profile;

    @FXML
    private Button addUser;

    @FXML
    private Button manageUser;

    @FXML
    private Button requirements;
	    
		@FXML
		private TableView<AllRequirementsModel> allReqTable;
		@FXML
		private TableColumn<AllRequirementsModel, Integer> reqno;
		@FXML
		private TableColumn<AllRequirementsModel, String> allReq;

		private ObservableList<AllRequirementsModel> dataList = FXCollections.observableArrayList();

		@Override
		public void initialize(URL url, ResourceBundle rb) {

			Connection con;
			try {
				con = DriverManager.getConnection("jdbc:mysql://localhost/java", "root", "");
				Statement stmt = con.createStatement();

				ResultSet rs = stmt.executeQuery("select * from allreq order by reqno desc limit 5");

				while (rs.next()) {

					dataList.add(new AllRequirementsModel(rs.getInt("reqno"), rs.getString("req")));

				}
				allReqTable.setItems(dataList);

			} catch (SQLException e) {
				e.printStackTrace();
				System.err.println(String.format("Error: %s", e.getMessage()));
			}
			reqno.setCellValueFactory(new PropertyValueFactory<AllRequirementsModel, Integer>("reqno"));
			allReq.setCellValueFactory(new PropertyValueFactory<AllRequirementsModel, String>("req"));
		}


    @FXML
    void addUsers(ActionEvent event) {
		try {

			FXMLLoader newloader = new FXMLLoader(getClass().getResource("/view/adduser.fxml"));

			Pane root2 = newloader.load();
			Scene scene = new Scene(root2);
			Stage newstage = new Stage();
			newstage.setResizable(false);
			newstage.setScene(scene);

			newstage.show();
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println(String.format("Error: %s", e.getMessage()));
		}
    }

    @FXML
    void manageUsers(ActionEvent event) {
		try {

			FXMLLoader newloader = new FXMLLoader(getClass().getResource("/view/manageusers.fxml"));

			Pane root2 = newloader.load();

			Scene scene = new Scene(root2);
			Stage newstage = new Stage();
			newstage.setResizable(false);
			newstage.setScene(scene);

			newstage.show();
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println(String.format("Error: %s", e.getMessage()));
		}
    }
    
    @FXML
    void yourProfile(ActionEvent event) {
		try {

			FXMLLoader newloader = new FXMLLoader(getClass().getResource("/view/profile.fxml"));

			Pane root2 = newloader.load();
			Scene scene = new Scene(root2);
			Stage newstage = new Stage();
			newstage.setResizable(false);
			newstage.setScene(scene);

			newstage.show();
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println(String.format("Error: %s", e.getMessage()));
		}
    }

    @FXML
    void requirements(ActionEvent event) {
		try {

			FXMLLoader newloader = new FXMLLoader(getClass().getResource("/view/allrequirement.fxml"));

			Pane root2 = newloader.load();

			AllRequirementsController AllRequirementsController = new AllRequirementsController();
			Scene scene = new Scene(root2);
			Stage newstage = new Stage();
			newstage.setResizable(false);
			newstage.setScene(scene);

			newstage.show();
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println(String.format("Error: %s", e.getMessage()));
		}
    }

    @FXML
    void logout(ActionEvent event) {
      LogoutController LogoutController= new LogoutController(event);
  	}
    }

