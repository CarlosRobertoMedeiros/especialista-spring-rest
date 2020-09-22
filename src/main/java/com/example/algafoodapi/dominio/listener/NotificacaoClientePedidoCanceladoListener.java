package com.example.algafoodapi.dominio.listener;
/*
 *  @criado em: 11/08/2020 - {06:47}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */

import com.example.algafoodapi.dominio.event.PedidoCanceladoEvent;
import com.example.algafoodapi.dominio.modelo.Pedido;
import com.example.algafoodapi.dominio.service.EnvioEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.event.TransactionalEventListener;

public class NotificacaoClientePedidoCanceladoListener {

    @Autowired
    private EnvioEmailService envioEmailService;

    @TransactionalEventListener
    public void aoCancelarPedido(PedidoCanceladoEvent event) {
        Pedido pedido = event.getPedido();

        EnvioEmailService.Mensagem mensagem =  EnvioEmailService.Mensagem.builder()
                .assunto(pedido.getRestaurante().getNome()+ " - Pedido cancelado")
                .corpo("emails/pedido-cancelado.html")
                .variavel("pedido",pedido)
                .destinatario(pedido.getCliente().getEmail()).build();

        envioEmailService.enviar(mensagem);
    }


}
