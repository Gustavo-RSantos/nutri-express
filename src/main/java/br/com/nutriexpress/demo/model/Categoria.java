package br.com.nutriexpress.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nome;
    private String descricao;

    @OneToMany(mappedBy = "categoria") // Determina a maneira de relacionamento entre as Classes -> 1:N
    private List<Prato> pratos; // Cria uma Lista dos pratos que possuem essa categoria
}
