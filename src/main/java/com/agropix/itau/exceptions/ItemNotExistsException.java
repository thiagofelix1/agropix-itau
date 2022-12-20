package com.agropix.itau.exceptions;

public class ItemNotExistsException extends RuntimeException{

    public ItemNotExistsException(String message) {
        super(message);
    }
}
