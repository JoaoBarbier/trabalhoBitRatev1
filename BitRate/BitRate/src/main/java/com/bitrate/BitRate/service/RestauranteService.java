package com.bitrate.BitRate.service;

import com.bitrate.BitRate.model.Restaurante;
import com.bitrate.BitRate.repository.RestauranteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestauranteService {

    @Autowired
    private RestauranteRepository restauranteRepository;

    public List<Restaurante> listarTodos() {
        return restauranteRepository.findAll();
    }

    public Restaurante buscarPorId(Long id) {
        return restauranteRepository.findById(id).orElse(null);
    }

    public void salvar(Restaurante restaurante) {
        Restaurante duplicado = restauranteRepository.findByNomeAndEndereco(
            restaurante.getNome(), 
            restaurante.getEndereco()
        );

        if (duplicado != null) {
            return; 
        }
    
        restauranteRepository.save(restaurante);
    }

    public void excluir(Long id) {
        restauranteRepository.deleteById(id);
    }
}

