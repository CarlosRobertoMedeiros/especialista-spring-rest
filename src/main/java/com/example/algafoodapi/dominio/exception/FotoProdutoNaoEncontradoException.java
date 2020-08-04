package com.example.algafoodapi.dominio.exception;
/*
 *  @criado em: 03/08/2020 - {21:21}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */

public class FotoProdutoNaoEncontradoException extends EntidadeNaoEncontradaException {

    public FotoProdutoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }

    public FotoProdutoNaoEncontradoException(Long restauranteId, Long produtoId) {
        this(String.format("Não existe um cadastro de foto do produto com código %d para o restaurante de código %d",
                produtoId, restauranteId));
    }
}
