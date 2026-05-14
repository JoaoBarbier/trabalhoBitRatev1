package com.bitrate.BitRate.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Critica")
@Getter @Setter
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
    @JoinColumn(name = "id_cliente") // Chave Estrangeira 
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "id_restaurante") // Chave Estrangeira 
    private Restaurante restaurante;
}