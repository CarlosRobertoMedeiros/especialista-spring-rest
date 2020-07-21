package com.example.algafoodapi.api.model;
/*
 *  @criado em: 20/07/2020 - {21:03}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PermissaoModel {

    private Long id;
    private String nome;
    private String descricao;

}
