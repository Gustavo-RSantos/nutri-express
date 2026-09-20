package br.com.nutriexpress.demo.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.nutriexpress.demo.model.Categoria;
import br.com.nutriexpress.demo.repository.CategoriaRepository;

@Configuration
public class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(CategoriaRepository repository) {
        return args -> {
//            Categoria categoria1 = new Categoria();
//            categoria1.setNome("Categoria 1");
//            categoria1.setDescricao("Descrição da categoria 1");
//
//            Categoria categoria2 = new Categoria();
//            categoria2.setNome("Categoria 2");
//            categoria2.setDescricao("Descrição da categoria 2");
//
//            log.info("Preloading " + repository.save(categoria1));
//            log.info("Preloading " + repository.save(categoria2));
        };
    }
}
