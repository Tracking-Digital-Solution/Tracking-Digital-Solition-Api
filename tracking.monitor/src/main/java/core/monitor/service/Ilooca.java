package core.monitor.service;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.discos.DiscoGrupo;
import com.github.britooo.looca.api.group.memoria.Memoria;
import com.github.britooo.looca.api.group.processador.Processador;
import com.github.britooo.looca.api.group.rede.Rede;
import com.github.britooo.looca.api.group.sistema.Sistema;
import com.github.britooo.looca.api.group.temperatura.Temperatura;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

public interface Ilooca extends ITemplateJdbc {
	//Conexão Looca
	Looca looca = new Looca();
	Rede rede = looca.getRede();
	Sistema sistema = looca.getSistema();
	Processador processador = looca.getProcessador();
	Memoria memoria = new Memoria();
	DiscoGrupo discoGrupo = new DiscoGrupo();
	Temperatura temperatura = new Temperatura();

	// Variaveis id por ip
	String ip = rede.getParametros().getServidoresDns().toString();
	String id = con.queryForObject(String.format(
					"select (idMaquinaCorporativa) from MaquinaCorporativa where ip = %s", ip
			),
			new BeanPropertyRowMapper<String>(String.class));
}
