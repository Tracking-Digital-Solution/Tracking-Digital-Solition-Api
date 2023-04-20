package core.monitor.resources;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author consultor
 */
public class Conexao {
	private JdbcTemplate conexaoDoBanco;

	public Conexao() {
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

}