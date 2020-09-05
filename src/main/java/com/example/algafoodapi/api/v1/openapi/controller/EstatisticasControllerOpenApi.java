package com.example.algafoodapi.api.v1.openapi.controller;
/*
 *  @criado em: 23/08/2020 - {13:51}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */

import com.example.algafoodapi.api.v1.controller.EstatisticasController;
import com.example.algafoodapi.dominio.filter.VendaDiariaFilter;
import com.example.algafoodapi.dominio.modelo.dto.VendaDiaria;
import io.swagger.annotations.*;
import net.sf.jasperreports.engine.JRException;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Api(tags = "Estatísticas")
public interface EstatisticasControllerOpenApi {

    @ApiOperation("Consulta estatísticas de vendas diárias")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "restauranteId", value = "ID do restaurante",
                    example = "1", dataType = "int"),
            @ApiImplicitParam(name = "dataCriacaoInicio", value = "Data/hora inicial da criação do pedido",
                    example = "2019-12-01T00:00:00Z", dataType = "date-time"),
            @ApiImplicitParam(name = "dataCriacaoFim", value = "Data/hora final da criação do pedido",
                    example = "2019-12-02T23:59:59Z", dataType = "date-time")
    })
    List<VendaDiaria> consultarVendasDiarias(
            VendaDiariaFilter filtro,

            @ApiParam(value = "Deslocamento de horário a ser considerado na consulta em relação ao UTC",
                    defaultValue = "+00:00")
                    String timeOffset);

    ResponseEntity<byte[]> consultarVendasDiariasPDF(
            VendaDiariaFilter filtro,
            String timeOffset) throws JRException;

    @ApiOperation(value = "Estatísticas", hidden = true)
    EstatisticasController.EstatisticasModel estatisticas();



}
