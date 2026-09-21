package br.dtos.pratos;

public record PratoResponseDTO(
        Long id,
        String nome,
        String descricao,
        Double valor,
        String categoria,
        Integer calorias,
        Double quantidade,
        String unidadeMedida
){}