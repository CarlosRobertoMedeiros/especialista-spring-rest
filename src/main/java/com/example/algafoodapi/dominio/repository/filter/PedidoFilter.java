package com.example.algafoodapi.dominio.repository.filter;
/*
 *  @criado em: 27/07/2020 - {08:35}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.OffsetDateTime;

@Setter
@Getter
public class PedidoFilter {

    private Long clienteId;
    private Long restauranteId;

    @DateTimeFormat(iso= DateTimeFormat.ISO.DATE_TIME)
    private OffsetDateTime dataCriacaoInicio;

    @DateTimeFormat(iso= DateTimeFormat.ISO.DATE_TIME)
    private OffsetDateTime dataCriacaoFim;



}
