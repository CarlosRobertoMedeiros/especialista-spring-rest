package com.example.algafoodapi.listener;
/*
 *  @criado em: 15/06/2020 - {05:28}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */

import com.example.algafoodapi.notificacao.NivelUrgencia;
import com.example.algafoodapi.notificacao.Notificador;
import com.example.algafoodapi.notificacao.TipoDoNotificador;
import com.example.algafoodapi.service.ClienteAtivadoEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class NotificacaoService {

    @TipoDoNotificador(NivelUrgencia.URGENTE)
    @Autowired
    Notificador notificador;

    @EventListener
    public void clienteAtivadoListener(ClienteAtivadoEvent event){
        notificador.notificarEmail(event.getCliente(), "Seu Cadastro no Sistema Está Ativo");
    }
}
