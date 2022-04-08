package br.com.challange.videos.videos.form;

import br.com.challange.videos.videos.model.Categoria;
import br.com.challange.videos.videos.repository.CategoriaRepository;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class CategoriaForm {

    @NotEmpty
    @Size(min = 5)
    private String titulo;
    @NotEmpty
    @Size(min = 6)
    private String color;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Categoria converter(CategoriaRepository categoriaRepository) {
        return new Categoria(titulo, color);
    }
}
