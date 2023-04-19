package core.monitor.components;

import core.monitor.service.Conexao;
import org.springframework.jdbc.core.JdbcTemplate;

public interface Conection {
	Conexao conexao = new Conexao();
	JdbcTemplate con = conexao.getConexaoDoBanco();
}
