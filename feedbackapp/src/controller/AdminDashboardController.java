package controller;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import dbcon.DbCon;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.util.Callback;
import model.AllRequirementsModel;
import model.ManageUsersModel;

public class AdminDashboardController implements Initializable {
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
	public TableView<AllRequirementsModel> allReqTable;

	@FXML
	public TableColumn<AllRequirementsModel, Integer> reqno;

	@FXML
	public TableColumn<AllRequirementsModel, String> allReq;

	@FXML
	public TableColumn<AllRequirementsModel, String> userNmColumn;

	@FXML
	public TableColumn<AllRequirementsModel, String> actionCol;

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
	public TableColumn<ManageUsersModel, Integer> slnoColum;

	@FXML
	protected TableColumn<ManageUsersModel, String> fullNameColumn;

	@FXML
	protected TableColumn<ManageUsersModel, String> userNameColumn;

	@FXML
	protected TableColumn<ManageUsersModel, String> emailIdColumn;

	@FXML
	private TextArea textfld;

	@SuppressWarnings("rawtypes")
	@FXML
	protected TableColumn actionColumn;

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

	@FXML
	private AnchorPane reqContainer;

	@FXML
	private TextField fullNameProfile;

	@FXML
	private TextField userPasswordProfile;

	@FXML
	private TextField userNameProfile;

	@FXML
	private TextField emailIdProfile;

	@FXML
	private TextField userStateProfile;

	@FXML
	private Button updateUser;

	@FXML
	public TableView<AllRequirementsModel> initTable;

	@FXML
	public TableColumn<AllRequirementsModel, Integer> reqNum;

	@FXML
	public TableColumn<AllRequirementsModel, String> reqDetails;

	@FXML
	public TableColumn<AllRequirementsModel, String> uNameColumn;
	
	@FXML
	protected AnchorPane initContainer;
	
    @FXML
	private Label adminLabel2;
    
    @FXML
    private Label adminLabel1;
	
    @FXML
    protected Button homeBtn;

	ArrayList<String> initlist = new ArrayList<>();

	ArrayList<String> list = new ArrayList<>();

	ArrayList<String> statelist = new ArrayList<>();

	ArrayList<String> levellist = new ArrayList<>();

	public static ObservableList<ManageUsersModel> usersList = FXCollections.observableArrayList();

	public static ObservableList<AllRequirementsModel> reqList = FXCollections.observableArrayList();

	public static Statement stmt;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			DbCon dbCon = new DbCon();

			stmt = dbCon.con.createStatement();

			ResultSet rs = stmt.executeQuery("select * from allreq where mrk is null order by reqno desc limit 5");

