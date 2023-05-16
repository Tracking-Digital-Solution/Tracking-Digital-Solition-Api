package core.monitor.jar.core.monitor.jar;

import static core.monitor.jar.core.monitor.resources.ITemplateJdbc.conMySQL;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class ConexaoMySql {

    private JdbcTemplate conexaoDoBancoMySql;

    //Configurando a conexão e o acesso
    public ConexaoMySql() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/banco1?useTimezone=true&&serverTimezone=UTC");
        dataSource.setUsername("root");
        dataSource.setPassword("urubu100");

        this.conexaoDoBancoMySql = new JdbcTemplate(dataSource);
    }
    
    public Boolean validationLogin(String email, String senha) {
        try {
          conMySQL.queryForObject(String.format("select * from perfil where email = '%s' and senha = '%s'", email, senha),
                    new BeanPropertyRowMapper<>(Usuario.class));
        } catch (DataAccessException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    public JdbcTemplate getConexaoDoBancoMysql() {
        return conexaoDoBancoMySql;
    }

}
