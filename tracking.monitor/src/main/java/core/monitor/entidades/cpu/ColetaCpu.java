package core.monitor.entidades.cpu;

import core.monitor.entidades.maquina.MaquinaCorporativa;
import org.springframework.jdbc.core.RowCallbackHandler;

import java.time.LocalDateTime;

public class ColetaCpu {
	private Integer IdCpu;
	private Double usoAtual;
	private MaquinaCorporativa maquinaCorporativa;
	private LocalDateTime dataHora;
	private CpuDadosEstaticos cpuDadosEstaticos;


	public ColetaCpu(
			Integer idCpu,
			Double usoAtual,
			MaquinaCorporativa maquinaCorporativa,
			LocalDateTime dataHora,
			CpuDadosEstaticos cpuDadosEstaticos
	) {
		this.IdCpu = idCpu;
		this.usoAtual = usoAtual;
		this.maquinaCorporativa = maquinaCorporativa;
		this.dataHora = dataHora;
		this.cpuDadosEstaticos = cpuDadosEstaticos;
	}

	public ColetaCpu() {
	}

	public Integer getIdCpu() {
		return IdCpu;
	}

	public void setIdCpu(Integer idCpu) {
		IdCpu = idCpu;
	}

	public Double getUsoAtual() {
		return usoAtual;
	}

	public void setUsoAtual(Double usoAtual) {
		this.usoAtual = usoAtual;
	}

	public MaquinaCorporativa getMaquinaCorporativa() {
		return maquinaCorporativa;
	}

	public void setMaquinaCorporativa(MaquinaCorporativa maquinaCorporativa) {
		this.maquinaCorporativa = maquinaCorporativa;
	}

	public LocalDateTime getDataHora() {
		return dataHora;
	}

	public void setDataHora(LocalDateTime dataHora) {
		this.dataHora = dataHora;
	}

	public CpuDadosEstaticos getCpuMaquinaCorporativa() {
		return cpuDadosEstaticos;
	}

	public void setCpuMaquinaCorporativa(CpuDadosEstaticos cpuDadosEstaticos) {
		this.cpuDadosEstaticos = cpuDadosEstaticos;
	}
}
