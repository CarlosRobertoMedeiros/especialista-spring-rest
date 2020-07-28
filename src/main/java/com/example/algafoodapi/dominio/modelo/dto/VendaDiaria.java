package com.example.algafoodapi.dominio.modelo.dto;
/*
 *  @criado em: 28/07/2020 - {06:22}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@AllArgsConstructor
@Getter
@Setter
public class VendaDiaria {

    private Date data;
    private Long totalVendas;
    private BigDecimal totalFaturado;

}
