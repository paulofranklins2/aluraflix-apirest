package br.com.challange.videos.videos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.challange.videos.videos.model.Video;

@Repository
public interface VideoRepository extends JpaRepository<Video, Long> {

    List<Video> findByCategoriaId(Long id);

    Video findByTitulo(String titulo);
}
