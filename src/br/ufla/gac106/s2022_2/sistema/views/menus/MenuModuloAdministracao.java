package br.ufla.gac106.s2022_2.sistema.views.menus;

import br.ufla.gac106.s2022_2.sistema.controllers.UsuarioController;
import br.ufla.gac106.s2022_2.sistema.models.atletas.Atleta;
import br.ufla.gac106.s2022_2.sistema.views.AtletaView;
import br.ufla.gac106.s2022_2.sistema.views.UsuarioView;

import java.util.ArrayList;
import java.util.Scanner;

public class MenuModuloAdministracao {
    private final AtletaView atletaView;
    private boolean podeExcluir;
    private final Scanner scanner = new Scanner(System.in);
    private final
    UsuarioView usuarioView = new UsuarioView();
    public MenuModuloAdministracao(ArrayList<Atleta> atletas){
        this.atletaView = new AtletaView(atletas);
    }

    public void menu()
    {
        if(UsuarioController.usuarioAutenticado() != null && UsuarioController.usuarioAutenticado().ehAdmin()) {
            while (true) {
                System.out.println("1 - Cadastrar atleta");
                System.out.println("2 - Visualizar atletas");
                System.out.println("3 - Ver detalhes de atleta");
                System.out.println("4 - Excluir atleta");
                System.out.println("5 - Cadastrar usuario");
                System.out.println("6 - Sair");
                System.out.print("Opção: ");

                int opcao = scanner.nextInt();
                scanner.nextLine();

                if (opcao == 1) {
                    atletaView.cadastrarAtleta();
                } else if (opcao == 2) {
                    atletaView.visualizarAtletas();
                } else if (opcao == 3) {
                    atletaView.verDetalhesAtleta();
                } else if (opcao == 4) {
                    if (UsuarioController.usuarioAutenticado().ehAdmin()) {
                        atletaView.excluirAtleta();
                    } else {
                        System.out.println("Não tem autorização para excluir atleta");
                    }
                } else if (opcao == 5) {
                    usuarioView.cadastrarUsuario();
                } else if (opcao == 6) {
                    break;
                } else {
                    System.out.println("Opção inválida!");
                }
            }
        }else {
            System.out.println("O usuario devera estar autenticado como Administrador para acessar esse módulo.");
        }
    }
}
