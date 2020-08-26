package com.example.algafoodapi.api.model;
/*
 *  @criado em: 16/07/2020 - {09:35}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "formasPagamento")
@Setter
@Getter
public class FormaPagamentoModel extends RepresentationModel<FormaPagamentoModel> {

    @ApiModelProperty(example = "1")
    private Long id;

    @ApiModelProperty(example = "Cartão de crédito")
    private String descricao;
}
