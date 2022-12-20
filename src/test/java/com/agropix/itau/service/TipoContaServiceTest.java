package com.agropix.itau.service;

import com.agropix.itau.dto.TipoContaRequest;
import com.agropix.itau.exceptions.ItemNotExistsException;
import com.agropix.itau.mapper.TipoContaMapper;
import com.agropix.itau.model.TipoConta;
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
public class TipoContaServiceTest {

    @InjectMocks
    private TipoContaService tipoContaService;

    @Mock
    private TipoContaRepository tipoContaRepository;

    @Mock
    private TipoContaMapper tipoContaMapper;

    private TipoConta fakeTipoConta;
    private List<TipoConta> fakeTipoContaList;
    @BeforeEach
    void setup() {
        fakeTipoConta = ServicesFactory.createTipoConta();
        fakeTipoContaList = ServicesFactory.createTipoContaList(5);
    }

    @Test
    void deve_salvar_e_retornar_tipo_conta() {
        Mockito.when(tipoContaRepository.save(Mockito.any())).thenReturn(fakeTipoConta);
        Mockito.when(tipoContaMapper.toModel(Mockito.any())).thenReturn(fakeTipoConta);
        TipoConta tipoConta = tipoContaService.create(toRequest(fakeTipoConta));
        Assertions.assertEquals(tipoConta.getId(), fakeTipoConta.getId());
    }

    @Test
    void deve_retornar_tipo_conta_por_id() {
        Mockito.when(tipoContaRepository.findById(Mockito.any())).thenReturn(Optional.of(fakeTipoConta));
        TipoConta tipoConta = tipoContaService.findTipoContaById(fakeTipoConta.getId());
        Assertions.assertEquals(fakeTipoConta.getId(), tipoConta.getId());
    }

    @Test
    void deve_falhar_ao_buscar_id_invalido() {
        Mockito.when(tipoContaRepository.findById(Mockito.any())).thenReturn(Optional.empty());
        Assertions.assertThrows(ItemNotExistsException.class, () -> {
            tipoContaService.findTipoContaById(UUID.randomUUID());
        });
    }

    @Test
    void deve_atualizar_tipo_conta_e_retornar() {
        Mockito.when(tipoContaRepository.findById(Mockito.any())).thenReturn(Optional.of(fakeTipoConta));
        Mockito.when(tipoContaMapper.toModel(Mockito.any())).thenReturn(fakeTipoConta);
        Mockito.when(tipoContaRepository.save(Mockito.any())).thenReturn(fakeTipoConta);
        String tipoContaAntesDeAtualizar = fakeTipoConta.getTipoConta();
        fakeTipoConta.setTipoConta("Poupança");
        TipoConta tipoConta = tipoContaService.update(fakeTipoConta.getId(), toRequest(fakeTipoConta));
        Assertions.assertEquals(fakeTipoConta.getId(), tipoConta.getId());
        Assertions.assertNotEquals(tipoContaAntesDeAtualizar, fakeTipoConta.getTipoConta());
    }

    @Test
    void deve_retornar_todos_os_tipo_conta() {
        Mockito.when(tipoContaRepository.findAll()).thenReturn(fakeTipoContaList);
        List<TipoConta> tipoContaList = tipoContaService.findAll();
        Assertions.assertEquals(fakeTipoContaList.size(), tipoContaList.size());
    }

    private TipoContaRequest toRequest(TipoConta tipoConta) {
        TipoContaRequest tipoContaRequest = new TipoContaRequest();
        tipoContaRequest.setTipoConta(tipoConta.getTipoConta());
        return tipoContaRequest;
    }

}
