package com.bitrate.BitRate.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "Restaurante")
@Data
@NoArgsConstructor @AllArgsConstructor
public class Restaurante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRestaurante;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String endereco;

    private double notaMedia;

    private String foto;

    /**
     * Relacionamento: Um restaurante possui muitas avaliações.
     * mappedBy: Indica que o campo "restaurante" na classe Avaliacao é o dono da relação.
     * FetchType.EAGER: Carrega as avaliações automaticamente sempre que buscar um restaurante.
     */
    @OneToMany(mappedBy = "restaurante", fetch = FetchType.EAGER)
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