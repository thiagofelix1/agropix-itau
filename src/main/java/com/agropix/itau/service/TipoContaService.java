package com.agropix.itau.service;

import com.agropix.itau.dto.TipoContaRequest;
import com.agropix.itau.exceptions.ItemNotExistsException;
import com.agropix.itau.mapper.TipoContaMapper;
import com.agropix.itau.model.TipoConta;
import com.agropix.itau.repository.TipoContaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class TipoContaService {

    private final TipoContaRepository repository;
    private final TipoContaMapper mapper;

    public TipoConta create (TipoContaRequest tipoContaRequest) {
        TipoConta tipoConta = mapper.toModel(tipoContaRequest);
        return repository.save(tipoConta);
    }

    public TipoConta findTipoContaById(UUID tipoContaId) {
        return repository.findById(tipoContaId)
                .orElseThrow(() -> new ItemNotExistsException("Tipo de conta não encontrado"));
    }

    public TipoConta update(UUID tipoContaId, TipoContaRequest tipoContaRequest) {
        TipoConta tipoConta = findTipoContaById(tipoContaId);
        tipoConta = mapper.toModel(tipoContaRequest);
        tipoConta.setId(tipoContaId);
        repository.save(tipoConta);
        return tipoConta;
    }

    public void delete(UUID tipoContaId) {
        TipoConta tipoConta = findTipoContaById(tipoContaId);
        repository.delete(tipoConta);
    }

    public List<TipoConta> findAll() {
        return repository.findAll();
    }

}