package com.agropix.itau.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
public class ChavePixCreationRequest {

    @NotNull(message = "Chave Pix do cliente não pode ser nulo!")
    private String chavePix;

    @NotNull(message = "Chave Pix do cliente não pode ser nulo!")
    private String tipo;

    @NotNull(message = "Chave Pix do cliente não pode ser nulo!")
    private UUID contaId;

}
