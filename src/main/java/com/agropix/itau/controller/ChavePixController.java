package com.agropix.itau.controller;

import com.agropix.itau.dto.ChavePixBacenResponse;
import com.agropix.itau.dto.ChavePixRequest;
import com.agropix.itau.dto.ChavePixResponse;
import com.agropix.itau.mapper.ChavePixMapper;
import com.agropix.itau.model.ChavePix;
import com.agropix.itau.service.ChavePixService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/chavePix")
public class ChavePixController {

    private final ChavePixService service;
    private final ChavePixMapper mapper;

    @PostMapping()
    public ResponseEntity<ChavePixResponse> create(@RequestBody ChavePixRequest chavePixRequest) {
        Optional<ChavePix> chavePix = service.save(chavePixRequest);
        ChavePixResponse chavePixResponse = mapper.toResponse(chavePix.get());
        return ResponseEntity.status(HttpStatus.CREATED).body(chavePixResponse);
    }

    @GetMapping("{chavePixId}")
    public ResponseEntity<ChavePixResponse> findById(@PathVariable String chavePixId) {
        ChavePix chavePix = service.findByChavePix(chavePixId);
        ChavePixResponse chavePixResponse = mapper.toResponse(chavePix);
        return ResponseEntity.status(HttpStatus.OK).body(chavePixResponse);
    }

    @GetMapping()
    public ResponseEntity<List<ChavePixResponse>> findAll() {
        List<ChavePix> chavePixList = service.findAll();
        List<ChavePixResponse> chavePixResponseList = mapper.toResponseList(chavePixList);
        return ResponseEntity.status(HttpStatus.OK).body(chavePixResponseList);
    }

    @DeleteMapping("{chavePixId}")
    public void delete(@PathVariable UUID chavePixId) {
        service.delete(chavePixId);
    }

}
