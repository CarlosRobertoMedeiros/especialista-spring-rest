package com.example.algafoodapi.api.openapi.controller;
/*
 *  @criado em: 20/08/2020 - {08:43}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */

import com.example.algafoodapi.api.exceptionhandler.Problem;
import com.example.algafoodapi.api.model.CidadeModel;
import com.example.algafoodapi.api.model.input.CidadeInput;
import io.swagger.annotations.*;
import org.springframework.hateoas.CollectionModel;

import java.util.List;

@Api(tags = "Cidades")
public interface CidadeControllerOpenApi {

    @ApiOperation("Lista as Cidades")
    CollectionModel<CidadeModel> listarTodas();


    @ApiOperation("Busca a cidade por ID")
    @ApiResponses({
            @ApiResponse(code = 400, message = "ID da Cidade Inválido",response = Problem.class),
            @ApiResponse(code = 404, message = "Cidade não encontrada",response = Problem.class)
    })
    CidadeModel buscar(@ApiParam(value ="ID de uma cidade", example = "1",required = true)
                              Long id);

    @ApiOperation("Cadastra uma Cidade")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Cidade Cadastrada")
    })

    CidadeModel adicionar(@ApiParam(name = "corpo", value = "Representação de uma Nova Cidade",required = true)
                                 CidadeInput cidadeInput);

    @ApiOperation("Atualiza uma Cidade por ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Cidade atualizada"),
            @ApiResponse(code = 404, message = "Cidade não encontrada", response = Problem.class)
    })

    CidadeModel atualizar(@ApiParam(value = "ID de uma Cidade",example = "1",required = true)
                                 Long cidadeId,
                                 @ApiParam(name = "corpo", value = "Representação de uma Cidade com os novos dados")
                                 CidadeInput cidadeInput);

    @ApiOperation("Exclui uma Cidade por ID")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Cidade excluida"),
            @ApiResponse(code = 404, message = "Cidade não encontrada", response = Problem.class)
    })

    void remover(@ApiParam(value = "ID de uma Cidade",example = "1",required = true)
                        Long cidadeId);

}
