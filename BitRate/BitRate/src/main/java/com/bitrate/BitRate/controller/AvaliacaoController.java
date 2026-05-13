package com.bitrate.BitRate.controller;

import com.bitrate.BitRate.model.Avaliacao;
import com.bitrate.BitRate.repository.AvaliacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/avaliacoes")
@CrossOrigin("*")
public class AvaliacaoController {

    @Autowired
    private AvaliacaoRepository repository;

    @GetMapping
    public List<Avaliacao> listar() {
        return repository.findAll();
    }

    @PostMapping
    public Avaliacao postar(@RequestBody Avaliacao avaliacao) {
        // Validação simples de estrelas
        if(avaliacao.getEstrelas() < 1 || avaliacao.getEstrelas() > 5) {
            throw new RuntimeException("A nota deve ser entre 1 e 5");
        }
        return repository.save(avaliacao);
    }
}