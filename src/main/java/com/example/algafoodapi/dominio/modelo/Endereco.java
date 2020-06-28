package com.example.algafoodapi.dominio.modelo;
/*
 *  @criado em: 27/06/2020 - {07:39}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */

import lombok.Data;

import javax.persistence.*;


@Data
@Embeddable
public class Endereco {

    @Column(name = "endereco_cep")
    private String cep;

    @Column(name = "endereco_logradouro")
    private String logradouro;

    @Column(name = "endereco_numero")
    private String numero;

    @Column(name = "endereco_complemento")
    private String complemento;

    @Column(name = "endereco_bairro")
    private String bairro;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "endereco_id_cidade")
    private Cidade cidade;

}
