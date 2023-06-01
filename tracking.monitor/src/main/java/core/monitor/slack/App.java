package core.monitor.slack;

import core.monitor.entidades.cpu.CpuDadosEstaticos;
import core.monitor.entidades.hd.HdDadosEstaticos;
import core.monitor.entidades.memoria.RamDadosEstaticos;
import core.monitor.services.cpu.CpuDadosEstaticosService;
import core.monitor.services.hd.HdDadosEstaticosService;
import core.monitor.services.ram.RamDadosEstaticosService;
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
        CpuDadosEstaticos cpuDadosEstaticos = new CpuDadosEstaticos();

        if(!gs.getListColetaCPU().isEmpty()) {
            if (usoAtual > cpuDadosEstaticos.getRiscoCPU()) {
                json.put("text", "Crítico - Sua CPU está em % de uso :shrug:");
                Slack.sendMessage(json);
            }
        }
    }
    public void sendMessageRAM(Long usoAtual) throws IOException, InterruptedException {
        RamDadosEstaticos ramDadosEstaticos = new RamDadosEstaticos();

        if (!gs.getListRiscoRAM().isEmpty()) {
            if (usoAtual > ramDadosEstaticos.getRiscoRam()) {
                json.put("text", "Crítico - Sua RAM está em % de uso :shrug:");
                Slack.sendMessage(json);
            }
        }
    }
    public void sendMessageHD(Long usoAtual) throws IOException, InterruptedException {
        HdDadosEstaticos hdDadosEstaticos = new HdDadosEstaticos();

        if (!gs.getListRiscoHD().isEmpty()) {
            if (usoAtual < hdDadosEstaticos.getRiscoHd()) {
                json.put("text", "Crítico - Seu HD está em % disponível :shrug:");
                Slack.sendMessage(json);
            }
        }
    }

    public void sendMessageAlertCPU(Integer usoAtual) throws IOException, InterruptedException {
        CpuDadosEstaticos cpuDadosEstaticos = new CpuDadosEstaticos();

        if (!gs.getListRiscoCPU().isEmpty()) {
            if (usoAtual > (cpuDadosEstaticos.getRiscoCPU() * 80 / 100)) {
                json.put("text", "Alerta - Sua CPU está em % de uso. Atenção: Seu limite pode ser atigindo em breve " +
                        ":shrug:");
                Slack.sendMessage(json);
            }
        }
    }

    public void sendMessageAlertRAM(Long usoAtual) throws IOException, InterruptedException {
        RamDadosEstaticos ramDadosEstaticos = new RamDadosEstaticos();

        if (!gs.getListRiscoRAM().isEmpty()) {
            if (usoAtual > (ramDadosEstaticos.getRiscoRam() * 80 / 100)) {
                json.put("text", "Alerta - Sua RAM está em % de uso. Atenção: Seu limite pode ser atigindo em breve " +
                        ":shrug:");
                Slack.sendMessage(json);
            }
        }
    }

    public void sendMessageAlertHD(Long usoAtual) throws IOException, InterruptedException {
        HdDadosEstaticos hdDadosEstaticos = new HdDadosEstaticos();

        if (!gs.getListRiscoHD().isEmpty()) {
            if (usoAtual > (hdDadosEstaticos.getRiscoHd() * 80 / 100)) {
                json.put("text", "Alerta - Seu HD está em % disponível. Atenção: Seu limite pode ser atigindo em breve " +
                        ":shrug:");
                Slack.sendMessage(json);
            }
        }
    }
}
