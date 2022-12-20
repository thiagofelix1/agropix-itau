package com.agropix.itau.dto;

import com.agropix.itau.model.Conta;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class ChavePixResponse {

    private String chavePix;

    private String tipo;

    private Conta conta;
}
