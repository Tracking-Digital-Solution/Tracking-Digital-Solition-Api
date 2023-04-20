package core.monitor.entidades.cpu;
import java.util.ArrayList;
import java.util.List;

public class CpuMaquinaCorporativa {

	private Integer idColeta;
	private Integer risco;
	private String nomeProcessador;

	private List<ColetaCpu> listaColetaCpu = new ArrayList<>();
	public CpuMaquinaCorporativa(Integer idColeta, Integer risco, String nomeProcessador) {
		this.idColeta = idColeta;
		this.risco = risco;
		this.nomeProcessador = nomeProcessador;
	}

	public CpuMaquinaCorporativa() {
	}

	public List<ColetaCpu> getListaColetaCpu() {
		return listaColetaCpu;
	}

	public Integer getIdColeta() {
		return idColeta;
	}

	public void setIdColeta(Integer idColeta) {
		this.idColeta = idColeta;
	}

	public Integer getRisco() {
		return risco;
	}

	public void setRisco(Integer risco) {
		this.risco = risco;
	}

	public String getNomeProcessador() {
		return nomeProcessador;
	}

	public void setNomeProcessador(String nomeProcessador) {
		this.nomeProcessador = nomeProcessador;
	}
}
