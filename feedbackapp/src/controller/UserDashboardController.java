package controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;

import dbcon.DbCon;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import model.AllRequirementsModel;
import model.YourRequirementsModel;

public class UserDashboardController {

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
	private AnchorPane profileContainer;

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
	private Button profileBtn;

	@FXML
	private AnchorPane postContainer;

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
	private TableColumn actionCol;
	
	public String loggedinUname = controller.curUname;

	public static Statement stmt;
	
	public ObservableList<AllRequirementsModel> dataList = FXCollections.observableArrayList();
	
	public ObservableList<YourRequirementsModel> dataList2 = FXCollections.observableArrayList();


	public void setLabelText(String text) {
		wlcometxt.setText("Welcome " + text);
	}

	@FXML
	void onClickDisplay(ActionEvent event) {

		if (event.getSource() == profileBtn) {

			profileContainer.setVisible(true);
			reqContainer.setVisible(false);
			yourReqContainer.setVisible(false);
			postContainer.setVisible(false);
			getUserProfileDetails();
		}

		else if (event.getSource() == allReqButton) {

			profileContainer.setVisible(false);
			reqContainer.setVisible(true);
			yourReqContainer.setVisible(false);
			postContainer.setVisible(false);
			allReqTable.getItems().clear();
			AllRequirements();

		} else if (event.getSource() == postButton) {

			profileContainer.setVisible(false);
			reqContainer.setVisible(false);
			yourReqContainer.setVisible(false);
			postContainer.setVisible(true);

		} else if (event.getSource() == yourReqButton) {

			profileContainer.setVisible(false);
			reqContainer.setVisible(false);
			yourReqContainer.setVisible(true);
			postContainer.setVisible(false);
			yourReqTable.getItems().clear();
			yourRequirements();

		}
	}
	
	@FXML
    void postRequirements(ActionEvent event) {
        
    	String reqText = postText.getText();

    	try {
    		
			PostRequirementsController.postRequirements(reqText);
			
		} catch (SQLException e) {

			e.printStackTrace();
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
			try {
				DbCon dbCon = new DbCon();
				Statement stmt = dbCon.con.createStatement();

				ResultSet rs = stmt.executeQuery("select * from allreq where mrk is null");

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
	
	public void yourRequirements() {
		try {
			DbCon dbCon = new DbCon();
			Statement stmt = dbCon.con.createStatement();

			ResultSet rs = stmt.executeQuery("select * from allreq where mrk is null and username='" + loggedinUname + "'");

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
							Connection con;
							try {
								con = DriverManager.getConnection("jdbc:mysql://localhost/java", "root", "");
								Statement stmt = con.createStatement();
								int result = stmt.executeUpdate("update allreq set mrk='d' where reqno='" + reqno + "'");
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
	}
	public void refreshTable() {
		dataList.clear();
		yourReqTable.getItems().clear();
		yourRequirements();
	}
	
    
	@FXML
	public void onClickLogout(ActionEvent event) {
		LogoutController LogoutController = new LogoutController(event);
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
