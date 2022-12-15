package com.agropix.itau.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotNull;
@Getter
@Setter
public class ClienteRequest {

    @NotNull(message = "Nome do cliente não pode ser nulo!")
    private String nome;

    @NotNull(message = "CPF do cliente não pode ser nulo!")
    @Length(min =11, max = 11)
    private String cpf;

    @NotNull(message = "E-mail do cliente não pode ser nulo!")
    private String email;

    @NotNull@NotNull(message = "Telefone do cliente não pode ser nulo!")
    private String telefone;

}
