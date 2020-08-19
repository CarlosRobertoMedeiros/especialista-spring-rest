package com.example.algafoodapi.api.model;
/*
 *  @criado em: 15/07/2020 - {08:12}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

//@ApiModel(value = "Cidade", description = "Representa uma cidade")
@Setter
@Getter
public class CidadeModel {

//    @ApiModelProperty(value = "ID da Cidade", example = "1")
    @ApiModelProperty(example = "1")
    private Long id;

    @ApiModelProperty(example = "Distrito Federal")
    private String nome;

    private EstadoModel estado;
}
