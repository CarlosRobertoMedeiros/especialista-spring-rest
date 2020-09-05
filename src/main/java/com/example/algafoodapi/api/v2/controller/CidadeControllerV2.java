package com.example.algafoodapi.api.v2.controller;
/*
 *  @criado em: 24/06/2020 - {09:02}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */

import com.example.algafoodapi.api.ResourceUriHelper;
import com.example.algafoodapi.api.v1.model.CidadeModel;
import com.example.algafoodapi.api.v2.assembler.CidadeInputDisassemblerV2;
import com.example.algafoodapi.api.v2.assembler.CidadeModelAssemblerV2;
import com.example.algafoodapi.api.v2.model.CidadeModelV2;
import com.example.algafoodapi.api.v2.model.input.CidadeInputV2;
import com.example.algafoodapi.core.web.AlgaMediaTypes;
import com.example.algafoodapi.dominio.exception.EstadoNaoEncontradoException;
import com.example.algafoodapi.dominio.exception.NegocioException;
import com.example.algafoodapi.dominio.modelo.Cidade;
import com.example.algafoodapi.dominio.repository.CidadeRepository;
import com.example.algafoodapi.dominio.service.CadastroCidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/v2/cidades", produces = MediaType.APPLICATION_JSON_VALUE)
public class CidadeControllerV2 {

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private CadastroCidadeService cidadeService;

    @Autowired
    private CidadeModelAssemblerV2 cidadeModelAssembler;

    @Autowired
    private CidadeInputDisassemblerV2 cidadeInputDisassembler;

    @GetMapping
    public CollectionModel<CidadeModelV2> listarTodas(){
        List<Cidade> todasCidades = cidadeRepository.findAll();
        return cidadeModelAssembler.toCollectionModel(todasCidades);
    }

    @GetMapping("/{id}")
    public CidadeModelV2 buscar(@PathVariable Long id){
        Cidade cidade = cidadeService.buscarOuFalhar(id);
        return  cidadeModelAssembler.toModel(cidade);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CidadeModelV2 adicionar(@RequestBody @Valid CidadeInputV2 cidadeInput){

        try {
            Cidade cidade = cidadeInputDisassembler.toDomainObject(cidadeInput);
            cidade = cidadeService.salvar(cidade);
            CidadeModelV2 cidadeModel =  cidadeModelAssembler.toModel(cidade);

            ResourceUriHelper.addUriInResponseHeader(cidadeModel.getIdCidade());

            return  cidadeModel;
        }catch (EstadoNaoEncontradoException e){
            throw new NegocioException(e.getMessage(),e);
        }
    }

    @PutMapping("/{cidadeId}")
    public CidadeModelV2 atualizar(@PathVariable Long cidadeId,
                                 @RequestBody @Valid CidadeInputV2 cidadeInput) {
        try {
            Cidade cidadeAtual = cidadeService.buscarOuFalhar(cidadeId);
            cidadeInputDisassembler.copyToDomainObject(cidadeInput, cidadeAtual);
            cidadeAtual = cidadeService.salvar(cidadeAtual);
            return cidadeModelAssembler.toModel(cidadeAtual);
        }catch (EstadoNaoEncontradoException e){
            throw new NegocioException(e.getMessage(),e);
        }
    }

    @DeleteMapping("/{cidadeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long cidadeId) {
           cidadeService.excluir(cidadeId);
    }

}
