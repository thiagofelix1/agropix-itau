package com.agropix.itau.mapper;

import com.agropix.itau.dto.ContaRequest;
import com.agropix.itau.dto.ContaResponse;
import com.agropix.itau.model.Conta;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface ContaMapper {

    ContaResponse toResponse(Conta conta);
    Conta toModel(ContaRequest contaRequest);
    List<ContaResponse> toResponseList(List<Conta> listaContas);

}
