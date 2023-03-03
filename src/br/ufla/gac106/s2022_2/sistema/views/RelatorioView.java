package br.ufla.gac106.s2022_2.sistema.views;

import br.ufla.gac106.s2022_2.base.Avaliacao;
import br.ufla.gac106.s2022_2.base.Avaliacoes;
import br.ufla.gac106.s2022_2.base.relatorios.Grafico;
import br.ufla.gac106.s2022_2.sistema.controllers.RelatorioController;
import br.ufla.gac106.s2022_2.sistema.models.atletas.Atleta;

import java.util.*;

public class RelatorioView {
    private final ArrayList<Atleta> atletas;
    private final RelatorioController relatorioController;
    public RelatorioView(ArrayList<Atleta> atletas){
        this.atletas = atletas;
        this.relatorioController = new RelatorioController(atletas);
    }

    public void quantidadeAtletasClassificados(){
        Map<String, Integer> quantidadeAtletasClassificados = relatorioController.quantidadeAtletasClassificados();

        for (String chave : quantidadeAtletasClassificados.keySet()) {
            System.out.println("Atletas " + chave + ": " + quantidadeAtletasClassificados.get(chave));
        }
    }

    public void top5AtletasClassificados(){
        Map<String, List<Atleta>> top5atletas = relatorioController.top5AtletasClassificados();

        for (String chave : top5atletas.keySet()) {
            System.out.println("Esporte: " + chave);
            for(Atleta atleta : top5atletas.get(chave) ){
                System.out.println(atleta.getNome() + " - " + atleta.getMediaClassificacoes());
            }
        }
    }
    public void top3UsuariosMaisClassificaram(){
        List<Map.Entry<String, Integer>> top3Usuarios = relatorioController.top3UsuariosMaisClassificaram();

        for (Map.Entry<String, Integer> entrada : top3Usuarios) {
            System.out.println(entrada.getKey() + " : " + entrada.getValue() + " vezes" );
        }
    }
    public void top3UsuariosMaisComentaram(){
        List<Map.Entry<String, Integer>> top3Usuarios = relatorioController.top3UsuariosMaisComentaram();

        for (Map.Entry<String, Integer> entrada : top3Usuarios) {
            System.out.println(entrada.getKey() + " : " + entrada.getValue() + " vezes" );
        }
    }

    public void graficoAvaliacoesGerais() {
        Avaliacoes avaliacoesAtletas = new Avaliacoes() {
            @Override
            public String temaAvaliacao() {
                return "Avaliações dos Atletas";
            }

            @Override
            public Collection<Avaliacao> colecaoAvaliacoes() {
                Collection<Avaliacao> avaliacoes = new ArrayList<>();

                Map<String, Double> avaliacoesGerais = relatorioController.getAvaliacoesGerais();

                for (String chave : avaliacoesGerais.keySet()) {
                    avaliacoes.add(new Avaliacao() {
                        @Override
                        public String nomeItemAvaliado() {
                            return chave;
                        }

                        @Override
                        public double classificacaoMedia() {
                            return avaliacoesGerais.get(chave);
                        }
                    });
                }

                return avaliacoes;
            }
        };

        Grafico grafico = new Grafico();
        grafico.exibir("Avaliações dos Atletas", avaliacoesAtletas);
    }
}
