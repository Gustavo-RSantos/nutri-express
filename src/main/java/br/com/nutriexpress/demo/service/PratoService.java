package br.com.nutriexpress.demo.service;

import java.util.List;

import br.com.nutriexpress.demo.model.Categoria;
import br.com.nutriexpress.demo.repository.CategoriaRepository;
import br.dtos.pratos.PratoRequestDTO;
import br.dtos.pratos.PratoResponseDTO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.nutriexpress.demo.model.Prato;
import br.com.nutriexpress.demo.repository.PratoRepository;

@Service
public class PratoService {
    @Autowired
    private PratoRepository pratoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    // Get -> Listando todos os pratos ("/pratos")
    public List<PratoResponseDTO> getAllPratos(){
        return pratoRepository.findAll()
                .stream()
                .map(prato ->
                        new PratoResponseDTO(
                            prato.getNome(),
                            prato.getDescricao(),
                            prato.getValor(),
                            prato.getCategoria(),
                            prato.getCalorias(),
                            prato.getQuantidade(),
                            prato.getUnidadeMedida()
                        )
                )
                .toList();
    }

    // Get -> Prato especifico pelo ID ("/pratos/{id}")
    public PratoResponseDTO getPratoById(Long id){
        Prato prato = pratoRepository.findById(id).orElseThrow(() -> new RuntimeException("Prato com o ID: " + id + "não foi encontrado"));
        return new PratoResponseDTO(
                prato.getNome(),
                prato.getDescricao(),
                prato.getValor(),
                prato.getCategoria(),
                prato.getCalorias(),
                prato.getQuantidade(),
                prato.getUnidadeMedida()
        );
    }

    // GET -> Filtra os pratos pela categoria ("/pratos?categoria={categoria}")


    // POST -> Cria um novo prato ("/pratos")
    @Transactional
    public PratoResponseDTO createNewPrato(PratoRequestDTO pratoDTO){
        Categoria categoria = categoriaRepository.findByNome(pratoDTO.categoria() //Procura se a categoria existe baseado no nome
                        .trim()) //Remove espaços em branco da digitação
                        .orElseThrow(() -> new RuntimeException("Categoria com o nome: " + pratoDTO.categoria() + "não foi encontrado"));

        Prato newPrato = new Prato();
        newPrato.setNome(pratoDTO.nome());
        newPrato.setDescricao(pratoDTO.descricao());
        newPrato.setValor(pratoDTO.valor());
        newPrato.setCategoria(categoria);
        newPrato.setCalorias(pratoDTO.calorias());
        newPrato.setQuantidade(pratoDTO.quantidade());
        newPrato.setUnidadeMedida(pratoDTO.unidadeMedida());

        pratoRepository.save(newPrato);

        return new PratoResponseDTO(
                newPrato.getNome(),
                newPrato.getDescricao(),
                newPrato.getValor(),
                newPrato.getCategoria(),
                newPrato.getCalorias(),
                newPrato.getQuantidade(),
                newPrato.getUnidadeMedida()
        );
    }


    // PUT -> Atualiza um prato existente ("/pratos/{id}")


    // DELETE -> Deleta o prato ("/pratos/{id}")
}
