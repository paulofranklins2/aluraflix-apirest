package br.com.challange.videos.videos.controller;
import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.challange.videos.videos.controller.dto.VideoDto;
import br.com.challange.videos.videos.form.VideoForm;
import br.com.challange.videos.videos.model.Video;
import br.com.challange.videos.videos.repository.VideoRepository;

@RestController // pra nao precisar colocar @ResponseBody nos methodos
@RequestMapping("/videos")
public class VideoController {

	@Autowired
	private VideoRepository videoRepository;

	@GetMapping
	public List<VideoDto> findAll() {
		List<Video> videos = videoRepository.findAll();
		return VideoDto.converter(videos);
	}

//	@GetMapping
//	public List<VideoDto> findByTitulo(String titulo) {
//		if (titulo == null) {
//			List<Video> videos = videoRepository.findAll();
//			return VideoDto.converter(videos);
//		} else {
//			List<Video> videos = videoRepository.findByTitulo(titulo);
//			return VideoDto.converter(videos);
//		}
//	}

	// adicionar um arquivo ao banco de dados com uma resposta ao 201
	@PostMapping
	public ResponseEntity<VideoDto> cadastrar(@RequestBody @Valid VideoForm form, UriComponentsBuilder uriBuilder) {
		Video video = form.converter(videoRepository);
		video = videoRepository.save(video);
		URI uri = uriBuilder.path("/videos/{id}").buildAndExpand(video.getId()).toUri();
		return ResponseEntity.created(uri).body(new VideoDto(video));
		
		
	}
//	adicionar um video ao banco de dados
//	@PostMapping
//	public void cadastrar(@RequestBody VideoForm form) {
//		Video video = form.converter(videoRepository);
//		videoRepository.save(video);
//	}

//	@GetMapping
//	public List<VideoDto> findByDescricao(String descricao){
//		List<Video> videos = videoRepository.findByDescricao(descricao);
//		return VideoDto.converter(videos);
//		
//	}

}
