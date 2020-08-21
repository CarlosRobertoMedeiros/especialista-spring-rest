package com.example.algafoodapi.api.model.input;
/*
 *  @criado em: 16/07/2020 - {09:34}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class FormaPagamentoInput {

    @ApiModelProperty(example = "Cartão de crédito", required = true)
    @NotBlank
    private String descricao;

}
