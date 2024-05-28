package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	private final static String USER = "root";
	private final static String PASSWORD = "admin";
	private final static String PORT = "3306";
	private final static String DATABASE = "mydatabase";  
    private final static String HOST = "localhost";    
	
	
	public Connection connect() {
		try {
			StringBuilder urlConnection = new StringBuilder();

	        urlConnection.append("jdbc:mysql://")
	                     .append(HOST)
	                     .append(":")
	                     .append(PORT)
	                     .append("/")
	                     .append(DATABASE)
	                     .append("?user=")
	                     .append(USER)
	                     .append("&password=")
	                     .append(PASSWORD)
	                     .append("?useSSL=false&serverTimezone=UTC");  	   
	        
			Connection connection = DriverManager.getConnection(urlConnection.toString());
			
			return connection;
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}

	}
}
