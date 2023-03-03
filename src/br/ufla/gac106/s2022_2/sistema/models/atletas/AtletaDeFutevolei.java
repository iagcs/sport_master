package br.ufla.gac106.s2022_2.sistema.models.atletas;

public class AtletaDeFutevolei extends Atleta {
    
    private final String lado;
    private final String melhorAtaque;

    public AtletaDeFutevolei(String nome, int idade, float altura, float peso, String lado, String melhorAtaque, String descricaoEsportePraticado){
        super(nome, idade, altura, peso, "Futevolei", descricaoEsportePraticado);
        this.lado = lado;
        this.melhorAtaque = melhorAtaque;
    }

    public String getLado(){
        return lado;
    }

    public String getMelhorAtaque(){
        return melhorAtaque;
    }

    public String montarDescricao(){
        return super.montarDescricao() + "Lado: " + lado + "\n" +
                "Melhor ataque: " + melhorAtaque;
    }

}
