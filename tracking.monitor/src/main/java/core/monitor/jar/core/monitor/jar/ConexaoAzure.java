package core.monitor.jar.core.monitor.jar;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

public class ConexaoAzure {

    private JdbcTemplate conexaoDoBancoAzure;

    //Configurando a conexão e o acesso
    public ConexaoAzure() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        dataSource.setUrl("jdbc:sqlserver://bd-tracking-digital-solution.database.windows.net:1433;database=trackingdigitalsolution;encrypt"
                + "=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net");
        dataSource.setUsername("admin-tracking-digital-solution");
        dataSource.setPassword("#Gfgrupo10");

        this.conexaoDoBancoAzure = new JdbcTemplate(dataSource);
    }

    public JdbcTemplate getConexaoDoBancoAzure() {
        return conexaoDoBancoAzure;
    }

}
