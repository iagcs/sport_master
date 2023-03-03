package br.ufla.gac106.s2022_2.sistema.views;

import br.ufla.gac106.javaWikiAPI.JavaWikiInternalException;
import br.ufla.gac106.javaWikiAPI.PaginaWiki;
import br.ufla.gac106.javaWikiAPI.UnsuccessfulHTTPRequestException;
import br.ufla.gac106.javaWikiAPI.Wiki;
import br.ufla.gac106.s2022_2.sistema.controllers.AtletaController;
import br.ufla.gac106.s2022_2.sistema.controllers.UsuarioController;
import br.ufla.gac106.s2022_2.sistema.models.atletas.Comentario;
import br.ufla.gac106.s2022_2.sistema.models.atletas.Atleta;

import java.util.ArrayList;
import java.util.Scanner;

public class AtletaView {
    private final AtletaController atletaController;
    private final Scanner scanner;
    private final Wiki wiki;
    private PaginaWiki paginaWiki;
    public AtletaView(ArrayList<Atleta> atletas ){
        scanner = new Scanner(System.in);
        this.atletaController = new AtletaController(atletas);
        this.wiki = new Wiki();
    }

    public void cadastrarAtleta() {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Idade: ");
        int idade = Integer.parseInt(scanner.nextLine());

        System.out.print("Altura: ");
        float altura = Float.parseFloat(scanner.nextLine());

        System.out.print("Peso: ");
        float peso = Float.parseFloat(scanner.nextLine());

        System.out.print("Tipo (Atletismo/Futebol/Futevolei): ");
        String tipo = scanner.nextLine();

        try{
            paginaWiki = wiki.consultarPagina(tipo);
        }catch(JavaWikiInternalException | UnsuccessfulHTTPRequestException exception){
            System.out.println(exception.getMessage());
        }

        String descricaoEsportePraticado = paginaWiki.getResumo();

        System.out.print("Descricao" + tipo + ": " + descricaoEsportePraticado);

        if (tipo.equals("Atletismo")) {
            System.out.print("Modalidade: ");
            String modalidade = scanner.nextLine();

            atletaController.cadastrarAtletaAtletismo(nome, idade, altura, peso, modalidade, descricaoEsportePraticado);
        } else if (tipo.equals("Futebol")) {
            System.out.print("Posicao: ");
            String posicao = scanner.nextLine();

            System.out.print("Perna Boa: ");
            String pernaBoa = scanner.nextLine();

            System.out.print("Titular: (1- sim, 2 - nao)");
            int titular = Integer.parseInt(scanner.nextLine());
            boolean ehTitular = false;
            ehTitular = titular == 1;

            System.out.print("Time: ");
            String time = scanner.nextLine();

            atletaController.cadastrarAtletaFutebol(nome, idade, altura, peso, posicao, pernaBoa, ehTitular, time, descricaoEsportePraticado);
        }else if (tipo.equals("Futevolei")) {
            System.out.print("Lado que joga: ");
            String lado = scanner.nextLine();

            System.out.print("Melhor ataque: ");
            String melhorAtaque = scanner.nextLine();

            atletaController.cadastrarAtletaFutevolei(nome, idade, altura, peso, lado, melhorAtaque, descricaoEsportePraticado);
        }else {
            System.out.println("Tipo inválido!");
        }
    }

    public ArrayList<Atleta> pegaTodosAtletas(){
        return atletaController.visualizarAtletas();
    }

    public void visualizarAtletas(){
        ArrayList<Atleta> atletas = atletaController.visualizarAtletas();
        for (Atleta atleta : atletas) {
            System.out.println("Nome: " + atleta.getNome());
            System.out.println("Tipo: " + atleta.getTipo());
        }
    }

    public void verDetalhesAtleta(){
        System.out.print("Nome do atleta: ");
        String nome = scanner.nextLine();
        String atleta = atletaController.verDetalhesAtleta(nome);
        if(atleta != null){
            System.out.println(atleta);
        }else {
            System.out.println("Atleta não encontrado!");
        }
    }

    public void excluirAtleta(){
        System.out.print("Nome do atleta: ");
        String nome = scanner.nextLine();

        if(atletaController.excluirAtleta(nome)){
            System.out.println("Atleta excluído com sucesso!");
        }else{
            System.out.println("Atleta não encontrado!");
        }
    }

    public void visualizarAtletasComClassificacoes(){
        System.out.println("---------------------------------------- ");
        System.out.println("Lista de atletas com medias cadastradas: \n");
        ArrayList<Atleta> atletas = atletaController.listarComMedias();
        int iterador = 0;

        for (Atleta atleta : atletas) {
            iterador++;
            System.out.println("\t" + iterador + "- " + atleta.getNome() + " - Classificação média: " + atleta.getMediaClassificacoes());
        }
        System.out.println("---------------------------------------- ");
    }

