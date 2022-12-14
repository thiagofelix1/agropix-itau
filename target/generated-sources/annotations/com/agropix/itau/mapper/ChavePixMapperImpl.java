package com.agropix.itau.mapper;

import com.agropix.itau.dto.ChavePixRequest;
import com.agropix.itau.dto.ChavePixResponse;
import com.agropix.itau.model.ChavePix;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-12-14T12:56:15-0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 11.0.16.1 (Azul Systems, Inc.)"
)
@Component
public class ChavePixMapperImpl implements ChavePixMapper {

    @Override
    public ChavePixResponse toResponse(ChavePix chavePix) {
        if ( chavePix == null ) {
            return null;
        }

        ChavePixResponse chavePixResponse = new ChavePixResponse();

        chavePixResponse.setChavePix( chavePix.getChavePix() );
        chavePixResponse.setTipo( chavePix.getTipo() );
        chavePixResponse.setConta( chavePix.getConta() );

        return chavePixResponse;
    }

    @Override
    public ChavePix toModel(ChavePixRequest chavePixRequest) {
        if ( chavePixRequest == null ) {
            return null;
        }

        ChavePix.ChavePixBuilder chavePix = ChavePix.builder();

        chavePix.chavePix( chavePixRequest.getChavePix() );
        chavePix.tipo( chavePixRequest.getTipo() );

        return chavePix.build();
    }

    @Override
    public List<ChavePixResponse> toResponseList(List<ChavePix> chavePixList) {
        if ( chavePixList == null ) {
            return null;
        }

        List<ChavePixResponse> list = new ArrayList<ChavePixResponse>( chavePixList.size() );
        for ( ChavePix chavePix : chavePixList ) {
            list.add( toResponse( chavePix ) );
        }

        return list;
    }
}
