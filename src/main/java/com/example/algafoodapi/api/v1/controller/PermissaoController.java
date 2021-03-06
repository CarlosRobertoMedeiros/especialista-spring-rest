package com.example.algafoodapi.api.v1.controller;
/*
 *  @criado em: 31/08/2020 - {09:47}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */

import com.example.algafoodapi.api.v1.assembler.PermissaoModelAssembler;
import com.example.algafoodapi.api.v1.model.PermissaoModel;
import com.example.algafoodapi.api.v1.openapi.controller.PermissaoControllerOpenApi;
import com.example.algafoodapi.core.security.CheckSecurity;
import com.example.algafoodapi.dominio.modelo.Permissao;
import com.example.algafoodapi.dominio.repository.PermissaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/v1/permissoes", produces = MediaType.APPLICATION_JSON_VALUE)
public class PermissaoController implements PermissaoControllerOpenApi {

    @Autowired
    private PermissaoRepository permissaoRepository;

    @Autowired
    private PermissaoModelAssembler permissaoModelAssembler;

    @CheckSecurity.UsuariosGruposPermissoes.PodeConsultar
    @Override
    @GetMapping
    public CollectionModel<PermissaoModel> listar() {
        List<Permissao> todasPermissoes = permissaoRepository.findAll();

        return permissaoModelAssembler.toCollectionModel(todasPermissoes);
    }



}
