package core.monitor.services;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.rede.Rede;
import core.monitor.entidades.maquina.MaquinaCorporativa;
import core.monitor.repositorio.Ilooca;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

import static core.monitor.jar.core.monitor.resources.ITemplateJdbc.conMySQL;

public class MaquinaCorporativaService implements Ilooca {

	public static String getSystemName() throws UnknownHostException {
		String systemName = InetAddress.getLocalHost().getHostName();
		return systemName;
	}

	public Boolean executeQueryUpdateMaquinaCorporativa() {
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
					"update MaquinaCorporativa set SistemaOperacional = (?),ip = (?) where nomeMaquina = (?)",
					maquinaCorporativa.getSistemaOperacional(), maquinaCorporativa.getIp(), maquinaCorporativa.getNomeMaquina()
			);
			System.out.println("Máquina: Atualizado concluido com êxito");
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	public Integer returnExpectedIdMaquinaCorporativa() {
		try {
			List<MaquinaCorporativa> listaQuery = con.query("select top 1 idMaquinaCorporativa from MaquinaCorporativa where nomeMaquina = '" + getSystemName() + "'",
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

	public void inserirMaquinaBancoMysql(Integer idPerfil) {
		try {
			System.out.println(validationIdMaquina());
			if(validationIdMaquina() == null){
				System.out.println("Maquina não existe!");
			}else{
				String systemName = getSystemName();
				Integer idMaquinaCorporativa = returnExpectedIdMaquinaCorporativa();

				if (idMaquinaCorporativa.equals(0)) {
					System.out.println("Id da máquina corporativa não foi Encontrado!");
					return;
				}
				MaquinaCorporativa maquinaCorporativa = new MaquinaCorporativa(
						idMaquinaCorporativa,
						getIp(),
						sistema.getSistemaOperacional(),
						systemName
				);
				conMySQL.update(
						"insert into MaquinaCorporativa values " +
								"((?),(?),(?),(?),(?))",
						idMaquinaCorporativa
						,maquinaCorporativa.getIp()
						,maquinaCorporativa.getSistemaOperacional()
						,maquinaCorporativa.getNomeMaquina()
						,idPerfil
				);
				System.out.println("Máquina Cadastrada no banco local");
			}

		} catch (DataAccessException e) {
			System.out.println("Máquina local já existe!");
		} catch (UnknownHostException e) {
			throw new RuntimeException(e);
		}

	}
	public Integer validationIdMaquina(){
		MaquinaCorporativaService maquinaCorporativa = new MaquinaCorporativaService();
		try{
			List<MaquinaCorporativa> listaDeMaquinas = conMySQL.query(
					"select idMaquinaCorporativa from MaquinaCorporativa where idMaquinaCorporativa = "+
							maquinaCorporativa.returnExpectedIdMaquinaCorporativa()
					,new BeanPropertyRowMapper<>(MaquinaCorporativa.class)
			);
			return listaDeMaquinas.get(0).getIdMaquinaCorporativa();
		} catch (DataAccessException ex) {
			return null;
		}catch (IndexOutOfBoundsException e){
			List<MaquinaCorporativa> listaDeMaquinas = con.query(
					"select idMaquinaCorporativa from MaquinaCorporativa where idMaquinaCorporativa = "+
							maquinaCorporativa.returnExpectedIdMaquinaCorporativa()
					,new BeanPropertyRowMapper<>(MaquinaCorporativa.class));

			return listaDeMaquinas.get(0).getIdMaquinaCorporativa();
		}
	}

	@Override
	public String getIp() {
		Looca looca = new Looca();
		Rede rede = looca.getRede();
		return rede.getParametros().getServidoresDns().toString();
	}

}
