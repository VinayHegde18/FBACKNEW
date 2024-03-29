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

import javax.xml.transform.Source;

import dbcon.DbCon;
import javafx.animation.Animation.Status;
import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;
import model.AllRequirementsModel;
import model.YourRequirementsModel;

public class UserDashboardController implements Initializable {

	@FXML
	private Pane userPage;

	@FXML
	private Button updateProfileButton;

	@FXML
	private Button homeBtn;

	@FXML
	private AnchorPane userContainer;

	@FXML
	private Label wlcometxt;

	@FXML
	private Label headerText;

	@FXML
	private Button postButton;

	@FXML
	private Button allReqButton;

	@FXML
	private Button yourReqButton;

	@FXML
	private Button logoutButton;

	@FXML
	public AnchorPane profileContainer;

	@FXML
	public TextField fullNameProfile;

	@FXML
	public TextField userPasswordProfile;

	@FXML
	public TextField userNameProfile;

	@FXML
	public TextField emailIdProfile;

	@FXML
	public TextField userStateProfile;

	@FXML
	public Button profileBtn;

	@FXML
	public AnchorPane postContainer;

	@FXML
	private Button postReq;

	@FXML
	private TextArea postText;

	@FXML
	private AnchorPane reqContainer;

	@FXML
	private TableView<YourRequirementsModel> yourReqTable;

	@FXML
	private TableColumn<YourRequirementsModel, Integer> yreqno;

	@FXML
	private TableColumn<YourRequirementsModel, String> yreq;

	@FXML
	private AnchorPane yourReqContainer;

	@FXML
	private TableView<AllRequirementsModel> allReqTable;

	@FXML
	private TableColumn<AllRequirementsModel, Integer> reqno;

	@FXML
	private TableColumn<AllRequirementsModel, String> allReq;

	@FXML
	private TableColumn<AllRequirementsModel, String> userNmColumn;

	@FXML
	private TableColumn actionCol;

	@FXML
	private AnchorPane initContainer;

	@FXML
	private VBox menuVbox;

	@FXML
	public TableView<AllRequirementsModel> initTable;

	@FXML
	public TableColumn<AllRequirementsModel, Integer> reqNum;

	@FXML
	public TableColumn<AllRequirementsModel, String> reqDetails;

	@FXML
	public TableColumn<AllRequirementsModel, String> uNameCol;

	private String loggedinUname = controller.curUname;

	public ObservableList<AllRequirementsModel> dataList = FXCollections.observableArrayList();

	public ObservableList<YourRequirementsModel> dataList2 = FXCollections.observableArrayList();

	public static ObservableList<AllRequirementsModel> reqList = FXCollections.observableArrayList();

//	DbCon dbCon = new DbCon();

	public static Statement stmt;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		DbCon dbCon = new DbCon();
//		int btnId=1;
		try {
			stmt = dbCon.con.createStatement();
			String menuQueryString = "select userauthlevel.menuBtn as menubtn,menumaster.menuname as menuname from menumaster INNER join userauthlevel ON menumaster.menubtn= userauthlevel.menuBtn  and authusername='"
					+ loggedinUname + "' and authlevelname='visible'";
			ResultSet menulist = stmt.executeQuery(menuQueryString);
			while (menulist.next()) {
				String buttonString = menulist.getString("menuname");
				Button button = new Button(buttonString);
//				String conv = String.valueOf(btnId);
//				String btnid = "menuBtn"+conv;
				String btnid = menulist.getString("menubtn");
				button.setId(btnid);
				button.setMinWidth(200);
				button.setMaxWidth(200);
				button.setMaxHeight(40);
				button.setMinHeight(40);
				button.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						onClickDisplay(btnid);
					}

				});
				menuVbox.getChildren().addAll(button);
