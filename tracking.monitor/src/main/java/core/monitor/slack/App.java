package core.monitor.slack;

import core.monitor.entidades.cpu.CpuDadosEstaticos;
import core.monitor.entidades.hd.HdDadosEstaticos;
import core.monitor.entidades.memoria.RamDadosEstaticos;
import net.minidev.json.JSONObject;
import registros.GravadorService;

import java.io.IOException;
import java.net.UnknownHostException;

public class App {
    JSONObject json = new JSONObject();
    GravadorService gs = new GravadorService();

    public App() throws UnknownHostException {
    }


    public void sendMessageCPU(Integer usoAtual) throws IOException, InterruptedException {
        CpuDadosEstaticos cpuDadosEstaticos = new CpuDadosEstaticos(null,gs.getListRiscoCPU().get(gs.getListRiscoCPU().size() - 1).getRiscoCPU(),null);

        if(!gs.getListColetaCPU().isEmpty()) {
            if (usoAtual > cpuDadosEstaticos.getRiscoCPU()) {
                json.put("text", "Crítico - Sua CPU está em % de uso :shrug:");
                Slack.sendMessage(json);
            }
        }
    }
    public void sendMessageRAM(Long usoAtual) throws IOException, InterruptedException {
        RamDadosEstaticos ramDadosEstaticos = new RamDadosEstaticos(null,gs.getListRiscoRAM().get(gs.getListRiscoRAM().size() - 1).getRiscoRam(),null);

        if (!gs.getListRiscoRAM().isEmpty()) {
            if (usoAtual > ramDadosEstaticos.getRiscoRam()) {
                json.put("text", "Crítico - Sua RAM está em % de uso :shrug:");
                Slack.sendMessage(json);
            }
        }
    }
    public void sendMessageHD(Long usoAtual) throws IOException, InterruptedException {
        HdDadosEstaticos hdDadosEstaticos = new HdDadosEstaticos(null,gs.getListRiscoHD().get(gs.getListRiscoHD().size() - 1).getRiscoHd(),null);

        if (!gs.getListRiscoHD().isEmpty()) {
            if (usoAtual < hdDadosEstaticos.getRiscoHd()) {
                json.put("text", "Crítico - Seu HD está em % disponível :shrug:");
                Slack.sendMessage(json);
            }
        }
    }

    public void sendMessageAlertCPU(Integer usoAtual) throws IOException, InterruptedException {
        CpuDadosEstaticos cpuDadosEstaticos = new CpuDadosEstaticos(null,gs.getListRiscoCPU().get(gs.getListRiscoCPU().size() - 1).getRiscoCPU(),null);

        if (!gs.getListRiscoCPU().isEmpty()) {
            if (usoAtual > (cpuDadosEstaticos.getRiscoCPU() * 80 / 100)) {
                json.put("text", "Alerta - Sua CPU está em % de uso. Atenção: Seu limite pode ser atigindo em breve " +
                        ":shrug:");
                Slack.sendMessage(json);
            }
        }
    }

    public void sendMessageAlertRAM(Long usoAtual) throws IOException, InterruptedException {
        RamDadosEstaticos ramDadosEstaticos = new RamDadosEstaticos(null,gs.getListRiscoRAM().get(gs.getListRiscoRAM().size() - 1).getRiscoRam(),null);

        if (!gs.getListRiscoRAM().isEmpty()) {
            if (usoAtual > (ramDadosEstaticos.getRiscoRam() * 80 / 100)) {
                json.put("text", "Alerta - Sua RAM está em % de uso. Atenção: Seu limite pode ser atigindo em breve " +
                        ":shrug:");
                Slack.sendMessage(json);
            }
        }
    }

    public void sendMessageAlertHD(Long usoAtual) throws IOException, InterruptedException {
        HdDadosEstaticos hdDadosEstaticos = new HdDadosEstaticos(null,gs.getListRiscoHD().get(gs.getListRiscoHD().size() - 1).getRiscoHd(),null);

        if (!gs.getListRiscoHD().isEmpty()) {
            if (usoAtual > (hdDadosEstaticos.getRiscoHd() * 80 / 100)) {
                json.put("text", "Alerta - Seu HD está em % disponível. Atenção: Seu limite pode ser atigindo em breve " +
                        ":shrug:");
                Slack.sendMessage(json);
            }
        }
    }
}
