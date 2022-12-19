package com.agropix.itau.exceptions;

public class SaldoInsuficienteException extends RuntimeException{
    public SaldoInsuficienteException(String message) {
        super(message);
    }
}
