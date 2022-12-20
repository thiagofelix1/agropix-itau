package com.agropix.itau.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class InfoChavePixBacen {
    private final String nomeTitular;
    private final String chave;
    private final String tipo;
    private final String banco;
}
