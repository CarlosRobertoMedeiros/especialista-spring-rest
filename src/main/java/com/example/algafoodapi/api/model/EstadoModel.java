package com.example.algafoodapi.api.model;
/*
 *  @criado em: 15/07/2020 - {08:12}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EstadoModel {

    @ApiModelProperty(example = "1")
    private Long id;

    @ApiModelProperty(example = "Brasília")
    private String nome;
}
