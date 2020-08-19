package com.example.algafoodapi.api.model.input;
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
public class CidadeInput {

    @ApiModelProperty(example = "Brasília", required = true)
    @NotBlank
    private String nome;

    @Valid
    @NotNull
    private EstadoIdInput estado;

}
