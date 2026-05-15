package com.bitrate.BitRate.service;

import com.bitrate.BitRate.model.Avaliacao;
import com.bitrate.BitRate.repository.AvaliacaoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AvaliacaoService {

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    public List<Avaliacao> buscarPorRestaurante(Long idRestaurante) {
        return avaliacaoRepository.findByRestauranteIdRestaurante(idRestaurante);
    }

    public List<Avaliacao> buscarPorCliente(Long idCliente) {
        return avaliacaoRepository.findByClienteIdCliente(idCliente);
    }

    public Avaliacao salvar(Avaliacao avaliacao) {
        if (avaliacao.getDataPublicacao() == null) {
            avaliacao.setDataPublicacao(LocalDateTime.now());
        }
        return avaliacaoRepository.save(avaliacao);
    }

    public void excluir(Long id) {
        avaliacaoRepository.deleteById(id);
    }
}