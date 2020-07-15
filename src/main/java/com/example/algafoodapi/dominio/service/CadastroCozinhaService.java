package com.example.algafoodapi.dominio.service;
/*
 *  @criado em: 21/06/2020 - {07:30}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */

import com.example.algafoodapi.dominio.exception.CozinhaNaoEncontradaException;
import com.example.algafoodapi.dominio.exception.EntidadeEmUsoException;
import com.example.algafoodapi.dominio.modelo.Cozinha;
import com.example.algafoodapi.dominio.repository.CozinhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CadastroCozinhaService {

    private static final String MSG_COZINHA_EM_USO = "Cozinha de Código %d não pode ser removida, pois está em uso";

    @Autowired
    private CozinhaRepository cozinhaRepository;

    @Transactional
    public Cozinha salvar(Cozinha cozinha){
        //Implementar a Regra de Negócio Aqui
        return cozinhaRepository.save(cozinha);
    }

    @Transactional
    public void excluir(Long id){
        try {
            cozinhaRepository.deleteById(id);
            cozinhaRepository.flush();

        }catch (EmptyResultDataAccessException e){
            throw new CozinhaNaoEncontradaException(id);
        }catch (DataIntegrityViolationException e){
            throw new EntidadeEmUsoException(
                String.format(MSG_COZINHA_EM_USO,id));
        }
    }

    public Cozinha buscarOuFalhar(Long cozinhaId){
        return cozinhaRepository.findById(cozinhaId)
                .orElseThrow(()-> new CozinhaNaoEncontradaException(cozinhaId));
    }


}
