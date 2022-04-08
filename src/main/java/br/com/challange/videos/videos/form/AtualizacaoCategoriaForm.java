package br.com.challange.videos.videos.form;

import br.com.challange.videos.videos.model.Categoria;
import br.com.challange.videos.videos.repository.CategoriaRepository;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class AtualizacaoCategoriaForm {

    @NotEmpty
    @Size(min = 5)
    private String titulo;
    @NotEmpty
    @Size(min = 5)
    private String color;

    public String getTitulo() {
        return titulo;
    }
    public String getColor() {
        return color;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Categoria atualizar (Long id, CategoriaRepository categoriaRepository){
        Categoria  categoria = categoriaRepository.getOne(id);
        categoria.setTitulo(this.titulo);
        categoria.setColor(this.color);
        return categoria;
    }
}
