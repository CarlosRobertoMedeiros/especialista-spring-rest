package com.example.algafoodapi.api.v1.controller;
/*
 *  @criado em: 02/09/2020 - {11:00}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */

import com.example.algafoodapi.api.v1.AlgaLinks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class RootEntryPointController {

    @Autowired
    private AlgaLinks algaLinks;

    @GetMapping
    public RootEntryPointModel root(){
        RootEntryPointModel rootEntryPointModel = new RootEntryPointModel();

        rootEntryPointModel.add(algaLinks.linkToCozinhas("/v1/cozinhas"));
        rootEntryPointModel.add(algaLinks.linkToPedidos("/v1/pedidos"));
        rootEntryPointModel.add(algaLinks.linkToRestaurantes("/v1/restaurantes"));
        rootEntryPointModel.add(algaLinks.linkToGrupos("/v1/grupos"));
        rootEntryPointModel.add(algaLinks.linkToUsuarios("/v1/usuarios"));
        rootEntryPointModel.add(algaLinks.linkToPermissoes("/v1/permissoes"));
        rootEntryPointModel.add(algaLinks.linkToFormasPagamento("/v1/formas-pagamento"));
        rootEntryPointModel.add(algaLinks.linkToEstados("/v1/estados"));
        rootEntryPointModel.add(algaLinks.linkToCidades("/v1/cidades"));
        rootEntryPointModel.add(algaLinks.linkToEstatisticas("/v1/estatisticas"));

        return  rootEntryPointModel;

    }

    private static class RootEntryPointModel extends RepresentationModel<RootEntryPointModel>{

    }


}
