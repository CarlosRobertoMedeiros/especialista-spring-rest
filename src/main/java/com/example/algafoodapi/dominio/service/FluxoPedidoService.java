package com.example.algafoodapi.dominio.service;
/*
 *  @criado em: 22/07/2020 - {08:40}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */

import com.example.algafoodapi.dominio.modelo.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FluxoPedidoService {

    @Autowired
    private EmissaoPedidoService emissaoPedidoService;

    @Autowired
    private EnvioEmailService envioEmailService;

    @Transactional
    public void confirmar(String codigoPedido){
        Pedido pedido = emissaoPedidoService.buscarOuFalhar(codigoPedido);
        pedido.confirmar();

        EnvioEmailService.Mensagem mensagem =  EnvioEmailService.Mensagem.builder()
                .assunto(pedido.getRestaurante().getNome()+ " - Pedido confirmado")
                .corpo("pedido-confirmado.html")
                .variavel("pedido",pedido)
                .destinatario(pedido.getCliente().getEmail()).build();

        envioEmailService.enviar(mensagem);


    }

    @Transactional
    public void cancelar(String codigoPedido) {
        Pedido pedido = emissaoPedidoService.buscarOuFalhar(codigoPedido);
       pedido.cancelar();
    }

    @Transactional
    public void entregar(String codigoPedido) {
        Pedido pedido = emissaoPedidoService.buscarOuFalhar(codigoPedido);
        pedido.entregar();
    }

}
