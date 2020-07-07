package com.example.algafoodapi.api.controller;
/*
 *  @criado em: 24/06/2020 - {09:02}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */

import com.example.algafoodapi.dominio.exception.EstadoNaoEncontradoException;
import com.example.algafoodapi.dominio.exception.NegocioException;
import com.example.algafoodapi.dominio.modelo.Cidade;
import com.example.algafoodapi.dominio.repository.CidadeRepository;
import com.example.algafoodapi.dominio.service.CadastroCidadeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/cidades")
public class CidadeController {

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private CadastroCidadeService cidadeService;

    @GetMapping
    public List<Cidade> listarTodas(){
        return cidadeRepository.findAll();
    }

    @GetMapping("/{id}")
    public Cidade buscar(@PathVariable Long id){
        return cidadeService.buscarOuFalhar(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cidade adicionar(@RequestBody @Valid Cidade cidade){

        try {
            return cidadeService.salvar(cidade);
        }catch (EstadoNaoEncontradoException e){
            throw new NegocioException(e.getMessage(),e);
        }
    }

    @PutMapping("/{cidadeId}")
    public Cidade atualizar(@PathVariable Long cidadeId,
                                       @RequestBody @Valid Cidade cidade) {
        try {
            Cidade cidadeAtual = cidadeService.buscarOuFalhar(cidadeId);
            BeanUtils.copyProperties(cidade, cidadeAtual, "id");
            return cidadeService.salvar(cidadeAtual);
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
