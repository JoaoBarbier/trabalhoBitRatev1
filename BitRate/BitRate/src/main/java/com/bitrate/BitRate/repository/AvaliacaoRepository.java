package com.bitrate.BitRate.repository;

import com.bitrate.BitRate.model.Avaliacao;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {
    // Busca todas as críticas feitas em um restaurante específico
    List<Avaliacao> findByRestauranteIdRestaurante(Long idRestaurante);

    // Busca todas as críticas escritas por um cliente específico
    List<Avaliacao> findByClienteIdCliente(Long idCliente);
}