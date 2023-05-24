/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.monitor.services.hd;

import core.monitor.entidades.cpu.CpuDadosEstaticos;
import core.monitor.entidades.hd.HdDadosEstaticos;
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
            }else{
                insertStaticDataMysql();
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

    public void insertStaticDataMysql() throws UnknownHostException {
        try{
            List<HdDadosEstaticos> listaDadosEstaticosHDAzure = ITemplateJdbc.con.query(
                    "select ce.* from MaquinaCorporativa mc " +
                            "INNER JOIN ColetaHD cc on mc.idMaquinaCorporativa = cc.idHd " +
                            "INNER JOIN HdDadosEstaticos ce on cc.idHd = ce.idHdDadosEstaticos " +
                            "where mc.nomeMaquina = '" + getSystemName() + "'",
                    new BeanPropertyRowMapper<>(HdDadosEstaticos.class)
            );

            List<HdDadosEstaticos> listaDadosEstaticosHD = conMySQL.query(
                    "select ce.* from MaquinaCorporativa mc " +
                            "INNER JOIN ColetaHD cc on mc.idMaquinaCorporativa = cc.idHd " +
                            "INNER JOIN HdDadosEstaticos ce on cc.idHd = ce.idHdDadosEstaticos " +
                            "where mc.nomeMaquina = '" + getSystemName() + "'",
                    new BeanPropertyRowMapper<>(HdDadosEstaticos.class)
            );
            if (listaDadosEstaticosHD.isEmpty()){
                conMySQL.update("INSERT INTO HdDadosEstaticos values " +
                                "((?),30,(?),(?))",
                        listaDadosEstaticosHDAzure.get(0).getIdHdDadosEstaticos()
                        , discoGrupo.getDiscos().get(0).getModelo()
                        ,discoGrupo.getTamanhoTotal());
            }
        }catch (DuplicateKeyException e){
            System.out.println("Dados Estaticos Atualizados Localmente");
        }
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
