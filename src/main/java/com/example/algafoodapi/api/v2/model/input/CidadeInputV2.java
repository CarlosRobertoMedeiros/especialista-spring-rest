package com.example.algafoodapi.api.v2.model.input;
/*
 *  @criado em: 15/07/2020 - {08:11}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */


import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CidadeInputV2 {

    @ApiModelProperty(example = "Brasília", required = true)
    @NotBlank
    private String nomeCidade;

    @ApiModelProperty(example = "1", required = true)
    @NotNull
    private Long idEstado;
}
