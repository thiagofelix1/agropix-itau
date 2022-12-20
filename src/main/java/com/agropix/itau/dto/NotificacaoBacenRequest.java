package com.agropix.itau.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotificacaoBacenRequest {

    private String chaveOrigem;
    private String chaveDestino;
    private String bancoOrigem;
    private String nomePessoaEnvio;
    private double valor;

}
