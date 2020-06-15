package com.example.algafoodapi.service;
/*
 *  @criado em: 15/06/2020 - {05:24}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */

import com.example.algafoodapi.modelo.Cliente;

public class ClienteAtivadoEvent {

    private Cliente cliente;

    public ClienteAtivadoEvent(Cliente cliente) {
        this.cliente = cliente;
    }

    public Cliente getCliente() {
        return cliente;
    }
}
