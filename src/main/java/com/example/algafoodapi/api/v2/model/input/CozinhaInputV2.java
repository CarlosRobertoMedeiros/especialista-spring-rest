package com.example.algafoodapi.api.v2.model.input;
/*
 *  @criado em: 04/09/2020 - {21:25}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
@ApiModel("CozinhaInput")
public class CozinhaInputV2 {

    @ApiModelProperty(example = "Brasileira", required = true)
    @NotBlank
    private String nomeCozinha;
}

