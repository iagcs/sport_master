package br.ufla.gac106.s2022_2.sistema.persistencia.atletas;

import br.ufla.gac106.s2022_2.sistema.models.atletas.Atleta;

import java.util.ArrayList;
import java.util.List;

public interface AtletasPersistencia {
    void salvarAtletas(List<Atleta> atletas);
    ArrayList<Atleta> carregarAtletas();
}
