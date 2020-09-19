package com.example.algafoodapi.core.security;
/*
 *  @criado em: 17/09/2020 - {12:24}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */

import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

public @interface CheckSecurity {

    public @interface Cozinhas {

        @PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('EDITAR_COZINHAS')")
        @Retention(RUNTIME)
        @Target(ElementType.METHOD)
        public @interface PodeEditar{}

        @PreAuthorize("hasAuthority('SCOPE_READ') and isAuthenticated()")
        @Retention(RUNTIME)
        @Target(ElementType.METHOD)
        public @interface PodeConsultar{}

    }


}
