package br.ufla.gac106.s2022_2.sistema.views.menus;

import java.util.ArrayList;
import java.util.Scanner;

import br.ufla.gac106.s2022_2.sistema.models.atletas.Atleta;
import br.ufla.gac106.s2022_2.sistema.persistencia.ArquivosTextoPersistenciaFactory;
import br.ufla.gac106.s2022_2.sistema.persistencia.PersistenciaFactory;
import br.ufla.gac106.s2022_2.sistema.persistencia.atletas.AtletasPersistencia;
import br.ufla.gac106.s2022_2.sistema.persistencia.comentarios.UsuariosPersistencia;
import br.ufla.gac106.s2022_2.sistema.views.UsuarioView;

public class MenuPrincipal {
    private final ArrayList<Atleta> atletas;
    public MenuModuloAdministracao menuModuloAdministracao;
    public MenuModuloAvaliacao menuModuloAvaliacao;
    public MenuModuloRelatorio menuModuloRelatorio;
    private final UsuarioView usuarioView = new UsuarioView();
    private final Scanner scanner = new Scanner(System.in);
    private final PersistenciaFactory factory = new ArquivosTextoPersistenciaFactory();
    private final UsuariosPersistencia usuariosPersistencia = factory.criarUsuariosPersistencia();
    private final AtletasPersistencia atletasPersistencia = factory.criarAtletasPersistencia();

    public MenuPrincipal(){
        usuariosPersistencia.carregarUsuarios();
        this.atletas = atletasPersistencia.carregarAtletas();
        menuModuloAdministracao = new MenuModuloAdministracao(atletas);
        menuModuloAvaliacao = new MenuModuloAvaliacao(atletas);
        menuModuloRelatorio = new MenuModuloRelatorio(atletas);
    }

    public void menu(){
        while (true) {
            System.out.println("1 - Autenticar usuário");
            System.out.println("2 - Cadastrar usuário");
            System.out.println("3 - Acessar módulo de Administração");
            System.out.println("4 - Acessar módulo de Avaliação");
            System.out.println("5 - Acessar módulo de Relatórios");
            System.out.println("6 - Sair");
            System.out.print("Opção: ");

            int opcao = Integer.parseInt(scanner.nextLine());

            if (opcao == 1) {
                usuarioView.autenticarUsuario();
            } else if (opcao == 2) {
                usuarioView.cadastrarUsuario();
            } else if (opcao == 3) {
                menuModuloAdministracao.menu();
            }else if(opcao == 4){
                menuModuloAvaliacao.menu();
            }else if(opcao == 5){
                menuModuloRelatorio.menu();
            }
            else if (opcao == 6) {
                atletasPersistencia.salvarAtletas(atletas);
                break;
            } else {
                System.out.println("Opção inválida!");
            }
        }
    }
    
}