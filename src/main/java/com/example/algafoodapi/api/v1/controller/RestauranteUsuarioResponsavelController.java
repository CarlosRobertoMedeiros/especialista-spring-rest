package com.example.algafoodapi.api.v1.controller;
/*
 *  @criado em: 21/07/2020 - {05:45}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */

import com.example.algafoodapi.api.v1.AlgaLinks;
import com.example.algafoodapi.api.v1.assembler.UsuarioModelAssembler;
import com.example.algafoodapi.api.v1.model.UsuarioModel;
import com.example.algafoodapi.api.v1.openapi.controller.RestauranteUsuarioResponsavelControllerOpenApi;
import com.example.algafoodapi.core.security.CheckSecurity;
import com.example.algafoodapi.dominio.repository.EstadoRepository;
import com.example.algafoodapi.dominio.service.CadastroRestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.algafoodapi.dominio.modelo.Restaurante;

@RestController
@RequestMapping(path = "/v1/restaurantes/{restauranteId}/responsaveis",
        produces = MediaType.APPLICATION_JSON_VALUE)
public class RestauranteUsuarioResponsavelController implements RestauranteUsuarioResponsavelControllerOpenApi {

    @Autowired
    private CadastroRestauranteService restauranteService;

    @Autowired
    private UsuarioModelAssembler usuarioModelAssembler;

    @Autowired
    private EstadoRepository estadoRepository;

    @Autowired
    private AlgaLinks algaLinks;

    @CheckSecurity.Restaurantes.PodeConsultar
    @GetMapping
    public CollectionModel<UsuarioModel> listar(@PathVariable Long restauranteId) {
        Restaurante restaurante = restauranteService.buscarOuFalhar(restauranteId);

        CollectionModel<UsuarioModel> usuariosModel = usuarioModelAssembler
                .toCollectionModel(restaurante.getResponsaveis())
                    .removeLinks()
                    .add(algaLinks.linkToRestauranteResponsaveis(restauranteId))
                    .add(algaLinks.linkToRestauranteResponsavelAssociacao(restauranteId, "associar"));

        usuariosModel.getContent().stream().forEach(usuarioModel -> {
            usuarioModel.add(algaLinks.linkToRestauranteResponsavelDesassociacao(
                    restauranteId, usuarioModel.getId(), "desassociar"));
        });

        return usuariosModel;
    }

    @CheckSecurity.Restaurantes.PodeEditar
    @DeleteMapping("/{usuarioId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> desassociar(@PathVariable Long restauranteId, @PathVariable Long usuarioId) {
        restauranteService.desassociarResponsavel(restauranteId, usuarioId);
        return ResponseEntity.noContent().build();
    }

    @CheckSecurity.Restaurantes.PodeEditar
    @PutMapping("/{usuarioId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> associar(@PathVariable Long restauranteId, @PathVariable Long usuarioId) {
        restauranteService.associarResponsavel(restauranteId, usuarioId);
        return ResponseEntity.noContent().build();
    }

}
