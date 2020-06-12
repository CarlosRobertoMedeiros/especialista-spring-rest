package com.example.algafoodapi.rest;
/*
 *  @criado em: 12/06/2020 - {05:43}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */

import com.example.algafoodapi.modelo.Cliente;
import com.example.algafoodapi.notificacao.NotificadorEmail;
import com.example.algafoodapi.service.AtivacaoClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MeuPrimeiroController {

    private AtivacaoClienteService ativacaoClienteService;

    public MeuPrimeiroController(AtivacaoClienteService ativacaoClienteService) {
        this.ativacaoClienteService = ativacaoClienteService;
        System.out.println("Meu Primeiro Controller" + ativacaoClienteService);
    }

    @GetMapping("/hello")
    @ResponseBody
    public String hello(){
        Cliente cliente = new Cliente("João","joao@gmail.com","321321",false);

        ativacaoClienteService.ativar(cliente);

        return "Hello";
    }
}
