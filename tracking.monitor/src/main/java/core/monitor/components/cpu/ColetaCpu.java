package core.monitor.components.cpu;

import com.github.britooo.looca.api.group.processador.Processador;
import core.monitor.components.maquina.MaquinaCorporativa;
import core.monitor.service.Ilooca;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class ColetaCpu implements Ilooca {
	Processador processador = looca.getProcessador();
	MaquinaCorporativa maquina = new MaquinaCorporativa();

	Integer usoAtual = (int)Math.round(processador.getUso());
	LocalDateTime dataHora = LocalDateTime.now();
	Map<String, String> mapColetaRam = new HashMap<>();
	ColetaCpuExecute coletaRam = new ColetaCpuExecute();


	public void setMapColetaRam() {
		mapColetaRam.put("Uso", usoAtual.toString());
		mapColetaRam.put("Data", dataHora.toString());
		coletaRam.executeQueryInsertColetaCpu(usoAtual.toString(),dataHora.toString(), id);
	}

}
