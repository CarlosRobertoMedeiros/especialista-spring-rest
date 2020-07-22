package com.example.algafoodapi.dominio.service;
/*
 *  @criado em: 22/07/2020 - {08:40}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */

import com.example.algafoodapi.dominio.exception.NegocioException;
import com.example.algafoodapi.dominio.modelo.Pedido;
import com.example.algafoodapi.dominio.modelo.StatusPedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;

@Service
public class FluxoPedidoService {

    @Autowired
    private EmissaoPedidoService emissaoPedidoService;

    @Transactional
    public void confirmar(Long pedidoId){
        Pedido pedido = emissaoPedidoService.buscarOuFalhar(pedidoId);
        if (!pedido.getStatus().equals(StatusPedido.CRIADO)){
            throw new NegocioException(
                    String.format("Status do pedido %n não pode ser alterado de %s para %s",
                            pedido.getId(),
                            pedido.getStatus().getDescricao(),
                            StatusPedido.CONFIRMADO.getDescricao()));
        }
        pedido.setStatus(StatusPedido.CONFIRMADO);
        pedido.setDataConfirmacao(OffsetDateTime.now());

    }

}
