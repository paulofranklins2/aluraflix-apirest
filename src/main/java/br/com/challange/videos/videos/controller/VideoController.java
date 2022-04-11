package br.com.challange.videos.videos.controller;

import java.net.URI;
import java.util.*;

import javax.transaction.Transactional;
import javax.validation.Valid;

import br.com.challange.videos.videos.form.AtualizacaoVideoForm;
import br.com.challange.videos.videos.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
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
    private CategoriaRepository categoriaRepository;

    @Autowired
    private VideoRepository videoRepository;

    @GetMapping
    public List<VideoDto> findAll() {
        List<Video> videos = videoRepository.findAll();
        return VideoDto.converter(videos);
    }

    @GetMapping("/")
    @RequestMapping(params = "titulo", value = "/", method = RequestMethod.GET)
    public List<VideoDto> findByName(@Param("titulo") @RequestParam String titulo) {
        List<Video> findAll = videoRepository.findAll();

        List<Video> resultado = new ArrayList<>();
        for (int i = 0; i < findAll.size(); i++) {
            if (findAll.get(i).getTitulo().toLowerCase(Locale.ROOT).contains(titulo.toLowerCase(Locale.ROOT))) {
                resultado.add(findAll.get(i));
            }
        }
        return VideoDto.converter(resultado);
    }

    //    @GetMapping("/")
    @RequestMapping(params = "page", value = "/", method = RequestMethod.GET)
    public Page<VideoDto> videosPaginados(@RequestParam int page) {
        Sort sort = Sort.by("id").ascending();
        PageRequest pageRequest = PageRequest.of(page, 5, sort);

        Page<Video> videos = videoRepository.findAll(pageRequest);
        return VideoDto.converterToPage(videos);
    }

    @GetMapping("/teste")
    public Page<VideoDto> multipleParams(@RequestParam(required = false) Integer page, @RequestParam(required = false) String titulo) {
        if (page != null && titulo == null) {
            Sort sort = Sort.by("id").ascending();
            PageRequest pageRequest = PageRequest.of(page, 5, sort);

            Page<Video> videos = videoRepository.findAll(pageRequest);
            return VideoDto.converterToPage(videos);
        } else if (titulo != null && page == null) {
            List<Video> findAll = videoRepository.findAll();
            List<Video> resultado = new ArrayList<>();

            for (int i = 0; i < findAll.size(); i++) {
                if (findAll.get(i).getTitulo().toLowerCase(Locale.ROOT).contains(titulo.toLowerCase(Locale.ROOT))) {
                    resultado.add(findAll.get(i));
                }
            }

            Page<Video> converter = new PageImpl<>(resultado);
            return VideoDto.converterToPage(converter);
        } else {
            Sort sort = Sort.by("id").ascending();
            PageRequest pageRequest = PageRequest.of(0, 5, sort);

            Page<Video> videos = videoRepository.findAll(pageRequest);
            return VideoDto.converterToPage(videos);
        }


    }

    @GetMapping("/{id}")
    public VideoDto findById(@PathVariable Long id) {
        Video video = videoRepository.getOne(id);
        return new VideoDto(video);
    }


    @GetMapping("/free")
    public Page<VideoDto> videosGratis() {
        Sort sort = Sort.by("id").descending();
        PageRequest pageRequest = PageRequest.of(0, 5, sort);
        Page<Video> videos = videoRepository.findAll(pageRequest);
        return VideoDto.converterToPage(videos);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<VideoDto> register(@RequestBody @Valid VideoForm videoForm, UriComponentsBuilder uriBuilder) {
        Video video = videoForm.converter(categoriaRepository);
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
