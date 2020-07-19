package com.example.algafoodapi.dominio.exception;
/*
 *  @criado em: 19/07/2020 - {10:20}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */

public class UsuarioNaoEncontradoException extends EntidadeNaoEncontradaException{

    public UsuarioNaoEncontradoException(String mensagem) {
        super(mensagem);
    }

    public UsuarioNaoEncontradoException(Long usuarioId) {
        this(String.format("Não existe um cadastro de usuário com código %d", usuarioId));
    }

}
