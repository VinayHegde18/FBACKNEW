package controller;


import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import dbcon.DbCon;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.AllRequirementsModel;

public class AllRequirementsController{


	@FXML
	private TableView<AllRequirementsModel> allReqTable;

	@FXML
	private TableColumn<AllRequirementsModel, Integer> reqno;

	@FXML
	private TableColumn<AllRequirementsModel, String> allReq;
	
	public ObservableList<AllRequirementsModel> dataList = FXCollections.observableArrayList();

public void getUserProfileDetails() {
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

}
