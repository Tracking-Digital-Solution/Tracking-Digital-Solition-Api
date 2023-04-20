package core.monitor.entidades.cpu;

import core.monitor.entidades.maquina.MaquinaCorporativa;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ColetaCpu {
	private Integer IdCpu;
	private Double usoAtual;
	private MaquinaCorporativa maquinaCorporativa;
	private LocalDateTime dataHora;
	private CpuMaquinaCorporativa cpuMaquinaCorporativa;


	public ColetaCpu(Integer idCpu, Double usoAtual, MaquinaCorporativa maquinaCorporativa, LocalDateTime dataHora, CpuMaquinaCorporativa cpuMaquinaCorporativa) {
		IdCpu = idCpu;
		this.usoAtual = usoAtual;
		this.maquinaCorporativa = maquinaCorporativa;
		this.dataHora = dataHora;
		this.cpuMaquinaCorporativa = cpuMaquinaCorporativa;
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

	public CpuMaquinaCorporativa getCpuMaquinaCorporativa() {
		return cpuMaquinaCorporativa;
	}

	public void setCpuMaquinaCorporativa(CpuMaquinaCorporativa cpuMaquinaCorporativa) {
		this.cpuMaquinaCorporativa = cpuMaquinaCorporativa;
	}
}
