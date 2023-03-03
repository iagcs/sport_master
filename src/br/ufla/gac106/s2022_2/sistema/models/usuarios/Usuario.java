package br.ufla.gac106.s2022_2.sistema.models.usuarios;

public class Usuario {
    private String nome;
    private final String senha;

    public Usuario(String nome, String senha) {
        this.nome = nome;
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public String getSenha(){
        return senha;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean autenticar(String senha) {
        return this.senha.equals(senha);
    }

    public boolean podeAcessarModuloAdmin() {
        return false;
    }

    public boolean ehAdmin() {
        return false;
    }
}