package br.dtos.pratos;

import br.com.nutriexpress.demo.model.Categoria;

public record PratoResponseDTO(
        String nome,
        String descricao,
        Double valor,
        Categoria categoria,
        Integer calorias,
        Double quantidade,
        String unidadeMedida
){}