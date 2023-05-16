package core.monitor.jar.core.monitor.resources;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author consultor
 */
public class ConexaoServiceMySQL {

   private JdbcTemplate conexaoDoBancoMySql;

    //Configurando a conexão e o acesso

    /**
     *
     */
    public ConexaoServiceMySQL() {
      BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/banco1?useTimezone=true&&serverTimezone=UTC");
        dataSource.setUsername("root");
        dataSource.setPassword("urubu100");

        this.conexaoDoBancoMySql = new JdbcTemplate(dataSource);

    }



    public JdbcTemplate getConexaoDoBanco() {
        return conexaoDoBancoMySql;
    }

    public Connection driverManager() throws SQLException {
        return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/trackingdigitalsolution",
                "grupo10user",
                "grupo10user");
    }
}

