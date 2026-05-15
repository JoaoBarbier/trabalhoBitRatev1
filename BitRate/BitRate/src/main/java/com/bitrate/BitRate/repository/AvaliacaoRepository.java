package com.bitrate.BitRate.repository;

import com.bitrate.BitRate.model.Avaliacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {

    //Busca Criticas por Restaurante
    List<Avaliacao> findByRestauranteIdRestaurante(Long idRestaurante);

    //Busca Criticas por Cliente
    List<Avaliacao> findByClienteIdCliente(Long idCliente);
}