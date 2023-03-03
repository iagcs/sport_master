package br.ufla.gac106.s2022_2.sistema.models.usuarios;

public class Moderador extends Usuario {
    public Moderador(String nome, String senha) {
        super(nome, senha);
    }

    @Override
    public boolean podeAcessarModuloAdmin() {
        return true;
    }
}