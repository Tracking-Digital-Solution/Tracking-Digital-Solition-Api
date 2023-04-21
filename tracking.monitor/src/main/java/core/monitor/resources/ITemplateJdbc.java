package core.monitor.resources;
import org.springframework.jdbc.core.JdbcTemplate;

public interface ITemplateJdbc {
	//Conexão banco de dados
	ConexaoService CONEXAO_SERVICE = new ConexaoService();
	JdbcTemplate con = CONEXAO_SERVICE.getConexaoDoBanco();
}
