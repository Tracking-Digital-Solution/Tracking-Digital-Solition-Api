package core.monitor.services.cpu;

import core.monitor.entidades.cpu.CpuDadosEstaticos;
import core.monitor.entidades.maquina.MaquinaCorporativa;
import core.monitor.jar.core.monitor.resources.ITemplateJdbc;
import core.monitor.repositorio.Ilooca;
import core.monitor.services.MaquinaCorporativaService;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

public class CpuDadosEstaticosService implements Ilooca, ITemplateJdbc {

	public void executeQueryInsertCpuDadosEstaticos() {
		try {
			if (!(returnNameMachineByDatabase().equals(getSystemName()))) {
				MaquinaCorporativaService mcs = new MaquinaCorporativaService();
				insertCpuDadosEstaticos(processador.getNome(), mcs.returnExpectedIdMaquinaCorporativa());
				System.out.println("Dados Estáticos Inseridos");
			}else{
				insertStaticDataMysql(processador.getNome());
			}

		} catch (DuplicateKeyException | IllegalStateException | UnknownHostException e) {
			System.out.println("Dados estaticos dessa máquina já existem!");
		}

	}

	private void insertCpuDadosEstaticos(String nomeProcessador, Integer idMaquina) {
		ITemplateJdbc.con.update(
				"insert into CpuDadosEstaticos " +
						"values ((?),75,(?))",
				idMaquina,nomeProcessador
		);

                conMySQL.update(
				"insert into CpuDadosEstaticos " +
						"values ((?),75,(?))",
				idMaquina,nomeProcessador
		);
	}
	public String returnNameMachineByDatabase() throws UnknownHostException {
		try {
			List<MaquinaCorporativa> maquina = ITemplateJdbc.con.query(
					"select top 1 mc.nomeMaquina from maquinacorporativa mc " +
							"INNER JOIN coletacpu cc on mc.idMaquinaCorporativa = cc.idCPU " +
							"INNER JOIN cpudadosestaticos ce on cc.idCPU = ce.idCpuDadosEstaticos " +
							"where mc.nomeMaquina = '" + getSystemName() + "'",
					new BeanPropertyRowMapper<>(MaquinaCorporativa.class)
			);
			return maquina.get(0).getNomeMaquina();
		} catch (Exception e) {
			return "Inexistente";
		}
                
                
	}
        
        public void insertStaticDataMysql(String nomeProcessador) throws UnknownHostException {
			try{
				List<CpuDadosEstaticos> listaDadosEstaticosCPUAzure = ITemplateJdbc.con.query(
						"select ce.* from MaquinaCorporativa mc " +
								"INNER JOIN ColetaCPU cc on mc.idMaquinaCorporativa = cc.idCPU " +
								"INNER JOIN CpuDadosEstaticos ce on cc.idCPU = ce.idCpuDadosEstaticos " +
								"where mc.nomeMaquina = '" + getSystemName() + "'",
						new BeanPropertyRowMapper<>(CpuDadosEstaticos.class)
				);

				List<CpuDadosEstaticos> listaDadosEstaticosCPU = conMySQL.query(
						"select ce.* from MaquinaCorporativa mc " +
								"INNER JOIN ColetaCPU cc on mc.idMaquinaCorporativa = cc.idCPU " +
								"INNER JOIN CpuDadosEstaticos ce on cc.idCPU = ce.idCpuDadosEstaticos " +
								"where mc.nomeMaquina = '" + getSystemName() + "'",
						new BeanPropertyRowMapper<>(CpuDadosEstaticos.class)
				);
				if (listaDadosEstaticosCPU.isEmpty()){
					conMySQL.update("INSERT INTO CpuDadosEstaticos values " +
									"((?), 75, (?))",
							listaDadosEstaticosCPUAzure.get(0).getIdCpuDadosEstaticos(), nomeProcessador);
				}
			}catch (DuplicateKeyException e){
				System.out.println("Dados Estaticos Atualizados Localmente");
			}
		}



	private String getSystemName() throws UnknownHostException {
		String systemName = InetAddress.getLocalHost().getHostName();
		return systemName;
	}

	@Override
	public String getIp() {
		return null;
	}
}