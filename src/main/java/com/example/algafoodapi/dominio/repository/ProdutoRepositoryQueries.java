package com.example.algafoodapi.dominio.repository;
/*
 *  @criado em: 01/08/2020 - {17:42}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */

import com.example.algafoodapi.dominio.modelo.FotoProduto;

public interface ProdutoRepositoryQueries {

    FotoProduto save(FotoProduto foto);

    void delete(FotoProduto foto);


}
