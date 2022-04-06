package br.com.challange.videos.videos.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.challange.videos.videos.model.Video;
import br.com.challange.videos.videos.repository.VideoRepository;

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

	public Video converter(VideoRepository videoRepository) {
		return new Video(titulo, descricao, url);
	}

}
