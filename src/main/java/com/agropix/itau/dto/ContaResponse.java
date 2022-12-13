package com.agropix.itau.dto;

import com.agropix.itau.model.Cliente;
import com.agropix.itau.model.TipoConta;

import java.math.BigDecimal;
import java.util.UUID;

public class ContaResponse {

    private UUID id;
    private Long numeroConta;
    private Integer digito;
    private String agencia;
    private Cliente cliente;
    private Double saldo;
    private TipoConta tipoConta;
    private String nomeBanco;

}
