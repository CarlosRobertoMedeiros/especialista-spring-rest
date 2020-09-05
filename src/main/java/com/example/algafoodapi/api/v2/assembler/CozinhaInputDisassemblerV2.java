package com.example.algafoodapi.api.v2.assembler;
/*
 *  @criado em: 04/09/2020 - {21:27}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */

import com.example.algafoodapi.api.v2.model.input.CozinhaInputV2;
import com.example.algafoodapi.dominio.modelo.Cozinha;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CozinhaInputDisassemblerV2 {

    @Autowired
    private ModelMapper modelMapper;

    public Cozinha toDomainObject(CozinhaInputV2 cozinhaInput) {
        return modelMapper.map(cozinhaInput, Cozinha.class);
    }

    public void copyToDomainObject(CozinhaInputV2 cozinhaInput, Cozinha cozinha) {
        modelMapper.map(cozinhaInput, cozinha);
    }

}
