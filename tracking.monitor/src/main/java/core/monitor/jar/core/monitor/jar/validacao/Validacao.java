/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.monitor.jar.core.monitor.jar.validacao;

import core.monitor.jar.core.monitor.jar.ConexaoAzure;
import core.monitor.jar.core.monitor.jar.ConexaoMySql;
import core.monitor.jar.core.monitor.jar.Usuario;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static core.monitor.jar.core.monitor.resources.ITemplateJdbc.conMySQL;

/**
 *
 * @author gabsm
 */
public class Validacao {
    ConexaoAzure conexaoAzure = new ConexaoAzure();
    JdbcTemplate conAzure = conexaoAzure.getConexaoDoBancoAzure();
    
    ConexaoMySql conexaoMySql = new ConexaoMySql();
    JdbcTemplate conMySQL = conexaoMySql.getConexaoDoBancoMysql();



    public Boolean validarLoginMysql(String email, String senha){
        try {
          conMySQL.queryForObject(String.format("select * from perfil where email = '%s' and senha = '%s'", email, senha),
                    new BeanPropertyRowMapper<>(Usuario.class));
            System.out.println("Login Feito com sucesso!");
          return true;
        } catch (DataAccessException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public Integer returnIdPerfil (String email, String senha){
        try {
            List<Usuario> listaUsuario= conAzure.query(String.format("select * from Perfil where email = '%s' and senha = '%s'", email, senha),
                    new BeanPropertyRowMapper<>(Usuario.class));
            Usuario usuario = listaUsuario.get(0);
            conMySQL.update(
                    "INSERT INTO Perfil VALUES (?, ?, ?, ?, ?, ?)",
                    usuario.getIdPerfil(),
                    usuario.getNome(),
                    usuario.getEmail(),
                    usuario.getSenha(),
                    usuario.getCpf(),
                    usuario.getPerfilAdministrador()
            );

            List<Usuario> listaUsuarioMySql= conMySQL.query(String.format("select * from Perfil where idPerfil = %d",usuario.getIdPerfil()),
                    new BeanPropertyRowMapper<>(Usuario.class));
            Usuario usuarioMySql = listaUsuario.get(0);

            return usuarioMySql.getIdPerfil();
        } catch (DuplicateKeyException de){
            System.out.println("Perfil já existe");
            List<Usuario> listaUsuario= conAzure.query(String.format("select * from Perfil where email = '%s' and senha = '%s'", email, senha),
                    new BeanPropertyRowMapper<>(Usuario.class));
            Usuario usuario = listaUsuario.get(0);
            return usuario.getIdPerfil();
        } catch (DataAccessException e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }

     public Boolean validarLoginAzure(String email, String senha){
        try {
          conAzure.queryForObject(String.format("select * from perfil where email = '%s' and senha = '%s'", email, senha),
                    new BeanPropertyRowMapper<>(Usuario.class));
            System.out.println("Login Feito com sucesso!");
          return true;
        } catch (DataAccessException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
   

