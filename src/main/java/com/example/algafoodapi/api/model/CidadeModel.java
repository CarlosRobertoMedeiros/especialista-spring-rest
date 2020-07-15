package com.example.algafoodapi.api.model;
/*
 *  @criado em: 15/07/2020 - {08:12}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CidadeModel {

    private Long id;
    private String nome;
    private EstadoModel estado;
}
