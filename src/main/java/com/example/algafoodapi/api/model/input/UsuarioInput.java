package com.example.algafoodapi.api.model.input;
/*
 *  @criado em: 19/07/2020 - {10:02}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Setter
@Getter
public class UsuarioInput {

    @NotBlank
    private String nome;

    @NotBlank
    @Email
    private String email;

}
