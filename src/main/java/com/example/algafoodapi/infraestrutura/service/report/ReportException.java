package com.example.algafoodapi.infraestrutura.service.report;
/*
 *  @criado em: 29/07/2020 - {07:04}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */

public class ReportException extends RuntimeException{
    public ReportException(String message) {
        super(message);
    }

    public ReportException(String message, Throwable cause) {
        super(message, cause);
    }
}
