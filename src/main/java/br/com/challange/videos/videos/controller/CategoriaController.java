package br.com.challange.videos.videos.controller;

import br.com.challange.videos.videos.controller.dto.CategoriaDto;
import br.com.challange.videos.videos.controller.dto.VideoDto;
import br.com.challange.videos.videos.form.AtualizacaoCategoriaForm;
import br.com.challange.videos.videos.form.CategoriaForm;
import br.com.challange.videos.videos.model.Categoria;
import br.com.challange.videos.videos.model.Video;
import br.com.challange.videos.videos.repository.CategoriaRepository;
import br.com.challange.videos.videos.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categoria")
public class CategoriaController extends ResponseEntityExceptionHandler {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private VideoRepository videoRepository;

    @GetMapping
    public List<CategoriaDto> findAll() {
        List<Categoria> categoria = categoriaRepository.findAll();
        return CategoriaDto.converter(categoria);
    }

    @GetMapping("/page")
    public Page<CategoriaDto> findAll(int page){
        Pageable pageable = PageRequest.of(page, 5);
        Page<Categoria> categorias = categoriaRepository.findAll(pageable);
        return CategoriaDto.converterToCategoriasDtoPage(categorias);
    }

    @GetMapping("/")
    public Page<CategoriaDto> showCategories(@RequestParam int page) {
        Pageable pageable = PageRequest.of(page, 2);
        Page<Categoria> categorias = categoriaRepository.findAll(pageable);
        return CategoriaDto.converterToCategoriasDtoPage(categorias);
    }

    @GetMapping("/{id}")
    public CategoriaDto findById(@PathVariable Long id) {
        Categoria categoria = categoriaRepository.getOne(id);
        return new CategoriaDto(categoria);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<CategoriaDto> cadastrarVideo(@RequestBody @Valid CategoriaForm categoriaForm, UriComponentsBuilder uriBuilder) {
        Categoria categoria = categoriaForm.converter(categoriaRepository);
        categoria = categoriaRepository.save(categoria);

        URI uri = uriBuilder.path("/categoria/{id}").buildAndExpand(categoria.getId()).toUri();
        return ResponseEntity.created(uri).body(new CategoriaDto(categoria));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<CategoriaDto> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizacaoCategoriaForm form) {
        Categoria categoria = form.atualizar(id, categoriaRepository);
        return ResponseEntity.ok(new CategoriaDto(categoria));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<CategoriaDto> remover(@PathVariable Long id) {
        categoriaRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}/videos")
    public ResponseEntity<List<VideoDto>> findByIdCategoriaId(@PathVariable Long id) {
        Optional<Categoria> categoria = categoriaRepository.findById(id);

        if (!categoria.isPresent()) {
            throw new EntityNotFoundException();
        } else {
            List<Video> videosPorCategoria = videoRepository.findByCategoriaId(id);
            List<VideoDto> videos = VideoDto.converter(videosPorCategoria);
            return ResponseEntity.ok().body(videos);
        }
    }


}
