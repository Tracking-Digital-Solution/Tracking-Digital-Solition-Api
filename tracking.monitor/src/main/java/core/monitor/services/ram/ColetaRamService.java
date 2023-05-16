package core.monitor.services.ram;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.rede.Rede;
import static core.monitor.jar.core.monitor.resources.ITemplateJdbc.conMySQL;
import core.monitor.repositorio.Ilooca;
import core.monitor.resources.ITemplateJdbc;
import core.monitor.services.MaquinaCorporativaService;

import java.time.LocalDateTime;

public class ColetaRamService implements Ilooca, ITemplateJdbc {

    public void executeQueryInsertColetaRam() {
        MaquinaCorporativaService maquinaCorporativaService = new MaquinaCorporativaService();
        Long longUsoAtual = looca.getMemoria().getEmUso();
        Double doubleDisponivel = looca.getMemoria().getDisponivel().doubleValue();
        Integer idMaquinaCorporativa = maquinaCorporativaService.returnExpectedIdMaquinaCorporativa();
        Integer idHdDadosEstaticos = idMaquinaCorporativa;

        insertColetaRamDinamico(
                longUsoAtual,
                doubleDisponivel,
                LocalDateTime.now(),
                idMaquinaCorporativa,
                idHdDadosEstaticos
        );
        System.out.println("Insert coleta RAM: Concluído com êxito");

    }

    public void insertColetaRamDinamico(Long disponivel, Double doubleDisponivel, LocalDateTime dataHora, Integer idMaquinaCorporativa, Integer idRamDadosEstaticos) {
        ITemplateJdbc.con.update(
                 "insert into ColetaRAM "
                + "values ((?),(?),(?),(?),(?))",
                disponivel, doubleDisponivel, dataHora, idMaquinaCorporativa, idRamDadosEstaticos

        );
        conMySQL.update(
                 "insert into ColetaRAM "
                + "values (null,(?),(?),(?),(?),(?))",
                disponivel, doubleDisponivel, dataHora, idMaquinaCorporativa, idRamDadosEstaticos

        );
       
    }

    @Override
    public String getIp() {
        Looca looca = new Looca();
        Rede rede = looca.getRede();
        return rede.getParametros().getServidoresDns().toString();
    }
}
