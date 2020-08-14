package com.example.algafood.client.model;
/*
 *  @criado em: 14/08/2020 - {06:42}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */

import lombok.Data;

import java.math.BigDecimal;

@Data
public class RestauranteResumoModel {

    private Long id;
    private String nome;
    private BigDecimal taxaFrete;
    private CozinhaModel cozinha;

}
