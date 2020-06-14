package com.example.algafoodapi.notificacao;
/*
 *  @criado em: 14/06/2020 - {11:06}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */

import com.example.algafoodapi.modelo.Cliente;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("dev")
@TipoDoNotificador(NivelUrgencia.URGENTE)
@Component
public class NotificadorEmailMock implements Notificador {

    public NotificadorEmailMock() {
        System.out.println("Notificador Email Mock ");
    }

    @Override
    public void notificarEmail(Cliente cliente, String mensagem) {
        System.out.printf("MOCK: Notificação seria enviada para: %s através da mensagem: %s\n",
                cliente.getNome(),cliente.getEmail());
    }
}
