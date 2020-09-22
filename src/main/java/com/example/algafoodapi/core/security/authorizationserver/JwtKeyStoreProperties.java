package com.example.algafoodapi.core.security.authorizationserver;
/*
 *  @criado em: 14/09/2020 - {10:13}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

//import javax.validation.constraints.NotBlank;

@Setter
@Getter
@Validated
@Component
@ConfigurationProperties("algafood.jwt.keystore")
public class JwtKeyStoreProperties {

    //@NotBlank
    private Resource jksLocation;

    //@NotBlank
    private String password;

    //@NotBlank
    private String keypairAlias;

}
