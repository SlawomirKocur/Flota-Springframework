package flota;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.proteanit.sql.DbUtils;

@Component
public class Analyze {
	
	@Autowired
	MainGUIWindow window;
	@Autowired
	DBDataSource sqlConnection;
	@Autowired
	Summary summary;
	
	
	
	
	
	Date dostepnosc = null;
	private double priceDouble;
	
	/*
	 * wybranyLadunekWBTN = (String) comboBoxLadunek.getSelectedItem(); wybranyPort
	 * = (String) comboBoxPort.getSelectedItem(); wybranyPortWyladunkowy = (String)
	 * comboBoxPortWyladunkowy.getSelectedItem(); String iloscString =
	 * textIlosc.getText(); iloscDouble = Double.parseDouble(iloscString); ilosc =
	 * Double.parseDouble(textIlosc.getText()); dataZaladunku =
	 * dateChooserZaladunek.getDate(); dataWyladunku =
	 * dateChooserWyladunek.getDate();
	 * 
	 * SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
	 * 
	 * String dataDostepnosci = format.format(new Date());
	 * 
	 * /* SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	 * 
	 * dateFormat.format(new Date())
	 * 
	 */

	public void provideAnalyze() {
	
	String wybranyLadunekWBTN = (String) window.comboBoxLadunek.getSelectedItem();
	
	SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

	String sd = dateFormat.format(window.dateChooserZaladunek.getDate());

	Double ilosc = Double.parseDouble(window.textIlosc.getText());

	try {
		
		
		Statement stat = sqlConnection.getconnection().createStatement();
		String command1 = ("Select CENA_ZA_TONE_USD FROM LADUNEK WHERE NAZWA_LADUNKU = '"
				+ wybranyLadunekWBTN + "'");
		ResultSet rs = stat.executeQuery(command1);
		while (rs.next()) {
			priceDouble = rs.getDouble(1);

		
		}

		double cenaDouble = priceDouble;
		
		/*
		 * Statement statDostepnosc = connAnalys.createStatement(); String command2 =
		 * ("Select SZCOWANA_DATA_DOSTEPNOSCI FROM STATEK;"); ResultSet rsDost =
		 * statDostepnosc.executeQuery(command2); while(rsDost.next()) { dostepnosc =
		 * rs.getDate(1);
		 * 
		 * }
		 */
		String query = ("select \r\n" + "NAZWA_STATKU, '" + ilosc + "' *'" + cenaDouble
				+ "'as ZYSK_BRUTTO,\r\n" + "DOBOWY_KOSZY_PALIWA_USD *'" + summary.dist + "'as KOSZT,\r\n"
				+ "DOBOWY_KOSZY_PALIWA_USD + LADOWNOSC_STATKU_DWT *'" + cenaDouble + "'as ZYSK_NETTO,\r\n"
				+ "SZCOWANA_DATA_DOSTEPNOSCI\r\n" + "\r\n" + "from STATEK\r\n" + "  \r\n"
				+ "where LADOWNOSC_STATKU_DWT >= '" + ilosc + "'  and SZCOWANA_DATA_DOSTEPNOSCI <= '" + sd
				+ "' \r\n" + "\r\n" + "ORDER BY ZYSK_NETTO DESC;");
		PreparedStatement pst = sqlConnection.getconnection().prepareStatement(query);
		ResultSet rs2 = pst.executeQuery();
		window.table.setModel(DbUtils.resultSetToTableModel(rs2));
		sqlConnection.getconnection().close();
		/*
		 * WORKING SQL STATMENT AS EXAMPLE select NAZWA_STATKU, LADOWNOSC_STATKU_DWT
		 * *2000 as ZYSK_BRUTTO, DOBOWY_KOSZY_PALIWA_USD * 3 as KOSZT,
		 * DOBOWY_KOSZY_PALIWA_USD + LADOWNOSC_STATKU_DWT * 2000 as ZYSK_NETTO,
		 * SZCOWANA_DATA_DOSTEPNOSCI
		 * 
		 * from STATEK
		 * 
		 * where LADOWNOSC_STATKU_DWT >=30000 and SZCOWANA_DATA_DOSTEPNOSCI <=
		 * '11/07/2021'
		 * 
		 * ORDER BY ZYSK_NETTO DESC;
		 * 
		 */

		/*
		 * String query = "select NAZWA_STATKU, LADOWNOSC_STATKU_DWT; PreparedStatement
		 * pst = connAnalys.prepareStatement(query); ResultSet rs = pst.executeQuery();
		 * table.setModel(DbUtils.resultSetToTableModel(rs));
		 * 
		 */

	} catch (Exception ex) {
		JOptionPane.showMessageDialog(null, "B³¹d po³¹czenia z baz¹");

	}
	
	}
	

	

}
