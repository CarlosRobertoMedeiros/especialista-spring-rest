package com.example.algafoodapi.notificacao;
/*
 *  @criado em: 12/06/2020 - {05:27}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */

import com.example.algafoodapi.modelo.Cliente;
import org.springframework.stereotype.Component;

@TipoDoNotificador(NivelUrgencia.URGENTE)
@Component
public class NotificadorEmail implements Notificador {

    @Override
    public void notificarEmail(Cliente cliente, String mensagem) {
        System.out.printf("Notificando %s através da mensagem: %s\n",
                           cliente.getNome(),cliente.getEmail());
    }
}