			while (rs.next()) {

				reqList.add(
						new AllRequirementsModel(rs.getInt("reqno"), rs.getString("req"), rs.getString("username")));
			}
			initTable.setItems(reqList);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		reqNum.setCellValueFactory(new PropertyValueFactory<AllRequirementsModel, Integer>("reqno"));
		reqDetails.setCellValueFactory(new PropertyValueFactory<AllRequirementsModel, String>("req"));
		uNameColumn.setCellValueFactory(new PropertyValueFactory<AllRequirementsModel, String>("uName"));

	}

	@FXML
	void onSelectChangeView(ActionEvent event) {

		if (event.getSource() == profile) {

			profileContainer.setVisible(true);

			rightContainer1.setVisible(false);

			manageUsersTable.setVisible(false);

			allReqTable.setVisible(false);

			authPanel.setVisible(false);
			
			initContainer.setVisible(false);

			getProfileDetails();

		}

		else if (event.getSource() == addUser) {
			profileContainer.setVisible(false);

			rightContainer1.setVisible(true);

			manageUsersTable.setVisible(false);

			allReqTable.setVisible(false);

			authPanel.setVisible(false);
			
			initContainer.setVisible(false);

			levellist.clear();
			statelist.clear();

			getUserLevel();
			getState();

		}

		else if (event.getSource() == manageUser) {
			profileContainer.setVisible(false);

			rightContainer1.setVisible(false);

			manageUsersTable.setVisible(true);

			allReqTable.setVisible(false);

			authPanel.setVisible(false);
			
			initContainer.setVisible(false);

			usersList.clear();

			manageUsersTable.getItems().clear();

			manageUsers();

		}

		else if (event.getSource() == requirements) {

			profileContainer.setVisible(false);

			rightContainer1.setVisible(false);

			manageUsersTable.setVisible(false);

			allReqTable.setVisible(true);

			authPanel.setVisible(false);
			
			initContainer.setVisible(false);

			reqList.clear();

			allReqTable.getItems().clear();

			AllRequirements();
		}

		else if (event.getSource() == authLevel) {

			profileContainer.setVisible(false);

			rightContainer1.setVisible(false);

			manageUsersTable.setVisible(false);

			allReqTable.setVisible(false);

			authPanel.setVisible(true);
			
			initContainer.setVisible(false);

			try {
				DbCon dbCon = new DbCon();
				Statement stmt = dbCon.con.createStatement();

				ResultSet rs = stmt.executeQuery("select * from authtbl");

				while (rs.next()) {

					list.add(rs.getString("authname"));
				}
				authLevelBox.getItems().addAll(list);

			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
		else if (event.getSource() == homeBtn){

			profileContainer.setVisible(false);

			rightContainer1.setVisible(false);

			manageUsersTable.setVisible(false);

			allReqTable.setVisible(false);

			authPanel.setVisible(false);
			
			initContainer.setVisible(true);
			initTable.getItems().clear();
			initialize(null, null);
		}
	}

	public void getState() {

		userState.getItems().removeAll(userState.getItems());

		try {
			DbCon dbCon = new DbCon();
			Statement stmt = dbCon.con.createStatement();

			ResultSet rs1 = stmt.executeQuery("select * from statelist");

			while (rs1.next()) {
				statelist.add(rs1.getString("statename"));
			}
			userState.getItems().addAll(statelist);

		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	public void getUserLevel() {

		userLevel.getItems().removeAll(userLevel.getItems());

		try {
			DbCon dbCon = new DbCon();
			Statement stmt = dbCon.con.createStatement();

			ResultSet rs2 = stmt.executeQuery("select * from userlevel");

			while (rs2.next()) {

				levellist.add(rs2.getString("levelid"));
			}

			userLevel.getItems().addAll(levellist);

		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	public void getProfileDetails() {

		DbCon dbCon = new DbCon();

		String loggedinUname = controller.curUname;

		Statement stmt;

		try {
			stmt = dbCon.con.createStatement();

			ResultSet profileRs = stmt
					.executeQuery("select * from users where del is null and username='" + loggedinUname + "'");

			while (profileRs.next()) {

				fullNameProfile.setText(profileRs.getString("name"));

				userPasswordProfile.setText(profileRs.getString("password"));

				userNameProfile.setText(profileRs.getString("username"));

				emailIdProfile.setText(profileRs.getString("email"));

				userStateProfile.setText(profileRs.getString("userstate"));
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public void manageUsers() {
		DbCon dbCon = new DbCon();

		try {
			Statement stmt = dbCon.con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from users where del is null");

			while (rs.next()) {

				usersList.add(new ManageUsersModel(rs.getInt("userid"), rs.getString("name"), rs.getString("username"),
						rs.getString("email")));
			}
			manageUsersTable.setItems(usersList);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		slnoColum.setCellValueFactory(new PropertyValueFactory<ManageUsersModel, Integer>("userId"));
		fullNameColumn.setCellValueFactory(new PropertyValueFactory<ManageUsersModel, String>("fullName"));
		userNameColumn.setCellValueFactory(new PropertyValueFactory<ManageUsersModel, String>("userName"));
		emailIdColumn.setCellValueFactory(new PropertyValueFactory<ManageUsersModel, String>("emailId"));

		Callback<TableColumn<ManageUsersModel, String>, TableCell<ManageUsersModel, String>> cellfactory = (param) -> {

			final TableCell<ManageUsersModel, String> cell = new TableCell<ManageUsersModel, String>() {

				@Override
				public void updateItem(String item, boolean empty) {
					super.updateItem(item, empty);

					if (empty) {
						setGraphic(null);
						setText(null);
					} else {
						final Button deleteButton = new Button("Delete");
						deleteButton.setOnAction(event -> {
							ManageUsersModel m = getTableView().getItems().get(getIndex());
							int userId = m.getUserId();

							if (userId != 1) {

								Alert alert = new Alert(AlertType.CONFIRMATION);
								alert.setContentText("Are You sure You want to delete this user?");
								Optional<ButtonType> option = alert.showAndWait();
								if (option.get().equals(ButtonType.OK)) {
									try {
										DbCon dbCon = new DbCon();
										Statement stmt = dbCon.con.createStatement();
										int result = stmt.executeUpdate(
												"update users set del='d' where userid='" + userId + "'");
										if (result == 1) {
											Alert newAlert = new Alert(Alert.AlertType.INFORMATION);
											newAlert.setContentText("deleted user");
											newAlert.show();
											refreshTable();
										}
									} catch (SQLException e) {
										e.printStackTrace();
									}
								}
							} else {
								Alert newAlert = new Alert(Alert.AlertType.INFORMATION);
								newAlert.setContentText("Cannot delete Admin");
								newAlert.show();
							}
						});
						setGraphic(deleteButton);
						setText(null);

					}
				}

			};

			return cell;
		};

		actionColumn.setCellFactory(cellfactory);
	}

	public void AllRequirements() {

		try {
			
			allReqTable.getItems().clear();

			DbCon dbCon = new DbCon();

			stmt = dbCon.con.createStatement();

			ResultSet rs = stmt.executeQuery("select * from allreq where mrk is null");

			while (rs.next()) {

				reqList.add(
						new AllRequirementsModel(rs.getInt("reqno"), rs.getString("req"), rs.getString("username")));
			}
			allReqTable.setItems(reqList);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		reqno.setCellValueFactory(new PropertyValueFactory<AllRequirementsModel, Integer>("reqno"));
		allReq.setCellValueFactory(new PropertyValueFactory<AllRequirementsModel, String>("req"));
		userNmColumn.setCellValueFactory(new PropertyValueFactory<AllRequirementsModel, String>("uName"));

		Callback<TableColumn<AllRequirementsModel, String>, TableCell<AllRequirementsModel, String>> cellfactoryNew = (
				param) -> {

			final TableCell<AllRequirementsModel, String> cell = new TableCell<AllRequirementsModel, String>() {

				@Override
				public void updateItem(String item, boolean empty) {
					super.updateItem(item, empty);

					if (empty) {
						setGraphic(null);
						setText(null);
					} else {
						final Button deleteButton = new Button("Delete");
						deleteButton.setOnAction(event -> {
							AllRequirementsModel m = getTableView().getItems().get(getIndex());
							int reqNo = m.getReqno();

							Alert alert = new Alert(AlertType.CONFIRMATION);
							alert.setContentText("Are You sure You want to delete this requirement?");
							Optional<ButtonType> option = alert.showAndWait();
							if (option.get().equals(ButtonType.OK)) {
								try {
									DbCon dbCon = new DbCon();
									Statement stmt = dbCon.con.createStatement();
									int result = stmt
											.executeUpdate("update allreq set mrk='d' where reqno='" + reqNo + "'");
									if (result == 1) {
										Alert newAlert = new Alert(Alert.AlertType.INFORMATION);
										newAlert.setContentText("deleted requirement");
										newAlert.show();
										refreshReqTable();
									}
								} catch (SQLException e) {
									e.printStackTrace();
								}
							}
						});
						setGraphic(deleteButton);
						setText(null);

					}
				}

			};

			return cell;
		};

		actionCol.setCellFactory(cellfactoryNew);

	    allReq.setCellFactory(param -> {
	        return new TableCell<AllRequirementsModel, String>() {
	            @Override
	            protected void updateItem(String item, boolean empty) {
	                super.updateItem(item, empty);

	                if (item == null || empty) {
	                    setText(null);
	                    setStyle("");
	                } else {
	                    Text text = new Text(item);
	                    text.setStyle("-fx-text-alignment:justify;");                      
	                    text.wrappingWidthProperty().bind(getTableColumn().widthProperty().subtract(35));
	                    setGraphic(text);
	                }
	            }
	        };
	    });
	}

	public void refreshReqTable() {
		allReqTable.getItems().clear();
		AllRequirements();
	}
//
//	@SuppressWarnings({ "rawtypes", "unchecked" })
//	public void selectCourse() {
//		allReqTable.getSelectionModel().setCellSelectionEnabled(true);
//		ObservableList selectedCells = allReqTable.getSelectionModel().getSelectedCells();
//		selectedCells.addListener(new ListChangeListener() {
//
//			@Override
//			public void onChanged(Change c) {
//				if (!selectedCells.isEmpty()) {
//					TablePosition tablePositionOne = (TablePosition) selectedCells.get(0);
//					Object val = tablePositionOne.getTableColumn().getCellData(tablePositionOne.getRow());
//					String text11 = val.toString();
//					try {
//						FXMLLoader newloader = new FXMLLoader(getClass().getResource("/view/ViewRequirement.fxml"));
//
//						Parent root2 = newloader.load();
//						ViewRequirementController viewRequirementController = newloader.getController();
//						viewRequirementController.setTexts(text11);
//
//						Scene scene = new Scene(root2);
//						Stage newstage = new Stage();
//						newstage.setResizable(false);
//						newstage.setScene(scene);
//						newstage.show();
//
//					} catch (IOException e) {
//
//						e.printStackTrace();
//						System.err.println(String.format("Error: %s", e.getMessage()));
//					}
//				}
//			}
//		});
//	}

	@FXML
	public void createUser(ActionEvent event) {

		String uname = userName.getText();

		String pword = password.getText();

		String cpword = confirmPassword.getText();

		String emailStr = emailId.getText();

		String name = fullName.getText();

		String uLevelString = userLevel.getItems().get(0);

		int uLevel = Integer.parseInt(uLevelString);

		String uState = userState.getItems().get(0);

		AddUserController.onClickCreateUser(uname, pword, cpword, emailStr, name, uLevel, uState);
		clearUsersFields();
	}

	@FXML
	void onClickClear(ActionEvent event) {

		userName.setText("");

		password.setText("");

		confirmPassword.setText("");

		emailId.setText("");

		fullName.setText("");

		dobdt.setText("");

		levellist.clear();
		statelist.clear();

		getState();
		getUserLevel();

	}

	@FXML
	void onClickUpdateUser(ActionEvent event) {

		DbCon dbCon = new DbCon();

		try {
			stmt = dbCon.con.createStatement();

			ResultSet rs2 = stmt.executeQuery("select * from userlevel");

			while (rs2.next()) {

				levellist.add(rs2.getString("levelid"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void refreshTable() {
		usersList.clear();
		manageUsersTable.getItems().clear();
		manageUsers();
	}

	public void clearUsersFields() {
		userName.setText("");

		password.setText("");

		confirmPassword.setText("");

		emailId.setText("");

		fullName.setText("");

		dobdt.setText("");

		levellist.clear();
		statelist.clear();

		getState();
		getUserLevel();
	}

	@FXML
	void logout(ActionEvent event) {

		@SuppressWarnings("unused")
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
