package com.example.algafoodapi.api.v1.assembler;
/*
 *  @criado em: 20/07/2020 - {21:04}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */

import com.example.algafoodapi.api.v1.AlgaLinks;
import com.example.algafoodapi.api.v1.model.PermissaoModel;
import com.example.algafoodapi.dominio.modelo.Permissao;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class PermissaoModelAssembler
        implements RepresentationModelAssembler<Permissao, PermissaoModel> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AlgaLinks algaLinks;

    @Override
    public PermissaoModel toModel(Permissao permissao) {
        PermissaoModel permissaoModel = modelMapper.map(permissao, PermissaoModel.class);
        return permissaoModel;
    }

    @Override
    public CollectionModel<PermissaoModel> toCollectionModel(Iterable<? extends Permissao> entities) {
        return RepresentationModelAssembler.super.toCollectionModel(entities)
                .add(algaLinks.linkToPermissoes());
    }

}