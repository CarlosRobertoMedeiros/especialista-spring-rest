package com.example.algafoodapi.api.model.input;
/*
 *  @criado em: 19/07/2020 - {10:12}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class UsuarioComSenhaInput  extends UsuarioInput{

    @ApiModelProperty(example = "123", required = true)
    @NotBlank
    private String senha;
}
