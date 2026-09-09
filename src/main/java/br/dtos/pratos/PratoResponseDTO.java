package br.dtos.pratos;

import br.com.nutriexpress.demo.model.Categoria;
import br.com.nutriexpress.demo.model.Prato;

public class PratoResponseDTO {
    Long id;
    String nome;
    String descricao;
    Double valor;
    Categoria categoria;
    Integer calorias;
    Double quantidade;
    String unidadeMedida;

    public PratoResponseDTO(Prato prato) {
        this.id = prato.getId();
        this.nome = prato.getNome();
        this.descricao = prato.getDescricao();
        this.valor = prato.getValor();
        this.categoria = prato.getCategoria();
        this.calorias = prato.getCalorias();
        this.quantidade = prato.getQuantidade();
        this.unidadeMedida = prato.getUnidadeMedida();
    }
}
