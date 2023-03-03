package br.ufla.gac106.s2022_2.sistema.persistencia.comentarios;

import br.ufla.gac106.s2022_2.sistema.models.usuarios.Usuario;

import java.util.List;

public interface UsuariosPersistencia {
    void salvarUsuarios(List<Usuario> usuarios);
    List<Usuario> carregarUsuarios();
}
