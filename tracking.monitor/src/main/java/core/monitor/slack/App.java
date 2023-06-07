package core.monitor.slack;

import com.github.britooo.looca.api.core.Looca;
import core.monitor.entidades.cpu.CpuDadosEstaticos;
import core.monitor.entidades.hd.HdDadosEstaticos;
import core.monitor.entidades.maquina.MaquinaCorporativa;
import core.monitor.entidades.memoria.RamDadosEstaticos;
import net.minidev.json.JSONObject;
import registros.GravadorService;

import java.io.IOException;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class App {
    JSONObject json = new JSONObject();
    GravadorService gs = new GravadorService();
    public static LocalDateTime lastUpdateCpuCritico;
    public static LocalDateTime lastUpdateCpuAlerta;
    public static LocalDateTime lastUpdateHdCritico;
    public static LocalDateTime lastUpdateHdAlerta;
    public static LocalDateTime lastUpdateRamCritico;
    public static LocalDateTime lastUpdateRamAlerta;


    public App() throws UnknownHostException {
    }


    public void sendMessageCPU(Integer usoAtual) throws IOException, InterruptedException {
        CpuDadosEstaticos cpuDadosEstaticos = new CpuDadosEstaticos(null,gs.getListRiscoCPU().get(gs.getListRiscoCPU().size() - 1).getRiscoCPU(),null);



                if (lastUpdateCpuCritico == null || ChronoUnit.SECONDS.between(lastUpdateCpuCritico, LocalDateTime.now()) >= 30) {
                    if (!gs.getListColetaCPU().isEmpty()) {
                        if (usoAtual > cpuDadosEstaticos.getRiscoCPU()) {
                            json.put("text", "Crítico - Sua CPU está em " + String.format("%d", usoAtual) + "% de uso :shrug:");
                            Slack.sendMessage(json);
                        }
                    }
                    lastUpdateCpuCritico = LocalDateTime.now();
                } else {
                    System.out.println("Caso estiver em estado Crítico em 30 segundos, será enviado um novo alerta, lastUpdate: " + lastUpdateCpuCritico);
                }


    }

    public void sendMessageRAM(Long usoAtual) throws IOException, InterruptedException {
        RamDadosEstaticos ramDadosEstaticos = new RamDadosEstaticos(null,gs.getListRiscoRAM().get(gs.getListRiscoRAM().size() - 1).getRiscoRam(),null);
        Looca looca = new Looca();



                if (lastUpdateRamCritico == null || ChronoUnit.SECONDS.between(lastUpdateRamCritico, LocalDateTime.now()) >= 30) {
                    Double ramUsadoGB =  usoAtual / Math.pow(1024, 3);
                    Long ramTotal = looca.getMemoria().getTotal();
                    Double ramTotalGB = ramTotal / Math.pow(1024, 3);
                    Double contaRAM = ( ramUsadoGB / ramTotalGB) * 100;

                    if (!gs.getListRiscoRAM().isEmpty()) {
                        if (usoAtual > ramDadosEstaticos.getRiscoRam()) {
                            json.put("text", "Crítico - Sua RAM está em "+ String.format("%.0f",contaRAM) +"% de uso :shrug:");
                            Slack.sendMessage(json);
                        }
                    }
                    lastUpdateRamCritico = LocalDateTime.now();
                } else {
                    System.out.println("Caso estiver em estado Crítico em 30 segundos, será enviado um novo alerta, lastUpdate: " + lastUpdateRamCritico);
                }



    }
    public void sendMessageHD(Long usoAtual) throws IOException, InterruptedException {
        HdDadosEstaticos hdDadosEstaticos = new HdDadosEstaticos(null,gs.getListRiscoHD().get(gs.getListRiscoHD().size() - 1).getRiscoHd(),null);
        Looca looca = new Looca();

                if (lastUpdateHdCritico == null || ChronoUnit.SECONDS.between(lastUpdateHdCritico, LocalDateTime.now()) >= 30) {
                    Double hdDisponivelGB = usoAtual / (1024.0 * 1024.0 * 1024.0);
                    Long hdTotal = looca.getGrupoDeDiscos().getTamanhoTotal();
                    Double hdTotalGB = hdTotal / (1024.0 * 1024.0 * 1024.0);
                    Double contaHD = ((hdTotalGB - hdDisponivelGB)  / hdTotalGB) * 100;

                    if (!gs.getListRiscoHD().isEmpty()) {
                        if (usoAtual < hdDadosEstaticos.getRiscoHd()) {
                            json.put("text", "Crítico - Seu HD está em "+String.format("%.0f",contaHD)+"% disponível :shrug:");
                            Slack.sendMessage(json);
                        }
                    }
                    lastUpdateHdCritico = LocalDateTime.now();
                } else {
                    System.out.println("Caso estiver em estado Crítico em 30 segundos, será enviado um novo alerta, lastUpdate: " + lastUpdateHdCritico);
                }

    }

    public void sendMessageAlertCPU(Integer usoAtual) throws IOException, InterruptedException {
        CpuDadosEstaticos cpuDadosEstaticos = new CpuDadosEstaticos(null,gs.getListRiscoCPU().get(gs.getListRiscoCPU().size() - 1).getRiscoCPU(),null);


                if (lastUpdateCpuAlerta == null || ChronoUnit.SECONDS.between(lastUpdateCpuAlerta, LocalDateTime.now()) >= 30) {
                    if (!gs.getListRiscoCPU().isEmpty()) {
                        if (usoAtual > (cpuDadosEstaticos.getRiscoCPU() * 1.20)) {
                            json.put("text", "Alerta - Sua CPU está em "+String.format("%d",usoAtual)+"% de uso. Atenção: Seu limite pode ser atigindo em breve " +
                                    ":shrug:");
                            Slack.sendMessage(json);
                        }
                    }
                    lastUpdateCpuAlerta = LocalDateTime.now();
                } else {
                    System.out.println("Um novo alerta será enviado em 30 segundos, lastUpdate: " + lastUpdateCpuAlerta);
                }


    }

    public void sendMessageAlertRAM(Long usoAtual) throws IOException, InterruptedException {
        RamDadosEstaticos ramDadosEstaticos = new RamDadosEstaticos(null,gs.getListRiscoRAM().get(gs.getListRiscoRAM().size() - 1).getRiscoRam(),null);
        Looca looca = new Looca();


                if (lastUpdateRamAlerta == null || ChronoUnit.SECONDS.between(lastUpdateRamAlerta, LocalDateTime.now()) >= 30) {
                    Double ramUsadoGB =  usoAtual / Math.pow(1024, 3);
                    Long ramTotal = looca.getMemoria().getTotal();
                    Double ramTotalGB = ramTotal / Math.pow(1024, 3);
                    Double contaRAM = ( ramUsadoGB / ramTotalGB) * 100;

                    if (!gs.getListRiscoRAM().isEmpty()) {
                        if (usoAtual > (ramDadosEstaticos.getRiscoRam() * 80 / 100)) {
                            json.put("text", "Alerta - Sua RAM está em "+String.format("%.0f",contaRAM)+"% de uso. Atenção: Seu limite pode ser atigindo em breve " +
                                    ":shrug:");
                            Slack.sendMessage(json);
                        }
                    }
                    lastUpdateRamAlerta = LocalDateTime.now();
                } else {
                    System.out.println("Um novo alerta será enviado em 30 segundos, lastUpdate: " + lastUpdateRamAlerta);
                }



    }

    public void sendMessageAlertHD(Long usoAtual) throws IOException, InterruptedException {
        HdDadosEstaticos hdDadosEstaticos = new HdDadosEstaticos(null,gs.getListRiscoHD().get(gs.getListRiscoHD().size() - 1).getRiscoHd(),null);
        Looca looca = new Looca();


                if (lastUpdateHdAlerta == null || ChronoUnit.SECONDS.between(lastUpdateHdAlerta, LocalDateTime.now()) >= 30) {
                    Double hdDisponivelGB = usoAtual / (1024.0 * 1024.0 * 1024.0);
                    Long hdTotal = looca.getGrupoDeDiscos().getTamanhoTotal();
                    Double hdTotalGB = hdTotal / (1024.0 * 1024.0 * 1024.0);
                    Double contaHD = ((hdTotalGB - hdDisponivelGB)  / hdTotalGB) * 100;

                    if (!gs.getListRiscoHD().isEmpty()) {
                        if (usoAtual > (hdDadosEstaticos.getRiscoHd() * 80 / 100)) {
                            json.put("text", "Alerta - Seu HD está em "+String.format("%.0f",contaHD)+"% disponível. Atenção: Seu limite pode ser atigindo em breve " +
                                    ":shrug:");
                            Slack.sendMessage(json);
                        }
                    }

                    lastUpdateHdAlerta = LocalDateTime.now();
                } else {
                    System.out.println("Um novo alerta será enviado em 30 segundos, lastUpdate: " + lastUpdateHdAlerta);
                }

        }

}
