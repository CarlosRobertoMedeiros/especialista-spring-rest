package com.example.algafoodapi.core.validation;
/*
 *  @criado em: 08/07/2020 - {06:05}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
public class ValidationConfig {

    @Bean
    public LocalValidatorFactoryBean validator(MessageSource messageSource){
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(messageSource); //Aqui usa o message.properties como prioridade
        return bean;
    }

}
