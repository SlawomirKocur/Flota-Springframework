package flota;

import java.sql.SQLException;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AspectChoice {

	@Autowired
	MySQLConnection connection;
	
	@Pointcut("execution(*flota.CargoPortListPopulate.sqlStatement(..)")
	
	
	@AfterReturning("sqlStatement()")
	public void connCloseCargo() throws SQLException {
	
		
		connection.getconnection().close();
		System.out.println("po³¹czenie zamkniete");
	}
	
	
	
	
	@Pointcut("execution(*flota.PortListPopulate.sqlStatement(..)")
	@AfterReturning("sqlStatement()")
		public void connClosePort() throws SQLException {
			
			
			connection.getconnection().close();
			System.out.println("po³¹czenie zamkniete");
		}
	}
	

