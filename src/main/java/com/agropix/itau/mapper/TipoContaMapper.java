package com.agropix.itau.mapper;

import com.agropix.itau.dto.TipoContaRequest;
import com.agropix.itau.dto.TipoContaResponse;
import com.agropix.itau.model.TipoConta;

import java.util.List;

public interface TipoContaMapper {

    // ToDo: Criar Tipo Conta Mapper
    TipoContaResponse toResponse(TipoConta tipoConta);
    TipoConta toModel(TipoContaRequest tipoContaRequest);
    List<TipoContaResponse> toModelList(List<TipoConta> listaTipoConta);

}
