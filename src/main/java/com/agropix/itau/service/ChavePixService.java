package com.agropix.itau.service;

import com.agropix.itau.dto.ChavePixCreationRequest;
import com.agropix.itau.mapper.ChavePixMapper;
import com.agropix.itau.model.ChavePix;
import com.agropix.itau.model.Conta;
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
    private final ContaService contaService;

    public ChavePix findByChavePix(String chavePix) {
        return repository.getChavePixByChavePix(chavePix).orElseThrow(
                () -> new RuntimeException("Chave pix não encontrada!"));
    }

    public ChavePix save(ChavePixCreationRequest chavePixCreationRequest) {
        ChavePix chavePix = mapper.toModel(chavePixCreationRequest);
        Conta conta = contaService.findById(chavePixCreationRequest.getContaId());
        chavePix.setConta(conta);
        repository.save(chavePix);
        return chavePix;
    }

    public ChavePix findById(UUID chavePixId) {
        return repository.findById(chavePixId)
                .orElseThrow(() -> new RuntimeException("Chave Pix não encontrada!"));
    }

    public void delete(UUID chavePixId) {
        ChavePix chavePix = findById(chavePixId);
        repository.delete(chavePix);
    }

    public List<ChavePix> findAll() {
        return repository.findAll();
    }

}
