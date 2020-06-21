package com.example.algafoodapi.dominio.service;
/*
 *  @criado em: 21/06/2020 - {07:30}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */

import com.example.algafoodapi.dominio.modelo.Cozinha;
import com.example.algafoodapi.dominio.repository.CozinhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CadastroCozinhaService {

    @Autowired
    private CozinhaRepository cozinhaRepository;

    public Cozinha salvar(Cozinha cozinha){
        //Implementar a Regra de Negócio Aqui
        return cozinhaRepository.salvar(cozinha);
    }

}
