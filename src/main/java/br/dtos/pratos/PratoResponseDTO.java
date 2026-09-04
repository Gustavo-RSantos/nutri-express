package br.dtos.pratos;
public record PratoResponseDTO(
    Long id,
    String nome,
    String descricao,
    Double preco,
    Integer calorias,
    String categoria
) {}
