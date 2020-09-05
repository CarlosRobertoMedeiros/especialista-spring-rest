package com.example.algafoodapi.api.v1.controller;
/*
 *  @criado em: 28/07/2020 - {06:33}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */

import com.example.algafoodapi.api.v1.AlgaLinks;
import com.example.algafoodapi.api.v1.openapi.controller.EstatisticasControllerOpenApi;
import com.example.algafoodapi.dominio.filter.VendaDiariaFilter;
import com.example.algafoodapi.dominio.modelo.dto.VendaDiaria;
import com.example.algafoodapi.dominio.service.VendaQueryService;
import com.example.algafoodapi.dominio.service.VendaReportService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/v1/estatisticas")
public class EstatisticasController implements EstatisticasControllerOpenApi {

    @Autowired
    private VendaQueryService vendaQueryService;

    @Autowired
    private VendaReportService vendaReportService;

    @Autowired
    private AlgaLinks algaLinks;

    @GetMapping(value="/vendas-diarias", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<VendaDiaria> consultarVendasDiarias(VendaDiariaFilter filtro,
                                                    @RequestParam(required = false, defaultValue = "+00:00") String timeOffSet){
        return vendaQueryService.consultarVendasDiarias(filtro,timeOffSet) ;
    }

    @GetMapping(value= "/vendas-diarias", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> consultarVendasDiariasPDF(VendaDiariaFilter filtro,
                                                                 @RequestParam(required = false, defaultValue = "+00:00") String timeOffSet) throws JRException {
        byte[] bytesPdf = vendaReportService.emitirVendasDiarias(filtro,timeOffSet);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION,"attachment;filename=vendas-diarias.pdf"); //Fazer Download


        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .headers(headers)
                .body(bytesPdf);
    }

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public EstatisticasModel estatisticas() {

        EstatisticasModel estatisticasModel = new EstatisticasModel();
        estatisticasModel.add(algaLinks.linkToEstatisticasVendasDiarias("vendas-diarias"));
        return estatisticasModel;
    }

    public static class EstatisticasModel extends RepresentationModel<EstatisticasModel> {
    }

}
