package com.agropix.itau.service;

import com.agropix.itau.dto.InfoChavePixBacen;
import com.agropix.itau.dto.TransferenciaPixRequest;
import com.agropix.itau.dto.TransferenciaPixResponse;
import com.agropix.itau.model.ChavePix;
import com.agropix.itau.model.Conta;
import com.agropix.itau.model.StatusTransferencia;
import com.agropix.itau.model.TransferenciaPix;
import com.agropix.itau.repository.TransferenciaPixRepository;
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
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class TransferenciaPixServiceTest {
    @InjectMocks
    private TransferenciaPixService transferenciaPixService;
    @Mock
    private ContaService contaService;
    @Mock
    private TransferenciaPixRepository repository;
    @Mock
    private ChavePixService chavePixService;
    @Mock
    private RestTemplate restTemplate;

    private TransferenciaPix fakeTransferenciaPix;
    private List<TransferenciaPix> fakeTransferenciaPixList;

    @BeforeEach
    void setup() {
        fakeTransferenciaPix = ServicesFactory.createTransferenciaPix();
        fakeTransferenciaPixList = ServicesFactory.createTransferenciaPixList(5);
    }

    @Test
    void deve_transferir_pix_e_tirar_valor_da_conta() {
        ChavePix chavePix = ServicesFactory.createChavePix();

        TransferenciaPixResponse transferenciaPixResponseMock =  new TransferenciaPixResponse();
        ResponseEntity<TransferenciaPixResponse> response = ResponseEntity.status(HttpStatus.OK).body(transferenciaPixResponseMock);

        Mockito.when(chavePixService.findByChavePix(Mockito.any())).thenReturn(chavePix);
        Mockito.when(restTemplate.postForEntity(Mockito.anyString(), Mockito.anyString(), Mockito.any(Class.class))).thenReturn(response);
        TransferenciaPixResponse transferenciaPixResponse = transferenciaPixService.transfer(toRequest(fakeTransferenciaPix));
        Mockito.verify(contaService, Mockito.times(1)).withdraw(Mockito.any(), Mockito.any());
        Assertions.assertEquals(StatusTransferencia.CONCLUIDA, transferenciaPixResponse.getStatusTransferencia());
    }

    @Test
    void deve_falhar_quando_status_resposta_bacen_diferente_de_ok_ao_transferir_pix_e_depositar_valor() {
        ChavePix chavePix = ServicesFactory.createChavePix();

        TransferenciaPixResponse transferenciaPixResponseMock =  new TransferenciaPixResponse();
        ResponseEntity<TransferenciaPixResponse> response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(transferenciaPixResponseMock);

        Mockito.when(chavePixService.findByChavePix(Mockito.any())).thenReturn(chavePix);
        Mockito.when(restTemplate.postForEntity(Mockito.anyString(), Mockito.anyString(), Mockito.any(Class.class))).thenReturn(response);
        Assertions.assertThrows(RuntimeException.class, () ->{
            transferenciaPixService.transfer(toRequest(fakeTransferenciaPix));
        });
        Mockito.verify(contaService, Mockito.times(1)).deposit(Mockito.any(), Mockito.any());
    }

    @Test
    void deve_retornar_informacoes_chave_pix_bacen() {
        InfoChavePixBacen infoChavePixBacenMock = new InfoChavePixBacen("Thiago Felix", "thiagofelixdasilva099@gmail.com", "EMAIL", "ADA");
        ResponseEntity response = ResponseEntity.status(HttpStatus.OK).body(infoChavePixBacenMock);
        Mockito.when(restTemplate.getForEntity(Mockito.anyString(), Mockito.any(Class.class))).thenReturn(response);
        InfoChavePixBacen infoChavePixBacen = transferenciaPixService.buscarInformacoesChavePix("thiagofelixdasilva099@gmail.com");
        Assertions.assertEquals(infoChavePixBacenMock.getChave(), infoChavePixBacen.getChave());
        Assertions.assertEquals(infoChavePixBacenMock.getTipo(), infoChavePixBacen.getTipo());
        Assertions.assertEquals(infoChavePixBacenMock.getNomeTitular(), infoChavePixBacen.getNomeTitular());
        Assertions.assertEquals(infoChavePixBacenMock.getBanco(), infoChavePixBacen.getBanco());
    }

    @Test
    void deve_falhar_quando_status_resposta_bacen_diferente_de_ok_ao_buscar_informacoes_chave_pix() {
        TransferenciaPixResponse transferenciaPixResponseMock =  new TransferenciaPixResponse();
        ResponseEntity<TransferenciaPixResponse> response = ResponseEntity.status(HttpStatus.OK).body(transferenciaPixResponseMock);

        Assertions.assertThrows(RuntimeException.class, () -> {
            transferenciaPixService.transfer(toRequest(fakeTransferenciaPix));
        });
    }

    @Test
    void deve_receber_pix_e_depositar_valor_na_conta() {
        ChavePix chavePix = ServicesFactory.createChavePix();
        Mockito.when(chavePixService.findByChavePix(Mockito.any())).thenReturn(chavePix);
        transferenciaPixService.receberPix(chavePix.getChavePix(), 100.00);
        Mockito.verify(contaService, Mockito.times(1)).deposit(Mockito.any(), Mockito.any());
    }

    private TransferenciaPixRequest toRequest(TransferenciaPix transferenciaPix) {
        TransferenciaPixRequest transferenciaPixRequest = new TransferenciaPixRequest();
        transferenciaPixRequest.setChaveOrigem(transferenciaPix.getChaveOrigem().getChavePix());
        transferenciaPixRequest.setChaveDestino(transferenciaPix.getChaveDestino());
        transferenciaPixRequest.setValor(transferenciaPix.getValor());
        return transferenciaPixRequest;
    }
}
