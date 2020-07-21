package com.example.algafoodapi.api.model.input;
/*
 *  @criado em: 15/07/2020 - {08:09}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class EstadoInput {

    @NotBlank
    private String nome;

}