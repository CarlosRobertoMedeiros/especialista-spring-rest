package com.example.algafood.client;
/*
 *  @criado em: 14/08/2020 - {06:48}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */

import com.example.algafood.client.api.RestauranteClient;
import org.springframework.web.client.RestTemplate;

public class ListagemRestauranteMain {

    public static void main(String[] args) {

        RestTemplate restTemplate = new RestTemplate();

        RestauranteClient restauranteClient = new RestauranteClient(
                restTemplate, "http://api.algafood.local:8080");

        restauranteClient.listar().stream()
                .forEach(restaurante -> System.out.println(restaurante));
    }

}
