package com.example.algafoodapi.infraestrutura.service;/*
 *  @criado em: 29/07/2020 - {06:16}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */

import com.example.algafoodapi.dominio.filter.VendaDiariaFilter;
import com.example.algafoodapi.dominio.service.VendaReportService;
import org.springframework.stereotype.Service;

@Service
public class PdfVendaReportServiceImpl implements VendaReportService {

    @Override
    public byte[] emitirVendasDiarias(VendaDiariaFilter filter, String timeOffSet) {
        return null;
    }
}
