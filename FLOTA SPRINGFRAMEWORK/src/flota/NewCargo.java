package flota;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NewCargo {

	@Autowired
	MainGUIWindow window;
	@Autowired
	MySQLConnection sqlConnection;
	/*
	 * czesc kodu obslugujaca dodawanie nowego ladunku do bazy danych
	 * 
	 * 
	 * 
	 */

	public void setNewCargo() throws SQLException {
		//sqlConnection.getconnection();
		String polecenieSql = ("INSERT INTO LADUNEK(NAZWA_LADUNKU,   CENA_ZA_TONE_USD, OBJETOSC_TONY, GRAIN_STANDARD, UWAGI_DOT_LADUNKU) VALUES(?,?,?,?,?)");
		try {

			// polecenie dla bazy danych

			PreparedStatement stmt = sqlConnection.getconnection().prepareStatement(polecenieSql);

			stmt.setString(1, window.textNazwaLadunku.getText());
			stmt.setDouble(2, Double.parseDouble(window.textCenaLadunku.getText()));
			stmt.setDouble(3, Double.parseDouble(window.textCenaLadunku.getText()));
			stmt.setString(4, window.grainStandard.getSelectedItem());
			stmt.setString(5, window.textUwagi.getText());

			stmt.executeUpdate();
			stmt.close();
			sqlConnection.getconnection().close();

			JOptionPane.showMessageDialog(null, "Dodano nowy typ ³adunku do bazy danych");

		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "B³¹d, nie dodano nowego typu ³adunku do bazy");

		}

	}

}
