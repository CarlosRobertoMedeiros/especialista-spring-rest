package com.example.algafoodapi.service;/*
 *  @criado em: 12/06/2020 - {05:36}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */

import com.example.algafoodapi.modelo.Cliente;
import com.example.algafoodapi.notificacao.Notificador;

public class AtivacaoClienteService {

    private Notificador notificador;

    public AtivacaoClienteService(Notificador notificador) {
        this.notificador = notificador;
        System.out.println("Ativação Cliente Service "+this+" notificadorEmail "+notificador);
    }

    public void ativar(Cliente cliente){
        cliente.ativar();
        notificador.notificarEmail(cliente, "Seu Cadastro No sistema Está Ativo");

    }
}
