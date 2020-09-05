package com.example.algafoodapi.api.v1.openapi.model;
/*
 *  @criado em: 02/09/2020 - {21:10}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */

import com.example.algafoodapi.api.v1.model.EstadoModel;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.hateoas.Links;

import java.util.List;

@ApiModel("EstadosModel")
@Data
public class EstadosModelOpenApi {

    private EstadosEmbeddedModelOpenApi _embedded;
    private Links _links;

    @ApiModel("EstadosEmbeddedModel")
    @Data
    public class EstadosEmbeddedModelOpenApi {

        private List<EstadoModel> estados;

    }




}
