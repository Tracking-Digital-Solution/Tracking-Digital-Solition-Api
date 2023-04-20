package core.monitor.repositorio;

import core.monitor.entidades.maquina.MaquinaCorporativa;
import resources.Ilooca;

public class MaquinaCorporativaRepositorio implements Ilooca {
	protected void executeQueryInsertColetaCpu(String usoAtual, String idMaquinaCorporativa, String dataHora,
	                                           Integer  fkEstaticaCPU) {
		con.update("insert into ColetaCPU(usoAtual, fkMaquinaCorporativa, dataHora, fkEstaticaCPU) " +
						"values " +
						"(?,?,?,?)",
				usoAtual, idMaquinaCorporativa, dataHora, fkEstaticaCPU);
		System.out.println("Insert de coleta de dados CPU concluidos com êxito");
	}

	protected void executeQueryStaticValuesCpu(Integer risco, String nome) {
		try {
			if(validationStaticValuesCpu()){
				con.update("insert into CpuMaquinaCorporativa(risco, nomeProcessador) " +
								"values " +
								"(?,?)",
						risco, nome);
				System.out.println("Insert de dados estáticos CPU concluidos com êxito");
			}
			else{
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
