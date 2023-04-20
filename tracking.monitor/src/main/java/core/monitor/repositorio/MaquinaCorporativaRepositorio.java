package core.monitor.repositorio;

import core.monitor.entidades.maquina.MaquinaCorporativa;
import core.monitor.resources.Ilooca;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.util.List;

public class MaquinaCorporativaRepositorio implements Ilooca{

	public void executeQueryUpdateMaquinaCorporativa(MaquinaCorporativa maquinaCorporativa) {
		if (!validationStaticValuesCpu()) {
			String test = maquinaCorporativa.getNomeMaquina().substring(0 ,40);
			con.update(
					"update MaquinaCorporativa set SistemaOperacional = (?),nomeMaquina = (?) where ip = (?)",
					maquinaCorporativa.getSistemaOperacional(), test, maquinaCorporativa.getIp()
			);
			System.out.println("Insert de coleta de dados CPU concluidos com êxito");
		}

	}

	private Boolean validationStaticValuesCpu() {
		List<MaquinaCorporativa> listaIdMaquinaCorporativa = con.query("select idMaquinaCorporativa from MaquinaCorporativa where ip = '"+ip+"'",
				new BeanPropertyRowMapper<>(MaquinaCorporativa.class));
		return listaIdMaquinaCorporativa.isEmpty();


	}
}
