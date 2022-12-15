package com.agropix.itau.controller;

import com.agropix.itau.dto.TipoContaRequest;
import com.agropix.itau.dto.TipoContaResponse;
import com.agropix.itau.mapper.TipoContaMapper;
import com.agropix.itau.model.TipoConta;
import com.agropix.itau.service.TipoContaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/api/tipo-conta")
@RequiredArgsConstructor
public class TipoContaController {

    private final TipoContaService service;
    private final TipoContaMapper mapper;

    @PostMapping()
    public ResponseEntity<TipoContaResponse> create(@Valid @RequestBody TipoContaRequest tipoContaRequest) {
        TipoConta tipoConta = service.create(tipoContaRequest);
        TipoContaResponse tipoContaResponse = mapper.toResponse(tipoConta);
        return ResponseEntity.status(HttpStatus.CREATED).body(tipoContaResponse);
    }

    @GetMapping("{tipoContaId}")
    public ResponseEntity<TipoContaResponse> findById(@PathVariable UUID tipoContaId) {
        TipoConta tipoConta = service.findTipoContaById(tipoContaId);
        TipoContaResponse tipoContaResponse = mapper.toResponse(tipoConta);
        return ResponseEntity.status(HttpStatus.OK).body(tipoContaResponse);
    }

    @PutMapping("{tipoContaId}")
    public ResponseEntity<TipoContaResponse> update(@Valid @RequestBody TipoContaRequest tipoContaRequest, @PathVariable UUID tipoContaId) {
        TipoConta tipoConta = service.update(tipoContaId, tipoContaRequest);
        TipoContaResponse tipoContaResponse = mapper.toResponse(tipoConta);
        return ResponseEntity.status(HttpStatus.OK).body(tipoContaResponse);
    }

    @DeleteMapping("{tipoContaId}")
    public void delete(@PathVariable UUID tipoContaId) {
        service.delete(tipoContaId);
    }

}
