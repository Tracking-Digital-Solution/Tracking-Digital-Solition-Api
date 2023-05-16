package core.monitor.services.cpu;

import core.monitor.entidades.cpu.CpuDadosEstaticos;
import core.monitor.entidades.maquina.MaquinaCorporativa;
import core.monitor.jar.core.monitor.resources.ITemplateJdbc;
import core.monitor.repositorio.Ilooca;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

public class CpuDadosEstaticosService implements Ilooca, ITemplateJdbc {

	public void executeQueryInsertCpuDadosEstaticos() {
		try {
			if (!(returnNameMachineByDatabase().equals(getSystemName()))) {
				insertCpuDadosEstaticos(processador.getNome());
				System.out.println("Dados Estáticos Inseridos");
			}
		} catch (IllegalStateException | UnknownHostException e) {
			System.out.println("Dados estaticos dessa máquina já existem!");
		}

	}

	private void insertCpuDadosEstaticos(String nomeProcessador) {
		ITemplateJdbc.con.update(
				"insert into CpuDadosEstaticos " +
						"values (75,(?))",
				nomeProcessador
		);
                
                conMySQL.update(
				"insert into CpuDadosEstaticos " +
						"values (null,75,(?))",
				nomeProcessador
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
        
        

	private String getSystemName() throws UnknownHostException {
		String systemName = InetAddress.getLocalHost().getHostName();
		return systemName;
	}

	@Override
	public String getIp() {
		return null;
	}
}