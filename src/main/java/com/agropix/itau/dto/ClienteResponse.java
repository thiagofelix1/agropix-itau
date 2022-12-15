package com.agropix.itau.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;
@Getter
@Setter
public class ClienteResponse {

    private UUID id;
    private String nome;
    private String cpf;
    private String email;
    private String telefone;

}
