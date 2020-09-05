package com.example.algafoodapi.api.v1.model;/*
 *  @criado em: 18/07/2020 - {21:52}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoModel {

    @ApiModelProperty(example = "70000-000")
    private String cep;

    @ApiModelProperty(example = "SQN")
    private String logradouro;

    @ApiModelProperty(example = "\"402\"")
    private String numero;

    @ApiModelProperty(example = "Bloco Z Apt X")
    private String complemento;

    @ApiModelProperty(example = "Asa Norte")
    private String bairro;

    private CidadeResumoModel cidade;
}
