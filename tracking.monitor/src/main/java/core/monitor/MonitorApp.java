package core.monitor;

import core.monitor.entidades.maquina.MaquinaCorporativa;
import core.monitor.entidades.memoria.RamDadosEstaticos;
import core.monitor.repositorio.Ilooca;
import core.monitor.services.cpu.ColetaCpuService;
import core.monitor.services.cpu.CpuDadosEstaticosService;
import core.monitor.services.hd.ColetaHdService;
import core.monitor.services.hd.HdDadosEstaticosService;
import core.monitor.services.MaquinaCorporativaService;
import core.monitor.services.ram.ColetaRamService;
import core.monitor.services.ram.RamDadosEstaticosService;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import registros.GeradorDeRegistros;
import registros.GravadorService;

import java.time.LocalDateTime;
import java.util.Date;

/*
 * @author gabsm
 */

public class MonitorApp implements Ilooca {

	public static void main(String[] args){
		try {
			Integer contadorHoras = new Date().getHours();
			//Inserindo máquina
			MaquinaCorporativaService maquinaCorporativaService = new MaquinaCorporativaService();
			if (maquinaCorporativaService.executeQueryUpdateMaquinaCorporativa()) {
				//Inserir dados nas tabelas de coleta

				//Inserir CpuDadosEstaticos (DADOS ESTÁTICOS)
				CpuDadosEstaticosService cpuDadosEstaticosService = new CpuDadosEstaticosService();
				cpuDadosEstaticosService.executeQueryInsertCpuDadosEstaticos();

				//Inserir ColetaCpu (DADOS DINÂMICOS)
				ColetaCpuService coletaCpuService = new ColetaCpuService();
				coletaCpuService.executeQueryInsertColetaCpu();
                                
				//Inserir HdDadosEstaticos (DADOS ESTÁTICOS)
				HdDadosEstaticosService hdDadosEstaticoservice = new HdDadosEstaticosService();
				hdDadosEstaticoservice.executeQueryInsertHdDadosEstaticos();
                               
                                
				//Inserir ColetaHd (DADOS DINÂMICOS)
				ColetaHdService coletaHdService = new ColetaHdService();
				coletaHdService.executeQueryInsertColetaHd();

				//Inserir RamDadosEstaticos (DADOS ESTÁTICOS)
				RamDadosEstaticosService ramDadosEstaticosService = new RamDadosEstaticosService();
				ramDadosEstaticosService.executeQueryInsertRamDadosEstaticos();

				//Inserir ColetaRam (DADOS DINÂMICOS)
				ColetaRamService coletaRamService = new ColetaRamService();
				coletaRamService.executeQueryInsertColetaRam();


				//Geração de Logs
				GeradorDeRegistros geradorDeRegistros = new GeradorDeRegistros();
				GravadorService gs = new GravadorService();
//TODO: fAZER ESSA CLAUSULÁ IF FUNCIONAR SÓ QUANDO O PARAMETRO FOR MUDADO, E QUANDO FOR MUDADO EX: 75 == 50 -> TRUE 50 == 50 : FALSE
//				if () {
//					geradorDeRegistros.gerarLog(new MaquinaCorporativa());
//				}
//				else{
//					System.out.println("Nenhuma persistencia de dados estaticos foram alterados!");
//				}
			} else {
				System.out.println("Maquina não existe!");
			}

		} catch (CannotGetJdbcConnectionException e) {
			System.out.println("Não há conexão com o banco!");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public String getIp() {
		return null;
	}
}