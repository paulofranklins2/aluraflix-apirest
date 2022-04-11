package br.com.challange.videos.videos.model;


import javax.persistence.*;

@Entity
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String color;

    public Categoria(){

    }
    public Categoria(String titulo){
        this.titulo = titulo;

    }

    public Categoria(String titulo, String color){
        this.titulo = titulo;
        this.color = color;
    }

    public Long getId() {
        return id;
    }

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
}
