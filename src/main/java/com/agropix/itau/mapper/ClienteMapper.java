package com.agropix.itau.mapper;

import com.agropix.itau.dto.ClienteRequest;
import com.agropix.itau.dto.ClienteResponse;
import com.agropix.itau.model.Cliente;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface ClienteMapper {

    ClienteResponse toResponse(Cliente cliente);
    Cliente toModel(ClienteRequest clienteRequest);
    List<ClienteResponse> toResponseList(List<Cliente> listaClientes);

}
