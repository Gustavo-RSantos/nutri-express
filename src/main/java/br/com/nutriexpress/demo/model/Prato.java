package br.com.nutriexpress.demo.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Prato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String descricao;
    private Double valor;
    @OneToMany
    private Categoria categoria; // Recebendo erro para realizando o relacionamento entre tabelas
    private Integer calorias;
    private Double quantidade;
    private String unidadeMedida;

    public Prato(String nome, String descricao, Double valor, Categoria categoria, Integer calorias, Double quantidade, String unidadeMedida) {
        this.nome = nome;
        this.descricao = descricao;
        this.valor = valor;
        this.categoria = categoria;
        this.calorias = calorias;
        this.quantidade = quantidade;
        this.unidadeMedida = unidadeMedida;
    }

}
