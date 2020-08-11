package com.example.algafoodapi.core.email;
/*
 *  @criado em: 11/08/2020 - {05:26}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */

import com.example.algafoodapi.dominio.service.EnvioEmailService;
import com.example.algafoodapi.infraestrutura.service.email.FakeEnvioEmailService;
import com.example.algafoodapi.infraestrutura.service.email.SMTPEnvioEmailService;
import com.example.algafoodapi.infraestrutura.service.email.SandboxEnvioEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmailConfig {

    @Autowired
    private EmailProperties emailProperties;

    @Bean
    public EnvioEmailService envioEmailService(){
        switch (emailProperties.getImpl()) {
            case FAKE:
                return new FakeEnvioEmailService();
            case SMTP:
                return new SMTPEnvioEmailService();
            case SANDBOX:
                return new SandboxEnvioEmailService();
            default:
                return null;
        }

    }

}
