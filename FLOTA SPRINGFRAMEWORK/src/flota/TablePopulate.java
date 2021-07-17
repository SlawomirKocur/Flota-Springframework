package flota;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.proteanit.sql.DbUtils;

@Component
public class TablePopulate {

	@Autowired
	private MySQLConnection sqlConnection;
	@Autowired
	MainGUIWindow window;

	public void getShipLost() throws SQLException {

		String query = "Select* From STATEK";
		PreparedStatement stmtShip = sqlConnection.getconnection().prepareStatement(query);

		ResultSet rs = stmtShip.executeQuery();

		window.table.setModel(DbUtils.resultSetToTableModel(rs));

	}

	public void getCargoList() throws SQLException {

		String query = "Select* From LADUNEK";
		PreparedStatement stmtCargo = sqlConnection.getconnection().prepareStatement(query);

		ResultSet rs = stmtCargo.executeQuery();

		window.table.setModel(DbUtils.resultSetToTableModel(rs));

	}

	public void getPortList() throws SQLException {

		String query = "Select* From PORT";
		PreparedStatement stmtCargo = sqlConnection.getconnection().prepareStatement(query);

		ResultSet rs = stmtCargo.executeQuery();

		window.table.setModel(DbUtils.resultSetToTableModel(rs));

	}

}
