package com.example.algafoodapi.api.model.input;
/*
 *  @criado em: 15/07/2020 - {08:07}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class EstadoIdInput {

    @ApiModelProperty(example = "1", required = true)
    @NotNull
    private Long id;
}
