package com.example.algafoodapi.notificacao;/*
 *  @criado em: 12/06/2020 - {06:25}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */

import com.example.algafoodapi.modelo.Cliente;

public interface Notificador {
    void notificarEmail(Cliente cliente, String mensagem);
}
