package br.com.challange.videos.videos.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import br.com.challange.videos.videos.model.Categoria;
import br.com.challange.videos.videos.model.Video;
import br.com.challange.videos.videos.repository.CategoriaRepository;

public class VideoForm {


    @NotEmpty
    @Size(min = 5)
    private String titulo;

    @NotEmpty
    @Size(min = 5)
    private String descricao;

    @NotEmpty
    @Size(min = 5)
    private String url;

    @NotEmpty
    private String nomeCategoria;

    private String verificarTituloCategoria(String nomeCategoria) {
        if (nomeCategoria == null || nomeCategoria.isBlank()) {
            return "LIVRE";
        } else {
            return nomeCategoria;
        }
    }

    public Video converter(CategoriaRepository categoriaRepository) {
        String titulo = verificarTituloCategoria(nomeCategoria);
        Categoria categoria = categoriaRepository.findByTitulo(titulo);
        return new Video(this.titulo, this.descricao, this.url, categoria);
    }

    public String getNomeCategoria() {
        return nomeCategoria;
    }

    public void setNomeCategoria(String nomeCategoria) {
        this.nomeCategoria = nomeCategoria;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
