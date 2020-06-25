package com.example.algafoodapi.dominio.service;
/*
 *  @criado em: 21/06/2020 - {07:30}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */

import com.example.algafoodapi.dominio.exception.EntidadeEmUsoException;
import com.example.algafoodapi.dominio.exception.EntidadeNaoEncontradaException;
import com.example.algafoodapi.dominio.modelo.Cozinha;
import com.example.algafoodapi.dominio.repository.CozinhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CadastroCozinhaService {

    @Autowired
    private CozinhaRepository cozinhaRepository;

    public Cozinha salvar(Cozinha cozinha){
        //Implementar a Regra de Negócio Aqui
        return cozinhaRepository.save(cozinha);
    }

    public void excluir(Long id){
        try {
            cozinhaRepository.deleteById(id);
        }catch (EmptyResultDataAccessException e){
            throw new EntidadeNaoEncontradaException(
                String.format("Não existe um cadastro de cozinha com o código %d ",id));
        }catch (DataIntegrityViolationException e){
            throw new EntidadeEmUsoException(
                String.format("Cozinha de Código %d não pode ser removida, pois está em uso",id));
        }
    }


}
