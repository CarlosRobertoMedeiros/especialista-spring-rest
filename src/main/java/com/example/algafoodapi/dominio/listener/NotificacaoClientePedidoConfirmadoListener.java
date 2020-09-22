package com.example.algafoodapi.dominio.listener;
/*
 *  @criado em: 11/08/2020 - {06:21}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */

import com.example.algafoodapi.dominio.event.PedidoConfirmadoEvent;
import com.example.algafoodapi.dominio.modelo.Pedido;
import com.example.algafoodapi.dominio.service.EnvioEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class NotificacaoClientePedidoConfirmadoListener {

    @Autowired
    EnvioEmailService envioEmailService;

    @TransactionalEventListener()
    public void aoConfirmarPedido(PedidoConfirmadoEvent event){
        Pedido pedido = event.getPedido();

        EnvioEmailService.Mensagem mensagem =  EnvioEmailService.Mensagem.builder()
                .assunto(pedido.getRestaurante().getNome()+ " - Pedido confirmado")
                .corpo("emails/pedido-confirmado.html")
                .variavel("pedido",pedido)
                .destinatario(pedido.getCliente().getEmail()).build();

        envioEmailService.enviar(mensagem);


    }

}
