package com.example.algafoodapi.core.squiggly;
/*
 *  @criado em: 25/07/2020 - {21:50}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;

public class TomcatCustomizer implements WebServerFactoryCustomizer<TomcatServletWebServerFactory> {

    @Override
    public void customize(TomcatServletWebServerFactory factory) {
        factory.addConnectorCustomizers(connector -> connector.setAttribute("relaxedQueryChars", "[]"));
    }

}
