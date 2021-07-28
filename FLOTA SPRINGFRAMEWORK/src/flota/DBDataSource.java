package flota;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.stereotype.Component;

@Component
public class DBDataSource {
	/*
	//connection to MySQL database
	public Connection getconnection() throws SQLException {

		try {
			Class.forName("com.mysql.jdbc.cj.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		String url = "jdbc:mysql:FlotaMySQL.sql";
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
