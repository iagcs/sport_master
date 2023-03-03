package br.ufla.gac106.s2022_2.sistema.models.atletas;

import br.ufla.gac106.s2022_2.sistema.models.usuarios.Usuario;
import java.util.Date;

public class Comentario {
    private final Usuario author;
    private final String text;
    private final Date date;

    public Comentario(Usuario author, String text) {
        this.author = author;
        this.text = text;
        this.date = new Date();
    }

    public Usuario getAuthor() {
        return author;
    }

    public String getText() {
        return text;
    }

    public Date getDate() {
        return date;
    }
}
