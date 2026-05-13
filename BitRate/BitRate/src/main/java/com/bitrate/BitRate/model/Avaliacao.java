package com.bitrate.BitRate.model;

import jakarta.persistence.*;

@Entity
public class Avaliacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int estrelas; // Aqui será de 1 a 5

    @Column(length = 5000) // Espaço de sobra para 1000 palavras
    private String comentario;

    @ManyToOne // Muitas avaliações para UM restaurante
    @JoinColumn(name = "restaurante_id")
    private Restaurante restaurante;

    public Avaliacao() {}

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public int getEstrelas() { return estrelas; }
    public void setEstrelas(int estrelas) { this.estrelas = estrelas; }
    public String getComentario() { return comentario; }
    public void setComentario(String comentario) { this.comentario = comentario; }
    public Restaurante getRestaurante() { return restaurante; }
    public void setRestaurante(Restaurante restaurante) { this.restaurante = restaurante; }
}