package com.agropix.itau.mapper;

import com.agropix.itau.dto.TipoContaRequest;
import com.agropix.itau.dto.TipoContaResponse;
import com.agropix.itau.model.TipoConta;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface TipoContaMapper {

    TipoContaResponse toResponse(TipoConta tipoConta);
    TipoConta toModel(TipoContaRequest tipoContaRequest);
    List<TipoContaResponse> toModelList(List<TipoConta> listaTipoConta);

}
