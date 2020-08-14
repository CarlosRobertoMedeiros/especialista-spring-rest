package com.example.algafood.client.api;
/*
 *  @criado em: 14/08/2020 - {06:32}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */

import com.example.algafood.client.model.RestauranteResumoModel;
import com.example.algafoodapi.dominio.modelo.Restaurante;
import lombok.AllArgsConstructor;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
public class RestauranteClient {

    private static final String RESOURCE_PATH = "/restaurantes";

    private RestTemplate restTemplate;
    private String url = "http://api.algafood.local:8080";

    public List<RestauranteResumoModel> listar(){
        try {
            URI resourceUri = URI.create(url + RESOURCE_PATH);

            RestauranteResumoModel[] restaurantes = restTemplate
                    .getForObject(resourceUri, RestauranteResumoModel[].class);

            return Arrays.asList(restaurantes);
        }catch(RestClientResponseException e){
            throw new ClientApiException(e.getMessage(),e);
        }
    }
}
