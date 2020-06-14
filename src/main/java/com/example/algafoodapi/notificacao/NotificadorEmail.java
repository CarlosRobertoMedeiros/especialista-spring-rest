package com.example.algafoodapi.notificacao;
/*
 *  @criado em: 12/06/2020 - {05:27}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */

import com.example.algafoodapi.modelo.Cliente;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("prod")
@TipoDoNotificador(NivelUrgencia.URGENTE)
@Component
public class NotificadorEmail implements Notificador {

    public NotificadorEmail() {
        System.out.println("Notificador Email Real");
    }

    @Override
    public void notificarEmail(Cliente cliente, String mensagem) {
        System.out.printf("Notificando %s através da mensagem: %s\n",
                           cliente.getNome(),cliente.getEmail());
    }
}
