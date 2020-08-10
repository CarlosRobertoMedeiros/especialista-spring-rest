package com.example.algafoodapi.infraestrutura.service.email;
/*
 *  @criado em: 02/08/2020 - {10:06}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */

public class EmailException extends RuntimeException{

    public EmailException(String message) {
        super(message);
    }

    public EmailException(String message, Throwable cause) {
        super(message, cause);
    }
}
