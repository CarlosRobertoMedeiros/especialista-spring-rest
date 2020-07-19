package com.example.algafoodapi.api.model;
/*
 *  @criado em: 19/07/2020 - {10:14}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioModel {
    private Long id;
    private String nome;
    private String email;
}
