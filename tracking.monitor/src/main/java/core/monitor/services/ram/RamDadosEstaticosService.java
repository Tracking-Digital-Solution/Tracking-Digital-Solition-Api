/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.monitor.services.ram;

import core.monitor.entidades.hd.HdDadosEstaticos;
import core.monitor.entidades.maquina.MaquinaCorporativa;
import core.monitor.entidades.memoria.RamDadosEstaticos;
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
public class RamDadosEstaticosService implements Ilooca, ITemplateJdbc {

    public void executeQueryInsertRamDadosEstaticos() {
        try {
            if (!(returnNameMachineByDatabase().equals(getSystemName()))) {
                insertRamDadosEstaticos();
                System.out.println("Dados Estáticos Inseridos");
            }else{
                insertStaticDataMysql();
            }
        } catch (DuplicateKeyException | UnknownHostException e) {
            System.out.println("Dados estaticos dessa máquina já existem!");
        }

    }

    private void insertRamDadosEstaticos() {
        MaquinaCorporativaService mcs = new MaquinaCorporativaService();

        ITemplateJdbc.con.update(
                "insert into RamDadosEstaticos "
                + "values ((?),80,(?))",
                mcs.returnExpectedIdMaquinaCorporativa()
                ,memoria.getTotal()
        );

        conMySQL.update(
               "insert into RamDadosEstaticos "
                + "values ((?),80,(?))",
                mcs.returnExpectedIdMaquinaCorporativa()
                ,memoria.getTotal()
        );
       
    }

    public String returnNameMachineByDatabase() throws UnknownHostException {
        try {
            List<MaquinaCorporativa> maquina =  ITemplateJdbc.con.query(
                    "select top 1 mc.nomeMaquina from maquinacorporativa mc "
                    + "INNER JOIN coletaram cr on mc.idMaquinaCorporativa = cr.idRam "
                    + "INNER JOIN ramdadosestaticos re on cr.idRam = re.idRamDadosEstaticos "
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
            List<RamDadosEstaticos> listaDadosEstaticosRamAzure = ITemplateJdbc.con.query(
                    "select ce.* from MaquinaCorporativa mc " +
                            "INNER JOIN ColetaRAM cc on mc.idMaquinaCorporativa = cc.idRam " +
                            "INNER JOIN RamDadosEstaticos ce on cc.idRam = ce.idRamDadosEstaticos " +
                            "where mc.nomeMaquina = '" + getSystemName() + "'",
                    new BeanPropertyRowMapper<>(RamDadosEstaticos.class)
            );

            List<RamDadosEstaticos> listaDadosEstaticosRam = conMySQL.query(
                    "select ce.* from MaquinaCorporativa mc " +
                            "INNER JOIN ColetaRAM cc on mc.idMaquinaCorporativa = cc.idRam " +
                            "INNER JOIN RamDadosEstaticos ce on cc.idRam = ce.idRamDadosEstaticos " +
                            "where mc.nomeMaquina = ?",
                    new BeanPropertyRowMapper<>(RamDadosEstaticos.class),
                    getSystemName()
            );
            if (listaDadosEstaticosRam.isEmpty()){
                conMySQL.update(
                        "insert into RamDadosEstaticos "
                                + "values ((?),80,(?))",
                        listaDadosEstaticosRamAzure.get(0).getIdRamDadosEstaticos()
                        ,memoria.getTotal()
                );
            }
        }catch (DuplicateKeyException e){
            System.out.println("Dados Estaticos Atualizados Localmente");
        }
    }

    @Override
    public String getIp() {
        return null;
    }
}
