package core.monitor.entidades.maquina;

import core.monitor.entidades.cpu.ColetaCpu;

import java.util.ArrayList;
import java.util.List;

public class MaquinaCorporativa {
	private Integer idMaquinaCorporativa;
	private String ip;
	private String sistemaOperacional;
	private String nomeMaquina;

	private List<ColetaCpu> listaColetaCpu = new ArrayList<>();


	public MaquinaCorporativa(Integer idMaquinaCorporativa, String ip, String sistemaOperacional, String nomeMaquina) {
		this.idMaquinaCorporativa = idMaquinaCorporativa;
		this.ip = ip;
		this.sistemaOperacional = sistemaOperacional;
		this.nomeMaquina = nomeMaquina;
	}

	public MaquinaCorporativa() {
	}

	public MaquinaCorporativa(MaquinaCorporativa maquinaCorporativa01) {
	}

	public List<ColetaCpu> getListaColetaCpu() {
		return listaColetaCpu;
	}
	public Integer getIdMaquinaCorporativa() {
		return idMaquinaCorporativa;
	}

	public void setIdMaquinaCorporativa(Integer idMaquinaCorporativa) {
		this.idMaquinaCorporativa = idMaquinaCorporativa;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getSistemaOperacional() {
		return sistemaOperacional;
	}

	public void setSistemaOperacional(String sistemaOperacional) {
		this.sistemaOperacional = sistemaOperacional;
	}

	public String getNomeMaquina() {
		return nomeMaquina;
	}

	public void setNomeMaquina(String nomeMaquina) {
		this.nomeMaquina = nomeMaquina;
	}
}
