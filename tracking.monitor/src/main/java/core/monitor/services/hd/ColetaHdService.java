package core.monitor.services.hd;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.rede.Rede;
import core.monitor.entidades.hd.ColetaHd;
import static core.monitor.jar.core.monitor.resources.ITemplateJdbc.conMySQL;
import core.monitor.repositorio.Ilooca;
import core.monitor.resources.ITemplateJdbc;

import core.monitor.services.MaquinaCorporativaService;
import core.monitor.slack.App;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.io.IOException;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;

public class ColetaHdService implements Ilooca, ITemplateJdbc {

    public void executeQueryInsertColetaHd() throws IOException, InterruptedException {

        MaquinaCorporativaService maquinaCorporativaService = new MaquinaCorporativaService();
        Long longDisponivelHd = (discoGrupo.getVolumes().get(0).getDisponivel());
        Integer idMaquinaCorporativa = maquinaCorporativaService.returnExpectedIdMaquinaCorporativa();
        Integer idHdDadosEstaticos = idMaquinaCorporativa;

        insertColetaHdDinamico(
                longDisponivelHd,
                LocalDateTime.now(),
                idMaquinaCorporativa,
                idHdDadosEstaticos
        );
        System.out.println("Insert coleta HD: Concluído com êxito");
        try {
            App app = new App();
            app.sendMessageHD(longDisponivelHd);
            app.sendMessageAlertHD(longDisponivelHd);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }catch (IndexOutOfBoundsException e){
            System.out.println("Não Possui dados estaticos parametrizados para enviar para o slack");
        }
    }

    public void insertColetaHdDinamico(Long disponivel, LocalDateTime dataHora, Integer idMaquinaCorporativa, Integer idHdDadosEstaticos) {
        ITemplateJdbc.con.update(
                "insert into ColetaHD "
                + "values ((?),(?),(?),(?))",
                disponivel, dataHora, idMaquinaCorporativa, idHdDadosEstaticos
        );
        conMySQL.update(
               "insert into ColetaHD "
                + "values (null,(?),(?),(?),(?))",
                disponivel, dataHora, idMaquinaCorporativa, idHdDadosEstaticos
        );
     
    }

    @Override
    public String getIp() {
        Looca looca = new Looca();
        Rede rede = looca.getRede();
        return rede.getParametros().getServidoresDns().toString();
    }
}
