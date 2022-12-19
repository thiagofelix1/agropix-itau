package com.agropix.itau.service;

import com.agropix.itau.dto.ContaRequest;
import com.agropix.itau.dto.TipoContaRequest;
import com.agropix.itau.exceptions.ItemNotExistsException;
import com.agropix.itau.mapper.ContaMapper;
import com.agropix.itau.mapper.TipoContaMapper;
import com.agropix.itau.model.Cliente;
import com.agropix.itau.model.Conta;
import com.agropix.itau.model.TipoConta;
import com.agropix.itau.repository.ContaRepository;
import com.agropix.itau.repository.TipoContaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class ContaServiceTest {
    @InjectMocks
    private ContaService contaService;

    @Mock
    private ClienteService clienteService;
    @Mock
    private TipoContaService tipoContaService;

    @Mock
    private ContaRepository contaRepository;

    @Mock
    private ContaMapper contaMapper;

    private Conta fakeConta;
    private List<Conta> fakeContaList;
    @BeforeEach
    void setup() {
        fakeConta = ServicesFactory.createConta();
        fakeContaList = ServicesFactory.createContaList(5);
    }

    @Test
    void deve_salvar_e_retornar_conta() {
        Cliente cliente = ServicesFactory.createCliente();
        TipoConta tipoConta = ServicesFactory.createTipoConta();

        Mockito.when(contaRepository.save(Mockito.any())).thenReturn(fakeConta);
        Mockito.when(contaMapper.toModel(Mockito.any())).thenReturn(fakeConta);
        Mockito.when(tipoContaService.findTipoContaById(Mockito.any())).thenReturn(tipoConta);
        Mockito.when(clienteService.findById(Mockito.any())).thenReturn(cliente);
        Conta conta = contaService.save(toRequest(fakeConta));
        Assertions.assertEquals(conta.getId(), fakeConta.getId());
    }

    @Test
    void deve_retornar_conta_por_id() {
        Mockito.when(contaRepository.findById(Mockito.any())).thenReturn(Optional.of(fakeConta));
        Conta conta = contaService.findById(fakeConta.getId());
        Assertions.assertEquals(fakeConta.getId(), conta.getId());
    }

    @Test
    void deve_falhar_ao_buscar_id_invalido() {
        Mockito.when(contaRepository.findById(Mockito.any())).thenReturn(Optional.empty());
        Assertions.assertThrows(ItemNotExistsException.class, () -> {
            contaService.findById(UUID.randomUUID());
        });
    }

    @Test
    void deve_atualizar_conta_e_retornar() {
        Mockito.when(contaRepository.findById(Mockito.any())).thenReturn(Optional.of(fakeConta));
        Mockito.when(contaMapper.toModel(Mockito.any())).thenReturn(fakeConta);
        Mockito.when(contaRepository.save(Mockito.any())).thenReturn(fakeConta);
        Long numeroContaAntesDeAtualizar = fakeConta.getNumeroConta();
        fakeConta.setNumeroConta(54687L);
        Conta conta = contaService.update(fakeConta.getId(), toRequest(fakeConta));
        Assertions.assertEquals(fakeConta.getId(), conta.getId());
        Assertions.assertNotEquals(numeroContaAntesDeAtualizar, fakeConta.getTipoConta());
    }

    @Test
    void deve_retornar_todas_as_contas() {
        Mockito.when(contaRepository.findAll()).thenReturn(fakeContaList);
        List<Conta> tipoContaList = contaService.findAll();
        Assertions.assertEquals(fakeContaList.size(), tipoContaList.size());
    }

    private ContaRequest toRequest(Conta conta) {
        ContaRequest contaRequest = new ContaRequest();
        contaRequest.setNumeroConta(conta.getNumeroConta());
        contaRequest.setDigito(conta.getDigito());
        contaRequest.setAgencia(conta.getAgencia());
        contaRequest.setNomeBanco(conta.getNomeBanco());
        contaRequest.setClienteId(conta.getCliente().getId());
        contaRequest.setTipoContaId(conta.getTipoConta().getId());
        return contaRequest;
    }

}
