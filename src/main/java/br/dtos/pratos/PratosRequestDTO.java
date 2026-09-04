package br.dtos.pratos;

import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class PratosRequestDTO {

    @NotBlank (message = "Campo de nome do prato é obrigátorio")
    @Size(message = "O tamanho do nome não pode exceder os 100 caracteres")
    private String nome;

    @Size (message = "O tamanho da descrição não pode exceder os 200 caracteres")
    private String descricao;

    @Positive (message = "O campo de preço precisa conter um numero positivo")
    @NotBlank (message = "O campo de preço é obrigátorio")
    private Double preco;

    // private Integer calorias;
    // private Boolean disponivel;
    
}