//				btnId++;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		try {

			ResultSet rs = stmt.executeQuery("select * from allreq where mrk is null");

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
		uNameCol.setCellValueFactory(new PropertyValueFactory<AllRequirementsModel, String>("uName"));
		reqDetails.setCellFactory(param -> {
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

	public void setLabelText(String text) {
		wlcometxt.setText("Welcome " + text);
	}

	public void onClickDisplay(String btnId) {

		if (btnId.equalsIgnoreCase("menuBtn1")) {

			profileContainer.setVisible(false);
			reqContainer.setVisible(false);
			yourReqContainer.setVisible(false);
			initContainer.setVisible(true);
			postContainer.setVisible(false);
			AllRequirements();
		}

		else if (btnId.equalsIgnoreCase("menuBtn2")) {

			profileContainer.setVisible(true);
			reqContainer.setVisible(false);
			yourReqContainer.setVisible(false);
			postContainer.setVisible(false);
			initContainer.setVisible(false);
			allReqTable.getItems().clear();
			getUserProfileDetails();

		} else if (btnId.equalsIgnoreCase("menuBtn3")) {

			profileContainer.setVisible(false);
			reqContainer.setVisible(false);
			yourReqContainer.setVisible(false);
			postContainer.setVisible(true);
			initContainer.setVisible(false);
			postText.clear();

		} else if (btnId.equalsIgnoreCase("menuBtn4")) {
			postContainer.setVisible(false);
			profileContainer.setVisible(false);
			reqContainer.setVisible(true);
			yourReqContainer.setVisible(false);
			initContainer.setVisible(false);
			yourReqTable.getItems().clear();
			AllRequirements();

		} else if (btnId.equalsIgnoreCase("menuBtn5")) {
			postContainer.setVisible(false);
			profileContainer.setVisible(false);
			reqContainer.setVisible(false);
			yourReqContainer.setVisible(true);
			initContainer.setVisible(false);
			yourReqTable.getItems().clear();
			yourRequirements();

		}
	}

	@FXML
	void postRequirements(ActionEvent event) {

		String reqText = postText.getText();

		try {

			PostRequirementsController.postRequirements(reqText);
			postText.clear();

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	@FXML
	void updateProfile(ActionEvent event) {
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

				int initRes = stmt.executeUpdate("update users set del='D' where username='" + userNameString + "'");

				int result = stmt
						.executeUpdate("insert into users(name,email,username,password,cpassword,userstate) values ('"
								+ fullNameString + "','" + emaiIdString + "','" + userNameString + "','"
								+ userPasswordString + "','" + userPasswordString + "','" + userStateString + "')");

				if (result == 1) {
					Alert newAlert = new Alert(Alert.AlertType.INFORMATION);
					newAlert.setContentText("Profile Updated Successfully");
					newAlert.show();
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	public void getUserProfileDetails() {
		DbCon dbCon = new DbCon();

		try {
			stmt = dbCon.con.createStatement();

			ResultSet profileRs2 = stmt
					.executeQuery("select * from users where del is null and username='" + loggedinUname + "'");

			while (profileRs2.next()) {

				fullNameProfile.setText(profileRs2.getString("name"));

				userPasswordProfile.setText(profileRs2.getString("password"));

				userNameProfile.setText(profileRs2.getString("username"));

				emailIdProfile.setText(profileRs2.getString("email"));

				userStateProfile.setText(profileRs2.getString("userstate"));

			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	public void AllRequirements() {
		allReqTable.getItems().clear();
		try {
			DbCon dbCon = new DbCon();
			stmt = dbCon.con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from allreq where mrk is null");

			while (rs.next()) {

				dataList.add(
						new AllRequirementsModel(rs.getInt("reqno"), rs.getString("req"), rs.getString("username")));
			}
			allReqTable.setItems(dataList);

		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println(String.format("Error: %s", e.getMessage()));
		}
		reqno.setCellValueFactory(new PropertyValueFactory<AllRequirementsModel, Integer>("reqno"));
		allReq.setCellValueFactory(new PropertyValueFactory<AllRequirementsModel, String>("req"));
		userNmColumn.setCellValueFactory(new PropertyValueFactory<AllRequirementsModel, String>("uName"));
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

	public void yourRequirements() {
		try {
			DbCon dbCon = new DbCon();
			stmt = dbCon.con.createStatement();

			ResultSet rs = stmt
					.executeQuery("select * from allreq where mrk is null and username='" + loggedinUname + "'");

			while (rs.next()) {

				dataList2.add(new YourRequirementsModel(rs.getInt("reqno"), rs.getString("req")));

			}
			yourReqTable.setItems(dataList2);

		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println(String.format("Error: %s", e.getMessage()));
		}
		yreqno.setCellValueFactory(new PropertyValueFactory<YourRequirementsModel, Integer>("reqno"));
		yreq.setCellValueFactory(new PropertyValueFactory<YourRequirementsModel, String>("req"));

		Callback<TableColumn<YourRequirementsModel, String>, TableCell<YourRequirementsModel, String>> cellfactory = (
				param) -> {

			final TableCell<YourRequirementsModel, String> cell = new TableCell<YourRequirementsModel, String>() {

				@Override
				public void updateItem(String item, boolean empty) {
					super.updateItem(item, empty);

					if (empty) {
						setGraphic(null);
						setText(null);
					} else {
						final Button editButton = new Button("DELETE");
						editButton.setOnAction(event -> {
							YourRequirementsModel m = getTableView().getItems().get(getIndex());
							int reqno = m.getReqno();
							Alert alert = new Alert(AlertType.CONFIRMATION);
							alert.setContentText("Are You sure You want to delete this requirement?");
							Optional<ButtonType> option = alert.showAndWait();
							if (option.get().equals(ButtonType.OK)) {
								try {
									DbCon dbCon = new DbCon();
									stmt = dbCon.con.createStatement();
									int result = stmt
											.executeUpdate("update allreq set mrk='d' where reqno='" + reqno + "'");
									if (result == 1) {
										Alert newAlert = new Alert(Alert.AlertType.INFORMATION);
										newAlert.setContentText("deleted!");
										newAlert.show();
										refreshTable();
									}
								} catch (SQLException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
						});
						setGraphic(editButton);
						setText(null);
					}
				}

			};

			return cell;
		};

		actionCol.setCellFactory(cellfactory);
		yreq.setCellFactory(param -> {
			return new TableCell<YourRequirementsModel, String>() {
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

	public void refreshTable() {
		dataList.clear();
		yourReqTable.getItems().clear();
		yourRequirements();
	}

	@FXML
	public void onClickLogout(ActionEvent event) {
		LogoutController LogoutController = new LogoutController();
		LogoutController.LogoutController(event);
	}

//	@FXML
//	Button yourReqButton;
//   @FXML
//	public void onClickYourRequirements(ActionEvent event) {
//		try {
//
//			FXMLLoader newloader2 = new FXMLLoader(getClass().getResource("/view/yourrequirements.fxml"));
//
//			Pane root3 = newloader2.load();
//
//			YourrequirementsController YourrequirementsController = new YourrequirementsController();
//			Scene scene = new Scene(root3);
//			Stage newstage1 = new Stage();
//			newstage1.setScene(scene);
//
//			newstage1.show();
//		} catch (IOException e) {
//			e.printStackTrace();
//			System.err.println(String.format("Error: %s", e.getMessage()));
//		}
//	}
//

}
