package core.monitor.services.hd;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.rede.Rede;
import core.monitor.entidades.cpu.ColetaCpu;
import core.monitor.repositorio.Ilooca;

import core.monitor.services.MaquinaCorporativaService;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;

public class ColetaHdService implements Ilooca {

	public void executeQueryInsertColetaHd() {
		MaquinaCorporativaService maquinaCorporativaService = new MaquinaCorporativaService();
		Long longDisponivelHd = (discoGrupo.getVolumes().get(0).getDisponivel());
		Integer idMaquinaCorporativa = maquinaCorporativaService.returnExpectedIdMaquinaCorporativa();
		Integer idHdDadosEstaticos = idMaquinaCorporativa;

		insertColetaHdDinamico(
				longDisponivelHd,
				LocalDateTime.now(),
				idMaquinaCorporativa,
				idHdDadosEstaticos
		);
		System.out.println("Insert coleta HD: Concluído com êxito");

	}

	public void insertColetaHdDinamico(Long disponivel, LocalDateTime dataHora, Integer idMaquinaCorporativa, Integer idHdDadosEstaticos) {
		con.update(
				"insert into ColetaHd " +
						"values ((?),(?),(?),(?))",
				disponivel, dataHora, idMaquinaCorporativa, idHdDadosEstaticos
		);
	}

	@Override
	public String getIp() {
		Looca looca = new Looca();
		Rede rede = looca.getRede();
		return rede.getParametros().getServidoresDns().toString();
	}
}