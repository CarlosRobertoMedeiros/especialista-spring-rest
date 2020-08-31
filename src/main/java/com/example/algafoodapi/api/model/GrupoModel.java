package com.example.algafoodapi.api.model;
/*
 *  @criado em: 18/07/2020 - {23:41}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */


import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "grupos")
@Getter
@Setter
public class GrupoModel
        extends RepresentationModel<GrupoModel>  {

    @ApiModelProperty(example = "1")
    private Long id;

    @ApiModelProperty(example = "Gerente")
    private String nome;

}
