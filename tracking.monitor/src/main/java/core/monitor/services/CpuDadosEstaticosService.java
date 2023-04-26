package core.monitor.services;

import core.monitor.entidades.cpu.CpuDadosEstaticos;
import core.monitor.repositorio.Ilooca;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.util.List;

public class CpuDadosEstaticosService implements Ilooca {

	public void executeQueryInsertCpuDadosEstaticos() {
		CpuDadosEstaticos cpuDadosEstaticos = new CpuDadosEstaticos();

		try{
			if ((returnExpectedAlreadyDataCpuDadosEstaticos().isEmpty())) {
				insertCpuDadosEstaticos(processador.getNome());
			}
		}catch (IllegalStateException e){
			System.out.println("Dados estaticos dessa máquina já existem!");
		}

	}

	private void insertCpuDadosEstaticos(String nomeProcessador) {
		con.update(
				"insert into CpuDadosEstaticos " +
						"values (75,(?))",
				nomeProcessador
		);
	}

	public List<CpuDadosEstaticos> getAllCpuDadosEstaticos() {
		return con.query(
				"select * from CpuDadosEstaticos",
				new BeanPropertyRowMapper<>(CpuDadosEstaticos.class));
	}

	public Integer returnExpectedIdCpuDadosEstaticos(Integer idCpuDadosEstaticos) {
		try {
			List<CpuDadosEstaticos> listaQuery = con.query("select idCpuDadosEstaticos,riscoCPU from CpuDadosEstaticos where idCpuDadosEstaticos = '" + idCpuDadosEstaticos + "'",
					new BeanPropertyRowMapper<>(CpuDadosEstaticos.class));
			return listaQuery.get(idCpuDadosEstaticos - 1).getIdCpuDadosEstaticos();
		} catch (NullPointerException e) {
			System.out.println("Não há dados cadastrados com esse ID!!");
			return null;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	public List<Integer> returnExpectedAlreadyDataCpuDadosEstaticos() {
		return con.query(
				"select 1 from maquinacorporativa mc " +
						"INNER JOIN coletacpu cc on mc.idMaquinaCorporativa = cc.idCPU " +
						"INNER JOIN cpudadosestaticos ce on cc.idCPU = ce.idCpuDadosEstaticos",
				new BeanPropertyRowMapper<>()
		);
	}

	@Override
	public String getIp() {
		return null;
	}
}