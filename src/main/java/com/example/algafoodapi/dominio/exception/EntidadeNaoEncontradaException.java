package com.example.algafoodapi.dominio.exception;
/*
 *  @criado em: 23/06/2020 - {07:27}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public abstract class EntidadeNaoEncontradaException extends NegocioException {

    public EntidadeNaoEncontradaException(String message) {
        super(message);
    }
}
