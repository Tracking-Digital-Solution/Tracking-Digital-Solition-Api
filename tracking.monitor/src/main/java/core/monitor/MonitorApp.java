package core.monitor;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.memoria.Memoria;
import com.github.britooo.looca.api.group.processador.Processador;
import com.github.britooo.looca.api.group.rede.Rede;
import com.github.britooo.looca.api.group.sistema.Sistema;
import com.github.britooo.looca.api.group.discos.DiscoGrupo;
import com.github.britooo.looca.api.group.temperatura.Temperatura;

import core.monitor.components.maquina.MaquinaCorporativa;
import core.monitor.components.maquina.MaquinaCorporativaExecute;
import core.monitor.service.Conexao;
import core.monitor.service.Ilooca;
import org.springframework.jdbc.core.JdbcTemplate;

/*
 * @author gabsm
 */
public class MonitorApp implements Ilooca {


	public static void main(String[] args) {
		Looca looca = new Looca();
		// Abrindo conexão
		Conexao conexao = new Conexao();
		JdbcTemplate con = conexao.getConexaoDoBanco();



		//Inserindo em MaquinaCoporativa
		MaquinaCorporativa maquina = new MaquinaCorporativa();
//		maquina.setMapMaquinaCorporativa();
		System.out.println(Math.round(processador.getUso()));
		System.out.println();

    }

}
