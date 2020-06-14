package com.example.algafoodapi.notificacao;/*
 *  @criado em: 12/06/2020 - {16:59}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */

import com.example.algafoodapi.modelo.Cliente;
import org.springframework.stereotype.Component;

@TipoDoNotificador(NivelUrgencia.NORMAL)
@Component
public class NotificadorSMS implements Notificador {

    @Override
    public void notificarEmail(Cliente cliente, String mensagem) {
        System.out.printf("Notificando %s através da mensagem: %s\n",
                cliente.getTelefone(),cliente.getEmail());
    }
}
