package br.dtos.pratos;

import br.com.nutriexpress.demo.model.Prato;

// public record PratoResponseDTO(String nome, String descricao, Double preco, Integer calorias, String categoria) {
//     public static PratoResponseDTO toDTO(Prato prato){
//         return new PratoResponseDTO( 
//             prato.getNome(), 
//             prato.getDescricao(),
//             prato.getPreco(),  
//             prato.getCalorias(),
//             prato.getCategoriaId());
//     }
// }


public class PratoResponseDTO {
    Long id;
    String nome;
    String descricao;
    Double preco; 
    Integer calorias; 

    public PratoResponseDTO(Prato prato) {
        this.id = prato.getId();
        this.nome = prato.getNome();
        this.descricao = prato.getDescricao();
        this.preco = prato.getPreco();               
        this.calorias = prato.getCalorias();
    }
}
