package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	private final static String USER = "root";
	private final static String PASSWORD = "admin";
	private final static String PORT = "3306";
	private final static String DATABASE = "starlink_db";  
    private final static String HOST = "localhost";    
	
	
	public Connection getConnection() {
		try {
			String baseURL = "jdbc:mysql://" + HOST + ':' + PORT + "/" + DATABASE;
			
			System.out.println("URL: " + baseURL);
	        
			Class.forName("com.mysql.cj.jdbc.Driver"); 
			
			Connection connection = DriverManager.getConnection(baseURL, USER, PASSWORD);
			
			return connection;
		} catch(SQLException e) {
			throw new RuntimeException(e);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}

	}
}
