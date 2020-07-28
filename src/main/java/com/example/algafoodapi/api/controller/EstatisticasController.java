package com.example.algafoodapi.api.controller;
/*
 *  @criado em: 28/07/2020 - {06:33}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */

import com.example.algafoodapi.dominio.filter.VendaDiariaFilter;
import com.example.algafoodapi.dominio.modelo.dto.VendaDiaria;
import com.example.algafoodapi.dominio.service.VendaQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/estatisticas")
public class EstatisticasController {

    @Autowired
    private VendaQueryService vendaQueryService;

    @GetMapping("/vendas-diarias")
    public List<VendaDiaria> consultarVendasDiarias(VendaDiariaFilter filtro){
        return vendaQueryService.consultarVendasDiarias(filtro);
    }

}
