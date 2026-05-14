package com.bitrate.BitRate.service;

import com.bitrate.BitRate.model.Cliente;
import com.bitrate.BitRate.repository.ClienteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional; // Evita erro de quando você tenta acessar algo que é nulo

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> listarTodos() {
        return clienteRepository.findAll();
    }

    public Cliente buscarPorId(Long id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        return cliente.orElse(null);
    }

    public Cliente salvar(Cliente cliente) {
        if (clienteRepository.findByCpf(cliente.getCpf()) != null) {
            return null;
        }

        if (clienteRepository.findByEmail(cliente.getEmail()) != null) {
            return null;
        }

        return clienteRepository.save(cliente);
    }

    public void excluir(Long id) {
        clienteRepository.deleteById(id);
    }
}