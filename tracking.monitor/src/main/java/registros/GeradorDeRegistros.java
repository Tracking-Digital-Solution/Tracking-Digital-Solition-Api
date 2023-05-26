package registros;

import core.monitor.entidades.cpu.CpuDadosEstaticos;
import core.monitor.entidades.hd.HdDadosEstaticos;
import core.monitor.entidades.memoria.RamDadosEstaticos;
import core.monitor.resources.ITemplateJdbc;

import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.*;

public class GeradorDeRegistros implements ITemplateJdbc {
    GravadorService gravadorService = new GravadorService();
    private static Formatter arquivoCriado; // envia texto para um arquivo

    public static void main(String[] args) {
        abrirArquivo();
        adicionarDados();
        fecharArquivo();
    }

    // Método para abrir (ou criar) o arquivo arquivo.txt
    public static void abrirArquivo() {
        Date dataSistema = new Date();
        SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");

        //Logs-12-05-2023.txt
        String nomeArquivo = "Logs-" + formato.format(dataSistema) + ".txt";

        try {
            arquivoCriado = new Formatter(nomeArquivo); // Abrir o arquivo
            System.out.println("Arquivo criado: " + nomeArquivo);
        } catch (SecurityException securityException) {
            System.err.println("Não é possível escrever no arquivo. Finalizando.");
            System.exit(1); // terminar o programa
        } catch (FileNotFoundException fileNotFoundException) {
            System.err.println("Erro ao abrir o arquivo. Finalizando.");
            System.exit(1); // Finalizar o programa
        }
    }

    // Método para adicionar registros ao arquivo
    public static void adicionarDados() {
        GravadorService gravadorService = new GravadorService();
        Calendar cal = Calendar.getInstance();
        Integer riscoAnteriosCPU = 0;
        Integer riscoAnteriosHD = 0;
        Integer riscoAnteriosRAM = 0;

        int horaAtual = cal.get(Calendar.HOUR_OF_DAY);
        long intervalo = 3600000;
        System.out.println(horaAtual);
        for(int i = 0; i < 10 ; i++) { // iterar até que seja encontrado o marcador de fim-de-arquivo
            try {
                // Gravar novo registro no arquivo; não verifica se entrada é válida.
                arquivoCriado.format(
                        "-----------------------\n" +
                        "Ultimo Registro Estatico de CPU\n" +
                        "Dados de CPU: %.2f \n\n" +
                        "Ultimo Registro Estatico de CPU \n" +
                        "Dados de Memória: %d \n\n" +
                        "Ultimo Registro Estatico de CPU\n" +
                        "Dados de HDD: %d\n\n" +
                        gravadorService.getRiscoCPU().getRiscoCPU(),
                        gravadorService.getRiscoRAM().getRiscoRam(),
                        gravadorService.getRiscoHD().getRiscoHd()
                );

                break;
            } catch (FormatterClosedException formatterClosedException) {
                System.err.println("Erro ao escrever no arquivo. Finalizando.");
                break;
            } catch (NoSuchElementException elementException) {
                System.err.println("Entrada inválida. Tente novamente.");
//                entrada.nextLine(); // Descartar a entrada para que o usuário possa tentar de novo
            } catch (UnknownHostException e) {
                throw new RuntimeException(e);
            }
            System.out.print("Entre com o próximo código e item:\n");
        } // Fim do laço while
    } // Fim do método adicionarDados

    // Método para fechar o arquivo
    public static void fecharArquivo() {
        if (arquivoCriado != null) {
            arquivoCriado.close();
        }
    }

} // Fim da classe CriarArquivoTexto

