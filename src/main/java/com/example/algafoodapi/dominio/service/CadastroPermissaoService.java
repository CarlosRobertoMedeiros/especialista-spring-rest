package com.example.algafoodapi.dominio.service;/*
 *  @criado em: 20/07/2020 - {21:08}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */

import com.example.algafoodapi.dominio.exception.PermissaoNaoEncontradaException;
import com.example.algafoodapi.dominio.modelo.Permissao;
import com.example.algafoodapi.dominio.repository.PermissaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CadastroPermissaoService {
    @Autowired
    private PermissaoRepository permissaoRepository;

    public Permissao buscarOuFalhar(Long permissaoId) {
        return permissaoRepository.findById(permissaoId)
                .orElseThrow(() -> new PermissaoNaoEncontradaException(permissaoId));
    }

}
