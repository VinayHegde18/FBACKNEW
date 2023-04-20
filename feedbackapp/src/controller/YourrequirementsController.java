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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import model.ManageUsersModel;
import model.YourRequirementsModel;

public class YourrequirementsController implements Initializable {

	@FXML
	private TableView<YourRequirementsModel> yourReqTable;
	@FXML
	private TableColumn<YourRequirementsModel, Integer> reqno;
	@FXML
	private TableColumn<YourRequirementsModel, String> allReq;
	@FXML
	private TableColumn actionCol;

	private ObservableList<YourRequirementsModel> dataList = FXCollections.observableArrayList();

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		controller controller = new controller();
		String uname = controller.uname;
		Connection con;
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost/java", "root", "");
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("select * from allreq where mrk is null and username='" + uname + "'");

			while (rs.next()) {

				dataList.add(new YourRequirementsModel(rs.getInt("reqno"), rs.getString("req")));

			}
			yourReqTable.setItems(dataList);

		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println(String.format("Error: %s", e.getMessage()));
		}
		reqno.setCellValueFactory(new PropertyValueFactory<YourRequirementsModel, Integer>("reqno"));
		allReq.setCellValueFactory(new PropertyValueFactory<YourRequirementsModel, String>("req"));

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
		initialize(null,null);
	}
}