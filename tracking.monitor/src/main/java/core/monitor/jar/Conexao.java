package login.jar;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import com.microsoft.sqlserver.jdbc.SQLServerDriver;

public class Conexao {

    private JdbcTemplate conexaoDoBanco;

    //Configurando a conexão e o acesso
    public Conexao() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        dataSource​.setUrl("jdbc:sqlserver://bd-tracking-digital-solution.database.windows.net:1433;database=trackingdigitalsolution;encrypt"
                + "=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net");
        dataSource​.setUsername("admin-tracking-digital-solution");
        dataSource​.setPassword("#Gfgrupo10");

        this.conexaoDoBanco = new JdbcTemplate(dataSource);
    }

    public JdbcTemplate getConexaoDoBanco() {
        return conexaoDoBanco;
    }
}
