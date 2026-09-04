package br.com.nutriexpress.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.nutriexpress.demo.model.Prato;
import br.com.nutriexpress.demo.repository.PratoRepository;

@Service
public class PratoService {
    
    @Autowired
    private PratoRepository repository;
    
    public PratoService(PratoRepository repository){
        this.repository = repository;
    }

    // Get -> Listando todos os pratos ("/pratos")
    public List<Prato> listarPratos(){
        return repository.findAll();
    }

    // Get -> Prato especifico pelo ID ("/pratos/{id}")
    public Prato getPratoById(Long id){
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Prato com o ID: " + id + "não foi encontrado"));
    }

    // GET -> Filtra os pratos pela categoria ("/pratos?categoria={categoria}")

    public Prato getPratoByCategoria(Long categoriaId){

    }

    // POST -> Cria um novo prato ("/pratos")


    // PUT -> Atualiza um prato existente ("/pratos/{id}")


    // DELETE -> Deleta o prato ("/pratos/{id}")
}
