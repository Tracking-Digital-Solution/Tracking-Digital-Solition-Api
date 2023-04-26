package core.monitor.services;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.rede.Rede;
import core.monitor.entidades.cpu.ColetaCpu;
import core.monitor.repositorio.Ilooca;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.time.LocalDateTime;
import java.util.List;

public class ColetaCpuService implements Ilooca {

	public void executeQueryInsertColetaCpu() {


		MaquinaCorporativaService maquinaCorporativaService = new MaquinaCorporativaService();
		CpuDadosEstaticosService cpuDadosEstaticosService = new CpuDadosEstaticosService();

		Double doubleUsoAtual = looca.getProcessador().getUso();
		Integer usoAtual = doubleUsoAtual.intValue();
		Integer idMaquinaCorporativa = maquinaCorporativaService.returnExpectedIdMaquinaCorporativa();
		Integer idCpuDadosEstaticos = cpuDadosEstaticosService.returnExpectedIdCpuDadosEstaticos(idMaquinaCorporativa);

		insertCpu(
				usoAtual,
				LocalDateTime.now(),
				idMaquinaCorporativa,
				idCpuDadosEstaticos
		);
		System.out.println("Insert coleta cpu: Concluído com êxito");

	}

	public void insertCpu(Integer usoAtual, LocalDateTime dataHora, Integer idMaquinaCorporativa, Integer IdCpuDadosEstaicos) {
		con.update(
				"insert into ColetaCPU " +
						"values ((?),(?),(?),(?))",
				usoAtual, dataHora, idMaquinaCorporativa, IdCpuDadosEstaicos
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