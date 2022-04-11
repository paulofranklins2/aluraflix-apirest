package br.com.challange.videos.videos.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.challange.videos.videos.model.Categoria;
import br.com.challange.videos.videos.model.Video;
import org.springframework.data.domain.Page;

import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

public class VideoDto {

    private Long id;
    private String titulo;
    private String descricao;
    private String url;
    private String nomeCategoria;



    public VideoDto(Video video) {
        this.id = video.getId();
        this.titulo = video.getTitulo();
        this.descricao = video.getDescricao();
        this.url = video.getUrl();
        this.nomeCategoria = video.getCategoria().getTitulo();
    }


    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getUrl() {
        return url;
    }

    public String getNomeCategoria() {
        return nomeCategoria;
    }

    public static List<VideoDto> converter(List<Video> videos) {
        return videos.stream().map(VideoDto::new).collect(Collectors.toList());
    }

    public static Page<VideoDto> converterToPage(Page<Video> videos) {
        return videos.map(VideoDto::new);
    }

}
