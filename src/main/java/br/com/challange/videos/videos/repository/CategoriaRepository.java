package br.com.challange.videos.videos.repository;

import br.com.challange.videos.videos.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    Categoria findByTitulo(String titulo);
}
