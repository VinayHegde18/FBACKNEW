package controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import javax.xml.catalog.Catalog;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import model.AllRequirementsModel;
import model.ManageUsersModel;


public class AdminDashboardController{
	
	 private ObservableList<AllRequirementsModel> dataList = FXCollections.observableArrayList();
	    
//		@Override
//		public void initialize(URL arg0, ResourceBundle arg1) {
	 public AdminDashboardController() {
			Connection con;
			try {
				con = DriverManager.getConnection("jdbc:mysql://localhost/java", "root", "");
				Statement stmt = con.createStatement();

				ResultSet rs = stmt.executeQuery("select * from allreq order by reqno desc limit 5");

				while (rs.next()) {

					dataList.add(new AllRequirementsModel(rs.getInt("reqno"), rs.getString("req")));

				}
				globalVariables.allReqTable.setItems(dataList);

			} catch (SQLException e) {
				e.printStackTrace();
				System.err.println(String.format("Error: %s", e.getMessage()));
			}
			globalVariables.reqno.setCellValueFactory(new PropertyValueFactory<AllRequirementsModel, Integer>("reqno"));
			globalVariables.allReq.setCellValueFactory(new PropertyValueFactory<AllRequirementsModel, String>("req"));
			
		}

	
       GlobalVariables globalVariables = new GlobalVariables();

	    @FXML
	    void onSelectChangeView(ActionEvent event) {
	    	try{
	    	 if(event.getSource()==globalVariables.profile) {
	    		 globalVariables.profileContainer.setVisible(true);
	    		 globalVariables.rightContainer1.setVisible(false);
	    		 globalVariables.manageUsersTable.setVisible(false);
	    		 globalVariables.allReqTable1.setVisible(false);
	    		 globalVariables.rightContainer.setVisible(false);
	          }
	          else if(event.getSource()==globalVariables.addUser) {
	        	  globalVariables.profileContainer.setVisible(false);
	        	  globalVariables.rightContainer1.setVisible(true);
	        	  globalVariables.manageUsersTable.setVisible(false);
	        	  globalVariables.allReqTable1.setVisible(false);
	        	  globalVariables.rightContainer.setVisible(false);
		    		 
		    		 AddUserController addUserController = new AddUserController();
	          }
	          else if(event.getSource()==globalVariables.manageUser) {
	        	  globalVariables.profileContainer.setVisible(false);
	        	  globalVariables.rightContainer1.setVisible(false);
	        	  globalVariables.manageUsersTable.setVisible(true);
	        	  globalVariables.allReqTable1.setVisible(false);
	        	  globalVariables.rightContainer.setVisible(false);
	        	  ManageUsersController manageUsersController = new ManageUsersController();
		    		 manageUsersController.initialize(null, null);
	          }
	          else if(event.getSource()==globalVariables.requirements) {
	        	  globalVariables.profileContainer.setVisible(false);
	        	  globalVariables.rightContainer1.setVisible(false);
	        	  globalVariables.manageUsersTable.setVisible(false);
	        	  globalVariables.allReqTable1.setVisible(true);
	        	  globalVariables.rightContainer.setVisible(false);
		    		 AllRequirementsController AllRequirementsController = new AllRequirementsController();
	          }
	    }
	    catch(Exception e) {
			System.out.println("error while loading");

		}
	    }
	   
	    @FXML
	    void logout(ActionEvent event) {
	      LogoutController LogoutController= new LogoutController(event);
	  	}
	
	
//	    
//    @FXML
//    private AnchorPane adminContainer;
//
//    @FXML
//    private Button logout;
//
//    @FXML
//    private AnchorPane adminLeftContainer;
//    
//    @FXML
//    private Button profile;
//
//    @FXML
//    private Button addUser;
//
//    @FXML
//    private Button manageUser;
//
//    @FXML
//    private Button requirements;
//	    
//		@FXML
//		private TableView<AllRequirementsModel> allReqTable;
//		@FXML
//		private TableColumn<AllRequirementsModel, Integer> reqno;
//		@FXML
//		private TableColumn<AllRequirementsModel, String> allReq;
//
//		private ObservableList<AllRequirementsModel> dataList = FXCollections.observableArrayList();
//
//		@Override
//		public void initialize(URL url, ResourceBundle rb) {
//
//			Connection con;
//			try {
//				con = DriverManager.getConnection("jdbc:mysql://localhost/java", "root", "");
//				Statement stmt = con.createStatement();
//
//				ResultSet rs = stmt.executeQuery("select * from allreq order by reqno desc limit 5");
//
//				while (rs.next()) {
//
//					dataList.add(new AllRequirementsModel(rs.getInt("reqno"), rs.getString("req")));
//
//				}
//				allReqTable.setItems(dataList);
//
//			} catch (SQLException e) {
//				e.printStackTrace();
//				System.err.println(String.format("Error: %s", e.getMessage()));
//			}
//			reqno.setCellValueFactory(new PropertyValueFactory<AllRequirementsModel, Integer>("reqno"));
//			allReq.setCellValueFactory(new PropertyValueFactory<AllRequirementsModel, String>("req"));
//		}
//
//
//    @FXML
//    void addUsers(ActionEvent event) {
//		try {
//
//			FXMLLoader newloader = new FXMLLoader(getClass().getResource("/view/adduser.fxml"));
//
//			Pane root2 = newloader.load();
//			Scene scene = new Scene(root2);
//			Stage newstage = new Stage();
//			newstage.setResizable(false);
//			newstage.setScene(scene);
//
//			newstage.show();
//		} catch (IOException e) {
//			e.printStackTrace();
//			System.err.println(String.format("Error: %s", e.getMessage()));
//		}
//    }
//
//    @FXML
//    void manageUsers(ActionEvent event) {
//		try {
//
//			FXMLLoader newloader = new FXMLLoader(getClass().getResource("/view/manageusers.fxml"));
//
//			Pane root2 = newloader.load();
//
//			Scene scene = new Scene(root2);
//			Stage newstage = new Stage();
//			newstage.setResizable(false);
//			newstage.setScene(scene);
//
//			newstage.show();
//		} catch (IOException e) {
//			e.printStackTrace();
//			System.err.println(String.format("Error: %s", e.getMessage()));
//		}
//    }
//    
//    @FXML
//    void yourProfile(ActionEvent event) {
//		try {
//
//			FXMLLoader newloader = new FXMLLoader(getClass().getResource("/view/profile.fxml"));
//
//			Pane root2 = newloader.load();
//			Scene scene = new Scene(root2);
//			Stage newstage = new Stage();
//			newstage.setResizable(false);
//			newstage.setScene(scene);
//
//			newstage.show();
//		} catch (IOException e) {
//			e.printStackTrace();
//			System.err.println(String.format("Error: %s", e.getMessage()));
//		}
//    }
//
//    @FXML
//    void requirements(ActionEvent event) {
//		try {
//
//			FXMLLoader newloader = new FXMLLoader(getClass().getResource("/view/allrequirement.fxml"));
//
//			Pane root2 = newloader.load();
//
//			AllRequirementsController AllRequirementsController = new AllRequirementsController();
//			Scene scene = new Scene(root2);
//			Stage newstage = new Stage();
//			newstage.setResizable(false);
//			newstage.setScene(scene);
//
//			newstage.show();
//		} catch (IOException e) {
//			e.printStackTrace();
//			System.err.println(String.format("Error: %s", e.getMessage()));
//		}
//    }
//
    }

