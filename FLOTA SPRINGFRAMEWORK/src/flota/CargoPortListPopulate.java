package flota;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class CargoPortListPopulate {

	@Autowired
	private  DBDataSource sqlConnection;

	private ArrayList<String> listCargo = new ArrayList();
	private String arrayListCargo[];

	private ArrayList<String> listPort = new ArrayList();
	private String arrayListPort[];
	
	private ArrayList<String> listPortDis = new ArrayList();
	private String arrayListPortDis[];
	
	
	public String[] sqlStatementCargoList() throws SQLException {
	
		Statement stmtCargo = sqlConnection.getconnection().createStatement();
		ResultSet rsCargo = stmtCargo.executeQuery("SELECT NAZWA_LADUNKU FROM LADUNEK");
		while (rsCargo.next()) {
		String sCargo = rsCargo.getString(1);
		
		
		// wyniki zapytania sa dodawane do Arraylisty
		// nastepnie array lista jest przerabiana na tablice
		// ktora nastepnie tworzy poszczegolne wiersze w JComboBox
		listCargo.add(sCargo);
		arrayListCargo = listCargo.toArray(new String[listCargo.size()]);
		sqlConnection.getconnection().close();

	
		}
		return arrayListCargo;
		
}
	
	public String[] sqlStatementPortList() throws SQLException{
		Statement stmtPort = sqlConnection.getconnection().createStatement();
		ResultSet rsPort = stmtPort.executeQuery("SELECT NAZWA_PORTU FROM PORT");
		while (rsPort.next()) {
		String sPort = rsPort.getString(1);
		
		// wyniki zapytania sa dodawane do Arraylisty
		// nastepnie array lista jest przerabiana na tablice
		// ktora nastepnie tworzy poszczegolne wiersze w JComboBox
		listPort.add(sPort);
		arrayListPort = listPort.toArray(new String[listPort.size()]);
		sqlConnection.getconnection().close();
		
		}
		return arrayListPort;
		
	}
	
	public String[] sqlStatementPortDisList() throws SQLException{
		Statement stmtPortDis = sqlConnection.getconnection().createStatement();
		ResultSet rsPortDis = stmtPortDis.executeQuery("SELECT NAZWA_PORTU FROM PORT");
		while (rsPortDis.next()) {
		String sPortDis = rsPortDis.getString(1);
		
		// wyniki zapytania sa dodawane do Arraylisty
		// nastepnie array lista jest przerabiana na tablice
		// ktora nastepnie tworzy poszczegolne wiersze w JComboBox
		listPortDis.add(sPortDis);
		arrayListPortDis = listPortDis.toArray(new String[listPortDis.size()]);
		sqlConnection.getconnection().close();
		
		
		}
		return arrayListPortDis;
		
	}
	
		
	
}
