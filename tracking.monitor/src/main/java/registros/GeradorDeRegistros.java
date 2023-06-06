package registros;

import core.monitor.entidades.cpu.CpuDadosEstaticos;
import core.monitor.entidades.hd.HdDadosEstaticos;
import core.monitor.entidades.maquina.MaquinaCorporativa;
import core.monitor.entidades.memoria.RamDadosEstaticos;
import core.monitor.repositorio.Ilooca;
import core.monitor.resources.ITemplateJdbc;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class GeradorDeRegistros implements ITemplateJdbc, Ilooca {
    public int horaCriacaoLog;

    public String gerarLog(MaquinaCorporativa maquinaCorporativa) {

        // ferramenta que gera os logs
        Logger logger = Logger.getLogger("MyLog");
        GravadorService gravadorService = new GravadorService();
        LocalDateTime getCurrentTime = LocalDateTime.now();
        this.horaCriacaoLog = getCurrentTime.getHour();

        // gerenciador de arquivos
        FileHandler fh;

        // pega o horario atual e seta o horario dele
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH'h'mm'm'ss's'");
        String currentTime = getCurrentTime.format(timeFormatter);

        //pega a data
        LocalDate getCurrentDate = LocalDate.now();
        Integer year = getCurrentDate.getYear();
        Integer month = getCurrentDate.getMonthValue();
        Integer day = getCurrentDate.getDayOfMonth();
        String currentDate = String.format("%d-%d-%d", day, month, year);

        //prefixo da log
        String logDirectory = "logs";

        //cria o path das logs por data
        File directory = new File(logDirectory + File.separator + currentDate);

        if (!directory.exists()) {
            directory.mkdirs();
        }

        //nome da nova log
        String fileName = "TrackingLogs " + currentDate + " - " + currentTime + ".log";


        try {
            // This block configure the logger with handler and formatter
            //iDENTIFICA pasta chamada ~logs/date~ e cria o novo arquivo dentro dela
            String logFileSeparator = "logs" + File.separator + currentDate + File.separator + fileName;
            fh = new FileHandler(logFileSeparator);

            logger.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);

            // the following statement is used to log any messages
            if(
                    gravadorService.getListRiscoCPU().size() > 0 &&
                    gravadorService.getListRiscoHD().size() > 0 &&
                    gravadorService.getListRiscoRAM().size() > 0
            ) {
                logger.info(
                        "\nNome da maquina do Usuário: " + rede.getParametros().getHostName()
                                + "\nCPU: " + gravadorService.getListRiscoCPU().get(gravadorService.getListRiscoCPU().size() - 1).toString()
                                + "\nHD: " + gravadorService.getListRiscoHD().get(gravadorService.getListRiscoHD().size() - 1)
                                + "\nRam: " + gravadorService.getListRiscoRAM().get(gravadorService.getListRiscoRAM().size() - 1)
                );
            }

        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "lastUpdate";
    }

    public int getHoraCriacaoLog() {
        return horaCriacaoLog;
    }

    @Override
    public String getIp() {
        return null;
    }
} // Fim da classe CriarArquivoTexto
