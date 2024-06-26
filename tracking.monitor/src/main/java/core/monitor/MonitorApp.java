package core.monitor;

import core.monitor.entidades.maquina.MaquinaCorporativa;
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

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import org.apache.http.util.Args;

public class MonitorApp implements Ilooca {
    public static LocalDateTime lastUpdate;

    public static void main(String[] args) {
        GeradorDeRegistros geradorDeRegistros = new GeradorDeRegistros();

        while (true) {
            try {
                if (lastUpdate == null || ChronoUnit.HOURS.between(lastUpdate, LocalDateTime.now()) >= 1) {
                    geradorDeRegistros.gerarLog(new MaquinaCorporativa());
                    lastUpdate = LocalDateTime.now();
                } else {
                    System.out.println("Arquivo será criado a cada 1 hora, lastUpdate: " + lastUpdate);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

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

            try {
                Thread.sleep(5000); // Aguarda 5 segundos antes da próxima execução
            } catch (InterruptedException e) {
                e.printStackTrace();
                break; // Sai do loop caso ocorra uma interrupção
            }
        }
    }


    @Override
    public String getIp() {
        return null;
    }
}