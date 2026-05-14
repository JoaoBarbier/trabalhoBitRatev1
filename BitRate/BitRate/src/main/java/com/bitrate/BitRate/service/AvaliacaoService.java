package com.bitrate.BitRate.service;

import com.bitrate.BitRate.model.Avaliacao;
import com.bitrate.BitRate.repository.AvaliacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime; // Certifique-se de que este import existe
import java.util.List;

@Service
public class AvaliacaoService {

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    public Avaliacao salvar(Avaliacao avaliacao) {
        // Verifica se a data é nula e atribui o horário atual
        if (avaliacao.getDataPublicacao() == null) {
            avaliacao.setDataPublicacao(LocalDateTime.now());
        }
        return avaliacaoRepository.save(avaliacao);
    }

    public List<Avaliacao> buscarPorRestaurante(Long idRestaurante) {
        return avaliacaoRepository.findByRestauranteIdRestaurante(idRestaurante);
    }

    public List<Avaliacao> buscarPorCliente(Long idCliente) {
        return avaliacaoRepository.findByClienteIdCliente(idCliente);
    }
}