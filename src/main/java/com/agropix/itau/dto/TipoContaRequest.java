package com.agropix.itau.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
@Getter
@Setter
public class TipoContaRequest {

    @NotNull(message = "Tipo Conta não pode ser nulo!")
    private String tipoConta;

}
