package com.example.algafoodapi.dominio.exception;
/*
 *  @criado em: 23/06/2020 - {07:27}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */

public class PedidoNaoEncontradoException extends EntidadeNaoEncontradaException {

//    public PedidoNaoEncontradoException(String message) {
//        super(message);
//    }

    public PedidoNaoEncontradoException(String codigoPedido) {
        super(String.format("Não existe um pedido com código %s",codigoPedido));
    }
}
