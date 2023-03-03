package br.ufla.gac106.s2022_2.sistema.views;

import br.ufla.gac106.s2022_2.sistema.controllers.UsuarioController;

import java.util.Scanner;

public class UsuarioView {
    private final Scanner scanner = new Scanner(System.in);

    public void cadastrarUsuario(){
        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        if(UsuarioController.usuarioAutenticado() != null && UsuarioController.usuarioAutenticado().ehAdmin()){
            System.out.print("Tipo usuario(Administrador/Moderador/Usuario): ");
            String tipo = scanner.nextLine();
            if(UsuarioController.cadastrarUsuario(nome, senha, tipo)){
                System.out.println("Usuario cadastrado!");
                return;
            }
        }

        if(UsuarioController.cadastrarUsuario(nome, senha, null)){
            System.out.println("Usuario cadastrado!");
            return;
        }

        System.out.println("Houve problemas ao cadastrar usuario!");
    }

    public void autenticarUsuario(){
        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        if(UsuarioController.autenticarUsuario(nome, senha)){
            System.out.println("Usuario autenticado com sucesso!");
            return;
        }

        System.out.println("Falha ao autenticar usuario!");
    }
}
