package br.ufla.gac106.s2022_2.sistema.models.atletas;

public class AtletaDeAtletismo extends Atleta{
    private final String modalidade;

    public AtletaDeAtletismo(String nome, int idade, float altura, float peso, String modalidade, String descricaoEsportePraticado){
        super(nome, idade, altura, peso, "Atletismo", descricaoEsportePraticado);
        this.modalidade = modalidade;
    }

    public String getModalidade(){
        return modalidade;
    }

    public String montarDescricao(){
        return super.montarDescricao() + "Modalidade: " + modalidade;
    }

}