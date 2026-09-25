package br.com.nutriexpress.demo.service;

import java.util.List;

import br.com.nutriexpress.demo.exception.DadoNaoEncontradoException;
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
    // Função para evitar repetição da conversão dos dados retornados para o Controller
    // Convertendo o objeto PRATO para o DTO de resposta
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
                .map(this::convertToDTO) // Faz o mapeamento dos dados recebidos e retorna um DTO para cada elemento
                .toList(); // Retorna uma lista comum com todos os elementos coletados
    }

    // Get -> Prato especifico pelo ID ("/pratos/{id}")
    public PratoResponseDTO getPratoById(Long id){
        //Busco o prato baseado no ID, caso não seja encontrado devolve uma mensagem de erro.
        Prato prato = pratoRepository.findById(id)
                // Caso o Prato não seja encontrado ele retorna 404
                                    // Tratamento de Excessão customizado para caso não seja encontrado o DADO no banco -> 404
                .orElseThrow(() -> new DadoNaoEncontradoException("Prato de id " + id + " não encontrado"));
        return convertToDTO(prato);
    }

    // GET -> Filtra os pratos pela categoria ("/pratos?categoria={"nome"}")
    @Transactional
    public List<PratoResponseDTO> getAllPratosByCategoria(String categoria_name){
        // Valida pelo nome da categoria se ela existe dentro do banco de dados.
        Categoria categoria = categoriaRepository.findByNome(categoria_name)
                                    // Tratamento de Excessão customizado para caso não seja encontrado o DADO no banco -> 404
                .orElseThrow(() -> new DadoNaoEncontradoException( "Categoria de nome: " + categoria_name + " não encontrada."));

        // Passa o ID da categoria para o repository dos pratos buscar todos os pratos que contem esse ID,
        // e retorna lista de PratoResponseDTO para o controller
        return pratoRepository.findAllByCategoriaId(categoria.getId())
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    // GET -> Filtra pratos pela quantidade de calorias
    public List<PratoResponseDTO> getAllPratosByMaxCalorias(Double max){
        return pratoRepository.findByCaloriasLessThanEqual(max)
                .stream()
                .map(this::convertToDTO)
                .toList();
    }


    // POST -> Cria um novo prato ("/pratos")
    @Transactional
    public PratoResponseDTO createNewPrato(PratoRequestDTO pratoDTO){
        // Procura se a categoria existe baseado no nome, caso não seja uma categoria anteriormente criada
        // A criação do prato é bloqueado e o usuario recebe o Status 404
        Categoria categoria = categoriaRepository.findByNome(pratoDTO.categoria() // Busca categoria pelo nome digitado
                        //Remove espaços em branco no inicio e no final da digitação
                        .trim())
                        // Tratamento de Excessão customizado para caso não seja encontrado o DADO no banco -> 404
                        .orElseThrow(() -> new DadoNaoEncontradoException("Categoria de nome: " + pratoDTO.categoria() + " não encontrada."));
        Prato newPrato = new Prato(); // Criando o novo objeto prato e atribuindo os valores recebidos
        newPrato.setNome(pratoDTO.nome());
        newPrato.setDescricao(pratoDTO.descricao());
        newPrato.setValor(pratoDTO.valor());
        newPrato.setCategoria(categoria);
        newPrato.setCalorias(pratoDTO.calorias());
        newPrato.setQuantidade(pratoDTO.quantidade());
        newPrato.setUnidadeMedida(pratoDTO.unidadeMedida());

        pratoRepository.save(newPrato);

        return convertToDTO(newPrato); // Retorna o Prato que foi criado para o usuario
    }


    // PUT -> Atualiza um prato existente ("/pratos/{id}")
    @Transactional
    public PratoResponseDTO updatePrato(Long id, PratoRequestDTO updatedDataPrato){

        Prato prato = pratoRepository.findById(id).orElseThrow(() -> new DadoNaoEncontradoException("Prato de id " + id + " não encontrado para realizar as atualizações"));
        if(updatedDataPrato.categoria() != null) { // Condicional criada para manipulação da categoria, caso ela vier NULL na requisição, ela não é alterada.
            Categoria categoria = categoriaRepository.findByNome(updatedDataPrato.categoria() // Procura se a categoria digitada existe no banco
                            .trim())
                                                // Tratamento de Excessão customizado para caso não seja encontrado o DADO no banco -> 404
                            .orElseThrow(() -> new DadoNaoEncontradoException("Categoria de nome: " + updatedDataPrato.categoria() + " não encontrada!."));
            prato.setCategoria(categoria); // Se passar por totas as etapas ele atualiza dentro do objeto PRATO com a categoria recebida
        }

        // Verificação para recebimento de dados "NULL" pelo usuario não ter escrito nenhum dado para alteração
        prato.setNome(updatedDataPrato.nome() != null ? updatedDataPrato.nome() : prato.getNome()); //se o dado for NULL, ele mantem o dado antigo
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
        //Busca o prato com o ID recebido, caso o ID não exista ele retorna o STATUS 404
        Prato prato = pratoRepository.findById(id).orElseThrow(() -> new DadoNaoEncontradoException("Prato de id " + id + " não encontrado para ser apagado"));
        pratoRepository.delete(prato); // Deleta o prato no banco
    }
}
