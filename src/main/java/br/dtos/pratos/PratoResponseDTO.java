package br.dtos.pratos;

public record PratoResponseDTO(
        Long id,
        String nome,
        String descricao,
        Double valor,
        // Retorna apenas o nome da categoria e não o objeto completo
        // Isso evita Loop infinito no retorno dos dados do Prato
        String categoria,
        Integer calorias,
        Double quantidade,
        String unidadeMedida
){}