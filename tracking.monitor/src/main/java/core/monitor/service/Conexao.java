package core.monitor.service;

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
        
//        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
//        
//        dataSource.setUrl("jdbc:mysql://localhost:3306/tecflix"); // trocar o localhost:3306 pelo endereço do banco e o tecflix pelo nome do banco
//        
//        dataSource.setUsername("root"); //Usuario do banco
//        
//        dataSource.setPassword("1234"); //Senha do banco

        this.conexaoDoBanco = new JdbcTemplate(dataSource);
    }

    public JdbcTemplate getConexaoDoBanco() {
        return conexaoDoBanco;
    }

}
