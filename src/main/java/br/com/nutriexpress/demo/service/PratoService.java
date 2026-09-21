package br.com.nutriexpress.demo.service;

import java.util.List;

import br.com.nutriexpress.demo.model.Categoria;
import br.com.nutriexpress.demo.repository.CategoriaRepository;
import br.dtos.pratos.PratoRequestDTO;
import br.dtos.pratos.PratoResponseDTO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import br.com.nutriexpress.demo.model.Prato;
import br.com.nutriexpress.demo.repository.PratoRepository;
import org.springframework.web.server.ResponseStatusException;

@Service
public class PratoService {
    // Função para evitar repetição na conversão dos dados retornados do repository para os DTOs
    private PratoResponseDTO convertToDTO(Prato prato){
        return new PratoResponseDTO(
                prato.getId(),
                prato.getNome(),
                prato.getDescricao(),
                prato.getValor(),
                prato.getCategoria().getNome(),
                prato.getCalorias(),
                prato.getQuantidade(),
                prato.getUnidadeMedida()
        );
    }

    @Autowired
    private PratoRepository pratoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    // Get -> Listando todos os pratos ("/pratos")
    public List<PratoResponseDTO> getAllPratos(){
        return pratoRepository.findAll()
                .stream()
                .map(this::convertToDTO) // faço o mapeamento dos dados recebido do banco e devolvo um PratoResponseDTO para cada elemento.
                .toList(); // Retorna uma lista comum com todos os elementos coletados
    }

    // Get -> Prato especifico pelo ID ("/pratos/{id}")
    public PratoResponseDTO getPratoById(Long id){
        //Busco o prato baseado no ID, caso não seja encontrado devolve uma mensagem de erro.
        Prato prato = pratoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Prato de id " + id + " não encontrado")); // Caso o Prato não seja encontrado ele retorna 404
        return convertToDTO(prato);
    }

    // GET -> Filtra os pratos pela categoria ("/pratos?categoria={categoria}")


    // POST -> Cria um novo prato ("/pratos")
    @Transactional
    public PratoResponseDTO createNewPrato(PratoRequestDTO pratoDTO){
        Categoria categoria = categoriaRepository.findByNome(pratoDTO.categoria() // Procura se a categoria existe baseado no nome
                        .trim()) //Remove espaços em branco da digitação
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Categoria de nome: " + pratoDTO.categoria() + " não encontrada.")); // Caso o Prato não seja encontrado ele retorna 404

        Prato newPrato = new Prato();
        newPrato.setNome(pratoDTO.nome());
        newPrato.setDescricao(pratoDTO.descricao());
        newPrato.setValor(pratoDTO.valor());
        newPrato.setCategoria(categoria);
        newPrato.setCalorias(pratoDTO.calorias());
        newPrato.setQuantidade(pratoDTO.quantidade());
        newPrato.setUnidadeMedida(pratoDTO.unidadeMedida());

        pratoRepository.save(newPrato);

        return convertToDTO(newPrato);
    }


    // PUT -> Atualiza um prato existente ("/pratos/{id}")
    @Transactional
    public PratoResponseDTO updatePrato(Long id, PratoRequestDTO updatedDataPrato){

        Prato prato = pratoRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Prato de id " + id + " não encontrado para realizar as atualizações"));
        if(updatedDataPrato.categoria() != null) { // Condicional criada para manipulação da categoria, caso ela vier NULL na requisição, ela não é alterada.
            Categoria categoria = categoriaRepository.findByNome(updatedDataPrato.categoria() // Procura se a categoria digitada existe no banco
                            .trim())
                            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Categoria de nome: " + updatedDataPrato.categoria() + " não encontrada!."));
            prato.setCategoria(categoria); // Se passar por totas as etapas ele atualiza dentro do objeto PRATO com a categoria recebida
        }

        prato.setNome(updatedDataPrato.nome() != null ? updatedDataPrato.nome() : prato.getNome()); // Verifica se o dados recebido da API é NULL se for, ele mantem o dado antigo
        prato.setDescricao(updatedDataPrato.descricao() != null ? updatedDataPrato.descricao() : prato.getDescricao());
        prato.setValor(updatedDataPrato.valor() != null ? updatedDataPrato.valor() : prato.getValor());
        prato.setCalorias(updatedDataPrato.calorias() != null ? updatedDataPrato.calorias() : prato.getCalorias());
        prato.setQuantidade(updatedDataPrato.quantidade() != null ? updatedDataPrato.quantidade() : prato.getQuantidade());
        prato.setUnidadeMedida(updatedDataPrato.unidadeMedida() != null ? updatedDataPrato.unidadeMedida() : prato.getUnidadeMedida());

         pratoRepository.save(prato);
         return convertToDTO(prato);
    }


    // DELETE -> Deleta o prato ("/pratos/{id}")
    @Transactional
    public void deletePrato(Long id){
        Prato prato = pratoRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Prato de id " + id + " não encontrado para ser apagado"));
        pratoRepository.delete(prato);
    }
}
