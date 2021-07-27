/*
 * This class initialize autoscanning for components
 *
 */

package flota;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@EnableAspectJAutoProxy
@Configuration
@ComponentScan(basePackages = "flota")
public class FlotaConfig {
/*
	@Bean
	public DataSource mysqlDataSource() {
	DriverManagerDataSource datasource = new DriverManagerDataSource();
	datasource.setDriverClassName("com.mysql.jdbc.cj.Driver");
	datasource.setUrl("jdbc:mysql:FlotaMySQL.sql");
	datasource.setUsername("root");
	datasource.setPassword("aleksandra1958");
	return datasource;
	}
	*/
}
