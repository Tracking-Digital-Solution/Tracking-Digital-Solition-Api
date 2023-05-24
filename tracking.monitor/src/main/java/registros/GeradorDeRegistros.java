package registros;

import core.monitor.repositorio.Ilooca;

import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.*;

public class GeradorDeRegistros implements Ilooca {

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
        Scanner entrada = new Scanner(System.in);
        System.out.printf("%s%n", "Entre com o código do produto e nome do item para cadastro:");
        Calendar cal = Calendar.getInstance();
        int horaAtual = cal.get(Calendar.HOUR_OF_DAY);
        long intervalo = 3600000;
        System.out.println(horaAtual);
        for(int i = 0; i < 10 ; i++) { // iterar até que seja encontrado o marcador de fim-de-arquivo
            try {
                // Gravar novo registro no arquivo; não verifica se entrada é válida.
                arquivoCriado.format(
                        "Usuario: %s \n" +
                        "Data de login: %s \n" +
                        ""

                );
//                try {
//                    Thread.sleep(intervalo);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                break;
            } catch (FormatterClosedException formatterClosedException) {
                System.err.println("Erro ao escrever no arquivo. Finalizando.");
                break;
            } catch (NoSuchElementException elementException) {
                System.err.println("Entrada inválida. Tente novamente.");
//                entrada.nextLine(); // Descartar a entrada para que o usuário possa tentar de novo
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


    @Override
    public String getIp() {
        return null;
    }
} // Fim da classe CriarArquivoTexto

