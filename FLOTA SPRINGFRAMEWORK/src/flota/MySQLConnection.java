package flota;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.stereotype.Component;

@Component
public class MySQLConnection {
	/*
	//connection to MySQL database
	public Connection getconnection() throws SQLException {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		//String url = "jdbc:mysql:Flota.sql";
		String url = "jdbc:mysql://127.0.0.1:3306/flotamysql";
		String username = "root";
		String password = "aleksandra1958";

		
		return DriverManager.getConnection(url, username, password);

	}
	*/
	
	//
	//connection to SQlite database
	public Connection getconnection() throws SQLException{
		
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	
		String url = "jdbc:sqlite:Flota.db";
	
		
		return DriverManager.getConnection(url);

	
	
	}

}
