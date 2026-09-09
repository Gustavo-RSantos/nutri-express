package br.dtos.pratos;

import jakarta.validation.constraints.*;


public record PratoRequestDTO( 
    @NotBlank(message = "Campo de nome do prato é obrigátorio")
    @NotEmpty (message = "Campo de nome não pode estar vazio")
    @Size(min = 3, max = 100, message = "O tamanho do nome não pode exceder os 100 caracteres")
    String nome,

    @Size (min = 10, max = 200, message = "O campo deve conter entre 10 a 200 caracteres")
    String descricao,

    @NotNull(message = "O campo de preço é obrigátorio")
    @PositiveOrZero(message = "O campo de preço precisa ser maior do que zero")
    Double valor,

    @NotBlank (message = "Prato precisa conter uma categoria")
    @NotEmpty (message = "Categoria não pode estar vazia")
    @Size(min = 2, max = 50, message = "O campo deve ter entre 2 a 50 caracteres")
    String categoria,

    @NotNull (message = "Campo de calorias é obrigátorio")
    @PositiveOrZero (message = "O nomero de calorias deve ser maior do que zero")
    Integer calorias,

    @NotNull (message = "Campo de quantidade é obrigátorio")
    @PositiveOrZero (message = "O nomero de quantidade deve ser maior do que zero")
    Double quantidade,

    @NotBlank (message = "Campo de unidade de medida é obrigátorio")
    @NotEmpty (message = "O campo de unidade de medida não pode estar vazio")
    String unidadeMedida
) 
{}

    
