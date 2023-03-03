package br.ufla.gac106.s2022_2.sistema.views.menus;

import br.ufla.gac106.s2022_2.sistema.controllers.UsuarioController;
import br.ufla.gac106.s2022_2.sistema.models.atletas.Atleta;
import br.ufla.gac106.s2022_2.sistema.views.RelatorioView;

import java.util.*;

public class MenuModuloRelatorio {
    private final Scanner scanner = new Scanner(System.in);
    private final RelatorioView relatorioView;
    public MenuModuloRelatorio(ArrayList<Atleta> atletas){
        this.relatorioView = new RelatorioView(atletas);
    }
    public void menu(){
        if(UsuarioController.usuarioAutenticado() != null) {
            while (true) {
                System.out.println("1 - Quantidade de atletas classificados");
                System.out.println("2 - Top 5 atletas melhor classificados de cada tipo");
                System.out.println("3 - Top 3 usuarios que mais cclassificaram atletas");
                System.out.println("4 - Top 3 usuarios que mais comentaram atletas");
                System.out.println("5 - Exibir grafico das avaliacoes");
                System.out.println("6 - Sair");
                System.out.print("Opção: ");

                int opcao = scanner.nextInt();
                scanner.nextLine();

                if (opcao == 1) {
                    relatorioView.quantidadeAtletasClassificados();
                } else if (opcao == 2) {
                    relatorioView.top5AtletasClassificados();
                } else if (opcao == 3) {
                    relatorioView.top3UsuariosMaisClassificaram();
                } else if (opcao == 4) {
                    relatorioView.top3UsuariosMaisComentaram();
                }else if (opcao == 5) {
                    relatorioView.graficoAvaliacoesGerais();
                }else if (opcao == 6) {
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
