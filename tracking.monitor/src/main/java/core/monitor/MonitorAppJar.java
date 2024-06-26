package core.monitor;

import core.monitor.repositorio.Ilooca;
import core.monitor.services.cpu.ColetaCpuService;
import core.monitor.services.cpu.CpuDadosEstaticosService;
import core.monitor.services.hd.ColetaHdService;
import core.monitor.services.hd.HdDadosEstaticosService;
import core.monitor.services.MaquinaCorporativaService;
import core.monitor.services.ram.ColetaRamService;
import core.monitor.services.ram.RamDadosEstaticosService;
import org.springframework.jdbc.CannotGetJdbcConnectionException;


public class MonitorAppJar implements Ilooca {
    public void monitorApp(){
        try {
                // Inserir máquina
                MaquinaCorporativaService maquinaCorporativaService = new MaquinaCorporativaService();
                if (maquinaCorporativaService.executeQueryUpdateMaquinaCorporativa()) {
                    // Inserir CpuDadosEstaticos (DADOS ESTÁTICOS)
                    CpuDadosEstaticosService cpuDadosEstaticosService = new CpuDadosEstaticosService();
                    cpuDadosEstaticosService.executeQueryInsertCpuDadosEstaticos();

                    // Inserir ColetaCpu (DADOS DINÂMICOS)
                    ColetaCpuService coletaCpuService = new ColetaCpuService();
                    coletaCpuService.executeQueryInsertColetaCpu();

                    // Inserir HdDadosEstaticos (DADOS ESTÁTICOS)
                    HdDadosEstaticosService hdDadosEstaticoservice = new HdDadosEstaticosService();
                    hdDadosEstaticoservice.executeQueryInsertHdDadosEstaticos();

                    // Inserir ColetaHd (DADOS DINÂMICOS)
                    ColetaHdService coletaHdService = new ColetaHdService();
                    coletaHdService.executeQueryInsertColetaHd();

                    // Inserir RamDadosEstaticos (DADOS ESTÁTICOS)
                    RamDadosEstaticosService ramDadosEstaticosService = new RamDadosEstaticosService();
                    ramDadosEstaticosService.executeQueryInsertRamDadosEstaticos();

                    // Inserir ColetaRam (DADOS DINÂMICOS)
                    ColetaRamService coletaRamService = new ColetaRamService();
                    coletaRamService.executeQueryInsertColetaRam();
                } else {
                    System.out.println("Máquina não existe!");
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