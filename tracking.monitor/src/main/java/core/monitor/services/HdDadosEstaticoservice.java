/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.monitor.services;

import core.monitor.entidades.cpu.CpuDadosEstaticos;
import core.monitor.entidades.hd.HdDadosEstaticos;
import static core.monitor.repositorio.Ilooca.processador;
import static core.monitor.resources.ITemplateJdbc.con;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

/**
 *
 * @author leska
 */
public class HdDadosEstaticoservice {

    public void executeQueryInsertHdDadosEstaticos() {
        HdDadosEstaticos hdDadosEstaticos = new HdDadosEstaticos();

        try {
            if (!(returnExpectedAlreadyDatahdDadosEstaticos().equals(getSystemName()))) {
                inserthdDadosEstaticos(hdDadosEstaticos.getNomeHd());
            }
        } catch (IllegalStateException | UnknownHostException e) {
            System.out.println("Dados estaticos dessa máquina já existem!");
        }

    }

    private void inserthdDadosEstaticos(String nomeHd) {
        con.update(
                "insert into hdDadosEstaticos(riscoHD, modelo) "
                + "values (30,(?))",
                nomeHd
        );
    }

//	public List<CpuDadosEstaticos> getAllCpuDadosEstaticos() {
//		return con.query(
//				"select * from CpuDadosEstaticos",
//				new BeanPropertyRowMapper<>(CpuDadosEstaticos.class));
//	}
    public Integer returnExpectedIdhdDadosEstaticos(Integer idhdDadosEstaticos) {
        try {
            List<HdDadosEstaticos> listaQuery = con.query("select idhdDadosEstaticos,risco from hdDadosEstaticos where idhdDadosEstaticos = '" + idhdDadosEstaticos + "'",
                    new BeanPropertyRowMapper<>(HdDadosEstaticos.class));
            return listaQuery.get(idhdDadosEstaticos - 2).getIdHdDadosEstaticos();
        } catch (NullPointerException e) {
            System.out.println("Não há dados cadastrados com esse ID!!");
            return null;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public String returnExpectedAlreadyDatahdDadosEstaticos() throws UnknownHostException {
        try {
            String nomeMaquina = con.queryForObject(
                    "select mc.nomeMaquina from maquinacorporativa mc "
                    + "INNER JOIN coletaHd cc on mc.idMaquinaCorporativa = cc.idHd "
                    + "INNER JOIN Hddadosestaticos ce on cc.idHd = ce.idHdDadosEstaticos "
                    + "where mc.nomeMaquina = '" + getSystemName() + "'",
                    new BeanPropertyRowMapper<>(String.class)
            );
            
            System.out.println(nomeMaquina);
            return nomeMaquina;
        } catch (Exception e) {
            return "Inexistente";
        }
    }

    private String getSystemName() throws UnknownHostException {
        String systemName = InetAddress.getLocalHost().getHostName();
        return systemName;
    }

//	@Override
//	public String getIp() {
//		return null;
//	}
}
