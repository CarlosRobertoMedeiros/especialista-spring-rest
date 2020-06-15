package com.example.algafoodapi.notificacao;
/*
 *  @criado em: 12/06/2020 - {05:27}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */

import com.example.algafoodapi.modelo.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@TipoDoNotificador(NivelUrgencia.URGENTE)
@Component
public class NotificadorEmail implements Notificador {

    @Autowired
    NotificadorProperties properties;

    @Override
    public void notificarEmail(Cliente cliente, String mensagem) {
        System.out.println("Host: "+properties.getHostServidor());
        System.out.println("Porta" + properties.getPortaServidor());

        System.out.printf("Notificando %s através da mensagem: %s\n",
                           cliente.getNome(),cliente.getEmail());
    }
}
