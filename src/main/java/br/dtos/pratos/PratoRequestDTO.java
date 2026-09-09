package br.dtos.pratos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record PratoRequestDTO( 
    @NotBlank (message = "Campo de nome do prato é obrigátorio") 
    @Size (message = "O tamanho do nome não pode exceder os 100 caracteres")
    String nome,
    @Size (message = "O tamanho da descrição não pode exceder os 200 caracteres")
    String descricao,
    @NotBlank (message = "O campo de preço é obrigátorio") 
    @Positive (message = "O campo de preço precisa conter um numero positivo")
    Double preco
) 
{}
    // // private Integer calorias;
    // // private Boolean disponivel;
    
