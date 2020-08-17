package com.example.algafoodapi.api.assembler;
/*
 *  @criado em: 18/07/2020 - {23:44}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */

import com.example.algafoodapi.api.model.input.PedidoInput;
import com.example.algafoodapi.api.model.input.ProdutoInput;
import com.example.algafoodapi.dominio.modelo.Pedido;
import com.example.algafoodapi.dominio.modelo.Produto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PedidoInputDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public Pedido toDomainObject(PedidoInput pedidoInput) {
        return modelMapper.map(pedidoInput, Pedido.class);
    }

    public void copyToDomainObject(PedidoInput pedidoInput, Pedido pedido) {
        modelMapper.map(pedidoInput, pedido);
    }
}