package com.agropix.itau.service;

import com.agropix.itau.dto.TipoContaRequest;
import com.agropix.itau.dto.TipoContaResponse;
import com.agropix.itau.mapper.TipoContaMapper;
import com.agropix.itau.model.TipoConta;
import com.agropix.itau.repository.TipoContaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TipoContaService {

    private final TipoContaRepository tipoContaRepository;
    private final TipoContaMapper tipoContaMapper;

    // ToDo: Create Tipo Conta Service
    public TipoConta create (TipoContaRequest tipoContaRequest) {
        TipoConta tipoConta = tipoContaMapper.toModel(tipoContaRequest);
        return tipoContaRepository.save(tipoConta);
    }

    // ToDo: Read Tipo Conta Service

    // ToDo: Update Tipo Conta Service

    // ToDo: Delete Tipo Conta Service

}
