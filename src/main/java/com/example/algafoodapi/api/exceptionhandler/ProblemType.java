package com.example.algafoodapi.api.exceptionhandler;
/*
 *  @criado em: 03/07/2020 - {22:24}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */

import lombok.Getter;

@Getter
public enum ProblemType {
    ENTIDADE_NAO_ENCONTRADA("/entidade-nao-encontrada", "Entidade não encontrada"),
    ENTIDADE_EM_USO("/entidade-em-uso", "Entidade em uso"),
    ERRO_NEGOCIO("/erro-negocio", "Violação de regra de negócio"),
    MENSAGEM_INCOMPREENSIVEL("/mensagem-incompreensivel", "Mensagem Incompreensível");

    private String title;
    private String uri;

    ProblemType(String path, String title) {
        this.uri = "https://algafood.com.br"+path;
        this.title = title;
    }
}
