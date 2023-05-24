package core.monitor.jar.core.monitor.jar;

import static core.monitor.jar.core.monitor.resources.ITemplateJdbc.con;
import static core.monitor.jar.core.monitor.resources.ITemplateJdbc.conMySQL;

import core.monitor.entidades.maquina.MaquinaCorporativa;
import core.monitor.services.MaquinaCorporativaService;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class ConexaoMySql {

    private JdbcTemplate conexaoDoBancoMySql;

    //Configurando a conexão e o acesso
    public ConexaoMySql() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/banco1?useTimezone=true&&serverTimezone=UTC");
        dataSource.setUsername("root");
        dataSource.setPassword("urubu100");

        this.conexaoDoBancoMySql = new JdbcTemplate(dataSource);
    }

    public JdbcTemplate getConexaoDoBancoMysql() {
        return conexaoDoBancoMySql;
    }

}
