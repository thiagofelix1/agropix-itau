package com.agropix.itau.dto;

import com.agropix.itau.model.Cliente;
import com.agropix.itau.model.TipoConta;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.UUID;

public class ContaRequest {

    // ToDo: Criar Conta Request
    @NotNull
    private Long numeroConta;
    @NotNull
    private Integer digito;
    @NotNull
    private String agencia;
    @NotNull
    private UUID clienteId;
    @NotNull
    private UUID tipoContaId;
    @NotNull
    private String nomeBanco;

}
