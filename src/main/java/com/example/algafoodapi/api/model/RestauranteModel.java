package com.example.algafoodapi.api.model;
/*
 *  @criado em: 14/07/2020 - {13:07}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */

import lombok.Getter;
import lombok.Setter;

import javax.websocket.server.ServerEndpoint;
import java.math.BigDecimal;

@Getter
@Setter
public class RestauranteModel {

    private Long id;
    private String nome;
    private BigDecimal taxaFrete;
    private CozinhaModel cozinhaModel;

}
