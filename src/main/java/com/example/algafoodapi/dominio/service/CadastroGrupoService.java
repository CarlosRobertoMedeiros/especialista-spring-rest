package com.example.algafoodapi.dominio.service;
/*
 *  @criado em: 18/07/2020 - {23:49}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */

import com.example.algafoodapi.dominio.exception.EntidadeEmUsoException;
import com.example.algafoodapi.dominio.exception.GrupoNaoEncontradoException;
import com.example.algafoodapi.dominio.modelo.Grupo;
import com.example.algafoodapi.dominio.repository.GrupoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CadastroGrupoService {

    private static final String MSG_GRUPO_EM_USO
            = "Grupo de código %d não pode ser removido, pois está em uso";

    @Autowired
    private GrupoRepository grupoRepository;

    @Transactional
    public Grupo salvar(Grupo grupo) {
        return grupoRepository.save(grupo);
    }

    @Transactional
    public void excluir(Long Id) {
        try {
            grupoRepository.deleteById(Id);
            grupoRepository.flush();

        } catch (EmptyResultDataAccessException e) {
            throw new GrupoNaoEncontradoException(Id);

        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format(MSG_GRUPO_EM_USO, Id));
        }
    }

    public Grupo buscarOuFalhar(Long Id) {
        return grupoRepository.findById(Id)
                .orElseThrow(() -> new GrupoNaoEncontradoException(Id));
    }

}