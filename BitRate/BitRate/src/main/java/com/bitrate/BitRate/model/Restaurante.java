package com.bitrate.BitRate.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@Table(name = "Restaurante")
@Data                // Gera Getters, Setters, toString, equals e hashCode
@NoArgsConstructor   // Gera o construtor vazio (obrigatório para o Hibernate)
@AllArgsConstructor  // Gera um construtor com todos os campos
public class Restaurante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRestaurante;

    @Column(nullable = false)
    private String nome;

    private String endereco;

    private double notaMedia;

    private String foto;

    // No Model Restaurante.java
    @OneToMany(mappedBy = "restaurante", fetch = FetchType.EAGER) // Adicione isso!
    private List<Avaliacao> avaliacoes;

    public double getMediaNotas() {
        if (avaliacoes == null || avaliacoes.isEmpty()) {
            return 0.0;
        }
        return avaliacoes.stream()
                        .mapToInt(Avaliacao::getNota)
                        .average()
                        .orElse(0.0);
    }
}