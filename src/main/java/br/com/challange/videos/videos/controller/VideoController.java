package br.com.challange.videos.videos.controller;

import java.net.URI;
import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import javax.validation.Valid;

import br.com.challange.videos.videos.config.validacao.NotFoundException;
import br.com.challange.videos.videos.controller.dto.detalhadoVideoDto;
import br.com.challange.videos.videos.form.AtualizacaoVideoForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    //methodFindAll
    @GetMapping
    public List<VideoDto> findAll() {
        List<Video> videos = videoRepository.findAll();
        return VideoDto.converter(videos);
    }

    @GetMapping("/{id}")
    public detalhadoVideoDto findById(@PathVariable Long id) {
        Video video = videoRepository.getOne(id);
        return new detalhadoVideoDto(video);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<VideoDto> cadastrarVideo(@RequestBody @Valid VideoForm form, UriComponentsBuilder uriBuilder) {
        Video video = form.converter(videoRepository);
        video = videoRepository.save(video);
        URI uri = uriBuilder.path("/videos/{id}").buildAndExpand(video.getId()).toUri();
        return ResponseEntity.created(uri).body(new VideoDto(video));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<VideoDto> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizacaoVideoForm form) {
        Video video = form.atualizar(id, videoRepository);
        return ResponseEntity.ok(new VideoDto(video));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<VideoDto> remover(@PathVariable Long id) {
        videoRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }


}
