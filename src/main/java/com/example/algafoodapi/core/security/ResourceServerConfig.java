package com.example.algafoodapi.core.security;
/*
 *  @criado em: 07/09/2020 - {18:05}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class ResourceServerConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .anyRequest().authenticated()
            .and()
                .cors().and()
                .oauth2ResourceServer().opaqueToken();
    }
}
