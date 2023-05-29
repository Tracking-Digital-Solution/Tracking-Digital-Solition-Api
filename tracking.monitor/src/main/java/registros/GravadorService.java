package registros;

import core.monitor.entidades.cpu.CpuDadosEstaticos;
import core.monitor.entidades.hd.HdDadosEstaticos;
import core.monitor.entidades.maquina.MaquinaCorporativa;
import core.monitor.entidades.memoria.RamDadosEstaticos;
import core.monitor.resources.ITemplateJdbc;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.net.UnknownHostException;
import java.util.List;

import static core.monitor.services.MaquinaCorporativaService.getSystemName;

public class GravadorService implements ITemplateJdbc {
    private JdbcTemplate jdbcTemplate;

    public List<CpuDadosEstaticos> getListRiscoCPU() throws UnknownHostException {
        List<CpuDadosEstaticos> listaDadosEstaticosCPU = con.query("select ce.* from maquinacorporativa mc " +
                "INNER JOIN coletacpu cc on mc.idMaquinaCorporativa = cc.idCPU " +
                "INNER JOIN cpudadosestaticos ce on cc.idCPU = ce.idCpuDadosEstaticos " +
                "where mc.nomeMaquina = '" + getSystemName() + "'", new BeanPropertyRowMapper<>(CpuDadosEstaticos.class));

        return listaDadosEstaticosCPU;
    }

    public List<HdDadosEstaticos> getListRiscoHD() throws UnknownHostException {
        List<HdDadosEstaticos> listaDadosEstaticosHd = con.query("select ce.* from maquinacorporativa mc " +
                "INNER JOIN coletahd cc on mc.idMaquinaCorporativa = cc.idHD " +
                "INNER JOIN hddadosestaticos ce on cc.idHD = ce.idHdDadosEstaticos " +
                "where mc.nomeMaquina = '" + getSystemName() + "'", new BeanPropertyRowMapper<>(HdDadosEstaticos.class));

        return listaDadosEstaticosHd;
    }

    public List<RamDadosEstaticos> getListRiscoRAM()throws UnknownHostException {
        List<RamDadosEstaticos> listaDadosEstaticosRAM = con.query("select ce.* from maquinacorporativa mc " +
                "INNER JOIN coletaram cc on mc.idMaquinaCorporativa = cc.idRAM " +
                "INNER JOIN ramdadosestaticos ce on cc.idRAM = ce.idRamDadosEstaticos " +
                "where mc.nomeMaquina = '" + getSystemName() + "'", new BeanPropertyRowMapper<>(RamDadosEstaticos.class));

        return listaDadosEstaticosRAM;
    }

    public List<MaquinaCorporativa> getListMaquinaCorporativa()throws UnknownHostException {
        List<MaquinaCorporativa> listaMaquinaCorporativa =
        con.query( "SELECT * FROM maquinacorporativa", new BeanPropertyRowMapper<>(MaquinaCorporativa.class));

        return listaMaquinaCorporativa;
    }
}
