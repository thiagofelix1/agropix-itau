package com.agropix.itau.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
public class ContaRequest {

    @NotNull(message = "Número da conta do cliente não pode ser nulo!")
    private Long numeroConta;
    @NotNull@NotNull(message = "Digito do número da conta do cliente não pode ser nulo!")
    private Integer digito;
    @NotNull@NotNull(message = "Agencia do cliente da conta não pode ser nulo!")
    private String agencia;
    @NotNull(message = "Id do cliente da conta não pode ser nulo!")
    private UUID clienteId;
    @NotNull(message = "Id do tipo da conta não pode ser nulo!")
    private UUID tipoContaId;
    @NotNull(message = "Nome do banco não pode ser nulo!")
    private String nomeBanco;

}
