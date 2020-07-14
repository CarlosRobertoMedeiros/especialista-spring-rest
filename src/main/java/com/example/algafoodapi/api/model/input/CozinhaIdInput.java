package com.example.algafoodapi.api.model.input;
/*
 *  @criado em: 14/07/2020 - {14:31}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Setter
@Getter
public class CozinhaIdInput {

    @NotNull
    private Long id;
}
