package core.monitor.jar.core.monitor.jar;

public class Usuario {

    private Integer idPerfil; 
    private String nome;
    private String cpf;
    private String email;
    private String senha;
    private Integer perfilAdministrador;


    public Usuario(Integer id, String nome, String email, String senha ,String cpf, Integer perfilAdministrador) {
        this.idPerfil = id;
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.senha = senha;
        this.perfilAdministrador = perfilAdministrador;
    }

    public Usuario() {
    }

    public Integer getIdPerfil() {
        return idPerfil;
    }

    public Integer getPerfilAdministrador() {
        return perfilAdministrador;
    }

    public void setPerfilAdministrador(Integer perfilAdministrador) {
        this.perfilAdministrador = perfilAdministrador;
    }

    public void setIdPerfil(Integer idPerfil) {
        this.idPerfil = idPerfil;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public String toString() {
        return "\nUsuario:\n" +
                "id: " + idPerfil +
                "\n nome: '" + nome + '\'' +
                "\n cpf: '" + cpf + '\'' +
                "\n email: '" + email + '\'' +
                "\n senha: '" + senha + '\'' +
                "\n cargo: " + perfilAdministrador +
                '\n';
    }
}
