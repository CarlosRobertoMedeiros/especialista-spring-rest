package com.example.algafoodapi.api.controller;
/*
 *  @criado em: 21/07/2020 - {06:55}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */

import com.example.algafoodapi.api.assembler.PedidoModelAssembler;
import com.example.algafoodapi.api.model.PedidoModel;
import com.example.algafoodapi.dominio.modelo.Pedido;
import com.example.algafoodapi.dominio.repository.PedidoRepository;
import com.example.algafoodapi.dominio.service.EmissaoPedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoController {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private EmissaoPedidoService emissaoPedidoService;

    @Autowired
    private PedidoModelAssembler pedidoModelAssembler;

    @GetMapping
    public List<PedidoModel> listar() {
        List<Pedido> todosPedidos = pedidoRepository.findAll();

        return pedidoModelAssembler.toCollectionModel(todosPedidos);
    }

    @GetMapping("/{pedidoId}")
    public PedidoModel buscar(@PathVariable Long pedidoId) {
        Pedido pedido = emissaoPedidoService.buscarOuFalhar(pedidoId);

        return pedidoModelAssembler.toModel(pedido);
    }
}
