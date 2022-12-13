package com.agropix.itau.mapper;

import com.agropix.itau.dto.ClienteRequest;
import com.agropix.itau.dto.ClienteResponse;
import com.agropix.itau.model.Cliente;
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
public class ClienteMapperImpl implements ClienteMapper {

    @Override
    public ClienteResponse toResponse(Cliente cliente) {
        if ( cliente == null ) {
            return null;
        }

        ClienteResponse clienteResponse = new ClienteResponse();

        return clienteResponse;
    }

    @Override
    public Cliente toModel(ClienteRequest clienteRequest) {
        if ( clienteRequest == null ) {
            return null;
        }

        Cliente.ClienteBuilder cliente = Cliente.builder();

        return cliente.build();
    }

    @Override
    public List<ClienteResponse> toResponseList(List<Cliente> listaClientes) {
        if ( listaClientes == null ) {
            return null;
        }

        List<ClienteResponse> list = new ArrayList<ClienteResponse>( listaClientes.size() );
        for ( Cliente cliente : listaClientes ) {
            list.add( toResponse( cliente ) );
        }

        return list;
    }
}
