package com.example.algafoodapi.api.v1.openapi.model;
/*
 *  @criado em: 02/09/2020 - {21:03}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel("PageModel")
@Getter
@Setter
public class PageModelOpenApi {

    @ApiModelProperty(example = "10", value = "Quantidade de Registros por Página")
    private Long size;

    @ApiModelProperty(example = "50", value = "Total de Registros")
    private Long totalElements;

    @ApiModelProperty(example = "5", value = "Total de Páginas")
    private Long totalPages;

    @ApiModelProperty(example = "0", value = "Número da Página (Começa em 0)")
    private Long number;

}
