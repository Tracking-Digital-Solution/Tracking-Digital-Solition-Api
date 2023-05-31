package core.monitor.entidades.memoria;

import java.math.BigInteger;

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

    @Override
    public String toString() {
        return  "\nID :" + idRamDadosEstaticos +
                "\nAlerta de risco em: " + riscoRam + "%\n\n";
    }
}
