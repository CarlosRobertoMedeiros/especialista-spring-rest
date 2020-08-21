package com.example.algafoodapi.api.model;
/*
 *  @criado em: 14/07/2020 - {13:11}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */

import com.example.algafoodapi.api.model.view.RestauranteView;
import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CozinhaModel {

    @ApiModelProperty(example = "1")
    @JsonView(RestauranteView.Resumo.class)
    private Long id;

    @ApiModelProperty(example = "Brasileira")
    @JsonView(RestauranteView.Resumo.class)
    private String nome;

}
