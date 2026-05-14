package com.bitrate.BitRate.repository;

import com.bitrate.BitRate.model.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {
    // Procura um restaurante que tenha o MESMO nome E o MESMO endereço
    Restaurante findByNomeAndEndereco(String nome, String endereco);
}