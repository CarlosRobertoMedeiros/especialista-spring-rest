package com.example.algafoodapi.api.v1.controller;
/*
 *  @criado em: 19/06/2020 - {17:00}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */

import com.example.algafoodapi.api.v1.assembler.CozinhaInputDisassembler;
import com.example.algafoodapi.api.v1.assembler.CozinhaModelAssembler;
import com.example.algafoodapi.api.v1.model.CozinhaModel;
import com.example.algafoodapi.api.v1.model.input.CozinhaInput;
import com.example.algafoodapi.api.v1.openapi.controller.CozinhaControllerOpenApi;
import com.example.algafoodapi.dominio.modelo.Cozinha;
import com.example.algafoodapi.dominio.repository.CozinhaRepository;
import com.example.algafoodapi.dominio.service.CadastroCozinhaService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping(value= "/v1/cozinhas" , produces = MediaType.APPLICATION_JSON_VALUE)
public class CozinhaController implements CozinhaControllerOpenApi {



    @Autowired
    private CozinhaRepository cozinhaRepository;

    @Autowired
    private CadastroCozinhaService cozinhaService;

    @Autowired
    private CozinhaModelAssembler cozinhaModelAssembler;

    @Autowired
    private CozinhaInputDisassembler cozinhaInputDisassembler;

    @Autowired
    private PagedResourcesAssembler<Cozinha> pagedResourcesAssembler; //No Intelij mostra com erro, porém, esta funcionando

    @GetMapping
    public PagedModel<CozinhaModel> listar(@PageableDefault(size = 10) Pageable pageable){

        log.info("Listando cozinhas com paginas de {} registros ",pageable.getPageSize());

        //teste para validação
       /* if (true) {
            throw new RuntimeException("Teste de exception");
        }*/

        Page<Cozinha> cozinhasPage = cozinhaRepository.findAll(pageable);

        PagedModel<CozinhaModel> cozinhasPagedModel = pagedResourcesAssembler
                    .toModel(cozinhasPage,cozinhaModelAssembler);

        return cozinhasPagedModel;

    }

    @GetMapping("/{id}")
    public CozinhaModel buscar(@PathVariable Long id){

        Cozinha cozinha = cozinhaService.buscarOuFalhar(id);
        return cozinhaModelAssembler.toModel(cozinha);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CozinhaModel adicionar(@RequestBody @Valid CozinhaInput cozinhaInput){
        Cozinha cozinha = cozinhaInputDisassembler.toDomainObject(cozinhaInput);
        cozinha = cozinhaService.salvar(cozinha);
        return cozinhaModelAssembler.toModel(cozinha);
    }

    @PutMapping("/{id}")
    public CozinhaModel atualizar(@PathVariable Long id,
                                             @RequestBody @Valid CozinhaInput cozinhaInput){
        Cozinha cozinhaAtual = cozinhaService.buscarOuFalhar(id);
        cozinhaInputDisassembler.copyToDomainObject(cozinhaInput, cozinhaAtual);
        cozinhaAtual = cozinhaService.salvar(cozinhaAtual);

        return cozinhaModelAssembler.toModel(cozinhaAtual);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long id){
         cozinhaService.excluir(id);
    }


}
