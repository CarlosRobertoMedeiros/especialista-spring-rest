package com.example.algafoodapi.dominio.service;
/*
 *  @criado em: 24/06/2020 - {09:00}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */

import com.example.algafoodapi.dominio.exception.CidadeNaoEncontradaException;
import com.example.algafoodapi.dominio.exception.EntidadeEmUsoException;
import com.example.algafoodapi.dominio.exception.EntidadeNaoEncontradaException;
import com.example.algafoodapi.dominio.modelo.Cidade;
import com.example.algafoodapi.dominio.modelo.Estado;
import com.example.algafoodapi.dominio.repository.CidadeRepository;
import com.example.algafoodapi.dominio.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CadastroCidadeService {

    private static final String MSG_CIDADE_EM_USO = "Cidade de Código %d não pode ser removida, pois está em uso";

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private CadastroEstadoService estadoService;

    public Cidade salvar(Cidade cidade) {
        Long estadoId = cidade.getEstado().getId();
        Estado estado = estadoService.buscarOuFalhar(estadoId);
        cidade.setEstado(estado);
        return cidadeRepository.save(cidade);
    }

    public void excluir(Long cidadeId) {
        try {
            cidadeRepository.deleteById(cidadeId);

        } catch (EmptyResultDataAccessException e) {
            throw new CidadeNaoEncontradaException(cidadeId);

        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format(MSG_CIDADE_EM_USO, cidadeId));
        }
    }

    public Cidade buscarOuFalhar(Long id) {
        return cidadeRepository.findById(id)
                .orElseThrow(()-> new CidadeNaoEncontradaException(id));
    }
}
