package com.example.algafoodapi.api.exceptionhandler;
/*
 *  @criado em: 03/07/2020 - {20:55}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;

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
@ApiModel("Problema")
@JsonInclude(JsonInclude.Include.NON_NULL) //Inclui apenas valores não nulos para os campos abaixo
@Getter
@Builder
public class Problem {

    @ApiModelProperty(example = "400", position = 1)
    private Integer status;

    @ApiModelProperty(example = "2019-12-01T18:09:02.70844Z", position = 5)
    private OffsetDateTime timestamp;

    @ApiModelProperty(example = "https://algafood.com.br/dados-invalidos", position = 10)
    private String type;

    @ApiModelProperty(example = "Dados inválidos", position = 15)
    private String title;

    @ApiModelProperty(example = "Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.",
            position = 20)
    private String detail;

    @ApiModelProperty(example = "Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.",
            position = 25)
    private String userMessage;

    @ApiModelProperty(value = "Lista de objetos ou campos que geraram o erro (opcional)",
            position = 30)
    private List<Object> objects;

    @ApiModel("ObjetoProblema")
    @Getter
    @Builder
    public static class Object {

        @ApiModelProperty(example = "preco")
        private String name;

        @ApiModelProperty(example = "O preço é obrigatório")
        private String userMessage;

    }
}
