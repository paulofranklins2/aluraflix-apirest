package br.com.challange.videos.videos.controller.dto;

import br.com.challange.videos.videos.model.Categoria;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public class CategoriaDto {
    private Long id;
    private String titulo;
    private String color;

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getColor() {
        return color;
    }

    public CategoriaDto(Categoria categoria) {
        this.titulo = categoria.getTitulo();
        this.id = categoria.getId();
        this.color = categoria.getColor();
    }

    public static List<CategoriaDto> converter(List<Categoria> categoriasList) {
        return categoriasList.stream().map(CategoriaDto::new).collect(Collectors.toList());
    }

    public static Page<CategoriaDto> converterToCategoriasDtoPage(Page<Categoria> categorias) {
        return categorias.map(CategoriaDto::new);
    }

}
