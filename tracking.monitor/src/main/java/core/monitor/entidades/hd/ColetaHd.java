/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.monitor.entidades.hd;

import core.monitor.entidades.cpu.CpuDadosEstaticos;
import core.monitor.entidades.maquina.MaquinaCorporativa;
import java.time.LocalDateTime;

/**
 *
 * @author leska
 */
public class ColetaHd {

    private Integer IdHd;
    private Double usoAtual;
    private MaquinaCorporativa maquinaCorporativa;
    private LocalDateTime dataHora;
    private CpuDadosEstaticos hdDadosEstaticos;

    public void ColetaHd() {
    }

    public ColetaHd(Integer IdHd, Double usoAtual, MaquinaCorporativa maquinaCorporativa, LocalDateTime dataHora, CpuDadosEstaticos hdDadosEstaticos) {
        this.IdHd = IdHd;
        this.usoAtual = usoAtual;
        this.maquinaCorporativa = maquinaCorporativa;
        this.dataHora = dataHora;
        this.hdDadosEstaticos = hdDadosEstaticos;
    }

    public Integer getIdHd() {
        return IdHd;
    }

    public void setIdHd(Integer IdHd) {
        this.IdHd = IdHd;
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

    public CpuDadosEstaticos getHdDadosEstaticos() {
        return hdDadosEstaticos;
    }

    public void setHdDadosEstaticos(CpuDadosEstaticos hdDadosEstaticos) {
        this.hdDadosEstaticos = hdDadosEstaticos;
    }

}
