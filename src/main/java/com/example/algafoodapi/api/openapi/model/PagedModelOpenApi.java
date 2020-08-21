package com.example.algafoodapi.api.openapi.model;
/*
 *  @criado em: 21/08/2020 - {08:46}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */

import com.example.algafoodapi.api.model.CozinhaModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PagedModelOpenApi<T> {

    private List<T> content;

    @ApiModelProperty(example = "10", value = "Quantidade de Registros por Página")
    private Long size;

    @ApiModelProperty(example = "50", value = "Total de Registros")
    private Long totalElements;

    @ApiModelProperty(example = "5", value = "Total de Páginas")
    private Long totalPages;

    @ApiModelProperty(example = "0", value = "Número da Página (Começa em 0)")
    private Long number;
}
