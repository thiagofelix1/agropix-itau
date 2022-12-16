package com.agropix.itau.service;

import com.agropix.itau.dto.InfoChavePixBacen;
import com.agropix.itau.dto.TransferenciaPixRequest;
import com.agropix.itau.dto.TransferenciaPixResponse;
import com.agropix.itau.model.ChavePix;
import com.agropix.itau.model.StatusTransferencia;
import com.agropix.itau.model.TransferenciaPix;
import com.agropix.itau.repository.TransferenciaPixRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class TransferenciaPixService {

    private final ContaService contaService;
    private final TransferenciaPixRepository repository;
    private final ChavePixService chavePixService;
    private final RestTemplate restTemplate;

    public TransferenciaPixResponse transfer(TransferenciaPixRequest transferenciaPixRequest) {
        ChavePix chavePixOrigem = chavePixService.findByChavePix(transferenciaPixRequest.getChaveOrigem());
        ChavePix chavePixDestino = chavePixService.findByChavePix(transferenciaPixRequest.getChaveDestino());

        TransferenciaPix transferenciaPix = TransferenciaPix.builder()
                .chaveOrigem(chavePixOrigem)
                .chaveDestino(chavePixDestino)
                .valor(transferenciaPixRequest.getValor())
                .statusTransferencia(StatusTransferencia.PENDENTE)
                .build();
        repository.save(transferenciaPix);
        contaService.withdraw(chavePixOrigem.getConta().getId(), transferenciaPix.getValor());


        String req = jsonBacenBuilder(transferenciaPixRequest);
        ResponseEntity<TransferenciaPixResponse> response =  restTemplate.postForEntity("/transferencia-pix", req, TransferenciaPixResponse.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            transferenciaPix.setStatusTransferencia(StatusTransferencia.CONCLUIDA);
        }
        else {
            transferenciaPix.setStatusTransferencia(StatusTransferencia.CANCELADA);
            contaService.deposit(transferenciaPix.getChaveOrigem().getConta().getId(), transferenciaPix.getValor());
            throw new RuntimeException(response.getBody().toString());
        }
        repository.save(transferenciaPix);
        return response.getBody();
    }

    public InfoChavePixBacen buscarInformacoesChavePix(String chave) {
        String url = "/chavePix/";
        ResponseEntity<InfoChavePixBacen> response = restTemplate.getForEntity(url, InfoChavePixBacen.class);

        if (response.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException(response.getBody().toString());
        }
        return response.getBody();
    }
    private String jsonBacenBuilder(TransferenciaPixRequest transferenciaPixRequest) {

        ObjectMapper objectMapper = new ObjectMapper();

        ObjectNode root = objectMapper.createObjectNode();

        root.put("chaveOrigem", transferenciaPixRequest.getChaveOrigem());
        root.put("chaveDestino", transferenciaPixRequest.getChaveDestino());
        root.put("valor", transferenciaPixRequest.getValor());

        String json;

        try {
            json  = objectMapper.writeValueAsString(root);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return json;
    }
}
