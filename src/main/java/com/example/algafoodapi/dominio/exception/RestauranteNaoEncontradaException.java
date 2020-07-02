package com.example.algafoodapi.dominio.exception;
/*
 *  @criado em: 23/06/2020 - {07:27}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND)
public class RestauranteNaoEncontradaException extends NegocioException {

    public RestauranteNaoEncontradaException(String message) {
        super(message);
    }

    public RestauranteNaoEncontradaException(Long id) {
        this(String.format("Não existe um cadastro de restaurante com código %d",id));
    }
}
