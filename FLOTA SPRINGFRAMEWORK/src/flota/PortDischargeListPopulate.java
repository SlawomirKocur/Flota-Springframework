package flota;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PortDischargeListPopulate {

	@Autowired
	private  MySQLConnection sqlConnection;

	static ArrayList<String> listPort = new ArrayList();
	static String arrayListPortDischarge[];

	public String[] sqlStatement() throws SQLException {
	
		Statement stmtLadunek = sqlConnection.getconnection().createStatement();
		ResultSet rsLadunek = stmtLadunek.executeQuery("SELECT NAZWA_PORTU FROM PORT");
		while (rsLadunek.next()) {
		String sLadunek = rsLadunek.getString(1);
		
		// wyniki zapytania sa dodawane do Arraylisty
		// nastepnie array lista jest przerabiana na tablice
		// ktora nastepnie tworzy poszczegolne wiersze w JComboBox
		listPort.add(sLadunek);
		arrayListPortDischarge = listPort.toArray(new String[listPort.size()]);
		
		}
		return arrayListPortDischarge;
		
	}
}
