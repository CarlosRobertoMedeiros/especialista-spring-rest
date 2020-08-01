package com.example.algafoodapi.dominio.service;
/*
 *  @criado em: 01/08/2020 - {17:38}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */

import com.example.algafoodapi.dominio.modelo.FotoProduto;
import com.example.algafoodapi.dominio.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
public class CatalogoFotoProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;


    @Transactional
    public FotoProduto salvar(FotoProduto foto){
        Long restauranteId = foto.getRestauranteId();
        Long produtoId = foto.getProduto().getId();

        Optional<FotoProduto> fotoExistente = produtoRepository
                .findFotoById(restauranteId,produtoId);
        if(fotoExistente.isPresent()){
            produtoRepository.delete(fotoExistente.get());
        }
        return produtoRepository.save(foto);
    }

}
