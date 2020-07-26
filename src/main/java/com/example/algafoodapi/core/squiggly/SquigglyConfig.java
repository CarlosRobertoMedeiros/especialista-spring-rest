package com.example.algafoodapi.core.squiggly;
/*
 *  @criado em: 25/07/2020 - {21:40}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.bohnman.squiggly.Squiggly;
import com.github.bohnman.squiggly.web.RequestSquigglyContextProvider;
import com.github.bohnman.squiggly.web.SquigglyRequestFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.Collection;

@Configuration
public class SquigglyConfig {

    @Bean
    public FilterRegistrationBean<SquigglyRequestFilter> squigglyRequestFilter(ObjectMapper objectMapper){
        Squiggly.init(objectMapper,new RequestSquigglyContextProvider("campos",null));

        //Usa os filtros apenas para pedidos e restaurantes
        Collection<String> urlPatterns = Arrays.asList("/pedidos/*","/restaurantes/*");

        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean<SquigglyRequestFilter>();
        filterRegistrationBean.setFilter(new SquigglyRequestFilter());
        filterRegistrationBean.setOrder(1);
        filterRegistrationBean.setUrlPatterns(urlPatterns);
        return filterRegistrationBean;
    }

}
