package com.example.algafoodapi.api.model.input;
/*
 *  @criado em: 18/07/2020 - {23:40}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class GrupoInput {

    @NotBlank
    private String nome;
}
