package com.agropix.itau.mapper;

import com.agropix.itau.dto.ContaRequest;
import com.agropix.itau.dto.ContaResponse;
import com.agropix.itau.model.Conta;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-12-13T12:24:57-0400",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 11.0.17 (Ubuntu)"
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

        Conta conta = new Conta();

        conta.setNumeroConta( contaRequest.getNumeroConta() );
        conta.setDigito( contaRequest.getDigito() );
        conta.setAgencia( contaRequest.getAgencia() );
        conta.setNomeBanco( contaRequest.getNomeBanco() );

        return conta;
    }

    @Override
    public List<ContaResponse> toResponseList(List<Conta> listaContas) {
        if ( listaContas == null ) {
            return null;
        }

        List<ContaResponse> list = new ArrayList<ContaResponse>( listaContas.size() );
        for ( Conta conta : listaContas ) {
            list.add( toResponse( conta ) );
        }

        return list;
    }
}
