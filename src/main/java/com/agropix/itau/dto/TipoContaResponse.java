package com.agropix.itau.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class TipoContaResponse {

    private UUID id;
    private String tipoConta;

}
