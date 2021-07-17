package flota;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.stereotype.Component;

@Component
public class MySQLConnection {

	//public Connection conn = null;

	public Connection getconnection() throws SQLException {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		String url = "jdbc:mysql://127.0.0.1:3306/flotamysql";
		String username = "root";
		String password = "aleksandra1958";

		
		return DriverManager.getConnection(url, username, password);

	}
	/*public void closeConnection() throws SQLException {
		conn.close();
		
	}
*/
}
