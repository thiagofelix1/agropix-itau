package com.agropix.itau.mapper;

import com.agropix.itau.dto.ContaRequest;
import com.agropix.itau.dto.ContaResponse;
import com.agropix.itau.model.Conta;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-12-12T13:47:20-0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 11.0.16.1 (Azul Systems, Inc.)"
)
@Component
public class ContaMapperImpl implements ContaMapper {

    @Override
    public ContaResponse toResponse(Conta conta) {
        if ( conta == null ) {
            return null;
        }

        ContaResponse contaResponse = new ContaResponse();

        return contaResponse;
    }

    @Override
    public Conta toModel(ContaRequest contaRequest) {
        if ( contaRequest == null ) {
            return null;
        }

        Conta.ContaBuilder conta = Conta.builder();

        return conta.build();
    }
}
