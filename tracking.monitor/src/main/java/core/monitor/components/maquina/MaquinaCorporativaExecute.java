package core.monitor.components.maquina;

import core.monitor.service.Conexao;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class MaquinaCorporativaExecute {
	protected void executeQuery(String ip, String sistemaOperacional, String nomeMaquina) {
		Conexao conexao = new Conexao();
		JdbcTemplate con = conexao.getConexaoDoBanco();

//		System.out.println(validationMachine());
		try {
			if (validationMachine()) {
				con.update("insert into MaquinaCorporativa(IP,sistemaOperacional,nomeMaquina) " +
								"values " +
								"(?,?,?)",
						ip, sistemaOperacional, nomeMaquina);
				System.out.println("Máquina Cadastrada com sucesso");
			} else {
				throw new RuntimeException();
			}
		} catch (RuntimeException e) {
			throw new RuntimeException("Erro ao Cadastrar Máquina: Ip já existente");
		}
	}

	private Boolean validationMachine() {
		Conexao conexao = new Conexao();
		JdbcTemplate con = conexao.getConexaoDoBanco();

		List<MaquinaCorporativa> listaMaquina = con.query("select (IP) from MaquinaCorporativa",
				new BeanPropertyRowMapper<>(MaquinaCorporativa.class));
		for (int i = 0; i < listaMaquina.size(); i++) {
			if (listaMaquina.contains(listaMaquina.get(i))) {
				return false;
			}
		}
		return true;
	}
}

