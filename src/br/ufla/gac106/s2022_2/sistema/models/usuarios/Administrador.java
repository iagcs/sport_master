package br.ufla.gac106.s2022_2.sistema.models.usuarios;

public class Administrador extends Usuario {
    public Administrador(String nome, String senha) {
        super(nome, senha);
    }

    @Override
    public boolean podeAcessarModuloAdmin() {
        return true;
    }

    @Override
    public boolean ehAdmin() {
        return true;
    }
}