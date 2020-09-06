package com.example.algafoodapi.api.v2.model;
/*
 *  @criado em: 15/07/2020 - {08:12}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@ApiModel("CidadeModel")
@Relation(collectionRelation = "cidades")
@Setter
@Getter
public class CidadeModelV2 extends RepresentationModel<CidadeModelV2> {

    @ApiModelProperty(example = "1")
    private Long idCidade;

    @ApiModelProperty(example = "Distrito Federal")
    private String nomeCidade;
    private Long idEstado;
    private String nomeEstado;
}
