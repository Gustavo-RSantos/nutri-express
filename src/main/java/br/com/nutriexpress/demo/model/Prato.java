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

    @ManyToOne // Determina a maneira de relacionamento entre as Classes -> 1
    @JoinColumn(name = "categoria_id", referencedColumnName = "id") // Cria a coluna de relacionamento entre as tabelas
    private Categoria categoria;

    private Integer calorias;
    private Double quantidade;
    private String unidadeMedida;
}
