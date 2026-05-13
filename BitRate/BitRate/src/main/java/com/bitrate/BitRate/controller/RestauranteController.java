package com.bitrate.BitRate.controller;

import com.bitrate.BitRate.model.Restaurante;
import com.bitrate.BitRate.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

        import java.util.List;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

    @Autowired
    private RestauranteRepository repository;

    @GetMapping
    public List<Restaurante> listar() {
        return repository.findAll();
    }

    @PostMapping
    public Restaurante salvar(@RequestBody Restaurante restaurante) {
        return repository.save(restaurante);
    }
}