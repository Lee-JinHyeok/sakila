package sakila.db;

import java.sql.*;

public class DBHelper {
	public static Connection getConnection() throws Exception{
			
		Class.forName("org.mariadb.jdbc.Driver");
		//호스팅 db
		//Connection conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/j5539538","j5539538","J1200");
		//호스트 db
		Connection conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/j5539", "root", "java1234");
		return conn;
	}
	public static void close(ResultSet rs, Statement stmt, Connection conn) {
		
		if( rs != null) {
			try {rs.close();}	catch(SQLException e) {e.printStackTrace();}
		}
		if( stmt != null) {
			try {stmt.close();}	catch(SQLException e) {e.printStackTrace();}
		}
		if( conn != null) {
			try {conn.close();}	catch(SQLException e) {e.printStackTrace();}
		}
	}
}
