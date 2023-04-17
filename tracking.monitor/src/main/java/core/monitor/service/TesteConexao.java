//package core.monitor.service;
//
//import org.springframework.jdbc.core.BeanPropertyRowMapper;
//import org.springframework.jdbc.core.JdbcTemplate;
//
//import java.util.List;
//
///*
// *
// * @author gabsmvb
// */
//public class TesteConexao {
//
//    public static void main(String[] args) {
//        // Abrindo conexão
//        Conexao conexao = new Conexao();
//        JdbcTemplate con = conexao.getConexaoDoBanco();
//
//        con.execute("drop table if exists filme;");
//
//        con.execute("create table filme("
//                + "id int primary key auto_increment,"
//                + "nome varchar(45),"
//                + "ano_lancamento int"
//                + ");");
//
////      Executando o comando de insert
//        con.update("insert into filme values (null, 'Shrek 5', 2023)");
//        con.update("insert into filme values (null, 'Feitiço do Tempo', 1993)");
//        con.update("insert into filme values (null, 'Her', 2013)");
//
////        Executando o comando de insert com Filme
//        Filme novoFilme = new Filme();
//        novoFilme.setNome("Harry Potter");
//        novoFilme.setAnoLancamento(2011);
//        con.update("insert into filme values (?,?,?)",
//                novoFilme.getId(),
//                novoFilme.getNome(),
//                novoFilme.getAnoLancamento());
//
////        Lista com tipo
//        List<Filme> listaDeFilmesComTipo = con.query("select * from filme;",
//                new BeanPropertyRowMapper(Filme.class));
//
//        System.out.println(listaDeFilmesComTipo);
//
////        Executando um update na tabela
//        con.update("update filme set nome = 'Shrek 6' where id = ?;", 3);
//
//        listaDeFilmesComTipo = con.query("select * from filme;",
//                new BeanPropertyRowMapper(Filme.class));
//
//        System.out.println(listaDeFilmesComTipo);
//
////        Executando um delete na tabela
//        con.update("delete from filme where id = ?;", 3);
//
//        listaDeFilmesComTipo = con.query("select * from filme;",
//                new BeanPropertyRowMapper(Filme.class));
//
//        System.out.println(listaDeFilmesComTipo);
//
////        Buscando uma lista com where
//        List<Filme> listaDeFilmesFiltrada = con.query("select * from filme where nome = ?",
//                new BeanPropertyRowMapper(Filme.class), "Shrek 5");
//
//        System.out.println(listaDeFilmesFiltrada);
//
////        Buscando um filme com where
////        Se vier mais de um item, vai dar erro
//        Filme filme = con.queryForObject("select * from filme where nome = ?",
//                new BeanPropertyRowMapper<Filme>(Filme.class), "Shrek 5");
//
//        System.out.println(filme.getNome());
//
//    }
//}
