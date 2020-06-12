package com.example.algafoodapi.config;
/*
 *  @criado em: 12/06/2020 - {06:52}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */

import com.example.algafoodapi.notificacao.NotificadorEmail;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AlgaConfig {

    @Bean
    public NotificadorEmail notificadorEmail(){
        NotificadorEmail notificadorEmail = new NotificadorEmail("smtp.algamailcom.br");
        notificadorEmail.setCaixaAlta(true);

        return notificadorEmail;
    }

}
