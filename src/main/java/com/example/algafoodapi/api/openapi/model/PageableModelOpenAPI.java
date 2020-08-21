package com.example.algafoodapi.api.openapi.model;
/*
 *  @criado em: 20/08/2020 - {09:16}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@ApiModel("Pageable")
@Setter
@Getter
public class PageableModelOpenAPI {

    @ApiModelProperty(example = "0", value = "Número da página (começa em 0)")
    private int page;

    @ApiModelProperty(example = "10", value = "Quantidade de Elementos por Página")
    private int size;

    @ApiModelProperty(example = "nome,asc", value = "Nome da Propriedade para Ordenação")
    private List<String> sort;

}
