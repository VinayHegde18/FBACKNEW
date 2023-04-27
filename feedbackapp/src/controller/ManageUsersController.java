package controller;

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
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import model.AllRequirementsModel;
import model.ManageUsersModel;

public class ManageUsersController implements Initializable {

	@FXML
	private TableView<ManageUsersModel> manageUsersTable;

	@FXML
	private TableColumn<ManageUsersModel, Integer> slnoColumn;

	@FXML
	private TableColumn<ManageUsersModel, String> fullNameColumn;

	@FXML
	private TableColumn<ManageUsersModel, String> userNameColumn;

	@FXML
	private TableColumn<ManageUsersModel, String> emailIdColumn;

	@FXML
	private TableColumn actionColumn;

	private ObservableList<ManageUsersModel> dataList = FXCollections.observableArrayList();

	@Override
	public void initialize(URL url, ResourceBundle rb) {
 
		Connection con;
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost/java", "root", "");
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("select * from users where del is null");

			while (rs.next()) {

				dataList.add(new ManageUsersModel(rs.getInt("userid"), rs.getString("name"), rs.getString("username"),
						rs.getString("email")));
			}
			manageUsersTable.setItems(dataList);

		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println(String.format("Error: %s", e.getMessage()));
		}
		slnoColumn.setCellValueFactory(new PropertyValueFactory<ManageUsersModel, Integer>("slno"));
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
							int userId = m.getSlno();
							
							if(userId!=1) {
							
							Alert alert = new Alert(AlertType.CONFIRMATION);
							alert.setContentText("Are You sure You want to delete this user?");
							Optional<ButtonType> option = alert.showAndWait();
							if (option.get().equals(ButtonType.OK)) {
								Connection con;
								try {
									con = DriverManager.getConnection("jdbc:mysql://localhost/java", "root", "");
									Statement stmt = con.createStatement();
									int result = stmt.executeUpdate("update users set del='d' where userid='" + userId + "'");
									if (result == 1) {
										Alert newAlert = new Alert(Alert.AlertType.INFORMATION);
										newAlert.setContentText("deleted user");
										newAlert.show();
										refreshTable();
									}
								} catch (SQLException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
							}
							else {
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
	
	public void refreshTable() {
		dataList.clear();
		ManageUsersController mmController = new ManageUsersController();
	}
}
