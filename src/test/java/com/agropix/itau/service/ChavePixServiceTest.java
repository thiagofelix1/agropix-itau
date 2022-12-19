package com.agropix.itau.service;

import com.agropix.itau.dto.ChavePixBacenResponse;
import com.agropix.itau.dto.ChavePixRequest;
import com.agropix.itau.dto.ChavePixResponse;
import com.agropix.itau.exceptions.ChavePixExistenteException;
import com.agropix.itau.exceptions.FalhaComunicacaoBacenException;
import com.agropix.itau.exceptions.ItemNotExistsException;
import com.agropix.itau.mapper.ChavePixMapper;
import com.agropix.itau.model.ChavePix;
import com.agropix.itau.model.Cliente;
import com.agropix.itau.model.Conta;
import com.agropix.itau.repository.ChavePixRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class ChavePixServiceTest {

    @InjectMocks
    private ChavePixService chavePixService;

    @Mock
    private ChavePixRepository chavePixRepository;

    @Mock
    private ChavePixMapper chavePixMapper;

    @Mock
    private ContaService contaService;

    @Mock
    private RestTemplate restTemplate;

    private ChavePix fakeChavePix;
    private List<ChavePix> fakeChavePixList;

    @BeforeEach
    void setup() {
        fakeChavePix = ServicesFactory.createChavePix();
        fakeChavePixList = ServicesFactory.createChavePixList(5);
    }

    @Test
    void deve_salvar_e_retornar_chave_pix() {
        Conta conta = ServicesFactory.createConta();
        ChavePixBacenResponse chavePixBacenResponse = new ChavePixBacenResponse();
        ResponseEntity<ChavePixBacenResponse> response = ResponseEntity
                .status(HttpStatus.CREATED).body(chavePixBacenResponse);

        Mockito.when(chavePixMapper.toModel(Mockito.any())).thenReturn(fakeChavePix);
        Mockito.when(contaService.findById(Mockito.any())).thenReturn(conta);
        Mockito.when(restTemplate.postForEntity(Mockito.anyString(), Mockito.anyString(), Mockito.any(Class.class))).thenReturn(response);
        Mockito.when(chavePixRepository.save(Mockito.any())).thenReturn(fakeChavePix);
        Optional<ChavePix> chavePixOptional = chavePixService.save(toRequest(fakeChavePix));
        Assertions.assertEquals(fakeChavePix.getId(), chavePixOptional.get().getId());
        Assertions.assertEquals(fakeChavePix.getChavePix(), chavePixOptional.get().getChavePix());
    }

    @Test
    void deve_falhar_quando_resposta_bacen_unprocessable_entity() {
        Conta conta = ServicesFactory.createConta();
        ChavePixBacenResponse chavePixBacenResponse = new ChavePixBacenResponse();
        ResponseEntity<ChavePixBacenResponse> response = ResponseEntity
                .status(HttpStatus.UNPROCESSABLE_ENTITY).body(chavePixBacenResponse);

        Mockito.when(chavePixMapper.toModel(Mockito.any())).thenReturn(fakeChavePix);
        Mockito.when(contaService.findById(Mockito.any())).thenReturn(conta);
        Mockito.when(restTemplate.postForEntity(Mockito.anyString(), Mockito.anyString(), Mockito.any(Class.class))).thenReturn(response);
        Assertions.assertThrows(RuntimeException.class, () -> {
            chavePixService.save(toRequest(fakeChavePix));
        });
    }

    @Test
    void deve_falhar_quando_resposta_bacen_bad_request() {
        Conta conta = ServicesFactory.createConta();
        ChavePixBacenResponse chavePixBacenResponse = new ChavePixBacenResponse();
        ResponseEntity<ChavePixBacenResponse> response = ResponseEntity
                .status(HttpStatus.BAD_REQUEST).body(chavePixBacenResponse);

        Mockito.when(chavePixMapper.toModel(Mockito.any())).thenReturn(fakeChavePix);
        Mockito.when(contaService.findById(Mockito.any())).thenReturn(conta);
        Mockito.when(restTemplate.postForEntity(Mockito.anyString(), Mockito.anyString(), Mockito.any(Class.class))).thenReturn(response);
        Assertions.assertThrows(ChavePixExistenteException.class, () -> {
            chavePixService.save(toRequest(fakeChavePix));
        });
    }

    @Test
    void deve_falhar_quando_resposta_bacen_diferente_de_created() {
        Conta conta = ServicesFactory.createConta();
        ChavePixBacenResponse chavePixBacenResponse = new ChavePixBacenResponse();
        ResponseEntity<ChavePixBacenResponse> response = ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR).body(chavePixBacenResponse);

        Mockito.when(chavePixMapper.toModel(Mockito.any())).thenReturn(fakeChavePix);
        Mockito.when(contaService.findById(Mockito.any())).thenReturn(conta);
        Mockito.when(restTemplate.postForEntity(Mockito.anyString(), Mockito.anyString(), Mockito.any(Class.class))).thenReturn(response);
        Assertions.assertThrows(FalhaComunicacaoBacenException.class, () -> {
            chavePixService.save(toRequest(fakeChavePix));
        });
    }

    @Test
    void deve_retornar_chave_pix_por_id() {
        Mockito.when(chavePixRepository.findById(Mockito.any())).thenReturn(Optional.of(fakeChavePix));
        ChavePix chavePix = chavePixService.findById(fakeChavePix.getId());
        Assertions.assertEquals(fakeChavePix.getId(), chavePix.getId());
    }

    @Test
    void deve_falhar_ao_buscar_id_invalido() {
        Mockito.when(chavePixRepository.findById(Mockito.any())).thenReturn(Optional.empty());
        Assertions.assertThrows(ItemNotExistsException.class, () -> {
            chavePixService.findById(UUID.randomUUID());
        });
    }

    @Test
    void deve_retornar_todas_as_chaves_pix() {
        Mockito.when(chavePixRepository.findAll()).thenReturn(fakeChavePixList);
        List<ChavePix> chavePixList = chavePixService.findAll();
        Assertions.assertEquals(fakeChavePixList.size(), chavePixList.size());
    }

    private ChavePixRequest toRequest(ChavePix chavePix) {
        ChavePixRequest chavePixRequest = new ChavePixRequest();
        chavePixRequest.setChavePix(chavePix.getChavePix());
        chavePixRequest.setTipo(chavePix.getTipo());
        chavePixRequest.setContaId(chavePix.getConta().getId());
        return chavePixRequest;
    }
}
