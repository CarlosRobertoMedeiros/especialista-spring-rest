package com.example.algafoodapi.api.model.input;
/*
 *  @criado em: 18/07/2020 - {23:02}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CidadeIdInput {

    @ApiModelProperty(example = "1", required = true)
    @NotNull
    private Long id;

}
