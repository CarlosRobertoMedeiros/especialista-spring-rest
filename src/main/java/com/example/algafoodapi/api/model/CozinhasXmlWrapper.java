package com.example.algafoodapi.api.model;
/*
 *  @criado em: 19/06/2020 - {19:49}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */

import com.example.algafoodapi.dominio.modelo.Cozinha;
import lombok.Data;
import lombok.NonNull;

import java.util.List;

@Data
public class CozinhasXmlWrapper {

    @NonNull
    private List<Cozinha> cozinhas;


}
