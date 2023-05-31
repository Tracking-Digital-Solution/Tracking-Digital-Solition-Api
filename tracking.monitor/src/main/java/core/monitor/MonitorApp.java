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
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import static core.monitor.MonitorApp2.lastUpdate;

/*
 * @author gabsm
 */

public class MonitorApp implements Ilooca {


    public static void main(String[] args) throws InterruptedException {
        GeradorDeRegistros geradorDeRegistros = new GeradorDeRegistros();
        if (lastUpdate == null || ChronoUnit.SECONDS.between(lastUpdate, LocalDateTime.now()) >= 20) {
            geradorDeRegistros.gerarLog(new MaquinaCorporativa());
            lastUpdate = LocalDateTime.now();
        } else {
            System.out.println("Arquivo será criado a cada 1 hora, lastUpdate: " + lastUpdate);
        }


        try {
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

            } else {
                System.out.println("Maquina não existe!");
            }

        } catch (CannotGetJdbcConnectionException e) {
            System.out.println("Não há conexão com o banco!");
        } catch (Exception e) {
            e.printStackTrace();
        }

        Thread.sleep(5000);
        main(args);

    }




    @Override
    public String getIp() {
        return null;
    }
}
