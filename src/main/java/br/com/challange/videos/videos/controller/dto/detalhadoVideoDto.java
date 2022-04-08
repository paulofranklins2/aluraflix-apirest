package br.com.challange.videos.videos.controller.dto;

import br.com.challange.videos.videos.model.Video;

public class detalhadoVideoDto {
    private Long id;
    private String titulo;
    private String descricao;
    private String url;

    public detalhadoVideoDto(Video video) {
        this.id = video.getId();
        this.titulo = video.getTitulo();
        this.descricao = video.getDescricao();
        this.url = video.getUrl();
    }

    public String getUrl() {
        return url;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getTitulo() {
        return titulo;
    }

    public Long getId() {
        return id;
    }
}
