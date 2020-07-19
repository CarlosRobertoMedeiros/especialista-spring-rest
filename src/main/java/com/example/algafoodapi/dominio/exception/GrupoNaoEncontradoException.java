package com.example.algafoodapi.dominio.exception;
/*
 *  @criado em: 18/07/2020 - {23:46}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */

public class GrupoNaoEncontradoException extends EntidadeNaoEncontradaException{

    public GrupoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }

    public GrupoNaoEncontradoException(Long estadoId) {
        this(String.format("Não existe um cadastro de grupo com código %d", estadoId));
    }

}
