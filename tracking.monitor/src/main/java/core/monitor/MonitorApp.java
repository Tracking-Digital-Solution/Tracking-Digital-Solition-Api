package core.monitor;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.memoria.Memoria;
import com.github.britooo.looca.api.group.processador.Processador;
import com.github.britooo.looca.api.group.rede.Rede;
import com.github.britooo.looca.api.group.sistema.Sistema;
import com.github.britooo.looca.api.group.discos.DiscoGrupo;
import com.github.britooo.looca.api.group.temperatura.Temperatura;

import org.w3c.dom.ls.LSOutput;
import oshi.SystemInfo;
import oshi.software.os.OSFileStore;

import java.util.List;

/*
 * @author gabsm
 */
public class MonitorApp {


	public static void main(String[] args) {
		Looca looca = new Looca();
		Rede rede = looca.getRede();
		System.out.println(rede.getGrupoDeInterfaces().getInterfaces());
		System.out.println(rede.getParametros());
		System.out.println("Processador");
		Processador processador = looca.getProcessador();
		System.out.println(processador);

		System.out.println("");
		System.out.println("Memória");
		Memoria memoria = new Memoria();
		System.out.println(memoria);

		System.out.println("");
		DiscoGrupo discoGrupo = new DiscoGrupo();
		System.out.println("");

		System.out.println("Grupo de disco");
		DiscoGrupo grupoDeDiscos = looca.getGrupoDeDiscos();
		System.out.println(grupoDeDiscos.getDiscos());
		System.out.println(grupoDeDiscos.getQuantidadeDeDiscos());
		System.out.println(grupoDeDiscos.getTamanhoTotal());
		System.out.println(grupoDeDiscos.getVolumes());


		System.out.println("Temperatura");
		Temperatura temperatura = new Temperatura();
		System.out.println(temperatura.getTemperatura());
		System.out.println("");

		Sistema sistema = looca.getSistema();
		System.out.println("Sistema");
		System.out.println(sistema);

    }

}
