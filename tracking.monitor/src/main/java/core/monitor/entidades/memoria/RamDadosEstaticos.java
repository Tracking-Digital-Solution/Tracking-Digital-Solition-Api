/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.monitor.entidades.memoria;

import core.monitor.entidades.cpu.ColetaCpu;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author leska
 */
public class RamDadosEstaticos {

    private Integer idHdDadosEstaticos;
    private Integer riscoRam;
    private Integer total;

    public RamDadosEstaticos() {
    }

    public RamDadosEstaticos(Integer idHdDadosEstaticos, Integer riscoRam, Integer total) {
        this.idHdDadosEstaticos = idHdDadosEstaticos;
        this.riscoRam = riscoRam;
        this.total = total;
    }




}
