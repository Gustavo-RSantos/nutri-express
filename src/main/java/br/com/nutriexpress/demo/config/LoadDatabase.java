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
            // Criação de Categorias básicas fornecidas no documento de instruções da atividade na inicialização no projeto.
            Categoria categoria1 = new Categoria();
            categoria1.setNome("Vegano");
            categoria1.setDescricao("Refeições 100% vegetais, coloridas e equilibradas. Criadas artesanalmente para entregar texturas incríveis, cremosidade natural e o melhor da culinária consciente.");

            Categoria categoria2 = new Categoria();
            categoria2.setNome("Low carb");
            categoria2.setDescricao("Refeição que reduz a quantidade de carboidratos e aposta em proteínas magras, gorduras boas e vegetais frescos");

            Categoria categoria3 = new Categoria();
            categoria3.setNome("Fitness");
            categoria3.setDescricao("Refeição que destacar o sabor, o equilíbrio nutricional e o benefício para a saúde, sem parecer sem graça ou restritivo");

            log.info("Preloading " + repository.save(categoria1));
            log.info("Preloading " + repository.save(categoria2));
            log.info("Preloading " + repository.save(categoria3));
        };
    }
}
