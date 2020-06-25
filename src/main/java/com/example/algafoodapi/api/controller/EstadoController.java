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
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/estados")
public class EstadoController {

    @Autowired
    private EstadoRepository estadoRepository;

    @Autowired
    private CadastroEstadoService estadoService;

    @GetMapping
    public List<Estado> listar(){
        return estadoRepository.findAll();
    }

    @GetMapping("/{idEstado}")
    public ResponseEntity<Estado> buscar(@PathVariable Long idEstado){
        Optional<Estado> estado = estadoRepository.findById(idEstado);

        if (estado.isPresent()){
            return ResponseEntity
                        .ok(estado.get());
        }
        return ResponseEntity
                   .status(HttpStatus.NOT_FOUND)
                    .build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Estado adicionar(@RequestBody Estado estado){
        return estadoService.salvar(estado);
    }


    @PutMapping("/{idEstado}")
    public ResponseEntity<Estado> atualizar(@PathVariable Long idEstado, @RequestBody Estado estado){
        Optional<Estado> estadoAtual = estadoRepository.findById(idEstado);

        if(estado!=null){
            BeanUtils.copyProperties(estado,estadoAtual.get(),"id");
            Estado estadoAtualSalvo = estadoService.salvar(estadoAtual.get());
            return ResponseEntity.ok(estadoAtualSalvo);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{idEstado}")
    public ResponseEntity<?> remover(@PathVariable Long idEstado){
        try{
            estadoService.excluir(idEstado);
            return ResponseEntity.noContent().build();
        }catch (EntidadeNaoEncontradaException e){
            return ResponseEntity.notFound().build();
        }catch (EntidadeEmUsoException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }




}
