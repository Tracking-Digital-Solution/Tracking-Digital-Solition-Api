package registros;

import core.monitor.entidades.cpu.CpuDadosEstaticos;
import core.monitor.entidades.hd.HdDadosEstaticos;
import core.monitor.entidades.memoria.RamDadosEstaticos;
import core.monitor.resources.ITemplateJdbc;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.net.UnknownHostException;
import java.util.List;

import static core.monitor.services.MaquinaCorporativaService.getSystemName;

public class GravadorService implements ITemplateJdbc {

    public CpuDadosEstaticos getRiscoCPU() throws UnknownHostException {
        List<CpuDadosEstaticos> listaDadosEstaticosCPU = con.query("select top 1 ce.* from maquinacorporativa mc " +
                "INNER JOIN coletacpu cc on mc.idMaquinaCorporativa = cc.idCPU " +
                "INNER JOIN cpudadosestaticos ce on cc.idCPU = ce.idCpuDadosEstaticos " +
                "where mc.nomeMaquina = '" + getSystemName() + "'", new BeanPropertyRowMapper<>(CpuDadosEstaticos.class));

        return listaDadosEstaticosCPU.get(0);
    }

    public HdDadosEstaticos getRiscoHD() throws UnknownHostException {
        List<HdDadosEstaticos> listaDadosEstaticosHd = con.query("select top 1 ce.* from maquinacorporativa mc " +
                "INNER JOIN coletahd cc on mc.idMaquinaCorporativa = cc.idHD " +
                "INNER JOIN hddadosestaticos ce on cc.idHD = ce.idHdDadosEstaticos " +
                "where mc.nomeMaquina = '" + getSystemName() + "'", new BeanPropertyRowMapper<>(HdDadosEstaticos.class));

        return listaDadosEstaticosHd.get(0);
    }

    public RamDadosEstaticos getRiscoRAM()throws UnknownHostException {
        List<RamDadosEstaticos> listaDadosEstaticosRAM = con.query("select top 1 ce.* from maquinacorporativa mc " +
                "INNER JOIN coletaram cc on mc.idMaquinaCorporativa = cc.idRAM " +
                "INNER JOIN ramdadosestaticos ce on cc.idRAM = ce.idRamDadosEstaticos " +
                "where mc.nomeMaquina = '" + getSystemName() + "'", new BeanPropertyRowMapper<>(RamDadosEstaticos.class));

        return listaDadosEstaticosRAM.get(0);
    }


}