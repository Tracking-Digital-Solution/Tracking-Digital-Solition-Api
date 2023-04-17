package core.monitor;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.memoria.Memoria;
import com.github.britooo.looca.api.group.processador.Processador;
import com.github.britooo.looca.api.group.rede.Rede;
import com.github.britooo.looca.api.group.sistema.Sistema;
import com.github.britooo.looca.api.group.discos.Disco;
import com.github.britooo.looca.api.group.discos.DiscoGrupo;
import com.github.britooo.looca.api.group.temperatura.Temperatura;

import core.monitor.service.Conexao;
import org.springframework.jdbc.core.JdbcTemplate;
import org.w3c.dom.ls.LSOutput;
import oshi.software.os.OSFileStore;

import java.util.List;

/*
 * @author gabsm
 */
public class MonitorApp {


	public static void main(String[] args) {
		Looca looca = new Looca();
		// Abrindo conexão
		Conexao conexao = new Conexao();
		JdbcTemplate con = conexao.getConexaoDoBanco();

		Sistema sistema = looca.getSistema();
		Processador processador = looca.getProcessador();
		Memoria memoria = new Memoria();
		DiscoGrupo discoGrupo = new DiscoGrupo();
		Temperatura temperatura = new Temperatura();
		Rede rede = looca.getRede();

		//Variaveis
		String ip = rede.getParametros().getServidoresDns().toString();
		String sistemaOperacional = sistema.getSistemaOperacional();
		String nomeMaquina = rede.getParametros().getHostName();

		//Inserindo em MaquinaCoporativa
		con.update("insert into MaquinaCorporativa(IP,sistemaOperacional,fkPerfil,fkEndereco,nomeMaquina) " +
				"values " +
				"(?,?,?,?,?)",
				ip, sistemaOperacional, 1, 1, nomeMaquina);

    }

}
