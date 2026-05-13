package com.bitrate.BitRate.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Avaliacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String usuario;
    private String comentario;
    private int nota; // 1 a 5

    @ManyToOne
    @JoinColumn(name = "restaurante_id")
    private Restaurante restaurante;
}