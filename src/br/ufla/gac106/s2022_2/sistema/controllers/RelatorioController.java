package br.ufla.gac106.s2022_2.sistema.controllers;

import br.ufla.gac106.s2022_2.sistema.models.atletas.Avaliacao;
import br.ufla.gac106.s2022_2.sistema.models.atletas.Comentario;
import br.ufla.gac106.s2022_2.sistema.models.atletas.Atleta;
import br.ufla.gac106.s2022_2.sistema.models.usuarios.Usuario;

import java.util.*;

public class RelatorioController {
    private final ArrayList<Atleta> atletas;
    public RelatorioController(ArrayList<Atleta> atletas){
        this.atletas = atletas;
    }

    public Map<String, Integer> quantidadeAtletasClassificados(){
        Map<String, Integer> resultado = new HashMap<>();
        int qtdClassificados = 0;
        int qtdNaoClassificados = 0;

        for (Atleta atleta : atletas) {
            if (!atleta.getClassificacoes().isEmpty()) {
                qtdClassificados++;
            } else {
                qtdNaoClassificados++;
            }
        }

        resultado.put("classificados", qtdClassificados);
        resultado.put("naoClassificados", qtdNaoClassificados);

        return resultado;
    }

    public Map<String, List<Atleta>> top5AtletasClassificados(){
        Map<String, List<Atleta>> resultado = new HashMap<>();
        ArrayList<Atleta> atletasDeAtletismo = new ArrayList<>();
        ArrayList<Atleta> atletasDeFutebol = new ArrayList<>();
        ArrayList<Atleta> atletasDeFutevolei = new ArrayList<>();

        for (Atleta atleta : atletas) {
            if(atleta.getTipo().equals("Atletismo")){
                atletasDeAtletismo.add(atleta);
            }if(atleta.getTipo().equals("Futebol")){
                atletasDeFutebol.add(atleta);
            }else if(atleta.getTipo().equals("Futevolei")){
                atletasDeFutebol.add(atleta);
            }
        }

        Comparator<Atleta> comparador = Comparator.comparing(Atleta::getMediaClassificacoes);
        Collections.sort(atletasDeAtletismo, comparador.reversed());
        Collections.sort(atletasDeFutebol, comparador.reversed());
        Collections.sort(atletasDeFutevolei, comparador.reversed());

        resultado.put("Atletismo", atletasDeAtletismo.subList(0, Math.min(5, atletasDeAtletismo.size())));
        resultado.put("Futebol", atletasDeFutebol.subList(0, Math.min(5, atletasDeFutebol.size())));
        resultado.put("Futevolei", atletasDeFutevolei.subList(0, Math.min(5, atletasDeFutevolei.size())));

        return resultado;
    }

    public List<Map.Entry<String, Integer>> top3UsuariosMaisClassificaram(){
        Map<String, Integer> resultado = new HashMap<>();
        ArrayList<Usuario> usuarios = UsuarioController.todosUsuarios();
        int quantidadeClassificacoes = 0;

        for(Usuario usuario : usuarios){
            quantidadeClassificacoes = 0;
            for(Atleta atleta : atletas){
                if(atleta.getClassificacaoDoUsuario(usuario.getNome()) != -1.0){
                    quantidadeClassificacoes++;
                    resultado.put(usuario.getNome(), quantidadeClassificacoes);
                }
            }
        }

        List<Map.Entry<String, Integer>> lista = new ArrayList<>(resultado.entrySet());

        Collections.sort(lista, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });

        if(lista.size() > 3){
            List<Map.Entry<String, Integer>> maiores = lista.subList(0, 3);
            return maiores;
        }

        return lista;
    }

    public List<Map.Entry<String, Integer>> top3UsuariosMaisComentaram(){
        Map<String, Integer> resultado = new HashMap<>();
        ArrayList<Usuario> usuarios = UsuarioController.todosUsuarios();
        int quantidadeComentarios = 0;

        for(Usuario usuario : usuarios){
            quantidadeComentarios = 0;
            for(Atleta atleta : atletas){
                for(Comentario comentario : atleta.getComentarios()){
                    if(comentario.getAuthor().getNome().equals(usuario.getNome())){
                        ++quantidadeComentarios;
                        resultado.put(usuario.getNome(), quantidadeComentarios);
                    }
                }
            }
        }

        List<Map.Entry<String, Integer>> lista = new ArrayList<>(resultado.entrySet());

        Collections.sort(lista, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });

        if(lista.size() > 3){
            List<Map.Entry<String, Integer>> maiores = lista.subList(0, 3);
            return maiores;
        }

        return lista;
    }

    public HashMap<String, Double> getAvaliacoesGerais(){
        HashMap<String, Double>avaliacoes = new HashMap<String, Double>();

        for (Atleta atleta : atletas) {
            avaliacoes.put(atleta.getNome(), atleta.getMediaClassificacoes());
        }

        return avaliacoes;
    }
}
