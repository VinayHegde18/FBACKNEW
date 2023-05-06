package dbcon;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DbCon {
    public Connection con;
    private static DbCon dbc;
    public DbCon() {
        try {
        	con = DriverManager.getConnection("jdbc:mysql://localhost/java", "root", "");
        } catch (SQLException ex) {
            Logger.getLogger(DbCon.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static DbCon getDatabaseConnection() {
        if (dbc == null) {
            dbc = new DbCon();
        }
        return dbc;
    }
    
    public Connection getConnection() {
        return con;
    }
    
    public static void main(String[] args) {
        new DbCon();
    }
}




//import java.security.PublicKey;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//import java.sql.Statement;
//
//public class DbCon {
//	
//	private static Connection con;
//	public DbCon() {
//		 try {
//                con = DriverManager.getConnection("jdbc:mysql://localhost/java", "root", "");
//				Statement stmt = con.createStatement();
//
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		 	 
//	}
//	public Connection dbCon() {
//
//			return con;
//	}
//}
