package com.agropix.itau.controller;

import com.agropix.itau.dto.ClienteRequest;
import com.agropix.itau.dto.ClienteResponse;
import com.agropix.itau.mapper.ClienteMapper;
import com.agropix.itau.model.Cliente;
import com.agropix.itau.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cliente")
public class ClienteController {

    private final ClienteService service;
    private final ClienteMapper mapper;

    @PostMapping()
    public ResponseEntity<ClienteResponse> create(@Valid @RequestBody ClienteRequest clienteRequest) {
        Cliente cliente = service.save(clienteRequest);
        ClienteResponse clienteResponse = mapper.toResponse(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteResponse);
    }

    @GetMapping("{clienteId}")
    public ResponseEntity<ClienteResponse> findById(@PathVariable UUID clienteId) {
        Cliente cliente = service.findById(clienteId);
        ClienteResponse clienteResponse = mapper.toResponse(cliente);
        return ResponseEntity.status(HttpStatus.OK).body(clienteResponse);
    }

    @GetMapping()
    public ResponseEntity<List<ClienteResponse>> findAll() {
        List<Cliente> clienteList = service.findAll();
        List<ClienteResponse> clienteResponseList = mapper.toResponseList(clienteList);
        return ResponseEntity.status(HttpStatus.OK).body(clienteResponseList);
    }

    @PutMapping("{clienteId}")
    public ResponseEntity<ClienteResponse> update(@Valid @RequestBody ClienteRequest clienteRequest, @PathVariable UUID clienteId) {
        Cliente cliente = service.update(clienteId, clienteRequest);
        ClienteResponse clienteResponse = mapper.toResponse(cliente);
        return ResponseEntity.status(HttpStatus.OK).body(clienteResponse);
    }

    @DeleteMapping("{clienteId}")
    public void delete(@PathVariable UUID clienteId) {
        service.delete(clienteId);
    }

}
