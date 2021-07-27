package flota;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;

import javax.swing.JOptionPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Summary {

	@Autowired
	MainGUIWindow window;
	@Autowired
	DBDataSource sqlConnection;
	
	private String wybranyLadunekWBTN;
	private String wybranyPort;
	private String wybranyPortWyladunkowy;
	private String iloscString;
	
	private Double iloscDouble;
	private Double ilosc;
	
	private Date dataZaladunku;
	private Date dataWyladunku;
	private Double wartoscZamowienia;
	private String wartosZamowieniString;
	
	double lat1;
	double lon1;
	double lat2;
	double lon2;
	double dist;
	
	public void summary() {
	
	wybranyLadunekWBTN = (String) window.comboBoxLadunek.getSelectedItem();
	wybranyPort = (String) window.comboBoxPort.getSelectedItem();
	wybranyPortWyladunkowy = (String) window.comboBoxPortWyladunkowy.getSelectedItem();
	iloscString = window.textIlosc.getText();
	iloscDouble = Double.parseDouble(iloscString);
	ilosc = Double.parseDouble(window.textIlosc.getText());
	
	
	dataZaladunku = window.dateChooserZaladunek.getDate();
	dataWyladunku = window.dateChooserWyladunek.getDate();

	String podsumowanieLadunek = wybranyLadunekWBTN;
	String podsumowaniePort = wybranyPort;
	String podsumowanieWyladunkowy = wybranyPortWyladunkowy;
	String podsumowanieIlosc = window.textIlosc.getText();
	String podsumowanieDataZa = dataZaladunku.toString();
	String podsumowanieDataWy = dataWyladunku.toString();

	window.lblZamowienieLadunek.setText(podsumowanieLadunek);
	window.lblZamowieniePort.setText(podsumowaniePort);
	window.lblZamowieniePWyladunkowy.setText(podsumowanieWyladunkowy);
	window.lblZamowienieIlosc.setText(podsumowanieIlosc);
	window.lblZamowienieDataZal.setText(podsumowanieDataZa);
	window.lblZamowienieDataWy.setText(podsumowanieDataWy);

	Double wartoscZam = Double.parseDouble(podsumowanieIlosc);

	Double cenaDouble;
	


	try {

		

		Statement stat = sqlConnection.getconnection().createStatement();

		String command = ("Select CENA_ZA_TONE_USD FROM LADUNEK WHERE NAZWA_LADUNKU = '"
				+ wybranyLadunekWBTN + "'");
		
		ResultSet rs = stat.executeQuery(command);

		while (rs.next()) {
			cenaDouble = rs.getDouble(1);
			wartoscZamowienia = cenaDouble * ilosc;
			wartosZamowieniString = String.valueOf(wartoscZamowienia);
			sqlConnection.getconnection().close();
		}

	} catch (Exception e3) {
		JOptionPane.showMessageDialog(null, "Problem with connection of database");

	}



	try {

		// pobiera szerokosc geograficzna z bazydanych
		// nastepnie przypisuje do zmiennej
		// conn = DriverManager.getConnection("jdbc:sqlite:C:\\JAVA\\Moje bazy
		// danych\\Flota.db");
		

		Statement stat = sqlConnection.getconnection().createStatement();
		String pobierzSzerokoscPortZaladunkowy = ("Select SZEROKOSC_GEOGRAFICZNA FROM PORT WHERE NAZWA_PORTU = '"
				+ wybranyPort + "'");
		ResultSet rsszer = stat.executeQuery(pobierzSzerokoscPortZaladunkowy);
		while (rsszer.next()) {
			lat1 = rsszer.getDouble(1);
		}

		// pobiera dlugosc geograficzna z bazy danycg
		// nastepnie przypisuje do zmiennej
		Statement stat2 = sqlConnection.getconnection().createStatement();
		String pobierzDlugoscPortZaladunkowy = ("Select DLUGOSC_GEOGRAFICZNA FROM PORT WHERE NAZWA_PORTU = '"
				+ wybranyPort + "'");
		ResultSet rsdl = stat.executeQuery(pobierzDlugoscPortZaladunkowy);
		while (rsdl.next()) {
			lon1 = rsdl.getDouble(1);
		}

		// pobiera szerokosc geograficzna z bazydanych
		// nastepnie przypisuje do zmiennej
		Statement stat3 = sqlConnection.getconnection().createStatement();
		String pobierzSzerokoscPortWyladunkowy = ("Select SZEROKOSC_GEOGRAFICZNA FROM PORT WHERE NAZWA_PORTU = '"
				+ wybranyPortWyladunkowy + "'");
		ResultSet rsszer2 = stat.executeQuery(pobierzSzerokoscPortWyladunkowy);
		while (rsszer2.next()) {
			lat2 = rsszer2.getDouble(1);
		}

		// pobiera dlugosc geograficzna z bazy danycg
		// nastepnie przypisuje do zmiennej
		Statement stat4 = sqlConnection.getconnection().createStatement();
		String pobierzDlugoscPortWyladunkowy = ("Select DLUGOSC_GEOGRAFICZNA FROM PORT WHERE NAZWA_PORTU = '"
				+ wybranyPortWyladunkowy + "'");
		ResultSet rsdl2 = stat.executeQuery(pobierzDlugoscPortWyladunkowy);
		while (rsdl2.next()) {
			lon2 = rsdl2.getDouble(1);
		}

		// zamyka polaczenie z baza danych
		sqlConnection.getconnection().close();

	} catch (Exception e4) {
		JOptionPane.showMessageDialog(null, "Problem with connection of database");
	}

	double theta = lon1 - lon2;
	dist = Math.sin(Math.toRadians(lat1)) * Math.sin(Math.toRadians(lat2)) + Math.cos(Math.toRadians(lat1))
			* Math.cos(Math.toRadians(lat2)) * Math.cos(Math.toRadians(theta));
	dist = Math.acos(dist);
	dist = Math.toDegrees(dist);

	dist = dist * 0.8684;

	window.lblWartoscZamowienia.setText("Wartoœæ zamówienia wynosi: " + wartosZamowieniString + " USD"
			+ "\n Dystans do przep³yniêcia: " + dist);

}
	
}
	
	
	
	

