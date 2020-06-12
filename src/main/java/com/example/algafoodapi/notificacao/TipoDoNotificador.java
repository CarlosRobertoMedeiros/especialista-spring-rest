package com.example.algafoodapi.notificacao;
/*
 *  @criado em: 12/06/2020 - {17:31}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */

import org.springframework.beans.factory.annotation.Qualifier;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME) //Anotação Lida em tempo de execução
@Qualifier
public @interface TipoDoNotificador {
    NivelUrgencia value();
}
