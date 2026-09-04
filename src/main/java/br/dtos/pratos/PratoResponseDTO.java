package br.dtos.pratos;

import br.com.nutriexpress.demo.model.Prato;

public class PratoResponseDTO {
    private Long id;
    private String nome;
    private String descricao;
    private Double preco;
    private Integer calorias;
    private Boolean disponivel;
    private Long categoriaId;

    public PratoResponseDTO(Prato prato){
        this.id = prato.getId();
        this.nome = prato.getNome();
        this.descricao = prato.getDescricao();
        this.preco = prato.getPreco();
        this.calorias = prato.getCalorias();
        this.disponivel = prato.getDisponivel();
        this.categoriaId = prato.getCategoriaId();
    }
}
