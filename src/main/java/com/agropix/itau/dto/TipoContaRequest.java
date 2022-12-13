package com.agropix.itau.dto;

import javax.validation.constraints.NotNull;

public class TipoContaRequest {

    @NotNull(message = "Tipo Conta não pode ser nulo!")
    private String tipoConta;

}
