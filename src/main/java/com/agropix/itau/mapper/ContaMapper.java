package com.agropix.itau.mapper;

import com.agropix.itau.dto.ContaRequest;
import com.agropix.itau.dto.ContaResponse;
import com.agropix.itau.model.Conta;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface ContaMapper {

    // ToDo: Create Conta Mapper

    ContaResponse toResponse(Conta conta);
    Conta toModel(ContaRequest contaRequest);

}
