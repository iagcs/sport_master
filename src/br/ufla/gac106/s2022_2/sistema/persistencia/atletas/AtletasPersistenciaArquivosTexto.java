package br.ufla.gac106.s2022_2.sistema.persistencia.atletas;

import br.ufla.gac106.s2022_2.sistema.controllers.UsuarioController;
import br.ufla.gac106.s2022_2.sistema.models.atletas.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class AtletasPersistenciaArquivosTexto implements AtletasPersistencia{
    private static final String ARQUIVO_ATLETAS = "atletas.txt";

    @Override
    public void salvarAtletas(List<Atleta> atletas) {
        // Implementação para salvar os atletas em arquivo de texto
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(ARQUIVO_ATLETAS));
            for (Atleta atleta : atletas) {
                String nome = atleta.getNome();
                int idade = atleta.getIdade();
                float altura = atleta.getAltura();
                float peso = atleta.getPeso();
                String tipo = atleta.getTipo();
                Double media = atleta.getMediaClassificacoes();
                String descricao = atleta.getDescricaoEsportePraticado();
                ArrayList<Comentario> comentarios = atleta.getComentarios();

                if (atleta.getTipo().equals("Atletismo")) {
                    AtletaDeAtletismo atletismo = (AtletaDeAtletismo) atleta;
                    String modalidade = atletismo.getModalidade();
                    bw.write(nome + "," + idade + "," + altura + "," + peso + "," + tipo + "," + modalidade + "," + descricao + "," +media);
                    if(!comentarios.isEmpty()){
                        bw.write(",");
                        for(Comentario comentario : comentarios){
                            bw.write(comentario.getAuthor().getNome() + "#" + comentario.getText());
                            if (comentarios.indexOf(comentario) != comentarios.size() - 1) {
                                bw.write("@");
                            }
                        }
                    }
                    bw.newLine();
                } else if (atleta.getTipo().equals("Futebol")) {
                    AtletaDeFutebol futebol = (AtletaDeFutebol) atleta;
                    String posicao = futebol.getPosicao();
                    String pernaBoa = futebol.getPernaBoa();
                    Boolean titular = futebol.getTitular();
                    String time = futebol.getTime();
                    bw.write(nome + "," + idade + "," + altura + "," + peso + "," + tipo + "," + posicao + "," + pernaBoa + "," + titular + "," + time + "," + descricao + "," + media);
                    if(!comentarios.isEmpty()) {
                        bw.write(",");
                        for (Comentario comentario : comentarios) {
                            bw.write(comentario.getAuthor().getNome() + "#" + comentario.getText());
                            if (comentarios.indexOf(comentario) != comentarios.size() - 1) {
                                bw.write("@");
                            }
                        }
                    }
                    bw.newLine();
                }else if (atleta.getTipo().equals("Futevolei")) {
                    AtletaDeFutevolei futevolei = (AtletaDeFutevolei) atleta;
                    String lado = futevolei.getLado();
                    String melhorAtaque = futevolei.getMelhorAtaque();
                    bw.write(nome + "," + idade + "," + altura + "," + peso + "," + tipo + "," + lado + "," + melhorAtaque + "," + descricao + "," + media);
                    if(!comentarios.isEmpty()) {
                        bw.write(",");
                        for (Comentario comentario : comentarios) {
                            bw.write(comentario.getAuthor().getNome() + "#" + comentario.getText());
                            if (comentarios.indexOf(comentario) != comentarios.size() - 1) {
                                bw.write("@");
                            }
                        }
                    }
                    bw.newLine();
                }
            }
            bw.close();
        } catch (Exception e) {
            System.out.println("Erro ao salvar atletas");
        }
    }

    @Override
    public ArrayList<Atleta> carregarAtletas() {
        ArrayList<Atleta> atletas = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(ARQUIVO_ATLETAS));
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(",");
                if (dados[4].equals("Atletismo")) {
                    System.out.println(dados[4]);
                    Atleta atleta = new AtletaDeAtletismo(dados[0], Integer.parseInt(dados[1]), Float.parseFloat(dados[2]),  Float.parseFloat(dados[3]), dados[5], dados[6]);
                    atleta.setMediaClassificacoes(Double.parseDouble(dados[7]));
                    ArrayList<Comentario> comentarios = new ArrayList<>();
                    if(dados.length > 8) {
                        String[] comentariosCarregados = dados[8].split("@");
                        for (int i = 0; i < comentariosCarregados.length; i++) {
                            String[] dadosComentario = comentariosCarregados[i].split("#");
                            comentarios.add(new Comentario(UsuarioController.pegaPorNome(dadosComentario[0]), dadosComentario[1]));
                        }
                        atleta.setComentarios(comentarios);
                    }
                    atletas.add(atleta);
                } else if (dados[4].equals("Futebol")) {
                    Atleta atleta = new AtletaDeFutebol(dados[0], Integer.parseInt(dados[1]), Float.parseFloat(dados[2]),  Float.parseFloat(dados[3]), dados[5], dados[6], Boolean.parseBoolean(dados[7]), dados[8], dados[9]);
                    atleta.setMediaClassificacoes(Double.parseDouble(dados[10]));
                    ArrayList<Comentario> comentarios = new ArrayList<>();
                    if(dados.length > 11) {
                        String[] comentariosCarregados = dados[11].split("@");
                        for (int i = 0; i < comentariosCarregados.length; i++) {
                            String[] dadosComentario = comentariosCarregados[i].split("#");
                            comentarios.add(new Comentario(UsuarioController.pegaPorNome(dadosComentario[0]), dadosComentario[1]));
                        }
                        atleta.setComentarios(comentarios);
                    }
                    atletas.add(atleta);
                }else if(dados[4].equals("Futevolei")){
                    Atleta atleta = new AtletaDeFutevolei(dados[0], Integer.parseInt(dados[1]), Float.parseFloat(dados[2]),  Float.parseFloat(dados[3]), dados[5], dados[6], dados[7]);
                    atleta.setMediaClassificacoes(Double.parseDouble(dados[8]));
                    ArrayList<Comentario> comentarios = new ArrayList<>();
                    if(dados.length > 9) {
                        String[] comentariosCarregados = dados[9].split("@");
                        for (String comentario : comentariosCarregados) {
                            String[] dadosComentario = comentario.split("#");
                            comentarios.add(new Comentario(UsuarioController.pegaPorNome(dadosComentario[0]), dadosComentario[1]));
                        }
                        atleta.setComentarios(comentarios);
                    }
                    atletas.add(atleta);
                }
            }
            br.close();
        } catch (Exception e) {
            System.out.println("Erro ao carregar atletas: " + e.getMessage());
        }

        return atletas;
    }
}
