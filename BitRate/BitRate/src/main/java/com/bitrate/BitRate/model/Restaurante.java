package com.bitrate.BitRate.model;

import jakarta.persistence.*;
import java.util.List;


@Entity // Isso avisa ao Spring: "Crie uma tabela para essa classe"
public class Restaurante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ID auto-incremento no MySQL
    private Long id;

    private String nome;
    private String cozinha;
    private String especialidade;
    private String endereco;

    // Dentro da classe Restaurante.java
    @OneToMany(mappedBy = "restaurante", cascade = CascadeType.ALL)
    private List<Avaliacao> avaliacoes;



    // Construtores (Obrigatório ter um vazio para o JPA)
    public Restaurante() {}

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getCozinha() { return cozinha; }
    public void setEspecialidade (String especialidade) { this.especialidade = especialidade;}
    public String getEspecialidade() {return especialidade; }
    public void setCozinha(String cozinha) { this.cozinha = cozinha; }
    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }
    public List<Avaliacao> getAvaliacoes() {
        return avaliacoes;
    }
    public void setAvaliacoes(List<Avaliacao> avaliacoes) {
        this.avaliacoes = avaliacoes;
    }
}