package core.monitor.resources;
import org.springframework.jdbc.core.JdbcTemplate;

public interface ITemplateJdbc {
	//Conexão banco de dados
	Conexao conexao = new Conexao();
	JdbcTemplate con = conexao.getConexaoDoBanco();
}
