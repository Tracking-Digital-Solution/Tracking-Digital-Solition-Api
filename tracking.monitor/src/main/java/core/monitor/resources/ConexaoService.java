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

    //Configurando a conexão e o acesso

    /**
     *
     */
    public ConexaoService() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        dataSource.setUrl("jdbc:sqlserver://bd-tracking-digital-solution.database.windows.net:1433;database=trackingdigitalsolution;encrypt"
                + "=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net");
        dataSource.setUsername("admin-tracking-digital-solution");
        dataSource.setPassword("#Gfgrupo10");

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

