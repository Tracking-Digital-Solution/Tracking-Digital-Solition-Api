package core.monitor.components;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.rede.Rede;
import com.github.britooo.looca.api.group.sistema.Sistema;

public class MaquinaCorporativa {
	Looca looca = new Looca();
	Rede rede = looca.getRede();
	Sistema sistema = new Sistema();


	//Variaveis
	String ip = rede.getParametros().getServidoresDns().toString();
	String sistemaOperacional = sistema.getSistemaOperacional();
	String nomeMaquina = rede.getParametros().getHostName();

	//Metodos
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
		return "Maquina Corporativa:" +
				"\nIp: '" + ip + '\'' +
				"\n Sistema Operacional: '" + sistemaOperacional + '\'' +
				"\n Nome da máquina: '" + nomeMaquina + '\'' +
				'}';
	}
}
