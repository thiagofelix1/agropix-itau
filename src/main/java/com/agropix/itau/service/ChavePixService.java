package com.agropix.itau.service;

import com.agropix.itau.dto.ChavePixRequest;
import com.agropix.itau.dto.ChavePixBacenResponse;
import com.agropix.itau.exceptions.ChavePixExistenteException;
import com.agropix.itau.exceptions.FalhaComunicacaoBacenException;
import com.agropix.itau.exceptions.ItemNotExistsException;
import com.agropix.itau.mapper.ChavePixMapper;
import com.agropix.itau.model.ChavePix;
import com.agropix.itau.model.Conta;
import com.agropix.itau.repository.ChavePixRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ChavePixService {
    @Value("${banco.name}")
    private String nomeBanco;
    @Value("${banco.codigo}")
    private String codigoBanco;
    private final ChavePixRepository repository;
    private final ChavePixMapper mapper;
    private final ContaService contaService;
    private final RestTemplate restTemplate;

    public ChavePix findByChavePix(String chavePix) {
        return repository.getChavePixByChavePix(chavePix).orElseThrow(
                () -> new ItemNotExistsException("Chave pix não encontrada!"));
    }

    public Optional<ChavePix> save(ChavePixRequest chavePixRequest) {
        ChavePix chavePix = mapper.toModel(chavePixRequest);
        Conta conta = contaService.findById(chavePixRequest.getContaId());
        chavePix.setConta(conta);
        String req = jsonBacenBuilder(chavePix);
        ResponseEntity<ChavePixBacenResponse> response =  restTemplate.postForEntity("/chavePix", req, ChavePixBacenResponse.class);
        if (response.getStatusCode() == HttpStatus.CREATED) {
            repository.save(chavePix);
            return Optional.of(chavePix);
        }
        else if (response.getStatusCode() == HttpStatus.UNPROCESSABLE_ENTITY) {
            throw new RuntimeException(response.getBody().toString());
        }
        else if (response.getStatusCode() == HttpStatus.BAD_REQUEST) {
            throw new ChavePixExistenteException("Está chave já está cadastrada em outro banco, por favor solicitar portabilidade");
        }
        else {
            throw new FalhaComunicacaoBacenException("Não foi possível realizar o cadastro da chave pix");
        }

    }

    public ChavePix findById(UUID chavePixId) {
        return repository.findById(chavePixId)
                .orElseThrow(() -> new ItemNotExistsException("Chave Pix não encontrada!"));
    }

    public List<ChavePix> findAll() {
        return repository.findAll();
    }

    public void delete(UUID chavePixId) {
        ChavePix chavePix = findById(chavePixId);
        repository.delete(chavePix);
    }

    private String jsonBacenBuilder(ChavePix chavePix) {

        ObjectMapper objectMapper = new ObjectMapper();

        ObjectNode root = objectMapper.createObjectNode();

        ObjectNode banco = objectMapper.createObjectNode();
        banco.put("nome", nomeBanco);
        banco.put("codigo", codigoBanco);

        ObjectNode titular = objectMapper.createObjectNode();
        titular.put("nome", chavePix.getConta().getCliente().getNome());
        titular.put("cpf", chavePix.getConta().getCliente().getCpf());
        titular.put("email", chavePix.getConta().getCliente().getEmail());
        titular.put("telefone", chavePix.getConta().getCliente().getTelefone());

        ObjectNode conta = objectMapper.createObjectNode();
        conta.put("numero", chavePix.getConta().getNumeroConta());
        conta.put("digito", chavePix.getConta().getDigito());

        root.put("chave", chavePix.getChavePix());
        root.put("banco", banco);
        root.put("tipo", chavePix.getTipo());
        root.put("titular", titular);
        root.put("conta", conta);

        String json;

        try {
           json  = objectMapper.writeValueAsString(root);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return json;
    }

}
