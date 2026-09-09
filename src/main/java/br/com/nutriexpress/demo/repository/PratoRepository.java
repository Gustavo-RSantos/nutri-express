// Desenvolver comunicação ao banco pelo repository
// Pontos importantes:
// - O acesso deve ser feito pelo JpaRepository para que não aja escrita de SQL manualmente
// - JPA já possui metodos prontos por funções como: save(), findById() e etc
// - PostgreeSQL deve esta rodando localmente ou via Docker
// - Adicinar "spring-boot-starter-data-jpa" e "postgresql" no pom.xml
package br.com.nutriexpress.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.nutriexpress.demo.model.Prato;

public interface PratoRepository extends JpaRepository<Prato, Long> {

}