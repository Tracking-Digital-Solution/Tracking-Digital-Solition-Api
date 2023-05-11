/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.monitor.entidades.hd;

import core.monitor.entidades.cpu.ColetaCpu;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author leska
 */
public class HdDadosEstaticos {

    private Integer idHdDadosEstaticos;
    private Integer riscoHd;
    private String nomeHd;
    private List<ColetaCpu> listaColetaCpu = new ArrayList<>();

    public HdDadosEstaticos() {
    }

    public HdDadosEstaticos(Integer idHdDadosEstaticos, Integer riscoHd, String nomeHd) {
        this.idHdDadosEstaticos = idHdDadosEstaticos;
        this.riscoHd = riscoHd;
        this.nomeHd = nomeHd;
    }

    public Integer getIdHdDadosEstaticos() {
        return idHdDadosEstaticos;
    }

    public void setIdHdDadosEstaticos(Integer idHdDadosEstaticos) {
        this.idHdDadosEstaticos = idHdDadosEstaticos;
    }

    public Integer getRiscoHd() {
        return riscoHd;
    }

    public void setRiscoHd(Integer riscoHd) {
        this.riscoHd = riscoHd;
    }

    public String getNomeHd() {
        return nomeHd;
    }

    public void setNomeHd(String nomeHd) {
        this.nomeHd = nomeHd;
    }

    public List<ColetaCpu> getListaColetaCpu() {
        return listaColetaCpu;
    }


}
