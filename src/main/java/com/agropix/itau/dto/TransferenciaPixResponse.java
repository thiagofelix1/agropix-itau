package com.agropix.itau.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class TransferenciaPixResponse {
    private String chaveOrigem;
    private String chaveDestino;
    private Double valor;
    private String banco;
}
