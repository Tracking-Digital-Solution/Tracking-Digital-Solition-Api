package core.monitor;

import core.monitor.repositorio.Ilooca;
import core.monitor.services.ColetaCpuService;
import core.monitor.services.CpuDadosEstaticosService;
import core.monitor.services.MaquinaCorporativaService;
import org.springframework.jdbc.CannotGetJdbcConnectionException;

import java.net.UnknownHostException;
/*
 * @author gabsm
 */

public class MonitorApp implements Ilooca {

	public static void main(String[] args){
		try {
			//Inserindo máquina
			MaquinaCorporativaService maquinaCorporativaService = new MaquinaCorporativaService();
//			if (maquinaCorporativaService.executeQueryUpdateMaquinaCorporativa()) {
//				//Inserir dados nas tabelas de coleta
//
//				//Inserir CpuDadosEstaticos
				CpuDadosEstaticosService cpuDadosEstaticosService = new CpuDadosEstaticosService();
				cpuDadosEstaticosService.executeQueryInsertCpuDadosEstaticos();
//
//				//Inserir ColetaCpu
				ColetaCpuService coletaCpuService = new ColetaCpuService();
				coletaCpuService.executeQueryInsertColetaCpu();
//
//			} else {
//				System.out.println("Erro 500! não existe nada");
//			}
		} catch (CannotGetJdbcConnectionException e) {
			System.out.println("Não há conexão com o banco!");
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	@Override
	public String getIp() {
		return null;
	}
}


//	List<MaquinaCorporativa> listAllMaquinaCorporativa = getAllMaquinaCorporativa();
//	List<ColetaCpu> listAllColetaCpu = getAllColetaCpu();
//	List<CpuDadosEstaticos> listAllCpuDadosEstaticos = getAllCpuDadosEstaticos();


//	public List<MaquinaCorporativa> getAllMaquinaCorporativa() {
//		List<MaquinaCorporativa> select = con.query(
//				"select * from MaquinaCorporativa",
//				new BeanPropertyRowMapper<>(MaquinaCorporativa.class));
//		return select;
//	}
//
//	public List<ColetaCpu> getAllColetaCpu() {
//		return con.query(
//				"select * from MaquinaCorporativa",
//				new BeanPropertyRowMapper<>(ColetaCpu.class));
//	}
//
//	public List<CpuDadosEstaticos> getAllCpuDadosEstaticos() {
//		return con.query(
//				"select * from MaquinaCorporativa",
//				new BeanPropertyRowMapper<>(CpuDadosEstaticos.class));
//	}
