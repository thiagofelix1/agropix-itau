package com.agropix.itau.service;

import com.agropix.itau.dto.ChavePixRequest;
import com.agropix.itau.mapper.ChavePixMapper;
import com.agropix.itau.model.ChavePix;
import com.agropix.itau.repository.ChavePixRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ChavePixService {

    private final ChavePixRepository repository;
    private final ChavePixMapper mapper;

    public ChavePix save(ChavePixRequest chavePixRequest) {
        ChavePix chavePix = mapper.toModel(chavePixRequest);
        repository.save(chavePix);
        return chavePix;
    }

    public ChavePix findById(UUID chavePixId) {
        return repository.findById(chavePixId)
                .orElseThrow(() -> new RuntimeException("Chave Pix não encontrada!"));
    }

    public ChavePix update(UUID chavePixId, ChavePixRequest chavePixRequest) {
        ChavePix chavePix = findById(chavePixId);
        chavePix = mapper.toModel(chavePixRequest);
        chavePix.setId(chavePixId);
        repository.save(chavePix);
        return chavePix;
    }

    public void delete(UUID chavePixId) {
        ChavePix chavePix = findById(chavePixId);
        repository.delete(chavePix);
    }

    public List<ChavePix> findAll() {
        return repository.findAll();
    }

}
