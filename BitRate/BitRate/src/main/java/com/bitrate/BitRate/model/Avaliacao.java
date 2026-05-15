package com.bitrate.BitRate.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "Critica") //Mudar nome para Avaliacao no Futuro
@Data
@NoArgsConstructor @AllArgsConstructor
public class Avaliacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_critica")
    private Long idCritica;

    @Column(name = "texto_avaliacao", length = 5000)
    private String textoAvaliacao;

    private int nota; 

    @Column(name = "data_publicacao") 
    private LocalDateTime dataPublicacao;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "id_restaurante")
    private Restaurante restaurante;
}