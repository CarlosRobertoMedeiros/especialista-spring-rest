package com.example.algafoodapi.service;/*
 *  @criado em: 12/06/2020 - {05:36}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */

import com.example.algafoodapi.modelo.Cliente;
import com.example.algafoodapi.notificacao.Notificador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AtivacaoClienteService {

    @Autowired
    private List<Notificador> notificadores;

    public void ativar(Cliente cliente){
        cliente.ativar();

        for (Notificador notificador:notificadores){
            //Deveria ter mudado para notificar
            notificador.notificarEmail(cliente,"Seu Cadastro no Sistema está Ativo !!!");
        }
    }
}
