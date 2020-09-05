package com.example.algafoodapi.api.v1.openapi.model;
/*
 *  @criado em: 02/09/2020 - {21:16}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */

import com.example.algafoodapi.api.v1.model.PermissaoModel;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.hateoas.Links;

import java.util.List;

@ApiModel("PermissoesModel")
@Data
public class PermissoesModelOpenApi {
    private PermissoesEmbeddedModelOpenApi _embedded;
    private Links _links;

    @ApiModel("PermissoesEmbeddedModel")
    @Data
    public class PermissoesEmbeddedModelOpenApi {

        private List<PermissaoModel> permissoes;

    }

}
