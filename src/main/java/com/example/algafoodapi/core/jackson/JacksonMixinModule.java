package com.example.algafoodapi.core.jackson;
/*
 *  @criado em: 13/07/2020 - {06:07}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */

import com.example.algafoodapi.dominio.modelo.Cidade;
import com.example.algafoodapi.dominio.modelo.Cozinha;
import com.example.algafoodapi.dominio.modelo.Restaurante;
import com.example.algafoodapi.dominio.modelo.mixin.CidadeMixin;
import com.example.algafoodapi.dominio.modelo.mixin.CozinhaMixin;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.stereotype.Component;

//Classe Responsável por registrar os Mixin
@Component
public class JacksonMixinModule extends SimpleModule {

    public JacksonMixinModule() {
        setMixInAnnotation(Cidade.class, CidadeMixin.class);
        setMixInAnnotation(Cozinha.class, CozinhaMixin.class);
    }
}
