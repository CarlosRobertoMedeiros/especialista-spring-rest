package com.example.algafoodapi.api.model.input;
/*
 *  @criado em: 19/07/2020 - {10:12}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class UsuarioComSenhaInput  extends UsuarioInput{

    @NotBlank
    private String senha;
}
