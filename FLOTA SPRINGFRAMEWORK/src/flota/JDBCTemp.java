package flota;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
public class JDBCTemp {

	private DataBaseConnection DBConn = new DataBaseConnection();

	private JdbcTemplate jdbcTemplate = new JdbcTemplate(DBConn.dataSource());

	public void insert() {

		String sql = "INSERT INTO port (NAZWA_PORTU, KRAJ )VALUES ('HAVANA', 'CUBA')";
		jdbcTemplate.update(sql);

	}
}
