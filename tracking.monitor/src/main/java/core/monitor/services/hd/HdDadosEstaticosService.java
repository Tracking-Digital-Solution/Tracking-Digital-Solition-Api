/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.monitor.services.hd;

import core.monitor.entidades.maquina.MaquinaCorporativa;
import core.monitor.jar.core.monitor.resources.ITemplateJdbc;
import core.monitor.repositorio.Ilooca;
import core.monitor.services.MaquinaCorporativaService;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

/**
 * @author leska
 */
public class HdDadosEstaticosService implements Ilooca, ITemplateJdbc {

    public void executeQueryInsertHdDadosEstaticos() {
        try {
            if (!(returnNameMachineByDatabase().equals(getSystemName()))) {
                insertHdDadosEstaticos();
                System.out.println("Dados Estáticos Inseridos");
            }
        } catch (DuplicateKeyException | IllegalStateException | UnknownHostException e) {
            System.out.println("Dados estaticos dessa máquina já existem!");
        }

    }

    private void insertHdDadosEstaticos() {
        MaquinaCorporativaService mcs = new MaquinaCorporativaService();
        ITemplateJdbc.con.update(
                 "insert into hdDadosEstaticos "
                + "values ((?),30,(?),(?))",
                mcs.returnExpectedIdMaquinaCorporativa()
                , discoGrupo.getDiscos().get(0).getModelo()
                ,discoGrupo.getTamanhoTotal()
        );

        conMySQL.update(
                 "insert into hdDadosEstaticos "
                + "values ((?),30,(?),(?))",
                mcs.returnExpectedIdMaquinaCorporativa()
                , discoGrupo.getDiscos().get(0).getModelo()
                ,discoGrupo.getTamanhoTotal()
        );
        
    }

    public String returnNameMachineByDatabase() throws UnknownHostException {
        try {
            List<MaquinaCorporativa> maquina = ITemplateJdbc.con.query(
                    "select top 1 mc.nomeMaquina from maquinacorporativa mc "
                    + "INNER JOIN coletahd ch on mc.idMaquinaCorporativa = ch.idHd "
                    + "INNER JOIN hddadosestaticos he on ch.idHd = he.idHdDadosEstaticos "
                    + "where mc.nomeMaquina = '" + getSystemName() + "'",
                    new BeanPropertyRowMapper<>(MaquinaCorporativa.class)
            );
            return maquina.get(0).getNomeMaquina();
        } catch (Exception e) {
            return "Inexistente";
        }
    }

    private String getSystemName() throws UnknownHostException {
        String systemName = InetAddress.getLocalHost().getHostName();
        return systemName;
    }

    @Override
    public String getIp() {
        return null;
    }

//	@Override
//	public String getIp() {
//		return null;
//	}
}
