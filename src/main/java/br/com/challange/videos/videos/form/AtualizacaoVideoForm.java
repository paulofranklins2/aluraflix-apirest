package br.com.challange.videos.videos.form;

import br.com.challange.videos.videos.model.Video;
import br.com.challange.videos.videos.repository.VideoRepository;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class AtualizacaoVideoForm {

    @NotEmpty
    @Size(min = 5)
    private String titulo;
    @NotEmpty
    @Size(min = 5)
    private String descricao;
    @NotEmpty
    @Size(min = 5)
    private String url;


    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getUrl() {
        return url;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Video atualizar(Long id, VideoRepository videoRepository) {
        Video video = videoRepository.getOne(id);
        video.setTitulo(this.titulo);
        video.setDescricao(this.descricao);
        video.setUrl(this.url);
        return video;
    }
}
