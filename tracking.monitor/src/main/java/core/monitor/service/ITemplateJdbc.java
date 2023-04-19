package core.monitor.service;

import org.springframework.jdbc.core.JdbcTemplate;

public interface ITemplateJdbc {
	Conexao conexao = new Conexao();
	JdbcTemplate con = conexao.getConexaoDoBanco();
}
