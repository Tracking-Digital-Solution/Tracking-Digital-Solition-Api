package core.monitor.slack;

import core.monitor.entidades.cpu.CpuDadosEstaticos;
import core.monitor.entidades.hd.HdDadosEstaticos;
import core.monitor.entidades.memoria.RamDadosEstaticos;
import core.monitor.services.cpu.CpuDadosEstaticosService;
import net.minidev.json.JSONObject;

import java.io.IOException;

public class App {
    JSONObject json = new JSONObject();
    CpuDadosEstaticos cpuDadosEstaticos = new CpuDadosEstaticos();
    RamDadosEstaticos ramDadosEstaticos = new RamDadosEstaticos();

    HdDadosEstaticos hdDadosEstaticos = new HdDadosEstaticos();


    public void sendMessageCPU(Integer usoAtual) throws IOException, InterruptedException {
        if(usoAtual > cpuDadosEstaticos.getRiscoCPU()){
            json.put("text", "Crítico - Sua CPU está em % de uso :shrug:");
            Slack.sendMessage(json);
        }
    }
    public void sendMessageRAM(Long usoAtual) throws IOException, InterruptedException {
        if(usoAtual > ramDadosEstaticos.getRiscoRam()){
            json.put("text", "Crítico - Sua RAM está em % de uso :shrug:");
            Slack.sendMessage(json);
        }
    }
    public void sendMessageHD(Long usoAtual) throws IOException, InterruptedException {
        if(usoAtual < hdDadosEstaticos.getRiscoHd()){
            json.put("text", "Crítico - Seu HD está em % disponível :shrug:");
            Slack.sendMessage(json);
        }
    }

    public void sendMessageAlertCPU(Integer usoAtual) throws IOException, InterruptedException {
        if(usoAtual > (cpuDadosEstaticos.getRiscoCPU() * 80/100)){
            json.put("text", "Alerta - Sua CPU está em % de uso. Atenção: Seu limite pode ser atigindo em breve " +
                    ":shrug:");
            Slack.sendMessage(json);
        }
    }

    public void sendMessageAlertRAM(Long usoAtual) throws IOException, InterruptedException {
        if(usoAtual > (ramDadosEstaticos.getRiscoRam() * 80/100)){
            json.put("text", "Alerta - Sua RAM está em % de uso. Atenção: Seu limite pode ser atigindo em breve " +
                    ":shrug:");
            Slack.sendMessage(json);
        }
    }

    public void sendMessageAlertHD(Integer usoAtual) throws IOException, InterruptedException {
        if(usoAtual > (hdDadosEstaticos.getRiscoHd() * 80/100)){
            json.put("text", "Alerta - Seu HD está em % disponível. Atenção: Seu limite pode ser atigindo em breve " +
                    ":shrug:");
            Slack.sendMessage(json);
        }
    }
}