    public Atleta visualizarDetalhesAtletaComMedia(){
        System.out.println("------------------------------------------------------------------------ ");
        System.out.print("Escolha um atleta para ver mais detalhes (digite o número correspondente): ");
        int index = Integer.parseInt(scanner.nextLine());
        ArrayList<Atleta> atletas = atletaController.listarComMedias();
        Atleta atletaEscolhido = atletas.get(index-1);

        System.out.println("\n" + atletaEscolhido.montarDescricao() + "\nMedia classificacoes: " + atletaEscolhido.getMediaClassificacoes());
        System.out.println("Comentarios: ");
        if(atletaEscolhido.getComentarios().isEmpty()) {
            System.out.println("\t(Sem comentários)");
        }
        for (Comentario comentario : atletaEscolhido.getComentarios()) {
            System.out.println("\tComentario: " + comentario.getText());
            System.out.println("\tAutor: " + comentario.getAuthor().getNome());
            System.out.println("\tData: " + comentario.getDate() + "\n");
        }
        System.out.println("------------------------------------------------------------------------ ");

        return atletaEscolhido;
    }

    public void filtrarAtletas(){
        System.out.println("------------------------------------------------------------------------ ");
        System.out.println("1- Filtrar por nome ");
        System.out.println("2- Filtrar por classificacao media ");
        System.out.println("3- Filtrar por ambas as opcoes ");
        System.out.print("Escolha uma opcao: ");
        int op = Integer.parseInt(scanner.nextLine());
        int iterador = 0;
        ArrayList<Atleta> atletas = new ArrayList<>();

        if(op == 1){
            System.out.print("\nDigite o nome a ser filtrado: ");
            String nome = scanner.nextLine();
            atletas = atletaController.filtrar(nome, null);
        }else if(op == 2){
            System.out.print("\nDigite a classificacao media a ser filtrado: ");
            Double classificacaoMedia = Double.parseDouble(scanner.nextLine());
            atletas = atletaController.filtrar(null, classificacaoMedia);
        } else if (op == 3) {
            System.out.print("\nDigite o nome a ser filtrado: ");
            String nome = scanner.nextLine();
            System.out.print("Digite a classificacao media a ser filtrado: ");
            Double classificacaoMedia = Double.parseDouble(scanner.nextLine());
            atletas = atletaController.filtrar(nome, classificacaoMedia);
        }
        System.out.println("\nAtletas filtrados: ");
        if(atletas.isEmpty()){
            System.out.println("\t(Nao ha atletas com dados fornecidos)");
        }
        for (Atleta atleta : atletas) {
            iterador++;
            System.out.println("\t" + iterador + "- " + atleta.getNome() + " - Classificação média: " + atleta.getMediaClassificacoes());
        }
        System.out.println("------------------------------------------------------------------------ ");
    }
    public void adicionarComentarioParaAtleta(){
        Atleta atletaEscolhido = visualizarDetalhesAtletaComMedia();

        System.out.print("Digite um comentario para o atleta escolhido: ");
        String comentario = scanner.nextLine();

        atletaController.comentarAtleta(atletaEscolhido, UsuarioController.usuarioAutenticado(), comentario);

        System.out.println("------------------------------------------------------------------------ ");
    }

    public void classificarAtleta(){
        Atleta atletaEscolhido = visualizarDetalhesAtletaComMedia();

        if (atletaController.getClassificacaoDoUsuario(atletaEscolhido, UsuarioController.usuarioAutenticado().getNome()) != -1.0) {
            System.out.println("Você já classificou este atleta anteriormente com a nota " + atletaController.getClassificacaoDoUsuario(atletaEscolhido, UsuarioController.usuarioAutenticado().getNome()));
            System.out.println("Deseja alterar sua classificação? (S/N)");
            String resposta = scanner.nextLine();
            if (resposta.equalsIgnoreCase("S")) {
                System.out.print("Nova Classificacao (digite um número de 0 a 10): ");
                Double classificacao = Double.parseDouble(scanner.nextLine());
                if(atletaController.classificarAtleta(atletaEscolhido, UsuarioController.usuarioAutenticado().getNome(), classificacao)){
                    System.out.println("Classificado com sucesso!");
                }else{
                    System.out.println("A classificacao deve de 0 a 10.");
                }
            }
        }else{
            System.out.print("Classifique o atleta escolhido (digite um número de 0 a 10): ");
            Double classificacao = Double.parseDouble(scanner.nextLine());
            if(atletaController.classificarAtleta(atletaEscolhido, UsuarioController.usuarioAutenticado().getNome(), classificacao)){
                System.out.println("\nClassificado com sucesso!");
            }else{
                System.out.println("\nA classificacao deve de 0 a 10.");
            }
        }
        System.out.println("------------------------------------------------------------------------ ");
    }
}
