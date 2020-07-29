package com.example.algafoodapi.infraestrutura.service.report;
/*
 *  @criado em: 29/07/2020 - {06:16}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */

import com.example.algafoodapi.dominio.filter.VendaDiariaFilter;
import com.example.algafoodapi.dominio.modelo.dto.VendaDiaria;
import com.example.algafoodapi.dominio.service.VendaQueryService;
import com.example.algafoodapi.dominio.service.VendaReportService;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Service
public class PdfVendaReportServiceImpl implements VendaReportService {

    @Autowired
    private VendaQueryService vendaQueryService;

    @Override
    public byte[] emitirVendasDiarias(VendaDiariaFilter filter, String timeOffSet) throws JRException {

        InputStream inputStream = this.getClass().getResourceAsStream(
                "/relatorios/vendas-diarias.jasper");

        Map<String,Object> parametros = new HashMap<String,Object>();
        parametros.put("REPORT_LOCALE", new Locale("pt","BR"));

        List<VendaDiaria> vendasDiarias = vendaQueryService.consultarVendasDiarias(filter,timeOffSet);
        JRDataSource dataSource = new JRBeanCollectionDataSource(vendasDiarias);

        JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, parametros, dataSource);

        try {
            return JasperExportManager.exportReportToPdf(jasperPrint);
        } catch (Exception e) {
            throw new ReportException("Não foi Possível emitir relatório de vendas diárias",e);
        }
    }
}
