package com.bitrate.BitRate.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity // Isso avisa ao Spring: "Crie uma tabela para essa classe"
public class Restaurante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ID auto-incremento no MySQL
    private Long id;

    private String nome;
    private String cozinha;
    private String endereco;

    // Construtores (Obrigatório ter um vazio para o JPA)
    public Restaurante() {}

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getCozinha() { return cozinha; }
    public void setCozinha(String cozinha) { this.cozinha = cozinha; }
    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }
}