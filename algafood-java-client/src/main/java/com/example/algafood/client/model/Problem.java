package com.example.algafood.client.model;
/*
 *  @criado em: 14/08/2020 - {06:44}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */

import lombok.Data;

@Data
public class Problem {

    private Integer status;
    private OffSetDateTime timestamp;
    private String userMessage;

}
