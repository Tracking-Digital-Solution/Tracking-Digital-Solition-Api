package core.monitor.components.cpu;

import core.monitor.service.Ilooca;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.util.List;

public class ColetaCpuExecute implements Ilooca {

	protected void executeQueryInsertColetaCpu(String usoAtual, String idMaquinaCorporativa, String dataHora) {
		con.update("insert into ColetaCPU(usoAtual, fkMaquinaCorporativa, dataHora) " +
						"values " +
						"(?,?,?)",
				usoAtual, idMaquinaCorporativa, dataHora);
		System.out.println("Insert de coleta de dados CPU concluidos com êxito");
	}

	protected void executeQueryStaticValuesCpu(String risco, String nome) {
		try {
			if(validationStaticValuesCpu()){
				con.update("insert into CpuMaquinaCorporativa(risco, nomeProcessador) " +
								"values " +
								"(?,?)",
						risco, nome);
				System.out.println("Insert de dados estáticos CPU concluidos com êxito");
			}else{
				throw new RuntimeException();
			}
		} catch (RuntimeException e) {
			throw new RuntimeException("Erro ao inserir dados  de CPU no banco de dados");
		}
	}
	private Boolean validationStaticValuesCpu() {
		List<ColetaCpu> listaColetaCpu = con.query("select (usoAtual, dataHora) from ColetaCPU",
				new BeanPropertyRowMapper<>(ColetaCpu.class));
		for (int i = 0; i < listaColetaCpu.size(); i++) {
			if (listaColetaCpu.contains(listaColetaCpu.get(i))) {
				return false;
			}
		}
		return true;
	}

}
