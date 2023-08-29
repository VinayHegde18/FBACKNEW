package controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import model.AddCountryModel;
import model.ManageUsersModel;

public class AddCountryController{
	
    @FXML
    private ChoiceBox<String> authLevelBox;
	
    ArrayList<String> list = new ArrayList<>();
    public AddCountryController() {
	
	try {
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost/java", "root", "");
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("select * from countrylist");
	
		while(rs.next())
		{
				list.add(rs.getString("countryname"));
				System.out.println(list);
		}
		authLevelBox.getItems().addAll(list);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    }
    
    public ChoiceBox<String> returnNames() {
    	return authLevelBox;
    }
//	public ObservableList<AddCountryModel> countryList = FXCollections.observableArrayList();
//	
//	private Connection con;
//	
//	public ArrayList<String> AddCountryController() {
//		
//	   ArrayList<String> list = new ArrayList<>();
//		Statement stmt;
//		try {
//			con = DriverManager.getConnection("jdbc:mysql://localhost/java", "root", "");
//			stmt = con.createStatement();
//			ResultSet rs = stmt.executeQuery("select * from countrylist");
//
//			while (rs.next()) {
//
//				countryList.add(new AddCountryModel(rs.getInt("countryid"), rs.getString("countryname")));
//			}
//			
//			for (int i = 0; i < countryList.size(); i++) {
//				  list.add(countryList.get(i).toString());
//			}
//			authLevelBox.getItems().addAll(list);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		return list;
//	}
//       
}
