package com.example.algafoodapi.api.model;
/*
 *  @criado em: 21/07/2020 - {06:50}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */

import com.fasterxml.jackson.annotation.JsonFilter;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

//@JsonFilter("pedidoFilter")
@Setter
@Getter
public class PedidoResumoModel {

    private String codigo;
    private BigDecimal subtotal;
    private BigDecimal taxaFrete;
    private BigDecimal valorTotal;
    private String status;
    private OffsetDateTime dataCriacao;
    private RestauranteResumoModel restaurante;
    private UsuarioModel cliente;

}
