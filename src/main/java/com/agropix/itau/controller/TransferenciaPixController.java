package com.agropix.itau.controller;

import com.agropix.itau.dto.ChavePixRequest;
import com.agropix.itau.dto.TransferenciaPixRequest;
import com.agropix.itau.dto.TransferenciaPixResponse;
import com.agropix.itau.service.TransferenciaPixService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/transferencia-pix")
public class TransferenciaPixController {

    private final TransferenciaPixService service;

    @PostMapping
    public ResponseEntity<TransferenciaPixResponse> transfer(@Valid @RequestBody TransferenciaPixRequest transferenciaPixRequest) {
        return null;
    }

}
