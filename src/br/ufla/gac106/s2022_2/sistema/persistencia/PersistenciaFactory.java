package br.ufla.gac106.s2022_2.sistema.persistencia;

import br.ufla.gac106.s2022_2.sistema.persistencia.atletas.AtletasPersistencia;
import br.ufla.gac106.s2022_2.sistema.persistencia.comentarios.UsuariosPersistencia;

public interface PersistenciaFactory {
    UsuariosPersistencia criarUsuariosPersistencia();
    AtletasPersistencia criarAtletasPersistencia();
}
