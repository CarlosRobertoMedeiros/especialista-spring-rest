package com.example.algafoodapi.dominio.exception;
/*
 *  @criado em: 23/06/2020 - {07:22}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class EntidadeEmUsoException extends NegocioException {

    public EntidadeEmUsoException(String message) {
        super(message);
    }
}
