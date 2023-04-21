package core.monitor.resources;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author consultor
 */
public class ConexaoService {
	private JdbcTemplate conexaoDoBanco;

	public ConexaoService() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/trackingdigitalsolution");
		dataSource.setUsername("grupo10user");
		dataSource.setPassword("grupo10user");
		this.conexaoDoBanco = new JdbcTemplate(dataSource);
	}

	public JdbcTemplate getConexaoDoBanco() {
		return conexaoDoBanco;
	}

	public Connection driverManager() throws SQLException {
		return DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/trackingdigitalsolution",
				"grupo10user",
				"grupo10user");
	}
}