package com.example.algafoodapi.core.security.authorizationserver;
/*
 *  @criado em: 21/09/2020 - {22:41}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController {

    @GetMapping("/login")
    public String login(){
        return "pages/login";
    }

    @GetMapping("/oauth/confirm_access")
    public String approval(){
        return "pages/approval";
    }

}
