package com.example.algafoodapi.dominio.service;
/*
 *  @criado em: 21/07/2020 - {06:54}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */

import com.example.algafoodapi.dominio.exception.PedidoNaoEncontradoException;
import com.example.algafoodapi.dominio.modelo.Pedido;
import com.example.algafoodapi.dominio.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmissaoPedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    public Pedido buscarOuFalhar(Long pedidoId) {
        return pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new PedidoNaoEncontradoException(pedidoId));
    }
}
