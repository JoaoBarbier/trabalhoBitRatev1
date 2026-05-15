package com.bitrate.BitRate.model;

import jakarta.persistence.*;
import lombok.*;

// futuras Implementações
@Entity
@Table(name = "avaliacoes_criticas")
@Data
@NoArgsConstructor @AllArgsConstructor
public class AvaliaCritica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAvaliaCritica;

    private int nota;

    @ManyToOne
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "id_avaliacao", nullable = false)
    private Avaliacao avaliacao;
}