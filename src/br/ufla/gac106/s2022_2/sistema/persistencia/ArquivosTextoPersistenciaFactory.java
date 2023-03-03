package br.ufla.gac106.s2022_2.sistema.persistencia;

import br.ufla.gac106.s2022_2.sistema.persistencia.atletas.AtletasPersistencia;
import br.ufla.gac106.s2022_2.sistema.persistencia.atletas.AtletasPersistenciaArquivosTexto;
import br.ufla.gac106.s2022_2.sistema.persistencia.comentarios.UsuariosPersistencia;
import br.ufla.gac106.s2022_2.sistema.persistencia.comentarios.UsuariosPersistenciaArquivosTexto;

public class ArquivosTextoPersistenciaFactory implements PersistenciaFactory{
    @Override
    public UsuariosPersistencia criarUsuariosPersistencia() {
        return new UsuariosPersistenciaArquivosTexto();
    }

    @Override
    public AtletasPersistencia criarAtletasPersistencia() {
        return new AtletasPersistenciaArquivosTexto();
    }
}
