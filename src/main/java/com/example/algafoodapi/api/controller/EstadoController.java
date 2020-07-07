package com.example.algafoodapi.api.controller;/*
 *  @criado em: 19/06/2020 - {17:57}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */

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

    @GetMapping
    public List<Estado> listar(){
        return estadoRepository.findAll();
    }

    @GetMapping("/{idEstado}")
    public Estado buscar(@PathVariable Long idEstado){
        return estadoService.buscarOuFalhar(idEstado);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Estado adicionar(@RequestBody @Valid Estado estado){
        return estadoService.salvar(estado);
    }


    @PutMapping("/{idEstado}")
    public Estado atualizar(@PathVariable Long idEstado, @RequestBody @Valid Estado estado){
        Estado estadoAtual = estadoService.buscarOuFalhar(idEstado);
        BeanUtils.copyProperties(estado,estadoAtual,"id");
        return  estadoService.salvar(estadoAtual);
    }

    @DeleteMapping("/{idEstado}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long idEstado) {
        estadoService.excluir(idEstado);
    }





}
