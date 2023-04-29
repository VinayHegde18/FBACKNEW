package dbcon;

import java.security.PublicKey;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DbCon {
	
	private static Connection con;
	public DbCon() {
		 try {
                con = DriverManager.getConnection("jdbc:mysql://localhost/java", "root", "");
				Statement stmt = con.createStatement();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 	 
	}
	public Connection dbCon() {

			return con;
	}
}
