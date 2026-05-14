package com.bitrate.BitRate.repository;

import com.bitrate.BitRate.model.Avaliacao; // Certifique-se que o nome da classe é Avaliacao
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvaliaCriticaRepository extends JpaRepository<Avaliacao, Long> {
}