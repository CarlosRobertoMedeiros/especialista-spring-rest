package com.example.algafoodapi.api.v1.controller;
/*
 *  @criado em: 02/09/2020 - {11:00}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */

import com.example.algafoodapi.api.v1.AlgaLinks;
import com.example.algafoodapi.core.security.AlgaSecurity;
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

    @Autowired
    private AlgaSecurity algaSecurity;

    @GetMapping
    public RootEntryPointModel root(){
        RootEntryPointModel rootEntryPointModel = new RootEntryPointModel();

        if (algaSecurity.podeConsultarCozinhas()) {
            rootEntryPointModel.add(algaLinks.linkToCozinhas("/v1/cozinhas"));
        }

        if (algaSecurity.podePesquisarPedidos()) {
            rootEntryPointModel.add(algaLinks.linkToPedidos("/v1/pedidos"));
        }

        if (algaSecurity.podeConsultarRestaurantes()) {
            rootEntryPointModel.add(algaLinks.linkToRestaurantes("/v1/restaurantes"));
        }

        if (algaSecurity.podeConsultarUsuariosGruposPermissoes()) {
            rootEntryPointModel.add(algaLinks.linkToGrupos("/v1/grupos"));
            rootEntryPointModel.add(algaLinks.linkToUsuarios("/v1/usuarios"));
            rootEntryPointModel.add(algaLinks.linkToPermissoes("/v1/permissoes"));
        }

        if (algaSecurity.podeConsultarFormasPagamento()) {
            rootEntryPointModel.add(algaLinks.linkToFormasPagamento("/v1/formas-pagamento"));
        }

        if (algaSecurity.podeConsultarEstados()) {
            rootEntryPointModel.add(algaLinks.linkToEstados("/v1/estados"));
        }

        if (algaSecurity.podeConsultarCidades()) {
            rootEntryPointModel.add(algaLinks.linkToCidades("/v1/cidades"));
        }

        if (algaSecurity.podeConsultarEstatisticas()) {
            rootEntryPointModel.add(algaLinks.linkToEstatisticas("/v1/estatisticas"));
        }

        return rootEntryPointModel;

    }

    private static class RootEntryPointModel extends RepresentationModel<RootEntryPointModel>{

    }


}