package com.example.algafoodapi.dominio.exception;
/*
 *  @criado em: 23/06/2020 - {07:27}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class EstadoNaoEncontradoException extends EntidadeNaoEncontradaException {

    public EstadoNaoEncontradoException(String message) {
        super(message);
    }

    public EstadoNaoEncontradoException(Long estadoId) {
        this(String.format("Não existe um cadastro de estado com código %d",estadoId));
    }
}
