package core.monitor.components.maquina;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.rede.Rede;
import com.github.britooo.looca.api.group.sistema.Sistema;
import core.monitor.service.Ilooca;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.util.HashMap;
import java.util.Map;

public class MaquinaCorporativa implements Ilooca {
	Looca looca = new Looca();
	Rede rede = looca.getRede();
	//Variaveis
	String ip = rede.getParametros().getServidoresDns().toString();
	String nomeMaquina = rede.getParametros().getHostName();
	Sistema sistema = new Sistema();
	MaquinaCorporativaExecute maquina = new MaquinaCorporativaExecute();
	String sistemaOperacional = sistema.getSistemaOperacional();

	String idMaquinaCorporativa = getIndex();

	Map<String, String> mapMaquinaCorporativa = new HashMap<>();

	//Metodos
	public void setMapMaquinaCorporativa() {
		mapMaquinaCorporativa.put("IP", ip);
		mapMaquinaCorporativa.put("Sistema Operacional", sistemaOperacional);
		mapMaquinaCorporativa.put("Nome da Maquina", nomeMaquina);
		maquina.executeQuery(ip, sistemaOperacional, nomeMaquina);
	}


	public String getIndex(){
		 String id = con.queryForObject(String.format(
				 "select (idMaquinaCorporativa) from MaquinaCorporativa where ip = %s", ip
				 ),
				new BeanPropertyRowMapper<String>(String.class));
		return id;
	}
	public Looca getLooca() {
		return looca;
	}

	public Rede getRede() {
		return rede;
	}

	public Sistema getSistema() {
		return sistema;
	}

	public String getIp() {
		return ip;
	}

	public String getSistemaOperacional() {
		return sistemaOperacional;
	}

	public String getNomeMaquina() {
		return nomeMaquina;
	}

	@Override
	public String toString() {
		return String.format(
				"Máquina Corporativa\n" +
						"Ip: %s\n" +
						"Sistema operacional: %s\n" +
						"Nome da máquina: %s\n\n",
				ip,sistemaOperacional,nomeMaquina
		);
	}
}
