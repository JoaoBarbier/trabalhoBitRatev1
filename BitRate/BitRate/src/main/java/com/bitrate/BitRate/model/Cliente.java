package com.bitrate.BitRate.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "clientes")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCliente;

    private String nome;
    private String foto;

    @Column(unique = true, nullable = false) // CPF Único
    private String cpf;

    private LocalDate dataNascimento;

    @Column(unique = true, nullable = false) // E-mail Único
    private String email;

    private String senha;
    private String tipo;

    // Relacionamentos mantidos...
    @ManyToMany
    @JoinTable(
        name = "restaurante_favorito",
        joinColumns = @JoinColumn(name = "id_cliente"),
        inverseJoinColumns = @JoinColumn(name = "id_restaurante")
    )
    private Set<Restaurante> restaurantesFavoritos;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private java.util.List<Avaliacao> avaliacoes;
}