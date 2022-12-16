package com.agropix.itau.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class TransferenciaPixResponse {

    private UUID id;
    private String chaveOrigem;
    private String chaveDestino;
    private Double valor;

}
