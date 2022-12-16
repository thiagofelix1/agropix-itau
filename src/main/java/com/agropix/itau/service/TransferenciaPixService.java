package com.agropix.itau.service;

import com.agropix.itau.dto.TransferenciaPixRequest;
import com.agropix.itau.dto.TransferenciaPixResponse;
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
    private final RestTemplate restTemplate;

    public ResponseEntity<TransferenciaPixResponse> transfer(TransferenciaPixRequest transferenciaPixRequest) {

        // ToDo: Lógica de realização de débito

        String req = jsonBacenBuilder(transferenciaPixRequest);
        ResponseEntity<TransferenciaPixResponse> response =  restTemplate.postForEntity("/transferencia-pix", req, TransferenciaPixResponse.class);
        if (response.getStatusCode() == HttpStatus.OK) {

            // ToDo: Lógica de realização saque

        }
        else if (response.getStatusCode() == HttpStatus.UNPROCESSABLE_ENTITY) {
            throw new RuntimeException(response.getBody().toString());
        }

        return response;
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
