package com.example.algafoodapi.api.v1.openapi.controller;
/*
 *  @criado em: 20/08/2020 - {09:02}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */

import com.example.algafoodapi.api.exceptionhandler.Problem;
import com.example.algafoodapi.api.v1.model.GrupoModel;
import com.example.algafoodapi.api.v1.model.input.GrupoInput;
import io.swagger.annotations.*;
import org.springframework.hateoas.CollectionModel;

@Api(tags = "Grupos")
public interface GrupoControllerOpenApi {

    @ApiOperation("Lista os grupos")
    public CollectionModel<GrupoModel> listar();

    @ApiOperation("Busca um grupo por ID")
    @ApiResponses({
            @ApiResponse(code = 400, message = "ID da grupo inválido", response = Problem.class),
            @ApiResponse(code = 404, message = "Grupo não encontrado", response = Problem.class)
    })
    public GrupoModel buscar(
            @ApiParam(value = "ID de um grupo", example = "1", required = true)
                    Long grupoId);

    @ApiOperation("Cadastra um grupo")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Grupo cadastrado"),
    })
    public GrupoModel adicionar(
            @ApiParam(name = "corpo", value = "Representação de um novo grupo", required = true)
                    GrupoInput grupoInput);

    @ApiOperation("Atualiza um grupo por ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Grupo atualizado"),
            @ApiResponse(code = 404, message = "Grupo não encontrado", response = Problem.class)
    })
    public GrupoModel atualizar(
            @ApiParam(value = "ID de um grupo", example = "1", required = true)
                    Long grupoId,

            @ApiParam(name = "corpo", value = "Representação de um grupo com os novos dados")
                    GrupoInput grupoInput);

    @ApiOperation("Exclui um grupo por ID")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Grupo excluído"),
            @ApiResponse(code = 404, message = "Grupo não encontrado", response = Problem.class)
    })
    public void remover(
            @ApiParam(value = "ID de um grupo", example = "1", required = true)
                    Long grupoId);


}
