package resources;
import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.rede.Rede;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
public interface ITemplateJdbc {
	//Conexão banco de dados
	Conexao conexao = new Conexao();
	JdbcTemplate con = conexao.getConexaoDoBanco();
}
