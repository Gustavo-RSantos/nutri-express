package br.dtos.pratos;

import jakarta.validation.constraints.*;

public record PratoUpdateRequestDTO(
        // Criei esse DTO para durante o UPDATE o usuario ter a opção não escrever os campos que não quer altera tirando as notações @NotNull e @NotBlank,
        // assim permitindo a passagem do NULL na requisição e verificando apenas os dados recebidos na requisição e não permitindo dados invalidos.
        // Os NULL são tratados dentro do Services.

        @Size(min = 3, max = 100, message = "O tamanho do nome não pode exceder os 100 caracteres")
        String nome,

        @Size (min = 10, max = 200, message = "O campo deve conter entre 10 a 200 caracteres")
        String descricao,

        @PositiveOrZero(message = "O campo de preço precisa ser maior do que zero")
        Double valor,

        @Size(min = 2, max = 50, message = "O campo deve ter entre 2 a 50 caracteres")
        String categoria,

        @PositiveOrZero (message = "O nomero de calorias deve ser maior do que zero")
        Integer calorias,

        @PositiveOrZero (message = "O nomero de quantidade deve ser maior do que zero")
        Double quantidade,

        String unidadeMedida
) {}
