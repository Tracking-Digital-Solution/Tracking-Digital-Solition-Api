package core.monitor.resources;
import org.springframework.jdbc.core.JdbcTemplate;

public interface ITemplateJdbc {
	//Conexão banco de dados
	ConexaoService conexao = new ConexaoService();
	JdbcTemplate con = conexao.getConexaoDoBanco();
}
