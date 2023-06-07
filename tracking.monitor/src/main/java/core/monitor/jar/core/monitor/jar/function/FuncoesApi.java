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
    Double cpu = con.queryForObject(
        "SELECT MAX(cc.usoAtual) pico FROM maquinacorporativa mc " +
        "INNER JOIN coletacpu cc ON cc.fkMaquina = mc.idMaquinaCorporativa " +
        "WHERE fkMaquina = (SELECT idMaquinaCorporativa FROM MaquinaCorporativa WHERE nomeMaquina = '" + getSystemName() + "') AND DAY(cc.dataHota) = DAY(getDate())",
        Double.class
    );
    return cpu;
}

    public Double buscarBancoCpuUltimo() throws UnknownHostException {
        List<ColetaCpu> cpu = con.query("SELECT TOP 1 cc.usoAtual FROM maquinacorporativa mc \n" +
                        "INNER JOIN coletacpu cc ON cc.fkMaquina = mc.idMaquinaCorporativa \n" +
                        "WHERE fkMaquina = (select idMaquinaCorporativa from MaquinaCorporativa where nomeMaquina = '" + getSystemName()+"') "+
                        "order by cc.idCPU desc"
                , new BeanPropertyRowMapper<>(ColetaCpu.class));
       return cpu.get(0).getUsoAtual();

    }
    
    public String buscarBancoCpuStatus() throws UnknownHostException{
        String cpu = con.queryForObject("SELECT TOP 1\n" +
"    CASE\n" +
"        WHEN cc.usoAtual <= (SELECT MAX(ce.usoAtual) FROM coletaCpu ce WHERE DAY(cc.dataHota) = DAY(getDate()))\n" +
"		 THEN 'OK'\n" +
"        ELSE 'Aviso'\n" +
"    END AS Status\n" +
"FROM maquinacorporativa mc\n" +
"INNER JOIN coletacpu cc ON cc.fkMaquina = mc.idMaquinaCorporativa\n" +
"WHERE fkMaquina = (SELECT idMaquinaCorporativa FROM MaquinaCorporativa WHERE nomeMaquina = '" + getSystemName()+"') AND DAY(cc.dataHota) = DAY(getDate())\n" +
"order by cc.usoAtual desc  ",String.class);
        return cpu;
    }
    
        public String buscarBancoCpuContagem() throws UnknownHostException{
        String cpu = con.queryForObject("SELECT TOP 1\n" +
"    'Limite atingido: ' + CAST(COUNT(*) AS NVARCHAR) + 'x' AS Contagem\n" +
"FROM maquinacorporativa mc\n" +
"INNER JOIN coletacpu cc ON cc.fkMaquina = mc.idMaquinaCorporativa\n" +
"WHERE fkMaquina = (SELECT idMaquinaCorporativa FROM MaquinaCorporativa WHERE nomeMaquina = '" + getSystemName()+"') " +
"GROUP BY cc.usoAtual",String.class);
        return cpu;
    }
    
    
