package com.example.algafoodapi.core.web;
/*
 *  @criado em: 13/08/2020 - {13:34}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.ShallowEtagHeaderFilter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private ApiDeprecationHandler apiDeprecationHandler;

    //Aqui é Para Depreciar uma versão da API
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(apiDeprecationHandler);
    }

    @Bean
    public ShallowEtagHeaderFilter shallowETagHeaderFilter(){
        return new ShallowEtagHeaderFilter();
    }

//    @Override
//    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
//        configurer.defaultContentType(AlgaMediaTypes.V2_APPLICATION_JSON);
//    }
}
