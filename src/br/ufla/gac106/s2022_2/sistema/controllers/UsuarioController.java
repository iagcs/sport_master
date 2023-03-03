package br.ufla.gac106.s2022_2.sistema.controllers;

import br.ufla.gac106.s2022_2.sistema.models.usuarios.Administrador;
import br.ufla.gac106.s2022_2.sistema.models.usuarios.Moderador;
import br.ufla.gac106.s2022_2.sistema.models.usuarios.Usuario;
import br.ufla.gac106.s2022_2.sistema.models.usuarios.UsuarioNormal;

import java.util.Scanner;
import java.util.ArrayList;

public class UsuarioController {
    private static final ArrayList<Usuario> usuarios = new ArrayList<Usuario>();

    private static Usuario usuarioAutenticado;

    public static boolean cadastrarUsuario(String nome, String senha, String tipo) {
        if(tipo != null){
            if(tipo.equals("Administrador")){
                Usuario administrador = new Administrador(nome, senha);
                usuarios.add(administrador);
            }else if(tipo.equals("Moderador")){
                Usuario moderador = new Moderador(nome,senha);
                usuarios.add(moderador);
            }else if(tipo.equals("Usuario")){
                Usuario usuario = new UsuarioNormal(nome, senha);
                usuarios.add(usuario);
            }
            return true;
        }

        Usuario usuario = new UsuarioNormal(nome, senha);
        usuarios.add(usuario);

        return true;
    }

    public static boolean autenticarUsuario(String nome, String senha) {
        for (Usuario usuario : usuarios) {
            if (usuario.getNome().equals(nome) && usuario.autenticar(senha)) {
                usuarioAutenticado = usuario;
                return true;
            }
        }
        return false;
    }

    public static ArrayList<Usuario> todosUsuarios(){
        return usuarios;
    }

    public static Usuario pegaPorNome(String nome){
        for (Usuario usuario : usuarios) {
            if (usuario.getNome().equals(nome)){
                return usuario;
            }
        }
        return null;
    }

    public static Usuario usuarioAutenticado() {
        return usuarioAutenticado;
    }

}
