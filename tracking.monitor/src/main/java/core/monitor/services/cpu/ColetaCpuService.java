package core.monitor.services.cpu;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.rede.Rede;
import core.monitor.jar.core.monitor.resources.ITemplateJdbc;
import core.monitor.repositorio.Ilooca;
import core.monitor.services.MaquinaCorporativaService;
import core.monitor.slack.App;

import java.io.IOException;
import java.time.LocalDateTime;

public class ColetaCpuService implements Ilooca, ITemplateJdbc {

	public void executeQueryInsertColetaCpu() throws IOException, InterruptedException {
		App app = new App();

		MaquinaCorporativaService maquinaCorporativaService = new MaquinaCorporativaService();
		Double doubleUsoAtual = processador.getUso();
		Integer usoAtual = doubleUsoAtual.intValue();
		Integer idMaquinaCorporativa = maquinaCorporativaService.returnExpectedIdMaquinaCorporativa();
		Integer idCpuDadosEstaticos = idMaquinaCorporativa;

		insertColetaCpuDinamico(
				usoAtual,
				LocalDateTime.now(),
				idMaquinaCorporativa,
				idCpuDadosEstaticos
		);
		System.out.println("Insert coleta cpu: Concluído com êxito");
		app.sendMessageCPU(usoAtual);
	}

	public void insertColetaCpuDinamico(Integer usoAtual, LocalDateTime dataHora, Integer idMaquinaCorporativa, Integer IdCpuDadosEstaicos) {
		ITemplateJdbc.con.update(
				"insert into ColetaCPU " +
						"values ((?),(?),(?),(?))",
				usoAtual, dataHora, idMaquinaCorporativa, IdCpuDadosEstaicos
		);
		conMySQL.update(
				"insert into ColetaCPU " +
						"values (null,(?),(?),(?),(?))",
				usoAtual, dataHora, idMaquinaCorporativa, IdCpuDadosEstaicos
		);
	}

	@Override
	public String getIp() {
		Looca looca = new Looca();
		Rede rede = looca.getRede();
		return rede.getParametros().getServidoresDns().toString();
	}
}