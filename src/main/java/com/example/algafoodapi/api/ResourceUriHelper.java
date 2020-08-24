package com.example.algafoodapi.api;
/*
 *  @criado em: 24/08/2020 - {16:21}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */

import lombok.experimental.UtilityClass;
import org.springframework.http.HttpHeaders;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.net.URI;

@UtilityClass
public class ResourceUriHelper {

    public static void addUriInResponseHeader(Object resourceId){

        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(resourceId).toUri();

        HttpServletResponse response = ((ServletRequestAttributes)
                RequestContextHolder.getRequestAttributes()).getResponse();

        response.setHeader(HttpHeaders.LOCATION,uri.toString());
    }
}
