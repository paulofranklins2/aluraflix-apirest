package br.com.challange.videos.videos.controller.dto;

import br.com.challange.videos.videos.model.Video;

import java.util.List;
import java.util.stream.Collectors;

public class VideoApenasNomeDto {

    //    private Long id;
    private String titulo;


    public VideoApenasNomeDto(Video video) {

        this.titulo = video.getTitulo();


    }



    public String getTitulo() {
        return titulo;
    }

    public static List<VideoApenasNomeDto> converter(List<Video> videos) {
        return videos.stream().map(VideoApenasNomeDto::new).collect(Collectors.toList());
    }

}
