package com.example.algafoodapi.api.model.input;
/*
 *  @criado em: 15/07/2020 - {08:07}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class EstadoIdInput {

    @NotNull
    private Long id;
}