//TODO: FAZER A BUSCA DO HD FUNCIONAR
    public Double buscarBancoHDPico() throws UnknownHostException {
        Long hd = con.queryForObject("SELECT MAX(cc.disponivel) pico FROM maquinacorporativa mc \n" +
"        INNER JOIN coletahd cc ON cc.fkMaquina = mc.idMaquinaCorporativa \n" +
"        WHERE fkMaquina = (SELECT idMaquinaCorporativa FROM MaquinaCorporativa WHERE nomeMaquina = '"+getSystemName()+"') AND DAY(cc.dataHora) = DAY(getDate())"
                ,Long.class);

                            Double hdUsadoGB = hd / Math.pow(1024, 3);
                            Long hdTotal = looca.getGrupoDeDiscos().getTamanhoTotal();
                            Double hdTotalGB = hdTotal / (1024.0 * 1024.0 * 1024.0);
                            Double contaHD = ((hdTotalGB - hdUsadoGB)  / hdTotalGB) * 100;
        return contaHD;
    }
    
      public String buscarBancoHdStatus() throws UnknownHostException{
        String hd = con.queryForObject("SELECT TOP 1\n" +
"    CASE\n" +
"        WHEN cc.disponivel <= (\n" +
"			SELECT MAX(ce.disponivel) FROM coletaHd ce WHERE DAY(cc.dataHora) = DAY(getDate())\n" +
"			)  THEN 'OK'\n" +
"        ELSE 'Aviso'\n" +
"    END AS Status\n" +
"FROM maquinacorporativa mc\n" +
"INNER JOIN coletahd cc ON cc.fkMaquina = mc.idMaquinaCorporativa\n" +
"WHERE fkMaquina = (SELECT idMaquinaCorporativa FROM MaquinaCorporativa WHERE nomeMaquina = '"+getSystemName()+"') AND DAY(cc.dataHora) = DAY(getDate())\n" +
"order by cc.disponivel desc  ",String.class);
        return hd;
    }
    
        public String buscarBancoHdContagem() throws UnknownHostException{
        String hd = con.queryForObject("SELECT TOP 1\n" +
"    'Limite atingido: ' + CAST(COUNT(*) AS NVARCHAR) + 'x' AS Contagem\n" +
"FROM maquinacorporativa mc\n" +
"INNER JOIN coletahd cc ON cc.fkMaquina = mc.idMaquinaCorporativa\n" +
"WHERE fkMaquina = (SELECT idMaquinaCorporativa FROM MaquinaCorporativa WHERE nomeMaquina = '"+getSystemName()+"')\n" +
"GROUP BY cc.disponivel",String.class);
        return hd;
    }
    

    public Double buscarBancoRAMPico() throws UnknownHostException {
        Long ram = con.queryForObject("SELECT MAX(cc.usoAtual) pico FROM maquinacorporativa mc \n" +
"        INNER JOIN coletaram cc ON cc.fkMaquina = mc.idMaquinaCorporativa \n" +
"        WHERE fkMaquina = (SELECT idMaquinaCorporativa FROM MaquinaCorporativa WHERE nomeMaquina = '"+getSystemName()+"') AND DAY(cc.dataHora) = DAY(getDate())"
                ,Long.class);
        
              Double ramUsadoGB =  ram / Math.pow(1024, 3);
              Long ramTotal = looca.getMemoria().getTotal();
              Double ramTotalGB = ramTotal / Math.pow(1024, 3);
              Double contaRAM = ( ramUsadoGB / ramTotalGB) * 100;
        return contaRAM;
    }

    public String buscarBancoRamStatus() throws UnknownHostException {
        String hd = con.queryForObject("SELECT TOP 1\n" +
"    CASE\n" +
"        WHEN cc.usoAtual <= (\n" +
"			SELECT MAX(ce.disponivel) FROM coletaram ce WHERE DAY(cc.dataHora) = DAY(getDate())\n" +
"			)  THEN 'OK'\n" +
"        ELSE 'Aviso'\n" +
"    END AS Status\n" +
"FROM maquinacorporativa mc\n" +
"INNER JOIN coletaram cc ON cc.fkMaquina = mc.idMaquinaCorporativa\n" +
"WHERE fkMaquina = (SELECT idMaquinaCorporativa FROM MaquinaCorporativa WHERE nomeMaquina = '"+getSystemName()+"') AND DAY(cc.dataHora) = DAY(getDate())\n" +
"order by cc.disponivel desc  ",String.class);
        return hd;
    }
    
    public String buscarBancoRamContagem() throws UnknownHostException {
        String ram = con.queryForObject("SELECT TOP 1 'Limite atingido: ' + CAST(COUNT(*) AS NVARCHAR) + 'x' AS Contagem\n" +
            "FROM maquinacorporativa mc\n" +
            "INNER JOIN coletaram cc ON cc.fkMaquina = mc.idMaquinaCorporativa\n" +
            "WHERE\n" +
            "    fkMaquina = (SELECT idMaquinaCorporativa FROM MaquinaCorporativa WHERE nomeMaquina = '"+getSystemName()+"')\n" +
            "    AND cc.usoAtual <= (\n" +
            "        SELECT MAX(ce.usoAtual)\n" +
            "        FROM coletaram ce\n" +
            "        WHERE\n" +
            "            ce.dataHora >= CAST(DATEADD(DAY, DATEDIFF(DAY, 0, cc.dataHora), 0) AS DATETIME)\n" +
            "            AND ce.dataHora < DATEADD(DAY, 1, CAST(DATEADD(DAY, DATEDIFF(DAY, 0, cc.dataHora), 0) AS DATETIME))\n" +
            "    )\n" +
            "    AND DAY(cc.dataHora) = DAY(GETDATE())\n" +
            "GROUP BY cc.disponivel\n" +
            "ORDER BY cc.disponivel DESC;",String.class);
        return ram;
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
