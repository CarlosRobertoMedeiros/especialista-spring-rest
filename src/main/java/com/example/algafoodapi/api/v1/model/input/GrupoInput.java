package com.example.algafoodapi.api.v1.model.input;
/*
 *  @criado em: 18/07/2020 - {23:40}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class GrupoInput {

    @ApiModelProperty(example = "Gerente", required = true)
    @NotBlank
    private String nome;
}
