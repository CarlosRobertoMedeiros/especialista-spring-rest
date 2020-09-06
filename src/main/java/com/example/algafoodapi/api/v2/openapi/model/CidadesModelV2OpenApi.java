package com.example.algafoodapi.api.v2.openapi.model;
/*
 *  @criado em: 06/09/2020 - {14:57}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */

import com.example.algafoodapi.api.v2.model.CidadeModelV2;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.hateoas.Links;

import java.util.List;

@ApiModel("CidadesModel")
@Data
public class CidadesModelV2OpenApi {

    private CidadesEmbeddedModelOpenApi _embedded;
    private Links _links;

    @ApiModel("CidadesEmbeddedModel")
    @Data
    public class CidadesEmbeddedModelOpenApi {

        private List<CidadeModelV2> cidades;

    }


}
