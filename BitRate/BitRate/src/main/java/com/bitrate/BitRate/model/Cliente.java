package com.bitrate.BitRate.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;
import java.util.List;

@Entity
@Table(name = "clientes")
@Data
@NoArgsConstructor @AllArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCliente;

    private String nome;

    private String foto;

    @Column(unique = true, nullable = false)
    private String cpf;

    private LocalDate dataNascimento;

    @Column(unique = true, nullable = false) 
    private String email;

    private String senha;

    private String tipo;

    // Futuras Implementações
    @ManyToMany
    @JoinTable(
        name = "restaurante_favorito",
        joinColumns = @JoinColumn(name = "id_cliente"),
        inverseJoinColumns = @JoinColumn(name = "id_restaurante")
    )
    private Set<Restaurante> restaurantesFavoritos;

    // Futuras Implementações
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Avaliacao> avaliacoes;
}