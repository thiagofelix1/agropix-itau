package com.agropix.itau.mapper;

import com.agropix.itau.dto.TipoContaRequest;
import com.agropix.itau.dto.TipoContaResponse;
import com.agropix.itau.model.TipoConta;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-12-13T18:30:21-0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 11.0.16.1 (Azul Systems, Inc.)"
)
@Component
public class TipoContaMapperImpl implements TipoContaMapper {

    @Override
    public TipoContaResponse toResponse(TipoConta tipoConta) {
        if ( tipoConta == null ) {
            return null;
        }

        TipoContaResponse tipoContaResponse = new TipoContaResponse();

        return tipoContaResponse;
    }

    @Override
    public TipoConta toModel(TipoContaRequest tipoContaRequest) {
        if ( tipoContaRequest == null ) {
            return null;
        }

        TipoConta.TipoContaBuilder tipoConta = TipoConta.builder();

        return tipoConta.build();
    }

    @Override
    public List<TipoContaResponse> toModelList(List<TipoConta> listaTipoConta) {
        if ( listaTipoConta == null ) {
            return null;
        }

        List<TipoContaResponse> list = new ArrayList<TipoContaResponse>( listaTipoConta.size() );
        for ( TipoConta tipoConta : listaTipoConta ) {
            list.add( toResponse( tipoConta ) );
        }

        return list;
    }
}
