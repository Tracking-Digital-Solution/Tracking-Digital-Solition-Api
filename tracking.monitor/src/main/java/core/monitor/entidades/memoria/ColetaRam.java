/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.monitor.entidades.memoria;

import core.monitor.entidades.cpu.CpuDadosEstaticos;
import core.monitor.entidades.maquina.MaquinaCorporativa;

import java.time.LocalDateTime;

/**
 *
 * @author leska
 */
public class ColetaRam {

    private Integer idRam;
    private Double usoAtual;
    private Double disponivel;
    private MaquinaCorporativa maquinaCorporativa;
    private LocalDateTime dataHora;
    private RamDadosEstaticos ramDadosEstaticos;

    public ColetaRam() {
    }

    public ColetaRam(
            Integer idRam
            , Double usoAtual
            , Double disponivel
            , MaquinaCorporativa maquinaCorporativa
            , LocalDateTime dataHora
            , RamDadosEstaticos ramDadosEstaticos
    ) {
        this.idRam = idRam;
        this.usoAtual = usoAtual;
        this.disponivel = disponivel;
        this.maquinaCorporativa = maquinaCorporativa;
        this.dataHora = dataHora;
        this.ramDadosEstaticos = ramDadosEstaticos;
    }

    public Integer getIdRam() {
        return idRam;
    }

    public Double getUsoAtual() {
        return usoAtual;
    }

    public MaquinaCorporativa getMaquinaCorporativa() {
        return maquinaCorporativa;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public RamDadosEstaticos getRamDadosEstaticos() {
        return ramDadosEstaticos;
    }

    public Double getDisponivel() {
        return disponivel;
    }

    @Override
    public String toString() {
        return "ColetaRam{" +
                "idRam=" + idRam +
                ", usoAtual=" + usoAtual +
                ", disponivel=" + disponivel +
                ", maquinaCorporativa=" + maquinaCorporativa +
                ", dataHora=" + dataHora +
                ", ramDadosEstaticos=" + ramDadosEstaticos +
                '}';
    }
}
