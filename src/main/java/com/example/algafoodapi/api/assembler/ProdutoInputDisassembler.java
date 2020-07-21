package com.example.algafoodapi.api.assembler;
/*
 *  @criado em: 18/07/2020 - {23:44}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */

import com.example.algafoodapi.api.model.input.GrupoInput;
import com.example.algafoodapi.api.model.input.ProdutoInput;
import com.example.algafoodapi.dominio.modelo.Grupo;
import com.example.algafoodapi.dominio.modelo.Produto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProdutoInputDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public Produto toDomainObject(ProdutoInput produtoInput) {
        return modelMapper.map(produtoInput, Produto.class);
    }

    public void copyToDomainObject(ProdutoInput produtoInput, Produto produto) {
        modelMapper.map(produtoInput, produto);
    }
}