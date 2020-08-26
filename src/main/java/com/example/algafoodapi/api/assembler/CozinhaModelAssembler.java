package com.example.algafoodapi.api.assembler;
/*
 *  @criado em: 15/07/2020 - {08:13}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */

import com.example.algafoodapi.api.AlgaLinks;
import com.example.algafoodapi.api.controller.CozinhaController;
import com.example.algafoodapi.api.model.CozinhaModel;
import com.example.algafoodapi.dominio.modelo.Cozinha;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Component
public class CozinhaModelAssembler
        extends RepresentationModelAssemblerSupport<Cozinha,CozinhaModel> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AlgaLinks algaLinks;

    public CozinhaModelAssembler(){
        super(CozinhaController.class, CozinhaModel.class);
    }

    @Override
    public CozinhaModel toModel(Cozinha cozinha) {

        CozinhaModel cozinhaModel = createModelWithId(cozinha.getId(), cozinha);
        modelMapper.map(cozinha, cozinhaModel);

        cozinhaModel.add(algaLinks.linkToCozinhas("cozinhas"));

        return cozinhaModel;
    }



}
