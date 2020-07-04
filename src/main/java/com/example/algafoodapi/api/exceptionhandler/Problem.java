package com.example.algafoodapi.api.exceptionhandler;
/*
 *  @criado em: 03/07/2020 - {20:55}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

/**
 * Implementando o Padrão de Resposta baseado na RFC 7807 (Problem Details for HTTP APIs)
 *
 * Exemplo:
 * {
 * 	"status": 400,
 * 	"type": "https://algafood.com.br/recurso-em-uso",
 * 	"title": "Recurso em Uso",
 * 	"detail": "Não foi Possível Excluir a cozinha de código 8, porque ela está em uso",
 * }
 */
@JsonInclude(JsonInclude.Include.NON_NULL) //Inclui apenas valores não nulos para os campos abaixo
@Getter
@Builder
public class Problem {

    private Integer status;
    private String type;
    private String title;
    private String detail;


}
