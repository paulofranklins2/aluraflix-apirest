package br.com.challange.videos.videos.controller;

import br.com.challange.videos.videos.controller.dto.CategoriaDto;
import br.com.challange.videos.videos.controller.dto.VideoDto;
import br.com.challange.videos.videos.form.AtualizacaoCategoriaForm;
import br.com.challange.videos.videos.form.AtualizacaoVideoForm;
import br.com.challange.videos.videos.form.CategoriaForm;
import br.com.challange.videos.videos.model.Categoria;
import br.com.challange.videos.videos.model.Video;
import br.com.challange.videos.videos.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/categoria")
public class CategoriaController extends ResponseEntityExceptionHandler {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @GetMapping
    public List<Categoria> findAll() {
        List<Categoria> categorias = categoriaRepository.findAll();
        return categorias;
    }

    @GetMapping("/{id}")
    public CategoriaDto findById(@PathVariable Long id) {
        Categoria categoria = categoriaRepository.getOne(id);
        return new CategoriaDto(categoria);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<CategoriaDto> cadastrarVideo(@RequestBody @Valid CategoriaForm form, UriComponentsBuilder uriBuilder) {
        Categoria categoria = form.converter(categoriaRepository);
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


}
