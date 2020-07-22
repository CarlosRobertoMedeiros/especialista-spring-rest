package com.example.algafoodapi.api.controller;
/*
 *  @criado em: 21/07/2020 - {06:55}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */

import com.example.algafoodapi.api.assembler.PedidoInputDisassembler;
import com.example.algafoodapi.api.assembler.PedidoModelAssembler;
import com.example.algafoodapi.api.assembler.PedidoResumoModelAssembler;
import com.example.algafoodapi.api.model.PedidoModel;
import com.example.algafoodapi.api.model.PedidoResumoModel;
import com.example.algafoodapi.api.model.input.PedidoInput;
import com.example.algafoodapi.dominio.exception.EntidadeNaoEncontradaException;
import com.example.algafoodapi.dominio.exception.NegocioException;
import com.example.algafoodapi.dominio.modelo.Pedido;
import com.example.algafoodapi.dominio.modelo.Usuario;
import com.example.algafoodapi.dominio.repository.PedidoRepository;
import com.example.algafoodapi.dominio.service.EmissaoPedidoService;
import com.example.algafoodapi.dominio.service.FluxoPedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/pedidos/{pedidoId}")
public class FluxoPedidoController {

    @Autowired
    private FluxoPedidoService fluxoPedido;

    @PutMapping("/confirmacao")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void confirmar(@PathVariable Long pedidoId){
        fluxoPedido.confirmar(pedidoId);
    }


}
