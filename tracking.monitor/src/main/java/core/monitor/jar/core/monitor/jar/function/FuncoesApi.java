package core.monitor.jar.core.monitor.jar.function;

import core.monitor.entidades.cpu.ColetaCpu;
import core.monitor.entidades.hd.ColetaHd;
import core.monitor.entidades.memoria.ColetaRam;
import core.monitor.repositorio.Ilooca;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

public class FuncoesApi implements Ilooca {

    public Double buscarUsoAtualCPU(){
      return looca.getProcessador().getUso();
    }
    public Integer buscarUsoAtualHD(){
        Long hd = looca.getGrupoDeDiscos().getVolumes().get(0).getDisponivel();
        int doisPrimeirosDigitos = (int) (hd / (long) Math.pow(10, (int) Math.log10(hd) - 1));
        return doisPrimeirosDigitos;
    }
    public Integer buscarUsoAtualRAM(){
        Long ram = looca.getMemoria().getEmUso();
        int doisPrimeirosDigitos = (int) (ram / (long) Math.pow(10, (int) Math.log10(ram) - 1));
        return doisPrimeirosDigitos;
    }

    public Double buscarBancoCpuPico() throws UnknownHostException {
        List<ColetaCpu> cpu = con.query("SELECT MAX(usoAtual) cc.usoAtual FROM maquinacorporativa mc \n" +
                        "INNER JOIN coletacpu cc ON cc.fkMaquina = mc.idMaquinaCorporativa \n" +
                        "WHERE fkMaquina = (select idMaquinaCorporativa from MaquinaCorporativa where nomeMaquina = '" + getSystemName()+"') "
                , new BeanPropertyRowMapper<>(ColetaCpu.class));
       return cpu.get(0).getUsoAtual();
    }

    public Double buscarBancoCpuUltimo() throws UnknownHostException {
        List<ColetaCpu> cpu = con.query("SELECT TOP 1 cc.usoAtual FROM maquinacorporativa mc \n" +
                        "INNER JOIN coletacpu cc ON cc.fkMaquina = mc.idMaquinaCorporativa \n" +
                        "WHERE fkMaquina = (select idMaquinaCorporativa from MaquinaCorporativa where nomeMaquina = '" + getSystemName()+"') "+
                        "order by cc.idCPU desc"
                , new BeanPropertyRowMapper<>(ColetaCpu.class));
       return cpu.get(0).getUsoAtual();

    }
//TODO: FAZER A BUSCA DO HD FUNCIONAR
    public Double buscarBancoHDPico() throws UnknownHostException {
        List<ColetaHd> hd = con.query("SELECT cc.disponivel FROM maquinacorporativa mc " +
                        "INNER JOIN coletahd cc ON mc.idMaquinaCorporativa = cc.idHD " +
                        "WHERE fkMaquina = (select idMaquinaCorporativa from MaquinaCorporativa where nomeMaquina = '" + getSystemName()+"')" +
                        "order by cc.idHD desc"
                , new BeanPropertyRowMapper<>(ColetaHd.class));
        return hd.get(0).getDisponivel();
    }

    public Double buscarBancoHDUltimo() throws UnknownHostException {
        List<ColetaHd> hd = con.query("SELECT cc.disponivel FROM maquinacorporativa mc " +
                        "INNER JOIN coletahd cc ON mc.idMaquinaCorporativa = cc.idHD " +
                        "WHERE fkMaquina = (select idMaquinaCorporativa from MaquinaCorporativa where nomeMaquina = '" + getSystemName()+"')" +
                        "order by cc.idHD desc"
                , new BeanPropertyRowMapper<>(ColetaHd.class));
        System.out.println(hd.get(0).getIdHd());
        return hd.get(0).getDisponivel();
    }

    public Double buscarBancoRAM() throws UnknownHostException {
        List<ColetaRam> ram = con.query("SELECT cc.usoAtual FROM maquinacorporativa mc " +
                        "INNER JOIN coletaram cc ON mc.idMaquinaCorporativa = cc.idram " +
                        "WHERE fkMaquina = (select idMaquinaCorporativa from MaquinaCorporativa where nomeMaquina = '" + getSystemName()+"')" +
                        "order by cc.idram desc"
                , new BeanPropertyRowMapper<>(ColetaRam.class));
        return ram.get(0).getUsoAtual();
    }

    public Double buscarBancoRAMUltimo() throws UnknownHostException {
        List<ColetaRam> ram = con.query("SELECT cc.usoAtual FROM maquinacorporativa mc " +
                        "INNER JOIN coletaram cc ON mc.idMaquinaCorporativa = cc.idram " +
                        "WHERE fkMaquina = (select idMaquinaCorporativa from MaquinaCorporativa where nomeMaquina = '" + getSystemName()+"')" +
                        "order by cc.idram desc"
                , new BeanPropertyRowMapper<>(ColetaRam.class));
        System.out.println(ram.get(1).getUsoAtual());
        return ram.get(1).getUsoAtual();
    }



    @Override
    public String getIp() {
        return null;
    }

    private static String getSystemName() throws UnknownHostException {
        String systemName = InetAddress.getLocalHost().getHostName();
        return systemName;
    }

}
