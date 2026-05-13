package com.bitrate.BitRate.controller;

import com.bitrate.BitRate.model.Restaurante;
import com.bitrate.BitRate.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Diz que esta classe atende pedidos via URL
@RequestMapping("/api/restaurantes") // O endereço base (ex: localhost:8080/api/restaurantes)
@CrossOrigin("*") // LIBERA O ACESSO PARA OS MANOS DO FRONT! (Importante até 12 de junho)
public class RestauranteController {

    @Autowired // Traz o seu "Almoxarifado" (Repository) para cá automaticamente
    private RestauranteRepository repository;

    // 1. LISTAR TODOS (Read)
    @GetMapping
    public List<Restaurante> listar() {
        return repository.findAll();
    }

    // 2. ADICIONAR NOVO (Create)
    @PostMapping
    public Restaurante adicionar(@RequestBody Restaurante restaurante) {
        return repository.save(restaurante);
    }

    // 3. EDITAR (Update)
    @PutMapping("/{id}")
    public Restaurante editar(@PathVariable Long id, @RequestBody Restaurante restauranteAtualizado) {
        // O .map ajuda a encontrar o restaurante pelo ID. Se não achar, não faz nada.
        return repository.findById(id)
                .map(restaurante -> {
                    restaurante.setNome(restauranteAtualizado.getNome());
                    restaurante.setCozinha(restauranteAtualizado.getCozinha());
                    restaurante.setEspecialidade(restauranteAtualizado.getEspecialidade());
                    restaurante.setEndereco(restauranteAtualizado.getEndereco());
                    return repository.save(restaurante);
                }).orElseGet(() -> {
                    restauranteAtualizado.setId(id);
                    return repository.save(restauranteAtualizado);
                });
    }

    // 4. DELETAR (Delete)
    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
        repository.deleteById(id);
    }
}