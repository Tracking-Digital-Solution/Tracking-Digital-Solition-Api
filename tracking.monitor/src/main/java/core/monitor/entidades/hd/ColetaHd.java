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

    private Integer idHd;
    private Double disponivel;
    private MaquinaCorporativa maquinaCorporativa;
    private LocalDateTime dataHora;
    private HdDadosEstaticos hdDadosEstaticos;

    public ColetaHd() {
    }

    public ColetaHd(
            Integer idHd
            ,Double disponivel
            ,MaquinaCorporativa maquinaCorporativa
            ,LocalDateTime dataHora
            ,HdDadosEstaticos hdDadosEstaticos
    ) {
        this.idHd = idHd;
        this.disponivel = disponivel;
        this.maquinaCorporativa = maquinaCorporativa;
        this.dataHora = dataHora;
        this.hdDadosEstaticos = hdDadosEstaticos;
    }

    public Integer getIdHd() {
        return idHd;
    }

    public Double getDisponivel() {
        return disponivel;
    }

    public MaquinaCorporativa getMaquinaCorporativa() {
        return maquinaCorporativa;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public HdDadosEstaticos getHdDadosEstaticos() {
        return hdDadosEstaticos;
    }
}
