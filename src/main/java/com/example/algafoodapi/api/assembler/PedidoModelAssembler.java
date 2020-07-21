package com.example.algafoodapi.api.assembler;
/*
 *  @criado em: 21/07/2020 - {06:51}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */

import com.example.algafoodapi.api.model.PedidoModel;
import com.example.algafoodapi.dominio.modelo.Pedido;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PedidoModelAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public PedidoModel toModel(Pedido pedido) {
        return modelMapper.map(pedido, PedidoModel.class);
    }

    public List<PedidoModel> toCollectionModel(List<Pedido> pedidos) {
        return pedidos.stream()
                .map(pedido -> toModel(pedido))
                .collect(Collectors.toList());
    }
}
