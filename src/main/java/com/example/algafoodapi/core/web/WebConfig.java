package com.example.algafoodapi.core.web;
/*
 *  @criado em: 13/08/2020 - {13:34}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedMethods("*");
//			.allowedOrigins("*")
//			.maxAge(30);
    }
}