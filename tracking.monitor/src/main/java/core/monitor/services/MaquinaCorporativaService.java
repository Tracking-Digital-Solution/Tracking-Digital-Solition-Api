package core.monitor.services;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.rede.Rede;
import core.monitor.entidades.maquina.MaquinaCorporativa;
import core.monitor.repositorio.Ilooca;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

public class MaquinaCorporativaService implements Ilooca {

	private static String getSystemName() throws UnknownHostException {
		String systemName = InetAddress.getLocalHost().getHostName();
		return systemName;
	}

	public Boolean executeQueryUpdateMaquinaCorporativa() throws UnknownHostException {
		try {
			String systemName = getSystemName();

			Integer idMaquinaCorporativa = returnExpectedIdMaquinaCorporativa();

			if (idMaquinaCorporativa.equals(0)) {
				System.out.println("Não Encontrado!");
				return false;
			}
			MaquinaCorporativa maquinaCorporativa = new MaquinaCorporativa(
					idMaquinaCorporativa,
					getIp(),
					sistema.getSistemaOperacional(),
					systemName
			);

			con.update(
					"update MaquinaCorporativa set SistemaOperacional = (?),nomeMaquina = (?) where ip = (?)",
					maquinaCorporativa.getSistemaOperacional(), maquinaCorporativa.getNomeMaquina(), maquinaCorporativa.getIp()
			);
			System.out.println("Insert de coleta de dados CPU concluidos com êxito");
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	public Integer returnExpectedIdMaquinaCorporativa() {
		try {
			List<MaquinaCorporativa> listaQuery = con.query("select idMaquinaCorporativa from MaquinaCorporativa where ip = '" + getIp() + "'",
					new BeanPropertyRowMapper<>(MaquinaCorporativa.class));
			return listaQuery.get(0).getIdMaquinaCorporativa();
		} catch (NullPointerException e) {
			System.out.println("Não há maquina cadastrada com esse ip!!");
			return 0;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return 0;
		}
	}

	@Override
	public String getIp() {
		Looca looca = new Looca();
		Rede rede = looca.getRede();
		return rede.getParametros().getServidoresDns().toString();
	}

}
