package com.agropix.itau.mapper;

import com.agropix.itau.dto.ChavePixCreationRequest;
import com.agropix.itau.dto.ChavePixResponse;
import com.agropix.itau.model.ChavePix;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface ChavePixMapper {

    ChavePixResponse toResponse(ChavePix chavePix);
    ChavePix toModel(ChavePixCreationRequest chavePixCreationRequest);
    List<ChavePixResponse> toResponseList(List<ChavePix> chavePixList);

}
