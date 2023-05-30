package registros;

import core.monitor.entidades.cpu.CpuDadosEstaticos;
import core.monitor.entidades.hd.HdDadosEstaticos;
import core.monitor.entidades.maquina.MaquinaCorporativa;
import core.monitor.entidades.memoria.RamDadosEstaticos;
import core.monitor.resources.ITemplateJdbc;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class GeradorDeRegistros implements ITemplateJdbc {

    public void gerarLog(MaquinaCorporativa maquinaCorporativa) {
    CpuDadosEstaticos cpuDadosEstaticos = new CpuDadosEstaticos();
    HdDadosEstaticos hdDadosEstaticos = new HdDadosEstaticos();
    RamDadosEstaticos ramDadosEstaticos = new RamDadosEstaticos();

        // ferramenta que gera os logs
        Logger logger = Logger.getLogger("MyLog");

        // gerenciador de arquivos
        FileHandler fh;

        // pega o horario atual e seta o horario dele
        LocalDateTime getCurrentTime = LocalDateTime.now();
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
            logger.info(
                    "\nNome da maquina do Usuário: " + maquinaCorporativa.getNomeMaquina()
                            + "\nCPU: " + cpuDadosEstaticos.getRiscoCPU()
                            + "\nHD: " + hdDadosEstaticos.getRiscoHd()
                            + "\nRam: " + ramDadosEstaticos.getRiscoRam()
            );

        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


} // Fim da classe CriarArquivoTexto
