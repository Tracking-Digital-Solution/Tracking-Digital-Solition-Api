/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.monitor.services.ram;

import core.monitor.entidades.maquina.MaquinaCorporativa;
import core.monitor.jar.core.monitor.resources.ITemplateJdbc;
import core.monitor.repositorio.Ilooca;
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
            }
        } catch (IllegalStateException | UnknownHostException e) {
            System.out.println("Dados estaticos dessa máquina já existem!");
        }

    }

    private void insertRamDadosEstaticos() {

        ITemplateJdbc.con.update(
                "insert into RamDadosEstaticos(riscoRAM, total) "
                + "values (80,(?))",
                memoria.getTotal()
        );

        conMySQL.update(
               "insert into RamDadosEstaticos(riscoRAM, total) "
                + "values (null,80,(?))",
                memoria.getTotal()
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

    @Override
    public String getIp() {
        return null;
    }
}
