package com.example.algafoodapi.dominio.exception;
/*
 *  @criado em: 23/06/2020 - {07:27}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.BAD_REQUEST)
public class NegocioException extends RuntimeException {

    public NegocioException(String message) {
        super(message);
    }

    public NegocioException(String message, Throwable causa) {
        super(message,causa);
    }
}
