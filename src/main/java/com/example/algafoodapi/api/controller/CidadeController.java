package com.example.algafoodapi.api.controller;
/*
 *  @criado em: 24/06/2020 - {09:02}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */

import com.example.algafoodapi.api.assembler.CidadeInputDisassembler;
import com.example.algafoodapi.api.assembler.CidadeModelAssembler;
import com.example.algafoodapi.api.model.CidadeModel;
import com.example.algafoodapi.api.model.input.CidadeInput;
import com.example.algafoodapi.dominio.exception.EstadoNaoEncontradoException;
import com.example.algafoodapi.dominio.exception.NegocioException;
import com.example.algafoodapi.dominio.modelo.Cidade;
import com.example.algafoodapi.dominio.repository.CidadeRepository;
import com.example.algafoodapi.dominio.service.CadastroCidadeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "Cidades")
@RestController
@RequestMapping(value = "/cidades")
public class CidadeController {

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private CadastroCidadeService cidadeService;

    @Autowired
    private CidadeModelAssembler cidadeModelAssembler;

    @Autowired
    private CidadeInputDisassembler cidadeInputDisassembler;

    @ApiOperation("Lista as Cidades")
    @GetMapping
    public List<CidadeModel> listarTodas(){
        List<Cidade> todasCidades = cidadeRepository.findAll();
        return cidadeModelAssembler.toCollectionModel(todasCidades);
    }

    @ApiOperation("Busca a cidade por ID")
    @GetMapping("/{id}")
    public CidadeModel buscar(@ApiParam(value ="ID de uma cidade", example = "1")
                              @PathVariable Long id){
        Cidade cidade = cidadeService.buscarOuFalhar(id);
        return cidadeModelAssembler.toModel(cidade);
    }

    @ApiOperation("Cadastra uma Cidade")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CidadeModel adicionar(@ApiParam(name = "corpo", value = "Representação de uma Nova Cidade")
                                 @RequestBody @Valid CidadeInput cidadeInput){

        try {
            Cidade cidade = cidadeInputDisassembler.toDomainObject(cidadeInput);
            cidade = cidadeService.salvar(cidade);
            return cidadeModelAssembler.toModel(cidade);
        }catch (EstadoNaoEncontradoException e){
            throw new NegocioException(e.getMessage(),e);
        }
    }

    @ApiOperation("Atualiza uma Cidade por ID")
    @PutMapping("/{cidadeId}")
    public CidadeModel atualizar(@ApiParam(value = "ID de uma Cidade",example = "1")
                                 @PathVariable Long cidadeId,
                                 @ApiParam(name = "corpo", value = "Representação de uma Cidade com os novos dados")
                                 @RequestBody @Valid CidadeInput cidadeInput) {
        try {
            Cidade cidadeAtual = cidadeService.buscarOuFalhar(cidadeId);
            cidadeInputDisassembler.copyToDomainObject(cidadeInput, cidadeAtual);
            cidadeAtual = cidadeService.salvar(cidadeAtual);
            return cidadeModelAssembler.toModel(cidadeAtual);
        }catch (EstadoNaoEncontradoException e){
            throw new NegocioException(e.getMessage(),e);
        }
    }

    @ApiOperation("Exclui uma Cidade")
    @DeleteMapping("/{cidadeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@ApiParam(value = "ID de uma Cidade",example = "1")
                        @PathVariable Long cidadeId) {
           cidadeService.excluir(cidadeId);
    }

}
