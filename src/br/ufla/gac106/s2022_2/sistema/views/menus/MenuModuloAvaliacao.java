package br.ufla.gac106.s2022_2.sistema.views.menus;

import br.ufla.gac106.s2022_2.sistema.controllers.UsuarioController;
import br.ufla.gac106.s2022_2.sistema.models.atletas.Atleta;
import br.ufla.gac106.s2022_2.sistema.views.AtletaView;

import java.util.ArrayList;
import java.util.Scanner;

public class MenuModuloAvaliacao {
    private final AtletaView atletaView;
    private final Scanner scanner = new Scanner(System.in);
    public MenuModuloAvaliacao(ArrayList<Atleta> atletas){
        this.atletaView = new AtletaView(atletas);
    }
    public void menu(){
        if(UsuarioController.usuarioAutenticado() != null) {
            while (true) {
                System.out.println("1 - Visualizar atletas com classificacoes");
                System.out.println("2 - Filtrar atletas");
                System.out.println("3 - Adicionar comentario para atleta");
                System.out.println("4 - Classificar atleta");
                System.out.println("5 - Visualizar detalhes do atleta e suas avaliacoes");
                System.out.println("6 - Sair");
                System.out.print("Opção: ");

                int opcao = scanner.nextInt();
                scanner.nextLine();

                if (opcao == 1) {
                    atletaView.visualizarAtletasComClassificacoes();
                } else if (opcao == 2) {
                    atletaView.filtrarAtletas();
                } else if (opcao == 3) {
                    atletaView.adicionarComentarioParaAtleta();
                } else if (opcao == 4) {
                    atletaView.classificarAtleta();
                } else if (opcao == 5) {
                    atletaView.visualizarDetalhesAtletaComMedia();
                } else if (opcao == 6) {
                    break;
                } else {
                    System.out.println("Opção inválida!");
                }
            }
        }else{
            System.out.println("Necessita estar logado para acessa esse modulo!");
        }
    }
}
