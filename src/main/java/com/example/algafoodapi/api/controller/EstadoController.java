package com.example.algafoodapi.api.controller;/*
 *  @criado em: 19/06/2020 - {17:57}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */

import com.example.algafoodapi.api.assembler.EstadoInputDisassembler;
import com.example.algafoodapi.api.assembler.EstadoModelAssembler;
import com.example.algafoodapi.api.model.EstadoModel;
import com.example.algafoodapi.api.model.input.EstadoInput;
import com.example.algafoodapi.dominio.exception.EntidadeEmUsoException;
import com.example.algafoodapi.dominio.exception.EntidadeNaoEncontradaException;
import com.example.algafoodapi.dominio.modelo.Estado;
import com.example.algafoodapi.dominio.repository.EstadoRepository;
import com.example.algafoodapi.dominio.service.CadastroEstadoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/estados")
public class EstadoController {

    private static final String MSG_ESTADO_NAO_ENCONTRADA = "Não existe um cadastro de estado com o código %d";
    private static final String MSG_ESTADO_EM_USO = "Estado de Código %d não pode ser removida, pois está em uso";

    @Autowired
    private EstadoRepository estadoRepository;

    @Autowired
    private CadastroEstadoService estadoService;

    @Autowired
    private EstadoModelAssembler estadoModelAssembler;

    @Autowired
    private EstadoInputDisassembler estadoInputDisassembler;

    @GetMapping
    public List<EstadoModel> listar(){
        List<Estado> estados = estadoRepository.findAll();
        return estadoModelAssembler.toCollectionModel(estados);
    }

    @GetMapping("/{idEstado}")
    public EstadoModel buscar(@PathVariable Long idEstado){
        Estado estado = estadoService.buscarOuFalhar(idEstado);
        return estadoModelAssembler.toModel(estado);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EstadoModel adicionar(@RequestBody @Valid EstadoInput estadoInput){
        Estado estado = estadoInputDisassembler.toDomainObject(estadoInput);
        estado = estadoService.salvar(estado);
        return estadoModelAssembler.toModel(estado);
    }


    @PutMapping("/{idEstado}")
    public EstadoModel atualizar(@PathVariable Long idEstado, @RequestBody @Valid EstadoInput estadoInput){
        Estado estadoAtual = estadoService.buscarOuFalhar(idEstado);
        estadoInputDisassembler.copyToDomainObject(estadoInput, estadoAtual);
        estadoAtual = estadoService.salvar(estadoAtual);
        return estadoModelAssembler.toModel(estadoAtual);
    }

    @DeleteMapping("/{idEstado}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long idEstado) {
        estadoService.excluir(idEstado);
    }





}
