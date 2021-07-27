package flota;


import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NewShip {

	/*
	 * ¯utowanie wartosc Date wybranej w JDateChooser na format String oraz
	 * pozostalych wartosci wybranych w panelu "nowy statek ze String na Double aby
	 * w nbazie danych wpisane wartosci reprezentowaly wartosci liczbowe"
	 * 
	 */
	@Autowired
	MainGUIWindow window;
	@Autowired
	DBDataSource sqlConnection;

	public void setNewShip() throws SQLException {

		
		String polecenieSql = ("INSERT INTO STATEK(NAZWA_STATKU,   LADOWNOSC_STATKU_DWT, LADOWNOSC_STATKU_OBJETOSC_M3, SZCOWANA_DATA_DOSTEPNOSCI, DOBOWY_KOSZY_PALIWA_USD) VALUES(?,?,?,?,?)");
		try {

			PreparedStatement stmt = sqlConnection.getconnection().prepareStatement(polecenieSql);

			stmt.setString(1, window.textNazwaNowegoStatku.getText());
			stmt.setDouble(2, Double.parseDouble(window.textLadownoscStatku.getText()));
			stmt.setDouble(3, Double.parseDouble(window.textObjetoscStatku.getText()));
			stmt.setString(4, (new java.text.SimpleDateFormat("dd-MM-yyyy")).format(window.dateChooser.getDate()));
			stmt.setDouble(5, Double.parseDouble(window.textKoszt.getText()));

			stmt.executeUpdate();
			stmt.close();
			sqlConnection.getconnection().close();

			JOptionPane.showMessageDialog(null, "Dodano nowy statek do bazy danych");

		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "B³¹d, nie dodano statku do bazy\nWprowadz wszystkie dane");

		}
	}

}
