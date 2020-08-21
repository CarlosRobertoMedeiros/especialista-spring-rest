package com.example.algafoodapi.api.model;
/*
 *  @criado em: 14/07/2020 - {13:11}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CidadeResumoModel {

    @ApiModelProperty(example = "1")
    private Long id;

    @ApiModelProperty(example = "Brasília")
    private String nome;

    @ApiModelProperty(example = "DF")
    private String estado;

}
