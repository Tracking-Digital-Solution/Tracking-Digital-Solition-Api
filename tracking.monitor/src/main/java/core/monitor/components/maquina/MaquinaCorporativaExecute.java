package core.monitor.components.maquina;

import core.monitor.service.ITemplateJdbc;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.util.List;

public class MaquinaCorporativaExecute implements ITemplateJdbc {
	protected void executeQuery(String ip, String sistemaOperacional, String nomeMaquina) {
//		System.out.println(validationMachine());
		try {
			if (!(validationMachine())) {
				con.update("update sistemaOperacional SET (?) where ip = ?",
						sistemaOperacional, ip);
				con.update("update nomeMaquina SET (?) where ip = ?",
						nomeMaquina, ip);

				System.out.println(
						String.format(
								"Máquina Corporativa: %s\n" +
										"Ip: %s\n" +
										"Sistema operacional: %s\n",
								nomeMaquina, ip, sistemaOperacional)
				);
			} else {
				throw new RuntimeException();
			}
		} catch (RuntimeException e) {
			throw new RuntimeException("Erro ao Cadastrar Máquina: Ip já existente");
		}
	}

	private Boolean validationMachine() {
		List<MaquinaCorporativa> listaMaquina = con.query("select (IP) from MaquinaCorporativa",
				new BeanPropertyRowMapper<>(MaquinaCorporativa.class));
		for (int i = 0; i < listaMaquina.size(); i++) {
			if (listaMaquina.contains(listaMaquina.get(i))) {
				return true;
			}
		}
		return false;
	}
}

