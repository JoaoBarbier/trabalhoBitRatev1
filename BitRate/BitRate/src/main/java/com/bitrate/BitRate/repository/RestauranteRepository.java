package com.bitrate.BitRate.repository;

import com.bitrate.BitRate.model.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {

    Restaurante findByNomeAndEndereco(String nome, String endereco);

}