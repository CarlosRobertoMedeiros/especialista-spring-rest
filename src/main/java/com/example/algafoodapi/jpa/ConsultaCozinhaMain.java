package com.example.algafoodapi.jpa;
/*
 *  @criado em: 17/06/2020 - {08:07}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */

import com.example.algafoodapi.AlgafoodApiApplication;
import com.example.algafoodapi.dominio.modelo.Cozinha;
import com.example.algafoodapi.dominio.repository.CozinhaRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;

import java.util.List;

public class ConsultaCozinhaMain {

    public static void main(String[] args) {

        //Fonte Primária das Configurações
        ApplicationContext applicationContext =  new SpringApplicationBuilder(AlgafoodApiApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        CozinhaRepository cozinhas = applicationContext.getBean(CozinhaRepository.class);

        //Listando
        List<Cozinha> todasCozinhas = cozinhas.listar();
        for(Cozinha cozinha : todasCozinhas){
            System.out.println(cozinha.getNome());
        }

        //Adicionando
        Cozinha cozinha1 = new Cozinha();
        cozinha1.setNome("Brasileira");

        Cozinha cozinha2 = new Cozinha();
        cozinha2.setNome("Japonesa");

        cozinha1 = cozinhas.salvar(cozinha1);
        cozinha2 = cozinhas.salvar(cozinha2);
        System.out.printf("%d - %s\n",cozinha1.getId(),cozinha1.getNome());
        System.out.printf("%d - %s\n",cozinha2.getId(),cozinha2.getNome());

        //Consulta Por Id
        Cozinha cozinha3 = cozinhas.buscar(2L);
        System.out.printf("%d - %s\n",cozinha3.getId(),cozinha3.getNome());

        //Alterar Dados
        Cozinha cozinha4 = new Cozinha();
        cozinha4.setId(1L);
        cozinha4.setNome("Brasileira");
        cozinha4 = cozinhas.salvar(cozinha4);
        System.out.printf("%d - %s\n",cozinha4.getId(),cozinha4.getNome());

        //Remover Dados
        Cozinha cozinha5 = new Cozinha();
        cozinha5.setId(1L);
        cozinhas.remover(cozinha5);


    }
}
