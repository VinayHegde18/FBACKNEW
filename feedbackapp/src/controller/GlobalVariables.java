package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import model.AllRequirementsModel;
import model.ManageUsersModel;

public class GlobalVariables {

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

	    @FXML
	    protected TableView<AllRequirementsModel> allReqTable;

	    @FXML
	    protected TableColumn<AllRequirementsModel, Integer> reqno;

	    @FXML
	    protected TableColumn<AllRequirementsModel, String> allReq;

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
	    protected MenuButton userLevel;

	    @FXML
	    protected MenuButton userCountry;

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
	    protected AnchorPane profileContainer;
}
