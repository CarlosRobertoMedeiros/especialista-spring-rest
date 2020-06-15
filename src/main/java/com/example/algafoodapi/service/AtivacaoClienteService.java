package com.example.algafoodapi.service;
/*
 *  @criado em: 12/06/2020 - {05:36}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */

import com.example.algafoodapi.modelo.Cliente;
import com.example.algafoodapi.notificacao.NivelUrgencia;
import com.example.algafoodapi.notificacao.Notificador;
import com.example.algafoodapi.notificacao.TipoDoNotificador;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;

@Component
public class AtivacaoClienteService{

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    public void ativar(Cliente cliente){
        cliente.ativar ();
        //Vai disparar um evento para o sistema
        //Qualquer Classe que quiser vai poder consumir o ouvinte
        eventPublisher.publishEvent(new ClienteAtivadoEvent(cliente));
    }
}
