package com.example.algafoodapi.api.model.input;
/*
 *  @criado em: 22/07/2020 - {07:55}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class RestauranteIdInput {

    @NotNull
    private Long id;

}
