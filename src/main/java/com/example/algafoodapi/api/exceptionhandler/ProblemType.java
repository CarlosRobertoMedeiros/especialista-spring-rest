package com.example.algafoodapi.api.exceptionhandler;
/*
 *  @criado em: 03/07/2020 - {22:24}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */

import lombok.Getter;

@Getter
public enum ProblemType {
    RECURSO_NAO_ENCONTRADO("/recurso-nao-encontrado", "Recurso não encontrado"),
    ENTIDADE_EM_USO("/entidade-em-uso", "Entidade em uso"),
    ERRO_NEGOCIO("/erro-negocio", "Violação de regra de negócio"),
    MENSAGEM_INCOMPREENSIVEL("/mensagem-incompreensivel", "Mensagem Incompreensível"),
    PARAMETRO_INVALIDO("/parametro-invalido", "Parâmetro Inválido"),
    DADOS_INVALIDOS("/dados-invalidos", "Dados inválidos"),
    ERRO_DE_SISTEMA("/erro-de-sistema", "Erro de sistema");

    private String title;
    private String uri;

    ProblemType(String path, String title) {
        this.uri = "https://algafood.com.br"+path;
        this.title = title;
    }
}
