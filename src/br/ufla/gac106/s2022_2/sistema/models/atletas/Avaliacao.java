package br.ufla.gac106.s2022_2.sistema.models.atletas;

import br.ufla.gac106.s2022_2.sistema.models.usuarios.Usuario;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Avaliacao {
    private Map<String, Double> classificacoes = new HashMap<>();
    private ArrayList<Comentario> comentarios;
    private Double mediaClassificacoes = 0.0;

    public Avaliacao(){
        this.comentarios = new ArrayList<Comentario>();
    }

    public void setClassificacoes(Map<String,Double> classificacoes) {
        this.classificacoes = classificacoes;
        mediaClassificacoes = calcularMediaClassificacoes();
    }
    public boolean setClassificacao(Double classificacao, String nomeUsuario){
        if(classificacao >= 0 && classificacao <= 10){
            this.classificacoes.put(nomeUsuario, classificacao);
            return true;
        }
        return false;
    }

    public Double getClassificacaoDoUsuario(String nomeUsuario) {
        return classificacoes.getOrDefault(nomeUsuario, -1.0);
    }

    public void setMediaClassificacoes(double mediaClassificacoes) {
        this.mediaClassificacoes = mediaClassificacoes;
    }

    public Double getMediaClassificacoes(){
        if(mediaClassificacoes == null){
            return calcularMediaClassificacoes();
        }
        return mediaClassificacoes;
    }

    public Map<String, Double> getClassificacoes() {
        return classificacoes;
    }

    public double calcularMediaClassificacoes() {
        double somaClassificacoes = 0;
        int quantidadeClassificacoes = 0;
        for (Double classificacao : this.classificacoes.values()) {
            somaClassificacoes += classificacao;
            quantidadeClassificacoes++;
        }
        if (quantidadeClassificacoes == 0) {
            return 0; // não há classificações, retorna 0
        }

        return somaClassificacoes / quantidadeClassificacoes;
    }

    public void setComentario(Usuario author, String text) {
        Comentario comment = new Comentario(author, text);
        comentarios.add(comment);
    }
    public void setComentarios(ArrayList<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    public ArrayList<Comentario> getComentarios(){
        return comentarios;
    }
}
