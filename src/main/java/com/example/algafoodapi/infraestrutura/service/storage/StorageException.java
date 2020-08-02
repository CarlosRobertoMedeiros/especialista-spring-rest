package com.example.algafoodapi.infraestrutura.service.storage;
/*
 *  @criado em: 02/08/2020 - {10:06}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */

public class StorageException extends RuntimeException{

    public StorageException(String message) {
        super(message);
    }

    public StorageException(String message, Throwable cause) {
        super(message, cause);
    }
}
