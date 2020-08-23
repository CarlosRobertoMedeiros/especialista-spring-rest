package com.example.algafoodapi.api.model.input;
/*
 *  @criado em: 19/07/2020 - {10:02}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Setter
@Getter
public class UsuarioInput {

    @ApiModelProperty(example = "João da Silva", required = true)
    @NotBlank
    private String nome;

    @ApiModelProperty(example = "joao.ger@algafood.com.br", required = true)
    @NotBlank
    @Email
    private String email;

}
