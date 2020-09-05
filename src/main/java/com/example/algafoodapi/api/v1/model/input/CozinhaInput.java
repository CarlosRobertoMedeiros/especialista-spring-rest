package com.example.algafoodapi.api.v1.model.input;
/*
 *  @criado em: 15/07/2020 - {08:10}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class CozinhaInput {

    @ApiModelProperty(example = "Brasileira", required = true)
    @NotBlank
    private String nome;
}
