package com.example.algafoodapi.api.model;
/*
 *  @criado em: 21/07/2020 - {06:49}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ItemPedidoModel {
    private Long produtoId;
    private String produtoNome;
    private Integer quantidade;
    private BigDecimal precoUnitario;
    private BigDecimal precoTotal;
    private String observacao;
}
