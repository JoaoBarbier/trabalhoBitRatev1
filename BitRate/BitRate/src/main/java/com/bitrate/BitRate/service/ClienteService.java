package com.bitrate.BitRate.service;

import com.bitrate.BitRate.model.Cliente;
import com.bitrate.BitRate.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional; // Usado para evitar valores Nulos

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
        return clienteRepository.save(cliente);
    }
}