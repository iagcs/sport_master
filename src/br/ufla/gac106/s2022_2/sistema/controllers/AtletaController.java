package br.ufla.gac106.s2022_2.sistema.controllers;

import br.ufla.gac106.s2022_2.sistema.models.atletas.Atleta;
import br.ufla.gac106.s2022_2.sistema.models.atletas.AtletaDeAtletismo;
import br.ufla.gac106.s2022_2.sistema.models.atletas.AtletaDeFutebol;
import br.ufla.gac106.s2022_2.sistema.models.atletas.AtletaDeFutevolei;
import br.ufla.gac106.s2022_2.sistema.models.usuarios.Usuario;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;

public class AtletaController {
    private final ArrayList<Atleta> atletas;

    public AtletaController(ArrayList<Atleta> atletas ){
        this.atletas = atletas;
    }

    public void cadastrarAtletaAtletismo(String nome, int idade, float altura, float peso, String modalidade, String descricaoEsportePraticado) {
        AtletaDeAtletismo atletismo = new AtletaDeAtletismo(nome, idade, altura, peso, modalidade, descricaoEsportePraticado);
        atletas.add(atletismo);
    }

    public void cadastrarAtletaFutebol(String nome, int idade, float altura, float peso, String posicao, String pernaBoa, boolean ehTitular, String time, String descricaoEsportePraticado) {
        AtletaDeFutebol atleta = new AtletaDeFutebol(nome, idade, altura, peso, posicao, pernaBoa, ehTitular, time, descricaoEsportePraticado);
        atletas.add(atleta);
    }

    public ArrayList<Atleta> cadastrarAtletaFutevolei(String nome, int idade, float altura, float peso, String lado, String melhorAtaque, String descricaoEsportePraticado) {
        AtletaDeFutevolei atleta = new AtletaDeFutevolei(nome, idade, altura, peso, lado, melhorAtaque, descricaoEsportePraticado);
        atletas.add(atleta);

        return atletas;
    }

    public ArrayList<Atleta> visualizarAtletas() {
        return new ArrayList<Atleta>(atletas);
    }

    public String verDetalhesAtleta(String nome) {
        for (Atleta atleta : atletas) {
            if (atleta.getNome().equals(nome)) {
                return atleta.montarDescricao();
            }
        }
        return null;
    }

    public boolean excluirAtleta(String nome) {
        for (Atleta atleta : atletas) {
            if (atleta.getNome().equals(nome)) {
                atletas.remove(atleta);
                return true;
            }
        }
        return false;
    }

    public ArrayList<Atleta> listarComMedias() {
        ArrayList<Atleta> listaComMedias = new ArrayList<>();
        for (Atleta atleta : atletas) {
            double media = atleta.getMediaClassificacoes();
            Atleta atletaComMedia = new Atleta(atleta.getNome(), atleta.getIdade(), atleta.getAltura(), atleta.getPeso(), atleta.getTipo(), atleta.getDescricaoEsportePraticado());
            atletaComMedia.setClassificacoes(atleta.getClassificacoes());
            atletaComMedia.setMediaClassificacoes(media);
            atletaComMedia.setComentarios(atleta.getComentarios());
            listaComMedias.add(atletaComMedia);
        }
        listaComMedias.sort(Comparator.comparing(Atleta::getNome));
        return listaComMedias;
    }

    public ArrayList<Atleta> filtrar(String nome, Double media) {
        ArrayList<Atleta> listaFiltrada = new ArrayList<>();
        for (Atleta atleta : atletas) {
            if ((nome == null || atleta.getNome().toLowerCase().contains(nome.toLowerCase()))
                    && (media == null || Objects.equals(atleta.getMediaClassificacoes(), media))) {
                listaFiltrada.add(atleta);
            }
        }
        listaFiltrada.sort(Comparator.comparing(Atleta::getNome));
        return listaFiltrada;
    }

    public void comentarAtleta(Atleta atleta, Usuario usuario, String comentario){
        atleta.setComentario(usuario, comentario);
    }

    public boolean classificarAtleta(Atleta atleta, String nomeUsuario, Double classificacao){
        return atleta.setClassificacao(classificacao, nomeUsuario);
    }

    public Double getClassificacaoDoUsuario(Atleta atleta, String nome){
        return atleta.getClassificacaoDoUsuario(nome);
    }

}
