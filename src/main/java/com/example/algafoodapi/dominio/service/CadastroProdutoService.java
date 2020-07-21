package com.example.algafoodapi.dominio.service;
/*
 *  @criado em: 23/06/2020 - {10:24}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */

import com.example.algafoodapi.dominio.exception.EntidadeEmUsoException;
import com.example.algafoodapi.dominio.exception.EstadoNaoEncontradoException;
import com.example.algafoodapi.dominio.exception.ProdutoNaoEncontradoException;
import com.example.algafoodapi.dominio.modelo.Estado;
import com.example.algafoodapi.dominio.modelo.Produto;
import com.example.algafoodapi.dominio.repository.EstadoRepository;
import com.example.algafoodapi.dominio.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CadastroProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Transactional
    public Produto salvar(Produto produto) {
        return produtoRepository.save(produto);
    }

    public Produto buscarOuFalhar(Long restauranteId, Long produtoId) {
        return produtoRepository.findById(restauranteId, produtoId)
                .orElseThrow(() -> new ProdutoNaoEncontradoException(restauranteId, produtoId));
    }
}
