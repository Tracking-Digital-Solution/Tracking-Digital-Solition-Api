package core.monitor.services;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.rede.Rede;
import core.monitor.entidades.cpu.ColetaCpu;
import core.monitor.repositorio.Ilooca;
import static core.monitor.resources.ITemplateJdbc.con;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.time.LocalDateTime;
import java.util.List;

public class ColetaHdService implements Ilooca {

	public void executeQueryInsertColetaHd() {


		MaquinaCorporativaService maquinaCorporativaService = new MaquinaCorporativaService();
		HdDadosEstaticoservice hdDadosEstaticosService = new HdDadosEstaticoservice();

		Long longUsoAtual = looca.getMemoria().getEmUso();
		Integer usoAtual = longUsoAtual.intValue();
		Integer idMaquinaCorporativa = maquinaCorporativaService.returnExpectedIdMaquinaCorporativa();
		Integer idHdDadosEstaticos = hdDadosEstaticosService.returnExpectedIdhdDadosEstaticos(idMaquinaCorporativa);
		insertHd(
				usoAtual,
				LocalDateTime.now(),
				idMaquinaCorporativa,
				idHdDadosEstaticos
		);
		System.out.println("Insert coleta cpu: Concluído com êxito");

	}

	public void insertHd(Integer usoAtual, LocalDateTime dataHora, Integer idMaquinaCorporativa, Integer idHdDadosEstaticos) {
		con.update(
				"insert into ColetaHd " +
						"values ((?),(?),(?),(?))",
				usoAtual, dataHora, idMaquinaCorporativa, idHdDadosEstaticos
		);
	}

	private List<ColetaCpu> getAllColetaCpu() {
		return con.query(
				"select * from ColetaCpu",
				new BeanPropertyRowMapper<>(ColetaCpu.class));
	}

	@Override
	public String getIp() {
		Looca looca = new Looca();
		Rede rede = looca.getRede();
		return rede.getParametros().getServidoresDns().toString();
	}
}