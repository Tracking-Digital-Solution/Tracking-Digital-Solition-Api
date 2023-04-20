package core.monitor;

import core.monitor.entidades.maquina.MaquinaCorporativa;
import core.monitor.repositorio.MaquinaCorporativaRepositorio;
import core.monitor.resources.Ilooca;
/*
 * @author gabsm
 */

public class MonitorApp implements Ilooca {

	public static void main(String[] args) {
		MaquinaCorporativaRepositorio maquinaCorporativaRepositorio = new MaquinaCorporativaRepositorio();
		maquinaCorporativaRepositorio.executeQueryUpdateMaquinaCorporativa(
				new MaquinaCorporativa(null,ip,sistema.getSistemaOperacional(), processador.getNome())
		);
//		Looca looca = new Looca();
//		Sistema sistema = looca.getSistema();
//		sistema.getPermissao();
//		sistema.getFabricante();
//		sistema.getArquitetura();
//		sistema.getInicializado();
//		sistema.getSistemaOperacional();
//
//		System.out.println("Processador");
//		Processador processador = looca.getProcessador();
//		System.out.println(processador.getFabricante());
//		System.out.println(processador.getUso());
//		System.out.println(processador.getFrequencia());
//		System.out.println(processador.getId());
//		System.out.println(processador.getNome());
//		System.out.println(processador.getNumeroCpusFisicas());
//		System.out.println(processador.getIdentificador());
//		System.out.println(processador.getMicroarquitetura());
//		System.out.println(processador.getNumeroPacotesFisicos());
//
//		System.out.println("");
//		System.out.println("Memória");
//		Memoria memoria = new Memoria();
//		System.out.println(memoria.getTotal());
//		System.out.println(memoria.getDisponivel());
//		System.out.println(memoria.getEmUso());
//		System.out.println("");
//		System.out.println("Disco");
//		/*Disco disco = new Disco();*/
//
//		DiscoGrupo discoGrupo = new DiscoGrupo();
//		DiscoGrupo grupoDeDiscos = looca.getGrupoDeDiscos();
//		List<Disco> discos = grupoDeDiscos.getDiscos();
//
//		for (Disco disco : discos) {
//			System.out.println(disco);
//		}
//
//		System.out.println("Temperatura");
//		Temperatura temperatura = new Temperatura();
//		System.out.println(temperatura.getTemperatura());
//
//		System.out.println("Sistema");
//		System.out.println(sistema.getPermissao());
//        System.out.println(sistema.getFabricante());
//        System.out.println(sistema.getArquitetura());
//        System.out.println(sistema.getInicializado());
//        System.out.println(sistema.getSistemaOperacional());

	}


}
