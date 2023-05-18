package core.monitor.slack;

import net.minidev.json.JSONObject;

import java.io.IOException;

public class App {
    JSONObject json = new JSONObject();

    public void sendMessageCPU(Integer usoAtual) throws IOException, InterruptedException {
        if(usoAtual > 80){
            json.put("text", "Crítico - Sua CPU está em % de uso :shrug:");
            Slack.sendMessage(json);
        }
    }
    public void sendMessageRAM(Long usoAtual) throws IOException, InterruptedException {
        if(usoAtual > 80){
            json.put("text", "Crítico - Sua RAM está em % de uso :shrug:");
            Slack.sendMessage(json);
        }
    }
    public void sendMessageHD(Long usoAtual) throws IOException, InterruptedException {
        if(usoAtual < 20){
            json.put("text", "Crítico - Seu HD está em % disponível :shrug:");
            Slack.sendMessage(json);
        }
    }

    public void sendMessageAlertCPU(Integer usoAtual) throws IOException, InterruptedException {
        if(usoAtual > 80){
            json.put("text", "Alerta - Sua CPU está em % de uso. Atenção: Seu limite pode ser atigindo em breve " +
                    ":shrug:");
            Slack.sendMessage(json);
        }
    }

    public void sendMessageAlertRAM(Long usoAtual) throws IOException, InterruptedException {
        if(usoAtual > 80){
            json.put("text", "Alerta - Sua RAM está em % de uso. Atenção: Seu limite pode ser atigindo em breve " +
                    ":shrug:");
            Slack.sendMessage(json);
        }
    }

    public void sendMessageAlertHD(Integer usoAtual) throws IOException, InterruptedException {
        if(usoAtual < 50){
            json.put("text", "Alerta - Seu HD está em % disponível. Atenção: Seu limite pode ser atigindo em breve " +
                    ":shrug:");
            Slack.sendMessage(json);
        }
    }
}
