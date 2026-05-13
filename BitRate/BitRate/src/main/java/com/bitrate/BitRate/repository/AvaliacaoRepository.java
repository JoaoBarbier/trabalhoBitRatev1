package com.bitrate.BitRate.repository;

import com.bitrate.BitRate.model.Avaliacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {
    //listar as notas
}