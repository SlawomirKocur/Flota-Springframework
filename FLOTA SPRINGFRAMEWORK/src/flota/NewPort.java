package flota;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NewPort {

	@Autowired
	private MySQLConnection sqlConnection;
	@Autowired
	MainGUIWindow window;

	public void setNewPort() throws SQLException {

	
		String polecenieSql = ("INSERT INTO PORT(NAZWA_PORTU, KRAJ, KRAJ_PELNA_NAZWA, SZEROKOSC_GEOGRAFICZNA, DLUGOSC_GEOGRAFICZNA) VALUES(?,?,?,?,?)");

		try {

			PreparedStatement stmt = sqlConnection.getconnection().prepareStatement(polecenieSql);
			stmt.setString(1, window.textFieldnazwaNowegoPortuNazwa.getText());
			stmt.setString(2, window.textFieldnazwaNowegoPortuKraj.getText());
			stmt.setString(3, window.textFieldnazwaNowegoPortuPNazwa.getText());
			stmt.setString(4, window.textFieldnazwaNowegoPortuSzerokosc.getText());
			stmt.setString(5, window.textFieldnazwaNowegoPortuDlugosc.getText());

			stmt.executeUpdate();
			stmt.close();
			// sqlConnection.getconnection().close();
			JOptionPane.showMessageDialog(null, "Dodano nowy port do bazy danych");

		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "B³¹d, nie dodano portu do bazy");

		}

	}
}
