package controller;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;

import dbcon.DbCon;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.util.Callback;
import model.AllRequirementsModel;
import model.ManageUsersModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.util.ReportCreator;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.export.SimpleDocxReportConfiguration;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import net.sf.jasperreports.swing.JRViewer;
import net.sf.jasperreports.view.JasperViewer;

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

	@FXML
	private ChoiceBox<String> userStateBox;

	@FXML
	private ChoiceBox<String> userNameBox;

	@FXML
	private ChoiceBox<String> menuBox;

	@FXML
	private Button updateUserAuthLevel;

	ArrayList<String> initlist = new ArrayList<>();

	ArrayList<String> list = new ArrayList<>();

	ArrayList<String> userList = new ArrayList<>();

	ArrayList<String> menuList = new ArrayList<>();

	ArrayList<String> statelist = new ArrayList<>();

	ArrayList<String> levellist = new ArrayList<>();

	public static ObservableList<ManageUsersModel> usersList = FXCollections.observableArrayList();

	public static ObservableList<AllRequirementsModel> reqList = FXCollections.observableArrayList();

	public static Statement stmt;

	@FXML
	private Button genarateReportBtn;

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

			userList.clear();
			menuList.clear();
			list.clear();

			getUserNameBox();

			getAuthLevel();
			getMenu();

		} else if (event.getSource() == homeBtn) {

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

	@FXML
	void keypressEvent(KeyEvent event) {
		KeyPressEvent keyPressEvent = new KeyPressEvent();
		keyPressEvent.escKeyEvenet(event);
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

	public void getMenu() {

		menuBox.getItems().removeAll(menuBox.getItems());

		try {
			DbCon dbCon = new DbCon();
			Statement stmt = dbCon.con.createStatement();

			ResultSet rs1 = stmt.executeQuery("select * from menumaster");

			while (rs1.next()) {
				menuList.add(rs1.getString("menubtn"));
			}
			menuBox.getItems().addAll(menuList);

		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	public void getUserLevel() {

		userLevel.getItems().removeAll(userLevel.getItems());
//		userLevel.getItems().clear();

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

	public void getAuthLevel() {

		authLevelBox.getItems().removeAll(authLevelBox.getItems());
//		authLevelBox.getItems().clear();

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

	public void getUserNameBox() {

		userNameBox.getItems().removeAll(userNameBox.getItems());

		try {
			DbCon dbCon = new DbCon();
			Statement stmt = dbCon.con.createStatement();

			ResultSet rs = stmt.executeQuery("select * from users where del is null");

			while (rs.next()) {

				userList.add(rs.getString("username"));
			}
			userNameBox.getItems().addAll(userList);

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

				userStateBox.getItems().setAll(profileRs.getString("userstate"));

			}
			userStateBox.getItems().addAll(statelist);

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

		String fullNameString = fullNameProfile.getText();

		String userPasswordString = userPasswordProfile.getText();

		String userNameString = userNameProfile.getText();

		String emaiIdString = emailIdProfile.getText();

		String userStateString = userStateProfile.getText();

		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setContentText("Are You sure You want to update?");
		Optional<ButtonType> option = alert.showAndWait();
		if (option.get().equals(ButtonType.OK)) {

			DbCon dbCon = new DbCon();

			try {
				stmt = dbCon.con.createStatement();

				int result = stmt.executeUpdate("UPDATE USERS SET name='" + fullNameString + "',email='" + emaiIdString
						+ "',password='" + userPasswordString + "',cpassword='" + userPasswordString + "',userstate='"
						+ userStateString + "' WHERE USERNAME='" + userNameString + "'");

				if (result >= 1) {
					Alert newAlert = new Alert(Alert.AlertType.INFORMATION);
					newAlert.setContentText("Profile Updated Successfully");
					newAlert.show();
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
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
	void genarateReport(ActionEvent event) {
		DbCon dbCon = new DbCon();
		Connection con = dbCon.con;
		try {
			JasperDesign jDesign = JRXmlLoader
					.load("C:\\Users\\Administrator\\git\\FBACKNEW\\feedbackapp\\src\\reports\\allusers.jrxml");
			String str = "select * from users where del is null";
			JRDesignQuery updateQuery = new JRDesignQuery();
			updateQuery.setText(str);
			jDesign.setQuery(updateQuery);
			JasperReport jReport = JasperCompileManager.compileReport(jDesign);
			JasperPrint jPrint = JasperFillManager.fillReport(jReport, null, con);
			JasperViewer.viewReport(jPrint, false);

		} catch (JRException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void updateUserAuthLevelFunction(ActionEvent event) {

//		String authUserName=userNameBox.getItems().get(0);
		String authUserName = userNameBox.getValue();
		String authLevelName = authLevelBox.getValue();
		String menuBtn = menuBox.getValue();

		try {
			DbCon dbCon = new DbCon();
			Statement stmt = dbCon.con.createStatement();
			stmt = dbCon.con.createStatement();

			String authQuery = "select * from userauthlevel where authusername='" + authUserName
					+ "' and authlevelname='Visible'";
			ResultSet rSet = stmt.executeQuery(authQuery);
			if (rSet.next()) {
				int result = stmt.executeUpdate("update userauthlevel set authlevelname='" + authLevelName
						+ "' where authusername='" + authUserName + "' and authlevelname='Visible' ");
				if (result > 0) {
					Alert newAlert = new Alert(Alert.AlertType.INFORMATION);
					newAlert.setContentText("Updated Successfully");
					newAlert.show();
				} else {
					Alert newAlert = new Alert(Alert.AlertType.ERROR);
					newAlert.setContentText("Update Unsuccessfull");
					newAlert.show();
				}
			} else {

				int result = stmt
						.executeUpdate("insert into userauthlevel(authusername,authLevelName,menubtn) values ('"
								+ authUserName + "','" + authLevelName + "','" + menuBtn + "')");
				if (result > 0) {
					Alert newAlert = new Alert(Alert.AlertType.INFORMATION);
					newAlert.setContentText("Saved Successfully");
					newAlert.show();
				} else {
					Alert newAlert = new Alert(Alert.AlertType.ERROR);
					newAlert.setContentText("Save Unsuccessfull");
					newAlert.show();
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		getUserNameBox();
		getAuthLevel();
		getMenu();

	}

	@FXML
	void logout(ActionEvent event) {
		LogoutController LogoutController = new LogoutController();
		LogoutController.LogoutController(event);

	}
}
