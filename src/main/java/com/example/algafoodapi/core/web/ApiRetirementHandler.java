package com.example.algafoodapi.core.web;/*
 *  @criado em: 06/09/2020 - {15:31}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class ApiRetirementHandler extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request.getRequestURI().startsWith("/v1/")){
            response.setStatus(HttpStatus.GONE.value()); //Recurso não existe no servidor sendo permanente
        }
        return false;
    }
}
