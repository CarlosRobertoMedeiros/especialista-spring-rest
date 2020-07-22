package com.example.algafoodapi.dominio.modelo;
/*
 *  @criado em: 01/07/2020 - {09:19}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */

public enum StatusPedido {
    CRIADO("Criado"),
    CONFIRMADO("Confirmado"),
    ENTREGUE("Entregue"),
    CANCELADO("Cancelado");

    private String descricao;

    StatusPedido(String descricao){
        this.descricao = descricao;
    }

    public String getDescricao(){
        return this.descricao;
    }


}
