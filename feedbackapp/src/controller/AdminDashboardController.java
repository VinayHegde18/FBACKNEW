package controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.xml.catalog.Catalog;

import jakarta.persistence.metamodel.StaticMetamodel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import model.AddCountryModel;
import model.AllRequirementsModel;
import model.ManageUsersModel;

public class AdminDashboardController {
	@FXML
	protected AnchorPane adminContainer;

	@FXML
	protected Button logout;

	@FXML
	protected AnchorPane adminLeftContainer;

	@FXML
	protected Button profile;

	@FXML
	protected Button addUser;

	@FXML
	protected Button manageUser;

	@FXML
	protected Button requirements;

	@FXML
	protected Button someText;

	@FXML
	protected AnchorPane rightContainer;

//	    @FXML
//	    protected TableView<AllRequirementsModel> allReqTable;
//
//	    @FXML
//	    protected TableColumn<AllRequirementsModel, Integer> reqno;
//
//	    @FXML
//	    protected TableColumn<AllRequirementsModel, String> allReq;

	@FXML
	protected AnchorPane rightContainer1;

	@FXML
	protected TextField fullName;

	@FXML
	protected TextField userName;

	@FXML
	protected TextField emailId;

	@FXML
	protected TextField dobdt;

	@FXML
	private ChoiceBox<String> userLevel;

	@FXML
	private ChoiceBox<String> userState;

	@FXML
	protected TextField password;

	@FXML
	protected TextField confirmPassword;

	@FXML
	protected Button createButton;

	@FXML
	protected Button clearButton;

	@FXML
	protected TableView<ManageUsersModel> manageUsersTable;

	@FXML
	protected TableColumn<ManageUsersModel, Integer> slnoColumn;

	@FXML
	protected TableColumn<ManageUsersModel, String> fullNameColumn;

	@FXML
	protected TableColumn<ManageUsersModel, String> userNameColumn;

	@FXML
	protected TableColumn<ManageUsersModel, String> emailIdColumn;

	@FXML
	protected TableColumn actionColumn;

	@FXML
	protected TableView<?> allReqTable1;

	@FXML
	protected TableColumn<AllRequirementsModel, Integer> reqno1;

	@FXML
	protected TableColumn<AllRequirementsModel, String> allReq1;

	@FXML
	protected TextField unameProfile;

	@FXML
	private Button authLevel;

	@FXML
	private AnchorPane authPanel;

	@FXML
	private TextField authField;

	@FXML
	protected AnchorPane profileContainer;

	@FXML
	private ChoiceBox<String> authLevelBox;

//	 private ObservableList<AllRequirementsModel> dataList = FXCollections.observableArrayList();

//		@Override
//		public void initialize(URL arg0, ResourceBundle arg1) {
//	 public AdminDashboardController() {
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
//			
//		}

	GlobalVariables globalVariables = new GlobalVariables();

	@FXML
	void onSelectChangeView(ActionEvent event) {

		if (event.getSource() == profile) {
			profileContainer.setVisible(true);
			rightContainer1.setVisible(false);
			manageUsersTable.setVisible(false);
			allReqTable1.setVisible(false);
			authPanel.setVisible(false);
		} else if (event.getSource() == addUser) {
			profileContainer.setVisible(false);
			rightContainer1.setVisible(true);
			manageUsersTable.setVisible(false);
			allReqTable1.setVisible(false);
			authPanel.setVisible(false);

			ArrayList<String> levellist = new ArrayList<>();

			Connection con;
			try {
				con = DriverManager.getConnection("jdbc:mysql://localhost/java", "root", "");
				Statement stmt = con.createStatement();
				ResultSet rs1 = stmt.executeQuery("select * from userlevel");

				while (rs1.next()) {
//							countryList.add(rs.getInt("countryid"), rs.getString("countryname"));
					levellist.add(rs1.getString("levelname"));
				}
				userLevel.getItems().addAll(levellist);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			ArrayList<String> statelist = new ArrayList<>();

			try {
				con = DriverManager.getConnection("jdbc:mysql://localhost/java", "root", "");
				Statement stmt = con.createStatement();
				ResultSet rs1 = stmt.executeQuery("select * from statelist");

				while (rs1.next()) {
//							countryList.add(rs.getInt("countryid"), rs.getString("countryname"));
					statelist.add(rs1.getString("statename"));
				}
				userState.getItems().addAll(statelist);
			} catch (SQLException e) {

				e.printStackTrace();
			}

			if (event.getSource() == createButton) {
				createUser();
			}
			if (event.getSource() == clearButton) {
						String uname = userName.getText();
						String pword = password.getText();
						String cpword = confirmPassword.getText();
						String emailStr = emailId.getText();
						String name = fullName.getText();
				AddUserController addUserController = new AddUserController();
				addUserController.onClickClear();
			}

		} else if (event.getSource() == manageUser) {
			profileContainer.setVisible(false);
			rightContainer1.setVisible(false);
			manageUsersTable.setVisible(true);
			allReqTable1.setVisible(false);
			authPanel.setVisible(false);
			ManageUsersController manageUsersController = new ManageUsersController();
			manageUsersController.getUsers();
		} else if (event.getSource() == requirements) {
			profileContainer.setVisible(false);
			rightContainer1.setVisible(false);
			manageUsersTable.setVisible(false);
			allReqTable1.setVisible(true);
			authPanel.setVisible(false);
//		    		 AllRequirementsController AllRequirementsController = new AllRequirementsController();
		}

		else if (event.getSource() == authLevel) {
			profileContainer.setVisible(false);
			rightContainer1.setVisible(false);
			manageUsersTable.setVisible(false);
			allReqTable1.setVisible(false);
			authPanel.setVisible(true);

//	        	  AddCountryController addCountryController = new AddCountryController();
//	        	  authLevelBox.getItems().addAll(addCountryController.list);

			ArrayList<String> list = new ArrayList<>();

			Connection con;
			try {
				con = DriverManager.getConnection("jdbc:mysql://localhost/java", "root", "");
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("select * from authtbl");

				while (rs.next()) {
//							countryList.add(rs.getInt("countryid"), rs.getString("countryname"));
					list.add(rs.getString("authname"));
				}
				authLevelBox.getItems().addAll(list);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}
	
	public void createUser() {
		String uname = userName.getText();
		String pword = password.getText();
		String cpword = confirmPassword.getText();
		String emailStr = emailId.getText();
		String name = fullName.getText();
		AddUserController addUserController = new AddUserController();
		AddUserController.onClickCreateUser(uname, pword, cpword, emailStr, name);
	}
	
	

	@FXML
	void logout(ActionEvent event) {
		LogoutController LogoutController = new LogoutController(event);
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
