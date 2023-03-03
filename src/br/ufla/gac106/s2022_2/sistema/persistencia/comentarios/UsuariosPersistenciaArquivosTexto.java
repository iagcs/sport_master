package br.ufla.gac106.s2022_2.sistema.persistencia.comentarios;
import br.ufla.gac106.s2022_2.sistema.controllers.UsuarioController;
import br.ufla.gac106.s2022_2.sistema.models.usuarios.Usuario;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class UsuariosPersistenciaArquivosTexto implements UsuariosPersistencia {
    private static final String ARQUIVO_USUARIOS = "usuarios.txt";
    @Override
    public void salvarUsuarios(List<Usuario> usuarios) {
        // Implementação para salvar os atletas em arquivo de texto
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(ARQUIVO_USUARIOS));
            for (Usuario usuario : usuarios) {
                String nome = usuario.getNome();
                String senha = usuario.getSenha();

                bw.write(nome + "," + senha);
                bw.newLine();
            }
            bw.close();
        } catch (Exception e) {
            System.out.println("Erro ao salvar atletas");
        }
    }

    @Override
    public ArrayList<Usuario> carregarUsuarios() {
        ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(ARQUIVO_USUARIOS));
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(",");
                if(dados.length > 2){
                    UsuarioController.cadastrarUsuario(dados[0], dados[1], dados[2]);
                }else{
                    UsuarioController.cadastrarUsuario(dados[0], dados[1], null);
                }
            }
            br.close();
        } catch (Exception e) {
            System.out.println("Erro ao carregar usuarios");
        }
        usuarios = UsuarioController.todosUsuarios();

        return usuarios;
    }
}
