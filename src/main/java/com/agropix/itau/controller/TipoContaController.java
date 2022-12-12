package com.agropix.itau.controller;

import com.agropix.itau.dto.TipoContaRequest;
import com.agropix.itau.dto.TipoContaResponse;
import com.agropix.itau.mapper.TipoContaMapper;
import com.agropix.itau.model.TipoConta;
import com.agropix.itau.service.TipoContaService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/tipo-conta")
@RequiredArgsConstructor
public class TipoContaController {

    private final TipoContaService service;
    private final TipoContaMapper mapper;

    // ToDo: Create TipoConta Controller
    @PostMapping()
    public ResponseEntity<TipoContaResponse> create(@RequestBody TipoContaRequest tipoContaRequest) {
        TipoConta tipoConta = service.create(tipoContaRequest);
        TipoContaResponse tipoContaResponse = mapper.toResponse(tipoConta);
        return ResponseEntity.status(HttpStatus.CREATED).body(tipoContaResponse);
    }

    // ToDo: Read TipoConta Controller
//    @GetMapping("{tipoContaId}")
//    public ResponseEntity<TipoContaResponse> findById(@PathVariable UUID tipoContaId) {
//        TipoConta tipoConta = service.findTipoContaId(tipoContaId);
//        return new ResponseEntity<>(tipoConta, HttpStatus.OK);
//    }

    // ToDo: Update TipoConta Controller

    // ToDo: Delete TipoConta Controller

}
