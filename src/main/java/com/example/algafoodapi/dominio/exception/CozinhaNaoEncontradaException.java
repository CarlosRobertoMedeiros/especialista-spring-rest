package com.example.algafoodapi.dominio.exception;
/*
 *  @criado em: 23/06/2020 - {07:27}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND)
public class CozinhaNaoEncontradaException extends NegocioException {

    public CozinhaNaoEncontradaException(String message) {
        super(message);
    }

    public CozinhaNaoEncontradaException(Long id) {
        this(String.format("Não existe um cadastro de cozinha com código %d",id));
    }
}
