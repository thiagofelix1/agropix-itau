package com.agropix.itau.service;

import com.agropix.itau.dto.ClienteRequest;
import com.agropix.itau.mapper.ClienteMapper;
import com.agropix.itau.model.Cliente;
import com.agropix.itau.repository.ClienteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ClienteService {

    private final ClienteRepository repository;
    private final ClienteMapper mapper;

    public Cliente save(ClienteRequest clienteRequest) {
        Cliente cliente = mapper.toModel(clienteRequest);
        repository.save(cliente);
        return cliente;
    }

    public Cliente findById(UUID contaId) {
        return repository.findById(contaId)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado!"));
    }

    public Cliente update(UUID clienteId, ClienteRequest clienteRequest) {
        Cliente cliente = findById(clienteId);
        cliente = mapper.toModel(clienteRequest);
        cliente.setId(clienteId);
        repository.save(cliente);
        return cliente;
    }

    public void delete(UUID clienteId) {
        Cliente cliente = findById(clienteId);
        repository.delete(cliente);
    }

    public List<Cliente> findAll() {
        return repository.findAll();
    }

}
