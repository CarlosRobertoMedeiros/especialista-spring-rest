package com.example.algafoodapi.api.model;
/*
 *  @criado em: 14/07/2020 - {13:11}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CidadeResumoModel {

    private Long id;
    private String nome;
    private String estado;

}
