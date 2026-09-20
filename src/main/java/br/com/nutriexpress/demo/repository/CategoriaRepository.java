package br.com.nutriexpress.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.nutriexpress.demo.model.Categoria;

import java.util.Optional;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    // Função para buscar a categoria pelo nome,
    // para ajudar na validação que busca uma categoria com o nome digitado pelo usuario.
    Optional<Categoria> findByNome(String nome);
}
