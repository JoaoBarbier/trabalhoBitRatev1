package com.bitrate.BitRate.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter
public class AvaliaCritica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAvaliaCritica;

    private int nota; // A nota de 0 a 5 que o usuário deu para a crítica

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente; // Quem está avaliando

    @ManyToOne
    @JoinColumn(name = "id_avaliacao")
    private Avaliacao avaliacao; // A crítica que está sendo avaliada
}