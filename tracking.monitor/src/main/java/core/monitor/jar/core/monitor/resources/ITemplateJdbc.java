package core.monitor.jar.core.monitor.resources;
import core.monitor.resources.ConexaoService;
import core.monitor.jar.core.monitor.resources.ITemplateJdbc;
import org.springframework.jdbc.core.JdbcTemplate;

public interface ITemplateJdbc {
	//Conexão banco de dados
	ConexaoService CONEXAO_SERVICE = new ConexaoService();
	JdbcTemplate con = CONEXAO_SERVICE.getConexaoDoBanco();
	ConexaoServiceMySQL CONEXAO_SERVICE_MYSQL = new ConexaoServiceMySQL();
	JdbcTemplate conMySQL = CONEXAO_SERVICE_MYSQL.getConexaoDoBanco();
}
