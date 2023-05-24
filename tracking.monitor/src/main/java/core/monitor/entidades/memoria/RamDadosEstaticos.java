/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.monitor.entidades.memoria;

import core.monitor.entidades.cpu.ColetaCpu;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author leska
 */
public class RamDadosEstaticos {

    private Integer idRamDadosEstaticos;
    private Integer riscoRam;
    private BigInteger total;

    public RamDadosEstaticos() {
    }

    public RamDadosEstaticos(Integer idRamDadosEstaticos, Integer riscoRam, BigInteger total) {
        this.idRamDadosEstaticos = idRamDadosEstaticos;
        this.riscoRam = riscoRam;
        this.total = total;
    }


    public Integer getIdRamDadosEstaticos() {
        return idRamDadosEstaticos;
    }

    public void setIdRamDadosEstaticos(Integer idRamDadosEstaticos) {
        this.idRamDadosEstaticos = idRamDadosEstaticos;
    }

    public Integer getRiscoRam() {
        return riscoRam;
    }

    public void setRiscoRam(Integer riscoRam) {
        this.riscoRam = riscoRam;
    }

    public BigInteger getTotal() {
        return total;
    }

    public void setTotal(BigInteger total) {
        this.total = total;
    }
}
