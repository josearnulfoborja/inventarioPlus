package com.example.InventarioPlus.service;

import com.example.InventarioPlus.model.Cliente;
import com.example.InventarioPlus.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteJpaService {

    private final ClienteRepository repository;

    public ClienteJpaService(ClienteRepository repository) {
        this.repository = repository;
    }

    public List<Cliente> findAll() {
        return repository.findAll();
    }

    public Optional<Cliente> findById(Integer id) {
        return repository.findById(id);
    }

    public Cliente save(Cliente cliente) {
        return repository.save(cliente);
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }
}
