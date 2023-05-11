package core.monitor.entidades.cpu;
import java.util.ArrayList;
import java.util.List;

public class CpuDadosEstaticos {

	private Integer idCpuDadosEstaticos;
	private Integer riscoCPU;
	private String nomeProcessador;
	private List<ColetaCpu> listaColetaCpu = new ArrayList<>();

	public CpuDadosEstaticos(Integer idCpuDadosEstaticos, Integer riscoCPU, String nomeProcessador) {
		this.idCpuDadosEstaticos = idCpuDadosEstaticos;
		this.riscoCPU = riscoCPU;
		this.nomeProcessador = nomeProcessador;
	}

	public CpuDadosEstaticos() {
	}

	public List<ColetaCpu> getListaColetaCpu() {
		return listaColetaCpu;
	}

	public Integer getIdCpuDadosEstaticos() {
		return idCpuDadosEstaticos;
	}

	public void setIdCpuDadosEstaticos(Integer idCpuDadosEstaticos) {
		this.idCpuDadosEstaticos = idCpuDadosEstaticos;
	}

	public Integer getRiscoCPU() {
		return riscoCPU;
	}

	public void setRiscoCPU(Integer riscoCPU) {
		this.riscoCPU = riscoCPU;
	}

	public String getNomeProcessador() {
		return nomeProcessador;
	}

	public void setNomeProcessador(String nomeProcessador) {
		this.nomeProcessador = nomeProcessador;
	}
}
