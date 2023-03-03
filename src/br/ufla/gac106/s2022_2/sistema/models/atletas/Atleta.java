package br.ufla.gac106.s2022_2.sistema.models.atletas;

import br.ufla.gac106.s2022_2.sistema.models.usuarios.Usuario;

import java.util.ArrayList;
import java.util.Map;

public class Atleta{
    private final String nome;
    private final int idade;
    private final float altura;
    private final float peso;
    private final String tipo;
    private final String descricaoEsportePraticado;
    private final Avaliacao avaliacao;

    public Atleta(String nome, int idade, float altura, float peso, String tipo, String descricaoEsportePraticado){
        this.nome = nome;
        this.idade = idade;
        this.altura = altura;
        this.peso = peso;
        this.tipo = tipo;
        this.descricaoEsportePraticado = descricaoEsportePraticado;
        this.avaliacao = new Avaliacao();
    }

    public String getNome(){
        return nome;
    }

    public int getIdade(){
        return idade;
    }

    public float getAltura(){
        return altura;
    }

    public float getPeso(){
        return peso;
    }

    public String getTipo(){
        return tipo;
    }


    public String getDescricaoEsportePraticado(){ return descricaoEsportePraticado; }

    public String montarDescricao(){
        return "Nome: " + nome + 
                "\nIdade: " + idade +
                "\nAltura: " + altura +
                "\nPeso: " + peso +
                "\nTipo: " + tipo +
                "\nDescricao Esporte: "+ descricaoEsportePraticado;
    }

    public void setClassificacoes(Map<String, Double> classificacoes) {
        avaliacao.setClassificacoes(classificacoes);
    }

    public boolean setClassificacao(Double classificacao, String nomeUsuario) {
        return avaliacao.setClassificacao(classificacao, nomeUsuario);
    }

    public Double getClassificacaoDoUsuario(String nomeUsuario) {
        return avaliacao.getClassificacaoDoUsuario(nomeUsuario);
    }

    public void setMediaClassificacoes(double mediaClassificacoes) {
        avaliacao.setMediaClassificacoes(mediaClassificacoes);
    }

    public Double getMediaClassificacoes() {
        return avaliacao.getMediaClassificacoes();
    }

    public Map<String, Double> getClassificacoes() {
        return avaliacao.getClassificacoes();
    }

    public void setComentario(Usuario usuario, String comentario) {
        avaliacao.setComentario(usuario, comentario);
    }

    public void setComentarios(ArrayList<Comentario> comentarios) {
        this.avaliacao.setComentarios(comentarios);
    }

    public ArrayList<Comentario> getComentarios() {
        return avaliacao.getComentarios();
    }

    public Double calcularMediaClassificacoes(){
        return avaliacao.calcularMediaClassificacoes();
    }

}