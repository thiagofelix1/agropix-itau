package com.agropix.itau.service;

import com.agropix.itau.dto.ClienteRequest;
import com.agropix.itau.exceptions.ItemNotExistsException;
import com.agropix.itau.mapper.ClienteMapper;
import com.agropix.itau.model.Cliente;
import com.agropix.itau.repository.ClienteRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

@ExtendWith(MockitoExtension.class)
public class ClienteServiceTest {

    @InjectMocks
    private ClienteService clienteService;

    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private ClienteMapper clienteMapper;

    private Cliente fakeCliente;
    private List<Cliente> fakeClienteList;
    @BeforeEach
    void setup() {
        fakeCliente = ServicesFactory.createCliente();
        fakeClienteList = ServicesFactory.createClienteList(5);
    }

    @Test
    void deve_salvar_e_retornar_cliente() {
        Mockito.when(clienteRepository.save(Mockito.any())).thenReturn(fakeCliente);
        Mockito.when(clienteMapper.toModel(Mockito.any())).thenReturn(fakeCliente);
        Cliente cliente = clienteService.save(toRequest(fakeCliente));
        Assertions.assertEquals(cliente.getId(), fakeCliente.getId());
    }

    @Test
    void deve_retornar_cliente_por_id() {
        Mockito.when(clienteRepository.findById(Mockito.any())).thenReturn(Optional.of(fakeCliente));
        Cliente cliente = clienteService.findById(fakeCliente.getId());
        Assertions.assertEquals(fakeCliente.getId(), cliente.getId());
    }

    @Test
    void deve_falhar_ao_buscar_id_invalido() {
        Mockito.when(clienteRepository.findById(Mockito.any())).thenReturn(Optional.empty());
        Assertions.assertThrows(ItemNotExistsException.class, () -> {
            clienteService.findById(UUID.randomUUID());
        });
    }

    @Test
    void deve_atualizar_cliente_e_retornar() {
        Mockito.when(clienteRepository.findById(Mockito.any())).thenReturn(Optional.of(fakeCliente));
        Mockito.when(clienteMapper.toModel(Mockito.any())).thenReturn(fakeCliente);
        Mockito.when(clienteRepository.save(Mockito.any())).thenReturn(fakeCliente);
        String nomeAntesDeAtualizar = fakeCliente.getNome();
        fakeCliente.setNome("Paulo Silva");
        Cliente cliente = clienteService.update(fakeCliente.getId(), toRequest(fakeCliente));
        Assertions.assertEquals(fakeCliente.getId(), cliente.getId());
        Assertions.assertNotEquals(nomeAntesDeAtualizar, fakeCliente.getNome());
    }

    @Test
    void deve_retornar_todos_os_clientes() {
        Mockito.when(clienteRepository.findAll()).thenReturn(fakeClienteList);
        List<Cliente> clienteList = clienteService.findAll();
        Assertions.assertEquals(fakeClienteList.size(), clienteList.size());
    }

    private ClienteRequest toRequest(Cliente cliente) {
        ClienteRequest clienteRequest = new ClienteRequest();
        clienteRequest.setNome(cliente.getNome());
        clienteRequest.setCpf(cliente.getCpf());
        clienteRequest.setEmail(cliente.getEmail());
        clienteRequest.setTelefone(cliente.getTelefone());
        return clienteRequest;
    }
}
