package br.ufla.gac106.s2022_2.sistema.models.atletas;

public class AtletaDeFutebol extends Atleta{

    private final String posicao;
    private final String pernaBoa;
    private final boolean titular;
    private final String time;

    public AtletaDeFutebol(String nome, int idade, float altura, float peso, String posicao, String pernaBoa, boolean titular, String time, String descricaoEsportePraticado){
        super(nome, idade, altura, peso, "Futebol", descricaoEsportePraticado);
        this.posicao = posicao;
        this.pernaBoa = pernaBoa;
        this.titular = titular;
        this.time = time;
    }

    public String getPosicao(){
        return posicao;
    }

    public String getPernaBoa(){
        return pernaBoa;
    }

    public boolean getTitular(){
        return titular;
    }

    public String getTime(){
        return time;
    }

    public String montarDescricao(){
        return super.montarDescricao() + "Posição: " + posicao + "\n" +
                "Perna boa: " + pernaBoa + "\n" +
                "Titular: " + titular + "\n" +
                "Time: " + time;
    }
    
}
