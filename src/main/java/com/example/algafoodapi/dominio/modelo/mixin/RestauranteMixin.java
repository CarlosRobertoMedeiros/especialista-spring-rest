package com.example.algafoodapi.dominio.modelo.mixin;
/*
 *  @criado em: 13/07/2020 - {06:02}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */

import com.example.algafoodapi.dominio.modelo.Cozinha;
import com.example.algafoodapi.dominio.modelo.Endereco;
import com.example.algafoodapi.dominio.modelo.FormaPagamento;
import com.example.algafoodapi.dominio.modelo.Produto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public abstract class RestauranteMixin {

    @JsonIgnoreProperties(value={"nome","hibernateLazyInitializer"})
    private Cozinha cozinha;

    @JsonIgnore
    private Endereco endereco;

    @JsonIgnore
    private LocalDateTime dataCadastro;

    @JsonIgnore
    private LocalDateTime dataAtualizacao;

    @JsonIgnore
    private List<FormaPagamento> formasdePagamento = new ArrayList<>();

    @JsonIgnore
    private List<Produto> produtos = new ArrayList<>();

}
