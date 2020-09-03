package com.example.algafoodapi.api.openapi.model;
/*
 *  @criado em: 02/09/2020 - {20:40}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@ApiModel("Links")
public class LinksModelOpenApi {

    private LinkModel rel;

    @Setter
    @Getter
    @ApiModel("Link")
    private class LinkModel{
        private String href;
        private boolean templated;

    }



}
